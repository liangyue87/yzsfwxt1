<template>
	<el-row :gutter="30">
		<el-col :span="5">
			<el-card class="box-card">
			<div class="grid-content bg-purple">
				<el-tree
					node-key="id"
					:data="data"
					:props="defaultProps"

					@node-click="handleNodeClick"
				></el-tree>
			</div>
			</el-card>
		</el-col>
		<!--编辑页面-->
		<el-col :span="12">
			<el-card class="box-card">
				<div class="grid-content bg-purple-light">
					<el-form
						ref="form"
						:model="form"
						label-width="120px"
						@submit.prevent="onSubmit"
					>
						<el-form-item>
							<div style="padding-bottom: 15px;">
								<el-button
									type="primary"
									@click="addMenu('form')"

								>
									添加子节点
								</el-button>
								<el-button
									type="danger"
									@click.native="deleMenu"

								>
									删除子节点
								</el-button>
							</div>
						</el-form-item>
						<el-form-item label="菜单名称" prop="menuname">
							<el-input v-model="form.menuname"></el-input>
						</el-form-item>
						<el-form-item label="页面地址" prop="menuurl">
							<el-input v-model="form.menuurl"></el-input>
						</el-form-item>
						<el-form-item label="排列序号" prop="ordinal">
							<el-input v-model="form.ordinal"></el-input>
						</el-form-item>
						<el-form-item label="页面框架" prop="target">
							<el-input v-model="form.target"></el-input>
						</el-form-item>
						<el-form-item label="大图标地址" prop="bigicon">
							<el-input v-model="form.bigicon"></el-input>
						</el-form-item>
						<el-form-item label="小图标地址" prop="menuicon">
							<el-input v-model="form.menuicon"></el-input>
						</el-form-item>
						<el-form-item label="是否启用" prop="valid">
							<el-switch on-text="ok"
									   off-text="no"

						</el-form-item>
						<el-form-item>
							<div v-if="show">
								<el-button
									type="primary"
									@click.native="editMenu"
                                    :loading="Loading"
								>
									立即修改
								</el-button>
								<el-button @click.native="empty('form')">重置</el-button>
							</div>
							<div v-else>
								<el-button
									type="success"
									@click.native="addMenus"
                                    :loading="Loading"
								>
									立即创建
								</el-button>
								<el-button @click.native="empty('form')">重置</el-button>
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
import { getAllMenuList, getMenuById ,deleteMenu ,saveMenu } from '../../api/menu';
var b;
export default {
	data() {
		return {

            show: true,//添加/修改的按钮
			data: [],
			data2:[],
            Loading: false, //加载画面属性
			defaultProps: {
				children: 'children',
				label: 'text'
			},
			form: {
				parentid:'', //上级菜单
				menuname: '', //菜单名称
				menuurl: '', //菜单地址
				ordinal: '', //排序
				bigicon: '', //大图标地址
				menuicon: '', //小图标地址
				valid: '', //是否启用
				target: '', //页面框架
                menuid:''   //主键id
			}
		};
	},
	methods: {
        //清空列表
        empty(formName){
            this.$refs[formName].resetFields();//清空列表
        },
		//添加按钮
		addMenu(formName){

			this.$refs[formName].resetFields();//清空列表
			this.show = false;
			
		},
        //树点击事件
		handleNodeClick(data) {
            b = data;
			this.data2 = data;
			console.log(this.data2);
			  this.show= true;
			var params = {
				menuid : data.id
			};
			getMenuById(params).then(res => {
				this.form = res.data.menuEntity;
				//NProgress.done();
			});



		},
        //添加事件
        addMenus(){

			this.$refs.form.validate(valid => {
				if (valid) {
					//this.$confirm('确认提交吗？', '提示', {}).then(() => {
						this.Loading = true;

						if(this.form.isused){
							this.form.valid = "0";
						}else{
							this.form.valid = "1";
						}

						let para = Object.assign({}, this.form); //获取表单

						para.parentid = this.data2.id; //吧上级id存入
						para.menuid = "";//主键id置空
						//console.log(para);

						saveMenu(para).then(res => {
							this.Loading = false;//关闭添加等待动画

							//NProgress.done();
							this.$message({
								message: '添加成功',
								type: 'success'
							});
							this.$refs['form'].resetFields();

							this.getMenu();
						});
					//});
				}
			});


		},
        //删除事件
        deleMenu(){
        //console.log(b.children.length);父节点下面有几个子节点
            if(b.children.length == 0){
                this.$refs.form.validate(valid => {
                    if (valid) {
                        this.$confirm('删除之后无法找回,确认删除数据吗？', '提示', {}).then(() => {
                            this.Loading = true;
                            //NProgress.start();
                            let params ={id: this.form.menuid};
                            deleteMenu(params).then(res => {
                                this.Loading = false;
                                //NProgress.done();
                                this.$message({
                                    message: '删除成功',
                                    type: 'success'
                                });
                                this.$refs['form'].resetFields();

                                this.getMenu();
                            });
                        });
                    }
                });

            }else{
                this.$message({
                    message: '此目录下有内容,无发删除!',
                    type: 'warning'
                });
            }
            alert(this.form.menuid);
        },
        //修改事件
        editMenu(){
			this.$refs.form.validate(valid => {
				if (valid) {
					//this.$confirm('确认提交吗？', '提示', {}).then(() => {
						this.Loading = true;
						let para = Object.assign({}, this.form); //获取表单
						saveMenu(para).then(res => {
							this.Loading = false;//关闭添加等待动画
							this.$message({
								message: '修改成功',
								type: 'success'
							});
							this.$refs['form'].resetFields();

							this.getMenu();
						});
					//});
				}
			});

        },
		getMenu() {
			//NProgress.start();
			getAllMenuList().then(res => {
				this.data = res.data;
				//NProgress.done();
			});
		}
	},
	onSubmit() {
		console.log('submit!');
	},
	mounted() {
		this.getMenu();
	}
};
</script>
