package org.example.collectivepurchases.models.composite;

public interface Component {
    String getName();
    double getPrice();
    void add(Component component);
    void remove(Component component);
    Component getChild(int i);
}