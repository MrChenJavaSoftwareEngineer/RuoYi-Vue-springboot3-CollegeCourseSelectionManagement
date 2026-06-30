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
import com.ruoyi.system.domain.教室;
import com.ruoyi.system.service.I教室Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 存储教室基本信息Controller
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
@RestController
@RequestMapping("/system/教室")
public class 教室Controller extends BaseController
{
    @Autowired
    private I教室Service 教室Service;

    /**
     * 查询存储教室基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:教室:list')")
    @GetMapping("/list")
    public TableDataInfo list(教室 教室)
    {
        startPage();
        List<教室> list = 教室Service.select教室List(教室);
        return getDataTable(list);
    }

    /**
     * 导出存储教室基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:教室:export')")
    @Log(title = "存储教室基本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, 教室 教室)
    {
        List<教室> list = 教室Service.select教室List(教室);
        ExcelUtil<教室> util = new ExcelUtil<教室>(教室.class);
        util.exportExcel(response, list, "存储教室基本信息数据");
    }

    /**
     * 获取存储教室基本信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:教室:query')")
    @GetMapping(value = "/{教室编号}")
    public AjaxResult getInfo(@PathVariable("教室编号") String 教室编号)
    {
        return success(教室Service.select教室By教室编号(教室编号));
    }

    /**
     * 新增存储教室基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:教室:add')")
    @Log(title = "存储教室基本信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody 教室 教室)
    {
        return toAjax(教室Service.insert教室(教室));
    }

    /**
     * 修改存储教室基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:教室:edit')")
    @Log(title = "存储教室基本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody 教室 教室)
    {
        return toAjax(教室Service.update教室(教室));
    }

    /**
     * 删除存储教室基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:教室:remove')")
    @Log(title = "存储教室基本信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{教室编号s}")
    public AjaxResult remove(@PathVariable String[] 教室编号s)
    {
        return toAjax(教室Service.delete教室By教室编号s(教室编号s));
    }
}
