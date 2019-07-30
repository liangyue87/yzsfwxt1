<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
					<el-input
						v-on:input ="getSbcj"
					  placeholder="请输入内容"
					  v-model="filters.Sbcjname"
					  
					  clearable>
					</el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="getSbcj">查询</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="handleAdd">添加供货商</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!-- 供货商表-->
		<el-table  :data="sbcj" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
			<el-table-column type="selection" width="55">
			</el-table-column>

			<el-table-column prop="bh" label="编号" width="100" sortable>
			</el-table-column>
			<el-table-column prop="mc" label="供货商" min-width="500" sortable >
			</el-table-column>

			<el-table-column prop="jc" label="简称" width="200" sortable>
			</el-table-column>
			
			<el-table-column label="操作" width="150">
				<template scope="scope">
					<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
					<el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>

		<!--工具条-->
		<el-col :span="24" class="toolbar">
			<el-button type="danger" :disabled="this.sels.length===0">批量删除</el-button>
			<el-pagination layout="total, sizes, prev, pager, next, jumper"
						   @current-change="handleCurrentChange"
						   @size-change="handleSizeChange"

						   :page-size="this.size"	
						   :total="this.total"
						   :page-sizes="this.sizes"
						   style="float:right;">
			</el-pagination>
		</el-col>

		<!--编辑界面-->
		<el-dialog title="编辑"   :visible.sync="editFormVisible" :close-on-click-modal="false">
			<el-form :model="editForm" label-width="80px" :rules="editFormSbcj" ref="editForm">
				
				<el-form-item prop="bh" label="编号" >
					<el-input-number  v-model="editForm.bh"></el-input-number>
				</el-form-item>
				<el-form-item label="供货商" prop="mc">
					<el-input v-model="editForm.mc" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="简称" prop="jc">
					<el-input v-model="editForm.jc" auto-complete="off"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="editFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="editSubmit" :loading="editLoading">修改</el-button>
			</div>
		</el-dialog>

		<!--新增界面-->
		<el-dialog title="新增" :visible.sync="addFormVisible" :close-on-click-modal="false">
			<el-form :model="addForm" label-width="80px" :rules="addFormSbcj" ref="addForm">
				<el-form-item label="编号" prop="bh">
					<el-input-number v-model="addForm.bh"   :min="min" :max="max"></el-input-number>
				</el-form-item>

				<el-form-item prop="mc" label="供货商" >
					<el-input v-model="addForm.mc" @change="py"  auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="简称" prop="jc">
					<el-input v-model="addForm.jc" auto-complete="off"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="addFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
			</div>
		</el-dialog>
	</section>
</template>

<script>
	import util from '../../common/js/util'
	import vPinyin from '../../common/js/vue-py'//缩写
	//import PY from '../../common/js/main'
	//import pinyin from '../../common/js/pinyin-match-master/dist/main' //py检索
	import Vue from 'vue'
	//import VueResource from 'vue-resource';
	//Vue.use(VueResource)
	//import NProgress from 'nprogress'
	import { addSbcj, deleSbcj, updateSbcj,getSbcjLists} from '../../api/sbcj';
	//import { getSbcjLists } from '../../api/menu.js';
	
	
	
	
	export default {
		data() {
			return {
				bhlist:0,//编号长度
				min:0,
				max:0,
				
				filters: {
					Sbcjname:''
				},
				sbcj:[],
				total: 10,
				page: 1,//初始页
				size: 10,//  每页的数据
				sizes:[10,15,20,30],
				listLoading: false,
				sels: [],//列表选中列
				//搜索输入框
				restaurants: [],
				state4: '',
				timeout:  null,

				editFormVisible: false,//编辑界面是否显示
				editLoading: false,
				editFormSbcj: {
					mc: [
						{ required: true, message: '请输入供货商名称', trigger: 'blur' }
					]
				},


				addFormVisible: false,//新增界面是否显示
				addLoading: false,
				addFormSbcj: {
					mc: [
						{ required: true, message: '请输入供货商名称', trigger: 'blur' }
					]
				},
			
				//新增界面数据
				addForm: {
					mc: '',
					bh: '',
					jc: ''
				},
				//编辑界面数据
				editForm: {
                    mc: '',
                    bh: '',
                    jc: ''

				}
			}
		},
		methods: {
			py(){
				
				this.addForm.jc  = vPinyin.chineseToPinYin(this.addForm.mc);
				
				console.log(this.addForm.jc);
				
			},
			//分页
			handleCurrentChange(currentPage) {
				this.page = currentPage;
				this.getSbcj();
			},
			//当前页条数
			handleSizeChange(size){
				this.size = size;
				 this.getSbcj();
			},
			//获取用户列表
			getSbcj() {
				let params = {
					page: this.page - 1,
					size: this.size,
					sbcjname: this.filters.Sbcjname
				};
				console.log(params);
				this.listLoading = true;
			
				getSbcjLists(params).then((res) => {
					//let data1 = res.data;
					//console.log(data1);
					this.sbcj = res.data.sbcj;
					
					console.log(res.data);
				
					this.total = res.data.total;
					this.listLoading = false;

				})/* .catch(function (err) {
					console.log(err);
				}) */;
				this.bhlist = this.sbcj.length;
				alert("数据"+this.sbcj);
			},
			//删除
			handleDel(index, row) {
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					this.listLoading = true;

					let para = { id: row.bh };
					deleSbcj(para).then((res) => {
						this.listLoading = false;
						this.$message({
							message: '删除成功',
							type: 'success'
						});
						this.getSbcj();
					});
				}).catch(() => {

				});
			},
			//显示编辑界面
			handleEdit(index, row) {
				 this.editFormVisible = true;
				 this.editForm = Object.assign({},row);//编辑表单的赋值

			},
			//显示新增界面
			handleAdd() {
				
				this.addFormVisible = true;
				this.addForm.bh=this.bhlist + 1;
				this.max = this.bhlist + 100;
				this.min = this.bhlist + 1;
				
			},
			//编辑
			editSubmit() {
				this.$refs.editForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.editLoading = true;
							//NProgress.start();
							let para = Object.assign({}, this.editForm);
							updateSbcj(para).then((res) => {
								this.editLoading = false;
								//NProgress.done();
								this.$message({
									 message: '提交成功',
									 type: 'success'
								});
								this.$refs['editForm'].resetFields();
								this.editFormVisible = false;
								this.getSbcj();
							});
						});
					}
				});
			},
			//新增
			addSubmit() {
				this.$refs.addForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.addLoading = true;
							let para = Object.assign({}, this.addForm);
							addSbcj(para).then((res) => {
								this.addLoading = false;
								//NProgress.done();
								this.$message({
									message: '提交成功',
									type: 'success'
								});
								this.$refs['addForm'].resetFields();
								this.addFormVisible = false;
								this.getSbcj();
							});
						});
					}
				});
			},
			selsChange(sels) {
				this.sels = sels;
			
			},

		} ,
		/*//批量删除
		batchRemove: function () {
			var ids = this.sels.map(item => item.id).toString();
			this.$confirm('确认删除选中记录吗？', '提示', {
				type: 'warning'
			}).then(() => {
				this.listLoading = true;
				//NProgress.start();
				let para = { ids: ids };
				batchRemoveRole(para).then((res) => {
					this.listLoading = false;
					//NProgress.done();
					this.$message({
						message: '删除成功',
						type: 'success'
					});
					this.getUsers();
				});
			}).catch(() => {

			});
		}, */
	
		mounted() {
			this.getSbcj();
		}
	}



</script>

<style scoped>

</style>