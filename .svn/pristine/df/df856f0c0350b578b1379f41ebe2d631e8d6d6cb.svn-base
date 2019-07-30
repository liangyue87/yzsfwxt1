<template>
    <section>
        <el-row>
            <el-col :span="24">
                <div v-if="type == '1' || type == '2'">

                </div>
                <div v-else>
                    <!--红黄绿灯时间-->
                    <hhldsx :xmid="xmid" :activityid="activityId"></hhldsx>
                </div>
                <!--项目基本信息模板-->
                <XMJBXX></XMJBXX>
                <!--发起造价洽谈内容-->
                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">选择参与造价洽谈部门和人员</div>
                    </div>
                    <div class="text item" id="row">
                        <el-row>
                            <el-col :span="4">洽谈部门</el-col>
                            <el-col :span="4">是否短信通知</el-col>
                            <el-col :span="4">人员</el-col>
                            <el-col :span="4">联系电话</el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="4">设计部门</el-col>
                            <el-col :span="4"><el-switch v-model="check_value.sjy_value" active-color="#13ce66" inactive-color="#ff4949" disabled></el-switch></el-col>
                            <el-col :span="4"><span id="sjry"></span></el-col>
                            <el-col :span="4"><span id="sjrysjh"></span></el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="4">预算部门</el-col>
                            <el-col :span="4"><el-switch v-model="check_value.sgdw_value" active-color="#13ce66" inactive-color="#ff4949" disabled></el-switch></el-col>
                            <el-col :span="4"><span id="ysry"></span></el-col>
                            <el-col :span="4"><span id="ysrysjh"></span></el-col>
                        </el-row>

                        <el-row>
                            <el-col :span="1">
                                备注：
                            </el-col>
                            <el-col :span="11">
                                <el-input v-model="bz" size="small" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="12">
                                洽谈时间：<el-date-picker v-model="qtsj" size="small"
                                                     type="datetime" placeholder="选择日期时间"
                                                     format="yyyy-MM-dd HH:mm"
                                                     value-format="yyyy-MM-dd HH:mm" :readonly="true">
                            </el-date-picker>
                            </el-col>
                        </el-row>
                    </div>
                </el-card>
                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">反馈意见</div>
                    </div>
                    <div id="row1">
                        <el-row>
                            <el-col :span="2" style="text-align: center;margin-top: 5px">
                                <span>洽谈结果&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="11">
                                <el-input
                                        size="small"
                                        placeholder="请输入内容"
                                        v-model="qtresult" :readonly="sfzd">
                                </el-input>
                            </el-col>
                            <el-col :span="1">&nbsp;</el-col>
                            <div v-if="type == '2'">

                            </div>
                            <div v-else>
                                <el-col :span="5">
                                    <el-upload
                                            class="upload-demo"
                                            ref="upload"
                                            :show-file-list='false'
                                            :multiple=true
                                            action="#"
                                            :http-request="submitUpload"
                                            :on-preview="handlePreview"
                                            :on-change="handleChange"
                                            :on-remove="handleRemove"
                                            :file-list="fileList">
                                        <el-button slot="trigger" size="mini" type="success" id="chooseFile">
                                            <i class="iconfont icon-shangchuan_f"></i>选取文件
                                        </el-button>
                                    </el-upload>
                                </el-col>
                            </div>
                        </el-row>
                        <el-table :data="gridData" :stripe="true" height="300" style="padding-bottom: 10px">
                            <el-table-column property="FJMC" label="附件名称" align="center"></el-table-column>
                            <el-table-column property="CJSJ" label="上传时间" align="center"></el-table-column>
                            <el-table-column property="USERNAME" label="上传人" align="center"></el-table-column>
                            <el-table-column property="" label="操作" align="center">
                                <template slot-scope="scope">
                                    <el-row>
                                        <el-col :span="24">
                                            <div v-if="type == '2'">
                                                <label title="下载">
                                                    <i class="iconfont icon-xiazai"
                                                       @click="downloadFile_vue(scope.$index, scope.row)"
                                                       style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                                </label>
                                            </div>
                                            <div v-else>
                                                <label title="下载">
                                                    <i class="iconfont icon-xiazai"
                                                       @click="downloadFile_vue(scope.$index, scope.row)"
                                                       style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                                </label>
                                                <label title="删除">
                                                    <i class="iconfont delete"
                                                       @click="deleteFj_vue(scope.$index, scope.row)"
                                                       style="cursor:pointer;padding: 2px;color: #f78989"></i>
                                                </label>
                                            </div>
                                        </el-col>
                                    </el-row>
                                </template>
                            </el-table-column>
                        </el-table>
                    </div>
                </el-card>
                    <el-row style="text-align: center;margin-top: 25px;">
                        <el-col :span="24" style="text-align: center">
                            <div v-if="type == '1'">
                                <router-link :to="'/YSZjqtList'">
                                    <el-button size="mini" type="info" @click="toXckcrwcj">
                                        <i class="iconfont icon-quxiao"></i>取消</el-button>
                                </router-link>
                                <el-button size="mini" type="primary" @click="goxmqj()" >
                                    <i class="iconfont icon-xiangmuquanjing_"></i>项目全景
                                </el-button>
                            </div>
                            <div v-else-if="type == '2'">

                            </div>
                            <div v-else>
                                <router-link :to="'/Backlog'">
                                    <el-button size="mini" type="info" @click="toXckcrwcj">
                                        <i class="iconfont icon-quxiao"></i>取消</el-button>
                                </router-link>
                                <router-link :to="tabUrl">
                                    <el-button size="mini" type="primary" :loading="submitLoading" @click="saveXckcTask">
                                        <i class="iconfont icon-queren"></i>提交</el-button>
                                </router-link>
                                <el-button size="mini" type="primary" @click="goxmqj()" >
                                    <i class="iconfont icon-xiangmuquanjing_"></i>项目全景
                                </el-button>
                            </div>
                        </el-col>
                    </el-row>
                </el-col>
            </el-row>

        </section>
    </template>

    <script>
        import axios from 'axios'
        import XMJBXX from '@/views/template/xmjbxx.vue';
        import hhldsx from '@/views/template/hhldsx.vue';
        import util from '../../common/js/util'
        import {saveYsZjqtFk,queryZjqtzbById,importFile,queryZllist,deleteFjByid,download,updateCheckTask} from '../../api/YsZjqt'
        export default {
            name: "YSZjqt",
            data(){
                return{
                    tabUrl:'',
                    gridData:[],
                    zllistLoading:false,
                    submitLoading:false,
                    qtsj : '',//预约勘查时间
                    fqr: '',
                    bz: '',
                    xmid:this.$route.query.xmid,
                    refid:this.$route.query.refid,
                    activityId:this.$route.query.activityId,
                    taskId:this.$route.query.taskId,
                    type:this.$route.query.type,
                    qtjg:this.$route.query.qtjg,
                    check_value :{
                        sjy_value: true,
                        sgdw_value: true
                    },
                    qtresult: '',//洽谈结果
                    sfzd:false,//洽谈结果是否只读
                    fileList:[],//上传文件集合
                }
            },
            methods: {
                //跳转至项目全景
                goxmqj(){
                    this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid  }})
                },
                deleteFj_vue(index,row){
                    let para = {fjId:row.ID};
                    deleteFjByid(para).then(res=>{
                        this.queryZllist();
                    });
                },
                downloadFile_vue(index,row){
                    let formData = new FormData();
                    formData.append("path",row.FJLJ);
                    download(formData).then((res)=>{
                        const content = res.data;
                        const blob = new Blob([content]);
                        const fileName = row.FJMC;
                        if ('download' in document.createElement('a')) { // 非IE下载
                            const elink = document.createElement('a');
                            elink.download = fileName;
                            elink.style.display = 'none';
                            elink.href = URL.createObjectURL(blob);
                            document.body.appendChild(elink);
                            elink.click();
                            URL.revokeObjectURL(elink.href); // 释放URL 对象
                            document.body.removeChild(elink);
                        } else { // IE10+下载
                            navigator.msSaveBlob(blob, fileName);
                        }
                    });
                },
                queryZllist(){
                    let para = {zbid:this.refid};
                    queryZllist(para).then(res=>{
                        this.gridData = res.data.fjList;
                    });
                    this.tabUrl = "/YSZjqtFk?xmid="+this.xmid+"&refid="+this.refid+"&activityId="+this.activityId
                        +"&taskId="+this.taskId;
                },
                queryinit(){
                  let params = {
                      zbid:this.refid
                  };
                  queryZjqtzbById(params).then((res)=>{
                      this.qtsj = res.data.list[0].QTSJ;
                      this.bz = res.data.list[0].BZ;
                      let cbList = res.data.cbList;
                      if(cbList[0].CYBM == '设计部门'){
                          document.getElementById("sjry").innerText = cbList[0].USERNAME ? cbList[0].USERNAME : '暂无人员';
                          document.getElementById("sjrysjh").innerText = cbList[0].SJHM ? cbList[0].SJHM :'';
                          if(cbList[0].SFFSDX == 1){
                              this.check_value.sjy_value = true;
                          }else{
                              this.check_value.sjy_value = false;
                          }
                          document.getElementById("ysry").innerText = cbList[1].USERNAME ? cbList[1].USERNAME : '暂无人员';
                          document.getElementById("ysrysjh").innerText = cbList[1].SJHM ? cbList[1].SJHM :'';
                          if(cbList[1].SFFSDX == 1){
                              this.check_value.sgdw_value = true;
                          }else{
                              this.check_value.sgdw_value = false;
                          }
                      }else if(cbList[0].CYBM == '预算部门'){
                          document.getElementById("sjry").innerText = cbList[1].USERNAME ? cbList[1].USERNAME : '暂无人员';
                          document.getElementById("sjrysjh").innerText = cbList[1].SJHM ? cbList[1].SJHM :'';
                          if(cbList[1].SFFSDX == 1){
                              this.check_value.sjy_value = true;
                          }else{
                              this.check_value.sjy_value = false;
                          }
                          document.getElementById("ysry").innerText = cbList[0].USERNAME ? cbList[0].USERNAME : '暂无人员';
                          document.getElementById("ysrysjh").innerText = cbList[0].SJHM ? cbList[0].SJHM :'';
                          if(cbList[0].SFFSDX == 1){
                              this.check_value.sgdw_value = true;
                          }else{
                              this.check_value.sgdw_value = false;
                          }
                      }
                  });
                },
                toXckcrwcj(){//跳转页面造价洽谈反馈列表
                    this.$routerTab.close();
                    this.tabUrl = "/Backlog";
                    this.$routerTab.refresh('/Backlog');
                    this.$routerTab.refresh('/MyWork');
                    this.$routerTab.refresh('/YSZjqtList');
                },
                saveXckcTask(){//洽谈反馈提交
                    this.$confirm('您正在递交工作结果，一旦递交确认后将不能打回或再次修改，你确定要递交吗？', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        if(this.qtresult == ''){
                            this.$message({
                                message: '请输入洽谈结果！',
                                type: 'warning'
                            });
                        }else{
                            let params = {
                                qtresult: this.qtresult,
                                refid:this.refid
                            };
                            this.submitLoading = true;
                            saveYsZjqtFk(params).then((res) => {
                                if(res){
                                    this.$message({
                                        message: '提交成功',
                                        type: 'success'
                                    });
                                    this.submitLoading = false;
                                }else {
                                    this.$message.error('提交失败！');
                                }
                            });
                            this.toXckcrwcj();
                        }
                    }).catch(() => {

                    });
                },
                handleRemove(file, fileList) {
                },
                handlePreview(file) {
                },
                handleChange(file,fileList){
                    this.fileList = fileList;
                },
                submitUpload(event) {
                    let fileList = this.fileList;
                    if (fileList !=null && fileList.length>0){
                        // for (let i=0;i<fileList.length;i++){
                            let file = fileList[fileList.length-1];
                            let formData = new FormData();
                            formData.append("operation",this.refid);
                            formData.append("multipartFiles",file.raw);
                            formData.append("filename",file.name);
                            formData.append("xmid",this.xmid);
                            importFile(formData).then((res) => {
                                let msg = res.msg;
                                if(msg == '上传成功'){
                                    this.$message({
                                        message: '上传成功',
                                        type: 'success'
                                    });
                                    this.fileList=[];
                                    this.queryZllist();
                                }else{
                                    this.$message.error('上传失败！');
                                }
                            });
                        // }
                    }
                },
                //流程改变签收状态
                changeQszt(){
                    if(this.type == '1'){
                        this.sfzd = true;
                        this.qtresult = this.qtjg;
                    }
                    if(this.taskId != '' && this.taskId != undefined){
                        let para = {taskId: this.taskId};
                        updateCheckTask(para).then(res=>{

                        });
                    }
                },
            },
            mounted() {
                this.queryinit();
                this.queryZllist();
                this.changeQszt();
            },
            components: {
                'hhldsx': hhldsx,
                'XMJBXX': XMJBXX  //将别名demo 变成 组件 Demo
            }
        }
    </script>

    <style scoped>

    </style>
