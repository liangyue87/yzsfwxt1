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
							<el-input v-model="filters.dwmc" @keyup.enter.native="findDone"></el-input>
						</el-col>
						<el-col :span="2" style="text-align: right">
							<span>任务编号&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-input v-model="filters.rwbh" @keyup.enter.native="findDone"></el-input>
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
							<el-button size="mini" type="primary" @click="findDone"><i class="iconfont icon-chaxun"></i>查询</el-button>
						</el-col>
					</el-form-item>
				</el-form>
			</el-col>
			<el-col :span="24" class="toolbar">
				<!--列表-->
				<el-table :data="row" border highlight-current-row v-loading="listLoading"
						  style="width: 100%;" height="400" :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
					<el-table-column type="index" label="序号" width="100" align="center">
					</el-table-column>
					<el-table-column property="RWBH" label="任务编号" width="180" align="center">
					</el-table-column>
					<el-table-column property="ACTIVITYNAME" label="环节名称" width="150" align="center">
					</el-table-column>
					<el-table-column property="QSZT" label="签收状态" width="120" align="center">
					</el-table-column>
					<el-table-column property="AZLX" label="安装类型" width="180" align="center">
					</el-table-column>
					<el-table-column property="XMMC" label="项目名称" width="200" align="center">
					</el-table-column>
					<el-table-column property="XMDZ" label="工程地址"  min-width="180" align="center">
					</el-table-column>
					<el-table-column property="FSSJ" label="发送时间"  width="180" align="center">
					</el-table-column>
					<el-table-column property="Y_STR" label="绿灯时限"  width="180" align="center">
					</el-table-column>
					<el-table-column property="R_STR" label="红灯时限"  width="180" align="center">
					</el-table-column>
					<el-table-column label="操作" width="100" align="center" fixed="right">
						<template slot-scope="scope">
							<label title="查看" @click="toShow(scope.$index, scope.row)">
								<i class="iconfont icon-hetong" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
						</template>
					</el-table-column>
				</el-table>
			</el-col>
			<el-col :span="24" class="toolbar" >
				<!--工具条-->
				<el-pagination background layout="total, prev, pager, next, sizes , jumper"
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

	import { findDone,getTypeCheckbox  } from '../../api/work';
	export default {
		data() {
			return {
				filters: {
					sqbh:'',
					dwmc:'',
					lxr:'',
					kssj:'',
					jssj:'',
					type:''
				},
				row: [],
				total: 10,//表格数据总数量
				page: 1,//当前页码
				size: 10,//每页容量
				listLoading: false,
				typeList :[]
			}
		},
		methods: {
			//获取勘查任务
			findDone(flag) {
				if(flag!=1){
					this.page=1
				}
				let JSONstr = sessionStorage.getItem("user");
				let JSON = eval("("+JSONstr+")");
				let params = Object.assign({ page: this.page, size: this .size, rwblr: JSON.id }, this.filters);
				this.listLoading = true;
				findDone(params).then((res) => {
					if(res.data.total>0&&res.data.list.length<=0){
						this.findDone();
					}
					this.total = res.data.total;
					this.row = res.data.list;
					this.listLoading = false;
				});
			},
			handleCurrentChange(val) {
				this.page = val;
				this.findDone(1);
			},
			handleSizeChange(val) {
				this.size = val;
				this.findDone();
			},
			//跳转至查看界面
			toShow: function (index, row) {
				let c_activityid = row.C_ACTIVITYID.substr(0,1);
				let xmid = row.ID;
				this.$router.push({path: '/Jsplumb', query: {xmid : xmid,type : c_activityid}})
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
			this.findDone();
		}
	}

</script>

<style scoped>

</style>
