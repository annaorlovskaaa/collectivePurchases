package org.example.collectivepurchases.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.collectivepurchases.dtos.PurchasePlanDto;
import org.example.collectivepurchases.facade.CollectivePurchasesFacade;
import org.example.collectivepurchases.models.PurchasePlan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/purchase-plans")
@Slf4j
public class PurchasePlanController {

    private final CollectivePurchasesFacade collectivePurchasesFacade;

    @PostMapping("/create")
    public ResponseEntity<Object> createPurchasePlan(@RequestBody PurchasePlan purchasePlan) {
        PurchasePlanDto createdPlan = collectivePurchasesFacade.createPurchasePlan(purchasePlan);
        return ResponseEntity.ok().body(Map.of("message", "Purchase plan created successfully", "purchasePlan", createdPlan));
    }

    @GetMapping("/group/{groupShoppingListId}")
    public ResponseEntity<Object> getPurchasePlansByGroupShoppingListId(@PathVariable Long groupShoppingListId) {
        List<PurchasePlanDto> purchasePlans = collectivePurchasesFacade.getPurchasePlansByGroupShoppingListId(groupShoppingListId);
        return purchasePlans.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No purchase plans found"))
                : ResponseEntity.ok(Map.of("purchasePlans", purchasePlans));
    }

    @PostMapping("/create-important")
    public ResponseEntity<Object> createImportantPurchasePlan(@RequestBody PurchasePlan purchasePlan) {
        PurchasePlanDto createdPlan = collectivePurchasesFacade.createImportantPurchasePlan(purchasePlan);
        return ResponseEntity.ok().body(Map.of("message", "Important purchase plan created successfully", "purchasePlan", createdPlan));
    }

    @PostMapping("/{purchasePlanId}/receipts")
    public ResponseEntity<PurchasePlanDto> addReceiptToPurchasePlan(@PathVariable Long purchasePlanId, @RequestBody String receiptUrl) {
        PurchasePlanDto response = collectivePurchasesFacade.addReceiptToPurchasePlan(purchasePlanId, receiptUrl);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{purchasePlanId}/receipts")
    public ResponseEntity<PurchasePlanDto> updateReceiptInPurchasePlan(@PathVariable Long purchasePlanId, @RequestBody String receiptUrl) {
        PurchasePlanDto response = collectivePurchasesFacade.updateReceiptInPurchasePlan(purchasePlanId, receiptUrl);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{purchasePlanId}/receipts")
    public ResponseEntity<Void> deleteReceiptFromPurchasePlan(@PathVariable Long purchasePlanId) {
        collectivePurchasesFacade.deleteReceiptFromPurchasePlan(purchasePlanId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/important/{purchasePlanId}")
    public ResponseEntity<PurchasePlanDto> removeImportantPurchasePlan(@PathVariable Long purchasePlanId) {
        PurchasePlanDto response = collectivePurchasesFacade.removeImportantPurchasePlan(purchasePlanId);
        return ResponseEntity.ok(response);
    }
}