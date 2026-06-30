package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.学生;

/**
 * 存储学生基本信息Service接口
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
public interface I学生Service 
{
    /**
     * 查询存储学生基本信息
     * 
     * @param 学号 存储学生基本信息主键
     * @return 存储学生基本信息
     */
    public 学生 select学生By学号(String 学号);

    /**
     * 查询存储学生基本信息列表
     * 
     * @param 学生 存储学生基本信息
     * @return 存储学生基本信息集合
     */
    public List<学生> select学生List(学生 学生);

    /**
     * 新增存储学生基本信息
     * 
     * @param 学生 存储学生基本信息
     * @return 结果
     */
    public int insert学生(学生 学生);

    /**
     * 修改存储学生基本信息
     * 
     * @param 学生 存储学生基本信息
     * @return 结果
     */
    public int update学生(学生 学生);

    /**
     * 批量删除存储学生基本信息
     * 
     * @param 学号s 需要删除的存储学生基本信息主键集合
     * @return 结果
     */
    public int delete学生By学号s(String[] 学号s);

    /**
     * 删除存储学生基本信息信息
     * 
     * @param 学号 存储学生基本信息主键
     * @return 结果
     */
    public int delete学生By学号(String 学号);
}
