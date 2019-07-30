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
						<el-form :model="xmxxForm" label-width="70px" ref="xmxxForm" :inline="true">
							<el-row>
								<el-col :span="6">
									<el-form-item label="申请编号" v-if="!flag">
										<el-input v-model="xmxxForm.sqbh" :readonly="true" size="small" class="inputBox"></el-input>
									</el-form-item>
								</el-col>

								<el-col :span="6" >
									<el-form-item label="项目编号" v-if="flag">
										<el-input v-model="xmxxForm.xmbh" :readonly="true" size="small" 	class="inputBox"></el-input>
									</el-form-item>
								</el-col>

								<el-col :span="6">
									<el-form-item label="项目名称" v-if="flag">
										<el-input v-model="xmxxForm.xmmc" :readonly="true" size="small" class="inputBox"></el-input>
									</el-form-item>
								</el-col>

								<el-col :span="6">
									<el-form-item label="项目地址" v-if="flag">
										<el-input v-model="xmxxForm.xmdz" :readonly="true" size="small" class="inputBox"></el-input>
									</el-form-item>
								</el-col>

								<el-col :span="6">
									<el-form-item label="工程名称" v-if="!flag">
										<el-input v-model="xmxxForm.xmmc" :readonly="true" size="small" class="inputBox"></el-input>
									</el-form-item>
								</el-col>

								<el-col :span="6">
									<el-form-item label="工程地址" v-if="!flag">
										<el-input v-model="xmxxForm.xmdz" :readonly="true" size="small" class="inputBox"></el-input>
									</el-form-item>
								</el-col>

								<!--<el-col :span="6">
									<el-form-item label="联系人">
										<el-input v-model="xmxxForm.lxr" :readonly="true" size="small" class="inputBox"></el-input>
									</el-form-item>
								</el-col>-->

								<el-col :span="6">
									<el-form-item label="所属区域">
										<el-input v-model="xmxxForm.ssqy" :readonly="true" size="small" class="inputBox"></el-input>
									</el-form-item>
								</el-col>

								<el-col :span="6">
									<el-form-item label="报建单位">
										<el-input v-model="xmxxForm.bjdw" :readonly="true" size="small" class="inputBox"></el-input>
									</el-form-item>
								</el-col>

								<el-col :span="6">
									<el-form-item label="联系人">
										<el-input v-model="xmxxForm.lxr" :readonly="true" size="small" class="inputBox"></el-input>
									</el-form-item>
								</el-col>

								<el-col :span="6">
									<el-form-item label="联系方式">
										<el-input v-model="xmxxForm.lxrsjh" :readonly="true" size="small" class="inputBox"></el-input>
									</el-form-item>
								</el-col>

								<el-col :span="6">
									<el-form-item label="受理日期">
										<el-date-picker type="date" placeholder="" v-model="xmxxForm.slsj" size="small"
														:readonly="true" style="padding: 0px;width: 100%;">
										</el-date-picker>
									</el-form-item>
								</el-col>

								<el-col :span="6" v-if="flag">
									<el-form-item label="立项日期">
										<el-date-picker type="date" placeholder="未立项" v-model="xmxxForm.lxsj" size="small"
														:readonly="true" style="padding: 0px;width: 100%;">
										</el-date-picker>
									</el-form-item>
								</el-col>

								<el-col :span="6">
									<el-form-item label="项目经理">
										<el-input v-model="xmxxForm.xmfzr_str" :readonly="true" size="small" class="inputBox"></el-input>
									</el-form-item>
								</el-col>

								<el-col :span="6">
									<el-form-item label="联系方式">
										<el-input v-model="xmxxForm.xmfzr_sjh" :readonly="true" size="small" class="inputBox"></el-input>
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
					slsj : '',//受理时间
					lxrq : '',//立项时间
					xmdz : '',//项目地址
					xmmc : '',//项目名称
					xmfzr_str : '',//项目经理
					xmfzr_sjh : '',//项目经理联系方式
					ssqy : '',
					bjdw : ''
				},
				flag : true,//是否立项
				xmid : this.$route.query.xmid
			}
		},
		mounted() {

			let params = {
				xmid :this.xmid
			};
			findXmxx(params).then((res) => {
				let obj = eval("("+ res.data.xmxx +")");
				if(obj != null){
					for(var i in obj){
						if(obj[i] == 'null'){
							obj[i] = '';
						}
					}
					this.xmxxForm = obj;
					this.xmxxForm.ssqy = obj.ssqy_str;
					this.xmxxForm.bjdw = obj.dwmc;
					if(res.data.sflx==0){
						this.flag = false;
						this.xmxxForm.slsj = obj.slrq;
						this.xmxxForm.xmfzr_str = obj.xmjl_str;
						this.xmxxForm.xmfzr_sjh = obj.xmjl_sjh;
						//this.xmxxForm.xmmc = '未立项';
						//this.xmxxForm.xmbh = '未立项';
						//this.xmxxForm.lxsj = '未立项';
					}
				}
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
		width: 115% !important;
	}
</style>