<template>
    <section>
        <el-row>
            <!--工具条-->
            <el-col :span="24" class="toolbar" style="border: #f5f7fa solid 1px">
                <el-form>
                    <el-form-item>
                        <router-link :to="'/Sgxdlb'">
                            <el-button size="mini" type="info" v-on:click="return_vue">
                                <i class="iconfont icon-quxiao"></i>取消</el-button>
                        </router-link>
                        <el-button size="mini" type="primary" v-on:click="addSggcd_vue">
                            <i class="iconfont icon-xinzeng"></i>新增施工单</el-button>
                    </el-form-item>
                </el-form>
            </el-col>

            <el-col :span="24" class="toolbar">
                <!--列表-->
                <el-table :data="row" highlight-current-row v-loading="listLoading" style="width: 100%;" height="450"
                          :stripe="true" :row-style="rowClass" :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
                    <el-table-column property="xmbh" label="项目编号" width="200" align="center"></el-table-column>
                    <el-table-column property="xmmc" label="项目名称" header-align="center" align="left"></el-table-column>
                    <el-table-column property="rwzt" label="任务状态" align="center"></el-table-column>
                    <el-table-column property="lxr" label="报建联系人" align="center"></el-table-column>
                    <el-table-column property="cjsj" label="创建时间" width="160" align="center"></el-table-column>
                    <el-table-column label="操作" fixed="right" width="100" align="center">
                        <template slot-scope="scope">
                            <div v-if="(scope.row.lczt == 0)">
                                <label title="编辑">
                                    <i class="iconfont icon-ai-edit"  @click="editSggcd_vue(scope.$index, scope.row)"
                                       style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                </label>
                            </div>
                            <div v-else>

                            </div>
                        </template>
                    </el-table-column>
                </el-table>
            </el-col>
            <el-col :span="24" class="toolbar" >
                <!--工具条-->
                <el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[10, 15, 30, 50,100]"
                               @current-change="handleCurrentChange" @size-change="handleSizeChange"
                               :page-size="this.size" style="float:right;" :total="this.total">
                </el-pagination>
            </el-col>
        </el-row>
    </section>
</template>

<script>
    import { getSggcdList,findsgdid } from '../../api/sgjg';
    export default {
        data() {
            return {
                row:[],
                total: 10,//表格数据总数量
                page: 1,//当前页码
                size: 15,//每页容量
                listLoading: false,

                xmid:this.$route.query.xmid,//项目ID
            }
        },
        mounted() {
            this.getsggcdlb_vue();
        },
        methods: {
            //查询
            getsggcdlb_vue() {
                let params = {
                    xmid:this.xmid,
                    page:this.page-1,
                    size:this.size,
                };
                getSggcdList(params).then(res => {
                    this.row = res.data.list;
                    this.total = res.data.total;
                });
            },
            //进入新增施工任务单界面
            addSggcd_vue:function(){
                findsgdid().then(res=>{
                    this.$router.push({path: '/addSgxd', query: {xmid:this.xmid,type:'1',isEdit:'0',sgdid:res.data.sgdid }});
                });
            },
            //进入编辑施工任务单界面
            editSggcd_vue:function (index, row) {
                this.$router.push({path: '/editSgxd', query: {xmid:row.xmid,type:'1',isEdit:'1',sgdid:row.id,rwbh:row.rwbh }});//传值 id
            },
            //审核施工任务单
            shSggcd_vue:function (index, row) {
                this.$router.push({path: '/shSgxd', query: {xmid:row.xmid,type:'1',isEdit:'2',sgdid:row.id }});//传值 id
            },
            //指派人员
            zpSggcd_vue:function (index, row) {
                this.$router.push({path: '/zpSgxd', query: {xmid:row.xmid,type:'1',isEdit:'2',sgdid:row.id }});//传值 id
            },
            //施工任务单下单
            xdSggcd_vue:function (index, row) {
                this.$router.push({path: '/xdSgxd', query: {xmid:row.xmid,type:'1',isEdit:'2',sgdid:row.id,xmjl:row.xmgzry }});//传值 id
            },
            //返回上一界面
            return_vue:function(){
                this.$routerTab.close();
            },
            handleCurrentChange(val) {
                this.page = val;
                this.getsggcdlb_vue();
            },
            handleSizeChange(val) {
                this.size = val;
                this.page = 1;
                this.getsggcdlb_vue();
            },
            rowClass:function (row,index) {
                return {"height":"6.0vh"}
            }
        }
    }
</script>

<style scoped>

</style>