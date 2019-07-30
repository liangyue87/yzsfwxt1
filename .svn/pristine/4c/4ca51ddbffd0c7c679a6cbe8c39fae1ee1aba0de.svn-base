<template>
	<section>


		<!--工具条-->
		<el-col :span="24" class="toolbar" style="border: #f5f7fa solid 1px">
			<el-form  :model="YsForm" style="margin-top: 10px">
				<el-form-item>
					<el-col :span="2" style="text-align: right">
						<span>项目编号 &nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-input size="small" v-model="YsForm.xmbh"></el-input>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>报建联系人 &nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-input size="small" v-model="YsForm.lxr"></el-input>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>工程地址 &nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-input size="small" v-model="YsForm.xmdz"></el-input>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="2" style="text-align: right">
						<span>安装类型：</span>
					</el-col>
					<el-col :span="5">
						<el-cascader
								:options="azlxList"
								:show-all-levels="false"
								v-model="YsForm.azlx"
								:clearable="true"
								style="width: 100%"
								size="small"
								placeholder="全部"
						></el-cascader>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>立项时间 &nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-date-picker
								size="small"
								v-model="YsForm.date1"
								type="date"
								placeholder="选择日期" style="width: 100%;">
						</el-date-picker>
					</el-col>
					<el-col class="line" :span="2" style="text-align: center">-</el-col>
					<el-col :span="5">
						<el-date-picker
								size="small"
								v-model="YsForm.date2"
								type="date"
								placeholder="选择日期" style="width: 100%;">
						</el-date-picker>
					</el-col>
					<el-col :span="30" style="text-align: center;margin-left: 10px">
						<el-button size="small" type="primary" icon="el-icon-search" v-on:click="getList">查询</el-button>
					</el-col>
				</el-form-item>
			</el-form>
		</el-col>
		<el-col :span="24" class="toolbar">
			<!-- 查询结果  -->

			<el-table :data="ysbz" fit style="width: 100%" highlight-current-row height="400px" stripe="true" :row-style="rowClass" :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
				<el-table-column
					fixed
					property="xmbh"
					label="项目编号"
					sortable
					width="150"
				></el-table-column>
				<!--<el-table-column label="项目状态" width="120" fixed sortable>
					<template  slot-scope="scope">
						&lt;!&ndash;0是待办  1 是结办&ndash;&gt;
						<span v-if="scope.row.yszt == 0" style="color: red;">待预算</span>
						<span v-if="scope.row.yszt == 1" style="color: #B94A48;">预算中..</span>
						<span v-if="scope.row.yszt == 2" style="color: coral;">变更中..</span>
						<span v-if="scope.row.yszt == 3" style="color: #009900;">完成</span>
						<span v-if="scope.row.yszt == 4" style="color: #646464;">作废</span>
						
					</template>
						
				</el-table-column>-->
				<el-table-column
					property="azlx"
					label="安装类型"
					width="80"
					align="center"
				></el-table-column>
				<el-table-column property="lxr" label="联系人" width="70"></el-table-column>
				<el-table-column property="lxrsjh" label="联系人手机" width="120"></el-table-column>
				<el-table-column property="xmmc" label="项目名称" width="120"></el-table-column>
				<el-table-column property="lxsj" label="立项时间" width="180"></el-table-column>
				<el-table-column property="xmdz" label="工程地址" width="200"></el-table-column>
				<el-table-column property="dwmc" label="报建单位" width="150"></el-table-column>
				<el-table-column fixed="right" label="操作" width="100">
					<template  slot-scope="scope">
						<!-- <el-button size="small" type="primary" @click.native="toFqlhkc">发起委托</el-button> v-if="scope.row.yszt == 0 " -->

						<el-button  size="small" type="primary" @click="toFqlhkc(scope.row)">
							预算编制
						</el-button>
						<!--<el-button v-else   size="small" style="width: 80px;" @click="toFqlhkc(scope.row)">
							查看
						</el-button>-->
						
						<!-- <el-button size="small" type="info" @click="toFeturn(scope.row)">退回</el-button> -->

						<!-- <el-button v-else size="small"  @click="tochakan(scope.row)">查看状态</el-button> -->
					</template>
				</el-table-column>
			</el-table>
		</el-col>
		<!--分页-->
		<el-col :span="24" class="toolbar">
			<el-pagination
				layout="total, prev, pager, next, sizes , jumper"
				@current-change="handleCurrentChange"
				@size-change="handleSizeChange"
				:page-size="this.size"
				:total="this.total"
				:page-sizes="this.sizes"
				style="float:right;"
			></el-pagination>
		</el-col>
	</section>
</template>

<script>
import util from '../../common/js/util';
import Vue from 'vue';
import { Tree } from 'element-ui';
//Vue.component(Tree.name, Tree);
Vue.use(Tree);
//import VueResource from 'vue-resource';
//Vue.use(VueResource)
//import NProgress from 'nprogress'
import { getYswtList, findAzlx, ToBackward } from '../../api/Yswt';

export default {
	data() {
		return {
			ysbz: [], //数据
			azlxList:[],
			YsForm: {
				//表单
				date1: '', //时间范围
				date2:'',
				lxr : '', //联系人
				xmdz: '', //项目地址
				azlx: '', //项目类型
				yszt: '',
				xmbh: '' //项目编号
			},
			//show: false, //项目状态
			page: 1, //初始页
			size: 10, //  每页的数据
			sizes: [10, 15, 20, 30], //写死每页条数
			total: 3
		};
	},
	methods: {
		//分页
		handleCurrentChange(currentPage) {
			this.page = currentPage;
			this.getList();
		},
		//当前页条数
		handleSizeChange(size) {
			this.size = size;
			this.getList();
		},
		//查询安装类型下拉框数据
		findAzlx_vue(){
			let params = {};
			findAzlx(params).then(res=>{
				this.azlxList = res.data.list;
			});
		},
		//查询
		getList() {
			let params = {
				xmbh    : this.YsForm.xmbh, //项目编号
				lxr     : this.YsForm.lxr, //联系人
				azlx    : this.YsForm.azlx[1],
				yszt    : this.YsForm.yszt,
				//lxrsjh: this.YsForm.lxrsjh,//联系人手机号
				xmdz    : this.YsForm.xmdz, //项目地址
				date1  : this.YsForm.date1,//时间范围
				date2 : this.YsForm.date2,
				page    : this.page,
				size    : this.size,
				bs      : 2,
			}
			getYswtList(params).then(res => {
				this.ysbz  = res.data.ysbz;
			});
		},

		//预算编制40003
		toFqlhkc(tow1) {
			this.$router.push({
				path: '/YSbzxq',
				query: {
					tow1: tow1
				}
			});
		},

	},
	mounted() {
		this.getList();
		this.findAzlx_vue();
	}
};
</script>
