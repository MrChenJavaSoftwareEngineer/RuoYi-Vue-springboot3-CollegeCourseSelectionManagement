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
import com.ruoyi.system.domain.课程;
import com.ruoyi.system.service.I课程Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 存储课程基本信息Controller
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
@RestController
@RequestMapping("/system/课程")
public class 课程Controller extends BaseController
{
    @Autowired
    private I课程Service 课程Service;

    /**
     * 查询存储课程基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:课程:list')")
    @GetMapping("/list")
    public TableDataInfo list(课程 课程)
    {
        startPage();
        List<课程> list = 课程Service.select课程List(课程);
        return getDataTable(list);
    }

    /**
     * 导出存储课程基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:课程:export')")
    @Log(title = "存储课程基本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, 课程 课程)
    {
        List<课程> list = 课程Service.select课程List(课程);
        ExcelUtil<课程> util = new ExcelUtil<课程>(课程.class);
        util.exportExcel(response, list, "存储课程基本信息数据");
    }

    /**
     * 获取存储课程基本信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:课程:query')")
    @GetMapping(value = "/{课程编号}")
    public AjaxResult getInfo(@PathVariable("课程编号") String 课程编号)
    {
        return success(课程Service.select课程By课程编号(课程编号));
    }

    /**
     * 新增存储课程基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:课程:add')")
    @Log(title = "存储课程基本信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody 课程 课程)
    {
        return toAjax(课程Service.insert课程(课程));
    }

    /**
     * 修改存储课程基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:课程:edit')")
    @Log(title = "存储课程基本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody 课程 课程)
    {
        return toAjax(课程Service.update课程(课程));
    }

    /**
     * 删除存储课程基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:课程:remove')")
    @Log(title = "存储课程基本信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{课程编号s}")
    public AjaxResult remove(@PathVariable String[] 课程编号s)
    {
        return toAjax(课程Service.delete课程By课程编号s(课程编号s));
    }
}
