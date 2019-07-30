<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<el-row>
			<el-col :span="24">
				<!--项目基本信息模板-->
				<XMJBXX></XMJBXX>
				<!--配合联合勘查的部门-->
				<el-card class="box-card"  style="margin-top: 15px;">
					<div slot="header" class="clearfix">
						<span>勘查反馈</span>
					</div>
					<div class="text item rowDiv" style="width: 100%;">
						<el-form :model="kcfk" label-width="100px" ref="xmxxForm">
							<el-row>
								<el-col :span="12">
									<el-form-item label="调查项分类">
										<el-radio v-model="kcfk.dcxfl" label="1">项目周边管网</el-radio>
										<el-radio v-model="kcfk.dcxfl" label="2">具体位置测压</el-radio>
									</el-form-item>
								</el-col>
								<el-col :span="12">
									<span>注：调查项主要内容，一张调查表可以有多个调查项</span>
								</el-col>
							</el-row>

							<el-row>
								<el-col :span="12">
									<el-form-item label="线路主路">
										<el-input type="text" v-model="kcfk.gxzl"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="12">
									<el-form-item label="管线路段">
										<el-input type="text" v-model="kcfk.gxld"></el-input>
									</el-form-item>
								</el-col>
							</el-row>

							<el-row>
								<el-col :span="1">&nbsp;</el-col>
								<el-col :span="20"></el-col>
								管线在主路的
								<el-select v-model="kcfk.gxzzl" placeholder="请选择" style="width:10%;">
									<el-option
											v-for="item in zlwz"
											:key="item.value"
											:label="item.label"
											:value="item.value">
									</el-option>
								</el-select>
								侧，距主路中线
								<el-input type="text" v-model="kcfk.gxjzl" style="width:10%;"></el-input>
								米，正上方覆盖物
								<el-select v-model="kcfk.gxzsf" placeholder="请选择" style="width:15%;">
									<el-option
											v-for="item in zsffgw"
											:key="item.value"
											:label="item.label"
											:value="item.value">
									</el-option>
								</el-select>
								。
							</el-row>


							<el-row>
								<el-row><el-col :span="1">&nbsp;</el-col></el-row>
								<el-col :span="1">&nbsp;</el-col>
								<el-col :span="20">
									<span>管线主路：管线所在道路名称，非市政道路不填。若调查测压点压力，可在调查点位置中填写位置信息。</span>
								</el-col>
								<el-row><br><el-col :span="2">&nbsp;</el-col></el-row>
							</el-row>

							<el-row>
								<el-col :span="12">
									<el-form-item label="测压对象">
										<el-input type="text" v-model="kcfk.cydx"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="12">
									<el-form-item label="测压时间">
										<el-date-picker v-model="kcfk.cysj" type="datetime" placeholder="选择日期时间"
														value-format="yyyy-MM-dd HH:mm" format="yyyy-MM-dd HH:mm" ></el-date-picker>
									</el-form-item>
								</el-col>
							</el-row>

							<el-row>
								<el-col :span="24">
									<el-form-item label="调查点位置">
										<el-input type="text" v-model="kcfk.dcdwz" style="width: 100%;"></el-input>
									</el-form-item>
								</el-col>
							</el-row>

							<el-row>
								<el-col :span="1">&nbsp;</el-col>
								<el-col :span="12">
									<span>测压对象：指消防栓、小区首层最高点（一层水龙头）等具体供水设施。</span><br>
									<span>调查点位置：指测压点、装表点等具有点特征的地址，如：A路与B路交口C侧D米，A路B小区门口C侧D米。</span>
								</el-col>

							</el-row>

							<el-row><el-col :span="2">&nbsp;</el-col></el-row>

							<el-row>
								<el-col :span="12">
									<el-form-item label="测压点压力">
										<el-input type="text" v-model="kcfk.cydyl"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="12">
									<el-form-item label="测压点标高">
										<el-input type="text" v-model="kcfk.cydbg"></el-input>
									</el-form-item>
								</el-col>
							</el-row>

							<el-row>
								<el-col :span="3">
									<el-form-item label="">
										<el-checkbox v-model="kcfk.bwts" true-label="1" false-label="0">并网通水</el-checkbox>
										<!--<el-input type="text" v-model="kcfk.bwts"></el-input>-->
									</el-form-item>
								</el-col>
								<el-col :span="3">
									<el-form-item label="">
										<el-checkbox v-model="kcfk.gwyj" true-label="1" false-label="0">管网移交</el-checkbox>
										<!--<el-input type="text" v-model="kcfk.gwyj"></el-input>-->
									</el-form-item>
								</el-col>
							</el-row>

							<el-row>
								<el-col :span="12">
									<el-form-item label="调查人">
										<el-input type="text" v-model="kcfk.dcr"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="12">
									<el-form-item label="调查日期">
										<el-date-picker v-model="kcfk.dcrq" type="datetime" placeholder="选择日期时间"
														value-format="yyyy-MM-dd HH:mm" format="yyyy-MM-dd HH:mm" ></el-date-picker>
										<!--<el-input type="text" v-model="kcfk.dcrq"></el-input>-->
									</el-form-item>
								</el-col>
							</el-row>

							<el-row>
								<el-col :span="24">
									<el-form-item label="备注">
										<el-input type="textarea" v-model="kcfk.bz"></el-input>
									</el-form-item>
								</el-col>
							</el-row>

						</el-form>
					</div>
				</el-card>
				<el-row style="text-align: center;margin-top: 25px;">
					<el-button @click="toRwFklb">取消</el-button>
					<el-button type="primary" @click="submitKcfk">提交</el-button>
				</el-row>
			</el-col>
		</el-row>

	</section>
</template>
<style lang="css">
	.rowDiv el-row{
		margin: 10px;
	}
</style>
<script>
	import axios from 'axios'
	import XMJBXX from '@/views/template/xmjbxx.vue';
	import util from '../../../common/js/util'
	import { saveXckcFk } from '../../../api/xkylx';

	export default {
		data() {
			return {
				type : '3',//区所
				kcsj : '2019-10-10 15:00:00',//预约勘查时间
				xmid:this.$route.query.xmid,
				zlwz: [{value:'1',label:'上方'},{value:'2',label:'下方'},{value:'3',label:'左右侧'}],
				zsffgw: [{value:'1',label:'杆子'},{value:'2',label:'道路'},{value:'3',label:'柱子'}],
				kcfk : {
					dcxfl:'',
					gxzl: '',
					gxld: '',
					gxjzl: '',
					gxzzl: '',
					gxzsf: '',
					gxkj: '',
					gxcz: '',
					cydx: '',
					cysj: '',
					dcdwz: '',
					cydbg: '',
					cydyl: '',
					gwyj: '',
					bwts: '',
					dcr: '',
					dcrq: '',
					bz: ''
				},
				fileList : []
			}
		},
		methods: {
			toRwFklb(){
				this.$router.push({path: '/Rwfklbqs', query: {}})
			},
			submitKcfk(){//提交勘察反馈
				let params = Object.assign({ type: this.type,xmid: this.xmid}, this.kcfk);
				saveXckcFk(params).then((res) => {
					if(res.result == 'success'){
						this.$message({
							message: '提交反馈结果成功',
							type: 'success'
						});
						this.toRwFklb();
					}else {
						this.$message({
							message: '提交反馈结果异常',
							type: 'error'
						});
					}
				});
			},
			submitUpload() {
				let fileList = this.fileList;
				let operation = "设计院反馈勘查附件";

				if (fileList !=null && fileList.length >0){
					for (let i=0;i<fileList.length;i++){
						let file = fileList[i];
						let formData = new FormData();
						formData.append("operation",operation);
						formData.append("multipartFiles",file.raw);
						formData.append("filename",file.name);
						formData.append("type","sjy");
						let instance = axios.create({
							baseURL:'http://localhost:8080',
							headers: {'Content-Type':'multipart/form-data'}
						});
						instance.post('/XkylxController/importFile',formData).then((res) => {
							let msg = res.data.msg;
							if(msg == '上传成功'){
								this.$message({
									message: '上传成功',
									type: 'success'
								});
							}else{
								this.$message.error('上传失败！');
							}
						});
					}
				}else {
					this.$message.warning('未选择任何文件！');
				}
			}

		},
		mounted() {


		},
		components: {
			'XMJBXX': XMJBXX  //将别名demo 变成 组件 Demo
		}

	}
</script>