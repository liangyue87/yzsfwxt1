<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<el-row>
			<!--工具条-->
			<el-col :span="24" class="toolbar" style="border: 1px solid #f5f7fa;">
				<el-form  :model="filters" style="margin-top: 10px" size="small">
					<el-form-item>
						<el-col :span="2" style="text-align: right">
							<span>项目名称&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-input v-model="filters.dwmc" @keyup.enter.native="findBacklog"></el-input>
						</el-col>
						<el-col :span="2" style="text-align: right">
							<span>任务编号&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-input v-model="filters.rwbh" @keyup.enter.native="findBacklog"></el-input>
						</el-col>
						<el-col :span="2" style="text-align: right">
							<span>任务类别&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-select v-model="filters.type" clearable  style="width:100%" placeholder="请选择">
								<el-option v-for="item in typeList"
										   :key="item.ID"
										   :label="item.MC"
										   :value="item.BM"></el-option>
							</el-select>
						</el-col>
					</el-form-item>
					<el-form-item>
						<el-col :span="2" style="text-align: right">
							<span>发起时间&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-date-picker type="date" placeholder="选择开始时间" value-format="yyyy-MM-dd" v-model="filters.kssj" style="width: 100%;">
							</el-date-picker>
						</el-col>
						<el-col class="line" :span="2" style="text-align: center">-</el-col>
						<el-col :span="5">
							<el-date-picker type="date" placeholder="选择结束时间" value-format="yyyy-MM-dd" v-model="filters.jssj" style="width: 100%;">
							</el-date-picker>
						</el-col>
						<el-col :span="2" style="text-align: right">
							&nbsp;
						</el-col>
						<el-col :span="3" style="text-align: left">
							<el-button size="mini" type="primary" @click="findBacklog"><i class="iconfont icon-chaxun"></i>查询</el-button>
						</el-col>
					</el-form-item>
				</el-form>
			</el-col>
			<el-col :span="24" class="toolbar">
			<!--列表-->
			<el-table :data="row" class="addThe_phone_inner" highlight-current-row v-loading="listLoading"
					  style="width: 100%;" height="400" :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
				<el-table-column type="index" label="状态" width="100" align="left">
					<template slot-scope="scope">
						<i v-if="scope.row.QSZT == '已签收' && scope.row.SFHUANGD == '0' && scope.row.SFHONGD== '0'" class="el-icon-s-opportunity lignt_green"></i>
						<i v-else-if="scope.row.QSZT == '已签收' && scope.row.SFHUANGD == '1' && scope.row.SFHONGD== '0'" class="el-icon-s-opportunity lignt_yellow"></i>
						<i v-else-if="scope.row.QSZT == '已签收' && scope.row.SFHONGD== '1'" class="el-icon-s-opportunity lignt_red"></i>
						<span v-else-if="scope.row.QSZT == '未签收' && scope.row.QSSFCS== '1'">
							<i  class="el-icon-s-opportunity lignt_red"></i>
							<i  class="iconfont icon-new1 lignt_red" style="font-size: 21px;position: relative;top: 5px;left: -7px;"></i>
						</span>
						<span v-else-if="scope.row.QSZT == '未签收' && scope.row.QSSFCS== '0'">
							<i  class="el-icon-s-opportunity lignt_green"></i>
							<i  class="iconfont icon-new1 lignt_green" style="font-size: 21px;position: relative;top: 5px;left: -7px;"></i>
						</span>
					</template>
				</el-table-column>
				<el-table-column property="RWBH" label="任务编号" width="180" align="center">
				</el-table-column>
				<el-table-column property="XMMC" label="项目名称" width="200" align="center">
				</el-table-column>
				<el-table-column property="AZLX" label="安装类型" width="200" align="center">
				</el-table-column>
				<el-table-column property="ACTIVITYNAME" label="环节名称" width="180" align="center">
				</el-table-column>
				<el-table-column property="XMDZ" label="工程地址"  min-width="200" align="center">
				</el-table-column>
				<el-table-column property="DWMC" label="建设单位"  min-width="200" align="center">
				</el-table-column>
				<el-table-column property="FSSJ" label="发送时间"  width="180" align="center">
				</el-table-column>
				<el-table-column property="QSZT" label="签收状态" width="100" align="center">
				</el-table-column>
				<el-table-column property="ZDJD_STR" label="最大进度"  width="150" align="center">
				</el-table-column>
				<el-table-column property="Y_STR" label="绿灯时限"  width="180" align="center">
				</el-table-column>
				<el-table-column property="R_STR" label="黄灯时限"  width="180" align="center">
				</el-table-column>
				<el-table-column label="操作" width="100" align="center" fixed="right">
					<template slot-scope="scope">
						<label title="办理" @click="toDo(scope.$index, scope.row)">
							<i class="iconfont icon-hetong" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
					</template>
				</el-table-column>
			</el-table>
			</el-col>
			<el-col :span="24" class="toolbar" >
			<!--工具条-->
				<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[10, 15, 30, 50,100]"
						@current-change="handleCurrentChange" @size-change="handleSizeChange"
						:current-page.sync = "this.page"
						:page-size="this.size" style="float:right;" :total="this.total">
				</el-pagination>

			</el-col>
		</el-row>
	</section>
</template>

<script>
	import util from '../../common/js/util'

	import { findBacklog,getTypeCheckbox } from '../../api/work';
	import { getYswtList } from '../../api/Yswt';
	import { getXqslById,toXqqs,Qsmessages } from '../../api/xqsl';
	import { getSgrwdById,queryxmxxByid } from '../../api/sgjg';
	import { getZhysdById,selectRwzbid } from '../../api/zhys';
	import { goSjrwfp } from '../../api/sjst';
	import { BwyjHandleEdit } from '../../api/bwyj';


	export default {
		data() {
			return {
				ldsh:[],
				yszp:[],
				ysbz:[],
				ysqs:[],
				filters: {
					rwbh:'',
					dwmc:'',
					kssj:'',
					jssj:'',
					type:''
				},
				row: [],
				total: 10,//表格数据总数量
				page: 1,//当前页码
				size: 15,//每页容量
				listLoading: false,
				typeList :[]
			}
		},
		methods: {
			//获取勘查任务
			findBacklog(flag) {
				if(flag!=1){
					this.page=1
				}
				let JSONstr = sessionStorage.getItem("user");
				let JSON = eval("("+JSONstr+")");
				let params = Object.assign({ page: this.page, size: this .size, rwblr: JSON.id }, this.filters);
				this.listLoading = true;
				findBacklog(params).then((res) => {
					if(res.data.total>0&&res.data.list.length<=0){
						this.findBacklog();
					}
					this.total = res.data.total;
					this.row = res.data.list;
					this.listLoading = false;
				});
			},
			handleCurrentChange(val) {
				this.page = val;
				this.findBacklog(1);
			},
			handleSizeChange(val) {
				this.size = val;
				this.page = 1;
				this.findBacklog();
			},
			//跳转至办理界面
			toDo: function (index, row) {
				let flag = row.C_ACTIVITYID;
				if(flag == '20021'){
					this.$router.push({path: '/Rwzpsjy', query: {xmid : row.ID,type : '1',activityId : flag}})
				}else if(flag == '20022'){
					this.$router.push({path: '/Rwzpsgdw', query: {xmid : row.ID,type : '2',activityId : flag}})
				}else if(flag == '20023'){
					this.$router.push({path: '/Rwzpqs', query: {xmid : row.ID,type : '3',activityId : flag}})
				}else if(flag == '20024'){
					this.$router.push({path: '/Rwzpys', query: {xmid : row.ID,type : '4',activityId : flag}})
				}else if(flag == '20031'){
					this.$router.push({path: '/Rwfksjy', query: {xmid : row.ID,type : '1',activityId : flag}})
				}else if(flag == '20032'){
					this.$router.push({path: '/Rwfksgdw', query: {xmid : row.ID,type : '2',activityId : flag}})
				}else if(flag == '20033'){
					this.$router.push({path: '/Rwfkqs', query: {xmid : row.ID,type : '3',activityId : flag}})
				}else if(flag == '20034'){
					this.$router.push({path: '/Rwfkys', query: {xmid : row.ID,type : '4',activityId : flag}})
				}else if(flag == '20006'){
					this.$router.push({path: '/Fkpj', query: {xmid : row.ID,activityId : flag, rwbh: row.RWBH}})
				}else if(flag == '20007'){
					this.$router.push({path: '/Fkjgqr', query: {xmid : row.ID,activityId : flag}})
				}else if(flag == '30005'){
					this.$router.push({path: '/Fqgwxxdcsj', query: {xmid : row.ID,bmbm_Qs:row.BMBM_QS}})
				} else if(flag == '30006'){
					this.$router.push({path: '/Gwxxfqlbsj', query: {xmid : row.ID,taskId: row.TASKID}})
				} else if(flag == '30001'){
					this.$router.push({path: '/fqwtsj', query: {xmid : row.ID}})
				} else if(flag == '30002'){
					let params = {
						xmid:row.ID,
						activityId:'30002'
					}
					goSjrwfp(params).then((res) =>{
						if(res.data==true){
							this.$router.push({path: '/sjrwfp', query: {xmid : row.ID,activityId:'30002'}})
						}
					})
				} else if(flag == '30003'){
					let params = {
						xmid:row.ID,
						activityId:'30003'
					}
					goSjrwfp(params).then((res) =>{
						if(res.data==true){
							this.$router.push({path: '/scsjcg', query: {xmid : row.ID,activityId:'30003',sjstrzwid:row.REFID,isxmtc:"0"}})
						}
					})
				} else if (flag == '40001'){
					let params = {
						xmbh: row.XMBH,//项目编号
						/*azlx: row.AZLX,*/
						xmdz: row.XMDZ,//项目地址
						page: '1',
						size: '99999',
						bs:0,
					}
					getYswtList(params).then(res => {

						this.yswt = res.data.yswt;
						for (let i=0;i<this.yswt.length;i++){
							if (row.ID == this.yswt[i].id){
								this.$router.push({path:'/YSfqwt', query:{ tow1:this.yswt[i],yszt:this.yswt[i].yszt,xmid:row.ID }})
							}
						}
					});
				}
				else if (flag == '40002'){
					this.$router.push({path: '/YSruzp', query: {xmid : row.ID}})
				}else if (flag == '40003'){
					let params = {
						xmid: row.ID,//项目id
						xmbh: row.XMBH,//项目编号
						/*azlx: row.AZLX,*/
						xmdz: row.XMDZ,//项目地址
						page: '1',
						size: '99999',
						bs:2,
					}
					getYswtList(params).then(res => {
						this.ysbz = res.data.ysbz;
						for (let i=0;i<this.ysbz.length;i++){
							if (row.ID == this.ysbz[i].id){
								this.$router.push({path: '/YSbzxq',query:{ tow1:this.ysbz[i],xmid:row.ID }})
							}
						}
					});
				}else if(flag == '40008'){
					let params = {
						xmbh: row.XMBH,//项目编号
						/*azlx: row.AZLX,*/
						xmdz: row.XMDZ,//项目地址
						page: '1',
						size: '99999',
						bs:3,
					}
					getYswtList(params).then(res => {
						this.ldsh = res.data.ysldsh;
						for (let i=0;i<this.ldsh.length;i++){
							if (row.ID == this.ldsh[i].id){
								this.$router.push({path:'/YSldsh', query:{ tow1:this.ldsh[i],xmid:row.ID  }})
							}
						}
					});
				}else if(flag == '40081'){
					let params = {
						xmbh: row.XMBH,//项目编号
						/*azlx: row.AZLX,*/
						xmdz: row.XMDZ,//项目地址
						page: '1',
						size: '99999',
						bs:4,
					}
					getYswtList(params).then(res => {
						this.ysqs = res.data.ysqs;
						for (let i=0;i<this.ysqs.length;i++){
							if (row.ID == this.ysqs[i].id){
								this.$router.push({path:'/Qsxq', query:{ tow1:this.ysqs[i],xmid:row.ID  }})
							}
						}
					});
				}else if(flag=='C0018'){
					this.$router.push({path: '/Bzyhjsslbsj', query: {xmid : row.ID,taskId: row.TASKID}})
				}else if(flag=='C0019'){
					this.$router.push({path: '/JssqCslbsj',  query: {xmid : row.ID,taskId: row.TASKID}})
				}else if(flag == '10004' || flag == '10005' || flag == '10006'){
					let params = {
						rwid:row.ID
					};
					getXqslById(params).then(res => {
						let rows = res.data.list[0];
						let param = {
							rwid:rows.ID,
							type:rows.TYPEID,
							activityId : flag,
							taskId:row.TASKID
						};
						this.$router.push({path: '/zpry', query: param})
					});
				}else if(flag == '10007' || flag == '10008' || flag == '10009'){
					let params = {
						rwid:row.ID
					};
					getXqslById(params).then(res => {
						let rows = res.data.list[0];
						let para = Object.assign({activityId : flag,rwid:row.ID,taskId:row.TASKID},rows);
						this.$router.push({path: '/qkxq', query: para});
					});
				}else if(flag == '40011'){
					// this.$router.push({path: '/YSZjqt', query: {xmid: row.ID}});
					this.$router.push({path: '/YSZjqtFk', query: {xmid: row.ID,refid:row.REFID,
							activityId : flag,taskId:row.TASKID}});
				}
				// else if(flag == '40012'){
				// 	this.$router.push({path: '/YSZjqtFk', query: {xmid: row.ID,refid:row.REFID}});
				// }
				else if(flag == '70001'){
					this.$router.push({path: '/editSgxd', query: {xmid:row.ID,type:'1',isEdit:'1',
							sgdid:row.REFID,activityId : flag,rwbh:row.RWBH,isTh:'1',taskId:row.TASKID }});
				}else if(flag == '70002'){
					this.$router.push({path: '/shSgxd', query: {xmid:row.ID,type:'1',isEdit:'2',
							sgdid:row.REFID,activityId : flag,taskId:row.TASKID }});
				}else if(flag == '70003'){
					this.$router.push({path: '/zpSgxd', query: {xmid:row.ID,type:'1',isEdit:'2',
							sgdid:row.REFID,activityId : flag,taskId:row.TASKID }});
				}else if(flag == '70004'){

				}else if(flag == '70005'){
					let params = {
						sgdid:row.REFID
					};
					getSgrwdById(params).then(res => {
						this.$router.push({path: '/xdSgxd', query: {xmid:row.ID,type:'1',isEdit:'2',
								sgdid:row.REFID,xmjl:res.data.list[0].XMGZRY,activityId : flag,taskId:row.TASKID }});
					});
				}else if(flag == '80001'){
					this.$router.push({path: '/Sqzhyslbsj',query: {xmid:row.ID}});
				}else if(flag == '80002'){
					let params = {
						xmid:row.ID,
					};
					selectRwzbid(params).then((res) => {
						this.$router.push({path: '/Xdzhysdlbsj',query: {xmid:row.ID,sqbh:res.data.sqbh ,sqdid:res.data.sqdid ,
								sqlx:res.data.sqlx,lxr:res.data.lxr,lxrsjh:res.data.lxrsjh,bmbmQs:res.data.bmbmQs}});
					});

					// let params = {
					// 	sqdid:row.REFID
					// };
					// getZhysdById(params).then(res => {
					// 	this.$router.push({path: '/Xdzhysdlbsj',query: {xmid:row.ID,sqdid:row.REFID,
					// 			sqlx:res.data.list[0].}});
					// });
				}else if(flag == '80003' || flag == '80004' || flag == '80005' || flag == '80006' || flag == '80007'
						|| flag == '80008'){
					this.$router.push({path: '/zpzhys',query: {xmid:row.ID,sqlx:row.sqlx,sqbh:row.sqbh,
							sqdid:row.REFID.split(",")[0],flag:flag,bmmc:row.REFID.split(",")[1],activityId : flag }});
				}else if(flag == '80009' || flag == '80010' || flag == '80011' || flag == '80012' || flag == '80013'
						|| flag == '80014'){
					this.$router.push({path: '/yszhys',query: {xmid:row.ID,sqlx:row.sqlx,sqbh:row.sqbh,
							sqdid:row.REFID.split(",")[0],bmmc:row.REFID.split(",")[1],activityId : flag }});
				}else if(flag == '80015'){
					this.$router.push({path: '/fkzhys',query: {xmid:row.ID,sqdid:row.REFID.split(",")[0],activityId : flag }});
				}else if(flag == '90001'){
					let params = {
						xmid:row.ID,
					};
					queryxmxxByid(params).then(res=>{
						this.$router.push({path: '/addSbyjd',
							query: {xmid:row.ID,zyjdbh:row.RWBH,yjzbid:row.REFID,isEdit:'1',xmmc:res.data.list[0].XMMC,isTh:'1',
								xmbh:res.data.list[0].XMBH,gclx:res.data.list[0].AZLX,
								jsdw:res.data.list[0].DWMC,activityId : flag,taskId:row.TASKID}});
					});
				}else if(flag == '90003'){
					this.$router.push({path: '/shSbyjd', query: {xmid:row.ID,rwbh:row.RWBH,
							refId:row.REFID,activityId : flag,taskId:row.TASKID }});
				}else if(flag == '90004'){
					this.$router.push({path: '/cbySbyjd', query: {xmid:row.ID,rwbh:row.RWBH,
							refId:row.REFID,activityId : flag,taskId:row.TASKID }});
				}else if(flag == '90010'){
					let params={
						xmid:row.ID,
						activityId:'90010',
						type:"bwyj"
					}
					BwyjHandleEdit(params).then((res)=>{
						if(res.data){
							this.$router.push({path: '/bwyjHandleShow', query: {xmid:row.ID,isReadonly:'1' }});
						}
					})

				}
			}
		},
		mounted() {
			let end = new Date();
			let start = new Date();
			start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);//一个月前的时间
			this.filters.jssj = util.formatDate.format(end,'yyyy-MM-dd');
			this.filters.kssj = util.formatDate.format(start,'yyyy-MM-dd');

			getTypeCheckbox().then((res) => {
				this.typeList = res.data.list;
			});

			this.findBacklog();
		}
	}

</script>

<style scoped>
	.lignt_green{
		color: #67c23a;
		font-size: large
	}
	.lignt_yellow{
		color: #fee300;
		font-size: large
	}
	.lignt_red{
		color: #f56c6c;
		font-size: large
	}
	.addThe_phone_inner::-webkit-scrollbar {/*滚动条整体样式*/
		width: 0px !important ; /*高宽分别对应横竖滚动条的尺寸*/
		height: 0px !important;
	}
	.addThe_phone_inner::-webkit-scrollbar-thumb {/*滚动条里面小方块*/
		border-radius: 10px !important;
		box-shadow: inset 0 0 5px rgba(0,0,0,0.2) !important;
		background: #c1c1c1 !important;
	}
	.addThe_phone_inner::-webkit-scrollbar-track {/*滚动条里面轨道*/
		box-shadow: inset 0 0 5px rgba(0,0,0,0.2) !important;
		border-radius: 10px !important;
		background: #EDEDED !important;
	}
</style>
