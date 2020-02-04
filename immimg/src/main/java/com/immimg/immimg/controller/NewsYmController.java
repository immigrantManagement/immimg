package com.immimg.immimg.controller;

import com.immimg.immimg.model.entity.News;
import com.immimg.immimg.model.entity.NewsDetails;
import com.immimg.immimg.service.NewsService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 * @version V1.0
 * @Project immigrantManagement
 * @Package com.immimg.immimg.controller
 * @date 2020/1/31 10:49 星期五
 */
@Controller
@Api(tags="这是最新新闻页面")
public class NewsYmController {
    @Resource
    private NewsService newsService;

    @ApiOperation(value = "根据新闻id查询最新的新闻信息\",notes = \"成功就给新闻数据，错了就返回字符串")

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataType = "Integer", example = "1"),
    })
    @ApiResponses({
            @ApiResponse(code = 123,message = "未找到符合该新闻id的新闻信息"),
            @ApiResponse(code = 200,message = "找到符合该新闻id的新闻信息"),
    })

    @PostMapping(value = "News")
    public String News(Model model){
        List<News> list=newsService.selectXinWen();
        model.addAttribute("list", list);
        return "ym/xinWen";
    }

    @PostMapping(value = "newsParticulars")
    public String newsParticulars(Integer nid, Model model){
        List<NewsDetails> list=newsService.selectNewById(nid);
        model.addAttribute("list", list);
    return "ym/xwXq39";
    }
}
