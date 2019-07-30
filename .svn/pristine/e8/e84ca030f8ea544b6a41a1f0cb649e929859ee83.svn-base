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
                        <el-row>
                            <el-col :span="14">
                                <table class="border-table">
                                    <tr style="text-align: center">
                                        <td>资料类型</td>
                                        <td>资料数量</td>
                                        <td>操作</td>
                                    </tr>
                                    <tr style="text-align: center">
                                        <td>施工资料</td>
                                        <td>{{sgzl}}</td>
                                        <td @click="getSgZl()" style="color: #00a65a">详情</td>
                                    </tr>
                                    <tr style="text-align: center">
                                        <td>设计成果</td>
                                        <td>{{sjcg}}</td>
                                        <td @click="getSjcg()" style="color: #00a65a">详情</td>
                                    </tr>
                                    <tr style="text-align: center">
                                        <td>客户提供资料</td>
                                        <td>{{khzl}}</td>
                                        <td @click="getKhtgzl()" style="color: #00a65a">详情</td>
                                    </tr>
                                </table>
                            </el-col>
                        </el-row>
                    </div>
                </el-card>

                <!--综合验收范围-->
                <ZHYSFW ref="zhysxx"></ZHYSFW>

                <el-card class="box-card" style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">指派人员</div>
                    </div>
                    <div class="text item">
                        <el-row>
                            <el-form label-width="120px" :inline="true">
                                <el-radio-group v-model="resource" style="width: 100%;">
                                    <el-col :span="6" v-model="data" v-for="(item,index) in data" class="col" :key="index">
                                        <el-form-item style="width: 45%">
                                            <el-radio v-model="radio" :label="item.USERID">{{item.USERNAME}}（在建项目{{item.NUM}}个）</el-radio>
                                        </el-form-item>
                                    </el-col>
                                </el-radio-group>
                            </el-form>
                            <el-form>
                                <el-form-item>
                                    <el-col :span="12">
                                        <el-col :span="6">
                                            <router-link :to="'/Backlog'">
                                                <el-button size="mini" type="info" @click="toReturn">
                                                    <i class="iconfont icon-quxiao"></i>取消</el-button>
                                            </router-link>
                                        </el-col>
                                        <el-col :span="6">
                                            <router-link :to="taburl">
                                                <el-button size="mini" type="success" :loading="zpLoading" @click="fqys_vue">
                                                    <i class="iconfont icon-shenhetongguorenyuan"></i>指派</el-button>
                                            </router-link>
                                        </el-col>
                                    </el-col>
                                </el-form-item>
                            </el-form>
                        </el-row>
                    </div>
                </el-card>

                <!--客户提供资料-->
                <el-dialog :title="title" :visible.sync="selectZlfl_1" width="60%">
                    <el-table :data="zlflform_1" v-loading="listLoading_1">
                        <el-table-column property="fjmc" label="附件名称" width="250"></el-table-column>
                        <el-table-column property="nameSter" label="附件类型" width="150"></el-table-column>
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
                    <el-pagination layout="total, prev, pager, next" @current-change="handleCurrentChange_1"
                                   :page-size="this.size_1" :total="this.total_1" style="float:right;">
                    </el-pagination>
                </el-dialog>
                <!--设计成果-->
                <el-dialog :title="title" :visible.sync="selectZlfl_2" width="60%">
                    <el-table :data="zlflform_2" v-loading="listLoading_2">
                        <el-table-column property="fjmc" label="附件名称" width="250"></el-table-column>
                        <el-table-column property="nameSter" label="附件类型" width="150"></el-table-column>
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
                    <el-pagination layout="total, prev, pager, next" @current-change="handleCurrentChange_2"
                                   :page-size="this.size_2" :total="this.total_2" style="float:right;">
                    </el-pagination>
                </el-dialog>
                <!--施工资料-->
                <el-dialog :title="title" :visible.sync="selectZlfl_3" width="60%">
                    <el-table :data="zlflform_3" v-loading="listLoading_3">
                        <el-table-column property="fjmc" label="附件名称" width="250"></el-table-column>
                        <el-table-column property="nameSter" label="附件类型" width="150"></el-table-column>
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
                    <el-pagination layout="total, prev, pager, next" @current-change="handleCurrentChange_3"
                                   :page-size="this.size_3" :total="this.total_3" style="float:right;">
                    </el-pagination>
                </el-dialog>

            </el-col>
        </el-row>

    </section>
</template>

<script>
    import hhldsx from '@/views/template/hhldsx.vue';
    import GWJBXX from '@/views/template/gwjbxx.vue';
    import ZHYSFW from '@/views/template/Zhysfw.vue';
    import util from '../../common/js/util'
    import {selectZlflsl, doFqys,getZpry,download} from '../../api/zhys'
    import axios from 'axios';
    export default {

        data() {
            return {
                zpLoading:false,
                taburl:'',
                xmid:this.$route.query.xmid,//项目id
                sqdid:this.$route.query.sqdid,//申请单id
                activityId:this.$route.query.activityId,

                sqlx:this.$route.query.sqlx,//项目id
                sqbh:this.$route.query.sqbh,//项目id
                flag:this.$route.query.flag,//流程节点
                bmmc:this.$route.query.bmmc,//部门名称

                title: '',
                sgzl: "", sjcg: "", khzl: "",
                resource:'',
                radio : '',//选中的指派人员
                data :[],
                zlflform_1:[],
                zlflform_2:[],
                zlflform_3:[],
                total_1: 5,total_2: 5,total_3: 5,//表格数据总数量
                page_1: 1, page_2: 1, page_3: 1,//当前页码
                size_1: 5,size_2: 5,size_3: 5,//每页容量
                listLoading_1:false,
                listLoading_2:false,
                listLoading_3:false,
                selectZlfl_1: false,
                selectZlfl_2: false,
                selectZlfl_3: false,
            }
        },
        mounted() {
            this.getZilflxx();
            this.openZhysxx();
            this.getZpry();
        },
        components: {
            'hhldsx': hhldsx,
            'GWJBXX': GWJBXX,//基本信息模板
            'ZHYSFW':ZHYSFW//综合验收范围模板
        },
        methods: {
            //打开综合验收范围信息界面
            openZhysxx(){
                setTimeout(() => {
                    this.$refs.zhysxx.getZhyfwsxx();
                },0)
            },
            //发起指派
            fqys_vue() {
                if(this.resource == ''){
                    this.$message({
                        message: '请选择指派人员！',
                        type: 'warning'
                    });
                    this.taburl = "/zpzhys?xmid="+this.xmid+"&sqdid="+this.sqdid+"&flag="+this.flag
                        +"&bmmc="+this.bmmc+"&activityId="+this.activityId;
                }else{
                    let params = Object.assign({xmid:this.xmid,name: this.resource,type:'2',bmmc:this.bmmc,
                                            sqdid:this.sqdid});
                    this.zpLoading = true;
                    this.taburl = "/Backlog";
                    this.$routerTab.close();
                    this.$routerTab.refresh('/Backlog');
                    doFqys(params).then((res)=>{
                        if(res.data == true){
                            this.$message({
                                message: '指派成功',
                                type: 'success'
                            });
                            this.zpLoading = false;
                        }else{
                            this.$message.error('指派失败！');
                        }
                    });
                }
            },
            //获取指派人员名单
            getZpry(){
                let params = {flag:this.flag,bmmc:this.bmmc};
                getZpry(params).then((res) => {
                    this.data = res.data.zpry;
                });
            },
            //取消
            toReturn() {
                this.$routerTab.close();
            },
            //资料分类及下载 数量
            getZilflxx() {
                let params = Object.assign({xmid: this.xmid, sqlx: this.sqlx, sqbh: this.sqbh, page: this.page_1, size: this.size_1,
                    page: this.page_2, size: this.size_2,page: this.page_3, size: this.size_3});
                selectZlflsl(params).then((res) => {
                    if (res.data.total > 0) {//客户提供资料数量
                        this.khzl = res.data.total;
                    } else {
                        this.khzl = "- ";
                    }
                    if (res.data.sjcgtotal > 0) {//设计成果数量
                        this.sjcg = res.data.sjcgtotal;
                    } else {
                        this.sjcg = "- ";
                    }
                    if (res.data.sgtotal > 0) {//施工资料数量
                        this.sgzl = res.data.sgtotal;
                    } else {
                        this.sgzl = "- ";
                    }
                });
            },
            getKhtgzl(){//客户资料
                let params = Object.assign({xmid: this.xmid, sqlx: this.sqlx, sqbh: this.sqbh, page: this.page_1, size: this.size_1});
                this.listLoading_1 = true;
                selectZlflsl(params).then((res) => {
                    this.title = "客户提供资料";
                    this.total_1= res.data.total;
                    this.zlflform_1 = res.data.khtglist;
                    this.listLoading_1 = false;
                    this.selectZlfl_1 = true;
                });

            },
            handleCurrentChange_1(val) {
                this.page_1 = val;
                this.getKhtgzl();

            },
            handleCurrentChange_2(){
                this.page_2 = val;
                this.getSjcg();//设计成果
            },
            handleCurrentChange_3(){
                this.page_3 = val;
                this.getSgZl();//施工资料
            },
            getSjcg(){//设计成果
                let xmid = localStorage.getItem("xmid");
                let sqlx = localStorage.getItem("sqlx");
                let sqbh = localStorage.getItem("sqbh");
                let params = Object.assign({xmid: xmid, sqlx: sqlx, sqbh: sqbh, page: this.page_2, size: this.size_2});
                this.listLoading_2 = true;
                selectZlflsl(params).then((res) => {
                    console.log(res.data.sjcgtotal+"---------------")
                    this.title = "设计成果";
                    this.total_2= res.data.sjcgtotal;
                    this.zlflform_2 = res.data.sjcglist;
                    this.listLoading_2 = false;
                    this.selectZlfl_2 = true;
                });
            },
            getSgZl(){

                let params = Object.assign({xmid: this.xmid, sqlx: this.sqlx, sqbh: this.sqbh, page: this.page_3, size: this.size_3});
                this.listLoading_3 = true;
                selectZlflsl(params).then((res) => {
                    console.log(res.data.sgtotal+"---------------")
                    console.log(res.data.sglist+"---------------")
                    this.title = "施工资料";
                    this.total_3= res.data.sgtotal;
                    this.zlflform_3 = res.data.sglist;
                    this.listLoading_3 = false;
                    this.selectZlfl_3 = true;
                });
            },
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
                download(formData).then((res)=>{
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

