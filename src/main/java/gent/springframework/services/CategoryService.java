package gent.springframework.services;

import gent.springframework.api.v1.model.CategoryDTO;
import gent.springframework.domain.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryByName(String name);
}
