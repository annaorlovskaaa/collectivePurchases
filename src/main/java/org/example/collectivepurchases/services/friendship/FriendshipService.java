package org.example.collectivepurchases.services.friendship;

import org.example.collectivepurchases.dtos.FriendshipDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FriendshipService {
    Boolean addFriendship(FriendshipDto friendshipDto);

    boolean isFriend(Long userId, Long friendId);
    List<FriendshipDto> getFriendshipsByUserId(Long userId);

    Boolean deleteFriendship(Long userId, Long friendId);
}