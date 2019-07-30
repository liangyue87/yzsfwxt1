<template>
	<el-row :gutter="30">
		<el-col :span="5">
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

		<el-col :span="12">
			<el-card class="box-card">
				<!--编辑页面-->
				<div class="grid-content bg-purple-light">
					<el-form
						ref="form"
						:model="form"
						label-width="120px"
						@submit.prevent="onSubmit">
						<el-form-item>
							<div style="padding-bottom: 15px;">
								<el-button
										size="mini"
									type="primary"
									@click="addDepts('form')" >
									添加子节点
								</el-button>
								<el-button
										size="mini"
									type="danger"
									@click.native="deleDepts" >
									删除子节点
								</el-button>
							</div>
						</el-form-item>
						<el-form-item label="部门名称" prop="orgname">
							<el-input v-model="form.orgname"></el-input>
						</el-form-item>
						<el-form-item label="排序序号" prop="ordinal">
							<el-input v-model="form.ordinal"></el-input>
						</el-form-item>
						<el-form-item label="部门负责人" prop="bmfzr">
							<el-button size="mini" type="primary" @click="XuanZe()"><i class="iconfont icon-xinzeng"></i>新增</el-button>&nbsp;&nbsp;&nbsp;&nbsp;
							<span>{{form.bmfzr}}</span>
							<!--<el-input v-model="form.bmfzr"></el-input>-->
						</el-form-item>

						<el-form-item label="是否禁用" prop="isused">
							<el-switch 
							on-text="ok" 
							off-text="no" 
							
							active-value="1"
							inactive-value="0"
							v-model="form.isused"></el-switch>
						</el-form-item> 
						<el-form-item>
							<div v-if="show">
								<el-button
										size="mini"
									type="primary"
									@click.native="editDept"
									:loading="Loading">
									立即修改
								</el-button>
								<el-button size="mini" @click.native="empty('form')">重置</el-button>
							</div>
							<div v-else>
								<el-button
										size="mini"
									type="success"
									@click.native="addDept2"
									:loading="Loading">
									立即创建
								</el-button>
								<el-button size="mini" @click.native="empty('form')">重置</el-button>
							</div>
						</el-form-item>
					</el-form>
				</div>
			</el-card>
		</el-col>
	</el-row>
</template>
<script>
import util from '../../common/js/util';
import Vue from 'vue';

import { getAllDept, getDeptById, saveDept,deleteDept } from '../../api/dept';
var a; //点击获取焦点的id长度
var b; //店家获取交点的对象
export default {
	data() {
		return {
			value4: true,
			data: [], //响应数据
			data2:[], //下拉选中的暂存数据
			Loading: false, //加载画面属性
			openIsDisabled: true, //删除和立即修改按钮
			openIsDele:true,//添加按钮
			show: false, //判断修改和添加

			defaultProps: {
				children: 'children',
				label: 'text'
			},
			form: {
				orgname: '', //部门名称
				bmfzr:'',//部门负责人
				bmfzrid:'',
				ordinal: '', //排序
				isused: '', //是否启用
				orgid: '', //部门编号
				parentid: '' //上级部门
			}
		};
	},
	methods: {
		getuser() {
			var userobj = null;
			userobj = localStorage.getItem("userobj");
			if (userobj != null) {
				var obj = JSON.parse(userobj);

				if (typeof obj.username != "undefined") {
					var data2id = localStorage.getItem("data2id");
					if (data2id != null){

						var params = {
							orgid: data2id
						};
						getDeptById(params).then(res => {
							this.form = res.data.department;
							this.form.bmfzr = obj.username;
							this.form.bmfzrid = obj.userid;
						});
						this.show = true;
					}

				}
			}
		},
		//跳转用户选择界面
		  XuanZe() {
			   /*if (this.kcfk.hysj != null) {
				   localStorage.setItem("hysj", this.kcfk.hysj);
			   }
			   if (this.kcfk.info != null) {
				   localStorage.setItem("info", this.kcfk.info);
			   }*/
			  	localStorage.setItem("data2id", this.data2.id);
			   this.$router.push({path: '/Userxx', query: {}})
		   },
		//清空列表
		empty(formName){
			this.$refs[formName].resetFields();//清空列表

		},
		//通过点击树 来用id回显信息
		handleNodeClick(data) {
			this.data2 = data;//选中点击树行数据
			var params = {
				orgid: data.id
			};
			getDeptById(params).then(res => {
				this.form = res.data.department;
				//NProgress.done();
			});
			this.show = true;
			return;
			b = data;
			//动态按钮禁用与改变
			 a = (data.id +"").length;
			console.log(a);
			/* if (data.text != '部门管理' || a.length >= 6  )  {*/
			/* if (a == 7 || a == 32 ){
				this.show = true;
				this.openIsDisabled = false;
			} else {
				this.show = false;
				this.openIsDisabled = true;
			} */
			 switch(a){
			 case 7:
			   	this.show = true;
				this.openIsDisabled = false;
				this.openIsDele=false;
			   break;
			 case 32:
			   this.show = true;
			   this.openIsDisabled = false;
			   this.openIsDele=false;
			 break;
			 default:
			   this.show = false;
			   this.openIsDisabled = true;
			    this.openIsDele=true;
			   break;
			}
		},
		//查询全部
		getAllDeptList() {
			//NProgress.start();
			getAllDept().then(res => {
				this.data = res.data;
				//NProgress.done();

			});

		},
		//添加子节点按钮
		addDepts(formName) {
			if(this.data2.length == 0){
				this.$message({
					message: '请选一个节点后在操作',
					type: 'error'
				});
			}
			this.show = false;//修改按钮变成添加按钮
			this.openIsDisabled = false;//删除和立即修改按钮
			this.$refs[formName].resetFields();//清空列表

			//console.log(this.data2);


		},
		//新增的方法
		addDept2(){
			this.$refs.form.validate(valid => {
				if (valid) {
					this.$confirm('确认提交吗？', '提示', {}).then(() => {
						this.Loading = true;

						if(this.form.isused){
							 this.form.isused = "0";
						}else{
							this.form.isused = "1";
						}

						let para = Object.assign({}, this.form); //获取表单
						para.parentid = this.data2.id; //吧上级id存入
						para.orgid = "";//主键id置空
						//console.log(para);

						 saveDept(para).then(res => {
							this.Loading = false;//关闭添加等待动画

							//NProgress.done();
							this.$message({
								message: '添加成功',
								type: 'success'
							});
							this.$refs['form'].resetFields();
							this.openIsDisabled = true;//删除和立即修改按钮
							this.getAllDeptList();
						});
					});
				}
			});


		},
		//编辑方法
		editDept: function() {
			this.$refs.form.validate(valid => {
				if (valid) {
					this.$confirm('确认提交吗？', '提示', {}).then(() => {
						this.Loading = true;
						//NProgress.start();
						let para = Object.assign({}, this.form);
						saveDept(para).then(res => {
							this.Loading = false;
							//NProgress.done();
							this.$message({
								message: '修改成功',
								type: 'success'
							});
							this.$refs['form'].resetFields();

							this.getAllDeptList();
						});
					});
				}
			});
		},
		//删除
		deleDepts() {

			//console.log(b.children.length);
			if(b.children.length == 0){
				this.$refs.form.validate(valid => {
					if (valid) {
						this.$confirm('删除之后无法找回,确认删除数据吗？', '提示', {}).then(() => {
							this.Loading = true;
							//NProgress.start();
							let params ={id: this.form.orgid};
							deleteDept(params).then(res => {
								this.Loading = false;
								//NProgress.done();
								this.$message({
									message: '删除成功',
									type: 'success'
								});
								this.$refs['form'].resetFields();

								this.getAllDeptList();
							});
						});
					}
				});

			}else{
				this.$message({
					message: '此目录下有子节点,无发删除!',
					type: 'warning'
				});
			}
			alert(this.form.orgid);
		}
	},
	onSubmit() {
		console.log('submit!');
	},
	mounted() {
		//刷新加载选中部门负责人
		this.getuser();
		this.getAllDeptList();
	}
};
</script>
