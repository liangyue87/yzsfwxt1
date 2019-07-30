<!--物料清单配置界面-->
<template>
    <section>
        <el-row style="margin-top: 15px;">
                        <el-form  :model="filters" >
                            <el-form-item>
                                <el-col :span="2" style="text-align: right">
                                    <span>{{wlname}}名称：</span>
                                </el-col>
                                <el-col :span="4">
                                    <el-input v-model="filters.wlmc" size="small" @keyup.enter.native="getData_vue"></el-input>
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <span>{{wlname}}编码：</span>
                                </el-col>
                                <el-col :span="4">
                                    <el-input v-model="filters.wlbm" size="small" @keyup.enter.native="getData_vue"></el-input>
                                </el-col>
                                <el-col :span="2" style="text-align: center">
                                    <el-button type="primary" size="mini" @click="getData_vue()" icon="el-icon-search">查询</el-button>
                                </el-col>
                            </el-form-item>
                        </el-form>
                    <el-row v-if="isxmtc!='2'">
                        <div class="grid-content bg-purple-light" style="display: flex;align-items: center;width: 100%;height: 40px;background:#f5f7fa">
                            <span style="color: #606266;;margin-left:10px">{{wlname}}选择</span>
                        </div>
                        <elx-editable :data.sync="Data" style="width: 100%" :stripe="true" >
                            <elx-editable-column
                                    prop="wlbm"
                                    :label=wlbh
                                    align="center"
                                    width="250">
                            </elx-editable-column>
                            <elx-editable-column
                                    prop="wlmc"
                                    :label=wlmc
                                    align="center">
                            </elx-editable-column>
                            <elx-editable-column
                                    prop="ggxh"
                                    label="规格型号"
                                    align="center">
                            </elx-editable-column>
                            <elx-editable-column
                                    prop="jbjldw"
                                    label="基本计量单位"
                                    align="center">
                            </elx-editable-column>
                            <elx-editable-column
                                    prop="sxsl"
                                    label="需要数量"
                                    align="center"
                                    :edit-render="{name: 'ElInputNumber'}">

                            </elx-editable-column>
                            <elx-editable-column  label="操作" align="center" width="180" v-if="this.qb != '0'">
                                <template slot-scope="scope">
                                    <label title="确认添加"><i @click="wlHandleAdd_vue(scope.$index, scope.row)" class="iconfont icon-queren" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                                </template>
                            </elx-editable-column>
                        </elx-editable>
                        <el-col :span="24" class="toolbar" >
                            <!--工具条-->
                            <el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[5, 10, 20, 50]"
                                           @current-change="handleCurrentChange" @size-change="handleSizeChange":current-page.sync="this.page"
                                           :page-size="this.size" style="float:right;" :total="this.total">
                            </el-pagination>
                        </el-col>
                    </el-row>

                    <el-row>
                        <div class="grid-content bg-purple-light" style="display: flex;align-items: center;width: 100%;height: 40px;background:#f5f7fa">
                            <span style="color: #606266;;margin-left:10px">{{wlname}}清单列表</span>
                        </div>
                        <elx-editable :data.sync="pickData" style="width: 100%" :stripe="true" @blur-active="HandleEdit_vue">
                            <elx-editable-column
                                    prop="wlbm"
                                    :label=wlbh
                                    align="center"
                                    width="250">
                            </elx-editable-column>
                            <elx-editable-column
                                    prop="wlmc"
                                    :label=wlmc
                                    align="center">
                            </elx-editable-column>
                            <elx-editable-column
                                    prop="ggxh"
                                    label="规格型号"
                                    align="center">
                            </elx-editable-column>
                            <elx-editable-column
                                    prop="jbjldw"
                                    label="基本计量单位"
                                    align="center">
                            </elx-editable-column>
                            <elx-editable-column
                                    prop="sxsl"
                                    label="需要数量"
                                    align="center"
                                    :edit-render="{name: 'ElInputNumber'}">

                            </elx-editable-column>
                            <elx-editable-column  label="操作" align="center" width="180" v-if="this.qb != '0'">
                                <template slot-scope="scope">
                                    <label title="删除" v-if="isxmtc!='2'"><i @click="HandleDelete_vue(scope.$index, scope.row)" class="iconfont delete" style="font-size:18px;cursor:pointer;padding: 2px;color: #F56C6C"></i></label>
                                </template>
                            </elx-editable-column>
                        </elx-editable>
                        <el-col :span="24" class="toolbar" >
                            <!--工具条-->
                            <el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[5, 10, 20, 50]"
                                           @current-change="pickHandleCurrentChange" @size-change="pickHandleSizeChange":current-page.sync="this.page"
                                           :page-size="this.pickSize" :total="this.pickTotal" style="float:right;" >
                            </el-pagination>
                        </el-col>
                    </el-row>
        </el-row>
    </section>
</template>

<script>
    import {wlHandleAdd,pickHandle,getWlData,wlqdHandleEdit,wlqdHandleDelete} from '../../api/sjst';
    export default {
        props:{
            xmid:{
                type: String,
                required: true
            },
            name:{
                type: Number,
                required: false
            },
            qb:{
                required: false
            },
            isxmtc:{
                type: String,
                required: true
            }
        },
        data() {
            return {
                wlmc:"",
                wlbh:"",
                wlname:"",
                total: 0,//表格数据总数量
                page: 1,//当前页码
                size: 5,//每页容量
                pickTotal: 0,//表格数据总数量
                pickPage: 1,//当前页码
                pickSize: 5,//每页容量
                Data:[],
                pickData:[],
                filters:{
                    wlmc:"",
                    wlbm:""
                },
                //xmid:"1"
            }
        },

        methods: {
            getData_vue(){
                this.getWlData_vue();
                this.pickHandle_vue()
            },
            //获取物料信息
            getWlData_vue(){
                let params={
                    page:this.page,
                    size:this.size
                }
                params = Object.assign(params,this.filters);
                getWlData(params).then((res)=>{
                    if(res.data!=null){
                        this.Data = res.data.row;
                        this.total = res.data.total
                    }
                })
            },
            handleCurrentChange(val){
              this.page=val;
               this.getWlData_vue()
            },
            handleSizeChange(val) {
                this.size = val;
                this.page = 1;
                this.getWlData_vue();
            },
            pickHandleSizeChange(val){
                this.picksize = val;
                this.pickPage = 1;
                this.pickHandle_vue();
            },
            //判断是否编辑
            /*getIsEdit(){
                for(let i = 0;i<this.Data.length;i++){
                    if(this.Data[i].isEdit){
                        return true;
                    }
                }
                return false;
            },*/
            //编辑数量
            cellEdit(row, column, cell, event){
                /*let isEdit = this.getIsEdit();
                if(isEdit){
                    this.$message.error('请先确认添加！');
                }*/
                if(column.property=='sxsl'){
                    row.isEdit=true;
                }
            },
            //物料添加
            wlHandleAdd_vue(index,row){
                var reg = /^[0-9]*[1-9][0-9]*$/;
                if (!reg.test(row.sxsl)) {
                    this.$message.error('需要数量请填写大于0的整数！');
                    return;
                }
                let params={
                    xmid:this.xmid,
                    wlkid:row.id,
                    sxsl:row.sxsl
                }
                wlHandleAdd(params).then((res)=>{
                    if(res.data!=null){
                        if(res.data=="-1"){
                            this.$message.error('已添加该类物料！');
                        }else if(res.data=="1"){
                            this.pickHandle_vue();

                        }else{
                            this.$message.error('添加失败');
                        }
                        row.isEdit=false;
                    }

                })
            },
            /*getPickIsEdit(){
                for(let i = 0;i<this.pickData.length;i++){
                    if(this.pickData[i].isEdit){
                        return true;
                    }
                }
                return false;
            },*/
            pickHandleCurrentChange(val){
                this.pickPage = val;
                this.pickHandle_vue();
            },
            pickCellEdit(row, column, cell, event){
               /* let isEdit = this.getPickIsEdit();
                if(isEdit){
                    this.$message.error('请先确认修改！');
                }*/
               if(column.property=='sxsl'){
                   row.pickIsEdit=true;
               }
            },
            //项目物料查询
            pickHandle_vue(){
              let params = {
                  xmid:this.xmid,
                  page:this.pickPage,
                  size:this.pickSize
              }
                params = Object.assign(params,this.filters);
                pickHandle(params).then((res)=>{
                    if(res.data!=null){
                        if(res.data.total>0&&res.data.row.length<=0){
                            this.pickPage=this.pickPage-1
                            this.pickHandle_vue();
                        }
                        this.pickTotal=res.data.total;
                        this.pickData = res.data.row
                    }
                })

               /* if (this.pickTotal == '5'){
                    this.pickPage = '1';
                    this.pickHandle_vue();
                    alert("000")
                    return;
                }*/
            },
            //物料编辑
            HandleEdit_vue(row,column,cell){
                if(this.isxmtc!='2'){
                    var reg = /^[0-9]*[1-9][0-9]*$/;
                    if (!reg.test(row.sxsl)) {
                        this.$message.error('需要数量请填写大于0的整数！');
                        this.pickHandle_vue();
                        return;
                    }

                    let params={
                        id:row.id,
                        sxsl:row.sxsl
                    }
                    wlqdHandleEdit(params).then((res)=>{
                        this.pickHandle_vue()
                        row.pickIsEdit=false;
                    })
                }else{
                    return;
                }

            },
            //删除物料
            HandleDelete_vue(index,row){
                this.$confirm('确认删除该物料吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    let params={
                        id:row.id
                    }
                    wlqdHandleDelete(params).then((res)=>{
                        this.pickHandle_vue()
                    })
                })
            }
        },
        mounted() {
            this.getWlData_vue();
            this.pickHandle_vue();
                if(this.name==0){
                    this.wlname="材料"

                }else{
                    this.wlname="物料"
                }
            this.wlmc=this.wlname+"名称";
            this.wlbh=this.wlname+"编码";
        },
        watch:{

        }
    }

</script>

