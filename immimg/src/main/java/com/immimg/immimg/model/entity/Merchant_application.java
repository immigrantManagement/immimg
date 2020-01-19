package com.immimg.immimg.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version V1.0
 * @Description:商家申请表
 * @Project: immimg
 * @Package com.immimg.immimg.model.entity
 * @author编写人员 宁坤
 * @date 2020/1/19 15:20 星期日
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Merchant_application {
    /**
     * 商家索引id，商家客户id，申请状态
     */
    private Integer id,use_id,flag;
    /**
     * 商家名称，商家提交的加盟材料连接，申请时间，最后修改时间，审核时间，备注
     */
    private String name,link,creatte_date,update_date,verified_date,comment;
}
