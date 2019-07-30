<template>
	<section>
		<el-row>
			<el-col :span="24" class="toolbar" >
				<el-row style="text-align: right;margin-bottom: 10px" v-if="isReadonly != '1'">
					<el-button type="primary"size="mini" @click="openSbsxdzj_vue()"><i class="iconfont icon-xinzeng" style="font-size: 12px"></i>新增水表三箱</el-button>
					<el-button type="primary"size="mini" @click="openFmdzj_vue()"><i class="iconfont icon-xinzeng" style="font-size: 12px"></i>新增阀门点志记</el-button>
					<el-button type="primary"size="mini" @click="openXhsdzj_vue()"><i class="iconfont icon-xinzeng" style="font-size: 12px"></i>新增消火栓点志记</el-button>
					<el-button type="primary"size="mini" @click="openGclgdzj_vue()"><i class="iconfont icon-xinzeng" style="font-size: 12px"></i>新增高层立管点志记</el-button>
				</el-row>
				<!--列表-->
				<el-table :data="row" highlight-current-row v-loading="listLoading" :stripe="true" style="width: 100%;">
					<el-table-column type="index" label="序号" width="80" align="center">
					</el-table-column>
					<el-table-column prop="dzjbh" label="点志记编号" width="150" align="center">
					</el-table-column>
					<el-table-column prop="dzjlxbhname" label="点志记类型" width="150" align="center">
					</el-table-column>
					<el-table-column prop="dzjmc" label="点志记名称" width="150" header-align="center" align="left">
					</el-table-column>
					<el-table-column property="lm" label="路名" width="100" header-align="center" align="left"></el-table-column>
					<el-table-column prop="azrq_vue" label="安装日期" width="150" align="center">
					</el-table-column>
					<el-table-column prop="czrq_vue" label="操作时间" width="150" align="center">
					</el-table-column>
					<el-table-column prop="czr" label="操作人" width="130" align="center">
					</el-table-column>
					<el-table-column prop="htr" label="绘图人" width="130" align="center">
					</el-table-column>
					<el-table-column prop="zt" label="状态" width="100" align="center" :formatter="ztFormatter">
					</el-table-column>
					<el-table-column label="操作" width="150" fixed="right" align="center">
						<template slot-scope="scope">
							<label title="查看" v-if="isReadonly == '1'"><i @click="dzjHandleShow_vue(scope.$index, scope.row)" class="iconfont icon-yulan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
							<label title="编辑" v-if="isReadonly != '1'"><i @click="dzjHandleEdit_vue(scope.$index, scope.row)" class="iconfont icon-ai-edit" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
							<label title="删除" v-if="isReadonly != '1'"><i @click="dzjHandleDelete_vue(scope.$index, scope.row)" class="iconfont delete" style="font-size:18px;cursor:pointer;padding: 2px;color: #F56C6C"></i></label>
						</template>
					</el-table-column>
				</el-table>
			</el-col>
			<el-col :span="24" class="toolbar" >
				<!--工具条-->
				<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[5,10,20,50]"
							   @current-change="handleCurrentChange" @size-change="handleSizeChange":current-page.sync="this.page"
							   :page-size="this.size" style="float:right;" :total="this.total">
				</el-pagination>

			</el-col>
		</el-row>
		<el-dialog title="阀门点志记"  :visible.sync="fmdzjDialogFormVisible" width="50%" :append-to-body="true" :close-on-click-modal="false">
			<fmdzj :id="dzjid" :xmid="xmid" v-if="fmdzjDialogFormVisible" @childConcer="closeFmDialog"></fmdzj>
		</el-dialog>
		<el-dialog title="消火栓点志记"  :visible.sync="xhsdzjDialogFormVisible" width="50%" :append-to-body="true" :close-on-click-modal="false">
			<xhsdzj :dzjid="dzjid" :xmid="xmid" v-if="xhsdzjDialogFormVisible" @childConcer="closeFmDialog"></xhsdzj>
		</el-dialog>
		<el-dialog title="高层立管点志记"  :visible.sync="gclgdzjDialogFormVisible" width="50%" :append-to-body="true" :close-on-click-modal="false">
			<gclgdzj :dzjid="dzjid" :xmid="xmid" v-if="gclgdzjDialogFormVisible" @childConcer="closeFmDialog"></gclgdzj>
		</el-dialog>
		<el-dialog title="水表三箱点志记"  :visible.sync="sbsxdzjDialogFormVisible" width="60%" :append-to-body="true" :close-on-click-modal="false">
			<sbsxdzj :dzjid="dzjid" :xmid="xmid" v-if="sbsxdzjDialogFormVisible" @childConcer="closeSbsxDialog"></sbsxdzj>
		</el-dialog>

		<!--查看 readonly-->
		<el-dialog title="阀门点志记"  :visible.sync="fmdzjDialogFormVisibleShow" width="50%" :append-to-body="true" :close-on-click-modal="false">
			<fmdzjShow :id="dzjid" :xmid="xmid" v-if="fmdzjDialogFormVisibleShow" @childConcer="closeFmDialog"></fmdzjShow>
		</el-dialog>
		<el-dialog title="消火栓点志记"  :visible.sync="xhsdzjDialogFormVisibleShow" width="50%" :append-to-body="true" :close-on-click-modal="false">
			<xhsdzjShow :dzjid="dzjid" :xmid="xmid" v-if="xhsdzjDialogFormVisibleShow" @childConcer="closeFmDialog"></xhsdzjShow>
		</el-dialog>
		<el-dialog title="高层立管点志记"  :visible.sync="gclgdzjDialogFormVisibleShow" width="50%" :append-to-body="true" :close-on-click-modal="false">
			<gclgdzjShow :dzjid="dzjid" :xmid="xmid" v-if="gclgdzjDialogFormVisibleShow" @childConcer="closeFmDialog"></gclgdzjShow>
		</el-dialog>
		<el-dialog title="水表三箱点志记"  :visible.sync="sbsxdzjDialogFormVisibleShow" width="50%" :append-to-body="true" :close-on-click-modal="false">
			<sbsxdzjShow :dzjid="dzjid" :xmid="xmid" v-if="sbsxdzjDialogFormVisibleShow" @childConcer="closeSbsxDialog"></sbsxdzjShow>
		</el-dialog>
	</section>
</template>

<script>

    import xhsdzj from '@/views/yj/bwyj/dzjnrsjgl/addxhs.vue';
	import fmdzj from '@/views/yj/bwyj/dzjnrsjgl/fmdzj.vue';
	import sbsxdzj from '@/views/yj/bwyj/dzjnrsjgl/sbsxdzj.vue';
	import gclgdzj from '@/views/yj/bwyj/dzjnrsjgl/gclgdzj.vue';

	import xhsdzjShow from '@/views/yj/bwyj/dzjnrsjgl/showAddxhs.vue';
	import fmdzjShow from '@/views/yj/bwyj/dzjnrsjgl/showFmdzj.vue';
	import sbsxdzjShow from '@/views/yj/bwyj/dzjnrsjgl/showSbsxdzj.vue';
	import gclgdzjShow from '@/views/yj/bwyj/dzjnrsjgl/showGclgdzj.vue';

	import { getDzjlb,dzjHandleDelete } from '../../../../api/bwyj';
	export default {
		components: {
			'fmdzj': fmdzj,  //将别名demo 变成 组件 Demo
			'xhsdzj':xhsdzj,
			'gclgdzj':gclgdzj,
			'sbsxdzj' : sbsxdzj,
			'xhsdzjShow' : xhsdzjShow,
			'fmdzjShow' : fmdzjShow,
			'sbsxdzjShow' : sbsxdzjShow,
			'gclgdzjShow' : gclgdzjShow,
		},
		props:{
			xmid:{
				type: String,
				required: true
			},
			isReadonly:{
				type: String,
				required: true
			}
		},
		data() {
			return {
				fmdzjDialogFormVisible:false,
                sbsxdzjDialogFormVisible:false,
                xhsdzjDialogFormVisible:false,
                gclgdzjDialogFormVisible:false,

				fmdzjDialogFormVisibleShow:false,
				sbsxdzjDialogFormVisibleShow:false,
				xhsdzjDialogFormVisibleShow:false,
				gclgdzjDialogFormVisibleShow:false,

				row: [],
				dzjid:"",
				total: 5,//表格数据总数量
				page: 1,//当前页码
				size: 5,//每页容量
				//xmid:"1",
				listLoading: false,
				fmdzjForm:{
					id:"",
					dzjbh:"",
					zt:"0",
					lm:"",
					fmbh:"",
					fmkj:"",
					fmlx:"",
					fmxh:"",
					fgfs:"",
					zdfs:"",
					fgsd:"",
					zdfx:"",
					fgcz:"",
					fmzy:"",
					azrq:"",
					htr:""
				},
			}
		},
		methods: {
			//状态格式化
			ztFormatter(row, column){
				switch (row.zt) {
					case "0":
						return "正在制单";
						break;
					case "1":
						return "准备移交";
						break;
					case "2":
						return "单据已传";
						break;
					case "3":
						return "作废终止";
						break;
					case "4":
						return "交接完成";
						break;
					case "5":
						return "区所已收";
						break;
				}
			},
			//监听阀门页面
			closeFmDialog(result){
				if(result){
					this.getDzjlb_vue();
					this.fmdzjDialogFormVisible=false;
					this.xhsdzjDialogFormVisible=false;
					this.gclgdzjDialogFormVisible=false;
				}
			},
			//监听水表三箱页面
			closeSbsxDialog(result){
				if(result){
					this.getDzjlb_vue();
					this.sbsxdzjDialogFormVisible=false;
				}
			},
			handleCurrentChange(val) {
				this.page = val;
				this.getDzjlb_vue();
			},
			handleSizeChange(val) {
				this.size = val;
				this.page=1;
				this.getDzjlb_vue();
			},
			//获取点志记列表
			getDzjlb_vue() {
				let params = {
					page: this.page,
					size: this.size,
					xmid:this.xmid
				};

				this.listLoading = true;
				getDzjlb(params).then((res) => {
					this.total = res.data.total;
					this.row = res.data.row;
					this.listLoading = false;
				});
			},
			//打开水表三箱新增页面
			openSbsxdzj_vue(){
				this.dzjid="";
				this.sbsxdzjDialogFormVisible=true;
			},
			//打开阀门新增页面
			openFmdzj_vue(){
				this.dzjid="";
				this.fmdzjDialogFormVisible=true;
			},
            //打开消火栓新增页面
            openXhsdzj_vue(){
                this.dzjid="";
                this.xhsdzjDialogFormVisible=true;
			},
			//高层立管
			openGclgdzj_vue(){
				this.dzjid="";
				this.gclgdzjDialogFormVisible=true;
			},
			//打开阀门编辑页面
			dzjHandleEdit_vue(index,row) {
				this.dzjid = row.id;
				switch (row.dzjlxbh) {
					case "01":
						this.sbsxdzjDialogFormVisible = true;
						break;
					case "02":
						this.fmdzjDialogFormVisible = true;
						break;
					case "03":
						this.xhsdzjDialogFormVisible = true;
						break;
					case "04":
						this.gclgdzjDialogFormVisible = true;
						break;
					default:
						break;
				}
			},
			dzjHandleShow_vue(index,row){
				this.dzjid = row.id;
				switch (row.dzjlxbh) {
					case "01":
						this.sbsxdzjDialogFormVisibleShow = true;
						break;
					case "02":
						this.fmdzjDialogFormVisibleShow = true;
						break;
					case "03":
						this.xhsdzjDialogFormVisibleShow = true;
						break;
					case "04":
						this.gclgdzjDialogFormVisibleShow = true;
						break;
					default:
						break;
				}
			},
		//	删除点志记
			dzjHandleDelete_vue(index,row){
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					let params = {
						id:row.id,
						zt:"3"
					}
					dzjHandleDelete(params).then((res)=>{
						if(res.data){
							this.$message.success('删除成功！');
							this.getDzjlb_vue();
						}else{
							this.$message.error('删除失败！');
						}
					})
				})

			}
		},
		mounted() {
			this.getDzjlb_vue();
		}
	}

</script>
<style>
	.el-button--medium {
		font-size: 12px;
	}
</style>