<template>
    <section>
        <el-row>
            <!--工具条-->
            <el-col :span="24">
                <el-card class="box-card" style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">基本信息</div>
                    </div>
                    <el-form  :model="filters" >
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>项目编号 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="filters.xmbh" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>项目名称 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="filters.xmmc" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>建设单位 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="filters.jsdw" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>用户联系人 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="filters.lxr" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>联系方式 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="filters.lxrsjh" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>通知单编号 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="filters.tzbh" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>缴费单位 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="filters.jfdw"></el-input>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>收款账号 &nbsp;</span>
                            </el-col>
                            <el-col :span="12">
                                <el-input size="small" v-model="filters.skzh"  :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>缴费说明 &nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" type="textarea" v-model="filters.xmqk" :autosize="{ minRows: 8, maxRows: 10}"></el-input>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>温馨提示 &nbsp;</span>
                            </el-col>
                            <el-col :span="12">
                                <el-input size="small" type="textarea" v-model="filters.bz" :autosize="{ minRows: 8, maxRows: 10}"></el-input>
                            </el-col>
                        </el-form-item>
                    </el-form>
                </el-card>
                <el-card class="box-card" style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">缴费
                            <el-button style="float: right; padding: 3px 0" type="text" @click="openYsxx">选择缴费项</el-button>
                        </div>
                    </div>
                    <el-row>
                        <el-form :model="formData" ref="formData" v-for="(domain, index) in formData.domains">
                            <el-form-item>
                                <el-row>
                                    <el-col :span="3" style="text-align: right">
                                        <span>{{domain.label}}&nbsp;&nbsp;</span>
                                    </el-col>
                                    <el-col :span="21">
                                        <div v-if="domain.bh == '06'">
                                            <el-input size="small" v-model="domain.value"
                                                      @change="getZje(domain.value)"
                                                      @blur="addToZje(domain.value)" :readonly="true" style="width: 240px"></el-input>
                                            <el-tag type="info">{{domain.tag}}</el-tag>
                                            <el-button type="text"
                                                       @click="openGs_vue">填子项自动汇总</el-button>
                                            <span style="width: 300px; "><b>大写 ：</b></span>
                                            <strong>{{domain.value | toChies}}</strong>
                                        </div>
                                        <div v-else-if="domain.bh == '05'">
                                            <el-input size="small" v-model="domain.value"
                                                      @change="getZje(domain.value)"
                                                      @blur="addToZje(domain.value)" :readonly="true" style="width: 240px"></el-input>
                                            <el-tag type="info">{{domain.tag}}</el-tag>
                                            <el-button type="text"
                                                       @click="openHb_vue">填子项自动汇总</el-button>
                                            <span style="width: 300px; "><b>大写 ：</b></span>
                                            <strong>{{domain.value | toChies}}</strong>
                                        </div>
                                        <div v-else>
                                            <el-form-item :key="domain.key" :prop="'domains.' + index + '.value'"
                                                          :rules="{required: true, pattern: /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/,
                                                            message: '请输入正确的金额', trigger: 'blur'}">
                                                <el-input size="small" style="width: 240px" v-model="domain.value"
                                                          @change="getZje(domain.value)"
                                                          @blur="addToZje(domain.value)"></el-input>
                                                <el-tag type="info">{{domain.tag}}</el-tag>
                                                <span style="width: 300px; "><b>大写 ：</b></span>
                                                <strong>{{domain.value | toChies}}</strong>
                                            </el-form-item>
                                        </div>
                                    </el-col>
                                </el-row>
                            </el-form-item>
                        </el-form>
                    </el-row>
                    <el-row>
                        <el-col style="text-align: left;">
                            <JEJS :value="zje"></JEJS>
                        </el-col>
                    </el-row>
                </el-card>
                <el-row style="text-align: center;margin-top: 25px;">
                    <router-link :to="{path:'/Jftzd', query:{xmid:this.filters.xmid,dwmc:this.filters.jfdw,
                                azlx:this.filters.azlx,sqlx:this.filters.sqlx,xmbh:this.filters.xmbh,
                                lxr:this.filters.lxr,lxrsjh:this.filters.lxrsjh}}">
                        <el-button size="mini" type="info" @click="toReturn()">
                            <i class="iconfont icon-quxiao"></i>取消</el-button>
                    </router-link>
                    <router-link :to="zpurl">
                        <el-button size="mini" type="primary" @click="submitForm('formData')">
                            <i class="iconfont icon-save"></i>保存</el-button>
                    </router-link>
                    <el-button size="mini" type="primary" @click="goxmqj()" >
                        <i class="iconfont icon-xiangmuquanjing_"></i>项目全景
                    </el-button>
                </el-row>
            </el-col>
        </el-row>

        <!--费用分类弹窗-->
        <el-dialog :visible.sync="dialogVisible" width="25%" :center="true">
            <el-row>
                <el-col>
                    <el-card class="box-card">
                        <div slot="header" class="clearfix"><span>费用项</span></div>
                        <div class="text item">
                            <el-checkbox-group v-model="checkList">
                                <el-row v-model="options" v-for="item in options" :key="item.index">
                                    <el-checkbox :label="item.LABEL">{{item.LABEL}}</el-checkbox>
                                </el-row>
                            </el-checkbox-group>
                        </div>
                    </el-card>
                </el-col>
                <el-col :span="24" style="text-align: center;margin-top: 25px">
                    <el-button size="mini" type="info" @click="dialogVisible = false">
                        <i class="iconfont icon-quxiao"></i>取消</el-button>
                    <el-button size="mini" type="primary" @click="Selected">
                        <i class="iconfont icon-queren"></i>确定</el-button>
                </el-col>
            </el-row>
        </el-dialog>

        <!--工商表勾选费用弹窗-->
        <el-dialog :visible.sync="gsbdialogVisible" width="60%" :center="true">
            <JFGSB ref="jfgsb" :zbkjList="this.zbkjList" @closeGs_vue="closeGs_vue"></JFGSB>
        </el-dialog>

        <!--户表勾选费用弹窗-->
        <el-dialog :visible.sync="hbdialogVisible" width="60%" :center="true">
            <JFHB ref="jfhb" :zbkjList="this.zbkjList" @openLd_vue="openLd_vue" @closeHb_vue="closeHb_vue"></JFHB>
        </el-dialog>

        <!--楼栋勾选列表弹窗-->
        <el-dialog :visible.sync="lddialogVisible" width="60%" :center="true">
            <JFLD ref="jfld" @openLdsb_vue="openLdsb_vue" @closeLd_vue="closeLd_vue"></JFLD>
        </el-dialog>

        <!--楼栋水表勾选列表弹窗-->
        <el-dialog :visible.sync="ldsbdialogVisible" width="60%" :center="true">
            <JFLDSB ref="jfldsb" :ldid="this.ldid" @closeLdsb_vue="closeLdsb_vue"></JFLDSB>
        </el-dialog>
    </section>
</template>

<script>
    import { getSkzh,addJftzd,getGcflm,querylxByname,getjbxx } from '../../api/htsf';
    import JEJS from '../../components/Jejs';
    import JFGSB from '../htsf/component/Jfgsb';
    import JFHB from '../htsf/component/Jfhb';
    import JFLD from './component/Jfld';
    import JFLDSB from './component/Jfldsb';
    import util from '../../common/js/util';
    export default {
        name: "addJftzd",
        components: {JEJS,JFGSB,JFHB,JFLD,JFLDSB},
        data() {
            return {
                zpurl:'',
                formData: {
                    domains: [],//{ value: '',label:''}
                },
                filters: {
                    xmid:this.$route.query.xmid,//项目ID
                    azlx:this.$route.query.azlx,//安装类型
                    sqlx:this.$route.query.sqlx,//申请类型
                    xmbh:this.$route.query.xmbh,//项目编号
                    xmmc:'',//项目名称
                    jsdw:'',//建设单位
                    lxr:'',//联系人
                    lxrsjh:'',//联系人手机号
                    tzbh:'',
                    jfdw:this.$route.query.dwmc,
                    skzh:'',
                    xmqk:'',
                    bz:'',
                },
                checkList: [],//checkbox select
                dialogVisible : false,

                gcfValue: [],
                options: [],
                zje:'',

                /*工商表勾选*/
                gsbdialogVisible:false,
                zbkjList:[],
                /*户表勾选*/
                hbdialogVisible:false,
                lddialogVisible:false,
                ldid:'',
                ldsbdialogVisible:false,
                jtgsbc:'0',
                jtjmbc:'0',
            }
        },
        mounted() {
            this.findGcflm();
            this.findParam();
            this.findSkzh();
            this.getjbxx();
        },
        methods: {
            //跳转至项目全景
            goxmqj(){
                this.$router.push({path:'/Xmlct', query:{ xmid:this.filters.xmid  }})
            },
            //获取项目基本信息
            getjbxx(){
                let param={
                    xmid:this.filters.xmid
                };
                getjbxx(param).then(res=>{
                    this.filters.xmbh = res.jbxxlist[0].xmbh;
                    this.filters.xmmc = res.jbxxlist[0].xmmc;
                    this.filters.jsdw = res.jbxxlist[0].dwmc;
                    this.filters.lxr = res.jbxxlist[0].lxr;
                    this.filters.lxrsjh = res.jbxxlist[0].lxrsjh;

                });


            },
            //打开工商表勾选弹窗
            openGs_vue(){
                this.gsbdialogVisible = true;
            },
            closeGs_vue(res){
                this.jtgsbc = res.split(",")[1];
                this.gsbdialogVisible = false;
                let fylist = this.formData.domains;
                fylist.forEach((fyrow,index)=>{
                    if(fyrow.bh == '06'){
                        fyrow.value = res.split(",")[0];
                        this.getZje();
                    }
                });
            },
            //打开户表勾选弹窗
            openHb_vue(){
                this.hbdialogVisible = true;
            },
            closeHb_vue(res){
                this.jtjmbc = res.split(",")[1];
                this.hbdialogVisible = false;
                let fylist = this.formData.domains;
                fylist.forEach((fyrow,index)=>{
                    if(fyrow.bh == '05'){
                        fyrow.value = res.split(",")[0];
                        this.getZje();
                    }
                });
            },
            //打开楼栋弹窗
            openLd_vue(){
                this.lddialogVisible = true;
            },
            closeLd_vue(){
                this.lddialogVisible = false;
                setTimeout(() => {
                    this.$refs.jfhb.queryHb_vue();
                },0);
            },
            //打开楼栋勾选水表弹窗
            openLdsb_vue(ldid){
                this.ldid = ldid;
                this.ldsbdialogVisible = true;
                setTimeout(() => {
                    this.$refs.jfldsb.getLdsb_vue(ldid);
                },0);
            },
            closeLdsb_vue(){
                this.ldsbdialogVisible = false;
                setTimeout(() => {
                    this.$refs.jfld.getLd_vue();
                },0);
            },
            //打开选择预算项弹窗
            openYsxx() {
                this.dialogVisible = true;
            },
            Selected() {
                let list = this.checkList;
                let flist = this.formData.domains;
                if(list.length == 0){
                    for(let i=0;i<3;i++){
                        for(let j=0;j<flist.length;j++){
                            this.formData.domains.splice(j,1);
                        }
                    }
                    this.dialogVisible = false;
                    this.getZje();
                }else{
                    let lx = "(";
                    this.zje = "";
                    list.forEach((row,index)=>{
                        if(util.formatDate.arrayindex(this.formData.domains,row) == -1){
                            lx += "'" + row + "',";
                        }
                    });
                    for(let i = 0;i<10;i++){
                        for(let j=0;j<flist.length;j++){
                            if(util.formatDate.arrayindex1(list,flist[j].label) == -1){
                                this.formData.domains.splice(j,1);
                            }
                        }
                    }
                    lx = lx.substring(0,lx.length-1);
                    lx += ")";
                    if(lx == ')'){
                        this.dialogVisible = false;
                        this.getZje();
                    }else{
                        let param = {lx:lx,xmid:this.filters.xmid};
                        querylxByname(param).then(res=>{
                            let lxlist = res.data.list;
                            lxlist.forEach((lxrow,index)=>{
                                this.formData.domains.push({
                                    value: '',
                                    label:lxrow.MC,
                                    tag:lxrow.TAG,
                                    bh:lxrow.BH,
                                });
                            });
                            this.getZje();
                            this.dialogVisible = false;
                        });
                    }
                }
            },
            //获取从上个页面传来的参数
            findParam(){
                let date = this.publicUtil.getNowDay();
                if(this.filters.sqlx == '10' || this.filters.sqlx == '30'){//新建
                    this.filters.bz = '用户交费到帐后，请携带以下资料：\r\n' +
                        '1.转帐凭证（进帐单、现金缴款单、网银电子回单加盖财务章）原件，收费通知单原件。\r\n' +
                        '2.加盖公章的企业开票信息，内容包括付款方名称、纳税人识别号、地址和电话、开户行和账号。\r\n' +
                        '3.税务登记证或者三证合一的营业执照复印件。\r\n' +
                        '4.付款方如需开具增值税专用发票须提供单位一般纳税人证明材料复印件。\n' +
                        '5.付款方如为个人，提供身份证复印件开具增值税普通发票。\r\n';
                }else if(this.filters.sqlx == '40'){
                    this.filters.bz = '请用户缴费后带银行进账单原件到供水集团业务大厅开据发票。';
                }else if(this.filters.sqlx == '20'){//工商报装
                    let format = this.publicUtil.computeYmd(date,3);
                    this.filters.bz = '根据税务部门关于发票开具单位名称需与申报单位(合同签订单位)、转账单位名称相一致的管理要求，现对用户申报及发票开具事项告知如下：\r\n' +
                        '1.由建设单位申报并付款的，可以开票；申报单位与转账单位不一致的，除下列两种情况外，均不符合开票条件：\r\n' +
                        '（1）申报单位和转账单位为母子公司关系，并要求开具转账单位发票的，申报单位需出具母子公司法律关系证明，并注明开具发票给子公司，双方加盖公章后方可开票。\r\n' +
                        '（2）申报单位为财政支付单位，工程款由施工单位转账，并要求开具施工单位发票的，申报单位需出具说明并加盖双方公章方可开票。\r\n' +
                        '2.客户转账交费后，持转帐凭证原件（进帐单、现金交款单、网银电子回单加盖财务章）、收费通知单原件到财务开具发票：\r\n' +
                        '（1）一般纳税人单位开具增值税专票，需提供开票资料信息：付款方名称、组织机构代码或三证合一的统一社会信用代码、单位地址和电话、开户行和账号。\r\n' +
                        '（2）非一般纳税人单位开具增值税普票，需提供开票资料信息：付款方名称、组织机构代码或三证合一的统一社会信用代码。\r\n' +
                        '（3）个人客户开具增值税普票。\r\n' +
                        '温馨提示：\r\n' +
                        '为了及时保障您的接水需求，请您在'+format+'前完成交费开票，并同步提供道路破复（绿化移植）手续或证明材料，逾期未办理的，该项目接水申请单将自动作废，如用户需要接水，请重新填表申报，特此提示。';
                }
            },
            //获取收款账号
            findSkzh(){
                let params = "";
                getSkzh(params).then((res) => {
                    this.filters.skzh = res.data.row[0].NAME+" "+res.data.row[0].VALUE+" "+res.data.row[0].CODE;
                    this.filters.tzbh = res.data.tzbh;
                });
            },
            //获取工程费栏目下拉框
            findGcflm(){
                let params = "";
                getGcflm(params).then((res) => {
                    this.options = res.data.row;
                    this.zbkjList = res.data.zbkjList;
                });
            },
            //得到总金额
            getZje(res){
                let data = this.formData.domains;
                this.zje = "";
                data.forEach((item,index)=>{
                    if(item.label != '总工程费' && item.value != ''){
                        if(this.zje == ''){
                            this.zje = parseFloat(item.value).toFixed(2);
                        }else{
                            this.zje = (parseFloat(item.value)+parseFloat(this.zje)).toFixed(2);
                        }
                    }
                });
            },
            //金额添加到总金额
            addToZje(res){
                let data = this.formData.domains;
                data.forEach((item,index)=>{
                    if(item.label == '总工程费'){
                        item.value = this.zje;
                    }
                });
            },
            /*格式化表单数据*/
            formatData(data){   //动态表单生成的是一个对象数组，需要把对象数组转成一个对象
                let obj = {};
                data.forEach((item,index)=>{
                    obj['value'+(index+1)] = item.value;
                    obj['label'+(index+1)] = item.label;
                });
                return obj
            },
            /*提交表单*/
            submitForm() {
                let list = this.formData.domains;
                let params = this.filters;
                let i = 0;
                let j = 0;
                list.forEach((item,index)=>{
                    params['value'+(index+1)] = item.value;
                    params['label'+(index+1)] = item.label;
                    i++;
                    if(item.value == ''){
                        j++;
                    }
                });
                params['fyfls'] = i;//费用分类数
                params['zje'] = this.zje;
                params['sfkpgsbid'] = sessionStorage.getItem("sfkpgsbid");
                params['sfkpjmbid'] = sessionStorage.getItem("sfkpjmbid");
                params['jtjmbc'] = this.jtjmbc;
                params['jtgsbc'] = this.jtgsbc;
                if(j==0){
                    this.zpurl = '/Jftzd?xmid='+this.filters.xmid+"&dwmc="+this.filters.jfdw+
                         "&azlx="+this.filters.azlx+"&sqlx="+this.filters.sqlx+"&xmbh="+this.filters.xmbh+
                         "&lxr="+this.filters.lxr+"&lxrsjh="+this.filters.lxrsjh;
                    this.$routerTab.close();
                    addJftzd(params).then((res) => {
                        if(res.data == true){
                            this.$message({
                                message: '提交成功',
                                type: 'success'
                            });
                            this.$routerTab.refresh('/Jftzd?xmid='+this.filters.xmid+'&dwmc='+this.filters.jfdw+'&azlx='+
                                this.filters.azlx+'&sqlx='+this.filters.sqlx+'&xmbh='+this.filters.xmbh+
                                '&lxr='+this.filters.lxr+'&lxrsjh='+this.filters.lxrsjh);
                        }else{
                            this.$message.error('提交失败！');
                        }
                    });
                }else{
                    this.zpurl = "/AddJftzd?xmid="+this.filters.xmid+"&dwmc="+this.filters.jfdw+
                        "&azlx="+this.filters.azlx+"&sqlx="+this.filters.sqlx+"&xmbh="+this.filters.xmbh;
                    this.$message({
                        message: '有分项金额未填写',
                        type: 'warning'
                    });
                }
            },
            /*重置表单*/
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            toReturn(){
                this.$routerTab.close();
            },
            rowClass:function (row,index) {
                return {"height":"6.0vh"}
            }
        },
        filters:{//局部过滤器
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
        }
    }
</script>

<style scoped>

</style>