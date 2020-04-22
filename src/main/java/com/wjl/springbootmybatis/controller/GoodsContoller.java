package com.wjl.springbootmybatis.controller;


import com.wjl.springbootmybatis.Utils.Page;
import com.wjl.springbootmybatis.Utils.RedisUtil;
import com.wjl.springbootmybatis.entity.Goods;
import com.wjl.springbootmybatis.entity.MiaoshaGoods;
import com.wjl.springbootmybatis.service.GoodsService;
import com.wjl.springbootmybatis.vo.GoodsPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsContoller {

    @Autowired
    GoodsService goodsService;
    @Autowired
    RedisUtil redisUtil;
    @RequestMapping("/showGoods")
    @ResponseBody
    public GoodsPage showGoods(String pageNum){

        Page page = new Page();

        page.setPageNum(Integer.parseInt(pageNum));//当前的页数
        page.setPageSize(3);//页面显示的记录数


        //查询出goods表中的记录数
        int count = goodsService.getGoodsCount();

        page.setTotalCount(count);

        List<Goods> goods = goodsService.getAllGoodsByPage(page);

        GoodsPage goodsPage = new GoodsPage();//list《goods》和总页数的包装类型

        goodsPage.setGoods(goods);
        goodsPage.setPage(page);

        return goodsPage;
    }
    /*
    * */
    @GetMapping("/miaoshaGoods/{goodsId}")
    public ModelAndView selectMiaoshaGoods(@PathVariable("goodsId") String goodsId,ModelAndView modelAndView){
        MiaoshaGoods miaoshaGoods=goodsService.selectMiaoshaGoods(goodsId);
        Long beginTime=miaoshaGoods.getBegin_time().getTime();
        Long endTime=miaoshaGoods.getEnd_time().getTime();
        long nowTime=new Date().getTime();
        Long howLongEnd;
        Long howLongBegin;
        int numBer=miaoshaGoods.getMiaosha_stock();
        int status=0;
        if (numBer>0) {
            if (beginTime > nowTime) {
                status = 0;//活动未开始
                 howLongBegin=(beginTime-nowTime);
                modelAndView.addObject("howLongBegin",howLongBegin);
            } else if (endTime < nowTime) {
                status = 1;//活动已结束
            } else {
                status = 2;//活动正在进行
                 howLongEnd=(endTime-nowTime);
                modelAndView.addObject("howLongEnd",howLongEnd);
            }
        }else {
            status=3;//已售完
        }

        if(status == 0 || status == 2){

            String key = "goods"+miaoshaGoods.getMiaoshagoods_id();

            //将商品库存存放在redis中
            long stock =  redisUtil.lGetListSize(key);

            if(stock <= 0){
                for(int i =0;i<miaoshaGoods.getMiaosha_stock();i++){
                    redisUtil.Rpush(key,1);
                }
            }

        }
        modelAndView.addObject("miaoShaGoods",miaoshaGoods);
        modelAndView.addObject("goodsSatus",status);
        modelAndView.addObject("goodsId",goodsId);
        modelAndView.setViewName("product");
        return modelAndView;
    }
}
