import request from '@/utils/request'

// 查询存储教室基本信息列表
export function list教室(query) {
  return request({
    url: '/system/教室/list',
    method: 'get',
    params: query
  })
}

// 查询存储教室基本信息详细
export function get教室(教室编号) {
  return request({
    url: '/system/教室/' + 教室编号,
    method: 'get'
  })
}

// 新增存储教室基本信息
export function add教室(data) {
  return request({
    url: '/system/教室',
    method: 'post',
    data: data
  })
}

// 修改存储教室基本信息
export function update教室(data) {
  return request({
    url: '/system/教室',
    method: 'put',
    data: data
  })
}

// 删除存储教室基本信息
export function del教室(教室编号) {
  return request({
    url: '/system/教室/' + 教室编号,
    method: 'delete'
  })
}
