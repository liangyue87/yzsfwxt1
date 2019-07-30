<template>
    <section>
        <el-row>
            <el-col :span="24">
                <!--项目基本信息模板-->
                <YJXMJBXX></YJXMJBXX>
                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">移交单列表</div>
                    </div>
                    <el-col :span="24" class="toolbar" style="border: #f5f7fa solid 1px" >
                        <router-link :to="'/Sbyjxmlb'">
                            <el-button size="mini" type="info" v-on:click="return_vue">
                                <i class="iconfont icon-quxiao"></i>取消</el-button>
                        </router-link>
                        <el-button size="mini" type="primary" v-on:click="addSbyj_vue">
                            <i class="iconfont icon-xinzeng"></i>新增移交单</el-button>
                    </el-col>
                    <el-col :span="24" class="toolbar">
                        <!--列表-->
                        <el-table :data="row" highlight-current-row v-loading="listLoading" style="width: 100%;" height="300"
                                  :stripe="true" :row-style="rowClass" :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
                            <el-table-column property="xmbh" label="项目编号" width="200" align="center"></el-table-column>
                            <el-table-column property="xmmc" label="项目名称" align="center"></el-table-column>
                            <el-table-column property="sbyjdbh" label="单据编号" align="center"></el-table-column>
                            <el-table-column property="username" label="发起人" align="center"></el-table-column>
                            <el-table-column property="cjsj" label="发起时间" align="center" :formatter="formatCjsj"></el-table-column>
                            <el-table-column property="yjzt" label="移交单状态" align="center"></el-table-column>
                            <el-table-column property="sbs" label="水表数量" align="center"></el-table-column>
                            <el-table-column fixed="right" label="操作" width="100" align="center">
                                <template slot-scope="scope">
                                    <div v-if="scope.row.bjzt == 'kbj'">
                                        <label title="编辑">
                                            <i class="iconfont icon-ai-edit"  @click="editSbyj_vue(scope.$index, scope.row)"
                                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label>
                                    </div>
                                    <div v-else>

                                    </div>
                                </template>
                            </el-table-column>
                        </el-table>
                    </el-col>
                    <!--<el-col :span="24" class="toolbar" >-->
                        <!--&lt;!&ndash;工具条&ndash;&gt;-->
                        <!--<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[10, 15, 30, 50,100]"-->
                                       <!--@current-change="handleCurrentChange" @size-change="handleSizeChange"-->
                                       <!--:page-size="this.size" style="float:right;" :total="this.total">-->
                        <!--</el-pagination>-->
                    <!--</el-col>-->
                </el-card>
            </el-col>

        </el-row>
    </section>
</template>

<script>
    import { querySbyjList,getyjzbid } from '../../api/sbyj';
    import YJXMJBXX from '@/views/template/yjxmjbxx.vue';
    export default {
        name: "sbyjdlb",
        components: {
            'YJXMJBXX': YJXMJBXX,  //移交项目基本信息组件
        },
        data() {
            return {
                row:[],
                total: 0,//表格数据总数量
                page: 1,//当前页码
                size: 15,//每页容量
                listLoading:false,
                xmid:this.$route.query.xmid,//项目id
                xmbh:this.$route.query.xmbh,//项目编号
                xmmc:this.$route.query.xmmc,//项目名称
                jsdw:this.$route.query.jsdw,//建设单位
                gclx:this.$route.query.gclx,//工程类型

            }
        },
        mounted() {
            this.showSbyjd_vue();
        },
        methods: {
            //根据项目ID查询水表移交单列表
            showSbyjd_vue(){
                let params = {xmid:this.xmid};
                querySbyjList(params).then(res=>{
                    this.row = res.data.list;
                });
            },
            //去新增水表移交单界面
            addSbyj_vue(){
                getyjzbid().then(res=>{
                    this.$router.push({path: '/addSbyjd',
                        query: {xmid:this.xmid,xmmc:this.xmmc,xmbh:this.xmbh,gclx:this.gclx,jsdw:this.jsdw,
                            yjzbid:res.data.yjzbid }});
                });
            },
            //去编辑水表移交单界面
            editSbyj_vue(index,row){
                this.$router.push({path: '/addSbyjd', query: {xmid:this.xmid,zyjdbh:row.sbyjdbh,
                        xmmc:this.xmmc,xmbh:this.xmbh,gclx:this.gclx,jsdw:this.jsdw,isEdit:'1',
                        yjzbid:row.id}});
            },
            return_vue(){
                this.$routerTab.close();
            },
            formatCjsj: function (row, column) {
                let sj = row.cjsj;
                return this.publicUtil.formatChange(sj);
            },
            handleCurrentChange(val) {
                this.page = val;
                this.showSbyjd_vue();
            },
            handleSizeChange(val) {
                this.size = val;
                this.page = 1;
                this.showSbyjd_vue();
            },
            rowClass:function (row,index) {
                return {"height":"6.0vh"}
            }
        }
    }
</script>

<style scoped>

</style>