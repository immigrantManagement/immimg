package com.immimg.immimg.dao;

import com.immimg.immimg.model.entity.Merchant_application;
import com.immimg.immimg.model.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @version V1.0
 * @Description:用户管理dao层
 * @Project: immimg
 * @Package com.immimg.immimg.dao
 * @author编写人员 宁坤
 * @date 2020/1/18 18:12 星期六
 */
public interface UserDao {
    /**
     * 根据用户id获取用户信息
     * @param id 用户id
     * @return 用户对象
     */
    public User getUserById(@Param("id") Integer id);

    /**
     * 根据用户id修改密码
     * @param password  用户输入的新密码
     * @return 是否修改成功
     */
    public int updateForPassWord(@Param("password") String password,@Param("id") Integer id);

    /**
     * 根据用户id获取用户申请资料并根据资料申请状态分页
     * @param id 用户id
     * @param flag 资料状态
     * @param pageindex 当前页数据开始位置
     * @param pagecount 每页总条数
     * @return 申请资料集合
     */
    public List<Merchant_application> getAllMaById(@Param("id") Integer id,
                                                   @Param("flag") Integer flag,
                                                   @Param("pageindex")Integer pageindex,
                                                   @Param("pagecount")Integer pagecount);


    /**
     * 获取总条数
     * @param id 用户id
     * @param flag 资料状态
     * @return 数据总条数是
     */
    public Integer getCount(@Param("id") Integer id,
                        @Param("flag") Integer flag);
}
