package org.example.collectivepurchases.services.purchaseplan;

import org.example.collectivepurchases.models.PurchasePlan;


public interface PurchasePlanDecorator {
    PurchasePlan decorate(PurchasePlan purchasePlan);
}