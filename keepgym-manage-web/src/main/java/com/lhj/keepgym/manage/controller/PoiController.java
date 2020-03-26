package com.lhj.keepgym.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.lhj.keepgym.service.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;



/**
 * 导出到数据excel
 * @author Shinelon
 */
@RestController
public class PoiController {

    @Reference(group = "manage")
    private IncomeService incomeService;

    @Reference(group = "manage")
    private MembersService membersService;

    @Reference(group = "manage")
    private ManagerService managerService;

    @Reference(group = "manage")
    private CoachService coachService;

    @Reference(group = "manage")
    private FeedbackService feedbackService;

    @GetMapping("/toIncomeExcel")
    public String toImportIncomeExcel( HttpServletResponse response) throws FileNotFoundException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // 创建一个文件
        String path = "keepGym收入报表.xlsx";
        List<HashMap<String, Object>> lists = incomeService.findAllIncomeForPoi();
        String[] title = {"序号", "类型", "金额", "创建时间", "经办人id"};
        // 创建excel工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建一个工作表sheet
        XSSFSheet sheet = workbook.createSheet();
        // 创建第一行
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = null;

        // 插入第一行数据 id 地区名称
        for (int i = 0; i < title.length; i++) {
            // 创建一行的一格
            cell = row.createCell(i);
            // 赋值
            cell.setCellValue(title[i]);
        }
        // 追加数据行数
        int j = 1;
        HashMap<String, Object> list = null;
        for (int i = 0; i < lists.size(); i++) {
            // 从集合中得到一个对象
            list = lists.get(i);
            // 创建第2行

            XSSFRow nextrow = sheet.createRow(i + 1);
            // 创建第1列并赋值
            XSSFCell cessk = nextrow.createCell(0);
            cessk.setCellValue(String.valueOf(list.get("id")));

            cessk = nextrow.createCell(1);
            cessk.setCellValue((String) list.get("revenue_type"));

            cessk = nextrow.createCell(2);
            cessk.setCellValue((String) list.get("money"));

            cessk = nextrow.createCell(3);
            cessk.setCellValue(simpleDateFormat.format(list.get("create_time")));

            cessk = nextrow.createCell(4);
            cessk.setCellValue((String) list.get("create_id"));
//可自己再按照需要添加函数，注意将cessk = nextrow.createCell(num);这其中的num修改就行，按照顺序加上1就行
            j++;
        }

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + path);
        FileOutputStream income = new FileOutputStream("E:/excel/" + path);
        try {
            workbook.write(income);
            income.flush();
            income.close();
            return "success";
        } catch (IOException ignored) {
        }
        return "fail";

    }

    @GetMapping("/toMembersExcel")
    public String toMembersExcel(HttpServletResponse response) throws FileNotFoundException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 创建一个文件
        String path = "keepGym会员信息表.xlsx";
        List<HashMap<String, Object>> lists = membersService.findAllMembersForPoi();
        String[] title = {"id", "姓名", "密码", "性别", "电话", "邮箱",
                "地址", "办理人", "办理时间", "结束时间","会员卡等级", "会员卡状态"};
        // 创建excel工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建一个工作表sheet
        XSSFSheet sheet = workbook.createSheet();
        // 创建第一行
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = null;

        // 插入第一行数据 id 地区名称
        for (int i = 0; i < title.length; i++) {
            // 创建一行的一格
            cell = row.createCell(i);
            // 赋值
            cell.setCellValue(title[i]);
        }
        // 追加数据行数
        int j = 1;
        HashMap<String, Object> list = null;
        for (int i = 0; i < lists.size(); i++) {
            // 从集合中得到一个对象
            list = lists.get(i);
            // 创建第2行

            XSSFRow nextrow = sheet.createRow(i + 1);
            // 创建第1列并赋值
            XSSFCell cessk = nextrow.createCell(0);
            cessk.setCellValue((String)(list.get("id")));

            cessk = nextrow.createCell(1);
            cessk.setCellValue((String) list.get("username"));

            cessk = nextrow.createCell(2);
            cessk.setCellValue((String) list.get("password"));

            cessk = nextrow.createCell(3);
            cessk.setCellValue((String) list.get("gender"));

            cessk = nextrow.createCell(4);
            cessk.setCellValue((String) list.get("phone"));

            cessk = nextrow.createCell(5);
            cessk.setCellValue((String) list.get("email"));

            cessk = nextrow.createCell(6);
            cessk.setCellValue((String) list.get("address"));

            cessk = nextrow.createCell(7);
            cessk.setCellValue((String) list.get("create_id"));

            cessk = nextrow.createCell(8);
            cessk.setCellValue(simpleDateFormat.format( list.get("create_time")));

            cessk = nextrow.createCell(9);
            cessk.setCellValue(simpleDateFormat.format(list.get("end_time")));

            cessk = nextrow.createCell(10);
            cessk.setCellValue((String) list.get("level"));

            cessk = nextrow.createCell(11);
            cessk.setCellValue((String) list.get("status"));
//可自己再按照需要添加函数，注意将cessk = nextrow.createCell(num);这其中的num修改就行，按照顺序加上1就行
            j++;
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + path);
        FileOutputStream income = new FileOutputStream("E:/excel/" + path);
        try {
            workbook.write(income);
            income.flush();
            income.close();
            return "success";
        } catch (IOException ignored) {
        }
        return "fail";

    }

    @GetMapping("/toManagerExcel")
    public String toManagerExcel( HttpServletResponse response) throws FileNotFoundException {
        // 创建一个文件
        String path = "keepGym管理员信息表.xlsx";
        List<HashMap<String, Object>> lists = managerService.findAllManagerForPoi();
        String[] title = {"id", "姓名", "密码", "性别", "电话", "邮箱",
                "地址", "职位", "工资卡"};
        // 创建excel工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建一个工作表sheet
        XSSFSheet sheet = workbook.createSheet();
        // 创建第一行
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = null;

        // 插入第一行数据 id 地区名称
        for (int i = 0; i < title.length; i++) {
            // 创建一行的一格
            cell = row.createCell(i);
            // 赋值
            cell.setCellValue(title[i]);
        }
        // 追加数据行数
        int j = 1;
        HashMap<String, Object> list = null;
        for (int i = 0; i < lists.size(); i++) {
            // 从集合中得到一个对象
            list = lists.get(i);
            // 创建第2行

            XSSFRow nextrow = sheet.createRow(i + 1);
            // 创建第1列并赋值
            XSSFCell cessk = nextrow.createCell(0);
            cessk.setCellValue((String)(list.get("id")));

            cessk = nextrow.createCell(1);
            cessk.setCellValue((String) list.get("name"));

            cessk = nextrow.createCell(2);
            cessk.setCellValue((String) list.get("password"));

            cessk = nextrow.createCell(3);
            cessk.setCellValue((String) list.get("gender"));

            cessk = nextrow.createCell(4);
            cessk.setCellValue((String) list.get("phone"));

            cessk = nextrow.createCell(5);
            cessk.setCellValue((String) list.get("email"));

            cessk = nextrow.createCell(6);
            cessk.setCellValue((String) list.get("address"));

            cessk = nextrow.createCell(7);
            cessk.setCellValue((String) list.get("position"));

            cessk = nextrow.createCell(8);
            cessk.setCellValue((String) list.get("bank_card"));
//可自己再按照需要添加函数，注意将cessk = nextrow.createCell(num);这其中的num修改就行，按照顺序加上1就行
            j++;
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + path);
        FileOutputStream income = new FileOutputStream("E:/excel/" + path);
        try {
            workbook.write(income);
            income.flush();
            income.close();
            return "success";
        } catch (IOException ignored) {
        }
        return "fail";

    }

    @GetMapping("/toCoachExcel")
    public String toCoachExcel( HttpServletResponse response) throws FileNotFoundException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 创建一个文件
        String path = "keepGym教练信息表.xlsx";
        List<HashMap<String, Object>> lists = coachService.findAllCoachForPoi();
        String[] title = {"id", "姓名", "电话", "性别", "地址", "毕业院校",
                "类型", "个性签名", "入职时间","教龄","工资卡"};
        // 创建excel工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建一个工作表sheet
        XSSFSheet sheet = workbook.createSheet();
        // 创建第一行
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = null;

        // 插入第一行数据 id 地区名称
        for (int i = 0; i < title.length; i++) {
            // 创建一行的一格
            cell = row.createCell(i);
            // 赋值
            cell.setCellValue(title[i]);
        }
        // 追加数据行数
        int j = 1;
        HashMap<String, Object> list = null;
        for (int i = 0; i < lists.size(); i++) {
            // 从集合中得到一个对象
            list = lists.get(i);
            // 创建第2行

            XSSFRow nextrow = sheet.createRow(i + 1);
            // 创建第1列并赋值
            XSSFCell cessk = nextrow.createCell(0);
            cessk.setCellValue((String)(list.get("id")));

            cessk = nextrow.createCell(1);
            cessk.setCellValue((String) list.get("name"));

            cessk = nextrow.createCell(2);
            cessk.setCellValue((String) list.get("phone"));

            cessk = nextrow.createCell(3);
            cessk.setCellValue((String) list.get("gender"));

            cessk = nextrow.createCell(4);
            cessk.setCellValue((String) list.get("address"));

            cessk = nextrow.createCell(5);
            cessk.setCellValue((String) list.get("gradu_school"));

            cessk = nextrow.createCell(6);
            cessk.setCellValue((String) list.get("type"));

            cessk = nextrow.createCell(7);
            cessk.setCellValue((String) list.get("content"));

            cessk = nextrow.createCell(8);
            cessk.setCellValue(simpleDateFormat.format( list.get("enter_time")));

            cessk = nextrow.createCell(9);
            cessk.setCellValue((String) list.get("teach_time"));

            cessk = nextrow.createCell(10);
            cessk.setCellValue((String) list.get("bank_card"));
//可自己再按照需要添加函数，注意将cessk = nextrow.createCell(num);这其中的num修改就行，按照顺序加上1就行
            j++;
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + path);
        FileOutputStream income = new FileOutputStream("E:/excel/" + path);
        try {
            workbook.write(income);
            income.flush();
            income.close();
            return "success";
        } catch (IOException ignored) {
        }
        return "fail";

    }

    @GetMapping("/toFeedbackExcel")
    public String toFeedbackExcel( HttpServletResponse response) throws FileNotFoundException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 创建一个文件
        String path = "keepGym意见反馈表.xlsx";
        List<HashMap<String, Object>> lists = feedbackService.findAllFeedbackForPoi();
        String[] title = {"会员id", "会员姓名", "会员电话", "您对健身房整体环境及健身氛围是否满意？", "您对驻场教练的服务态度是否满意？",
                "您对健身房安排的团体操课丰富性是否满意？", "您对健身房器械环境有哪些意见和建议？", "您对健身房工作人员的服务态度是否满意？请输入令你满意或者不满的员工姓名（可以填写具体事例）" ,
                "反馈时间"};
        // 创建excel工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建一个工作表sheet
        XSSFSheet sheet = workbook.createSheet();
        // 创建第一行
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = null;

        // 插入第一行数据 id 地区名称
        for (int i = 0; i < title.length; i++) {
            // 创建一行的一格
            cell = row.createCell(i);
            // 赋值
            cell.setCellValue(title[i]);
        }
        // 追加数据行数
        int j = 1;
        HashMap<String, Object> list = null;
        for (int i = 0; i < lists.size(); i++) {
            // 从集合中得到一个对象
            list = lists.get(i);
            // 创建第2行

            XSSFRow nextrow = sheet.createRow(i + 1);
            // 创建第1列并赋值
            XSSFCell cessk = nextrow.createCell(0);
            cessk.setCellValue((String)(list.get("member_id")));

            cessk = nextrow.createCell(1);
            cessk.setCellValue((String) list.get("member_name"));

            cessk = nextrow.createCell(2);
            cessk.setCellValue((String) list.get("phone"));

            cessk = nextrow.createCell(3);
            cessk.setCellValue((String) list.get("resource1"));

            cessk = nextrow.createCell(4);
            cessk.setCellValue((String) list.get("resource2"));

            cessk = nextrow.createCell(5);
            cessk.setCellValue((String) list.get("resource3"));

            cessk = nextrow.createCell(6);
            cessk.setCellValue((String) list.get("resource4"));

            cessk = nextrow.createCell(7);
            cessk.setCellValue((String) list.get("resource5"));

            cessk = nextrow.createCell(8);
            cessk.setCellValue(simpleDateFormat.format( list.get("feedback_time")));

//可自己再按照需要添加函数，注意将cessk = nextrow.createCell(num);这其中的num修改就行，按照顺序加上1就行
            j++;
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + path);
        FileOutputStream income = new FileOutputStream("E:/excel/" + path);
        try {
            workbook.write(income);
            income.flush();
            income.close();
            return "success";
        } catch (IOException ignored) {
        }
        return "fail";

    }
}
