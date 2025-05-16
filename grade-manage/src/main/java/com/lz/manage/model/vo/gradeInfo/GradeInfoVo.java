package com.lz.manage.model.vo.gradeInfo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.lz.common.annotation.Excel;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.manage.model.domain.GradeInfo;

/**
 * 学生成绩信息Vo对象 tb_grade_info
 *
 * @author YY
 * @date 2025-05-16
 */
@Data
public class GradeInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 成绩编号
     */
    @Excel(name = "成绩编号")
    private Long gradeId;

    /**
     * 课程名称
     */
    @Excel(name = "课程名称")
    private String courseName;
    private Long courseId;

    /**
     * 学生成绩
     */
    @Excel(name = "学生成绩")
    private BigDecimal score;

    /**
     * 成绩描述
     */
    @Excel(name = "成绩描述")
    private String gradeDesc;

    /**
     * 学生
     */
    @Excel(name = "学生")
    private String userName;
    private Long userId;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 更新人
     */
    @Excel(name = "更新人")
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateTime;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;


    /**
     * 对象转封装类
     *
     * @param gradeInfo GradeInfo实体对象
     * @return GradeInfoVo
     */
    public static GradeInfoVo objToVo(GradeInfo gradeInfo) {
        if (gradeInfo == null) {
            return null;
        }
        GradeInfoVo gradeInfoVo = new GradeInfoVo();
        BeanUtils.copyProperties(gradeInfo, gradeInfoVo);
        return gradeInfoVo;
    }
}
