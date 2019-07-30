
<template>
	<div>
		<div class="bt" style="margin-bottom: 20px">
			<el-col :span="24" class="toolbar" style="border: #f5f7fa solid 1px">
				<el-form  style="margin-top: 10px">
					<el-form-item>
						<el-col :span="2" style="text-align: right">
							<span>项目编号 &nbsp;</span>
						</el-col>
						<el-col :span="4">
							<el-date-picker
									size="small"
									v-model="rq"
									type="month"
									placeholder="选择月">
							</el-date-picker>
						</el-col>
						<el-col :span="3" style="text-align: center;margin-top: -17px">
							<el-button size="mini" type="primary"  v-on:click="getHsjglr_vue(1)"><i class="iconfont icon-chaxun"></i>查询</el-button>
						</el-col>
					</el-form-item>
				</el-form>
			</el-col>
			<div align="center">
				<span class="bt_div_span" style="font-size: 25px;">用户投资类业务 办结统计与超时分析</span>
			</div>
			<!--<div class="bt_div2" style="">
				<el-form style="margin-top: 10px;" >
					<el-form-item>
						<el-col :span="2" style="text-align: right">
							<span>月份 &nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-date-picker
									size="small"
									v-model="rq"
									type="month"
									placeholder="选择月">
							</el-date-picker>
						</el-col>
						<el-col :span="1" style="text-align: center">
							<el-button size="small" type="primary" style="height: 33px"><i class="iconfont icon-chaxun"></i>查询</el-button>
						</el-col>
					</el-form-item>
				</el-form>
			</div>-->


		</div>
		<div id="example-container" class="wrapper">
			<HotTable :root="root" :settings="hotSettings"></HotTable>
		</div>
	</div>
</template>
<script>

	import { HotTable } from '@handsontable/vue';
	import 'handsontable/dist/handsontable.full.css';
	export default {
		data: function () {
			return {
				rq:'',
				root: 'test-hot',
				hotSettings: {
					licenseKey: 'non-commercial-and-evaluation',
					data: [        //数据，可以是数据，对象
						['状态','分类', '', '单位','本期','','','','本年至今（含本期）','','','','往年至今（含本年）','','',''],
						['状态','分类', '', '单位','合计','工商类','居民类','改造类','合计','工商类','居民类','改造类','合计','工商类','居民类','改造类'],
						['新增', '设计委托', '', '个', '','','','','','','','','','','',''],
						['新增', '预算委托', '', '个', '','','','','','','','','','','',''],
						['新增', '下单施工','', '户', '', '','','','','','','','','','',''],
						['新增', '综合验收','', '户', '', '','','','','','','','','','',''],
						['新增', '移交','', '户', '', '','','','','','','','','','',''],
						['办结', '设计委托','总量', '个', '', '','','','','','','','','','',''],
						['办结', '','超时量', '个', '', '','','','','','','','','','',''],
						['办结', '','平均时长', '天', '', '','','','','','','','','','',''],
						['办结', '预算委托','总量', '笔', '', '','','','','','','','','','',''],
						['办结', '','超时量', '笔', '', '','','','','','','','','','',''],
						['办结', '','平均时长', '天', '', '','','','','','','','','','',''],
						['办结', '施工通水','总量', '户', '', '','','','','','','','','','',''],
						['办结', '','超时量', '户', '', '','','','','','','','','','',''],
						['办结', '','平均时长', '天', '', '','','','','','','','','','',''],
						['办结', '综合验收','总量', '户', '', '','','','','','','','','','',''],
						['办结', '','超时量', '户', '', '','','','','','','','','','',''],
						['办结', '','平均时长', '天', '', '','','','','','','','','','',''],
						['办结', '移交','总量', '户', '', '','','','','','','','','','',''],
						['办结', '','超时量', '户', '', '','','','','','','','','','',''],
						['办结', '','平均时长', '天', '', '','','','','','','','','','',''],
						['未办结', '设计委托','总量', '个', '', '','','','','','','','','','',''],
						['未办结', '','超时量', '个', '', '','','','','','','','','','',''],
						['未办结', '','超时量占比', '%', '', '','','','','','','','','','',''],
						['未办结', '预算委托','总量', '笔', '', '','','','','','','','','','',''],
						['未办结', '','超时量', '笔', '', '','','','','','','','','','',''],
						['未办结', '','超时量占比', '%', '', '','','','','','','','','','',''],
						['未办结', '施工通水','总量', '户', '', '','','','','','','','','','',''],
						['未办结', '','超时量', '%', '', '','','','','','','','','','',''],
						['未办结', '','超时量占比', '天', '', '','','','','','','','','','',''],
						['未办结', '综合验收','总量', '户', '', '','','','','','','','','','',''],
						['未办结', '','超时量', '户', '', '','','','','','','','','','',''],
						['未办结', '','超时量占比', '%', '', '','','','','','','','','','',''],
						['未办结', '移交','总量', '户', '', '','','','','','','','','','',''],
						['未办结', '','超时量', '户', '', '','','','','','','','','','',''],
						['未办结', '','超时量占比', '%', '', '','','','','','','','','','',''],
					],
					startRows: 11,//行列范围
					startCols: 6,
					minRows: 5,  //最小行列
					minCols: 5,
					maxRows: 50,  //最大行列
					maxCols: 50,
					rowHeaders: true,//行表头
					colHeaders:   false,//自定义列表头or 布尔值

					 //minSpareCols: 2, //列留白
					 //minSpareRows: 2,//行留白
					currentRowClassName: 'currentRow', //为选中行添加类名，可以更改样式
					currentColClassName: 'currentCol',//为选中列添加类名
					autoWrapRow: true, //自动换行
					contextMenu: {   //自定义右键菜单，可汉化，默认布尔值
						items: {
							"row_above": {
								name:'上方插入一行'
							},
							"row_below": {
								name:'下方插入一行'
							},
							"col_left": {
								name:'左方插入列'
							},
							"col_right": {
								name:'右方插入列'
							},
							"hsep1": "---------", //提供分隔线

							"make_read_only": {
								name: '只读',
							},
							"borders": {
								name: '表格线',
							},
							"commentsAddEdit": {
								name: '添加备注',
							},
							"commentsRemove": {
								name: '取消备注',
							},
							"freeze_column": {
								name: '固定列',
							},
							"unfreeze_column": {
								name: '取消列固定',
							},
							"hsep2": "---------",
						}
					},//右键效果
					fillHandle: true, //选中拖拽复制 possible values: true, false, "horizontal", "vertical"
					fixedColumnsLeft: 0,//固定左边列数
					fixedRowsTop: 0,//固定上边列数
					mergeCells: [   //合并
						{row: 0, col: 0, rowspan: 2, colspan: 1},  //指定合并，从（1,1）开始行3列3合并成一格
						{row: 0, col: 1, rowspan: 2, colspan: 2}, //指定合并，从（1,1）开始行3列3合并成一格
						{row: 0, col: 3, rowspan: 2, colspan: 1}, //指定合并，从（1,1）开始行3列3合并成一格
						{row: 0, col: 4, rowspan: 1, colspan: 4},
						{row: 0, col: 8, rowspan: 1, colspan: 4},
						{row: 0, col: 12, rowspan: 1, colspan: 4},
						{row: 2, col: 0, rowspan: 5, colspan: 1},
						{row: 2, col: 1, rowspan: 1, colspan: 2},
						{row: 3, col: 1, rowspan: 1, colspan: 2},
						{row: 4, col: 1, rowspan: 1, colspan: 2},
						{row: 5, col: 1, rowspan: 1, colspan: 2},
						{row: 6, col: 1, rowspan: 1, colspan: 2},
						{row: 7, col: 0, rowspan: 15, colspan: 1},
						{row: 7, col: 1, rowspan: 3, colspan: 1},//办结设计委托向下合并三行
						{row: 10, col: 1, rowspan: 3, colspan: 1},//办结预算委托向下合并三行
						{row: 13, col: 1, rowspan: 3, colspan: 1},//办结施工通水向下合并三行
						{row: 16, col: 1, rowspan: 3, colspan: 1},//办结综合验收向下合并三行
						{row: 19, col: 1, rowspan: 3, colspan: 1},//办结移交向下合并三行
						{row: 22, col: 0, rowspan: 15, colspan: 1},//未办结向下合并14行
						{row: 22, col: 1, rowspan: 3, colspan: 1},//未办结设计委托向下合并三行
						{row: 25, col: 1, rowspan: 3, colspan: 1},//未办结预算委托向下合并三行
						{row: 28, col: 1, rowspan: 3, colspan: 1},//未办结施工通水向下合并三行
						{row: 31, col: 1, rowspan: 3, colspan: 1},//未办结综合验收向下合并三行
						{row: 34, col: 1, rowspan: 3, colspan: 1},//未办结移交向下合并三行

					],
					columns: [     //添加每一列的数据类型和一些配置
						{

							//data: 'title', 此设置标识，数组对象数据格式时的列字段

							type: 'numeric',   //时间格式
							readOnly: true,
							className: "htCenter htMiddle"
						},

						{

							//data: 'title', 此设置标识，数组对象数据格式时的列字段

							type: 'numeric',   //时间格式
							readOnly: true,
							className: "htCenter htMiddle"
						},

						{

							//data: 'title', 此设置标识，数组对象数据格式时的列字段

							type: 'numeric',   //时间格式
							readOnly: true,
							className: "htCenter htMiddle"
						},

						{

							//data: 'title', 此设置标识，数组对象数据格式时的列字段

							type: 'numeric',   //时间格式
							readOnly: true,
							className: "htCenter htMiddle"
						},

						{

							//data: 'title', 此设置标识，数组对象数据格式时的列字段

							type: 'numeric',   //时间格式
							readOnly: true,
							className: "htCenter htMiddle"
						},

						{

							//data: 'title', 此设置标识，数组对象数据格式时的列字段

							type: 'numeric',   //时间格式
							readOnly: true,
							className: "htCenter htMiddle"
						},

						{

							//data: 'title', 此设置标识，数组对象数据格式时的列字段

							type: 'numeric',   //时间格式
							readOnly: true,
							className: "htCenter htMiddle"
						},
						{

							//data: 'title', 此设置标识，数组对象数据格式时的列字段

							type: 'numeric',   //时间格式
							readOnly: true,
							className: "htCenter htMiddle"
						},
						{

							//data: 'title', 此设置标识，数组对象数据格式时的列字段

							type: 'numeric',   //时间格式
							readOnly: true,
							className: "htCenter htMiddle"
						},
						{

							//data: 'title', 此设置标识，数组对象数据格式时的列字段

							type: 'numeric',   //时间格式
							readOnly: true,
							className: "htCenter htMiddle"
						},
						{

							//data: 'title', 此设置标识，数组对象数据格式时的列字段

							type: 'numeric',   //时间格式
							readOnly: true,
							className: "htCenter htMiddle"
						},
						{

							//data: 'title', 此设置标识，数组对象数据格式时的列字段

							type: 'numeric',   //时间格式
							readOnly: true,
							className: "htCenter htMiddle"
						},
						{

							//data: 'title', 此设置标识，数组对象数据格式时的列字段

							type: 'numeric',   //时间格式
							readOnly: true,
							className: "htCenter htMiddle"
						},
						{

							//data: 'title', 此设置标识，数组对象数据格式时的列字段

							type: 'numeric',   //时间格式
							readOnly: true,
							className: "htCenter htMiddle"
						},
						{

							//data: 'title', 此设置标识，数组对象数据格式时的列字段

							type: 'numeric',   //时间格式
							readOnly: true,
							className: "htCenter htMiddle"
						},
						{

							//data: 'title', 此设置标识，数组对象数据格式时的列字段

							type: 'numeric',   //时间格式
							readOnly: true,
							className: "htCenter htMiddle"
						}
					],
					manualColumnFreeze: true, //手动固定列
					manualColumnResize: true,//手工更改列距
					manualRowResize: true,//手动更改行距
					comments: true, //添加注释
					cell: [
						{row: 7, col: 2, className: 'htLeft'},
						{row: 8, col: 2, className: 'htLeft'},
						{row: 9, col: 2, className: 'htLeft'},
						{row: 10, col: 2, className: 'htLeft'},
						{row: 11, col: 2, className: 'htLeft'},
						{row: 12, col: 2, className: 'htLeft'},
						{row: 13, col: 2, className: 'htLeft'},
						{row: 14, col: 2, className: 'htLeft'},
						{row: 15, col: 2, className: 'htLeft'},
						{row: 16, col: 2, className: 'htLeft'},
						{row: 17, col: 2, className: 'htLeft'},
						{row: 18, col: 2, className: 'htLeft'},
						{row: 19, col: 2, className: 'htLeft'},
						{row: 20, col: 2, className: 'htLeft'},
						{row: 21, col: 2, className: 'htLeft'},
						{row: 22, col: 2, className: 'htLeft'},
						{row: 23, col: 2, className: 'htLeft'},
						{row: 24, col: 2, className: 'htLeft'},
						{row: 25, col: 2, className: 'htLeft'},
						{row: 26, col: 2, className: 'htLeft'},
						{row: 27, col: 2, className: 'htLeft'},
						{row: 28, col: 2, className: 'htLeft'},
						{row: 29, col: 2, className: 'htLeft'},
						{row: 30, col: 2, className: 'htLeft'},
						{row: 31, col: 2, className: 'htLeft'},
						{row: 32, col: 2, className: 'htLeft'},
						{row: 33, col: 2, className: 'htLeft'},
						{row: 34, col: 2, className: 'htLeft'},
						{row: 35, col: 2, className: 'htLeft'},
						{row: 36, col: 2, className: 'htLeft'},

					 ],
					height:900,
					customBorders:[],//添加边框
					columnSorting: true,//排序
					stretchH: 'all',//根据宽度横向扩展，last:只扩展最后一列，none：默认不扩展
				}
			};
		},
		name: 'SampleApp',
		components: {
			HotTable
		}
	}
</script>

<style scoped>
	button{
		margin: 20px 20px;
	}
	.handsontable .currentRow {
		background-color: #E7E8EF;
	}

	.handsontable .currentCol {
		background-color: #F9F9FB;
	}
	.ht_master .wtHolder {
		overflow-x: hidden;
	}
	.bt{
		margin: 0 auto;
	}
	.bt_div_span{
		font-size: 25px;
	}
</style>