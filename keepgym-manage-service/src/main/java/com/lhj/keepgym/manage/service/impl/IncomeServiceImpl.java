package com.lhj.keepgym.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Income;
import com.lhj.keepgym.manage.mapper.IncomeMapper;
import com.lhj.keepgym.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private IncomeMapper incomeMapper;

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
        return incomeMapper.selectAllIncome();
    }

    @Override
    public String findIncomeLastMonth() {
        float totalMoney = 0;
        List<Income> incomeLastMonth = incomeMapper.findIncomeLastMonth();
        if (incomeLastMonth != null) {
            for (Income income : incomeLastMonth) {
                totalMoney = totalMoney + Float.parseFloat(income.getMoney());
            }
        }
        return String.valueOf(totalMoney);
    }

    @Override
    public List<Float> findIncomeAtMonth() {
        float money = 0;
        List<Float> list = new ArrayList<Float>();
        List<Income> incomeList = new ArrayList<Income>();
        incomeList = incomeMapper.findIncomeAWeek();
        if (incomeList != null) {
            for (Income income : incomeList) {
                money = money + Float.parseFloat(income.getMoney());
            }
        }
        list.add(money);
        money = 0;

        incomeList = incomeMapper.findIncomeBWeek();
        if (incomeList != null) {
            for (Income income : incomeList) {
                money = money + Float.parseFloat(income.getMoney());
            }
        }
        list.add(money);
        money = 0;

        incomeList = incomeMapper.findIncomeCWeek();
        if (incomeList != null) {
            for (Income income : incomeList) {
                money = money + Float.parseFloat(income.getMoney());
            }
        }
        list.add(money);
        money = 0;

        incomeList = incomeMapper.findIncomeDWeek();
        if (incomeList != null) {
            for (Income income : incomeList) {
                money = money + Float.parseFloat(income.getMoney());
            }
        }
        list.add(money);

        return list;
    }

    @Override
    public List<Float> findIncomeType() {
        float addCardIncome = 0;
        float stopCardIncome = 0;
        float renewCardIncome = 0;
        float coachIncome = 0;
        List<Income> incomeLastMonth = incomeMapper.findIncomeLastMonth();
        List<Float> list = new ArrayList<Float>();
        if (incomeLastMonth != null) {
            for (Income income : incomeLastMonth) {
                if("会员办卡".equals(income.getRevenueType())){
                    addCardIncome = addCardIncome + Float.parseFloat(income.getMoney());
                }
                if("会员续费".equals(income.getRevenueType())){
                    renewCardIncome = renewCardIncome + Float.parseFloat(income.getMoney());
                }
                if("会员停卡".equals(income.getRevenueType())){
                    stopCardIncome = stopCardIncome + Float.parseFloat(income.getMoney());
                }
                if("私教收入".equals(income.getRevenueType())){
                    coachIncome = coachIncome + Float.parseFloat(income.getMoney());
                }
            }
            list.add(addCardIncome);
            list.add(renewCardIncome);
            list.add(stopCardIncome);
            list.add(coachIncome);
            return list;
        }
        return null;
    }

    @Override
    public List<HashMap<String, Object>> findAllIncomeForPoi() {
        return incomeMapper.findAllIncome();
    }
}
