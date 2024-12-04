package org.example.collectivepurchases.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupShoppingListDto {
    private Long id;
    private String name;
    private String description;
    private UserDto userDto;
}