<template>
    <section>
        <el-row>
            <el-col :span="24">
                <!--项目基本信息-->
                <el-card class="box-card" style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div class="div_title">项目信息
                            <span class="span_cz" @click="openBody('1')" v-if="!xmxxsfzk"><i class="el-icon-caret-right">点击展开</i></span>
                            <span class="span_cz" @click="closeBody('1')" v-if="xmxxsfzk"><i class="el-icon-caret-bottom">点击收起</i></span>
                        </div>
                    </div>
                    <el-form :model="filters" v-if="xmxxbody" >
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>合同编号 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="filters.htbh" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>安装类型 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="filters.azlx" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>项目编号 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="filters.xmbh" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>项目名称 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="filters.xmmc" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>联系人 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="filters.lxr" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>联系方式 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="filters.lxfs" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>建设单位 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="filters.jsdw" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>立项日期 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="filters.lxrq" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                    </el-form>
                </el-card>

                <!--预算金额-->
                <el-card class="box-card" style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">预算成果
                            <span class="span_cz" @click="openBody('2')" v-if="!ysjesfzk"><i class="el-icon-caret-right">点击展开</i></span>
                            <span class="span_cz" @click="closeBody('2')" v-if="ysjesfzk"><i class="el-icon-caret-bottom">点击收起</i></span>
                        </div>
                    </div>
                    <div class="text item" v-if="ysjebody">
                        <el-form ref="form" :model="formData" label-width="80px">
                            <el-form-item label="缴费项" label-width="200px">
                                <span class="cd">预算金额</span>
                                <span class="cd" style="margin-left: 20%">大写</span>
                                <span class="cd" style="margin-left: 22%">备注</span>
                            </el-form-item>
                            <el-form-item
                                    v-for="(a, index) in formData.domains"
                                    :label="a.mc"
                                    label-width="200px"
                                    :key="index"
                                    :prop="'domains.' + index + '.ysje'"
                            >
                                <el-input
                                        v-if="a.mc != null"
                                        v-model="a.ysje"
                                        @change="getZje(a.ysje,a.mc)"
                                        placeholder="预算金额"
                                        class="cd"
                                        size="small"
                                        id="n1"
                                        :min = 0
                                        :readonly="sfzd"
                                ></el-input>
                                <el-input class="cd" v-if="a.mc != null" size="small" v-model="a.dxysje" placeholder="大写" :readonly="true"></el-input>
                                <el-input class="cd" v-if="a.mc != null" size="small" v-model="a.bz" placeholder="备注" :readonly="sfzd"></el-input>
                            </el-form-item>
                            <JEJS :value="zje"></JEJS>
                        </el-form>
                    </div>
                </el-card>

                <!--合同信息-->
                <el-card class="box-card" style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">合同信息
                            <span class="span_cz" @click="openBody('3')" v-if="!htxxsfzk"><i class="el-icon-caret-right">点击展开</i></span>
                            <span class="span_cz" @click="closeBody('3')" v-if="htxxsfzk"><i class="el-icon-caret-bottom">点击收起</i></span>
                        </div>
                    </div>
                    <el-form :model="htForm" v-if="htxxbody" ref="htForm" class="demo-ruleForm">
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>工程名称 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="htForm.gcmc" :readonly="sfzd"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>工程地址 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="htForm.gcdz" :readonly="sfzd"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>项目类别 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                               <!-- <el-select v-model="htForm.xmlb" size="small" :disabled="sfzd"  clearable="" style="width: 252px;">
                                    <el-option v-for="item in xmflm"
                                               :key="item.codevalue"
                                               :label="item.codename"
                                               :value="item.codevalue"></el-option>
                                </el-select>-->
                                <el-input size="small" v-model="htForm.xmlb" :disabled="true" ></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>甲方单位 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="jfdw" :rules="this.publicUtil.notNull">
                                    <el-input size="small" v-model="htForm.jfdw" :readonly="sfzd"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>甲方开户行 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="htForm.jfkhh" :readonly="sfzd"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>开户行账号 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="htForm.jfzh" :readonly="sfzd"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>乙方单位 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="htForm.yfdw" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>乙方开户行 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="htForm.yfkhh" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>开户行账号 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="htForm.yfzh" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>总日历天数 &nbsp;</span>
                            </el-col>
                            <el-col :span="5"><!--:rules="this.publicUtil.InterNum"-->
                                <el-form-item prop="zrlts">
                                    <el-input size="small" v-model="htForm.zrlts" :readonly="sfzd"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>会签通过时间 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-date-picker
                                        v-model="htForm.hqtgsj"
                                        type="date"
                                        format="yyyy-MM-dd"
                                        value-format="yyyy-MM-dd"
                                        :readonly="sfzd"
                                        placeholder="选择日期" size="small" style="width: 252px;">
                                </el-date-picker>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>合同签订时间 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-date-picker
                                        v-model="htForm.htqdsj"
                                        type="date"
                                        format="yyyy-MM-dd"
                                        value-format="yyyy-MM-dd"
                                        :readonly="sfzd"
                                        placeholder="选择日期" size="small" style="width: 252px;">
                                </el-date-picker>
                            </el-col>
                        </el-form-item>
                    </el-form>
                </el-card>

                <!--结算方式-->
                <el-card class="box-card" style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">结算方式
                            <span class="span_cz" @click="openBody('4')" v-if="!jsfssfzk"><i class="el-icon-caret-right">点击展开</i></span>
                            <span class="span_cz" @click="closeBody('4')" v-if="jsfssfzk"><i class="el-icon-caret-bottom">点击收起</i></span>
                        </div>
                    </div>
                    <el-form v-if="jsfsbody">
                        <el-checkbox-group v-model="jsvalue">
                            <el-col :span="24" v-model="jsdata" v-for="(item,index) in jsdata" class="col"
                                    style="margin-top: 1px" :key="index">
                                <el-form-item>
                                    <el-col :span="8" :push="3">
                                        <el-checkbox v-model="jsradio" :disabled="sfzd" :label="item.bh">{{item.mc}}</el-checkbox>
                                    </el-col>
                                    <el-col :span="10">
                                        <el-input size="small" v-model="item.ms" :readonly="true"></el-input>
                                    </el-col>
                                    <el-col :span="2">&nbsp;</el-col>
                                    <el-col :span="4">
                                        <el-input size="small" placeholder="请输入备注" :readonly="sfzd" v-model="item.bz"></el-input>
                                    </el-col>
                                </el-form-item>
                            </el-col>
                        </el-checkbox-group>
                    </el-form>
                </el-card>

                <el-card class="box-card" style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">支付方式
                            <span class="span_cz" @click="openBody('7')" v-if="!zffsfzk"><i class="el-icon-caret-right">点击展开</i></span>
                            <span class="span_cz" @click="closeBody('7')" v-if="zffsfzk"><i class="el-icon-caret-bottom">点击收起</i></span>
                        </div>
                    </div>
                    <div class="text item" v-if="zffsbody">
                        <el-row>
                            <el-col :span="6" :push="3" class="col">
                                <span class="line_h"><el-radio v-model="zffs" :disabled="sfzd" :label="1" @change="zffsChange">一次性付清</el-radio></span>
                            </el-col>
                            <el-col :span="6" :push="3" class="col">
                                <span class="line_h"><el-radio v-model="zffs" :disabled="sfzd" :label="2" @change="zffsChange">分期支付</el-radio></span>
                            </el-col>
                            <el-col :span="12" :push="3" class="col" v-if="fqsForm">
                                <div v-if="ishx == '1'">
                                    分期数&nbsp;<el-input v-model="fqs" size="small" style="width: 30%" placeholder="分期数为2--6期"
                                                       @keyup.native="numberFormatter" :readonly="sfzd"></el-input>
                                </div>
                                <div v-else>
                                    分期数&nbsp;<el-input v-model="fqs" size="small" style="width: 30%" placeholder="分期数为2--6期"
                                                       @keyup.native="numberFormatter" :readonly="sfzd"></el-input>
                                    &nbsp;<el-button size="small" @click="saveFqs" >确定</el-button>
                                </div>
                            </el-col>
                        </el-row>
                        <el-row v-if="fqsForm">
                            <el-card class="box-card" style="margin-top: 10px;" v-model="fqData.domains" v-for="item in fqData.domains">
                                <div class="text item" v-if="item.qs!=fqs">
                                    第 {{item.qs}} 期： 付款总金额：{{item.zje}}元（{{item.perce}}%）
                                    <span @click="setfqje(item.qs)" ><a style="cursor: pointer;color: #409eff;">设置</a></span>
                                </div>
                                <div class="text item" v-if="item.qs==fqs">
                                    第 {{item.qs}} 期： 付款总金额：{{item.zje}}元（{{item.perce}}%）
                                    <span @click="showfqje(item.qs)" ><a style="cursor: pointer;color: #409eff;">查看</a></span>
                                </div>
                            </el-card>
                        </el-row>
                    </div>
                </el-card>

                <!--合同凭据上传-->
                <el-card class="box-card" style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">合同凭据上传
                            <span class="span_cz" @click="openBody('6')" v-if="!htscsfzk"><i class="el-icon-caret-right">点击展开</i></span>
                            <span class="span_cz" @click="closeBody('6')" v-if="htscsfzk"><i class="el-icon-caret-bottom">点击收起</i></span>
                        </div>
                    </div>
                    <div id="row1" v-if="htscbody">
                        <div v-if="ishx == '1'">

                        </div>
                        <div v-else>
                            <el-form :inline="true">
                                <el-upload
                                        class="upload-demo"
                                        ref="upload"
                                        action="#"
                                        :show-file-list='false'
                                        :multiple=true
                                        :on-preview="handlePreview"
                                        :http-request="submitUpload"
                                        :on-remove="handleRemove"
                                        :on-change="handleChange"
                                        :file-list="fileList">
                                    <el-button slot="trigger" size="mini" type="success">
                                        <i class="iconfont icon-shangchuan_f"></i>选取文件</el-button>
                                    <!--<el-button style="margin-left: 10px;"  size="mini" type="success" @click="submitPs($event)">-->
                                    <!--<i class="iconfont icon-paishe"></i>拍摄</el-button>-->
                                </el-upload>
                            </el-form>
                        </div>
                        <el-table :data="gridData" :stripe="true" height="250" style="padding-bottom: 10px">
                            <el-table-column property="FJMC" label="附件名称" align="center"></el-table-column>
                            <el-table-column property="CJSJ" label="上传时间" align="center"></el-table-column>
                            <el-table-column property="USERNAME" label="上传人" align="center"></el-table-column>
                            <el-table-column property="" label="操作" align="center">
                                <template slot-scope="scope">
                                    <el-row>
                                        <el-col :span="24">
                                            <div v-if="ishx == '1'">
                                                <label title="下载">
                                                    <i class="iconfont icon-xiazai"
                                                       @click="downloadFile_vue(scope.$index, scope.row)"
                                                       style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                                </label>
                                            </div>
                                            <div v-else>
                                                <label title="下载">
                                                    <i class="iconfont icon-xiazai"
                                                       @click="downloadFile_vue(scope.$index, scope.row)"
                                                       style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                                </label>
                                                <label title="删除">
                                                    <i class="iconfont delete"
                                                       @click="deleteFj_vue(scope.$index, scope.row)"
                                                       style="cursor:pointer;padding: 2px;color: #f78989"></i>
                                                </label>
                                            </div>
                                        </el-col>
                                    </el-row>
                                </template>
                            </el-table-column>
                        </el-table>
                    </div>
                </el-card>

                <div v-if="ishx == '1'">

                </div>
                <div v-else>
                    <el-row style="text-align: center;margin-top: 25px;">
                        <router-link :to="'/Xmlb'">
                            <el-button size="mini" type="info" @click="toReturn_vue" >
                                <i class="iconfont icon-quxiao"></i>取消
                            </el-button>
                        </router-link>
                        <router-link :to="zpurl">
                            <el-button size="mini" type="primary" :loading="submitLoading" @click="add_vue">
                                <i class="iconfont icon-queren"></i>保存
                            </el-button>
                        </router-link>
                        <el-button size="mini" type="primary" @click="goxmqj()" >
                            <i class="iconfont icon-xiangmuquanjing_"></i>项目全景
                        </el-button>
                    </el-row>
                </div>
            </el-col>
        </el-row>



        <el-dialog title="设置分期金额" :visible.sync ="editFormVisible" :close-on-click-modal="false" top="8vh">
            <el-form label-width="80px" ref="editForm" size="small" class="dialog">
                <el-form-item v-for="(a, index) in qData.domains"
                        :label="a.mc"
                        label-width="120px"
                        :key="index"
                        :prop="'domains.' + index + '.ysje'">
                    <el-input v-if="a.mc != null" v-model="a.ysje"
                            placeholder="预算金额" :readonly="fqReadonly" @keyup.native="fqjeFormatter(index)" @change="getFqzje"
                            class="cd"></el-input>
                    <el-input class="cd" v-if="a.fqperce != null" size="small" v-model="a.fqperce" style="width: 15%" @change="getFqzje"
                              placeholder="占比" @keyup.native="fqperFormatter(index)"></el-input>
                    <span v-if="a.fqperce != null">%</span>
                    <span v-if="a.fqperce != null">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        （共 {{formData.domains[index].ysje}} 元）
                    </span>
                </el-form-item>
                <JEJS :value="qData.zje" style="margin-top: 20px;"></JEJS>
            </el-form>
            <div slot="footer" class="dialog-footer" style="text-align: center;" v-if="editFormButtom">
                <el-button size="mini" type="info" @click.native="editFormVisible = false">
                    <i class="iconfont icon-quxiao"></i>取消</el-button>

                <el-button size="mini" type="primary" @click="editSubmit" :loading="editLoading" v-if="sfzd != true">
                    <i class="iconfont icon-queren"></i>保存</el-button>
            </div>
        </el-dialog>
    </section>
</template>

<script>
    import Vue from "vue"
    import { queryxmxxByid,importFile,queryZllist,deleteFjByid,download,queryYslist,saveHt,saveFqje,findFqje,queryHtbz } from '../../api/htsf';
    import JEJS from '../../components/Jejs.vue';
    export default {
        name: "Htbz",
        components: {
            "JEJS":JEJS
        },
        data() {
            return {
                ishx:this.$route.query.ishx,
                sfzd:false,
                xmflm:[],//项目分类码
                xmid:this.$route.query.xmid,
                isEdit:this.$route.query.isEdit,
                htzbid:'',
                ehtzbid:this.$route.query.ehtzbid,
                editzbid:'',
                submitLoading: false,
                zpurl:'',
                filters:{
                    htbh:'',azlx:'',xmbh:'',xmmc:'',lxr:'',lxfs:'',jsdw:'',lxrq:''
                },
                htForm:{
                    gcmc:'',gcdz:'',jfdw:'',jfkhh:'',jfzh:'',yfdw:'',yfkhh:'',yfzh:'',
                    zrlts:'',hqtgsj:'',htqdsj:'',xmlb:''
                },
                /*预算金额*/
                formData: {
                    domains: [{}]
                },
                zje: 0,
                /*结算方式*/
                jsvalue:[],
                jsdata:[],//结算方式集合
                jsradio:[],//选中结算方式
                /*合同凭证上传*/
                gridData:[],
                fileList:[],//上传文件集合
                fileIds:[],//已上传的资料ID集合

                //支付方式
                zffs: 1,
                fqsForm : false,
                editFormVisible : false,
                editFormButtom : true,
                editLoading : false,
                fqReadonly : false,
                fqs: '',//分期数
                dqq: 0,//当前编辑第几期
                fqData: {
                    domains: []
                },
                qData: {
                    domains: [],
                    zje:0
                },
                xmxxbody:false,
                xmxxsfzk:false,
                ysjebody:true,
                ysjesfzk:true,
                htxxbody:true,
                htxxsfzk:true,
                jsfsbody:true,
                jsfssfzk:true,
                htscbody:true,
                htscsfzk:true,
                zffsbody:true,
                zffsfzk:true
            }
        },
        mounted(){
          this.queryinit();
          this.queryZllist();
          this.queryYslist();
        },
        methods:{
            //跳转至项目全景
            goxmqj(){
                this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid  }})
            },
            //初始化方法
            queryinit(){
                if(this.ishx == '1'){
                    this.sfzd = true;
                    this.fqReadonly = true;
                }
                let para = {xmid:this.xmid,isEdit:this.isEdit};
                queryxmxxByid(para).then(res=>{
                    console.log(res);
                    this.filters.azlx = res.data.list[0].azlxname;
                    this.filters.xmbh = res.data.list[0].xmbh;
                    this.filters.xmmc = res.data.list[0].xmmc;
                    this.filters.lxr = res.data.list[0].lxr;
                    this.filters.lxfs = res.data.list[0].lxrsjh;
                    this.filters.jsdw = res.data.list[0].dwmc;
                    this.filters.lxrq = res.data.list[0].lxsjname;
                    this.filters.htbh = res.data.htbh;
                    this.htForm.gcmc = res.data.list[0].xmmc;
                    this.htForm.gcdz = res.data.list[0].xmdz;
                    this.htForm.jfdw = res.data.list[0].dwmc;
                    this.htForm.yfdw = res.data.gskhyhList[0].NAME;
                    this.htForm.yfkhh = res.data.gskhyhList[0].CODE;
                    this.htForm.yfzh = res.data.gskhyhList[0].VALUE;
                    this.jsdata = res.data.jsfsList;
                    this.htzbid = res.data.htzbid;
                   /* this.xmflm = res.data.xmflm;*/
                    this.htForm.xmlb = res.data.list[0].azlxname;
                    if(this.isEdit == '1'){
                        this.htForm.xmlb = res.data.list[0].xmlb;
                    }
                });
                if(this.isEdit == "1"){
                    queryHtbz(para).then(res=>{
                        let list = res.list[0];
                        this.htForm.jfdw = list.JFDW == undefined ? '' :list.JFDW;
                        this.htForm.jfkhh = list.JFKHH == undefined ? '' :list.JFKHH;
                        this.htForm.jfzh = list.YHZH == undefined ? '' :list.YHZH;
                        this.htForm.zrlts = list.GQZRLTS == undefined ? '' :list.GQZRLTS;
                        this.htForm.hqtgsj = list.TGSJ;
                        this.htForm.htqdsj = list.QDSJ;
                        this.jsvalue = res.jsvalue;
                        this.editzbid = list.ID;
                        this.zffs = list.ZFFS;
                        this.fqs = list.FQS;
                    });
                    this.initFqxx();
                }
            },
            openBody(xs) {
                switch (xs) {
                    case '1':
                        this.xmxxbody = true;
                        this.xmxxsfzk = true;
                        break;
                    case '2':
                        this.ysjebody = true;
                        this.ysjesfzk = true;
                        break;
                    case '3':
                        this.htxxbody = true;
                        this.htxxsfzk = true;
                        break;
                    case '4':
                        this.jsfsbody = true;
                        this.jsfssfzk = true;
                        break;
                    case '6':
                        this.htscbody = true;
                        this.htscsfzk = true;
                        break;
                    case '7':
                        this.zffsbody = true;
                        this.zffsfzk = true;
                        break;

                }
            },
            closeBody(xs) {
                switch (xs) {
                    case '1':
                        this.xmxxbody = false;
                        this.xmxxsfzk = false;
                        break;
                    case '2':
                        this.ysjebody = false;
                        this.ysjesfzk = false;
                        break;
                    case '3':
                        this.htxxbody = false;
                        this.htxxsfzk = false;
                        break;
                    case '4':
                        this.jsfsbody = false;
                        this.jsfssfzk = false;
                        break;
                    case '6':
                        this.htscbody = false;
                        this.htscsfzk = false;
                        break;
                    case '7':
                        this.zffsbody = false;
                        this.zffsfzk = false;
                        break;
                }
            },
            toReturn_vue(){
                this.$routerTab.close();
                this.$routerTab.refresh('/Xmlb');
            },
            zffsChange(radio){
                if(radio == 2){
                    this.fqsForm = true;
                }else if(radio == 1){
                    this.fqsForm = false;
                }
            },
            numberFormatter(){
                this.fqs=this.fqs.replace(/[^\.\d]/g,'');
                this.fqs=this.fqs.replace('.','');
            },
            fqjeFormatter(index){
                this.qData.domains[index].ysje = this.qData.domains[index].ysje.trim() != "" ? this.qData.domains[index].ysje :0
                let sm = parseFloat(this.qData.domains[index].ysje);
                let zm = parseFloat(this.formData.domains[index].ysje);
                let per = (sm/zm*100).toFixed(2);
                //if(per.indexOf(".")){}
                this.qData.domains[index].fqperce = per;
            },
            fqperFormatter(index){
                this.qData.domains[index].fqperce = this.qData.domains[index].fqperce.trim() != "" ? this.qData.domains[index].fqperce :0
                let per = parseFloat(this.qData.domains[index].fqperce);
                let zm = parseFloat(this.formData.domains[index].ysje);
                let je = this.formData.domains[index].ysje.toFixed(2);
                if(je.charAt(je.length-1) == '1' || je.charAt(je.length-1) == '3'
                || je.charAt(je.length-1) == '5' || je.charAt(je.length-1) == '7' || je.charAt(je.length-1) == '9'){
                    this.qData.domains[index].ysje = ((per/100 * zm).toFixed(2) - 0.01).toFixed(2);
                }else {
                    this.qData.domains[index].ysje = (per/100 * zm).toFixed(2);
                }

            },
            initFqxx() {//合同编辑  初始化分期支付设置
                if(this.zffs = 2){
                    this.fqsForm = true;
                    let params = {htzbid : this.ehtzbid}
                    findFqje(params).then(res=>{
                        this.fqData.domains = res.data.fqxx;
                    })
                }
            },
            saveFqs() {//设置分期数
                if(parseInt(this.fqs)>6 || parseInt(this.fqs)<2) {
                    this.fqs = "";
                    this.$message({
                        message: '分期数只能为2—6期',
                        type: 'warning'
                    });
                }else {
                    if(this.fqData.domains.length != 0){
                        this.$confirm('修改分期数将会重置当前的分期设置?', '提示', {
                            type: 'warning'
                        }).then(() => {
                            this.setFq();
                        })
                    }else {
                        this.setFq();
                    }
                }
            },
            setFq() {//设置分期初始金额配比
                this.fqData.domains = [];
                for( let o=1;o<=this.fqs;o++){
                    let fqmx = JSON.parse(JSON.stringify(this.formData.domains));//复制对象的属性值
                    if(o == this.fqs){
                        let zje = 0;
                        for (let i = 0; i < fqmx.length; i++) {
                            var propName = fqmx[i];
                            if(typeof(propName.ysje) != "undefined"){
                                zje = (parseFloat(zje) + parseFloat(propName.ysje)).toFixed(2);
                                fqmx[i].fqperce = '100';
                            }
                        }
                        this.fqData.domains.push({qs:o,zje:zje,perce:'100',bz:'',fqmx:fqmx})
                    }else {
                        for (let i = 0; i < fqmx.length; i++) {
                            if(typeof(fqmx[i].ysje) != "undefined"){
                                fqmx[i].ysje = '0';
                                fqmx[i].fqperce = '0';
                            }
                        }
                        this.fqData.domains.push({qs:o,zje:'0',perce:'0',bz:'',fqmx:fqmx})
                    }
                }
                this.sumbitFqToDb();
            },
            sumbitFqToDb(){//保存分期设置到数据库
                let datas = {};
                for(let i =0;i<this.fqData.domains.length;i++){
                    datas[ 'zje'+(i+1) ] = this.fqData.domains[i].zje;
                    datas[ 'perce'+(i+1) ] = this.fqData.domains[i].perce;
                    let pams = {};
                    for(let j =0;j<this.fqData.domains[i].fqmx.length;j++){
                        let obj = this.fqData.domains[i].fqmx[j];
                        pams['fqje'+(j+1)] = obj.ysje;
                        pams['fqperce'+(j+1)] = obj.fqperce;
                        pams['ysfl'+(j+1)] = obj.ysfl == undefined ? obj.fqfl:obj.ysfl;
                        pams['mc'+(j+1)] = obj.mc;
                    }
                    datas[ 'fqmx'+(i+1) ] = pams;
                }
                let htzbid = "";
                if(this.isEdit == "1"){
                    htzbid = this.ehtzbid;
                }else {
                    htzbid = this.htzbid
                }
                let params = {datas : JSON.stringify(datas),htzbid : htzbid};//JSON.stringify(this.fqData.domains)
                saveFqje(params).then(res=>{

                })
            },
            setfqje(o){
                this.dqq = o;
                this.qData.domains = JSON.parse(JSON.stringify(this.fqData.domains[o-1].fqmx));//复制对象的属性值
                this.qData.zje = this.fqData.domains[o-1].zje;
                //this.qData.domains = this.fqData.domains[--o].fqmx;
                this.fqReadonly = false;
                this.editFormButtom = true;
                this.editFormVisible = true;
            },
            showfqje(o){
                this.qData.domains = JSON.parse(JSON.stringify(this.fqData.domains[o-1].fqmx));//复制对象的属性值
                this.qData.zje = this.fqData.domains[o-1].zje;
                //this.qData.domains = this.fqData.domains[--o].fqmx;
                this.fqReadonly = true;
                this.editFormButtom = false;
                this.editFormVisible = true;
            },
            getFqzje(){
                let zje = 0;
                for(let i = 0;i<this.qData.domains.length;i++){
                    if(this.qData.domains[i].ysje != undefined && this.qData.domains[i].ysje != ""){
                        let je = parseFloat(this.qData.domains[i].ysje);
                        zje = parseFloat(zje.toFixed(2)) + parseFloat(je.toFixed(2));
                        var oNumberObject=new  Number(zje);
                        zje = parseFloat(oNumberObject.toFixed(2));
                    }
                }
                this.qData.zje = zje;
            },
            editSubmit(){//保存分期金额设置
                let o = this.dqq -1;
                let fqmx = JSON.parse(JSON.stringify(this.qData.domains));//复制对象的属性值
                let now_fqmx = JSON.parse(JSON.stringify(this.fqData.domains[o].fqmx));//当前设置这期保存好的数据
                let last_fqmx = JSON.parse(JSON.stringify(this.fqData.domains[this.fqData.domains.length-1].fqmx));//最后一期
                for(let j=1;j<fqmx.length;j++){
                    if(fqmx[j].ysje != undefined && fqmx[j].ysje != ""){
                        if( parseFloat(fqmx[j].ysje) > ( parseFloat(last_fqmx[j].ysje) + parseFloat(now_fqmx[j].ysje) ) ){
                            this.$message({
                                message: fqmx[j].mc+'费用已经超出，请重新设置！',
                                type: 'error'
                            });
                            return;
                        }
                    }
                }
                this.fqData.domains[o].fqmx = fqmx;
                this.fqData.domains[o].zje = this.qData.zje;
                this.qData.perce = (parseFloat(this.qData.zje) / parseFloat(this.zje) *100).toFixed(2);
                this.fqData.domains[o].perce = this.qData.perce;
                Vue.set(this.fqData.domains,o,this.fqData.domains[o])//设置当前期显示的金额

                //计算最后一期的金额
                //let last_fqmx = JSON.parse(JSON.stringify(this.fqData.domains[this.fqData.domains.length-1].fqmx));
                let last_zje = 0;
                for(let i =1;i<last_fqmx.length;i++){//从一开始
                    if(last_fqmx[i].ysje != undefined && last_fqmx[i].ysje != ""){
                        /*if( parseFloat(now_fqmx[i].ysje) > parseFloat(fqmx[i].ysje) ){
                            last_fqmx[i].ysje = parseFloat(last_fqmx[i].ysje) + parseFloat(now_fqmx[i].ysje) - parseFloat(fqmx[i].ysje);
                        }else {
                            last_fqmx[i].ysje = parseFloat(last_fqmx[i].ysje) + parseFloat(now_fqmx[i].ysje) - parseFloat(fqmx[i].ysje);
                        }*/
                        last_fqmx[i].ysje = (parseFloat(last_fqmx[i].ysje) + parseFloat(now_fqmx[i].ysje) - parseFloat(fqmx[i].ysje)).toFixed(2);
                        last_zje += parseFloat(last_fqmx[i].ysje);
                        last_fqmx[i].fqperce = ( parseFloat(last_fqmx[i].ysje)/parseFloat(this.formData.domains[i].ysje )*100 ).toFixed(2);
                    }
                }
                this.fqData.domains[this.fqData.domains.length-1].fqmx = last_fqmx;
                this.fqData.domains[this.fqData.domains.length-1].zje = parseFloat(last_zje).toFixed(2);
                this.fqData.domains[this.fqData.domains.length-1].perce = (last_zje / parseFloat(this.zje) *100).toFixed(2);
                Vue.set(this.fqData.domains,this.fqData.domains.length-1,this.fqData.domains[this.fqData.domains.length-1])//设置最后一期显示的金额

                this.sumbitFqToDb();
                this.editFormVisible = false;
            },
            add_vue(){
                this.$refs.htForm.validate((valid) => {
                    if (valid) {
                        if(this.jsvalue == ''){
                            this.$message({
                                message: '请选择结算方式！',
                                type: 'warning'
                            });
                            if(this.isEdit == '1'){
                                this.zpurl = '/Htbz?xmid='+this.xmid+"&isEdit=1&ehtzbid="+this.editzbid;
                            }else{
                                this.zpurl = '/Htbz?xmid='+this.xmid;
                            }
                        }else{
                            let fqs = 0;
                            if(this.zffs == 2){
                                fqs = this.fqs;
                            }
                            let para = Object.assign({jsfs:this.jsvalue,xmid:this.xmid,htbh: this.filters.htbh,
                                gczje:this.zje,htzbid:this.htzbid,zffs:this.zffs,fqs:fqs,editzbid:this.editzbid,
                                    isEdit:this.isEdit}
                            ,this.htForm);
                            let list = this.formData.domains;
                            let i = 0;
                            let j = 0;
                            this.zpurl = '/Xmlb';
                            this.$routerTab.close();
                            list.forEach((row, index) => {
                                if(index != 0){
                                    para['ysje'+(index)] = row.ysje;
                                    para['ysfl'+(index)] = row.ysfl;
                                    para['bz'+(index)] = row.bz;
                                    i++;
                                }
                            });
                            para['counts'] = i;
                            let jslist = this.jsvalue;
                            let bzlist = this.jsdata;
                            let jsfs = "";
                            jslist.forEach((row,index)=>{
                                bzlist.forEach((rows,indexs)=>{
                                    if(row == rows.bh){
                                        jsfs += row+"%%"+rows.bz+"@#";
                                    }
                                });
                            });
                            if(jsfs != ''){
                                para['Subjsfs'] = jsfs.substring(0,jsfs.length-2);
                            }
                            saveHt(para).then(res=>{
                                if(res == true){
                                    this.$message({
                                        message: '保存成功',
                                        type: 'success'
                                    });
                                }else{
                                    this.$message.error('保存失败！');
                                }
                                this.$routerTab.refresh('/Xmlb');
                            });
                        }
                    }else{
                        this.zpurl = '/Htbz?xmid='+this.xmid;
                    }
                });
            },
            //查询预算项
            queryYslist(){
                let para = {xmid:this.xmid};
                queryYslist(para).then(res=>{
                    let list = res.data.ysList;
                    for (let i = 0; i < list.length; i++) {
                        this.formData.domains.push({
                            bz: list[i].BZ,
                            ysje: list[i].YSJE,
                            ysfl: list[i].YSFL,
                            mc: list[i].MC,
                            dxysje: this.toChies(list[i].YSJE)
                        });
                    }
                    this.getZje();
                });
            },
            //得到总金额
            getZje(ysje,mc) {
                let data = this.formData.domains;
                this.zje = 0;
                data.forEach((a, index) => {
                    if (a.ysje != '' && index != 0) {
                        let num = parseFloat(a.ysje).toFixed(2);
                        if (this.zje == 0) {
                            this.zje = num;
                        } else {
                            this.zje = (parseFloat(num) + parseFloat(this.zje)).toFixed(2);
                        }
                    }
                    if(mc == a.mc){
                        a.dxysje = this.toChies(ysje);
                    }
                });
            },
            //拍摄
            submitPs(){

            },
            //上传
            submitUpload(event) {
                let fileList = this.fileList;
                if (fileList !=null && fileList.length >0){
                    // for (let i=0;i<fileList.length;i++){
                        let file = fileList[fileList.length-1];
                        let formData = new FormData();
                        if(this.isEdit == "1"){
                            formData.append("htzbid",this.ehtzbid);
                        }else{
                            formData.append("htzbid",this.htzbid);
                        }
                        formData.append("multipartFiles",file.raw);
                        formData.append("filename",file.name);
                        formData.append("xmid",this.xmid);
                        importFile(formData).then((res) => {
                            let msg = res.msg;
                            if(msg == '上传成功'){
                                this.$message({
                                    message: '上传成功',
                                    type: 'success'
                                });
                                let fileId = res.fileId;
                                this.fileIds = this.fileIds.concat(fileId);
                            }else{
                                this.$message.error('上传失败！');
                            }
                            this.fileList = [];
                            this.queryZllist();
                        });
                    // }
                }
            },
            //查询已上传资料
            queryZllist(){
                let para = {};
                if(this.isEdit == "1"){
                    para = {htzbid:this.ehtzbid};
                }else{
                    para = {htzbid:this.htzbid};
                }
                queryZllist(para).then(res=>{
                    this.gridData = res.data.fjList;
                });
            },
            //删除附件
            deleteFj_vue(index,row){
                let para = {fjId:row.ID};
                deleteFjByid(para).then(res=>{
                    this.queryZllist();
                });
            },
            //下载附件
            downloadFile_vue(index,row){
                let formData = new FormData();
                formData.append("path",row.FJLJ);
                download(formData).then((res)=>{
                    const content = res.data;
                    const blob = new Blob([content]);
                    const fileName = row.FJMC;
                    if ('download' in document.createElement('a')) { // 非IE下载
                        const elink = document.createElement('a');
                        elink.download = fileName;
                        elink.style.display = 'none';
                        elink.href = URL.createObjectURL(blob);
                        document.body.appendChild(elink);
                        elink.click();
                        URL.revokeObjectURL(elink.href); // 释放URL 对象
                        document.body.removeChild(elink);
                    } else { // IE10+下载
                        navigator.msSaveBlob(blob, fileName);
                    }
                });
            },
            handleRemove(file, fileList) {
            },
            handlePreview(file) {
            },
            handleChange(file,fileList){
                this.fileList = fileList;
            },
            toChies:function(money){
                //汉字的数字
                var cnNums = new Array('零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖');
                //基本单位
                var cnIntRadice = new Array('', '拾', '佰', '仟');
                //对应整数部分扩展单位
                var cnIntUnits = new Array('', '万', '亿', '兆');
                //对应小数部分单位
                var cnDecUnits = new Array('角', '分', '毫', '厘');
                //整数金额时后面跟的字符
                var cnInteger = '整';
                //整型完以后的单位
                var cnIntLast = '元';
                //最大处理的数字
                var maxNum = 999999999999999.9999;
                //金额整数部分
                var integerNum;
                //金额小数部分
                var decimalNum;
                //输出的中文金额字符串
                var chineseStr = '';
                //分离金额后用的数组，预定义
                var parts;
                if (money == '') { return ''; }
                money = parseFloat(money);
                if (money >= maxNum) {
                    //超出最大处理数字
                    return '';
                }
                if (money == 0) {
                    chineseStr = cnNums[0] + cnIntLast + cnInteger;
                    return chineseStr;
                }
                //转换为字符串
                money = money.toString();
                if (money.indexOf('.') == -1) {
                    integerNum = money;
                    decimalNum = '';
                } else {
                    parts = money.split('.');
                    integerNum = parts[0];
                    decimalNum = parts[1].substr(0, 4);
                }
                //获取整型部分转换
                if (parseInt(integerNum, 10) > 0) {
                    var zeroCount = 0;
                    var IntLen = integerNum.length;
                    for (var i = 0; i < IntLen; i++) {
                        var n = integerNum.substr(i, 1);
                        var p = IntLen - i - 1;
                        var q = p / 4;
                        var m = p % 4;
                        if (n == '0') {
                            zeroCount++;
                        } else {
                            if (zeroCount > 0) {
                                chineseStr += cnNums[0];
                            }
                            //归零
                            zeroCount = 0;
                            chineseStr += cnNums[parseInt(n)] + cnIntRadice[m];
                        }
                        if (m == 0 && zeroCount < 4) {
                            chineseStr += cnIntUnits[q];
                        }
                    }
                    chineseStr += cnIntLast;
                }
                //小数部分
                if (decimalNum != '') {
                    var decLen = decimalNum.length;
                    for (var i = 0; i < decLen; i++) {
                        var n = decimalNum.substr(i, 1);
                        if (n != '0') {
                            chineseStr += cnNums[Number(n)] + cnDecUnits[i];
                        }
                    }
                }
                if (chineseStr == '') {
                    chineseStr += cnNums[0] + cnIntLast + cnInteger;
                } else if (decimalNum == '') {
                    chineseStr += cnInteger;
                }
                return chineseStr;
            }
        },
    }
</script>

<style scoped>
    .div_title {
        margin:-18px -20px;
        padding:10px 20px;
        background: #f5f7fa;
        height: 18px;
    }
    .span_cz {
        display: inline-block;
        float: right;
        cursor: pointer;
        color: #666;
    }
    .cd {
        width: 25%;
        padding-left: 50px;
    }
    .line_h {
        line-height: 32px;
    }
    .dialog .el-form-item--small.el-form-item {
        margin-bottom: 5px;
    }
</style>