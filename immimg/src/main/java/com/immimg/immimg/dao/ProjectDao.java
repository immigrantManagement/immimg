package com.immimg.immimg.dao;

import com.immimg.immimg.model.entity.Project;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ProjectDao {
    /**
     * 通过标题和创建时间获取项目总条数
     * @param title 标题
     * @param createDate 创建时间
     * @return 总条数
     */
    Integer getProjectTotalCount(@Param("title") String title,@Param("createDate") Date createDate);

    /**
     * 通过标题和创建时间查询项目
     * @param title 标题
     * @param createDate 创建时间
     * @param currentPage 当前页
     * @param pageCount 每页显示数量
     * @return 查询后的项目列表
     */
    List<Project> getProjectByTitleAndCreateDate(@Param("title")String title,@Param("createDate")Date createDate,@Param("currentPage")Integer currentPage,@Param("pageCount")Integer pageCount);

    /**
     * 通过id更改项目状态为隐藏(假删除)
     * @param id 项目编号
     * @return 返回1更改成功返回0更改失败
     */
    Integer deleteProject(@Param("id")Integer id);

    /**
     * 添加项目
     * @param project 添加的项目对象
     * @return 返回1添加成功 返回0添加失败
     */
    Integer addProject(@Param("project")Project project);

    /**
     * 根据id查到项目
     * @param id 项目id
     * @return 返回项目对象
     */
    Project getProjectById(@Param("id")Integer id);

    /**
     * 修改项目
     * @param project 修改的项目对象
     * @return 修改成功返回1 修改失败返回0
     */
    Integer updateProject(@Param("project")Project project);
}
