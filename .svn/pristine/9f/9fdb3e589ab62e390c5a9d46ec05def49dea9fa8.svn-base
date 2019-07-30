<template>
    <section>
    <el-button size="small" type="primary"><i class="iconfont icon-weituoshejiguanli"></i>委托设计</el-button>
    <el-button size="small" type="primary"><i class="iconfont icon-yusuan"></i>委托预算</el-button>
        <el-button size="small" type="primary"><i class="iconfont icon-tiaocha"></i>委托官网调查</el-button>
        <el-button size="small" type="primary"><i class="iconfont icon-set_up_project"></i>立项</el-button>
        <el-button size="small" type="primary"><i class="iconfont icon-hetong"></i>合同编制</el-button>
        <el-button size="small" type="primary"><i class="iconfont icon-weibiaoti-"></i>下单</el-button>
        <el-button size="small" type="primary"><i class="iconfont icon-chaxun"></i>查询</el-button>
        <el-button size="small" type="primary"><i class="iconfont icon-xinzeng"></i>新增</el-button>
        <el-button size="small" type="primary"><i class="iconfont icon-caijifanweipeizhi"></i>范围配置</el-button>
        <el-button size="small" type="info"><i class="iconfont icon-fanhui"></i>退回</el-button>
        <el-button size="small" type="primary"><i class="iconfont icon-tuxing"></i>转单</el-button>
        <el-button size="small" type="info"><i class="iconfont icon-quxiao"></i>取消</el-button>
        <el-button size="small" type="primary"><i class="iconfont icon-save"></i>保存</el-button>
        <el-button size="small" type="primary"><i class="iconfont icon-queren"></i>确定</el-button>
        <el-button size="small" type="info"><i class="iconfont icon-shenpibutongguo"></i>审批不通过</el-button>
        <el-button size="small" type="primary"><i class="iconfont icon-queren"></i>提交</el-button>
        <el-button size="small" type="success" icon="el-icon-printer">打印</el-button>
        <el-button size="small" type="primary"><i class="iconfont icon-shengcheng"></i>生成</el-button>
        <el-button size="small" type="danger" icon="el-icon-delete">清空</el-button>
        <el-button size="small" type="primary"><i class="iconfont icon-shenpitongguo"></i>审批通过</el-button>
        <el-button size="small" type="primary"><i class="iconfont icon-shenpitongguo"></i>应用</el-button>
        <el-button size="small" type="success"><i class="iconfont icon-xiazai"></i>下载模块</el-button>
        <el-button size="small" type="success"><i class="iconfont icon-daoru"></i>导入</el-button>
        <el-button size="small" type="success"><i class="iconfont icon-yingyongtubiaodaochu"></i>导出</el-button>
    </section>
</template>

<script>
    import axios from 'axios';
    import '../../assets/iconfont/iconfont.css'

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
