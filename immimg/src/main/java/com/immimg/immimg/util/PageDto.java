package com.immimg.immimg.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @version V1.0
 * @Description:分页工具类
 * @Project: immimg
 * @Package com.immimg.immimg.util
 * @author编写人员 宁坤
 * @date 2020/1/19 16:01 星期日
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto <T>{
    //数据总条数
    private long total;
    //当前页开始数据
    private Integer pageindex;
    //每页总条数
    private Integer pagecount;
    //总页数
    private Integer pagetotal;
    //当前页数据集合
    private List<T> data;
    //是否有上一页
    private boolean hasPrevPage=false;
    //是否有下一页
    private boolean hasNextPage=false;
}
