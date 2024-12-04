package org.example.collectivepurchases.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Table(name = "wishlist_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WishListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long itemId;
    private String name;
    @ManyToOne
    @JoinColumn(name = "wish_list_id")
    private WishList wishList;
}