package com.wjl.springbootmybatis.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;

import com.wjl.springbootmybatis.Utils.AlipayConfig;
import com.wjl.springbootmybatis.entity.MiaoShaMessage;
import com.wjl.springbootmybatis.entity.OrderDetailInfoVo;
import com.wjl.springbootmybatis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/alipay")
public class AlipayController {
    // 获取配置文件的信息
    String app_id = AlipayConfig.app_id;
    String private_key = AlipayConfig.private_key;
    String notify_url = AlipayConfig.notify_url;
    String return_url = AlipayConfig.return_url;
    String url = AlipayConfig.url;
    String charset = AlipayConfig.charset;
    String format = AlipayConfig.format;
    String public_key = AlipayConfig.public_key;
    String signtype = AlipayConfig.signtype;

    @Autowired
    OrderService orderService;

    /**
     * 支付请求
     *
     * @param "order"
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/pay")
    public void pay(HttpServletRequest request, HttpServletResponse response, String order_no, HttpSession session) throws Exception {
//        OrderInfo orderInfo= (OrderInfo) modelAndView.getModel().get("orderInfo");
//        OrderInfo orderInfo= (OrderInfo) request.getAttribute("orderInfo");

//        OrderInfo orderInfo = orderService.getOrderInfoByOrderNo(orderNo);
//       模拟从前台传来的数据
////        String orderNo = orderInfo.getOrderNo(); // 生成订单号
//        String totalAmount = String.valueOf(orderInfo.getMiaoShaGoods().getMiaosha_price()); // 支付总金额
//        String subject = "商品秒杀System.out.println订单"; // 订单名称
//        String body = orderInfo.getMiaoShaGoods().getGoods().getGoods_desc(); // 商品描述
        OrderDetailInfoVo orderInfoVo=orderService.selectAllInfoByOrderNo(order_no);
        String orderNo =orderInfoVo.getOrder_no();//订单编号
        String totalAmount =String.valueOf(orderInfoVo.getMiaoshaGoods().getMiaosha_price());//总价格
        String subject = orderInfoVo.getGoods().getGoods_name(); // 订单名称
        String body = orderInfoVo.getGoods().getGoods_desc();//订单描述
        // 封装请求客户端
        AlipayClient client = new DefaultAlipayClient(url, app_id, private_key,
                format, charset, public_key, signtype);
        String path = request.getRequestURI();
        path = path.substring(0, path.lastIndexOf("/"));
//        String resulturl = "http://localhost:8080/" + path + return_url;
        String resulturl = path + return_url;
        // 支付请求
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(resulturl);
        alipayRequest.setNotifyUrl(notify_url);
        AlipayTradePayModel model = new AlipayTradePayModel();
        model.setProductCode("FAST_INSTANT_TRADE_PAY"); // 设置销售产品码
        model.setOutTradeNo(orderNo); // 设置订单号
        model.setSubject(subject); // 订单名称
        model.setTotalAmount(totalAmount); // 支付总金额
        model.setBody(body); // 设置商品描述
        alipayRequest.setBizModel(model);

        String form = client.pageExecute(alipayRequest).getBody(); // 生成表单

        response.setContentType("text/html;charset=" + charset);
        response.getWriter().write(form); // 直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * 同步跳转
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping("/returnUrl")
    public ModelAndView returnUrl(HttpServletRequest request,HttpSession session) throws Exception {
        ModelAndView mav = new ModelAndView();
        // 获取支付宝GET过来反馈信息（官方固定代码）
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, public_key, charset, signtype); // 调用SDK验证签名

        // 返回界面
        if (signVerified) {
            String orderNo = params.get("out_trade_no");
            String alipayNo = params.get("trade_no");
            OrderDetailInfoVo orderInfoVo = new OrderDetailInfoVo();
            orderInfoVo.setOrder_no(orderNo);
            orderInfoVo.setOrder_pay_no(alipayNo);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            orderInfoVo.setPay_time(df.parse(df.format(new Date())));// new Date()为获取当前系统时间
            orderService.updateOrder(orderInfoVo);
            //改变订单状态
            MiaoShaMessage miaoShaMessage=new MiaoShaMessage();
            miaoShaMessage.setUser_account(orderInfoVo.getUser_account());
            miaoShaMessage.setMiaoshagoods_id(String.valueOf(orderInfoVo.getMiaoshagoods_id()));
            orderService.updateOrderState(miaoShaMessage);

            OrderDetailInfoVo orderInfoVo2=orderService.selectAllInfoByOrderNo(orderNo);
            session.setAttribute("orderInfoVo",orderInfoVo2);
            mav.setViewName("redirect:/orderInfo");
        } else {
            System.out.println("前往支付失败页面");
            mav.setViewName("failReturn");
        }
        return mav;
    }

    /**
     * 支付宝服务器异步通知
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping("/notifyUrl")
    public void notifyUrl(HttpServletRequest request) throws Exception {
        System.out.printf("支付成功2");
        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, public_key, charset, signtype); // 调用SDK验证签名

        if (signVerified) { // 验证成功 更新订单信息
            System.out.println("异步通知成功");
            // 商户订单号
            String out_trade_no = request.getParameter("out_trade_no");
            // 交易状态
            String trade_status = request.getParameter("trade_status");
            // 修改数据库
        } else {
            System.out.println("异步通知失败");
        }
    }

}
