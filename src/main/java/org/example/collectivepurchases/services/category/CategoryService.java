package org.example.collectivepurchases.services.category;


import org.example.collectivepurchases.models.composite.Category;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    Category getOrCreateCategory(String name);
}
