<template>
	<section>
		<el-row style="margin-top: 15px;">
			<div class="text item">
				<el-button style="margin-bottom: 10px;" size="mini" type="primary" @click="fmpzAdd_vue()" v-if="isReadonly != '1'"><i class="iconfont icon-xinzeng" style="font-size: 12px"></i>新增</el-button>
				<el-table :data="Data"style="width: 100%" :stripe="true">
					<el-table-column type="index" label="序号" width="80" align="center">
					</el-table-column>
					<el-table-column
							prop="fmbh"
							label="阀门编号"
							align="center"
							width="150">
					</el-table-column>
					<el-table-column
							prop="azwz"
							label="安装位置"
							align="center">
					</el-table-column>
					<el-table-column
							prop="azrq_vue"
							label="安装日期"
							align="center"
							width="180">
					</el-table-column>
					<el-table-column
							prop="fmkj"
							label="阀门口径"
							align="center">
					</el-table-column>
					<el-table-column
							prop="fmlx"
							label="阀门类型"
							align="center">
					</el-table-column>
					<!--<el-table-column-->
							<!--prop="fmcj"-->
							<!--label="阀门厂家"-->
							<!--align="center">-->
					<!--</el-table-column>-->
					<el-table-column
							prop="fmxh"
							label="阀门型号"
							align="center">
					</el-table-column>
					<!--<el-table-column-->
							<!--prop="fgfs"-->
							<!--label="阀杆方身"-->
							<!--align="center">-->
					<!--</el-table-column>-->
					<!--<el-table-column-->
							<!--prop="fgsd"-->
							<!--label="阀杆深度"-->
							<!--align="center">-->
					<!--</el-table-column>-->
					<!--<el-table-column-->
						<!--prop="fgcz"-->
						<!--label="阀杆材质"-->
						<!--align="center">-->
					<!--</el-table-column>-->
					<!--<el-table-column-->
							<!--prop="zdfs"-->
							<!--label="转动方式"-->
							<!--align="center">-->
					<!--</el-table-column>-->
					<!--<el-table-column-->
							<!--prop="zdfx"-->
							<!--label="转动方向"-->
							<!--align="center">-->
					<!--</el-table-column>-->
					<!--<el-table-column-->
							<!--prop="qbzs"-->
							<!--label="启闭转数"-->
							<!--align="center">-->
					<!--</el-table-column>-->
					<!--<el-table-column-->
							<!--prop="fmzy"-->
							<!--label="阀门作用"-->
							<!--align="center">-->
					<!--</el-table-column>-->
					<!--<el-table-column-->
						<!--prop="jgcz"-->
						<!--label="阀门盖材质"-->
						<!--align="center"-->
					<!--width="100">-->
					<!--</el-table-column>-->
					<!--<el-table-column-->
							<!--prop="jgxs"-->
							<!--label="阀门盖形式"-->
							<!--align="center"-->
					<!--width="100">-->
					<!--</el-table-column>-->
					<el-table-column
							prop="gps"
							label="GPS数据"
							align="center">
					</el-table-column>
					<el-table-column
							prop="czsj_vue"
							label="操作时间"
							align="center"
					width="180">
					</el-table-column>
					<el-table-column
							prop="czrname"
							label="操作人"
							align="center"
							width="180">
					</el-table-column>
					<el-table-column
							prop="bz"
							label="备注"
							align="center">
					</el-table-column>
					<el-table-column
							prop="yzdzj"
							label="已制点志记"
							align="center"
							width="120"
							:formatter="yzdzjFormatter">
					</el-table-column>
					<el-table-column  label="操作" align="center"width="200" fixed="right">
						<template slot-scope="scope">
							<label title="查看" v-if="isReadonly == '1'"><i @click="fmpzHandle_vue(scope.$index, scope.row)" class="iconfont icon-yulan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
							<label title="编辑" v-if="isReadonly != '1'"><i @click="fmpzHandle_vue(scope.$index, scope.row)" class="iconfont icon-ai-edit" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
							<label title="删除" v-if="isReadonly != '1'"><i @click="fmpzDelete_vue(scope.$index, scope.row)" class="iconfont delete" style="font-size:18px;cursor:pointer;padding: 2px;color: #F56C6C"></i></label>

							<!--<el-button v-if="isReadonly != '1'"-->
									<!--size="mini"-->
									<!--@click="fmpzHandle_vue(scope.$index, scope.row)">编辑</el-button>-->
							<!--<el-button v-if="isReadonly != '1'"-->
									<!--size="mini"-->
									<!--type="danger"-->
									<!--@click="fmpzDelete_vue(scope.$index, scope.row)">删除</el-button>-->
							<!--<el-button v-if="isReadonly == '1'"-->
									   <!--size="mini"-->
									   <!--@click="fmpzHandle_vue(scope.$index, scope.row)">查看</el-button>-->
						</template>
					</el-table-column>
				</el-table>
				<el-col :span="24" class="toolbar" >
					<!--工具条-->
					<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[5,10,20,50]"
								   @current-change="handleCurrentChange" @size-change="handleSizeChange":current-page.sync="this.page"
								   :page-size="this.size" style="float:right;" :total="this.total">
					</el-pagination>
				</el-col>
			</div>
		</el-row>
		<el-dialog title="阀门信息录入"  :visible.sync="fmpzDialogFormVisible" width="50%" top="2vh" :append-to-body="true" :close-on-click-modal="false" >
			<fmgl :id="fmid" :xmid="xmid" :isReadonly="isReadonly" v-if="fmpzDialogFormVisible" @childConcer="closeFmDialog"></fmgl>
		</el-dialog>

	</section>
</template>
<script>
	import fmgl from '@/views/yj/bwyj/dzjnrsjgl/fmxxwh.vue';
	import { getFmData,fmxxwh,getSb,getFmkj,getFmlx,getFmzy,getZdfs,getZdfx,getfjgcz,getFjgxs,fmpzHandle,fmpzDelete,getFgcz } from '../../../../api/bwyj';
	export default {
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
		components: {
			'fmgl': fmgl
		},
		data() {
			return {
				fmglVisible:false,
				fmid:"",
				fjnumber:0,
				total: 0,//表格数据总数量
				page: 1,//当前页码
				size: 10,//每页容量
				//xmid:"1",
				Data:[],
				SbData:[],
				fmkjbhOptions:[],
				fmzybhOptions:[],
				zdfsbhOptions:[],
				fjgczbhOptions:[],
				fmlxbhOptions:[],
				fjgxsbhOptions:[],
				zdfxbhOptions:[],
				fgczbhOptions:[],
				ztOptions:[{
					codevalue: '0',
					codename: '正在制单'
				},{
					codevalue: '1',
					codename: '准备移交'
				},{
					codevalue: '2',
					codename: '单据已传'
				},{
					codevalue: '3',
					codename: '作废终止'
				},{
					codevalue: '4',
					codename: '交接完成'
				},{
					codevalue: '5',
					codename: '区所已收'
				}],
				fmdzjDialogFormVisible:false,
				SbDialogFormVisible:false,
				fmpzDialogFormVisible:false,

				fmForm:{
					sbid:"",
					id:"",
					fmbh:"",
					azwz:"",
					fmcj:"",
					fmkjbh:"",
					bz:"",
					fmzybh:"",
					zdfsbh:"",
					qbzs:"",
					fgfs:"",
					fjgczbh:"",
					gps:"",
					sbbzh:"",
					azrq_vue:"",
					fmlxbh:"",
					fmxh:"",
					zdfxbh:"",
					fgczbh:"",
					fgsd:"",
					fjgxsbh:""
				}
			}
		},
		methods: {
			//转换已制点志记格式
			yzdzjFormatter(row, column){
				if(row.yzdzj){
					return "是";
				}else{
					return "否";
				}
			},
			//监听子组件
			closeFmDialog(result){
					this.getFmData_vue();
					this.fmpzDialogFormVisible = false;
			},
			bzhyflFormatter(row, column){
				switch (row.bzhyfl) {
					case 'HB':
						return "户表"
						break;
					case 'WJJLB':
						return "五级计量表"
						break;
					case 'DB':
						return "地表"
						break;
					default:
						break;
				}
			},
			//获取阀门
			getFmData_vue() {
				console.log(this.isReadonly)
				let params = {
					page: this.page,
					size: this.size,
					xmid: this.xmid
				}
				getFmData(params).then((res) => {
					if (res.data != null) {
						this.Data = res.data.row
						this.total = res.data.total
					}
				})
			},
			handleCurrentChange(val){
					this.page = val;
					this.getFmData_vue();
			},
			handleSizeChange(val) {
				this.size = val;
				this.page=1;
				this.getFmData_vue();
			},
			CurrentChange(val){
				this.page = val;
				this.openSb_vue();
			},
			//清空阀门数据
			claerFb(){
				this.fmForm={
					sbid:"",
					id:"",
					fmbh:"",
					azwz:"",
					fmcj:"",
					fmkjbh:"",
					bz:"",
					fmzybh:"",
					zdfsbh:"",
					qbzs:"",
					fgfs:"",
					fjgczbh:"",
					gps:"",
					sbbzh:"",
					azrq_vue:"",
					fmlxbh:"",
					fmxh:"",
					zdfxbh:"",
					fgczbh:"",
					fgsd:"",
					fjgxsbh:""
				}
			},
			//新增阀门
			fmpzAdd_vue() {
				this.fmid="";
				this.fmpzDialogFormVisible=true;

				// this.claerFb();
				// this.fmpzDialogFormVisible = true;
			},
			//阀门参数验证
			checkFmParams() {
				if (this.fmForm.azwz == "") {
					this.$message.error('安装位置不可为空！');
					return false;
				}
				if (this.fmForm.fmcj == "") {
					this.$message.error('阀门厂家不可为空！');
					return false;
				}
				if (this.fmForm.fmkjbh == "") {
					this.$message.error('阀门口径不可为空！');
					return false;
				}
				if (this.fmForm.fmzybh == "") {
					this.$message.error('阀门作用不可为空！');
					return false;
				}
				if (this.fmForm.zdfsbh == "") {
					this.$message.error('转动方式不可为空！');
					return false;
				}
				if (this.fmForm.qbzs == "") {
					this.$message.error('启闭转数不可为空！');
					return false;
				}
				if (this.fmForm.fgfs == "") {
					this.$message.error('阀杆方身不可为空！');
					return false;
				}
				if (this.fmForm.fjgczbh == "") {
					this.$message.error('阀井盖材质不可为空！');
					return false;
				}
				if (this.fmForm.sbbzh == "") {
					this.$message.error('水表报装号不可为空！');
					return false;
				}
				if (this.fmForm.azrq_vue == "") {
					this.$message.error('安装日期不可为空！');
					return false;
				}
				if (this.fmForm.fmlxbh == "") {
					this.$message.error('阀门类型不可为空！');
					return false;
				}
				if (this.fmForm.fmxh == "") {
					this.$message.error('阀门型号不可为空！');
					return false;
				}
				if (this.fmForm.zdfxbh == "") {
					this.$message.error('转动方向不可为空！');
					return false;
				}
				if (this.fmForm.fgczbh == "") {
					this.$message.error('阀杆材质不可为空！');
					return false;
				}

				if (this.fmForm.fgsd == "") {
					this.$message.error('阀杆深度不可为空！');
					return false;
				}
				if (this.fmForm.fjgxsbh == "") {
					this.$message.error('阀井盖形式不可为空！');
					return false;
				}
				if (this.fmForm.gps == "") {
					this.$message.error('GPS数据不可为空！');
					return false;
				}
				return true;
			},
			//阀门数据回显
			fmpzHandle_vue(index, row) {
				this.fmid=row.id;
				this.fmpzDialogFormVisible=true;
				// let params = {
				// 	id: row.id
				// }
				// fmpzHandle(params).then((res) => {
				// 	if (res.data != null) {
				// 		this.fmForm = res.data.row[0]
				// 		this.fmpzDialogFormVisible = true
				// 	}
				// })
			},
			//删除阀门
			fmpzDelete_vue(index, row) {
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					let params = {
						id: row.id
					}
					fmpzDelete(params).then((res) => {
						if (res.data) {
							this.getFmData_vue();
						}
					})
				})
			},
			//阀门信息维护
			fmxxwh_vue(){
				let checkFmParams = this.checkFmParams();
				if(!checkFmParams){
					return;
				}
				let param={
					xmid:this.xmid
				}
				let params = Object.assign(param,this.fmForm);
				fmxxwh(params).then((res)=>{
					if(res.data){
						this.getFmData_vue();
						this.fmpzDialogFormVisible=false;
					}else{
						this.$message.error('保存失败！');
					}
				})
			},
			//获取水表
			openSb_vue(){
				let params={
					page: this.page,
					size: this.size,
					xmId:this.xmid
				}
				getSb(params).then((res)=>{
					if(res.data!=null){
						this.SbData=res.data.row;

					}
					this.SbDialogFormVisible=true;
				})
			},
			//选择水表
			chooseSb_vue(index, row){
				this.fmForm.sbbzh = row.hh;
				this.fmForm.sbid = row.id;
				this.SbDialogFormVisible=false;
			},
		//	获取阀门口径
			getFmkj_vue(){
				let params={}
				getFmkj(params).then((res)=>{
					if(res.data!=null){
						this.fmkjbhOptions=res.data.row;
					}
				})
			},
		//	获取阀门类型
			getFmlx_vue(){
				let params={}
				getFmlx(params).then((res)=>{
					if(res.data!=null){
						this.fmlxbhOptions=res.data.row;
					}
				})
			},
		//	阀门作用
			getFmzy_vue(){
				let params={}
				getFmzy(params).then((res)=>{
					if(res.data!=null){
						this.fmzybhOptions=res.data.row;
					}
				})
			},
		//	转动方式
			getZdfs_vue(){
				let params={}
				getZdfs(params).then((res)=>{
					if(res.data!=null){
						this.zdfsbhOptions=res.data.row;
					}
				})
			},
		//	转动方向
			getZdfx_vue(){
				let params={}
				getZdfx(params).then((res)=>{
					if(res.data!=null){
						this.zdfxbhOptions=res.data.row;
					}
				})
			},
		//	阀井盖材质
			getfjgcz_vue(){
				let params={}
				getfjgcz(params).then((res)=>{
					if(res.data!=null){
						this.fjgczbhOptions=res.data.row;
					}
				})
			},
		//	阀井盖形式
			getFjgxs_vue(){
				let params={}
				getFjgxs(params).then((res)=>{
					if(res.data!=null){
						this.fjgxsbhOptions=res.data.row;
					}
				})
			},
			//	阀杆材质
			getFgcz_vue(){
				let params={}
				getFgcz(params).then((res)=>{
					if(res.data!=null){
						this.fgczbhOptions=res.data.row;
					}
				})
			},
		//	打开点志记管理
			opendzjgl_vue(index,row){

				this.fmdzjForm=row;
				this.fmdzjDialogFormVisible = true;
			}
		},
		mounted() {
			this.getFmData_vue();
			this.getFmkj_vue();
			this.getFmlx_vue();
			this.getFmzy_vue();
			this.getZdfs_vue();
			this.getZdfx_vue();
			this.getfjgcz_vue();
			this.getFjgxs_vue();
			this.getFgcz_vue();
		}
	}
</script>

<style>

	.el-dialog__body {
		padding: 0 20px;
		color: #606266;
		font-size: 14px;
	}
	.el-table td, .el-table th {
		padding: 6px 0;
		min-width: 0;
		-webkit-box-sizing: border-box;
		box-sizing: border-box;
		text-overflow: ellipsis;
		vertical-align: middle;
		position: relative;
		text-align: left;
	}
	.el-input__inner{
		height: 40px;
	}
</style>