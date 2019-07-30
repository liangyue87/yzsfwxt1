<template>
	<el-row>
		<el-col :span="24">
			<el-row style="margin-top: 15px;">
				<el-col :span="24">
					<el-card class="box-card">
						<div slot="header" class="clearfix"><span>项目基本信息</span></div>
						<div class="text item">
							<el-form :model="Ysfrom" label-width="120px" ref="xmxxForm" :inline="true">
								<el-form-item label="项目编号"><el-input v-model="Ysfrom.xmbh" :readonly="true" style="width: 300px"></el-input></el-form-item>
								<el-form-item label="项目名称"><el-input v-model="Ysfrom.xmmc" :readonly="true" style="width: 300px"></el-input></el-form-item>
								<el-form-item label="联系人"><el-input v-model="Ysfrom.lxr" :readonly="true" style="width: 300px"></el-input></el-form-item>
								<el-form-item label="联系方式"><el-input v-model="Ysfrom.lxrsjh" :readonly="true" style="width: 300px"></el-input></el-form-item>
								<el-form-item label="立项时间"><el-input v-model="Ysfrom.lxsj" :readonly="true" style="width: 300px"></el-input></el-form-item>
								<el-form-item label="项目地址"><el-input v-model="Ysfrom.xmdz" :readonly="true" style="width: 300px"></el-input></el-form-item>
							</el-form>
						</div>
					</el-card>
				</el-col>
			</el-row>
		</el-col>
		<el-col :span="24" style="text-align: center;margin-top: 35px;"></el-col>
	</el-row>
</template>

<script>
</script>

<style>
</style>
