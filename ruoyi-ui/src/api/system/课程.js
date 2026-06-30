import request from '@/utils/request'

// 查询存储课程基本信息列表
export function list课程(query) {
  return request({
    url: '/system/课程/list',
    method: 'get',
    params: query
  })
}

// 查询存储课程基本信息详细
export function get课程(课程编号) {
  return request({
    url: '/system/课程/' + 课程编号,
    method: 'get'
  })
}

// 新增存储课程基本信息
export function add课程(data) {
  return request({
    url: '/system/课程',
    method: 'post',
    data: data
  })
}

// 修改存储课程基本信息
export function update课程(data) {
  return request({
    url: '/system/课程',
    method: 'put',
    data: data
  })
}

// 删除存储课程基本信息
export function del课程(课程编号) {
  return request({
    url: '/system/课程/' + 课程编号,
    method: 'delete'
  })
}
