package org.example.collectivepurchases.services.category;

import lombok.AllArgsConstructor;
import org.example.collectivepurchases.models.composite.Category;
import org.example.collectivepurchases.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public Category getOrCreateCategory(String name) {
        return categoryRepository.findByName(name)
                .orElseGet(() -> categoryRepository.save(new Category(null, name, new ArrayList<>())));
    }
}