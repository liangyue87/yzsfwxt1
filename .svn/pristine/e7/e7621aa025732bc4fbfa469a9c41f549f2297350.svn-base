<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="border: #f5f7fa solid 1px">
			<el-form :model="YsForm" style="margin-top: 10px">
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
						<span>项目负责人 &nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-input size="small" v-model="YsForm.xmfzr"></el-input>
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
								v-model="YsForm.codename"
								:clearable="true"
								style="width: 100%"
								size="small"
								placeholder="全部"
						></el-cascader>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>编制人员 &nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-input size="small" v-model="YsForm.ysr"></el-input>
					</el-col>
					<el-col :span="2" style="text-align: center;margin-left: 10px">
						<el-button size="mini" type="primary" v-on:click="getList"><i class="iconfont icon-chaxun"></i>查询</el-button>
					</el-col>
					<el-col :span="1" style="text-align: center">
						<el-button size="mini" type="success" @click="dc_vue">
							<i class="iconfont icon-yingyongtubiaodaochu"></i>导出</el-button>
					</el-col>
				</el-form-item>
			</el-form>
		</el-col>
		<el-col :span="24" class="toolbar">
			<!-- 查询结果  -->
			<el-table id="out-ysbz" :data="ysbz" fit style="width: 100%" border highlight-current-row height="400px" stripe="true" :row-style="rowClass" :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
				<el-table-column property="XMBH" label="项目编号" width="200" align="center"></el-table-column>
				<el-table-column property="CODENAME" label="安装类型" width="180" align="center"></el-table-column>
				<el-table-column property="XMMC" label="项目名称" width="180" align="center"></el-table-column>
				<el-table-column property="XMFZR" label="项目负责人" width="120" align="center"></el-table-column>
				<el-table-column property="DWMC" label="报建单位" width="200" align="center"></el-table-column>
				<el-table-column property="QSSJ" label="编制开始时间" width="200" align="center"></el-table-column>
				<el-table-column property="JSSJ" label="结束时间" width="200" align="center"></el-table-column>
				<el-table-column property="YSR" label="编制人员" width="120" align="center"></el-table-column>
				<el-table-column property="ZJE" label="预算金额(元)" width="120" align="center"></el-table-column>
			</el-table>
		</el-col>
		<!--分页-->
		<el-col :span="24" class="toolbar">
			<el-pagination
				layout="total, prev, pager, next, sizes , jumper"
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
import util from '../../../common/js/util';
import Vue from 'vue';
import { Tree } from 'element-ui';
Vue.use(Tree);
import { getYsbzcxList, findAzlx, ToBackward } from '../../../api/Yswt';
import FileSaver from "file-saver";
import XLSX from "xlsx";

export default {
	data() {
		return {
			ysbz: [],
			azlxList:[],
			YsForm: {
				xmmc: '', //项目地址
				codename: '', //项目类型
				xmfzr: '',//项目负责人
				xmbh: '', //项目编号
				ysr:'',
			},
			//show: false, //项目状态
			page: 1, //初始页
			size: 10, //  每页的数据
			sizes: [10, 15, 30, 50,100], //写死每页条数
			total: 3,
			typeNameArr: [],
			typeNamePos: 0,
			storeArr: [],
			storePos: 0,
			feeArr: [],
			feePos: 0,
		};
	},
	methods: {
		//分页
		handleCurrentChange(currentPage) {
			this.page = currentPage;
			this.getList(0);
		},
		//当前页条数
		handleSizeChange(size) {
			this.size = size;
			this.getList(0);
		},
		//查询安装类型下拉框数据
		findAzlx_vue(){
			let params = {};
			findAzlx(params).then(res=>{
				this.azlxList = res.data.list;
			});
		},
		//查询
		getList(flag) {
			if(flag==1){
				this.page=1
			}
			let params = {
				xmbh : this.YsForm.xmbh, //项目编号
				codename : this.YsForm.codename[1],
				xmmc : this.YsForm.xmmc, //项目地址
				xmfzr : this.YsForm.xmfzr,//时间范围
				ysr : this.YsForm.ysr,
				page : this.page,
				size : this.size,
			}
			getYsbzcxList(params).then(res => {


				if (res.total > 0 && res.YsbzcxList.length <= 0) {
					this.getList(1);
				}
				this.ysbz  = res.YsbzcxList;
				this.total = res.total;
				this.merage();
			});
		},
		objectSpanMethod({ row, column, rowIndex, columnIndex }) {
			//row 当前行；column 当前列；rowIndex 当前行号；columnIndex 当前列号
			if (columnIndex === 0 || columnIndex === 1 || columnIndex === 2 ||
					columnIndex === 3 || columnIndex === 4 || columnIndex === 5 || columnIndex === 6) {
				// 第一列的合并方法
				const row1 = this.typeNameArr[rowIndex];
				const col1 = row1 > 0 ? 1 : 0; // 如果被合并了row = 0; 则他这个列需要取消
				return {
					rowspan: row1,
					colspan: col1,
				};
			}
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
			var wb = XLSX.utils.table_to_book(document.querySelector("#out-ysbz"));
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
						"预算编制项目列表"+name+".xlsx"
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
			for (let i = 0; i < this.ysbz.length; i += 1) {
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
					if (this.ysbz[i].XMBH === this.ysbz[i - 1].XMBH) {
						this.typeNameArr[this.typeNamePos] += 1;
						this.typeNameArr.push(0);
					} else {
						this.typeNameArr.push(1);
						this.typeNamePos = i;
					}
				}
			}
		},


	},
	mounted() {
		this.getList(1);
		this.findAzlx_vue();
	}
};
</script>
