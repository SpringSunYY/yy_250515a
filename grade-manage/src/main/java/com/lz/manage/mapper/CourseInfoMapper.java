package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.CourseInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 课程信息Mapper接口
 * 
 * @author YY
 * @date 2025-05-16
 */
public interface CourseInfoMapper extends BaseMapper<CourseInfo>
{
    /**
     * 查询课程信息
     * 
     * @param courseId 课程信息主键
     * @return 课程信息
     */
    public CourseInfo selectCourseInfoByCourseId(Long courseId);

    /**
     * 查询课程信息列表
     * 
     * @param courseInfo 课程信息
     * @return 课程信息集合
     */
    public List<CourseInfo> selectCourseInfoList(CourseInfo courseInfo);

    /**
     * 新增课程信息
     * 
     * @param courseInfo 课程信息
     * @return 结果
     */
    public int insertCourseInfo(CourseInfo courseInfo);

    /**
     * 修改课程信息
     * 
     * @param courseInfo 课程信息
     * @return 结果
     */
    public int updateCourseInfo(CourseInfo courseInfo);

    /**
     * 删除课程信息
     * 
     * @param courseId 课程信息主键
     * @return 结果
     */
    public int deleteCourseInfoByCourseId(Long courseId);

    /**
     * 批量删除课程信息
     * 
     * @param courseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCourseInfoByCourseIds(Long[] courseIds);
}
