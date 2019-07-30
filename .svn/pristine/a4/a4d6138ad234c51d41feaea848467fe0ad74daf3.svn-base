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
                            <span> 委托预算状态： </span>
                        </el-col>
                        <el-col :span="5">
                            <el-select v-model="filters.wtyszt"clearable  placeholder="请选择" style="width:100%">
                                <el-option label="待预算" value="0"></el-option>
                                <el-option label="预算中" value="1"></el-option>
                                <el-option label="预算变更中" value="2"></el-option>
                                <el-option label="完成" value="3"></el-option>
                                <el-option label="作废" value="4"></el-option>
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
                    <el-table-column prop="xmmc" label="项目名称" width="200" align="center">
                    </el-table-column>
                    <el-table-column prop="yskssj" label="委托预算时间" width="200" align="center">
                    </el-table-column>
                    <el-table-column prop="ysjssj" label="预算完成时间" width="200" align="center">
                    </el-table-column>
                    <el-table-column prop="username" label="预算人员" width="100" align="center">
                    </el-table-column>
                    <el-table-column prop="mobilephone" label="预算人员联系方式" width="150" align="center">
                    </el-table-column>
                    <el-table-column prop="szgwysje" label="市政管网工程费" width="120" align="center">
                    </el-table-column>
                    <el-table-column prop="tygwysje" label="庭院管网工程费" width="120" align="center">
                    </el-table-column>
                    <el-table-column prop="shbfysje" label="生活泵房" width="100" align="center">
                    </el-table-column>
                    <el-table-column prop="dbysje" label="地表费" width="100" align="center">
                    </el-table-column>
                    <el-table-column prop="hbysje" label="户表费" width="100" align="center">
                    </el-table-column>
                    <el-table-column prop="dgysje" label="吊管" width="100" align="center">
                    </el-table-column>
                    <el-table-column prop="kzzjysje" label="抗震支架" width="90" align="center">
                    </el-table-column>
                    <el-table-column prop="kyscysje" label="试压冲洗" width="90" align="center">
                    </el-table-column>
                    <el-table-column prop="sjysje" label="设计费" width="90" align="center">
                    </el-table-column>
                    <el-table-column prop="jlysje" label="监理费" width="190" align="center">
                    </el-table-column>
                    <el-table-column prop="ysze" label="预算总额" width="90" align="center">
                    </el-table-column>
                    <el-table-column prop="yhqrsj" label="用户确认预算时间" width="200" align="center">
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

    import{getWtysList}from'../../api/zhcx';
    export default {
        data(){
            return{
                filters:{
                    xmmc:'',
                    wtyszt:'',

                },
                row: [],
                total: 10,//表格数据总数量
                page: 1,//当前页码
                size: 10,//每页容量
              //  nodeid:'30001',//nodeid要从新设计.........
                listLoading: false,
            }
        },
        methods:{

            handleCurrentChange(val) {
                this.page = val;
                this.getWtysList();
            },
            //获取项目列表
            getHsjglr_vue() {
                let param = {
                    page: this.page - 1,
                    size: this .size,
             //       nodeid:this.nodeid,
                    xmmc:this.filters.xmmc,
                    wtyszt:this.filters.wtyszt,

                }
                getWtysList(param).then((res) => {
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