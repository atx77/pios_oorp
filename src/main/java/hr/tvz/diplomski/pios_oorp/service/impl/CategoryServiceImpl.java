package hr.tvz.diplomski.pios_oorp.service.impl;

import hr.tvz.diplomski.pios_oorp.domain.Category;
import hr.tvz.diplomski.pios_oorp.repository.CategoryRepository;
import hr.tvz.diplomski.pios_oorp.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllParentCategories() {
        return categoryRepository.findAllByParentCategoryIsNull();
    }

    @Transactional
    @Override
    public void createNewCategory(Long parentCategoryId, String categoryName) {
        Assert.notNull(categoryName, "Field categoryName cannot be null");

        Category parentCategory = null;
        if (parentCategoryId != null) {
            parentCategory = categoryRepository.getById(parentCategoryId);
            if (parentCategory == null) {
                throw new IllegalArgumentException("No category with id " + parentCategoryId + "found");
            }
        }

        Category category = new Category();
        category.setParentCategory(parentCategory);
        category.setActive(true);
        category.setName(StringUtils.normalizeSpace(categoryName));
        categoryRepository.save(category);

        if (parentCategory != null) {
            parentCategory.getSubCategories().add(category);
            categoryRepository.save(parentCategory);
        }
    }

    @Override
    public Optional<Category> getCategoryById(Long id) throws IllegalArgumentException{
        return categoryRepository.findById(id);
    }

    @Override
    public void changeCategoryVisibility(Long categoryId) {
        Category category = getCategoryById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category " + categoryId + "not found!"));
        category.setActive(!category.isActive());
        categoryRepository.save(category);
    }
}
