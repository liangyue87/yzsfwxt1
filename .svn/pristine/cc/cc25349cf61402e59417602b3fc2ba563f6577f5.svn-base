<template>
	<section>
		<el-row>
			<!--工具条-->
			<el-col :span="24" class="toolbar" style="border: 1px solid #f5f7fa;">
				<el-form  :model="filters" style="margin-top: 10px" size="small">
					<el-form-item>
						<el-col :span="2" style="text-align: right">
							<span>项目名称&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-input v-model="filters.xmmc" @keyup.enter.native="getHhldcx(1)"></el-input>
						</el-col>
						<el-col :span="2" style="text-align: right">
							<span>项目编号&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-input v-model="filters.xmbh" @keyup.enter.native="getHhldcx(1)"></el-input>
						</el-col>
						<el-col :span="2" style="text-align: right">
							<span>状态&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-select v-model="filters.d" clearable  style="width:100%" placeholder="请选择">
								<el-option
										v-for="item in options"
										:key="item.value"
										:label="item.label"
										:value="item.value">
								</el-option>
							</el-select>
						</el-col>
					</el-form-item>
					<el-form-item>
						<el-col :span="2" style="text-align: right">
							<span>项目负责人&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-input v-model="filters.xmfzr" @keyup.enter.native="getHhldcx(1)"></el-input>
						</el-col>
						<el-col class="line" :span="2" style="text-align: right">
							环节名称&nbsp;
						</el-col>
						<el-col :span="5">
							<el-select v-model="filters.activityname" clearable  style="width:100%" placeholder="请选择">
								<el-option v-for="item in typeList"
										   :key="item.ID"
										   :label="item.MC"
										   :value="item.BM"></el-option>
							</el-select>
						</el-col>
						<el-col class="line" :span="2" style="text-align: right">
							任务处理人&nbsp;
						</el-col>
						<el-col :span="5">
							<el-input v-model="filters.rwblr" @keyup.enter.native="getHhldcx(1)"></el-input>
						</el-col>
					</el-form-item>
					<el-form-item>
						<el-col :span="2" style="text-align: right">
							<span>时间范围&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-date-picker type="date" placeholder="选择开始时间" value-format="yyyy-MM-dd" v-model="filters.kssj" style="width: 100%;">
							</el-date-picker>
						</el-col>
						<el-col class="line" :span="2" style="text-align: center">-</el-col>
						<el-col :span="5">
							<el-date-picker type="date" placeholder="选择结束时间" value-format="yyyy-MM-dd" v-model="filters.jssj" style="width: 100%;">
							</el-date-picker>
						</el-col>
						<el-col :span="2" style="text-align: right">
							&nbsp;
						</el-col>
						<el-col :span="3" style="text-align: left">
							<el-button size="mini" type="primary" @click="getHhldcx(1)"><i class="iconfont icon-chaxun"></i>查询</el-button>
							<el-button size="mini" type="success" @click="dc_vue"><i class="iconfont icon-yingyongtubiaodaochu"></i>导出</el-button>
						</el-col>
					</el-form-item>
				</el-form>
			</el-col>
			<el-col :span="24" class="toolbar">
			<!--列表-->
			<el-table id="out-hhld" :data="hhldcx" class="addThe_phone_inner" :span-method="objectSpanMethod" border highlight-current-row v-loading="listLoading"
					  style="width: 100%;" height="400" :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }" :row-style="rowClass">
				<el-table-column property="XMBH" label="项目编号" width="180" align="center">
				</el-table-column>
				<el-table-column property="XMMC" label="项目名称" width="180" align="center">
				</el-table-column>
				<el-table-column property="XMFZR" label="项目负责人" width="120" align="center">
				</el-table-column>
				<el-table-column property="ACTIVITYNAME" label="环节名称" width="160" align="center">
				</el-table-column>
				<el-table-column property="RWBLR" label="任务处理人"  min-width="120" align="center">
				</el-table-column>
				<el-table-column property="CHECK_SX_STR" label="要求签收时间"  width="180" align="center">
				</el-table-column>
				<el-table-column property="CHECK_TIME_STR" label="签收时间" width="180" align="center">
				</el-table-column>
				<el-table-column property="JSSX_YL_STR" label="任务要求时间"  width="180" align="center">
				</el-table-column>
				<el-table-column property="JSSJ_STR" label="任务完成时间"  width="180" align="center">
				</el-table-column>
				<el-table-column type="index" label="状态" width="80" align="center">
					<template slot-scope="scope">
						<span v-if="scope.row.LD == '0' && scope.row.HD== '0'">绿灯</span>
						<span v-else-if="scope.row.LD == '1' && scope.row.HD== '0'">黄灯</span>
						<span v-else-if="scope.row.HD== '1'">红灯</span>
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
	import util from '../../../common/js/util';
	import { getHhldcx ,findAzlx} from '../../../api/Yswt';
	import { findBacklog,getTypeCheckbox } from '../../../api/work';
	import FileSaver from "file-saver";
	import XLSX from "xlsx";

	export default {
		name: "hhldcx",
		data() {
			return {
				ldsh:[],
				yszp:[],
				ysbz:[],
				ysqs:[],
				filters: {
					xmmc:'',
					xmbh:'',
					xmfzr:'',
					activityname:'',
					rwblr:'',
					type:'',
					d:'2',//灯的颜色
					kssj:'',
					jssj:'',
				},
				hhldcx: [],
				total: 10,//表格数据总数量
				page: 1,//当前页码
				size: 15,//每页容量
				listLoading: false,
				typeList :[],
				typeNameArr: [],
				typeNamePos: 0,
				storeArr: [],
				storePos: 0,
				feeArr: [],
				feePos: 0,
				options: [{
					value: '0',
					label: '绿灯'
				}, {
					value: '1',
					label: '黄灯'
				}, {
					value: '2',
					label: '红灯'
				}],
			}
		},
		methods: {
			//查询列表
			getHhldcx(flag) {
				if(flag==1){
					this.page=1
				}
				let params = {
					d:this.filters.d,//灯的颜色（状态判断）
					xmmc: this.filters.xmmc,//项目名称
					xmbh: this.filters.xmbh,//项目编号
					xmfzr: this.filters.xmfzr,//项目负责人
					activityname: this.filters.activityname,//环节名称
					rwblr: this.filters.rwblr,//任务办理人
					type: this.filters.type,//状态（红黄绿）
					kssj: this.filters.kssj,//开始时间
					jssj: this.filters.jssj,//失败时间
					page: this.page,
					size: this.size,
				}
				getHhldcx(params).then(res => {
					if (res.total > 0 && res.hhldcx.length <= 0) {
						this.getList(1);
					}
					this.hhldcx = res.hhldcx;
					this.total = res.total;
					this.merage();
				});
			},
			//to下载表格
			dc_vue(){
				// 设置当前日期
				let time = new Date();
				let year = time.getFullYear();
				let month = time.getMonth() + 1;
				let day = time.getDate();
				let name = year + "" + month + "" + day;
				/* 从表生成工作簿对象 */
				var wb = XLSX.utils.table_to_book(document.querySelector("#out-hhld"));
				/* 获取二进制字符串作为输出 */
				var wbout = XLSX.write(wb, {
					bookType: "xlsx",
					bookSST: true,
					type: "array"
				});
				try {
					FileSaver.saveAs(
							//Blob 对象表示一个不可变、原始数据的类文件对象。
							//Blob 表示的不一定是JavaScript原生格式的数据。
							//File 接口基于Blob，继承了 blob 的功能并将其扩展使其支持用户系统上的文件。
							//返回一个新创建的 Blob 对象，其内容由参数中给定的数组串联组成。
							new Blob([wbout], { type: "application/octet-stream" }),
							//设置导出文件名称
							"红黄绿灯项目列表"+name+".xlsx"
					);
				} catch (e) {
					if (typeof console !== "undefined") console.log(e, wbout);
				}
				return wbout;
			},
			merageInit() {
				this.typeNameArr = [];
				this.typeNamePos = 0;
				this.storeArr = [];
				this.storePos = 0;
				this.feeArr = [];
				this.feePos = 0;
			},
			merage() {
				this.merageInit();
				for (let i = 0; i < this.hhldcx.length; i += 1) {
					if (i === 0) {
						// 第一行必须存在
						this.typeNameArr.push(1);
						this.typeNamePos = 0;
						this.storeArr.push(1);
						this.storePos = 0;
						this.feeArr.push(1);
						this.feePos = 0;
					} else {
						// 判断当前元素与上一个元素是否相同,eg：this.typeNamePos 是 this.typeNameArr序号
						// 第一列
						// eslint-disable-next-line no-lonely-if
						if (this.hhldcx[i].XMBH === this.hhldcx[i - 1].XMBH) {
							this.typeNameArr[this.typeNamePos] += 1;
							this.typeNameArr.push(0);
						} else {
							this.typeNameArr.push(1);
							this.typeNamePos = i;
						}
					}
				}
			},
			objectSpanMethod({ row, column, rowIndex, columnIndex }) {
				//row 当前行；column 当前列；rowIndex 当前行号；columnIndex 当前列号
				if (columnIndex === 0 || columnIndex === 1 || columnIndex === 2) {
					// 第一列的合并方法
					const row1 = this.typeNameArr[rowIndex];
					const col1 = row1 > 0 ? 1 : 0; // 如果被合并了row = 0; 则他这个列需要取消
					return {
						rowspan: row1,
						colspan: col1,
					};
				}
			},
			rowClass:function (row,index) {
				return {"height":"4.0vh"}
			},
			//分页
			handleCurrentChange(currentPage) {
				this.page = currentPage;
				this.getHhldcx(0);
			},
			//当前页条数
			handleSizeChange(size){
				this.size = size;
				this.getHhldcx(0);
			},

		},
		mounted() {
			let end = new Date();
			let start = new Date();
			start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);//一个月前的时间
			this.filters.jssj = util.formatDate.format(end,'yyyy-MM-dd');
			this.filters.kssj = util.formatDate.format(start,'yyyy-MM-dd');

			//初始化列表
			this.getHhldcx(1);

			//初始化环节名称查询
			getTypeCheckbox().then((res) => {
				this.typeList = res.data.list;
			});


		}
	}

</script>

<style scoped>
	.lignt_green{
		color: #67c23a;
		font-size: large
	}
	.lignt_yellow{
		color: #fee300;
		font-size: large
	}
	.lignt_red{
		color: #f56c6c;
		font-size: large
	}
	.addThe_phone_inner::-webkit-scrollbar {/*滚动条整体样式*/
		width: 0px !important ; /*高宽分别对应横竖滚动条的尺寸*/
		height: 0px !important;
	}
	.addThe_phone_inner::-webkit-scrollbar-thumb {/*滚动条里面小方块*/
		border-radius: 10px !important;
		box-shadow: inset 0 0 5px rgba(0,0,0,0.2) !important;
		background: #c1c1c1 !important;
	}
	.addThe_phone_inner::-webkit-scrollbar-track {/*滚动条里面轨道*/
		box-shadow: inset 0 0 5px rgba(0,0,0,0.2) !important;
		border-radius: 10px !important;
		background: #EDEDED !important;
	}
</style>
