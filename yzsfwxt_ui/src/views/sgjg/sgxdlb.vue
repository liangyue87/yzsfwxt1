<template>
    <section>
        <el-row>
            <!--工具条-->
            <el-col :span="24" class="toolbar" style="border: #f5f7fa solid 1px" >
                <el-form  :model="filters" >
                    <el-form-item>
                        <el-col :span="2" style="text-align: right">
                            <span>项目编号&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="filters.xmbh"></el-input>
                        </el-col>
                        <el-col :span="2"style="text-align: right">
                            <span>项目名称&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="filters.xmmc"></el-input>
                        </el-col>
                        <el-col :span="3" style="text-align: left;margin-left: 15px">
                            <el-button size="mini" type="primary" v-on:click="getsgxdlb_vue">
                                <i class="iconfont icon-chaxun"></i>查询</el-button>
                        </el-col>
                    </el-form-item>
                </el-form>
            </el-col>

            <el-col :span="24" class="toolbar">
                <!--列表-->
                <el-table :data="row" highlight-current-row v-loading="listLoading" style="width: 100%;" height="450"
                          :stripe="true" :row-style="rowClass" :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
                    <el-table-column property="xmbh" label="项目编号" width="200" align="center"></el-table-column>
                    <el-table-column property="vazlx" label="安装类型" width="100" align="center"></el-table-column>
                    <el-table-column property="lxr" label="联系人" width="100" align="center"></el-table-column>
                    <el-table-column property="lxrsjh" label="联系人手机" align="center"></el-table-column>
                    <el-table-column property="xmmc" label="项目名称" width="200" header-align="center" align="left"></el-table-column>
                    <el-table-column property="dwmc" label="单位名称" align="center"></el-table-column>
                    <el-table-column property="lxsj" label="立项时间" width="160" align="center" :formatter="formatLxsj"></el-table-column>
                    <!--<el-table-column property="xmdz" label="项目地址" width="228" align="center"></el-table-column>-->
                    <el-table-column label="操作" fixed="right" width="150" align="center">
                        <template slot-scope="scope">
                            <label title="下单">
                                <i class="iconfont icon-weibiaoti-"  @click="addSgxd_vue(scope.$index, scope.row)"
                                   style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                            </label>
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
    import { getSgxdList } from '../../api/sgjg';
    export default {
        name: "sgxllb",
        data() {
            return {
                row:[],
                total: 0,//表格数据总数量
                page: 1,//当前页码
                size: 15,//每页容量
                listLoading: false,
                filters:{
                    xmbh:''
                    ,xmmc:''
                },
            }
        },
        mounted() {
            this.getsgxdlb_vue();
        },
        created() {
            let lett = this;
            document.onkeydown = function(e) {
                var key = window.event.keyCode;
                if (key == 13) {
                    lett.getsgxdlb_vue();
                }
            }
        },
        methods: {
            //查询
            getsgxdlb_vue() {
                let params = {
                    xmbh:this.filters.xmbh,//项目编号
                    xmmc:this.filters.xmmc,
                    page:this.page-1,
                    size:this.size,
                };
                getSgxdList(params).then(res => {
                    this.row = res.data.list;
                    this.total = res.data.total;
                });
            },
            //跳转到新增施工下单界面
            addSgxd_vue:function (index, row) {
                // this.$router.push({path: '/addSgxd', query: {xmid:row.id,type:'1',isEdit:'0' }});
                this.$router.push({path: '/sggcdlb', query: {xmid:row.id}});
            },
            //立项时间格式转换
            formatLxsj: function (row, column) {
                let sj = row.lxsj;
                return this.publicUtil.formatChange(sj);
            },
            handleCurrentChange(val) {
                this.page = val;
                this.getsgxdlb_vue();
            },
            handleSizeChange(val) {
                this.size = val;
                this.page = 1;
                this.getsgxdlb_vue();
            },
            rowClass:function (row,index) {
                return {"height":"6.0vh"}
            }
        }
    }
</script>

<style scoped>

</style>