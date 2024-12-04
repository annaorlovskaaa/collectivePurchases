package org.example.collectivepurchases.services.purchaseplan;

import org.example.collectivepurchases.dtos.PurchasePlanDto;
import org.example.collectivepurchases.models.PurchasePlan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PurchasePlanService {
    PurchasePlanDto createPurchasePlan(PurchasePlan purchasePlan);
    List<PurchasePlanDto> getPurchasePlansByGroupShoppingListId(Long groupShoppingListId);
    PurchasePlanDto createImportantPurchasePlan(PurchasePlan purchasePlan);
    PurchasePlanDto removeImportantPurchasePlan(Long purchasePlanId);

    PurchasePlanDto addReceiptToPurchasePlan(Long purchasePlanId, String receiptUrl);
    PurchasePlanDto updateReceiptInPurchasePlan(Long purchasePlanId, String receiptUrl);
    void deleteReceiptFromPurchasePlan(Long purchasePlanId);
}