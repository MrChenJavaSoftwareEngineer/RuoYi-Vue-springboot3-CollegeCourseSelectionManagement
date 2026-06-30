<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="年级" prop="年级">
        <el-input
          v-model="queryParams.年级"
          placeholder="请输入学生所在年级"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="姓名" prop="姓名">
        <el-input
          v-model="queryParams.姓名"
          placeholder="请输入学生姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="用户类型标识" prop="用户类型">-->
<!--        <el-input-->
<!--          v-model="queryParams.用户类型"-->
<!--          placeholder="请输入用户类型标识"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="专业" prop="专业">
        <el-input
          v-model="queryParams.专业"
          placeholder="请输入学生所学专业"
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
          v-hasPermi="['system:学生:add']"
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
          v-hasPermi="['system:学生:edit']"
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
          v-hasPermi="['system:学生:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:学生:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="学生List" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="学号" align="center" prop="学号" />
      <el-table-column label="年级" align="center" prop="年级" />
      <el-table-column label="姓名" align="center" prop="姓名" />
<!--      <el-table-column label="用户类型标识" align="center" prop="用户类型" />-->
      <el-table-column label="所学专业" align="center" prop="专业" />
<!--      <el-table-column label="软删除标记：0-未删除，1-已删除" align="center" prop="逻辑删除" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:学生:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:学生:remove']"
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

    <!-- 添加或修改存储学生基本信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="年级" prop="年级">
          <el-input v-model="form.年级" placeholder="请输入学生所在年级" />
        </el-form-item>
        <el-form-item label="姓名" prop="姓名">
          <el-input v-model="form.姓名" placeholder="请输入学生姓名" />
        </el-form-item>
        <el-form-item label="类型" prop="用户类型">
          <el-input v-model="form.用户类型" placeholder="请输入用户类型标识" />
        </el-form-item>
        <el-form-item label="专业" prop="专业">
          <el-input v-model="form.专业" placeholder="请输入学生所学专业" />
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
import { list学生, get学生, del学生, add学生, update学生 } from "@/api/system/学生"

export default {
  name: "学生",
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
      // 存储学生基本信息表格数据
      学生List: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        年级: null,
        姓名: null,
        用户类型: null,
        专业: null,
        逻辑删除: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        年级: [
          { required: true, message: "学生所在年级不能为空", trigger: "blur" }
        ],
        姓名: [
          { required: true, message: "学生姓名不能为空", trigger: "blur" }
        ],
        专业: [
          { required: true, message: "学生所学专业不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询存储学生基本信息列表 */
    getList() {
      this.loading = true
      list学生(this.queryParams).then(response => {
        this.学生List = response.rows
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
        学号: null,
        年级: null,
        姓名: null,
        用户类型: null,
        专业: null,
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
      this.ids = selection.map(item => item.学号)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加存储学生基本信息"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const 学号 = row.学号 || this.ids
      get学生(学号).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改存储学生基本信息"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.学号 != null) {
            update学生(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            add学生(this.form).then(response => {
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
      const 学号s = row.学号 || this.ids
      this.$modal.confirm('是否确认删除存储学生基本信息编号为"' + 学号s + '"的数据项？').then(function() {
        return del学生(学号s)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/学生/export', {
        ...this.queryParams
      }, `学生_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
