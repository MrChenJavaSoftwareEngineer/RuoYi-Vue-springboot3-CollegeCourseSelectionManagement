<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="课程全称" prop="课程名称">
        <el-input
          v-model="queryParams.课程名称"
          placeholder="请输入课程全称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课程容量" prop="课程容量">
        <el-input
          v-model="queryParams.课程容量"
          placeholder="请输入课程可容纳的最大学生数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课程学分" prop="学分">
        <el-input
          v-model="queryParams.学分"
          placeholder="请输入课程学分值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="必须先修的课程编号，可为空" prop="先修课程编号">-->
<!--        <el-input-->
<!--          v-model="queryParams.先修课程编号"-->
<!--          placeholder="请输入必须先修的课程编号，可为空"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="专业" prop="所属专业">
        <el-input
          v-model="queryParams.所属专业"
          placeholder="请输入课程所属的专业领域"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="软删除标记：0-未删除，1-已删除" prop="逻辑删除">-->
<!--        <el-input-->
<!--          v-model="queryParams.逻辑删除"-->
<!--          placeholder="请输入软删除标记：0-未删除，1-已删除"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:课程:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:课程:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:课程:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:课程:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="课程List" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="课程编号" align="center" prop="课程编号" />
      <el-table-column label="课程全称" align="center" prop="课程名称" />
      <el-table-column label="课程简介" align="center" prop="课程简介" />
      <el-table-column label="课程容量" align="center" prop="课程容量" />
      <el-table-column label="课程学分值" align="center" prop="学分" />
      <el-table-column label="先修课程" align="center" prop="先修课程编号" />
      <el-table-column label="所属专业" align="center" prop="所属专业" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:课程:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:课程:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改存储课程基本信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课程全称" prop="课程名称">
          <el-input v-model="form.课程名称" placeholder="请输入课程全称" />
        </el-form-item>
        <el-form-item label="课程简介" prop="课程简介">
          <el-input v-model="form.课程简介" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="课程容量" prop="课程容量">
          <el-input v-model="form.课程容量" placeholder="请输入课程可容纳的最大学生数" />
        </el-form-item>
        <el-form-item label="课程学分" prop="学分">
          <el-input v-model="form.学分" placeholder="请输入课程学分值" />
        </el-form-item>
        <el-form-item label="先修课程" prop="先修课程编号">
          <el-input v-model="form.先修课程编号" placeholder="请输入必须先修的课程编号，可为空" />
        </el-form-item>
        <el-form-item label="所属专业" prop="所属专业">
          <el-input v-model="form.所属专业" placeholder="请输入课程所属的专业领域" />
        </el-form-item>
<!--        <el-form-item label="软删除标记：0-未删除，1-已删除" prop="逻辑删除">-->
<!--          <el-input v-model="form.逻辑删除" placeholder="请输入软删除标记：0-未删除，1-已删除" />-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { list课程, get课程, del课程, add课程, update课程 } from "@/api/system/课程"

export default {
  name: "课程",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 存储课程基本信息表格数据
      课程List: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        课程名称: null,
        课程简介: null,
        课程容量: null,
        学分: null,
        先修课程编号: null,
        所属专业: null,
        逻辑删除: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        课程名称: [
          { required: true, message: "课程全称不能为空", trigger: "blur" }
        ],
        课程容量: [
          { required: true, message: "课程可容纳的最大学生数不能为空", trigger: "blur" }
        ],
        学分: [
          { required: true, message: "课程学分值不能为空", trigger: "blur" }
        ],
        所属专业: [
          { required: true, message: "课程所属的专业领域不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询存储课程基本信息列表 */
    getList() {
      this.loading = true
      list课程(this.queryParams).then(response => {
        this.课程List = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        课程编号: null,
        课程名称: null,
        课程简介: null,
        课程容量: null,
        学分: null,
        先修课程编号: null,
        所属专业: null,
        逻辑删除: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.课程编号)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加存储课程基本信息"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const 课程编号 = row.课程编号 || this.ids
      get课程(课程编号).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改存储课程基本信息"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.课程编号 != null) {
            update课程(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            add课程(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const 课程编号s = row.课程编号 || this.ids
      this.$modal.confirm('是否确认删除存储课程基本信息编号为"' + 课程编号s + '"的数据项？').then(function() {
        return del课程(课程编号s)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/课程/export', {
        ...this.queryParams
      }, `课程_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
