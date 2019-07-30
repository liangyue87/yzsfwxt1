<template>
    <section>
        <el-row>
            <el-col :span="24">
                <el-card class="box-card" style="margin-top: 15px;" body-style="{}">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">收费确认
                        </div>
                    </div>
                    <el-form :model="filters" ref="filters" class="demo-ruleForm">
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>应收金额&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="ysje" size="small" placeholder="" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>实收金额&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="ssje" :rules="this.publicUtil.notNull">
                                    <el-input v-model="filters.ssje" size="small" placeholder="输入实收金额" :readonly="sfzd"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>确认人&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.qrr" size="small" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>票据类型&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-select v-model="filters.pjlx" :disabled="sfzd" size="small" style="width: 250px;" placeholder="请选择">
                                    <el-option v-for="item in pjList"
                                               :key="item.VALUE"
                                               :label="item.LABEL"
                                               :value="item.VALUE"></el-option>
                                </el-select>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>票据号&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.pjh" size="small" placeholder="输入票据号" :readonly="sfzd"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>开票日期&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="kprq" :rules="this.publicUtil.notNull">
                                    <el-date-picker
                                            style="width: 250px;"
                                            v-model="filters.kprq"
                                            type="date"
                                            format="yyyy-MM-dd"
                                            value-format="yyyy-MM-dd"
                                            placeholder="选择日期" size="small" :readonly="sfzd">
                                    </el-date-picker>
                                </el-form-item>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>备注&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.bz" size="small" placeholder="输入备注" :readonly="sfzd"></el-input>
                            </el-col>
                        </el-form-item>
                    </el-form>
                </el-card>

                <el-card class="box-card" style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">凭据上传
                        </div>
                    </div>
                    <div id="row1">
                        <el-form :inline="true">
                            <el-upload
                                    class="upload-demo"
                                    ref="upload"
                                    :show-file-list='false'
                                    action="#"
                                    :multiple="true"
                                    :http-request="submitUpload"
                                    :on-preview="handlePreview"
                                    :on-change="handleChange"
                                    :on-remove="handleRemove"
                                    accept=".jpg,.jpeg,.png"
                                    :file-list="fileList">
                                <el-button slot="trigger" size="mini" type="success">
                                    <i class="iconfont icon-shangchuan_f"></i>选取文件</el-button>
                                <el-button size="mini" type="success" @click="openVisible" id="choosePs">
                                    <i class="iconfont icon-paishe"></i>拍摄</el-button>
                            </el-upload>
                        </el-form>
                        <el-table :data="gridData" :stripe="true" height="200" style="padding-bottom: 10px">
                            <el-table-column property="FJMC" label="附件名称" align="center"></el-table-column>
                            <el-table-column property="CJSJ" label="上传时间" align="center"></el-table-column>
                            <el-table-column property="USERNAME" label="上传人" align="center"></el-table-column>
                            <el-table-column property="" label="操作" align="center">
                                <template slot-scope="scope">
                                    <el-row>
                                        <el-col :span="24">
                                            <label title="下载">
                                                <i class="iconfont icon-xiazai"
                                                   @click="downloadFile_vue(scope.$index, scope.row)"
                                                   style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                            </label>
                                            <label title="删除">
                                                <i class="iconfont delete"
                                                   @click="deleteFj_vue(scope.$index, scope.row)"
                                                   style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                            </label>
                                        </el-col>
                                    </el-row>
                                </template>
                            </el-table-column>
                        </el-table>
                    </div>
                </el-card>
            </el-col>
        </el-row>
        <el-row style="text-align: center;margin-top: 25px">
            <div v-if="this.type == '1'">
                <router-link :to="{path:'/Jftzd', query:{xmid:this.xmid,dwmc:this.dwmc,xmbh:this.xmbh,
                            azlx:this.azlx,sqlx:this.sqlx,lxr:this.lxr,lxrsjh:this.lxrsjh}}">
                    <el-button size="mini" type="info" @click="toReturn">
                        <i class="iconfont icon-quxiao"></i>取消</el-button>
                </router-link>
                <el-button size="mini" type="primary" @click="goxmqj()" >
                    <i class="iconfont icon-xiangmuquanjing_"></i>项目全景
                </el-button>
            </div>
            <div v-else>
                <router-link :to="{path:'/Jftzd', query:{xmid:this.xmid,dwmc:this.dwmc,xmbh:this.xmbh,
                            azlx:this.azlx,sqlx:this.sqlx,lxr:this.lxr,lxrsjh:this.lxrsjh}}">
                    <el-button size="mini" type="info" @click="toReturn">
                        <i class="iconfont icon-quxiao"></i>取消</el-button>
                </router-link>
                <router-link :to="tabUrl">
                    <el-button size="mini" type="primary" :loading="addLoading" @click="handleAdd">
                        <i class="iconfont icon-queren"></i>确认</el-button>
                </router-link>
                <el-button size="mini" type="primary" @click="goxmqj()" >
                    <i class="iconfont icon-xiangmuquanjing_"></i>项目全景
                </el-button>
            </div>
        </el-row>
        <!--拍摄-->
        <el-dialog title="拍摄" :visible.sync="creamVisible" top="5vh">
            <DocCamera v-bind:dirName="dirName" v-bind:fileName="fileName"
                       v-on:listenToChildEvent="showMsg"></DocCamera>
        </el-dialog>
    </section>
</template>

<script>
    import DocCamera from '../template/DocCamera'
    import {importPz,querypjlx,updateSftzd,queryjfZllist,deletejfFjByid,download,saveImageToFj } from '../../api/htsf';
    export default {
        name: "sfqr",
        components: {
            'DocCamera': DocCamera
        },
        data(){
            return {
                addLoading:false,
                sfzd:false,
                azlx:this.$route.query.azlx,//安装类型
                sqlx:this.$route.query.sqlx,//申请类型
                xmid:this.$route.query.xmid,//项目ID

                ysje:this.$route.query.ysje,
                dwmc:this.$route.query.dwmc,//项目ID
                xmbh:this.$route.query.xmbh,//项目ID
                lxr:this.$route.query.lxr,
                lxrsjh:this.$route.query.lxrsjh,
                type:this.$route.query.type,

                tabUrl:'',
                filters:{
                    zbid:this.$route.query.zbid,
                    ssje:'',
                    qrr:'',
                    pjlx:'01',
                    pjh:'',
                    kprq:'',
                    bz:'',
                },
                pjList:[],
                fileList:[],

                gridData:[],
                /*拍摄*/
                fileName: 'sfkp',//给子组件的文件名字
                dirName: '',//保存图片的文件夹名字
                creamVisible: false,
            }
        },
        mounted() {
            this.init();
            this.queryZllist();
            this.psinit();
        },
        methods:{
            //跳转至项目全景
            goxmqj(){
                this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid  }})
            },
            //初始化方法
            init(){
                let para = {type:this.type,zbid:this.filters.zbid};
                querypjlx(para).then(res=>{
                    this.pjList = res.data.list;
                    let list = res.data.sfzbList;
                    if(this.type == '1'){
                        this.filters.pjh = list[0].PJH;
                        this.filters.pjlx = list[0].PJLX;
                        this.filters.ssje = list[0].SSJE;
                        this.filters.qrr = list[0].USERNAME;
                        this.filters.bz = list[0].SKBZ;
                        this.filters.kprq = list[0].KPRQ;
                        this.sfzd = true;
                    }else {
                        this.filters.qrr = res.data.qrr;
                    }
                });
                this.tabUrl = "/sfqr?zbid="+this.filters.zbid+"&ysje="+this.ysje+"&xmid="+this.xmid
                    +"&azlx="+this.azlx+"&sqlx="+this.sqlx+"&dwmc="+this.dwmc+"&xmbh="+this.xmbh
                    +"&lxr="+this.lxr+"&lxrsjh="+this.lxrsjh+"&type="+this.type;
            },
            toReturn(){
                this.$routerTab.close();
            },
            handleAdd(){
                this.$confirm('您正在递交工作结果，一旦递交确认后将不能打回或再次修改，你确定要递交吗？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$refs.filters.validate((valid) => {
                        if (valid) {
                            if(parseFloat(this.filters.ssje) < parseFloat(this.ysje)){
                                this.$message({
                                    message: '实收金额应大于等于应收金额！',
                                    type: 'warning'
                                });
                            }else{
                                let params = this.filters;
                                this.addLoading = true;
                                this.tabUrl = "/Jftzd?xmid="+this.xmid+"&dwmc="+this.dwmc+"&azlx="+this.azlx
                                    +"&sqlx="+this.sqlx+"&xmbh="+this.xmbh+"&lxr="+this.lxr+"&lxrsjh="+this.lxrsjh;
                                this.$routerTab.close();
                                this.$routerTab.refresh("/Jftzd?xmid="+this.xmid+"&dwmc="+this.dwmc+"&azlx="+this.azlx
                                    +"&sqlx="+this.sqlx+"&xmbh="+this.xmbh+"&lxr="+this.lxr+"&lxrsjh="+this.lxrsjh);
                                updateSftzd(params).then((res) => {
                                    if(res.data == true){
                                        this.$message({
                                            message: '确认成功',
                                            type: 'success'
                                        });
                                        this.addLoading = false;
                                    }
                                });
                            }
                        }else{
                            this.tabUrl = "/sfqr?zbid="+this.filters.zbid+"&ysje="+this.ysje+"&xmid="+this.xmid+
                                "&azlx="+this.azlx+"&sqlx="+this.sqlx+"&dwmc="+this.dwmc+"&xmbh="+this.xmbh
                                +"&lxr="+this.lxr+"&lxrsjh="+this.lxrsjh;
                        }
                    });
                }).catch(() => {

                });
            },
            submitUpload(event) {
                let fileList = this.fileList;
                if (fileList !=null && fileList.length >0){
                    // for (let i=0;i<fileList.length;i++){
                        let file = fileList[fileList.length-1];
                        let formData = new FormData();
                        formData.append("zbid",this.filters.zbid);
                        formData.append("multipartFiles",file.raw);
                        formData.append("filename",file.name);
                        formData.append("xmbh",this.xmbh);
                        importPz(formData).then((res) => {
                            let msg = res.msg;
                            if(msg == '上传成功'){
                                this.$message({
                                    message: '上传成功',
                                    type: 'success'
                                });
                                this.fileList=[];
                            }else{
                                this.$message.error('上传失败！');
                            }
                            this.queryZllist();
                        });
                    // }
                }
            },
            //查询已上传资料
            queryZllist(){
                let para = {zbid:this.filters.zbid};
                queryjfZllist(para).then(res=>{
                    this.gridData = res.data.fjList;
                });
            },
            //删除附件
            deleteFj_vue(index,row){
                let para = {fjId:row.ID};
                deletejfFjByid(para).then(res=>{
                    this.queryZllist();
                });
            },
            //下载附件
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
            handleRemove(file, fileList) {
            },
            handlePreview(file) {
            },
            handleChange(file,fileList){
                this.fileList = fileList;
            },
            psinit() {//初始化参数
                let JSONstr = sessionStorage.getItem("user");
                let JSON = eval("(" + JSONstr + ")");
                this.dirName = this.xmbh;//文件夹名字
            },
            showMsg: function (data) {//存储子组件保存的图片名称
                let params = {zbid:this.filters.zbid, dirName: this.dirName,
                    fileName: data};
                saveImageToFj(params).then((res) => {
                    let msg = res.msg;
                    if (msg == '上传成功') {
                        this.$message({
                            message: '上传成功',
                            type: 'success'
                        });
                        this.fileList=[];
                    } else {
                        this.$message.error('上传失败！');
                    }
                    this.creamVisible = false;
                    this.queryZllist();
                });
            },
            openVisible(index, row) {
                this.creamVisible = true;
            },
        }
    }
</script>

<style scoped>

</style>