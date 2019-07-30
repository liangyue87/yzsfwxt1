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
							<span>项目状态：</span>
						</el-col>
						<el-col :span="5">
							<el-select v-model="filters.xmzt" style="width: 100%" size="small">
								<el-option
										v-for="item in xmztOptions"
										:key="item.codevalue"
										:label="item.codename"
										:value="item.codevalue">
								</el-option>
							</el-select>
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
							<el-date-picker :editable="false" size="small " value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期" v-model="filters.kssj" style="width: 100%;"></el-date-picker>
						</el-col>
						<el-col class="line" :span="2" style="text-align: center">-</el-col>
						<el-col :span="5">
							<el-date-picker :editable="false" size="small " value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期" v-model="filters.jssj" style="width: 100%;"></el-date-picker>
						</el-col>
						<el-col :span="3" style="text-align: center">
							<el-button size="mini " type="primary" v-on:click="getXmlb_vue" ><i class="iconfont icon-chaxun"></i>查询</el-button>
						</el-col>
					</el-form-item>
				</el-form>
			</el-col>
			<el-col :span="24" class="toolbar" >
				<!--列表-->
				<el-table :data="row" :stripe="true" highlight-current-row v-loading="listLoading"  style="width: 100%;" height="400px">
					<el-table-column type="index" label="序号"  align="center">
					</el-table-column>
					<el-table-column prop="XMBH" label="项目编号" width="200" align="center">
					</el-table-column>
					<el-table-column prop="XMMC" label="项目名称"  header-align="center">
					</el-table-column>
					<el-table-column prop="AZLX_VUE" label="安装类型"  align="center">
					</el-table-column>
					<el-table-column prop="LXSJ" label="立项时间"  align="center" width="150">
					</el-table-column>
					<el-table-column prop="STATUS" label="项目状态"  align="center" :formatter="ztformatter">
					</el-table-column>
					<el-table-column prop="DWMC" label="单位名称"  align="center">
					</el-table-column>
					<el-table-column prop="XZQY" label="行政区域"  align="center">
					</el-table-column>
					<el-table-column prop="LXR" label="联系人"  align="center">
					</el-table-column>
					<el-table-column label="操作" align="left">
						<template slot-scope="scope">
							<label  title="项目管理"><i @click="xmEdit_vue(scope.$index, scope.row)" class="iconfont icon-caijifanweipeizhi" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
							<label  title="设计补充"><i @click="handleEdit_vue(scope.$index, scope.row)" class="iconfont icon-weituoshejiguanli" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
							<label v-if="scope.row.YSR!=null && scope.row.YSR!=''" title="预算补充" ><i @click="yusuanEdit_vue(scope.$index, scope.row)" class="iconfont icon-yusuan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
							<label v-if="scope.row.ISORNOTSTOP!=0 || scope.row.ISORNOTSTOP!='0'" title="项目终止" ><i @click="stopReason(scope.$index, scope.row)" class="iconfont icon-tingzhi" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
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
			<el-dialog title="终止原因" :visible.sync = "zzyyVisible" :close-on-click-modal="false">
				<el-input v-model="xmid" style="display: none" ></el-input>
				<el-input
						type="textarea"
						:rows="4"
						placeholder="请输入内容"
						v-model="zzyy">
				</el-input>
				<div slot="footer" class="dialog-footer">
					<el-button size="mini" type="info" @click="zzyyVisible = false"><i class="iconfont icon-quxiao"></i>取 消</el-button>
					<el-button type="primary" size="mini" @click="stopProject_vue()"><i class="iconfont icon-queren"></i>确 定</el-button>
				</div>
			</el-dialog>
		</el-row>
	</section>
</template>

<script>
	import '../../assets/iconfont/iconfont.css'
	import util from '../../common/js/util'
	import { getTcxm,stopProject} from '../../api/sjst';
	import { findAzlx } from '../../api/sbyj';
	export default {
		data() {
			return {
				filters: {
					xmbh:'',
					xmmc:'',
					azlx_vue:'',
					xmzt:'',
					kssj:'',
					jssj:''
				},
				xmztOptions:[{
					codevalue: '1',
					codename: '正在进行'
				},{
					codevalue: '2',
					codename: '已完成'
				},{
					codevalue: '-1',
					codename: '作废终止'
				}],
				azlxList:[],
				row: [],
				zzyy:"",
				total: 10,//表格数据总数量
				page: 1,//当前页码
				size: 10,//每页容量
				activityId:'30001',
				listLoading: false,
				zzyyVisible:false,
				xmid:""
			}
		},
		methods: {
			//项目终止
			stopReason(index,row){
				this.zzyy="";
				this.xmid=row.ID;
				this.zzyyVisible=true;
			},
			stopProject_vue(){
				if(this.zzyy==""){
					this.$message.error('请填写终止原因！');
					return;
				}
				let params = {
					xmid:this.xmid,
					zzyy:this.zzyy
				}
				stopProject(params).then((res)=>{
					if(res.data){
						this.zzyyVisible=false;
						this.getXmlb_vue();
						this.$message.success('终止成功');
					}else{
						this.$message.error('终止失败');
					}
				})
			},
			//格式化状态
			ztformatter(row, column) {
				switch (row.STATUS) {
					case 2:
					case "2":
						return "已完成";
						break;
					case 1:
					case "1":
						return "正在进行";
						break;
					case -1:
					case "-1":
						return "作废终止";
						break;
					default:
						break;
				}
			},
			//查询安装类型下拉框数据
			findAzlx_vue(){
				let params = {};
				findAzlx(params).then(res=>{
					this.azlxList = res.data.list;
				});
			},
			handleCurrentChange(val) {
				this.page = val;
				this.getXmlb_vue(1);
			},
			handleSizeChange(val) {
                this.size = val;
                this.getXmlb_vue();
            },
			//获取项目列表
			getXmlb_vue(flag) {
				if(flag!=1){
					this.page=1
				}
				let azlx = "";
				if(this.filters.azlx_vue!=""){
					azlx = this.filters.azlx_vue[1]
				}
				let params = {
					page: this.page,
					size: this.size,
					activityId:this.activityId,
					azlx:azlx,
					xmbh:this.filters.xmbh,
					xmmc:this.filters.xmmc,
					xmzt:this.filters.xmzt,
					kssj:this.filters.kssj,
					jssj:this.filters.jssj
				};
				this.listLoading = true;
				getTcxm(params).then((res) => {
					if(res.data.total>0&&res.data.row.length<=0){
						this.getXmlb_vue();
					}
					this.total = res.data.total;
					this.row = res.data.row;
					this.listLoading = false;


				});
			},
			xmEdit_vue (index, row) {
				this.$router.push({path: '/sjbc', query: {xmid:row.ID,isxmtc:"1"}})
			},
			yusuanEdit_vue(index, row){
				this.$router.push({path: '/YSbc', query: {xmid:row.ID,activityId:'40003',sjstrzwid:row.SJSTID,isxmtc:"1",ysr:row.YSR,yszbid:row.YSID}})
			},
			handleEdit_vue (index, row) {
				this.$router.push({path: '/scsjcg', query: {xmid:row.ID,activityId:'30003',sjstrzwid:row.SJSTID,isxmtc:"1"}})
			}
		},
		mounted() {
			let end = new Date();
			let start = new Date();
			start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);//一个月前的时间
			this.filters.jssj = util.formatDate.format(end,'yyyy-MM-dd');
			this.filters.kssj = util.formatDate.format(start,'yyyy-MM-dd');
			this.getXmlb_vue();
			this.findAzlx_vue();
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
