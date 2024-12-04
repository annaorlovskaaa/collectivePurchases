package org.example.collectivepurchases.repositories;

import org.example.collectivepurchases.models.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListItemRepository extends JpaRepository<WishListItem, Long> {
}
