<template>
	<section>
		<el-row style="margin-top: 15px;">
			<div class="text item">
				<el-button v-if="this.qb != '0'&&isxmtc!='2'" style="margin-bottom: 10px;" size="mini" type="primary" @click="dbpzAdd_vue()"><i class="iconfont icon-xinzeng"></i>新增工商表</el-button>
				<el-table :data="Data" style="width: 100%"  :stripe="true">
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
					</el-table-column>
					<el-table-column
							prop="jffsname"
							label="交费方式"
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
		<el-dialog title="工商表详细信息" :visible.sync="dbpzDialogFormVisible" width="50%" :close-on-click-modal="false">
			<el-form :model="dbpzForm" :inline="true" class="demo-form-inline" ref="dbpzForm" :rules="rules" >
				<el-input  v-model="dbpzForm.id" style="display: none" size="small"></el-input>
				<el-form-item label="报装类别" :label-position="labelPosition" prop="bzlb"   :label-width="formLabelWidth" style="margin-bottom: 15px" size="small">
					<el-select v-model="dbpzForm.bzlb" style="width: 200px;" @change="bzlxchange">
						<el-option
								v-for="item in bzlbOptions"
								:key="item.codevalue"
								:label="item.codename"
								:value="item.codevalue">
						</el-option>
					</el-select>
				</el-form-item>

				<el-form-item label="报装户号" :label-position="labelPosition" prop="hh" :label-width="formLabelWidth" style="margin-bottom: 15px" size="small">
					<el-input :disabled="true" v-model="dbpzForm.hh" v-if="dbpzForm.bzlb!=4&&dbpzForm.hh!=null&&dbpzForm.hh!=''" style="width: 200px;" size="small"></el-input>
					<el-input :disabled="dbpzForm.bzlb!=4" v-else v-model="dbpzForm.hh" style="width: 200px;" size="small"></el-input>
				</el-form-item>
				<el-form-item label="户名" :label-position="labelPosition" :label-width="formLabelWidth" style="margin-bottom: 15px" prop="hm"  size="small">
					<el-input v-model="dbpzForm.hm" style="width: 200px;"></el-input>
				</el-form-item>
				<el-form-item label="装表地址" :label-position="labelPosition" :label-width="formLabelWidth" style="margin-bottom: 15px" prop="dz"size="small" >
					<el-input v-model="dbpzForm.dz" style="width: 200px;" ></el-input>
				</el-form-item>
				<el-form-item label="原口径" :label-position="labelPosition" :label-width="formLabelWidth" v-if="dbpzForm.bzlb==4" style="margin-bottom: 15px" prop="kjbh"  size="small">
					<el-select v-model="dbpzForm.ykjbh" style="width: 200px;">
						<el-option
								v-for="item in kjOptions"
								:key="item.codevalue"
								:label="item.codename"
								:value="item.codevalue">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="口径" :label-position="labelPosition" :label-width="formLabelWidth" style="margin-bottom: 15px" prop="kjbh"  size="small">
					<el-select v-model="dbpzForm.kjbh" style="width: 200px;">
						<el-option
								v-for="item in kjOptions"
								:key="item.codevalue"
								:label="item.codename"
								:value="item.codevalue">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="数量" v-if="dbpzForm.bzlb!=4" :label-position="labelPosition" :label-width="formLabelWidth" style="margin-bottom: 15px" prop="counts"size="small" >
					<el-input v-model="dbpzForm.counts" style="width: 200px;" :readonly="readonly"></el-input>
				</el-form-item>
				<el-form-item label="用水性质" :label-position="labelPosition" :label-width="formLabelWidth" style="margin-bottom: 15px" prop="ysxz"  size="small">
					<el-select v-model="dbpzForm.ysxzbh" style="width: 200px;">
						<el-option
								v-for="item in ysxzOptions"
								:key="item.codevalue"
								:label="item.codename"
								:value="item.codevalue">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="行业分类" :label-position="labelPosition" :label-width="formLabelWidth" style="margin-bottom: 15px" prop="hyfl"  size="small">
					<el-select v-model="dbpzForm.hyflbh" style="width: 200px;">
						<el-option
								v-for="item in hyflOptions"
								:key="item.codevalue"
								:label="item.codename"
								:value="item.codevalue">
						</el-option>
					</el-select>
				</el-form-item>
				<el-row>
					<el-form-item label="交费方式" :label-position="labelPosition" :label-width="formLabelWidth" style="margin-bottom: 15px" prop="hyfl"size="small" >
						<el-select v-model="dbpzForm.jffsbh" style="width: 200px;" @change="getShow" >
							<el-option
									v-for="item in jffsOptions"
									:key="item.codevalue"
									:label="item.codename"
									:value="item.codevalue">
							</el-option>
						</el-select>
					</el-form-item>
                    <el-form-item label="水表类别" :label-position="labelPosition" :label-width="formLabelWidth" style="margin-bottom: 15px" prop="type"size="small" >
                        <el-select v-model="dbpzForm.type" style="width: 200px;" >
                            <el-option
                                    v-for="item in typeOptions"
                                    :key="item.codevalue"
                                    :label="item.codename"
                                    :value="item.codevalue">
                            </el-option>
                        </el-select>
                    </el-form-item>
				</el-row>
				<el-form-item label="账户名" :label-position="labelPosition" :label-width="formLabelWidth"style="margin-bottom: 15px" size="small" prop="khmc" v-if="isOrNotShow">
					<el-input v-model="dbpzForm.khmc" style="width: 200px;" ></el-input>
				</el-form-item>
				<el-form-item label="账号" :label-position="labelPosition" size="small" :label-width="formLabelWidth" style="margin-bottom: 15px" prop="yhzh" v-if="isOrNotShow">
					<el-input v-model="dbpzForm.yhzh" style="width: 200px;" ></el-input>
				</el-form-item>
				<el-form-item label="开户行" :label-position="labelPosition" :label-width="formLabelWidth"  size="small" style="margin-bottom: 15px" prop="khyh" v-if="isOrNotShow">
						<el-select v-model="dbpzForm.khzh" style="width: 200px;" @change="getKhyhData_vue">
							<el-option
									v-for="item in khzhOptions"
									:key="item.codevalue"
									:label="item.codename"
									:value="item.codevalue">
							</el-option>
						</el-select>
				</el-form-item>
				<el-form-item label=" " :label-position="labelPosition" :label-width="formLabelWidth"style="margin-bottom: 15px" prop="khyh" v-if="isOrNotShow" size="small">
					<el-select v-model="dbpzForm.khyh" style="width: 200px;" @change="change()">
						<el-option
								v-for="item in khyhOptions"
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
	import {getKjData,getCodeData,getKhzhData,getKhyhData,dbxq,initDbpzTable,dbpzDelete,getHyflCodeData,getYsxzData,getHyflData } from '../../api/sjst';
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
			var checkNum = (rule, value, callback) => {
				//var reg =  /^[1-9]\d*$/;
				var reg = /^[0-9]*[1-9][0-9]*$/;
				if (!reg.test(value) && !value == '') {
					callback(new Error('该字段必须为整数'));
				} else {
					callback();
				}
			};
			return {
				rules:{
					hh:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					hm:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
							],
					dz:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					counts:[
						{ validator: checkNum, trigger: 'blur'}
					],

					khmc:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					yhzh:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					]},
				total: 0,//表格数据总数量
				page: 1,//当前页码
				size: 5,//每页容量
				//xmid:"1",
				isOrNotShow:false,
				readonly:false,
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
				},{
					codevalue:4,
					codename:'移改表'
				}],
                typeOptions:[{
                    codevalue: 1,
                    codename: '工商表'
                },{
                    codevalue:4,
                    codename:'消防栓表'
                },{
                    codevalue:0,
                    codename:'其他'
                }],
				dbpzDialogFormVisible:false,
				loading:false,
				dbpzForm:{
				    type:1,//水表类别
					id:"",
					hm:"",
					hh:"保存后自动生成",
					kjbh:"",
					ykjbh:"",
					bzlb:1,
					dz:"",
					ysxzbh:"",
					hyflbh:"",
					jffsbh:"",
					khzh:"",
					khmc:"",
					yhzh:"",
					khyh:"",
					counts:""
				}
			}
		},
		methods: {
			bzlxchange(val){
				if(val==4){
					this.dbpzForm.hh=""
				}else{
					this.dbpzForm.hh="保存后自动生成"
				}
			},

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
			//获取交费方式
			getjffsData_vue(){
				let params={
					type:'jffs'
				}
				getCodeData(params).then((res)=>{
					if(res.data!=null){
						this.jffsOptions = res.data.row;
						this.dbpzForm.jffsbh = 'XJ'
						if(this.dbpzForm.jffsbh=='XJ'){
							this.isOrNotShow=false;
						}else{
							this.isOrNotShow=true;
						}
					}
				})
			},
			//获取开户总行
			getKhzhData_vue(val,khyhval){
				let params={}
				getKhzhData(params).then((res)=>{
					if(res.data!=null){
						if(val==""&&val==null||val==undefined){
							this.dbpzForm.khzh = res.data.row[0].codevalue;
						}else{
							this.dbpzForm.khzh = val;
						}
						this.khzhOptions = res.data.row;
						this.getKhyhData_vue(this.dbpzForm.khzh,khyhval);
					}
				})
			},
			//获取开户银行
			getKhyhData_vue(value,khyhval){
				let params = {type:value}
				getKhyhData(params).then((res)=>{
					if(res.data!=null){
						if(khyhval==""&&khyhval==null||khyhval==undefined){
							this.dbpzForm.khyh = res.data.row[0].codevalue;
						}else{
							this.dbpzForm.khyh = khyhval;
						}
						this.khyhOptions = res.data.row;
					}
				})
			},
			getShow(value){
				if(value=='XJ'){
					this.isOrNotShow=false;
				}else{
					this.isOrNotShow=true;
				}
			},

			dbxq_vue(formName){
				this.loading=true;
				// if(this.getForm()){
				// 	return;
				// }
				if(this.dbpzForm.jffsbh=='XJ'){
					this.dbpzForm.yhzh="";
					this.dbpzForm.khmc="";
					this.dbpzForm.khzh="";
					this.dbpzForm.khyh="";
				}

				this.$refs[formName].validate((valid) => {
					if (valid) {
						let params = {
							hhlxbh:'DB',
							xmId:this.xmid,
							zt:0
						}
						if(this.dbpzForm.hh=='保存后自动生成'){
							this.dbpzForm.hh="";
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
					hhlxbh:'DB',
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
				this.getKhzhData_vue();
				this.dbpzForm={
				    type:1,
					hm:"",
					hh:"保存后自动生成",
					kjbh:this.kjOptions[0].codevalue,
					ykjbh:this.kjOptions[0].codevalue,
					ysxzbh:this.ysxzOptions[0].codevalue,
					hyflbh:this.hyflOptions[0].codevalue,
					bzlb:1,
					dz:"",
					jffsbh:'XJ',
					khmc:"",
					yhzh:"",
					counts:1,
				};
				this.isOrNotShow=false;
				this.dbpzDialogFormVisible=true;
				this.readonly=false;
			},
			dbpzHandle_vue(index,row){
				this.readonly=true;
				this.dbpzForm.counts = 1;
				this.dbpzForm.id=row.id;
				// this.dbpzForm.khyh=row.khyh;
				// this.dbpzForm.khzh=row.yhbh;
				this.dbpzForm.bzlb=row.bzlb;
				this.dbpzForm.dz=row.dz;
				this.dbpzForm.hh=row.hh;
				this.dbpzForm.hm=row.hm;

				// this.getYsxzData_vue(row.ysxzbh,row.hyflbh);
				this.getKhzhData_vue(row.yhbh,row.khyh);
				this.dbpzForm.hyflbh=row.hyflbh;
				this.dbpzForm.jffsbh=row.jffsbh;
				this.dbpzForm.ysxzbh=row.ysxzbh;
				this.dbpzForm.yhzh=row.yhzh;
				this.dbpzForm.khmc=row.khmc;
				this.dbpzForm.kjbh=row.kjbh;
				this.dbpzForm.ykjbh=row.ykjbh;
				if(row.jffsbh=='XJ'){
					this.isOrNotShow=false;
				}else{
					this.isOrNotShow=true;
				}
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
			this.getjffsData_vue();
			this.getKhzhData_vue();
		}
	}
</script>

