<template>
    <section>
        <el-row>
            <el-col :span="24">
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
                                        <td> <label title="查看">
                                            <i class="iconfont icon-tiaocha"  @click="getSgZl()" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label></td>
                                       <!-- <td @click="getSgZl()" style="color: #00a65a">详情</td>-->
                                    </tr>
                                    <tr style="text-align: center">
                                        <td>设计成果</td>
                                        <td>{{sjcg}}</td>
                                        <td> <label title="查看">
                                            <i class="iconfont icon-tiaocha"  @click="getSjcg()" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label></td>
                                       <!-- <td @click="getSjcg()" style="color: #00a65a">详情</td>-->
                                    </tr>
                                    <tr style="text-align: center">
                                        <td>客户提供资料</td>
                                        <td>{{khzl}}</td>
                                        <td> <label title="查看">
                                            <i class="iconfont icon-tiaocha"  @click="getKhtgzl()" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label></td>
                                       <!-- <td @click="getKhtgzl()" style="color: #00a65a">详情</td>-->
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
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">发起申请</div>
                    </div>
                    <div class="text item">
                        <el-row style="left: 15px;margin-top: 30px;">
                            <el-col :span="2">
                                备注：
                            </el-col>
                            <el-col :span="11">
                                <el-input v-model="bz"></el-input>
                            </el-col>
                        </el-row>
                        <el-row style="margin-top: 30px;">
                            <el-col :span="10">&nbsp;</el-col>
                            <el-col :span="12">
                                <router-link :to="tabUrl">
                                <el-button size="mini" type="info" @click="cancel()">
                                    <i class="iconfont icon-quxiao"></i>取消</el-button>
                                <el-button size="mini" type="primary" @click="tijiao()" :loading="sumbitLoading">
                                    <i class="iconfont icon-queren"></i>提交</el-button>
                                </router-link>
                            </el-col>
                        </el-row>
                    </div>
                </el-card>
                <!--客户提供资料-->
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
                <!--设计成果-->
                <el-dialog :title="title" :visible.sync="selectZlfl_2" width="60%">
                    <el-tabs type="card">
                        <el-table :data="zlflform_2" v-loading="listLoading_2">
                            <el-table-column property="fjmc" label="附件名称" width="250"></el-table-column>
                          <!--  <el-table-column property="nameSter" label="附件类型" width="150"></el-table-column>-->
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
                                           @current-change="handleCurrentChange_2" @size-change="handleSizeChange_2"
                                           :page-sizes="[5, 10, 20, 50]"
                                           :page-size="this.size_2" :total="this.total_2" style="float:right;">
                            </el-pagination>
                        </el-col>
                    </el-tabs>
                </el-dialog>
                <!--施工资料-->
                <el-dialog :title="title" :visible.sync="selectZlfl_3" width="60%">
                    <el-tabs type="card">
                        <el-table :data="zlflform_3" v-loading="listLoading_3">
                            <el-table-column property="fjmc" label="附件名称" width="250"></el-table-column>
                          <!--  <el-table-column property="nameSter" label="附件类型" width="150"></el-table-column>-->
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
                                           @current-change="handleCurrentChange_3" @size-change="handleSizeChange_3"
                                           :page-sizes="[5, 10, 20, 50]"
                                           :page-size="this.size_3" :total="this.total_3" style="float:right;">
                            </el-pagination>
                        </el-col>
                    </el-tabs>
                </el-dialog>
            </el-col>
            <el-col :span="24" style="text-align: center;margin-top: 35px;">
            </el-col>
        </el-row>

    </section>
</template>

<script>
    import GWJBXX from '@/views/template/gwjbxx.vue';
    import ZHYSFW from '@/views/template/Zhysfw.vue';
    import util from '../../common/js/util'
    import {selectZlflsl, saveZhysSqzb,download} from '../../api/zhys'
    import axios from 'axios';

    export default {

        data() {
            return {
                title: '',
                sgzl: '', sjcg: '', khzl: '',
                fjmc: '', fjlx_Yj: '', scsjStr: '',
                selectZlfl_1: false,
                selectZlfl_2: false,
                selectZlfl_3: false,
                //备注内容
                bz: '',
                zlflform_1: [],
                zlflform_2: [],
                zlflform_3: [],
                total_1: 5, total_2: 5, total_3: 5,//表格数据总数量
                page_1: 1, page_2: 1, page_3: 1,//当前页码
                size_1: 5, size_2: 5, size_3: 5,//每页容量
                listLoading_1: false,
                listLoading_2: false,
                listLoading_3: false,
                xmid: this.$route.query.xmid,
                sqlx: this.$route.query.sqlx,
                sqbh: this.$route.query.sqbh,
                sumbitLoading:false,
                tabUrl: '',

            }
        },
        mounted() {
            this.getZilflxx();
            this.openZhysxx();
        },
        components: {
            'GWJBXX': GWJBXX,  //将别名demo 变成 组件 Demo
            'ZHYSFW': ZHYSFW
        },
        methods: {
            cancel() {//取消
                this.tabUrl='/Sqzhys';
                this.$routerTab.close();
                this.$routerTab.refresh('/Sqzhys');
            },
            tiaozhuan(){
                this.$router.push({path: '/Backlog', query: {}});
            },
            //打开综合验收范围信息界面
            openZhysxx() {
                setTimeout(() => {
                    this.$refs.zhysxx.getZhyfwsxx();
                }, 0)
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
            handleCurrentChange_2() {
                this.page_2 = val;
                this.getSjcg();//设计成果
            },
            handleSizeChange_2(val) {
                this.size_2 = val;
                this.page_2 = 1;
                this.getSjcg();
            },
            handleCurrentChange_3(val) {
                this.page_3 = val;
                this.getSgZl();//施工资料
            },
            handleSizeChange_3(val) {
                this.size_3 = val;
                this.page_3 = 1;
                this.getSgZl();
            },
            getZilflxx(str) {//资料分类及下载 数量
                let params = Object.assign({
                    xmid: this.xmid, sqlx: this.sqlx, sqbh: this.sqbh, page: this.page_1, size: this.size_1,
                    page: this.page_2, size: this.size_2, page: this.page_3, size: this.size_3
                });
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
            getKhtgzl() {//客户资料
                let params = Object.assign({xmid: this.xmid, sqlx: this.sqlx, sqbh: this.sqbh, page: this.page_1, size: this.size_1});
                this.listLoading_1 = true;
                selectZlflsl(params).then((res) => {
                    this.title = "客户提供资料";
                    this.total_1 = res.data.total;
                    this.zlflform_1 = res.data.khtglist;
                    this.listLoading_1 = false;
                    this.selectZlfl_1 = true;
                });

            },
            getSjcg() {//设计成果

                let params = Object.assign({xmid: this.xmid, sqlx: this.sqlx, sqbh: this.sqbh, page: this.page_2, size: this.size_2});
                this.listLoading_2 = true;
                selectZlflsl(params).then((res) => {
                    this.title = "设计成果";
                    this.total_2 = res.data.sjcgtotal;
                    this.zlflform_2 = res.data.sjcglist;
                    this.listLoading_2 = false;
                    this.selectZlfl_2 = true;
                });
            },
            getSgZl() {
                let params = Object.assign({xmid: this.xmid, sqlx: this.sqlx, sqbh: this.sqbh, page: this.page_3, size: this.size_3});
                this.listLoading_3 = true;
                selectZlflsl(params).then((res) => {

                    this.title = "施工资料";
                    this.total_3 = res.data.sgtotal;
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
            tijiao() {  //发起申请
                let params = Object.assign({xmid: this.xmid, bz: this.bz});
                this.sumbitLoading=true;
                this.tabUrl='/Sqzhys';
                this.$routerTab.close();
                this.$routerTab.refresh('/Sqzhys');
                saveZhysSqzb(params).then((res) => {
                    this.sumbitLoading=false;
                    if (res.result == 'success') {
                        this.$message({
                            message: '提交成功',
                            type: 'success'
                        });
                        localStorage.clear();

                    } else {
                        this.$message.error('提交失败！');
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

