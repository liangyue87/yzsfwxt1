<template>
    <section>
        <el-button size="small" type="" @click="openVisible">拍摄</el-button>
        <el-button size="small" type="" @click="showImageList">预览(已拍摄{{fileSize}}张)</el-button>
        <br>
        <a href="#" @click="openVisible">拍摄</a>
        <a href="#" @click="showImageList">预览(已拍摄{{fileSize}}张)</a>

        <el-dialog title="拍摄" :visible.sync = "creamVisible" top="5vh">
            <DocCamera v-bind:dirName ="dirName" v-bind:fileName ="fileName" v-on:listenToChildEvent="showMsg"></DocCamera>
        </el-dialog>

        <el-dialog title="预览" :visible.sync ="photoVisible" width="800px">
            <el-row>
                <el-col :span="24">
                    <el-button type="primary" @click.native="type = '1'" v-if="type == '0'">选择</el-button>
                    <el-button type="danger"  v-if="type == '1'" @click="deleteImage">删除</el-button>
                    <el-button type="info" @click.native="type = '0'" v-if="type == '1'">取消</el-button>
                </el-col>
            </el-row>
            <el-row style="margin-top: 25px;">
                <el-checkbox-group v-model="checkImage">
                    <el-col :span="6" v-model="file" v-for="item in file" :key="item.name" style="margin-top: 2px;">
                        <img :src="item.src" width="150px" height="120px" @click="showImage" style="margin-right: 2px">
                        <el-checkbox :label="item.name" v-if="type == '1'" size="medium">&nbsp;</el-checkbox>
                    </el-col>
                </el-checkbox-group>
            </el-row>
        </el-dialog>
    </section>
</template>

<script>
    import DocCamera from '../template/DocCamera'
    import { showImage } from '../../api/xkylx';
    export default {
        name: "Page11",
        components:{
            'DocCamera' : DocCamera
        },
        data(){
            return{
                //用来接收子组件保存的图片名称以及之前保存的图片 将名字push进去
                fileItem : [],
                fileName: 'xkylx',//给子组件的文件名字
                dirName: '',//保存图片的文件夹名字
                file : [],//图片Base64数据
                checkImage : [],//选中的文件暂存数组
                creamVisible : false,
                photoVisible : false,
                type : 0,
                fileSize : 0,
                slide1 : []
            }
        },
        methods:{
            init(){//初始化参数
                this.fileSize = this.fileItem.length;
                let JSONstr = sessionStorage.getItem("user");
                let JSON = eval("("+JSONstr+")");
                //console.log(JSON);
                //console.log(JSON.username);
                this.dirName = JSON.username;//文件夹名字
            },
            showMsg:function(data){//存储子组件保存的图片名称
                this.fileItem.push(data);
                this.fileSize = this.fileItem.length;
                console.log(this.fileItem);
                //接口预留位置  data为图片名称 将图片名称保存到数据库
            },
            openVisible(){
                this.creamVisible = true;
            },
            showImageList(){//显示图片列表
                this.file = [];
                let params = {fileItem: this.fileItem.join(",")}
                showImage(params).then((res) => {
                    if (res.data != null) {
                        let result = res.data.row;
                        for (let i = 0; i < result.length; i++) {
                            let item = {};
                            item.name = this.fileItem[i];
                            item.msrc = item.src = "data:image/jpeg;base64," + result[i];
                            this.file.push(item);
                        }
                    }
                    this.creamVisible = false;
                    this.photoVisible = true;
                })
            },
            showImage(){//预览图片
                this.slide1 = [];
                let params = {fileItem: this.fileItem.join(",")}
                showImage(params).then((res) => {
                    if (res.data != null) {
                        let result = res.data.row;
                        for (let i = 0; i < result.length; i++) {
                            let img = new Image();
                            img.src = "data:image/jpeg;base64," + result[i];
                            let item = {};
                            item.msrc = item.src = "data:image/jpeg;base64," + result[i];
                            item.h = img.height;
                            item.w = img.width;
                            this.slide1.push(item);
                        }
                        this.$preview.openList(0, this.slide1);
                    }
                })
            },
            deleteImage(){
                let name = this.checkImage;
                for(let i =0;i<name.length;i++){
                    for(let j =0;j<this.fileItem.length;j++){
                        if(name[i] == this.fileItem[j]){
                            this.file.splice(j,1);
                            this.fileItem.splice(j,1);
                            //接口预留位置  this.fileItem[j]为图片名称 将删除的图片名称传入接口，数据库中删除图片

                            j = j - 1;
                        }
                    }
                }
                this.checkImage = [];
                this.fileSize = this.fileItem.length;
            },
            start(el){
                if(el=='bStartRec(d:\\test.avi)')
                {
                    timer = window.setInterval("TimeGo()",1000);
                }
                if(el=='bStopRec')
                {
                    window.clearInterval(timer);
                    TotalTimelenth=0;
                    alert("录屏结束");
                }
                webSocket.send(el);
                return false;
            }
        },
        mounted(){
            this.init();
        }
    }
</script>

<style scoped>

</style>
