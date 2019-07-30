<template>
	<section>
	
				
		<!--条件查询-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="YsForm">
				<el-form-item label="项目编号" sortable>
					<el-input v-model="YsForm.xmbh" v-on:input="getList" placeholder="输入项目编号"></el-input>
				</el-form-item>
					<el-form-item label="项目状态"  >
						<el-select v-model="YsForm.status" placeholder="请选项目状态" @change="getList">
							<el-option label="全部" value=""></el-option>
							<el-option label="待告知" value="0"></el-option>
							<el-option label="已告知" value="1"></el-option>
						</el-select>
					</el-form-item>
			
				<br />
				<el-form-item label=" 联系人　" >
					<el-input v-model="YsForm.lxr" v-on:input="getList" placeholder="输入联系人"></el-input>
				</el-form-item>
				<el-form-item label="项目地址" >
					<el-input v-model="YsForm.xmdz" v-on:input="getList" placeholder="输入项目地址"></el-input>
				</el-form-item>
				<el-form-item label="　安装类型">
					<el-input v-model="YsForm.azlx" v-on:input="getList" placeholder="输入安装类型"></el-input>
				</el-form-item>
			</el-form>
		</el-col>
<el-col :span="24" class="toolbar">
		<!-- 查询结果  -->
		<el-table :data="yswt" fit style="width: 100%" highlight-current-row>
			<el-table-column fixed property="xmbh" label="项目编号" sortable width="150"></el-table-column>
			<el-table-column label="项目状态" width="120">
				<template  slot-scope="scope">
					<!--0是待办  1 是结办-->
					<span v-if="scope.row.sfsh == '1' " style="color: #E64242;">待告知</span>
				</template>
			</el-table-column>
			<el-table-column property="azlx" label="安装类型" width="120"  align="center"></el-table-column>
			<el-table-column property="lxr" label="联系人" width="120"></el-table-column>
			<el-table-column property="lxrsjh" label="联系人手机" width="120"></el-table-column>
			<el-table-column property="xmmc" label="项目名称" width="120"></el-table-column>
			<el-table-column property="dwmc" label="单位名称" width="150"></el-table-column>
			<el-table-column property="lxsj" label="立项时间" width="180"></el-table-column>
			<el-table-column property="xmdz" label="项目地址" width="300"></el-table-column>
			<el-table-column fixed="right" label="操作" width="100">
					<template  slot-scope="scope">
						<!-- <el-button size="small" type="primary" @click.native="toFqlhkc">发起委托</el-button> -->
						
							
							<el-button v-if="scope.row.sfsh == '1' " size="small" type="primary" @click="toFqlhkc(scope.row)">告知用户</el-button>
						
					</template>
			</el-table-column>
			
		</el-table>
</el-col>
		<!--分页-->
			<el-col :span="24" class="toolbar">
			<el-pagination
				layout="sizes, prev, pager, next"
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
		import { getYswtList ,setXmidToSession} from '../../api/Yswt';

export default {	
	data() {
		return {
			yswt: [],//数据
			YsForm: { //表单
				//date1: '', //时间范围
				lxr:'', //联系人
				xmdz:'', //项目地址
				azlx:'', //项目类型
				status:'', //项目状态
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
		//查询
		getList() {
			//alert(this.YsForm.date1);
			let params = {
				yszt:this.YsForm.status, //项目状态
				xmbh:this.YsForm.xmbh,//项目编号
				lxr:this.YsForm.lxr,//联系人
				azlx:this.YsForm.azlx,
				//lxrsjh:this.YsForm.lxrsjh,//联系人手机号
				xmdz:this.YsForm.xmdz,//项目地址
				//date:this.YsForm.date1,//时间范围
				page:this.page,
				size:this.size,
				bs:3,
			}
			//alert()
			getYswtList(params).then(res => {
				this.yswt = res.data.ysldsh;
				this.total = res.data.total;
				console.log(this.yswt);
				//alert(res.data.yswt.status);
			});
		},
		
	//告知用户
	toFqlhkc(tow1){
	alert("告知用户方法");
	},
	test(){
		this.getList();
		console.log(this.data1);
		alert(this.data1)
	}
	},
	mounted() {
		this.getList();
	}

}
</script>
