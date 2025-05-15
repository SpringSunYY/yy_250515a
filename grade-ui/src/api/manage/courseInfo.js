import request from '@/utils/request'

// 查询课程信息列表
export function listCourseInfo(query) {
  return request({
    url: '/manage/courseInfo/list',
    method: 'get',
    params: query
  })
}

// 查询课程信息详细
export function getCourseInfo(courseId) {
  return request({
    url: '/manage/courseInfo/' + courseId,
    method: 'get'
  })
}

// 新增课程信息
export function addCourseInfo(data) {
  return request({
    url: '/manage/courseInfo',
    method: 'post',
    data: data
  })
}

// 修改课程信息
export function updateCourseInfo(data) {
  return request({
    url: '/manage/courseInfo',
    method: 'put',
    data: data
  })
}

// 删除课程信息
export function delCourseInfo(courseId) {
  return request({
    url: '/manage/courseInfo/' + courseId,
    method: 'delete'
  })
}
