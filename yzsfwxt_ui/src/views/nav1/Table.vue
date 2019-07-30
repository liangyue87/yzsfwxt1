<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<el-row :gutter="10">
		<el-col :span="4">
			<el-card class="box-card">
				<div class="grid-content bg-purple">
					<el-tree
						ref="tree"
						node-key="id"
						:data="data"
						:props="defaultProps"
						@node-click="handleNodeClick"
						:default-expanded-keys="['0']"
						highlight-current
						:expand-on-click-node="false"
					></el-tree>
				</div>
			</el-card>
		</el-col>
		<!--工具条-->
		<el-col :span="20" class="toolbar" style="margin: 0px;">
				<el-col :span="24" class="toolbar" >
					<el-form :inline="true" :model="filters" @submit.native.prevent>
						<el-form-item>
							<el-input v-model="filters.username" size="small" placeholder="姓名"></el-input>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" size="mini" @click="getUsers"><i class="iconfont icon-chaxun"></i>查询</el-button>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" size="mini" @click="handleAdd"><i class="iconfont icon-xinzeng"></i>新增用户</el-button>
						</el-form-item>
					</el-form>
				</el-col>
				<el-col :span="24" class="toolbar" >
				<!--列表-->
				<el-table :data="users" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
					<el-table-column type="index" label="序号" width="60" align="center">
					</el-table-column>
					<el-table-column prop="username" label="姓名" width="150" sortable align="center">
					</el-table-column>
					<el-table-column prop="account" label="账号" width="150" sortable align="center">
					</el-table-column>
					<el-table-column prop="mobilephone" label="手机号" width="120" align="center">
					</el-table-column>
					<el-table-column prop="gddh" label="对外电话" width="120" align="center">
					</el-table-column>
					<el-table-column prop="email" label="邮箱" width="180" align="center">
					</el-table-column>
					<el-table-column prop="islocked" label="是否启用" width="120" :formatter="formatIslocked" align="center">
					</el-table-column>
					<el-table-column label="操作" min-width="120" align="center">
						<template slot-scope="scope">
							<label title="编辑" @click="handleEdit(scope.$index, scope.row)">
								<i class="iconfont icon-ai-edit" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
							<label title="删除" @click="handleDel(scope.$index, scope.row)">
								<i class="iconfont delete" style="cursor:pointer;padding: 2px;color: #f78989"></i></label>

							<!--<el-button size="small" type="warning" @click="openEditForm(scope.$index, scope.row)">编辑</el-button>
                            <el-button size="small" type="danger" @click="deleteFk(scope.$index, scope.row)">删除</el-button>-->
						</template>
					</el-table-column>
				</el-table>
				</el-col>
				<el-col :span="24" class="toolbar" >
				<!--工具条-->
					<!--<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>-->
					<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[10, 15, 30, 50,100]"
								   @current-change="handleCurrentChange" @size-change="handleSizeChange"
								   :current-page.sync = "this.page"
								   :page-size="this.size" style="float:right;" :total="this.total">
					</el-pagination>
					<!--<el-pagination layout="total, prev, pager, next"  @current-change="handleCurrentChange" :page-size="this.size" :total="this.total" style="float:right;">
					</el-pagination>-->
				</el-col>
			</el-col>
		</el-row>
		<!--编辑界面-->
		<el-dialog title="编辑" :visible.sync ="editFormVisible" :close-on-click-modal="false">
			<el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
				<el-form-item label="姓名" prop="name">
					<el-input v-model="editForm.username" auto-complete="off"></el-input>
				</el-form-item>

				<el-form-item label="登录账号">
					<el-input v-model="editForm.account" auto-complete="off" readonly disabled></el-input>
				</el-form-item>
				<!--<el-form-item label="密码" prop="password" :rules="this.publicUtil.mimayz" >
					<el-input type="password" v-model="editForm.password" auto-complete="off"></el-input>
				</el-form-item>-->
				<el-form-item label="手机号">
					<el-input v-model="editForm.mobilephone"></el-input>
				</el-form-item>
				<el-form-item label="对外电话">
					<el-input v-model="editForm.gddh"></el-input>
				</el-form-item>
				<el-form-item label="邮箱">
					<el-input v-model="editForm.email"></el-input>
				</el-form-item>
				<el-form-item label="是否启用">
					<el-radio-group v-model="editForm.islocked">
						<el-radio  class="radio" :label="0">启用</el-radio>
						<el-radio  class="radio" :label="-1">冻结</el-radio>
					</el-radio-group>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer" style="text-align: center;">
				<el-button size="mini" type="info" @click.native="editFormVisible = false">
					<i class="iconfont icon-quxiao"></i>取消</el-button>

				<el-button size="mini" type="primary" @click="editSubmit" :loading="editLoading">
					<i class="iconfont icon-queren"></i>提交</el-button>

				<el-button size="mini" type="primary" @click="editpassword" :loading="editLoading">
					<i class="iconfont icon-queren"></i>重置密码</el-button>
			</div>
		</el-dialog>

		<!--新增界面-->
		<el-dialog title="新增" :visible.sync ="addFormVisible" :close-on-click-modal="false">
			<el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
				<el-form-item label="姓名" prop="name">
				<el-input v-model="addForm.username" auto-complete="off"></el-input>
			</el-form-item>

				<el-form-item label="登录账号">
					<el-input v-model="addForm.account" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="密码" prop="password" :rules="this.publicUtil.mimayz">
					<el-input show-password v-model="addForm.password" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="手机号" prop="mobilephone" :rules="this.publicUtil.phone">
					<el-input v-model="addForm.mobilephone"></el-input>
				</el-form-item>
				<el-form-item label="对外电话">
					<el-input v-model="editForm.gddh"></el-input>
				</el-form-item>
				<el-form-item label="邮箱" prop="email">
					<el-input v-model="addForm.email"></el-input>
				</el-form-item>
				<el-form-item label="是否启用">
					<el-radio-group v-model="addForm.islocked">
						<el-radio  class="radio" :label="0">启用</el-radio>
						<el-radio  class="radio" :label="-1">冻结</el-radio>
					</el-radio-group>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer" style="text-align: center;">
				<el-button size="mini" type="info" @click.native="addFormVisible = false">
					<i class="iconfont icon-quxiao"></i>取消</el-button>

				<el-button size="mini" type="primary" @click="addSubmit" :loading="addLoading">
					<i class="iconfont icon-queren"></i>提交</el-button>
			</div>
		</el-dialog>
	</section>
</template>

<script>
	import util from '../../common/js/util'
	import Vue from 'vue'
	//import VueResource from 'vue-resource';
	//Vue.use(VueResource)
	//import NProgress from 'nprogress'
	import { getUserList, removeUser, batchRemoveUser, addUser ,showAllDept,editpassword} from '../../api/api';

	export default {
		data() {
			return {
				filters: {
					username: ''
				},
				data:[],
				defaultProps: {
					children: 'children',
					label: 'text'
				},
				deptid:'',//选中部门的id
				users: [],
				total: 10,//表格数据总数量
				page: 1,//当前页码
				size: 10,//每页容量
				listLoading: false,
				sels: [],//列表选中列

				editFormVisible: false,//编辑界面是否显示
				editLoading: false,
				editFormRules: {
					username: [
						{ required: true, message: '请输入姓名', trigger: 'blur' }
					]
				},
				//编辑界面数据
				editForm: {
					id: 0,
					username: '',
					account: -1,
					mobilephone: '',
					email: '',
					gddh : '',
					islocked: ''
				},

				addFormVisible: false,//新增界面是否显示
				addLoading: false,
				addFormRules: {
					username: [
						{ required: true, message: '请输入姓名', trigger: 'blur' }
					],
					password:[
						{required: true, message: '请输入密码', trigger: 'blur'},
						{ pattern: /^(?![0-9]+$)(?![a-zA-Z]+$)(?![_]+$)[0-9A-Za-z_]{8,16}$/, message: '密码需同时包含字母和数字，长度8-16位' }
					],
					email:[
						{required: false, message: '请输入邮箱', trigger: 'blur'},
						{pattern: /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/, message: '请输入有效的邮箱!' }
					]


				},
				//新增界面数据
				addForm: {
					id: 0,
					username: '',
					account: -1,
					password: 0,
					mobilephone: '',
					email: '',
					gddh : '',
					islocked: ''
				}
			}
		},
		//Enter键监听执行查询
		created() {
			let lett = this;
			document.onkeydown = function(e) {
				var key = window.event.keyCode;
				if (key == 13) {
					lett.getUsers();
				}
			}
		},
		methods: {
			//重置密码
			editpassword : function(){
				this.$confirm('此操作将重置密码, 密码为"hfgs_666666"', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {

					let para = {
						password : 'hfgs_666666',
						userid : this.editForm.userid
					}
					editpassword(para).then((res) => {
						if (res.flag == "true"){
							this.$message({
								message: '重置成功',
								type: 'success'
							});
							this.editFormVisible = false;
						} else {
							this.$message({
								message: '重置失败',
								type: 'warning'
							});
						}

						this.getUsers();
					});

				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消重置'
					});
				});






			},
			//显示转换
			formatIslocked : function (row, column) {
				return row.islocked == '0' ? '正常' : row.islocked != '0' ? '冻结' : '未知';
			},
			handleCurrentChange(val) {
				this.page = val;
				this.getUsers();
			},
			handleSizeChange(val) {
				this.size = val;
				this.page = 1;
				this.getUsers();
			},
			//获取用户列表
			getUsers() {
				let para = {
					deptid: this.deptid,
					page: this.page - 1,
					size: this .size,
					username: this.filters.username
				};
				this.listLoading = true;
				getUserList(para).then((res) => {
					this.total = res.data.total;
					this.users = res.data.users;
					this.listLoading = false;
				});
			},
			//删除
			handleDel: function (index, row) {
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					//this.listLoading = true;
					//NProgress.start();
					let para = { id: row.userid };
					removeUser(para).then((res) => {
						//this.listLoading = false;
						//NProgress.done();
						this.$message({
							message: '删除成功',
							type: 'success'
						});
						this.getUsers();
					});
				});
			},
			//显示编辑界面
			handleEdit: function (index, row) {
				this.editFormVisible = true;
				delete row.regtime;
				delete row.lastlogin;
				this.editForm = Object.assign({}, row);//编辑表单的赋值
				this.editForm.islocked = parseInt(row.islocked);//性别赋值
			},
			//显示新增界面
			handleAdd: function () {
				if(this.deptid == ''){
					this.$message({
						message: '请选择部门节点后在新增用户',
						type: 'warning'
					});
					return;
				}
				this.addFormVisible = true;
				this.addForm = {
					id: 0,
					username: '',
					account: '',
					password: 'hfgs_666666',
					mobilephone: '',
					email: '',
					islocked: 0,
				};
			},
			//编辑
			editSubmit: function () {
				this.editLoading = true;
				//手机号校验
				var sj=/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57]|19[0-9]|16[0-9])[0-9]{8}$/;
				if (sj.test(this.editForm.mobilephone)==false && this.editForm.mobilephone!="" && typeof this.editForm.mobilephone!="undefined"){
					this.editLoading = false;
					this.$message({
						message: '手机格式不正确',
						type: 'warning'
					});
					return false
				}

				let para = Object.assign({}, this.editForm);
					addUser(para).then((res) => {
						if(res = 'ok'){
							this.$message({
								message: '提交成功',
								type: 'success'
							});
						}else {
							this.$message({
								message: '提交失败',
								type: 'error'
							});
						}
						this.editLoading = false;
						this.$refs['editForm'].resetFields();
						this.editFormVisible = false;
						this.getUsers();
					});
			},
			//新增
			addSubmit: function () {
				this.addLoading = true;
				let para = Object.assign({'deptid':this.deptid}, this.addForm);
				//para.birth = (!para.birth || para.birth == '') ? '' : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd');
				if (this.addForm.username==""){
					this.addLoading = false;
					this.$message({
						message: '姓名不能为空',
						type: 'warning'
					});
					return false
				}
				if (this.addForm.account==""){
					this.addLoading = false;
					this.$message({
						message: '登录账号不能为空',
						type: 'warning'
					});
					return false
				}

				//密码校验
				var reg=/^(?![0-9]+$)(?![a-zA-Z]+$)(?![_]+$)[0-9A-Za-z_]{8,16}$/;
				//手机号校验
				var sj=/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57]|19[0-9]|16[0-9])[0-9]{8}$/;

				if (this.addForm.password==""){
					this.addLoading = false;
					this.$message({
						message: '密码不能为空',
						type: 'warning'
					});
					return false
				}

				if(!reg.test(this.addForm.password) ){
					this.addLoading = false;
					this.$message({
						message: '密码格式不正确',
						type: 'warning'
					});
					return false
				}

			//	alert(reg.test(this.addForm.mobilephone))
				if (sj.test(this.addForm.mobilephone)==false && this.addForm.mobilephone!=""){
					this.addLoading = false;
					this.$message({
						message: '手机格式不正确',
						type: 'warning'
					});
					return false
				}



				addUser(para).then((res) => {
					if(res = 'ok'){
						this.$message({
							message: '提交成功',
							type: 'success'
						});
					}else {
						this.$message({
							message: '提交失败',
							type: 'error'
						});
					}
					this.addLoading = false;
					this.$refs['addForm'].resetFields();
					this.addFormVisible = false;
					this.getUsers();
				})
			},
			selsChange: function (sels) {
				this.sels = sels;
			},
			//批量删除
			batchRemove: function () {
				var ids = this.sels.map(item => item.id).toString();
				this.$confirm('确认删除选中记录吗？', '提示', {
					type: 'warning'
				}).then(() => {
					this.listLoading = true;
					//NProgress.start();
					let para = { ids: ids };
					batchRemoveUser(para).then((res) => {
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
			},
			//查询全部
			getAllDeptList() {
				//NProgress.start();
				showAllDept().then(res => {
					this.data = res.data;
					//NProgress.done();

				});
			},
			handleNodeClick(data) {//点击节点 table显示部门的人员
				this.deptid = data.id;
				this.getUsers();
			}
		},
		mounted() {
			this.getAllDeptList();
			this.getUsers();

			/*this.$nextTick(() => {
				this.$refs.tree.setCurrentKey('3440103');
			});*/
		}
	}

</script>

<style scoped>

</style>
