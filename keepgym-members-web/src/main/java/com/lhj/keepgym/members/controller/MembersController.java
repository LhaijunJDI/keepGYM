package com.lhj.keepgym.members.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.lhj.keepgym.annotations.LoginRequired;
import com.lhj.keepgym.bean.Members;
import com.lhj.keepgym.service.MembersService;
import com.lhj.keepgym.utils.CookieUtil;
import com.lhj.keepgym.utils.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MembersController {
    @Reference
    private MembersService membersService;


    //检验token是否正确并将状态和用户id和密码返回给拦截器
    @RequestMapping("/verify")
    @ResponseBody
    public String verify(String token,String currentIp) {

        Map<String,String> map = new HashMap<String, String>();
        // 通过jwt校验token真假
        Map<String, Object> decode = JwtUtil.decode(token, "Lhaijun", currentIp);
        if(StringUtils.isEmpty(decode)){
            map.put("status","fail");
            return JSON.toJSONString(map);
        }
        map.put("status","success");
        map.put("memberId",(String)decode.get("memberId"));
        map.put("memberName",(String)decode.get("memberName"));
        return JSON.toJSONString(map);
    }

    /**
     * 检查用户是否存在及账号密码是否正确，如果正确就为其颁发token
     * @param memberId
     * @param password
     * @param request
     * @return
     */
    @GetMapping("/checkMember")
    @ResponseBody
    public String getMemberById(String memberId, String password, HttpServletRequest request) {
        String token = "";
        Members member = membersService.findMemberByIdAndPwd(memberId, password);
        if (member != null) {
            Map<String, Object> userMap = new HashMap<String, Object>();
            userMap.put("memberId", memberId);
            userMap.put("memberName", member.getUsername());
            //从request中获取ip
            String ip = request.getRemoteAddr();
            if(!StringUtils.isEmpty(ip)){
                ip = "127.0.0.1";
            }
            //按照加密算法生成token
            token = JwtUtil.encode("Lhaijun", userMap, ip);
            if (!StringUtils.isEmpty(token)) {
                return token;
            }
        }
        token = "fail";
        return token;
    }



    //前往会员首页
    @LoginRequired
    @RequestMapping("/toMemberGym")
    public String toMemberGym(String memberId, Model model) {
        model.addAttribute("memberId", memberId);
        return "memberIndex";
    }

    //前往会员信息页面
    @LoginRequired
    @RequestMapping("/toMemberInfo")
    public String toMemberInfo(String memberId, Model model) {
        model.addAttribute("memberId", memberId);
        return "memberInfo";
    }

    //查找会员信息并返回到信息页面
    @LoginRequired
    @RequestMapping("/toSearchMember")
    @ResponseBody
    public Members toSearchMember(String memberId, Model model) {
        model.addAttribute("memberId", memberId);
        Members member = membersService.findMemberById(memberId);
        if(member!=null){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date endTime = member.getEndTime();
        Date createTime = member.getCreateTime();
        Date nowTime = new Date();
        simpleDateFormat.format(endTime);
        member.setEndTime(endTime);
        simpleDateFormat.format(createTime);
        member.setCreateTime(createTime);
        simpleDateFormat.format(nowTime);
        String reamainTime = String.valueOf((endTime.getTime()-nowTime.getTime())/(60*1000*60*24));
        member.setRemainTime(reamainTime);
        if (nowTime.before(endTime)) {
           member.setExpire("true");
        } else {
            member.setExpire("false");
        }
        return member;
        }
        return null;
    }
    //修改会员信息
    @LoginRequired
    @PutMapping("/updateMember")
    @ResponseBody
    public String updateMember(@RequestBody Members members){
        String result = membersService.updateMember(members);
        return  result;
    }

    //修改会员密码
    @LoginRequired
    @PutMapping("/alterMemberPwd")
    @ResponseBody
    public String alterMemberPwd(String memberId,String originPwd,String newPwd){
        String result = membersService.updateMemberPwd(memberId, originPwd, newPwd);
        return result;
    }


    /**
     * 退出登录
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/loginOut")
    public String loginOut(HttpServletRequest request, HttpServletResponse response){
        CookieUtil.deleteCookie(request,response,"oldCookie");
        return "main";
    }


    @LoginRequired
    @RequestMapping("/toMemberNotice")
    public String toMemberNotice(String memberId, Model model) {
        model.addAttribute("memberId", memberId);
        return "memberNotice";
    }
}
