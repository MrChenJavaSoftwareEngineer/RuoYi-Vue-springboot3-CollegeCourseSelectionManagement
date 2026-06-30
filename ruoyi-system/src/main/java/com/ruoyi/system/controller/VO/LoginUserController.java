package com.ruoyi.system.controller.VO;

import com.ruoyi.system.domain.课程计划;
import com.ruoyi.system.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: LoginUserController
 * Package: com.ruoyi.system.controller.VO
 * Description:
 *
 * @Author 陈泽
 * @Create 2025/12/16 下午2:45
 * @Version 1.0
 */
@RestController
@RequestMapping("/loginUser")
@CrossOrigin
public class LoginUserController {
    @Autowired
    private LoginUserService loginUserService;
    @GetMapping("/login1")
    public String login(String username, String password) {
        return loginUserService.login(username, password);
    }
    @GetMapping("/getAllCoursePlan")
    public List<课程计划> getAllCoursePlan(String studentId) {
        return loginUserService.getAllCoursePlan(studentId);
    }
    @GetMapping("/getCoursePlan")
    public List<课程计划> getCoursePlan(String studentId) {
        return loginUserService.getCoursePlan(studentId);
    }
    @GetMapping("/selectCourse")
    public String selectCourse(String studentId, String 课程计划编号) {
        return loginUserService.selectCourse(studentId, 课程计划编号);
    }
}
