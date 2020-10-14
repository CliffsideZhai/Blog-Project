package com.cliffside.dao;

import com.cliffside.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author windo
 */
@Component
@Mapper
public interface AdminUserMapper {
    /**
     *
     * @param record
     * @return 返回添加了的用户
     */
    int insert(AdminUser record);

    /**
     * 选择性的添加admin的信息，
     * 比如修改等
     * @param record
     * @return 有选择性的添加
     */
    int insertSelective(AdminUser record);

    /**
     * 登陆方法
     * @param userName
     * @param password
     * @return
     */
    AdminUser login(@Param("userName") String userName, @Param("password") String password);

    /**
     * 用Id获取Admin类
     * @param adminUserId
     * @return
     */
    AdminUser selectByPrimaryKey(Integer adminUserId);

    /**
     * 有选择性的更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(AdminUser record);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(AdminUser record);

}
