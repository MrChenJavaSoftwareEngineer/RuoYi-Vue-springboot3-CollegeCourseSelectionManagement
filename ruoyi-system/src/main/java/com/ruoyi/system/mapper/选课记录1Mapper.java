package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.选课记录1;

/**
 * 存储学生选课记录Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
public interface 选课记录1Mapper 
{
    /**
     * 查询存储学生选课记录
     * 
     * @param 选课记录ID 存储学生选课记录主键
     * @return 存储学生选课记录
     */
    public 选课记录1 select选课记录1By选课记录ID(Long 选课记录ID);

    /**
     * 查询存储学生选课记录列表
     * 
     * @param 选课记录1 存储学生选课记录
     * @return 存储学生选课记录集合
     */
    public List<选课记录1> select选课记录1List(选课记录1 选课记录1);

    /**
     * 新增存储学生选课记录
     * 
     * @param 选课记录1 存储学生选课记录
     * @return 结果
     */
    public int insert选课记录1(选课记录1 选课记录1);

    /**
     * 修改存储学生选课记录
     * 
     * @param 选课记录1 存储学生选课记录
     * @return 结果
     */
    public int update选课记录1(选课记录1 选课记录1);

    /**
     * 删除存储学生选课记录
     * 
     * @param 选课记录ID 存储学生选课记录主键
     * @return 结果
     */
    public int delete选课记录1By选课记录ID(Long 选课记录ID);

    /**
     * 批量删除存储学生选课记录
     * 
     * @param 选课记录IDs 需要删除的数据主键集合
     * @return 结果
     */
    public int delete选课记录1By选课记录IDs(Long[] 选课记录IDs);
}
