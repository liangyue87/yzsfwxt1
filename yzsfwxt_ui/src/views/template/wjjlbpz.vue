<template>
	<section>
		<el-row style="margin-top: 15px;">
			<div class="text item">
				<el-button v-if="this.qb != '0'&&isxmtc!='2'" style="margin-bottom: 10px;" size="mini" type="primary" @click="dbpzAdd_vue()"><i class="iconfont icon-xinzeng"></i>新增五级计量表</el-button>
				<el-table :data="Data" style="width: 100%" :stripe="true">
					<el-table-column
							prop="bzlb"
							label="报装类别"
							align="center"
							:formatter="bzlbFormatter">
					</el-table-column>
					<el-table-column
							prop="hh"
							label="报装户号"
							align="center">
					</el-table-column>
					<el-table-column
							prop="hm"
							label="户名"
							align="center">
					</el-table-column>
					<el-table-column
							prop="dz"
							label="装表地址"
							align="center">
					</el-table-column>
					<el-table-column
							prop="kjname"
							label="口径"
							align="center">
					</el-table-column>
					<el-table-column
							prop="ysxzname"
							label="用水性质"
							align="center">
					</el-table-column>
					<el-table-column
							prop="hyflname"
							label="行业分类"
							align="center">
					</el-table-column><!--v-if="this.qb != '0'"-->
					<el-table-column  label="操作" align="center" width="180" >
						<template slot-scope="scope">
							<label title="编辑"><i @click="dbpzHandle_vue(scope.$index, scope.row)" class="iconfont icon-ai-edit" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
							<label title="删除" v-if="isxmtc!='2'"><i @click="dbpzDelete_vue(scope.$index, scope.row)" class="iconfont delete" style="font-size:18px;cursor:pointer;padding: 2px;color: #F56C6C"></i></label>
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
			</div>
		</el-row>
		<el-dialog title="五级计量表详细信息" :visible.sync="dbpzDialogFormVisible" width="50%" :close-on-click-modal="false">
			<el-form :model="dbpzForm" :rules="rules" :inline="true" class="demo-form-inline" ref="dbpzForm">
				<el-input style="display: none" v-model="dbpzForm.id"></el-input>
				<el-form-item label="报装类别" :label-position="labelPosition" style="margin-bottom: 15px" size="small" prop="bzlb" :label-width="formLabelWidth">
					<el-select v-model="dbpzForm.bzlb" style="width: 200px;" >
						<el-option
								v-for="item in bzlbOptions"
								:key="item.codevalue"
								:label="item.codename"
								:value="item.codevalue">
						</el-option>
					</el-select>
				</el-form-item>

				<el-form-item label="报装户号" :label-position="labelPosition" style="margin-bottom: 15px" size="small" :label-width="formLabelWidth">
					<el-input :disabled="true" v-model="dbpzForm.hh" v-if="dbpzForm.hh!=null&&dbpzForm.hh!=''" style="width: 200px;"></el-input>
					<el-input :disabled="true" v-else value="保存后自动生成" style="width: 200px;"></el-input>
				</el-form-item>
				<el-form-item label="户名" :label-position="labelPosition"  style="margin-bottom: 15px" size="small" :label-width="formLabelWidth" prop="hm" >
					<el-input v-model="dbpzForm.hm" style="width: 200px;"></el-input>
				</el-form-item>
				<el-form-item label="装表地址" :label-position="labelPosition" style="margin-bottom: 15px" size="small" :label-width="formLabelWidth" prop="dz" >
					<el-input v-model="dbpzForm.dz" style="width: 200px;"></el-input>
				</el-form-item>
				<el-form-item label="口径" :label-position="labelPosition" style="margin-bottom: 15px" size="small" :label-width="formLabelWidth" prop="kj" >
					<el-select v-model="dbpzForm.kjbh" style="width: 200px;">
						<el-option
								v-for="item in kjOptions"
								:key="item.codevalue"
								:label="item.codename"
								:value="item.codevalue">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="用水性质" :label-position="labelPosition" style="margin-bottom: 15px" size="small" :label-width="formLabelWidth" prop="ysxz" >
					<!--<el-select v-model="dbpzForm.ysxzbh" style="width: 200px;" @change="getHyflData_vue">-->
						<el-select v-model="dbpzForm.ysxzbh" style="width: 200px;" >
						<el-option
								v-for="item in ysxzOptions"
								:key="item.codevalue"
								:label="item.codename"
								:value="item.codevalue">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="行业分类" :label-position="labelPosition" style="margin-bottom: 15px" size="small" :label-width="formLabelWidth" prop="hyfl" >
					<!--<el-select v-model="dbpzForm.hyflbh" style="width: 200px;"@change="change()">-->
					<el-select v-model="dbpzForm.hyflbh" style="width: 200px;">
						<el-option
								v-for="item in hyflOptions"
								:key="item.codevalue"
								:label="item.codename"
								:value="item.codevalue">
						</el-option>
					</el-select>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button type="info" size="mini" @click="dbpzDialogFormVisible = false" v-if="isxmtc!='2'"><i class="iconfont icon-quxiao"></i>取 消</el-button>
				<el-button type="primary" size="mini" @click="dbxq_vue('dbpzForm')" :loading="loading" v-if="isxmtc!='2'"><i class="iconfont icon-queren"></i>确 定</el-button>
			</div>
		</el-dialog>
	</section>
</template>
<script>
	import {getKjData,getCodeData,dbxq,initDbpzTable,dbpzDelete,getHyflCodeData,getYsxzData,getHyflData } from '../../api/sjst';
	export default {
		props:{
			xmid:{
				type: String,
				required: true
			},
			qb:{
				required: false
			},
			isxmtc:{
				type: String,
				required: true
			}
		},
		data() {

			return {
				rules:{
					hm:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					dz:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					]},
				total: 0,//表格数据总数量
				page: 1,//当前页码
				size: 5,//每页容量
				//xmid:"1",
				isOrNotShow:false,
				formLabelWidth:"100px",
				labelPosition: 'right',
				Data:[],
				khzhOptions:[],
				khyhOptions:[],
				hyflOptions:[],
				ysxzOptions:[],
				kjOptions:[],
				jffsOptions:[],
				bzlbOptions:[{
					codevalue: 1,
					codename: '新装'
				}],
				dbpzDialogFormVisible:false,
				loading:false,
				dbpzForm:{
					id:"",
					hm:"",
					hh:"",
					kjbh:"",
					bzlb:1,
					dz:"",
					ysxzbh:"",
					hyflbh:"",
				}
			}
		},
		methods: {
			//包装类别格式化
			bzlbFormatter:function (row, column){
				switch (row.bzlb) {
					case 1:
						return '新装'
						break;
					case 2:
						return '移表'
						break;
					case 3:
						return '改表'
						break;
					case 4:
						return '移改表'
						break;
					default:
						break;
				}
			},
			//获取水表口径
			getKjData_vue(){
				let params={}
				getKjData(params).then((res)=>{
					if(res.data!=null){
						this.dbpzForm.kjbh = res.data.row[0].codevalue
						this.kjOptions = res.data.row;
					}
				})
			},
			//获取用水性质
			// getYsxzData_vue(){
			// 	let params={
			// 		type:'ysxz'
			// 	}
			// 	getCodeData(params).then((res)=>{
			// 		if(res.data!=null){
			// 			this.dbpzForm.ysxzbh = res.data.row[0].codevalue
			// 			this.ysxzOptions = res.data.row;
			// 		}
			// 	})
			// },
			change(){
				this.$forceUpdate()
			},
			//获取用水性质
			// getYsxzData_vue(val,hyflval){
			// 	let params={
			// 		type:'ysxz'
			// 	}
			// 	getCodeData(params).then((res)=>{
			// 		if(res.data!=null){
			// 			if(val==""&&val==null||val==undefined){
			// 				this.dbpzForm.ysxzbh = res.data.row[0].id;
			// 			}else{
			// 				this.dbpzForm.ysxzbh = val;
			// 			}
			// 			this.getHyflData_vue(this.dbpzForm.ysxzbh,hyflval);
			// 			this.ysxzOptions = res.data.row;
			// 		}
			// 	})
			// },
			//获取行业分类
			// getHyflData_vue(){
			// 	let params={
			// 		type:'hyfl'
			// 	}
			// 	getCodeData(params).then((res)=>{
			// 		if(res.data!=null){
			// 			this.dbpzForm.hyflbh = res.data.row[0].codevalue
			// 			this.hyflOptions = res.data.row;
			// 		}
			// 	})
			// },
			//获取行业分类
			// getHyflData_vue(parentid,val){
			// 	let params={
			// 		parentid:parentid
			// 	}
			// 	getHyflCodeData(params).then((res)=>{
			// 		if(res.data!=null){
			// 			if(res.data.row.length>0){
			// 				if(val==""||val==null||val==undefined){
			// 					this.dbpzForm.hyflbh = res.data.row[0].codevalue
			// 				}else{
			// 					this.dbpzForm.hyflbh = val;
			// 				}
			// 				this.hyflOptions = res.data.row;
			// 			}else{
			// 				this.dbpzForm.hyflbh = "";
			// 				this.hyflOptions = [];
			// 			}
			//
			// 		}
			// 	})
			// },
			getYsxzData_vue(){
				getYsxzData().then((res)=>{
					if(res.data!=null){
						this.ysxzOptions = res.data.row;
						this.dbpzForm.ysxzbh = res.data.row[0].codevalue;

					}
				})
			},
			//获取行业分类
			getHyflData_vue(){
				getHyflData().then((res)=>{
					if(res.data!=null){
						this.hyflOptions = res.data.row;
						this.dbpzForm.hyflbh = res.data.row[0].codevalue;

					}
				})
			},
			dbxq_vue(formName){
				// if(this.getForm()){
				// 	return;
				// }
				if(this.loading){
					return;
				}
				this.loading=true;
				this.$refs[formName].validate((valid) => {
					if (valid) {
						let params = {
							hhlxbh:'WJJLB',
							xmId:this.xmid,
							zt:0
						}
						params = Object.assign(params,this.dbpzForm);
						dbxq(params).then((res)=>{
							this.loading=false;
							if(res){
								this.$message.success('保存成功！');
								this.initDbpzTable_vue();
								this.dbpzDialogFormVisible=false;
							}else{
								this.$message.error('保存失败！');
							}

						})
					} else {
						this.loading=false;
						return false;
					}
				});


			},
			//初始化地表配置
			initDbpzTable_vue(){
				let params={
					xmId:this.xmid,
					page:this.page,
					size:this.size,
					hhlxbh:'WJJLB',
				}
				initDbpzTable(params).then((res)=>{
					if(res.data!=null){
						if(res.data.total>0&&res.data.row.length<=0){
							this.page=this.page-1
							this.initDbpzTable_vue();
						}
						this.Data = res.data.row;
						this.total=res.data.total;
					}
				})
			},
			handleCurrentChange(val) {
				this.page = val;
				this.initDbpzTable_vue();
			},
			handleSizeChange(val) {
				this.size = val;
				this.page = 1;
				this.initDbpzTable_vue();
			},
			dbpzAdd_vue(){
				this.getYsxzData_vue();
				this.dbpzForm={
					hm:"",
					hh:"",
					kjbh:this.kjOptions[0].codevalue,
					ysxzbh:this.ysxzOptions[0].codevalue,
					hyflbh:this.hyflOptions[0].codevalue,
					bzlb:1,
					dz:"",
				}
				this.dbpzDialogFormVisible=true;
			},
			dbpzHandle_vue(index,row){
				this.dbpzForm.id=row.id;
				this.dbpzForm.bzlb=row.bzlb;
				this.dbpzForm.dz=row.dz;
				this.dbpzForm.hh=row.hh;
				this.dbpzForm.hm=row.hm;
				this.dbpzForm.hyflbh=row.hyflbh;
				this.dbpzForm.ysxzbh=row.ysxzbh;
				this.dbpzDialogFormVisible=true;

			},
			dbpzDelete_vue(index,row){
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					let params = {
						id: row.id,
						zt: 1
					}
					dbpzDelete(params).then((res) => {
						if(res.data){
							this.initDbpzTable_vue();
						}
					})
				})
			}

		},
		mounted() {
			this.initDbpzTable_vue();
			this.getKjData_vue();
			this.getYsxzData_vue();
			this.getHyflData_vue();
		}
	}
</script>

