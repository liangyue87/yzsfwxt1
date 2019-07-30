<template>
	<section>
		<el-row>
			<!--工具条-->
			<el-col :span="24" class="toolbar" style="border: #f5f7fa solid 1px">
				<el-form  :model="filters" >
					<el-form-item>
						<el-col :span="2" style="text-align: right">
							<span>项目编号：</span>
						</el-col>
						<el-col :span="5">
							<el-input clearable v-model="filters.xmbh" size="small " @keyup.enter.native="getXmlb_vue"></el-input>
						</el-col>
						<el-col :span="2"style="text-align: right">
							<span>项目名称：</span>
						</el-col>
						<el-col :span="5">
							<el-input clearable v-model="filters.xmmc" size="small " @keyup.enter.native="getXmlb_vue"></el-input>
						</el-col>
						<el-col :span="2"style="text-align: right">
							<span>单位名称：</span>
						</el-col>
						<el-col :span="5">
							<el-input clearable v-model="filters.dwmc" size="small " @keyup.enter.native="getXmlb_vue"></el-input>
						</el-col>

					</el-form-item>
					<el-form-item>
						<el-col :span="2"style="text-align: right">
							<span>联系人：</span>
						</el-col>
						<el-col :span="5">
							<el-input clearable v-model="filters.lxr" size="small " @keyup.enter.native="getXmlb_vue"></el-input>
						</el-col>
						<el-col :span="2"style="text-align: right">
							<span>立项时间：</span>
						</el-col>
						<el-col :span="5">
							<el-date-picker :editable="false" size="small " value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期" v-model="filters.kssj" style="width: 100%;"></el-date-picker>
						</el-col>
						<el-col class="line" :span="2" style="text-align: center">-</el-col>
						<el-col :span="5">
							<el-date-picker :editable="false" size="small " value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期" v-model="filters.jssj" style="width: 100%;"></el-date-picker>
						</el-col>


					</el-form-item>
					<el-form-item>
						<el-col :span="2"style="text-align: right">
							<span>是否发起：</span>
						</el-col>
						<el-col :span="5">
							<el-select v-model="filters.isfq"  style="width: 100%" size="small" >
								<el-option
										v-for="item in isfqOptions"
										:key="item.codevalue"
										:label="item.codename"
										:value="item.codevalue">
								</el-option>
							</el-select>
						</el-col>
						<el-col :span="2"style="text-align: right">
							<span>是否完成：</span>
						</el-col>
						<el-col :span="5">
							<el-select v-model="filters.iswc"  style="width: 100%" size="small">
								<el-option
										v-for="item in iswcOptions"
										:key="item.codevalue"
										:label="item.codename"
										:value="item.codevalue">
								</el-option>
							</el-select>
						</el-col>
						<el-col :span="3" style="text-align: center">
							<el-button size="mini" type="primary" v-on:click="getXmlb_vue" ><i class="iconfont icon-chaxun"></i>查询</el-button>
						</el-col>
					</el-form-item>
				</el-form>
			</el-col>
			<el-col :span="24" class="toolbar" >
				<!--列表-->
				<el-table :data="row" :stripe="true" highlight-current-row v-loading="listLoading"  style="width: 100%;" height="400px">
					<el-table-column type="index" label="序号"  align="center">
					</el-table-column>
					<el-table-column prop="xmbh" label="项目编号" width="200" align="center">
					</el-table-column>
					<el-table-column prop="xmmc" label="项目名称"  header-align="center">
					</el-table-column>
					<el-table-column prop="dwmc" label="单位名称"  align="center">
					</el-table-column>
					<el-table-column prop="xmdz" label="项目地址"  header-align="center">
					</el-table-column>
					<el-table-column prop="lxr" label="联系人"  align="center">
					</el-table-column>
					<el-table-column prop="lxsj" label="立项时间"  align="center">
					</el-table-column>
					<el-table-column label="操作" align="center">
						<template slot-scope="scope">

							<label v-if="!scope.row.iswcs" title="委托设计"><i @click="handleEdit_vue(scope.$index, scope.row)" class="iconfont icon-weituoshejiguanli" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
							<!--<el-button size="small" type="primary" @click="handleEdit_vue(scope.$index, scope.row)"><i class="iconfont icon-weituoshejiguanli"></i>委托设计</el-button>-->
						</template>
					</el-table-column>
				</el-table>
			</el-col>
			<el-col :span="24" class="toolbar" >
				<!--工具条-->
				<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[10, 15, 30, 50,100]"
							   @current-change="handleCurrentChange" @size-change="handleSizeChange" :current-page.sync="this.page"
							   :page-size="this.size" style="float:right;" :total="this.total">
				</el-pagination>
			</el-col>
		</el-row>
	</section>
</template>

<script>
	import '../../assets/iconfont/iconfont.css'
	import util from '../../common/js/util'
	import { getXmlb,handleEdit } from '../../api/sjst';
	export default {
		data() {
			return {
				filters: {
					isfq:"2",
					iswc:"",
					xmbh:'',
					xmmc:'',
					dwmc:'',
					lxr:'',
					kssj:'',
					jssj:''
				},
				row: [],
				isfqOptions:[{
					codevalue: '',
					codename: '全部'
				},{
					codevalue: '2',
					codename: '未发起'
				},{
					codevalue: '1',
					codename: '已发起'
				}],
				iswcOptions:[{
					codevalue: '',
					codename: '全部'
				},{
					codevalue: '2',
					codename: '未完成'
				},{
					codevalue: '1',
					codename: '已完成'
				}],
				total: 10,//表格数据总数量
				page: 1,//当前页码
				size: 10,//每页容量
				activityId:'30001',
				listLoading: false,
			}
		},
		methods: {
			handleCurrentChange(val) {
				this.page = val;
				this.getXmlb_vue(1);
			},
			handleSizeChange(val) {
                this.size = val;
				this.page = 1;
                this.getXmlb_vue();
            },
			//获取项目列表
			getXmlb_vue(flag) {
				if(flag!=1){
					this.page=1
				}
				let param = {
					page: this.page,
					size: this.size,
					activityId:this.activityId
				};
				let params = Object.assign(param,this.filters);
				this.listLoading = true;
				//NProgress.start();
				getXmlb(params).then((res) => {
					if(res.data.total>0&&res.data.row.length<=0){
						this.getXmlb_vue();
					}
					this.total = res.data.total;
					this.row = res.data.row;
					this.listLoading = false;
				});
			},
			//跳转至发起委托设计界面
			handleEdit_vue (index, row) {
				this.$router.push({path: '/fqwtsj', query: {xmid:row.id}})
				// let params = {
				// 	xmid:row.id,
				// 	activityId:this.activityId,
				// 	type:"sjst"
				// }
				// handleEdit(params).then((res) =>{
				// 	if(res.data==true){
				// 		this.$router.push({path: '/fqwtsj', query: {xmid:row.id}})
					// }
				// })

				//setXmidToSession(params).then((res) =>{
					//this.$router.push({path: '/fqwtsj', query: {xmid:row.id}});
              //  this.$router.push({path: '/fqgwxxdc'});
				//});
			}
		},
		mounted() {
			let end = new Date();
			let start = new Date();
			start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);//一个月前的时间
			this.filters.jssj = util.formatDate.format(end,'yyyy-MM-dd');
			this.filters.kssj = util.formatDate.format(start,'yyyy-MM-dd');
			this.getXmlb_vue();
		}
	}

</script>
<style>
	.toolbar {
		background: #ffffff;
		 /*padding: 10px;*/
		margin: 10px 0px;
	}
	.el-table th{
		background-color: #f5f7fa;
	}
</style>
