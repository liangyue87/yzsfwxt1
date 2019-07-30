<!--施工下单项目范围模板界面
ly
-->
<template>
    <section>
        <el-row style="margin-top: 15px;height: 300px">
            <el-col :span="24" >
                <el-row>
                    <el-col :span="4">
                        <el-tree :data="data" :props="defaultProps" :default-expand-all="true" @node-click="handleNodeClick"></el-tree>
                    </el-col>
                    <el-col :span="20" v-if="tyFlag==true">
                        <div class="grid-content bg-purple-light" style="display: flex;align-items: center;width: 100%;height: 40px;background:#f5f7fa">
                            <span style="color: #606266;;margin-left:10px">庭院范围</span>
                        </div>
                        <el-table :data="TYData" style="width: 100%" border>
                            <el-table-column property="BH" label="庭院编号" align="center">
                            </el-table-column>
                            <el-table-column property="MC" label="庭院名称" align="center">
                            </el-table-column>
                            <el-table-column property="CJR" label="创建人" align="center">
                            </el-table-column>
                            <el-table-column property="CJSJ" label="创建时间" align="center">
                            </el-table-column>
                        </el-table>

                    </el-col>
                    <el-col :span="20" v-if="ldFlag==true">
                        <div class="grid-content bg-purple-light" style="display: flex;align-items: center;width: 100%;height: 40px;background:#f5f7fa">
                            <span style="color: #606266;;margin-left:10px">楼栋范围</span>
                        </div>
                        <el-table :data="LDData" style="width: 100%" border>
                            <el-table-column property="TYMC" label="庭院名称" align="center">
                            </el-table-column>
                            <el-table-column property="BH" label="楼栋编号" align="center">
                            </el-table-column>
                            <el-table-column property="CJR" label="创建人" align="center">
                            </el-table-column>
                            <el-table-column property="CJSJ" label="创建时间" align="center">
                            </el-table-column>
                        </el-table>
                    </el-col>
                    <el-col :span="20" v-if="dxsFlag==true">
                        <div class="grid-content bg-purple-light" style="display: flex;align-items: center;width: 100%;height: 40px;background:#f5f7fa">
                            <span style="color: #606266;;margin-left:10px">地下室范围</span>
                        </div>
                        <el-table :data="DXSData" style="width: 100%" border>
                            <el-table-column property="BH" label="地下室编号" align="center">
                            </el-table-column>
                            <el-table-column property="MC" label="地下室名称" align="center">
                            </el-table-column>
                            <el-table-column property="CJR" label="创建人" align="center">
                            </el-table-column>
                            <el-table-column property="CJSJ" label="创建时间" align="center">
                            </el-table-column>
                        </el-table>
                    </el-col>
                    <el-col :span="20" v-if="bfFlag==true">
                        <div class="grid-content bg-purple-light" style="display: flex;align-items: center;width: 100%;height: 40px;background:#f5f7fa">
                            <span style="color: #606266;;margin-left:10px">泵房范围</span>
                        </div>
                        <el-table :data="BFData" style="width: 100%" border>
                            <el-table-column property="BH" label="泵房编号" align="center">
                            </el-table-column>
                            <el-table-column property="MC" label="泵房名称" align="center">
                            </el-table-column>
                            <el-table-column property="CJR" label="创建人" align="center">
                            </el-table-column>
                            <el-table-column property="CJSJ" label="创建时间" align="center">
                            </el-table-column>
                        </el-table>
                    </el-col>
                </el-row>
            </el-col>
        </el-row>
    </section>
</template>

<script>
    import {queryGcxdByXmid} from '../../api/sgjg'
    export default {
        name: "sgxdXmfw",
        data() {
            return {
                data: [{
                    label: '项目范围配置',
                    children: [{
                        id:"TY",
                        label: '庭院范围'
                    },{
                        id:"LD",
                        label: '楼栋范围'
                    },{
                        id:"DXS",
                        label: '地下室范围'
                    },{
                        id:"BF",
                        label: '泵房范围'
                    }]
                }],
                TYData:[],
                LDData:[],
                DXSData:[],
                BFData:[],
                tyFlag:true,
                ldFlag:false,
                dxsFlag:false,
                bfFlag:false,
                sxFlag:false,
                defaultProps: {
                    children: 'children',
                    label: 'label'
                },
                xmid:this.$route.query.xmid
            }
        },
        methods: {
            handleNodeClick(data) {
                if(data.id!=null){
                    this.tyFlag=false;
                    this.ldFlag=false;
                    this.dxsFlag=false;
                    this.bfFlag=false;
                    switch (data.id) {
                        case "TY":
                            this.tyFlag=true;
                            break;
                        case "LD":
                            this.ldFlag=true;
                            break;
                        case "DXS":
                            this.dxsFlag=true;
                            break;
                        case "BF":
                            this.bfFlag=true;
                            break;
                    }
                }
            },
            initTable_vue: function(){
                let params={
                    xmid:this.xmid,
                    type:"1"
                }
                queryGcxdByXmid(params).then((res)=>{
                    if(res.data!=null){
                        this.TYData = res.data.tyList;
                        this.BFData = res.data.bfList;
                        this.DXSData = res.data.dxsList;
                        this.LDData = res.data.ldList;
                    }
                })
            },
            mounted() {
                this.initTable_vue();
            }
        }
    }
</script>

<style scoped>

</style>