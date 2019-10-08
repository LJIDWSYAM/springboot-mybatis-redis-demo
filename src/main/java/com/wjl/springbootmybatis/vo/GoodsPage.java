package com.wjl.springbootmybatis.vo;


import com.wjl.springbootmybatis.Utils.Page;
import com.wjl.springbootmybatis.entity.Goods;

import java.util.List;

public class GoodsPage {

    private List<Goods> goods;

    private Page page;

    public List<Goods>  getGoods() {
        return goods;
    }

    public void setGoods(List<Goods>  goods) {
        this.goods = goods;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
