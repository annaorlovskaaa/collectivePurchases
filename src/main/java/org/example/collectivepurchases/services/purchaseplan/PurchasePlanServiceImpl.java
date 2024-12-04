package org.example.collectivepurchases.services.purchaseplan;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.collectivepurchases.dtos.PurchasePlanDto;
import org.example.collectivepurchases.models.PurchasePlan;
import org.example.collectivepurchases.repositories.PurchasePlanRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PurchasePlanServiceImpl implements PurchasePlanService {
    private final PurchasePlanRepository purchasePlanRepository;
    private final PurchasePlanDecorator importantPurchasePlanDecorator = new ImportantPurchasePlanDecorator();

    @Override
    public PurchasePlanDto createPurchasePlan(PurchasePlan purchasePlan) {
        PurchasePlan savedPlan = purchasePlanRepository.save(purchasePlan);
        return buildPurchasePlanDto(savedPlan);
    }

    @Override
    public List<PurchasePlanDto> getPurchasePlansByGroupShoppingListId(Long groupShoppingListId) {
        List<PurchasePlan> purchasePlans = purchasePlanRepository.findByGroupShoppingListId(groupShoppingListId);
        return purchasePlans.stream()
                .map(PurchasePlanServiceImpl::buildPurchasePlanDto)
                .collect(Collectors.toList());
    }

    @Override
    public PurchasePlanDto createImportantPurchasePlan(PurchasePlan purchasePlan) {
        PurchasePlan decoratedPlan = importantPurchasePlanDecorator.decorate(purchasePlan);
        return createPurchasePlan(decoratedPlan);
    }

    @Override
    public PurchasePlanDto removeImportantPurchasePlan(Long purchasePlanId) {
        PurchasePlan purchasePlan = purchasePlanRepository.findById(purchasePlanId).orElseThrow(() -> new IllegalArgumentException("Purchase plan not found"));
        purchasePlan.setImportant(false);
        PurchasePlan updatedPlan = purchasePlanRepository.save(purchasePlan);
        return buildPurchasePlanDto(updatedPlan);
    }

    @Override
    public PurchasePlanDto addReceiptToPurchasePlan(Long purchasePlanId, String receiptUrl) {
        PurchasePlan purchasePlan = purchasePlanRepository.findById(purchasePlanId)
                .orElseThrow(() -> new IllegalArgumentException("Purchase plan not found"));
        if (purchasePlan.getReceiptUrl() == null) {
            purchasePlan.setReceiptUrl(new ArrayList<>());
        }
        purchasePlan.getReceiptUrl().add(receiptUrl);
        PurchasePlan updatedPlan = purchasePlanRepository.save(purchasePlan);
        return buildPurchasePlanDto(updatedPlan);
    }

    @Override
    public PurchasePlanDto updateReceiptInPurchasePlan(Long purchasePlanId, String receiptUrl) {
        PurchasePlan purchasePlan = purchasePlanRepository.findById(purchasePlanId)
                .orElseThrow(() -> new IllegalArgumentException("Purchase plan not found"));
        purchasePlan.getReceiptUrl().clear();
        purchasePlan.getReceiptUrl().add(receiptUrl);
        PurchasePlan updatedPlan = purchasePlanRepository.save(purchasePlan);
        return buildPurchasePlanDto(updatedPlan);
    }

    @Override
    public void deleteReceiptFromPurchasePlan(Long purchasePlanId) {
        PurchasePlan purchasePlan = purchasePlanRepository.findById(purchasePlanId)
                .orElseThrow(() -> new IllegalArgumentException("Purchase plan not found"));
        if (purchasePlan.getReceiptUrl() != null) {
            purchasePlan.getReceiptUrl().clear();
        }
        purchasePlanRepository.save(purchasePlan);
    }

    private static PurchasePlanDto buildPurchasePlanDto(PurchasePlan purchasePlan) {
        return PurchasePlanDto.builder()
                .id(purchasePlan.getId())
                .itemId(purchasePlan.getItem().getId())
                .userId(purchasePlan.getUser().getId())
                .groupShoppingListId(purchasePlan.getGroupShoppingList().getId())
                .purchased(purchasePlan.isPurchased())
                .quantity(purchasePlan.getQuantity())
                .actualPrice(purchasePlan.getActualPrice())
                .isImportant(purchasePlan.isImportant())
                .receiptUrl(purchasePlan.getReceiptUrl())
                .build();
    }
}