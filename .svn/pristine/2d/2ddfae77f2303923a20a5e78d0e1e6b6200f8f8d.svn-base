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
						<span>项目名称 &nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-input size="small" v-model="YsForm.xmmc"></el-input>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>项目地址 &nbsp;</span>
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
						<el-input size="small" v-model="YsForm.azlx"></el-input>
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
						<span>验收情况 &nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-select v-model="YsForm.pdzt" placeholder="请选项目状态" style="width: 100%" size="small">
							<el-option label="全部" value=""></el-option>
							<el-option label="已验收" value="0"></el-option>
							<el-option label="未验收" value="1"></el-option>
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
			<el-table :data="gcHgpd" fit style="width: 100%" highlight-current-row height="460px" :stripe="true" :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
				<el-table-column fixed property="xmbh" label="项目编号" width="200"></el-table-column>
				<!--<el-table-column label="验收情况" width="90">
					<template slot-scope="scope">
						&lt;!&ndash;0是验收合格  1 是结办&ndash;&gt;
						<span v-if="scope.row.pdzt == '0'" style="color: green;">验收合格</span>
						<span v-else style="color: red">验收不合格</span>
					</template>
				</el-table-column>-->
				<el-table-column property="codename" label="安装类型" align="center" ></el-table-column>
				<el-table-column property="lxr" label="联系人" ></el-table-column>
				<el-table-column property="lxrsjh" label="联系人手机" ></el-table-column>
				<el-table-column property="xmmc" label="项目名称" ></el-table-column>
				<el-table-column property="dwmc" label="单位名称" ></el-table-column>
				<el-table-column property="lxsj" label="立项时间" ></el-table-column>
				<!--<el-table-column property="xmdz" label="项目地址"></el-table-column>-->
				<el-table-column fixed="right" label="操作" width="60">
					<template slot-scope="scope">
						<label title="出具合格评定表" @click="toFqlhkc(scope.row)">
							<i class="iconfont icon-tiaocha" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
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
	Vue.use(Tree);
	import util from '../../common/js/util';
	import Vue from 'vue';
	import { Tree } from 'element-ui';
	import { getSqgcHgpdList} from '../../api/Gcpd';
	import { findAzlx} from '../../api/Yswt';

	export default {
		data() {
			return {
				gcHgpd: [],//数据
				azlxList:[],
				YsForm: { //表单
					pdzt : '',//评定状态
					date1: '', //时间范围
					date2: '',
					lxr:'', //联系人
					xmdz:'', //项目地址
					azlx: [], //项目类型
					status:'', //项目状态
					xmbh: '' ,//项目编号
					xmmc:''
				},
				//show: false, //项目状态
				page: 1, //初始页
				size: 10, //  每页的数据
				sizes: [10, 15, 20, 30] ,//写死每页条数
				total:3
			}
		},
		//Enter键监听执行查询
		created() {
			let lett = this;
			document.onkeydown = function(e) {
				var key = window.event.keyCode;
				if (key == 13) {
					lett.getList();
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
				this.getList();
			},
			//当前页条数
			handleSizeChange(size){
				this.size = size;
				this.getList();
			},
			//查询
			getList(flag) {
				if(flag==1){
					this.page=1
				}
				let params = {
					pdzt:this.YsForm.pdzt, //项目状态
					xmbh:this.YsForm.xmbh,//项目编号
					xmmc:this.YsForm.xmmc,//项目名称
					lxr:this.YsForm.lxr,//联系人
					azlx:this.YsForm.azlx[1],//安装类型
					xmdz:this.YsForm.xmdz,//项目地址
					date1: this.YsForm.date1,//时间范围
					date2: this.YsForm.date2,
					page:this.page,
					size:this.size
				}
				getSqgcHgpdList(params).then(res => {
					if (res.data.total>0 && res.data.gcHgpd.length<=0){
						this.getList();
					}
					this.gcHgpd = res.data.gcHgpd;
					this.total = res.data.total;
				});
			},
			//跳转至发起勘查任务界面
			toFqlhkc(tow1){
				this.$router.push({
					path:'/Jgyshgzm',
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
