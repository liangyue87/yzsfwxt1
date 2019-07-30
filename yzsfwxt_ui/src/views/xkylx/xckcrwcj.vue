<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<el-row>
			<!--工具条-->
			<el-col :span="24" class="toolbar" style="border: 1px solid #f5f7fa;">
				<el-form  :model="filters" style="margin-top: 10px" size="small">
					<el-form-item>
						<el-col :span="2" style="text-align: right">
							<span>项目/申请编号&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-input v-model="filters.xmbh" @keyup.enter.native="getKcrw"></el-input>
						</el-col>
						<el-col :span="2"style="text-align: right">
							<span>单位名称&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-input v-model="filters.dwmc" @keyup.enter.native="getKcrw"></el-input>
						</el-col>
						<el-col :span="2"style="text-align: right">
							<span>联系人&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-input v-model="filters.lxr" @keyup.enter.native="getKcrw"></el-input>
						</el-col>
					</el-form-item>
					<el-form-item>
						<el-col :span="2" style="text-align: right">
							<span>任务状态&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-select v-model="filters.rwzt" placeholder="请选择" style="width:100%">
								<el-option label="未发起勘查任务" value="0"></el-option>
								<el-option label="已发起勘查任务" value="1"></el-option>
								<el-option label="勘查任务已完成" value="2"></el-option>
							</el-select>
						</el-col>
						<el-col :span="2"style="text-align: right">
							<span>受理时间&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-date-picker type="date" placeholder="选择开始时间" v-model="filters.kssj" value-format="yyyy-MM-dd" style="width: 100%;">
							</el-date-picker>
						</el-col>
						<el-col class="line" :span="2" style="text-align: center">-</el-col>
						<el-col :span="5">
							<el-date-picker type="date" placeholder="选择结束时间" v-model="filters.jssj" value-format="yyyy-MM-dd" style="width: 100%;">
							</el-date-picker>
						</el-col>
						<el-col :span="3" style="text-align: center">
							<el-button size="mini" type="primary" @click="getKcrw()"><i class="iconfont icon-chaxun"></i>查询</el-button>
						</el-col>
					</el-form-item>
				</el-form>
			</el-col>
			<el-col :span="24" class="toolbar">
			<!--列表-->
			<el-table :data="row" highlight-current-row v-loading="listLoading" style="width: 100%;"
					  height="400" :header-cell-style="{fontSize:'15px',background:'#f5f7fa'}">
				<el-table-column type="index" label="序号" width="100" align="center">
				</el-table-column>
				<el-table-column property="xmbh" label="项目/申请编号" width="200" align="center">
				</el-table-column>
				<el-table-column property="lxr" label="联系人" width="180" align="center">
				</el-table-column>
				<el-table-column property="lxrsjh" label="联系方式" width="180" align="center">
				</el-table-column>
				<el-table-column property="slrq" label="受理时间" width="180" align="center">
				</el-table-column>
				<el-table-column property="dwmc" label="单位名称" min-width="180" align="center">
				</el-table-column>
				<!--<el-table-column property="xmdz" label="工程地址" align="center" min-width="180" >
				</el-table-column>-->
				<el-table-column label="操作" width="100" align="center" fixed="right" v-if="cz_show">
					<template slot-scope="scope">
						<label v-if="scope.row.rwzt =='0'" title="发起现场勘查" @click="toFqlhkc(scope.$index, scope.row, '0')">
							<i class="iconfont icon-xianchangkancha" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
						<label v-if="scope.row.rwzt =='1'" title="修改本次勘查任务" @click="toFqlhkc(scope.$index, scope.row, '1')">
							<i class="iconfont icon-xianchangkancha" style="cursor:pointer;padding: 2px;color: #E6A23C"></i></label>
					</template>
				</el-table-column>
			</el-table>
			</el-col>
			<el-col :span="24" class="toolbar" >
			<!--工具条-->
				<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[10, 15, 30, 50,100]"
							   :current-page.sync = "this.page"
							   @current-change="handleCurrentChange" @size-change="handleSizeChange"
							   :page-size="this.size" style="float:right;" :total="this.total">
				</el-pagination>
			</el-col>
		</el-row>
	</section>
</template>

<script>
	import util from '../../common/js/util'

	import { getKcrw } from '../../api/xkylx';
	export default {
		data() {
			return {
				filters: {
					xmbh:'',
					rwzt:'0',
					lxr:'',
					xmjd:'',
					kssj:'',
					jssj:''
				},
				cz_show: true,
				row: [],
				total: 10,//表格数据总数量
				page: 1,//当前页码
				size: 15,//每页容量
				listLoading: false
			}
		},
		methods: {
			//获取勘查任务
			getKcrw(flag) {
				if(flag!=1){
					this.page=1
				}
				this.filters.xmjd = '1';
				let params = Object.assign({ page: this.page, size: this .size }, this.filters);
				this.listLoading = true;
				getKcrw(params).then((res) => {
					if(res.data.total>0&&res.data.row.length<=0){
						this.getKcrw();
					}
					this.total = res.data.total;
					this.row = res.data.row;
					if(this.filters.rwzt == 2){
						this.cz_show = false;
					}else {
						this.cz_show = true;
					}
					this.listLoading = false;
				});
			},
			handleCurrentChange(val) {
				this.page = val;
				this.getKcrw(1);
			},
			handleSizeChange(val) {
				this.size = val;
				this.page = 1;
				this.getKcrw();
			},
			//跳转至发起勘查任务界面
			toFqlhkc: function (index, row, type) {

				//setXmidToSession(params).then((res) =>{
					this.$router.push({path: '/Fqxckc', query: {xmid : row.id,kcdd: row.xmdz,type : type}})
				//});
				//this.$router.push({name: 'fqxckc', params: {paicheNo: '123'}})

				/*this.editFormVisible = true;
				this.editForm = Object.assign({}, row);//编辑表单的赋值
				this.editForm.islocked = parseInt(row.islocked);//性别赋值*/
			}
		},
		mounted() {
			let end = new Date();
			let start = new Date();
			start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);//一个月前的时间
			this.filters.jssj = util.formatDate.format(end,'yyyy-MM-dd');
			this.filters.kssj = util.formatDate.format(start,'yyyy-MM-dd');
			this.getKcrw();
		}
	}

</script>

<style scoped>

</style>
