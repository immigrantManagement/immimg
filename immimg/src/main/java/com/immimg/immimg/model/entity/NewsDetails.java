package com.immimg.immimg.model.entity;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lenovo
 * @version V1.0
 * @Project immigrantManagement
 * @Package com.immimg.immimg.model.entity
 * @date 2020/1/22 11:28 星期三
 */

public class NewsDetails {
    private Integer nid;
    private String title;//新闻标题
    Date date=new Date();
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String create_date=simpleDateFormat.format(date);//创建日期
    private String author;//作者
    private Integer hits;//阅读次数
    private String paragraph1;//详情id
    private String paragraph2;//段落1-13
    private String paragraph3;
    private String paragraph4;
    private String paragraph5;
    private String paragraph6;
    private String paragraph7;
    private String paragraph8;
    private String paragraph9;
    private String paragraph10;
    private String paragraph11;
    private String paragraph12;
    private String paragraph13;

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getParagraph1() {
        return paragraph1;
    }

    public void setParagraph1(String paragraph1) {
        this.paragraph1 = paragraph1;
    }

    public String getParagraph2() {
        return paragraph2;
    }

    public void setParagraph2(String paragraph2) {
        this.paragraph2 = paragraph2;
    }

    public String getParagraph3() {
        return paragraph3;
    }

    public void setParagraph3(String paragraph3) {
        this.paragraph3 = paragraph3;
    }

    public String getParagraph4() {
        return paragraph4;
    }

    public void setParagraph4(String paragraph4) {
        this.paragraph4 = paragraph4;
    }

    public String getParagraph5() {
        return paragraph5;
    }

    public void setParagraph5(String paragraph5) {
        this.paragraph5 = paragraph5;
    }

    public String getParagraph6() {
        return paragraph6;
    }

    public void setParagraph6(String paragraph6) {
        this.paragraph6 = paragraph6;
    }

    public String getParagraph7() {
        return paragraph7;
    }

    public void setParagraph7(String paragraph7) {
        this.paragraph7 = paragraph7;
    }

    public String getParagraph8() {
        return paragraph8;
    }

    public void setParagraph8(String paragraph8) {
        this.paragraph8 = paragraph8;
    }

    public String getParagraph9() {
        return paragraph9;
    }

    public void setParagraph9(String paragraph9) {
        this.paragraph9 = paragraph9;
    }

    public String getParagraph10() {
        return paragraph10;
    }

    public void setParagraph10(String paragraph10) {
        this.paragraph10 = paragraph10;
    }

    public String getParagraph11() {
        return paragraph11;
    }

    public void setParagraph11(String paragraph11) {
        this.paragraph11 = paragraph11;
    }

    public String getParagraph12() {
        return paragraph12;
    }

    public void setParagraph12(String paragraph12) {
        this.paragraph12 = paragraph12;
    }

    public String getParagraph13() {
        return paragraph13;
    }

    public void setParagraph13(String paragraph13) {
        this.paragraph13 = paragraph13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }

    public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public NewsDetails(Integer nid, String title, Date date, SimpleDateFormat simpleDateFormat, String create_date, String author, Integer hits, String paragraph1, String paragraph2, String paragraph3, String paragraph4, String paragraph5, String paragraph6, String paragraph7, String paragraph8, String paragraph9, String paragraph10, String paragraph11, String paragraph12, String paragraph13) {
        this.nid = nid;
        this.title = title;
        this.date = date;
        this.simpleDateFormat = simpleDateFormat;
        this.create_date = create_date;
        this.author = author;
        this.hits = hits;
        this.paragraph1 = paragraph1;
        this.paragraph2 = paragraph2;
        this.paragraph3 = paragraph3;
        this.paragraph4 = paragraph4;
        this.paragraph5 = paragraph5;
        this.paragraph6 = paragraph6;
        this.paragraph7 = paragraph7;
        this.paragraph8 = paragraph8;
        this.paragraph9 = paragraph9;
        this.paragraph10 = paragraph10;
        this.paragraph11 = paragraph11;
        this.paragraph12 = paragraph12;
        this.paragraph13 = paragraph13;
    }

    public NewsDetails() {
    }
}
