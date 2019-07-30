<template>
	<section>
		<el-dialog  title="阀门信息"  :visible.sync="fmDialogFormVisible" width="80%" :append-to-body="true" :close-on-click-modal="false">
			<el-table :data="Data" style="width: 100%" border :row-class-name="fmtableRowClassName">
				<el-table-column type="index" label="序号" width="80" align="center">
				</el-table-column>
				<el-table-column
						prop="fmbh"
						label="阀门编号"
						align="center"
						width="150">
				</el-table-column>
				<el-table-column
						prop="azrq_vue"
						label="安装日期"
						align="center"
						width="100">
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
				<el-table-column
						prop="fmcj"
						label="阀门厂家"
						align="center">
				</el-table-column>
				<el-table-column
						prop="fmxh"
						label="阀门型号"
						align="center">
				</el-table-column>
				<el-table-column
						prop="czsj_vue"
						label="操作时间"
						align="center"
						width="100">
				</el-table-column>
				<el-table-column
						prop="czrname"
						label="操作人"
						align="center">
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
				<el-table-column  label="操作" align="center"width="150" fixed="right">
					<template slot-scope="scope">
						<label title="选择" ><i @click="chooseFm(scope.$index, scope.row)" class="iconfont icon-xuanze" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
					</template>
				</el-table-column>
			</el-table>
			<el-row>
				<!--工具条-->
				<!--<el-pagination layout="total, prev, pager, next"  @current-change="handleCurrentChange" :page-size="this.size" :total="this.total" style="float:right;">-->
				<!--</el-pagination>-->
				<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[10, 15, 30, 50,100]"
							   @current-change="handleCurrentChange" @size-change="handleSizeChange":current-page.sync="this.page"
							   :page-size="this.size" style="float:right;" :total="this.total">
				</el-pagination>
			</el-row>
		</el-dialog>
		<el-dialog title="阀门信息录入"  :visible.sync="fmpzDialogFormVisible" width="50%" :append-to-body="true" :close-on-click-modal="false">
			<fmgl :id="fmid" :xmid="xmid"  @childConcer="closeFmDialog"></fmgl>
		</el-dialog>
		<el-dialog title="选择水表信息" :visible.sync="SbDialogFormVisible" width="80%" :append-to-body="true" :close-on-click-modal="false">
			<el-table :data="SbData"style="width: 100%" border :row-class-name="tableRowClassName">
				<el-table-column type="index" label="序号" width="80" align="center">
				</el-table-column>
				<el-table-column
						prop="hhlxbh"
						label="户号类型"
						align="center"
						:formatter="bzhyflFormatter">
				</el-table-column>
				<el-table-column
						prop="hh"
						label="报装号"
						align="center"
						width="200">
				</el-table-column>
				<el-table-column
						prop="hyflname"
						label="报装行业分类"
						align="center">
				</el-table-column>
				<el-table-column
						prop="yshyfl"
						label="验收行业分类"
						align="center">
				</el-table-column>
				<el-table-column
						prop="azwz"
						label="装表地址"
						align="center">
				</el-table-column>
				<el-table-column
						prop="sgdwSbkjName"
						label="装表口径"
						align="center">
				</el-table-column>
				<el-table-column
						prop="yyzxBsm"
						label="验收表身码"
						align="center">
				</el-table-column>
				<el-table-column
						prop="yyzxHh"
						label="立户号"
						align="center">
				</el-table-column>
				<el-table-column  label="操作" align="center" fixed="right">
					<template slot-scope="scope">
						<label title="选择" ><i @click="chooseSb_vue(scope.$index, scope.row)" class="iconfont icon-xuanze" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
					</template>
				</el-table-column>

			</el-table>
			<!--工具条-->
			<el-row>
				<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[5,10,20,50]"
							   @current-change="CurrentChange" @size-change="SizeChange"
							   :page-size="this.size" style="float:right;" :total="this.total">
				</el-pagination>
			</el-row>
		</el-dialog>
		<el-form  :model="sbsxdzjForm" ref="sbsxdzjForm" :rules="rules">
			<el-input style="display: none" v-model="sbsxdzjForm.id"  ></el-input>
			<el-form-item>
				<el-col :span="3" style="text-align: right">
					<span>点志记编号&nbsp;&nbsp;</span>
				</el-col>
				<el-col :span="9">
					<el-input readonly v-model="sbsxdzjForm.dzjbh" v-if="sbsxdzjForm.dzjbh!=null&&sbsxdzjForm.dzjbh!=''" size="small"></el-input>
					<el-input readonly v-else value="保存后自动生成" size="small"></el-input>
				</el-col>
				<el-col :span="5"style="text-align: right" >
					<span>点志记状态&nbsp;&nbsp;</span>
				</el-col>
				<el-col :span="7">
					<label v-if="sbsxdzjForm.zt=='0'">正在制单</label>
					<label v-if="sbsxdzjForm.zt=='1'">准备移交</label>
					<label v-if="sbsxdzjForm.zt=='2'">单据已传</label>
					<label v-if="sbsxdzjForm.zt=='3'">作废终止</label>
					<label v-if="sbsxdzjForm.zt=='4'">交接完成</label>
					<label v-if="sbsxdzjForm.zt=='5'">区所已收</label>
				</el-col>
			</el-form-item>

			<el-form-item>
				<el-col :span="3" style="text-align: right">
					<span>路名&nbsp;&nbsp;</span>
				</el-col>
				<el-col :span="21">
					<el-form-item prop="lm">
						<el-input v-model="sbsxdzjForm.lm" size="small"></el-input></el-form-item>
				</el-col>
			</el-form-item>

			<el-form-item>
				<el-col :span="3" style="text-align: right">
					<span>接管名称&nbsp;&nbsp;</span>
				</el-col>
				<el-col :span="5">
					<el-form-item prop="jgmc">
					<el-select v-model="sbsxdzjForm.jgmc" size="small">
						<el-option v-for="item in jgmcOptions"
								   :key="item.BH"
								   :label="item.MC"
								   :value="item.BH"></el-option>
					</el-select></el-form-item>
				</el-col>
				<el-col :span="4">(干管名称)</el-col>
				<el-col :span="5" style="text-align: right">
					<span>接管口径&nbsp;&nbsp;</span>
				</el-col>
				<el-col :span="4">
					<el-form-item prop="jgkj">
					<el-select v-model="sbsxdzjForm.jgkj" size="small">
						<el-option v-for="item in jgkjOptions"
								   :key="item.BH"
								   :label="item.MC"
								   :value="item.BH"></el-option>
					</el-select></el-form-item>
				</el-col>
				<el-col :span="3">mm(干管口径)</el-col>
			</el-form-item>

			<el-form-item>
				<el-col :span="3" style="text-align: right">
					<span>水表报装号&nbsp;&nbsp;</span>
				</el-col>
				<el-col :span="5">
					<el-form-item prop="sbbzh">
						<el-input readonly v-model="sbsxdzjForm.sbbzh"  size="small" ></el-input></el-form-item>
				</el-col>
				<el-col :span="4">
					<el-row>
						<el-col :span="12">
							<el-button style="margin-left:10px;" size="mini" type="primary" @click="dzjxz_vue"><i class="iconfont icon-xuanze" style="font-size: 12px"></i>选择</el-button>
						</el-col>
					</el-row>
				</el-col>
				<el-col :span="12" style="text-align: right">
					<span></span>
				</el-col>
			</el-form-item>

			<el-form-item>
				<el-col :span="3" style="text-align: right">
					<span>表前管材质&nbsp;&nbsp;</span>
				</el-col>
				<el-col :span="5">
					<el-form-item prop="bqgcz">
					<el-select v-model="sbsxdzjForm.bqgcz" size="small">
						<el-option v-for="item in bqgczOptions"
								   :key="item.BH"
								   :label="item.MC"
								   :value="item.BH"></el-option>
					</el-select></el-form-item>
				</el-col>
				<el-col :span="4">&nbsp;</el-col>
				<el-col :span="5" style="text-align: right">
					<span>表前管口径&nbsp;&nbsp;</span>
				</el-col>
				<el-col :span="4">
					<el-form-item prop="bqgcz">
					<el-select v-model="sbsxdzjForm.bqgkj" size="small">
						<el-option v-for="item in jgkjOptions"
								   :key="item.BH"
								   :label="item.MC"
								   :value="item.BH"></el-option>
					</el-select></el-form-item>
				</el-col>
				<el-col :span="3">mm</el-col>
			</el-form-item>

			<el-form-item v-if="1==2">
				<el-col :span="3" style="text-align: right">
					<span>消火栓编号&nbsp;&nbsp;</span>
				</el-col>
				<el-col :span="5">
					<el-input readonly v-model="xhsdzjForm.xhsbh"  size="small" ></el-input>
				</el-col>
				<el-col :span="4">
					<el-row>
						<el-col :span="10">
							<el-button style="margin-left:10px;" size="mini" type="primary" @click="dzjxz_vue"><i class="iconfont icon-xuanze" style="font-size: 12px"></i>选择</el-button>
						</el-col>
						<el-col :span="14">
							<el-button  v-if="xhsdzjForm.xhsbh!=''" style="margin-left:10px;" size="mini" type="primary" @click="xhspzHandle_vue"><i class="iconfont icon-ai-edit" style="font-size: 12px"></i>编辑</el-button>&nbsp;
						</el-col>
					</el-row>
				</el-col>
			</el-form-item>

			<el-form-item>
				<el-col :span="3" style="text-align: right">
					<span>表前阀编号&nbsp;&nbsp;</span>
				</el-col>
				<el-col :span="5">
					<el-form-item prop="bqfbh">
						<el-input readonly v-model="sbsxdzjForm.bqfbh"  size="small" ></el-input></el-form-item>
				</el-col>
				<el-col :span="5">
					<el-row>
						<el-row>
							<el-col :span="10">
								<el-button style="margin-left:10px;" size="mini" type="primary" @click="fmxz_vue('1')"><i class="iconfont icon-xuanze" style="font-size: 12px"></i>选择</el-button>
							</el-col>
							<el-col :span="14" v-if="sbsxdzjForm.bqfbh ==''">
								<el-button style="margin-left:10px;" size="mini" type="primary" @click="fmadd_vue('1')"><i class="iconfont icon-xinzeng" style="font-size: 12px"></i>新增</el-button>
							</el-col>
							<el-col :span="14" v-if="sbsxdzjForm.bqfbh!=''">
								<el-button  style="margin-left:10px;" size="mini" type="primary" @click="fmbj_vue('1')"><i class="iconfont icon-ai-edit" style="font-size: 12px"></i>编辑</el-button>&nbsp;
							</el-col>
						</el-row>
					</el-row>
				</el-col>
				<el-col :span="4" style="text-align: right">
					<span>阀表间距(前阀—表)&nbsp;</span>
				</el-col>
				<el-col :span="4">
					<el-form-item prop="fbjjqf">
						<el-input v-model="sbsxdzjForm.fbjjqf"  size="small" ></el-input></el-form-item>
				</el-col>
				<el-col :span="3">mm</el-col>
			</el-form-item>

			<el-form-item>
				<el-col :span="3" style="text-align: right">
					<span>表后阀编号&nbsp;&nbsp;</span>
				</el-col>
				<el-col :span="5">
					<el-form-item prop="bhfbh">
						<el-input readonly v-model="sbsxdzjForm.bhfbh"  size="small" ></el-input></el-form-item>
				</el-col>
				<el-col :span="5">
					<el-row>
						<el-col :span="10">
							<el-button style="margin-left:10px;" size="mini" type="primary" @click="fmxz_vue('2')"><i class="iconfont icon-xuanze" style="font-size: 12px"></i>选择</el-button>
						</el-col>
						<el-col :span="14" v-if="sbsxdzjForm.bhfbh ==''">
							<el-button style="margin-left:10px;" size="mini" type="primary" @click="fmadd_vue('2')"><i class="iconfont icon-xinzeng" style="font-size: 12px"></i>新增</el-button>
						</el-col>
						<el-col :span="14"  v-if="sbsxdzjForm.bhfbh!=''">
							<el-button style="margin-left:10px;" size="mini" type="primary" @click="fmbj_vue('2')"><i class="iconfont icon-ai-edit" style="font-size: 12px"></i>编辑</el-button>&nbsp;
						</el-col>
					</el-row>
				</el-col>
				<el-col :span="4" style="text-align: right">
					<span>阀表间距(表—后阀)&nbsp;</span>
				</el-col>
				<el-col :span="4">
					<el-form-item prop="fbjjhf">
						<el-input v-model="sbsxdzjForm.fbjjhf"  size="small" ></el-input></el-form-item>
				</el-col>
				<el-col :span="3">mm</el-col>
			</el-form-item>

			<el-form-item>
				<el-col :span="3" style="text-align: right">
					<span>绘图人&nbsp;&nbsp;</span>
				</el-col>
				<el-col :span="5">
					<el-input v-model="sbsxdzjForm.htr" placeholder="为空时保存当前登录人"  size="small"></el-input>
				</el-col>
				<el-col :span="4">
					<span>&nbsp;</span>
				</el-col>
				<el-col :span="5" style="text-align: right">
					<span>安装日期&nbsp;&nbsp;</span>
				</el-col>
				<el-col :span="5">
					<el-form-item prop="azrq_vue" :rules="this.publicUtil.notNull">
					<el-date-picker size="small" :editable="false" type="date" placeholder="选择日期" v-model="sbsxdzjForm.azrq_vue"
									value-format="yyyy-MM-dd" format="yyyy-MM-dd"  style="width: 100%"></el-date-picker></el-form-item>
				</el-col>
			</el-form-item>

			<el-form-item>
				<el-col :span="3" style="text-align: right">
					<span>点志记插图&nbsp;&nbsp;</span>
				</el-col>
				<el-col :span="21">
					<el-upload
							class="upload-demo"
							ref="upload"
							action="#"
							:on-preview="handlePreview"
							:http-request="submitUpload"
							:on-remove="handleRemove"
							:on-change="handleChange"
							:file-list="fileList"
							:auto-upload="true"
							:show-file-list=false>
						<el-button slot="trigger" size="mini" type="success" ><i class="iconfont icon-shangchuan_f" ></i>上传</el-button>
						<!--<el-button style="margin-left: 10px;" size="small" type="primary" @click="submitUpload" icon="el-icon-upload">上传</el-button>-->
						<el-button size="mini" type="success" style="margin-left: 10px" @click="download(0)"><i class="iconfont icon-xiazai_f"></i>下载</el-button>
						<el-button size="mini" type="primary" style="margin-left: 10px" @click="download(1)"><i class="iconfont icon-yulan"></i>预览</el-button>
						<label>附件（{{fjnumber}}）份</label>
						<div slot="tip" class="el-upload__tip"></div>
					</el-upload>
				</el-col>
			</el-form-item>
		</el-form>
		<div slot="footer" class="dialog-footer" style="text-align: center;padding-bottom: 10px">
			<el-button type="info" size="mini" @click="cancer"><i class="iconfont icon-quxiao"></i>取 消</el-button>
			<el-button type="primary" size="mini" @click="xhsdzjxxwh_vue('sbsxdzjForm')"><i class="iconfont icon-queren"></i>确 定</el-button>
		</div>



	</section>
</template>
<script>
	import { getSb,saveDzjsbsx,downloadSbsx,saveDzjSbsxfile,getOptions,getFmData,fmpzHandle,getDzjSbsxxx } from '../../../../api/bwyj';
	import fmgl from '@/views/yj/bwyj/dzjnrsjgl/fmxxwh.vue';
	export default {
		props:{
			xmid:{
				type: String,
				required: true
			},
			dzjid:{
				type: String,
				required: true
			}
		},
		components: {
			'fmgl': fmgl
		},
		data() {
			var checkNum = (rule, value, callback) => {
				//var reg =  /^[1-9]\d*$/;
				var reg = /^(?:0|[1-9]\d*)(?:\.\d*[1-9])?$/;
				if (!reg.test(value) && !value == '') {
					callback(new Error('该字段必须为数字'));
				} else {
					callback();
				}
			};
			return {
				rules:{
					lm:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					jgmc:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					jgkj:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					sbbzh:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					bqgcz:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					bqgkj:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					bqfbh:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					fbjjqf:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' },
						{ validator: checkNum, trigger: 'blur'}
					],
					bhfbh:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					fbjjhf:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' },
						{ validator: checkNum, trigger: 'blur'}
					],
					azrq_vue:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					]},
				name:"",
				path:'',//下载路径
				readonly:true,
				fmid:'',
				fmtype:'',//1表前阀  2表后阀
				fjnumber:0,
				total: 10,//表格数据总数量
				page: 1,//当前页码
				size: 10,//每页容量
				//xmid:'1',//项目id
				//dzjid:'',//点志记id
				sbid:'',//水表id
				bqfid:'',//表前阀id
				bhfid:'',//表后阀id
				Data:[],//阀门数据数据
				fileList:[],//文件列表
				filerow:[],//文件列表数据
				SbData:[],//水表列表数据
				jgmcOptions:[],//接管名称下拉数据
				jgkjOptions:[],//接管口径下拉数据
				bqgczOptions:[],//表前管材质下拉数据
				bqgkjOptions:[],//表前管口径下拉数据
				SbDialogFormVisible:false,//选择水表
				fmpzDialogFormVisible:false,//新增阀门编辑阀门
				fmDialogFormVisible:false,//选择阀门
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
				sbsxdzjForm:{
					id:"",
					dzjbh:"",
					dzjlxbh:"01",
					zt:"0",
					lm:"",
					jgmc:"",
					jgkj:"",
					sbbzh:"",
					bqgcz:"",
					bqgkj:"",
					bqfbh:"",
					fbjjqf:"",
					bhfbh:"",
					fbjjhf:"",
					htr:"",
					saveFj:'',
					dzjcblxbh:"03",
					sbid:this.sbid,//消火栓id
					azrq_vue:''
				},
			}
		},
		methods: {

			tableRowClassName({row, rowIndex}){
				if(row.hh==this.sbsxdzjForm.sbbzh){
					return 'success-row';
				}
				return '';
			},
			fmtableRowClassName({row, rowIndex}){
				if(row.fmbh==this.sbsxdzjForm.bqfbh){
					return 'success-row';
				}else if(row.fmbh==this.sbsxdzjForm.bhfbh){
					return 'warning-row';
				}
				return '';
			},
			bzhyflFormatter(row, column){
				switch (row.hhlxbh) {
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
			//转换已制点志记格式
			yzdzjFormatter(row, column){
				if(row.yzdzj){
					return "是";
				}else{
					return "否";
				}
			},
			fmbj_vue(type){
				this.fmtype = type;
				if(type == '1'){
					this.fmid = this.bqfid;
				}else {
					this.fmid = this.bhfid;
				}
				this.fmpzDialogFormVisible=true;
			},
			fmadd_vue(type){
				this.fmtype = type;
				/*if(type == '1'){
					this.fmid = this.bqfid;
				}else {
					this.fmid = this.bhfid;
				}*/
				this.fmid = '';
				this.fmpzDialogFormVisible=true;
			},
			chooseFm(index,row){
				if(this.fmtype =='1'){
					this.sbsxdzjForm.bqfbh = row.fmbh;
					this.bqfid = row.id;
				}else {
					this.sbsxdzjForm.bhfbh = row.fmbh;
					this.bhfid = row.id;
				}
				this.fmDialogFormVisible = false;
			},
			//监听子组件
			closeFmDialog(result){
				//this.getFmData_vue();
				let param={
					id:result
				}
				fmpzHandle(param).then((res)=>{
					if(res.data!=null){
						if(this.fmtype =='1'){
							this.sbsxdzjForm.bqfbh = res.data.row[0].fmbh;
							this.bqfid = res.data.row[0].id;
						}else {
							this.sbsxdzjForm.bhfbh = res.data.row[0].fmbh;
							this.bhfid = res.data.row[0].id;
						}
					}
				})
				this.fmpzDialogFormVisible = false;
			},
			//选择阀门
			fmxz_vue(type){
				this.fmtype = type;
				this.page=1;
				this.size=10;
				this.getFmData_vue();
			},
			//获取阀门数据
			getFmData_vue() {
				let params = {
					page: this.page,
					size: this.size,
					xmid: this.xmid
				}
				getFmData(params).then((res) => {
					if (res.data != null) {
						this.Data = res.data.row;
						this.total = res.data.total;
						this.fmDialogFormVisible=true
					}
				})
			},
			handleCurrentChange(val){
				this.page = val;
				this.getFmData_vue();
			},
			CurrentChange(val){
				this.page = val;
				this.openSb_vue();
			},
			SizeChange(val) {
				this.size = val;
				this.openSb_vue();
			},
			handleSizeChange(val) {
				this.size = val;
				this.page=1;
				this.getFmData_vue();
			},
			//选择水表按钮
			dzjxz_vue(){
				this.page=1;
				this.size=10;
				this.openSb_vue();
				this.SbDialogFormVisible=true;
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
						this.total = res.data.total;
					}
					this.SbDialogFormVisible=true;
				})
			},
			//选择水表
			chooseSb_vue(index, row){
				this.sbsxdzjForm.sbbzh = row.hh;
				this.sbid = row.id;
				this.SbDialogFormVisible=false;
			},
			//	获取消火栓型号
			/*getXhsxh_vue(){
				let params={}
				getxhsxh(params).then((res)=>{
					if(res.data!=null){
						this.xhsxhOptions=res.data.row;
					}
				})
			},*/
			//	获取下拉框数据
			getOptions(){
				let params={}
				getOptions(params).then((res)=>{
					if(res.data!=null){
						this.jgmcOptions=res.data.jgmc;
						this.jgkjOptions=res.data.jgkl;
						this.bqgczOptions=res.data.bqgcz;
					}
				})
			},
			//	保存点志记管理
			xhsdzjxxwh_vue(formName){
				// if( !this.checkSbsxParams() ){
				// 	return false;
				// }
				this.$refs[formName].validate((valid) => {
					if (valid) {
						let param={
							id : this.sbsxdzjForm.id,
							xmid:this.xmid,
							dzjlxbh:this.sbsxdzjForm.dzjlxbh,
							ggmcbh:this.sbsxdzjForm.jgmc,
							jgkjbh:this.sbsxdzjForm.jgkj,
							gdczbh:this.sbsxdzjForm.bqgcz,
							lm:this.sbsxdzjForm.lm,
							azrq_vue:this.sbsxdzjForm.azrq_vue,
							zt:this.sbsxdzjForm.zt,
							htr:this.sbsxdzjForm.htr,
							sbid:this.sbid,
							dzjlxbh:this.sbsxdzjForm.dzjlxbh,
							dzjcbbh:this.sbsxdzjForm.dzjcbbh,
							fbjjhf: this.sbsxdzjForm.fbjjhf,
							fbjjqf: this.sbsxdzjForm.fbjjqf,
							gdkjbh: this.sbsxdzjForm.bqgkj,
							bqfid:this.bqfid,
							bhfid:this.bhfid
						}
						let params = Object.assign(param);
						saveDzjsbsx(params).then((res)=>{
							if(res.data){
								this.$message.success('保存成功！');
								this.$emit('childConcer', true);
							}else{
								this.$message.error('保存失败！');
							}
						})
					} else {
						return false;
					}
				});

			},
			handleRemove(file, fileList) {
				console.log(file, fileList);
			},
			handlePreview(file) {
				console.log(file);
			},
			handleChange(file,fileList){
				this.fileList = fileList;
			},
			beforeRemove(file, fileList) {
				return this.$confirm(`确定移除 ${ file.name }？`);
			},
			cancer(){
				this.$emit('childConcer', true);
			},
			submitUpload() {
				let fileList = this.fileList;
				if(fileList.length>0){
					let file = fileList[0];
					let filetype = file.name.split(".")[file.name.split(".").length-1];
					if(filetype.toLowerCase()=='jpg'||filetype.toLowerCase()=='png'){
						if(this.fjnumber>0){
							this.$confirm('已存在一条图片,继续上传将覆盖上一张图片, 是否继续?', '提示', {
								confirmButtonText: '确定',
								cancelButtonText: '取消',
								type: 'warning'
							}).then(() => {
								let operation = "点志记上传附件";
									for (let i=0;i<fileList.length;i++){
										let file = fileList[i];
										let formData = new FormData();
										formData.append("xmid",this.xmid);
										formData.append("operation",operation);
										formData.append("multipartFiles",file.raw);
										formData.append("filename",file.name);
										formData.append("dzjid",this.dzjid);
										saveDzjSbsxfile(formData).then((res) => {
											let msg = res.msg;
											if(msg == '上传成功'){
												this.$message({
													message: '上传成功',
													type: 'success'
												});
												this.path = res.path;
												this.name = res.fileName;
												//this.sbsxdzjForm.id = res.dzjid;
												this.fjnumber = 1;
											}else{
												this.$message.error('上传失败！');
											}
											this.fileList=[];
										});
									}
							}).catch(() => {
								return;
							});
						}else{
							let operation = "点志记上传附件";
							for (let i=0;i<fileList.length;i++){
								let file = fileList[i];
								let formData = new FormData();
								formData.append("xmid",this.xmid);
								formData.append("operation",operation);
								formData.append("multipartFiles",file.raw);
								formData.append("filename",file.name);
								formData.append("dzjid",this.dzjid);
								saveDzjSbsxfile(formData).then((res) => {
									let msg = res.msg;
									if(msg == '上传成功'){
										this.$message({
											message: '上传成功',
											type: 'success'
										});
										this.path = res.path;
										this.name = res.fileName;
										this.sbsxdzjForm.id = res.dzjid;
										this.fjnumber = 1;
									}else{
										this.$message.error('上传失败！');
									}
									this.fileList=[];
								});
							}
						}

					}else{
						this.fileList=[];
						this.$message.warning('只能上传png/jpg格式文件！');
						return;
					}
				}else {
					this.$message.warning('未选择任何文件！');
					return;
				}
			},
			showDB(){
				let params = {id : this.dzjid};
				getDzjSbsxxx(params).then((res) => {
					if(res.data.model != undefined){
						let obj = eval("("+res.data.model+")");
						this.sbsxdzjForm.id = obj.id;
						this.xmid = obj.xmid;
						this.sbsxdzjForm.dzjlxbh= obj.dzjlxbh;
						this.sbsxdzjForm.jgmc= obj.jgmc;
						this.jgkjbh= obj.jgkjbh;
						this.sbsxdzjForm.lm= obj.lm;
						this.sbsxdzjForm.azrq_vue= obj.azrq_vue;
						this.sbsxdzjForm.zt= obj.zt;
						this.sbsxdzjForm.htr= obj.htr;
						this.sbid= obj.sbid;
						this.sbsxdzjForm.dzjlxbh= obj.dzjlxbh;
						this.sbsxdzjForm.dzjcbbh= obj.dzjcbbh;
						this.sbsxdzjForm.fbjjhf= obj.fbjjhf;
						this.sbsxdzjForm.fbjjqf= obj.fbjjqf;
						this.bqfid= obj.bqfid;
						this.bhfid= obj.bhfid;


						this.sbsxdzjForm.dzjbh = obj.dzjbh;
						this.sbsxdzjForm.jgmc = obj.ggmcbh;
						this.sbsxdzjForm.jgkj = obj.jgkjbh;
						this.sbsxdzjForm.bqgkj = obj.gdkjbh;
						this.sbsxdzjForm.bqgcz = obj.gdczbh;
						this.sbsxdzjForm.sbbzh = res.data.sbbzh;
						this.sbsxdzjForm.bqfbh = res.data.qfbh;
						this.sbsxdzjForm.bhfbh = res.data.hfbh;

						//this.fileList = [{name:res.data.name}]
						if(res.data.path != null){
							this.path = res.data.path;
							this.name = res.data.fileName;
							this.fjnumber = 1;
						}
					}
				})
			},
			checkSbsxParams(){;
				if (this.sbsxdzjForm.lm == "") {
					this.$message.error('路名不可为空！');
					return false;
				}
				if(this.sbsxdzjForm.jgmc==""){
					this.$message.error('接管名称不可为空！');
					return false;
				}
				if(this.sbsxdzjForm.jgkj==""){
					this.$message.error('接管口径不可为空！');
					return false;
				}
				if(this.sbsxdzjForm.sbbzh==""){
					this.$message.error('请选择水表！');
					return false;
				}
				if(this.sbsxdzjForm.bqgcz==""){
					this.$message.error('表前管材质不可为空！');
					return false;
				}
				if(this.sbsxdzjForm.bqgkj==""){
					this.$message.error('表前管口径不可为空！');
					return false;
				}
				if(this.sbsxdzjForm.bqfbh==""){
					this.$message.error('请选择表前阀！');
					return false;
				}
				if(this.sbsxdzjForm.fbjjqf==""){
					this.$message.error('阀表间距（前阀—表）不可为空！');
					return false;
				}
				if(this.sbsxdzjForm.bhfbh==""){
					this.$message.error('请选择表后阀！');
					return false;
				}
				if(this.sbsxdzjForm.fbjjhf==""){
					this.$message.error('阀表间距（表—后阀）不可为空！');
					return false;
				}
				if(this.sbsxdzjForm.azrq_vue==""){
					this.$message.error('安装日期不可为空！');
					return false;
				}
				return true;
			},
			download(flag){//附件查看或下载
				let formData = new FormData();
				if(this.path==''){
					this.$message.warning('未上传任何文件！');
					return;
				}

				formData.append("path",this.path);
				downloadSbsx(formData).then((res) => {// 处理返回的文件流
					if(flag==1){
						this.slide1=[];
						let reader = new FileReader()
						reader.onload = (e) => {
							let item = {};
							let img = new Image();
							img.src =e.target.result;
							item.msrc = item.src = e.target.result;
							// this.Imgsrc = e.target.result;
							item.h = img.height;
							item.w = img.width;
							this.slide1.push(item);
							this.$preview.openList(0, this.slide1);
							//this.msgFlag = true;
							//this.prop.src = this.Imgsrc
						}
						reader.readAsDataURL(res)
					}else{
						const content = res;
						const blob = new Blob([content])
						const fileName = this.name;
						if ('download' in document.createElement('a')) { // 非IE下载
							const elink = document.createElement('a')
							elink.download = fileName
							elink.style.display = 'none'
							elink.href = URL.createObjectURL(blob)
							document.body.appendChild(elink)
							elink.click()
							URL.revokeObjectURL(elink.href) // 释放URL 对象
							document.body.removeChild(elink)
						} else { // IE10+下载
							navigator.msSaveBlob(blob, fileName)
						}
					}
				})
			}

		},
		mounted() {
			this.getOptions();
			this.showDB();
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
	.el-button--mini, .el-button--mini.is-round {
		padding: 7px 10px;
	}
	.el-table .success-row {
		background: #f0f9eb;
	}
	.el-table .warning-row {
		background: oldlace;
	}
</style>