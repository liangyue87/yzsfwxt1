<template>
    <section>
        <div class="grid-content bg-purple-light" style="display: flex;align-items: center;width: 100%;height: 40px;background:#f5f7fa">
            <span style="color: #606266;;margin-left:10px">管网管理</span>
            <el-button v-if="this.qb != '0'&&isxmtc!='2'" size="mini" type="primary" @click="TYHandleAdd_vue()" style="margin-left: 10px"><i class="iconfont icon-xinzeng"></i>新增范围</el-button>
        </div>
        <el-table :data="GWData" style="width: 100%" :stripe="true">
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
            </el-table-column><!--v-if="this.qb != '0'"-->
            <el-table-column  label="操作" align="center"  >
                <template slot-scope="scope">
                    <label title="编辑"><i @click="TYHandleEdit_vue(scope.$index, scope.row)" class="iconfont icon-ai-edit" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                    <label title="删除" v-if="isxmtc!='2'"><i @click="TYHandleDelete_vue(scope.$index, scope.row)" class="iconfont delete" style="font-size:18px;cursor:pointer;padding: 2px;color: #F56C6C"></i></label>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="管网信息维护"   :visible.sync="tyDialogFormVisible" :append-to-body="true" :close-on-click-modal="false" top="2vh">
            <el-form :model="tyForm"   ref="tyForm">
                <el-input style="display: none" v-model="tyForm.id"  ></el-input>
                <el-form-item>
                    <el-col :span="4" style="text-align: right">
                        <span>管道编号&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="8">
                        <el-input :disabled="true" v-model="tyForm.gdbh" v-if="tyForm.gdbh!=null&&tyForm.gdbh!=''"  size="small"></el-input>
                        <el-input :disabled="true" v-else value="保存后自动生成" size="small" ></el-input>
                    </el-col>

                    <el-col :span="12">
                        <el-form-item prop="gwzt" style="text-align: center">
                            <el-radio-group v-model="tyForm.gwzt">
                                <el-radio v-for="item in gwztOption" :label="item.VALUE">{{item.LABEL}}</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-col :span="4" style="text-align: right">
                        <span>管道名称&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="8">
                        <el-input v-model="tyForm.gdmc" size="small"></el-input>
                    </el-col>
                    <el-col :span="4"style="text-align: right">
                        <span>管道口径&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item prop="gdkj">
                            <el-select v-model="tyForm.gdkj" style="width: 100%" size="small">
                                <el-option v-for="item in gdkjOption"
                                           :key="item.BH"
                                           :label="item.MC"
                                           :value="item.BH"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-col :span="4" style="text-align: right">
                        <span>管道长度&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="7">
                        <el-form-item prop="gdcd" :rules="rules.gdcd">
                            <el-input v-model="tyForm.gdcd" size="small" autocomplete="off"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="1">
                        <label>&nbsp;米</label>
                    </el-col>
                    <el-col :span="4"style="text-align: right">
                        <span>管道材质&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item prop="gdcz">
                            <el-select v-model="tyForm.gdcz" style="width: 100%" size="small">
                                <el-option v-for="item in gdczOption"
                                           :key="item.BH"
                                           :label="item.MC"
                                           :value="item.BH"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-form-item>

                <el-form-item>
                    <el-col :span="4" style="text-align: right">
                        <span>道路名称&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="8">
                        <el-input v-model="tyForm.dlmc" size="small"></el-input>
                    </el-col>
                    <el-col :span="4" style="text-align: right">
                        <span>路段&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="8">
                        <el-input v-model="tyForm.ldmc" size="small"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-col :span="4" style="text-align: right">
                        <span>消防栓数量&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item prop="xfssl" :rules="rules.xfssl">
                            <el-input v-model.number="tyForm.xfssl" size="small" autocomplete="off"></el-input></el-form-item>
                    </el-col>
                    <el-col :span="4" style="text-align: right">
                        <span>消防栓表数量&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item prop="xfsbsl" :rules="rules.xfsbsl">
                            <el-input v-model.number="tyForm.xfsbsl" size="small" autocomplete="off"></el-input></el-form-item>
                    </el-col>
                </el-form-item>

                <el-form-item  style="text-align: center" >
                    <el-col :span="16">
                        <el-checkbox label="是否需要物探" v-model="tyForm.sfxyft"></el-checkbox>
                        <el-checkbox label="是否需要地勘" v-model="tyForm.sfxydk" ></el-checkbox>
                        <el-checkbox label="是否需要测绘" v-model="tyForm.sfxych" ></el-checkbox>
                    </el-col>
                    <el-col :span="2" style="text-align: right">
                        <span>总费用&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="4">
                        <el-form-item  prop="zfy" :rules="rules.zfy">
                            <el-input v-model="tyForm.zfy" size="small" autocomplete="off"></el-input></el-form-item>
                    </el-col>
                    <el-col :span="2">
                        <span>元</span>
                    </el-col>
                </el-form-item>
                <el-form-item >
                    <el-col :span="4" style="text-align: right">
                        <span>备注&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="20">
                        <el-input type="textarea":rows="3"  v-model="tyForm.bz"></el-input>
                    </el-col>
                </el-form-item>
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">过路管信息</div>
                    </div>
                <el-row v-for="(item,index) in tyForm.glgModelList" :key="index" style="border-bottom: 1px solid #f0f0f0;padding: 10px;">
                    <el-input style="display: none" v-model="item.id"  ></el-input>
                    <el-input style="display: none" v-model="item.xmgdid"  ></el-input>
                    <el-form-item>
                        <el-col :span="4" style="text-align: right">
                            <span>过路管编号&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="8">
                            <el-input :disabled="true" v-model="item.glgbh" v-if="item.glgbh!=null&&item.glgbh!=''"  size="small"></el-input>
                            <el-input :disabled="true" v-else value="保存后自动生成" size="small" ></el-input>
                        </el-col>
                        <el-col :span="4" style="text-align: right">
                            <span>过路管名称&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="8">
                            <el-input v-model="item.glgmc" size="small"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="4" style="text-align: right">
                            <span>过路管口径&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="8">
                                <el-select v-model="item.glgkj" style="width: 100%" size="small">
                                    <el-option v-for="item in gdkjOption"
                                               :key="item.BH"
                                               :label="item.MC"
                                               :value="item.BH"></el-option>
                                </el-select>
                        </el-col>
                        <el-col :span="4" style="text-align: right">
                            <span>数量&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item :prop="'glgModelList.' + index +'.sl'" :rules="rules.sl">
                            <el-input v-model.number="item.sl" size="small" autocomplete="off"></el-input></el-form-item>
                        </el-col>
                    </el-form-item>
                    <el-form-item style="text-align: right" v-if="isxmtc!='2'">
                        <el-button type="danger" v-if="tyForm.glgModelList.length > 1" size="mini" @click="removeRow(index,item.id)">
                            <i class="iconfont delete_f" style="font-size: 16px"></i>删除
                        </el-button>
                        <el-button type="primary" size="mini" @click="addRow"><i class="iconfont icon-xinzeng"></i>新增</el-button>
                    </el-form-item>
                </el-row>
                </el-card>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button size="mini" type="info" @click="tyDialogFormVisible = false" v-if="isxmtc!='2'"><i class="iconfont icon-quxiao"></i>取 消</el-button>
                <el-button type="primary" size="mini" :loading="loading" @click="tyxxwh_vue('tyForm')" v-if="isxmtc!='2'"><i class="iconfont icon-queren"></i>确 定</el-button>
            </div>
        </el-dialog>
    </section>
</template>
<script>
    import {initGWTable,gwxxwh,initParams,GWHandleEdit,GWHandleDelete,GLGHandleDelete} from '../../api/sjst';
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
            var indexs;
            var checkNum = (rule, value, callback) => {
                //var reg =  /^[1-9]\d*$/;
                var reg = /^(?:0|[1-9]\d*)(?:\.\d*[1-9])?$/;
                if (!reg.test(value) && !value == '') {
                    callback(new Error('该字段必须为数字'));
                } else {
                    callback();
                }
            };
            var checkNumber = (rule, value, callback) => {
                if(value!==""){
                    if (!Number.isInteger(value)) {
                        callback(new Error('该字段必须为数字'));
                    } else {
                        callback();
                    }
                }else{
                    callback();
                }

            };

            return{

                rules:{
                    gdcd:[{ validator: checkNum, trigger: 'blur'}],
                    xfssl:[{ validator: checkNumber, trigger: 'blur'}],
                    xfsbsl:[{ validator: checkNumber, trigger: 'blur'}],
                    sl:[{ validator: checkNumber, trigger: 'blur'}],
                    zfy:[{ validator: checkNum, trigger: 'blur'}]
                },
                GWData:[],
                tyDialogFormVisible:false,
                gwztOption:[],
                gdkjOption:[],
                gdczOption:[],
                tyForm:{
                    id:"",
                    gdbh:"",
                    gwzt:"",
                    gdmc:"",
                    gdcd:"",
                    gdcz:"",
                    dlmc:"",
                    ldmc:"",
                    gdkj:"",
                    zfy:"",
                    xfssl:"",
                    xfsbsl:"",
                    sfxyft:false,
                    sfxydk:false,
                    sfxych:false,
                    bz:"",
                    cjr:"",
                    cjsj_vue:"",
                    glgModelList:[{
                        id:"",
                        xmgdid:"",
                        glgbh:"",
                        glgmc:"",
                        glgkj:"",
                        sl:""
                    }]
                },
                loading:false
            }
        },
        methods:{
            addRow() {
                this.tyForm.glgModelList.push({
                    id:"",
                    xmgdid:"",
                    glgbh:"",
                    ldcd:"",
                    glgkj:"",
                    sl:""
                });
            },
            // 删除属性列
            removeRow(index,id) {
                if(id!=null&&id!=""){
                    this.$confirm('确认删除该过路管吗?', '提示', {
                        type: 'warning'
                    }).then(() => {
                        let params={
                            id:id
                        }
                        GLGHandleDelete(params).then((res) => {
                            if(res.data){
                                this.tyForm.glgModelList.splice(index, 1);
                            }else{
                                this.$message.error('删除失败！');
                            }

                        })
                    })
                }else{
                    this.tyForm.glgModelList.splice(index, 1);
                }


            },

            initParams_vue(){
                let params={
                    type:'gwzt'
                }
                initParams(params).then((res)=>{
                    if(res!=null){
                        this.gwztOption = res.data.gwztList;
                        this.tyForm.gwzt = res.data.gwztList[0].VALUE;
                        this.gdkjOption = res.data.kjList;
                        this.gdczOption = res.data.gdczList;
                    }
                })
            },

            TYHandleAdd_vue(){
                this.initTYForm();
                this.tyDialogFormVisible=true;
            },
            initTYForm(){
                this.tyForm={
                        id:"",
                        gdbh:"",
                        gwzt:this.gwztOption[0].VALUE,
                        gdmc:"",
                        gdcd:"",
                        gdcz:"",
                        dlmc:"",
                        gdkj:"",
                        ldmc:"",
                        zfy:"",
                        xfssl:"",
                        xfsbsl:"",
                        sfxyft:false,
                        sfxydk:false,
                        sfxych:false,
                        bz:"",
                        cjr:"",
                        cjsj_vue:"",
                        glgModelList:[{
                        id:"",
                        xmgdid:"",
                        glgbh:"",
                        glgmc:"",
                        glgkj:"",
                        sl:""
                    }]
                }
            },
            initTYTable_vue(){
                let params={
                    xmid:this.xmid
                }
                initGWTable(params).then((res)=>{
                    if(res.data!=null){
                        this.GWData = res.data.row;
                    }
                })
            },
            tyxxwh_vue(formName){

                let param={
                    xmid:this.xmid,
                    zt:"1"
                }


                let params = Object.assign(param,this.tyForm);
                if(params.sfxych){
                    params.sfxych=1
                }else{
                    params.sfxych=0
                }
                if(params.sfxydk){
                    params.sfxydk=1
                }else{
                    params.sfxydk=0
                }
                if(params.sfxyft){
                    params.sfxyft=1
                }else{
                    params.sfxyft=0
                }
                for(let i = 0;i<params.glgModelList.length;i++){
                    if(params.glgModelList[i].sl==""){
                        params.glgModelList[i].sl=0;
                    }
                }
                if(params.gdcd==""){
                    params.gdcd = 0.0;
                }
                if(params.xfssl==""){
                    params.xfssl = 0;
                }
                if(params.xfsbsl==""){
                    params.xfsbsl = 0;
                }
                if(params.zfy==""){
                    params.zfy=0.0
                }
                params.glgModelList = JSON.stringify(params.glgModelList);
            this.loading=true;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        gwxxwh(params).then((res) => {
                            this.loading=false;
                            if(res.data){
                                this.$message.success('保存成功！');
                                this.tyDialogFormVisible=false;
                                 this.initTYTable_vue();
                            }else{
                                this.$message.error('保存失败！');
                            }
                        })
                    } else {
                        this.loading=false;
                        return false;
                    }
                });

            },
            TYHandleEdit_vue(index,row){
                this.initTYForm();
                this.tyDialogFormVisible=true;
                let params={
                    id:row.id
                }
                GWHandleEdit(params).then((res)=>{
                    if(res!=null){
                        this.tyForm.gwzt=res.data.gdList[0].gwzt;
                        this.tyForm.bh=res.data.gdList[0].bh;
                        this.tyForm.bz=res.data.gdList[0].bz;
                        this.tyForm.dlmc=res.data.gdList[0].dlmc;
                        this.tyForm.gdbh = res.data.gdList[0].gdbh;
                        this.tyForm.gdcd = res.data.gdList[0].gdcd;
                        this.tyForm.gdcz = res.data.gdList[0].gdcz;
                        this.tyForm.gdmc = res.data.gdList[0].gdmc;
                        this.tyForm.id = res.data.gdList[0].id;
                        this.tyForm.ldmc=res.data.gdList[0].ldmc;
                        this.tyForm.zfy=res.data.gdList[0].zfy;
                        this.tyForm.xfssl=res.data.gdList[0].xfssl;
                        this.tyForm.xfsbsl=res.data.gdList[0].xfsbsl;
                        this.tyForm.gdkj=res.data.gdList[0].gdkj;
                        this.tyForm.cjr=res.data.gdList[0].cjr;
                        this.tyForm.cjsj_vue=res.data.gdList[0].cjsj_vue;
                        if(res.data.gdList[0].sfxych==1||res.data.gdList[0].sfxych=="1"){
                            this.tyForm.sfxych=true;
                        }else{
                            this.tyForm.sfxych=false;
                        }
                        if(res.data.gdList[0].sfxydk==1||res.data.gdList[0].sfxydk=="1"){
                            this.tyForm.sfxydk=true;
                        }else{
                            this.tyForm.sfxydk=false;
                        }
                        if(res.data.gdList[0].sfxyft==1||res.data.gdList[0].sfxyft=="1"){
                            this.tyForm.sfxyft=true;
                        }else{
                            this.tyForm.sfxyft=false;
                        }
                        this.tyForm.glgModelList = res.data.glgList;
                    }
                })
            },
            TYHandleDelete_vue(index,row){
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    let params={
                        id:row.id,
                        zt:"0"
                    }
                    GWHandleDelete(params).then((res) => {
                        this.initTYTable_vue();
                    })
                })

            },
        },
        mounted(){
            this.initParams_vue();
            this.initTYTable_vue();
        }
    }
</script>