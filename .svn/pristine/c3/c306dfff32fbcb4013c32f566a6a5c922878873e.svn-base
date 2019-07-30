<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true" :model="filters">
                <el-form-item label="环节名称">
                    <el-input v-model="filters.name"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" v-on:click="getValues">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleAdd">新增</el-button>
                </el-form-item>
            </el-form>
        </el-col>

        <!--列表-->
        <el-table :data="DfActivity" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
            <el-table-column type="index" width="60">
            </el-table-column>
            <el-table-column prop="activityid" label="环节编号" width="100" sortable>
            </el-table-column>
            <el-table-column prop="activityname" label="环节名称" width="100" sortable>
            </el-table-column>
            <el-table-column prop="wfid" label="流程编号" width="100" sortable>
            </el-table-column>
            <el-table-column prop="formid" label="表单编号" width="100" sortable>
            </el-table-column>
            <el-table-column prop="bz" label="备注" width="100" sortable>
            </el-table-column>
            <el-table-column prop="checkMinute" label="签收时限" width="100" sortable>
            </el-table-column>
            <el-table-column prop="finishMinute" label="完成时限" width="100" sortable>
            </el-table-column>
            <el-table-column prop="delay" label="显示延期按扭" width="100" sortable>
            </el-table-column>
            <el-table-column prop="yellowFinish" label="黄灯时限" width="100" sortable>
            </el-table-column>
            <el-table-column prop="checkLimited" label="限制签收时间" width="100" sortable>
            </el-table-column>
            <el-table-column prop="finishLimited" label="限制截止时间" width="100" sortable>
            </el-table-column>
            <el-table-column prop="defaultCzr" label="默认操作人" width="100" sortable>
            </el-table-column>
            <el-table-column prop="bjjsgz" label="办结计时规则" width="100" sortable>
            </el-table-column>
            <el-table-column prop="flag" label="启用标识" width="100" sortable>
            </el-table-column>
            <el-table-column prop="type" label="分类" width="100" sortable>
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
            <el-form :model="editForm" label-width="100px" ref="editForm"  size="small" inline="true">
                <el-form-item label="环节编号" prop="name">
                    <el-input v-model="editForm.activityid" auto-complete="off" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="环节名称">
                    <el-input v-model="editForm.activityname" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="流程编号">
                    <el-input v-model="editForm.wfid" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="表单编号">
                    <el-input v-model="editForm.formid" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="editForm.bz" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="签收时限">
                    <el-input v-model="editForm.checkMinute" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="完成时限">
                    <el-input v-model="editForm.finishMinute" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="显示延期按扭">
                    <el-input v-model="editForm.delay" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="黄灯时限">
                    <el-input v-model="editForm.yellowFinish" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="限制签收时间">
                    <el-input v-model="editForm.checkLimited" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="限制截止时间">
                    <el-input v-model="editForm.finishLimited" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="默认操作人">
                    <el-input v-model="editForm.defaultCzr" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="办结计时规则">
                    <el-input v-model="editForm.bjjsgz" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="启用标识">
                    <el-input v-model="editForm.flag" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="分类">
                    <el-input v-model="editForm.type" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="editFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
            </div>
        </el-dialog>

        <!--新增界面-->
        <el-dialog title="新增" :visible.sync ="addFormVisible">
            <el-form :model="addForm" label-width="100px" ref="addForm" size="small" inline="true">
                <el-form-item label="环节编号" prop="name">
                    <el-input v-model="addForm.activityid" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="环节名称">
                    <el-input v-model="addForm.activityname" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="流程编号">
                    <el-input v-model="addForm.wfid" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="表单编号">
                    <el-input v-model="addForm.formid" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="addForm.bz" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="签收时限">
                    <el-input v-model="addForm.checkMinute" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="完成时限">
                    <el-input v-model="addForm.finishMinute" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="显示延期按扭">
                    <el-input v-model="addForm.delay" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="黄灯时限">
                    <el-input v-model="addForm.yellowFinish" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="限制签收时间">
                    <el-input v-model="addForm.checkLimited" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="限制截止时间">
                    <el-input v-model="addForm.finishLimited" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="默认操作人">
                    <el-input v-model="addForm.defaultCzr" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="办结计时规则">
                    <el-input v-model="addForm.bjjsgz" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="启用标识">
                    <el-input v-model="addForm.flag" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="分类">
                    <el-input v-model="addForm.type" auto-complete="off"></el-input>
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
    import { getDfActivity, remove, save } from '../../api/WfDfActivity';

    export default {
        data() {
            return {
                filters: {
                    name: ''
                },
                DfActivity: [],
                total: 10,//表格数据总数量
                page: 1,//当前页码
                size: 10,//每页容量
                listLoading: false,
                sels: [],//列表选中列
                editFormVisible: false,//编辑界面是否显示
                editLoading: false,
                //编辑界面数据
                editForm: {
                    activityid: '',
                    activityname: '',
                    wfid: '',
                    formid: '',
                    bz: '',
                    checkMinute: '',
                    finishMinute: '',
                    delay: '',
                    yellowFinish: '',
                    checkLimited: '',
                    finishLimited: '',
                    defaultCzr: '',
                    bjjsgz: '',
                    flag: '',
                    type: ''
                },
                addFormVisible: false,//新增界面是否显示
                addLoading: false,
                //新增界面数据
                addForm: {
                    activityid: '',
                    activityname: '',
                    wfid: '',
                    formid: '',
                    bz: '',
                    checkMinute: '',
                    finishMinute: '',
                    delay: '',
                    yellowFinish: '',
                    checkLimited: '',
                    finishLimited: '',
                    defaultCzr: '',
                    bjjsgz: '',
                    flag: '',
                    type: ''
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
                    size: this .size,
                    activityname: this.name
                };
                this.listLoading = true;
                //NProgress.start();
                getDfActivity(para).then((res) => {
                    this.total = res.data.total;
                    this.DfActivity = res.data.row;
                    this.listLoading = false;
                    //NProgress.done();
                });
            },
            //删除
            handleDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = { activityid: row.activityid };
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
                this.editFormVisible = true;
                this.editForm = Object.assign({}, row);//编辑表单的赋值
            },
            //显示新增界面
            handleAdd: function () {
                this.addFormVisible = true;
                this.addForm = {
                    activityid: '',
                    activityname: '',
                    wfid: '',
                    formid: '',
                    bz: '',
                    checkMinute: '',
                    finishMinute: '',
                    delay: '',
                    yellowFinish: '',
                    checkLimited: '',
                    finishLimited: '',
                    defaultCzr: '',
                    bjjsgz: '',
                    flag: '',
                    type: ''
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
