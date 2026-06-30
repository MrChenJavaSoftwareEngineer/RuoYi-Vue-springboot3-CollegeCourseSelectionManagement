package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 存储课程计划安排对象 课程计划
 * 
 * @author ruoyi
 * @date 2025-12-17
 */
public class 课程计划 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程计划唯一编号 */
    private String 课程计划编号;

    /** 课程计划类型（如：必修、选修等） */
    @Excel(name = "课程计划类型", readConverterExp = "如=：必修、选修等")
    private String 类型;

    /** 当天课程开始节次 */
    @Excel(name = "当天课程开始节次")
    private Long 当天开始节次;

    /** 当天课程结束节次 */
    @Excel(name = "当天课程结束节次")
    private Long 当天结束节次;

    /** 周内上课时间（1-7表示周一到周日） */
    @Excel(name = "周内上课时间", readConverterExp = "1=-7表示周一到周日")
    private Long 周内时间;

    /** 课程计划结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "课程计划结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date 结课时间;

    /** 软删除标记：0-未删除，1-已删除 */
    @Excel(name = "软删除标记：0-未删除，1-已删除")
    private Long 逻辑删除;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String 课程名称;

    /** 教室具体地址 */
    @Excel(name = "教室具体地址")
    private String 教室地址;

    /** 授课老师 */
    @Excel(name = "授课老师")
    private String 授课老师;

    public void set课程计划编号(String 课程计划编号) 
    {
        this.课程计划编号 = 课程计划编号;
    }

    public String get课程计划编号() 
    {
        return 课程计划编号;
    }

    public void set类型(String 类型) 
    {
        this.类型 = 类型;
    }

    public String get类型() 
    {
        return 类型;
    }

    public void set当天开始节次(Long 当天开始节次) 
    {
        this.当天开始节次 = 当天开始节次;
    }

    public Long get当天开始节次() 
    {
        return 当天开始节次;
    }

    public void set当天结束节次(Long 当天结束节次) 
    {
        this.当天结束节次 = 当天结束节次;
    }

    public Long get当天结束节次() 
    {
        return 当天结束节次;
    }

    public void set周内时间(Long 周内时间) 
    {
        this.周内时间 = 周内时间;
    }

    public Long get周内时间() 
    {
        return 周内时间;
    }

    public void set结课时间(Date 结课时间) 
    {
        this.结课时间 = 结课时间;
    }

    public Date get结课时间() 
    {
        return 结课时间;
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

    public void set教室地址(String 教室地址) 
    {
        this.教室地址 = 教室地址;
    }

    public String get教室地址() 
    {
        return 教室地址;
    }

    public void set授课老师(String 授课老师) 
    {
        this.授课老师 = 授课老师;
    }

    public String get授课老师() 
    {
        return 授课老师;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("课程计划编号", get课程计划编号())
            .append("类型", get类型())
            .append("当天开始节次", get当天开始节次())
            .append("当天结束节次", get当天结束节次())
            .append("周内时间", get周内时间())
            .append("结课时间", get结课时间())
            .append("逻辑删除", get逻辑删除())
            .append("课程名称", get课程名称())
            .append("教室地址", get教室地址())
            .append("授课老师", get授课老师())
            .toString();
    }
}
