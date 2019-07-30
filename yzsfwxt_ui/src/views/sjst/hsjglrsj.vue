<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
    <section>
        <el-row>
            <el-col :span="24">
                <!--项目基本信息模板-->
                <GWJBXX></GWJBXX>
                <!--配合联合勘查的部门-->
                <el-card class="box-card" style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">会议记录
                        </div>

                    </div>
                    <div class="text item">
                        <el-form :model="kcfk" label-width="150px" ref="xmxxForm">

                            <el-form-item label="参与人：" style="width: 45%">
                                    <el-input type="textarea" autosize v-model="kcfk.username"></el-input>
                                <span style="color: gainsboro">录入多人时,请用","逗号隔开</span>

                            </el-form-item>
                            <el-form-item label="会议时间：" style="width: 45%">
                                <el-col :span="12">
                                    <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm"
                                                    format="yyyy-MM-dd HH:mm" placeholder="选择日期" v-model="kcfk.hysj"
                                                    style="width: 100%;"></el-date-picker>
                                </el-col>
                                <!--   <el-col class="line" :span="2" style="text-align: center">-</el-col>
                                   <el-col :span="10">
                                       <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm" placeholder="选择日期" v-model="kcfk.jssj" style="width: 100%;"></el-date-picker>
                                   </el-col>-->
                            </el-form-item>

                            <el-form-item label="会审结果：" style="width: 45%">
                                <el-input type="textarea" autosize v-model="kcfk.info"></el-input>
                            </el-form-item>

                            <el-form-item label="附件上传：" style="width: 45%">
                                <el-upload
                                        class="upload-demo"
                                        ref="upload"
                                        :multiple=true
                                        action="#"
                                        :http-request="submitUpload"
                                        :on-preview="handlePreview"
                                        :on-change="handleChange"
                                        :on-remove="handleRemove"
                                        :file-list="fileList"
                                        :limit="1"
                                        :on-exceed="handleExceed"
                                >
                                    <el-button size="mini"  v-show="sc" type="success"><i
                                            class="iconfont icon-shangchuan_f"></i>上传
                                    </el-button>
                                    <!--  <el-button slot="trigger" size="small" type="primary">上传</el-button>-->
                                    <!--  <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传</el-button>
                                      <div slot="tip" class="el-upload__tip"></div>-->
                                </el-upload>
                            </el-form-item>
                            <el-table :data="row" border highlight-current-row v-loading="listLoading"
                                      style="width: 100%;">
                                <el-table-column prop="fjmc" label="资料名称" width="200" align="center">
                                </el-table-column>
                                <!--     <el-table-column prop="fjlx" label="资料类型" width="100" align="center">
                                     </el-table-column>-->
                                <el-table-column prop="scsj_str" label="上传时间" width="200" align="center">
                                </el-table-column>
                                <el-table-column prop="username" label="上传人" width="150" align="center">
                                </el-table-column>
                                <el-table-column label="操作" width="150" align="center">
                                    <template slot-scope="scope">
                                        <div v-if="scope.row.fjhz!=null&&(scope.row.fjhz.toLowerCase()=='png'||scope.row.fjhz.toLowerCase()=='jpg')">
                                            <el-col :span="24">
                                                <label title="删除" v-show="shanchu"><i class="iconfont delete" @click="handleEdit(scope.$index, scope.row)" style="cursor:pointer;padding: 4px;color: #66b1ff"></i></label>
                                                <label title="预览" ><i class="iconfont icon-yulan" @click="downloadFile_vue(scope.$index, scope.row,1)" style="cursor:pointer;padding: 4px;color: #66b1ff"></i></label>
                                                <label title="下载" ><i @click="zlfl_download(scope.$index, scope.row,2)" class="iconfont icon-xiazai" style="cursor:pointer;padding: 4px;color: #66b1ff"></i></label>
                                            </el-col>
                                        </div>
                                        <div v-else>
                                            <el-col :span="24">
                                                <label title="删除"  v-show="shanchu"><i @click="handleEdit(scope.$index, scope.row)" class="iconfont delete" style="cursor:pointer;padding: 4px;color: #66b1ff"></i></label>
                                                <label ><i  style="cursor:pointer;padding: 14px;color: #66b1ff " ></i></label>
                                                <label title="下载" ><i @click="zlfl_download(scope.$index, scope.row,2)" class="iconfont icon-xiazai" style="cursor:pointer;padding: 4px;color: #66b1ff"></i></label>
                                            </el-col>
                                        </div>
                                      <!--  <label title="删除" v-show="shanchu" @click="handleEdit(scope.$index, scope.row)">
                                            <i class="iconfont delete"
                                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                                        <label title="下载" v-show="xiazai" @click="zlfl_download(scope.$index, scope.row)">
                                            <i class="iconfont icon-xiazai"
                                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>-->

                                    </template>
                                </el-table-column>
                            </el-table>
                         <!--   <el-pagination background layout="total, prev, pager, next, sizes , jumper"
                                           @current-change="handleCurrentChange" @size-change="handleSizeChange"
                                           :page-sizes="[5, 10, 20, 50]"
                                           :page-size="this.size" :total="this.total" style="float:right;">
                            </el-pagination>-->
                        </el-form>
                    </div>
                </el-card>
                <el-row style="text-align: center;margin-top: 25px;">

                   <router-link :to="tabUrl">
                        <el-button size="mini" type="info" v-show="quxiao" @click="toRwFklb" icon="iconfont icon-quxiao">取消</el-button>
                    </router-link>

                    <el-button size="mini"  v-show="baocun" type="primary" @click="submitKcfk"
                               :loading="sumbitLoading"><i
                            class="iconfont icon-save"></i>保存
                    </el-button>
                    <el-button size="mini" v-show="xmqj" type="primary" @click="goxmqj()" :loading="sumbitLoading"><i class="iconfont icon-xiangmuquanjing_"></i>项目全景</el-button>
                </el-row>
            </el-col>
        </el-row>
    </section>
</template>
<style lang="css">
    .row {
        margin-top: 0px;
    }

    .col {
        margin-top: 12px;
    }
</style>
<script>
    import axios from 'axios'
    import $ from 'jquery'
    import GWJBXX from '@/views/template/gwjbxx.vue';
    import util from '../../common/js/util'
    import { download} from '../../api/zhys'
    import {saveHsjglr, getFile, getHsjgLsit, deleteHszl, getHsnr,download_sjst} from '../../api/sjst';

    export default {
        data() {
            return {

                //  type : '3',//区所
                kcsj: '2019-10-10 15:00:00',//预约勘查时间

                kcfk: {
                    hysj: '',
                    info: '',
                    username:''
                },
                fileList: [],
                fileIds: [],//上传附件id集合
                row: [],
                total: 5,//表格数据总数量
                page: 1,//当前页码
                size: 5,//每页容量
                listLoading: '',
                xmid: this.$route.query.xmid,
                type: this.$route.query.type,
                sumbitLoading: false,

                users: '',
                tabUrl: '',
                shanchu:true,//删除按钮
                cyr: true,//参与人
                baocun: true,//保存
                sc: true,//上传
                quxiao:true,//取消
                xmqj:true,//项目全景



            }
        },
        methods: {

            //跳转至项目全景
            goxmqj(){
                this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid  }})

            },
            handleExceed(files, fileList) {
                this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
            },
            handleRemove(file, fileList) {
            },
            handlePreview(file) {
            },
            handleChange(file, fileList) {
                this.fileList = fileList;
            },
            beforeRemove(file, fileList) {
                return this.$confirm(`确定移除 ${file.name}？`);
            },
            toHsjglr() {
                this.$router.push({path: '/hsjglr', query: {}})
            },
            submitKcfk() {//提交

                var usercyr = '';
                if(this.kcfk.username==null || this.kcfk.username==""){
                    this.$message.error('参与人不能为空');
                    return;
                }
                if(this.kcfk.hysj==null || this.kcfk.hysj==""){
                    this.$message.error('会议时间不能为空');
                    return;
                }
                if(this.kcfk.info==null || this.kcfk.info==""){
                    this.$message.error('会议结果不能为空');
                    return;
                }
                let params = Object.assign({usercyr: usercyr, xmid: this.xmid}, this.kcfk);

                this.sumbitLoading = true;
                this.$routerTab.close();
                this.$routerTab.refresh('/Hsjglr');
                saveHsjglr(params).then((res) => {
                    this.sumbitLoading = false;
                    if (res.result == 'success') {
                        this.$message({
                            message: '保存成功',
                            type: 'success'
                        });
                        localStorage.clear();

                    } else {
                        this.$message({
                            message: '保存失败',
                            type: 'error'
                        });
                    }
                });
            },
            submitUpload() {
                let fileList = this.fileList;
                let operation = "会审结果录入附件";

                if (fileList != null && fileList.length > 0) {
                    for (let i = 0; i < fileList.length; i++) {
                        let file = fileList[i];
                        let formData = new FormData();
                        formData.append("operation", operation);
                        formData.append("xmid", this.xmid);
                        formData.append("multipartFiles", file.raw);
                        formData.append("filename", file.name);
                        //  formData.append("type","sjy");
                        /*   let instance = axios.create({
                               baseURL:'http://localhost:8080',
                               headers: {'Content-Type':'multipart/form-data'}
                           });
                           instance.post('/HsjglrController/importFile',formData).then((res) => {*/
                        getFile(formData).then((res) => {

                            let msg = res.data.msg;
                            this.fileIds = this.fileIds.concat(res.data.id);
                            if (msg == 'success') {
                                this.$message({
                                    message: '上传成功',
                                    type: 'success'
                                });
                                this.gethsjg();
                            } else {
                                this.$message.error('上传失败！');
                            }
                            this.fileList = [];
                        });
                    }
                } else {
                    this.$message.warning('未选择任何文件！');
                }
            },
            /* 跳转用户选择界面*/
         /*   XuanZe() {
                if (this.kcfk.hysj != null) {
                    localStorage.setItem("hysj", this.kcfk.hysj);
                }
                if (this.kcfk.info != null) {
                    localStorage.setItem("info", this.kcfk.info);
                }
                this.$router.push({path: '/Userxx', query: {xmid: this.xmid}})
            },*/
         /*   getuser() {
                var userobj = null;
                userobj = localStorage.getItem("userobj");
                if (userobj != null) {
                    var usercyr = new Array()
                    var obj = JSON.parse(userobj);
                    for (let i = 0; i < obj.length; i++) {
                        usercyr.push(obj[i]);
                    }
                     this.usercyr=usercyr;
                    this.username = usercyr;
                }
            },*/
            handleCurrentChange(val) {
                this.page = val;
                this.gethsjg();
            },
            handleSizeChange(val) {
                this.size = val;
                this.page = 1;
                this.gethsjg();
            },
            //资料查询
            gethsjg() {
                //  let xmid = localStorage.getItem("xmid");
                let params = Object.assign({xmid: this.xmid, page: this.page, size: this.size});
                this.listLoading = true;
                getHsjgLsit(params).then((res) => {
                    if (res.data != null && res.data != "undefined") {
                        if (res.data.total > 0 && res.data.list.length <= 0) {
                            this.page = this.page - 1
                            this.gethsjg();
                        }
                        this.total = res.data.total;
                        this.row = res.data.list;
                        this.listLoading = false;
                    }

                });
            },
            //回显保存内容
            getHsjgNr() {
                let params = Object.assign({xmid: this.xmid});
                getHsnr(params).then((res) => {
                    //  console.log(res.hysj);
                    if (res.hysj != null) {
                        this.kcfk.hysj = res.hysj;
                        this.kcfk.info = res.hyjg;
                        this.kcfk.username = res.hycyr;
                        this.sc = false;
                        this.baocun = false;
                        this.shanchu=false;
                        this.quxiao=false;
                        this.xmqj=false;

                    }
                });
            },

            toRwFklb() {
                this.tabUrl = '/Hsjglr';
                this.$routerTab.close();
                this.$routerTab.refresh(this.tabUrl);
            },
            //删除
            handleEdit: function (index, row) {
                this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //    let xmid = localStorage.getItem("xmid");
                    let params = Object.assign({id: row.id,});
                    deleteHszl(params).then((res) => {
                        if (res.data.msg == 'success') {
                            this.$message({
                                message: '删除成功',
                                type: 'success'
                            });
                            this.gethsjg();
                        } else {
                            this.$message.error('删除失败！');
                        }
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });

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
            //预览
            downloadFile_vue(index,row,flag){
                let formData = new FormData()
                formData.append("path",row.fjlj);
                // let params = {
                // 	path:row.fjlj
                // }
                // let instance = axios.create({
                // 	baseURL:'http://localhost:8080',
                // 	headers:{
                // 		'Content-Type': 'application/json; application/octet-stream'
                // 	},
                // 	responseType: 'blob',
                //
                // });
                download_sjst(formData).then((res)=>{
                    if(flag==1){
                        this.slide1=[];
                        let reader = new FileReader()
                        reader.onload = (e) => {
                            let img = new Image();
                            img.src =e.target.result;
                            let item = {};
                            item.msrc = item.src = e.target.result;
                            // this.Imgsrc = e.target.result;
                            item.h = img.height;
                            item.w = img.width;
                            this.slide1.push(item);
                            this.$preview.openList(0, this.slide1);
                            //this.msgFlag = true;
                            //this.prop.src = this.Imgsrc
                        }
                        reader.readAsDataURL(res.data)
                    }else{
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
                    }
                })
                // instance.post('/sjstController/download',formData).then((res) => { // 处理返回的文件流
                //
                //
                //
                // })
            },

        },
        mounted() {

            let id = this.xmid;
            if (typeof id === "undefined") {
                let xmid = localStorage.getItem("xmid");
                this.xmid = xmid;
            }
            if (this.kcfk.hysj == null || this.kcfk.hysj == "" || this.kcfk.hysj == "undefined") {
                let hysj = localStorage.getItem("hysj");
                this.kcfk.hysj = hysj;
            }
            if (this.kcfk.info == null || this.kcfk.info == "" || this.kcfk.info == "undefined") {
                let info = localStorage.getItem("info");
                this.kcfk.info = info;
            }
          /*  this.getuser();*/
            this.getHsjgNr();
            this.gethsjg();
        },
        components: {
            'GWJBXX': GWJBXX  //将别名demo 变成 组件 Demo
        },

    }
</script>