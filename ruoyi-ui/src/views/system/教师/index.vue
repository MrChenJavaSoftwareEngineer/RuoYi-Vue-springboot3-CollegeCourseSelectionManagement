<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="教师姓名" prop="姓名">
        <el-input
          v-model="queryParams.姓名"
          placeholder="请输入教师姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="教师职称" prop="职称">
        <el-input
          v-model="queryParams.职称"
          placeholder="请输入教师职称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="部门" prop="部门">
        <el-input
          v-model="queryParams.部门"
          placeholder="请输入教师所在部门"
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
          v-hasPermi="['system:教师:add']"
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
          v-hasPermi="['system:教师:edit']"
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
          v-hasPermi="['system:教师:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:教师:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="教师List" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="教师唯一工号" align="center" prop="教师工号" />
      <el-table-column label="教师姓名" align="center" prop="姓名" />
      <el-table-column label="教师职称" align="center" prop="职称" />
      <el-table-column label="教师所在部门" align="center" prop="部门" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:教师:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:教师:remove']"
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

    <!-- 添加或修改存储教师基本信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="教师姓名" prop="姓名">
          <el-input v-model="form.姓名" placeholder="请输入教师姓名" />
        </el-form-item>
        <el-form-item label="教师职称" prop="职称">
          <el-input v-model="form.职称" placeholder="请输入教师职称" />
        </el-form-item>
        <el-form-item label="所在部门" prop="部门">
          <el-input v-model="form.部门" placeholder="请输入教师所在部门" />
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
import { list教师, get教师, del教师, add教师, update教师 } from "@/api/system/教师"

export default {
  name: "教师",
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
      // 存储教师基本信息表格数据
      教师List: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        姓名: null,
        职称: null,
        部门: null,
        逻辑删除: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        姓名: [
          { required: true, message: "教师姓名不能为空", trigger: "blur" }
        ],
        部门: [
          { required: true, message: "教师所在部门不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询存储教师基本信息列表 */
    getList() {
      this.loading = true
      list教师(this.queryParams).then(response => {
        this.教师List = response.rows
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
        教师工号: null,
        姓名: null,
        职称: null,
        部门: null,
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
      this.ids = selection.map(item => item.教师工号)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加存储教师基本信息"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const 教师工号 = row.教师工号 || this.ids
      get教师(教师工号).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改存储教师基本信息"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.教师工号 != null) {
            update教师(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            add教师(this.form).then(response => {
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
      const 教师工号s = row.教师工号 || this.ids
      this.$modal.confirm('是否确认删除存储教师基本信息编号为"' + 教师工号s + '"的数据项？').then(function() {
        return del教师(教师工号s)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/教师/export', {
        ...this.queryParams
      }, `教师_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
