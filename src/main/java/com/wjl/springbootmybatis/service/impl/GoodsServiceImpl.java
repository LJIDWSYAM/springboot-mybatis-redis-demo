package com.wjl.springbootmybatis.service.impl;

import com.wjl.springbootmybatis.Utils.Page;
import com.wjl.springbootmybatis.Utils.RedisUtil;
import com.wjl.springbootmybatis.dao.GoodsDao;
import com.wjl.springbootmybatis.entity.Goods;
import com.wjl.springbootmybatis.entity.MiaoShaMessage;
import com.wjl.springbootmybatis.entity.MiaoshaGoods;
import com.wjl.springbootmybatis.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsDao goodsDao;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public List<Goods> getAllGoods() {

        return goodsDao.selectGoods();
    }

    @Override
    public int getGoodsCount() {
        return  goodsDao.selectGoodsCount();
    }

    @Override
    public List<Goods> getAllGoodsByPage(Page page) {

        return goodsDao.selectGoodsByPage(page);
    }

    @Override
    public MiaoshaGoods selectMiaoshaGoods(String goodsId) {
        return goodsDao.selectMiaoshaGoods(goodsId);
    }

    @Override
    public void recoveryStockAndRedis(MiaoShaMessage miaoShaMessage) {
        goodsDao.recoveryStock(miaoShaMessage);
        String key = "goods"+miaoShaMessage.getMiaoshagoods_id();
        redisUtil.Rpush(key,1);

    }


}
