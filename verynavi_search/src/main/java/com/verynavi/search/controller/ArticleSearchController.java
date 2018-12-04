package com.verynavi.search.controller;

import com.verynavi.search.pojo.Article;
import com.verynavi.search.service.ArticleSearchService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleSearchController {

    @Autowired
    private ArticleSearchService articleSearchService;

    /**
     * 保存
     * @param article
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Article article){
        articleSearchService.save(article);
        return new Result(true, StatusCode.OK,"保存成功");
    }

    /**
     * 分页查询  搜索
     * @param keywords
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/search/{keywords}/{page}/{size}",method = RequestMethod.GET)
    public Result findByKeywords(@PathVariable("keywords") String keywords,@PathVariable("page") int page,@PathVariable("size") int size){
        Page<Article> articlePage = articleSearchService.findByKeywords(keywords, page, size);
        PageResult<Article> pageResult = new PageResult<>(articlePage.getTotalElements(), articlePage.getContent());
        return new Result(true, StatusCode.OK,"查询成功",pageResult);
    }
}
