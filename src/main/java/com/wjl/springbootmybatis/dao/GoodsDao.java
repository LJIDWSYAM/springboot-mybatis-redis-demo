package com.wjl.springbootmybatis.dao;


import com.wjl.springbootmybatis.Utils.Page;
import com.wjl.springbootmybatis.entity.Goods;
import com.wjl.springbootmybatis.entity.MiaoShaMessage;
import com.wjl.springbootmybatis.entity.MiaoshaGoods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GoodsDao {

    List<Goods> selectGoods();

    int selectGoodsCount();

    List<Goods> selectGoodsByPage(Page page);

    MiaoshaGoods selectMiaoshaGoods(String goodsId);

    void recoveryStock(MiaoShaMessage miaoShaMessage);

}
