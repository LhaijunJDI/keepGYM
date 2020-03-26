package com.lhj.keepgym.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.lhj.keepgym.annotations.LoginRequired;
import com.lhj.keepgym.bean.Manager;
import com.lhj.keepgym.bean.Members;
import com.lhj.keepgym.service.ClockService;
import com.lhj.keepgym.service.ManagerService;
import com.lhj.keepgym.utils.CookieUtil;
import com.lhj.keepgym.utils.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shinelon
 */
@Controller
public class ManagerController {
    @Reference(group = "manage")
    private ManagerService managerService;

    @Reference(group = "manage")
    private ClockService clockService;

    /**
     * 管理员登录
     *
     * @param id
     * @param password
     * @param request
     * @return
     */
    @GetMapping("/checkManage")
    @ResponseBody
    public String getManagerById(String id, String password, HttpServletRequest request) {
        String token = "";
        Manager manager = managerService.findManagerById(id, password);
        if (manager != null) {
            Map<String, Object> userMap = new HashMap<String, Object>();
            userMap.put("managerId", id);
            userMap.put("managerName", manager.getName());
            //从request中获取ip
            String ip = "127.0.0.1";
            //按照加密算法生成token
            token = JwtUtil.encode("Lhaijun", userMap, ip);
            if (!StringUtils.isEmpty(token)) {
                return token;
            }
        }
        token = "fail";
        return token;
    }

    //检验token是否正确并将状态和用户id和密码返回给拦截器
    @RequestMapping("/verifyManager")
    @ResponseBody
    public String verify(String token, String currentIp) {

        Map<String, String> map = new HashMap<String, String>();
        // 通过jwt校验token真假
        Map<String, Object> decode = JwtUtil.decode(token, "Lhaijun", currentIp);
        if (StringUtils.isEmpty(decode)) {
            map.put("status", "fail");
            return JSON.toJSONString(map);
        }
        map.put("status", "success");
        map.put("managerId", (String) decode.get("managerId"));
        map.put("managerName", (String) decode.get("managerName"));
        return JSON.toJSONString(map);
    }

    /**
     * 前往后台管理员首页
     *
     * @param managerId
     * @param model
     * @return
     */
    @LoginRequired(isManager = true)
    @RequestMapping("/toManageIndex")
    public String toManageIndex() {
        return "manageIndex";
    }

    /**
     * 前往会员管理页面
     *
     * @param managerId
     * @param model
     * @return
     */
    @LoginRequired(isManager = true)
    @RequestMapping("/toMemberManage")
    public String toMemberManage() {
        return "memberManage";
    }

    /**
     * 前往员工管理页面
     *
     * @param managerId
     * @param model
     * @return
     */
    @LoginRequired(isManager = true)
    @RequestMapping("/toEmployeeManage")
    public String toEmployeeManage() {
        return "employeeManage";
    }

    /**
     * 前往私教团课页面
     *
     * @param managerId
     * @param model
     * @return
     */
    @LoginRequired(isManager = true)
    @RequestMapping("/toCourseManage")
    public String toCourseManage() {
        return "courseManage";
    }

    /**
     * 前往会员通知页面
     *
     * @param managerId
     * @param model
     * @return
     */
    @LoginRequired(isManager = true)
    @RequestMapping("/toNoticeManage")
    public String toNoticeManage() {
        return "noticeManage";
    }

    /**
     * 前往意见反馈页面
     *
     * @param managerId
     * @param model
     * @return
     */
    @LoginRequired(isManager = true)
    @RequestMapping("/toFeedbackManage")
    public String toFeedbackManage() {
        return "feedbackManage";
    }

    /**
     * 前往数据导出页面
     *
     * @param managerId
     * @param model
     * @return
     */
    @LoginRequired(isManager = true)
    @RequestMapping("/toBackUpManage")
    public String toBackUpManage() {
        return "backupManage";
    }

    /**
     * 查询所有的管理员
     *
     * @return
     */
    @GetMapping("/toSearchAllManager")
    @ResponseBody
    public List<Manager> toSearchAllManager() {
        return managerService.findAllManager();
    }

    /**
     * 查询管理员信息
     *
     * @param managerId
     * @return
     */
    @GetMapping("/toSearchManagerInfo")
    @ResponseBody
    public Manager toSearchManagerInfo(String managerId) {
        return managerService.findOneManagerById(managerId);
    }

    /**
     * 修改管理员信息
     *
     * @param manager
     * @return
     */
    @PostMapping("/toAlterManagerInfo")
    @ResponseBody
    public String toAlterManagerInfo(@RequestBody Manager manager) {
        return managerService.updateManagerInfo(manager);
    }


    /**
     * 删除管理员
     *
     * @param managerId
     * @return
     */
    @DeleteMapping("/toDeleteManager")
    @ResponseBody
    public String toDeleteManager(String managerId) {
        return managerService.deleteManagerById(managerId);
    }

    /**
     * 添加管理员
     *
     * @param manager
     * @return
     */
    @PutMapping("/toAddManager")
    @ResponseBody
    public String toAddManager(@RequestBody Manager manager) {
        return managerService.insertManager(manager);
    }


    /**
     * 退出登录
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/toLoginOut")
    public String toLoginOut(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.deleteCookie(request, response, "oldManagerToken");
        return "login";
    }
}
