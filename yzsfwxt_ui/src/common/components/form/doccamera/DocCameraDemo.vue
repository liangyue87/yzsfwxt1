<template>
    <div>
        <form>
            <fieldset>
                <legend align="center">高拍仪界面</legend>
                <p><img id="photo" src="" width=900 height=480></p>
                <input type="button" style=background:green value="启动主" name="StartBtn"
                       v-on:click="start('bStartPlay')">
                <input type="button" style=background:green value="旋转启动主" name="StartBtn"
                       v-on:click="start('bStartPlayRotate(90)')">
                <input type="button" style=background:green value="启动副" name="StopBtn"
                       v-on:click="start('bStartPlay2')">
                <input type="button" style=background:green value="停止" name="StopBtn" v-on:click="start('bStopPlay')">
                <input type="button" style=background:green value="自动对焦" name="AutoFocus"
                       v-on:click="start('AutoFocus')">
                <input type="button" style=background:green value="识别二维码/条形码" name="sGetBarCodeOnline"
                       v-on:click="start('sGetBarCodeOnline')">
                <input type="button" style=background:green value="去黑边" name="StartBtn"
                       v-on:click="start('vSetDelHBFlag(true)')">
                <input type="button" style=background:green value="设置视频显示模式" name="StartBtn"
                       v-on:click="start('vSetVideoDispMode(0)')">
                <input type="button" style=background:green value="旋转启动" name="sGetBase64"
                       v-on:click="start('bStartPlayRotate(270)')">
                <input type="button" style=background:green value="设置分辨率" name="sGetBase64"
                       v-on:click="start('vSetResolution(5)')">
                <input type="button" style=background:green value="设置旋转角度" name="sGetBase64"
                       v-on:click="start('vSetRotate(0)')">
                <p></p>
                <input type="button" style=background:green value="Base64" name="sGetBase64"
                       v-on:click="start('sGetBase64')">
                <input type="button" style=background:green value="开始录屏" name="bStartRec"
                       v-on:click="start('bStartRec(d:\\test.avi)')">
                录制时间：<input id="TimeLenth" type="text" value="" size="15"/>
                <input type="button" style=background:green value="停止录屏" name="bStartRec"
                       v-on:click="start('bStopRec')">
                <input type="button" value="拍照" name="SaveJPGBtn" v-on:click="start('bSaveJPG(D:\\,Photo)')">
                <input type="button" value="http上传" name="bUpLoadImage"
                       v-on:click="start('bUpLoadImage(D:\\1.JPG|D:\\2.JPG|D:\\3.JPG,127.0.0.1,8080,/shop/servlet/uploadServlet)')">
                <input type="button" value="sUpLoadImageEx2" name="sUpLoadImageEx2"
                       v-on:click="start('sUpLoadImageEx2(D:\\1.JPG|D:\\2.JPG|D:\\3.JPG,127.0.0.1,8080,/shop/servlet/uploadServlet,false,true)')">
                <input type="button" value="ftp上传" name="iUploadFileToFtpEx"
                       v-on:click="start('iUploadFileToFtpEx(127.0.0.1,name,password,d:\\test.jpg,/FTPPath/temp,test.jpg,true)')">
                <input type="button" style=background:green value="FILTER设置" name="ParaSet"
                       v-on:click="start('displayVideoPara')">
                <input type="button" style=background:green value="PIN设置" name="ParaSet"
                       v-on:click="start('vSetCapturePin')">
                <p>
                    <input type="button" style=background:green value="bSetMode" name="ParaSet"
                           v-on:click="start('bSetMode(3)')">
                    <input type="button" style=background:green value="vSetExposure" name="ParaSet"
                           v-on:click="start('vSetExposure(50)')">
                    <input type="button" style=background:green value="vSetBrightness" name="ParaSet"
                           v-on:click="start('vSetBrightness(30)')">
                    <input type="button" style=background:green value="vSetContrast" name="ParaSet"
                           v-on:click="start('vSetContrast(50)')">
                    <input type="button" style=background:green value="vSetResolutionEx" name="ParaSet"
                           v-on:click="start('vSetResolutionEx(1,2)')">
                    <input type="button" style=background:green value="vSetVideoCompressTypeEx" name="ParaSet"
                           v-on:click="start('vSetVideoCompressTypeEx(1,1)')">
                </p>
                <p>
                    <input type="button" style=background:green value="打开文件" name="ParaSet"
                           v-on:click="upload"></input>
                </p>
            </fieldset>
        </form>
        <div id="messages"></div>
    </div>
</template>

<script>
    // import WebSocket from 'WebSocket'
    export default {
        name: "DocCameraDemo",
        data() {
            return {
                TotalTimelenth: 0,
                timer: null,
                webSocket: null,
                webSocketurl: "",
                begin_data: "",
                file1: null
            }
        },
        methods: {
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
            upload(){
                this.webSocket.send('OpenFile("D:\\Photo.jpg")');
                console.log(this.file1);
            },
            onMessage(event) {
                //console.log(event);
                if (event.data.indexOf('BarCodeTransferBegin') >= 0) {
                    alert(event.data.replace('BarCodeTransferBegin', '').replace('BarCodeTransferEnd', ''));
                } else if (event.data.indexOf('BeginBase64') >= 0) {
                    //document.getElementById("preview").src = begin_data + event.data.substring(0, event.data.length - 11);
                    alert(event.data.replace('BeginBase64', '').replace('EndBase64', ''));
                    //alert(begin_data + event.data.substring(0, event.data.length - 11));
                } else {
                    document.getElementById('photo').src = this.begin_data + event.data;
                }
            },
            onError(event) {
                alert(event.data);
                this.initWebsocket();
            },
            onClose(event) {
                this.webSocket.send("bStopPlay()");
            },
            onOpen(event) {
            }
            // onSend(Data){
            //     this.webSocket.send(Data);
            // }
        },
        mounted() {
            this.webSocketurl = "ws://localhost:1818";
            this.begin_data = "data:image/jpeg;base64,";
            this.initWebsocket();
        }
    }
</script>

<style scoped>

</style>
