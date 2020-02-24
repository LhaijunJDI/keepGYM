package com.lhj.keepgym.service;

import com.lhj.keepgym.bean.Members;

public interface MembersService {
    public Members findMemberById(String id);
    public String updateMember(Members members);
    String updateMemberPwd(String memberId,String originPwd,String newPwd);
}
