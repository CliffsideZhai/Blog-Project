package com.cliffside.service;

import com.cliffside.entity.AdminUser;

public interface AdminUserService {
    /**
     * 处理登录
     * @param userName
     * @param password
     * @return 一个admin 类
     */
    AdminUser login(String userName, String password);

    /**
     * 通过ID获取用户详细信息
     * @param loginUserId
     * @return 一个admin 类
     */
    AdminUser getUserDetailById(Integer loginUserId);

    /**
     * 修改当前登录用户的密码
     *
     * @param loginUserId
     * @param originalPassword
     * @param newPassword
     * @return 是否修改密码成功的Boolean类型
     */
    Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword);

    /**
     * 修改当前登录用户的名称信息
     * @param loginUserId
     * @param loginUserName
     * @param nickName
     * @return 是否修改用户名的Boolean类型
     */
    Boolean updateName(Integer loginUserId, String loginUserName, String nickName);
}
