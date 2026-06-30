import request from '@/utils/request'

// 查询存储教师基本信息列表
export function list教师(query) {
  return request({
    url: '/system/教师/list',
    method: 'get',
    params: query
  })
}

// 查询存储教师基本信息详细
export function get教师(教师工号) {
  return request({
    url: '/system/教师/' + 教师工号,
    method: 'get'
  })
}

// 新增存储教师基本信息
export function add教师(data) {
  return request({
    url: '/system/教师',
    method: 'post',
    data: data
  })
}

// 修改存储教师基本信息
export function update教师(data) {
  return request({
    url: '/system/教师',
    method: 'put',
    data: data
  })
}

// 删除存储教师基本信息
export function del教师(教师工号) {
  return request({
    url: '/system/教师/' + 教师工号,
    method: 'delete'
  })
}
