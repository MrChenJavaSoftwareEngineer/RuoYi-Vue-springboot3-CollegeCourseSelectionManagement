package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.教师Mapper;
import com.ruoyi.system.domain.教师;
import com.ruoyi.system.service.I教师Service;

/**
 * 存储教师基本信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
@Service
public class 教师ServiceImpl implements I教师Service 
{
    @Autowired
    private 教师Mapper 教师Mapper;

    /**
     * 查询存储教师基本信息
     * 
     * @param 教师工号 存储教师基本信息主键
     * @return 存储教师基本信息
     */
    @Override
    public 教师 select教师By教师工号(String 教师工号)
    {
        return 教师Mapper.select教师By教师工号(教师工号);
    }

    /**
     * 查询存储教师基本信息列表
     * 
     * @param 教师 存储教师基本信息
     * @return 存储教师基本信息
     */
    @Override
    public List<教师> select教师List(教师 教师)
    {
        return 教师Mapper.select教师List(教师);
    }

    /**
     * 新增存储教师基本信息
     * 
     * @param 教师 存储教师基本信息
     * @return 结果
     */
    @Override
    public int insert教师(教师 教师)
    {
        return 教师Mapper.insert教师(教师);
    }

    /**
     * 修改存储教师基本信息
     * 
     * @param 教师 存储教师基本信息
     * @return 结果
     */
    @Override
    public int update教师(教师 教师)
    {
        return 教师Mapper.update教师(教师);
    }

    /**
     * 批量删除存储教师基本信息
     * 
     * @param 教师工号s 需要删除的存储教师基本信息主键
     * @return 结果
     */
    @Override
    public int delete教师By教师工号s(String[] 教师工号s)
    {
        return 教师Mapper.delete教师By教师工号s(教师工号s);
    }

    /**
     * 删除存储教师基本信息信息
     * 
     * @param 教师工号 存储教师基本信息主键
     * @return 结果
     */
    @Override
    public int delete教师By教师工号(String 教师工号)
    {
        return 教师Mapper.delete教师By教师工号(教师工号);
    }
}
