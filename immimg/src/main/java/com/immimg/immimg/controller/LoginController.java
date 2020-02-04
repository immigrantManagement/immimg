package com.immimg.immimg.controller;

import com.immimg.immimg.model.entity.User;
import com.immimg.immimg.service.LoginService;
import com.immimg.immimg.util.RedisTemplateUtils;
import com.immimg.immimg.util.TokenUtils;
import com.immimg.immimg.util.UserAgentUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class LoginController {
    @Resource
    LoginService loginService;

    @Resource
    TokenUtils tokenUtils;

    @Resource
    RedisTemplateUtils redis;

    @PostMapping("login")
    public Map<String,Object> login(@RequestParam Map<String,String>map, HttpServletRequest request){
        Map<String, Object> res=new HashMap<>();
        res = loginService.login(map);
        if(1==(int)res.get("code")) {
            try {
                String token=tokenUtils.getToken(res.get("data"), UserAgentUtils.uaSparser.parse(String.valueOf(request)).getDeviceType());
                int i;
                for (i=0;i<3;i++) {
                    if (redis.set(token, ((User) res.get("data")).getId().toString())) {
                        res.put("token", token);
                        break;
                    }
                }
                if (res.get("token")==null){
                    res.put("code",2);
                    res.put("msg","服务器异常，请稍后再试……");
                }

            } catch (IOException e) {
                res.put("code",2);
                res.put("msg","服务器异常，请稍后再试……");
                e.printStackTrace();
            }
        }
        return res;
    }

    @PostMapping("sendVerifyNo")
    public Map<String,Object> sendVerifyNo(@RequestParam Map<String,String>map){
        return loginService.sendVerifyNo(map);
    }

    @PostMapping("loginByVerifyNo")
    public Map<String,Object> loginByVerifyNo(@RequestParam Map<String,String>map,HttpServletRequest request){
        Map<String, Object> res=new HashMap<>();
        res = loginService.loginByVerifyNo(map);
        if(1==(int)res.get("code")) {
            try {
                String token=tokenUtils.getToken(res.get("data"),UserAgentUtils.uaSparser.parse(String.valueOf(request)).getDeviceType());
                int i;
                for (i=0;i<3;i++) {
                    if (redis.set(token, ((User) res.get("data")).getId().toString())) {
                        res.put("token", token);
                        break;
                    }
                }
                if (res.get("token")==null){
                    res.put("code",2);
                    res.put("msg","服务器异常，请稍后再试……");
                }else {
                    redis.delete(map.get("mobile"));
                }

            } catch (IOException e) {
                res.put("code",2);
                res.put("msg","服务器异常，请稍后再试……");
                e.printStackTrace();
            }
        }
        return res;
    }



}
