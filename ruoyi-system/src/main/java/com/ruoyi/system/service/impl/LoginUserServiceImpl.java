package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.学生;
import com.ruoyi.system.domain.课程;
import com.ruoyi.system.domain.课程计划;
import com.ruoyi.system.domain.选课记录;
import com.ruoyi.system.mapper.学生Mapper;
import com.ruoyi.system.mapper.课程计划Mapper;
import com.ruoyi.system.mapper.选课记录Mapper;
import com.ruoyi.system.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: LoginUserServiceImpl
 * Package: com.ruoyi.system.service.impl
 * Description:
 *
 * @Author 陈泽
 * @Create 2025/12/16 下午2:50
 * @Version 1.0
 */
@Service
public class LoginUserServiceImpl implements LoginUserService {
    @Autowired
    private 课程计划ServiceImpl 课程计划Service;
    @Autowired
    private 选课记录Mapper 选课记录Mapper;
    @Autowired
    private 学生Mapper 学生Mapper;
    @Override
    public String login(String username, String studentId) {
        学生 学生 = 学生Mapper.select学生By学号(studentId);
        if (!学生.get姓名().equals( username)) {
            return "用户名错误";
        }
        return 学生.get学号();
    }

    @Override
    public List<课程计划> getAllCoursePlan(String studentId) {
        List<选课记录> 选课记录s = 选课记录Mapper.selectByStudentId(studentId);
        List<课程计划> list = new ArrayList<>();
        for (课程计划 课程计划 : 课程计划Service.select课程计划List(new 课程计划())) {
            if (选课记录s.stream().anyMatch(选课记录 -> !选课记录.get课程名称().equals(课程计划.get课程名称()))) {
                list.add(课程计划);
            }
        }
        return  list;
    }

    @Override
    public List<课程计划> getCoursePlan(String studentId) {
        List<选课记录> 选课记录s = 选课记录Mapper.selectByStudentId(studentId);
        List<课程计划> list = new ArrayList<>();
        for (课程计划 课程计划 : 课程计划Service.select课程计划List(new 课程计划())) {
            if (选课记录s.stream().anyMatch(选课记录 -> 选课记录.get课程名称().equals(课程计划.get课程名称()))) {
                list.add(课程计划);
            }
        }
        return list;
    }

    @Override
    public String selectCourse(String studentId, String 课程计划编号) {
        课程计划 课程计划 = 课程计划Service.select课程计划By课程计划编号(课程计划编号);
        if (课程计划 == null) {
            return "课程不存在";
        }
        选课记录 选课记录 = new 选课记录();
        选课记录.set学号(studentId);
        选课记录.set课程名称(课程计划.get课程名称());
        List<com.ruoyi.system.domain.选课记录> 选课记录s = 选课记录Mapper.select选课记录List(选课记录);
        if (选课记录s.size() > 0){
            return "已选";
        }
        选课记录.set选课时间(new java.util.Date());
        int i = 选课记录Mapper.insert选课记录(选课记录);
        if (i <= 0) {
            return "选课失败";
        }
        return "选课成功";
    }
}
