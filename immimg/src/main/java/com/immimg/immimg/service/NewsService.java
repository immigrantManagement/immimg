package com.immimg.immimg.service;

import com.immimg.immimg.model.entity.News;
import com.immimg.immimg.model.entity.NewsDetails;

import java.util.List;

/**
 * @author lenovo
 * @version V1.0
 * @Project immigrantManagement
 * @Package com.immimg.immimg.service.impl
 * @date 2020/1/20 23:20 星期一
 */
public interface NewsService {
    //查询全部
    List<News> selectAll();
    //分页查询全部
    List<News> selectFy(Integer currentPage);
    //新增
    int addStudent(News stu);
    //根据id查询
    News selectById(Integer id);
    //修改
    int updateStudent(News stu);
    //删除
    int delStudent(Integer id);

    //查询新闻页面
    List<News>selectXinWen();

    //根据id查询新闻详情
    List<NewsDetails> selectNewById(Integer nid);
}
