package org.example.collectivepurchases.services.purchaseplan;

import org.example.collectivepurchases.models.PurchasePlan;

public class ImportantPurchasePlanDecorator implements PurchasePlanDecorator {
    @Override
    public PurchasePlan decorate(PurchasePlan purchasePlan) {
        purchasePlan.setImportant(true);
        return purchasePlan;
    }
}