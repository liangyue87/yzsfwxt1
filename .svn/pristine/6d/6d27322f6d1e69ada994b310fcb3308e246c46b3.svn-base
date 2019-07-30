<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-card class="box-card"  style="margin-top: 15px;">
                <div slot="header" class="clearfix">
                    <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">工程概括</div>
                </div>
                <el-form :model="filters">
                    <el-form-item>
                        <el-col :span="2" style="text-align: right">
                            <span>项目编号：</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="filters.xmbh"></el-input>
                        </el-col>
                        <el-col :span="2" style="text-align: right">
                            <span>项目名称：</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="filters.xmmc"></el-input><!--@blur="queryByXmbh()"-->
                        </el-col>
                        <el-col :span="2" style="text-align: right">
                            <span>用户名称：</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="filters.yhmc"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="2" style="text-align: right">
                            <span>施工地址：</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="filters.sgdz"></el-input>
                        </el-col>
                        <el-col :span="2" style="text-align: right">
                            <span>联系人：</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="filters.lxr"></el-input>
                        </el-col>
                        <el-col :span="2" style="text-align: right">
                            <span>项目地址：</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="filters.xmjd"></el-input>
                        </el-col>
                        <!--<el-col :span="2" style="text-align: right">-->
                            <!--<el-button size="small" type="primary" @click="handleAdd">提交</el-button>-->
                        <!--</el-col>-->
                    </el-form-item>
                </el-form>
            </el-card>
        </el-col>
        <el-col :span="24" class="toolbar">
            <div>
                <UEditor :defaultMsg=defaultMsg :config=config ref="ue"></UEditor>
            </div>
        </el-col>
        <el-col :span="24" class="toolbar">
            <el-card class="box-card"  style="margin-top: 15px;">
                <div slot="header" class="clearfix">
                    <span>附件管理</span>
                </div>
                <div id="row1">
                    <el-form :inline="true">
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
                                :file-list="fileList">
                            <el-button slot="trigger" size="mini" type="primary">
                                <i class="iconfont icon-shangchuan"></i>选取文件</el-button>
                            <el-button style="margin-left: 10px;"  size="mini" type="success" @click="submitPs($event)">
                                <i class="iconfont icon-paishe"></i>拍摄</el-button>
                        </el-upload>
                    </el-form>
                    <el-table :data="gridData" :stripe="true" height="300" style="padding-bottom: 10px">
                        <el-table-column property="FJMC" label="附件名称" align="center"></el-table-column>
                        <el-table-column property="CJSJ" label="上传时间" align="center"></el-table-column>
                        <el-table-column property="USERNAME" label="上传人" align="center"></el-table-column>
                        <el-table-column property="" label="操作" align="center">
                            <template slot-scope="scope">
                                <el-row>
                                    <el-col :span="24">
                                        <label title="删除">
                                            <i class="iconfont delete"
                                               @click="deleteFj_vue(scope.$index, scope.row)"
                                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label>
                                        <label title="下载">
                                            <i class="iconfont icon-xiazai"
                                               @click="downloadFile_vue(scope.$index, scope.row)"
                                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label>
                                    </el-col>
                                </el-row>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </el-card>
            <el-row style="text-align: center;margin-top: 25px;">
                <el-button size="mini" type="primary" :loading="submitLoading" ><!--@click="handleAdd"-->
                    <i class="iconfont icon-queren"></i>提交</el-button>
            </el-row>
        </el-col>
    </section>
</template>

<script>
    import util from '../../common/js/util'
    import { add_htbz,getXmBybh,importFile,queryZllist } from '../../api/htsf';
    import UEditor from '../../UE.vue';

    export default {
        name: "Htbz",
        components: {UEditor},
        inject:['reload'],
        data() {
            return {
                gridData:[],

                filters: {
                    submitLoading:false,
                    xmbh:'',
                    xmmc:'',
                    yhmc:'',
                    sgdz:'',
                    lxr:'',
                    xmjd:''
                },
                editLoading: false,
                defaultMsg: '',
                config: {
                    autoHeightEnabled: false,
                    autoFloatEnabled: true,
                    initialFrameWidth: null,
                    initialFrameHeight: 450
                },
                fileList:[],//上传文件集合
                fileIds:[],//已上传的资料ID集合
            }
        },
        mounted() {
            this.queryByXmbh();
            this.queryZllist();
        },
        methods: {
            //保存合同模板
            handleAdd: function () {
                this.$confirm('确认提交吗？', '提示', {}).then(() => {
                    var iframe = document.getElementsByTagName("iframe")[0];
                    var win = iframe.contentWindow;  // 通过contentWindow获取ifame子页面的window窗体对象。(不允许跨域名访问)
                    var iframeDocument = iframe.contentWindow.document;
                    console.log(this.defaultMsg);
                    console.log(iframeDocument);
                    var input1 = iframeDocument.getElementById("gsk_xmdm").innerText;
                    let para = {
                        xmbh: this.filters.xmbh,
                        xmmc: this.filters.xmmc,
                        yhmc: this.filters.yhmc,
                        sgdz: this.filters.sgdz,
                        lxr: this.filters.lxr,
                        xmjd: this.filters.xmjd
                    };
                    this.submitLoading = true;
                    add_htbz(para).then((res) => {
                        this.$message({
                            message: '提交成功',
                            type: 'success'
                        });
                        this.submitLoading = false;
                        this.$router.push({path: '/Xmlb'})
                    });
                });
            },
            //根据项目编号查询项目基本信息
            queryByXmbh: function(){
                let params = this.$route.query;
                let xmbh = params.xmbh;
                getXmBybh(params).then((res) => {
                    this.filters.xmbh = params.xmbh;
                    this.filters.xmmc = res.data.list.xmmc;
                    this.filters.yhmc = res.data.list.xyhmc;
                    this.filters.sgdz = res.data.list.dwmc;
                    this.filters.lxr = res.data.list.lxr;
                    this.filters.xmjd = res.data.list.xmdz;
                    this.defaultMsg = res.data.mbnr;
                    this.$refs['ue'].setUMContent(this.defaultMsg);
                });
            },
            //拍摄
            submitPs(){

            },
            //上传
            submitUpload(event) {
                let fileList = this.fileList;
                if (fileList !=null){
                    for (let i=0;i<fileList.length;i++){
                        let file = fileList[i];
                        let formData = new FormData();
                        // formData.append("operation",this.xmid);
                        formData.append("multipartFiles",file.raw);
                        formData.append("filename",file.name);
                        importFile(formData).then((res) => {
                            let msg = res.msg;
                            if(msg == '上传成功'){
                                this.$message({
                                    message: '上传成功',
                                    type: 'success'
                                });
                                let fileId = res.fileId;
                                this.fileIds = this.fileIds.concat(fileId);
                            }else{
                                this.$message.error('上传失败！');
                            }
                            this.fileList = [];
                        });
                    }
                }
            },
            //查询已上传资料
            queryZllist(){
                let para = {zbid:this.refid};
                queryZllist(para).then(res=>{
                    this.gridData = res.data.fjList;
                });
            },
            //删除附件
            deleteFj_vue(){

            },
            //下载附件
            downloadFile_vue(){

            },
            handleRemove(file, fileList) {
            },
            handlePreview(file) {
            },
            handleChange(file,fileList){
                this.fileList = fileList;
            },
        }
    }
</script>

<style scoped>

</style>