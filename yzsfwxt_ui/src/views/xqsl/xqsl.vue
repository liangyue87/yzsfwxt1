<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<el-row>
			<!--工具条-->
			<el-col :span="24" class="toolbar" style="border: #f5f7fa solid 1px">
				<el-form :model="filters">
					<el-form-item>
						<el-col :span="2" style="text-align: right">
							<span>申请类型&nbsp;&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-select v-model="filters.sqlx" size="small" style="width:260px;" placeholder="请选择">
								<el-option label="全部" value=""></el-option>
								<el-option v-for="item in sqList"
										   :key="item.VALUE"
										   :label="item.LABEL"
										   :value="item.VALUE"></el-option>
							</el-select>
						</el-col>
						<el-col :span="2" style="text-align: right">
							<span>分派状态&nbsp;&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-select v-model="filters.status" size="small " placeholder="请选择" style="width:260px;">
								<el-option label="全部" value=""></el-option>
								<el-option label="待分派" value="0"></el-option>
								<el-option label="待签收" value="1"></el-option>
								<el-option label="已签收" value="2"></el-option>
							</el-select>
						</el-col>
						<el-col :span="2" style="text-align: right">
							<span>受理时间&nbsp;&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-date-picker
									v-model="filters.date1"
									type="daterange"
									range-separator="至"
									start-placeholder="日期范围"
									end-placeholder="日期范围"
									size="small ">
							</el-date-picker>
						</el-col>
					</el-form-item>
					<el-form-item>
						<el-col :span="2" style="text-align: right">
							<span>工程名称&nbsp;&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-input v-model="filters.gcmc" size="small " style="width:260px;" placeholder="输入工程名称"></el-input>
						</el-col>
						<el-col :span="2" style="text-align: right">
							<span>报建联系人&nbsp;&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-input v-model="filters.lxr" size="small " style="width:260px;" placeholder="输入报建联系人"></el-input>
						</el-col>
						<el-col :span="2" style="text-align: right">
							<span>工程地址&nbsp;&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-input v-model="filters.gcdz" size="small " style="width:260px;" placeholder="输入工程地址"></el-input>
						</el-col>
					</el-form-item>
					<el-form-item>
						<el-col :span="5" style="text-align: right">
							<el-button size="mini" type="primary" @click="handleQuery">
								<i class="iconfont icon-chaxun"></i>查询</el-button>
							<el-button size="mini" type="primary" @click="handleAdd">
								<i class="iconfont icon-xinzeng"></i>新增受理单</el-button>
						</el-col>
					</el-form-item>
				</el-form>
			</el-col>
			<el-col :span="24" class="toolbar" >
			<!--列表-->
			<el-table :data="row" highlight-current-row v-loading="listLoading" style="width: 100%;" height="360"
					  :stripe="true" :row-style="rowClass" :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
				<el-table-column type="index" label="序号" width="60" align="center">
				</el-table-column>
				<el-table-column property="type" label="申请类型" align="center">
				</el-table-column>
				<el-table-column property="azlxname" label="安装类型" width="100" align="center">
				</el-table-column>
				<el-table-column property="lxr" label="联系人" align="center">
				</el-table-column>
				<el-table-column property="lxrsjh" label="联系方式" align="center">
				</el-table-column>
				<el-table-column property="slrq" label="受理时间" width="160" align="center">
				</el-table-column>
				<el-table-column property="xmmc" label="工程名称" align="left">
				</el-table-column>
				<el-table-column property="xmdz" label="工程地址" width="200" header-align="center" align="left" >
				</el-table-column>
				<el-table-column property="dwmc" label="报建单位" align="left" >
				</el-table-column>
				<el-table-column label="操作" fixed="right" width="100" align="center">
					<template slot-scope="scope">
						<label title="编辑">
							<i class="iconfont icon-ai-edit"  @click="toEdit(scope.row)"
							   style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
						</label>
						<label title="删除">
							<i class="iconfont delete"
							   @click="deleteXq(scope.row)"
							   style="cursor:pointer;padding: 2px;color: #f78989"></i>
						</label>
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
	import util from '../../common/js/util'

	import { getXqsl,queryXqsl,deleteXq } from '../../api/xqsl';
	export default {
		data() {
			return {
				filters: {
					sqlx:'',
					status:'',
					gcmc:'',
					lxr:'',
					gcdz: '',
					date1:''
				},
				row: [],
				sqList:[],//申请类型下拉框数据
				total: 10,//表格数据总数量
				page: 1,//当前页码
				size: 15,//每页容量
				listLoading: false
			}
		},
		//Enter键监听执行查询
		created() {
			let lett = this;
			document.onkeydown = function(e) {
				var key = window.event.keyCode;
				if (key == 13) {
					lett.handleQuery();
				}
			}
		},
		methods: {
			//获取勘查任务
            getAllXqsl() {
				let params = {
					page: this.page - 1,
					size: this.size,
				};
				this.listLoading = true;
                getXqsl(params).then((res) => {
					this.total = res.data.total;
					this.row = res.data.row;
					this.listLoading = false;
				});
			},
			//新增受理单
			handleAdd(){
				this.$router.push({path:'/addXq'});
			},
			//條件查詢需求受理界面
			handleQuery(){
				let params = {
					page: this.page - 1,
					size: this.size,
					sqlx: this.filters.sqlx,
					status: this.filters.status,
					gcmc: this.filters.gcmc,
					lxr: this.filters.lxr,
					gcdz: this.filters.gcdz,
				};
				if(this.filters.date1 != null && this.filters.date1 != ''){
					params['date1'] = this.filters.date1[0];
					params['date2'] = this.filters.date1[1];
				}
				this.listLoading = true;
				queryXqsl(params).then((res) => {
					this.total = res.data.total;
					this.row = res.data.row;
                    this.sqList = res.data.sqList;
					this.listLoading = false;
				});
			},
			//去指派人員界面
			toZpry(row){
				let params = {
					rwid:row.id,
					gcmc:row.xmmc,
					xmdz:row.xmdz,
					dwmc:row.dwmc,
					lxr:row.lxr,
					lxrsjh:row.lxrsjh,
					slrq:row.slrq,
					azlx:row.azlx,
					type:row.type
				};
				this.$router.push({path: '/zpry', query: params})
			},
			toEdit(row){
				let params = row;
				this.$router.push({path: '/editXq', query: params});
			},
			deleteXq(row){
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					let para = {id:row.id,type:row.typeid};
					deleteXq(para).then(res=>{
						if(res == true){
							this.$message({
								message: '删除成功',
								type: 'success'
							});
							this.handleQuery();
						}else{
							this.$message.error('删除失败！');
						}
					});
				}).catch(() => {

				});
			},
            handleCurrentChange(val) {
                this.page = val;
                this.handleQuery();
            },
            handleSizeChange(val) {
                this.size = val;
                this.page = 1;
                this.handleQuery();
            },
			rowClass:function (row,index) {
				return {"height":"6.0vh"}
			}
		},
		mounted() {
			this.handleQuery();
		}
	}

</script>

<style scoped>
</style>
