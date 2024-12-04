package org.example.collectivepurchases.models.composite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private double price;
    private double quantity;

    public static class Builder {
        private Long id;
        private String name;
        private Category category;
        private double price;
        private double quantity;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder category(Category category) {
            this.category = category;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder quantity(double quantity) {
            this.quantity = quantity;
            return this;
        }

        public Item build() {
            Item item = new Item();
            item.id = this.id;
            item.name = this.name;
            item.category = this.category;
            item.price = this.price;
            item.quantity = this.quantity;
            return item;
        }
    }
}