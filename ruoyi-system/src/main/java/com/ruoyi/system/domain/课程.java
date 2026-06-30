package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 存储课程基本信息对象 课程
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
public class 课程 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程唯一标识符 */
    private String 课程编号;

    /** 课程全称 */
    @Excel(name = "课程全称")
    private String 课程名称;

    /** 课程详细描述和内容介绍 */
    @Excel(name = "课程详细描述和内容介绍")
    private String 课程简介;

    /** 课程可容纳的最大学生数 */
    @Excel(name = "课程可容纳的最大学生数")
    private Long 课程容量;

    /** 课程学分值 */
    @Excel(name = "课程学分值")
    private BigDecimal 学分;

    /** 必须先修的课程编号，可为空 */
    @Excel(name = "必须先修的课程编号，可为空")
    private String 先修课程编号;

    /** 课程所属的专业领域 */
    @Excel(name = "课程所属的专业领域")
    private String 所属专业;

    /** 软删除标记：0-未删除，1-已删除 */
    @Excel(name = "软删除标记：0-未删除，1-已删除")
    private Long 逻辑删除;

    public void set课程编号(String 课程编号) 
    {
        this.课程编号 = 课程编号;
    }

    public String get课程编号() 
    {
        return 课程编号;
    }

    public void set课程名称(String 课程名称) 
    {
        this.课程名称 = 课程名称;
    }

    public String get课程名称() 
    {
        return 课程名称;
    }

    public void set课程简介(String 课程简介) 
    {
        this.课程简介 = 课程简介;
    }

    public String get课程简介() 
    {
        return 课程简介;
    }

    public void set课程容量(Long 课程容量) 
    {
        this.课程容量 = 课程容量;
    }

    public Long get课程容量() 
    {
        return 课程容量;
    }

    public void set学分(BigDecimal 学分) 
    {
        this.学分 = 学分;
    }

    public BigDecimal get学分() 
    {
        return 学分;
    }

    public void set先修课程编号(String 先修课程编号) 
    {
        this.先修课程编号 = 先修课程编号;
    }

    public String get先修课程编号() 
    {
        return 先修课程编号;
    }

    public void set所属专业(String 所属专业) 
    {
        this.所属专业 = 所属专业;
    }

    public String get所属专业() 
    {
        return 所属专业;
    }

    public void set逻辑删除(Long 逻辑删除) 
    {
        this.逻辑删除 = 逻辑删除;
    }

    public Long get逻辑删除() 
    {
        return 逻辑删除;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("课程编号", get课程编号())
            .append("课程名称", get课程名称())
            .append("课程简介", get课程简介())
            .append("课程容量", get课程容量())
            .append("学分", get学分())
            .append("先修课程编号", get先修课程编号())
            .append("所属专业", get所属专业())
            .append("逻辑删除", get逻辑删除())
            .toString();
    }
}
