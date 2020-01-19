package com.immimg.immimg.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * @version V1.0
 * @Description:项目表实体类
 * @Project: immimg
 * @Package com.immimg.immimg.model.entity
 * @author 孙春超
 * @date 2020/1/19 18:40 星期日
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    /**
     * 项目索引ID，状态：1显示  -1隐藏
     */
    private int id,flag;
    /**
     * 项目标题，项目简介，项目内容，备注
     */
    private String title,intro,content,comment;
    /**
     * 创建日期
     */
    private Date createDate;
}
