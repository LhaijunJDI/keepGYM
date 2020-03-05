package com.lhj.keepgym.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.bean.Members;
import com.lhj.keepgym.service.MembersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MembersController {

   @Reference
    private MembersService membersService;

    @PutMapping("/toAddMember")
    public String toAddMember(@RequestBody Members members){
        String result = membersService.insertMember(members);
        return result;
    }

    @GetMapping("/toSearchAllMembers")
    public List<Members> toAddMember(){
        List<Members> members = membersService.findAllMembers();
        return members;
    }

    @GetMapping("/toSearchAllEndTimeMembers")
    public List<Members> toSearchAllEndTimeMembers(){
        List<Members> members = membersService.findAllEndTimeMembers();
        return members;
    }


    @GetMapping("/toSearchMemberInfo")
    public Members toSearchMemberInfo(String memberId){
        Members member = membersService.findMemberById(memberId);
        return member;
    }


    @PostMapping("/toAlterMemberInfo")
    public String toAlterMemberInfo(@RequestBody Members members){
        String result = membersService.updateMember(members);
        return result;
    }


    @DeleteMapping("/toDeleteMember")
    public String toDeleteMember(String memberId){
        String result = membersService.deleteMemberById(memberId);
        return result;
    }


    @PostMapping("/toRenewMember")
    public String toRenewMember(String memberId,String createId,String level,String time){
        String result = membersService.updateRenewMember(memberId,createId,level,time);
        return result;
    }

    @PostMapping("/toStopCard")
    public String toStopCard(String memberId,String createId,String time){
        String result = membersService.updateStopCardMember(memberId,createId,time);
        return result;
    }
}
