<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<el-row>
			<el-col :span="24">
				<!--项目基本信息模板-->
				<XMJBXX></XMJBXX>
				<el-card  class="box-card"  style="margin-top: 15px;">
					<div slot="header" class="clearfix">
						<span>点志记内容数据管理</span>
					</div>
					<div class="text item">
						<el-tabs v-model="activeName" type="card" >
							<el-tab-pane label="阀门管理" name="1"><fmgl :xmid="xmid" :isReadonly="isReadonly"></fmgl></el-tab-pane>
							<el-tab-pane label="消防栓管理" name="2"><Xhsgl :xmid="xmid" :isReadonly="isReadonly"></Xhsgl></el-tab-pane>
							<!--<el-tab-pane label="地表管理" name="5"><hbpz :xmid="xmid"></hbpz></el-tab-pane>-->
							<!--<el-tab-pane label="五级计量表管理" name="6"><hbpz :xmid="xmid"></hbpz></el-tab-pane>-->
							<el-tab-pane label="点志记管理" name="3"><dzjgl :xmid="xmid" :isReadonly="isReadonly"></dzjgl></el-tab-pane>
						</el-tabs>
					</div>
				</el-card>
				<el-card  class="box-card"  style="margin-top: 15px;">
					<div slot="header" class="clearfix">
						<span>表位移交基本信息</span>
					</div>
					<div class="text item">
						<el-form  :model="bwyjdForm" >
							<el-input style="display: none" v-model="bwyjdForm.id"  ></el-input>
							<el-form-item>
								<el-col :span="3" style="text-align: right">
									<span>表位移交单编号：</span>
								</el-col>
								<el-col :span="5">
									<el-input readonly v-model="bwyjdForm.bwyjdbh" v-if="bwyjdForm.bwyjdbh!=null&&bwyjdForm.bwyjdbh!=''" ></el-input>
									<el-input readonly v-else value="保存后自动生成" ></el-input>
								</el-col>
								<el-col :span="2" style="text-align: right" >
									<span>移交方式：</span>
								</el-col>
								<el-col :span="6">
									<el-radio-group v-model="bwyjdForm.yjfs">
										<el-radio :label="1">普通移交</el-radio>
										<el-radio :label="2">现场移交</el-radio>
									</el-radio-group>
								</el-col>
								<el-col :span="2" style="text-align: right" v-if="bwyjdForm.yjfs==2">
									<span>预约时间：</span>
								</el-col>
								<el-col :span="6" v-if="bwyjdForm.yjfs==2">
									<el-date-picker format="yyyy-MM-dd HH:mm" value-format="yyyy-MM-dd HH:mm" type="datetime" placeholder="选择日期" v-model="bwyjdForm.yysj_vue" style="width: 100%"></el-date-picker>
								</el-col>
							</el-form-item>
						</el-form>
					</div>
				</el-card>
				<el-row style="text-align: center;margin-top: 20px;">
					<router-link :to="tabUrl">
						<el-button type="info" size="mini" @click="cancel()"><i class="iconfont icon-quxiao" ></i>取消</el-button>
						<el-button type="primary" size="mini"@click="submit_vue()" v-if="!iscontinue"  :loading="loading"><i class="iconfont icon-queren"></i>提交</el-button>
					</router-link>
					<el-button size="mini" type="primary" @click="goxmqj()"style="margin-left: 10px" ><i class="iconfont icon-xiangmuquanjing_"></i>项目全景</el-button>
				</el-row>
			</el-col>
		</el-row>
    </section>
</template>

<style>

</style>
<script>
	import XMJBXX from '@/views/template/xmjbxx.vue';
	import fmgl from '@/views/yj/bwyj/dzjnrsjgl/fmgl.vue';
	import dzjgl from '@/views/yj/bwyj/dzjnrsjgl/dzjgl.vue';
    import Xhsgl from "./dzjnrsjgl/xhsgl";
	import {bwyjdzjSubmit,submitShow} from '../../../api/bwyj';
	import {rwtxfsdx} from '../../../api/sjst';

	export default {

		data() {
			return {
				tabUrl:'/bwyjHandle?xmid='+this.$route.query.xmid+'&isReadonly='+this.$route.query.isReadonly,
				loading:false,
				isReadonly:this.$route.query.isReadonly,
				xmid:this.$route.query.xmid,
				activeName:'3',
				activityId:'90009',
				iscontinue:false,
				bwyjdForm:{
					bwyjdbh:"",
					yjfs:1,
					yysj_vue:""
				}
			}
		},
		methods: {
			//跳转至项目全景
			goxmqj(){
				this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid  }})
			},
			submitShow_vue(){
				let params={
					xmid:this.xmid
				}
				submitShow(params).then((res)=>{
					this.iscontinue=res.data;
				})
			},
			cancel(){
				this.tabUrl='/bwyjlb'
				this.$routerTab.close();
				this.$routerTab.refresh(this.tabUrl);
			},
			submit_vue(){
				this.$confirm('您正在递交工作结果，一旦递交确认后将不能打回或再次修改，你确定要递交吗？', '提示', {
					type: 'warning'
				}).then(() => {
					let param = {
						xmid:this.xmid,
						activityId:this.activityId,
						type:"bwyj"
					}
					let params = Object.assign(param,this.bwyjdForm);
					this.loading=true;

					bwyjdzjSubmit(params).then((res)=>{
						this.loading=false;
						if(res.data==1){
							this.$message.success('提交成功！');
							let param={
								"xmid":this.xmid,
								"activitiId":this.activityId,
								"direction":0
							}
							rwtxfsdx(param).then((res)=>{})
							this.tabUrl='/bwyjlb'
							this.$routerTab.close();
							this.$routerTab.refresh('/bwyjlb');
						}else if(res.data==-1){
							this.$message.error('该环节缺少任务办理人，环节无法扭转，提交失败！');
						}else if(res.data==-2){
							this.$message.error('该环节有多个任务办理人，环节无法扭转，提交失败！');
						}else{
							this.$message.error('提交失败！');
						}
					})
				})

			}
		},

			mounted() {
				this.submitShow_vue();

			},
			components: {
                Xhsgl,
				'XMJBXX': XMJBXX,  //将别名demo 变成 组件 Demo
				'fmgl':fmgl,
				'dzjgl':dzjgl
			}
	}

</script>
