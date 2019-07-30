<template>
	<section>
		<el-dialog title="楼栋列表"  :visible.sync="gclgdzjDialogFormVisible" width="30%" :append-to-body="true" :close-on-click-modal="false">
			<el-row>
				<el-table :data="row" style="width: 100%" border :row-class-name="tableRowClassName">
                    <el-table-column type="index" label="序号" width="50" align="center">
					</el-table-column>
                     <el-table-column prop="id" v-if="show">
					</el-table-column>
					<el-table-column
                            width="100"
							prop="ldbh"
							label="楼栋号"
							align="center">
					</el-table-column>
                    <el-table-column  label="操作" align="center" width="150" fixed="right">
                        <template slot-scope="scope">
							<label title="选择" ><i @click="ldselect_vue(scope.$index, scope.row,scope.id)" class="iconfont icon-xuanze" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                        </template>
                    </el-table-column>
				</el-table>
				<el-col :span="24" class="toolbar" >
					<!--工具条-->
					<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[5,10,20,50]"
								   @current-change="handleCurrentChange" @size-change="handleSizeChange":current-page.sync="this.page"
								   :page-size="this.size" style="float:right;" :total="this.total">
					</el-pagination>
				</el-col>
			</el-row>
		</el-dialog>
		<el-form  :model="gclgdzjForm" ref="gclgdzjForm" :rules="rules">
				<el-input style="display: none" v-model="gclgdzjForm.id"  ></el-input>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>点志记编号：</span>
					</el-col>
					<el-col :span="9">
						<el-input readonly v-model="gclgdzjForm.dzjbh" v-if="gclgdzjForm.dzjbh!=null&&gclgdzjForm.dzjbh!=''" size="small" ></el-input>
						<el-input readonly v-else value="保存后自动生成"  size="small"></el-input>
					</el-col>
					<el-col :span="3" style="text-align: right" >
						<span>点志记状态：</span>
					</el-col>
					<el-col :span="9">
                        <label v-if="gclgdzjForm.zt=='0'">正在制单</label>
                        <label v-if="gclgdzjForm.zt=='1'">准备移交</label>
                        <label v-if="gclgdzjForm.zt=='2'">单据已传</label>
                        <label v-if="gclgdzjForm.zt=='3'">作废终止</label>
                        <label v-if="gclgdzjForm.zt=='4'">交接完成</label>
                        <label v-if="gclgdzjForm.zt=='5'">区所已收</label>
					</el-col>

				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>路名：</span>
					</el-col>
					<el-col :span="21">
						<el-form-item prop="lm">
							<el-input v-model="gclgdzjForm.lm" size="small"></el-input></el-form-item>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>楼栋号：</span>
					</el-col>
					<el-col :span="5">
						<el-form-item prop="ldbh">
							<el-input readonly v-model="gclgdzjForm.ldbh"  size="small" ></el-input></el-form-item>
					</el-col>
					<el-col :span="4">
                        <el-row>
                            <el-col :span="12">
                                <el-button style="margin-left:10px;" size="mini" type="primary" @click="dzjxz_vue"><i class="iconfont icon-xuanze" style="font-size: 12px"></i>选择</el-button>
                            </el-col>
                        </el-row>
					</el-col>
					<!-- <el-col :span="3" style="text-align: right">
						<span>型号：</span>
					</el-col>
					<el-col :span="9">
						<el-input readonly v-model="gclgdzjForm.xhbhmc"  ></el-input>
					</el-col> -->
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>安装日期：</span>
					</el-col>
					<el-col :span="9">
						<el-form-item prop="azrq_vue" :rules="this.publicUtil.notNull">
							<el-date-picker size="small" format="yyyy-MM-dd" value-format="yyyy-MM-dd" :editable="false" type="date" placeholder="选择日期" v-model="gclgdzjForm.azrq_vue" style="width: 100%"></el-date-picker>
						</el-form-item>
					</el-col>
					<!-- <el-col :span="3" style="text-align: right">
						<span>填表日期：</span>
					</el-col>
					<el-col :span="9">
						<el-date-picker :editable="false" type="date" placeholder="选择日期" v-model="gclgdzjForm.czrq_vue" style="width: 100%"></el-date-picker>
					</el-col> -->
					<el-col :span="3" style="text-align: right">
						<span>绘图人：</span>
					</el-col>
					<el-col :span="9">
						<el-input v-model="gclgdzjForm.htr" placeholder="为空时保存当前登录人" size="small" ></el-input>
					</el-col>
				</el-form-item>
				<el-form-item>
                    <!-- <el-col :span="3" style="text-align: right">
						<span>操作人：</span>
					</el-col>
					<el-col :span="9">
						<el-input v-model="gclgdzjForm.czr" placeholder="为空时保存当前登录人" ></el-input>
					</el-col> -->
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
			<div slot="footer" class="dialog-footer" style="text-align: center;padding-bottom: 10px">
				<el-button type="info" size="mini" @click="cancer()"><i class="iconfont icon-quxiao"></i>取 消</el-button>
				<el-button type="primary" size="mini" @click="Lddzjxxwh_vue('gclgdzjForm')"><i class="iconfont icon-queren"></i>确 定</el-button>
			</div>



	</section>
</template>
<script>
	import { getldData,xhsxxwh,getxhsxh,getXhsazfs,xhspzHandle,getSb,fmdzjxxwh,xhsdzjData,download,saveDzjfile,LddzjData,LdhxData } from '../../../../api/bwyj';

	export default {
        props:{
            xmid:{
                type: String,
                required: true
            },
            dzjid:{
                type: String,
                required: true
            }
        },
		data() {
			return {
				rules:{
					lm:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],

					ldbh:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					]},
				slide1:[],
                show:false,
				readonly:true,
				fjnumber:0,
				total: 10,//表格数据总数量
				page: 1,//当前页码
				size: 10,//每页容量
				 //xmid:'1',//项目id
				 //dzjid:'',//点志记id
				ldid:'',//楼栋id
                path:'',//下载路径
				row:[],//楼栋数据
                fileList:[],//文件列表
				filerow:[],//文件列表数据
				// SbData:[],//水表列表数据
				// xhsazfsOptions:[],//消火栓安装方式下拉数据
				// xhsxhOptions:[],//消火栓型号下拉数据
				SbDialogFormVisible:false,
				xhspzDialogFormVisible:false,
                xhslbDialogFormVisible:false,
                gclgdzjDialogFormVisible:false,
				ztOptions:[{
					codevalue: '0',
					codename: '正在制单'
				},{
					codevalue: '1',
					codename: '准备移交'
				},{
					codevalue: '2',
					codename: '单据已传'
				},{
					codevalue: '3',
					codename: '作废终止'
				},{
					codevalue: '4',
					codename: '交接完成'
				},{
					codevalue: '5',
					codename: '区所已收'
				}],
				LdForm:{
					id:"",
					dzjbh:"",
					dzjlxbh:"03",
					zt:"0",
					lm:"",
					ldbh:"",
                    azrq_vue:"",
                    czrq_vue:"",
					htr:"",
					czr:"",
					ldid:this.ldid
				},
				gclgdzjForm:{
					id:"",
					dzjbh:"",
					dzjlxbh:"03",
					zt:"0",
					lm:"",
					ldbh:"",
                    azrq_vue:"",
                    czrq_vue:"",
					htr:"",
					czr:"",
					ldid:this.ldid//楼栋id
				},
			}
		},
		methods: {
			tableRowClassName({row, rowIndex}){
				if(row.ldbh==this.gclgdzjForm.ldbh){
					return 'success-row';
				}
				return '';
			},
			bzhyflFormatter(row, column){
				switch (row.hhlxbh) {
					case 'HB':
						return "户表"
						break;
					case 'WJJLB':
						return "五级计量表"
						break;
					case 'DB':
						return "地表"
						break;
					default:
						break;
				}
			},
            //转换已制点志记格式
            yzdzjFormatter(row, column){
                if(row.yzdzj){
                    return "是";
                }else{
                    return "否";
                }
            },
			//获取楼栋
			getldData_vue(){
				let params={
					page: this.page,
					size: this.size,
                	xmid:this.xmid
				}
				getldData(params).then((res)=>{
					if(res.data!=null){
						this.row=res.data.row;
						this.total=res.data.total;
					}
				})
			},
			handleCurrentChange(val){
				this.page = val;
				this.getldData_vue();
			},
			CurrentChange(val){
				this.page = val;
				this.openSb_vue();
			},
			handleSizeChange(val) {
				this.size = val;
				this.page=1;
				this.getFmData_vue();
			},
			//选择楼栋按钮
			dzjxz_vue(){
				this.getldData_vue();
				this.gclgdzjDialogFormVisible=true;
			},
			//确认楼栋选择
			ldselect_vue(index,row){
				this.gclgdzjForm.ldbh = row.ldbh;
				this.ldid = row.id;
				this.gclgdzjDialogFormVisible=false;
			},
			cancer(){
				this.$emit('childConcer', true);
			},
			// checkxhsParams(){
			// 	if(this.xhsForm.xhscj==""){
			// 		this.$message.error('消火栓厂家不可为空！');
			// 		return false;
			// 	}
			// 	if(this.xhsForm.azrq_vue==""){
			// 		this.$message.error('安装日期不可为空！');
			// 		return false;
			// 	}
			// 	if(this.xhsForm.xhsxh==""){
			// 		this.$message.error('消火栓型号不可为空！');
			// 		return false;
			// 	}
			// 	if(this.xhsForm.azfs==""){
			// 		this.$message.error('安装方式不可为空！');
			// 		return false;
			// 	}
			// 	if(this.xhsForm.gps==""){
			// 		this.$message.error('GPS数据不可为空！');
			// 		return false;
			// 	}
			// 	return true;
			// },
			//楼栋参数验证
			checkdzjParams(){
				// if (this.gclgdzjForm.ldbh == "") {
				// 	this.$message.error('请选择楼栋！');
				// 	return false;
				// }
				if (this.gclgdzjForm.lm == "") {
					this.$message.error('路名不可为空！');
					return false;
				}
				// if(this.gclgdzjForm.czrq_vue==""){
				// 	this.$message.error('填表日期不可为空！');
				// 	return false;
                // }
                if(this.gclgdzjForm.azrq_vue==""){
					this.$message.error('安装日期不可为空！');
					return false;
				}

				return true;
			},
			//	保存点志记管理
			Lddzjxxwh_vue(formName){
				// let checkdzjParams = this.checkdzjParams();
				// if(!checkdzjParams){
				// 	return;
				// }
				this.$refs[formName].validate((valid) => {
					if (valid) {
						let param={
							id:this.dzjid,
							dzjbh:this.gclgdzjForm.dzjbh,
							ldid:this.ldid,
							lm:this.gclgdzjForm.lm,
							xmid:this.xmid,
							zt:"0",
							azrq_vue:this.gclgdzjForm.azrq_vue,
							ldbh:this.gclgdzjForm.ldbh,
							htr:this.gclgdzjForm.htr,
							dzjlxbh:"04",
							dzjcbbh:"06",
							fbjj:"0",
						}
						let params = Object.assign(param);
						fmdzjxxwh(params).then((res)=>{
							if(res.data){
								this.$emit('childConcer', true);
								this.$message({
									message: '保存成功！',
									type: 'success'
								});
							}else{
								this.$message.error('保存失败！');
							}
						})
					} else {
						return false;
					}
				});

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
            //清空楼栋点志记
            clearLddzj(){
                this.gclgdzjForm={
                       id:"",
					dzjbh:"",
					dzjlxbh:"03",
					zt:"0",
					lm:"",
					ldbh:"",
                    azrq_vue:"",
                    czrq_vue:"",
					htr:"",
					czr:"",
					ldid:""
                }
            },
            //楼栋点志记数据回显
            LddzjData_vue(){
			  if(this.dzjid==""){
			      this.clearLddzj();
			      this.ldid="";
              }else{
			      let params={
			          dzjid : this.dzjid
                  }
                  LddzjData(params).then((res)=>{
                      this.gclgdzjForm.id = res.data.row[0].dzjid;
                      this.gclgdzjForm.dzjbh = res.data.row[0].dzjbh;
                      this.gclgdzjForm.lm = res.data.row[0].lm;
                      this.gclgdzjForm.zt = res.data.row[0].zt;
                      this.gclgdzjForm.ldbh = res.data.row[0].ldbh;
                      this.gclgdzjForm.azrq_vue = res.data.row[0].azrq_vue;
                      this.gclgdzjForm.czrq_vue = res.data.row[0].czrq_vue;
                      this.gclgdzjForm.htr = res.data.row[0].htr;
                      this.gclgdzjForm.cjz = res.data.row[0].cjz;
					//this.gclgdzjForm.ldid = res.data.row[0].ldid;
					  this.ldid = res.data.row[0].id;
					  this.fjnumber = res.data.row[0].fjnumber;
					  this.path = res.data.row[0].path;
                  })
              }
            },

	submitUpload() {
				let fileList = this.fileList;
				if(fileList.length>0){
					let file = fileList[0];
					let filetype = file.name.split(".")[file.name.split(".").length-1];
					if(filetype.toLowerCase()=='jpg'||filetype.toLowerCase()=='png'){
						if(this.fjnumber>0){
							this.$confirm('已存在一条图片,继续上传将覆盖上一张图片, 是否继续?', '提示', {
								confirmButtonText: '确定',
								cancelButtonText: '取消',
								type: 'warning'
							}).then(() => {
								let formData = new FormData();
								formData.append("xmid",this.xmid);
								formData.append("dzjid",this.dzjid);
								formData.append("multipartFiles",file.raw);
								formData.append("filename",file.name);
								formData.append("fjlxej","gclgdzj");
								formData.append("fjnumber",this.fjnumber);
								saveDzjfile(formData).then((res) => {
									let msg = res.msg;
									if(msg == '上传成功'){
										this.$message({
											message: '上传成功',
											type: 'success'
										});
										this.path = res.path;
										this.dzjid = res.dzjid;
										this.fjnumber=1;
									}else{
										this.$message.error('上传失败！');
									}
									this.fileList=[];
								});
							}).catch(() => {
								return;
							});
						}else{
							let formData = new FormData();
							formData.append("xmid",this.xmid);
							formData.append("dzjid",this.dzjid);
							formData.append("multipartFiles",file.raw);
							formData.append("filename",file.name);
							formData.append("fjlxej","gclgdzj");
							formData.append("fjnumber",this.fjnumber);
							saveDzjfile(formData).then((res) => {
								let msg = res.msg;
								if(msg == '上传成功'){
									this.$message({
										message: '上传成功',
										type: 'success'
									});
									this.path = res.path;
									this.dzjid = res.dzjid;
									this.fjnumber=1;
								}else{
									this.$message.error('上传失败！');
								}
								this.fileList=[];
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
			LdhxData_vue() {
				let params = {
					id: this.xhsid
				}
				LdhxData(params).then((res) => {
					if (res.data != null) {
						this.LdForm = res.data.row[0]
						this.gclgdzjDialogFormVisible = true
					}
				})
			},
			look(){
				let formData = new FormData();
				if(this.path==''){
					this.$message.warning('未上传任何文件！');
					return;
				}
				this.slide1=[];
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
			},
			//    取消
			cancer(){
				this.$emit('childConcer', true);
			}
		},
		mounted() {
			 this.LddzjData_vue();
		}
	}
</script>

<style>
	.el-dialog__body {
		padding: 0 20px;
		color: #606266;
		font-size: 14px;
	}
	.el-table td, .el-table th {
		padding: 6px 0;
		min-width: 0;
		-webkit-box-sizing: border-box;
		box-sizing: border-box;
		text-overflow: ellipsis;
		vertical-align: middle;
		position: relative;
		text-align: left;
	}
	.el-input__inner{
		height: 40px;
	}
    .el-button--mini, .el-button--mini.is-round {
        padding: 7px 10px;
    }
	.el-table .success-row {
		background: #f0f9eb;
	}
</style>