package com.lhj.keepgym.members.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.lhj.keepgym.bean.Income;
import com.lhj.keepgym.members.config.AlipayConfig;
import com.lhj.keepgym.service.IncomeService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author Shinelon
 */
@Controller
public class AliPayController {
    @Reference(group = "member")
    private IncomeService incomeService;

    /**
     * 存储订单信息后前往支付页面
     * @param totalAmount
     * @param subject
     * @param memberId
     * @return
     */
    @RequestMapping("/goPay")
    public ModelAndView goPay(String totalAmount, String subject, String memberId) {
        ModelAndView mv = new ModelAndView("redirect:/toPay");
        mv.addObject("totalAmount", totalAmount);
        mv.addObject("subject", subject+"费用");
        mv.addObject("memberId", memberId);
        return mv;
    }


    /**
     * alipay支付页面
     * @param totalAmount
     * @param subject
     * @param memberId
     * @param httpResponse
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/toPay", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public void toPay(String totalAmount, String subject, String memberId,
                        HttpServletResponse httpResponse) throws ServletException, IOException {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        UUID uuid = UUID.randomUUID();
        String out_trade_no = memberId+"_"+uuid.toString();
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"total_amount\":\"" + totalAmount + "\","
                + "\"body\":\"" + subject + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + AlipayConfig.charset);
        httpResponse.getWriter().write(form);
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();

    }

    /**
     * 支付成功后返回的页面
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/alipayCallback")
    public String alipayCallback(HttpServletResponse response, HttpServletRequest request) {

        String out_trade_no=request.getParameter("out_trade_no");

        String sign = request.getParameter("sign");
        String totalAmount = request.getParameter("total_amount");
        String subject = request.getParameter("subject");
        String memberId = out_trade_no.split("_")[0];
        Income income = new Income();
        if (StringUtils.isNotBlank(sign)) {
            income.setCreateId(memberId);
            income.setRevenueType("会员续费");
            income.setMoney(totalAmount);
            incomeService.insert(income);
        }
        return "redirect:toMemberInfo?memberId="+memberId;
    }


    @RequestMapping("/alipayNotify")
    public String alipayNotify(HttpServletRequest request){

        return "success";
    }

}
