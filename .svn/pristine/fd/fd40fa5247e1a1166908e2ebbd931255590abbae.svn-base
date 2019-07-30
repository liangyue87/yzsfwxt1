<template>
    <section>
        <el-row>
            <el-col :span="24" >
                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">选择下单范围</div>
                    </div>
                    <div v-if="isEdit == '1' || isEdit == '0'">
                        <el-form :inline="true">
                            <el-form-item>
                                <el-button size="mini" type="primary" @click="openXdsb">
                                    <i class="iconfont icon-xuanze"></i>选择下单水表</el-button>
                            </el-form-item>
                            <el-form-item>
                                <el-button size="mini" type="primary" @click="openGwxx">
                                    <i class="iconfont icon-xuanze"></i>选择管网</el-button>
                            </el-form-item>
                            <el-form-item>
                                <el-button size="mini" type="primary" @click="openWlxx">
                                    <i class="iconfont icon-yulan_f"></i>物料信息</el-button>
                            </el-form-item>
                            <el-form-item>
                                <el-button size="mini" type="primary" @click="openXmfw">
                                    <i class="iconfont icon-yulan_f"></i>项目范围</el-button>
                            </el-form-item>

                        </el-form>
                    </div>
                    <div v-else>
                        <el-form :inline="true">
                            <el-form-item>
                                <el-button size="mini" type="primary" @click="openXdsb(1)">
                                    <i class="iconfont icon-xuanze"></i>选择下单水表</el-button>
                            </el-form-item>
                            <el-form-item>
                                <el-button size="mini" type="primary" @click="openGwxx">
                                    <i class="iconfont icon-xuanze"></i>选择管网</el-button>
                            </el-form-item>
                        </el-form>
                    </div>
                    <el-form ref="xdform" :model="xdform">
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>庭院&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="7">
                                <el-input size="small" v-model="xdform.srty" style="width:412px;" placeholder="输入庭院" :readonly="tyzd"></el-input>
                            </el-col>
                            <el-col :span="4" style="text-align: right">
                                <span>泵房&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="7">
                                <el-input size="small" v-model="xdform.srbf" style="width:206px;" placeholder="输入泵房" :readonly="bfzd"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>立管&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="7">
                                <el-input size="small" v-model="xdform.srlg" style="width:412px;" placeholder="输入立管" :readonly="lgzd"></el-input>
                            </el-col>
                            <el-col :span="4" style="text-align: right">
                                <span>吊管&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="7">
                                <el-input size="small" v-model="xdform.srdg" style="width:206px;" placeholder="输入吊管" :readonly="dgzd"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>工商户表&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="7">
                                <el-input size="small" v-model="xdform.srgs" style="width:412px;" placeholder="请选择工商户表" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="4" style="text-align: right">
                                <span>计划竣工时间&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="7">
                                <el-date-picker
                                        :readonly="jhjgsjzd"
                                        v-model="xdform.jhjgsj"
                                        type="date"
                                        format="yyyy-MM-dd"
                                        value-format="yyyy-MM-dd"
                                        placeholder="计划竣工时间"
                                        size="small"
                                        style="width:206px;"
                                >
                                </el-date-picker>
                            </el-col>

                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>居民户表&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="7">
                                <el-input size="small" v-model="xdform.srjm" style="width:412px;" placeholder="请选择居民户表" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="4" style="text-align: right">
                                <span>管网&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="7">
                                <el-input size="small" v-model="xdform.gwsl" style="width:206px;" placeholder="请选择管网" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>备注&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="7">
                                <el-input size="small" v-model="xdform.srbz" style="width:412px;" placeholder="输入备注" :readonly="bzzd"></el-input>
                            </el-col>
                            <el-col :span="4" style="text-align: right">
                                <span>其他&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="7">
                                <el-input size="small" v-model="xdform.srqt" style="width:206px;" placeholder="输入其他" :readonly="qtzd"></el-input>
                            </el-col>
                        </el-form-item>
                    </el-form>
                </el-card>
            </el-col>


            <el-dialog title="项目范围" width="60%" :visible.sync ="xmfwFormVisible">
                <!--项目范围模板-->
                <SGXDXMFW ref="xmfw" ></SGXDXMFW>
            </el-dialog>
            <el-dialog title="物料信息" :visible.sync ="wlxxFormVisible">
                <el-row>
                    <el-col>
                        <el-table :data="wlxx" highlight-current-row v-loading="listLoading" height="250" style="width: 100%;">
                            <el-table-column type="index" label="序号" width="50" align="center">
                            </el-table-column>
                            <el-table-column property="WLBM" label="物料编码" width="100" align="center">
                            </el-table-column>
                            <el-table-column property="WLMC" label="物料名称" width="100" align="center">
                            </el-table-column>
                            <el-table-column property="GGXH" label="规格型号" width="100" align="center">
                            </el-table-column>
                            <el-table-column property="JBJLDW" label="基本计量单位" width="140" align="center">
                            </el-table-column>
                            <el-table-column property="WLJBFL" label="物料基本分类" width="140" align="center">
                            </el-table-column>
                            <el-table-column property="XYSL" label="所需数量" width="90" align="center">
                            </el-table-column>
                        </el-table>
                    </el-col>
                    <el-col>
                        <el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[5 ,10 ,20, 50]"
                                       @current-change="wlhandleCurrentChange" @size-change="wlhandleSizeChange"
                                       :page-size="this.wlsize" style="float:right;" :total="this.wltotal">
                        </el-pagination>
                    </el-col>
                </el-row>
            </el-dialog>
            <el-dialog title="水表信息" :visible.sync ="xdsbFormVisible" width="70%" @close = "xdsbClose">
                <el-tabs type="card">
                    <el-tab-pane label="五级计量表">
                        <el-table ref="wbmultipleTable" :data="wjjlb" height="300"  highlight-current-row
                                  v-loading="sblistLoading" @selection-change="wbselsChange" style="width: 100%;">
                            <div v-if="isEdit == '1' || isEdit == '0'">
                                <el-table-column type="selection" width="50" align="center" :selectable='checkboxInit'>
                                </el-table-column>
                            </div>
                            <el-table-column property="HH" label="报装户号" align="center">
                            </el-table-column>
                            <el-table-column property="HM" label="户名" align="center">
                            </el-table-column>
                            <!--<el-table-column property="HM" label="安装类型" align="center">-->
                            <!--</el-table-column>-->
                            <el-table-column property="DZ" label="装表地址" align="center">
                            </el-table-column>
                            <el-table-column property="KJ" label="口径" align="center">
                            </el-table-column>
                            <el-table-column property="MPH" label="门牌号" align="center">
                            </el-table-column>
                            <el-table-column property="YSXZNAME" label="用水性质" align="center">
                            </el-table-column>
                            <el-table-column property="HYFLNAME" label="行业分类" align="center">
                            </el-table-column>
                            <el-table-column property="JFFSNAME" label="缴费方式" align="center">
                                <template slot-scope="scope">
                                    <p v-if="scope.row.JFFSNAME=='未缴费'" style="color: red">{{ scope.row.JFFSNAME}}</p>
                                    <p v-else>{{ scope.row.JFFSNAME}}</p>
                                </template>
                            </el-table-column>
                        </el-table>
                        <!--<el-col :span="24" class="toolbar">-->
                        <!--<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[5 ,10 ,20, 50]"-->
                        <!--@current-change="wbhandleCurrentChange" @size-change="wbhandleCurrentChange"-->
                        <!--:page-size="this.wbsize" style="float:right;" :total="this.wbtotal">-->
                        <!--</el-pagination>-->
                        <!--</el-col>-->
                    </el-tab-pane>
                    <el-tab-pane label="工商表">
                        <el-table ref="dbmultipleTable" :data="db" height="300"  highlight-current-row
                                  v-loading="sblistLoading" @selection-change="dbselsChange" style="width: 100%;">
                            <div v-if="isEdit == '1' || isEdit == '0'">
                                <el-table-column type="selection" width="50" align="center" :selectable='checkboxInit'>
                                </el-table-column>
                            </div>
                            <el-table-column property="HH" width="150" label="报装户号" align="center">
                            </el-table-column>
                            <el-table-column property="HM" label="户名" align="center">
                            </el-table-column>
                            <el-table-column property="SBTYPE" label="水表类别" align="center" :formatter="sblbFormatter">
                            </el-table-column>
                            <el-table-column property="DZ" label="装表地址" align="center">
                            </el-table-column>
                            <el-table-column property="KJ" label="口径" align="center">
                            </el-table-column>
                            <el-table-column property="MPH" label="门牌号" align="center">
                            </el-table-column>
                            <el-table-column property="YSXZNAME" label="用水性质" align="center">
                            </el-table-column>
                            <el-table-column property="HYFLNAME" label="行业分类" align="center">
                            </el-table-column>
                            <el-table-column property="JFFSNAME" label="缴费方式" align="center">
                            </el-table-column>
                            <el-table-column property="JFZT" label="缴费状态" align="center">
                                <template slot-scope="scope">
                                    <p v-if="scope.row.JFZT=='未缴费'" style="color: red">{{ scope.row.JFZT}}</p>
                                    <p v-else>{{ scope.row.JFZT}}</p>
                                </template>
                            </el-table-column>
                        </el-table>
                        <!--<el-col :span="24" class="toolbar">-->
                        <!--<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[5 ,10 ,20, 50]"-->
                        <!--@current-change="dbhandleCurrentChange" @size-change="dbhandleCurrentChange"-->
                        <!--:page-size="this.dbsize" style="float:right;" :total="this.dbtotal">-->
                        <!--</el-pagination>-->
                        <!--</el-col>-->
                    </el-tab-pane>
                    <el-tab-pane label="居民表">
                        <el-col :span="5">
                            <el-tree :data="treedata" :props="defaultProps" :default-expand-all="true" @node-click="handleNodeClick"></el-tree>
                        </el-col>
                        <el-col :span="19">
                            <div class="text item">
                                <el-table ref="hbmultipleTable" :data="hb" height="300" @selection-change="hbselsChange"
                                          highlight-current-row style="width: 100%;">
                                    <div v-if="isEdit == '1' || isEdit == '0'">
                                        <el-table-column type="selection" align="center" :selectable='hbcheckboxInit'>
                                        </el-table-column>
                                    </div>
                                    <el-table-column property="hh" width="130" label="报装户号"></el-table-column>
                                    <el-table-column property="hm" label="户名"></el-table-column>
                                    <el-table-column property="mph" label="门牌号"></el-table-column>
                                    <el-table-column property="dz" width="130" label="用户地址" :formatter="dzFormatter">
                                    </el-table-column>
                                    <el-table-column property="kjname" label="口径"></el-table-column>
                                    <el-table-column property="ysxzname" label="用水性质"></el-table-column>
                                    <el-table-column property="hyflname" label="行业分类"></el-table-column>
                                    <el-table-column property="jfzt" label="缴费状态">
                                        <template slot-scope="scope">
                                            <p v-if="scope.row.jfzt=='未缴费'" style="color: red">{{ scope.row.jfzt}}</p>
                                            <p v-else>{{ scope.row.jfzt}}</p>
                                        </template>
                                    </el-table-column>
                                </el-table>
                            </div>
                        </el-col>
                        <!--<el-table ref="hbmultipleTable" :data="hb" height="300"  highlight-current-row-->
                        <!--v-loading="sblistLoading" @selection-change="hbselsChange" style="width: 100%;">-->
                        <!--<div v-if="isEdit == '1' || isEdit == '0'">-->
                        <!--<el-table-column type="selection" align="center" :selectable='checkboxInit'>-->
                        <!--</el-table-column>-->
                        <!--</div>-->
                        <!--<el-table-column property="TYMC" label="所属庭院" align="center">-->
                        <!--</el-table-column>-->
                        <!--<el-table-column property="LDBH" label="楼栋号" align="center">-->
                        <!--</el-table-column>-->
                        <!--<el-table-column property="ZHS" label="总户数" align="center">-->
                        <!--</el-table-column>-->
                        <!--<el-table-column property="LFSX" label="楼房属性" align="center">-->
                        <!--</el-table-column>-->
                        <!--</el-table>-->
                    </el-tab-pane>
                    <div v-if="isEdit == '1' || isEdit == '0'">
                        <el-col :span="24" class="toolbar" style="text-align: center">
                            <el-button type="primary" size="mini" @click="sbSubmit"><!--:disabled="this.hbsels.length===0 && this.dbsels.length===0 && this.wbsels.length===0"-->
                                <i class="iconfont icon-queren"></i>提交</el-button>
                        </el-col>
                    </div>
                </el-tabs>
            </el-dialog>
            <el-dialog  title="管网信息" :visible.sync ="gwxxVisible" width="70%" @close = "xdsbClose" :close-on-click-modal="false">
                <el-row>
                    <el-table ref="multipleTable" :data="GWData" style="width: 100%"  tooltip-effect="dark"  @selection-change="handleSelectionChange">
                        <el-table-column
                                type="selection"
                                :selectable='gwcheckboxInit'
                                width="55">
                        </el-table-column>
                        <el-table-column
                                prop="gdbh"
                                label="管道编号"
                                align="center"
                                width="150">
                        </el-table-column>
                        <el-table-column
                                prop="gdmc"
                                label="管道名称"
                                align="center">
                        </el-table-column>
                        <el-table-column
                                prop="gwzt_vue"
                                label="管网状态"
                                align="center">
                        </el-table-column>
                        <el-table-column
                                prop="zfy"
                                label="总费用"
                                align="center">
                        </el-table-column>
                        <el-table-column
                                prop="gdkj_vue"
                                label="管道口径"
                                align="center"
                                width="80">
                        </el-table-column>
                        <el-table-column
                                prop="gdcz_vue"
                                label="管道材质"
                                align="center">
                        </el-table-column>
                        <el-table-column
                                prop="gdcd"
                                label="管道长度"
                                align="center"
                                width="80">
                        </el-table-column>
                        <el-table-column
                                prop="dlmc"
                                label="道路名称"
                                align="center">
                        </el-table-column>
                        <el-table-column
                                prop="xfssl"
                                label="消防栓数量"
                                align="center"
                                width="100">
                        </el-table-column>
                        <el-table-column
                                prop="xfsbsl"
                                label="消防栓表数量"
                                align="center"
                                width="110">
                        </el-table-column>

                    </el-table>
                </el-row>
                <el-row>
                    <div v-if="isEdit == '1' || isEdit == '0'">
                        <el-col :span="24" class="toolbar" style="text-align: center">
                            <el-button type="primary" size="mini" @click="gwSubmit_vue"><!--:disabled="this.hbsels.length===0 && this.dbsels.length===0 && this.wbsels.length===0"-->
                                <i class="iconfont icon-queren"></i>提交</el-button>
                        </el-col>
                    </div>
                </el-row>
            </el-dialog>
        </el-row>
    </section>
</template>

<script>
    import SGXDXMFW from '@/views/template/sgxdXmfw.vue';
    import {queryGcxdByXmid,Sbsubmit,xdsbClose,showEditForm,querySbByLdid,updateCheckTask,initLdhb,
        initDbpzTable,HbSumit,initGWTable_sgxd,gwsubmit} from '../../api/sgjg'
    export default {
        name: "sgxdXdfw",
        components: {
            'SGXDXMFW':SGXDXMFW,//施工下单项目范围组件
        },
        data(){
            return{
                gwxxVisible:false,
                GWData:[],
                multipleSelection:[],
                defaultProps: {
                    children: 'children',
                    label: 'label'
                },
                treedata: [{
                    label: '楼栋居民表',
                    children: [],
                }],
                ldids:'',
                ldid:"",

                xmid:this.$route.query.xmid,//项目id
                isEdit:this.$route.query.isEdit,//是否编辑
                sgdid:this.$route.query.sgdid,//施工单ID
                taskId:this.$route.query.taskId,//taskId

                xdform:{
                    gwsl:"",
                    srty:'',
                    srbf:'',
                    srlg:'',
                    srdg:'',
                    srgs:'',
                    srjm:'',
                    srqt:'',
                    jhjgsj:'',//计划竣工时间
                    srbz:'',//备注
                },

                tyzd:false,
                bfzd:false,
                lgzd:false,
                dgzd:false,
                qtzd:false,
                jhjgsjzd:false,
                bzzd:false,

                listLoading: false,
                sblistLoading:false,//下单水表列表加载
                zlfl:[],//资料分类集合
                xdfw:[],//下单范围集合
                wlxx:[],//物料信息集合
                wjjlb:[],//五级计量表集合
                db:[],//地表集合
                hb:[],//户表集合
                wlxxFormVisible:false,//是否打开==》物料信息弹窗
                xdsbFormVisible:false,//是否打开==》下单水表弹窗
                xmfwFormVisible:false,//是否打开==》项目范围弹窗
                wbsels: [],//五级计量表列表选中列
                dbsels: [],//地表列表选中列
                hbsels: [],//户表列表选中列
                /*分页*/
                wbsize:5,//五级计量表每页容量
                wbtotal:10,//五级计量表表格数据总数量
                wbpage:1,//五级计量表当前页码
                hbsize:5,//户表每页容量
                hbtotal:10,//户表表格数据总数量
                hbpage:1,//户表当前页码
                dbsize:5,//地表每页容量
                dbtotal:10,//地表表格数据总数量
                dbpage:1,//地表当前页码
                wlsize:5,//物料信息每页容量
                wltotal:10,//物料信息表格数据总数量
                wlpage:1,//物料信息当前页码
                /*分页*/
            }
        },
        mounted() {
            this.changeQszt();
        },
        methods:{
            sblbFormatter:function (row, column){
                switch (row.SBTYPE) {
                    case "0":
                        return '其他'
                        break;
                    case "1":
                        return '工商表'
                        break;
                    case "4":
                        return '消防栓表'
                        break;
                    default:
                        break;
                }
            },
            //流程改变签收状态
            changeQszt(){
                if(this.taskId != '' && this.taskId != undefined){
                    let para = {taskId: this.taskId};
                    updateCheckTask(para).then(res=>{

                    });
                }
            },
            handleNodeClick(data) {
                if(this.isEdit == '1' || this.isEdit == '0'){
                    let list = this.hbsels;
                    if(list.length >0){
                        if(this.ldid == data.id){

                        }else{
                            var hbids = list.map(item => item.id).toString();
                            let para = {hbids:hbids,ldid:this.ldid,sgdid:this.sgdid};
                            HbSumit(para).then(res=>{
                                this.initDbpzTable_vue(data.id);
                            });
                        }
                    }else{
                        let para = {hbid:'1',ldid:this.ldid,sgdid:this.sgdid};
                        HbSumit(para).then(res=>{
                            this.initDbpzTable_vue(data.id);
                        });
                    }
                }else{
                    this.initDbpzTable_vue(data.id);
                }
            },
            //初始化户表配置
            initDbpzTable_vue(ldid){
                let params={
                    ldid:ldid,
                    xmId:this.xmid,
                    sgdid:this.sgdid
                };
                if(this.isEdit == '1' || this.isEdit == '0'){

                }else{
                    params['type'] = "1";
                }
                initDbpzTable(params).then((res)=>{
                    if(res!=null){
                        this.hb = res.data.row;
                        this.ldid = ldid;
                        this.queryHbcheck();
                    }
                })
            },
            //初始化左边树
            initLdhb_vue(){
                let params = {
                    xmid:this.xmid,
                };
                this.ldids = "";
                initLdhb(params).then((res)=>{
                    if(res!=null){
                        this.treedata[0].children=res.row;
                        let list = res.row;
                        if(list.length>0){
                            this.ldids = "(";
                            list.forEach((res,index)=>{
                                if(index == 0){
                                    this.ldids += "'" + res.id + "'";
                                }else{
                                    this.ldids += ",'" + res.id + "'";
                                }
                            });
                            this.ldids += ")";
                        }
                    }
                });
            },
            dzFormatter(row, column){
                if(row.mph == null){
                    return '';
                }else{
                    return row.dz.replace("[楼号]",row.ldbh).replace("[门牌]",row.mph);
                }
            },
            //初始化方法
            childinit(){
                let isEdit = this.isEdit;
                if(isEdit == '1'){
                    let params = {xmid:this.xmid,sgdid:this.sgdid,type:'1'};
                    showEditForm(params).then((res)=>{
                        let list = res.data.sgxdList[0];
                        this.xdform.srty = list.GCNR_TY;
                        this.xdform.srbf = list.GCNR_BF;
                        this.xdform.srlg = list.GCNR_LG;
                        this.xdform.srdg = list.GCNR_DG;
                        this.xdform.srqt = list.GCNR_QT;
                        this.xdform.jhjgsj = list.JHJGSJ;
                        this.xdform.srbz = list.BZ;
                        this.xdsbClose();
                    });

                }else if(isEdit == '2'){
                    let params = {xmid:this.xmid,sgdid:this.sgdid,type:'1'};
                    showEditForm(params).then((res)=>{
                        let list = res.data.sgxdList[0];
                        this.xdform.srty = list.GCNR_TY;
                        this.xdform.srbf = list.GCNR_BF;
                        this.xdform.srlg = list.GCNR_LG;
                        this.xdform.srdg = list.GCNR_DG;
                        this.xdform.srqt = list.GCNR_QT;
                        this.xdform.jhjgsj = list.JHJGSJ;
                        this.xdform.srbz = list.BZ;
                        this.tyzd = true;
                        this.bfzd = true;
                        this.lgzd = true;
                        this.dgzd = true;
                        this.qtzd = true;
                        this.jhjgsjzd = true;
                        this.bzzd = true;
                        this.xdsbClose();
                    });
                }else{

                }
            },
            queryHbcheck(){
                if(this.hb != ''){
                    this.hb.forEach((row,index)=>{
                        if(row.sgxdrwid == this.sgdid){
                            setTimeout(()=>{
                                this.$refs.hbmultipleTable.toggleRowSelection(this.hb[index],true)
                            },1)
                        }else{
                            setTimeout(()=>{
                                this.$refs.hbmultipleTable.toggleRowSelection(this.hb[index],false)
                            },1)
                        }
                    })
                }
            },
            //查询水表是否默认选中
            querySbcheck(){
                if(this.db != ''){
                    this.db.forEach((row,index)=>{
                        if(this.isEdit == '1'){
                            if(row.TYPE == 'dbj'){
                                if(row.SGXDRWID == this.sgdid){
                                    setTimeout(()=>{
                                        this.$refs.dbmultipleTable.toggleRowSelection(this.db[index],true)
                                    },1)
                                }else{
                                    setTimeout(()=>{
                                        this.$refs.dbmultipleTable.toggleRowSelection(this.db[index],false)
                                    },1)
                                }
                            }else if(row.TYPE == 'wxd'){
                                setTimeout(()=>{
                                    this.$refs.dbmultipleTable.toggleRowSelection(this.db[index],false)
                                },1)
                            }
                        }else {
                            if(row.TYPE == 'dxd'){
                                if(row.SGXDRWID == this.sgdid){
                                    setTimeout(()=>{
                                        this.$refs.dbmultipleTable.toggleRowSelection(this.db[index],true)
                                    },1)
                                }else{
                                    setTimeout(()=>{
                                        this.$refs.dbmultipleTable.toggleRowSelection(this.db[index],false)
                                    },1)
                                }
                            }else if(row.TYPE == 'wxd'){
                                setTimeout(()=>{
                                    this.$refs.dbmultipleTable.toggleRowSelection(this.db[index],false)
                                },1)
                            }
                        }
                    })
                }
                if(this.wjjlb != ''){
                    this.wjjlb.forEach((row,index)=>{
                        if(this.isEdit == '1'){
                            if(row.TYPE == 'dbj'){
                                if(row.SGXDRWID == this.sgdid){
                                    setTimeout(()=>{
                                        this.$refs.wbmultipleTable.toggleRowSelection(this.wjjlb[index],true)
                                    },1)
                                }else{
                                    setTimeout(()=>{
                                        this.$refs.wbmultipleTable.toggleRowSelection(this.wjjlb[index],false)
                                    },1)
                                }
                            }else if(row.TYPE == 'wxd'){
                                setTimeout(()=>{
                                    this.$refs.wbmultipleTable.toggleRowSelection(this.wjjlb[index],false)
                                },1)
                            }
                        }else {
                            if(row.TYPE == 'dxd'){
                                if(row.SGXDRWID == this.sgdid){
                                    setTimeout(()=>{
                                        this.$refs.wbmultipleTable.toggleRowSelection(this.wjjlb[index],true)
                                    },1)
                                }else{
                                    setTimeout(()=>{
                                        this.$refs.wbmultipleTable.toggleRowSelection(this.wjjlb[index],false)
                                    },1)
                                }
                            }else if(row.TYPE == 'wxd'){
                                setTimeout(()=>{
                                    this.$refs.wbmultipleTable.toggleRowSelection(this.wjjlb[index],false)
                                },1)
                            }
                        }
                    })
                }
            },
            //查询水表是否默认不可选
            checkboxInit(row,index){
                if(row.CHECKABLE=='1'){
                    return false;
                }else{
                    return true;
                }
                // if(this.isEdit == '1'){
                //     if(row.TYPE == 'yxd'){
                //         return false;//不可勾选
                //     }else{
                //         if(row.TYPE == 'wxd'){
                //             return true;//可勾选
                //         }else if(row.TYPE == 'dbj'){
                //             if(row.SGXDRWID == this.sgdid){
                //                 return true;//可勾选
                //             }else{
                //                 return false;//不可勾选
                //             }
                //         }
                //     }
                // }else{
                //     if(row.TYPE == 'yxd' || row.TYPE == 'dbj'){
                //         return false;//不可勾选
                //     }else{
                //         return true;//可勾选
                //     }
                // }
            },
            gwcheckboxInit(row,index){
                if(row.checkable){
                    return false;
                }else{
                    return true;
                }
            },
            hbcheckboxInit(row,index){
                if(row.checkable){
                    return false;
                }else{
                    return true;
                }
                // if(row.sgxdrwid == '' || row.sgxdrwid == undefined){
                //     return true;//可勾选
                // }else{
                //     if(row.sgxdrwid == this.sgdid){
                //         return true;//可勾选
                //     }else{
                //         return false;//不可勾选
                //     }
                // }
            },
            //跳转到下单水表界面
            openXdsb(type){
                this.initLdhb_vue(type);
                this.findXdsb(type);
            },
            //管网------------
            openGwxx(){
                let params={
                    sgdid:this.sgdid,
                    xmid:this.xmid
                }
                initGWTable_sgxd(params).then((res)=>{
                    if(res.data!=null){
                        this.GWData = res.data.row;
                        this.$nextTick(function () {
                            if (this.GWData) {
                                this.GWData.forEach(row => {
                                    if(row.selected){
                                        this.$refs.multipleTable.toggleRowSelection(row, true);
                                    }
                                });
                            } else {
                                this.$refs.multipleTable.clearSelection();
                            }
                        })
                    }
                    this.gwxxVisible=true;
                })
            },

            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            gwSubmit_vue(){
                var gwids = this.multipleSelection.map(item => item.id).toString();
                this.$confirm('确认提交选中记录吗？', '提示', {
                    type: 'warning'
                }).then(() => {
                    let para = {gwids: gwids,  sgdid: this.sgdid};
                    gwsubmit(para).then((res) => {
                        this.$message({
                            message: '提交成功',
                            type: 'success'
                        });
                        this.gwxxVisible = false;
                    }).catch(() => {

                    });
                })
            },
            //------------------------------------------
            //查询下单水表信息
            findXdsb(type){
                let params = {
                    xmid:this.xmid,
                    type:"3",
                    hbpage:this.hbpage-1,
                    hbsize:this.hbsize,
                    wbsize:this.wbsize,
                    wbpage:this.wbpage-1,
                    dbpage:this.dbpage-1,
                    dbsize:this.dbsize,
                    lctype:type,
                    sgdid:this.sgdid
                };
                queryGcxdByXmid(params).then((res)=>{
                    this.wjjlb = res.data.wjjlbList;
                    this.db = res.data.dbList;
                    // this.hb = res.data.hbList;
                    this.xdsbFormVisible = true;
                    this.querySbcheck();
                    this.wbtotal = res.data.wbtotal;
                    this.hbtotal = res.data.hbtotal;
                    this.dbtotal = res.data.dbtotal;
                });
            },
            findXdwb(){
                let params = {
                    xmid:this.xmid,
                    type:"4",
                    wbsize:this.wbsize,
                    wbpage:this.wbpage-1,
                };
                queryGcxdByXmid(params).then((res)=>{
                    this.wjjlb = res.data.wjjlbList;
                    this.querySbcheck();
                    this.wbtotal = res.data.wbtotal;
                });
            },
            findXddb(){
                let params = {
                    xmid:this.xmid,
                    type:"5",
                    dbpage:this.dbpage-1,
                    dbsize:this.dbsize,
                };
                queryGcxdByXmid(params).then((res)=>{
                    this.db = res.data.dbList;
                    this.querySbcheck();
                    this.dbtotal = res.data.dbtotal;
                });
            },
            findXdhb(){
                let params = {
                    xmid:this.xmid,
                    type:"6",
                    hbpage:this.hbpage-1,
                    hbsize:this.hbsize,
                };
                queryGcxdByXmid(params).then((res)=>{
                    this.hb = res.data.hbList;
                    this.querySbcheck();
                    this.hbtotal = res.data.hbtotal;
                });
            },
            //打开物料信息界面
            openWlxx(){
                let params = {
                    xmid:this.xmid,
                    type:"2",
                    wlpage:this.wlpage-1,
                    wlsize:this.wlsize,
                };
                queryGcxdByXmid(params).then((res)=>{
                    this.wlxx = res.data.wlxxList;
                    this.wlxxFormVisible = true;
                    this.wltotal = res.data.wltotal;
                });
            },
            //打开项目范围信息界面
            openXmfw(){
                this.xmfwFormVisible = true;
                setTimeout(() => {
                    this.$refs.xmfw.initTable_vue();
                },0)
            },
            //水表提交
            sbSubmit(){
                // var hbids = this.hbsels.map(item => item.LDBS).toString();
                var dbids = this.dbsels.map(item => item.ID).toString();
                var wbids = this.wbsels.map(item => item.ID).toString();
                this.$confirm('确认提交选中记录吗？', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.sblistLoading = true;
                    let para = { dbids: dbids,wbids: wbids,sbIds:'',sgdid:this.sgdid};/*hbids: hbids,*/
                    Sbsubmit(para).then((res) => {
                        this.sblistLoading = false;
                        this.$message({
                            message: '提交成功',
                            type: 'success'
                        });
                        this.xdsbFormVisible = false;
                    }).catch(() => {

                    });

                    let list = this.hbsels;
                    if(list.length >0){
                        var hbids = list.map(item => item.id).toString();
                        let para = {hbids:hbids,ldid:list[0].ldid,sgdid:this.sgdid};
                        HbSumit(para).then(res=>{
                        });
                    }else{
                        let para = {hbid:'1',ldid:this.ldid,sgdid:this.sgdid};
                        HbSumit(para).then(res=>{
                            this.initDbpzTable_vue(data.id);
                        });
                    }
                })
            },
            //下单水表关闭事件
            xdsbClose(){
                let params = {sgdid:this.sgdid};
                xdsbClose(params).then((res) => {
                    let xmfwList = res.data.xmfwList;
                    if(res.data.xmfwList != null){
                        this.xdform.gwsl = res.data.xmfwList[0].GWCOUNT+"段";
                        this.xdform.srgs = "五级计量表"+res.data.xmfwList[0].WB+",工商表"+res.data.xmfwList[0].DB+"只 （详见预算清单）";
                        this.xdform.srjm = res.data.xmfwList[0].HB+"户";
                        this.$emit('sbdy_vue',this.xdform.srgs,this.xdform.srjm,this.xdform.srqt,this.xdform.gwsl,
                            this.xdform.srty,this.xdform.srlg,this.xdform.srbf,this.xdform.srdg,this.xdform.srbz);
                    }
                })
            },
            wbselsChange: function (sels) {
                if(sels.HH == '' && sels.MPH == '' && sels.YSXZNAME == '' && sels.HYFLNAME == ''){
                    this.$message({
                        message: '请完善水表信息！',
                        type: 'warning'
                    });
                }else{
                    this.wbsels = sels;
                }
            },
            dbselsChange: function (sels) {
                if(sels.HH == '' && sels.MPH == '' && sels.YSXZNAME == '' && sels.HYFLNAME == ''){
                    this.$message({
                        message: '请完善水表信息！',
                        type: 'warning'
                    });
                }else{
                    this.dbsels = sels;
                }
            },
            hbselsChange: function (sels) {
                if(sels.length > 0){
                    let para = {ldid:sels[0].ldid};
                    querySbByLdid(para).then(res=>{
                        let list = res.list;
                        let i=0;
                        list.forEach((item,index)=>{
                            if(item.HH != '' || item.MPH != '' || item.YSXZBH != '' || item.HYFLBH != ''){
                                i++;
                            }
                        });
                        if(i == 0){
                            this.$message({
                                message: '请完善水表信息！',
                                type: 'warning'
                            });
                        }else {
                            this.hbsels = sels;
                        }
                    });
                }else {
                    this.hbsels = sels;

                }
            },
            wlhandleCurrentChange(val) {
                this.wlpage = val;
                this.openWlxx();
            },
            wlhandleSizeChange(val) {
                this.wlsize = val;
                this.wlpage = 1;
                this.openWlxx();
            },
            wbhandleCurrentChange(val) {
                this.wbpage = val;
                this.findXdwb();
            },
            wbhandleSizeChange(val) {
                this.wbsize = val;
                this.wbpage = 1;
                this.findXdwb();
            },
            dbhandleCurrentChange(val) {
                this.dbpage = val;
                this.findXddb();
            },
            dbhandleSizeChange(val) {
                this.dbsize = val;
                this.dbpage = 1;
                this.findXddb();
            },
            hbhandleCurrentChange(val) {
                this.hbpage = val;
                this.findXdhb();
            },
            hbhandleSizeChange(val) {
                this.hbsize = val;
                this.hbpage = 1;
                this.findXdhb();
            },
        }
    }
</script>

<style scoped>

</style>