package org.example.collectivepurchases.models.composite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category_components")
public class CategoryComponent implements Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;

    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException("Leaf node doesn't support add operation.");
    }

    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException("Leaf node doesn't support remove operation.");
    }

    @Override
    public Component getChild(int i) {
        throw new UnsupportedOperationException("Leaf node doesn't support getChild operation.");
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }
}