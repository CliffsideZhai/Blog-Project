package com.cliffside.service;

import com.cliffside.entity.BlogCategory;
import com.cliffside.util.PageQueryUtil;
import com.cliffside.util.PageResult;

import java.util.List;

public interface CategoryService {

    /**
     * 查询分类的分页数据
     *
     * @param pageUtil
     * @return
     */
    PageResult getBlogCategoryPage(PageQueryUtil pageUtil);

    int getTotalCategories();

    /**
     * 添加分类数据
     *
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    Boolean saveCategory(String categoryName, String categoryIcon);

    Boolean deleteBatch(Integer[] ids);

    Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon);

    List<BlogCategory> getAllCategories();

    BlogCategory selectById(Integer id);
}
