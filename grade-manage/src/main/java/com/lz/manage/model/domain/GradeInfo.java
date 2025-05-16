package com.lz.manage.model.domain;

import java.io.Serializable;
import java.util.Map;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.lz.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 学生成绩信息对象 tb_grade_info
 *
 * @author YY
 * @date 2025-05-16
 */
@TableName("tb_grade_info")
@Data
public class GradeInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 成绩编号
     */
    @Excel(name = "成绩编号",type = Excel.Type.EXPORT)
    @TableId(value = "grade_id", type = IdType.ASSIGN_ID)
    private Long gradeId;

    /**
     * 课程名称
     */
    @Excel(name = "课程名称", type = Excel.Type.EXPORT)
    @TableField(exist = false)
    private String courseName;
    @Excel(name = "课程编号", type = Excel.Type.IMPORT)
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
    @Excel(name = "学生",type = Excel.Type.EXPORT)
    @TableField(exist = false)
    private String userName;
    @Excel(name = "学生编号", type = Excel.Type.IMPORT)
    private Long userId;

    /**
     * 创建人
     */
    @Excel(name = "创建人",type = Excel.Type.EXPORT)
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd",type = Excel.Type.EXPORT)
    private Date createTime;

    /**
     * 更新人
     */
    @Excel(name = "更新人",type = Excel.Type.EXPORT)
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd",type = Excel.Type.EXPORT)
    private Date updateTime;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;

    /**
     * 请求参数
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;
}
