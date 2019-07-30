<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<el-row :gutter="10">
			<!--工具条-->
			<el-col :span="24" class="toolbar" >
				<el-form :inline="true" :model="filters">
					<el-form-item>
						<el-input v-model="filters.nodename" placeholder="流程结点名称"></el-input>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" v-on:click="getNodes">查询</el-button>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" @click="handleAdd">新增流程节点</el-button>
					</el-form-item>
				</el-form>
			</el-col>
			<el-col :span="24" class="toolbar" >
			<!--列表-->
			<el-table :data="nodes" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
				<el-table-column prop="activityid" label="流程节点标识" width="150" sortable>
				</el-table-column>
				<el-table-column prop="pdefId" label="流程定义Id" width="120" sortable>
				</el-table-column>
				<el-table-column prop="nextnode" label="下一节点" width="110" sortable>
				</el-table-column>
				<el-table-column prop="nodename" label="流程结点名称" width="120">
				</el-table-column>
				<el-table-column prop="formkey" label="表单Key" width="150">
				</el-table-column>
				<el-table-column prop="parammap" label="变量参数集合" width="180">
				</el-table-column>
				<el-table-column prop="isstartnode" label="是否开始结点" width="120">
				</el-table-column>
				<el-table-column prop="direction" label="方向标识" width="100">
				</el-table-column>
				<el-table-column prop="needcheck" label="是否需要验证" width="120">
				</el-table-column>
				<el-table-column prop="visible" label="流程按钮显示" min-width="180">
				</el-table-column>
				<el-table-column label="操作" width="150">
					<template slot-scope="scope">
						<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
						<el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
					</template>
				</el-table-column>
			</el-table>
			</el-col>
			<el-col :span="24" class="toolbar" >
			<!--工具条-->
				<el-pagination layout="total, prev, pager, next"  @current-change="handleCurrentChange" :page-size="this.size" :total="this.total" style="float:right;">
				</el-pagination>
			</el-col>

		</el-row>
		<!--编辑界面-->
		<el-dialog title="编辑" :visible.sync ="editFormVisible">
			<el-form :model="editForm" label-width="120px" :rules="editFormRules" ref="editForm" size="small">
				<el-form-item label="流程节点标识" prop="name">
					<el-input v-model="editForm.activityid" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="流程定义Id">
					<el-input v-model="editForm.pdefId" auto-complete="off" disabled="true"></el-input>
				</el-form-item>
				<el-form-item label="下一节点">
					<el-input v-model="editForm.nextnode" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="流程节点名称">
					<el-input v-model="editForm.nodename" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="表单key">
					<el-input v-model="editForm.formkey" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="变量参数集合">
					<el-input v-model="editForm.parammap" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="是否开始节点">
					<el-input v-model="editForm.isstartnode" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="方向标识">
					<el-input v-model="editForm.direction" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="是否需要验证">
					<el-input v-model="editForm.needcheck" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="流程按钮显示">
					<el-input v-model="editForm.visible" auto-complete="off"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="editFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
			</div>
		</el-dialog>

		<!--新增界面-->
		<el-dialog title="新增" :visible.sync ="addFormVisible">
			<el-form :model="addForm" label-width="120px" :rules="addFormRules" ref="addForm" size="small">
				<el-form-item label="流程节点标识" prop="name">
					<el-input v-model="addForm.activityid" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="流程定义Id">
					<el-input v-model="addForm.pdefId" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="下一节点">
					<el-input v-model="addForm.nextnode" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="流程节点名称">
					<el-input v-model="addForm.nodename" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="表单key">
					<el-input v-model="addForm.formkey" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="变量参数集合">
					<el-input v-model="addForm.parammap" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="是否开始节点">
					<el-input v-model="addForm.isstartnode" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="方向标识">
					<el-input v-model="addForm.direction" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="是否需要验证">
					<el-input v-model="addForm.needcheck" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="流程按钮显示">
					<el-input v-model="addForm.visible" auto-complete="off"></el-input>
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
	import util from '../../common/js/util';
	import Vue from 'vue';
	import { getWfReNode, deleteNodeById, save} from '../../api/WfReNode';

	export default {
		data() {
			return {
				filters: {
					nodename: ''
				},

				nodes: [],
				total: 10,//表格数据总数量
				page: 1,//当前页码
				size: 10,//每页容量
				listLoading: false,
				sels: [],//列表选中列

				editFormVisible: false,//编辑界面是否显示
				editLoading: false,
				editFormRules: {
					pdefId: [
						{ required: true, message: '请输入流程定义ID', trigger: 'blur' }
					]
				},
				//编辑界面数据
				editForm: {
					activityid : '',
					pdefId : '',
					nextnode : '',
					nodename : '',
					formkey : '',
					parammap : '',
					isstartnode : '',
					direction : '',
					needcheck : '',
					visible : ''
				},

				addFormVisible: false,//新增界面是否显示
				addLoading: false,
				addFormRules: {
					pdefId: [
						{ required: true, message: '请输入流程定义ID', trigger: 'blur' }
					]
				},
				//新增界面数据
				addForm: {
					activityid : '',
					pdefId : '',
					nextnode : '',
					nodename : '',
					formkey : '',
					parammap : '',
					isstartnode : '',
					direction : '',
					needcheck : '',
					visible : ''
				}
			}
		},
		methods: {
			//显示转换
			formatIslocked : function (row, column) {
				return row.islocked == '0' ? '正常' : row.islocked != '0' ? '冻结' : '未知';
			},
			handleCurrentChange(val) {
				this.page = val;
				this.getNodes();
			},
			//获取用户列表
			getNodes() {
				let para = {
					page: this.page - 1,
					size: this .size,
					nodename: this.filters.nodename
				};
				this.listLoading = true;
				getWfReNode(para).then((res) => {
					this.total = res.data.total;
					this.nodes = res.data.row;
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
					let para = { id: row.pdefId };
					deleteNodeById(para).then((res) => {
						//this.listLoading = false;
						//NProgress.done();
						this.$message({
							message: '删除成功',
							type: 'success'
						});
						this.getNodes();
					});
				});
			},
			//显示编辑界面
			handleEdit: function (index, row) {
				this.editFormVisible = true;
				this.editForm = Object.assign({}, row);//编辑表单的赋值
				//this.editForm.islocked = parseInt(row.islocked);//性别赋值
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
					activityid : '',
					pdefId : '',
					nextnode : '',
					nodename : '',
					formkey : '',
					parammap : '',
					isstartnode : '',
					direction : '',
					needcheck : '',
					visible : ''
				};
			},
			//编辑
			editSubmit: function () {
				this.$refs.editForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.editLoading = true;
							//NProgress.start();
							let para = Object.assign({}, this.editForm);
							para.birth = (!para.birth || para.birth == '') ? '' : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd');
							save(para).then((res) => {
								this.editLoading = false;
								//NProgress.done();
								this.$message({
									message: '提交成功',
									type: 'success'
								});
								this.$refs['editForm'].resetFields();
								this.editFormVisible = false;
								this.getNodes();
							});
						});
					}
				});
			},
			//新增
			addSubmit: function () {
				this.$refs.addForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.addLoading = true;
							//NProgress.start();
							let para = Object.assign({'deptid':this.deptid}, this.addForm);
							para.birth = (!para.birth || para.birth == '') ? '' : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd');
							save(para).then((res) => {
								this.addLoading = false;
								//NProgress.done();
								this.$message({
									message: '提交成功',
									type: 'success'
								});
								this.$refs['addForm'].resetFields();
								this.addFormVisible = false;
								this.getNodes();
							});
						});
					}
				});
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
						this.getNodes();
					});
				}).catch(() => {

				});
			}
		},
		mounted() {
			this.getNodes();
		}
	}

</script>

<style scoped>

</style>
