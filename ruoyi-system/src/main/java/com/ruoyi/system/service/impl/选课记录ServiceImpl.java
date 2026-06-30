package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.选课记录Mapper;
import com.ruoyi.system.domain.选课记录;
import com.ruoyi.system.service.I选课记录Service;

/**
 * 存储学生选课记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
@Service
public class 选课记录ServiceImpl implements I选课记录Service 
{
    @Autowired
    private 选课记录Mapper 选课记录Mapper;

    /**
     * 查询存储学生选课记录
     * 
     * @param 选课记录ID 存储学生选课记录主键
     * @return 存储学生选课记录
     */
    @Override
    public 选课记录 select选课记录By选课记录ID(Long 选课记录ID)
    {
        return 选课记录Mapper.select选课记录By选课记录ID(选课记录ID);
    }

    /**
     * 查询存储学生选课记录列表
     * 
     * @param 选课记录 存储学生选课记录
     * @return 存储学生选课记录
     */
    @Override
    public List<选课记录> select选课记录List(选课记录 选课记录)
    {
        return 选课记录Mapper.select选课记录List(选课记录);
    }

    /**
     * 新增存储学生选课记录
     * 
     * @param 选课记录 存储学生选课记录
     * @return 结果
     */
    @Override
    public int insert选课记录(选课记录 选课记录)
    {
        return 选课记录Mapper.insert选课记录(选课记录);
    }

    /**
     * 修改存储学生选课记录
     * 
     * @param 选课记录 存储学生选课记录
     * @return 结果
     */
    @Override
    public int update选课记录(选课记录 选课记录)
    {
        return 选课记录Mapper.update选课记录(选课记录);
    }

    /**
     * 批量删除存储学生选课记录
     * 
     * @param 选课记录IDs 需要删除的存储学生选课记录主键
     * @return 结果
     */
    @Override
    public int delete选课记录By选课记录IDs(Long[] 选课记录IDs)
    {
        return 选课记录Mapper.delete选课记录By选课记录IDs(选课记录IDs);
    }

    /**
     * 删除存储学生选课记录信息
     * 
     * @param 选课记录ID 存储学生选课记录主键
     * @return 结果
     */
    @Override
    public int delete选课记录By选课记录ID(Long 选课记录ID)
    {
        return 选课记录Mapper.delete选课记录By选课记录ID(选课记录ID);
    }
}
