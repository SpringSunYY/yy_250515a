import request from '@/utils/request'

// 查询学生成绩信息列表
export function listGradeInfo(query) {
  return request({
    url: '/manage/gradeInfo/list',
    method: 'get',
    params: query
  })
}

// 查询学生成绩信息详细
export function getGradeInfo(gradeId) {
  return request({
    url: '/manage/gradeInfo/' + gradeId,
    method: 'get'
  })
}

// 新增学生成绩信息
export function addGradeInfo(data) {
  return request({
    url: '/manage/gradeInfo',
    method: 'post',
    data: data
  })
}

// 修改学生成绩信息
export function updateGradeInfo(data) {
  return request({
    url: '/manage/gradeInfo',
    method: 'put',
    data: data
  })
}

// 删除学生成绩信息
export function delGradeInfo(gradeId) {
  return request({
    url: '/manage/gradeInfo/' + gradeId,
    method: 'delete'
  })
}
