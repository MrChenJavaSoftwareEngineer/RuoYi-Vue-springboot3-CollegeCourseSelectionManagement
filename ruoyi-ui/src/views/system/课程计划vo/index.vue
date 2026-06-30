<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="类型" prop="类型">
        <el-input
          v-model="queryParams.类型"
          placeholder="请输入课程计划类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始节次" prop="当天开始节次">
        <el-input
          v-model="queryParams.当天开始节次"
          placeholder="请输入当天课程开始节次"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="结束节次" prop="当天结束节次">
        <el-input
          v-model="queryParams.当天结束节次"
          placeholder="请输入当天课程结束节次"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上课时间" prop="周内时间">
        <el-input
          v-model="queryParams.周内时间"
          placeholder="请输入周内上课时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="结束日期" prop="结课时间">
        <el-date-picker clearable
          v-model="queryParams.结课时间"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择课程计划结束日期">
        </el-date-picker>
      </el-form-item>
<!--      <el-form-item label="软删除标记：0-未删除，1-已删除" prop="逻辑删除">-->
<!--        <el-input-->
<!--          v-model="queryParams.逻辑删除"-->
<!--          placeholder="请输入软删除标记：0-未删除，1-已删除"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="课程名称" prop="课程名称">
        <el-input
          v-model="queryParams.课程名称"
          placeholder="请输入课程名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="教室地址" prop="教室地址">
        <el-input
          v-model="queryParams.教室地址"
          placeholder="请输入教室具体地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="授课老师" prop="授课老师">
        <el-input
          v-model="queryParams.授课老师"
          placeholder="请输入授课老师"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

<!--    <el-row :gutter="10" class="mb8">-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['system:课程计划vo:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['system:课程计划vo:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['system:课程计划vo:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['system:课程计划vo:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->
<!--      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>-->
<!--    </el-row>-->

    <el-table v-loading="loading" :data="课程计划voList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="类型" align="center" prop="类型" />
      <el-table-column label="开始节次" align="center" prop="当天开始节次" />
      <el-table-column label="结束节次" align="center" prop="当天结束节次" />
      <el-table-column label="上课时间" align="center" prop="周内时间" />
      <el-table-column label="结束日期" align="center" prop="结课时间" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.结课时间, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="课程名称" align="center" prop="课程名称" />
      <el-table-column label="教室地址" align="center" prop="教室地址" />
      <el-table-column label="授课老师" align="center" prop="授课老师" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:课程计划vo:remove']"
          >选课</el-button>
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

    <!-- 添加或修改存储课程计划安排对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="类型" prop="类型">
          <el-input v-model="form.类型" placeholder="请输入课程计划类型" />
        </el-form-item>
        <el-form-item label="开始节次" prop="当天开始节次">
          <el-input v-model="form.当天开始节次" placeholder="请输入当天课程开始节次" />
        </el-form-item>
        <el-form-item label="结束节次" prop="当天结束节次">
          <el-input v-model="form.当天结束节次" placeholder="请输入当天课程结束节次" />
        </el-form-item>
        <el-form-item label="上课时间" prop="周内时间">
          <el-input v-model="form.周内时间" placeholder="请输入周内上课时间" />
        </el-form-item>
        <el-form-item label="结束日期" prop="结课时间">
          <el-date-picker clearable
            v-model="form.结课时间"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择课程计划结束日期">
          </el-date-picker>
        </el-form-item>
<!--        <el-form-item label="软删除标记：0-未删除，1-已删除" prop="逻辑删除">-->
<!--          <el-input v-model="form.逻辑删除" placeholder="请输入软删除标记：0-未删除，1-已删除" />-->
<!--        </el-form-item>-->
        <el-form-item label="课程名称" prop="课程名称">
          <el-input v-model="form.课程名称" placeholder="请输入课程名称" />
        </el-form-item>
        <el-form-item label="教室地址" prop="教室地址">
          <el-input v-model="form.教室地址" placeholder="请输入教室具体地址" />
        </el-form-item>
        <el-form-item label="授课老师" prop="授课老师">
          <el-input v-model="form.授课老师" placeholder="请输入授课老师" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { list课程计划vo, get课程计划vo, del课程计划vo, add课程计划vo, update课程计划vo } from "@/api/system/课程计划vo"
import {parseTime} from "../../../utils/ruoyi";

export default {
  name: "课程计划vo",
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
      // 存储课程计划安排表格数据
      课程计划voList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        类型: null,
        当天开始节次: null,
        当天结束节次: null,
        周内时间: null,
        结课时间: null,
        逻辑删除: null,
        课程名称: null,
        教室地址: null,
        授课老师: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        类型: [
          { required: true, message: "课程计划类型不能为空", trigger: "blur" }
        ],
        当天开始节次: [
          { required: true, message: "当天课程开始节次不能为空", trigger: "blur" }
        ],
        当天结束节次: [
          { required: true, message: "当天课程结束节次不能为空", trigger: "blur" }
        ],
        周内时间: [
          { required: true, message: "周内上课时间不能为空", trigger: "blur" }
        ],
        结课时间: [
          { required: true, message: "课程计划结束日期不能为空", trigger: "blur" }
        ],
        课程名称: [
          { required: true, message: "课程名称不能为空", trigger: "blur" }
        ],
        教室地址: [
          { required: true, message: "教室具体地址不能为空", trigger: "blur" }
        ],
        授课老师: [
          { required: true, message: "授课老师不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    parseTime,
    /** 查询存储课程计划安排列表 */
    getList() {
      this.loading = true
      list课程计划vo(this.queryParams).then(response => {
        this.课程计划voList = response.rows
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
        课程计划编号: null,
        类型: null,
        当天开始节次: null,
        当天结束节次: null,
        周内时间: null,
        结课时间: null,
        逻辑删除: null,
        课程名称: null,
        教室地址: null,
        授课老师: null
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
      this.ids = selection.map(item => item.课程计划编号)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加存储课程计划安排"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const 课程计划编号 = row.课程计划编号 || this.ids
      get课程计划vo(课程计划编号).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改存储课程计划安排"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.课程计划编号 != null) {
            update课程计划vo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            add课程计划vo(this.form).then(response => {
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
      const 课程计划编号s = row.课程计划编号 || this.ids
      this.$modal.confirm('是否确认选择该课程？').then(function() {
        return del课程计划vo(课程计划编号s)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("选课成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/课程计划vo/export', {
        ...this.queryParams
      }, `课程计划vo_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
