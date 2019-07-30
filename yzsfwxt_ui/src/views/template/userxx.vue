<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
    <section>
        <el-row :gutter="10">
            <el-col :span="4">
                <el-card class="box-card">
                    <div class="grid-content bg-purple">
                        <el-tree
                                ref="tree"
                                node-key="id"
                                :data="data"
                                :props="defaultProps"
                                @node-click="handleNodeClick"
                                default-expand-all
                                highlight-current
                                :expand-on-click-node="false"
                        ></el-tree>
                    </div>
                </el-card>
            </el-col>
            <!--工具条-->
            <el-col :span="20" class="toolbar" style="margin: 0px;">
                <el-col :span="24" class="toolbar" >
                    <el-form :inline="true" :model="filters">
                        <el-form-item>
                            <el-input size="small" v-model="filters.username" placeholder="姓名"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button size="mini" type="primary" v-on:click="getUsers"><i class="iconfont icon-chaxun"></i>查询</el-button>
                        </el-form-item>

                    </el-form>
                </el-col>
                <el-col :span="24" class="toolbar" >
                    <!--列表-->
                    <el-table :data="users" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
                        <el-table-column type="selection" width="55">
                        </el-table-column>
                        <el-table-column type="index" label="序号" width="60">
                        </el-table-column>
                        <el-table-column prop="username" label="姓名" width="120" sortable>
                        </el-table-column>
                        <el-table-column prop="account" label="账号" width="110" sortable>
                        </el-table-column>
                        <el-table-column prop="mobilephone" label="手机号" width="120">
                        </el-table-column>
                        <el-table-column prop="email" label="邮箱" width="180">
                        </el-table-column>
                        <el-table-column prop="islocked" label="是否启用" min-width="180" :formatter="formatIslocked">
                        </el-table-column>

                    </el-table>
                </el-col>
                <el-col :span="24" class="toolbar" >
                    <!--工具条-->
                    <span><b>温馨提示：</b>当前只能选择一个部门负责人，如果选择多个将以选择的第一个人为准</span><br>
                    <el-button size="mini" type="primary" @click="batchRemove" :disabled="this.sels.length===0"><i class="iconfont icon-queren"></i>确定</el-button>
                    <el-pagination layout="total, prev, pager, next"  @current-change="handleCurrentChange" :page-size="this.size" :total="this.total" style="float:right;">
                    </el-pagination>
                </el-col>
            </el-col>
        </el-row>

    </section>
</template>

<script>
    import util from '../../common/js/util'
    import Vue from 'vue'
    //import VueResource from 'vue-resource';
    //Vue.use(VueResource)
    //import NProgress from 'nprogress'
    import { getUserList, showAllDept} from '../../api/api';

    export default {
        data() {
            return {
                filters: {
                    username: ''
                },
                data:[],
                defaultProps: {
                    children: 'children',
                    label: 'text'
                },
                deptid:'',//选中部门的id
                users: [],
                total: 10,//表格数据总数量
                page: 1,//当前页码
                size: 10,//每页容量
                listLoading: false,
                sels: [1],//列表选中列
                length : 0,
                editLoading: false,
                editFormRules: {
                    username: [
                        { required: true, message: '请输入姓名', trigger: 'blur' }
                    ]
                },
                addLoading: false,
                addFormRules: {
                    username: [
                        { required: true, message: '请输入姓名', trigger: 'blur' }
                    ]
                },
                xmid: this.$route.query.xmid,

            }
        },
        methods: {
            //显示转换
            formatIslocked : function (row, column) {
                return row.islocked == '0' ? '正常' : row.islocked != '0' ? '冻结' : '未知';
            },
            handleCurrentChange(val) {
                this.page = val;
                this.getUsers();
            },
            //获取用户列表
            getUsers() {
                let para = {
                    deptid: this.deptid,
                    page: this.page - 1,
                    size: this .size,
                    username: this.filters.username
                };
                this.listLoading = true;
                //NProgress.start();
                getUserList(para).then((res) => {
                    this.total = res.data.total;
                    this.users = res.data.users;
                    this.listLoading = false;
                    //NProgress.done();
                });

            },

            selsChange: function (sels) {
               /* if (sels.length > 0){
                    for (let i=0;i < sels.length;i++){
                        this.sels.push(sels[i].username);
                    }

                }*/
                this.sels = sels;
                console.log(this.sels[0].username)
                console.log(this.sels[0].userid)
            },
            //确定
            batchRemove: function () {

               /* // 定义一个新数组
                var newArr = [];
                // 遍历传进来的数组
                for(var i=0; i<this.sels.length; i++){
                    // 如果newArr里没有this.sels[i]
                    if( newArr.indexOf(this.sels[i]) == -1 ){
                        // 把this.sels[i]传进新数组
                        newArr.push(this.sels[i]);
                    }
                }*/
                //只存选择的第一个人
                var obj=JSON.stringify(this.sels[0]);
                localStorage.setItem("userobj",obj);
                this.$routerTab.close();
                this.$routerTab.refresh('/dept');
            },
            //查询全部
            getAllDeptList() {
                //NProgress.start();
                showAllDept().then(res => {
                    this.data = res.data;
                    //NProgress.done();
                });
            },
            handleNodeClick(data) {//点击节点 table显示部门的人员
                this.deptid = data.id;
                this.getUsers();
            }
        },
        mounted() {
            this.getAllDeptList();
            this.getUsers();

            /*this.$nextTick(() => {
                this.$refs.tree.setCurrentKey('3440103');
            });*/
        }
    }

</script>

<style scoped>

</style>
