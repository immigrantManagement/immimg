package com.immimg.immimg.service.impl;

import com.immimg.immimg.dao.UserDao;
import com.immimg.immimg.model.entity.Merchant_application;
import com.immimg.immimg.model.entity.User;
import com.immimg.immimg.service.UserService;
import com.immimg.immimg.util.PageDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version V1.0
 * @Description:
 * @Project: immimg
 * @Package com.immimg.immimg.service.impl
 * @author编写人员 宁坤
 * @date 2020/1/18 18:17 星期六
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userdao;

    /**
     * 根据用户id获取用户信息
     * @param id 用户id
     * @return 用户对象
     */
    public User getUserById(Integer id) {
        return userdao.getUserById(id);
    }

    /**
     * 根据用户id修改用户密码
     * @param password  用户输入的新密码
     * @param id 用户id
     * @return 是否修改成功
     */
    public boolean updateForPassWord(String password, Integer id) {
        int num=userdao.updateForPassWord(password, id);
        if(num>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 根据用户id获取申请资料并根据资料状态分页
     * @param id 用户id
     * @param flag 资料状态
     * @param pageindex 当前页数据开始位置
     * @param pagecount 每页总条数
     * @return
     */
    public PageDto<Merchant_application> getAllMaById(Integer id, Integer flag, Integer pageindex, Integer pagecount) {
        //获取总条数
        Integer total=this.getCount(id, flag);
        //获取当前页从哪一条开始
        Integer index=(pageindex-1)*pagecount;
        //获取当前页信息的集合
        List<Merchant_application>maList=userdao.getAllMaById(id, flag, index, pagecount);
        //创建分页对象
        PageDto<Merchant_application>mapageDto=new PageDto<>();
        //为总条数赋值
        mapageDto.setTotal((long)total);
        //为当前页赋值
        mapageDto.setPageindex(pageindex);
        //为页面条数上限赋值
        mapageDto.setPagecount(pagecount);
        //为总页数赋值
        mapageDto.setPagetotal(total%pagecount==0?total/pagecount:total/pagecount+1);
        //为集合赋值
        mapageDto.setData(maList);
        return mapageDto;
    }

    /**
     * 获取数据库总条数
     * @param id 用户id
     * @param flag 资料状态
     * @return
     */
    public Integer getCount(Integer id, Integer flag) {
        return userdao.getCount(id, flag);
    }
}
