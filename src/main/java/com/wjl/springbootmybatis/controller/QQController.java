package com.wjl.springbootmybatis.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.wjl.springbootmybatis.Utils.URLEncodeUtil;
import com.wjl.springbootmybatis.config.Constants;
import com.wjl.springbootmybatis.entity.QQUserInfo;
import com.wjl.springbootmybatis.entity.UserInfo;
import com.wjl.springbootmybatis.service.QQService;
import com.wjl.springbootmybatis.service.UserService;
import org.apache.commons.lang3.StringUtils;
import com.wjl.springbootmybatis.Utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.ModelAndView;


/**
 * qq登录
 *
 * @author wangsong
 * @date 2019年6月18日 下午8:04:15
 */
@Controller
public class QQController {

    /**
     * QQ ：读取Appid相关配置信息静态类
     */
    @Autowired
    private Constants constants;
    @Autowired
    QQService qqService;


    /**
     * 获得跳转到qq登录页的url,前台直接a连接访问
     *
     * @author wangsong
     * @date 2019年6月18日 下午8:29:20
     * @param session
     * @return
     * @throws Exception
     */
    @GetMapping("/getQQCode")
        public String getCode(HttpSession session ) throws Exception {
        // 拼接url
        StringBuilder url = new StringBuilder();
        url.append("https://graph.qq.com/oauth2.0/authorize?");
        url.append("response_type=code");
        url.append("&client_id=" + constants.getQqAppId());
        // 回调地址 ,回调地址要进行Encode转码
        String redirect_uri = constants.getQqRedirectUrl();
        // 转码
        url.append("&redirect_uri=" + URLEncodeUtil.getURLEncoderString(redirect_uri));
        url.append("&state=ok");
        // HttpClientUtils.get(url.toString(), "UTF-8");
        return "redirect:" + url;
    }



    /**
     * 开始登录
     *
     * @param code
     * @param
     * @param ：token过期调用刷新    token重新获取token信息
     * @param : accessToken，expiresIn，refreshToken，openId
     * @return
     * @throws Exception
     */
    @GetMapping("/QQLogin")
    public String QQLogin(String code, Model model,HttpSession session) throws Exception {
        //获取tocket
        Map<String, Object> qqProperties = getToken(code);
        //获取openId(每个用户的openId都是唯一不变的)
        String openId = getOpenId(qqProperties);
        qqProperties.put("openId",openId);

        //tocket过期刷新token
        //Map<String, Object> refreshToken = refreshToken(qqProperties);

        //获取数据
        QQUserInfo	userInfo =  getUserInfo(qqProperties);
        userInfo.setOpenId(openId);
        //向数据库里插入qq用户信息
        QQUserInfo qqUserInfo=qqService.selectQuserInfoByOpenid(openId);
        if (qqUserInfo==null){
            qqService.insertintoQquserInfo(userInfo);
        }
        UserInfo userInfo1=new UserInfo();
        userInfo1.setUser_account(openId);
        session.setAttribute("UserInfo",userInfo1);
        return "redirect:/listProducts";
    }

    /**
     * 获得token信息（授权，每个用户的都不一致） --> 获得token信息该步骤返回的token期限为一个月
     *
     * @param (<String,String> qqProperties)
     * @author wangsong
     * @return
     * @throws Exception
     * @date 2019年6月18日 下午8:56:45
     */
    public Map<String, Object> getToken(String code) throws Exception {
        StringBuilder url = new StringBuilder();
        url.append("https://graph.qq.com/oauth2.0/token?");
        url.append("grant_type=authorization_code");

        url.append("&client_id=" + constants.getQqAppId());
        url.append("&client_secret=" + constants.getQqAppSecret());
        url.append("&code=" + code);
        // 回调地址
        String redirect_uri = constants.getQqRedirectUrl();
        // 转码
        url.append("&redirect_uri=" + URLEncodeUtil.getURLEncoderString(redirect_uri));
        // 获得token
        String result = HttpClientUtils.get(url.toString(), "UTF-8");
        // 把token保存
        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(result, "&");
        String accessToken = StringUtils.substringAfterLast(items[0], "=");
        Long expiresIn = new Long(StringUtils.substringAfterLast(items[1], "="));
        String refreshToken = StringUtils.substringAfterLast(items[2], "=");
        //token信息
        Map<String,Object > qqProperties = new HashMap<String,Object >();
        qqProperties.put("accessToken", accessToken);
        qqProperties.put("expiresIn", String.valueOf(expiresIn));
        qqProperties.put("refreshToken", refreshToken);
        return qqProperties;
    }

    /**
     * 刷新token 信息（token过期，重新授权）
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/refreshToken")
    public Map<String,Object> refreshToken(Map<String,Object> qqProperties) throws Exception {
        // 获取refreshToken
        String refreshToken = (String) qqProperties.get("refreshToken");
        StringBuilder url = new StringBuilder("https://graph.qq.com/oauth2.0/token?");
        url.append("grant_type=refresh_token");
        url.append("&client_id=" + constants.getQqAppId());
        url.append("&client_secret=" + constants.getQqAppSecret());
        url.append("&refresh_token=" + refreshToken);
        String result = HttpClientUtils.get(url.toString(), "UTF-8");
        // 把新获取的token存到map中
        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(result, "&");
        String accessToken = StringUtils.substringAfterLast(items[0], "=");
        Long expiresIn = new Long(StringUtils.substringAfterLast(items[1], "="));
        String newRefreshToken = StringUtils.substringAfterLast(items[2], "=");
        //重置信息
        qqProperties.put("accessToken", accessToken);
        qqProperties.put("expiresIn", String.valueOf(expiresIn));
        qqProperties.put("refreshToken", newRefreshToken);
        return qqProperties;
    }

    /**
     * 获取用户openId（根据token）
     *
     * @param
     * @return
     * @throws Exception
     */
    public String getOpenId(Map<String,Object> qqProperties) throws Exception {
        // 获取保存的用户的token
        String accessToken = (String) qqProperties.get("accessToken");
        if (!StringUtils.isNotEmpty(accessToken)) {
            // return "未授权";
        }
        StringBuilder url = new StringBuilder("https://graph.qq.com/oauth2.0/me?");
        url.append("access_token=" + accessToken);
        String result = HttpClientUtils.get(url.toString(), "UTF-8");
        String openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
        return openId;
    }

    /**
     * 根据token,openId获取用户信息
     */
    public QQUserInfo getUserInfo(Map<String,Object> qqProperties) throws Exception {
        // 取token
        String accessToken = (String) qqProperties.get("accessToken");
        String openId = (String) qqProperties.get("openId");
        if (!StringUtils.isNotEmpty(accessToken) || !StringUtils.isNotEmpty(openId)) {
            return null;
        }
        //拼接url
        StringBuilder url = new StringBuilder("https://graph.qq.com/user/get_user_info?");
        url.append("access_token=" + accessToken);
        url.append("&oauth_consumer_key=" + constants.getQqAppId());
        url.append("&openid=" + openId);
        // 获取qq相关数据
        String result = HttpClientUtils.get(url.toString(), "UTF-8");
        Object json = JSON.parseObject(result, QQUserInfo.class);
        QQUserInfo userInfo = (QQUserInfo) json;
        return userInfo;
    }
}
