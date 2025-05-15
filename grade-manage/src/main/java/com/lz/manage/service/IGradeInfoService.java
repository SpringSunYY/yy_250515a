package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.GradeInfo;
import com.lz.manage.model.vo.gradeInfo.GradeInfoVo;
import com.lz.manage.model.dto.gradeInfo.GradeInfoQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 学生成绩信息Service接口
 * 
 * @author YY
 * @date 2025-05-16
 */
public interface IGradeInfoService extends IService<GradeInfo>
{
    //region mybatis代码
    /**
     * 查询学生成绩信息
     * 
     * @param gradeId 学生成绩信息主键
     * @return 学生成绩信息
     */
    public GradeInfo selectGradeInfoByGradeId(Long gradeId);

    /**
     * 查询学生成绩信息列表
     * 
     * @param gradeInfo 学生成绩信息
     * @return 学生成绩信息集合
     */
    public List<GradeInfo> selectGradeInfoList(GradeInfo gradeInfo);

    /**
     * 新增学生成绩信息
     * 
     * @param gradeInfo 学生成绩信息
     * @return 结果
     */
    public int insertGradeInfo(GradeInfo gradeInfo);

    /**
     * 修改学生成绩信息
     * 
     * @param gradeInfo 学生成绩信息
     * @return 结果
     */
    public int updateGradeInfo(GradeInfo gradeInfo);

    /**
     * 批量删除学生成绩信息
     * 
     * @param gradeIds 需要删除的学生成绩信息主键集合
     * @return 结果
     */
    public int deleteGradeInfoByGradeIds(Long[] gradeIds);

    /**
     * 删除学生成绩信息信息
     * 
     * @param gradeId 学生成绩信息主键
     * @return 结果
     */
    public int deleteGradeInfoByGradeId(Long gradeId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param gradeInfoQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<GradeInfo> getQueryWrapper(GradeInfoQuery gradeInfoQuery);

    /**
     * 转换vo
     *
     * @param gradeInfoList GradeInfo集合
     * @return GradeInfoVO集合
     */
    List<GradeInfoVo> convertVoList(List<GradeInfo> gradeInfoList);
}
