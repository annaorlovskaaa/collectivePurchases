package org.example.collectivepurchases.repositories;

import org.example.collectivepurchases.models.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<WishList, Long> {
    List<WishList> findByUserId(Long userId);
}
