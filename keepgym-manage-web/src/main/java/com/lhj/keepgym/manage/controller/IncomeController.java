package com.lhj.keepgym.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.keepgym.annotations.LoginRequired;
import com.lhj.keepgym.service.IncomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @author Shinelon
 */
@RestController
public class IncomeController {

    @Reference
    private IncomeService incomeService;

    /**
     *
     * @return 查询上个月的总收入
     */
    @GetMapping("/toSearchTotalIncome")
    public String toSearchTotalIncome() {
        return incomeService.findIncomeLastMonth();
    }

    /**
     *
     * @return 上个月每周的收入
     */
    @GetMapping("/toSearchIncomeAtMonth")
    public List<Float> toSearchIncomeAtMonth() {
        return incomeService.findIncomeAtMonth();
    }

    /**
     *
     * @return 上个月收入分布
     */
    @GetMapping("/toSearchIncomeType")
    public List<Float> toSearchIncomeType() {
        return incomeService.findIncomeType();
    }

}
