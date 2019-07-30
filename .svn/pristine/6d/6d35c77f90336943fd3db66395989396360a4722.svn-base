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
							<el-input clearable v-model="filters.xmbh" size="small" @keyup.enter.native="getBwyjlb_vue()"></el-input>
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
							<el-input clearable v-model="filters.dwmc" size="small" @keyup.enter.native="getBwyjlb_vue()"></el-input>
						</el-col>

					</el-form-item>
					<el-form-item>
						<el-col :span="2"style="text-align: right">
							<span>安装类型：</span>
						</el-col>
						<el-col :span="5">
							<el-cascader
									size="small"
									:options="azlxList"
									:show-all-levels="false"
									v-model="filters.azlx_vue"
									:clearable="true"
									style="width: 100%;"
							></el-cascader>
						</el-col>
						<el-col :span="2"style="text-align: right">
							<span>立项时间：</span>
						</el-col>
						<el-col :span="5">
							<el-date-picker :editable="false" size="small" type="date" placeholder="选择日期"format="yyyy-MM-dd"value-format="yyyy-MM-dd" v-model="filters.kssj" style="width: 100%;"></el-date-picker>
						</el-col>
						<el-col class="line" :span="2" style="text-align: center">-</el-col>
						<el-col :span="5">
							<el-date-picker :editable="false" size="small" type="date"value-format="yyyy-MM-dd" placeholder="选择日期" v-model="filters.jssj" format="yyyy-MM-dd" style="width: 100%;"></el-date-picker>
						</el-col>

					</el-form-item>
					<el-form-item>
						<el-col :span="2"style="text-align: right">
							<span>联系人：</span>
						</el-col>
						<el-col :span="5">
							<el-input clearable v-model="filters.lxr" size="small" @keyup.enter.native="getBwyjlb_vue()"></el-input>
						</el-col>
						<el-col :span="2"style="text-align: right">
							<span>是否发起：</span>
						</el-col>
						<el-col :span="5">
							<el-select v-model="filters.isfq"  style="width: 100%" size="small" @change="fqchange">
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
							<el-button size="mini" type="primary" v-on:click="getBwyjlb_vue" ><i class="iconfont icon-chaxun"></i>查询</el-button>
						</el-col>
					</el-form-item>
				</el-form>
			</el-col>
			<el-col :span="24" class="toolbar" >
				<!--列表-->
				<el-table :data="row" highlight-current-row v-loading="listLoading"  style="width: 100%;" :stripe="true" height="400px">
					<el-table-column type="index" label="序号"  align="center">
					</el-table-column>
					<el-table-column prop="xmbh" label="项目编号" width="200" align="center">
					</el-table-column>
					<el-table-column prop="xmmc" label="项目名称" width="150" align="center">
					</el-table-column>
					<el-table-column prop="dwmc" label="单位名称" align="center">
					</el-table-column>
					<el-table-column property="azlx" label="安装类型"   align="center"></el-table-column>
					<el-table-column prop="lxsj" label="立项时间" width="150" align="center">
					</el-table-column>
					<el-table-column prop="xmdz" label="项目地址" width="230" header-align="center">
					</el-table-column>
					<el-table-column prop="lxr" label="联系人"  align="center">
					</el-table-column>
					<el-table-column label="操作"  align="center">
						<template slot-scope="scope">
							<label v-if="!scope.row.iswcs" title="办理"><i @click="BwyjHandleEdit_vue(scope.$index, scope.row)" class="iconfont icon-banliliucheng-gongxiang" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
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
	import util from '../../../common/js/util'
	import { getBwyjlb,BwyjHandleEdit } from '../../../api/bwyj';
	import { findAzlx } from '../../../api/sbyj';
	export default {
		data() {
			return {
				filters: {
					isfq:"",
					iswc:"",
					xmbh:'',
					xmmc:'',
					dwmc:'',
					lxr:'',
					kssj:'',
					jssj:'',
					azlx_vue:[]
				},
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
				azlx:"",
				azlxList:[],
				row: [],
				total: 10,//表格数据总数量
				page: 1,//当前页码
				size: 10,//每页容量
				activityId:'90009',
				listLoading: false,
			}
		},
		methods: {
			fqchange(val){
				if(val=='2'){
					this.iswcOptions=[{
						codevalue: '',
						codename: '全部'
					}]
				}else{
					this.iswcOptions=[{
						codevalue: '',
						codename: '全部'
					},{
						codevalue: '2',
						codename: '未完成'
					},{
						codevalue: '1',
						codename: '已完成'
					}]
				}
			},
			handleCurrentChange(val) {
				this.page = val;
				this.getBwyjlb_vue(1);
			},
			handleSizeChange(val) {
				this.size = val;
				this.page = 1;
				this.getBwyjlb_vue();
			},
			//查询安装类型下拉框数据
			findAzlx_vue(){
				let params = {};
				findAzlx(params).then(res=>{
					console.log(res);
					this.azlxList = res.data.list;
				});
			},
			//获取表位移交项目列表
			getBwyjlb_vue(flag) {
				if(flag!=1){
					this.page=1
				}
				let param = {
					page: this.page,
					size: this.size,
					activityId:this.activityId,
					azlx:this.filters.azlx_vue[1]
				};

				let params = Object.assign(param,this.filters);
				this.listLoading = true;
				//NProgress.start();
				getBwyjlb(params).then((res) => {
					if(res.data.total>0&&res.data.row.length<=0){
						this.getBwyjlb_vue();
					}
					this.total = res.data.total;
					this.row = res.data.row;
					this.listLoading = false;
				});
			},
			//跳转至表位移交编辑界面
			BwyjHandleEdit_vue (index, row) {
				this.$router.push({path: '/bwyjHandle', query: {xmid:row.id,isReadonly:"0"}})
				// let params={
				// 	xmid:row.id,
				// 	activityId:this.activityId,
				// 	type:"bwyj"
				// }
				// BwyjHandleEdit(params).then((res)=>{
				// 	if(res.data){
				// 		this.$router.push({path: '/bwyjHandle', query: {xmid:row.id}})
						//this.$router.push({path: '/bwyjHandleShow', query: {xmid:row.id,iscontinue:row.iscontinue,isReadonly:"1"}})
				// 	}
				// })

			}
		},
		mounted() {
			let end = new Date();
			let start = new Date();
			start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);//一个月前的时间
			this.filters.jssj = util.formatDate.format(end,'yyyy-MM-dd');
			this.filters.kssj = util.formatDate.format(start,'yyyy-MM-dd');
			this.getBwyjlb_vue();
			this.findAzlx_vue();
		},

	}

</script>
