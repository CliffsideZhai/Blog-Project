package com.cliffside.service;

import com.cliffside.entity.BlogLink;
import com.cliffside.util.PageQueryUtil;
import com.cliffside.util.PageResult;

import java.util.List;
import java.util.Map;

public interface LinkService {
    /**
     * 查询友链的分页数据
     *
     * @param pageUtil
     * @return
     */
    PageResult getBlogLinkPage(PageQueryUtil pageUtil);

    int getTotalLinks();


    Boolean saveLink(BlogLink link);


    Boolean deleteBatch(Integer[] ids);

    BlogLink selectById(Integer id);

    Boolean updateLink(BlogLink tempLink);

    /**
     * 返回友链页面所需的所有数据
     *
     * @return
     */
    Map<Byte, List<BlogLink>> getLinksForLinkPage();

}
