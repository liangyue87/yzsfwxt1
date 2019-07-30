<template>
    <section>
        <el-row>
            <!--工具条-->
            <el-col :span="24" class="toolbar" style="border: #f5f7fa solid 1px" >
                <el-form :model="filters" >
                    <el-form-item>
                        <el-col :span="2" style="text-align: right">
                            <span>科室名称&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-select v-model="filters.ksmc" size="small" style="width: 100%;" clearable
                                       placeholder="请选择" @change="findAzlx(filters.ksmc)">
                                <el-option v-for="item in sqList"
                                           :key="item.VALUE"
                                           :label="item.LABEL"
                                           :value="item.VALUE"></el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="2" style="text-align: right">
                            <span>安装类型&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-select v-model="filters.azlx" size="small" style="width: 100%;" clearable
                                       placeholder="请选择">
                                <el-option v-for="item in azList"
                                           :key="item.VALUE"
                                           :label="item.LABEL"
                                           :value="item.VALUE"></el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="2" style="text-align: right">
                            <span>项目编号&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="filters.xmbh"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="2" style="text-align: right">
                            <span>项目名称&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="filters.xmmc"></el-input>
                        </el-col>
                        <!--<el-col :span="2" style="text-align: right">
                            <span>项目经理&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="filters.xmfzr"></el-input>
                        </el-col>-->
                        <el-col :span="2" style="text-align: right">
                            <span>编制时间&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-date-picker type="date" placeholder="选择开始时间" size="small"
                                            value-format="yyyy-MM-dd" v-model="filters.kssj" style="width: 100%;">
                            </el-date-picker>
                        </el-col>
                        <el-col :span="2" style="text-align: center;">
                            ——
                        </el-col>
                        <el-col :span="5">
                            <el-date-picker type="date" placeholder="选择结束时间" size="small"
                                            value-format="yyyy-MM-dd" v-model="filters.jssj" style="width: 100%;">
                            </el-date-picker>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="2" style="text-align: right">
                            <span>项目经理&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="filters.xmfzr"></el-input>
                        </el-col>
                        <el-col :span="5" style="text-align: center;margin-left: 25px;">
                            <el-button size="mini" type="primary" @click="getList">
                                <i class="iconfont icon-chaxun"></i>查询</el-button>
                            <el-button size="mini" type="success" @click="dc_vue">
                                <i class="iconfont icon-yingyongtubiaodaochu"></i>导出</el-button>
                        </el-col>
                    </el-form-item>
                </el-form>
            </el-col>

            <!--列表-->
            <el-col :span="24" class="toolbar">
                <el-table id="out-table" :data="tableData" :span-method="objectSpanMethod" border highlight-current-row v-loading="listLoading"
                          style="width: 100%;" height="400" :row-style="rowClass"
                          :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
                    <el-table-column property="KSMC" label="科室名称" width="120" align="center">
                    </el-table-column>
                    <el-table-column property="AZLXNAME" label="安装类型" width="120" align="center">
                    </el-table-column>
                    <el-table-column property="XMBH" label="项目编号" width="200" align="center">
                    </el-table-column>
                    <el-table-column property="XMMC" label="项目名称" width="180" align="center">
                    </el-table-column>
                    <el-table-column property="XMFZR" label="项目负责人" width="125" align="center">
                    </el-table-column>
                    <el-table-column property="DWMC" label="报建单位" width="180" align="center">
                    </el-table-column>
                    <el-table-column property="YSZFY" label="预算总费用" width="140" align="center">
                    </el-table-column>
                    <el-table-column property="TZDBH" label="收费通知单编号" width="180" align="center">
                    </el-table-column>
                    <el-table-column property="ZDSJ" label="编制时间" width="180" align="center">
                    </el-table-column>
                    <el-table-column property="YSJE" label="应收金额" width="180" align="center">
                    </el-table-column>
                    <el-table-column property="TZDZTNAME" label="是否确认到账" width="120" align="center">
                    </el-table-column>
                    <el-table-column property="SSJE" label="实收金额" width="180" align="center">
                    </el-table-column>
                    <el-table-column property="SFKP" label="是否开票" width="125" align="center">
                    </el-table-column>
                    <el-table-column property="PJLXNAME" label="票据类型" width="120" align="center">
                    </el-table-column>
                    <el-table-column property="PJH" label="票据号" width="180" align="center">
                    </el-table-column>
                </el-table>
            </el-col>
            <el-col :span="24" class="toolbar" >
                <!--工具条-->
                <el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[10, 15, 30, 50,100]"
                               @current-change="handleCurrentChange" @size-change="handleSizeChange"
                               :current-page.sync = "this.page"
                               :page-size="this.size" style="float:right;" :total="this.total">
                </el-pagination>

            </el-col>
        </el-row>
    </section>
</template>

<script>
    import { querysfkp } from '../../../api/sbyj';
    import { getSclx, queryUser } from '../../../api/xqsl';
    import FileSaver from "file-saver";
    import XLSX from "xlsx";

    export default {
        name: "sfkp",
        data() {
            return {
                page : 1,
                size : 15,
                tableData:[],
                total: 0,
                listLoading:false,
                azList: [],//安装类型下拉框数据集合
                sqList: [],//申请类型下拉框数据集合
                typeNameArr: [],
                typeNamePos: 0,
                storeArr: [],
                storePos: 0,
                feeArr: [],
                feePos: 0,
                filters: {
                    ksmc :'',
                    azlx :'',
                    xmmc :'',
                    xmbh :'',
                    xmfzr :'',
                    kssj :'',
                    jssj :''
                }
            }
        },
        methods:{
            //列表查询
            getList(){
                let params = Object.assign({ page: this.page, size: this .size }, this.filters);
                querysfkp(params).then(res=>{
                    this.tableData = res.data.row;
                    this.total = res.data.total;
                    this.merage();
                });
            },
            //获取当前用户/获得申请类型集合
            findKsmc() {
                let param = {
                    type: 'fwxz'
                };
                queryUser(param).then((res) => {
                    this.sqList = res.data.sqList;
                });
            },
            findAzlx() {
                let params = {sqlx: this.filters.ksmc,id:this.sqzbid};
                getSclx(params).then((res) => {
                    this.azList = res.data.azList;
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
            },
            //to下载表格
            dc_vue(){
                // 设置当前日期
                let time = new Date();
                let year = time.getFullYear();
                let month = time.getMonth() + 1;
                let day = time.getDate();
                let name = year + "" + month + "" + day;
                /* 从表生成工作簿对象 */
                var wb = XLSX.utils.table_to_book(document.querySelector("#out-table"));
                /* 获取二进制字符串作为输出 */
                var wbout = XLSX.write(wb, {
                    bookType: "xlsx",
                    bookSST: true,
                    type: "array"
                });
                try {
                    FileSaver.saveAs(
                        //Blob 对象表示一个不可变、原始数据的类文件对象。
                        //Blob 表示的不一定是JavaScript原生格式的数据。
                        //File 接口基于Blob，继承了 blob 的功能并将其扩展使其支持用户系统上的文件。
                        //返回一个新创建的 Blob 对象，其内容由参数中给定的数组串联组成。
                        new Blob([wbout], { type: "application/octet-stream" }),
                        //设置导出文件名称
                        "综合查询_收费开票"+name+".xlsx"
                    );
                } catch (e) {
                    if (typeof console !== "undefined") console.log(e, wbout);
                }
                return wbout;
            },
            merageInit() {
                this.typeNameArr = [];
                this.typeNamePos = 0;
                this.storeArr = [];
                this.storePos = 0;
                this.feeArr = [];
                this.feePos = 0;
            },
            merage() {
                this.merageInit();
                for (let i = 0; i < this.tableData.length; i += 1) {
                    if (i === 0) {
                        // 第一行必须存在
                        this.typeNameArr.push(1);
                        this.typeNamePos = 0;
                        this.storeArr.push(1);
                        this.storePos = 0;
                        this.feeArr.push(1);
                        this.feePos = 0;
                    } else {
                        // 判断当前元素与上一个元素是否相同,eg：this.typeNamePos 是 this.typeNameArr序号
                        // 第一列
                        // eslint-disable-next-line no-lonely-if
                        if (this.tableData[i].XMBH === this.tableData[i - 1].XMBH) {
                            this.typeNameArr[this.typeNamePos] += 1;
                            this.typeNameArr.push(0);
                        } else {
                            this.typeNameArr.push(1);
                            this.typeNamePos = i;
                        }
                    }
                }
            },
            objectSpanMethod({ row, column, rowIndex, columnIndex }) {
                //row 当前行；column 当前列；rowIndex 当前行号；columnIndex 当前列号
                if (columnIndex === 0 || columnIndex === 1 || columnIndex === 2 ||
                    columnIndex === 3 || columnIndex === 4 || columnIndex === 5 || columnIndex === 6) {
                    // 第一列的合并方法
                    const row1 = this.typeNameArr[rowIndex];
                    const col1 = row1 > 0 ? 1 : 0; // 如果被合并了row = 0; 则他这个列需要取消
                    return {
                        rowspan: row1,
                        colspan: col1,
                    };
                }
            },
            rowClass:function (row,index) {
                return {"height":"6.0vh"}
            }
        },
        mounted() {
            this.findKsmc();
            this.getList();
        }
    }
</script>

<style scoped>

</style>