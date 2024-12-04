package org.example.collectivepurchases.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchasePlanDto {
    private Long id;
    private Long itemId;
    private Long userId;
    private Long groupShoppingListId;
    private boolean purchased;
    private double quantity;
    private double actualPrice;
    private boolean isImportant;
    private ArrayList<String> receiptUrl;
}
