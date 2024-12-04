package org.example.collectivepurchases.models.composite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import CategoryComponent;
import Component;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category implements Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "category_id")
    private List<CategoryComponent> components = new ArrayList<>();

    @Override
    public void add(Component component) {
        components.add((CategoryComponent) component);
    }

    @Override
    public void remove(Component component) {
        components.remove(component);
    }

    @Override
    public Component getChild(int i) {
        return components.get(i);
    }

    @Override
    public double getPrice() {
        return components.stream().mapToDouble(Component::getPrice).sum();
    }

    @Override
    public String getName() {
        return name;
    }
}