<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<el-row>
			<!--工具条-->
			<el-col :span="24" class="toolbar" style="border: 1px solid #f5f7fa;">
				<el-form  :model="filters" style="margin-top: 10px" size="small">
					<el-form-item>
						<el-col :span="2" style="text-align: right">
							<span>申请编号&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-input v-model="filters.sqbh" @keyup.enter.native="getLxlb"></el-input>
						</el-col>
						<el-col :span="2"style="text-align: right">
							<span>单位名称&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-input v-model="filters.dwmc" @keyup.enter.native="getLxlb"></el-input>
						</el-col>
						<el-col :span="2"style="text-align: right">
							<span>联系人&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-input v-model="filters.lxr" @keyup.enter.native="getLxlb"></el-input>
						</el-col>
					</el-form-item>
					<el-form-item>
						<el-col :span="2" style="text-align: right">
							<span>立项状态&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-select v-model="filters.sflx" placeholder="请选择" style="width:100%">
								<el-option label="未立项" value="0"></el-option>
								<el-option label="已立项" value="1"></el-option>
							</el-select>
						</el-col>
						<el-col :span="2"style="text-align: right">
							<span>受理时间&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-date-picker type="date" placeholder="选择开始时间"
											v-model="filters.kssj" value-format="yyyy-MM-dd" style="width: 100%;">
							</el-date-picker>
						</el-col>
						<el-col class="line" :span="2" style="text-align: center">-</el-col>
						<el-col :span="5">
							<el-date-picker type="date" placeholder="选择结束时间"
											v-model="filters.jssj" value-format="yyyy-MM-dd" style="width: 100%;">
							</el-date-picker>
						</el-col>
						<el-col :span="3" style="text-align: center">
							<el-button size="mini" type="primary" @click="getLxlb"><i class="iconfont icon-chaxun"></i>查询</el-button>
						</el-col>
					</el-form-item>
				</el-form>
			</el-col>
			<el-col :span="24" class="toolbar">
			<!--列表-->
			<el-table :data="row" highlight-current-row v-loading="listLoading" style="width: 100%;"
					  height="400" :header-cell-style="{fontSize:'15px',background:'#f5f7fa'}">
				<el-table-column type="index" label="序号" width="80" align="center">
				</el-table-column>
				<el-table-column property="sqbh" label="申请编号" width="200" align="center">
				</el-table-column>
				<el-table-column property="sllx" label="申请类型" width="120" align="center">
				</el-table-column>
				<el-table-column property="dwmc" label="单位名称" min-width="180" align="center">
				</el-table-column>
				<el-table-column property="lxr" label="联系人" width="150" align="center">
				</el-table-column>
				<el-table-column property="lxrsjh" label="联系方式" width="150" align="center">
				</el-table-column>
				<el-table-column property="slrq" label="受理时间" width="200" align="center">
				</el-table-column>
				<!--<el-table-column property="xmdz" label="工程地址"  min-width="180" align="center">
				</el-table-column>-->
				<el-table-column label="操作" width="100" align="center" fixed="right" v-if="cz_show">
					<template slot-scope="scope">
						<label title="立项" @click="toLx(scope.$index, scope.row)">
							<i class="iconfont icon-set_up_project" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
					</template>
				</el-table-column>
			</el-table>
			</el-col>
			<el-col :span="24" class="toolbar" >
			<!--工具条-->
				<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[10, 15, 30, 50,100]"
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

	import { getLxlb } from '../../api/xkylx';
	export default {
		data() {
			return {
				filters: {
					sqbh:'',
					dwmc:'',
					lxr:'',
					kssj:'',
					jssj:'',
					sflx:'0'
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
			//获取立项列表
			getLxlb(flag) {
				if(flag!=1){
					this.page=1
				}
				let params = Object.assign({ page: this.page, size: this .size }, this.filters);
				this.listLoading = true;
				getLxlb(params).then((res) => {
					if(res.data.total>0&&res.data.row.length<=0){
						this.getLxlb();
					}
					this.total = res.data.total;
					this.row = res.data.row;
					if(this.filters.sflx == 0){
						this.cz_show = true;
					}else {
						this.cz_show = false;
					}
					this.listLoading = false;
				});
			},
			handleCurrentChange(val) {
				this.page = val;
				this.getLxlb(1);
			},
			handleSizeChange(val) {
				this.size = val;
				this.page = 1;
				this.getLxlb();
			},
			//跳转至立项界面
			toLx: function (index, row) {
				//setXmidToSession(params).then((res) =>{
					this.$router.push({path: '/Lx', query: {id : row.id,type : row.type}})
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

			this.getLxlb();
		}
	}

</script>

<style scoped>

</style>
