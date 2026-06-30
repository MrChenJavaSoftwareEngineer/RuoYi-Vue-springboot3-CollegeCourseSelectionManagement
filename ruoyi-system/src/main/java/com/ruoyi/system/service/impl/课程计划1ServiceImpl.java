package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.constant.Common;
import com.ruoyi.system.domain.选课记录;
import com.ruoyi.system.mapper.学生Mapper;
import com.ruoyi.system.mapper.选课记录Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.课程计划1Mapper;
import com.ruoyi.system.domain.课程计划1;
import com.ruoyi.system.service.I课程计划1Service;

/**
 * 存储课程计划安排Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-17
 */
@Service
public class 课程计划1ServiceImpl implements I课程计划1Service
{
    @Autowired
    private 课程计划1Mapper 课程计划1Mapper;
    @Autowired
    private 选课记录Mapper 选课记录Mapper;
    /**
     * 查询存储课程计划安排
     *
     * @param 课程计划编号 存储课程计划安排主键
     * @return 存储课程计划安排
     */
    @Override
    public 课程计划1 select课程计划1By课程计划编号(String 课程计划编号)
    {
        return 课程计划1Mapper.select课程计划1By课程计划编号(课程计划编号);
    }

    /**
     * 查询存储课程计划安排列表
     *
     * @param 课程计划1 存储课程计划安排
     * @return 存储课程计划安排
     */
    @Override
    public List<课程计划1> select课程计划1List(课程计划1 课程计划1)
    {
        return 课程计划1Mapper.select课程计划1List(课程计划1);
    }

    /**
     * 新增存储课程计划安排
     *
     * @param 课程计划1 存储课程计划安排
     * @return 结果
     */
    @Override
    public int insert课程计划1(课程计划1 课程计划1)
    {
        return 课程计划1Mapper.insert课程计划1(课程计划1);
    }

    /**
     * 修改存储课程计划安排
     *
     * @param 课程计划1 存储课程计划安排
     * @return 结果
     */
    @Override
    public int update课程计划1(课程计划1 课程计划1)
    {
        return 课程计划1Mapper.update课程计划1(课程计划1);
    }

    /**
     * 批量删除存储课程计划安排
     *
     * @param 课程计划编号s 需要删除的存储课程计划安排主键
     * @return 结果
     */
    @Override
    public int delete课程计划1By课程计划编号s(String[] 课程计划编号s)
    {
        String studentId = Common.USER_ID;
        String usr = 课程计划编号s[0];
        课程计划1 课程计划1 = 课程计划1Mapper.select课程计划1By课程计划编号(usr);
        for (选课记录 选课记录 : 选课记录Mapper.selectByStudentId(studentId)) {
            if (选课记录.get课程名称().equals(课程计划1.get课程名称())) {
                return 0;
            }
        }
        选课记录 选课记录 = new 选课记录();
        选课记录.set学号(studentId);
        选课记录.set课程名称(课程计划1.get课程名称());
        选课记录.set选课时间(new Date());
        return 选课记录Mapper.insert选课记录(选课记录);
    }

    /**
     * 删除存储课程计划安排信息
     *
     * @param 课程计划编号 存储课程计划安排主键
     * @return 结果
     */
    @Override
    public int delete课程计划1By课程计划编号(String 课程计划编号)
    {
        return 课程计划1Mapper.delete课程计划1By课程计划编号(课程计划编号);
    }
}
