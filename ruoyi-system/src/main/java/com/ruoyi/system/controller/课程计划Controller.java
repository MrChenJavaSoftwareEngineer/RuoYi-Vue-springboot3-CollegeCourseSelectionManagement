package com.ruoyi.system.controller;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.课程计划;
import com.ruoyi.system.service.I课程计划Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 存储课程计划安排Controller
 * 
 * @author ruoyi
 * @date 2025-12-17
 */
@RestController
@RequestMapping("/system/课程计划")
public class 课程计划Controller extends BaseController
{
    @Autowired
    private I课程计划Service 课程计划Service;

    /**
     * 查询存储课程计划安排列表
     */
    @PreAuthorize("@ss.hasPermi('system:课程计划:list')")
    @GetMapping("/list")
    public TableDataInfo list(课程计划 课程计划)
    {
        startPage();
        List<课程计划> list = 课程计划Service.select课程计划List(课程计划);
        return getDataTable(list);
    }

    /**
     * 导出存储课程计划安排列表
     */
    @PreAuthorize("@ss.hasPermi('system:课程计划:export')")
    @Log(title = "存储课程计划安排", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, 课程计划 课程计划)
    {
        List<课程计划> list = 课程计划Service.select课程计划List(课程计划);
        ExcelUtil<课程计划> util = new ExcelUtil<课程计划>(课程计划.class);
        util.exportExcel(response, list, "存储课程计划安排数据");
    }

    /**
     * 获取存储课程计划安排详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:课程计划:query')")
    @GetMapping(value = "/{课程计划编号}")
    public AjaxResult getInfo(@PathVariable("课程计划编号") String 课程计划编号)
    {
        return success(课程计划Service.select课程计划By课程计划编号(课程计划编号));
    }

    /**
     * 新增存储课程计划安排
     */
    @PreAuthorize("@ss.hasPermi('system:课程计划:add')")
    @Log(title = "存储课程计划安排", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody 课程计划 课程计划)
    {
        return toAjax(课程计划Service.insert课程计划(课程计划));
    }

    /**
     * 修改存储课程计划安排
     */
    @PreAuthorize("@ss.hasPermi('system:课程计划:edit')")
    @Log(title = "存储课程计划安排", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody 课程计划 课程计划)
    {
        return toAjax(课程计划Service.update课程计划(课程计划));
    }

    /**
     * 删除存储课程计划安排
     */
    @PreAuthorize("@ss.hasPermi('system:课程计划:remove')")
    @Log(title = "存储课程计划安排", businessType = BusinessType.DELETE)
	@DeleteMapping("/{课程计划编号s}")
    public AjaxResult remove(@PathVariable String[] 课程计划编号s)
    {
        return toAjax(课程计划Service.delete课程计划By课程计划编号s(课程计划编号s));
    }
}
