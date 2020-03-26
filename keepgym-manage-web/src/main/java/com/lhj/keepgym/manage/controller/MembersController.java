package com.lhj.keepgym.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.annotations.LoginRequired;
import com.lhj.keepgym.bean.Members;
import com.lhj.keepgym.service.MembersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Shinelon
 */
@RestController
public class MembersController {

   @Reference(group = "manage")
    private MembersService membersService;

    /**
     * 添加会员
     * @param members
     * @return
     */
    @PutMapping("/toAddMember")
    public String toAddMember(@RequestBody Members members){
        return membersService.insertMember(members);
    }

    /**
     * 查询全部会员信息
     * @return
     */
    @GetMapping("/toSearchAllMembers")
    public List<Members> toAddMember(){
        return membersService.findAllMembers();
    }

    /**
     * 查询还有一个月到期的会员
     * @return
     */
    @GetMapping("/toSearchAllEndTimeMembers")
    public List<Members> toSearchAllEndTimeMembers(){
        return membersService.findAllEndTimeMembers();
    }

    /**
     * 查询会员信息
     * @param memberId
     * @return
     */
    @GetMapping("/toSearchMemberInfo")
    public Members toSearchMemberInfo(String memberId){
        return membersService.findMemberById(memberId);
    }


    /**
     * 修改会员信息
     * @param members
     * @return
     */
    @PostMapping("/toAlterMemberInfo")
    public String toAlterMemberInfo(@RequestBody Members members){
        return membersService.updateMember(members);
    }

    /**
     * 删除会员
     * @param memberId
     * @return
     */
    @DeleteMapping("/toDeleteMember")
    public String toDeleteMember(String memberId){
        return membersService.deleteMemberById(memberId);
    }

    /**
     * 会员续费
     * @param memberId
     * @param createId
     * @param level
     * @param time
     * @return
     */
    @PostMapping("/toRenewMember")
    public String toRenewMember(String memberId,String createId,String level,String time){
        return membersService.updateRenewMember(memberId,createId,level,time);
    }

    /**
     * 会员停卡
     * @param memberId
     * @param createId
     * @param time
     * @return
     */
    @PostMapping("/toStopCard")
    public String toStopCard(String memberId,String createId,String time){
        return membersService.updateStopCardMember(memberId,createId,time);
    }

    /**
     * 统计所有会员的性别
     * @return
     */
    @GetMapping("/toSearchMembersSex")
    @ResponseBody
    public List<Map<String,String>> toSearchMembersSex(){
        return membersService.findAllMemberSex();
    }

    /**
     * 查询本月新增会员
     * @return
     */
    @GetMapping("/toSearchNewMembers")
    @ResponseBody
    public String toSearchNewMembers(){
        return membersService.findNewMembers();
    }


}
