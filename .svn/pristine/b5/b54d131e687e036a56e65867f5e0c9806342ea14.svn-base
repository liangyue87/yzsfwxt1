<template>
    <section>
        <el-card class="box-card" style="margin-top: 15px;" body-style="{}">
            <div slot="header" class="clearfix">
                <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">收费通知单关联的用户报表水表
                    <el-button style="float: right; padding: 3px 0" type="text" @click="saveldsb_vue">确认并关闭</el-button>
                </div>
            </div>
            <el-form :inline="true">
                <el-form-item label="户表" style="text-align: right">
                    <el-tag type="info">{{ldmx}}</el-tag>
                </el-form-item>
            </el-form>
            <el-table :data="ldrow" highlight-current-row v-loading="ldlistLoading" style="width: 100%;" height="360"
                      :stripe="true" :row-style="rowClass" :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
                <el-table-column property="tymc" label="庭院" align="center">
                </el-table-column>
                <el-table-column property="ldbh" label="楼栋号" align="center">
                </el-table-column>
                <el-table-column property="lfsx" label="楼房属性" align="center">
                </el-table-column>
                <el-table-column property="zhs" label="总户数" align="center">
                </el-table-column>
                <el-table-column property="yjsl" label="已出单户数" align="center">
                </el-table-column>
                <el-table-column property="wyjsl" label="未出单户数" align="center">
                </el-table-column>
                <el-table-column property="gxsl" label="勾选户数" align="center">
                </el-table-column>
                <el-table-column label="勾选明细" align="center">
                    <template slot-scope="scope">
                        <label title="打开选项">
                            <i class="iconfont icon-xiangqing"  @click="toldsb_vue(scope.row)"
                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                        </label>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
    </section>
</template>

<script>
    import {queryLdByxmid} from '../../../api/htsf';
    export default {
        name: "Jfldgx",
        data() {
            return {
                filters: {
                    xmid:this.$route.query.xmid,//项目ID
                },

                ldrow:[],//楼栋集合
                ldmx:'总数量：0    其他单据已含数量：0',//楼栋勾选明细
                ldlistLoading:false,
            }
        },
        mounted() {
            this.getLd_vue();
        },
        methods:{
            getLd_vue(){
                let para = {xmid:this.filters.xmid};
                queryLdByxmid(para).then(res=>{
                    this.ldrow = res.data.list;
                    if(res.data.zhs != '' && res.data.zhs != null){
                        this.ldmx = "总数量：" + res.data.zhs + "  其他单据已含数量：" + res.data.yjs;
                    }
                })
            },
            //根据楼栋打开楼栋内水表列表弹窗
            toldsb_vue(row){
                this.$emit('openLdsb_vue',row.ldid);
            },
            //楼栋水表选择完保存并关闭
            saveldsb_vue(){
                this.$emit('closeLd_vue');
            },
            rowClass:function (row,index) {
                return {"height":"6.0vh"}
            }
        }
    }
</script>

<style scoped>

</style>