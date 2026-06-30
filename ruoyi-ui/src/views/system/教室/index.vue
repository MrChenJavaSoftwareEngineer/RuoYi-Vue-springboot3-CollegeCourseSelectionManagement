<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="教室地址" prop="教室地址">
        <el-input
          v-model="queryParams.教室地址"
          placeholder="请输入教室具体位置"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="教室容量" prop="教室容量">
        <el-input
          v-model="queryParams.教室容量"
          placeholder="请输入教室最大容纳人数"
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
          v-hasPermi="['system:教室:add']"
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
          v-hasPermi="['system:教室:edit']"
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
          v-hasPermi="['system:教室:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:教室:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="教室List" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="教室唯一编号" align="center" prop="教室编号" />
      <el-table-column label="教室具体位置" align="center" prop="教室地址" />
      <el-table-column label="教室最大容纳人数" align="center" prop="教室容量" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:教室:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:教室:remove']"
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

    <!-- 添加或修改存储教室基本信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="教室地址" prop="教室地址">
          <el-input v-model="form.教室地址" placeholder="请输入教室具体位置" />
        </el-form-item>
        <el-form-item label="教室容量" prop="教室容量">
          <el-input v-model="form.教室容量" placeholder="请输入教室最大容纳人数" />
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
import { list教室, get教室, del教室, add教室, update教室 } from "@/api/system/教室"

export default {
  name: "教室",
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
      // 存储教室基本信息表格数据
      教室List: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        教室地址: null,
        教室容量: null,
        逻辑删除: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        教室地址: [
          { required: true, message: "教室具体位置不能为空", trigger: "blur" }
        ],
        教室容量: [
          { required: true, message: "教室最大容纳人数不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询存储教室基本信息列表 */
    getList() {
      this.loading = true
      list教室(this.queryParams).then(response => {
        this.教室List = response.rows
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
        教室编号: null,
        教室地址: null,
        教室容量: null,
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
      this.ids = selection.map(item => item.教室编号)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加存储教室基本信息"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const 教室编号 = row.教室编号 || this.ids
      get教室(教室编号).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改存储教室基本信息"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.教室编号 != null) {
            update教室(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            add教室(this.form).then(response => {
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
      const 教室编号s = row.教室编号 || this.ids
      this.$modal.confirm('是否确认删除存储教室基本信息编号为"' + 教室编号s + '"的数据项？').then(function() {
        return del教室(教室编号s)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/教室/export', {
        ...this.queryParams
      }, `教室_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
