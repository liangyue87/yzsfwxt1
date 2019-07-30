<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<el-row>
			<el-col :span="24" class="toolbar" >
				<!--列表-->
				<el-table :data="row" highlight-current-row v-loading="listLoading" style="width: 100%;"
						  height="550" :stripe="true" :row-style="rowClass" :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
					<el-table-column property="type" label="申请类型" width="100" align="center">
					</el-table-column>
					<el-table-column property="azlx" label="安装类型" width="100" align="center">
					</el-table-column>
					<el-table-column property="lxr" label="联系人" width="90" align="center">
					</el-table-column>
					<el-table-column property="lxrsjh" label="联系方式" width="100" align="center">
					</el-table-column>
					<el-table-column property="slrq" label="受理时间" width="100" align="center">
					</el-table-column>
					<el-table-column property="xmmc" label="工程名称" width="150" align="center">
					</el-table-column>
					<el-table-column property="xmdz" label="工程地址"  min-width="180" align="left" >
					</el-table-column>
					<el-table-column property="dwmc" label="报建单位"  min-width="180" align="left" >
					</el-table-column>
					<el-table-column fixed="right" label="操作" width="100">
						<template slot-scope="scope">
							<label title="签收">
								<i class="iconfont icon-qianshou"  @click="toQs(scope.$index, scope.row)"
								   style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
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

	import { getXqqs,toXqqs } from '../../api/xqsl';
	export default {
		data() {
			return {
				filters: {
					sqlx:'',
					status:'',
					gcmc:'',
					lxr:'',
					gcdz: ''
				},
				row: [],
				total: 10,//表格数据总数量
				page: 1,//当前页码
				size: 15,//每页容量
				listLoading: false
			}
		},
		methods: {
			//获取勘查任务
			getAllXqsl() {
				let params = {
					page: this.page - 1,
					size: this .size,
				};
				this.listLoading = true;
				getXqqs(params).then((res) => {
					this.total = res.data.total;
					this.row = res.data.row;
					this.listLoading = false;
				});
			},
			//签收
			toQs(index, row){
				let params = {
					rwid:row.id,
					type:row.type
				};
				toXqqs(params).then((res) => {
					if(res == true){
						let param = row;
						this.$router.push({path: '/qkxq', query: param})
					}else{
						this.$message.error('签收失败！');
					}
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
			this.getAllXqsl();
		}
	}

</script>

<style scoped>

</style>
