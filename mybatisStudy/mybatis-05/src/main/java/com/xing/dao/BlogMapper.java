package com.xing.dao;

import com.xing.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {

    //插入数据
    void addBlog(Blog blog);

    List<Blog> getBlog();

    //查询博客
    List<Blog> queryBlogIF(Map map);
}
