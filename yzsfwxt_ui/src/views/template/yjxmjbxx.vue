<!--项目基本信息模板界面-->
<template>
	<section>
		<el-row style="margin-top: 15px;">
			<el-col :span="24" >
				<el-card class="box-card">
					<div slot="header" class="clearfix">
						<div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">项目基本信息</div>
					</div>
					<div class="text item">
						<el-form :model="xmxxForm" label-width="80px" ref="xmxxForm" :inline="true">
							<el-row>
								<el-col :span="8">
									<el-form-item label="申请编号">
										<el-input size="small" v-model="xmxxForm.sqbh" :readonly="true" class="inputBox"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="8">
									<el-form-item label="项目编号">
										<el-input size="small" v-model="xmxxForm.xmbh" :readonly="true" :disabled="flag" class="inputBox"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="8">
									<el-form-item label="项目名称">
										<el-input size="small" v-model="xmxxForm.xmmc" :readonly="true" :disabled="flag" class="inputBox"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="8">
									<el-form-item label="工程地址">
										<el-input size="small" v-model="xmxxForm.xmdz" :readonly="true" class="inputBox"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="8">
									<el-form-item label="联系人">
										<el-input size="small" v-model="xmxxForm.lxr" :readonly="true" class="inputBox"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="8">
									<el-form-item label="联系方式">
										<el-input size="small" v-model="xmxxForm.lxrsjh" :readonly="true" class="inputBox"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
							<el-row>
								<el-col :span="8">
									<el-form-item label="验收日期">
										<el-date-picker size="small" type="date" placeholder="" v-model="xmxxForm.zhyrq"
														:readonly="true" style="padding: 0px;width: 130%;">
										</el-date-picker>
									</el-form-item>
								</el-col>
							</el-row>
						</el-form>
					</div>
				</el-card>

			</el-col>
		</el-row>
	</section>
</template>
<script>

	import { findXmxx } from '../../api/xkylx';
	export default {
		data() {
			return {
				xmxxForm: {
					sqbh : '',//申请单编号
					xmbh : '',//项目编号
					sqlx : '',//受理类型
					lxr : '',//联系人
					lxrsjh : '',//联系人联系方式
					zhyrq : '',//综合验收日期
					xmdz : '',//项目地址
					xmmc : ''//项目名称
				},
				flag:false,//是否立项
				xmid:this.$route.query.xmid
			}
		},

		methods: {
			//获取项目基本信息
			getXmxx() {


			}

		},
		mounted() {

			let params = {
				xmid :this.xmid,
				type : 'yj'
			};
			findXmxx(params).then((res) => {
				this.xmxxForm.sqbh = res.data.xmxx[0].sqbh;
				this.xmxxForm.xmbh = res.data.xmxx[0].xmbh;
				this.xmxxForm.sqlx = res.data.xmxx[0].sqlx;
				this.xmxxForm.lxr = res.data.xmxx[0].lxr;
				this.xmxxForm.lxrsjh = res.data.xmxx[0].lxrsjh;
				this.xmxxForm.zhyrq = res.data.xmxx[0].sjyssj;
				this.xmxxForm.xmdz = res.data.xmxx[0].xmdz;
				this.xmxxForm.xmmc = res.data.xmxx[0].xmmc;
			});

		}
	}

</script>

<style scoped>
	/*.itemRow{
    width:32%;
}
.itemRow .el-input {
    width: 150%;
}*/
	.inputBox{
		width: 150% !important;
	}
</style>