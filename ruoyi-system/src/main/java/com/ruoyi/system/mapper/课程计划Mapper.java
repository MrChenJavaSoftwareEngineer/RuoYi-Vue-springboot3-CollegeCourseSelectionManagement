package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.课程计划;

/**
 * 存储课程计划安排Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-17
 */
public interface 课程计划Mapper 
{
    /**
     * 查询存储课程计划安排
     * 
     * @param 课程计划编号 存储课程计划安排主键
     * @return 存储课程计划安排
     */
    public 课程计划 select课程计划By课程计划编号(String 课程计划编号);

    /**
     * 查询存储课程计划安排列表
     * 
     * @param 课程计划 存储课程计划安排
     * @return 存储课程计划安排集合
     */
    public List<课程计划> select课程计划List(课程计划 课程计划);

    /**
     * 新增存储课程计划安排
     * 
     * @param 课程计划 存储课程计划安排
     * @return 结果
     */
    public int insert课程计划(课程计划 课程计划);

    /**
     * 修改存储课程计划安排
     * 
     * @param 课程计划 存储课程计划安排
     * @return 结果
     */
    public int update课程计划(课程计划 课程计划);

    /**
     * 删除存储课程计划安排
     * 
     * @param 课程计划编号 存储课程计划安排主键
     * @return 结果
     */
    public int delete课程计划By课程计划编号(String 课程计划编号);

    /**
     * 批量删除存储课程计划安排
     * 
     * @param 课程计划编号s 需要删除的数据主键集合
     * @return 结果
     */
    public int delete课程计划By课程计划编号s(String[] 课程计划编号s);
}
