package com.lhj.keepgym.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Income;
import com.lhj.keepgym.bean.Members;
import com.lhj.keepgym.manage.mapper.IncomeMapper;
import com.lhj.keepgym.manage.mapper.MembersMapper;
import com.lhj.keepgym.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MembersServiceImpl implements MembersService {

    @Autowired
    private MembersMapper membersMapper;

    @Autowired
    private IncomeMapper incomeMapper;

    @Override
    public Members findMemberByIdAndPwd(String id, String password) {
        return null;
    }

    @Override
    public Members findMemberById(String id) {
        Members members = membersMapper.selectByPrimaryKey(id);
        if (members != null) {
            return members;
        }
        return null;
    }

    @Override
    public String updateMember(Members members) {
        int i = membersMapper.updateByPrimaryKeySelective(members);
        if (i > 0) {
            return "success";
        }
        return "fail";
    }

    @Override
    public String updateMemberPwd(String memberId, String originPwd, String newPwd) {
        return null;
    }

    @Override
    public String insertMember(Members members) {
        Income income = new Income();
        Calendar curr = Calendar.getInstance();
        Date endTime = curr.getTime();
        Date createTime = curr.getTime();
        curr.setTime(endTime);
        members.setCreateTime(createTime);
        if ("年卡".equals(members.getLevel())) {
            curr.add(Calendar.YEAR, 1);
            endTime = curr.getTime();
            members.setEndTime(endTime);
            members.setLevel("年卡");
            income.setMoney("1200.00");
        } else if ("季卡".equals(members.getLevel())) {
            curr.add(Calendar.MONTH, 3);
            endTime = curr.getTime();
            members.setEndTime(endTime);
            members.setLevel("季卡");
            income.setMoney("600.00");
        } else if ("月卡".equals(members.getLevel())) {
            curr.add(Calendar.MONTH, 1);
            endTime = curr.getTime();
            members.setEndTime(endTime);
            members.setLevel("月卡");
            income.setMoney("200.00");
        }
        members.setPassword("123456");
        members.setStatus("可用");
        members.setConfirm("0");
        int i = membersMapper.insertSelective(members);
        if (i > 0) {
            income.setCreateId(members.getCreateId());
            income.setRevenueType("会员办卡");
            income.setCreateTime(createTime);
            int insert = incomeMapper.insertSelective(income);
            if (insert > 0) {
                return "success";
            }
        }
        return "fail";
    }

    @Override
    public List<Members> findAllMembers() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Members> members = membersMapper.selectAll();
        if (members != null) {
            for (Members member : members) {
                if ("1".equals(member.getGender())) {
                    member.setGender("男");
                } else {
                    member.setGender("女");
                }
                member.setStrEndTime(simpleDateFormat.format(member.getEndTime()));
            }
            return members;
        }
        return null;
    }

    @Override
    public String deleteMemberById(String id) {
        int i = membersMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            return "success";
        }
        return "fail";
    }

    @Override
    public String updateRenewMember(String memberId, String createId, String level, String time) {
        Members members = membersMapper.selectByPrimaryKey(memberId);
        if (members != null) {
            String money = null;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(members.getEndTime());
            if ("年卡".equals(level)) {
                calendar.add(Calendar.YEAR, Integer.parseInt(time));
                money = String.valueOf(Integer.parseInt(time) * 900.00);
                members.setLevel(members.getLevel() + "+年卡");
            }
            if ("季卡".equals(level)) {
                calendar.add(Calendar.MONTH, Integer.parseInt(time) * 3);
                money = String.valueOf(Integer.parseInt(time) * 400.00);
                members.setLevel(members.getLevel() + "+季卡");
            }
            if ("月卡".equals(level)) {
                calendar.add(Calendar.MONTH, Integer.parseInt(time));
                money = String.valueOf(Integer.parseInt(time) * 80.00);
                members.setLevel(members.getLevel() + "+月卡");
            }
            members.setEndTime(calendar.getTime());
            int i = membersMapper.updateByPrimaryKeySelective(members);
            if (i > 0) {
                Income income = new Income();
                income.setCreateId(createId);
                income.setMoney(money);
                income.setRevenueType("会员续费");
                income.setCreateTime(new Date());
                int i1 = incomeMapper.insertSelective(income);
                if (i1 > 0) {
                    return "success";
                }
            }
        }
        return "fail";
    }

    @Override
    public String updateStopCardMember(String memberId, String createId, String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        Income income = new Income();
        Members members = membersMapper.selectByPrimaryKey(memberId);
        if (members != null) {
            calendar.setTime(members.getEndTime());
            if ("1".equals(time)) {
                calendar.add(Calendar.MONTH, 1);
                calendar1.add(Calendar.MONTH, 1);
                income.setMoney("50.00");
            }
            if ("2".equals(time)) {
                calendar.add(Calendar.MONTH, 2);
                calendar1.add(Calendar.MONTH, 2);
                income.setMoney("90.00");
            }
            if ("3".equals(time)) {
                calendar.add(Calendar.MONTH, 3);
                calendar1.add(Calendar.MONTH, 3);
                income.setMoney("120.00");
            }
            members.setEndTime(calendar.getTime());
            members.setStatus("禁用至" + simpleDateFormat.format(calendar1.getTime()));
            int i = membersMapper.updateByPrimaryKeySelective(members);
            if (i > 0) {
                income.setRevenueType("会员停卡");
                income.setCreateId(createId);
                income.setCreateTime(new Date());
                int i1 = incomeMapper.insertSelective(income);
                if (i1 > 0) {
                    return "success";
                }
            }
        }
        return "fail";
    }

    @Override
    public List<Members> findAllEndTimeMembers() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date nowTime = new Date();
        int day;
        List<Members> endTimeMembers = new ArrayList<Members>();
        List<Members> members = membersMapper.selectAll();
        if (members != null) {
            for (Members member : members) {
                if ("0".equals(member.getConfirm())) {
                    member.setConfirm("未通知");
                } else {
                    member.setConfirm("已通知");
                }
                day = (int) ((member.getEndTime().getTime() - nowTime.getTime()) / (1000 * 60 * 60 * 24));
                if (day < 30) {
                    member.setRemainTime(String.valueOf(day));
                    member.setStrEndTime(simpleDateFormat.format(member.getEndTime()));
                    endTimeMembers.add(member);
                }
            }
            return endTimeMembers;
        }
        return null;
    }

    @Override
    public List<Map<String, String>> findAllMemberSex() {
        int memNum = 0;
        int womenNum = 0;
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        List<Members> membersList = membersMapper.selectAll();
        if (membersList != null) {
            for (Members members : membersList) {
                if ("1".equals(members.getGender())) {
                    memNum++;
                }
                if ("2".equals(members.getGender())) {
                    womenNum++;
                }
            }
        }
        Map<String, String> menMap = new HashMap<String, String>();
        Map<String, String> womenMap = new HashMap<String, String>();
        menMap.put("name", "男");
        menMap.put("value", String.valueOf(memNum));
        data.add(menMap);
        womenMap.put("name", "女");
        womenMap.put("value", String.valueOf(womenNum));
        data.add(womenMap);
        return data;
    }

    @Override
    public String findNewMembers() {
        List<Members> members = membersMapper.findNewMembers();
        if (members != null) {
            return String.valueOf(members.size());
        }
        return null;
    }

    @Override
    public List<HashMap<String, Object>> findAllMembersForPoi() {
        return membersMapper.findAllMembersForPoi();
    }
}
