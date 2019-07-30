<!--缴费通知单工商表勾选缴费
ly
-->
<template>
    <section>
        <el-card class="box-card" style="margin-top: 15px;" body-style="{}">
            <div slot="header" class="clearfix">
                <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">应收费用快速填写栏
                    <el-button style="float: right; padding: 3px 0" type="text" @click="saveGsb_vue">确认并关闭</el-button>
                </div>
            </div>
            <el-form>
                <el-form-item>
                    <el-col :span="3" style="text-align: right">
                        <span>类型&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="8">
                        <el-input size="small" v-model="gslx"></el-input>
                    </el-col>
                    <el-col :span="3" style="text-align: right">
                        <span>口径&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="8">
                        <el-select size="small" v-model="zbkj" placeholder="请选择">
                            <el-option v-for="item in zbkjList"
                                       :key="item.VALUE"
                                       :label="item.LABEL"
                                       :value="item.VALUE"></el-option>
                        </el-select>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-col :span="3" style="text-align: right">
                        <span>应收&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="8">
                        <el-input size="small" v-model="gsysje" style="width: 250px"></el-input>元
                    </el-col>
                    <el-col :span="3" style="text-align: right">
                        <span>批量定价&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="8">
                        <el-button size="mini" type="primary"
                                   @click="gsdj_vue">
                            <i class="iconfont icon-save"></i>应用</el-button>
                    </el-col>
                </el-form-item>
            </el-form>
        </el-card>
        <el-card class="box-card" style="margin-top: 15px;" body-style="{}">
            <div slot="header" class="clearfix">
                <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">用户报建的水表</div>
            </div>
            <elx-editable ref="gsbmultipleTable" :data.sync="gsbrow" highlight-current-row v-loading="gslistLoading"
                          height="200"  @selection-change="gsselsChange" @blur-active="gselxedit_vue"  style="width: 100%;">
                <elx-editable-column type="selection" width="50" :selectable='gscheckboxInit' align="center">
                </elx-editable-column>
                <elx-editable-column prop="hh" label="报装户号" align="center">
                </elx-editable-column>
                <elx-editable-column prop="dz" label="位置" align="center">
                </elx-editable-column>
                <elx-editable-column prop="kjname" label="口径" align="center">
                </elx-editable-column>
                <elx-editable-column prop="gcfysje" label="应收金额" width="100" align="center" :edit-render="{name: 'ElInput'}">
                </elx-editable-column>
                <elx-editable-column prop="jbfs" label="接表方式" align="center">
                </elx-editable-column>
                <elx-editable-column prop="ysxzname" label="用水性质" align="center">
                </elx-editable-column>
                <elx-editable-column prop="hyflname" label="行业分类" align="center">
                </elx-editable-column>
                <!--<elx-editable-column prop="" label="用途" align="center">-->
                <!--</elx-editable-column>-->
                <elx-editable-column prop="jfzt" label="状态" align="center">
                </elx-editable-column>
            </elx-editable>
            <!--<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[5 ,10 ,20, 50]"-->
                           <!--@current-change="gsbhandleCurrentChange" @size-change="gsbhandleSizeChange"-->
                           <!--:page-size="this.gsbsize" style="float:right;" :total="this.gsbtotal">-->
            <!--</el-pagination>-->
        </el-card>
    </section>
</template>

<script>
    import {queryGsbByXmid,gsbelxedit,gspldjxg,gsjezj} from '../../../api/htsf';
    export default {
        name: "Jfgsb",
        data() {
            return {
                filters: {
                    xmid:this.$route.query.xmid,//项目ID
                },
                gslx:'',//类型
                zbkj:'',//口径
                gsysje:'',//工商应收金额
                gslistLoading:false,
                gsbrow:[],//工商集合
                gsbsels:[],//工商表勾选列集合
                gsbtotal: 0,//表格数据总数量
                gsbpage: 1,//当前页码
                gsbsize: 5,//每页容量
                jtgsbc:'0'//监听保存按钮
            }
        },
        props:{
            zbkjList:{
                type:Array,
            },
            tzdid:{
                type:String,
            }
        },
        mounted(){
            this.gslx = "工商表";
            this.zbkj = '';
            this.gsysje = '';
            this.queryGsb_vue();
        },
        methods:{
            queryGsb_vue(){
                let para = {xmid:this.filters.xmid,page:this.gsbpage-1,size:this.gsbsize,tzdid:this.tzdid};
                queryGsbByXmid(para).then(res=>{
                    this.gsbrow = res.data.gsbList;
                    this.gsbtotal = res.data.totol;
                    if(this.tzdid != '' && this.tzdid != null){
                        this.querySbcheck();
                    }
                });
            },
            //工商表勾选批量定价修改
            gsdj_vue(){
                let list = this.gsbsels;
                if(list.length != 0){
                    let sbid = "(";
                    list.forEach((row,index)=>{
                        if(index == 0){
                            sbid += "'" + row.id + "'";
                        }else{
                            sbid += ",'"+row.id + "'";
                        }
                    });
                    sbid += ")";
                    let para = {sbids:sbid,zbkj:this.zbkj,gsysje:this.gsysje};
                    if(this.zbkj = ''){

                    }else{
                        this.gslistLoading=true;
                        gspldjxg(para).then(res=>{
                            this.queryGsb_vue();
                            this.gslistLoading=false;
                        });
                    }
                }
            },
            //保存工商表并关闭
            saveGsb_vue(){
                this.jtgsbc = "1";
                let list = this.gsbsels;
                if(list.length != 0){
                    let sbid = "(";
                    list.forEach((row,index)=>{
                        if(index == 0){
                            sbid += "'" + row.id + "'";
                        }else{
                            sbid += ",'"+row.id + "'";
                        }
                    });
                    sbid += ")";
                    sessionStorage.setItem('sfkpgsbid',sbid);
                    let para = {sbids:sbid};
                    gsjezj(para).then(res=>{
                        if(res.msg == 'success'){
                            this.$message({
                                message: '保存成功',
                                type: 'success'
                            });
                            this.$emit('closeGs_vue',res.list[0].GSJE+","+this.jtgsbc);
                        }else{
                            this.$message.error('保存失败！');
                        }
                    });
                }else{
                    this.$emit('closeGs_vue',0);
                }
            },
            //查询水表是否默认选中
            querySbcheck(){
                if(this.gsbrow != ''){
                    this.gsbrow.forEach((row,index)=>{
                        if(row.gxzt == 'ygx'){
                            if(row.sftzdzbid == this.tzdid){
                                setTimeout(()=>{
                                    this.$refs.gsbmultipleTable.toggleRowSelection(this.gsbrow[index],true)
                                },1)
                            }
                        }else if(row.gxzt == 'wgx'){
                            setTimeout(()=>{
                                this.$refs.gsbmultipleTable.toggleRowSelection(this.gsbrow[index],false)
                            },1)
                        }
                    })
                }
            },
            //查询水表是否默认不可选
            gscheckboxInit(row,index){
                if(row.gxzt == 'ygx'){
                    if(row.sftzdzbid == this.tzdid){
                        return true;
                    }else{
                        return false;
                    }
                }else{
                    return true;
                }
            },
            //工商表勾选
            gsselsChange: function (sels) {
                this.gsbsels = sels;
            },
            //工商表内编辑保存
            gselxedit_vue(row,column,cell){
                let mc = column.property;
                let params =  Object.assign(row);
                let list = this.gsbsels;
                if(list.length != 0){
                    list.forEach((lrow,index)=>{
                        if(lrow.id == row.id){
                            gsbelxedit(params).then(res=>{

                            });
                        }else{
                        }
                    });
                }
            },
            gsbhandleCurrentChange(val) {
                this.gsbpage = val;
                this.queryGsb_vue();
            },
            gsbhandleSizeChange(val) {
                this.gsbsize = val;
                this.gsbpage = 1;
                this.queryGsb_vue();
            },
        },
    }
</script>

<style scoped>

</style>