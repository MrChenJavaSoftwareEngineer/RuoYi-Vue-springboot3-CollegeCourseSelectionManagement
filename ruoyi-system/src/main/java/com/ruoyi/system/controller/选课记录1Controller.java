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
import com.ruoyi.system.domain.选课记录1;
import com.ruoyi.system.service.I选课记录1Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 存储学生选课记录Controller
 * 
 * @author ruoyi
 * @date 2025-12-16
 */
@RestController
@RequestMapping("/system/选课记录vo")
public class 选课记录1Controller extends BaseController
{
    @Autowired
    private I选课记录1Service 选课记录1Service;

    /**
     * 查询存储学生选课记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:选课记录vo:list')")
    @GetMapping("/list")
    public TableDataInfo list(选课记录1 选课记录1)
    {
        startPage();
        List<选课记录1> list = 选课记录1Service.select选课记录1List(选课记录1);
        return getDataTable(list);
    }

    /**
     * 导出存储学生选课记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:选课记录vo:export')")
    @Log(title = "存储学生选课记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, 选课记录1 选课记录1)
    {
        List<选课记录1> list = 选课记录1Service.select选课记录1List(选课记录1);
        ExcelUtil<选课记录1> util = new ExcelUtil<选课记录1>(选课记录1.class);
        util.exportExcel(response, list, "存储学生选课记录数据");
    }

    /**
     * 获取存储学生选课记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:选课记录vo:query')")
    @GetMapping(value = "/{选课记录ID}")
    public AjaxResult getInfo(@PathVariable("选课记录ID") Long 选课记录ID)
    {
        return success(选课记录1Service.select选课记录1By选课记录ID(选课记录ID));
    }

    /**
     * 新增存储学生选课记录
     */
    @PreAuthorize("@ss.hasPermi('system:选课记录vo:add')")
    @Log(title = "存储学生选课记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody 选课记录1 选课记录1)
    {
        return toAjax(选课记录1Service.insert选课记录1(选课记录1));
    }

    /**
     * 修改存储学生选课记录
     */
    @PreAuthorize("@ss.hasPermi('system:选课记录vo:edit')")
    @Log(title = "存储学生选课记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody 选课记录1 选课记录1)
    {
        return toAjax(选课记录1Service.update选课记录1(选课记录1));
    }

    /**
     * 删除存储学生选课记录
     */
    @PreAuthorize("@ss.hasPermi('system:选课记录vo:remove')")
    @Log(title = "存储学生选课记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{选课记录IDs}")
    public AjaxResult remove(@PathVariable Long[] 选课记录IDs)
    {
        return toAjax(选课记录1Service.delete选课记录1By选课记录IDs(选课记录IDs));
    }
}
