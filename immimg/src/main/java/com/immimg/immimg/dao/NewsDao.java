package com.immimg.immimg.dao;

import com.immimg.immimg.model.entity.News;
import com.immimg.immimg.model.entity.NewsDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lenovo
 * @version V1.0
 * @Project immigrantManagement
 * @Package com.immimg.immimg.dao
 * @date 2020/1/20 23:25 星期一
 */
@Mapper
public interface NewsDao {
    //查询全部
    List<News> selectAll();
    //分页查询全部
    List<News>selectFy(@Param("currentPage")Integer currentPage);
    //新增
    int addStudent(@Param("stu") News stu);
    //根据id查询
   News selectById(@Param("id") Integer id);
    //修改
    int updateStudent(@Param("stu") News stu);
    //删除
    int delStudent(@Param("id") Integer id);
    //查询新闻页面
    List<News>selectXinWen();

    //根据id查询新闻详情
    List<NewsDetails> selectNewById(@Param("nid") Integer nid);
}
