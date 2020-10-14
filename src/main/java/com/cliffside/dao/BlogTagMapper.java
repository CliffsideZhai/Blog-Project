package com.cliffside.dao;

import com.cliffside.entity.BlogTag;
import com.cliffside.entity.BlogTagCount;
import com.cliffside.util.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface BlogTagMapper {

    List<BlogTag> findTagList(PageQueryUtil pageUtil);//新增List<BlogTag> findTagList(PageQueryUtil pageUtil);

    int getTotalTags(PageQueryUtil pageUtil);//新增int getTotalTags(PageQueryUtil pageUtil);

    BlogTag selectByPrimaryKey(Integer tagId);//BlogTag selectByPrimaryKey(Integer tagId);

    BlogTag selectByTagName(String tagName);//新增BlogTag selectByTagName(String tagName);

    int deleteByPrimaryKey(Integer tagId);//int deleteByPrimaryKey(Integer tagId);


    int deleteBatch(Integer[] ids);//新增int deleteBatch(Integer[] ids);

    int insert(BlogTag record);//int insert(BlogTag record);

    int insertSelective(BlogTag record);//int insertSelective(BlogTag record);

    int updateByPrimaryKeySelective(BlogTag record);// int updateByPrimaryKeySelective(BlogTag record);

    int updateByPrimaryKey(BlogTag record);//int updateByPrimaryKey(BlogTag record);


    int batchInsertBlogTag(List<BlogTag> tagList);//int batchInsertBlogTag(List<BlogTag> tagList);

    List<BlogTagCount> getTagCount();//List<BlogTagCount> getTagCount();
}