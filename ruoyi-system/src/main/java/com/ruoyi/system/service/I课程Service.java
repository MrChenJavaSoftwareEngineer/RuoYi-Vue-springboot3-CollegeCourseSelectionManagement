package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.课程;

/**
 * 存储课程基本信息Service接口
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
public interface I课程Service 
{
    /**
     * 查询存储课程基本信息
     * 
     * @param 课程编号 存储课程基本信息主键
     * @return 存储课程基本信息
     */
    public 课程 select课程By课程编号(String 课程编号);

    /**
     * 查询存储课程基本信息列表
     * 
     * @param 课程 存储课程基本信息
     * @return 存储课程基本信息集合
     */
    public List<课程> select课程List(课程 课程);

    /**
     * 新增存储课程基本信息
     * 
     * @param 课程 存储课程基本信息
     * @return 结果
     */
    public int insert课程(课程 课程);

    /**
     * 修改存储课程基本信息
     * 
     * @param 课程 存储课程基本信息
     * @return 结果
     */
    public int update课程(课程 课程);

    /**
     * 批量删除存储课程基本信息
     * 
     * @param 课程编号s 需要删除的存储课程基本信息主键集合
     * @return 结果
     */
    public int delete课程By课程编号s(String[] 课程编号s);

    /**
     * 删除存储课程基本信息信息
     * 
     * @param 课程编号 存储课程基本信息主键
     * @return 结果
     */
    public int delete课程By课程编号(String 课程编号);
}
