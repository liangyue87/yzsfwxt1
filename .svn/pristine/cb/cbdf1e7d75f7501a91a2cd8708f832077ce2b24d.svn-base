<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<el-row>
			<!--工具条-->
			<el-col :span="24" class="toolbar" >
				<el-form  :model="filters" >
					<el-form-item>
						<el-col :span="2" style="text-align: right">
							<span>项目/申请编号：</span>
						</el-col>
						<el-col :span="5">
							<el-input v-model="filters.xmbh"></el-input>
						</el-col>
						<el-col :span="2"style="text-align: right">
							<span>单位名称：</span>
						</el-col>
						<el-col :span="5">
							<el-input v-model="filters.dwmc"></el-input>
						</el-col>
						<el-col :span="2"style="text-align: right">
							<span>联系人：</span>
						</el-col>
						<el-col :span="5">
							<el-input v-model="filters.lxr"></el-input>
						</el-col>
					</el-form-item>
					<el-form-item>
						<el-col :span="2" style="text-align: right">
							<span>任务状态：</span>
						</el-col>
						<el-col :span="5">
							<el-select v-model="filters.xmjd"clearable  placeholder="请选择" style="width:100%">
								<el-option label="已发起勘查任务" value="0"></el-option>
								<el-option label="未发起勘查任务" value="1"></el-option>
							</el-select>
						</el-col>
						<el-col :span="2"style="text-align: right">
							<span>受理时间：</span>
						</el-col>
						<el-col :span="5">
							<el-date-picker type="datetime" placeholder="选择开始时间" v-model="filters.kssj" value-format="yyyy-MM-dd HH:mm:ss" style="width: 100%;">
							</el-date-picker>
						</el-col>
						<el-col class="line" :span="2" style="text-align: center">-</el-col>
						<el-col :span="5">
							<el-date-picker type="datetime" placeholder="选择结束时间" v-model="filters.jssj" value-format="yyyy-MM-dd HH:mm:ss" style="width: 100%;">
							</el-date-picker>
						</el-col>
						<el-col :span="3" style="text-align: center">
							<el-button type="primary" v-on:click="getFkPj">查询</el-button>
						</el-col>
					</el-form-item>
				</el-form>
			</el-col>
			<el-col :span="24" class="toolbar">
			<!--列表-->
			<el-table :data="row" border highlight-current-row v-loading="listLoading" style="width: 100%;">
				<el-table-column type="index" label="序号" width="100" align="center">
				</el-table-column>
				<el-table-column property="xmbh" label="项目/申请编号" width="150" align="center">
				</el-table-column>
				<el-table-column property="lxr" label="联系人" width="150" align="center">
				</el-table-column>
				<el-table-column property="lxrsjh" label="联系方式" width="150" align="center">
				</el-table-column>
				<el-table-column property="slrq" label="受理时间" width="180" align="center">
				</el-table-column>
				<el-table-column property="dwmc" label="单位名称" width="150" align="center">
				</el-table-column>
				<el-table-column property="xmdz" label="工程地址"  min-width="180" >
				</el-table-column>
				<el-table-column label="操作" width="250" >
					<template slot-scope="scope">
						<el-button size="small" type="primary" @click="toFkPj(scope.$index, scope.row)">勘查反馈评价</el-button>
					</template>
				</el-table-column>
			</el-table>
			</el-col>
			<el-col :span="24" class="toolbar" >
			<!--工具条-->
				<el-pagination layout="total, prev, pager, next" :page-size="this.size" :total="this.total" style="float:right;">
				</el-pagination>
			</el-col>
		</el-row>
	</section>
</template>

<script>
	import util from '../../../common/js/util'

	import { getKcfkPj } from '../../../api/xkylx';
	export default {
		data() {
			return {
				filters: {
					xmbh:'',
					dwmc:'',
					lxr:'',
					xmjd:'',
					kssj:'',
					jssj:''
				},
				row: [],
				total: 10,//表格数据总数量
				page: 1,//当前页码
				size: 10,//每页容量
				listLoading: false
			}
		},
		methods: {
			//获取勘查反馈评价任务列表
			getFkPj() {
				let end = new Date();
				let start = new Date();
				start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);//一个月前的时间
				this.filters.jssj = util.formatDate.format(end,'yyyy-MM-dd hh:mm:ss');
				this.filters.kssj = util.formatDate.format(start,'yyyy-MM-dd hh:mm:ss');
				this.filters.xmjd = '1';
				let params = Object.assign({ page: this.page, size: this .size }, this.filters);
				this.listLoading = true;
				getKcfkPj(params).then((res) => {
					//this.total = res.data.total;
					this.row = res.data.row;
					this.listLoading = false;
				});
			},
			//跳转至发起勘查任务界面
			toFkPj: function (index, row) {

				//setXmidToSession(params).then((res) =>{
					this.$router.push({path: '/Fkpj', query: {xmid : row.id}})
				//});
				//this.$router.push({name: 'fqxckc', params: {paicheNo: '123'}})

				/*this.editFormVisible = true;
				this.editForm = Object.assign({}, row);//编辑表单的赋值
				this.editForm.islocked = parseInt(row.islocked);//性别赋值*/
			}
		},
		mounted() {
			this.getFkPj();
		}
	}

</script>

<style scoped>

</style>
