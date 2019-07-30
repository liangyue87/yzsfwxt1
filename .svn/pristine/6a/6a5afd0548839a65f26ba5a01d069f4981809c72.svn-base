<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<el-row>
			<el-col :span="24">
				<hhldsx :xmid="xmid" :activityid="activityId"></hhldsx>
				<!--项目基本信息模板-->
				<XMJBXX></XMJBXX>

				<!--附件资料-->
				<zlfljxz></zlfljxz>
				<el-card class="box-card"  style="margin-top: 15px;">
					<div slot="header" class="clearfix">
						<div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">备注</div>
					</div>
					<el-input
							readonly
							type="textarea"
							:rows="4"
							v-model="textarea">
					</el-input>
				</el-card>
				<el-card class="box-card"  style="margin-top: 15px;">
					<div slot="header" class="clearfix">
						<span>指派人员</span>
					</div>
					<div class="text item">
						<!--可指派的人员-->
						<el-row>
							<el-col :span="6" v-model="data" v-for="item in data" class="col" :key="item.USERID">
								<el-radio v-model="radio" :label="item.USERID">{{item.USERNAME}}（在建项目{{item.NUM}}个）</el-radio>
							</el-col>
						</el-row>
						<el-row class="row"></el-row>
					</div>
				</el-card>
				<el-row style="text-align: center;margin-top: 20px;">
					<router-link :to="tabUrl">
						<el-button size="mini"  type="info" @click="cancel()"><i class="iconfont icon-quxiao"></i>取消</el-button>
						<el-button size="mini"  type="primary"@click="submit_vue()" :loading="loading"><i class="iconfont icon-queren"></i>提交</el-button>
					</router-link>
					<el-button size="mini" type="primary" @click="goxmqj()" style="margin-left: 10px;" ><i class="iconfont icon-xiangmuquanjing_"></i>项目全景</el-button>
				</el-row>
			</el-col>
		</el-row>
        <!--<img class="preview-img" v-for="(item, index) in imgList" :src="item.src" height="100" @click="$preview.open(index, imgList)">-->
		<!--<ImageView :src="Imgsrc"></ImageView>-->

    </section>
</template>

<style>

</style>
<script>
	import XMJBXX from '@/views/template/xmjbxx.vue';
	import hhldsx from '@/views/template/hhldsx.vue';
	import zlfljxz from '@/views/template/zlfljxz.vue';
	import ImageView from '../../common/components/form/img-preview/ImageView.vue'
	import axios from 'axios';
	import { getZlfl,goDetail,getZpry,sjrwfpSubmit,download_sjst,rwtxfsdx } from '../../api/sjst';
	export default {
		components:{
			ImageView
		},
		data() {
			return {
				textarea:"",
				loading: false,
				zlrow:[],
				data:[],
				radio:"",
				gridData:[],
				xmid:this.$route.query.xmid,
				activityId:this.$route.query.activityId,
				tabUrl:'/sjrwfp?xmid='+this.$route.query.xmid+'&activityId='+this.$route.query.activityId
			}
		},
		methods: {
			//跳转至项目全景
			goxmqj(){
				this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid  }})
			},
			// getZlfl_vue(){
			// 	let params = {
			// 		xmid: this.xmid,
			// 	};
			// 	getZlfl(params).then((res) => {
			// 		this.zlrow = res.data.row;
			// 	});
			// },
			goDetail_vue(index,row){
				let params={
					xmid:this.xmid,
					id:row.id
				}
				goDetail(params).then((res)=>{
					this.gridData = res.data.row;
				})
			},
			getZprySjy(){//获取设计院指派人员名单
				let params = {
					roleid : "369fd3cbf46a46809af4167a75f123b9",
					xmid:this.xmid,
					activityId:this.activityId
				};
				getZpry(params).then((res) => {
					this.data = res.data.zpry;
					this.radio = res.data.mrzpry;
					this.textarea = res.data.bz;
				});
			},
			downloadFile_vue(index,row,flag){
				let formData = new FormData();
				formData.append("path",row.fjlj);
				// let instance = axios.create({
				// 	baseURL:'http://localhost:8080',
				// 	headers:{
				// 		'Content-Type': 'application/json; application/octet-stream'
				// 	},
				// 	responseType: 'blob',
				//
				// });
				download_sjst(formData).then((res)=>{
					const content = res.data
					const blob = new Blob([content])
					const fileName = row.fjmc
					if ('download' in document.createElement('a')) { // 非IE下载
						const elink = document.createElement('a')
						elink.download = fileName
						elink.style.display = 'none'
						elink.href = URL.createObjectURL(blob)
						document.body.appendChild(elink)
						elink.click()
						URL.revokeObjectURL(elink.href) // 释放URL 对象
						document.body.removeChild(elink)
					} else { // IE10+下载
						navigator.msSaveBlob(blob, fileName)
					}
				})
				// instance.post('/sjstController/download',formData).then((res) => { // 处理返回的文件流
				   /* if(flag==1){
                        this.slide1=[];
                        let reader = new FileReader()
                        reader.onload = (e) => {
                            let item = {};
                            item.msrc = item.src = e.target.result;
                            // this.Imgsrc = e.target.result;
                            item.h=400;
                            item.w=600;
                            this.slide1.push(item);
                            this.$preview.openList(0, this.slide1);
                            //this.msgFlag = true;
                            //this.prop.src = this.Imgsrc
                        }
                        reader.readAsDataURL(res.data)
                    }else{*/

                    //}
				// })
			},

            handleClose(){},
            cancel(){
				this.tabUrl='/Backlog'
				this.$routerTab.close();
				this.$routerTab.refresh('/Backlog');
				this.$routerTab.refresh('/MyWork');
			},
            submit_vue(){
				this.$confirm('您正在递交工作结果，一旦递交确认后将不能打回或再次修改，你确定要递交吗？', '提示', {
					type: 'warning'
				}).then(() => {
					if(this.radio==''||this.radio==null){
						this.$message.error('请选择指派人员！');
						return;
					}
					let params={
						"xmid":this.xmid,
						"activityId":this.activityId,
						"sjzr":this.radio
					}
					this.loading=true;

					sjrwfpSubmit(params).then((res) => {
						this.loading=false;
						if(res.data==true){
							this.$message({
								message: '指派成功',
								type: 'success'
							});
							let param={
								"xmid":this.xmid,
								"activitiId":this.activityId,
								"direction":0
							}
							rwtxfsdx(param).then((res)=>{})
							this.tabUrl='/Backlog'
							this.$routerTab.close();
							this.$routerTab.refresh('/Backlog');
							this.$routerTab.refresh('/MyWork');
						}else {
							this.$message({
								message: '指派失败',
								type: 'error'
							});
						}
					})
				})
			}
		},

		mounted() {
			// this.getZlfl_vue();
			this.getZprySjy()
		},
		components: {
			'XMJBXX': XMJBXX,  //将别名demo 变成 组件 Demo
			'zlfljxz':zlfljxz,
			'hhldsx':hhldsx
		}

	}

</script>
