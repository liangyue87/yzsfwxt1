<template>
    <section>
        <div class="grid-content bg-purple-light" style="display: flex;align-items: center;height: 40px;background:#f5f7fa">
            <span style="color: #606266;;margin-left:10px">楼栋范围管理</span>
            <el-button v-if="this.qb != '0'&&isxmtc!='2'" size="mini" type="primary" @click="LDHandleAdd_vue()" style="margin-left: 10px"><i class="iconfont icon-xinzeng"></i>新增范围</el-button>
            <el-button size="mini" type="primary" v-if="buttonShow" @click="xjxqld_vue()" style="margin-left: 10px"><i class="iconfont icon-yulan"></i>新建小区楼栋查看</el-button>
        </div>
        <el-table :data="LDData"  :stripe="true" :summary-method="getSummaries" show-summary>
            <el-table-column
                    prop="tymc"
                    label="庭院名称"
                    align="center"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="ldbh"
                    label="楼栋范围"
                    align="center"
                    width="80">
            </el-table-column>
            <el-table-column
                    prop="ghhs"
                    label="规划户数"
                    align="center"
                    width="80">
            </el-table-column>
            <el-table-column
                    prop="kjmc"
                    label="水表口径"
                    align="center"
                    width="80">
            </el-table-column>
            <el-table-column
                    prop="lfsx"
                    label="楼房属性"
                    align="center"
                    width="100"
                    :formatter="formatter">
            </el-table-column>
            <el-table-column
                    prop="fqsl"
                    label="分区数量"
                    align="center"
                    width="80">
            </el-table-column>
            <el-table-column
                    prop="jyfq"
                    label="加压分区"
                    align="center">
            </el-table-column><!--v-if="this.qb != '0'"-->
            <el-table-column  label="操作" align="center" width="120" >
                <template slot-scope="scope">
                    <label title="编辑"><i @click="LDHandleEdit_vue(scope.$index, scope.row)" class="iconfont icon-ai-edit" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                    <label title="删除" v-if="isxmtc!='2'"><i @click="LDHandleDelete_vue(scope.$index, scope.row)" class="iconfont delete" style="font-size:18px;cursor:pointer;padding: 2px;color: #F56C6C"></i></label>
                    <label title="分区"><i @click="LDHandleFq_vue(scope.$index, scope.row)" class="iconfont icon-fenqu" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="楼栋信息维护" :visible.sync="ldDialogFormVisible" width="50%" :close-on-click-modal="false">
            <el-form :model="ldForm" :inline="true" class="demo-form-inline" :rules="rules" ref="ldForm">
                <el-form-item label="庭院名称" :label-position="labelPosition" prop="tyid" :label-width="formLabelWidth" style="margin-bottom: 20px" size="samll">
                    <el-input  v-model="ldForm.id" style="width: 200px;display: none" ></el-input>
                    <el-select v-model="ldForm.tyid" placeholder="请选择" style="width: 150px;" size="samll">
                        <el-option
                                v-for="item in tyOptions"
                                :key="item.codevalue"
                                :label="item.codename"
                                :value="item.codevalue">
                        </el-option>
                    </el-select>
                    <span style="color: #66b1ff;">注：若没有选项，请在项目庭院范围中增加</span>
                </el-form-item>
                <el-form-item label="楼房属性" :label-position="labelPosition" prop="lfsx" :label-width="formLabelWidth" style="margin-bottom: 20px" size="samll">
                    <el-select v-model="ldForm.lfsx" placeholder="请选择" style="width: 150px;" size="samll">
                        <el-option
                                v-for="item in lfsxOptions"
                                :key="item.codevalue"
                                :label="item.codename"
                                :value="item.codevalue">
                        </el-option>
                    </el-select>
                    <span style="color: #66b1ff;">注：平房请选多层</span>
                </el-form-item>
                <el-row>
                    <el-form-item label="楼栋范围" :label-position="labelPosition" prop="ldbh" :label-width="formLabelWidth" style="margin-bottom: 20px" size="small">
                        <el-input  v-model="ldForm.ldbh" style="width: 150px;"  size="samll"></el-input>
                    </el-form-item>
                </el-row>
                <el-row>
                    <el-form-item label="口径" :label-position="labelPosition" :label-width="formLabelWidth" prop="kjbh" style="margin-bottom: 15px" size="small">
                        <el-select v-model="ldForm.kjbh" style="width: 150px;" size="samll">
                            <el-option
                                    :key="item.codevalue"
                                    v-for="item in kjOptions"
                                    :label="item.codename"
                                    :value="item.codevalue">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-row>

                <el-form-item label="规划户数" :label-position="labelPosition" prop="ghhs" :label-width="formLabelWidth" style="margin-bottom: 20px" size="samll">
                    <el-input v-model.number="ldForm.ghhs" style="width: 150px;" size="samll"></el-input>
                    <span style="color: #66b1ff;">注：仅填数字，即将报装表数，设计预算用</span>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button size="mini" type="info" @click="ldDialogFormVisible = false" v-if="isxmtc!='2'"><i class="iconfont icon-quxiao"></i>取 消</el-button>
                <el-button size="mini" type="primary" @click="ldxxwh_vue('ldForm')" :loading="loading" v-if="isxmtc!='2'"><i class="iconfont icon-queren"></i>确 定</el-button>
            </div>
        </el-dialog>
        <el-dialog  title="单体楼号，分区，分层信息" :visible.sync="fqDialogFormVisible" width="30%">
            <el-form :model="fqForm" :inline="true" class="demo-form-inline"  ref="fqForm">
                <el-input  v-model="fqForm.ldid" style="width: 200px;display: none"></el-input>
                <el-form-item label="楼栋范围" :label-position="labelPosition" prop="ldh" :label-width="formLabelWidth" >
                    <el-input :disabled="true" v-model="fqForm.ldh" style="width: 200px;" size="small"></el-input>
                </el-form-item>
                <el-form-item label="分区数量" :label-position="labelPosition" prop="fqsl" :label-width="formLabelWidth">
                    <el-select v-model="fqForm.fqsl" placeholder="请选择" style="width: 200px;"  size="small">
                        <el-option
                                v-for="item in fqslOptions"
                                :key="item.codevalue"
                                :label="item.codename"
                                :value="item.codevalue">
                        </el-option>
                    </el-select>
                    <el-button
                            size="mini"
                            type="primary"
                            @click="produce()" v-if="isxmtc!='2'"><i class="iconfont icon-shengcheng" style="font-size: 12px;"></i>生成</el-button>
                </el-form-item>
            </el-form>
            <el-table :data="FQData" style="width: 100%" border @cell-click="cellEdit">
                <el-table-column
                        prop="fqmc"
                        label="分区名称"
                        align="center"
                        width="150"
                        >
                </el-table-column>
                <el-table-column
                        prop="qslc"
                        label="起始楼层"
                        align="center"
                        >
                    <template slot-scope="scope">
                            <span v-if="scope.row.qslcEdit">
                                <el-input size="mini"  v-model.number="scope.row.qslc" clearable>
                                </el-input>
                            </span>
                        <span v-else>{{scope.row.qslc}}</span>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="zdlc"
                        label="终点楼层"
                        align="center"
                        >
                    <template slot-scope="scope">
                            <span v-if="scope.row.zdlcEdit">
                                <el-input size="mini" v-model.number="scope.row.zdlc" clearable>
                                </el-input>
                            </span>
                        <span v-else>{{scope.row.zdlc}}</span>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="sybz"
                        label="试压标准"
                        align="center"
                        >
                    <template slot-scope="scope">
                            <span v-if="scope.row.sybzEdit">
                                <el-input  size="mini"  v-model="scope.row.sybz" clearable>
                                </el-input>
                            </span>
                        <span v-else>{{scope.row.sybz}}</span>
                    </template>
                </el-table-column>
            </el-table>
            <div slot="footer" class="dialog-footer">
                <el-button size="mini" @click="fqDialogFormVisible = false" type="info" v-if="isxmtc!='2'"><i class="iconfont icon-quxiao"></i>取 消</el-button>
                <el-button size="mini" type="primary" @click="fqxxwh_vue()" v-if="isxmtc!='2'"><i class="iconfont icon-queren"></i>确 定</el-button>
            </div>
        </el-dialog>
        <el-dialog  title="新建小区楼栋信息" :visible.sync="xjxqDialogFormVisible" width="50%">
            <el-table :data="XJXQLDData" style="width: 100%" border >
                <el-table-column
                        prop="ldh"
                        label="楼栋范围"
                        align="center">
                </el-table-column>
                <el-table-column
                        prop="dys"
                        label="单元数"
                        align="center">
                </el-table-column>
                <el-table-column
                        prop="lc"
                        label="楼层"
                        align="center">
                </el-table-column>
                <el-table-column
                        prop="mchs"
                        label="每层户数"
                        align="center">
                </el-table-column>
                <el-table-column
                        prop="zhs"
                        label="总户数"
                        align="center">
                </el-table-column>
                <el-table-column
                        prop="bz"
                        label="备注"
                        align="center">
                </el-table-column>
            </el-table>
        </el-dialog>
    </section>
</template>
<script>
    import {initLDTable,initLYOption,ldxxwh,getFQData,fqxxwh,xmsqlx,xjxqld,getKjData} from '../../api/sjst';
    export default {
        props:{
            xmid:{
                type: String,
                required: true
            },
            qb:{
                required: true
            },
            isxmtc:{
                type: String,
                required: true
            }
        },
        data(){
            let validcodeName=(rule,value,callback)=>{
                let reg=/^[A-Za-z0-9]+$/
                if(!reg.test(value)){callback(new Error('仅填数字、字母'))
                }else{
                    callback()
                }
            };
            return{
                rules:{
                    lfsx:[{ required: true, message: '该信息不能为空', trigger: 'change'}],
                    tyid:[{ required: true, message: '该信息不能为空', trigger: 'change'}],
                    ghhs:[
                        { required: true, message: '该信息不能为空', trigger: 'blur'},
                        { type: 'number', message: '该信息必须为数字值', trigger: 'blur'}
                    ],
                    ldbh:[{required:true,message:'该信息不能为空',trigger:'blur'}]

                },
                fqmcData:["市政直供","加压一区","加压二区","加压三区","加压四区","加压五区","加压六区","加压七区"],
                LDData:[],
                FQData:[],
                XJXQLDData:[],
                kjOptions:[],
                tyOptions:[],
                lfsxOptions:[{
                    codevalue: '0',
                    codename: '多层'
                },{
                    codevalue:'1',
                    codename:'高层'
                }],
                fqslOptions:[{
                    codevalue: 1,
                    codename: 1
                },{
                    codevalue: 2,
                    codename: 2
                },{
                    codevalue: 3,
                    codename: 3
                },{
                    codevalue: 4,
                    codename: 4
                },{
                    codevalue: 5,
                    codename: 5
                },{
                    codevalue: 6,
                    codename: 6
                },{
                    codevalue: 7,
                    codename: 7
                },{
                    codevalue: 8,
                    codename: 8
                }],
                fqDialogFormVisible:false,
                ldDialogFormVisible:false,
                xjxqDialogFormVisible:false,
                buttonShow:false,
                loading:false,
                ldForm:{
                    kjbh:"",
                    id:"",
                    ldbh:"",
                    tyid:"",
                    lfsx:"",
                    ghhs:""
                },
                fqForm:{
                    ldid:"",
                    ldh:"",
                    fqsl:1
                },
                formLabelWidth:"100px",
                labelPosition: 'right',
            }
        },
        methods:{
            getSummaries(param) {
                const { columns, data } = param;
                const sums = [];
                columns.forEach((column, index) => {
                    if (index === 0) {
                        sums[index] = '总计';
                        return;
                    }
                    const values = data.map(item => Number(item[column.property]));
                    if (!values.every(value => isNaN(value))) {
                        if(index===2){
                            sums[index] = values.reduce((prev, curr) => {
                                const value = Number(curr);
                                if (!isNaN(value)) {
                                    return prev + curr;
                                } else {
                                    return prev;
                                }
                            }, 0);
                            sums[index] += 0;
                        }else{
                            sums[index] = '-';
                        }
                    } else {
                        sums[index] = '-';
                    }
                });

                return sums;
            },
            /*初始化form*/
            initLDForm(){
                this.ldForm.id="";
                this.ldForm.ldbh="";
                this.ldForm.tyid="";
                this.ldForm.lfsx="";
                this.ldForm.ghhs=""
            },
            /*初始化庭院选择器*/
            initLYOption_vue(){
                let params = {
                    xmid:this.xmid
                }
                initLYOption(params).then((res)=>{
                    if(res.data!=null){
                        this.tyOptions=res.data.row
                    }
                })
            },
            formatter(row, column) {
                if(row.lfsx==0||row.lfsx=="0"){
                    return "多层";
                }else{
                    return "高层";
                }
            },
            /*初始化楼栋table*/
            initLDTable_vue(){
                let params={
                    xmid:this.xmid
                }
                initLDTable(params).then((res)=>{
                    if(res.data!=null){
                        this.LDData = res.data.row;
                    }
                })
            },
            /*新增楼栋*/
            LDHandleAdd_vue(){
                this.initLDForm();
                this.ldDialogFormVisible=true;
            },
            initMun(value){
                if(value==null||value==""){
                    value=0
                }
                return value;
            },
            /*楼栋信息维护*/
            ldxxwh_vue(formName){
                this.loading=true;
                let params={
                    id:this.ldForm.id,
                    ldbh:this.ldForm.ldbh,
                    tyid:this.ldForm.tyid,
                    lfsx:this.ldForm.lfsx,
                    ghhs:this.initMun(this.ldForm.ghhs),
                    ldsbkj:this.ldForm.kjbh,
                    zt:"1",
                    xmid:this.xmid
                };
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        ldxxwh(params).then((res)=>{
                            this.loading=false;
                            if(res.data!=null){
                                if(res.data=="-1"){
                                    this.$message({
                                        message: '该楼栋号已使用，请重新填写！',
                                        type: 'warning'
                                    });
                                }else if(res.data=="1"){
                                    this.$message.success('保存成功！');
                                    this.ldDialogFormVisible=false;
                                    this.initLDTable_vue();
                                }else{
                                    this.$message.error('保存失败！');
                                }
                            }

                        })
                    } else {
                        this.loading=false;
                        return false;
                    }
                });

            },
            //获取水表口径
            getKjData_vue(){
                let params={};
                getKjData(params).then((res)=>{
                    if(res.data!=null){
                        this.kjOptions = res.data.row;
                    }
                })
            },
            /*编辑楼栋*/
            LDHandleEdit_vue(index,row){
                this.initLDForm();
                this.ldDialogFormVisible=true;
                this.ldForm.id=row.id;
                this.ldForm.ldbh=row.ldbh;
                this.ldForm.tyid=row.tyid;
                this.ldForm.lfsx=row.lfsx;
                this.ldForm.kjbh=row.ldsbkj;
                this.ldForm.ghhs = Number(this.initMun(row.ghhs))
            },
           /* 删除楼栋*/
            LDHandleDelete_vue(index,row){
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    let params={
                        id:row.id,
                        xmid:this.xmid,
                        ldbh:row.ldbh,
                        tyid:row.tyid,
                        kjbh:row.ldsbkj,
                        lfsx:row.lfsx,
                        ghhs:Number(this.initMun(row.ghhs)),
                        zt:"0"
                    }
                    ldxxwh(params).then((res) => {
                        this.initLDTable_vue();
                    })
                })
            },
            //获取分区信息
            getFQData_vue(id){
                let params = {
                    "xmid":this.xmid,
                    "id":id
                }
                getFQData(params).then((res)=>{
                    if(res.data!=null){
                        this.FQData = res.data.row;
                    }
                })
            },
            /*单体楼栋，分区，分层信息*/
            LDHandleFq_vue(index,row){
                this.fqDialogFormVisible=true;
                this.fqForm.ldh = row.ldbh;
                this.fqForm.fqsl = row.fqsl;
                this.fqForm.ldid = row.id;
                this.getFQData_vue(row.id);
            },
            // 编辑单元格
            cellEdit(row, column, cell, event){
                switch (column.property) {
                    case "qslc":
                        row.qslcEdit = true;
                        break;
                    case "zdlc":
                        row.zdlcEdit = true;
                        break;
                    case "sybz":
                        row.sybzEdit = true;
                        break;
                    default:
                        break;
                }
            },
        //    生成分区
            produce(){
                let fqsl = this.fqForm.fqsl;
                let fqDataLength = this.FQData.length;
                if(fqsl>fqDataLength){
                    for(let i = fqDataLength;i<fqsl;i++){
                        this.FQData.push({"fqmc":this.fqmcData[i],"fqbh":i+1,"qslc":0,"zdlc":0,"sybz":0,"qslcEdit":false,"zdlcEdit":false,"sybzEdit":false,"xmid":this.xmid,"ldid":this.fqForm.ldid})
                    }
                }else if(fqsl<fqDataLength){
                    this.$confirm('确认删除最后'+(fqDataLength-fqsl)+'行记录吗?', '提示', {
                        type: 'warning'
                    }).then(() => {
                        for(let i = 0;i<fqDataLength-fqsl;i++){
                            this.FQData.pop();
                        }
                        this.fqxxwh_vue();
                    })
                }
            },
            //分区信息维护
            fqxxwh_vue(){
                for(let i = 0;i<this.FQData.length;i++){
                    if (!Number.isInteger(this.FQData[i].qslc)) {
                        this.$message.error('起始楼层请填写数字！');
                        return;
                    }
                    if (!Number.isInteger(this.FQData[i].zdlc)) {
                        this.$message.error('终点楼层请填写数字！');
                        return;
                    }
                    var reg = /^(?:0|[1-9]\d*)(?:\.\d*[1-9])?$/;
                    if (!reg.test(this.FQData[i].sybz) && this.FQData[i].sybz != '') {
                        this.$message.error('试压标准请填写数字！');
                        return;
                    }
                    if(this.FQData[i].qslc>this.FQData[i].zdlc){
                        this.$message.error('起始楼层不得大于终点楼层！');
                        return;
                    }
                    if(i>0&&this.FQData[i-1].zdlc>this.FQData[i].qslc){
                        this.$message.error('起始楼层不得小于上一分区终点楼层！');
                        return;
                    }
                    if(this.FQData[i].sybz===""){
                        this.FQData[i].sybz=0;
                    }
                }
                if(this.FQData.length>0){
                    let params = {
                        data:JSON.stringify(this.FQData)
                    }
                    fqxxwh(params).then((res)=>{
                        if(res){
                            this.$message.success('保存成功！');
                            this.fqDialogFormVisible=false;
                            this.initLDTable_vue();
                        }

                    })
                }
            },
            xmsqlx_vue(){
              let params = {
                  xmid:this.xmid
              }
              xmsqlx(params).then((res)=>{
                if(res.data!=null){
                    this.buttonShow=res.data
                }
              })
            },
            //新建小区楼栋
            xjxqld_vue(){
                let params = {
                    xmid:this.xmid
                }
                xjxqld(params).then((res)=>{
                    if(res.data!=null){
                        this.XJXQLDData = res.data.row;
                        this.xjxqDialogFormVisible=true
                    }
                })
            }
        },
        mounted(){
            this.initLDTable_vue();
            this.initLYOption_vue();
            this.xmsqlx_vue();
            this.getKjData_vue();
        }
    }
</script>
