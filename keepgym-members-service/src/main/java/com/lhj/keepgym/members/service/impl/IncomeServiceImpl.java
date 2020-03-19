package com.lhj.keepgym.members.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Income;
import com.lhj.keepgym.bean.Members;
import com.lhj.keepgym.members.mapper.IncomeMapper;
import com.lhj.keepgym.members.mapper.MembersMapper;
import com.lhj.keepgym.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Shinelon
 */
@Service
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    private IncomeMapper incomeMapper;

    @Autowired
    private MembersMapper membersMapper;

    @Override
    public Income queryById(Integer id) {
        return null;
    }

    @Override
    public List<Income> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public Income insert(Income income) {
        income.setCreateTime(new Date());
        String memberId = income.getCreateId();
        income.setCreateId("10001");
        int insert = incomeMapper.insert(income);
        if(insert>0){
            Calendar calendar = Calendar.getInstance();
            Members members = membersMapper.selectByPrimaryKey(memberId);
            calendar.setTime(members.getEndTime());
            if("200.00".equals(income.getMoney())){
                calendar.add(Calendar.MONTH,1);
            }
            if("600.00".equals(income.getMoney())){
                calendar.add(Calendar.MONTH,3);
            }
            if("1200.00".equals(income.getMoney())){
                calendar.add(Calendar.MONTH,12);
            }
            members.setEndTime(calendar.getTime());
            int i = membersMapper.updateByPrimaryKeySelective(members);
            if(i>0){
                return income;
            }
        }
        return null;
    }

    @Override
    public Income update(Income income) {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public List<Income> findAllIncome() {
        return null;
    }

    @Override
    public String findIncomeLastMonth() {
        return null;
    }

    @Override
    public List<Float> findIncomeAtMonth() {
        return null;
    }

    @Override
    public List<Float> findIncomeType() {
        return null;
    }

    @Override
    public List<HashMap<String, Object>> findAllIncomeForPoi() {
        return null;
    }
}
