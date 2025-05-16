package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.lz.common.annotation.DataScope;
import com.lz.common.core.domain.entity.SysUser;
import com.lz.common.utils.SecurityUtils;
import com.lz.common.utils.StringUtils;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;

import javax.annotation.Resource;

import com.lz.system.service.ISysUserService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.CourseInfoMapper;
import com.lz.manage.model.domain.CourseInfo;
import com.lz.manage.service.ICourseInfoService;
import com.lz.manage.model.dto.courseInfo.CourseInfoQuery;
import com.lz.manage.model.vo.courseInfo.CourseInfoVo;

/**
 * 课程信息Service业务层处理
 *
 * @author YY
 * @date 2025-05-16
 */
@Service
public class CourseInfoServiceImpl extends ServiceImpl<CourseInfoMapper, CourseInfo> implements ICourseInfoService {
    @Resource
    private CourseInfoMapper courseInfoMapper;

    @Resource
    private ISysUserService userService;
    //region mybatis代码

    /**
     * 查询课程信息
     *
     * @param courseId 课程信息主键
     * @return 课程信息
     */
    @Override
    public CourseInfo selectCourseInfoByCourseId(Long courseId) {
        return courseInfoMapper.selectCourseInfoByCourseId(courseId);
    }

    /**
     * 查询课程信息列表
     *
     * @param courseInfo 课程信息
     * @return 课程信息
     */
    @Override
    public List<CourseInfo> selectCourseInfoList(CourseInfo courseInfo) {
        List<CourseInfo> courseInfos = courseInfoMapper.selectCourseInfoList(courseInfo);
        for (CourseInfo info : courseInfos) {
            SysUser sysUser = userService.selectUserById(info.getUserId());
            if (StringUtils.isNotNull(sysUser)) {
                info.setUserName(sysUser.getUserName());
            }
        }
        return courseInfos;
    }

    /**
     * 新增课程信息
     *
     * @param courseInfo 课程信息
     * @return 结果
     */
    @Override
    public int insertCourseInfo(CourseInfo courseInfo) {
        courseInfo.setUserId(SecurityUtils.getUserId());
        courseInfo.setCreateTime(DateUtils.getNowDate());
        return courseInfoMapper.insertCourseInfo(courseInfo);
    }

    /**
     * 修改课程信息
     *
     * @param courseInfo 课程信息
     * @return 结果
     */
    @Override
    public int updateCourseInfo(CourseInfo courseInfo) {
        courseInfo.setUpdateBy(SecurityUtils.getUsername());
        courseInfo.setUpdateTime(DateUtils.getNowDate());
        return courseInfoMapper.updateCourseInfo(courseInfo);
    }

    /**
     * 批量删除课程信息
     *
     * @param courseIds 需要删除的课程信息主键
     * @return 结果
     */
    @Override
    public int deleteCourseInfoByCourseIds(Long[] courseIds) {
        return courseInfoMapper.deleteCourseInfoByCourseIds(courseIds);
    }

    /**
     * 删除课程信息信息
     *
     * @param courseId 课程信息主键
     * @return 结果
     */
    @Override
    public int deleteCourseInfoByCourseId(Long courseId) {
        return courseInfoMapper.deleteCourseInfoByCourseId(courseId);
    }

    //endregion
    @Override
    public QueryWrapper<CourseInfo> getQueryWrapper(CourseInfoQuery courseInfoQuery) {
        QueryWrapper<CourseInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = courseInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long courseId = courseInfoQuery.getCourseId();
        queryWrapper.eq(StringUtils.isNotNull(courseId), "course_id", courseId);

        String courseName = courseInfoQuery.getCourseName();
        queryWrapper.like(StringUtils.isNotEmpty(courseName), "course_name", courseName);

        Long userId = courseInfoQuery.getUserId();
        queryWrapper.eq(StringUtils.isNotNull(userId), "user_id", userId);

        Date createTime = courseInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime")) && StringUtils.isNotNull(params.get("endCreateTime")), "create_time", params.get("beginCreateTime"), params.get("endCreateTime"));

        String updateBy = courseInfoQuery.getUpdateBy();
        queryWrapper.like(StringUtils.isNotEmpty(updateBy), "update_by", updateBy);

        Date updateTime = courseInfoQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime")) && StringUtils.isNotNull(params.get("endUpdateTime")), "update_time", params.get("beginUpdateTime"), params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<CourseInfoVo> convertVoList(List<CourseInfo> courseInfoList) {
        if (StringUtils.isEmpty(courseInfoList)) {
            return Collections.emptyList();
        }
        return courseInfoList.stream().map(CourseInfoVo::objToVo).collect(Collectors.toList());
    }

}
