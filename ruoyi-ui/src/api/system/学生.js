import request from '@/utils/request'

// 查询存储学生基本信息列表
export function list学生(query) {
  return request({
    url: '/system/学生/list',
    method: 'get',
    params: query
  })
}

// 查询存储学生基本信息详细
export function get学生(学号) {
  return request({
    url: '/system/学生/' + 学号,
    method: 'get'
  })
}

// 新增存储学生基本信息
export function add学生(data) {
  return request({
    url: '/system/学生',
    method: 'post',
    data: data
  })
}

// 修改存储学生基本信息
export function update学生(data) {
  return request({
    url: '/system/学生',
    method: 'put',
    data: data
  })
}

// 删除存储学生基本信息
export function del学生(学号) {
  return request({
    url: '/system/学生/' + 学号,
    method: 'delete'
  })
}
