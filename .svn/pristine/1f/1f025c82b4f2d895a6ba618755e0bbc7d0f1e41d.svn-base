<template>
	<section>
		<el-row>
			<!--工具条-->
			<!--<el-col :span="24" class="toolbar" >
				<el-form  :model="filters" >
					<el-form-item>
						<el-col :span="2" style="text-align: right">
							<span>项目编号：</span>
						</el-col>
						<el-col :span="5">
							<el-input v-model="filters.xmbh"></el-input>
						</el-col>
						<el-col :span="2"style="text-align: right">
							<span>单位名称：</span>
						</el-col>
						<el-col :span="5">
							<el-input v-model="filters.dwmc"></el-input>
						</el-col>
						<el-col :span="2"style="text-align: right">
							<span>联系人：</span>
						</el-col>
						<el-col :span="5">
							<el-input v-model="filters.lxr"></el-input>
						</el-col>
					</el-form-item>
					<el-form-item>
						<el-col :span="2" style="text-align: right">
							<span>项目进度：</span>
						</el-col>
						<el-col :span="5">
							<el-select v-model="filters.xmjd"clearable  placeholder="请选择" style="width:100%">
								<el-option label="已办" value="0"></el-option>
								<el-option label="待办" value="1"></el-option>
							</el-select>
						</el-col>
						<el-col :span="2"style="text-align: right">
							<span>立项时间：</span>
						</el-col>
						<el-col :span="5">
							<el-date-picker type="date" placeholder="选择日期" v-model="filters.kssj" style="width: 100%;"></el-date-picker>
						</el-col>
						<el-col class="line" :span="2" style="text-align: center">-</el-col>
						<el-col :span="5">
							<el-date-picker placeholder="选择日期" v-model="filters.jssj" style="width: 100%;"></el-date-picker>
						</el-col>
						<el-col :span="3" style="text-align: center">
							<el-button type="primary" v-on:click="getXmlb_vue">查询</el-button>
						</el-col>
					</el-form-item>
				</el-form>
			</el-col>-->
			<el-col :span="24" class="toolbar" >
				<!--列表-->
				<el-table :data="row" :stripe="true" highlight-current-row v-loading="listLoading"  style="width: 100%;" height="500">
					<el-table-column type="index" label="序号"  align="center">
					</el-table-column>
					<el-table-column prop="xmbh" label="项目编号"  align="center">
					</el-table-column>
					<el-table-column prop="xmmc" label="项目名称" align="center">
					</el-table-column>
					<el-table-column prop="dwmc" label="单位名称"  align="center">
					</el-table-column>
					<el-table-column prop="xmdz" label="项目地址" width="250" header-align="center">
					</el-table-column>
					<el-table-column prop="lxr" label="联系人" align="center">
					</el-table-column>
					<el-table-column label="操作"  align="center">
						<template slot-scope="scope">
							<el-button size="mini" type="primary" @click="goSjcg_vue(scope.$index, scope.row)">上传设计成果</el-button>
						</template>
					</el-table-column>
				</el-table>
			</el-col>
			<el-col :span="24" class="toolbar" >
				<!--工具条-->
				<el-col :span="24" class="toolbar" >
					<!--工具条-->
					<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[10, 15, 30, 50,100]"
								   @current-change="handleCurrentChange" @size-change="handleSizeChange"
								   :page-size="this.size" style="float:right;" :total="this.total">
					</el-pagination>
				</el-col>
			</el-col>

		</el-row>
	</section>
</template>

<script>
	import { getSjrySjrwXmlb,goSjrwfp } from '../../api/sjst';
	export default {
		data() {
			return {
				/*filters: {
					xmbh:'',
					dwmc:'',
					lxr:'',
					xmjd:'',
					kssj:'',
					jssj:''
				},*/
				row: [],
				total: 10,//表格数据总数量
				page: 1,//当前页码
				size: 10,//每页容量
				activityId:'30003',
				listLoading: false,
			}
		},
		methods: {
			//显示转换
			handleCurrentChange(val) {
				this.page = val;
				this.getXmlb();
			},
			handleSizeChange(val) {
				this.size = val;
				this.getXmlb();
			},
			//获取项目列表
			getSjrySjrwXmlb_vue() {
				let params = {
					page: this.page,
					size: this .size,
					activityId:this.activityId
				};
				//let params = Object.assign(param,this.filters);
				this.listLoading = true;
				//NProgress.start();
				getSjrySjrwXmlb(params).then((res) => {
					this.total = res.data.total;
					this.row = res.data.row;
					this.listLoading = false;
				});
			},
			//跳转至上传设计审图成果界面
			goSjcg_vue (index, row) {
				let params = {
					xmid:row.id,
					activityId:this.activityId
				}
				goSjrwfp(params).then((res) =>{
					if(res.data==true){
						this.$router.push({path: '/scsjcg', query: {xmid:row.id,activityId:this.activityId,sjstrzwid:row.sjstrzwid,isxmtc:"0"}})
					}
				})

			}
		},
		mounted() {
			this.getSjrySjrwXmlb_vue();
		}
	}

</script>
