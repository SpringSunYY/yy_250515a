package com.lz.manage.model.dto.courseInfo;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.CourseInfo;
/**
 * 课程信息Vo对象 tb_course_info
 *
 * @author YY
 * @date 2025-05-16
 */
@Data
public class CourseInfoEdit implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 课程编号 */
    private Long courseId;

    /** 课程名称 */
    private String courseName;

    /** 课程描述 */
    private String courseDesc;

    /** 创建人 */
    private Long userId;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param courseInfoEdit 编辑对象
     * @return CourseInfo
     */
    public static CourseInfo editToObj(CourseInfoEdit courseInfoEdit) {
        if (courseInfoEdit == null) {
            return null;
        }
        CourseInfo courseInfo = new CourseInfo();
        BeanUtils.copyProperties(courseInfoEdit, courseInfo);
        return courseInfo;
    }
}
