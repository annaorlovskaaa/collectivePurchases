package org.example.collectivepurchases.services.friendship;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.collectivepurchases.dtos.FriendshipDto;
import org.example.collectivepurchases.models.Friendship;
import org.example.collectivepurchases.models.User;
import org.example.collectivepurchases.repositories.FriendshipRepository;
import org.example.collectivepurchases.services.user.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class FriendshipServiceImpl implements FriendshipService {

    private final FriendshipRepository friendshipRepository;
    private final UserService userService;

    @Override
    public Boolean addFriendship(FriendshipDto friendshipDto) {
        log.info("Adding friendship for userId: {} and friendIds: {}", friendshipDto.getUserId(), friendshipDto.getFriendIds());

        if (!userService.userExists(friendshipDto.getUserId())) {
            log.warn("User with userId: {} does not exist", friendshipDto.getUserId());
            return false;
        }

        if (friendshipDto.getFriendIds().contains(friendshipDto.getUserId())) {
            log.warn("User with userId: {} cannot add themselves as a friend", friendshipDto.getUserId());
            return false;
        }

        if (friendshipExists(friendshipDto)) {
            log.warn("Friendship already exists for userId: {} and friendIds: {}", friendshipDto.getUserId(), friendshipDto.getFriendIds());
            return false;
        }

        Friendship friendship = buildFriendship(friendshipDto);
        friendshipRepository.save(friendship);
        return true;
    }

    @Override
    public List<FriendshipDto> getFriendshipsByUserId(Long userId) {
        return friendshipRepository.findByUserId(userId)
                .stream()
                .map(friendship -> FriendshipDto.builder()
                        .id(friendship.getId())
                        .userId(friendship.getUserId())
                        .friendIds(friendship.getFriendIds())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Boolean deleteFriendship(Long userId, Long friendId) {
        Optional<Friendship> optionalFriendship = friendshipRepository.findByUserId(userId);
        if (optionalFriendship.isEmpty()) {
            log.warn("Friendship not found for userId: {} and friendId: {}", userId, friendId);
            return false;
        }
        Friendship friendship = optionalFriendship.get();
        List<User> friends = friendship.getFriends();
        User friendToRemove = friends.stream()
                .filter(friend -> friend.getId().equals(friendId))
                .findFirst()
                .orElse(null);
        if (friendToRemove != null) {
            friends.remove(friendToRemove);
            friendshipRepository.save(friendship);
            return true;
        }
        log.warn("Friendship not found for userId: {} and friendId: {}", userId, friendId);
        return false;
    }

    private Friendship buildFriendship(FriendshipDto friendshipDto) {
        List<User> friends = friendshipDto.getFriendIds().stream()
                .map(userService::getUserById)
                .collect(Collectors.toList());

        return Friendship.builder()
                .userId(friendshipDto.getUserId())
                .friends(friends)
                .build();
    }

    private boolean friendshipExists(FriendshipDto friendshipDto) {
        if (friendshipRepository.findByUserId(friendshipDto.getUserId()).isEmpty()) {
            return false;
        }
        return friendshipRepository.findByUserId(friendshipDto.getUserId())
                .stream()
                .anyMatch(friendship -> new HashSet<>(friendship.getFriendIds()).containsAll(friendshipDto.getFriendIds()));
    }

    @Override
    public boolean isFriend(Long userId, Long friendId) {
        Optional<Friendship> optionalFriendship = friendshipRepository.findByUserId(userId);
        if (optionalFriendship.isEmpty()) {
            return false;
        }
        Friendship friendship = optionalFriendship.get();
        return friendship.getFriends().stream()
                .anyMatch(friend -> friend.getId().equals(friendId));
    }

}