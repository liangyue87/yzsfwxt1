<template>
    <section>
        <el-row>
            <el-col :span="24" class="toolbar" >
                <el-form>
                    <el-form-item>
                        <el-col :span="2" style="text-align: right">
                            <span>门牌号：</span>
                        </el-col>
                        <el-col :span="2">
                            <el-input size="small" v-model="filters.beginMp"></el-input>
                        </el-col>
                        <el-col class="line" :span="1" style="text-align: center">-</el-col>
                        <el-col :span="2">
                            <el-input size="small" v-model="filters.endMp"></el-input>
                        </el-col>
                        <el-col class="line" :span="1" style="text-align: center">&nbsp;</el-col>
                        <el-col :span="10">
                            <el-button size="mini" type="primary" @click="qrgx_vue">
                                <i class="iconfont icon-queren"></i>确认勾选</el-button>
                            <el-button size="mini" type="info" style="margin-left: 3px" v-on:click="qxgx_vue">
                                <i class="iconfont icon-quxiao"></i>取消勾选</el-button>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="2" style="text-align: right">
                            <span>表册号：</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="bch"></el-input>
                        </el-col>
                        <el-col class="line" :span="1" style="text-align: center">&nbsp;</el-col>
                        <el-col :span="10">
                            <el-button size="mini" type="primary" v-on:click="pledit">
                                <i class="iconfont icon-ai-edit"></i>应用</el-button>
                            <router-link :to="{path:'/cbySbyjd', query:{xmid:this.xmid,rwbh:this.rwbh,
                            refId:this.refId,activityId:this.activityId,taskId:this.taskId}}">
                                <el-button size="mini" type="primary" v-on:click="save_vue">
                                    <i class="iconfont icon-save"></i>保存并退出</el-button>
                            </router-link>
                            <router-link :to="{path:'/cbySbyjd', query:{xmid:this.xmid,rwbh:this.rwbh,
                            refId:this.refId,activityId:this.activityId,taskId:this.taskId}}">
                                <el-button size="mini" type="info" v-on:click="return_vue">
                                    <i class="iconfont icon-quxiao"></i>返回</el-button>
                            </router-link>
                        </el-col>
                    </el-form-item>
                </el-form>
            </el-col>

            <el-col :span="24" class="toolbar">
                <!--列表-->
                <elx-editable ref="editable" :data.sync="row" border highlight-current-row v-loading="listLoading"
                             style="width: 100%;" @selection-change="selsChange">
                    <elx-editable-column type="selection" width="100" align="center">
                    </elx-editable-column>
                    <elx-editable-column label="操作" align="center">
                        <template slot-scope="scope">
                            <label title="编辑">
                                <i class="iconfont icon-ai-edit"  @click="edit_vue(scope.$index, scope.row)"
                                   style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                            </label>
                        </template>
                    </elx-editable-column>
                    <elx-editable-column prop="PQ" label="片区" width="150" align="center">
                    </elx-editable-column>
                    <elx-editable-column prop="HH" label="报装户号" width="150" align="center">
                    </elx-editable-column>
                    <elx-editable-column prop="HM" label="户名" width="150" align="center">
                    </elx-editable-column>
                    <elx-editable-column prop="ZBDZ" label="装表位置" width="100" align="center">
                    </elx-editable-column>
                    <elx-editable-column prop="BZKJ" label="口径" align="center">
                    </elx-editable-column>
                    <elx-editable-column prop="BSM" label="表身码" width="100" align="center">
                    </elx-editable-column>
                    <elx-editable-column prop="SBCJ" label="水表厂家" width="100" align="center">
                    </elx-editable-column>
                    <elx-editable-column prop="BCH" label="表册号" width="100" align="center">
                    </elx-editable-column>
                    <elx-editable-column prop="GPS" label="GPS" width="100" align="center">
                    </elx-editable-column>
                    <elx-editable-column prop="YHZH" label="银行账号" width="150" align="center">
                    </elx-editable-column>
                    <elx-editable-column prop="YSXZ" label="用水性质" width="150" align="center">
                    </elx-editable-column>
                    <elx-editable-column prop="HYFL" label="行业分类" align="center">
                    </elx-editable-column>
                </elx-editable>
            </el-col>

            <!--列编辑-->
            <el-dialog title="编辑" :visible.sync ="jmbFormVisible" width="1000px">
                <el-form :model="jmbform"  ref="jmbform" class="demo-ruleForm">
                    <el-form-item>
                        <el-button size="mini" type="primary" @click="jmbedit_vue()">
                            <i class="iconfont icon-save"></i>保存</el-button>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>项目编号&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="jmbform.xmbh" :readonly="true"></el-input>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>户号&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="jmbform.hh" :readonly="true"></el-input>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>户名&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="jmbform.hm" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>用户地址&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="21">
                            <el-input size="small" v-model="jmbform.ybdz" style="width: 520px" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>装表地址&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="21">
                            <el-input size="small" v-model="jmbform.zbdz" style="width: 520px" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>新装底数&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="jmbform.xzds" :readonly="true"></el-input>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>拆回旧表底数&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="jmbform.chjbds" :readonly="true"></el-input>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>表身码&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="jmbform.bsm" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>水表口径&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="jmbform.sbkj" :readonly="true"></el-input>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>水表厂家&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="jmbform.sbcj" :readonly="true"></el-input>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>水表类型&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="jmbform.sblx" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>用水性质&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-select size="small" v-model="jmbform.ysxz"
                                       disabled="true" placeholder="请选择">
                                <el-option v-for="item in ysxzList"
                                           :key="item.codevalue"
                                           :label="item.codename"
                                           :value="item.codevalue"></el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>行业分类&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-select size="small" v-model="jmbform.hyfl" disabled="true" placeholder="请选择">
                                <el-option v-for="item in hyflList"
                                           :key="item.codevalue"
                                           :label="item.codename"
                                           :value="item.codevalue"></el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>表册号&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item prop="bch" :rules="this.publicUtil.notNull">
                                <el-input size="small" v-model="jmbform.bch"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>短信服务&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-radio-group disabled="true" v-model="jmbform.dxfw">
                                <el-radio @click.native="toggleTab(1)" label="Y">有</el-radio>
                                <el-radio @click.native="toggleTab(2)" label="N">无</el-radio>
                            </el-radio-group>
                        </el-col>
                        <div v-if="dxxs">
                            <el-col :span="3" style="text-align: right">
                                <span>机主名称&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" readonly="true" v-model="jmbform.jzmc"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>手机号码&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" readonly="true" v-model="jmbform.sjhm"></el-input>
                            </el-col>
                        </div>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>代收垃圾费&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-radio-group disabled="true" v-model="jmbform.dsljf">
                                <el-radio label="1">是</el-radio>
                                <el-radio label="0">否</el-radio>
                            </el-radio-group>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>交费方式&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="13">
                            <el-input size="small" v-model="jmbform.jffs" style="width: 520px" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>片区&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-select size="small" v-model="jmbform.pq" disabled="true" placeholder="请选择">
                                <el-option v-for="item in pqList"
                                           :key="item.VALUE"
                                           :label="item.LABEL"
                                           :value="item.VALUE"></el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>GPS数据&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="13">
                            <el-input size="small" v-model="jmbform.gps" style="width: 520px" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>安装日期&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-date-picker size="small" type="date" placeholder="选择日期" v-model="jmbform.azrq" :readonly="true" style="width: 100%"></el-date-picker>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>通水日期&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-date-picker size="small" type="date" placeholder="选择日期" v-model="jmbform.tsrq" :readonly="true" style="width: 100%"></el-date-picker>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>现场移交&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-date-picker size="small" type="date" readonly="true" placeholder="选择日期"
                                        format="yyyy-MM-dd"
                                        value-format="yyyy-MM-dd"
                                        v-model="jmbform.xcyjrq" style="width: 100%"></el-date-picker>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>施工单位&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" readonly="true" v-model="jmbform.sgdw"></el-input>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>供水所&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" readonly="true" v-model="jmbform.gss"></el-input>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>营业中心&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" readonly="true" v-model="jmbform.yyzx"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>备注&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="21">
                            <el-input size="small" readonly="true" v-model="jmbform.bz" style="width: 520px"></el-input>
                        </el-col>
                    </el-form-item>
                </el-form>
            </el-dialog>

        </el-row>
    </section>
</template>

<script>
    import { shJmbByLdid,cbyeditsb,cbypledit,getHyflCodeData } from '../../api/sbyj';
    import { getYsxzData,getHyflData } from '../../api/sjst';
    export default {
        name: "sbyjHbgx",
        data() {
            return {
                xmid:this.$route.query.xmid,//项目id
                ldid:this.$route.query.ldid,//楼栋ID
                rwbh:this.$route.query.rwbh,//任务编号
                refId:this.$route.query.refId,
                activityId:this.$route.query.activityId,
                taskId:this.$route.query.taskId,
                xmdz:'',

                dxxs:false,

                row:[],//列表集合
                listLoading:false,
                sels:[],//选中列

                /*门牌号范围勾选*/
                filters:{
                    beginMp:'',
                    endMp:'',
                },
                /*门牌号范围勾选*/
                bch:'',

                /*列编辑*/
                ysxzList:[],//用水性质下拉框
                hyflList:[],//行业分类下拉框
                pqList:[],//营业中心片区下拉框
                sblxList:[],//水表类型下拉框
                sbcjList:[],//水表厂家下拉框
                jmbFormVisible:false,
                jmbform:{
                    sbid:'',
                    xmbh:'',hh:'',hm:'',ybdz:'',zbdz:'',azrq:'',tsrq:'',xcyjrq:'',xzds:'',chjbds:'',bsm:'',
                    sbkj:'',sbcj:'',sblx:'',ysxz:'',hyfl:'',dxfw:'N',dsljf:'0',bch:'',jffs:'',gps:'',pq:'',
                    jzmc:'',sjhm:'',sgdw:'',gss:'',yyzx:'',bz:''
                },
                /*列编辑*/
            }
        },
        mounted() {
            this.init_vue();
            this.getYsxzData_vue();
            this.getHyflData_vue();
        },
        methods: {
            //初始化方法
            init_vue(){
                let params = {xmid:this.xmid,ldid:this.ldid,yjdbh:this.rwbh};
                shJmbByLdid(params).then(res=>{
                    this.row = res.data.list;
                    this.pqList = res.data.pqList;
                    this.jmbform.xmbh = res.data.varList[0].XMBH;
                    this.sblxList = res.data.sblxList;
                    this.sbcjList = res.data.sbcjList;
                    this.xmdz = res.data.varList[0].XMDZ;
                });
            },
            //确认勾选
            qrgx_vue(){
                let beginMp = this.filters.beginMp;
                let endMp = this.filters.endMp;
                this.row.forEach((row,index)=>{
                    if(parseInt(beginMp) <= parseInt(row.MPH) && parseInt(endMp) >= parseInt(row.MPH)){
                        setTimeout(()=>{
                            this.$refs.editable.toggleRowSelection(this.row[index],true)
                        },1)
                    }else{
                        setTimeout(()=>{
                            this.$refs.editable.toggleRowSelection(this.row[index],false)
                        },1)
                    }
                })
            },
            //取消勾选
            qxgx_vue(){
                let row = this.row;
                let beginMp = this.filters.beginMp;
                let endMp = this.filters.endMp;
                this.row.forEach((row,index)=>{
                    if(parseInt(beginMp) <= parseInt(row.MPH) && parseInt(endMp) >= parseInt(row.MPH)){
                        setTimeout(()=>{
                            this.$refs.editable.toggleRowSelection(this.row[index],false)
                        },1)
                    }
                })
            },
            getYsxzData_vue(){
                getYsxzData().then((res)=>{
                    if(res.data!=null){
                        this.ysxzList = res.data.row;
                    }
                })
            },
            //获取行业分类
            getHyflData_vue(){
                getHyflData().then((res)=>{
                    if(res.data!=null){
                        this.hyflList = res.data.row;
                    }
                })
            },
            //进入列编辑界面
            edit_vue(index,row){
                this.jmbFormVisible = true;
                let lhh = row.YYZXHH == undefined ? '': row.YYZXHH;
                this.jmbform.hh = "新装"+row.HH +" 立户"+lhh;
                this.jmbform.hm = row.HM;
                if(row.MPH == ''){
                    this.jmbform.ybdz = "";
                }else{
                    this.jmbform.ybdz = row.YHDZ.replace("[楼号]",row.LDBH).replace("[门牌]",row.MPH);
                }
                this.jmbform.zbdz = row.ZBDZ;
                this.jmbform.azrq = row.AZRQ;
                this.jmbform.tsrq = row.TSRQ;
                this.jmbform.xcyjrq = row.XCYJRQ;
                this.jmbform.xzds = row.XZDS;
                this.jmbform.chjbds = row.CHJBDS;
                this.jmbform.bsm = row.BSM;
                let p = row.BZKJ == undefined ? '': row.BZKJ;
                let q = row.ZBKJ == undefined ? '': row.ZBKJ;
                this.jmbform.sbkj = "报装：Φ"+p +"mm 装表： Φ" + q + "mm";
                this.jmbform.sbcj = row.SBCJ;
                this.jmbform.sblx = row.SBLX;
                this.jmbform.ysxz = row.YSXZBH;
                this.jmbform.hyfl = row.HYFLBH;
                this.jmbform.dxfw = row.DXFWBH;
                if(row.DXFWBH == 'Y'){
                    this.dxxs = true;
                }
                this.jmbform.jzmc = row.DXMC;
                this.jmbform.sjhm = row.DXHM;
                this.jmbform.dsljf = row.ZSLJFBH;
                this.jmbform.bch = row.BCH;
                this.jmbform.jffs = row.JFFS;
                this.jmbform.gps = row.GPS;
                this.jmbform.pq = row.PQBH;
                this.jmbform.sgdw = row.YYZX_SGRY;
                this.jmbform.gss = row.YYZX_GSSRY;
                this.jmbform.yyzx = row.YYZX_YYZXRY;
                this.jmbform.bz = row.BZ;
                this.jmbform.sbid = row.ID;
            },
            //居民表保存
            jmbedit_vue(){
                this.$refs.jmbform.validate((valid) => {
                    if (valid) {
                        let params = this.jmbform;
                        this.listLoading = true;
                        cbyeditsb(params).then(res=>{
                            if(res == true){
                                this.$message({
                                    message: '保存成功',
                                    type: 'success'
                                });
                                this.jmbFormVisible = false;
                                this.init_vue();
                                this.listLoading = false;
                            }else{
                                this.$message.error('保存失败！');
                            }
                        });
                    }
                });
            },
            selsChange: function (sels) {
                this.sels = sels;
            },
            //批量编辑
            pledit(){
                if(this.bch == ''){
                    this.$message({
                        message: '请输入表册号！',
                        type: 'warning'
                    });
                }else{
                    let params = {bch:this.bch};
                    let list = this.sels;
                    let ids = "(";
                    list.forEach((row,index)=>{
                        if(index == 0){
                            ids += "'"+row.ID + "'";
                        }else{
                            ids += ",'"+row.ID + "'";
                        }
                    });
                    ids += ")";
                    params["ids"] = ids;
                    this.listLoading = true;
                    cbypledit(params).then(res=>{
                        if(res == true){
                            this.$message({
                                message: '保存成功',
                                type: 'success'
                            });
                            this.init_vue();
                            this.listLoading = false;
                        }else{
                            this.$message.error('保存失败！');
                        }
                    });
                }
            },
            //保存并退出
            save_vue(){
                this.$routerTab.close();
            },
            return_vue(){
                this.$routerTab.close();
            },
            formatYhdz(row, column){
                if(row.MPH == null){
                    return '';
                }else{
                    return row.YHDZ.replace("[楼号]",row.LDBH).replace("[门牌]",row.MPH);
                }
            }
        }
    }
</script>

<style scoped>
    .border-table {
        /*border-collapse: collapse;*/
       /* border: none;
        width: 100%;*/
    }
    .border-table td {
        border: solid #000 1px;
    }

</style>