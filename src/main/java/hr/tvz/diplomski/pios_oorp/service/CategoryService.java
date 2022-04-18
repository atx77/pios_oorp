package hr.tvz.diplomski.pios_oorp.service;

import hr.tvz.diplomski.pios_oorp.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    /**
     * @return list of all parent {@link Category}
     */
    List<Category> getAllParentCategories();

    /**
     * Creates new {@link Category} as parent category if parentCategoryId is empty or as subcategory of category with id from parentCategoryId
     * @param parentCategoryId Id of parent {@link Category}
     * @param categoryName name of {@link Category} to create
     */
    void createNewCategory(Long parentCategoryId, String categoryName);

    /**
     * Fetches {@link Category} by Id
     * @param id id of {@link Category} to fetch
     * @return fetched {@link Category}
     */
    Optional<Category> getCategoryById(Long id);

    /**
     * Changes visibility of {@link Category} by Id
     * @param categoryId Id of {@link Category} to change visibility
     */
    void changeCategoryVisibility(Long categoryId);
}
