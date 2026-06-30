package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 存储教师基本信息对象 教师
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
public class 教师 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 教师唯一工号 */
    private String 教师工号;

    /** 教师姓名 */
    @Excel(name = "教师姓名")
    private String 姓名;

    /** 教师职称（教授、副教授等） */
    @Excel(name = "教师职称", readConverterExp = "教=授、副教授等")
    private String 职称;

    /** 教师所在部门 */
    @Excel(name = "教师所在部门")
    private String 部门;

    /** 软删除标记：0-未删除，1-已删除 */
    @Excel(name = "软删除标记：0-未删除，1-已删除")
    private Long 逻辑删除;

    public void set教师工号(String 教师工号) 
    {
        this.教师工号 = 教师工号;
    }

    public String get教师工号() 
    {
        return 教师工号;
    }

    public void set姓名(String 姓名) 
    {
        this.姓名 = 姓名;
    }

    public String get姓名() 
    {
        return 姓名;
    }

    public void set职称(String 职称) 
    {
        this.职称 = 职称;
    }

    public String get职称() 
    {
        return 职称;
    }

    public void set部门(String 部门) 
    {
        this.部门 = 部门;
    }

    public String get部门() 
    {
        return 部门;
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
            .append("教师工号", get教师工号())
            .append("姓名", get姓名())
            .append("职称", get职称())
            .append("部门", get部门())
            .append("逻辑删除", get逻辑删除())
            .toString();
    }
}
