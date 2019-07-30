<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item >
					<el-input size="small"
						v-on:input ="getRole"
					  placeholder="请输入角色名称"
					  v-model="filters.rolename"
					  clearable>
					</el-input>
				</el-form-item>
				<el-form-item>
					<el-button size="mini" type="primary" @click="getRole"><i class="iconfont icon-chaxun"></i>查询</el-button>
				</el-form-item>
				<el-form-item>
					<el-button size="mini" type="primary" @click="handleAdd"><i class="iconfont icon-xinzeng"></i>添加角色</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!-- 角色表-->
		<el-table :data="roles" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
			<el-table-column prop="ordinal" label="排序" width="100" sortable>
			</el-table-column>
			<el-table-column prop="rolename" label="角色名" width="180" sortable>
			</el-table-column>

			<el-table-column prop="roletype" label="角色类型" width="180" sortable>
			</el-table-column>
			<el-table-column prop="note" label="备注" min-width="250" sortable>
			</el-table-column>

			<el-table-column label="操作" width="450">
				<template slot-scope="scope">
					<!--<el-button type="primary" size="mini" @click="handleMenu(scope.$index, scope.row)" style="background-color: white">
						<i class="iconfont icon-pc-background-administration" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
					</el-button>
					<el-button icon="el-icon-s-custom" type="primary" size="mini" @click="handleUser(scope.$index, scope.row)">指定人员</el-button>
					<el-button icon="el-icon-edit" size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
					<el-button icon="el-icon-delete" type="danger" size="mini" @click="handleDel(scope.$index, scope.row)">删除</el-button>-->
					<label title="分配模块" @click="handleMenu(scope.$index, scope.row)">
						<i class="iconfont icon-pc-background-administration" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
					<label title="指定人员" @click="handleUser(scope.$index, scope.row)">
						<i class="iconfont icon-shenhetongguorenyuan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
					<label title="编辑" @click="handleEdit(scope.$index, scope.row)">
						<i class="iconfont icon-ai-edit" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
					<label title="删除" @click="handleDel(scope.$index, scope.row)">
						<i class="iconfont delete" style="cursor:pointer;padding: 2px;"></i></label>

				</template>
			</el-table-column>
		</el-table>

		<!--工具条-->
		<el-col :span="24" class="toolbar">
			<!--<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>-->
			<el-pagination
					background
					layout="total, prev, pager, next, sizes , jumper"
					@current-change="handleCurrentChange"
					@size-change="handleSizeChange"
					:page-size="this.size" style="float:right;"
					:total="this.total">
			</el-pagination>
			<!--<el-pagination layout=" sizes, prev, pager, next, total, jumper"
						   @current-change="handleCurrentChange"
						   @size-change="handleSizeChange"

						   :page-size="this.size"
						   :total="this.total"
						   :page-sizes="this.sizes"
						   style="float:right;">
			</el-pagination>-->
		</el-col>

		<!--编辑界面-->
		<el-dialog title="编辑" :visible.sync ="editFormVisible" :close-on-click-modal="false">
			<el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
				<el-form-item label="角色名" prop="rolename">
					<el-input v-model="editForm.rolename" auto-complete="off"></el-input>
				</el-form-item>

				<el-form-item prop="ordinal" label="排序" >
					<el-input-number v-model="editForm.ordinal" :min="0" :max="200"></el-input-number>
				</el-form-item>
				<el-form-item label="角色类型" prop="roletype">
					<el-input v-model="editForm.roletype" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item prop="note" label="备注" >
					<el-input type="textarea" v-model="editForm.note"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer" style="text-align: center">
				<el-button size="mini" type="info" @click.native="editFormVisible = false">
					<i class="iconfont icon-quxiao"></i>取消</el-button>

				<el-button size="mini" type="primary" @click="editSubmit" :loading="editLoading">
					<i class="iconfont icon-queren"></i>提交</el-button>
				<!--<el-button @click.native="editFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>-->
			</div>
		</el-dialog>

		<!--新增界面-->
		<el-dialog title="新增" :visible.sync ="addFormVisible" :close-on-click-modal="false">
			<el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
				<el-form-item label="角色名" prop="rolename">
					<el-input v-model="addForm.rolename" auto-complete="off"></el-input>
				</el-form-item>

				<el-form-item prop="ordinal" label="排序" >
					<el-input-number v-model="addForm.ordinal" :min="0" :max="200"></el-input-number>
				</el-form-item>
				<el-form-item label="角色类型" prop="roletype">
					<el-input v-model="addForm.roletype" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item prop="note" label="备注" >
					<el-input type="textarea" v-model="addForm.note"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button size="mini" type="info" @click.native="addFormVisible = false">
					<i class="iconfont icon-quxiao"></i>取消</el-button>

				<el-button size="mini" type="primary" @click="addSubmit" :loading="addLoading">
					<i class="iconfont icon-queren"></i>提交</el-button>

				<!--<el-button @click.native="addFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>-->
			</div>
		</el-dialog>
		<!--模块配置弹出框-->
		<el-dialog title="模块分配" :visible.sync="menuFormVisible" width="30%">
			<el-card class="box-card" style="height: 450px;overflow: auto;">
				<div class="grid-content bg-purple">
					<el-tree node-key="id" :data="menuTree" :props="defaultProps"  ref="tree" :default-checked-keys="selectMenuid"
							 show-checkbox default-expand-all></el-tree>
				</div>
			</el-card>
			<div style="width: 100%;height: 50px;margin-top: 20px;text-align: center;">
				<el-button size="mini" type="info" @click.native="menuFormVisible = false">
					<i class="iconfont icon-quxiao"></i>取消</el-button>

				<el-button size="mini" type="primary" @click="saveRoleMenu" :loading="addLoading">
					<i class="iconfont icon-queren"></i>保存</el-button>

				<!--<el-button @click.native="saveRoleMenu">保存</el-button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<el-button @click="menuFormVisible = false">取消</el-button>-->
			</div>

		</el-dialog>

		<!--模块配置弹出框-->
		<el-dialog title="分配人员" :visible.sync="fpryFormVisible" width="30%">
			<el-card class="box-card" style="height: 450px;overflow: auto;">
				<div class="grid-content bg-purple">
					<el-tree node-key="id" :data="fpryTree" :props="defaultProps1"  ref="tree1" :default-checked-keys="selectUserid"
							 show-checkbox default-expand-all>
						<span slot-scope="{ node, data }">
            				<span>
                				<i :class="data.icon"></i>{{ node.label }}
            				</span>
						</span>
					</el-tree>
				</div>
			</el-card>
			<div style="width: 100%;height: 50px;margin-top: 20px;text-align: center;">
				<el-button size="mini" type="info" @click.native="fpryFormVisible = false">
					<i class="iconfont icon-quxiao"></i>取消</el-button>

				<el-button size="mini" type="primary" @click="saveRoleUser" :loading="addLoading">
					<i class="iconfont icon-queren"></i>保存</el-button>

				<!--<el-button @click.native="saveRoleUser">保存</el-button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<el-button @click="fpryFormVisible = false">取消</el-button>-->
			</div>

		</el-dialog>

	</section>
</template>

<script>
	import util from '../../common/js/util';

	import Vue from 'vue';
	import { Tree } from 'element-ui';
	//Vue.component(Tree.name, Tree);
	Vue.use(Tree);

	import { getRoleList, editRole, addRole ,delRole,batchRemoveRole, saveMenuForRole, getMenuForRole,getAllMenuList, getAssignUser, saveUserForRole, getUserForRole} from '../../api/role';

	export default {
		data() {
			return {

				filters: {
					rolename: ''
				},
				i: 10,
				roles: [],
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
				selectRoleid:'',
				menuFormVisible: false,//模块配置界面是否显示
				menuTree:[],
				defaultProps: {
					children: 'children',
					label: 'text'
				},
				selectMenuid:[],//选中的菜单节点

				//userFormVisible: false,//指定人员界面

				editFormVisible: false,//编辑界面是否显示
				editLoading: false,
				editFormRules: {
					name: [
						{ required: true, message: '请输入姓名', trigger: 'blur' }
					]
				},
				addFormVisible: false,//新增界面是否显示
				addLoading: false,
				addFormRules: {
					rolename: [
						{ required: true, message: '请输入姓名', trigger: 'blur' }
					]
				},
				fpryFormVisible: false,
				fpryTree:[],
				defaultProps1: {
					children: 'children',
					label: 'text'
				},
				selectUserid:[],//选中的人员id

				//新增界面数据
				addForm: {
					rolename: '',
					ordinal: '',
					note: '',
					roletype: ''
				},
				//编辑界面数据
				editForm: {
                    roleid:'',
					rolename: '',
					note: '',
					ordinal: '',
					roletype: ''

				}
			}
		},
		methods: {
			//分页
			handleCurrentChange(currentPage) {
				this.page = currentPage;
				this.getRole();
			},
			//当前页条数
			handleSizeChange(size){
				this.size = size;
				 this.getRole();
			},
			//获取用户列表
			getRole() {
				let params = {
					page: this.page - 1,
					size: this.size,
					rolename: this.filters.rolename
				};
				this.listLoading = true;
				getRoleList(params).then((res) => {
					this.roles = res.data.roles;
					this.total = res.data.total;
					this.listLoading = false;

				});

			},
			//删除
			handleDel: function (index, row) {
				this.$confirm('确认删除该角色吗？', '提示', {
					type: 'warning'
				}).then(() => {
					this.listLoading = true;

					let para = { id: row.roleid };
					delRole(para).then((res) => {
						this.listLoading = false;
						this.$message({
							message: '删除成功',
							type: 'success'
						});
						this.getRole();
					});
				}).catch(() => {

				});
			},
			//显示编辑界面
			handleEdit: function (index, row) {
				 this.editFormVisible = true;
				 this.editForm = Object.assign({},row);//编辑表单的赋值

			},
			//显示新增界面
			handleAdd: function () {
				this.addFormVisible = true;
				this.addForm = {
					rolename: '',
					ordinal: '',
					note: '',
					roletype: ''
				};
			},
			//显示分配人员界面
			handleFpry: function (index, row) {
				this.selectRoleid = row.roleid;
				this.fpryFormVisible = true;

			},
			//显示模块配置界面
			handleMenu: function (index, row) {
				try {
					this.$refs.tree.setCheckedKeys([]);
				}catch (e) {}
				this.selectRoleid = row.roleid;
				let params = {
					roleid: row.roleid,
				};
				getMenuForRole(params).then((res) => {//查询角色已配置菜单
					if(res.data.menuid.length == 0){
						this.selectMenuid = [];
					}else{
						this.selectMenuid = res.data.menuid.split(",");
					}
				});
				this.menuFormVisible = true;
			},
			//显示指定人员界面
			handleUser: function (index, row) {
				try {
					this.$refs.tree1.setCheckedKeys([]);
				}catch (e) {}
				this.selectRoleid = row.roleid;
				let params = {
					roleid: row.roleid,
				};
				getUserForRole(params).then((res) => {//查询角色已配置菜单
					if(res.data.userid.length == 0){
						this.selectUserid = [];
					}else{
						this.selectUserid = res.data.userid.split(",");
					}
				});
				this.fpryFormVisible = true;
			},
			//编辑
			editSubmit: function () {
				this.$refs.editForm.validate((valid) => {
					if (valid) {
						//this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.editLoading = true;
							//NProgress.start();
							let para = Object.assign({}, this.editForm);
							editRole(para).then((res) => {
								this.editLoading = false;
								//NProgress.done();
								this.$message({
									 message: '提交成功',
									 type: 'success'
								});
								this.$refs['editForm'].resetFields();
								this.editFormVisible = false;
								this.getRole();
							});
						//});
					}
				});
			},
			//新增
			addSubmit: function () {
				this.$refs.addForm.validate((valid) => {
					if (valid) {
						//this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.addLoading = true;
							let para = Object.assign({}, this.addForm);
							addRole(para).then((res) => {
								this.addLoading = false;
								//NProgress.done();
								this.$message({
									message: '提交成功',
									type: 'success'
								});
								this.$refs['addForm'].resetFields();
								this.addFormVisible = false;
								this.getRole();
							});
						//});
					}
				});
			},
			selsChange: function (sels) {
				this.sels = sels;
			},
			//批量删除
			batchRemove: function () {
				var ids = this.sels.map(item => item.id).toString();
				this.$confirm('确认删除该角色？', '提示', {
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
			},
			saveRoleMenu() {//保存提交角色分配菜单模块
				this.$refs.tree.getCheckedKeys(true);
				var params = {
					'roleid': this.selectRoleid,
					'menuid': this.$refs.tree.getCheckedKeys(true).toString()
				};
				saveMenuForRole(params).then((res) => {
					if(res == 'success'){
						this.$message({
							message: '提交成功',
							type: 'success'
						});
					}else{
						this.$message({
							message: '提交失败',
							type: 'fail'
						});
					}
					this.selectRoleid = '';
					this.menuFormVisible = false;
				});
			},
			saveRoleUser() {//保存提交角色分配人员模块
				this.$refs.tree1.getCheckedKeys(true);
				var params = {
					'roleid': this.selectRoleid,
					'userid': this.$refs.tree1.getCheckedKeys(true).toString()
				};
				saveUserForRole(params).then((res) => {
					if(res == 'success'){
						this.$message({
							message: '提交成功',
							type: 'success'
						});
					}else{
						this.$message({
							message: '提交失败',
							type: 'fail'
						});
					}
					this.selectRoleid = '';
					this.fpryFormVisible = false;
				});
			},
			getMenuTree() {
				//NProgress.start();
				getAllMenuList().then(res => {
					this.menuTree = res.data;
					//NProgress.done();
				});
			},
			getUserTree() {
				//NProgress.start();
				getAssignUser().then(res => {
					this.fpryTree = res.data;
				});
			}
		},
		mounted() {
			this.getRole();
			this.getMenuTree();
			this.getUserTree();
		}
	}



</script>

