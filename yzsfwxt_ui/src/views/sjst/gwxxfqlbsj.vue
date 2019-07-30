<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
    <section>
        <el-row>
            <el-col :span="24">
                <hhldsx :xmid="this.xmid" v-show="hhl" :activityid="this.activityId"></hhldsx>
                <!--项目基本信息模板-->
                <GWJBXX></GWJBXX>
                <!--配合联合勘查的部门-->
                <el-card class="box-card" style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">管网调查反馈
                        </div>
                        <!--<span>官网调查反馈</span>-->
                    </div>
                    <div class="text item" style="width: 100%;">
                        <el-button size="mini" v-show="tianjia" type="primary" @click="openAddForm"><i
                                class="iconfont icon-xinzeng"></i>添加
                        </el-button>
                        <!--   <el-button type="primary" size="small" @click="openAddForm" icon="el-icon-plus">添加</el-button>-->
                        <el-table :data="kcfkRow" v-loading="listLoading" border style="width: 100%;margin-top: 10px;">
                            <el-table-column type="index" label="序号" width="150" align="center">
                            </el-table-column>
                            <el-table-column property="dcxnr" label="调查内容" width="200" align="center"
                                             :formatter="formatDcnr">
                            </el-table-column>
                            <el-table-column property="dcr" label="调查人" width="200" align="center">
                            </el-table-column>
                            <el-table-column property="dcrq_str" label="调查日期" min-width="180" align="center">
                            </el-table-column>
                            <el-table-column label="操作" width="250" align="center">
                                <template slot-scope="scope">
                                    <label title="编辑" v-show="bianji" @click="openEditForm(scope.$index, scope.row)">
                                        <i class="iconfont icon-ai-edit"
                                           style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                                    <!-- <el-button size="small" type="warning" @click="openEditForm(scope.$index, scope.row)">编辑</el-button>-->
                                    <span>&nbsp;&nbsp;&nbsp;</span>
                                    <label title="删除" v-show="shanchu" @click="deleteFk(scope.$index, scope.row)">
                                        <i class="iconfont delete"
                                           style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                                    <label title="查看" v-show="chakan" @click="openEditForm(scope.$index, scope.row)">
                                        <i class="iconfont icon-hetong"
                                           style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                                    <!-- <el-button size="small" type="danger" @click="deleteFk(scope.$index, scope.row)">删除</el-button>-->
                                </template>
                            </el-table-column>
                        </el-table>
                    </div>
                    <!--   <el-form style="margin-top: 25px;">
                           <el-form-item label="反馈意见" style="width: 60%">
                               <el-input type="textarea" v-model="info"></el-input>
                           </el-form-item>
                       </el-form>-->
                </el-card>
                <el-row style="text-align: center;margin-top: 25px;">

                    <router-link :to="tabUrl">
                        <el-button size="mini" type="info" v-show="quxiao" @click="cancel"><i class="iconfont icon-quxiao"></i>取消
                        </el-button>
                    </router-link>
                    <!--<el-button size="small" type="info" @click="htyyVisible = true"><i-->
                            <!--class="iconfont icon-fanhui"></i>退回</el-button>-->
                        <el-button size="mini" v-show="tijiao"  type="primary" @click="submitKcfk" :loading="sumbitLoading"><i
                                class="iconfont icon-queren"></i>提交
                        </el-button>
                    <el-button size="mini"  type="primary" v-show="xmqj" @click="goxmqj()" :loading="sumbitLoading"><i class="iconfont icon-xiangmuquanjing_"></i>项目全景</el-button>

                </el-row>
            </el-col>
        </el-row>

        <el-dialog title="退回原因" :visible.sync = "htyyVisible" :close-on-click-modal="false">
            <el-input
                    type="textarea"
                    :rows="4"
                    placeholder="请输入内容"
                    v-model="htyy">
            </el-input>
            <div slot="footer" class="dialog-footer">
                <el-button size="small" type="info" @click="htyyVisible = false"><i class="iconfont icon-quxiao"></i>取 消</el-button>
                <el-button type="primary" size="small" @click="sjcgscBack_vue()"><i class="iconfont icon-queren"></i>确 定</el-button>
            </div>
        </el-dialog>
        <!--新增编辑界面-->
        <el-dialog :title="title" :visible.sync="editFormVisible" top="2vh" width="60%" :close-on-click-modal="false">
            <el-form :model="kcfk" :rules="rules" label-width="100px" ref="kcfk" size="mini">
                <el-input type="text" v-model="kcfk.id" style="display: none;"></el-input>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="调查项分类">
                            <el-radio v-model="kcfk.dcnr" label="0">项目周边管网</el-radio>
                            <el-radio v-model="kcfk.dcnr" label="1">具体位置测压</el-radio>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="">
                            <span>注：调查项主要内容，一张调查表可以有多个调查项</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="12">
                        <el-form-item label="线路主路" prop="gxzl">
                            <el-input type="text" v-model="kcfk.gxzl" class="inputBox"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="管线路段" prop="gxld">
                            <el-input type="text" v-model="kcfk.gxld" class="inputBox"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="24">
                        <el-form-item label="" prop="gxjzl">
                            管线在主路的
                            <el-select v-model="kcfk.gxzzl" placeholder="请选择" style="width:20%;">
                                <el-option
                                        v-for="item in zlwz"
                                        :key="item.id"
                                        :label="item.codename"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                            侧，距主路中线
                            <el-input type="text" v-model="kcfk.gxjzl" style="width:15%;"></el-input>
                            米，正上方覆盖物
                            <el-select v-model="kcfk.gxzsf" placeholder="请选择" style="width:20%;">
                                <el-option
                                        v-for="item in zsffgw"
                                        :key="item.id"
                                        :label="item.codename"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                            。
                        </el-form-item>
                    </el-col>

                </el-row>

                <el-row>
                    <el-col :span="12">
                        <el-form-item label="管线口径" prop="gxkj">
                            <el-select v-model="kcfk.gxkj" placeholder="请选择" class="inputBox">
                                <el-option
                                        v-for="item in gxkj"
                                        :key="item.id"
                                        :label="item.codename"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="管线材质">
                            <el-select v-model="kcfk.gxcz" placeholder="请选择" class="inputBox">
                                <el-option
                                        v-for="item in gxcz"
                                        :key="item.id"
                                        :label="item.codename"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="">
                            <span>管线主路：管线所在道路名称，非市政道路不填。若调查测压点压力，可在调查点位置中填写位置信息。</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="12">
                        <el-form-item label="测压对象">
                            <el-input type="text" v-model="kcfk.cydx" class="inputBox"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="测压时间">
                            <el-date-picker v-model="kcfk.cysj" type="datetime" placeholder="选择日期时间"
                                            value-format="yyyy-MM-dd HH:mm" format="yyyy-MM-dd HH:mm"
                                            class="inputBox"></el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="24">
                        <el-form-item label="调查点位置">
                            <el-input type="text" v-model="kcfk.dcdwz" class="inputBox"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="24">
                        <el-form-item label="">
                            <span>测压对象：指消防栓、小区首层最高点（一层水龙头）等具体供水设施。</span><br>
                            <span>调查点位置：指测压点、装表点等具有点特征的地址，如：A路与B路交口C侧D米，A路B小区门口C侧D米。</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="12">
                        <el-form-item label="测压点压力" prop="cydyl">
                            <el-input type="text" v-model="kcfk.cydyl" class="inputBox"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="测压点标高" prop="cydbg">
                            <el-input type="text" v-model="kcfk.cydbg" class="inputBox"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="3">
                        <el-form-item label="">
                            <el-checkbox v-model="kcfk.bwts" true-label="1" false-label="0">并网通水</el-checkbox>
                        </el-form-item>
                    </el-col>
                    <el-col :span="9">
                        <el-form-item label="">
                            <el-checkbox v-model="kcfk.gwyj" true-label="1" false-label="0">管网移交</el-checkbox>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="测压点深度" prop="cydsd">
                            <el-input type="text" v-model="kcfk.cydsd" class="inputBox"></el-input><span>米</span>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="12">
                        <el-form-item label="调查人" prop="dcr">
                            <el-input type="text" v-model="kcfk.dcr" class="inputBox"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="调查日期" prop="dcrq">
                            <el-date-picker v-model="kcfk.dcrq" type="date" placeholder="选择日期时间"
                                            value-format="yyyy-MM-dd" format="yyyy-MM-dd"
                                            class="inputBox"></el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :span="24">
                        <el-form-item label="备注">
                            <el-input type="textarea" v-model="kcfk.bz"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>

            <el-row>
                <el-col :span="24" style="text-align: center;margin: 5px;">
                    <el-button size="mini" type="info" @click.native="editFormVisible = false">
                        <i class="iconfont icon-quxiao"></i>取消
                    </el-button>

                    <el-button size="mini"  v-show="tijiao_1" type="primary" @click="saveFk('kcfk')" :loading="addLoading">
                        <i class="iconfont icon-save"></i>提交
                    </el-button>
                </el-col>
            </el-row>
        </el-dialog>

    </section>
</template>
<style lang="css">
    .rowDiv el-row {
        margin: 10px;
    }

    .inputBox {
        width: 100% !important;
    }
</style>
<script>

    import GWJBXX from '@/views/template/gwjbxx.vue';
    import hhldsx from '@/views/template/hhldsx.vue';
    import {getFkQsCode} from '../../api/xkylx';
    import {saveGwxxdcCB, getGwxxdcCB, deleteGwxxFq, saveGwxxFQ, saveReverSion,getQianshou} from '../../api/sjst';

    export default {
        data() {
            var checkNum = (rule, value, callback) => {
                //var reg =  /^[1-9]\d*$/;
                var reg = /^(?:0|[1-9]\d*)(?:\.\d*[1-9])?$/;
                if (!reg.test(value) && !value == '') {
                    callback(new Error('距离主路中路距离必须为非负数字'));
                } else {
                    callback();
                }
            };
            var checkNum2 = (rule, value, callback) => {
                //var reg =  /^[1-9]\d*$/;
                var reg = /^(?:0|[1-9]\d*)(?:\.\d*[1-9])?$/;
                if (!reg.test(value) && !value == '') {
                    callback(new Error('必须为有效数字'));
                } else {
                    callback();
                }
            };
            var checkGxzl = (rule, value, callback) => {
                if (value == '' && this.kcfk.dcnr == '0') {
                    callback(new Error('管线主路不能为空'));
                } else {
                    callback();
                }
            };
            var checkGxld = (rule, value, callback) => {
                if (value == '' && this.kcfk.dcnr == '0') {
                    callback(new Error('管线路段不能为空'));
                } else {
                    callback();
                }
            };
            var checkGxkj = (rule, value, callback) => {
                if (value == '' && this.kcfk.dcnr == '0') {
                    callback(new Error('管线口径不能为空'));
                } else {
                    callback();
                }
            };
            return {
                chakan:false,
                xmqj:true,//全景按钮
                hhl:true,
                tijiao:true,
                quxiao:true,
                tianjia:true,//添加反馈按钮
                bianji:true,//反馈编辑按钮
                shanchu:true,//反馈删除按钮
                tijiao_1:true,//添加反馈保存按钮

                flag : true,
                htyy:"",
                tabUrl: '',
                activityId: '30006',
                title: '',
                type: '3',//区所
                kcfkRow: [],//反馈记录
                gxkj: [],//管线口径
                gxcz: [],//管线材质
                zlwz: [],//相对主路方向
                zsffgw: [],//正上方覆盖物
                listLoading: false,
                editFormVisible: false,
                htyyVisible:false,
                sumbitLoading: false,
                kcsj: '2019-10-10 15:00:00',//预约勘查时间
                //   xmid : this.$route.query.xmid,
                kcrwcbid: '',
                //zlwz: [{value:'1',label:'上方'},{value:'2',label:'下方'},{value:'3',label:'左右侧'}],
                //zsffgw: [{value:'1',label:'杆子'},{value:'2',label:'道路'},{value:'3',label:'柱子'}],
                kcfk: {
                    id: '',
                    dcnr: '',
                    gxzl: '',
                    gxld: '',
                    gxjzl: '',
                    gxzzl: '',
                    gxzsf: '',
                    gxkj: '',
                    gxcz: '',
                    cydx: '',
                    cysj: '',
                    dcdwz: '',
                    cydbg: '',
                    cydyl: '',
                    gwyj: '0',
                    bwts: '0',
                    dcr: '',
                    dcrq: '',
                    bz: '',
                    cydsd:''//测压深度
                },
                xmid: this.$route.query.xmid,
                lctbh: this.$route.query.lctbh,
                taskId: this.$route.query.taskId,
                info: '',
                fileList: [],
                rules: {
                    gxjzl: [
                        {validator: checkNum, trigger: 'blur'}
                    ],
                    cydyl: [
                        {validator: checkNum2, trigger: 'blur'}
                    ],
                    cydbg: [
                        {validator: checkNum2, trigger: 'blur'}
                    ],
                    gxzl:[
                        {validator: checkGxzl, trigger: 'blur'}
                    ],
                    gxld:[
                        {validator: checkGxld, trigger: 'blur'}
                    ],
                    gxkj:[
                        {validator: checkGxkj, trigger: 'blur'}
                    ],
                    dcr: [
                        {required: true, message: '调查人不能为空', trigger: 'blur'}
                    ],
                    dcrq: [
                        {required: true, message: '调查日期不能为空', trigger: 'blur'}
                    ],
                },
                sumbitLoading: false,
                addLoading: false,
                isDisable: false
            }
        },
        methods: {
            //跳转至项目全景
            goxmqj(){
                this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid  }})

            },
            formatDcnr: function (row, column) {
                return row.dcnr == '0' ? '项目周边管网' : '具体位置测压';
            },
            cancel() {
                this.tabUrl = '/Backlog';
                this.$routerTab.close();
                this.$routerTab.refresh('/Backlog');

            },
            getCode() {
                getFkQsCode().then((res) => {
                    this.gxkj = res.data.gxkj;
                    this.gxcz = res.data.gxcz;
                    this.zsffgw = res.data.zsffgw;
                    this.zlwz = res.data.zlwz;
                });
            },
            getKcfkRow() {//获取区所反馈记录
                //   let  xmid =localStorage.getItem("xmid");
                let params = {xmid: this.xmid};

                this.listLoading = true;
                getGwxxdcCB(params).then((res) => {

                    this.kcrwcbid = res.data.kcrwcbid;
                    let row = res.data.row;
                    for (var i in row) {
                        if (row[i].dcrq != null) {
                            row[i].dcrq = row[i].dcrq.substring(0, row[i].dcrq.lastIndexOf(' '));
                        }
                        if (row[i].cysj != null) {
                            row[i].cysj = row[i].cysj.substring(0, row[i].cysj.lastIndexOf(':'));
                        }
                        row[i].dcrq = row[i].dcrq_str;
                        row[i].cysj = row[i].cysj_str;
                    }

                    this.kcfkRow = row;
                    this.listLoading = false;
                });
            },
            openEditForm: function (index, row) {

                this.title = "编辑";
                for (var i in row) {
                    if (row[i] == null) {
                        row[i] = '';
                    }
                }
                this.kcfk = row;
                //  this.kcfk.cysj = this.kcfk.cysj.substring(0,this.kcfk.cysj.lastIndexOf(':'));
                //   this.kcfk.dcrq = this.kcfk.dcrq.substring(0,this.kcfk.dcrq.lastIndexOf(' '));
                this.editFormVisible = true;
            },
            openAddForm: function () {
                this.title = "新增";
                this.kcfk = {
                    id: '',
                    dcnr: '0',
                    gxzl: '',
                    gxld: '',
                    gxjzl: '',
                    gxzzl: '',
                    gxzsf: '',
                    gxkj: '',
                    gxcz: '',
                    cydx: '',
                    cysj: '',
                    dcdwz: '',
                    cydbg: '',
                    cydyl: '',
                    gwyj: '0',
                    bwts: '0',
                    dcr: '',
                    dcrq: '',
                    bz: '',
                    cydsd:''//测压点深度
                };
                this.editFormVisible = true;
            },
            //删除
            deleteFk: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    let para = {id: row.id};
                    deleteGwxxFq(para).then((res) => {
                        if (res.result == 'success') {
                            this.$message({
                                message: '删除成功',
                                type: 'success'
                            });
                        }
                        this.getKcfkRow();
                    });
                });
            },
            //保存+新增
            saveFk: function (kcfk) {
                this.$refs[kcfk].validate((valid) => {
                    if (valid) {
                        if(this.kcfk.dcnr == '0'){
                            this.flag = false;
                        }
                        let params = Object.assign({xmid: this.xmid}, this.kcfk);
                        this.sumbitLoading = true;
                        saveGwxxdcCB(params).then((res) => {
                            this.sumbitLoading = false;
                            if (res.result == 'success') {
                                this.$message({
                                    message: '保存成功',
                                    type: 'success'
                                });
                            }
                            this.flag = true;
                            this.editFormVisible = false;
                            localStorage.clear();
                            this.getKcfkRow();
                        });
                    } else {
                       // this.$message.error('');
                        return false;
                    }
                });

            },
            submitKcfk() {
                if(this.kcfkRow.length<=0){
                    this.$message({
                        message: '请至少有一条项目周边管网信息',
                        type: 'warning'
                    });
                    return;
                }else {
                    //  let xmid=localStorage.getItem("xmid");
                    let params = {xmid: this.xmid, info: this.info};
                    this.sumbitLoading = true;
                    this.tabUrl = '/Backlog';
                    this.$routerTab.close();
                    this.$routerTab.refresh('/MyWork');
                    this.$routerTab.refresh('/Backlog');
                    saveGwxxFQ(params).then((res) => {
                        this.sumbitLoading = false;
                        if (res.result == 'success') {
                            this.$message({
                                message: '提交反馈结果成功',
                                type: 'success'
                            });
                        } else {
                            this.$message({
                                message: '提交反馈结果异常',
                                type: 'error'
                            });
                        }
                    });
                }
            },
            //退回
            sjcgscBack_vue: function () {
                // this.$prompt('请输入退回原因！', '温馨提示：', {
                //     confirmButtonText: '提交',
                //     cancelButtonText: '取消',
                //     inputPattern: /\S/,
                //     inputErrorMessage: '输入不能为空！'
                // }).then(({value}) => {
                    // let xmid=localStorage.getItem("xmid");
                if(this.htyy==''||this.htyy==null){
                    this.$message.error('请填写退回原因！');
                    return;
                }
                this.tabUrl = '/Backlog';
                this.$routerTab.close();
                this.$routerTab.refresh('/Backlog');
                    let params = {xmid: this.xmid, thyy: this.htyy};
                    saveReverSion(params).then((res) => {
                        if (res.result == 'success') {
                            this.$message({
                                message: '退回成功',
                                type: 'success'
                            });
                        } else {
                            this.$message({
                                message: '退回异常',
                                type: 'error'
                            });
                        }
                    });
            },
            qianShou(){
                let params = {taskId: this.taskId};
                getQianshou(params).then((res) => {
                  /*  if (res.result == 'success') {
                        this.$message({
                            message: '退回成功',
                            type: 'success'
                        });
                    } else {
                        this.$message({
                            message: '退回异常',
                            type: 'error'
                        });
                    }*/
                });

            },
            lctgwfk(){

                if(this.lctbh=="gwfksj"){ //项目全景跳转
                      this.hhl=false;
                     this.tijiao=false;
                    this.tijiao_1=false;
                    this.tianjia=false;
                    this.shanchu=false;
                    this.quxiao=false;
                    this.bianji=false;
                    this.chakan=true;
                    this.xmqj=false;
                }
            }

        },
        mounted() {
            this.qianShou();
            this.lctgwfk();
            this.getCode();
            this.getKcfkRow();
        },
        components: {
            'GWJBXX': GWJBXX,  //XMJBXX 变成 组件 XMJBXX
            'hhldsx': hhldsx,
        }

    }
</script>
<style>

</style>