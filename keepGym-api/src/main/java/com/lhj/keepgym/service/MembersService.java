package com.lhj.keepgym.service;

import com.lhj.keepgym.bean.Members;

import java.util.List;

public interface MembersService {
    public Members findMemberByIdAndPwd(String id,String password);
    public Members findMemberById(String id);
    public String updateMember(Members members);
    String updateMemberPwd(String memberId,String originPwd,String newPwd);

    String insertMember(Members members);

    List<Members> findAllMembers();

    String deleteMemberById(String memberId);

    String updateRenewMember(String memberId,String createId,String level, String time);

    String updateStopCardMember(String memberId, String createId, String time);

    List<Members> findAllEndTimeMembers();
}
