package com.lz.manage.model.dto.gradeInfo;

import java.util.Map;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import com.lz.manage.model.domain.GradeInfo;

/**
 * 学生成绩信息Query对象 tb_grade_info
 *
 * @author YY
 * @date 2025-05-16
 */
@Data
public class GradeInfoQuery implements Serializable {
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
     * 成绩描述
     */
    private String gradeDesc;

    /**
     * 学生
     */
    private Long userId;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

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
     * 请求参数
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;

    /**
     * 对象转封装类
     *
     * @param gradeInfoQuery 查询对象
     * @return GradeInfo
     */
    public static GradeInfo queryToObj(GradeInfoQuery gradeInfoQuery) {
        if (gradeInfoQuery == null) {
            return null;
        }
        GradeInfo gradeInfo = new GradeInfo();
        BeanUtils.copyProperties(gradeInfoQuery, gradeInfo);
        return gradeInfo;
    }
}
