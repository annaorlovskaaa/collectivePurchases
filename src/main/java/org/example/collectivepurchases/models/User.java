package org.example.collectivepurchases.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.collectivepurchases.models.enums.Role;

import java.util.List;

@Entity
@Builder
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private boolean isActive;
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "user_friendship_ids",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friendship_id")
    )
    private List<Friendship> friendships;

    @OneToMany(mappedBy = "user")
    private List<PurchasePlan> purchasePlans;

    @OneToMany(mappedBy = "user")
    private List<WishList> wishlists;
}