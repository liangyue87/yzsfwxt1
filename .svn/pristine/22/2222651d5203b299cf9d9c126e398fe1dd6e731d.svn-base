<template>
    <section>
        <el-card class="box-card" style="margin-top: 15px;" body-style="{}">
            <div slot="header" class="clearfix">
                <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">选择出具通知单的户表
                    <el-button style="float: right; padding: 3px 0" type="text" @click="savesbld_vue">确认并关闭</el-button>
                </div>
            </div>
            <el-table ref="hbmultipleTable" :data.sync="ldsbrow" highlight-current-row v-loading="ldsblistLoading" height="360"
                      @selection-change="ldsbselsChange"
                      :stripe="true" :row-style="rowClass" :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
                <el-table-column type="selection" align="center" :selectable='ldsbcheckboxInit'>
                </el-table-column>
                <el-table-column property="hh" label="报装户号" width="150" align="center">
                </el-table-column>
                <el-table-column property="dz" label="用户地址" width="150" align="center" :formatter="formatYhdz">
                </el-table-column>
                <el-table-column property="ldbh" label="楼栋号" align="center">
                </el-table-column>
                <el-table-column property="mph" label="门牌号" align="center">
                </el-table-column>
                <el-table-column property="kjname" label="报装口径" align="center">
                </el-table-column>
                <el-table-column property="ysxzname" label="报装用水性质" width="120" align="center">
                </el-table-column>
                <el-table-column property="jfztname" label="工程交费状态" width="120" align="center">
                </el-table-column>
            </el-table>
        </el-card>
    </section>
</template>

<script>
    import {querySbByldid,savejfhb} from '../../../api/htsf';
    export default {
        name: "Jfldsb",
        data() {
            return {
                filters: {
                    xmid:this.$route.query.xmid,//项目ID
                },

                ldsbrow:[],
                ldsblistLoading:false,
                ldsbsels:[],//楼栋水表勾选列集合
            }
        },
        props:{
            ldid:{
                type:String,
            },
            tzdid:{
                type:String,
            }
        },
        mounted() {
            this.getLdsb_vue();
        },
        methods:{
            getLdsb_vue(ldid){
                let para = {ldid:ldid,xmid:this.filters.xmid};
                querySbByldid(para).then(res=>{
                    this.ldsbrow = res.data.list;
                    this.querySbcheck();
                });
            },
            //水表选择完保存并关闭
            savesbld_vue(){
                let list = this.ldsbsels;
                if(list.length != 0){
                    let sbid = "";
                    let ldid = list[0].ldid;
                    list.forEach((row,index)=>{
                        if(index == 0){
                            sbid +=  row.id ;
                        }else{
                            sbid += ","+row.id;
                        }
                    });
                    let para = {sbids:sbid,ldid:ldid};
                    savejfhb(para).then(res=>{
                        this.$emit('closeLdsb_vue');
                    });
                }else{
                    let para = {type:'1',ldid:this.ldid};
                    savejfhb(para).then(res=>{
                        this.$emit('closeLdsb_vue');
                    });
                }
            },
            ldsbselsChange: function (sels) {
                this.ldsbsels = sels;
            },
            //查询水表是否默认不可选
            ldsbcheckboxInit(row,index){
                if(row.jfzt == 'fywj'){
                    if(this.tzdid == '' || this.tzdid == null){
                        return false;//不可勾选
                    }else{
                        if(row.jfzbid == this.tzdid){
                            return true;//可勾选
                        }else{
                            return false;//不可勾选
                        }
                    }
                }else{
                    return true;//可勾选
                }
            },
            //查询水表是否默认选中
            querySbcheck(){
                if(this.ldsbrow != ''){
                    this.ldsbrow.forEach((row,index)=>{
                        if(row.gxzt == 'ygx'){
                            setTimeout(()=>{
                                this.$refs.hbmultipleTable.toggleRowSelection(this.ldsbrow[index],true)
                            },1)
                        }else if(row.gxzt == 'wgx'){
                            if(this.tzdid == '' || this.tzdid == null){
                                setTimeout(()=>{
                                    this.$refs.hbmultipleTable.toggleRowSelection(this.ldsbrow[index],false)
                                },1)
                            }else{
                                if(row.jfzbid == this.tzdid){
                                    setTimeout(()=>{
                                        this.$refs.hbmultipleTable.toggleRowSelection(this.ldsbrow[index],true)
                                    },1)
                                }else{
                                    setTimeout(()=>{
                                        this.$refs.hbmultipleTable.toggleRowSelection(this.ldsbrow[index],false)
                                    },1)
                                }
                            }
                        }
                    })
                }
            },
            rowClass:function (row,index) {
                return {"height":"6.0vh"}
            },
            formatYhdz(row, column){
                if(row.mph == null){
                    return '';
                }else{
                    return row.dz.replace("[楼号]",row.ldbh).replace("[门牌]",row.mph);
                }
            }
        }
    }
</script>

<style scoped>

</style>