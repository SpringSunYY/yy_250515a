package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import com.lz.common.utils.StringUtils;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.GradeInfoMapper;
import com.lz.manage.model.domain.GradeInfo;
import com.lz.manage.service.IGradeInfoService;
import com.lz.manage.model.dto.gradeInfo.GradeInfoQuery;
import com.lz.manage.model.vo.gradeInfo.GradeInfoVo;

/**
 * 学生成绩信息Service业务层处理
 * 
 * @author YY
 * @date 2025-05-16
 */
@Service
public class GradeInfoServiceImpl extends ServiceImpl<GradeInfoMapper, GradeInfo> implements IGradeInfoService
{
    @Resource
    private GradeInfoMapper gradeInfoMapper;

    //region mybatis代码
    /**
     * 查询学生成绩信息
     * 
     * @param gradeId 学生成绩信息主键
     * @return 学生成绩信息
     */
    @Override
    public GradeInfo selectGradeInfoByGradeId(Long gradeId)
    {
        return gradeInfoMapper.selectGradeInfoByGradeId(gradeId);
    }

    /**
     * 查询学生成绩信息列表
     * 
     * @param gradeInfo 学生成绩信息
     * @return 学生成绩信息
     */
    @Override
    public List<GradeInfo> selectGradeInfoList(GradeInfo gradeInfo)
    {
        return gradeInfoMapper.selectGradeInfoList(gradeInfo);
    }

    /**
     * 新增学生成绩信息
     * 
     * @param gradeInfo 学生成绩信息
     * @return 结果
     */
    @Override
    public int insertGradeInfo(GradeInfo gradeInfo)
    {
        gradeInfo.setCreateTime(DateUtils.getNowDate());
        return gradeInfoMapper.insertGradeInfo(gradeInfo);
    }

    /**
     * 修改学生成绩信息
     * 
     * @param gradeInfo 学生成绩信息
     * @return 结果
     */
    @Override
    public int updateGradeInfo(GradeInfo gradeInfo)
    {
        gradeInfo.setUpdateTime(DateUtils.getNowDate());
        return gradeInfoMapper.updateGradeInfo(gradeInfo);
    }

    /**
     * 批量删除学生成绩信息
     * 
     * @param gradeIds 需要删除的学生成绩信息主键
     * @return 结果
     */
    @Override
    public int deleteGradeInfoByGradeIds(Long[] gradeIds)
    {
        return gradeInfoMapper.deleteGradeInfoByGradeIds(gradeIds);
    }

    /**
     * 删除学生成绩信息信息
     * 
     * @param gradeId 学生成绩信息主键
     * @return 结果
     */
    @Override
    public int deleteGradeInfoByGradeId(Long gradeId)
    {
        return gradeInfoMapper.deleteGradeInfoByGradeId(gradeId);
    }
    //endregion
    @Override
    public QueryWrapper<GradeInfo> getQueryWrapper(GradeInfoQuery gradeInfoQuery){
        QueryWrapper<GradeInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = gradeInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long gradeId = gradeInfoQuery.getGradeId();
        queryWrapper.eq( StringUtils.isNotNull(gradeId),"grade_id",gradeId);

        Long courseId = gradeInfoQuery.getCourseId();
        queryWrapper.eq( StringUtils.isNotNull(courseId),"course_id",courseId);

        BigDecimal score = gradeInfoQuery.getScore();
        queryWrapper.eq( StringUtils.isNotNull(score),"score",score);

        Long userId = gradeInfoQuery.getUserId();
        queryWrapper.eq( StringUtils.isNotNull(userId),"user_id",userId);

        Date createTime = gradeInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime"))&&StringUtils.isNotNull(params.get("endCreateTime")),"create_time",params.get("beginCreateTime"),params.get("endCreateTime"));

        String updateBy = gradeInfoQuery.getUpdateBy();
        queryWrapper.like(StringUtils.isNotEmpty(updateBy) ,"update_by",updateBy);

        Date updateTime = gradeInfoQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime"))&&StringUtils.isNotNull(params.get("endUpdateTime")),"update_time",params.get("beginUpdateTime"),params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<GradeInfoVo> convertVoList(List<GradeInfo> gradeInfoList) {
        if (StringUtils.isEmpty(gradeInfoList)) {
            return Collections.emptyList();
        }
        return gradeInfoList.stream().map(GradeInfoVo::objToVo).collect(Collectors.toList());
    }

}
