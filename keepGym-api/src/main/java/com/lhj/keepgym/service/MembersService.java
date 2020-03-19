package com.lhj.keepgym.service;

import com.lhj.keepgym.bean.Members;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    List<Map<String, String>> findAllMemberSex();

    String findNewMembers();

    List<HashMap<String, Object>> findAllMembersForPoi();
}
