package com.immimg.immimg.service.impl;

import com.immimg.immimg.dao.ProjectDao;
import com.immimg.immimg.model.entity.Project;
import com.immimg.immimg.service.ProjectService;
import com.immimg.immimg.util.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Service
public class ProjectServiceImpl implements ProjectService {
    @Resource
    private ProjectDao dao;

    @Override
    public Integer getProjectTotalCount(String title, Date createDate) {
        return dao.getProjectTotalCount(title, createDate);
    }

    @Override
    public PageDto<Project> getProjectByTitleAndCreateDate(String title, Date createDate, Integer currentPage, Integer pageCount) {
        //获取总数量
        Integer totalCount=this.getProjectTotalCount(title, createDate);
        //计算当前页
        Integer current=(currentPage-1)*pageCount;
        //查询当前页的数据
        List<Project> list=dao.getProjectByTitleAndCreateDate(title, createDate, current, pageCount);
        //创建分页工具类对象
        PageDto<Project> dto=new PageDto<>();
        //给总条数赋值
        dto.setTotal((long)totalCount);
        //给当前页赋值
        dto.setPageindex(currentPage);
        //给每页显示数量赋值
        dto.setPagecount(pageCount);
        //给总页数赋值
        dto.setPagetotal(totalCount%pageCount==0?totalCount/pageCount:totalCount/pageCount+1);
        //给数据集合赋值
        dto.setData(list);
        return dto;
    }

    @Override
    public Integer deleteProject(Integer id) {
        return dao.deleteProject(id);
    }

    @Override
    public Integer addProject(Project project) {
        return dao.addProject(project);
    }

    @Override
    public Project getProjectById(Integer id) {
        return dao.getProjectById(id);
    }

    @Override
    public Integer updateProject(Project project) {
        return dao.updateProject(project);
    }
}
