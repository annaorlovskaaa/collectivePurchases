package org.example.collectivepurchases.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Builder
@Table(name = "friendships")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @ManyToMany
    @JoinTable(
            name = "friendship_friends",
            joinColumns = @JoinColumn(name = "friendship_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<User> friends;

    public List<Long> getFriendIds() {
        return friends.stream()
                .map(User::getId)
                .collect(Collectors.toList());
    }
}