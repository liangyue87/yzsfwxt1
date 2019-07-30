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
                        <el-col :span="2" style="text-align: right">
                            <span>项目名称&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="filters.xmmc"></el-input>
                        </el-col>
                        <el-col :span="2" style="text-align: right">
                            <span>工程地址&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="filters.gcdz"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="2" style="text-align: right">
                            <span>安装类型&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-cascader
                                    :options="azlxList"
                                    :show-all-levels="false"
                                    v-model="azlx"
                                    :clearable="true"
                                    style="width: 255px;"
                                    size="small"
                            ></el-cascader>
                        </el-col>
                        <!--<el-col :span="2"style="text-align: right">-->
                            <!--<span>立项时间：</span>-->
                        <!--</el-col>-->
                        <!--<el-col :span="5">-->
                            <!--<el-date-picker-->
                                    <!--v-model="filters.kssj"-->
                                    <!--type="date"-->
                                    <!--placeholder="选择日期" style="width: 100%;">-->
                            <!--</el-date-picker>-->
                        <!--</el-col>-->
                        <!--<el-col class="line" :span="2" style="text-align: center">-</el-col>-->
                        <!--<el-col :span="5">-->
                            <!--<el-date-picker-->
                                    <!--v-model="filters.jssj"-->
                                    <!--type="date"-->
                                    <!--placeholder="选择日期" style="width: 100%;">-->
                            <!--</el-date-picker>-->
                        <!--</el-col>-->
                        <el-col :span="3" style="text-align: left;margin-left: 30px;">
                            <el-button size="mini" type="primary" v-on:click="getsbyjlb_vue">
                                <i class="iconfont icon-chaxun"></i>查询</el-button>
                        </el-col>
                    </el-form-item>
                </el-form>
            </el-col>

            <el-col :span="24" class="toolbar">
                <!--列表-->
                <el-table :data="row" highlight-current-row v-loading="listLoading" style="width: 100%;" height="400"
                          :stripe="true" :row-style="rowClass" :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
                    <el-table-column property="xmbh" label="项目编号" width="200" align="center"></el-table-column>
                    <el-table-column property="xmmc" label="项目名称" align="center"></el-table-column>
                    <el-table-column property="azlxname" label="安装类型" align="center"></el-table-column>
                    <el-table-column property="dwmc" label="报建单位" align="center"></el-table-column>
                    <el-table-column property="lxr" label="联系人" align="center"></el-table-column>
                    <el-table-column property="lxrsjh" label="联系方式" align="center"></el-table-column>
                    <el-table-column property="lxsj" label="立项时间" width="160" align="center" :formatter="formatLxsj"></el-table-column>
                    <el-table-column property="sjyssj" label="验收时间" width="160" align="center" :formatter="formatYssj"></el-table-column>
                    <!--<el-table-column property="xmdz" label="工程地址" width="220" align="center"></el-table-column>-->
                    <el-table-column fixed="right" label="操作" width="100" align="center">
                        <template slot-scope="scope">
                            <label title="移交">
                                <i class="iconfont icon-tuxing"  @click="sbyjdlb_vue(scope.$index, scope.row)"
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
    import { getSbyjList,findAzlx } from '../../api/sbyj';
    export default {
        name: "Sbyjxmlb",
        data() {
            return {
                filters:{
                    xmbh:''
                    ,xmmc:''
                    // ,kssj:''
                    // ,jssj:''
                    ,gcdz:''
                },
                row:[],
                total: 0,//表格数据总数量
                page: 1,//当前页码
                size: 15,//每页容量
                listLoading:false,
                azlx:[],
                azlxList:[],
            }
        },
        mounted() {
            this.getsbyjlb_vue();
            this.findAzlx_vue();
        },
        created() {
            let lett = this;
            document.onkeydown = function(e) {
                var key = window.event.keyCode;
                if (key == 13) {
                    lett.getsbyjlb_vue();
                }
            }
        },
        methods: {
            //查询
            getsbyjlb_vue() {
                let params = Object.assign({page:this.page-1,size:this.size,azlx:this.azlx[1]},this.filters);
                getSbyjList(params).then(res => {
                    this.row = res.data.list;
                    this.total = res.data.total;
                });
            },
            //查询安装类型下拉框数据
            findAzlx_vue(){
                let params = {};
                findAzlx(params).then(res=>{
                    this.azlxList = res.data.list;
                });
            },
            sbyjdlb_vue:function (index, row) {
                this.$router.push({path: '/sbyjdlb', query: {xmid:row.id,xmmc:row.xmmc,xmbh:row.xmbh,gclx:row.azlxname,jsdw:row.dwmc }});
            },
            //立项时间格式转换
            formatLxsj: function (row, column) {
                let sj = row.lxsj;
                return this.publicUtil.formatChange(sj);
            },
            formatYssj: function (row, column) {
                let sj = row.sjyssj;
                return this.publicUtil.formatChange_date(sj);
            },
            handleCurrentChange(val) {
                this.page = val;
                this.getsbyjlb_vue();
            },
            handleSizeChange(val) {
                this.size = val;
                this.page = 1;
                this.getsbyjlb_vue();
            },
            rowClass:function (row,index) {
                return {"height":"6.0vh"}
            }
        }
    }
</script>

<style scoped>

</style>