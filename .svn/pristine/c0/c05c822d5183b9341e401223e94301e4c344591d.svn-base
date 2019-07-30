<template>
    <section>
        <el-row>
            <el-col :span="24">
                <!--红黄绿灯时间-->
                <hhldsx :xmid="xmid" :activityid="activityId"></hhldsx>
                <!--项目基本信息模板-->
                <GWJBXX></GWJBXX>
                <!--发起造价洽谈需填写内容-->
                <el-card class="box-card" style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">资料分类及下载</div>
                    </div>
                    <div class="text item">
                        <el-table :data="zlrow"  border style="width: 100%;" row-key="id" >
                            <el-table-column prop="TYPE" label="资料类型" width="600" align="center">
                            </el-table-column>
                            <el-table-column prop="NUM" label="资料数量" width="300" align="center">
                            </el-table-column>
                            <el-table-column label="操作" align="center" width="200">
                                <template slot-scope="scope">
                                        <label title="详情">
                                            <i class="iconfont icon-xiangqing"  @click="toDetial_vue(scope.$index, scope.row)" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label>
                                </template>
                            </el-table-column>
                        </el-table>
                    </div>
                </el-card>

                <el-card class="box-card" style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">整改意见</div>
                    </div>
                    <el-row>
                        <el-form label-width="120px" :inline="true">
                            <el-col :span="24" v-model="data" v-for="(item,index) in data" :key="index">
                                <el-col :span="12">
                                    <el-form-item>
                                        <span slot="label">{{item.YSBM}}意见</span>
                                        <el-input size="small" style="width:400px;" :readonly="true" v-model="item.FKYJ"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="12">
                                    <el-form-item>
                                        <span slot="label">验收时间</span>
                                        <el-input size="small" :readonly="true" v-model="item.FKSJ"></el-input>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item>
                                    <span slot="label">项目经理意见</span>
                                    <el-input size="small" v-model="xmjlyj" placeholder="请输入意见" style="width:400px;"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="验收时间">
                                    <el-date-picker size="small" style="width: 187px;" type="date"
                                                    format="yyyy-MM-dd"
                                                    value-format="yyyy-MM-dd"
                                                    placeholder="请输入验收时间" v-model="xmjlyssj">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :span="24" style="text-align: center">
                                <router-link :to="'/Backlog'">
                                    <el-button size="mini" type="info" @click="toReturn">
                                        <i class="iconfont icon-quxiao"></i>取消</el-button>
                                </router-link>
                                <router-link :to="taburl">
                                    <el-button size="mini" type="info" :loading="zgLoading" @click="sgzg_vue">
                                        <i class="iconfont icon-fanhui"></i>施工整改</el-button>
                                </router-link>
                                <router-link :to="taburl">
                                    <el-button size="mini" type="success" :loading="subLoading" @click="fkysjl_vue">
                                        <i class="iconfont icon-queren"></i>通知施工单位</el-button>
                                </router-link>
                            </el-col>
                        </el-form>
                    </el-row>
                </el-card>

                <el-dialog :title="title" :visible.sync="selectZlfl_1" width="60%">
                    <el-tabs type="card">
                        <el-table :data="zlflform_1" v-loading="listLoading_1">
                            <el-table-column property="fjmc" label="附件名称" width="250"></el-table-column>
                            <!--   <el-table-column property="nameSter" label="附件类型" width="150"></el-table-column>-->
                            <el-table-column property="scsjStr" label="上传时间" width="200"></el-table-column>
                            <el-table-column label="操作" width="100">
                                <template slot-scope="scope">
                                    <label title="下载">
                                        <i class="iconfont icon-xiazai"  @click="zlfl_download(scope.$index, scope.row)"
                                           style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                    </label>
                                </template>
                            </el-table-column>
                        </el-table>
                        <el-col :span="24" class="toolbar">
                            <el-pagination background layout="total, prev, pager, next, sizes , jumper"
                                           @current-change="handleCurrentChange_1" @size-change="handleSizeChange_1"
                                           :page-sizes="[5, 10, 20, 50]"
                                           :page-size="this.size_1" :total="this.total_1" style="float:right;">
                            </el-pagination>
                        </el-col>
                    </el-tabs>
                </el-dialog>

            </el-col>
        </el-row>

    </section>
</template>

<script>
    import hhldsx from '@/views/template/hhldsx.vue';
    import GWJBXX from '@/views/template/gwjbxx.vue';
    import util from '../../common/js/util'
    import {doFqys,getFkyj,toDetial,Bmdownload} from '../../api/zhys'

    export default {

        data() {
            return {
                taburl:'',
                zgLoading:false,
                subLoading:false,
                xmid:this.$route.query.xmid,//项目id
                sqdid:this.$route.query.sqdid,//申请单id
                activityId:this.$route.query.activityId,

                data :[],
                bmmc :'',//部门名称
                xmjlyj:'',//项目经理意见
                xmjlyssj:'',//项目经理验收时间
                zlrow:[],//资料分类列表
                selectZlfl_1:false,
                listLoading_1: false,
                zlflform_1:[],
                title:'',
                page_1: 1,
                total_1: 5,
                size_1: 5,
            }
        },
        mounted() {
            this.getFkyj_vue();
        },
        components: {
            'hhldsx': hhldsx,
            'GWJBXX': GWJBXX,//基本信息模板
        },
        methods: {
            //反馈验收意见提交
            fkysjl_vue() {
                if(this.xmjlyj == '' || this.xmjlyssj == ''){
                    this.$message({
                        message: '请输入项目经理及验收时间！',
                        type: 'warning'
                    });
                    this.taburl = "fkzhys?xmid="+this.xmid+"&sqdid="+this.sqdid+"&activityId="+this.activityId;
                }else{
                    let params = Object.assign({xmid:this.xmid,xmjlyj: this.xmjlyj,xmjlyssj: this.xmjlyssj,
                        type:'4',sqdid:this.sqdid});
                    this.subLoading = true;
                    this.taburl='/Backlog';
                    this.$routerTab.close();
                    this.$routerTab.refresh('/Backlog');
                    doFqys(params).then((res)=>{
                        if(res.data == true){
                            this.$message({
                                message: '提交成功',
                                type: 'success'
                            });
                            this.subLoading = false;
                        }else{
                            this.$message.error('提交失败！');
                        }
                    });
                }
            },
            //施工整改
            sgzg_vue(){
                if(this.xmjlyj == '' || this.xmjlyssj == ''){
                    this.$message({
                        message: '请输入项目经理及验收时间！',
                        type: 'warning'
                    });
                    this.taburl = "fkzhys?xmid="+this.xmid+"&sqdid="+this.sqdid+"&activityId="+this.activityId;
                }else{
                    let params = Object.assign({xmid:this.xmid,xmjlyj: this.xmjlyj,xmjlyssj: this.xmjlyssj,
                        type:'5',sqdid:this.sqdid});
                    this.zgLoading = true;
                    this.taburl='/Backlog';
                    this.$routerTab.close();
                    this.$routerTab.refresh('/Backlog');
                    doFqys(params).then((res)=>{
                        if(res.data == true){
                            this.$message({
                                message: '提交成功',
                                type: 'success'
                            });
                            this.zgLoading = false;
                        }else{
                            this.$message.error('提交失败！');
                        }
                    });
                }
            },
            //获得反馈意见
            getFkyj_vue(){
                let params = {sqdid:this.sqdid,xmid:this.xmid};
                getFkyj(params).then((res) => {
                    this.data = res.data.fkyj;
                    this.zlrow = res.data.fkzl;
                });
            },
            toDetial_vue(index, row) {
               this.title=row.TYPE;
                this.selectZlfl_1=true;
                this.listLoading_1 = true;
                let params = Object.assign({xmid: this.xmid,zhyscbid:row.ZHYSCBID,page:this.page_1,size:this.size_1});
                toDetial(params).then((res) => {
                        this.total_1 = res.data.total;
                        this.zlflform_1 = res.data.list;
                    this.listLoading_1 = false;
                })
            },
            handleCurrentChange_1(val) {
                this.page_1 = val;
                this.getKhtgzl();
            },
            handleSizeChange_1(val) {
                this.size_1 = val;
                this.page_1 = 1;
                this.getKhtgzl();
            },
               //下载
            zlfl_download(index, row) {
                let formData = new FormData();
                formData.append("path", row.fjlj);
                /*  let instance = axios.create({
                      baseURL: 'http://localhost:8080',
                      headers: {
                          'Content-Type': 'application/json; application/octet-stream'
                      },
                      responseType: 'blob',
                  });
                  instance.post('/SqzhysController/download', formData).then((res) => { // 处理返回的文件流*/
                Bmdownload(formData).then((res)=>{
                    const content = res.data
                    const blob = new Blob([content])
                    const fileName = row.fjmc
                    if ('download' in document.createElement('a')) { // 非IE下载
                        const elink = document.createElement('a')
                        elink.download = fileName
                        elink.style.display = 'none'
                        elink.href = URL.createObjectURL(blob)
                        document.body.appendChild(elink)
                        elink.click()
                        URL.revokeObjectURL(elink.href) // 释放URL 对象
                        document.body.removeChild(elink)
                    } else { // IE10+下载
                        navigator.msSaveBlob(blob, fileName)
                    }
                });
            },
            //取消
            toReturn() {
                this.$routerTab.close();
            },
        },
    }
</script>
<style>
    .border-table {
        border-collapse: collapse;
        border: none;
        width: 100%;
    }

    .border-table td {
        border: solid rgb(235, 237, 240) 1px;
        width: 8%;
        height: 40px;
        text-align: center;
    }
</style>

