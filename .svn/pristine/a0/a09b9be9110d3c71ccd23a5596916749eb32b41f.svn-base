<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<el-row>
			<el-col :span="24">
				<!--红黄绿灯时间-->
				<hhldsx :xmid="xmid" :activityid="activityId"></hhldsx>
				<!--项目基本信息模板-->
				<XMJBXX></XMJBXX>
				<!--配合联合勘查的部门-->
				<el-card class="box-card"  style="margin-top: 15px;">
					<div slot="header" class="clearfix">
						<div class="div_title">指派预算勘查人员</div>
					</div>
					<div class="text item">
						<!--可指派的人员-->
						<el-row>
							<el-form label-width="80px">
								<el-col :span="24">
									<el-form-item label="预约时间">
										<el-date-picker v-model="kcsj" type="datetime"
														format="yyyy-MM-dd HH:mm" size="small" style="width: 200px;" readonly disabled>
										</el-date-picker>
									</el-form-item>
								</el-col>
								<el-col :span="24" style="margin-bottom: 0px;">
									<el-form-item label="勘查地点">
										<el-input type="text" v-model="kcdd" style="width: 400px;" readonly disabled></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="24" style="margin-bottom: 35px;">
									<el-form-item label="勘查备注">
										<el-input type="textarea" v-model="kcbz" style="width: 400px;" readonly disabled></el-input>
									</el-form-item>
								</el-col>
							</el-form>
							<el-col :span="6" v-model="data" v-for="item in data" style="margin-bottom: 25px;" :key="item.USERID">
								<el-radio v-model="radio" :label="item.USERID">{{item.USERNAME}}（在建项目{{item.NUM}}个）</el-radio>
							</el-col>
						</el-row>
						<el-row class="row"></el-row>
					</div>
				</el-card>
				<el-row style="margin-top: 25px;">
					<el-col :span="24" style="text-align: center">
						<el-button size="mini" type="info" @click="toRwlbsjy">
							<i class="iconfont icon-quxiao"></i>取消
						</el-button>

						<el-button size="mini" type="primary" @click="taskZpSjy" :loading="sumbitLoading">
							<i class="iconfont icon-shenhetongguorenyuan"></i>提交
						</el-button>

						<el-button size="mini" type="primary" @click="goxmqj()">
							<i class="iconfont icon-xiangmuquanjing_"></i>项目全景</el-button>
					</el-col>
				</el-row>
			</el-col>
		</el-row>

	</section>
</template>
<style lang="css">
	.row{
		margin-top: 0px;
	}
	.col{
		margin-top: 12px;
	}
</style>
<script>
	import Vue from 'vue'
	import XMJBXX from '@/views/template/xmjbxx.vue';
	import util from '../../common/js/util'
	import { saveTaskZp,getZpry,updateCheckTask } from '../../api/xkylx';
	import hhldsx from '@/views/template/hhldsx.vue';

	export default {
		data() {
			return {
				radio : '',//选中的指派人员
				type : '4',
				data :[],
				kcsj : '',//预约勘查时间
				kcbz : '',//勘查备注
				kcdd:'',
				xmid:this.$route.query.xmid,
				activityId:this.$route.query.activityId,
				sumbitLoading : false
			}
		},
		methods: {
			toRwlbsjy(){//跳转页面Rwlbsjy
				this.$routerTab.close();
				this.$routerTab.refresh('/Backlog');
				this.$routerTab.refresh('/MyWork');
			},
			goxmqj(){
				this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid }})
			},
			getZprySjy(){//获取预算科指派人员名单
				let params = {type : this.type,xmid : this.xmid};
				getZpry(params).then((res) => {
					this.data = res.data.zpry;
					this.kcsj = res.data.yykcsj;
					this.kcbz = res.data.kcbz;
					this.kcdd = res.data.kcdd;
				});
			},
			taskZpSjy(){//预算科指派人员
				if(this.radio == ''){
					this.$message({
						message: '未选择指派人员',
						type: 'warning'
					});
					return;
				}
				this.sumbitLoading = true;
				let params = {
					type : this.type,
					ryid : this.radio,
					xmid : this.xmid
				};
				saveTaskZp(params).then((res) => {
					this.sumbitLoading = false;
					this.$message({
						message: '指派成功',
						type: 'success'
					});
					this.toRwlbsjy();
				});
			}

		},
		mounted() {
			updateCheckTask({xmid:this.xmid,type:this.type}).then((res) => {});//更改任务签收状态
			this.getZprySjy();

		},
		components: {
			'hhldsx': hhldsx,
			'XMJBXX': XMJBXX  //将 别名 XMJBXX  变成 组件 XMJBXX
		}

	}
</script>

<style scoped>
	.div_title {
		margin:-18px -20px;
		padding:10px 20px;
		background: #f5f7fa;
		height: 18px;
	}
</style>
