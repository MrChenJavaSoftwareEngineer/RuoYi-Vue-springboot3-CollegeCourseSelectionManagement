package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.课程计划Mapper;
import com.ruoyi.system.domain.课程计划;
import com.ruoyi.system.service.I课程计划Service;

/**
 * 存储课程计划安排Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-17
 */
@Service
public class 课程计划ServiceImpl implements I课程计划Service 
{
    @Autowired
    private 课程计划Mapper 课程计划Mapper;

    /**
     * 查询存储课程计划安排
     * 
     * @param 课程计划编号 存储课程计划安排主键
     * @return 存储课程计划安排
     */
    @Override
    public 课程计划 select课程计划By课程计划编号(String 课程计划编号)
    {
        return 课程计划Mapper.select课程计划By课程计划编号(课程计划编号);
    }

    /**
     * 查询存储课程计划安排列表
     * 
     * @param 课程计划 存储课程计划安排
     * @return 存储课程计划安排
     */
    @Override
    public List<课程计划> select课程计划List(课程计划 课程计划)
    {
        return 课程计划Mapper.select课程计划List(课程计划);
    }

    /**
     * 新增存储课程计划安排
     * 
     * @param 课程计划 存储课程计划安排
     * @return 结果
     */
    @Override
    public int insert课程计划(课程计划 课程计划)
    {
        return 课程计划Mapper.insert课程计划(课程计划);
    }

    /**
     * 修改存储课程计划安排
     * 
     * @param 课程计划 存储课程计划安排
     * @return 结果
     */
    @Override
    public int update课程计划(课程计划 课程计划)
    {
        return 课程计划Mapper.update课程计划(课程计划);
    }

    /**
     * 批量删除存储课程计划安排
     * 
     * @param 课程计划编号s 需要删除的存储课程计划安排主键
     * @return 结果
     */
    @Override
    public int delete课程计划By课程计划编号s(String[] 课程计划编号s)
    {
        return 课程计划Mapper.delete课程计划By课程计划编号s(课程计划编号s);
    }

    /**
     * 删除存储课程计划安排信息
     * 
     * @param 课程计划编号 存储课程计划安排主键
     * @return 结果
     */
    @Override
    public int delete课程计划By课程计划编号(String 课程计划编号)
    {
        return 课程计划Mapper.delete课程计划By课程计划编号(课程计划编号);
    }
}
