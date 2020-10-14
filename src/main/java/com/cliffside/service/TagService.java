package com.cliffside.service;

import com.cliffside.entity.BlogTagCount;
import com.cliffside.util.PageQueryUtil;
import com.cliffside.util.PageResult;

import java.util.List;

public interface TagService {
    /**
     * 查询标签的分页数据
     *
     * @param pageUtil
     * @return
     */
    PageResult getBlogTagPage(PageQueryUtil pageUtil);

    Boolean saveTag(String tagName);

    Boolean deleteBatch(Integer[] ids);

    List<BlogTagCount> getBlogTagCountForIndex();

}