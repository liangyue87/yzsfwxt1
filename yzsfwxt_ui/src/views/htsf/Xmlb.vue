<template>
    <section>
        <!--条件查询-->
        <el-col :span="24" class="toolbar" style="border: #f5f7fa solid 1px">
            <el-form :model="HtForm">
                <el-form-item>
                    <el-col :span="2" style="text-align: right">
                        <span>项目编号&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="5">
                        <el-input v-model="HtForm.xmbh" size="small" placeholder="输入项目编号"></el-input>
                    </el-col>
                    <el-col :span="2" style="text-align: right">
                        <span>项目名称&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="5">
                        <el-input v-model="HtForm.xmmc" size="small" placeholder="输入项目名称"></el-input>
                    </el-col>
                    <el-col :span="2" style="text-align: right">
                        <span>项目时间&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="5">
                        <el-date-picker
                                v-model="HtForm.date1"
                                type="daterange"
                                unlink-panels
                                range-separator="至"
                                start-placeholder="日期范围"
                                end-placeholder="日期范围"
                                size="small">
                        </el-date-picker>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-col :span="2" style="text-align: right">
                        <span>联系人&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="5">
                        <el-input v-model="HtForm.lxr" size="small" placeholder="输入联系人"></el-input>
                    </el-col>
                    <el-col :span="2" style="text-align: right">
                        <span>项目地址&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="5">
                        <el-input v-model="HtForm.xmdz" size="small" placeholder="输入项目地址"></el-input>
                    </el-col>
                    <el-col :span="2" style="text-align: right">
                        <span>安装类型&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="5">
                        <el-cascader
                                :options="azlxList"
                                :show-all-levels="false"
                                v-model="HtForm.azlx"
                                :clearable="true"
                                style="width: 100%"
                                size="small"
                                placeholder="全部"
                        ></el-cascader>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-col :span="2" style="text-align: right">
                        <span>建设单位&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="5">
                        <el-input v-model="HtForm.jsdw" size="small" placeholder="输入建设单位"></el-input>
                    </el-col>
                    <el-col :span="2" style="text-align: right">
                        <span>甲方单位&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="5">
                        <el-input v-model="HtForm.jfdw" size="small" placeholder="输入甲方单位"></el-input>
                    </el-col>
                    <el-col :span="2" style="text-align: right">
                        <span>是否编制&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="5">
                        <el-select v-model="HtForm.sfbz" style="width:260px;" size="small"
                                   placeholder="请选择" :clearable="true">
                            <el-option label="未编制" value="0"></el-option>
                            <el-option label="已编制" value="1"></el-option>
                        </el-select>
                    </el-col>
                    <el-col :span="2" style="text-align: center;margin-left: 25px;">
                        <el-button size="mini" type="primary" @click="getList">
                            <i class="iconfont icon-chaxun"></i>查询</el-button>
                    </el-col>
                </el-form-item>
            </el-form>
        </el-col>
        <el-col :span="24" class="toolbar">
            <!-- 查询结果  -->
            <el-table :data="htlist" fit style="width: 100%" highlight-current-row height="400"
                      :stripe="true" :row-style="rowClass" :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
                <el-table-column property="xmbh" label="项目编号" width="200" align="center"></el-table-column>
                <el-table-column property="vazlx" label="安装类型" align="center"></el-table-column>
                <el-table-column property="lxr" label="联系人" width="100" align="center"></el-table-column>
                <el-table-column property="lxrsjh" label="联系人手机" align="center"></el-table-column>
                <el-table-column property="xmmc" label="项目名称" width="200" header-align="center" align="left"></el-table-column>
                <el-table-column property="dwmc" label="单位名称" align="center"></el-table-column>
                <el-table-column property="lxsj" label="立项时间" width="160" align="center"
                                 :formatter="formatLxsj"></el-table-column>
                <!--<el-table-column property="xmdz" label="项目地址" width="300" align="left"></el-table-column>-->
                <el-table-column fixed="right" label="操作" width="100" align="center">
                    <template slot-scope="scope">
                        <label title="合同编制">
                            <i class="iconfont icon-hetong"  @click="toHtbz(scope.$index, scope.row)"
                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                        </label>
                    </template>
                </el-table-column>
            </el-table>
        </el-col>
        <!--分页-->
        <el-col :span="24" class="toolbar" >
            <!--工具条-->
            <el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[10, 15, 30, 50,100]"
                           @current-change="handleCurrentChange" @size-change="handleSizeChange"
                           :page-size="this.size" style="float:right;" :total="this.total">
            </el-pagination>
        </el-col>
    </section>
</template>

<script>
    import util from '../../common/js/util';
    import Vue from 'vue';
    import { Tree } from 'element-ui';
    Vue.use(Tree);
    import { getHtbzList,queryHtbz,findAzlx } from '../../api/htsf';

    export default {
        data() {
            return {
                htlist: [],//数据
                HtForm: { //表单
                    date1: '', //时间范围
                    lxr:'', //联系人
                    xmdz:'', //项目地址
                    azlx:[], //项目类型
                    xmmc:'', //项目名称
                    xmbh: '', //项目编号
                    jsdw:'',//建设单位
                    jfdw:'',//甲方单位
                    sfbz:'',//是否编制
                },
                page: 1, //初始页
                size: 15, //  每页的数据
                total:10,
                azlxList:[],//安装类型集合
            }
        },
        created() {
            let lett = this;
            document.onkeydown = function(e) {
                var key = window.event.keyCode;
                if (key == 13) {
                    lett.getList();
                }
            }
        },
        methods: {
            //查询
            getList() {
                let params = {
                    xmbh:this.HtForm.xmbh,//项目编号
                    xmmc:this.HtForm.xmmc,//项目名称
                    lxr:this.HtForm.lxr,//联系人
                    xmdz:this.HtForm.xmdz,//项目地址
                    azlx:this.HtForm.azlx,
                    jsdw:this.HtForm.jsdw,
                    jfdw:this.HtForm.jfdw,
                    sfbz:this.HtForm.sfbz == '' || this.HtForm.sfbz == null ? '2':this.HtForm.sfbz,
                    page:this.page -1,
                    size:this.size,
                };
                if(this.HtForm.date1 != null && this.HtForm.date1 != ''){
                    params['beginTime'] = this.HtForm.date1[0];
                    params['endTime'] = this.HtForm.date1[1];
                }
                if(this.HtForm.azlx != ''){
                    params['azlxname'] = this.HtForm.azlx[1];
                }
                getHtbzList(params).then(res => {
                    this.htlist = res.list;
                    this.total = res.total;
                });
            },
            //查询安装类型下拉框数据
            findAzlx_vue(){
                let params = {};
                findAzlx(params).then(res=>{
                    this.azlxList = res.data.list;
                });
            },
            //跳转至合同编制界面
            toHtbz(index, row){
                let params = {
                    xmid : row.id
                };
                queryHtbz(params).then(res=>{
                    if(res.isEdit == 0){
                        this.$router.push({path: '/Htbz', query: {xmid:row.id}})
                    }else{
                        this.$router.push({path: '/Htbz', query: {xmid:row.id,isEdit:"1",ehtzbid:res.list[0].ID}})
                    }
                });
            },
            test(){
                this.getList();
            },
            //立项时间格式转换
            formatLxsj: function (row, column) {
                let sj = row.lxsj;
                return this.publicUtil.formatChange(sj);
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
            rowClass:function (row,index) {
                return {"height":"6.0vh"}
            }
        },
        mounted() {
            this.getList();
            this.findAzlx_vue();
        }

    }
</script>
