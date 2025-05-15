package com.lz.manage.model.dto.courseInfo;

import java.util.Map;
import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import com.lz.manage.model.domain.CourseInfo;
/**
 * 课程信息Query对象 tb_course_info
 *
 * @author YY
 * @date 2025-05-16
 */
@Data
public class CourseInfoQuery implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 课程编号 */
    private Long courseId;

    /** 课程名称 */
    private String courseName;

    /** 创建人 */
    private Long userId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /** 更新人 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;

    /**
     * 对象转封装类
     *
     * @param courseInfoQuery 查询对象
     * @return CourseInfo
     */
    public static CourseInfo queryToObj(CourseInfoQuery courseInfoQuery) {
        if (courseInfoQuery == null) {
            return null;
        }
        CourseInfo courseInfo = new CourseInfo();
        BeanUtils.copyProperties(courseInfoQuery, courseInfo);
        return courseInfo;
    }
}
