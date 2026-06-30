<template>
  <div class="app-container home">
    <!-- 系统标题和简介 -->
    <el-row :gutter="20" class="header-section">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <h2>高校选课系统</h2>
        <p>
          本系统是专为高等院校设计的智能化教务管理平台，已实现课程管理、选课记录、用户管理、系统服务等核心功能模块。系统采用模块化设计，支持多校区、多学院分级管理，为师生提供便捷高效的线上教务服务。
        </p>
        <p>
          <b>当前版本:</b> <span>v{{ version }}</span>
        </p>
        <p>
          <el-tag type="success" effect="dark">稳定运行中</el-tag>
          <el-tag type="warning" style="margin-left: 10px;">功能持续完善中</el-tag>
        </p>
        <p style="margin-top: 20px;">
          <el-button
            type="primary"
            icon="el-icon-s-management"
            @click="goModule('/course')"
          >课程管理</el-button
          >
          <el-button
            type="success"
            icon="el-icon-s-data"
            @click="goModule('/selection')"
            style="margin-left: 10px;"
          >选课记录</el-button
          >
        </p>
      </el-col>

      <el-col :sm="24" :lg="12" class="stats-section">
        <el-row>
          <el-col :span="12">
            <h2>系统统计</h2>
          </el-col>
        </el-row>
        <el-row :gutter="20" style="margin-top: 20px;">
          <el-col :span="12">
            <div class="stat-card">
              <div class="stat-icon" style="background-color: #409EFF;">
                <i class="el-icon-s-management"></i>
              </div>
              <div class="stat-info">
                <div class="stat-number">4</div>
                <div class="stat-label">已上线模块</div>
              </div>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="stat-card">
              <div class="stat-icon" style="background-color: #67C23A;">
                <i class="el-icon-user-solid"></i>
              </div>
              <div class="stat-info">
                <div class="stat-number">{{ userCount }}</div>
                <div class="stat-label">系统用户</div>
              </div>
            </div>
          </el-col>
        </el-row>
      </el-col>
    </el-row>

    <el-divider />

    <!-- 已实现功能模块 -->
    <div class="section-title">
      <h3><i class="el-icon-success" style="color: #67C23A; margin-right: 10px;"></i>已实现功能模块</h3>
      <p>以下模块已完成开发并上线运行，点击模块卡片可进入对应管理界面</p>
    </div>

    <el-row :gutter="20" class="module-section">
      <!-- 课程管理模块 -->
      <el-col :xs="24" :sm="12" :lg="6">
        <el-card class="module-card" shadow="hover" @click.native="goModule('/course')">
          <div class="module-header">
            <div class="module-icon" style="background-color: #409EFF;">
              <i class="el-icon-s-management"></i>
            </div>
            <div class="module-title">课程管理</div>
            <el-tag size="mini" type="success" effect="plain" class="status-tag">已上线</el-tag>
          </div>
          <div class="module-content">
            <p>管理课程相关的基础信息，包括教师、教室、课程计划等</p>
            <div class="sub-modules">
              <el-tag size="mini" class="sub-module-tag">教师管理</el-tag>
              <el-tag size="mini" class="sub-module-tag">教室管理</el-tag>
              <el-tag size="mini" class="sub-module-tag">课程管理</el-tag>
              <el-tag size="mini" class="sub-module-tag">课程计划安排</el-tag>
            </div>
          </div>
          <div class="module-footer">
            <span class="module-info">最后更新: 2025-12-10</span>
            <i class="el-icon-arrow-right"></i>
          </div>
        </el-card>
      </el-col>

      <!-- 选课记录管理模块 -->
      <el-col :xs="24" :sm="12" :lg="6">
        <el-card class="module-card" shadow="hover" @click.native="goModule('/selection')">
          <div class="module-header">
            <div class="module-icon" style="background-color: #67C23A;">
              <i class="el-icon-s-data"></i>
            </div>
            <div class="module-title">选课记录管理</div>
            <el-tag size="mini" type="success" effect="plain" class="status-tag">已上线</el-tag>
          </div>
          <div class="module-content">
            <p>管理学生选课记录，查看、审核和处理选课申请</p>
            <div class="sub-modules">
              <el-tag size="mini" class="sub-module-tag">学生选课记录</el-tag>
              <el-tag size="mini" class="sub-module-tag">选课审核</el-tag>
            </div>
          </div>
          <div class="module-footer">
            <span class="module-info">最后更新: 2025-12-15</span>
            <i class="el-icon-arrow-right"></i>
          </div>
        </el-card>
      </el-col>

      <!-- 用户管理模块 -->
      <el-col :xs="24" :sm="12" :lg="6">
        <el-card class="module-card" shadow="hover" @click.native="goModule('/user')">
          <div class="module-header">
            <div class="module-icon" style="background-color: #E6A23C;">
              <i class="el-icon-user-solid"></i>
            </div>
            <div class="module-title">用户管理</div>
            <el-tag size="mini" type="success" effect="plain" class="status-tag">已上线</el-tag>
          </div>
          <div class="module-content">
            <p>管理系统用户和学生信息，分配角色和权限</p>
            <div class="sub-modules">
              <el-tag size="mini" class="sub-module-tag">用户管理</el-tag>
              <el-tag size="mini" class="sub-module-tag">学生管理</el-tag>
            </div>
          </div>
          <div class="module-footer">
            <span class="module-info">最后更新: 2025-12-05</span>
            <i class="el-icon-arrow-right"></i>
          </div>
        </el-card>
      </el-col>

      <!-- 系统服务管理模块 -->
      <el-col :xs="24" :sm="12" :lg="6">
        <el-card class="module-card" shadow="hover" @click.native="goModule('/system')">
          <div class="module-header">
            <div class="module-icon" style="background-color: #F56C6C;">
              <i class="el-icon-setting"></i>
            </div>
            <div class="module-title">系统服务管理</div>
            <el-tag size="mini" type="success" effect="plain" class="status-tag">已上线</el-tag>
          </div>
          <div class="module-content">
            <p>管理系统基础服务，包括角色、权限和操作日志</p>
            <div class="sub-modules">
              <el-tag size="mini" class="sub-module-tag">用户管理</el-tag>
              <el-tag size="mini" class="sub-module-tag">角色管理</el-tag>
              <el-tag size="mini" class="sub-module-tag">日志管理</el-tag>
            </div>
          </div>
          <div class="module-footer">
            <span class="module-info">最后更新: 2025-12-12</span>
            <i class="el-icon-arrow-right"></i>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 待开发功能提示 -->
    <el-alert
      title="📢 功能开发计划"
      type="info"
      :closable="false"
      style="margin-top: 30px;"
    >
      <div style="line-height: 1.6;">
        <p>当前已上线4个核心功能模块，其他功能正在积极开发中，预计上线时间如下：</p>
        <ul style="margin: 10px 0 0 20px;">
          <li><strong>成绩管理模块</strong> - 预计2026年1月上线</li>
          <li><strong>排课管理模块</strong> - 预计2026年2月上线</li>
          <li><strong>在线评教模块</strong> - 预计2026年3月上线</li>
          <li><strong>移动端应用</strong> - 预计2026年4月上线</li>
        </ul>
        <p style="margin-top: 10px; color: #666; font-size: 13px;">
          如有功能需求或建议，请联系系统管理员或发送邮件至：<a href="mailto:support@university.edu.cn" style="color: #409EFF;">support@university.edu.cn</a>
        </p>
      </div>
    </el-alert>

    <!-- 快速入口和系统信息 -->
    <el-row :gutter="20" style="margin-top: 40px;">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="info-card">
          <div slot="header" class="clearfix">
            <span><i class="el-icon-s-order" style="color: #409EFF; margin-right: 5px;"></i>快速入口</span>
          </div>
          <div class="body">
            <div class="quick-item" @click="goModule('/course')">
              <i class="el-icon-s-management quick-icon"></i>
              <span>课程管理</span>
            </div>
            <div class="quick-item" @click="goModule('/selection')">
              <i class="el-icon-s-data quick-icon"></i>
              <span>选课记录</span>
            </div>
            <div class="quick-item" @click="goModule('/user')">
              <i class="el-icon-user-solid quick-icon"></i>
              <span>用户管理</span>
            </div>
            <div class="quick-item" @click="goModule('/system')">
              <i class="el-icon-setting quick-icon"></i>
              <span>系统设置</span>
            </div>
          </div>
        </el-card>
      </el-col>

      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="info-card">
          <div slot="header" class="clearfix">
            <span><i class="el-icon-time" style="color: #E6A23C; margin-right: 5px;"></i>最近更新</span>
          </div>
          <div class="body">
            <div class="update-item">
              <div class="update-title">选课记录管理优化</div>
              <div class="update-time">2025-12-15</div>
              <div class="update-desc">优化了选课记录查询和导出功能</div>
            </div>
            <div class="update-item">
              <div class="update-title">课程管理功能上线</div>
              <div class="update-time">2025-12-10</div>
              <div class="update-desc">新增课程计划安排功能</div>
            </div>
            <div class="update-item">
              <div class="update-title">系统服务管理完善</div>
              <div class="update-time">2025-12-12</div>
              <div class="update-desc">新增日志管理功能</div>
            </div>
          </div>
        </el-card>
      </el-col>

      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="info-card">
          <div slot="header" class="clearfix">
            <span><i class="el-icon-info" style="color: #67C23A; margin-right: 5px;"></i>系统信息</span>
          </div>
          <div class="body">
            <div class="system-info">
              <div class="info-label">系统版本</div>
              <div class="info-value">v{{ version }}</div>
            </div>
            <div class="system-info">
              <div class="info-label">开发团队</div>
              <div class="info-value">阿阳 & 技术团队</div>
            </div>
            <div class="system-info">
              <div class="info-label">上线时间</div>
              <div class="info-value">2025-11-01</div>
            </div>
            <div class="system-info">
              <div class="info-label">服务状态</div>
              <div class="info-value">
                <el-tag size="mini" type="success" effect="plain">运行正常</el-tag>
              </div>
            </div>
            <div class="system-info">
              <div class="info-label">技术支持</div>
              <div class="info-value">
                <a href="mailto:ayang@university.edu.cn" style="color: #409EFF;">ayang@university.edu.cn</a>
              </div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "1.2.0",
      // 模拟用户数量
      userCount: 1256
    }
  },
  methods: {
    goModule(path) {
      this.$message.success(`进入${path}模块 (示例演示)`);
      // 实际项目中这里应该是路由跳转
      // this.$router.push(path);
    }
  }
}
</script>

<style scoped lang="scss">
.home {
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", Arial, sans-serif;
  color: #333;

  .header-section {
    h2 {
      margin-top: 10px;
      font-size: 28px;
      font-weight: 500;
      color: #303133;
      border-left: 4px solid #409EFF;
      padding-left: 10px;
    }

    p {
      margin-top: 10px;
      line-height: 1.6;

      b {
        font-weight: 700;
        color: #409EFF;
      }
    }
  }

  .stats-section {
    h2 {
      color: #303133;
      font-size: 24px;
      font-weight: 500;
    }

    .stat-card {
      display: flex;
      align-items: center;
      padding: 20px;
      background-color: #f8f9fa;
      border-radius: 8px;
      box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);

      .stat-icon {
        width: 60px;
        height: 60px;
        line-height: 60px;
        border-radius: 8px;
        text-align: center;
        margin-right: 15px;

        i {
          font-size: 28px;
          color: white;
        }
      }

      .stat-info {
        .stat-number {
          font-size: 28px;
          font-weight: bold;
          color: #303133;
        }

        .stat-label {
          font-size: 14px;
          color: #606266;
          margin-top: 5px;
        }
      }
    }
  }

  .section-title {
    margin-bottom: 25px;

    h3 {
      font-size: 20px;
      font-weight: 500;
      color: #303133;
      margin-bottom: 8px;
      display: flex;
      align-items: center;
    }

    p {
      color: #606266;
      font-size: 14px;
    }
  }

  .module-section {
    .module-card {
      cursor: pointer;
      height: 100%;
      transition: all 0.3s;
      border: 1px solid #ebeef5;

      &:hover {
        transform: translateY(-5px);
        box-shadow: 0 6px 18px rgba(0, 0, 0, 0.1) !important;
        border-color: #409EFF;
      }

      .module-header {
        display: flex;
        align-items: center;
        margin-bottom: 15px;

        .module-icon {
          width: 50px;
          height: 50px;
          line-height: 50px;
          border-radius: 8px;
          text-align: center;
          margin-right: 12px;

          i {
            font-size: 24px;
            color: white;
          }
        }

        .module-title {
          flex: 1;
          font-size: 18px;
          font-weight: 500;
          color: #303133;
        }

        .status-tag {
          font-size: 12px;
        }
      }

      .module-content {
        p {
          color: #606266;
          font-size: 14px;
          line-height: 1.5;
          margin-bottom: 15px;
        }

        .sub-modules {
          .sub-module-tag {
            margin-right: 8px;
            margin-bottom: 8px;
            background-color: #f0f9ff;
            color: #409EFF;
            border: 1px solid #c6e2ff;
          }
        }
      }

      .module-footer {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-top: 20px;
        padding-top: 15px;
        border-top: 1px solid #ebeef5;

        .module-info {
          font-size: 12px;
          color: #909399;
        }

        i {
          color: #409EFF;
        }
      }
    }
  }

  .info-card {
    height: 100%;

    .body {
      .quick-item {
        display: flex;
        align-items: center;
        padding: 12px 0;
        cursor: pointer;
        border-bottom: 1px solid #f0f0f0;
        transition: all 0.2s;

        &:last-child {
          border-bottom: none;
        }

        &:hover {
          background-color: #f5f7fa;
          padding-left: 10px;
          border-radius: 4px;

          .quick-icon {
            color: #409EFF;
          }
        }

        .quick-icon {
          font-size: 18px;
          margin-right: 12px;
          color: #909399;
          transition: all 0.2s;
        }

        span {
          color: #606266;
        }
      }

      .update-item {
        padding: 12px 0;
        border-bottom: 1px solid #f0f0f0;

        &:last-child {
          border-bottom: none;
        }

        .update-title {
          font-weight: 500;
          color: #303133;
          margin-bottom: 4px;
        }

        .update-time {
          font-size: 12px;
          color: #909399;
          margin-bottom: 4px;
        }

        .update-desc {
          font-size: 13px;
          color: #606266;
        }
      }

      .system-info {
        display: flex;
        justify-content: space-between;
        padding: 10px 0;
        border-bottom: 1px solid #f0f0f0;

        &:last-child {
          border-bottom: none;
        }

        .info-label {
          color: #606266;
        }

        .info-value {
          color: #303133;
          font-weight: 500;
        }
      }
    }
  }

  // 响应式调整
  @media (max-width: 768px) {
    .header-section {
      .el-col-sm-24 {
        padding-left: 0 !important;
        padding-right: 0 !important;
      }
    }

    .module-section {
      .el-col-sm-12 {
        margin-bottom: 20px;
      }
    }
  }
}
</style>
