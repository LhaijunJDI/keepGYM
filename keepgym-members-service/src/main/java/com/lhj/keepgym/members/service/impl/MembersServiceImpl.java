package com.lhj.keepgym.members.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Members;
import com.lhj.keepgym.members.mapper.MembersMapper;
import com.lhj.keepgym.service.MembersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

@Service
public class MembersServiceImpl implements MembersService {

    @Autowired
    private MembersMapper membersMapper;

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


}
