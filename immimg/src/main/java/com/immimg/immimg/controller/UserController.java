package com.immimg.immimg.controller;

import com.alibaba.fastjson.JSON;
import com.immimg.immimg.model.entity.Merchant_application;
import com.immimg.immimg.model.entity.Result;
import com.immimg.immimg.model.entity.User;
import com.immimg.immimg.service.UserService;
import com.immimg.immimg.util.PageDto;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version V1.0
 * @Description:
 * @Project: immimg
 * @Package com.immimg.immimg.controller
 * @author编写人员 宁坤
 * @date 2020/1/18 18:19 星期六
 */
@RestController
@Api(tags = "这是用户信息管理")
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation(value = "根据用户id查询用户信息",notes = "成功返回用户信息，失败返回字符串")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",dataType = "Integer",example = "1")
    })
    @ApiResponses({
            @ApiResponse(code = 123,message = "未找到符合该用户id的用户信息"),
            @ApiResponse(code = 200,message = "找到符合该用户id的用户信息"),
    })
    @PostMapping("/getUser")
    public Result getUserById(@RequestParam(name = "id",required = false) Integer id, Model model){
        Result result=new Result();
        //获取用户信息
        User user=userService.getUserById(id);
        if(user!=null){
            result.setCode(200);
            result.setMessage("找到符合该用户id的用户信息");
            result.setData(JSON.toJSONString(user));
        }else{
            result.setCode(123);
            result.setMessage("未找到符合该用户id的用户信息");
        }
        model.addAttribute("user",user);
        return result;
    }

    @ApiOperation(value = "根据用户id修改用户密码",notes = "修改成功返回true，修改失败返回false")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password",value = "password",dataType = "String",example = "123456"),
            @ApiImplicitParam(name = "id",value = "id",dataType = "Integer",example = "1"),
    })
    @ApiResponses({
            @ApiResponse(code = 123,message = "修改密码失败"),
            @ApiResponse(code = 200,message = "密码已修改"),
    })
    @PostMapping("/upPasswd")
    public Result updateForPasswd(@RequestParam(name = "password",required = false) String password,
                                  @RequestParam(name = "id",required = false)Integer id){
        Result result=new Result();
        String flag="false";
        if(userService.updateForPassWord(password, id)){
            flag="true";
            result.setCode(200);
            result.setMessage("密码已修改");
            result.setData(JSON.toJSONString(flag));
        }else{
            result.setCode(123);
            result.setMessage("密码修改失败");
        }
        return result;
    }


    @ApiOperation(value = "根据用户id查询用户申请资料，并根据资料状态分页",notes = "查询成功返回资料集合，失败返回字符串")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",dataType = "Integer",example = "1"),
            @ApiImplicitParam(name = "flag",value = "flag",dataType = "Integer",example = "1"),
            @ApiImplicitParam(name = "pageindex",value = "pageindex",dataType = "Integer",example = "1"),
            @ApiImplicitParam(name = "pagecount",value = "pagecount",dataType = "Integer",example = "4"),
    })
    @ApiResponses({
            @ApiResponse(code = 123,message = "未找到符合条件的信息"),
            @ApiResponse(code = 200,message = "找到符合该条件的信息"),
    })
    @PostMapping("getMa")
    public Result getMa(@RequestParam(name = "id")Integer id,
                        @RequestParam(name = "flag",required = false)Integer flag,
                        @RequestParam(name = "pageindex",required = false,defaultValue = "1")Integer pageindex,
                        @RequestParam(name = "password",required = false,defaultValue = "4")Integer pagecount){
        Result result=new Result();
        //获取分页对象
        PageDto<Merchant_application>mapageDto=userService.getAllMaById(id, flag, pageindex, pagecount);
        //判断是否有上一页
        if(pageindex>1){
            mapageDto.setHasPrevPage(true);
        }else{
            mapageDto.setHasPrevPage(false);
        }
        //获取总页数
        Integer pagetotal=mapageDto.getPagetotal();
        //获取总条数
        long total=mapageDto.getTotal();
        //判断是否有下一页
        if(pageindex>=pagetotal){
            mapageDto.setHasNextPage(false);
        }else{
            mapageDto.setHasNextPage(true);
        }
        if(mapageDto.getData()!=null&&mapageDto.getData().size()>0){
            result.setCode(200);
            result.setMessage("找到符合该条件的信息");
            result.setData(JSON.toJSONString(mapageDto));
        }else{
            result.setCode(123);
            result.setMessage("找到符合该条件的信息");
        }
        return result;
    }
}
