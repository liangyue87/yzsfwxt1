<template>
    <section>
        <el-row>
            <!--会审结果录入-->
            <!--工具条-->
            <el-col :span="24" class="toolbar" style="border: #f5f7fa solid 1px">
                <el-form :model="filters" style="margin-top: 10px">
                    <el-form-item>
                        <el-col :span="2" style="text-align: right">
                            <span>项目编号 &nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input v-model="filters.xmbh" size="small "
                                      @keyup.enter.native="getHsjglr_vue(1)"></el-input>
                        </el-col>
                        <el-col :span="2" style="text-align: right">
                            <span>项目名称 &nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input v-model="filters.xmmc" size="small "
                                      @keyup.enter.native="getHsjglr_vue(1)"></el-input>
                        </el-col>
                        <el-col :span="2" style="text-align: right">
                            <span>会审状态 &nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-select v-model="filters.dqxmzt"  placeholder="请选择" style="width:100%"
                                       size="small ">
                                <el-option label="全部" value="0"></el-option>
                                <el-option label="正在进行" value="1"></el-option>
                                <el-option label="已经完成" value="2"></el-option>
                                <!--  <el-option label="任务作废" value="-1"></el-option>-->
                            </el-select>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="2" style="text-align: right">
                            <span>用户联系人 &nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input v-model="filters.lxr" size="small "
                                      @keyup.enter.native="getHsjglr_vue(1)"></el-input>
                        </el-col>
                        <el-col :span="2" style="text-align: right">
                            <span>立项日期 &nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-date-picker type="date" value-format="yyyy-MM-dd HH:mm" size="small " placeholder="选择日期"
                                            v-model="filters.kssj" style="width: 100%;"></el-date-picker>
                        </el-col>
                        <el-col class="line" :span="2" style="text-align: center">-</el-col>
                        <el-col :span="5">
                            <el-date-picker type="date" value-format="yyyy-MM-dd HH:mm" size="small " placeholder="选择日期"
                                            v-model="filters.jssj" style="width: 100%;"></el-date-picker>
                        </el-col>
                        <el-col :span="3" style="text-align: center">
                            <el-button size="mini" type="primary" v-on:click="getHsjglr_vue(1)"><i
                                    class="iconfont icon-chaxun"></i>查询
                            </el-button>

                        </el-col>
                    </el-form-item>
                </el-form>
            </el-col>
            <el-col :span="24" class="toolbar">
                <!--列表-->
                <el-table :data="row" v-loading="listLoading" style="width: 100%;" height="500px" :row-style="rowClass"
                          :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
                    <el-table-column prop="xmbh" label="项目编号" width="200" align="center">
                    </el-table-column>
                    <el-table-column prop="xmmc" label="项目名称" width="200" align="center">
                    </el-table-column>
                    <el-table-column prop="dqxmzt" label="会审状态" width="150" :formatter="formatStatus" align="center">
                    </el-table-column>
                    <el-table-column prop="azlx_vue" label="安装类型" width="150" align="center">
                    </el-table-column>
                    <el-table-column prop="lxr" label="用户联系人" width="150" align="center">
                    </el-table-column>
                    <el-table-column prop="lxsj" label="立项日期" width="200" align="center">
                    </el-table-column>
                    <el-table-column prop="lxrsjh" label="联系电话" width="200" align="center">
                    </el-table-column>
                    <!--<el-table-column prop="xmdz" label="工程地址" width="200" align="center">
                    </el-table-column>-->
                    <!-- <el-table-column prop="dwmc" label="报建单位" width="150" align="center">
                     </el-table-column>-->
                    <el-table-column label="操作" width="150" align="center" fixed="right">
                        <template slot-scope="scope">
                            <label v-if="scope.row.dqxmzt=='1'" title="会审结果录入"
                                   @click="handleEdit(scope.$index, scope.row)">
                                <i class="iconfont icon-huishenjieguolvru"
                                   style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                            <label v-else-if="scope.row.dqxmzt=='2'" title="查看会审结果"
                                   @click="handleEdit_1(scope.$index, scope.row)">
                                <i class="iconfont icon-huishenjieguolvru"
                                   style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                            <!-- <el-button size="small" type="primary" @click="handleEdit(scope.$index, scope.row)"><i class="iconfont icon-tiaocha"></i>发起</el-button>-->
                        </template>
                    </el-table-column>
                </el-table>
            </el-col>
            <el-col :span="24" class="toolbar">
                <!--工具条-->
                <el-pagination background layout="total, prev, pager, next, sizes , jumper"
                               :current-page.sync="this.page"
                               @current-change="handleCurrentChange" @size-change="handleSizeChange"
                               :page-sizes="[10, 15, 30, 50,100]"
                               :page-size="this.size" :total="this.total" style="float:right;">
                </el-pagination>
            </el-col>

        </el-row>
    </section>
</template>
<script>

    import {getHsjglr} from '../../api/sjst';

    export default {
        data() {
            return {
                filters: {
                    xmmc: '',
                    xmbh: '',
                    lxr: '',
                    dqxmzt: '0',
                    kssj: '',
                    jssj: ''
                },
                row: [],
                row_1: [],
                total: 10,//表格数据总数量
                page: 1,//当前页码
                size: 10,//每页容量
                nodeid: '30001',
                listLoading: false,
            }
        },
        methods: {
            //显示转换 状态 1 正在进行 2 已经完成 3 任务作废
            formatStatus: function (row, colum) {
                // return row.status=="1" ? '正在进行':'已经完成';
                if (row.dqxmzt == "1") {
                    return "正在进行";
                } else if (row.dqxmzt == "2") {
                    return "已经完成";
                } else if (row.dqxmzt == "-1") {
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
            rowClass: function (row, index) {
                return {"height": "6.0vh"}
            },
            //获取项目列表
            getHsjglr_vue(flag) {
                if (flag == 1) {
                    this.page = 1
                }
                let param = {
                    page: this.page,
                    size: this.size,
                    nodeid: this.nodeid,
                    xmmc: this.filters.xmmc,
                    xmdz: this.filters.xmdz,
                    lxr: this.filters.lxr,
                    dqxmzt: this.filters.dqxmzt,
                    kssj: this.filters.kssj,
                    jssj: this.filters.jssj,
                    xmbh: this.filters.xmbh

                };
                getHsjglr(param).then((res) => {
                    if (res.data.total > 0 && res.data.list.length <= 0) {
                        this.getList();
                    }
                    console.log(this.filters.dqxmzt);
                    if (this.filters.dqxmzt != "0") {
                        this.row = res.data.list.filter(item => item.dqxmzt == this.filters.dqxmzt);
                        /* for (let i = 0; i < this.row.length; i++) {
                             this.row[i].remove(this.dqxmzt);
                         }*/

                    } else {
                        this.row = res.data.list;
                    }
                    this.total = res.data.total;


                    // this.listLoading = false;
                });
            },
            //跳转至发起委托设计界面
            handleEdit: function (index, row) {
                localStorage.setItem("xmid", row.id);
                this.$router.push({path: '/Hsjglrsj', query: {xmid: row.id}});

            },
            //跳转至发起委托设计界面
            handleEdit_1: function (index, row) {
                localStorage.setItem("xmid", row.id);
                this.$router.push({path: '/Ckhsjglr', query: {xmid: row.id}});

            }
        },
        mounted() {
            this.getHsjglr_vue();

        }
    }

</script>