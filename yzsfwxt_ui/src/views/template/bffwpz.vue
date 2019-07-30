<template>
    <section>
        <div class="grid-content bg-purple-light" style="display: flex;align-items: center;width: 100%;height: 40px;background:#f5f7fa">
            <span style="color: #606266;;margin-left:10px">泵房管理</span>
            <el-button v-if="this.qb != '0'&&isxmtc!='2'" size="mini" type="primary" @click="BFHandleAdd_vue()" style="margin-left: 10px"><i class="iconfont icon-xinzeng"></i>新增范围</el-button>
        </div>
        <el-table :data="BFData" style="width: 100%" :stripe="true">
            <el-table-column
                    prop="bh"
                    label="泵房编号"
                    align="center"
                    width="250">
            </el-table-column>
            <el-table-column
                    prop="mc"
                    label="泵房名称"
                    align="center">
            </el-table-column><!--v-if="this.qb != '0'"-->
            <el-table-column  label="操作" align="center" width="180" >
                <template slot-scope="scope">
                    <label title="编辑"><i @click="BFHandleEdit_vue(scope.$index, scope.row)" class="iconfont icon-ai-edit" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                    <label title="删除" v-if="isxmtc!='2'"><i @click="BFHandleDelete_vue(scope.$index, scope.row)" class="iconfont delete" style="font-size:18px;cursor:pointer;padding: 2px;color: #F56C6C"></i></label>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="泵房信息维护" :visible.sync="bfDialogFormVisible" width="30%" :close-on-click-modal="false">
            <el-form :model="bfForm" :inline="true" class="demo-form-inline" :rules="rules" ref="bfForm">
                <el-row>
                    <el-form-item label="泵房编号" :label-position="labelPosition">
                        <el-input :disabled="true" v-model="bfForm.bh" v-if="bfForm.bh!=null&&bfForm.bh!=''"></el-input>
                        <span v-else>保存后自动生成</span>
                    </el-form-item>
                </el-row>

                <el-form-item label="泵房名称" :label-position="labelPosition" prop="mc" >
                    <el-input v-model="bfForm.mc"></el-input>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button size="mini" type="info" @click="bfDialogFormVisible = false" v-if="isxmtc!='2'"><i class="iconfont icon-quxiao"></i>取 消</el-button>
                <el-button type="primary" size="mini" @click="bfxxwh_vue('bfForm')" v-if="isxmtc!='2'"><i class="iconfont icon-queren"></i>确 定</el-button>
            </div>
        </el-dialog>
    </section>
</template>
<script>
    import {initBFTable,bfxxwh} from '../../api/sjst';
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
                BFData:[],
                bfDialogFormVisible:false,
                bfForm:{
                    bh:"",
                    mc:""
                },
                formLabelWidth:"100px",
                labelPosition: 'right',
            }
        },
        methods:{
            BFHandleAdd_vue(){
                this.initBFForm();
                this.bfDialogFormVisible=true;
            },
            initBFForm(){
                this.bfForm.bh="",
                    this.bfForm.mc=""
            },
            initBFTable_vue(){
                let params={
                    xmid:this.xmid
                }
                initBFTable(params).then((res)=>{
                    if(res.data!=null){
                        this.BFData = res.data.row;
                    }
                })
            },
            bfxxwh_vue(formName){
                let params={
                    xmid:this.xmid,
                    bh:this.bfForm.bh,
                    mc:this.bfForm.mc,
                    zt:"1"
                }
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        bfxxwh(params).then((res) => {
                            if(res.data){
                                this.$message.success('保存成功！');
                                this.bfDialogFormVisible=false;
                                this.initBFTable_vue();
                            }else{
                                this.$message.error('保存失败！');
                            }

                        })
                    } else {
                        return false;
                    }
                });

            },
            BFHandleEdit_vue(index,row){
                this.initBFForm();
                this.bfDialogFormVisible=true;
                this.bfForm.bh=row.bh,
                    this.bfForm.mc=row.mc
            },
            BFHandleDelete_vue(index,row){
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    let params={
                        xmid:this.xmid,
                        bh:row.bh,
                        mc:row.mc,
                        zt:"0"
                    }
                    bfxxwh(params).then((res) => {
                        this.initBFTable_vue();
                    })
                })
            }
        },
        mounted(){
            this.initBFTable_vue();
        }
    }
</script>