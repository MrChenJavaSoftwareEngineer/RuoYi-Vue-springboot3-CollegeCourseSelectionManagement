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
import com.ruoyi.system.domain.选课记录;
import com.ruoyi.system.service.I选课记录Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 存储学生选课记录Controller
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
@RestController
@RequestMapping("/system/选课记录")
public class 选课记录Controller extends BaseController
{
    @Autowired
    private I选课记录Service 选课记录Service;

    /**
     * 查询存储学生选课记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:选课记录:list')")
    @GetMapping("/list")
    public TableDataInfo list(选课记录 选课记录)
    {
        startPage();
        List<选课记录> list = 选课记录Service.select选课记录List(选课记录);
        return getDataTable(list);
    }

    /**
     * 导出存储学生选课记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:选课记录:export')")
    @Log(title = "存储学生选课记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, 选课记录 选课记录)
    {
        List<选课记录> list = 选课记录Service.select选课记录List(选课记录);
        ExcelUtil<选课记录> util = new ExcelUtil<选课记录>(选课记录.class);
        util.exportExcel(response, list, "存储学生选课记录数据");
    }

    /**
     * 获取存储学生选课记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:选课记录:query')")
    @GetMapping(value = "/{选课记录ID}")
    public AjaxResult getInfo(@PathVariable("选课记录ID") Long 选课记录ID)
    {
        return success(选课记录Service.select选课记录By选课记录ID(选课记录ID));
    }

    /**
     * 新增存储学生选课记录
     */
    @PreAuthorize("@ss.hasPermi('system:选课记录:add')")
    @Log(title = "存储学生选课记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody 选课记录 选课记录)
    {
        return toAjax(选课记录Service.insert选课记录(选课记录));
    }

    /**
     * 修改存储学生选课记录
     */
    @PreAuthorize("@ss.hasPermi('system:选课记录:edit')")
    @Log(title = "存储学生选课记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody 选课记录 选课记录)
    {
        return toAjax(选课记录Service.update选课记录(选课记录));
    }

    /**
     * 删除存储学生选课记录
     */
    @PreAuthorize("@ss.hasPermi('system:选课记录:remove')")
    @Log(title = "存储学生选课记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{选课记录IDs}")
    public AjaxResult remove(@PathVariable Long[] 选课记录IDs)
    {
        return toAjax(选课记录Service.delete选课记录By选课记录IDs(选课记录IDs));
    }
}
