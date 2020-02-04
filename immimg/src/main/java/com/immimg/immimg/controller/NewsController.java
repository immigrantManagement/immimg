package com.immimg.immimg.controller;

import com.immimg.immimg.model.entity.News;
import com.immimg.immimg.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 * @version V1.0
 * @Project immigrantManagement
 * @Package com.immimg.immimg.controller
 * @date 2020/1/21 11:08 星期二
 */
@Controller
public class NewsController {
    @Resource
    private NewsService newsService;

    @PostMapping(value = "/getAll")
    public String getAll(@RequestParam(value = "currentPage",required = false,defaultValue = "1") Integer currentPage, Model model){
        List<News> newsList=newsService.selectFy(currentPage);
        for (News news : newsList) {
            System.out.println(news.toString());
        }
        int n=newsService.selectAll().size();
        model.addAttribute("newsList",newsList);
        model.addAttribute("currentPage",currentPage);
        int page=n%4==0?n/4:n/4+1;
        model.addAttribute("page",page);
        System.out.println("当前页:"+currentPage+",总页数:"+page+",总条数"+newsList.size());
        return "xw/inbound";
    }
//    @RequestMapping(value = "toAddNews")
//    public String toAddNews(){
//        return "toAddNews";
//    }
    @PostMapping(value = "AddNews",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String AddNews(News news){
        int n=newsService.addStudent(news);
        if(n>0){
            return "<script>alert('添加成功!');location.href='getAll'</script>";
        }else{
            return "<script>alert('添加失败!');location.href='getAll</script>";
        }
    }
    @PostMapping(value = "deleteStudent",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteStudent(Integer id, RedirectAttributes ra){
        int n=newsService.delStudent(id);
        if(n>0){
            return "<script>alert('删除成功!');location.href='getAll'</script>";
        }else{
            return "<script>alert('删除失败!');location.href='getAll'</script>";
        }
    }
    @PostMapping(value = "updateInbound")
    public String updateInbound(Integer id,Model model){
        News newsList=newsService.selectById(id);
        model.addAttribute("newsList",newsList);
        return "xw/updateInbound";
    }
    @PostMapping(value = "updateNews")
    @ResponseBody
    public String updateNews(News news){
        int n=newsService.updateStudent(news);
        if(n>0){
            return"<script>alert('修改成功!');location.href='getAll'</script>";
        }else{
            return "<script>alert('修改失败!');location.href='getAll'</script>";
        }

    }
@PostMapping({"/","newsIndex"})
    public String index(){
        return "xw/newsIndex";
}
    @PostMapping("fenLei")
    public String fenLei(){
        return "xw/type";
    }
}

