<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="成绩编号" prop="gradeId">
        <el-input
          v-model="queryParams.gradeId"
          placeholder="请输入成绩编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课程名称" prop="courseId" v-if="isCourseQuery">
        <el-select
          v-model="queryParams.courseId"
          filterable
          remote
          reserve-keyword
          placeholder="请输入课程名称"
          :remote-method="selectCourseInfoList"
          :loading="courseLoading"
        >
          <el-option
            v-for="item in courseInfoList"
            :key="item.courseId"
            :label="item.courseName"
            :value="item.courseId"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="学生" prop="userId" v-if="isUserQuery">
        <el-select
          v-model="queryParams.userId"
          filterable
          remote
          reserve-keyword
          placeholder="请输入用户账号"
          :remote-method="selectUserInfoList"
          :loading="userLoading"
        >
          <el-option
            v-for="item in userInfoList"
            :key="item.userId"
            :label="item.userName"
            :value="item.userId"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="创建人" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入创建人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreateTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <!--      <el-form-item label="更新人" prop="updateBy">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.updateBy"-->
      <!--          placeholder="请输入更新人"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="更新时间">-->
      <!--        <el-date-picker-->
      <!--          v-model="daterangeUpdateTime"-->
      <!--          style="width: 240px"-->
      <!--          value-format="yyyy-MM-dd"-->
      <!--          type="daterange"-->
      <!--          range-separator="-"-->
      <!--          start-placeholder="开始日期"-->
      <!--          end-placeholder="结束日期"-->
      <!--        ></el-date-picker>-->
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
          v-hasPermi="['manage:gradeInfo:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['manage:gradeInfo:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['manage:gradeInfo:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['manage:gradeInfo:import']"
        >导入
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['manage:gradeInfo:export']"
        >导出
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          size="mini"
          v-hasPermi="['manage:gradeInfo:list']"
        >平均分:{{ statics.avgScore }}
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          size="mini"
          v-hasPermi="['manage:gradeInfo:list']"
        >最高分：{{ statics.maxScore }}
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          size="mini"
          v-hasPermi="['manage:gradeInfo:list']"
        >最低分：{{ statics.minScore }}
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="gradeInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="成绩编号" align="center" v-if="columns[0].visible" prop="gradeId"/>
      <el-table-column label="课程名称" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible"
                       prop="courseName"
      />
      <el-table-column label="学生成绩" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible"
                       prop="score"
      />
      <el-table-column label="成绩描述" :show-overflow-tooltip="true" align="center" v-if="columns[3].visible"
                       prop="gradeDesc"
      />
      <el-table-column label="学生" :show-overflow-tooltip="true" align="center" v-if="columns[4].visible"
                       prop="userName"
      />
      <el-table-column label="创建人" :show-overflow-tooltip="true" align="center" v-if="columns[5].visible"
                       prop="createBy"
      />
      <el-table-column label="创建时间" align="center" v-if="columns[6].visible" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人" :show-overflow-tooltip="true" align="center" v-if="columns[7].visible"
                       prop="updateBy"
      />
      <el-table-column label="更新时间" align="center" v-if="columns[8].visible" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" :show-overflow-tooltip="true" align="center" v-if="columns[9].visible"
                       prop="remark"
      />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manage:gradeInfo:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manage:gradeInfo:remove']"
          >删除
          </el-button>
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

    <!-- 添加或修改学生成绩信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课程名称" prop="courseId" v-if="isCourseQuery">
          <el-select
            v-model="form.courseId"
            filterable
            remote
            reserve-keyword
            placeholder="请输入课程名称"
            :remote-method="selectCourseInfoList"
            :loading="courseLoading"
          >
            <el-option
              v-for="item in courseInfoList"
              :key="item.courseId"
              :label="item.courseName"
              :value="item.courseId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学生成绩" prop="score">
          <el-input-number :min="0" :max="100" v-model="form.score" placeholder="请输入学生成绩"/>
        </el-form-item>
        <el-form-item label="成绩描述" prop="gradeDesc">
          <el-input v-model="form.gradeDesc" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="学生" prop="userId" v-if="isUserQuery">
          <el-select
            v-model="form.userId"
            filterable
            remote
            reserve-keyword
            placeholder="请输入用户账号"
            :remote-method="selectUserInfoList"
            :loading="userLoading"
          >
            <el-option
              v-for="item in userInfoList"
              :key="item.userId"
              :label="item.userName"
              :value="item.userId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 学生档案导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px">
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" slot="tip">
          <el-link type="info" style="font-size:12px" @click="importTemplate">下载模板</el-link>
        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listGradeInfo,
  getGradeInfo,
  delGradeInfo,
  addGradeInfo,
  updateGradeInfo,
  staticsGradeInfo
} from '@/api/manage/gradeInfo'
import { checkPermi } from '@/utils/permission'
import { allocatedUserList } from '@/api/system/role'
import { listCourseInfo } from '@/api/manage/courseInfo'
import { getToken } from '@/utils/auth'

export default {
  name: 'GradeInfo',
  data() {
    return {
      statics: {
        minScore: 0,
        maxScore: 100,
        avgScore: 0
      },
      isCourseQuery: false,
      //课程相关信息
      courseInfoList: [],
      courseLoading: false,
      courseQueryParams: {
        courseName: '',
        roleId: 100,
        pageNum: 1,
        pageSize: 100
      },
      isUserQuery: false,
      //用户相关信息
      userInfoList: [],
      userLoading: false,
      userQueryParams: {
        userName: '',
        roleId: 100,
        pageNum: 1,
        pageSize: 100
      },
      //表格展示列
      columns: [
        { key: 0, label: '成绩编号', visible: false },
        { key: 1, label: '课程名称', visible: true },
        { key: 2, label: '学生成绩', visible: true },
        { key: 3, label: '成绩描述', visible: true },
        { key: 4, label: '学生', visible: true },
        { key: 5, label: '创建人', visible: true },
        { key: 6, label: '创建时间', visible: true },
        { key: 7, label: '更新人', visible: false },
        { key: 8, label: '更新时间', visible: false },
        { key: 9, label: '备注', visible: false }
      ],
      // 遮罩层
      loading:
        true,
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
      // 学生成绩信息表格数据
      gradeInfoList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 备注时间范围
      daterangeCreateTime: [],
      // 备注时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        gradeId: null,
        courseId: null,
        gradeDesc: null,
        userId: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      }
      ,
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        courseId: [
          { required: true, message: '课程名称不能为空', trigger: 'blur' }
        ],
        score: [
          { required: true, message: '学生成绩不能为空', trigger: 'blur' }
        ],
        userId: [
          { required: true, message: '学生不能为空', trigger: 'blur' }
        ],
        createBy: [
          { required: true, message: '创建人不能为空', trigger: 'blur' }
        ],
        createTime: [
          { required: true, message: '创建时间不能为空', trigger: 'blur' }
        ]
      },
      // 导入参数
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: '',
        // 是否禁用上传
        isUploading: false,
        // 设置上传的请求头部
        headers: { Authorization: 'Bearer ' + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + '/manage/gradeInfo/importData'
      }
    }
  },
  created() {
    this.getList()
    if (checkPermi(['manage:gradeInfo:add'])) {
      this.isUserQuery = true
      this.getUserInfoList()
      this.isCourseQuery = true
      this.getCourseInfoList()
    }
  },
  methods: {
    checkPermi,
    /**
     * 获取课程列表推荐
     * @param query
     */
    selectCourseInfoList(query) {
      if (query !== '') {
        this.courseLoading = true
        this.courseQueryParams.courseName = query
        setTimeout(() => {
          this.getCourseInfoList()
        }, 200)
      } else {
        this.courseInfoList = []
        this.courseQueryParams.courseName = null
      }
    },
    /**
     * 获取课程信息列表
     */
    getCourseInfoList() {
      //添加查询参数
      if (this.form.courseId != null) {
        this.courseQueryParams.courseId = this.form.courseId
      } else {
        this.courseQueryParams.courseId = null
      }
      if (this.courseQueryParams.courseName !== '') {
        this.courseQueryParams.courseId = null
      }
      this.courseInfoList = []
      listCourseInfo(this.courseQueryParams).then(res => {
        this.courseInfoList = res?.rows
        this.courseLoading = false
      })
    }, /**
     * 获取学生用户列表推荐
     * @param query
     */
    selectUserInfoList(query) {
      if (query !== '') {
        this.userLoading = true
        this.userQueryParams.userName = query
        setTimeout(() => {
          this.getUserInfoList()
        }, 200)
      } else {
        this.userInfoList = []
        this.userQueryParams.userName = null
      }
    },
    /**
     * 获取学生用户信息列表
     */
    getUserInfoList() {
      //添加查询参数
      if (this.form.userId != null) {
        this.userQueryParams.userId = this.form.userId
      } else {
        this.userQueryParams.userId = null
      }
      if (this.userQueryParams.userName !== '') {
        this.userQueryParams.userId = null
      }
      allocatedUserList(this.userQueryParams).then(res => {
        this.userInfoList = res?.rows
        this.userLoading = false
      })
    },
    /** 查询学生成绩信息列表 */
    getList() {
      this.loading = true
      this.queryParams.params = {}
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params['beginCreateTime'] = this.daterangeCreateTime[0]
        this.queryParams.params['endCreateTime'] = this.daterangeCreateTime[1]
      }
      if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
        this.queryParams.params['beginUpdateTime'] = this.daterangeUpdateTime[0]
        this.queryParams.params['endUpdateTime'] = this.daterangeUpdateTime[1]
      }
      listGradeInfo(this.queryParams).then(response => {
        this.gradeInfoList = response.rows
        this.total = response.total
        this.loading = false
      })
      staticsGradeInfo(this.queryParams).then(res => {
        this.statics = res.data
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
        gradeId: null,
        courseId: null,
        score: null,
        gradeDesc: null,
        userId: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCreateTime = []
      this.daterangeUpdateTime = []
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.gradeId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加学生成绩信息'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const gradeId = row.gradeId || this.ids
      getGradeInfo(gradeId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改学生成绩信息'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.gradeId != null) {
            updateGradeInfo(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addGradeInfo(this.form).then(response => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const gradeIds = row.gradeId || this.ids
      this.$modal.confirm('是否确认删除学生成绩信息编号为"' + gradeIds + '"的数据项？').then(function() {
        return delGradeInfo(gradeIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manage/gradeInfo/export', {
        ...this.queryParams
      }, `gradeInfo_${new Date().getTime()}.xlsx`)
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = '学生成绩导入'
      this.upload.open = true
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('manage/gradeInfo/importTemplate', {}, `gradeInfo_template_${new Date().getTime()}.xlsx`)
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false
      this.upload.isUploading = false
      this.$refs.upload.clearFiles()
      this.$alert(response.msg, '导入结果', { dangerouslyUseHTMLString: true })
      this.getList()
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit()
    }
  }
}
</script>
