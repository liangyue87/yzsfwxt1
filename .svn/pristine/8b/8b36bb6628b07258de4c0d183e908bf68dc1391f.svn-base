<template>
    <section>
        <el-row>
            <!--工具条-->
            <el-col :span="24" class="toolbar" style="border: #f5f7fa solid 1px" >
                <el-form :model="filters" @submit.native.prevent>
                    <el-form-item>
                        <el-col :span="2" style="text-align: right">
                            <span><span style="color: red">*</span>报装户号</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="filters.bzhh" @keyup.enter.native="getList"></el-input>
                        </el-col>
                        <el-col :span="5" style="text-align: center;margin-left: 0px;">
                            <el-button size="mini" type="primary" @click="getList">
                                <i class="iconfont icon-chaxun"></i>查询</el-button>
                            <!--<el-button size="mini" type="success" @click="dc_vue">
                                <i class="iconfont icon-yingyongtubiaodaochu"></i>导出</el-button>-->
                        </el-col>
                    </el-form-item>
                </el-form>
            </el-col>

            <!--列表-->
            <el-col :span="24" class="toolbar">
                <el-table id="out-table" :data="tableData" border highlight-current-row v-loading="listLoading"
                          style="width: 100%;" height="400"
                          :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
                    <el-table-column property="HH" label="报装户号" width="130" align="center">
                    </el-table-column>
                    <el-table-column property="YYZX_HH" label="立户号" width="130" align="center">
                    </el-table-column>
                    <el-table-column property="HM" label="户名" width="180" align="center">
                    </el-table-column>
                    <el-table-column property="DZ" label="用户地址" min-width="180" align="center">
                    </el-table-column>
                    <el-table-column property="HHLXBH" label="水表类型" width="120" align="center">
                        <template slot-scope="scope">
                            <span v-if="scope.row.SBLX_STR == HB">户表</span>
                            <span v-else-if="scope.row.SBLX_STR == DB">工商表</span>
                            <span v-else-if="scope.row.SBLX_STR == WJJLB">五级计量表</span>
                        </template>
                    </el-table-column>
                    <el-table-column property="KJ_STR" label="报装口径" width="125" align="center">
                    </el-table-column>
                    <el-table-column property="YSXZ_STR" label="报装用水性质" width="180" align="center">
                    </el-table-column>
                    <el-table-column property="HYFL_STR" label="报装行业分类" width="140" align="center">
                    </el-table-column>
                </el-table>
            </el-col>
            <!--<el-col :span="24" class="toolbar" >
                &lt;!&ndash;工具条&ndash;&gt;
                <el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[10, 15, 30, 50,100]"
                               @current-change="handleCurrentChange" @size-change="handleSizeChange"
                               :current-page.sync = "this.page"
                               :page-size="this.size" style="float:right;" :total="this.total">
                </el-pagination>

            </el-col>-->
        </el-row>
    </section>
</template>

<script>
    import { getSbxxList } from '../../../api/zhcx';

    export default {
        name: "sbxx",
        data() {
            return {
                page : 1,
                size : 15,
                tableData:[],
                total: 0,
                listLoading:false,
                filters: {
                    bzhh :''
                }
            }
        },
        methods:{
            //列表查询
            getList(filters){
                this.filters.bzhh = this.filters.bzhh.trim();
                let params = Object.assign({ page: this.page, size: this .size }, this.filters);
                getSbxxList(params).then(res=>{
                    this.tableData = res.data.list;
                    this.total = res.data.total;
                });
            },
            handleCurrentChange(val) {
                this.page = val;
                this.getList();
            },
            handleSizeChange(val) {
                this.size = val;
                this.page = 1;
                this.getList();
            }
        },
        mounted() {
            this.getList();
        }
    }
</script>

<style scoped>

</style>