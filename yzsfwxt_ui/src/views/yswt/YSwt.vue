<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="border: #f5f7fa solid 1px" >
			<el-form  :model="YsForm" style="margin-top: 10px">
				<el-form-item>
					<el-col :span="2" style="text-align: right">
						<span>项目编号 &nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-input size="small" v-model="YsForm.xmbh"></el-input>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>项目名称 &nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-input size="small" v-model="YsForm.xmmc"></el-input>
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
						<span>安装类型 &nbsp;</span>
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
						<span>安装类型 &nbsp;</span>
					</el-col>
					<el-col :span="5">
						&lt;!&ndash;<el-cascader
								:options="azlxList"
								:show-all-levels="false"
								v-model="azlx"
								:clearable="true"
								style="width: 255px;"
						></el-cascader>&ndash;&gt;
						<el-input v-model="YsForm.azlx"></el-input>
					</el-col>-->
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
				</el-form-item>
				<el-form-item>
					<el-col :span="2" style="text-align: right">
						<span>报建联系人 &nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-input size="small" v-model="YsForm.lxr"></el-input>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>状态 &nbsp;</span>
					</el-col>
					<el-col :span="5">
						<!--<el-input size="small" v-model="YsForm.bjzt"></el-input>-->
						<el-select v-model="YsForm.bjzt" placeholder="请选择" size="small" style="width: 100%">
							<el-option
									v-for="item in options"
									:key="item.value"
									:label="item.label"
									:value="item.value">
							</el-option>
						</el-select>
					</el-col>
					<el-col :span="5" style="text-align: center;margin-left: 10px">
						<el-button size="mini" type="primary" v-on:click="getList(1)"><i class="iconfont icon-chaxun"></i>查询</el-button>
					</el-col>
				</el-form-item>
			</el-form>
		</el-col>
		<el-col :span="24" class="toolbar">
			<!-- 查询结果  -->
			<el-table :data="yswt" :stripe="true" style="width: 100%;" height="400px" :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
				<el-table-column fixed prop="xmbh" label="项目编号"></el-table-column>
				<!--<el-table-column label="项目状态" width="120">
					<template slot-scope="scope">
						&lt;!&ndash;0是待办  1 是结办&ndash;&gt;
						<span v-if="scope.row.yszt == '0'" style="color: #E64242;">待办</span>
						<span v-else style="color: red">驳回...</span>
					</template>
				</el-table-column>-->
				<el-table-column prop="codename" label="安装类型" align="center" width="120"></el-table-column>
				<el-table-column prop="lxr" label="联系人" width="80"></el-table-column>
				<el-table-column prop="lxrsjh" label="联系人手机" width="120"></el-table-column>
				<el-table-column prop="xmmc" label="项目名称" ></el-table-column>
				<el-table-column prop="dwmc" label="单位名称" ></el-table-column>
				<el-table-column prop="lxsj" label="立项时间" ></el-table-column>
				<!--<el-table-column property="xmdz" label="工程地址" ></el-table-column>
				<el-table-column property="htyy" label="备注" ></el-table-column>-->
				<el-table-column label="操作" width="60" v-if="cz">
					<template slot-scope="scope">
						<label title="委托预算" v-if="scope.row.xmmc != null ">
							<i class="iconfont icon-yusuan" @click="toFqlhkc(scope.row)"  style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
						</label>

					</template>
				</el-table-column>

			</el-table>
		</el-col>
		<!--分页-->
		<el-col :span="24" class="toolbar">
			<el-pagination
					layout="total, prev, pager, next, sizes , jumper"
					:current-page.sync = "this.page"
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
	/*import { getSbyjList,findAzlx } from '../../api/sbyj';*/

	export default {
		data() {
			return {
				cz:true,
				options: [{
					value: '0',
					label: '未发起'
				}, {
					value: '1',
					label: '已发起'
				}],
				yswt: [],//数据
				azlxList:[],
				YsForm: { //表单
					date1: '', //时间范围
					date2:'',
					lxr:'', //联系人
					xmdz:'', //项目地址
					azlx: [], //项目类型
					status:'', //项目状态
					xmbh: '' ,//项目编号
					bjzt:'0',//办结状态
				},
				//show: false, //项目状态
				page: 1, //初始页
				size: 10, //  每页的数据
				sizes: [10, 15, 30, 50,100] ,//写死每页条数
				total:3
			}
		},
		//Enter键监听执行查询
		created() {
			let lett = this;
			document.onkeydown = function(e) {
				var key = window.event.keyCode;
				if (key == 13) {
					lett.getList(1);
				}
			}
		},
		methods: {
			//查询安装类型下拉框数据
			findAzlx_vue(){
				let params = {};
				findAzlx(params).then(res=>{
					this.azlxList = res.data.list;
				});
			},
			//分页
			handleCurrentChange(currentPage) {
				this.page = currentPage;
				this.getList(0);
			},
			//当前页条数
			handleSizeChange(size){
				this.size = size;
				this.getList(0);
			},
			//查询
			getList(flag) {
				if(flag==1){
					this.page=1
				}
				let params = {
					yszt:this.YsForm.status, //项目状态
					xmbh:this.YsForm.xmbh,//项目编号
					lxr:this.YsForm.lxr,//联系人
					azlx:this.YsForm.azlx[1],//安装类型
					//lxrsjh:this.YsForm.lxrsjh,//联系人手机号
					xmdz:this.YsForm.xmdz,//项目地址
					xmmc:this.YsForm.xmmc,//项目名称
					date1: this.YsForm.date1,
					date2: this.YsForm.date2,
					bjzt : this.YsForm.bjzt,//办结状态
					page:this.page,
					size:this.size,
					bs:0,
				}
				getYswtList(params).then(res => {
					if (res.data.total>0 && res.data.yswt.length<=0){
						this.getList(1);
					}
					this.yswt = res.data.yswt;
					this.total = res.data.total;
				});
				if (this.YsForm.bjzt == '1'){
					this.cz = false;
				}else if (this.YsForm.bjzt == '0'){
					this.cz = true;
				}

			},
			rowClass:function (row,index) {
				return {"height":"6.0vh"}
			},
			//跳转至发起勘查任务界面
			toFqlhkc(tow1){
				this.$router.push({path:'/YSfqwt', query:{tow1:tow1 , yszt:tow1.yszt,xmid:tow1.id},})
			}
		},
		mounted() {
			this.getList(1);
			this.findAzlx_vue();
		}

	}
</script>
