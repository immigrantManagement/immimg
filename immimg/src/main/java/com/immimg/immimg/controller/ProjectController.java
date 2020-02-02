package com.immimg.immimg.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.internal.$Gson$Preconditions;
import com.immimg.immimg.model.entity.Project;
import com.immimg.immimg.model.entity.Result;
import com.immimg.immimg.service.ProjectService;
import com.immimg.immimg.util.PageDto;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "项目管理")
public class ProjectController {
    @Resource
    private ProjectService service;
    @ApiOperation(value = "根据标题和创建时间查询项目",notes = "查询成功返回数据集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title",value = "title",dataType = "String",example = "标题"),
            @ApiImplicitParam(name = "createDate",value = "createDate",dataType = "Date",example = "2020-01-01 00:00:00"),
            @ApiImplicitParam(name = "currentPage",value = "currentPage",dataType = "Integer",example = "1"),
            @ApiImplicitParam(name = "pageCount",value = "pageCount",dataType = "Integer",example = "4")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "查询成功"),
            @ApiResponse(code = 204,message = "未查询到符合条件的项目")
    })
    @PostMapping("/getProject")
    public Result getProject(@RequestParam(value = "title",required = false)String title,
                             @RequestParam(value = "createDate",required = false)Date createDate,
                             @RequestParam(value = "currentPage",required = false,defaultValue = "1")Integer currentPage,
                             @RequestParam(value = "pageCount",required = false,defaultValue = "4")Integer pageCount){
        PageDto<Project> dto=service.getProjectByTitleAndCreateDate(title, createDate, currentPage, pageCount);
        //判断是否有上一页
        if(currentPage>1){
            dto.setHasPrevPage(true);
        }else{
            dto.setHasPrevPage(false);
        }
        //获取总页数
        Integer totalPage=dto.getPagetotal();
        //判断是否有下一页
        if(currentPage<totalPage){
            dto.setHasNextPage(true);
        }else{
            dto.setHasNextPage(false);
        }
        Result result=new Result();
        if(dto.getData()!=null&&dto.getData().size()>0){
            result.setCode(200);
            result.setMessage("查询成功");
            result.setData(JSON.toJSONString(dto));
        }else{
            result.setCode(204);
            result.setMessage("未找到符合条件的项目");
        }
        return result;
    }

    @ApiOperation(value = "根据id删除项目",notes = "返回成功或失败")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",dataType = "Integer",example = "1")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "删除成功"),
            @ApiResponse(code = 204,message = "删除失败")
    })
    @PostMapping("/deleteProject")
    public Result deleteProject(Integer id){
        Result result=new Result();
        if(service.deleteProject(id)>0){
            result.setCode(200);
            result.setMessage("删除成功");
        }else{
            result.setCode(204);
            result.setMessage("删除失败");
        }
        return result;
    }

    @ApiOperation(value = "添加项目",notes = "返回添加成功或失败")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title",value = "title",dataType = "String",example = "这是项目的标题"),
            @ApiImplicitParam(name = "intro",value = "intro",dataType = "String",example = "这是项目的简介"),
            @ApiImplicitParam(name = "content",value = "content",dataType = "String",example = "这是项目的内容"),
            @ApiImplicitParam(name = "comment",value = "comment",dataType = "String",example = "这是项目的备注")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "添加成功"),
            @ApiResponse(code = 204,message = "添加失败")
    })
    @PostMapping("/addProject")
    public Result addProject(String title,String intro,String content,String comment){
        Project project=new Project();
        project.setTitle(title);
        project.setIntro(intro);
        project.setContent(content);
        project.setComment(comment);
        Result result=new Result();
        if(service.addProject(project)>0){
            result.setCode(200);
            result.setMessage("添加成功");
        }else{
            result.setCode(204);
            result.setMessage("添加失败");
        }
        return result;
    }
    @ApiOperation(value = "根据id查找项目",notes = "返回查找到的项目对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",dataType = "Integer",example = "1")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "查找到符合条件的项目"),
            @ApiResponse(code = 204,message = "未查找到符合条件的项目 ")
    })
    @PostMapping("/getProjectById")
    public Result getProjectById(Integer id){
        Result result=new Result();
        Project project=service.getProjectById(id);
        if(project!=null){
            result.setCode(200);
            result.setMessage("查找到符合条件的项目");
            result.setData(JSON.toJSONString(project));
        }else{
            result.setCode(204);
            result.setMessage("未找到符合条件的项目");
        }
        return  result;
    }
    @ApiOperation(value = "修改项目信息",notes = "返回修改成功或失败")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",dataType = "Integer",example = "1"),
            @ApiImplicitParam(name = "title",value = "title",dataType = "String",example = "这是项目的标题"),
            @ApiImplicitParam(name = "intro",value = "intro",dataType = "String",example = "这是项目的简介"),
            @ApiImplicitParam(name = "content",value = "content",dataType = "String",example = "这是项目的内容"),
            @ApiImplicitParam(name = "comment",value = "comment",dataType = "String",example = "这是项目的备注")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "修改成功"),
            @ApiResponse(code = 204,message = "修改失败")
    })
    @PostMapping("/updateProject")
    public Result updateProject(Integer id,String title,String intro,String content,String comment){
        Result result=new Result();
        Project project=new Project();
        project.setId(id);
        project.setTitle(title);
        project.setIntro(intro);
        project.setContent(content);
        project.setComment(comment);
        if(service.updateProject(project)>0){
            result.setCode(200);
            result.setMessage("修改成功");
        }else{
            result.setCode(204);
            result.setMessage("修改失败");
        }
        return result;
    }
}
