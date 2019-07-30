<template>
    <el-row :gutter="30">
        <el-col :span="5">
            <el-card class="box-card">
				<div class="grid-content bg-purple">
					<el-tree node-key="id" :data="data" :props="defaultProps" @node-click="handleNodeClick"
                         :expand-on-click-node="false" >
					</el-tree>
				</div>
            </el-card>
        </el-col>
        <!--编辑页面-->
        <el-col :span="12">
                <el-card class="box-card" >
                    <div class="grid-content bg-purple-light" >
                        <el-form ref="form" :model="form" label-width="80px" label-height="40px" @submit.prevent="onSubmit">
                            <el-form-item>
                            	<div style="padding-bottom: 15px;">
                            		<el-button
											size="mini"
                            			type="primary"
                            			@click="handleAdd"
                            			
                            		>
                            			添加子节点
                            		</el-button>
                            		<el-button
											size="mini"
                            			type="danger"
                            			@click.native="handleDelete"
                            			style="display: none;"
                            		>
                            			删除子节点
                            		</el-button>
                            	</div>
                            </el-form-item>
							
							
							<el-form-item label="id" style="display:none;">
                                <el-input v-model="form.id"></el-input>
                            </el-form-item>
                            <el-form-item label="编码">
                                <el-input v-model="form.codevalue" :readonly="readonly" :disabled="readonly"></el-input>
                            </el-form-item>
                            <el-form-item label="编码名称">
                                <el-input v-model="form.codename"></el-input>
                            </el-form-item>
                            <el-form-item label="排列序号">
                                <el-input v-model="form.sort"></el-input>
                            </el-form-item>
                            <el-form-item label="说明">
                                <el-input v-model="form.codedescription"></el-input>
                            </el-form-item>
                            <el-form-item label="parentid" style="display:none;">
                                <el-input v-model="form.parentid"></el-input>
                            </el-form-item>
                            
							<el-form-item>
								<div v-if="show">
									<el-button
											size="mini"
										type="primary"
										@click.native="handCodeSave"
										:loading="editLoading"
									>
										立即修改
									</el-button>
									<el-button size="mini" @click.native="empty('form')">重置</el-button>
								</div>
								<div v-else>
									<el-button
											size="mini"
										type="success"
										@click.native="handCodeSave"
										:disabled="openIsDisabled"
										:loading="editLoading"
									>
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
    import util from '../../common/js/util'
    import Vue from 'vue'
    import { getCodeTree,getCodeById,addCode,deleteCode } from '../../api/code';
    var parentid='';
    export default {
        data() {
            return {
				readonly : false,
				show:true,
                data: [],
                defaultProps: {
                    children: 'children',
                    label: 'text',
                },
				editLoading:false,
				listLoading: false,
                form: {
                    codevalue: '',
                    codename: '',
                    sort: '',
                    codedescription: ''
                }
            };

        },
        methods: {
            handleNodeClick(data) {
				this.show = true;
				this.readonly = true;
                parentid=data.id
                var params = {
                    "id" : data.id
                };
                getCodeById(params).then((res) => {
                    this.form = res.data.code;
                    //NProgress.done();
                });
            },
            handCodeSave:function (){
                this.$refs.form.validate((valid) => {
                    if (valid) {
                        //this.$confirm('确认保存吗？', '提示', {}).then(() => {
                            this.editLoading = false;
                            //NProgress.start();
                            let para = Object.assign({}, this.form);
                            // para.birth = (!para.birth || para.birth == '') ? '' : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd');
                            addCode(para).then((res) => {
                                this.editLoading = false;
                                //NProgress.done();
                                this.form = {
                                    id:'',
                                    codevalue: '',
                                    codename: '',
                                    sort: '',
                                    codedescription: '',
                                    parentid: ''
                                };
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['form'].resetFields();
                                // this.editFormVisible = false;
                                this.addFormVisible=false;
                                // this.getCodeById;
                                this.getMenu();
                            });
                        //});
                    }
                });
            },
            getMenu() {
                //NProgress.start();
				this.show = true;
                getCodeTree().then((res) => {
                    this.data = res.data;
                    console.log(res.data);
                    //NProgress.done();
                });
            },
            handleAdd: function () {
                this.readonly = false;
				this.show = false;
                console.log(this.data);
                this.form = {
                    id:'',
                    codevalue: '',
                    codename: '',
                    sort: '',
                    codedescription: '',
                    parentid: parentid
                };
            },
            handleDec:function(){
                this.addFormVisible=false;
            },
            handleDelete:function(){
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = { id: this.form.parentid };
                    deleteCode(para).then((res) => {
                        this.listLoading = false;
                        this.form = {
                            id:'',
                            codevalue: '',
                            codename: '',
                            sort: '',
                            codedescription: '',
                            parentid: ''
                        };
                        if(res == 'err'){
                            this.$message({
                                message: '删除失败',
                                type: 'fail'
                            });
                        }else{
                            this.$message({
                                message: '删除成功',
                                type: 'success'
                            });
                        }
                        this.getMenu();
                    });
                });
            },
            onSubmit() {
                console.log('submit!');
            },
        },

        mounted() {
            this.getMenu();

        }
    };
</script>