package com.lz.manage.model.vo.gradeInfo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 分数统计
 *
 * @Project: grade
 * @Author: YY
 * @CreateTime: 2025-05-16  23:40
 * @Version: 1.0
 */
@Data
public class GradeInfoStatics {
    //平均分
    private BigDecimal avgScore;
    //最高分
    private BigDecimal maxScore;
    //最低分
    private BigDecimal minScore;
}
