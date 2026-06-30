package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.教师;

/**
 * 存储教师基本信息Service接口
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
public interface I教师Service 
{
    /**
     * 查询存储教师基本信息
     * 
     * @param 教师工号 存储教师基本信息主键
     * @return 存储教师基本信息
     */
    public 教师 select教师By教师工号(String 教师工号);

    /**
     * 查询存储教师基本信息列表
     * 
     * @param 教师 存储教师基本信息
     * @return 存储教师基本信息集合
     */
    public List<教师> select教师List(教师 教师);

    /**
     * 新增存储教师基本信息
     * 
     * @param 教师 存储教师基本信息
     * @return 结果
     */
    public int insert教师(教师 教师);

    /**
     * 修改存储教师基本信息
     * 
     * @param 教师 存储教师基本信息
     * @return 结果
     */
    public int update教师(教师 教师);

    /**
     * 批量删除存储教师基本信息
     * 
     * @param 教师工号s 需要删除的存储教师基本信息主键集合
     * @return 结果
     */
    public int delete教师By教师工号s(String[] 教师工号s);

    /**
     * 删除存储教师基本信息信息
     * 
     * @param 教师工号 存储教师基本信息主键
     * @return 结果
     */
    public int delete教师By教师工号(String 教师工号);
}
