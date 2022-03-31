package hr.tvz.diplomski.pios_oorp.service;

import hr.tvz.diplomski.pios_oorp.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllParentCategories();
    void createNewCategory(Long parentCategoryId, String categoryName);
    Optional<Category> getCategoryById(Long id);
    void changeCategoryVisibility(Long categoryId);
}
