import request from '@/utils/request'

// 查询存储课程计划安排列表
export function list课程计划vo(query) {
  return request({
    url: '/system/课程计划vo/list',
    method: 'get',
    params: query
  })
}

// 查询存储课程计划安排详细
export function get课程计划vo(课程计划编号) {
  return request({
    url: '/system/课程计划vo/' + 课程计划编号,
    method: 'get'
  })
}

// 新增存储课程计划安排
export function add课程计划vo(data) {
  return request({
    url: '/system/课程计划vo',
    method: 'post',
    data: data
  })
}

// 修改存储课程计划安排
export function update课程计划vo(data) {
  return request({
    url: '/system/课程计划vo',
    method: 'put',
    data: data
  })
}

// 删除存储课程计划安排
export function del课程计划vo(课程计划编号) {
  return request({
    url: '/system/课程计划vo/' + 课程计划编号,
    method: 'delete'
  })
}
