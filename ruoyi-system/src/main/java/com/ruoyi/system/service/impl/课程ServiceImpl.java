package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.课程Mapper;
import com.ruoyi.system.domain.课程;
import com.ruoyi.system.service.I课程Service;

/**
 * 存储课程基本信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
@Service
public class 课程ServiceImpl implements I课程Service 
{
    @Autowired
    private 课程Mapper 课程Mapper;

    /**
     * 查询存储课程基本信息
     * 
     * @param 课程编号 存储课程基本信息主键
     * @return 存储课程基本信息
     */
    @Override
    public 课程 select课程By课程编号(String 课程编号)
    {
        return 课程Mapper.select课程By课程编号(课程编号);
    }

    /**
     * 查询存储课程基本信息列表
     * 
     * @param 课程 存储课程基本信息
     * @return 存储课程基本信息
     */
    @Override
    public List<课程> select课程List(课程 课程)
    {
        return 课程Mapper.select课程List(课程);
    }

    /**
     * 新增存储课程基本信息
     * 
     * @param 课程 存储课程基本信息
     * @return 结果
     */
    @Override
    public int insert课程(课程 课程)
    {
        return 课程Mapper.insert课程(课程);
    }

    /**
     * 修改存储课程基本信息
     * 
     * @param 课程 存储课程基本信息
     * @return 结果
     */
    @Override
    public int update课程(课程 课程)
    {
        return 课程Mapper.update课程(课程);
    }

    /**
     * 批量删除存储课程基本信息
     * 
     * @param 课程编号s 需要删除的存储课程基本信息主键
     * @return 结果
     */
    @Override
    public int delete课程By课程编号s(String[] 课程编号s)
    {
        return 课程Mapper.delete课程By课程编号s(课程编号s);
    }

    /**
     * 删除存储课程基本信息信息
     * 
     * @param 课程编号 存储课程基本信息主键
     * @return 结果
     */
    @Override
    public int delete课程By课程编号(String 课程编号)
    {
        return 课程Mapper.delete课程By课程编号(课程编号);
    }
}
