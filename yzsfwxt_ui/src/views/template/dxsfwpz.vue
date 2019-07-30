<template>
    <section>
        <div class="grid-content bg-purple-light" style="display: flex;align-items: center;width: 100%;height: 40px;background:#f5f7fa">
            <span style="color: #606266;;margin-left:10px">地下室管理</span>
            <el-button v-if="this.qb != '0'&&isxmtc!='2'" size="mini" type="primary" @click="DXSHandleAdd_vue()" style="margin-left: 10px"><i class="iconfont icon-xinzeng"></i>新增范围</el-button>
        </div>
        <el-table :data="DXSData" style="width: 100%" :stripe="true">
            <el-table-column
                    prop="bh"
                    label="地下室编号"
                    align="center"
                    width="250">
            </el-table-column>
            <el-table-column
                    prop="mc"
                    label="地下室名称"
                    align="center"
            >
            </el-table-column><!--v-if="this.qb != '0'"-->
            <el-table-column  label="操作" align="center" width="180" >
                <template slot-scope="scope">
                    <label title="编辑"><i @click="DXSHandleEdit_vue(scope.$index, scope.row)" class="iconfont icon-ai-edit" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                    <label title="删除"  v-if="isxmtc!='2'"><i @click="DXSHandleDelete_vue(scope.$index, scope.row)" class="iconfont delete" style="font-size:18px;cursor:pointer;padding: 2px;color: #F56C6C"></i></label>

                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="地下室信息维护" :visible.sync="dxsDialogFormVisible" width="30%" :close-on-click-modal="false">
            <el-form :model="dxsForm" :inline="true" class="demo-form-inline" :rules="rules" ref="dxsForm">
                <el-form-item label="地下室编号" :label-position="labelPosition">
                    <el-input :disabled="true" v-model="dxsForm.bh" v-if="dxsForm.bh!=null&&dxsForm.bh!=''"></el-input>
                    <span v-else>保存后自动生成</span>
                </el-form-item>
                <el-form-item label="地下室名称" :label-position="labelPosition" prop="mc" >
                    <el-input v-model="dxsForm.mc"></el-input>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button size="mini" type="info" @click="dxsDialogFormVisible = false"  v-if="isxmtc!='2'"><i class="iconfont icon-quxiao"></i>取 消</el-button>
                <el-button type="primary"size="mini" @click="dxsxxwh_vue('dxsForm')" :loading="loading"  v-if="isxmtc!='2'"><i class="iconfont icon-queren"></i>确 定</el-button>
            </div>
        </el-dialog>
    </section>
</template>
<script>
    import {initDXSTable,dxsxxwh} from '../../api/sjst';
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
                DXSData:[],
                dxsDialogFormVisible:false,
                loading:false,
                dxsForm:{
                    bh:"",
                    mc:""
                },
                formLabelWidth:"100px",
                labelPosition: 'right',
            }
        },
        methods:{
            DXSHandleAdd_vue(){
                this.initDXSForm();
                this.dxsDialogFormVisible=true;
            },
            initDXSForm(){
                this.dxsForm.bh="",
                    this.dxsForm.mc=""
            },
            initDXSTable_vue(){
                let params={
                    xmid:this.xmid
                }
                initDXSTable(params).then((res)=>{
                    if(res.data!=null){
                        this.DXSData = res.data.row;
                    }
                })
            },
            dxsxxwh_vue(formName){
                this.loading=true;
                let params={
                    xmid:this.xmid,
                    bh:this.dxsForm.bh,
                    mc:this.dxsForm.mc,
                    zt:"1"
                }
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        dxsxxwh(params).then((res) => {
                            this.loading=false;
                            if(res.data){
                                this.$message.success('保存成功！');
                                this.dxsDialogFormVisible=false;
                                this.initDXSTable_vue();
                            }else{
                                this.$message.error('保存失败！');
                            }

                        })
                    } else {
                        return false;
                    }
                });

            },
            DXSHandleEdit_vue(index,row){
                this.initDXSForm();
                this.dxsDialogFormVisible=true;
                this.dxsForm.bh=row.bh,
                    this.dxsForm.mc=row.mc
            },
            DXSHandleDelete_vue(index,row){
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    let params={
                        xmid:this.xmid,
                        bh:row.bh,
                        mc:row.mc,
                        zt:"0"
                    }
                    dxsxxwh(params).then((res) => {
                        this.initDXSTable_vue();
                    })
                })
            }
        },
        mounted(){
            this.initDXSTable_vue();
        }
    }
</script>