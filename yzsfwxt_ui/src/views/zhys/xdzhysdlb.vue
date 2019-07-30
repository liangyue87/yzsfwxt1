<template>
    <section>
        <el-row>
            <!--会审结果录入-->
            <!--工具条-->
            <el-col :span="24" class="toolbar" >
                <el-form   >
                    <el-form-item>
                        <el-col :span="2" style="text-align: right">
                            <span>项目编号 &nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input v-model="xmbh" size="small "></el-input>
                        </el-col>
                        <el-col :span="2" style="text-align: right">
                            <span>项目名称 &nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input v-model="xmmc" size="small "></el-input>
                        </el-col>
                        <el-col :span="2" style="text-align: right">
                            <span>项目状态：</span>
                        </el-col>
                        <el-col :span="5">
                            <el-select v-model="status" clearable  placeholder="请选择" style="width:100%" size="small ">
                                <el-option label="正在进行" value="1"></el-option>
                                <el-option label="已经完成" value="2"></el-option>
                                <el-option label="任务作废" value="-1"></el-option>
                            </el-select>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="2" style="text-align: right">
                            <span>用户联系人 &nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input v-model="lxr" size="small "></el-input>
                        </el-col>
                        <el-col :span="2" style="text-align: right">
                            <span>立项日期 &nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-date-picker type="date" value-format="yyyy-MM-dd HH:mm" size="small " placeholder="选择日期" v-model="kssj" style="width: 100%;"></el-date-picker>
                        </el-col>
                        <el-col class="line" :span="2" style="text-align: center">-</el-col>
                        <el-col :span="5">
                            <el-date-picker type="date" value-format="yyyy-MM-dd HH:mm" size="small " placeholder="选择日期" v-model="jssj" style="width: 100%;"></el-date-picker>
                        </el-col>
                        <el-col :span="3" style="text-align: center">
                            <el-button type="primary" v-on:click="getHsjglr_vue">查询</el-button>
                        </el-col>
                    </el-form-item>
                </el-form>
            </el-col>
            <el-col :span="24" class="toolbar" >
                <!--列表-->
                <el-table :data="row"  v-loading="listLoading" style="width: 100%;" height="500px" :row-style="rowClass" :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
                    <el-table-column prop="xmbh" label="项目编号" width="150" align="center">
                    </el-table-column>
                    <el-table-column prop="xmmc" label="项目名称" width="150" align="center">
                    </el-table-column>
                    <el-table-column prop="status" label="项目状态" width="100":formatter="formatStatus"  align="center">
                    </el-table-column>
                    <el-table-column prop="azlx" label="安装类型" width="100" align="center">
                    </el-table-column>
                    <el-table-column prop="lxr" label="用户联系人" width="150" align="center">
                    </el-table-column>
                    <el-table-column prop="lxsj_str" label="立项日期" width="200" align="center" >
                    </el-table-column>
                    <el-table-column prop="lxrsjh" label="联系电话" width="200" align="center">
                    </el-table-column>
                    <el-table-column label="操作" min-width="300" align="center" fixed="right">
                        <template slot-scope="scope">
                            <el-button size="small" type="primary" @click="handleEdit(scope.$index, scope.row)">发起</el-button>
                            <el-button size="small" type="primary" @click="zpry_vue(scope.$index, scope.row)">指派人员</el-button>
                            <el-button size="small" type="primary" @click="zhys_vue(scope.$index, scope.row)">综合验收</el-button>
                            <el-button size="small" type="primary" @click="fkys_vue(scope.$index, scope.row)">反馈验收意见</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-col>
            <el-col :span="24" class="toolbar" >
                <!--工具条-->
                <el-pagination background layout="total, prev, pager, next, sizes , jumper"
                               @current-change="handleCurrentChange" @size-change="handleSizeChange"  :page-sizes="[10, 15, 30, 50,100]"
                               :page-size="this.size" :total="this.total" style="float:right;">
                </el-pagination>
            </el-col>



        </el-row>
    </section>
</template>
<script>

    import{getXdzhysdlb}from'../../api/zhys';
    export default {
        data(){
            return{

                    xmmc:'',
                    xmbh:'',
                    lxr:'',
                    status:'',
                    kssj:'',
                    jssj:'',

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
            handleCurrentChange(val) {
                this.page = val;
                this.getHsjglr_vue();
            },
            handleSizeChange(val) {
                this.size = val;
                this.page = 1;
                this.getHsjglr_vue();
            },
            //获取项目列表
            getHsjglr_vue() {
                let params = Object.assign({ page: this.page, size: this .size ,xmmc:this.xmmc,xmbh:this.xmbh,lxr:this.lxr,status:this.status,kssj:this.kssj,jssj:this.jssj});
               this.listLoading=true;
                getXdzhysdlb(params).then((res) => {
                    this.total = res.data.total;
                    this.row = res.data.list;
                    this.listLoading=false;
                });
            },
            //跳转至发起委托设计界面
            handleEdit: function (index, row) {
               /* localStorage.setItem("xmid",row.id);
                localStorage.setItem("sqlx",row.sqlx);
                localStorage.setItem("sqbh",row.sqbh);
                localStorage.setItem("lxr",row.lxr);
                localStorage.setItem("lxrsjh",row.lxrsjh);
                localStorage.setItem("xzqybh",row.xzqybh);*/
                this.$router.push({path: '/Xdzhysdlbsj',query: {xmid:row.id,sqdid:row.sqzbid ,lxrsjh:row.lxrsjh,lxr:row.lxr,bmbmQs:row.bmbmQs}});

            },
            rowClass:function (row,index) {
                return {"height":"6.0vh"}
            },
            //跳转到指派人员界面
            zpry_vue:function (index,row){
                this.$router.push({path: '/zpzhys',query: {xmid:row.id,sqlx:row.sqlx,sqbh:row.sqbh,sqdid:row.sqzbid }});
            },
            //跳转到综合验收界面
            zhys_vue:function (index,row){
                this.$router.push({path: '/yszhys',query: {xmid:row.id,sqlx:row.sqlx,sqbh:row.sqbh,sqdid:row.sqzbid }});
            },
            //跳转到反馈验收界面
            fkys_vue:function (index,row){
                this.$router.push({path: '/fkzhys',query: {xmid:row.id,sqdid:row.sqzbid }});
            },
        },
        mounted() {
            this.getHsjglr_vue();

        }
    }

</script>