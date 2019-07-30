<template>
    <section>
        <el-row>
            <!--jssq-->
            <!--工具条-->
            <el-col :span="24" class="toolbar" style="border: #f5f7fa solid 1px">
                <el-form  :model="filters" >
                    <el-form-item>
                        <el-col :span="2" style="text-align: right">
                            <span>项目编号 &nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input v-model="filters.xmbh" size="small " @keyup.enter.native="getHsjglr_vue(1)"></el-input>
                        </el-col>
                        <el-col :span="2"style="text-align: right">
                            <span>项目名称 &nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input v-model="filters.xmmc" size="small " @keyup.enter.native="getHsjglr_vue(1)"></el-input>
                        </el-col>
                        <el-col :span="2" style="text-align: right">
                            <span>项目状态 &nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-select v-model="filters.status"clearable  placeholder="请选择" style="width:100%" size="small ">
                                <el-option label="全部" value=""></el-option>
                                <el-option label="正在进行" value="1"></el-option>
                                <el-option label="已经完成" value="2"></el-option>
                                <el-option label="任务作废" value="-1"></el-option>
                            </el-select>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="2"style="text-align: right">
                            <span>用户联系人 &nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input v-model="filters.lxr" size="small " @keyup.enter.native="getHsjglr_vue(1)"></el-input>
                        </el-col>
                        <el-col :span="2"style="text-align: right">
                            <span>立项日期 &nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-date-picker type="date" value-format="yyyy-MM-dd HH:mm" size="small " placeholder="选择日期" v-model="filters.kssj" style="width: 100%;"></el-date-picker>
                        </el-col>
                        <el-col class="line" :span="2" style="text-align: center">-</el-col>
                        <el-col :span="5">
                            <el-date-picker type="date" value-format="yyyy-MM-dd HH:mm" size="small " placeholder="选择日期" v-model="filters.jssj" style="width: 100%;"></el-date-picker>
                        </el-col>
                        <el-col :span="3" style="text-align: center">
                            <el-button size="mini" type="primary"  v-on:click="getHsjglr_vue(1)"><i class="iconfont icon-chaxun"></i>查询</el-button>
                           <!-- <el-button type="primary" v-on:click="getHsjglr_vue">查询</el-button>-->
                        </el-col>
                    </el-form-item>
                </el-form>
            </el-col>
            <el-col :span="24" class="toolbar" >
                <!--列表-->
                <el-table :data="row"  v-loading="listLoading" style="width: 100%;" height="500px" :row-style="rowClass" :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
                    <el-table-column prop="xmbh" label="项目编号" width="200" align="center">
                    </el-table-column>
                    <el-table-column prop="xmmc" label="项目名称" width="200" align="center">
                    </el-table-column>
                    <el-table-column prop="status" label="项目状态" width="150":formatter="formatStatus"  align="center">
                    </el-table-column>
                    <el-table-column prop="codename" label="安装类型" width="150" align="center">
                    </el-table-column>
                    <el-table-column prop="lxr" label="用户联系人" width="150" align="center">
                    </el-table-column>
                    <el-table-column prop="lxsj" label="立项日期" width="200" align="center" >
                    </el-table-column>
                    <el-table-column prop="lxrsjh" label="联系电话" width="200" align="center">
                    </el-table-column>
                    <!--<el-table-column prop="xmdz" label="工程地址" width="200" align="center">
                    </el-table-column>-->
                    <!-- <el-table-column prop="dwmc" label="报建单位" width="150" align="center">
                     </el-table-column>-->
                    <el-table-column label="操作" width="150" align="center" fixed="right">
                        <template slot-scope="scope">
                            <label title="发起结算" @click="handleEdit(scope.$index, scope.row)">
                                <i class="iconfont icon-tiaocha" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                          <!--  <el-button size="small" type="primary" @click="handleEdit(scope.$index, scope.row)">发起结算</el-button>-->
                        </template>
                    </el-table-column>
                </el-table>
            </el-col>
            <el-col :span="24" class="toolbar" >
                <!--工具条-->
                <el-pagination background layout="total, prev, pager, next, sizes , jumper" :current-page.sync = "this.page"
                               @current-change="handleCurrentChange" @size-change="handleSizeChange"  :page-sizes="[10, 15, 30, 50,100]"
                               :page-size="this.size" :total="this.total" style="float:right;">
                </el-pagination>
            </el-col>



        </el-row>
    </section>
</template>

<script>

    import{getJssqLsit}from'../../api/Js';
    export default {
        data(){
            return{
                filters:{
                    xmmc:'',
                    xmbh:'',
                    lxr:'',
                    status:'',
                    kssj:'',
                    jssj:''
                },
                row: [],
                total: 10,//表格数据总数量
                page: 1,//当前页码
                size: 10,//每页容量
                nodeid:'30001',
                listLoading: false,
            }
        },
        methods:{
            //显示转换 状态 1 正在进行 2 已经完成 3 任务作废
            formatStatus :function (row,colum) {
                // return row.status=="1" ? '正在进行':'已经完成';
                if(row.status=="1"){
                    return "正在进行";
                }else if(row.status=="2"){
                    return "已经完成";
                }else if(row.status=="-1") {
                    return "任务作废";
                }
            },

            //获取项目列表
            getHsjglr_vue(flag) {
                if(flag==1){
                    this.page=1
                }
                let params = Object.assign({ page: this.page, size: this .size }, this.filters);
                this.listLoading = true;
                getJssqLsit(params).then((res) => {//
                    if (res.data.total>0 && res.data.list.length<=0){
                        this.getList();
                    }
                    this.total = res.data.total;
                    this.row = res.data.list;
                    this.listLoading = false;
                });
            },
            handleSizeChange(val) {
                this.size = val;
                this.page = 1;
                this.getHsjglr_vue();
            },
            handleCurrentChange(val) {
                this.page = val;
                this.getHsjglr_vue();

            },
            //跳转至发起委托设计界面
            handleEdit: function (index, row) {
                this.$router.push({path: '/Jssqlbsj',query: {xmid:row.id ,sqlx:row.sqlx,sqbh:row.sqbh}});

            },
            rowClass:function (row,index) {
                return {"height":"6.0vh"}
            }
        },
        mounted() {
            this.getHsjglr_vue();

        }
    }

</script>