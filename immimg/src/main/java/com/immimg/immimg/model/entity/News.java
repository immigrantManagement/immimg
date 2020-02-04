package com.immimg.immimg.model.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lenovo
 * @version V1.0
 * @Project immigrantManagement
 * @Package com.immimg.immimg.model.entity
 * @date 2020/1/15 20:54 星期三
 */
@Data
public class News {
    private Integer id;//新闻索引ID
    private Integer type;//新闻类别
    private String title;//新闻标题
    private String content;//新闻内容
    private Integer create_user_id;//创建者ID
    private String author;//作者
    Date date=new Date();
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String create_date=simpleDateFormat.format(date);//创建日期

    Date date1=new Date();
    SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String update_date=simpleDateFormat.format(date);//最后修改时间

    Date date2=new Date();
    SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String verified_date=simpleDateFormat.format(date);//审核时间
    private Integer level;//优先级 默认为1 越高越靠前
    private Integer index;//顺序  相同优先级条件下  顺序越高越靠前
    private Integer flag;//1 显示  -1 隐藏
    private String photograph;//图片路径
    private Integer hits;//阅读次数
    private NewsDetails newsdetails;//详情
    private Integer nid;

    public News( String title, String content,  String author, String create_date, String update_date, String verified_date, String photograph, Integer hits) {


        this.title = title;
        this.content = content;

        this.author = author;


        this.create_date = create_date;

        this.update_date = update_date;

        this.verified_date = verified_date;

        this.photograph = photograph;
        this.hits = hits;

    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public News() {
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", create_user_id=" + create_user_id +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", simpleDateFormat=" + simpleDateFormat +
                ", create_date='" + create_date + '\'' +
                ", date1=" + date1 +
                ", simpleDateFormat1=" + simpleDateFormat1 +
                ", update_date='" + update_date + '\'' +
                ", date2=" + date2 +
                ", simpleDateFormat2=" + simpleDateFormat2 +
                ", verified_date='" + verified_date + '\'' +
                ", level=" + level +
                ", index=" + index +
                ", flag=" + flag +
                ", photograph='" + photograph + '\'' +
                ", hits=" + hits +
                ", newsdetails=" + newsdetails +
                '}';
    }
}
