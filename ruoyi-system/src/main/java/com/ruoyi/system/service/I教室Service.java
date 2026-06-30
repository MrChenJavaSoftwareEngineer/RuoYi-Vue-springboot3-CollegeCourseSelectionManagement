package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.教室;

/**
 * 存储教室基本信息Service接口
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
public interface I教室Service 
{
    /**
     * 查询存储教室基本信息
     * 
     * @param 教室编号 存储教室基本信息主键
     * @return 存储教室基本信息
     */
    public 教室 select教室By教室编号(String 教室编号);

    /**
     * 查询存储教室基本信息列表
     * 
     * @param 教室 存储教室基本信息
     * @return 存储教室基本信息集合
     */
    public List<教室> select教室List(教室 教室);

    /**
     * 新增存储教室基本信息
     * 
     * @param 教室 存储教室基本信息
     * @return 结果
     */
    public int insert教室(教室 教室);

    /**
     * 修改存储教室基本信息
     * 
     * @param 教室 存储教室基本信息
     * @return 结果
     */
    public int update教室(教室 教室);

    /**
     * 批量删除存储教室基本信息
     * 
     * @param 教室编号s 需要删除的存储教室基本信息主键集合
     * @return 结果
     */
    public int delete教室By教室编号s(String[] 教室编号s);

    /**
     * 删除存储教室基本信息信息
     * 
     * @param 教室编号 存储教室基本信息主键
     * @return 结果
     */
    public int delete教室By教室编号(String 教室编号);
}
