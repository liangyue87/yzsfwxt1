<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true" :model="filters">
                <el-form-item>
                    <el-select v-model="value" size="small" @change="getBankMaxNo(value)">
                        <el-option v-for="item in options"
                                   :key="item.VALUE"
                                   :label="item.LABEL"
                                   :value="item.VALUE"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" size="mini" v-on:click="getBanks"><i class="iconfont icon-chaxun"></i>查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" size="mini" @click="handleAdd"><i class="iconfont icon-xinzeng"></i>新增</el-button>
                </el-form-item>
            </el-form>
        </el-col>

        <!--列表-->
        <el-table :data="banks" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
            <!--<el-table-column type="selection" width="55">
            </el-table-column>-->
            <el-table-column type="index" label="序号" width="100">
            </el-table-column>
            <el-table-column prop="bh" label="支行编码" width="180" sortable>
            </el-table-column>
            <el-table-column prop="mc" label="支行名称" width="300" sortable>
            </el-table-column>
            <el-table-column prop="yhbh" label="银行编码" width="200" sortable>
            </el-table-column>
            <el-table-column prop="yhmc" label="银行名称" min-width="300" sortable>
            </el-table-column>
            <el-table-column label="操作" width="150">
                <template slot-scope="scope">
                    <label title="编辑" @click="handleEdit(scope.$index, scope.row)">
                        <i class="iconfont icon-ai-edit" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                    <label title="删除" @click="handleDel(scope.$index, scope.row)">
                        <i class="iconfont delete" style="cursor:pointer;padding: 2px;color: #f78989"></i></label>
                    <!--<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>-->
                </template>
            </el-table-column>
        </el-table>

        <!--工具条-->
        <el-col :span="24" class="toolbar">
            <!--<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>-->
            <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="this.size" :total="this.total" style="float:right;">
            </el-pagination>
        </el-col>

        <!--编辑界面-->
        <el-dialog title="编辑" :visible.sync ="editFormVisible">
            <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
                <el-form-item label="支行编码" prop="name">
                    <el-input v-model="editForm.bh" auto-complete="off" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="支行名称">
                    <el-input v-model="editForm.mc" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="银行编码">
                    <el-input v-model="editForm.yhbh" auto-complete="off" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="银行名称">
                    <el-input v-model="editForm.yhmc" :disabled="true"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="info" size="mini" @click.native="editFormVisible = false"><i class="iconfont icon-quxiao"></i>取消</el-button>
                <el-button type="primary" size="mini" @click.native="editSubmit" :loading="editLoading"><i class="iconfont icon-queren"></i>提交</el-button>
                <!--<el-button @click.native="editFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>-->
            </div>
        </el-dialog>

        <!--新增界面-->
        <el-dialog title="新增" :visible.sync ="addFormVisible">
            <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
                <el-form-item label="支行编码" prop="name">
                    <el-input v-model="addForm.bh" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="支行名称">
                    <el-input v-model="addForm.mc" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="银行编码">
                    <el-input v-model="addForm.yhbh" auto-complete="off" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="银行名称">
                    <el-input v-model="addForm.yhmc" :disabled="true"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer" style="text-align: center">
                <el-button type="info" size="mini" @click.native="addFormVisible = false"><i class="iconfont icon-quxiao"></i>取消</el-button>
                <el-button type="primary" size="mini" @click.native="addSubmit" :loading="addLoading"><i class="iconfont icon-queren"></i>提交</el-button>
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
    import { getBankList, remove, addBank,getBankNo,editBank,batchRemoveBank } from '../../api/bank';

    export default {
        data() {
            return {
                filters: {
                    mc: ''
                },
                banks: [],
                total: 10,//表格数据总数量
                page: 1,//当前页码
                size: 10,//每页容量
                search_yhmc: '建设银行',
                listLoading: false,
                sels: [],//列表选中列

                editFormVisible: false,//编辑界面是否显示
                editLoading: false,
                editFormRules: {
                    mc: [
                        { required: true, message: '请输入支行名称', trigger: 'blur' }
                    ]
                },
                //编辑界面数据
                editForm: {
                    bh: '',
                    mc: '',
                    yhbh: '',
                    yhmc: ''
                },

                addFormVisible: false,//新增界面是否显示
                addLoading: false,
                addFormRules: {
                    mc: [
                        { required: true, message: '请输入支行名称', trigger: 'blur' }
                    ]
                },
                //新增界面数据
                addForm: {
                    bh: '',
                    mc: '',
                    yhbh: '1',
                    yhmc: ''
                },
                options: '',
                value: '0305_建设银行',  // 选取默认值在里面就可以了，比如：  value:"选项2"；
                zhbh:''
            }
        },
        methods: {
            handleCurrentChange(val) {
                this.page = val;
                this.getBanks();
            },
            //获取用户列表
            getBanks() {
                let para = {
                    page: this.page - 1,
                    size: this .size,
                    search_yhmc: this.value.split("_")[1]
                    //page:0,
                    //name: this.filters.name
                    //size:10
                };
                this.listLoading = true;
                //NProgress.start();
                getBankList(para).then((res) => {
                    this.total = res.data.total;
                    this.banks = res.data.banks;
                    this.options = res.data.opvalue;
                    this.listLoading = false;
                    //NProgress.done();
                });
            },
            //根据银行编码获取支行编码
            getBankMaxNo(value) {
                let para = {
                    yhbh: this.value.split("_")[0],
                    yhmc: this.value.split("_")[1]
                };
                getBankNo(para).then((res) => {
                    this.zhbh = res.data.bh;
                    this.page = 1;
                    this.size = 10;
                    this.total = 10;
                });
            },
            //删除
            handleDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = { bh: row.bh };
                    remove(para).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getBanks();
                    });
                }).catch(() => {

                });
            },
            //显示编辑界面
            handleEdit: function (index, row) {
                this.editFormVisible = true;
                this.editForm = Object.assign({}, row);//编辑表单的赋值
                this.editForm.bh = row.bh;
                this.editForm.mc = row.mc;
                this.editForm.yhbh = row.yhbh;
                this.editForm.yhmc = row.yhmc;
            },
            //显示新增界面
            handleAdd: function () {
                this.addFormVisible = true;
                this.addForm = {
                    bh: this.zhbh,
                    mc: '',
                    yhbh: this.value.split("_")[0],
                    yhmc: this.value.split("_")[1]
                };
            },
            //编辑
            editSubmit: function () {
                this.$refs.editForm.validate((valid) => {
                    if (valid) {
                        //this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.editLoading = true;
                            //NProgress.start();
                            let para = Object.assign({}, this.editForm);
                            editBank(para).then((res) => {
                                this.editLoading = false;
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['editForm'].resetFields();
                                this.editFormVisible = false;
                                this.getBanks();
                            });
                        //});
                    }
                });
            },
            //新增
            addSubmit: function () {
                this.$refs.addForm.validate((valid) => {
                    if (valid) {
                        //this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.addLoading = true;
                            //NProgress.start();
                            let para = Object.assign({}, this.addForm);
                            addBank(para).then((res) => {
                                this.addLoading = false;
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['addForm'].resetFields();
                                this.addFormVisible = false;
                                this.getBanks();
                            });
                        //});
                    }
                });
            },
            selsChange: function (sels) {
                this.sels = sels;
            },
            //批量删除
            batchRemove: function () {
                var bhs = this.sels.map(item => item.bh).toString();
                this.$confirm('确认删除选中记录吗？', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = { bhs: bhs };
                    batchRemoveBank(para).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getBanks();
                    });
                }).catch(() => {

                });
            }
        },
        mounted() {
            this.getBanks();
            this.getBankMaxNo();
        }
    }

</script>

<style scoped>

</style>
