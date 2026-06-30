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
import com.ruoyi.system.domain.教师;
import com.ruoyi.system.service.I教师Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 存储教师基本信息Controller
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
@RestController
@RequestMapping("/system/教师")
public class 教师Controller extends BaseController
{
    @Autowired
    private I教师Service 教师Service;

    /**
     * 查询存储教师基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:教师:list')")
    @GetMapping("/list")
    public TableDataInfo list(教师 教师)
    {
        startPage();
        List<教师> list = 教师Service.select教师List(教师);
        return getDataTable(list);
    }

    /**
     * 导出存储教师基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:教师:export')")
    @Log(title = "存储教师基本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, 教师 教师)
    {
        List<教师> list = 教师Service.select教师List(教师);
        ExcelUtil<教师> util = new ExcelUtil<教师>(教师.class);
        util.exportExcel(response, list, "存储教师基本信息数据");
    }

    /**
     * 获取存储教师基本信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:教师:query')")
    @GetMapping(value = "/{教师工号}")
    public AjaxResult getInfo(@PathVariable("教师工号") String 教师工号)
    {
        return success(教师Service.select教师By教师工号(教师工号));
    }

    /**
     * 新增存储教师基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:教师:add')")
    @Log(title = "存储教师基本信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody 教师 教师)
    {
        return toAjax(教师Service.insert教师(教师));
    }

    /**
     * 修改存储教师基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:教师:edit')")
    @Log(title = "存储教师基本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody 教师 教师)
    {
        return toAjax(教师Service.update教师(教师));
    }

    /**
     * 删除存储教师基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:教师:remove')")
    @Log(title = "存储教师基本信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{教师工号s}")
    public AjaxResult remove(@PathVariable String[] 教师工号s)
    {
        return toAjax(教师Service.delete教师By教师工号s(教师工号s));
    }
}
