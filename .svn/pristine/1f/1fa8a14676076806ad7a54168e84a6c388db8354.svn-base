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
				<div align="center" >
					<span class="bt_div_span" style="font-size: 25px;">用户投资类业务 月度主要指标统计分析一览表</span>
				</div>

			</div>
		<div id="hotTable" class="hotTable">
			<HotTable  ref="testHot" :settings="hotSettings"></HotTable>
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

					data: [        //数据可以是二维数组，也可以是数组对象

						['指标名称', '指标名称', '单位', '本期', '', '', '', '本年合计','往年合计'],

						['指标名称', '指标名称', '单位', '工商类', '居民类', '改造类', '合计', '本年合计','往年合计'],

						['本期受理', '受理数', '个', '', '', '', '', '',''],

						['本期受理', '户数', '户', '', '', '', '', '',''],

						['勘查完成', '勘查完成', '个', '', '', '', '', '',''],

						['项目立项', '项目数', '个', '', '', '', '', '',''],

						['项目立项', '户数', '户', '', '', '', '', '',''],

						['项目立项', '户数同比增长', '%', '', '', '', '', '',''],

						['设计完成', '设计完成', '个', '', '', '', '', '',''],

						['预算完成', '笔数', '笔', '', '', '', '', '',''],

						['预算完成', '金额', '万元', '', '', '', '', '',''],

						['合同签订', '份数', '笔', '', '', '', '', '',''],

						['合同签订', '户数', '户', '', '', '', '', '',''],

						['合同签订', '金额', '万元', '', '', '', '', '',''],

						['费用收取', '缴费到账数量', '笔数', '', '', '', '', '',''],

						['费用收取', '缴费到账金额', '万元', '', '', '', '', '',''],

						['费用收取', '开票数量', '笔数', '', '', '', '', '',''],

						['费用收取', '开票金额', '万元', '', '', '', '', '',''],

						['施工下单', '下单户数', '万元', '', '', '', '', '',''],

						['施工下单', '涉及项目数', '笔数', '', '', '', '', '',''],

						['施工下单', '户数同比增长/百分比', '户', '', '', '', '', '',''],

						['施工下单', '户数同比增长/百分比', '%', '', '', '', '', '',''],

						['安装通水', '通水户数', '户', '', '', '', '', '',''],

						['安装通水', '涉及项目数', '个', '', '', '', '', '',''],

						['综合验收', '验收户数', '户', '', '', '', '', '',''],

						['综合验收', '涉及项目数', '个', '', '', '', '', '',''],

						['用户结算', '笔数', '笔', '', '', '', '', '',''],

						['用户结算', '金额', '万元', '', '', '', '', '',''],

						['移交', '户数', '户', '', '', '', '', '',''],

						['移交', '涉及项目数', '个', '', '', '', '', '','']

					],


					startRows: 11,//行列范围

					startCols: 6,

					minRows: 5,  //最小行列

					minCols: 5,

					maxRows: 30,  //最大行列

					maxCols: 20,

					rowHeaders: true,//行表头，可以使布尔值（行序号），可以使字符串（左侧行表头相同显示内容，可以解析html），也可以是数组（左侧行表头单独显示内容）。

					// colHeaders:   [ '题目', 'A选项', 'B选项', 'C选项', 'D选项','答案'],//自定义列表头or 布尔值

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

							"remove_row": {

								name: '删除行',

							},

							"remove_col": {

								name: '删除列',

							},

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

						{row: 0, col: 0, rowspan: 2, colspan: 2},
						{row: 0, col: 2, rowspan: 2, colspan: 1},
						{row: 0, col: 3, rowspan: 1, colspan: 4},
						{row: 0, col: 7, rowspan: 2, colspan: 1},
						{row: 0, col: 8, rowspan: 2, colspan: 1},
						{row: 2, col: 0, rowspan: 2, colspan: 1},
						{row: 4, col: 0, rowspan: 1, colspan: 2},
						{row: 5, col: 0, rowspan: 3, colspan: 1},
						{row: 8, col: 0, rowspan: 1, colspan: 2},
						{row: 9, col: 0, rowspan: 2, colspan: 1},
						{row: 11, col: 0, rowspan: 3, colspan: 1},
						{row: 14, col: 0, rowspan: 4, colspan: 1},
						{row: 18, col: 0, rowspan: 4, colspan: 1},
						{row: 22, col: 0, rowspan: 2, colspan: 1},
						{row: 24, col: 0, rowspan: 2, colspan: 1},
						{row: 26, col: 0, rowspan: 2, colspan: 1},
						{row: 28, col: 0, rowspan: 2, colspan: 1}
						//指定合并，从（1,1）开始行3列3合并成一格

						// {row: 3, col: 4, rowspan: 2, colspan: 2}

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
						}
					],

					afterChange: function (changes, source) { //数据改变时触发此方法

						console.log(this.getData());

					},

					manualColumnFreeze: true, //手动固定列

					manualColumnMove: true, //手动移动列

					manualRowMove: true,   //手动移动行

					manualColumnResize: true,//手工更改列距

					manualRowResize: true,//手动更改行距

					comments: true, //添加注释

					// cell: [

					//   {row: 1, col: 1, comment: {value: 'this is test'}},

					// ],
					height:700,

					customBorders:[],//添加边框

					columnSorting: true,//排序

					stretchH: 'all',//根据宽度横向扩展，last:只扩展最后一列，none：默认不扩展

				}

			};

		},

		name: 'handsonTable',

		components: {

			HotTable

		},

		methods: {

			saveData (){

				var examData = this.$refs.testHot.table.getData(); //这里要注意，如果使用this.hotSettings.data 保存表格数据，拖拽完以后数据的顺序将不会更新，因此使用 this.$refs.testHot.table.getData(); 来获取数据，获取的数据格式为二维数组。

				console.log(examData );

			}

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