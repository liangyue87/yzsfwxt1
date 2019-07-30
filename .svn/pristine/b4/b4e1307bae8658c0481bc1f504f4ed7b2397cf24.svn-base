<!--项目基本信息模板界面-->
<template>
    <section>
        <el-row style="margin-top: 15px;">
            <el-col :span="24">
                <!--项目基本信息模板-->
                <XMJBXX></XMJBXX>
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">资料分类及下载</div>
                    </div>
                    <div>
                        <el-table :data="zlrow"  :stripe="true" style="width: 100%;" :span-method="objectSpanMethod" border>
                            <el-table-column prop="parentname" label="一级资料类型"  align="center">
                            </el-table-column>
                            <el-table-column prop="codename" label="二级资料类型"  align="center">
                            </el-table-column>
                            <el-table-column prop="count" label="资料数量" align="center">
                            </el-table-column>
                            <el-table-column prop="SCR" label="上传人" align="center">
                            </el-table-column>
                            <el-table-column prop="SCSJ" label="上传时间" align="center">
                            </el-table-column>
                            <el-table-column label="操作" align="center">
                                <template slot-scope="scope" v-if="scope.row.count>0">
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
                                <div v-if="scope.row.fjhz!=null&&(scope.row.fjhz.toLowerCase()=='png'||scope.row.fjhz.toLowerCase()=='jpg')">
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
    import XMJBXX from '@/views/template/xmjbxx.vue';
    import '../../assets/iconfont/iconfont.css'
    import {getZlzl,toDetial} from '../../api/zlqj';
    import {download_sjst} from '../../api/sjst';
    export default {
        data() {
            return {
                zlrow:[],
                props:[],
                gridData: [],
                dialogTableVisible: false,
                xmid: this.$route.query.xmid,
                typeNameArr: [],
                typeNamePos: 0,
                storeArr: [],
                storePos: 0,
                feeArr: [],
                feePos: 0,
                hoverOrderArr: [],
            }
        },

        methods: {
            merageInit() {
                this.typeNameArr = [];
                this.typeNamePos = 0;
                this.storeArr = [];
                this.storePos = 0;
                this.feeArr = [];
                this.feePos = 0;
            },
            merage() {
                this.merageInit();
                for (let i = 0; i < this.zlrow.length; i += 1) {
                    if (i === 0) {
                        // 第一行必须存在
                        this.typeNameArr.push(1);
                        this.typeNamePos = 0;
                        this.storeArr.push(1);
                        this.storePos = 0;
                        this.feeArr.push(1);
                        this.feePos = 0;
                    } else {
                        // 判断当前元素与上一个元素是否相同,eg：this.typeNamePos 是 this.typeNameArr序号
                        // 第一列
                        // eslint-disable-next-line no-lonely-if
                        if (this.zlrow[i].parentname === this.zlrow[i - 1].parentname) {
                            this.typeNameArr[this.typeNamePos] += 1;
                            this.typeNameArr.push(0);
                        } else {
                            this.typeNameArr.push(1);
                            this.typeNamePos = i;
                        }
                    }
                }
            },
            objectSpanMethod({row, column, rowIndex, columnIndex}){
                if (columnIndex === 0) {
                    // 第一列的合并方法
                    const row1 = this.typeNameArr[rowIndex];
                    const col1 = row1 > 0 ? 1 : 0; // 如果被合并了row = 0; 则他这个列需要取消
                    return {
                        rowspan: row1,
                        colspan: col1,
                    };
                }
            },
            //获取资料总览
            getZlzl_vue() {
                let params = {
                    xmid: this.xmid,
                };
                getZlzl(params).then((res) => {
                    if (res.data != null && res.data.row != null) {
                        this.zlrow = res.data.row;
                        this.merage();
                    }
                })
            },
            toDetial_vue(index, row) {
                this.dialogTableVisible = true;
                let params = {
                    id: row.id,
                    parentid: row.parentid,
                    xmid: this.xmid
                }
                toDetial(params).then((res) => {
                    if (res.data != null && res.data.row != null) {
                        this.gridData = res.data.row;
                    }
                })
            },
            downloadFile_vue(index, row, flag) {
                let formData = new FormData();
                formData.append("path", row.fjlj);
                download_sjst(formData).then((res)=>{
                    if (flag == 1) {
                        this.slide1 = [];
                        let reader = new FileReader()
                        reader.onload = (e) => {
                            let item = {};
                            item.msrc = item.src = e.target.result;
                            let img = new Image();
                            img.src =e.target.result;
                            // this.Imgsrc = e.target.result;
                            item.h = img.height;
                            item.w = img.width;
                            this.slide1.push(item);
                            this.$preview.openList(0, this.slide1);
                            //this.msgFlag = true;
                            //this.prop.src = this.Imgsrc
                        }
                        reader.readAsDataURL(res.data)
                    } else {
                        const content = res.data
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
        components: {
            'XMJBXX': XMJBXX  //将别名demo 变成 组件 Demo
        }
    }

</script>

<style scoped>

</style>