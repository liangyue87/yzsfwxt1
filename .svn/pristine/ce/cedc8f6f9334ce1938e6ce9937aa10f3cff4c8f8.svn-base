<template>
    <div>
        <form>
            <el-row style="margin-top: 25px" :gutter="80">
                <el-col style="text-align: center" :span="2">
                    <el-button size="mini" type="primary"
                               v-on:click="startPlay"><i class="iconfont icon-stop"></i>启动</el-button>
                </el-col>
                <el-col style="text-align: center" :span="2">
                    <el-button size="mini" type="primary"
                               v-on:click="onPlay"><i class="iconfont icon-paishe"></i>拍摄</el-button>
                </el-col>
                <!--<el-col style="text-align: center" :span="2">
                    <el-button size="small" type="primary"
                               v-on:click="showImage">预览</el-button>
                </el-col>-->
                <el-col style="text-align: center" :span="2">
                    <el-button size="mini" type="primary"
                               v-on:click="stopPlay"><i class="iconfont icon-tingzhi"></i>停止</el-button>
                </el-col>
                <el-col style="text-align: center" :span="2">
                    <el-button size="mini" type="primary"
                               v-on:click="start('displayVideoPara')"><i class="iconfont icon-shezhi"></i>设置</el-button>
                </el-col>
                <el-col style="text-align: center" :span="2">
                    <el-button size="mini" type="primary"
                               v-on:click="start('bStartPlayRotate(90)')"><i class="iconfont icon-xuanzhuan"></i>旋转</el-button>
                </el-col>

            </el-row>

            <el-row>
            <el-col :span="24">
                <fieldset>
                    <legend align="center">高拍仪界面</legend>
                    <p><img id="photo" src="" style="width: 100%;height: 450px;"></p>

                </fieldset>
            </el-col>

            </el-row>
        </form>
        <div id="messages"></div>
    </div>
</template>

<script>
    import {docUpload_ip,docUpload_port} from "../../../src/httpConfig/baseUrl";
    import util from '../../common/js/util';
    import { uploadCream,getPath } from '../../api/xkylx';
    export default {
        name: "DocCamera",
        data() {
            return {
                fileItem : "",
                //fileName : "photo",
                timer: null,
                webSocket: null,
                webSocketurl: "",
                begin_data: "",
                date : "",
                path : "C:\\imageDir\\",
                img : "",
            }
        },
        props: ['fileName','dirName'],
        methods: {
            sendMsgToParent(){//向父组件传值
                this.$emit('listenToChildEvent',this.fileItem);
            },
            showImage(){//预览
                this.$emit('fatherMethod');
            },
            onPlay(){//拍照保存到路径
                this.webSocket.send('bCreateDir(C:\\imageDir\\)');
                let date = util.formatDate.format(new Date(),'yyyyMMddhhmmss');
                this.date = date;
                let el = 'bSaveJPG('+this.path+',DocCamera)';
                this.webSocket.send(el);//拍照
                this.fileItem= this.fileName + date+'.jpg' ;
                let timer = setTimeout(() => {
                    let ell = 'bUpLoadImage('+this.path+'DocCamera.jpg,'+docUpload_ip+','+docUpload_port+',/XkylxController/uploadCamera?' +
                       'date='+date+'&dirName='+this.dirName+'&fileName='+this.fileName+')';
                    this.start(ell);//上传
                    clearTimeout(timer)
                },2000);
                this.sendMsgToParent();
                //bDeleteFile("D:\\ tif.tif");
                return;
            },
            stopPlay(){//关闭摄像头
                this.webSocket.send('bStopPlay');
                return false;
            },
            startPlay(){//打开摄像头
                this.webSocket.send('bStartPlay');
                return false;
            },
            start(el) {
                if (el == 'bStartRec(d:\\test.avi)') {
                    this.timer = window.setInterval("TimeGo()", 1000);
                }
                if (el == 'bStopRec') {
                    window.clearInterval(timer);
                    this.TotalTimelenth = 0;
                    alert("录屏结束");
                }
                this.webSocket.send(el);
                return false;
            },
            vout_OnClick() {

            },
            initWebsocket() {
                this.webSocket = new WebSocket(this.webSocketurl);
                this.webSocket.onopen = this.onOpen;
                this.webSocket.onmessage = this.onMessage;
                this.webSocket.onerror = this.onError;
                this.webSocket.onclose = this.onClose;
                // this.webSocket.send("bStopPlay()");
            },
            onMessage(event) {
                //console.log(event);
                if (event.data.indexOf('BarCodeTransferBegin') >= 0) {
                   // alert(event.data.replace('BarCodeTransferBegin', '').replace('BarCodeTransferEnd', ''));
                } else if (event.data.indexOf('BeginBase64') >= 0) {
                    //document.getElementById("preview").src = begin_data + event.data.substring(0, event.data.length - 11);
                   // alert(event.data.replace('BeginBase64', '').replace('EndBase64', ''));
                    //alert(begin_data + event.data.substring(0, event.data.length - 11));
                } else {
                    try {
                        document.getElementById('photo').src = this.begin_data + event.data;
                    } catch (e) {
                    }
                    //this.img = event.data;
                }
            },
            onError(event) {
               // alert(event.data);
                this.initWebsocket();
            },
            onClose(event) {
                try {
                    this.webSocket.send("bStopPlay()");
                } catch (e) {
                }
            },
            onOpen(event) {
            }
        },
        mounted() {
            this.webSocketurl = "ws://localhost:1818";
            this.begin_data = "data:image/jpeg;base64,";
            this.initWebsocket();
            this.img = this.fileName;
        }
    }
</script>

<style scoped>


</style>
