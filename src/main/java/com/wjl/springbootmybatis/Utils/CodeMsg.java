package com.wjl.springbootmybatis.Utils;

public class CodeMsg {
    private int code;
    private String msg;
    public static CodeMsg Success=new CodeMsg(0,"success");
    public static CodeMsg loginError=new CodeMsg(5000,"请先登录");
    public static CodeMsg saverError=new CodeMsg(5001,"服务端异常");
    public static CodeMsg goodsSaled=new CodeMsg(5002,"商品已售完");
    public static CodeMsg goodsRepeatPurchase=new CodeMsg(5003,"商品每人限购一件");
    public static CodeMsg goodsNoPay=new CodeMsg(5004,"商品还未完成支付，即将跳转到支付页面");
    public static CodeMsg goodsTimeOut=new CodeMsg(5005,"对不起，超时未支付，已无法购买");
    public CodeMsg(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
