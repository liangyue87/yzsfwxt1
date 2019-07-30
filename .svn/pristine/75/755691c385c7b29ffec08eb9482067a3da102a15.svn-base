<template>
    <section>
        <el-row>
            <el-col :span="24" class="toolbar" >
                <el-form  :model="filters" >
                    <el-form-item>
                        <el-col :span="2" style="text-align: right">
                            <span>项目名称：</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input v-model="filters.xmmc" placeholder="输入项目名称/编号"></el-input>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span> 验收状态： </span>
                        </el-col>
                        <el-col :span="5">
                            <el-select v-model="filters.ysqk"clearable  placeholder="请选择" style="width:100%">
                                <el-option label="验收中" value="0"></el-option>
                                <el-option label="验收完成" value="1"></el-option>
                                <el-option label="验收作废" value="2"></el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="3" style="text-align: center">
                            <el-button type="primary" v-on:click="getHsjglr_vue">查询</el-button>
                        </el-col>
                    </el-form-item>
                </el-form>
            </el-col>
            <el-col :span="24" class="toolbar" >
                <!--列表-->
                <el-table :data="row" highlight-current-row v-loading="listLoading"  style="width: 100%;">
                    <el-table-column prop="xmbh" label="项目编号" width="150" align="center">
                    </el-table-column>
                    <el-table-column prop="xmmc" label="项目名称" width="150" align="center">
                    </el-table-column>
                    <el-table-column prop="fqrq" label="综合验收发起时间" width="200" align="center">
                    </el-table-column>
                    <el-table-column prop="yjyssj" label="预约验收时间" width="200" align="center">
                    </el-table-column>
                    <el-table-column prop="sjyssj" label="实际验收时间" width="200" align="center">
                    </el-table-column>
                    <el-table-column prop="rwcljs" label="验收完成时间" width="200" align="center">
                    </el-table-column>
                    <el-table-column prop="fkjg" label="验收结果" width="80" align="center">
                    </el-table-column>
                </el-table>
            </el-col>
            <el-col :span="24" class="toolbar" >
                <!--工具条-->
                <el-pagination layout="total, prev, pager, next"  @current-change="handleCurrentChange" :page-size="this.size" :total="this.total" style="float:right;">
                </el-pagination>
            </el-col>

        </el-row>
    </section>
</template>
<script>

    //  import{getKxkbLsit}from'../../api/zhcx';
    import {getZhyslbList} from "../../api/zhcx";

    export default {
        data(){
            return{

                filters:{
                    xmmc:'',
                    ysqk:'',

                },
                row: [],
                total: 10,//表格数据总数量
                page: 1,//当前页码
                size: 10,//每页容量
                nodeid:'30001',//nodeid要从新设计.........
                listLoading: false,
            }
        },
        methods:{

            handleCurrentChange(val) {
                this.page = val;
                this.getZhyslbList();
            },
            //获取项目列表
            getHsjglr_vue() {
                let param = {
                    page: this.page-1,
                    size: this .size,
                    // nodeid:this.nodeid,
                    xmmc:this.filters.xmmc,
                    ysqk:this.filters.ysqk,

                }
                getZhyslbList(param).then((res) => {
                    this.total = res.data.total;
                    this.row = res.data.list;
                    // this.listLoading = false;
                });
            },
        },
        mounted() {
            this.getHsjglr_vue();

        }
    }

</script>