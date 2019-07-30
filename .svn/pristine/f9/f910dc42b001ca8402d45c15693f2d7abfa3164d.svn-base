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
					<!--<el-col :span="2" style="text-align: right">
						<span>项目状态 &nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-select v-model="YsForm.sfsh" placeholder="请选项目状态" style="width: 100%">
							<el-option label="默认" value=""></el-option>
							<el-option label="待审核" value="0"></el-option>
							<el-option label="已审核" value="1"></el-option>
						</el-select>
					</el-col>-->
					<el-col :span="5" style="text-align: center;margin-left: 10px">
						<el-button size="small" type="primary" icon="el-icon-search" v-on:click="getList">查询</el-button>
					</el-col>
				</el-form-item>
			</el-form>
		</el-col>
<el-col :span="24" class="toolbar">
		<!-- 查询结果  -->
		<el-table :data="ysldsh" fit style="width: 100%" highlight-current-row height="400px" stripe="true" :row-style="rowClass" :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
			<el-table-column fixed property="xmbh" label="项目编号" sortable width="150"></el-table-column>
			<!--<el-table-column label="项目状态" width="120">
				<template slot-scope="scope">
					&lt;!&ndash;0是未审核  1 是已审核&ndash;&gt;
					<span v-if="scope.row.sfsh == '0' " style="color: #E64242;">未审核</span>
					<span v-else style="color: #11B95C;">已审核</span>
				</template>
			</el-table-column>-->
			<el-table-column property="azlx" label="安装类型" width="80"  align="center"></el-table-column>
			<el-table-column property="lxr" label="联系人" width="70"></el-table-column>
			<el-table-column property="lxrsjh" label="联系人手机" width="120"></el-table-column>
			<el-table-column property="xmmc" label="项目名称" width="120"></el-table-column>
			<el-table-column property="dwmc" label="单位名称" width="150"></el-table-column>
			<el-table-column property="lxsj" label="立项时间" width="180"></el-table-column>
			<el-table-column property="xmdz" label="工程地址" width="200"></el-table-column>
			<el-table-column fixed="right" label="操作" width="100">
					<template slot-scope="scope">
						<!-- <el-button size="small" type="primary" @click.native="toFqlhkc">发起委托</el-button> -->
						
							
							<!--<el-button v-if="scope.row.sfsh == '0' " size="small" type="primary" @click="toFqlhkc(scope.row)">详细信息</el-button>-->
							<el-button size="small" type="primary" @click="toFqlhkc(scope.row)">详细信息</el-button>
							<!-- <el-button v-else size="small"  @click="tochakan(scope.row)">查看状态</el-button> -->
							<!--<el-popover
								v-else
								placement="right"
								width="500"
								trigger="click">
								<el-steps :active="1">
									<el-step title="发起预算" description="xxx项目经理发起预算"></el-step>
									<el-step title="预算分派" description="xxx预算科分派"></el-step>
									<el-step title="预算编制" description="xxx预算专员"></el-step>
									<el-step title="预算完成" description="xxx预算科分派"></el-step>
									<el-step title="领导审批" description="xxx预算科长"></el-step>
									<el-step title="告知用户" description="xxx项目经理接收"></el-step>
								</el-steps>
								<el-button size="small"  slot="reference">查看状态</el-button>
							</el-popover>-->

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
		import { getYswtList ,findAzlx} from '../../api/Yswt';

export default {	
	data() {
		return {
			ysldsh: [],//数据
			azlxList:[],
			YsForm: { //表单
				lxr:'', //联系人
				xmdz:'', //项目地址
				azlx:'', //项目类型
				sfsh:'', //项目状态
				xmbh: '' ,//项目编号
			},
			//show: false, //项目状态
			page: 1, //初始页
			size: 10, //  每页的数据
			sizes: [10, 15, 20, 30] ,//写死每页条数
			total:3
		}
	},
	methods: {
		//分页
		handleCurrentChange(currentPage) {
			this.page = currentPage;
			this.getList();
		},
		//当前页条数
		handleSizeChange(size){
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
			//alert(this.YsForm.date1);
			let params = {
				yszt:this.YsForm.status, //项目状态
				xmbh:this.YsForm.xmbh,//项目编号
				lxr:this.YsForm.lxr,//联系人
				azlx:this.YsForm.azlx[1],
				//lxrsjh:this.YsForm.lxrsjh,//联系人手机号
				xmdz:this.YsForm.xmdz,//项目地址
				//date:this.YsForm.date1,//时间范围
				page:this.page,
				size:this.size,
				bs:3,
			}
			getYswtList(params).then(res => {
				this.ysldsh = res.data.ysldsh;
				this.total = res.data.total;
			});
		},
		
	//跳转至发起勘查任务界面
	toFqlhkc(tow1){
		this.$router.push({
			path:'/YSldsh',
			query:{
				tow1:tow1
			},
		})
	},
	test(){
		this.getList();
	}
	},
	mounted() {
		this.getList();
		this.findAzlx_vue();
	}

}
</script>
