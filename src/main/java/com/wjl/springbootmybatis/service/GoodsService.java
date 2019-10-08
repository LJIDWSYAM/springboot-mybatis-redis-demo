package com.wjl.springbootmybatis.service;

import com.wjl.springbootmybatis.Utils.Page;
import com.wjl.springbootmybatis.entity.Goods;
import com.wjl.springbootmybatis.entity.miaoshaGoods;

import java.util.List;

public interface GoodsService {

    List<Goods>  getAllGoods();

    int getGoodsCount();

    List<Goods>  getAllGoodsByPage(Page page);

    miaoshaGoods selectMiaoshaGoods(String goodsId);

}
