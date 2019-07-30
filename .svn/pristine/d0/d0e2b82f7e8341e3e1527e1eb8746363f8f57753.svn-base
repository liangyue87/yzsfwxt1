<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
    <section>
        <el-row :gutter="10">
            <!--工具条-->
            <el-col :span="24" class="toolbar" >
                <el-form :inline="true" :model="filters">
                    <el-form-item label="年份">
                        <el-input v-model="filters.year" placeholder="年份"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" v-on:click="xtgl_jjr">查询</el-button>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="handleAdd">新增</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
            <el-col :span="24" class="toolbar" >
                <!--列表-->
                <el-table :data="JjrList" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
                    <el-table-column type="selection" width="55">
                    </el-table-column>
                    <el-table-column type="index" width="60">
                    </el-table-column>
                    <el-table-column prop="id" label="id" width="100" v-if="show" >
                    </el-table-column>
                    <el-table-column prop="year" label="年份" width="100" sortable>
                    </el-table-column>
                    <el-table-column prop="date1" label="日期" width="100" sortable>
                    </el-table-column>
                    <el-table-column label="操作" width="150">
                        <template slot-scope="scope">
                            <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                            <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-col>
            <el-col :span="24" class="toolbar" >
                <!--工具条-->
                <el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>
                <el-pagination layout="total, prev, pager, next"  @current-change="handleCurrentChange" :page-size="this.size" :total="this.total" style="float:right;">
                </el-pagination>
            </el-col>

        </el-row>
        <!--编辑界面-->
        <el-dialog title="编辑" :visible.sync ="editFormVisible">
            <el-form :model="editForm" label-width="100px" :rules="editFormRules" ref="editForm" size="small" :inline="true">
                <el-form-item label="id" prop="id" v-if="show">
                    <el-input v-model="editForm.id" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="年份" prop="year">
                    <el-input v-model="editForm.year" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="月-日" prop="date1">
                    <el-input v-model="editForm.date1" auto-complete="off"></el-input>
                </el-form-item>
                <!-- <el-form-item label="类型" prop="lx">
                     <el-select v-model="editForm.lx" @change="getMbByLx(value)">
                         <el-option v-for="item in mblxCounts"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value"></el-option>
                     </el-select>
                 </el-form-item>-->
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="editFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
            </div>
        </el-dialog>

        <!--新增界面-->
        <el-dialog title="新增" :visible.sync ="addFormVisible" @close = "editClose">
            <el-form :model="addForm" label-width="100px" :rules="addFormRules" ref="addForm" size="small" :inline="true">
                <el-form-item label="年份" prop="name">
                    <el-input v-model="addForm.year" auto-complete="off" placeholder="XXXX"></el-input>
                </el-form-item>
                <el-form-item label="月-日" prop="name" >
                    <el-input v-model="addForm.date1" auto-complete="off" placeholder="XX -- XX"></el-input>
                </el-form-item>
                <!--<el-form-item label="类型" prop="lx">
                    <el-select v-model="addForm.lx" @change="getMbByLx(value)">
                       <el-option label="1" value="1"></el-option>
                        <el-option label="2" value="2"></el-option>
                    </el-select>
                </el-form-item>-->
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="addFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
            </div>
        </el-dialog>
    </section>
</template>

<script>
    import util from '../../common/js/util';
    import Vue from 'vue';
    import { getJjr, remove, saveJjr} from '../../api/Jjrdy';
    export default {
        data() {
            return {
                filters: {
                    year: '',
                    date1:'',
                    id:''
                },

                JjrList: [],
                total: 10,//表格数据总数量
                page: 1,//当前页码
                size: 10,//每页容量
                listLoading: false,
                sels: [],//列表选中列

                editFormVisible: false,//编辑界面是否显示
                editLoading: false,
                editFormRules: {
                    pdefId: [
                        { required: true, message: '请输入流程定义ID', trigger: 'blur' }
                    ]
                },
                //编辑界面数据
                editForm: {
                    year: '',
                    date1:'',
                    id:''
                },

                addFormVisible: false,//新增界面是否显示
                addLoading: false,
                addFormRules: {
                    pdefId: [
                        { required: true, message: '请输入流程定义ID', trigger: 'blur' }
                    ]
                },
                //新增界面数据
                addForm: {
                    year: '',
                    date1:'',
                    id:''
                }
            }
        },
        methods: {
            //显示转换
            formatIslocked : function (row, column) {
                return row.islocked == '0' ? '正常' : row.islocked != '0' ? '冻结' : '未知';
            },
            handleCurrentChange(val) {
                this.page = val;
                this.xtgl_jjr();
            },
            //获取用户列表
            xtgl_jjr() {
                let para = {
                    page: this.page - 1,
                    size: this .size,
                    year: this.filters.year,
                    date1: this.filters.date1,
                    id:this.filters.id
                };
                this.listLoading = true;
                getJjr(para).then((res) => {
                    this.total = res.data.total;
                    this.JjrList = res.data.row;
                    this.listLoading = false;
                });
            },
            //删除
            handleDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    //this.listLoading = true;
                    //NProgress.start();
                    let para = { id: row.id };
                    remove(para).then((res) => {
                        //this.listLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.xtgl_jjr();
                    });
                });
            },
            //显示编辑界面
            handleEdit: function (index, row) {
                this.editFormVisible = true;
                this.editForm = Object.assign({}, row);//编辑表单的赋值
                //this.editForm.islocked = parseInt(row.islocked);//性别赋值
            },
            //显示新增界面
            handleAdd: function () {
                this.addFormVisible = true;
                this.addForm = {
                    year: '',
                    date1: '',
                    id: ''
                };
            },
            //编辑
            editSubmit: function () {
                this.$refs.editForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.editLoading = true;
                            //NProgress.start();
                            let para = Object.assign({}, this.editForm);
                            //para.birth = (!para.birth || para.birth == '') ? '' : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd');
                            saveJjr(para).then((res) => {
                                this.editLoading = false;
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['editForm'].resetFields();
                                this.editFormVisible = false;
                                this.xtgl_jjr();
                            });
                        });
                    }
                });
            },
            //新增
            addSubmit: function () {
                this.$refs.addForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.addLoading = true;
                            //NProgress.start();
                            let para = Object.assign({}, this.addForm);
                           // para.birth = (!para.birth || para.birth == '') ? '' : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd');
                            saveJjr(para).then((res) => {
                                this.addLoading = false;
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['addForm'].resetFields();
                                this.addFormVisible = false;
                                this.xtgl_jjr();
                            });
                        });
                    }
                });
            },
            selsChange: function (sels) {
                this.sels = sels;
            },
            //批量删除
            batchRemove: function () {
                var ids = this.sels.map(item => item.id).toString();
                this.$confirm('确认删除选中记录吗？', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = { ids: ids };
                    batchRemoveUser(para).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.xtgl_jjr();
                    });
                }).catch(() => {

                });
            }
        },
        mounted() {
            this.xtgl_jjr();
        }
    }

</script>

<style scoped>

</style>
