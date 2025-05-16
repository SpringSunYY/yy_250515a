package com.lz.manage.model.dto.gradeInfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.GradeInfo;

/**
 * 学生成绩信息Vo对象 tb_grade_info
 *
 * @author YY
 * @date 2025-05-16
 */
@Data
public class GradeInfoInsert implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 成绩编号
     */
    private Long gradeId;

    /**
     * 课程名称
     */
    private Long courseId;

    /**
     * 学生成绩
     */
    private BigDecimal score;

    /**
     * 成绩描述
     */
    private String gradeDesc;

    /**
     * 学生
     */
    private Long userId;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param gradeInfoInsert 插入对象
     * @return GradeInfoInsert
     */
    public static GradeInfo insertToObj(GradeInfoInsert gradeInfoInsert) {
        if (gradeInfoInsert == null) {
            return null;
        }
        GradeInfo gradeInfo = new GradeInfo();
        BeanUtils.copyProperties(gradeInfoInsert, gradeInfo);
        return gradeInfo;
    }
}
