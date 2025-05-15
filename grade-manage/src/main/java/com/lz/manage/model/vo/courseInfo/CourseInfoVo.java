package com.lz.manage.model.vo.courseInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.lz.common.annotation.Excel;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.manage.model.domain.CourseInfo;
/**
 * 课程信息Vo对象 tb_course_info
 *
 * @author YY
 * @date 2025-05-16
 */
@Data
public class CourseInfoVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 课程编号 */
    @Excel(name = "课程编号")
    private Long courseId;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 课程描述 */
    @Excel(name = "课程描述")
    private String courseDesc;

    /** 创建人 */
    @Excel(name = "创建人")
    private Long userId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateTime;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;


     /**
     * 对象转封装类
     *
     * @param courseInfo CourseInfo实体对象
     * @return CourseInfoVo
     */
    public static CourseInfoVo objToVo(CourseInfo courseInfo) {
        if (courseInfo == null) {
            return null;
        }
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(courseInfo, courseInfoVo);
        return courseInfoVo;
    }
}
