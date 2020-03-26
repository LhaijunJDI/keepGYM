package com.lhj.keepgym.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhj.keepgym.bean.Income;
import com.lhj.keepgym.manage.mapper.IncomeMapper;
import com.lhj.keepgym.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Shinelon
 */
@Service(group = "manage")
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
    public List<Double> findIncomeAtMonth() {
        Double money = 0.0;
        List<Double> list = new ArrayList<Double>();
        List<Income> incomeList;
        incomeList = incomeMapper.findIncomeAWeek();
        if (incomeList != null) {
            for (Income income : incomeList) {
                money = money + Double.parseDouble(income.getMoney());
            }
        }
        list.add(money);
        money = 0.0;

        incomeList = incomeMapper.findIncomeBWeek();
        if (incomeList != null) {
            for (Income income : incomeList) {
                money = money + Double.parseDouble(income.getMoney());
            }
        }
        list.add(money);
        money = 0.0;

        incomeList = incomeMapper.findIncomeCWeek();
        if (incomeList != null) {
            for (Income income : incomeList) {
                money = money + Double.parseDouble(income.getMoney());
            }
        }
        list.add(money);
        money = 0.0;

        incomeList = incomeMapper.findIncomeDWeek();
        if (incomeList != null) {
            for (Income income : incomeList) {
                money = money + Double.parseDouble(income.getMoney());
            }
        }
        list.add(money);

        return list;
    }

    @Override
    public List<Double> findIncomeType() {
        Double addCardIncome = 0.0;
        Double stopCardIncome = 0.0;
        Double renewCardIncome = 0.0;
        Double coachIncome = 0.0;
        List<Income> incomeLastMonth = incomeMapper.findIncomeLastMonth();
        List<Double> list = new ArrayList<Double>();
        if (incomeLastMonth != null) {
            for (Income income : incomeLastMonth) {
                if("会员办卡".equals(income.getRevenueType())){
                    addCardIncome = addCardIncome + Double.parseDouble(income.getMoney());
                }
                if("会员续费".equals(income.getRevenueType())){
                    renewCardIncome = renewCardIncome + Double.parseDouble(income.getMoney());
                }
                if("会员停卡".equals(income.getRevenueType())){
                    stopCardIncome = stopCardIncome + Double.parseDouble(income.getMoney());
                }
                if("私教收入".equals(income.getRevenueType())){
                    coachIncome = coachIncome + Double.parseDouble(income.getMoney());
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
