package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 存储学生基本信息对象 学生
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
public class 学生 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生唯一学号 */
    private String 学号;

    /** 学生所在年级 */
    @Excel(name = "学生所在年级")
    private Long 年级;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String 姓名;

    /** 用户类型标识 */
    @Excel(name = "用户类型标识")
    private String 用户类型;

    /** 学生所学专业 */
    @Excel(name = "学生所学专业")
    private String 专业;

    /** 软删除标记：0-未删除，1-已删除 */
    @Excel(name = "软删除标记：0-未删除，1-已删除")
    private Long 逻辑删除;

    public void set学号(String 学号) 
    {
        this.学号 = 学号;
    }

    public String get学号() 
    {
        return 学号;
    }

    public void set年级(Long 年级) 
    {
        this.年级 = 年级;
    }

    public Long get年级() 
    {
        return 年级;
    }

    public void set姓名(String 姓名) 
    {
        this.姓名 = 姓名;
    }

    public String get姓名() 
    {
        return 姓名;
    }

    public void set用户类型(String 用户类型) 
    {
        this.用户类型 = 用户类型;
    }

    public String get用户类型() 
    {
        return 用户类型;
    }

    public void set专业(String 专业) 
    {
        this.专业 = 专业;
    }

    public String get专业() 
    {
        return 专业;
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
            .append("学号", get学号())
            .append("年级", get年级())
            .append("姓名", get姓名())
            .append("用户类型", get用户类型())
            .append("专业", get专业())
            .append("逻辑删除", get逻辑删除())
            .toString();
    }
}
