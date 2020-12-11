package com.xing.dao;

import com.xing.pojo.User;
import com.xing.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMapperTest {

    @Test
    public void test(){
        //第一步，获取sqlsession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            //方式一：getmapper
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User userById = userMapper.getUserById(1);

            //方式二，旧版本的
            //List<User> userList = sqlSession.selectList("com.xing.dao.UserDao.getUserList");

            System.out.println(userById);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭session
            sqlSession.close();
        }
    }
}
