<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true" :model="filters">
                <el-form-item label="项目编号:">
                    <el-input v-model="filters.xmbh" placeholder="请输入项目编号"></el-input>
                </el-form-item>
                <el-form-item label="项目名称:">
                    <el-input v-model="filters.xmmc" placeholder="请输入项目名称"></el-input>
                </el-form-item>
               <!-- <el-form-item label="合同发起时间">
                    <el-date-picker type="date" placeholder="选择日期" v-model="filters.startDate" style="width:210px;"></el-date-picker>
                </el-form-item>
                <el-form-item >
                    <el-col class="line" :span="2" style="text-align: center">-</el-col>
                </el-form-item>
                <el-form-item >
                    <el-date-picker type="date" placeholder="选择日期" v-model="filters.endDate" style="width:210px;"></el-date-picker>
                </el-form-item>-->
                <el-form-item label="合同状态:"  >
                    <el-select v-model="filters.zt" placeholder="请选合同状态" >
                        <el-option label="全部" value=""></el-option>
                        <el-option label="会签中" value="1"></el-option>
                        <el-option label="会签未通过" value="2"></el-option>
                        <el-option label="会签通过" value="3"></el-option>
                        <el-option label="作废" value="3"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" v-on:click="getHtqklb_vue">查询</el-button>
                </el-form-item>
            </el-form>
        </el-col>

        <!--列表-->
        <el-table :data="rows" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
           <!-- <el-table-column type="index" width="60">
            </el-table-column>-->
            <el-table-column prop="xmbh" label="项目编号" width="100" >
            </el-table-column>
            <el-table-column prop="xmmc" label="项目名称" width="100" >
                <!-- :formatter="formatSbfl"-->
            </el-table-column>
            <el-table-column prop="fssj" label="发起OA会签时间" width="140" >
            </el-table-column>
            <el-table-column prop="qrsj" label="会签结束时间" width="120" >
            </el-table-column>
            <el-table-column prop="zt" label="会签结果" width="100" >
            </el-table-column>
            <el-table-column prop="qdny" label="合同签订时间" width="110" >
            </el-table-column>
            <el-table-column prop="gczje" label="合同金额" width="100" sortable>
            </el-table-column>
            <el-table-column prop="htqdyfk" label="首付款" width="100" sortable>
            </el-table-column>
            <el-table-column prop="yhtbl" label="首付比例" width="100" >
            </el-table-column>
            <el-table-column prop="xyfs" label="补充协议份数" width="100" >
            </el-table-column>
            <el-table-column prop="zt" label="合同状态" width="100" :formatter="formatzt">
            </el-table-column>
            <!--   <el-table-column label="操作" width="150">
                   <template slot-scope="scope">
                       <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                       <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
                   </template>
               </el-table-column>-->
        </el-table>

        <!--工具条-->
        <el-col :span="24" class="toolbar">
            <el-pagination
                    layout="sizes, prev, pager, next"
                    @current-change="handleCurrentChange"
                    @size-change="handleSizeChange"
                    :page-size="this.size"
                    :total="this.total"
                    :page-sizes="this.sizes"
                    style="float:right;"

            ></el-pagination>
        </el-col>
    </section>
</template>
<script>
    /* import util from '../../common/js/util'*/

    import {getHtqklb} from '../../api/htqklb';
    export default {
        data() {
            return {
                filters: {
                    xmbh:'',
                    xmmc:'',
                    zt:''
                },
                rows: [],
                total: 10,//表格数据总数量
                page: 1,//当前页码
                size: 10,//每页容量
                sizes: [10, 15, 20, 30] ,
                listLoading: false
            }
        },
        methods: {
            //获取勘查任务
            getHtqklb_vue: function () {
                let params = Object.assign({page: this.page, size: this.size},this.filters);
                this.listLoading = true;
                getHtqklb(params).then((res) => {
                    this.total = res.total;
                    this.rows = res.row;
                    this.listLoading = false;
                });
            },
            formatzt: function (row, column) {
                var zt;
                switch (row.zt) {
                    case "1":
                        zt="会签中";
                        break;
                    case "2":
                        zt="会签未通过";
                        break;
                    case "3":
                        zt="会签通过";
                        break;
                    case "4":
                        zt="作废";
                        break;
                }
                return zt;
            },
            handleCurrentChange(currentPage) {
                this.page = currentPage;
                this.getHtqklb_vue();
            },
            //当前页条数
            handleSizeChange(size){
                this.size = size;
                this.getHtqklb_vue();
            },
            selsChange: function (sels) {
                this.sels = sels;
            },
        },
        mounted() {
            this.getHtqklb_vue();
        }
    }

</script>