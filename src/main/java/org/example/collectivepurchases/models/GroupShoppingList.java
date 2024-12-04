package org.example.collectivepurchases.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder(toBuilder = true)
@Table(name = "group_shopping_lists")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "groupShoppingList")
    private List<PurchasePlan> purchasePlans;

    @ManyToMany
    @JoinTable(
        name = "group_shopping_list_users",
        joinColumns = @JoinColumn(name = "group_shopping_list_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
}