<!--缴费通知单户表勾选缴费
ly
-->
<template>
    <section>
        <el-card class="box-card" style="margin-top: 15px;" body-style="{}">
            <div slot="header" class="clearfix">
                <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">应收费用快速填写栏
                    <el-button style="float: right; padding: 3px 0" type="text" @click="saveHb_vue">确认并关闭</el-button>
                </div>
            </div>
            <el-form>
                <el-form-item>
                    <el-col :span="4" style="text-align: right">
                        <span>楼栋&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="8">
                        <el-select size="small" @change="queryHb_vue" clearable  v-model="hbld" placeholder="请选择" style="width: 276px;">
                            <el-option v-for="item in ldList"
                                       :key="item.VALUE"
                                       :label="item.LABEL"
                                       :value="item.VALUE"></el-option>
                        </el-select>
                    </el-col>
                    <el-col :span="4" style="text-align: right">
                        <span>口径&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="8">
                        <el-select size="small" @change="queryHb_vue" clearable  v-model="hbzbkj" placeholder="请选择" style="width: 276px;">
                            <el-option v-for="item in zbkjList"
                                       :key="item.VALUE"
                                       :label="item.LABEL"
                                       :value="item.VALUE"></el-option>
                        </el-select>
                    </el-col>
                </el-form-item>
                <!--<el-form-item style="margin-top: -1%">-->
                    <!--<el-col :span="4" style="text-align: right">-->
                        <!--<span>楼栋数据完整度&nbsp;&nbsp;</span>-->
                    <!--</el-col>-->
                    <!--<el-col :span="8">-->
                        <!--<el-input size="small" v-model="ldsj" :readonly="true"></el-input>-->
                    <!--</el-col>-->
                    <!--<el-col :span="4" style="text-align: right">-->
                        <!--<span>单据数据完整度&nbsp;&nbsp;</span>-->
                    <!--</el-col>-->
                    <!--<el-col :span="8">-->
                        <!--<el-input size="small" v-model="djsj" :readonly="true"></el-input>-->
                    <!--</el-col>-->
                <!--</el-form-item>-->
                <el-form-item style="margin-top: -1%">
                    <el-col :span="4" style="text-align: right">
                        <span>分区&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="8">
                        <el-radio-group v-model="fq">
                            <el-radio label="没有加压分区或户表价格相同"><span style="font-size: 14px">没有加压分区或户表价格相同</span></el-radio>
                            <el-radio label="存在加压分区或户表价格不同"><span style="font-size: 14px">存在加压分区或户表价格不同</span></el-radio>
                        </el-radio-group>
                    </el-col>
                    <el-col :span="4" style="text-align: right">
                        <span>门牌范围&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="8">
                        <div v-if="fq=='没有加压分区或户表价格相同'">
                            <el-col :span="11">
                                <el-input size="small" v-model="beginMp" :readonly="true"></el-input>
                            </el-col>
                            <el-col class="line" :span="2" style="text-align: center">-</el-col>
                            <el-col :span="11">
                                <el-input size="small" v-model="endMp" :readonly="true"></el-input>
                            </el-col>
                        </div>
                        <div v-else>
                            <el-col :span="11">
                                <el-input size="small" v-model="beginMp"></el-input>
                            </el-col>
                            <el-col class="line" :span="2" style="text-align: center">-</el-col>
                            <el-col :span="11">
                                <el-input size="small" v-model="endMp"></el-input>
                            </el-col>
                        </div>
                    </el-col>
                </el-form-item>
                <el-form-item style="margin-top: -1%">
                    <el-col :span="4" style="text-align: right">
                        <span>应收&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="8">
                        <el-input size="small" v-model="hbysje" style="width: 250px"></el-input>元
                    </el-col>
                    <el-col :span="4" style="text-align: right">
                        <span>批量定价&nbsp;&nbsp;</span>
                    </el-col>
                    <el-col :span="8">
                        <el-button size="mini" type="primary"
                                   @click="hbdj_vue">
                            <i class="iconfont icon-save"></i>应用</el-button>
                    </el-col>
                </el-form-item>
            </el-form>
        </el-card>
        <el-card class="box-card" style="margin-top: 15px;" body-style="{}">
            <div slot="header" class="clearfix">
                <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">用户报建的水表
                    <!--<el-button style="float: right; padding: 3px 0" type="text" @click="saveHbgcf_vue">保存楼栋工程费</el-button>-->
                    <el-button style="float: right; padding: 3px 6px 0" type="text" @click="chooseHb_vue">选择未出通知单水表</el-button>
                </div>
            </div>
            <elx-editable :data.sync="hbrow" highlight-current-row v-loading="hblistLoading" height="200"
                          style="width: 100%;">
                <elx-editable-column prop="ldbh" label="楼栋号" align="center">
                </elx-editable-column>
                <elx-editable-column prop="hh" width="150" label="报装户号" align="center">
                </elx-editable-column>
                <elx-editable-column prop="dz" width="200" label="位置" align="center">
                </elx-editable-column>
                <elx-editable-column prop="mph" label="门牌号" align="center">
                </elx-editable-column>
                <elx-editable-column prop="kjname" label="口径" align="center">
                </elx-editable-column>
                <elx-editable-column prop="gcfysje" label="应收金额" width="100" align="center" :edit-render="{name: 'ElInput'}">
                </elx-editable-column>
                <elx-editable-column prop="jfzt" label="状态" align="center">
                </elx-editable-column>
            </elx-editable>
            <!--<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[5 ,10 ,20, 50]"-->
                           <!--@current-change="hbhandleCurrentChange" @size-change="hbhandleSizeChange"-->
                           <!--:page-size="this.hbsize" style="float:right;" :total="this.hbtotal">-->
            <!--</el-pagination>-->
        </el-card>
    </section>
</template>

<script>
    import {savejfhb,queryHb,saveHbgcf,hbjezj} from '../../../api/htsf';
    export default {
        name: "Jfhb",
        data() {
            return {
                filters: {
                    xmid:this.$route.query.xmid,//项目ID
                },

                hbld:'',//户表所选楼栋
                ldsj:'',//楼栋数据完整度
                djsj:'',//单据数据完整度
                ldList:[],//楼栋集合
                hbzbkj:'',//户表口径
                fq:'没有加压分区或户表价格相同',//分区
                beginMp:'',//起始门牌号
                endMp:'',//结束门牌号
                hbysje:'',
                hbrow:[],//户表集合
                allhbrow:[],//户表总数集合
                hblistLoading:false,
                hbtotal: 0,//表格数据总数量
                hbpage: 1,//当前页码
                hbsize: 5,//每页容量
                jtjmbc:'0',
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
        mounted() {
            this.hbysje = '';
            this.beginMp = '';
            this.endMp = '';
            this.hbld = '';
            this.hbzbkj = '';
            this.queryHb_vue();
        },
        methods:{
            queryHb_vue(){
                let para = {page:this.hbpage-1,size:this.hbsize,hbld:this.hbld,hbzbkj:this.hbzbkj,tzdid:this.tzdid,
                xmid:this.filters.xmid};
                queryHb(para).then(res=>{
                    this.hbtotal = res.data.total;
                    this.hbrow = res.data.list;
                    this.allhbrow = res.data.allList;
                    this.ldList = res.data.ldList;
                });
            },
            //户表勾选批量定价修改
            hbdj_vue(){
                let fq = this.fq;
                let list = this.hbrow;
                let ys = this.hbysje;
                if(fq == "没有加压分区或户表价格相同"){
                    if(list.length != 0){
                        list.forEach((row,index)=>{
                            row.gcfysje = ys;
                        });
                    }
                }else if(fq == "存在加压分区或户表价格不同"){
                    let beginmp = this.beginMp;
                    let endmp = this.endMp;
                    if(beginmp == '' || endmp == ''){

                    }else{
                        if(list.length != 0){
                            list.forEach((row,index)=>{
                                if(parseInt(beginmp) <= parseInt(row.mph) && parseInt(endmp) >= parseInt(row.mph)){
                                    row.gcfysje = ys;
                                }
                            });
                        }
                    }
                }else{

                }
            },
            //户表确认并关闭
            saveHb_vue(){
                this.jtjmbc = '1';
                let list = this.allhbrow;
                let lists = this.hbrow;
                let sfkpjmbid = "";
                if(lists.length !=0){
                    lists.forEach((row,index)=>{
                        if(index == 0){
                            sfkpjmbid += row.id + ":" + row.gcfysje;
                        }else{
                            sfkpjmbid += "," + row.id + ":" + row.gcfysje;
                        }
                    });
                    sessionStorage.setItem('sfkpjmbid',sfkpjmbid);
                }
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
                    let para = {sbids:sbid,sfkpjmbid:sfkpjmbid};
                    hbjezj(para).then(res=>{
                        if(res.msg == 'success'){
                            this.$message({
                                message: '保存成功',
                                type: 'success'
                            });
                            this.$emit('closeHb_vue',res.list[0].HBJE+","+this.jtjmbc);
                        }else{
                            this.$message.error('保存失败！');
                        }
                    });
                }else{
                    this.$emit('closeHb_vue',0);
                }
            },
            //选择未出通知单水表
            chooseHb_vue(){
                this.$emit('openLd_vue');
            },
            //保存楼栋工程费
            saveHbgcf_vue(){
                let list = this.hbrow;
                if(list.length !=0){
                    let sbids = "";
                    list.forEach((row,index)=>{
                        if(index == 0){
                            sbids += row.id + ":" + row.gcfysje;
                        }else{
                            sbids += "," + row.id + ":" + row.gcfysje;
                        }
                    });
                    let para = {sbids:sbids};
                    saveHbgcf(para).then(res=>{
                        if(res == true){
                            this.$message({
                                message: '保存成功',
                                type: 'success'
                            });
                        }else{
                            this.$message.error('保存失败！');
                        }
                    });
                }
            },
            hbhandleCurrentChange(val) {
                this.hbpage = val;
                this.queryHb_vue();
            },
            hbhandleSizeChange(val) {
                this.hbsize = val;
                this.hbpage = 1;
                this.queryHb_vue();
            },
            rowClass:function (row,index) {
                return {"height":"6.0vh"}
            }
        },
    }
</script>

<style scoped>

</style>