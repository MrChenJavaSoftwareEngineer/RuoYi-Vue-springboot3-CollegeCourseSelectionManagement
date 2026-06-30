import request from '@/utils/request'

// 查询存储学生选课记录列表
export function list选课记录(query) {
  return request({
    url: '/system/选课记录/list',
    method: 'get',
    params: query
  })
}

// 查询存储学生选课记录详细
export function get选课记录(选课记录ID) {
  return request({
    url: '/system/选课记录/' + 选课记录ID,
    method: 'get'
  })
}

// 新增存储学生选课记录
export function add选课记录(data) {
  return request({
    url: '/system/选课记录',
    method: 'post',
    data: data
  })
}

// 修改存储学生选课记录
export function update选课记录(data) {
  return request({
    url: '/system/选课记录',
    method: 'put',
    data: data
  })
}

// 删除存储学生选课记录
export function del选课记录(选课记录ID) {
  return request({
    url: '/system/选课记录/' + 选课记录ID,
    method: 'delete'
  })
}
