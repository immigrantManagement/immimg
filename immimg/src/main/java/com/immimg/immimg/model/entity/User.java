package com.immimg.immimg.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version v1.0
 * @ProjectName: immimg
 * @ClassName: User
 * @Description: TODO(一句话描述该类的功能)
 * @Author: Nlqiong
 * @Date: 2020/1/15 7:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 用户id，用户权限分类，用户状态
     */
    private Integer id,type,flag;
    /**
     * 用户手机号，密码，昵称
     */
    private String mobile,password,nickname;

}
