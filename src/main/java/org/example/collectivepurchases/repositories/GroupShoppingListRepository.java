package org.example.collectivepurchases.repositories;


import org.example.collectivepurchases.models.GroupShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupShoppingListRepository extends JpaRepository<GroupShoppingList, Long> {
}