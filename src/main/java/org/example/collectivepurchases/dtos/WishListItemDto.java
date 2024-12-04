package org.example.collectivepurchases.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishListItemDto {
    private Long id;
    private String name;
    private Long wishListId;
    private Long itemId;
}