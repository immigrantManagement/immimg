package com.immimg.immimg.service.impl;

import com.immimg.immimg.dao.NewsDao;
import com.immimg.immimg.model.entity.News;
import com.immimg.immimg.model.entity.NewsDetails;
import com.immimg.immimg.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 * @version V1.0
 * @Project immigrantManagement
 * @Package com.immimg.immimg.service.impl
 * @date 2020/1/20 23:22 星期一
 */
@Service
public class NewsServicelmpl implements NewsService {
    @Resource
    private NewsDao newsDao;
    @Override
    public List<News> selectAll() {
        return  newsDao.selectAll();
    }

    @Override
    public List<News> selectFy(Integer currentPage) {
        return newsDao.selectFy((currentPage-1)*4);
    }

    @Override
    public int addStudent(News stu) {
        return newsDao.addStudent(stu);
    }

    @Override
    public News selectById(Integer id) {
        return newsDao.selectById(id);
    }

    @Override
    public int updateStudent(News stu) {
        return newsDao.updateStudent(stu);
    }

    @Override
    public int delStudent(Integer id) {
        return newsDao.delStudent(id);
    }

    @Override
    public List<News> selectXinWen() {
        return newsDao.selectXinWen();
    }

    @Override
    public List<NewsDetails>selectNewById(Integer nid) {
        return newsDao.selectNewById(nid);
    }
}
