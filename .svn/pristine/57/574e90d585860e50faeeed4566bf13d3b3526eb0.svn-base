<template>
    <el-upload
            class="upload-demo"
            ref="upload"
            action="#"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :on-change="handleChange"
            :file-list="fileList"
            :auto-upload="false">
        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
        <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
    </el-upload>

</template>

<script>
    import axios from 'axios';

    export default {
        data() {
            return {
                fileList: [],
                ok: false
            };
        },
        methods: {
            submitUpload() {
                console.log("this.fileList", this.fileList);
                if (this.fileList != null) {
                    for (let i = 0; i < this.fileList.length; i++) {
                        let file = this.fileList[i];
                        let params = new FormData();
                        params.append("multipartFiles", file.raw);
                        params.append("operation", "upload_file");
                        let instance = axios.create({
                            baseURL: 'http://localhost:8080',
                            headers: {'Content-Type': 'multipart/form-data'}
                        });

                        instance.post('/XqslController/importEnterPriseQuota', params).then((res) => {
                            console.info("Play it ..." + res)
                        })
                    }
                }

            },
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
            },
            handleChange(file, fileList) {
                console.log("file", file);
                console.log("fileList", fileList);
                this.fileList = fileList;
            }
        }
    }
</script>
