<template>
    <section>
        <el-row>
            <!--工具条-->
            <el-col :span="24" class="toolbar" style="border: #f5f7fa solid 1px" >
            <el-form  :model="filters" >
            <el-form-item>
            <el-col :span="2" style="text-align: right">
            <span>工程名称：</span>
            </el-col>
            <el-col :span="5">
            <el-input size="small" v-model="filters.xmmc"></el-input>
            </el-col>
            <el-col :span="2" style="text-align: right">
            <span>工程地址：</span>
            </el-col>
            <el-col :span="5">
            <el-input size="small" v-model="filters.xmdz"></el-input>
            </el-col>
            <el-col :span="2" style="text-align: right">
            <span>联系人：</span>
            </el-col>
            <el-col :span="5">
            <el-input size="small" v-model="filters.lxr"></el-input>
            </el-col>
            </el-form-item>
            <el-form-item>
            <el-col :span="2" style="text-align: right">
            <span>状态：</span>
            </el-col>
            <el-col :span="5">
            <el-select size="small" v-model="filters.status" clearable  placeholder="请选择" style="width:100%">
            <el-option label="正在进行" value="1"></el-option>
            <el-option label="已经完成" value="2"></el-option>
            <el-option label="任务作废" value="-1"></el-option>
            </el-select>
            </el-col>
            <el-col :span="2" style="text-align: right">
            <span>受理时间：</span>
            </el-col>
            <el-col :span="5">
            <el-date-picker size="small" type="date" value-format="yyyy-MM-dd HH:mm"  placeholder="选择日期" v-model="filters.kssj" style="width: 100%;"></el-date-picker>
            </el-col>
            <el-col class="line" :span="2" style="text-align: center">-</el-col>
            <el-col :span="5">
            <el-date-picker size="small" type="date" value-format="yyyy-MM-dd HH:mm" placeholder="选择日期" v-model="filters.jssj" style="width: 100%;"></el-date-picker>
            </el-col>
            <el-col :span="3" style="text-align: center">
            <el-button size="small" type="primary" v-on:click="getGwxxLb_vue">查询</el-button>
            </el-col>
            </el-form-item>
            </el-form>
            </el-col>
            <el-col :span="24" class="toolbar">
                <!--列表-->
                <el-table :data="row" highlight-current-row v-loading="listLoading" style="width: 100%;" height="400"
                          :stripe="true" :row-style="rowClass" :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
                    <el-table-column type="index" label="序号" width="50" align="center">
                    </el-table-column>
                    <el-table-column property="xmbh" label="项目/申请编号" align="center">
                    </el-table-column>
                    <el-table-column property="lxr" label="联系人" align="center">
                    </el-table-column>
                    <el-table-column property="lxrsjh" label="联系方式" align="center">
                    </el-table-column>
                    <el-table-column property="slsj" label="受理时间" align="center">
                    </el-table-column>
                    <el-table-column property="dwmc" label="单位名称" align="center">
                    </el-table-column>
                    <el-table-column property="xmdz" label="工程地址" header-align="center" align="left">
                    </el-table-column>
                    <el-table-column label="操作" fixed="right" width="150" >
                        <template slot-scope="scope">
                            <label title="造价洽谈反馈结果录入">
                                <i class="iconfont icon-iconfontichomeyy"  @click="handleEdit(scope.$index, scope.row)"
                                   style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                            </label>
                        </template>
                    </el-table-column>
                </el-table>
            </el-col>
            <el-col :span="24" class="toolbar" >
                <el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[10, 15, 30, 50,100]"
                               @current-change="handleCurrentChange" @size-change="handleSizeChange"
                               :page-size="this.size" style="float:right;" :total="this.total">
                </el-pagination>
            </el-col>
        </el-row>
    </section>
</template>

<script>
    import {getZjqtFkList} from '../../api/yszjqt'
    import util from '../../common/js/util'
    export default {
        name: "ZjqtFkList",
        data() {
            return {
                filters: {
                    xmmc: '',
                    xmdz: '',
                    lxr: '',
                    status: '',
                    kssj: '',
                    jssj: ''
                },
                row: [],
                total: 10,//表格数据总数量
                page: 1,//当前页码
                size: 15,//每页容量
                nodeid: '30001',
                listLoading: false
            }
        },
        methods: {
            //显示转换 状态 1 正在进行 2 已经完成 3 任务作废
            formatStatus: function (row, colum) {
                // return row.status=="1" ? '正在进行':'已经完成';
                if (row.status == "1") {
                    return "正在进行";
                } else if (row.status == "2") {
                    return "已经完成";
                } else if (row.status == "-1") {
                    return "任务作废";
                }
            },
            getGwxxLb_vue() {
                let end = new Date();
                let start = new Date();
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);//一个月前的时间
                this.filters.jssj = util.formatDate.format(end,'yyyy-MM-dd hh:mm:ss');
                this.filters.kssj = util.formatDate.format(start,'yyyy-MM-dd hh:mm:ss');
                let param = {
                    page: this.page - 1,
                    size: this.size,
                    nodeid: this.nodeid,
                    xmmc: this.filters.xmmc,
                    xmdz: this.filters.xmdz,
                    lxr: this.filters.lxr,
                    status: this.filters.status,
                    kssj: this.filters.kssj,
                    jssj: this.filters.jssj
                }
                getZjqtFkList(param).then((res) => {
                    this.total = res.data.total;
                    this.row = res.data.row;
                    this.listLoading = false;
                });
            },
            //跳转至造价洽谈反馈录入界面
            handleEdit: function (index, row) {
                this.$router.push({path: '/YSZjqtFk', query: {xmid: row.id,refid:row.refid}});//传值 id
            },
            handleCurrentChange(val) {
                this.page = val;
                this.getGwxxLb_vue();
            },
            handleSizeChange(val) {
                this.size = val;
                this.page = 1;
                this.getGwxxLb_vue();
            },
            rowClass:function (row,index) {
                return {"height":"6.0vh"}
            }
        },
        mounted() {
            this.getGwxxLb_vue();
        }
    }
</script>

<style scoped>
    #row .el-row{
        margin-top: 10px;
    }
</style>
