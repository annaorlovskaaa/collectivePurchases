package org.example.collectivepurchases.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.collectivepurchases.models.composite.Item;

import java.util.ArrayList;

@Entity
@Builder
@Table(name = "purchase_plans")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchasePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_shopping_list_id")
    private GroupShoppingList groupShoppingList;
    private boolean isImportant;
    private boolean purchased;
    private double quantity;
    private double actualPrice;
    @ElementCollection
    private ArrayList<String> receiptUrl;
}