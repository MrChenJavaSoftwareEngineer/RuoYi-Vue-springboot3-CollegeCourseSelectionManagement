package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.学生Mapper;
import com.ruoyi.system.domain.学生;
import com.ruoyi.system.service.I学生Service;

/**
 * 存储学生基本信息Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-16
 */
@Service
public class 学生ServiceImpl implements I学生Service
{
    @Autowired
    private 学生Mapper 学生Mapper;

    /**
     * 查询存储学生基本信息
     *
     * @param 学号 存储学生基本信息主键
     * @return 存储学生基本信息
     */
    @Override
    public 学生 select学生By学号(String 学号)
    {
        return 学生Mapper.select学生By学号(学号);
    }

    /**
     * 查询存储学生基本信息列表
     *
     * @param 学生 存储学生基本信息
     * @return 存储学生基本信息
     */
    @Override
    public List<学生> select学生List(学生 学生)
    {
        return 学生Mapper.select学生List(学生);
    }

    /**
     * 新增存储学生基本信息
     *
     * @param 学生 存储学生基本信息
     * @return 结果
     */
    @Override
    public int insert学生(学生 学生)
    {
        学生.set学号("20250" + (int)(Math.random()*100));
        return 学生Mapper.insert学生(学生);
    }

    /**
     * 修改存储学生基本信息
     *
     * @param 学生 存储学生基本信息
     * @return 结果
     */
    @Override
    public int update学生(学生 学生)
    {
        return 学生Mapper.update学生(学生);
    }

    /**
     * 批量删除存储学生基本信息
     *
     * @param 学号s 需要删除的存储学生基本信息主键
     * @return 结果
     */
    @Override
    public int delete学生By学号s(String[] 学号s)
    {
        return 学生Mapper.delete学生By学号s(学号s);
    }

    /**
     * 删除存储学生基本信息信息
     *
     * @param 学号 存储学生基本信息主键
     * @return 结果
     */
    @Override
    public int delete学生By学号(String 学号)
    {
        return 学生Mapper.delete学生By学号(学号);
    }
}
