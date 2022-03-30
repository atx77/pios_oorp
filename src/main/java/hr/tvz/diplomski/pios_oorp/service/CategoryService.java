package hr.tvz.diplomski.pios_oorp.service;

import hr.tvz.diplomski.pios_oorp.domain.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllActiveCategories();
    void createNewCategory(Long parentCategoryId, String categoryName);
}
