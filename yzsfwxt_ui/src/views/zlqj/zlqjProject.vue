<script src="../../api/zlqj.js"></script>
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
							<span>单位名称：</span>
						</el-col>
						<el-col :span="5">
							<el-input clearable v-model="filters.dwmc" size="small " @keyup.enter.native="getXmlb_vue"></el-input>
						</el-col>
						<el-col :span="2"style="text-align: right">
							<span>联系人：</span>
						</el-col>
						<el-col :span="5">
							<el-input clearable v-model="filters.lxr" size="small " @keyup.enter.native="getXmlb_vue"></el-input>
						</el-col>
					</el-form-item>
					<el-form-item>
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
						<el-col :span="2"style="text-align: right">
							<span>项目名称：</span>
						</el-col>
						<el-col :span="5">
							<el-input clearable v-model="filters.xmmc" size="small " @keyup.enter.native="getXmlb_vue"></el-input>
						</el-col>
						<el-col :span="3" style="text-align: center">
							<el-button size="mini " type="primary" v-on:click="getXmlb_vue" ><i class="iconfont icon-chaxun"></i>查询</el-button>
						</el-col>
					</el-form-item>
				</el-form>
			</el-col>
			<el-col :span="24" class="toolbar" >
				<!--列表-->
				<el-table :data="row" :stripe="true" highlight-current-row v-loading="listLoading"  style="width: 100%;">
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
							<label title="查看"><i @click="handleEdit_vue(scope.$index, scope.row)" class="iconfont icon-yulan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
						</template>
					</el-table-column>
				</el-table>
			</el-col>
			<el-col :span="24" class="toolbar" >
				<!--工具条-->
				<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[10, 15, 30, 50,100]"
							   @current-change="handleCurrentChange" @size-change="handleSizeChange"
							   :page-size="this.size" style="float:right;" :total="this.total">
				</el-pagination>
			</el-col>
		</el-row>
	</section>
</template>

<script>
	import '../../assets/iconfont/iconfont.css'
	import util from '../../common/js/util'
	import { getProject} from '../../api/zlqj';
	export default {
		data() {
			return {
				filters: {
					xmbh:'',
					dwmc:'',
					lxr:'',
					kssj:'',
					jssj:'',
					xmmc:''
				},
				row: [],
				total: 10,//表格数据总数量
				page: 1,//当前页码
				size: 10,//每页容量
				listLoading: false,
			}
		},
		methods: {
			handleCurrentChange(val) {
				this.page = val;
				this.getXmlb_vue();
			},
			handleSizeChange(val) {
                this.size = val;
                this.getXmlb_vue();
            },
			//获取项目列表
			getXmlb_vue() {
				let param = {
					page: this.page,
					size: this.size,
				};
				let params = Object.assign(param,this.filters);
				this.listLoading = true;
				getProject(params).then((res) => {
					this.total = res.data.total;
					this.row = res.data.row;
					this.listLoading = false;
				});
			},
			handleEdit_vue (index, row) {
				this.$router.push({path: '/lookZl', query: {xmid:row.id}})
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
		margin: 10px 0px;
	}
	.el-table th{
		background-color: #f5f7fa;
	}
</style>
