package com.znb.web.test;

import com.znb.mapper.UsersMapper;
import com.znb.pojo.Users;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Auther zhangningbo
 * @Date:2019/11/26
 * @Description:com.znb.web.controller
 * @version:1.0
 */
public class MT {
    public static void main(String[] args) {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        System.out.println(sqlSessionFactory);
        System.out.println(session);
//        List<Users> users = session.selectList("com.znb.mapper.UsersMapper.selectUserAll");
//        System.out.println(users);
        UsersMapper userMapper = session.getMapper(UsersMapper.class);
        List<Users> users = userMapper.selectUserAll();
        System.out.println(users);
        session.close();
    }
}
