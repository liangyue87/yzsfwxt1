<template>
    <section>
        <el-row>
            <el-col :span="24" class="toolbar" >
                <!--列表-->
                <el-table :data="row" highlight-current-row v-loading="listLoading"  style="width: 100%;">
                    <el-table-column prop="xmid" label="项目编号" width="90">
                    </el-table-column>
                    <el-table-column prop="xmmc" label="项目名称" width="90">
                    </el-table-column>
                    <el-table-column prop="yskssj" label="预算金额" width="90">
                    </el-table-column>
                    <el-table-column prop="ysjssj" label="合同金额" width="90">
                    </el-table-column>
                    <el-table-column prop="username" label="增补费用" width="90">
                    </el-table-column>
                    <el-table-column prop="mobilephone" label="开出收款金额" width="120">
                    </el-table-column>
                    <el-table-column prop="szgwysje" label="收款确认金额" width="120">
                    </el-table-column>
                    <el-table-column prop="tygwysje" label="申请退款金额" width="120">
                    </el-table-column>
                    <el-table-column prop="shbfysje" label="退款确认金额" width="120">
                    </el-table-column>
                    <el-table-column prop="dbysje" label="剩余未缴纳" width="110">
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
    import {gettKxlbList} from "../../api/zhcx";

  export default {
        data(){
            return{
                filters:{
                    xmmc:'',
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
                this.getKxlbList();
            },
            //获取项目列表
            getHsjglr_vue() {
                let param = {
                    page: this.page,
                    size: this .size,
                   // nodeid:this.nodeid,

                }
                gettKxlbList(param).then((res) => {
                    this.total = res.data.total;
                    this.row = res.data.list;
                    // this.listLoading = false;
                });
            },
            //跳转至发起委托设计界面
            // handleEdit: function (index, row) {
            //     localStorage.setItem("xmid",row.id);
            //     localStorage.setItem("sqlx",row.sqlx);
            //     localStorage.setItem("sqbh",row.sqbh);
            //     this.$router.push({path: '/Sqzhyslbsj',query: {xmid:row.id }});
            //
            // }
        },
        mounted() {
            this.getHsjglr_vue();

        }
    }

</script>