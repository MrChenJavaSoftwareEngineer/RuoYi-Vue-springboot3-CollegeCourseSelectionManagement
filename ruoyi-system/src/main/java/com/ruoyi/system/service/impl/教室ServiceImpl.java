package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.教室Mapper;
import com.ruoyi.system.domain.教室;
import com.ruoyi.system.service.I教室Service;

/**
 * 存储教室基本信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
@Service
public class 教室ServiceImpl implements I教室Service 
{
    @Autowired
    private 教室Mapper 教室Mapper;

    /**
     * 查询存储教室基本信息
     * 
     * @param 教室编号 存储教室基本信息主键
     * @return 存储教室基本信息
     */
    @Override
    public 教室 select教室By教室编号(String 教室编号)
    {
        return 教室Mapper.select教室By教室编号(教室编号);
    }

    /**
     * 查询存储教室基本信息列表
     * 
     * @param 教室 存储教室基本信息
     * @return 存储教室基本信息
     */
    @Override
    public List<教室> select教室List(教室 教室)
    {
        return 教室Mapper.select教室List(教室);
    }

    /**
     * 新增存储教室基本信息
     * 
     * @param 教室 存储教室基本信息
     * @return 结果
     */
    @Override
    public int insert教室(教室 教室)
    {
        return 教室Mapper.insert教室(教室);
    }

    /**
     * 修改存储教室基本信息
     * 
     * @param 教室 存储教室基本信息
     * @return 结果
     */
    @Override
    public int update教室(教室 教室)
    {
        return 教室Mapper.update教室(教室);
    }

    /**
     * 批量删除存储教室基本信息
     * 
     * @param 教室编号s 需要删除的存储教室基本信息主键
     * @return 结果
     */
    @Override
    public int delete教室By教室编号s(String[] 教室编号s)
    {
        return 教室Mapper.delete教室By教室编号s(教室编号s);
    }

    /**
     * 删除存储教室基本信息信息
     * 
     * @param 教室编号 存储教室基本信息主键
     * @return 结果
     */
    @Override
    public int delete教室By教室编号(String 教室编号)
    {
        return 教室Mapper.delete教室By教室编号(教室编号);
    }
}
