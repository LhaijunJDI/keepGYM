package com.lhj.keepgym.members.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.bean.Members;
import com.lhj.keepgym.service.MembersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MembersController {
    @Reference
    private MembersService membersService;

    //验证会员是否存在
    @GetMapping("/getMemberById")
    @ResponseBody
    public String getMemberById(String id, String password) {
        Members member = membersService.findMemberById(id);
        if (member != null) {
            if (password.equals(member.getPassword())) {
                return "OK";
            } else {
                return "False";
            }
        }
        return "noMember";

    }
    //前往会员首页
    @RequestMapping("/toMemberGym")
    public String toMemberGym(String memberId, Model model) {
        model.addAttribute("memberId", memberId);
        return "memberIndex";
    }

    //前往会员信息页面
    @RequestMapping("/toMemberInfo")
    public String toMemberInfo(String memberId, Model model) {
        model.addAttribute("memberId", memberId);
        return "memberInfo";
    }

    //查找会员信息并返回到信息页面
    @RequestMapping("/toSearchMember")
    @ResponseBody
    public Members toSearchMember(String memberId, Model model) {
        model.addAttribute("memberId", memberId);
        Members member = membersService.findMemberById(memberId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date endTime = member.getEndTime();
        Date createTime = member.getCreateTime();
        Date nowTime = new Date();
        simpleDateFormat.format(endTime);
        member.setEndTime(endTime);
        simpleDateFormat.format(createTime);
        member.setCreateTime(createTime);
        simpleDateFormat.format(nowTime);
        if (nowTime.before(endTime)) {
           member.setExpire("true");
        } else {
            member.setExpire("false");
        }
        return member;
    }
    //修改会员信息
    @PutMapping("/updateMember")
    @ResponseBody
    public String updateMember(@RequestBody Members members){
        String result = membersService.updateMember(members);
        return  result;
    }

    //修改会员密码
    @PutMapping("/alterMemberPwd")
    @ResponseBody
    public String alterMemberPwd(String memberId,String originPwd,String newPwd){
        String result = membersService.updateMemberPwd(memberId, originPwd, newPwd);
        return result;
    }


}
