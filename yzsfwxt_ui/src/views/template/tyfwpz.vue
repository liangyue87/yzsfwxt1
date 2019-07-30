<template>
    <section>
        <div class="grid-content bg-purple-light" style="display: flex;align-items: center;width: 100%;height: 40px;background:#f5f7fa">
            <span style="color: #606266;;margin-left:10px">庭院管理</span>

            <el-button v-if="this.qb != '0'&&isxmtc!='2'" size="mini" type="primary" @click="TYHandleAdd_vue()" style="margin-left: 10px"><i class="iconfont icon-xinzeng"></i>新增范围</el-button>
        </div>
        <el-table :data="TYData" style="width: 100%" :stripe="true">
            <el-table-column
                    prop="bh"
                    label="庭院编号"
                    align="center"
                    width="250">
            </el-table-column>
            <el-table-column
                    prop="mc"
                    label="庭院名称"
                    align="center">
            </el-table-column><!--v-if="this.qb != '0'"-->
            <el-table-column  label="操作" align="center" width="180" >
                <template slot-scope="scope">
                    <label title="编辑"><i @click="TYHandleEdit_vue(scope.$index, scope.row)" class="iconfont icon-ai-edit" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                    <label title="删除" v-if="isxmtc!='2'"><i @click="TYHandleDelete_vue(scope.$index, scope.row)" class="iconfont delete" style="font-size:18px;cursor:pointer;padding: 2px;color: #F56C6C"></i></label>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="庭院信息维护" :visible.sync="tyDialogFormVisible" width="30%" :close-on-click-modal="false">
            <el-form :model="tyForm" :inline="true" class="demo-form-inline" :rules="rules" ref="tyForm">
                <el-row>
                    <el-form-item label="庭院编号" :label-position="labelPosition">
                        <el-input :disabled="true" v-model="tyForm.bh" v-if="tyForm.bh!=null&&tyForm.bh!=''"></el-input>
                        <span v-else>保存后自动生成</span>
                    </el-form-item>
                </el-row>
                <el-form-item label="庭院名称" :label-position="labelPosition" prop="mc" >
                    <el-input v-model="tyForm.mc"></el-input>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button size="mini" type="info" @click="tyDialogFormVisible = false" v-if="isxmtc!='2'"><i class="iconfont icon-quxiao"></i>取 消</el-button>
                <el-button type="primary" size="mini" :loading="loading" @click="tyxxwh_vue('tyForm')" v-if="isxmtc!='2'"><i class="iconfont icon-queren"></i>确 定</el-button>
            </div>
        </el-dialog>
    </section>
</template>
<script>
    import {initTYTable,tyxxwh} from '../../api/sjst';
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
                    mc:[{ required: true, message: '该信息不能为空', trigger: 'blur'}]
                },
                TYData:[],
                tyDialogFormVisible:false,
                tyForm:{
                    bh:"",
                    mc:""
                },
                formLabelWidth:"100px",
                labelPosition: 'right',
                loading:false
            }
        },
        methods:{
            TYHandleAdd_vue(){
                this.initTYForm();
                this.tyDialogFormVisible=true;
            },
            initTYForm(){
                this.tyForm.bh="",
                    this.tyForm.mc=""
            },
            initTYTable_vue(){
                let params={
                    xmid:this.xmid
                }
                initTYTable(params).then((res)=>{
                    if(res.data!=null){
                        this.TYData = res.data.row;
                    }
                })
            },
            tyxxwh_vue(formName){
                let params={
                    xmid:this.xmid,
                    bh:this.tyForm.bh,
                    mc:this.tyForm.mc,
                    zt:"1"
                }
            this.loading=true;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        tyxxwh(params).then((res) => {
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
                this.tyForm.bh=row.bh,
                    this.tyForm.mc=row.mc
            },
            TYHandleDelete_vue(index,row){
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    let params={
                        xmid:this.xmid,
                        bh:row.bh,
                        mc:row.mc,
                        zt:"0"
                    }
                    tyxxwh(params).then((res) => {
                        this.initTYTable_vue();
                    })
                })

            },
        },
        mounted(){
            this.initTYTable_vue();
        }
    }
</script>

<style>
    .el-dialog__footer {
        padding: 10px 20px 20px;
        text-align: center;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
    }
    .el-dialog__headerbtn {
        position: absolute;
        top: 14px;
        right: 20px;
        padding: 0;
        background: 0 0;
        border: none;
        outline: 0;
        cursor: pointer;
        font-size: 16px;
    }
</style>