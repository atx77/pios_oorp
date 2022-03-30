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

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllActiveCategories() {
        return categoryRepository.findAllByActiveIsTrueAndParentCategoryIsNull();
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
}
