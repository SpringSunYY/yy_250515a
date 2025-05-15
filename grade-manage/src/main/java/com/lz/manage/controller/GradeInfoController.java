package com.lz.manage.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lz.common.annotation.Log;
import com.lz.common.core.controller.BaseController;
import com.lz.common.core.domain.AjaxResult;
import com.lz.common.enums.BusinessType;
import com.lz.manage.model.domain.GradeInfo;
import com.lz.manage.model.vo.gradeInfo.GradeInfoVo;
import com.lz.manage.model.dto.gradeInfo.GradeInfoQuery;
import com.lz.manage.model.dto.gradeInfo.GradeInfoInsert;
import com.lz.manage.model.dto.gradeInfo.GradeInfoEdit;
import com.lz.manage.service.IGradeInfoService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;

/**
 * 学生成绩信息Controller
 *
 * @author YY
 * @date 2025-05-16
 */
@RestController
@RequestMapping("/manage/gradeInfo")
public class GradeInfoController extends BaseController
{
    @Resource
    private IGradeInfoService gradeInfoService;

    /**
     * 查询学生成绩信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:gradeInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(GradeInfoQuery gradeInfoQuery)
    {
        GradeInfo gradeInfo = GradeInfoQuery.queryToObj(gradeInfoQuery);
        startPage();
        List<GradeInfo> list = gradeInfoService.selectGradeInfoList(gradeInfo);
        List<GradeInfoVo> listVo= list.stream().map(GradeInfoVo::objToVo).collect(Collectors.toList());
        TableDataInfo table = getDataTable(list);
        table.setRows(listVo);
        return table;
    }

    /**
     * 导出学生成绩信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:gradeInfo:export')")
    @Log(title = "学生成绩信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GradeInfoQuery gradeInfoQuery)
    {
        GradeInfo gradeInfo = GradeInfoQuery.queryToObj(gradeInfoQuery);
        List<GradeInfo> list = gradeInfoService.selectGradeInfoList(gradeInfo);
        ExcelUtil<GradeInfo> util = new ExcelUtil<GradeInfo>(GradeInfo.class);
        util.exportExcel(response, list, "学生成绩信息数据");
    }

    /**
     * 获取学生成绩信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:gradeInfo:query')")
    @GetMapping(value = "/{gradeId}")
    public AjaxResult getInfo(@PathVariable("gradeId") Long gradeId)
    {
        GradeInfo gradeInfo = gradeInfoService.selectGradeInfoByGradeId(gradeId);
        return success(GradeInfoVo.objToVo(gradeInfo));
    }

    /**
     * 新增学生成绩信息
     */
    @PreAuthorize("@ss.hasPermi('manage:gradeInfo:add')")
    @Log(title = "学生成绩信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GradeInfoInsert gradeInfoInsert)
    {
        GradeInfo gradeInfo = GradeInfoInsert.insertToObj(gradeInfoInsert);
        return toAjax(gradeInfoService.insertGradeInfo(gradeInfo));
    }

    /**
     * 修改学生成绩信息
     */
    @PreAuthorize("@ss.hasPermi('manage:gradeInfo:edit')")
    @Log(title = "学生成绩信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GradeInfoEdit gradeInfoEdit)
    {
        GradeInfo gradeInfo = GradeInfoEdit.editToObj(gradeInfoEdit);
        return toAjax(gradeInfoService.updateGradeInfo(gradeInfo));
    }

    /**
     * 删除学生成绩信息
     */
    @PreAuthorize("@ss.hasPermi('manage:gradeInfo:remove')")
    @Log(title = "学生成绩信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{gradeIds}")
    public AjaxResult remove(@PathVariable Long[] gradeIds)
    {
        return toAjax(gradeInfoService.deleteGradeInfoByGradeIds(gradeIds));
    }
}
