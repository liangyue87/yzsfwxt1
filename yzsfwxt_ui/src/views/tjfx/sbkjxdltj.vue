
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
				<span class="bt_div_span" style="font-size: 25px">客户发展部水表口径下单量统计</span>
			</div>
			<!--<div class="bt_div2" style="">
				<el-form  style="margin-top: 10px;" >
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
			<HotTable :root="root" :settings="hotSettings" ></HotTable>
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
						['口径','', '15mm', '20mm','25mm','40mm','50mm','80mm','100mm','150mm','200mm','250mm','300mm','400mm','合计'],
						['分类','', '(只)', '(只)','(只)','(只)','(只)','(只)','(只)','(只)','(只)','(只)','(只)','(只)','合计'],
						['本期总计','下单量', '', '','','','','','','','','','','',''],
						['本期总计','环比', '', '','','','','','','','','','','',''],
						['本期总计','同比增长', '', '','','','','','','','','','','',''],
						['本期总计','同比', '', '','','','','','','','','','','',''],
						['本期总计','期末总量', '', '','','','','','','','','','','',''],
						['工商类','下单量', '', '','','','','','','','','','','',''],
						['工商类','环比', '', '','','','','','','','','','','',''],
						['工商类','同比增长', '', '','','','','','','','','','','',''],
						['工商类','同比', '', '','','','','','','','','','','',''],
						['工商类','期末总量', '', '','','','','','','','','','','',''],
						['居民类','下单量', '', '','','','','','','','','','','',''],
						['','环比', '', '','','','','','','','','','','',''],
						['','同比增长', '', '','','','','','','','','','','',''],
						['','同比', '', '','','','','','','','','','','',''],
						['','期末总量', '', '','','','','','','','','','','',''],
						['出户类','下单量', '', '','','','','','','','','','','',''],
						['','环比', '', '','','','','','','','','','','',''],
						['','同比增长', '', '','','','','','','','','','','',''],
						['','同比', '', '','','','','','','','','','','',''],
						['','期末总量', '', '','','','','','','','','','','',''],

					],
					startRows: 11,//行列范围
					startCols: 6,
					minRows: 5,  //最小行列
					minCols: 5,
					maxRows: 30,  //最大行列
					maxCols: 30,
					rowHeaders: true,//行表头
					colHeaders:   false,//自定义列表头or 布尔值
					height:550,
					// minSpareCols: 2, //列留白
					// minSpareRows: 2,//行留白
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
						{row: 0, col: 0, rowspan: 1, colspan: 2},  //指定合并，从（1,1）开始行3列3合并成一格
						{row: 1, col: 0, rowspan: 1, colspan: 2},
						{row: 2, col: 0, rowspan: 5, colspan: 1},
						{row: 7, col: 0, rowspan: 5, colspan: 1},
						{row: 12, col: 0, rowspan: 5, colspan: 1},
						{row: 17, col: 0, rowspan: 5, colspan: 1},
						{row: 0, col: 14, rowspan: 2, colspan: 1},

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
						}
					],
					manualColumnFreeze: true, //手动固定列
					manualColumnResize: true,//手工更改列距
					manualRowResize: true,//手动更改行距
					comments: true, //添加注释
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

</style>