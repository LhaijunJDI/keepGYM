package com.lhj.keepgym.members.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Members;
import com.lhj.keepgym.members.mapper.MembersMapper;
import com.lhj.keepgym.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MembersServiceImpl implements MembersService {

    @Autowired
    private MembersMapper membersMapper;

    @Override
    public Members findMemberById(int id) {
        Members member = membersMapper.findMemberById(id);
        return member;
    }
}
