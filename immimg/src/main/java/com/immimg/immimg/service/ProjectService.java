package com.immimg.immimg.service;

import com.immimg.immimg.model.entity.Project;
import com.immimg.immimg.util.PageDto;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ProjectService {
    /**
     * 通过标题和创建时间获取项目总条数
     * @param title 标题
     * @param createDate 创建时间
     * @return 总条数
     */
    Integer getProjectTotalCount( String title,Date createDate);

    /**
     * 通过标题和创建时间查询项目
     * @param title 标题
     * @param createDate 创建时间
     * @param currentPage 当前页
     * @param pageCount 每页显示数量
     * @return 查询后的项目列表
     */
    PageDto<Project> getProjectByTitleAndCreateDate(String title, Date createDate, Integer currentPage, Integer pageCount);

    /**
     * 通过id更改项目状态为隐藏(假删除)
     * @param id 项目编号
     * @return 返回1更改成功返回0更改失败
     */
    Integer deleteProject(Integer id);
    /**
     * 添加项目
     * @param project 添加的项目对象
     * @return 返回1添加成功 返回0添加失败
     */
    Integer addProject(Project project);

    /**
     * 根据id查找项目
     * @param id 项目id
     * @return 返回查找到的项目
     */
    Project getProjectById(Integer id);

    /**
     * 修改项目
     * @param project 要修改的项目对象
     * @return 修改成功返回1 修改失败返回0
     */
    Integer updateProject(Project project);
}
