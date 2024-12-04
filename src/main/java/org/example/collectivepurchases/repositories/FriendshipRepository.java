package org.example.collectivepurchases.repositories;

import org.example.collectivepurchases.models.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    Optional<Friendship> findByUserId(Long userId);
}