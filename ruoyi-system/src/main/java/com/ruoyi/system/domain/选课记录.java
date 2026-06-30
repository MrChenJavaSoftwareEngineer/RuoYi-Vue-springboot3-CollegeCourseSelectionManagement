package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 存储学生选课记录对象 选课记录
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
public class 选课记录 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 选课记录唯一标识 */
    private Long 选课记录ID;

    /** 学生学号，关联学生表 */
    @Excel(name = "学生学号，关联学生表")
    private String 学号;

    /** 学生课程成绩，可为空 */
    @Excel(name = "学生课程成绩，可为空")
    private BigDecimal 成绩;

    /** 选课操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "选课操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date 选课时间;

    /** 软删除标记：0-未删除，1-已删除 */
    @Excel(name = "软删除标记：0-未删除，1-已删除")
    private Long 逻辑删除;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String 课程名称;

    public void set选课记录ID(Long 选课记录ID) 
    {
        this.选课记录ID = 选课记录ID;
    }

    public Long get选课记录ID() 
    {
        return 选课记录ID;
    }

    public void set学号(String 学号) 
    {
        this.学号 = 学号;
    }

    public String get学号() 
    {
        return 学号;
    }

    public void set成绩(BigDecimal 成绩) 
    {
        this.成绩 = 成绩;
    }

    public BigDecimal get成绩() 
    {
        return 成绩;
    }

    public void set选课时间(Date 选课时间) 
    {
        this.选课时间 = 选课时间;
    }

    public Date get选课时间() 
    {
        return 选课时间;
    }

    public void set逻辑删除(Long 逻辑删除) 
    {
        this.逻辑删除 = 逻辑删除;
    }

    public Long get逻辑删除() 
    {
        return 逻辑删除;
    }

    public void set课程名称(String 课程名称) 
    {
        this.课程名称 = 课程名称;
    }

    public String get课程名称() 
    {
        return 课程名称;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("选课记录ID", get选课记录ID())
            .append("学号", get学号())
            .append("成绩", get成绩())
            .append("选课时间", get选课时间())
            .append("逻辑删除", get逻辑删除())
            .append("课程名称", get课程名称())
            .toString();
    }
}
