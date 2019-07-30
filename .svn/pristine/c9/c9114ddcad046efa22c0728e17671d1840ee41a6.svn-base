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
						<span>选择配合联合勘查的部门</span>
					</div>
					<div class="text item">
						<el-row class="row">
							<el-col :span="1">&nbsp;</el-col>
							<el-col :span="4">&nbsp;</el-col>
							<el-col :span="4">勘查部门</el-col>
							<el-col :span="4">是否要求到场</el-col>
						</el-row>
						<el-row class="row">
							<el-col :span="1">&nbsp;</el-col>
							<el-col :span="4" >设计部门</el-col>
							<el-col :span="4"><el-switch v-model="check_value.sjy_value" active-color="#13ce66" inactive-color="#ff4949"></el-switch></el-col>
							<el-col :span="4"><el-switch v-model="check_value.sjydc_value" active-color="#13ce66" inactive-color="#ff4949"></el-switch></el-col>
						</el-row>
						<el-row class="row">
							<el-col :span="1">&nbsp;</el-col>
							<el-col :span="4">施工单位</el-col>
							<el-col :span="4"><el-switch v-model="check_value.sgdw_value" active-color="#13ce66" inactive-color="#ff4949"></el-switch></el-col>
							<el-col :span="4"><el-switch v-model="check_value.sgdwdc_value" active-color="#13ce66" inactive-color="#ff4949"></el-switch></el-col>
						</el-row>
						<el-row class="row">
							<el-col :span="1">&nbsp;</el-col>
							<el-col :span="4">客发预算科</el-col>
							<el-col :span="4"><el-switch v-model="check_value.ysk_value" active-color="#13ce66" inactive-color="#ff4949"></el-switch></el-col>
							<el-col :span="4"><el-switch v-model="check_value.yskdc_value" active-color="#13ce66" inactive-color="#ff4949"></el-switch></el-col>
						</el-row>
						<el-row style="margin-top: 20px;">
							<el-col :span="12">&nbsp;</el-col>
							<el-col :span="12">
								预约勘查时间：<el-date-picker v-model="kcsj" type="datetime" placeholder="选择日期时间" default-time="14:00:00">
							</el-date-picker>
								<el-button @click="toXckcrwcj">取消</el-button>
								<el-button type="primary" @click="saveXckcTask">提交</el-button>
							</el-col>
						</el-row>
						<el-row class="row"></el-row>
					</div>
				</el-card>
			</el-col>

			<el-col :span="24" style="text-align: center;margin-top: 35px;">

			</el-col>
		</el-row>

	</section>
</template>
<style lang="css">
	.row{
		margin-top: 14px;
	}
	.row .el-col{
		text-align: center;
	}
</style>
<script>
	import XMJBXX from '@/views/template/xmjbxx.vue';
	import util from '../../common/js/util'

	import { saveXckcRwzb } from '../../api/xkylx';
	export default {
		data() {
			return {
				kcsj : '',//预约勘查时间
				check_value :{
					sjy_value: true,
					sgdw_value: true,
					ysk_value: true,
					sjydc_value: true,
					sgdwdc_value: true,
					yskdc_value: true
				}
			}
		},
		methods: {
			toXckcrwcj(){//跳转页面Xckcrwcj
				this.$router.push({path: '/YSwt.vue', query: {}});
			},
			saveXckcTask(){//发起联合勘查
				let params = Object.assign({ yykcrq : this.kcsj }, this.check_value)
				saveXckcRwzb(params).then((res) => {
					toXckcrwcj();
				});
			}
		},
		mounted() {

		},
		components: {
			'XMJBXX': XMJBXX  //将别名demo 变成 组件 Demo
		}

	}

</script>

<style scoped>

</style>