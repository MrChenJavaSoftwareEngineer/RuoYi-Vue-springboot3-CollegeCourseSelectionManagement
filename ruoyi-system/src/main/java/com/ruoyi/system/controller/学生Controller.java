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
import com.ruoyi.system.domain.学生;
import com.ruoyi.system.service.I学生Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 存储学生基本信息Controller
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
@RestController
@RequestMapping("/system/学生")
public class 学生Controller extends BaseController
{
    @Autowired
    private I学生Service 学生Service;

    /**
     * 查询存储学生基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:学生:list')")
    @GetMapping("/list")
    public TableDataInfo list(学生 学生)
    {
        startPage();
        List<学生> list = 学生Service.select学生List(学生);
        return getDataTable(list);
    }

    /**
     * 导出存储学生基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:学生:export')")
    @Log(title = "存储学生基本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, 学生 学生)
    {
        List<学生> list = 学生Service.select学生List(学生);
        ExcelUtil<学生> util = new ExcelUtil<学生>(学生.class);
        util.exportExcel(response, list, "存储学生基本信息数据");
    }

    /**
     * 获取存储学生基本信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:学生:query')")
    @GetMapping(value = "/{学号}")
    public AjaxResult getInfo(@PathVariable("学号") String 学号)
    {
        return success(学生Service.select学生By学号(学号));
    }

    /**
     * 新增存储学生基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:学生:add')")
    @Log(title = "存储学生基本信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody 学生 学生)
    {
        return toAjax(学生Service.insert学生(学生));
    }

    /**
     * 修改存储学生基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:学生:edit')")
    @Log(title = "存储学生基本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody 学生 学生)
    {
        return toAjax(学生Service.update学生(学生));
    }

    /**
     * 删除存储学生基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:学生:remove')")
    @Log(title = "存储学生基本信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{学号s}")
    public AjaxResult remove(@PathVariable String[] 学号s)
    {
        return toAjax(学生Service.delete学生By学号s(学号s));
    }
}
