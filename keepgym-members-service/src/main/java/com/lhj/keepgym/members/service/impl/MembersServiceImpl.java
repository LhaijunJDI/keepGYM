package com.lhj.keepgym.members.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.members.mapper.MembersMapper;
import com.lhj.keepgym.bean.Members;
import com.lhj.keepgym.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MembersServiceImpl implements MembersService {

    @Autowired
    private MembersMapper membersMapper;

    @Override
    public Members findMemberByIdAndPwd(String id,String password) {
        Members member = new Members();
        member.setId(id);
        member.setPassword(password);
        Members members = membersMapper.selectOne(member);
        if(members == null){
            return null;
        }
        return members;
    }

    @Override
    public Members findMemberById(String id) {
        Members member = membersMapper.findMemberById(id);
        return member;
    }

    @Override
    public String updateMember(Members members) {
        if(StringUtils.isEmpty(members.getId())){
            return "fail";
        }
        Example example =new Example(Members.class);
        example.createCriteria().andEqualTo("id",members.getId());
        membersMapper.updateByExampleSelective(members,example);
        return "success";
    }

    @Override
    public String updateMemberPwd(String memberId, String originPwd, String newPwd) {
        Members members = membersMapper.findMemberById(memberId);
        if(members != null){
            if(originPwd.equals(members.getPassword())){
                members.setPassword(newPwd);
                int i = membersMapper.updateByPrimaryKeySelective(members);
                if(i>0){
                    return "success";
                }
            }
        }
        return "fail";
    }

    @Override
    public String insertMember(Members members) {
        return null;
    }

    @Override
    public List<Members> findAllMembers() {
        return null;
    }

    @Override
    public String deleteMemberById(String memberId) {
        return null;
    }

    @Override
    public String updateRenewMember(String memberId, String createId, String level, String time) {
        return null;
    }

    @Override
    public String updateStopCardMember(String memberId, String createId, String time) {
        return null;
    }

    @Override
    public List<Members> findAllEndTimeMembers() {
        return null;
    }

    @Override
    public List<Map<String, String>> findAllMemberSex() {
        return null;
    }

    @Override
    public String findNewMembers() {
        return null;
    }

    @Override
    public List<HashMap<String, Object>> findAllMembersForPoi() {
        return null;
    }


}
