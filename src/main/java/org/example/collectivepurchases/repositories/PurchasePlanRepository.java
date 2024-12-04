package org.example.collectivepurchases.repositories;

import org.example.collectivepurchases.models.PurchasePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PurchasePlanRepository extends JpaRepository<PurchasePlan, Long> {
    List<PurchasePlan> findByGroupShoppingListId(Long groupShoppingListId);
}