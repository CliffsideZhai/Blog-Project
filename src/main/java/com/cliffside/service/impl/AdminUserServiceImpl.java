package com.cliffside.service.impl;


import com.cliffside.dao.AdminUserMapper;
import com.cliffside.entity.AdminUser;
import com.cliffside.service.AdminUserService;
import com.cliffside.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;



@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(String userName, String password) {
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        return adminUserMapper.login(userName, passwordMd5);
    }

    @Override
    public AdminUser getUserDetailById(Integer loginUserId) {
        return adminUserMapper.selectByPrimaryKey(loginUserId);
    }

    @Override
    public Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        //判断用户是否非空
        if (adminUser != null){
            String originalPasswordMd = MD5Util.MD5Encode(originalPassword,"UTF-8");
            String newPasswordMd5 = MD5Util.MD5Encode(newPassword,"UTF-8");
            //比较原密码是否正确
            if (originalPasswordMd.equals(adminUser.getLoginPassword())){
                //正确，设置新密码
                adminUser.setLoginPassword(newPasswordMd5);
                if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0 ){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        if(adminUser != null){
            adminUser.setLoginUserName(loginUserName);
            adminUser.setNickName(nickName);
        }if (adminUserMapper.updateByPrimaryKeySelective(adminUser) >0){
            return true;
        }
        return false;
    }
}
