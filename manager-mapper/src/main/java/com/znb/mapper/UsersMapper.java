package com.znb.mapper;

import com.znb.pojo.Users;

import java.util.List;

/**
 * @Auther zhangningbo
 * @Date:2019/11/23
 * @Description:com.znb.mapper
 * @version:1.0
 */
public interface UsersMapper {
    void insertUser(Users user);
    List<Users> selectUserAll();
}
