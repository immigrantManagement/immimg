package com.immimg.immimg.service.impl;

import com.immimg.immimg.dao.LoginDao;
import com.immimg.immimg.model.entity.Person;
import com.immimg.immimg.service.LoginService;
import com.immimg.immimg.util.VerifyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private LoginDao loginDao;

    @Resource
    private VerifyUtils verifyUtils;

    //普通用户跳转地址
    private String userIndex="user_index.html";

    //商家用户跳转地址
    private String merchantIndex="merchant_index.html";

    //超级管理员跳转地址
    private String superAdminIndex="super_admin_index.html";

    //普通管理员跳转地址
    private String adminIndex="admin_index.html";


    @Override
    public Map<String,Object> login(Map<String, String> map){
        Map<String,Object> res=new HashMap<>();
        String password = map.get("password");
        Person person=getPerson(map);
        if (person!=null){
            if (person.getPassword().equals(password)){
                if (person.getFlag()==1) {
                    res.put("code", 1);
                    res.put("msg", "登陆成功");
                    res.put("data", person);
                    if ("1".equals(map.get("flag"))) {
                        if (person.getType() == 1) {
                            //用户登陆后要跳转的地址
                            res.put("link", userIndex);
                        } else if (person.getType() == 2) {
                            //商家登陆后要跳转的地址
                            res.put("link", merchantIndex);
                        }
                    }else{
                        //管理员登陆后要跳转的地址
                        if (person.getType() == 1) {
                            res.put("link", superAdminIndex);
                        } else if (person.getType() == 2) {
                            res.put("link", adminIndex);
                        }
                    }
                }else {
                    res.put("code", 3);
                    res.put("msg", "该账号已被停用，请联系客服");
                }
            }else {
                res.put("code",2);
                res.put("msg","密码错误，请重新登录");
            }
        }else {
            res.put("code",0);
            res.put("msg","该手机号尚未注册");
        }
        return res;
    }

    @Override
    public Map<String,Object> sendVerifyNo(Map<String, String> map){
        Map<String,Object> res=new HashMap<>();
        Person person=getPerson(map);
        if (person!=null){
            if (person.getFlag()==1) {
                try {
                    if (verifyUtils.sendVerifyNo(person.getMobile())) {
                        res.put("code", 1);
                    } else {
                        res.put("code", 2);
                        res.put("msg", "验证码发送失败");
                    }
                } catch (Exception e) {
                    res.put("code", 5);
                    res.put("msg", "验证码发送异常");
                    e.printStackTrace();
                }
            }else {
                res.put("code",3);
                res.put("msg", "该账号已被停用，请联系客服");
            }
        }else{
            res.put("code",0);
            res.put("msg","该手机号尚未注册");
        }

        return res;
    }

    @Override
    public Map<String,Object> loginByVerifyNo(Map<String, String> map){
        Map<String,Object> res=new HashMap<>();
        String verifyNo = verifyUtils.getVerifyNoByMobile(map.get("mobile"));
        if (map.get("verifyNo").equals(verifyNo)){
            Person person=getPerson(map);
            if (person!=null){
                if (person.getFlag()==1) {
                    res.put("code", 1);
                    res.put("msg", "登陆成功");
                    res.put("data", person);
                    if ("1".equals(map.get("flag"))) {
                        if (person.getType() == 1) {
                            //用户登陆后要跳转的地址
                            res.put("link", userIndex);
                        } else if (person.getType() == 2) {
                            //商家登陆后要跳转的地址
                            res.put("link", merchantIndex);
                        }
                    }else{
                        //管理员登陆后要跳转的地址
                        if (person.getType() == 1) {
                            res.put("link", superAdminIndex);
                        } else if (person.getType() == 2) {
                            res.put("link",adminIndex);
                        }
                    }
                }else {
                    res.put("code", 3);
                    res.put("msg", "该账号已被停用，请联系客服");
                }
            }else{
                res.put("code",0);
                res.put("msg","该手机号尚未注册");
            }
        }else {
            res.put("code",2);
            res.put("msg","验证码错误，请重新登录");
        }
        return res;
    }


    private Person getPerson(Map<String, String> map){
        Person person=null;
        String mobile = map.get("mobile");
        if ("1".equals(map.get("flag"))) {
//            person=loginDao.getUserByMobile(mobile);
        }else {
            person=loginDao.getAdminByMobile(mobile);
        }
        return person;
    }


}
