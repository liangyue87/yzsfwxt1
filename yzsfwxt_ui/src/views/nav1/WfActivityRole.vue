<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true" :model="filters">
                <el-form-item>
                    <el-button type="primary" @click="handleAdd">新增</el-button>
                </el-form-item>
            </el-form>
        </el-col>

        <!--列表-->
        <el-table :data="ActivityRole" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">

            <el-table-column prop="wybs" label="唯一标识" width="200" sortable>
            </el-table-column>
            <el-table-column prop="activityid" label="环节编号" width="200" sortable>
            </el-table-column>
            <el-table-column prop="roleid" label="角色编号" width="200" sortable>
            </el-table-column>
            <el-table-column prop="ordinal" label="排列顺序" width="200" sortable>
            </el-table-column>
            <el-table-column label="操作" width="150">
                <template slot-scope="scope">
                    <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--工具条-->
        <el-col :span="24" class="toolbar">
            <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="this.size" :total="this.total" style="float:right;">
            </el-pagination>
        </el-col>

        <!--编辑界面-->
        <el-dialog title="编辑" :visible.sync ="editFormVisible">
            <el-form :model="editForm" label-width="80px" ref="editForm">
                <el-form-item label="唯一标识" prop="name">
                    <el-input v-model="editForm.wybs" auto-complete="off" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="环节编号" prop="name">
                    <el-input v-model="editForm.activityid" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="角色编号">
                    <el-input v-model="editForm.roleid" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="排列顺序">
                    <el-input v-model="editForm.ordinal" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="editFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
            </div>
        </el-dialog>

        <!--新增界面-->
        <el-dialog title="新增" :visible.sync ="addFormVisible">
            <el-form :model="addForm" label-width="80px" ref="addForm">
                <el-form-item label="唯一标识" prop="name">
                    <el-input v-model="addForm.wybs" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="环节编号" prop="name">
                    <el-input v-model="addForm.activityid" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="角色编号">
                    <el-input v-model="addForm.roleid" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="排列顺序">
                    <el-input v-model="addForm.ordinal" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="addFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
            </div>
        </el-dialog>
    </section>
</template>

<script>
    import util from '../../common/js/util'
    import Vue from 'vue'
    //import VueResource from 'vue-resource';
    //Vue.use(VueResource)
    //import NProgress from 'nprogress'
    import { getActivityRole, remove, save } from '../../api/WfActivityRole';

    export default {
        data() {
            return {
                filters: {
                    name: ''
                },
                ActivityRole: [],
                total: 10,//表格数据总数量
                page: 1,//当前页码
                size: 10,//每页容量
                listLoading: false,
                sels: [],//列表选中列
                editFormVisible: false,//编辑界面是否显示
                editLoading: false,
                //编辑界面数据
                editForm: {
                    wybs:'',
                    activityid: '',
                    roleid: '',
                    ordinal: ''
                },
                addFormVisible: false,//新增界面是否显示
                addLoading: false,
                //新增界面数据
                addForm: {
                    wybs:'',
                    activityid: '',
                    roleid: '',
                    ordinal: ''
                }
            }
        },
        methods: {
            handleCurrentChange(val) {
                this.page = val;
                this.getValues();
            },
            //获取用户列表
            getValues() {
                let para = {
                    page: this.page - 1,
                    size: this .size
                };
                this.listLoading = true;
                //NProgress.start();
                getActivityRole(para).then((res) => {
                    console.log(res);
                    this.total = res.data.total;
                    this.ActivityRole = res.data.row;
                    this.listLoading = false;
                    //NProgress.done();
                });
            },
            //删除
            handleDel: function (index, row) {
                console.log(row);
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = { id: row.wybs };
                    remove(para).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getValues();
                    });
                }).catch(() => {

                });
            },
            //显示编辑界面
            handleEdit: function (index, row) {
                console.log(row);
                this.editFormVisible = true;
                this.editForm = Object.assign({}, row);//编辑表单的赋值
            },
            //显示新增界面
            handleAdd: function () {
                this.addFormVisible = true;
                this.addForm = {
                    wybs:'',
                    activityid: '',
                    roleid: '',
                    ordinal: ''
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
                            save(para).then((res) => {
                                this.editLoading = false;
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['editForm'].resetFields();
                                this.editFormVisible = false;
                                this.getValues();
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
                            save(para).then((res) => {
                                this.addLoading = false;
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['addForm'].resetFields();
                                this.addFormVisible = false;
                                this.getValues();
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
                var wfids = this.sels.map(item => item.wfid).toString();
                this.$confirm('确认删除选中记录吗？', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = { wfids: wfids };
                    batchRemoveBank(para).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getValues();
                    });
                }).catch(() => {

                });
            }
        },
        mounted() {
            this.getValues();
        }
    }

</script>

<style scoped>

</style>
