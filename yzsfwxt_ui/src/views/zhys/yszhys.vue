<template>
    <section>
        <el-row>
            <el-col :span="24">
                <!--红黄绿灯时间-->
                <hhldsx :xmid="xmid" :activityid="activityId"></hhldsx>
                <!--项目基本信息模板-->
                <GWJBXX></GWJBXX>

                <el-card class="box-card" style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">{{bmmc}}意见</div>
                    </div>
                    <div class="text item">
                        <el-form :inline="true" class="demo-form-inline">
                            <el-col :span="12">
                                <el-form-item>
                                    <span slot="label">{{bmmc}}意见</span>
                                    <el-input size="small" v-model="bmyj" placeholder="请输入意见" style="width:400px;"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="验收时间">
                                    <el-date-picker size="small" type="date"
                                                    format="yyyy-MM-dd"
                                                    value-format="yyyy-MM-dd"
                                                    placeholder="请输入验收时间" v-model="yssj">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :span="24">
                                <el-form-item>
                                    <span slot="label">{{bmmc}}资料</span>
                                    <el-upload
                                            class="upload-demo"
                                            ref="upload"
                                            action="#"
                                            :show-file-list='false'
                                            :multiple=true
                                            :on-preview="handlePreview"
                                            :http-request="submitUpload"
                                            :on-remove="handleRemove"
                                            :on-change="handleChange"
                                            :file-list="fileList"
                                            :limit="1"
                                            :on-exceed="handleExceed">
                                        <el-button slot="trigger" size="mini" type="primary">
                                            <i class="iconfont icon-shangchuan"></i>上传</el-button>
                                    </el-upload>
                                    <el-table :data="fjData" style="width: 100%">
                                        <el-table-column property="fjmc" label="资料名" width="280"></el-table-column>
                                        <el-table-column property="fjhz" label="资料类型" width="180"></el-table-column>
                                        <el-table-column property="scsj1" label="上传时间" width="200"></el-table-column>
                                        <el-table-column property="username" label="上传人" width="200"></el-table-column>
                                        <el-table-column label="操作" width="200">
                                            <template slot-scope="scope">
                                                <div v-if="scope.row.fjhz=='png'||scope.row.fjhz=='jpg'">
                                                    <label title="查看" @click="downloadFile_vue(scope.$index, scope.row,1)">
                                                        <i class="iconfont icon-yulan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>

                                                    <label title="删除" @click="deleteFj(scope.$index, scope.row)">
                                                        <i class="iconfont delete" style="cursor:pointer;padding: 2px;color: #f78989"></i></label>
                                                </div>
                                                <div v-else>
                                                    <label title="下载" @click="downloadFile_vue(scope.$index, scope.row,2)">
                                                        <i class="iconfont icon-xiazai" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>

                                                    <label title="删除" @click="deleteFj(scope.$index, scope.row)">
                                                        <i class="iconfont delete" style="cursor:pointer;padding: 2px;color: #f78989"></i></label>
                                                </div>

                                            </template>
                                        </el-table-column>
                                    </el-table>
                                </el-form-item>
                            </el-col>
                        </el-form>
                    </div>
                </el-card>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="24" style="text-align: center">
                <router-link :to="'/Backlog'">
                    <el-button size="small" type="info" @click="toReturn">
                        <i class="iconfont icon-quxiao"></i>取消</el-button>
                </router-link>
                <router-link :to="taburl">
                    <el-button size="small" type="primary" :loading="ysLoading" @click="ysjl_vue">
                        <i class="iconfont icon-queren"></i>提交</el-button>
                </router-link>
            </el-col>
        </el-row>
    </section>
</template>

<script>
    import hhldsx from '@/views/template/hhldsx.vue';
    import GWJBXX from '@/views/template/gwjbxx.vue';
    import util from '../../common/js/util';
    import {doFqys,getCybm,importFile,getFkFjData,downloads,deleteFkFj} from '../../api/zhys';
    import {updateQszt } from '../../api/Yswt';

    export default {

        data() {
            return {
                ysLoading:false,
                taburl:'',
                xmid:this.$route.query.xmid,//项目id
                sqdid:this.$route.query.sqdid,//申请单id
                bmmc :this.$route.query.bmmc,//部门名称
                activityId:this.$route.query.activityId,
                bmyj:'',
                yssj:'',
                fjData:[],
                fileList:[],//上传文件集合
                fileIds:[],//已上传的资料ID集合
            }
        },
        mounted() {
            // this.getBm_vue();
            this.updateQszt();
            this.getFjData();
        },
        components: {
            'hhldsx': hhldsx,
            'GWJBXX': GWJBXX,//基本信息模板
        },
        methods: {
            //获取附件列表(上传)
            getFjData(){
                let params = {
                    xmid: this.xmid,
                    sqdid: this.sqdid,
                    bmmc:this.bmmc
                };

                getFkFjData(params).then((res) => {
                    this.fjData = res.ZhysRwcbFj;
                });
            },
            //获取当前登陆人所属参与部门
            getBm_vue(){
                let params = {};
                getCybm(params).then((res) => {
                    console.log(res)
                    this.bmmc = res.data.bmmc;
                });
            },
           deleteFj(index,row) {
                this.$confirm('确认删除该附件？, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'error'
                }).then(() => {
                    let params = {fjid:row.id}
                    deleteFkFj(params).then((res) => {
                        this.getFjData();
                    })
                }).catch(() => {
                });
            },
            //综合验收记录提交
            ysjl_vue() {
                if(this.bmyj == '' || this.yssj == ''){
                    this.$message({
                        message: '请输入部门意见及验收时间！',
                        type: 'warning'
                    });
                    this.taburl = "yszhys?xmid="+this.xmid+"&sqdid="+this.sqdid+"&bmmc="+this.bmmc+
                        "&activityId="+this.activityId;
                }else{
                    let params = Object.assign({xmid:this.xmid,type:'3',bmmc:this.bmmc,
                        bmyj:this.bmyj,yssj:this.yssj,sqdid:this.sqdid});
                    this.ysLoading = true;
                    this.taburl = "/Backlog";
                    this.$routerTab.close();
                    this.$routerTab.refresh('/Backlog');
                    doFqys(params).then((res)=>{
                        if(res.data == true){
                            this.$message({
                                message: '提交成功',
                                type: 'success'
                            });
                            this.ysLoading = false;
                        }else{
                            this.$message.error('提交失败！');
                        }
                    });
                }
            },
            submitUpload(event) {
                let fileList = this.fileList;
                if (fileList !=null){
                    for (let i=0;i<fileList.length;i++){
                        let file = fileList[i];
                        let formData = new FormData();
                        formData.append("sqdid",this.sqdid);
                        formData.append("bmmc",this.bmmc);
                        formData.append("xmid",this.xmid);
                        formData.append("multipartFiles",file.raw);
                        formData.append("filename",file.name);
                        importFile(formData).then((res) => {
                            let msg = res.msg;
                            if(msg == '上传成功'){
                                this.$message({
                                    message: '上传成功',
                                    type: 'success'
                                });
                                this.getFjData();
                            }else{
                                this.$message.error('上传失败！');
                            }
                            this.fileList = [];
                        });
                    }
                }
            },
            downloadFile_vue(index,row,flag){//附件查看或下载
                let formData = new FormData();
                formData.append("path",row.fjlj);
                downloads(formData).then((res) => { // 处理返回的文件流
                    if(flag==1){
                        this.slide1=[];
                        let reader = new FileReader()
                        reader.onload = (e) => {
                            let item = {};
                            let img = new Image();
                            img.src =e.target.result;
                            item.msrc = item.src = e.target.result;
                            // this.Imgsrc = e.target.result;
                            item.h = img.height;
                            item.w = img.width;
                            this.slide1.push(item);
                            this.$preview.openList(0, this.slide1);
                            //this.msgFlag = true;
                            //this.prop.src = this.Imgsrc
                        }
                        reader.readAsDataURL(res)
                    }else{
                        const content = res
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
            },
            //取消
            toReturn() {
                this.$routerTab.close();
            },
            handleRemove(file, fileList) {
            },
            handlePreview(file) {
            },
            handleExceed(files, fileList) {
                this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
            },
            handleChange(file,fileList){
                this.fileList = fileList;
            },
            //更新签收状态（我的代办中显示）
            updateQszt(){
                let params = {
                    xmid : this.xmid,
                    activityId:'40009'
                }
                updateQszt(params).then(res =>{

                });
            },
        }
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

