package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 存储教室基本信息对象 教室
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
public class 教室 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 教室唯一编号 */
    private String 教室编号;

    /** 教室具体位置 */
    @Excel(name = "教室具体位置")
    private String 教室地址;

    /** 教室最大容纳人数 */
    @Excel(name = "教室最大容纳人数")
    private Long 教室容量;

    /** 软删除标记：0-未删除，1-已删除 */
    @Excel(name = "软删除标记：0-未删除，1-已删除")
    private Long 逻辑删除;

    public void set教室编号(String 教室编号) 
    {
        this.教室编号 = 教室编号;
    }

    public String get教室编号() 
    {
        return 教室编号;
    }

    public void set教室地址(String 教室地址) 
    {
        this.教室地址 = 教室地址;
    }

    public String get教室地址() 
    {
        return 教室地址;
    }

    public void set教室容量(Long 教室容量) 
    {
        this.教室容量 = 教室容量;
    }

    public Long get教室容量() 
    {
        return 教室容量;
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
            .append("教室编号", get教室编号())
            .append("教室地址", get教室地址())
            .append("教室容量", get教室容量())
            .append("逻辑删除", get逻辑删除())
            .toString();
    }
}
