<template>
    <section>
        <div class="grid-content bg-purple-light" style="display: flex;align-items: center;width: 100%;height: 40px;background:#f5f7fa">
            <span style="color: #606266;;margin-left:10px">水箱管理</span>
            <el-button v-if="this.qb != '0'&&isxmtc!='2'" size="mini" type="primary" @click="SXHandleAdd_vue()" style="margin-left: 10px"><i class="iconfont icon-xinzeng"></i>新增范围</el-button>
        </div>
        <el-table :data="SXData" style="width: 100%" :stripe="true">
            <el-table-column
                    prop="sxbh"
                    label="水箱编号"
                    align="center"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="sxmc"
                    label="水箱名称"
                    align="center" >
            </el-table-column>
            <el-table-column
                    prop="cd"
                    label="长度(m)"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="kd"
                    label="宽度(m)"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="gd"
                    label="高度(m)"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="rj"
                    label="容积(m³)"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="bfmc"
                    label="泵房"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="bz"
                    label="备注"
                    align="center">
            </el-table-column><!--v-if="this.qb != '0'"-->
            <el-table-column prop="cz" label="操作" align="center" width="180" >
                <template slot-scope="scope">
                    <label title="编辑"><i @click="SXHandleEdit_vue(scope.$index, scope.row)" class="iconfont icon-ai-edit" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                    <label title="删除" v-if="isxmtc!='2'"><i @click="SXHandleDelete_vue(scope.$index, scope.row)" class="iconfont delete" style="font-size:18px;cursor:pointer;padding: 2px;color: #F56C6C"></i></label>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="水箱信息维护" :visible.sync="sxDialogFormVisible" width="50%" :close-on-click-modal="false">
            <el-form :model="sxForm" ref="sxForm" :inline="true" :rules="rules" >
                <el-row :span="24">
                    <el-form-item label="水箱编号" :label-position="labelPosition" prop="sxbh" :label-width="formLabelWidth" style="margin-bottom: 20px">
                        <el-input :disabled="true" v-model="sxForm.sxbh" v-if="sxForm.sxbh!=null&&sxForm.sxbh!=''"></el-input>
                        <span v-else>保存后自动生成</span>
                    </el-form-item>
                </el-row>
                <el-row :span="24">
                    <el-form-item label="水箱名称" :label-position="labelPosition" prop="sxmc" :label-width="formLabelWidth" style="margin-bottom: 20px">
                        <el-input v-model="sxForm.sxmc" ></el-input>
                    </el-form-item>
                </el-row>
                <el-form-item label="长度" :label-position="labelPosition" prop="cd" :label-width="formLabelWidth" style="margin-bottom: 20px">
                    <el-input  v-model.number="sxForm.cd" style="width: 90%"></el-input>m
                </el-form-item>
                <el-form-item label="宽度" :label-position="labelPosition" prop="kd" :label-width="formLabelWidth" style="margin-bottom: 20px">
                    <el-input v-model.number="sxForm.kd" style="width: 90%"></el-input>m
                </el-form-item>
                <el-form-item label="高度" :label-position="labelPosition" prop="gd" :label-width="formLabelWidth" style="margin-bottom: 20px">
                    <el-input v-model.number="sxForm.gd" style="width: 90%"></el-input>m
                </el-form-item>
                <el-form-item label="容积" :label-position="labelPosition" prop="rj" :label-width="formLabelWidth" style="margin-bottom: 20px">
                    <el-input v-model.number="sxForm.rj" style="width: 90%"></el-input>m³
                </el-form-item>
                <el-form-item label="泵房名称" :label-position="labelPosition" prop="bfid" :label-width="formLabelWidth" style="margin-bottom: 20px">
                    <el-select v-model="sxForm.bfid" placeholder="请选择">
                        <el-option
                                v-for="item in bfOptions"
                                :key="item.codevalue"
                                :label="item.codename"
                                :value="item.codevalue">
                        </el-option>
                    </el-select>
                    <span style="color: #66b1ff;">注：若没有选项，请在项目泵房管理中增加</span>
                </el-form-item>
                <el-form-item label="备注" :label-position="labelPosition" prop="bz" :label-width="formLabelWidth">
                    <el-input v-model="sxForm.bz" type="textarea" style="width: 600px;"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button size="mini" type="info" @click="sxDialogFormVisible = false" v-if="isxmtc!='2'"><i class="iconfont icon-quxiao"></i>取 消</el-button>
                <el-button type="primary" size="mini" @click="sxxxwh_vue('sxForm')" :loading="loading" v-if="isxmtc!='2'"><i class="iconfont icon-queren"></i>确 定</el-button>
            </div>
        </el-dialog>
    </section>
</template>
<script>
    import {initBFOption,initSXTable,sxxxwh} from '../../api/sjst';
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
            return{
                rules:{
                    sxmc:[{ required: true, message: '该信息不能为空', trigger: 'blur'}],
                    bfid:[{ required: true, message: '该信息不能为空', trigger: 'change'}],
                    cd:[
                        { required: true, message: '该信息不能为空', trigger: 'blur'},
                        { type: 'number', message: '该信息必须为数字值', trigger: 'blur'}
                    ],
                    kd:[
                        { required: true, message: '该信息不能为空', trigger: 'blur'},
                        { type: 'number', message: '该信息必须为数字值', trigger: 'blur'}
                    ],
                    gd:[
                        { required: true, message: '该信息不能为空', trigger: 'blur'},
                        { type: 'number', message: '该信息必须为数字值', trigger: 'blur'}
                    ],
                    rj:[
                        { required: true, message: '该信息不能为空', trigger: 'blur'},
                        { type: 'number', message: '该信息必须为数字值', trigger: 'blur'}
                    ],
                },
                SXData:[],
                bfOptions:[],
                sxDialogFormVisible:false,
                loading:false,
                sxForm:{
                    sxbh:"",
                    sxmc:"",
                    cd:"",
                    kd:"",
                    gd:"",
                    rj:"",
                    bfid:"",
                    bz:""
                },
                formLabelWidth:"100px",
                labelPosition: 'right',
            }
        },
        methods:{
            /*初始化泵房选择器*/
            initBFOption_vue(){
                let params = {
                    xmid:this.xmid
                }
                initBFOption(params).then((res)=>{
                    if(res.data!=null){
                        this.bfOptions=res.data.row
                    }
                })
            },
            initSXForm(){
                this.sxForm.sxbh="",
                    this.sxForm.sxmc="",
                    this.sxForm.cd="",
                    this.sxForm.kd="",
                    this.sxForm.gd="",
                    this.sxForm.rj="",
                    this.sxForm.bfid="",
                    this.sxForm.bz=""
            },
            initMun(value){
                if(value==null||value==""){
                    value=0
                }
                return value;
            },
            SXHandleAdd_vue(){
                this.initSXForm();
                this.sxDialogFormVisible=true;
            },
            initSXTable_vue(){
                let params={
                    xmid:this.xmid
                }
                initSXTable(params).then((res)=>{
                    if(res.data!=null){
                        this.SXData = res.data.row;
                    }
                })
            },
            sxxxwh_vue(formName){
                this.loading=true;
                let params={
                    sxbh:this.sxForm.sxbh,
                    sxmc:this.sxForm.sxmc,
                    cd:this.initMun(this.sxForm.cd),
                    kd:this.initMun(this.sxForm.kd),
                    gd:this.initMun(this.sxForm.gd),
                    rj:this.initMun(this.sxForm.rj),
                    bfid:this.sxForm.bfid,
                    bz:this.sxForm.bz,
                    zt:"1",
                    xmid:this.xmid
                };
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        sxxxwh(params).then((res)=>{
                            this.loading=false;
                            if(res.data){
                                this.$message.success('保存成功！');
                                this.sxDialogFormVisible=false;
                                this.initSXTable_vue();
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
            SXHandleEdit_vue(index,row){
                this.initSXForm();
                this.sxDialogFormVisible=true;
                this.sxForm.sxbh=row.sxbh,
                    this.sxForm.sxmc=row.sxmc,
                    this.sxForm.cd=Number(this.initMun(row.cd)),
                    this.sxForm.kd=Number(this.initMun(row.kd)),
                    this.sxForm.gd=Number(this.initMun(row.gd)),
                    this.sxForm.rj=Number(this.initMun(row.rj)),
                    this.sxForm.bfid=row.bfid,
                    this.sxForm.bz=row.bz
            },
            SXHandleDelete_vue(index,row){
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    let params={
                        xmid:this.xmid,
                        sxbh:row.sxbh,
                        sxmc:row.sxmc,
                        cd:Number(this.initMun(row.cd)),
                        kd:Number(this.initMun(row.kd)),
                        gd:Number(this.initMun(row.gd)),
                        rj:Number(this.initMun(row.rj)),
                        bfid:row.bfid,
                        bz:row.bz,
                        zt:"0"
                    }
                    sxxxwh(params).then((res) => {
                        this.initSXTable_vue();
                    })
                })
            }
        },
        mounted(){
            this.initSXTable_vue();
            this.initBFOption_vue();

        }
    }
</script>