<template>
	<section>
		<el-form  :model="fmdzjForm"  ref="fmdzjForm" :rules="rules">
			<el-input style="display: none" v-model="fmdzjForm.id"  ></el-input>
			<el-input style="display: none" v-model="fmdzjForm.fmid"  ></el-input>
			<el-form-item>
				<el-col :span="3" style="text-align: right">
					<span>点志记编号：</span>
				</el-col>
				<el-col :span="9">
					<el-input :disabled="true" v-model="fmdzjForm.dzjbh" v-if="fmdzjForm.dzjbh!=null&&fmdzjForm.dzjbh!=''" size="small"></el-input>
					<el-input :disabled="true" v-else value="保存后自动生成" size="small" ></el-input>
				</el-col>
				<el-col :span="4"style="text-align: right">
					<span>点志记状态：</span>
				</el-col>
				<el-col :span="8">
					<label v-if="fmdzjForm.zt=='0'">正在制单</label>
					<label v-if="fmdzjForm.zt=='1'">准备移交</label>
					<label v-if="fmdzjForm.zt=='2'">单据已传</label>
					<label v-if="fmdzjForm.zt=='3'">作废终止</label>
					<label v-if="fmdzjForm.zt=='4'">交接完成</label>
					<label v-if="fmdzjForm.zt=='5'">区所已收</label>
				</el-col>
			</el-form-item>
			<el-form-item>
				<el-col :span="3" style="text-align: right">
					<span>路名：</span>
				</el-col>
				<el-col :span="21">
					<el-form-item prop="lm">
						<el-input v-model="fmdzjForm.lm" size="small"></el-input></el-form-item>
				</el-col>
			</el-form-item>
			<el-form-item>
				<el-col :span="3" style="text-align: right">
					<span>阀门编号：</span>
				</el-col>
				<el-col :span="5">
					<el-form-item prop="fmbh">
						<el-input :readonly="true" v-model="fmdzjForm.fmbh" size="small"></el-input></el-form-item>
				</el-col>
				<el-col :span="5">
					<el-row>
						<el-col :span="12">
							<el-button type="primary"size="mini" style="margin-left: 5px" @click="openFm()"><i class="iconfont icon-xuanze" style="font-size: 12px"></i>选择</el-button>
						</el-col>
						<el-col :span="12">
							<el-button type="primary"size="mini" v-if="fmdzjForm.fmbh!=''"style="margin-left: 5px " @click="openFmhandle()"><i class="iconfont icon-ai-edit" style="font-size: 12px"></i>编辑</el-button>
						</el-col>
					</el-row>


				</el-col>

				<el-col :span="3" style="text-align: right">
					<span>阀门口径：</span>
				</el-col>
				<el-col :span="8">
					<el-input :readonly="true" v-model="fmdzjForm.fmkj" size="small"></el-input>
				</el-col>

			</el-form-item>
			<el-form-item>
				<el-col :span="3" style="text-align: right">
					<span>阀门类型：</span>
				</el-col>
				<el-col :span="9">
					<el-input :readonly="true" v-model="fmdzjForm.fmlx" size="small"></el-input>
				</el-col>
				<el-col :span="4" style="text-align: right">
					<span>阀门型号：</span>
				</el-col>
				<el-col :span="8">
					<el-input :readonly="true" v-model="fmdzjForm.fmxh" size="small"></el-input>
				</el-col>
			</el-form-item>
			<el-form-item>
				<el-col :span="3" style="text-align: right">
					<span>阀杆方身：</span>
				</el-col>
				<el-col :span="9">
					<el-input :readonly="true" v-model="fmdzjForm.fgfs" size="small"></el-input>
				</el-col>
				<el-col :span="4" style="text-align: right">
					<span>转动方式：</span>
				</el-col>
				<el-col :span="8">
					<el-input :readonly="true" v-model="fmdzjForm.zdfs" size="small"></el-input>
				</el-col>
			</el-form-item>
			<el-form-item>
				<el-col :span="3" style="text-align: right">
					<span>阀杆深度：</span>
				</el-col>
				<el-col :span="9">
					<el-input :readonly="true" v-model="fmdzjForm.fgsd" size="small"></el-input>
				</el-col>
				<el-col :span="4" style="text-align: right">
					<span>转动方向：</span>
				</el-col>
				<el-col :span="8">
					<el-input :readonly="true" v-model="fmdzjForm.zdfx" size="small"></el-input>
				</el-col>
			</el-form-item>
			<el-form-item>
				<el-col :span="3" style="text-align: right">
					<span>阀杆材质：</span>
				</el-col>
				<el-col :span="9">
					<el-input :readonly="true" v-model="fmdzjForm.fgcz" size="small"></el-input>
				</el-col>
				<el-col :span="4" style="text-align: right">
					<span>阀门作用：</span>
				</el-col>
				<el-col :span="8">
					<el-input :readonly="true" v-model="fmdzjForm.fmzy" size="small"></el-input>
				</el-col>
			</el-form-item>
			<el-form-item>
				<el-col :span="3" style="text-align: right">
					<span>安装日期：</span>
				</el-col>
				<el-col :span="9">
					<el-form-item prop="azrq_vue">
					<el-date-picker format="yyyy-MM-dd"value-format="yyyy-MM-dd" size="small" type="date" placeholder="选择日期" v-model="fmdzjForm.azrq_vue" style="width: 100%"></el-date-picker>
					</el-form-item>
				</el-col>
				<el-col :span="4" style="text-align: right">
					<span>绘图人：</span>
				</el-col>
				<el-col :span="8">
					<el-input v-model="fmdzjForm.htr" placeholder="为空时保存当前登录人" size="small"></el-input>
				</el-col>
			</el-form-item>
			<el-form-item>
				<el-col :span="3" style="text-align: right">
					<span>点志记插图：</span>
				</el-col>
                <el-col :span="21">
                    <el-upload
                            class="upload-demo"
                            ref="upload"
                            action="#"
							accept=".jpg,.png,.JPG,.PNG"
                            :on-preview="handlePreview"
							:http-request="submitUpload"
                            :on-remove="handleRemove"
                            :on-change="handleChange"
                            :file-list="fileList"
                            :auto-upload="true"
							 :show-file-list=false>
                        <el-button slot="trigger" size="mini" type="success" ><i class="iconfont icon-shangchuan_f"></i>上传</el-button>
                        <!--<el-button style="margin-left: 10px;" size="small" type="primary" @click="submitUpload" icon="el-icon-upload">上传</el-button>-->
                        <el-button size="mini" type="success" style="margin-left: 10px" @click="download"><i class="iconfont icon-xiazai_f"></i>下载</el-button>
                        <el-button size="mini" type="primary" style="margin-left: 10px" @click="look"><i class="iconfont icon-yulan"></i>预览</el-button>
                        <label>附件（{{fjnumber}}）份</label>
                        <div slot="tip" class="el-upload__tip"></div>
                    </el-upload>

                </el-col>
			</el-form-item>
		</el-form>
		<div slot="footer" class="dialog-footer" style="text-align: center;padding-bottom: 20px" >
			<el-button type="info" size="mini" @click="cancer()"><i class="iconfont icon-quxiao"></i>取 消</el-button>
			<el-button type="primary" size="mini" @click="fmdzjxxwh_vue('fmdzjForm')"><i class="iconfont icon-queren"></i>确 定</el-button>
		</div>
		<el-dialog title="阀门信息录入"  :visible.sync="fmpzDialogFormVisible" width="50%" :append-to-body="true" :close-on-click-modal="false">
			<fmgl :id="fmdzjForm.fmid" :xmid="xmid" @childConcer="closeFmDialog"></fmgl>
		</el-dialog>
		<el-dialog  title="阀门信息"  :visible.sync="fmDialogFormVisible" width="80%" :append-to-body="true" :close-on-click-modal="false">
			<el-table :data="Data"style="width: 100%" border :row-class-name="tableRowClassName">
				<el-table-column type="index" label="序号" width="80" align="center">
				</el-table-column>
				<el-table-column
						prop="fmbh"
						label="阀门编号"
						align="center"
						width="150">
				</el-table-column>
				<el-table-column
						prop="azrq_vue"
						label="安装日期"
						align="center"
						width="100">
				</el-table-column>
				<el-table-column
						prop="fmkj"
						label="阀门口径"
						align="center">
				</el-table-column>
				<el-table-column
						prop="fmlx"
						label="阀门类型"
						align="center">
				</el-table-column>
				<el-table-column
						prop="fmcj"
						label="阀门厂家"
						align="center">
				</el-table-column>
				<el-table-column
						prop="fmxh"
						label="阀门型号"
						align="center">
				</el-table-column>
				<el-table-column
						prop="czsj_vue"
						label="操作时间"
						align="center"
						width="100">
				</el-table-column>
				<el-table-column
						prop="czrname"
						label="操作人"
						align="center">
				</el-table-column>
				<el-table-column
						prop="bz"
						label="备注"
						align="center">
				</el-table-column>
				<el-table-column
						prop="yzdzj"
						label="已制点志记"
						align="center"
						width="120"
						:formatter="yzdzjFormatter">
				</el-table-column>
				<el-table-column  label="操作" align="center"width="150" fixed="right">
					<template slot-scope="scope">
						<label title="选择" ><i @click="chooseFm(scope.$index, scope.row)" class="iconfont icon-xuanze" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
					</template>
				</el-table-column>
			</el-table>
			<el-row>
				<!--工具条-->
				<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[5,10,20,50]"
							   @current-change="handleCurrentChange" @size-change="handleSizeChange":current-page.sync="this.page"
							   :page-size="this.size" style="float:right;" :total="this.total">
				</el-pagination>
			</el-row>
		</el-dialog>
	</section>
</template>

<script>
	import fmgl from '@/views/yj/bwyj/dzjnrsjgl/fmxxwh.vue';
	import {getfmdzjData,fmpzHandle,getFmData,fmdzjxxwh,saveDzjfile,download} from '../../../../api/bwyj';
	export default {
		props:{
			xmid:{
				type: String,
				required: true
			},
			id:{
				type: String,
				required: true
			}
		},
		components: {
			'fmgl': fmgl
		},
		data() {
			return {
				rules:{
					lm:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],

					azrq_vue:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],

					fmbh:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					]},
                imgBase64:"",
                slide1:[],
                path:"",
                fileList:[],
				fmpzDialogFormVisible:false,
				//fmdzjDialogFormVisible:false,
				fmDialogFormVisible:false,
				//xmid:"1",
				//id:"",
                Data:[],
				total: 0,//表格数据总数量
				page: 1,//当前页码
				size: 10,//每页容量
				fjnumber:0,
				fmdzjForm:{
					fmid:"",
					id:"",
					dzjbh:"",
					zt:"0",
					lm:"",
					fmbh:"",
					fmkj:"",
					fmlx:"",
					fmxh:"",
					fgfs:"",
					zdfs:"",
					fgsd:"",
					zdfx:"",
					fgcz:"",
					fmzy:"",
					azrq_vue:"",
					htr:""
				},
			}
		},
		methods: {
			tableRowClassName({row, rowIndex}){
				if(row.fmbh==this.fmdzjForm.fmbh){
					return 'success-row';
				}
				return '';
			},

			//监听子组件
			closeFmDialog(result){
					let param={
						id:result
					}
					fmpzHandle(param).then((res)=>{
						if(res.data!=null){
							this.fmdzjForm.fmbh = res.data.row[0].fmbh;
							this.fmdzjForm.fmid = res.data.row[0].id;
							this.fmdzjForm.fmkj = res.data.row[0].fmkj;
							this.fmdzjForm.fmlx = res.data.row[0].fmlx;
							this.fmdzjForm.fmxh = res.data.row[0].fmxh;
							this.fmdzjForm.fgfs = res.data.row[0].fgfs;
							this.fmdzjForm.zdfs = res.data.row[0].zdfs;
							this.fmdzjForm.fgsd = res.data.row[0].fgsd;
							this.fmdzjForm.zdfx = res.data.row[0].zdfx;
							this.fmdzjForm.fgcz = res.data.row[0].fgcz;
							this.fmdzjForm.fmzy = res.data.row[0].fmzy;
							this.fmpzDialogFormVisible = false;
						}
					})

			},
			//转换已制点志记格式
			yzdzjFormatter(row, column){
				if(row.yzdzj){
					return "是";
				}else{
					return "否";
				}
			},
			//打开阀门编辑dialog
			openFmhandle(){

				this.fmpzDialogFormVisible=true;
			},
			openFm(){
				this.getFmData_vue();
			},
			//获取阀门
			getFmData_vue() {
				let params = {
					page: this.page,
					size: this.size,
					xmid: this.xmid
				}
				getFmData(params).then((res) => {
					if (res.data != null) {
						this.Data = res.data.row;
						this.total = res.data.total;
						this.fmDialogFormVisible=true
					}
				})
			},
			handleCurrentChange(val){
				this.page = val;
				this.getFmData_vue();
			},
			handleSizeChange(val) {
				this.size = val;
				this.page=1;
				this.getFmData_vue();
			},
			chooseFm(index,row){
				this.fmdzjForm.fmbh = row.fmbh;
				this.fmdzjForm.fmid = row.id;
				this.fmdzjForm.fmkj = row.fmkj;
				this.fmdzjForm.fmlx = row.fmlx;
				this.fmdzjForm.fmxh = row.fmxh;
				this.fmdzjForm.fgfs = row.fgfs;
				this.fmdzjForm.zdfs = row.zdfs;
				this.fmdzjForm.fgsd = row.fgsd;
				this.fmdzjForm.zdfx = row.zdfx;
				this.fmdzjForm.fgcz = row.fgcz;
				this.fmdzjForm.fmzy = row.fmzy;
				this.fmDialogFormVisible=false;
			},
			getfmdzjData_vue(){
				if(this.id==""){
					this.fmdzjForm={
						fmid:"",
						id:"",
						dzjbh:"",
						zt:"0",
						lm:"",
						fmbh:"",
						fmkj:"",
						fmlx:"",
						fmxh:"",
						fgfs:"",
						zdfs:"",
						fgsd:"",
						zdfx:"",
						fgcz:"",
						fmzy:"",
						azrq_vue:"",
						htr:""
					}
					this.fmdzjDialogFormVisible=true
				}else{
					let params={
						id:this.id
					}
					getfmdzjData(params).then((res)=>{
						if(res.data!=null){
							let param={
								id:res.data.row[0].fmid
							}
							fmpzHandle(param).then((ress)=>{
								if(ress.data!=null){
									//this.fmdzjForm = ress.data.row[0];
									this.fmdzjForm.fmkj=ress.data.row[0].fmkj;
									this.fmdzjForm.fmlx=ress.data.row[0].fmlx;
									this.fmdzjForm.fmxh=ress.data.row[0].fmxh;
									this.fmdzjForm.fgfs=ress.data.row[0].fgfs;
									this.fmdzjForm.zdfs=ress.data.row[0].zdfs;
									this.fmdzjForm.fgsd=ress.data.row[0].fgsd;
									this.fmdzjForm.zdfx=ress.data.row[0].zdfx;
									this.fmdzjForm.fgcz=ress.data.row[0].fgcz;
									this.fmdzjForm.fmzy=ress.data.row[0].fmzy;
									this.fmdzjForm.fmid=res.data.row[0].fmid
									this.fmdzjForm.id = res.data.row[0].id;
									this.fmdzjForm.dzjbh = res.data.row[0].dzjbh;
									this.fmdzjForm.zt = res.data.row[0].zt;
									this.fmdzjForm.lm = res.data.row[0].lm;
									this.fmdzjForm.fmbh = ress.data.row[0].fmbh;
									this.fmdzjForm.azrq_vue = res.data.row[0].azrq_vue;
									this.fmdzjForm.htr = res.data.row[0].htr;
									this.fjnumber = res.data.row[0].fjnumber;
									this.path = res.data.row[0].path;
								}
							})
						}
					})
				}
			},
        //    阀门点志记参数验证
            checkdzjParams(){
                if (this.fmdzjForm.fmbh == "") {
                    this.$message.error('请选择阀门！');
                    return false;
                }
                if (this.fmdzjForm.lm == "") {
                    this.$message.error('路名不可为空！');
                    return false;
                }
                if(this.fmdzjForm.azrq_vue==""){
                    this.$message.error('安装日期不可为空！');
                    return false;
                }

                return true;
            },
        //阀门点志记信息维护
            fmdzjxxwh_vue(formName){
			    // let checkdzjParams = this.checkdzjParams();
			    // if(!checkdzjParams){
			    //     return;
                // }
				this.$refs[formName].validate((valid) => {
					if (valid) {
						let params={
							id:this.id,
							dzjbh:this.fmdzjForm.dzjbh,
							fmid:this.fmdzjForm.fmid,
							lm:this.fmdzjForm.lm,
							azrq_vue:this.fmdzjForm.azrq_vue,
							zt:this.fmdzjForm.zt,
							htr:this.fmdzjForm.htr,
							xmid:this.xmid,
							dzjlxbh:"02",
							dzjcbbh:"05",
							fbjj:"0"
						}
						fmdzjxxwh(params).then((res)=>{
							if(res.data){
								this.$message.success('保存成功！');
								this.$emit('childConcer', true);
							}else{
								this.$message.error('保存失败！');
							}
						})
					} else {
						return false;
					}
				});

            },
        //    取消
            cancer(){
                this.$emit('childConcer', true);
            },
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
            },
            handleChange(file,fileList){
                this.fileList = fileList;
            },
            beforeRemove(file, fileList) {
                return this.$confirm(`确定移除 ${ file.name }？`);
            },
            submitUpload() {
                let fileList = this.fileList;
                if(fileList.length>0){
                    let file = fileList[0];
                    let filetype = file.name.split(".")[file.name.split(".").length-1];
                    if(filetype.toLowerCase()=='jpg'||filetype.toLowerCase()=='png') {
                        if(this.fjnumber>0){
                            this.$confirm('已存在一条图片,继续上传将覆盖上一张图片, 是否继续?', '提示', {
                                confirmButtonText: '确定',
                                cancelButtonText: '取消',
                                type: 'warning'
                            }).then(() => {
                                let formData = new FormData();
                                formData.append("dzjid",this.id);
                                formData.append("multipartFiles",file.raw);
                                formData.append("filename",file.name);
                                formData.append("fjlxej","xhsdzj");
                                formData.append("fjnumber",this.fjnumber);
								 formData.append("xmid",this.xmid);
                                saveDzjfile(formData).then((res) => {
                                    let msg = res.msg;
                                    if(msg == '上传成功'){
                                        this.$message({
                                            message: '上传成功',
                                            type: 'success'
                                        });
                                        this.path = res.path;
                                        this.id = res.dzjid;
                                        this.fjnumber=1;
										this.fileList=[];
                                    }else{
										this.fileList=[];
                                        this.$message.error('上传失败！');
                                    }
                                });
                            }).catch(() => {
                                return;
                            });
                        }else{
                            let formData = new FormData();
                            formData.append("dzjid", this.id);
                            formData.append("multipartFiles", file.raw);
                            formData.append("filename", file.name);
                            formData.append("fjlxej", "fmdzj");
                            formData.append("fjnumber", this.fjnumber);
							formData.append("xmid",this.xmid);
                            saveDzjfile(formData).then((res) => {
                                let msg = res.msg;
                                if (msg == '上传成功') {
                                    this.$message({
                                        message: '上传成功',
                                        type: 'success'
                                    });
                                    this.path = res.path;
                                    this.id = res.dzjid;
                                    this.fjnumber=1;
									this.fileList=[];
                                } else {
									this.fileList=[];
                                    this.$message.error('上传失败！');
                                }
                            });
                        }

                    }else{
						this.fileList=[];
                        this.$message.warning('只能上传png/jpg格式文件！');
                        return;
                    }
                }else {
                    this.$message.warning('未选择任何文件！');
                    return;
                }
            },
            download(){//附件查看或下载
                let formData = new FormData();
                if(this.path==''){
                    this.$message.warning('未上传任何文件！');
                    return;
                }
                formData.append("path",this.path);
                download(formData).then((res) => { // 处理返回的文件流
                    const content = res.data
                    const blob = new Blob([content])
                        let files = this.path.split("/");
                        const fileName = files[files.length-1];
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
                })
            },
            look(){
                let formData = new FormData();
                if(this.path==''){
                    this.$message.warning('未上传任何文件！');
                    return;
                }
				this.slide1 = [];
                formData.append("path",this.path);
                download(formData).then((res) => { // 处理返回的文件流
                    let reader = new FileReader()
                    reader.onload = (e) => {
                        let img = new Image();
                        img.src =e.target.result;
                        let item = {};
                        item.msrc = item.src = e.target.result;
                        // this.Imgsrc = e.target.result;
                        item.h = img.height;
                        item.w = img.width;
                        this.slide1.push(item);
                        this.$preview.openList(0, this.slide1);
                        //this.msgFlag = true;
                        //this.prop.src = this.Imgsrc
                    }
                    reader.readAsDataURL(res.data)
                })
            }
		},
		mounted() {
			this.getfmdzjData_vue();
		}
	}

</script>

<style>
    .el-form-item {
        margin-bottom: 10px;
    }
	.el-table .success-row {
		background: #f0f9eb;
	}
</style>