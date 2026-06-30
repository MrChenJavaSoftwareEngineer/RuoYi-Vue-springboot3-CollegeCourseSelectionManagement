package com.ruoyi.system.service;

import com.ruoyi.system.domain.课程计划;
import com.ruoyi.system.domain.选课记录;

import java.util.List;

/**
 * ClassName: LoginUserService
 * Package: com.ruoyi.system.service
 * Description:
 *
 * @Author 陈泽
 * @Create 2025/12/16 下午2:46
 * @Version 1.0
 */
public interface LoginUserService {
    String login(String username, String password);
    List<课程计划> getAllCoursePlan(String studentId);
    List<课程计划> getCoursePlan(String studentId);
    String selectCourse(String studentId, String 课程计划编号);
}
