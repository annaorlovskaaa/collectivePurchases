package org.example.collectivepurchases.services.groupshoppinglist;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.collectivepurchases.dtos.GroupShoppingListDto;
import org.example.collectivepurchases.models.GroupShoppingList;
import org.example.collectivepurchases.models.User;
import org.example.collectivepurchases.repositories.GroupShoppingListRepository;
import org.example.collectivepurchases.services.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class GroupShoppingListServiceImpl implements GroupShoppingListService {

    private final GroupShoppingListRepository groupShoppingListRepository;
    private final UserService userService;

    @Override
    public Boolean createGroupShoppingList(String token, GroupShoppingListDto groupShoppingListDto) {
        log.info("Creating group shopping list with name: {}", groupShoppingListDto.getName());

        User user = userService.getUserByToken(token);
        if (user == null) {
            log.warn("User not found for token: {}", token);
            return false;
        }

        GroupShoppingList groupShoppingList = buildGroupShoppingList(groupShoppingListDto);
        groupShoppingList.setUser(user);
        groupShoppingListRepository.save(groupShoppingList);
        return true;
    }


    @Override
    public List<GroupShoppingListDto> getAllGroupShoppingLists() {
        log.info("Retrieving all group shopping lists");
        List<GroupShoppingList> lists = groupShoppingListRepository.findAll();
        return lists.stream()
                .map(this::buildGroupShoppingListDto)
                .collect(Collectors.toList());
    }

    @Override
    public GroupShoppingListDto getGroupShoppingListById(Long id, String token) {
        log.info("Retrieving group shopping list with id: {}", id);
        Optional<GroupShoppingList> optionalGroupShoppingList = groupShoppingListRepository.findById(id);
        if (optionalGroupShoppingList.isEmpty()) {
            log.warn("Group shopping list not found with id: {}", id);
            return null;
        }
        return buildGroupShoppingListDto(optionalGroupShoppingList.get());
    }

    @Override
    public Boolean updateGroupShoppingList(Long id, GroupShoppingListDto groupShoppingListDto) {
        log.info("Updating group shopping list with id: {}", id);
        Optional<GroupShoppingList> optionalGroupShoppingList = groupShoppingListRepository.findById(id);
        if (optionalGroupShoppingList.isEmpty()) {
            log.warn("Group shopping list not found with id: {}", id);
            return false;
        }
        GroupShoppingList groupShoppingList = optionalGroupShoppingList.get().toBuilder()
                .name(groupShoppingListDto.getName())
                .description(groupShoppingListDto.getDescription())
                .build();
        groupShoppingListRepository.save(groupShoppingList);
        return true;
    }

    @Override
    public Boolean deleteGroupShoppingList(Long id) {
        log.info("Deleting group shopping list with id: {}", id);
        if (groupShoppingListRepository.existsById(id)) {
            groupShoppingListRepository.deleteById(id);
            return true;
        }
        log.warn("Group shopping list not found with id: {}", id);
        return false;
    }

    @Override
    public Boolean addUserToGroupShoppingList(Long groupShoppingListId, Long userId) {
        log.info("Adding user with id: {} to group shopping list with id: {}", userId, groupShoppingListId);
        Optional<GroupShoppingList> optionalGroupShoppingList = groupShoppingListRepository.findById(groupShoppingListId);
        if (optionalGroupShoppingList.isEmpty()) {
            log.warn("Group shopping list not found with id: {}", groupShoppingListId);
            return false;
        }
        User user = userService.getUserById(userId);
        GroupShoppingList groupShoppingList = optionalGroupShoppingList.get();
        groupShoppingList.setUser(user);
        groupShoppingListRepository.save(groupShoppingList);
        return true;
    }

    @Override
    public boolean isUserInGroup(Long groupShoppingListId, Long userId) {
        GroupShoppingList groupShoppingList = groupShoppingListRepository.findById(groupShoppingListId)
                .orElseThrow(() -> new RuntimeException("Group shopping list not found"));
        return groupShoppingList.getUsers().stream().anyMatch(user -> user.getId().equals(userId)) ||
                groupShoppingList.getUser().getId().equals(userId);
    }

    private GroupShoppingList buildGroupShoppingList(GroupShoppingListDto groupShoppingListDto) {
        return GroupShoppingList.builder()
                .name(groupShoppingListDto.getName())
                .description(groupShoppingListDto.getDescription())
                .build();
    }

    private GroupShoppingListDto buildGroupShoppingListDto(GroupShoppingList groupShoppingList) {
        return GroupShoppingListDto.builder()
                .id(groupShoppingList.getId())
                .name(groupShoppingList.getName())
                .description(groupShoppingList.getDescription())
                .build();
    }
}