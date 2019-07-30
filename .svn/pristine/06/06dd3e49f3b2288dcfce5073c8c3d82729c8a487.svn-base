<template>
    <section>
        <el-row>
            <el-col :span="24">
                <hhldsx :xmid="this.xmid" :activityid="this.activityId"></hhldsx>
                <!--项目基本信息模板-->
                <GWJBXX></GWJBXX>
                <!--发起造价洽谈需填写内容-->
                <el-card class="box-card" style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">资料分类及下载
                        </div>
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
                                        <td><label title="查看">
                                            <i class="iconfont icon-tiaocha" @click="getSgZl()"
                                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label></td>
                                        <!-- <td @click="getSgZl()" style="color: #00a65a">详情</td>-->
                                    </tr>
                                    <tr style="text-align: center">
                                        <td>设计成果</td>
                                        <td>{{sjcg}}</td>
                                        <td><label title="查看">
                                            <i class="iconfont icon-tiaocha" @click="getSjcg()"
                                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label></td>
                                        <!--   <td @click="getSjcg()" style="color: #00a65a">详情</td>-->
                                    </tr>
                                    <tr style="text-align: center">
                                        <td>客户提供资料</td>
                                        <td>{{khzl}}</td>
                                        <td><label title="查看">
                                            <i class="iconfont icon-tiaocha" @click="getKhtgzl()"
                                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label></td>
                                        <!--<td @click="getKhtgzl()" style="color: #00a65a">详情</td>-->
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
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">选择参与部门
                        </div>
                    </div>
                    <el-form :inline="true" class="demo-form-inline">
                        <el-form-item label="预计验收时间">
                            <el-date-picker type="datetime"
                                            format="yyyy-MM-dd HH:mm"
                                            value-format="yyyy-MM-dd HH:mm"
                                            placeholder="" v-model="yjyssj">
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item label="项目类型">
                            <el-select v-model="xmlx" placeholder="请选择">
                                <el-option label="自建" value="1"></el-option>
                                <el-option label="代建" value="2"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-form>
                    <div class="text item" id="row">
                        <el-row>
                            <el-col :span="4">参与部门</el-col>
                            <el-col :span="4">是否短信通知</el-col>
                            <el-col :span="4">人员</el-col>
                            <el-col :span="4">联系电话</el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="4"><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;建设单位</span></el-col>
                            <el-col :span="4">
                                <el-switch v-model="check_value.jsdw_value" active-color="#13ce66"
                                           inactive-color="#ff4949"></el-switch>
                            </el-col>
                            <el-col :span="4"><span>{{bm_value.lxr}}</span></el-col>
                            <el-col :span="4"><span>{{bm_value.lxrsjh}}</span></el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="4">
                                <el-checkbox v-model="bm_value.c_jldw">监理单位</el-checkbox>
                            </el-col>
                            <el-col :span="4">
                                <el-switch v-model="check_value.jldw_value" active-color="#13ce66"
                                           inactive-color="#ff4949"></el-switch>
                            </el-col>
                            <el-col :span="4"><span>{{bm_value.jldwlxr}}</span></el-col>
                            <el-col :span="4"><span>{{bm_value.jldwlxrsjh}}</span></el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="4">
                                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{bm_value.gss}}</span>
                            </el-col>
                            <el-col :span="4">
                                <el-switch v-model="check_value.gss_value" active-color="#13ce66"
                                           inactive-color="#ff4949"></el-switch>
                            </el-col>
                            <el-col :span="4"><span>{{bm_value.gsslxr}}</span></el-col>
                            <el-col :span="4"><span>{{bm_value.gsslxrsjh}}</span></el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="4">
                                <el-checkbox v-model="bm_value.c_xxgh">信息规划部</el-checkbox>
                            </el-col>
                            <el-col :span="4">
                                <el-switch v-model="check_value.xxghb_value" active-color="#13ce66"
                                           inactive-color="#ff4949"></el-switch>
                            </el-col>
                            <el-col :span="4"><span>{{bm_value.xxghlxr}}</span></el-col>
                            <el-col :span="4"><span>{{bm_value.xxghlxrsjh}}</span></el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="4">
                                <el-checkbox v-model="bm_value.c_sgb">施工部门</el-checkbox>
                            </el-col>
                            <el-col :span="4">
                                <el-switch v-model="check_value.sgb_value" active-color="#13ce66"
                                           inactive-color="#ff4949"></el-switch>
                            </el-col>
                            <el-col :span="4"><span>{{bm_value.sgbmlxr}}</span></el-col>
                            <el-col :span="4"><span>{{bm_value.sgbmlxrsjh}}</span></el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="4">
                                <el-checkbox v-model="bm_value.c_gcgl">工程管理部</el-checkbox>
                            </el-col>
                            <el-col :span="4">
                                <el-switch v-model="check_value.gcgl_value" active-color="#13ce66"
                                           inactive-color="#ff4949"></el-switch>
                            </el-col>
                            <el-col :span="4"><span>{{bm_value.gcgllxr}}</span></el-col>
                            <el-col :span="4"><span>{{bm_value.gcgllxrsjh}}</span></el-col>
                        </el-row>

                    </div>
                </el-card>

                <el-row style="text-align: center">
                    <router-link :to="'/Backlog'">
                        <el-button size="mini" type="info" @click="cancel()">
                            <i class="iconfont icon-quxiao"></i>取消
                        </el-button>
                    </router-link>
                    <router-link :to="tabUrl">
                        <el-button size="mini" type="primary" @click="fqys_vue()" :loading="sumbitLoading">
                            <i class="iconfont icon-queren"></i>发起验收
                        </el-button>
                    </router-link>
                </el-row>

                <!--客户提供资料-->
                <el-dialog :title="title" :visible.sync="selectZlfl_1" width="60%">
                    <el-tabs type="card">
                        <el-table :data="zlflform_1" v-loading="listLoading_1">
                            <el-table-column property="fjmc" label="附件名称" width="250"></el-table-column>
                            <!--     <el-table-column property="nameSter" label="附件类型" width="150"></el-table-column>-->
                            <el-table-column property="scsjStr" label="上传时间" width="200"></el-table-column>
                            <el-table-column label="操作" width="100">
                                <template slot-scope="scope">
                                    <label title="下载">
                                        <i class="iconfont icon-xiazai" @click="zlfl_download(scope.$index, scope.row)"
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
                            <!--   <el-table-column property="nameSter" label="附件类型" width="150"></el-table-column>-->
                            <el-table-column property="scsjStr" label="上传时间" width="200"></el-table-column>
                            <el-table-column label="操作" width="100">
                                <template slot-scope="scope">
                                    <label title="下载">
                                        <i class="iconfont icon-xiazai" @click="zlfl_download(scope.$index, scope.row)"
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
                                        <i class="iconfont icon-xiazai" @click="zlfl_download(scope.$index, scope.row)"
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
    import hhldsx from '@/views/template/hhldsx.vue';
    import util from '../../common/js/util'
    import {selectZlflsl, doFqys, getBmxx, download, getQianshou} from '../../api/zhys'

    import axios from 'axios';


    export default {

        data() {
            return {
                activityId: '80002',
                xmid: this.$route.query.xmid,//项目id
                sqdid: this.$route.query.sqdid,//
                lxrsjh: this.$route.query.lxrsjh,//
                lxr: this.$route.query.lxr,//
                bmbmQs: this.$route.query.bmbmQs,//
                sqlx: this.$route.query.sqlx,
                sqbh: this.$route.query.sqbh,
                taskId: this.$route.query.taskId,

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
                check_value: {
                    jsdw_value: true,
                    jldw_value: true,
                    gss_value: true,
                    xxghb_value: true,
                    sgb_value: true,
                    gcgl_value: true
                },
                bm_value: {
                    c_jsdw: true, c_jldw: '', c_gss: '', c_xxgh: '', c_sgb: '', c_gcgl: '',
                    //部门人员信息
                    lxr: '', lxrsjh: '', gss: '', gsslxr: '', gsslxrsjh: '', jldwlxr: '', jldwlxrsjh: '',
                    xxghlxr: '', xxghlxrsjh: '', sgbmlxr: '', sgbmlxrsjh: '', gcgllxr: '', gcgllxrsjh: '',
                    gssid: '', jldwid: '', xxghid: '', sgbmid: '', gcglid: ''
                },
                yjyssj: '', xmlx: '',
                sumbitLoading: false,
                tabUrl: '',
            }
        },
        mounted() {
            this.qianShou();
            this.getZilflxx();
            this.openZhysxx();
            this.getBmxx();
        },
        components: {
            'GWJBXX': GWJBXX,  //将别名demo 变成 组件 Demo
            'ZHYSFW': ZHYSFW,
            'hhldsx': hhldsx,
        },
        methods: {
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
            getBmxx() {//部门信息显示
                this.bm_value.lxr = this.lxr;
                this.bm_value.lxrsjh = this.lxrsjh;
                let params = Object.assign({xmid: this.xmid, bmbm_qs: this.bmbmQs});
                getBmxx(params).then((res) => {//gsslxr  jldwlxr  gcgllxr

                    this.bm_value.gss = res.qsmc;
                    this.bm_value.gsslxr = res.gsslxr;
                    this.bm_value.gsslxrsjh = res.gsslxrsjh;
                    this.bm_value.jldwlxr = res.jldwlxr;
                    this.bm_value.jldwlxrsjh = res.jldwlxrsjh;
                    this.bm_value.xxghlxr = res.xxghlxr;
                    this.bm_value.xxghlxrsjh = res.xxghlxrsjh;
                    this.bm_value.sgbmlxr = res.sgbmlxr;
                    this.bm_value.sgbmlxrsjh = res.sgbmlxrsjh;
                    this.bm_value.gcgllxr = res.gcgllxr;
                    this.bm_value.gcgllxrsjh = res.gcgllxrsjh;

                    this.bm_value.gssid = res.gssid;
                    this.bm_value.jldwid = res.jldwid;
                    this.bm_value.xxghid = res.xxghid;
                    this.bm_value.sgbmid = res.sgbmid;
                    this.bm_value.gcglid = res.gcglid;

                });
            },
            //发起验收
            fqys_vue() {
                let bm = this.bm_value;
                if (bm.c_jldw == '' && bm.c_xxgh == '' && bm.c_sgb == '' && bm.c_gcgl == '') {
                    this.$message({
                        message: '未选择参与部门！',
                        type: 'warning'
                    });
                    this.tabUrl = "/Xdzhysdlbsj?xmid=" + this.xmid + "&sqbh=" + this.sqbh + "&sqdid=" + this.sqdid
                        + "&sqlx=" + this.sqlx + "&lxr=" + this.lxr + "&lxrsjh=" + this.lxrsjh + "&bmbmQs=" + this.bmbmQs;
                    return;
                } else {
                    if (this.yjyssj == '') {
                        this.$message({
                            message: '请输入预计验收时间！',
                            type: 'warning'
                        });
                        this.tabUrl = "/Xdzhysdlbsj?xmid=" + this.xmid + "&sqbh=" + this.sqbh + "&sqdid=" + this.sqdid
                            + "&sqlx=" + this.sqlx + "&lxr=" + this.lxr + "&lxrsjh=" + this.lxrsjh + "&bmbmQs=" + this.bmbmQs;
                        return;
                    } else {
                        let params = Object.assign({
                            xmid: this.xmid, yjyssj: this.yjyssj, xmlx: this.xmlx,
                            type: '1', sqdid: this.sqdid
                        }, this.bm_value);
                        this.sumbitLoading = true;
                        this.tabUrl = '/Backlog';
                        this.$routerTab.close();
                        this.$routerTab.refresh('/Backlog');
                        doFqys(params).then((res) => {
                            this.sumbitLoading = false;
                            if (res.data == true) {
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });

                            } else {
                                this.$message.error('提交失败！');
                            }
                        });
                    }
                }
            },
            //取消
            cancel() {
                this.$routerTab.close();
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
                let params = Object.assign({
                    xmid: this.xmid,
                    sqlx: this.sqlx,
                    sqbh: this.sqbh,
                    page: this.page_1,
                    size: this.size_1
                });
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

                let params = Object.assign({
                    xmid: this.xmid,
                    sqlx: this.sqlx,
                    sqbh: this.sqbh,
                    page: this.page_2,
                    size: this.size_2
                });
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
                let params = Object.assign({
                    xmid: this.xmid,
                    sqlx: this.sqlx,
                    sqbh: this.sqbh,
                    page: this.page_3,
                    size: this.size_3
                });
                this.listLoading_3 = true;
                selectZlflsl(params).then((res) => {
                    this.title = "施工资料";
                    this.total_3 = res.data.sgtotal;
                    this.zlflform_3 = res.data.sglist;
                    this.listLoading_3 = false;
                    this.selectZlfl_3 = true;
                });
            },
            zlfl_download(index, row) {//下载
                let formData = new FormData();
                formData.append("path", row.fjlj);
                /* console.log(formData);
                 console.log(row.fjlj);*/
                /* let instance = axios.create({
                     baseURL: 'http://localhost:8080',
                     headers: {
                         'Content-Type': 'application/json; application/octet-stream'
                     },
                     responseType: 'blob',
                 });
                 instance.post('/SqzhysController/download', formData).then((res) => { // 处理返回的文件流*/
                download(formData).then((res) => {
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
            qianShou() {
                let params = {taskId: this.taskId};
                getQianshou(params).then((res) => {
                    /*  if (res.result == 'success') {
                          this.$message({
                              message: '退回成功',
                              type: 'success'
                          });
                      } else {
                          this.$message({
                              message: '退回异常',
                              type: 'error'
                          });
                      }*/
                });

            }


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

