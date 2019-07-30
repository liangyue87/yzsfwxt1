<!--项目基本信息模板界面-->
<template>
    <section>
        <el-row style="margin-top: 15px;">
            <el-col :span="24">
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">资料分类及下载</div>
                    </div>
                    <div>
                        <el-table :data="zlrow"  :stripe="true" style="width: 100%;" row-key="id" >
                            <el-table-column prop="codename" label="资料类型" width="600" align="center">
                            </el-table-column>
                            <el-table-column prop="count" label="资料数量" width="300" align="center">
                            </el-table-column>
                            <el-table-column label="操作" align="center" width="200">
                                <template slot-scope="scope" v-if="scope.row.parentid!=''&&scope.row.parentid!=null">
                                    <el-row>
                                        <el-col :span="24">
                                            <label title="详情"><i @click="toDetial_vue(scope.$index, scope.row)" class="iconfont icon-xiangqing" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                                        </el-col>
                                    </el-row>
                                </template>
                            </el-table-column>
                        </el-table>
                    </div>
                </el-card>

            </el-col>
        </el-row>
        <el-dialog title="项目资料下载" :visible.sync="dialogTableVisible" :modal-append-to-body="false" style="padding-bottom: 10px" >
            <el-table :data="gridData" :stripe="true" style="padding-bottom: 10px" height="400px">
                <el-table-column property="fjmc" label="附件名称"  align="center"></el-table-column>
                <el-table-column property="scsj_vue" label="上传时间"  align="center"></el-table-column>
                <el-table-column property="scr" label="上传人"  align="center"></el-table-column>
                <el-table-column property="address" label="操作"  align="center">
                    <template slot-scope="scope">
                        <el-row>
                            <el-col :span="24">
                                <div v-if="scope.row.fjhz!=null&&(scope.row.fjhz.toLowerCase()=='png'||scope.row.fjhz.toLowerCase()=='jpg'||scope.row.fjhz.toLowerCase()=='.jpg')">
                                    <label title="预览"><i @click="downloadFile_vue(scope.$index, scope.row,1)" class="iconfont icon-yulan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                                    <label title="下载"><i @click="downloadFile_vue(scope.$index, scope.row,2)" class="iconfont icon-xiazai" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                                </div>
                                <div v-else>
                                    <label title="下载"><i @click="downloadFile_vue(scope.$index, scope.row,2)" class="iconfont icon-xiazai" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                                </div>
                            </el-col>
                        </el-row>
                    </template>
                </el-table-column>
            </el-table>
        </el-dialog>
    </section>

</template>
<style>

</style>
<script>
    import '../../assets/iconfont/iconfont.css'
    import {getZlzl, toDetial,download} from '../../api/xkylx';
    import axios from 'axios';
    export default {
        data() {
            return {
                zlrow:[],
                props:[],
                gridData: [],
                dialogTableVisible: false,
                sqdid: this.$route.query.id,
                type: this.$route.query.type

            }
        },

        methods: {
            //获取资料总览
            getZlzl_vue() {
                let params = {
                    sqdid: this.sqdid,
                    type: this.type,
                };
                getZlzl(params).then((res) => {
                    if (res.data != null && res.data.row != null) {
                        this.zlrow = res.data.row;
                    }
                })
            },
            toDetial_vue(index, row) {
                this.dialogTableVisible = true;
                let params = {
                    fjlx: row.id,
                    sqdid: this.sqdid,
                    type: this.type,
                }
                toDetial(params).then((res) => {
                    if (res.data != null && res.data.row != null) {
                        this.gridData = res.data.row;
                    }
                })
            },
            downloadFile_vue(index,row,flag){//附件查看或下载
                let formData = new FormData();
                formData.append("path",row.fjlj);
                download(formData).then((res) => { // 处理返回的文件流
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
            }

        },
        mounted() {
            this.getZlzl_vue();
        },
    }

</script>

<style scoped>

</style>