<template>
	<section>
		<el-row style="margin-top: 15px;">
			<div class="text item">
				<el-button style="margin-bottom: 10px;" size="mini" type="primary" @click="xfspzAdd_vue()" v-if="isReadonly != '1'"><i class="iconfont icon-xinzeng" style="font-size: 12px"></i>新增</el-button>
				<el-table :data="row" style="width: 100%" :stripe="true">
					<el-table-column type="index" label="序号" width="80" align="center">
					</el-table-column>
					<el-table-column
							width="150"
							prop="xhsbh"
							label="消火栓编号"
							align="center">
					</el-table-column>
					<el-table-column
							prop="azwz"
							label="安装位置"
							align="center">
					</el-table-column>
					<el-table-column
							width="180"
							prop="azrq_vue"
							label="安装日期"
							align="center">
					</el-table-column>
					<el-table-column
							width="100"
							prop="xhscj"
							label="消火栓厂家"
							align="center">
					</el-table-column>
					<el-table-column
							width="150"
							prop="xhbhmc"
							label="消火栓型号"
							align="center">
					</el-table-column>
					<el-table-column
							width="100"
							prop="azfsbhmc"
							label="安装方式"
							align="center">
					</el-table-column>
					<el-table-column
							width="200"
							prop="gps"
							label="GPS数据"
							align="center">
					</el-table-column>
					<el-table-column
							width="180"
							prop="czsj_vue"
							label="操作时间"
							align="center">
					</el-table-column>
					<el-table-column
							width="150"
							prop="czrname"
							label="操作人"
							align="center">
					</el-table-column>
					<el-table-column
							width="100"
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
					<el-table-column  label="操作" align="center" width="200px" fixed="right">
						<template slot-scope="scope">
							<label title="编辑" v-if="isReadonly != '1'"><i @click="xhspzHandle_vue(scope.$index, scope.row)" class="iconfont icon-ai-edit" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
							<label title="删除" v-if="isReadonly != '1'"><i @click="xhspzDelete_vue(scope.$index, scope.row)" class="iconfont delete" style="font-size:18px;cursor:pointer;padding: 2px;color: #F56C6C"></i></label>
							<label title="查看" v-if="isReadonly == '1'"><i @click="xhspzHandle_vue(scope.$index, scope.row)" class="iconfont icon-yulan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>

							<!--<el-button  size="mini" type="primary" @click="opendzjgl_vue(scope.$index, scope.row)">点志记管理</el-button>-->
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
		<el-dialog title="消火栓信息录入"  :visible.sync="xhspzDialogFormVisible" width="50%" :append-to-body="true"  :close-on-click-modal="false">
			<el-form  :model="xhsForm"  ref="xhsForm" :rules="rules">
				<el-input style="display: none" v-model="xhsForm.id"  ></el-input>
				<el-input style="display: none" v-model="xhsForm.sbid"  ></el-input>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>消火栓编号&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-input :disabled="true" v-model="xhsForm.xhsbh" v-if="xhsForm.xhsbh!=null&&xhsForm.xhsbh!=''"  size="small"></el-input>
						<el-input :disabled="true" v-else value="保存后自动生成" size="small" ></el-input>
					</el-col>
					<el-col :span="3" style="text-align: right">
						<span>水表报装号&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="6">
						<el-form-item prop="sbbzh">
							<el-input :disabled="true" v-model="xhsForm.sbbzh"  size="small"></el-input></el-form-item>
					</el-col>
					<el-col :span="3">
						<el-button type="primary" size="mini" @click="openSb_vue()" style="margin-left: 5px" v-if="isReadonly != '1'"><i class="iconfont icon-xuanze" style="font-size: 12px"></i>关联水表</el-button>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>安装位置&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="21">
						<el-form-item prop="azwz">
							<el-input v-model="xhsForm.azwz" size="small"></el-input></el-form-item>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>消火栓厂家&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-form-item prop="xhscj">
							<el-input v-model="xhsForm.xhscj"  size="small"></el-input></el-form-item>
					</el-col>
					<el-col :span="3" style="text-align: right">
						<span>安装日期&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-form-item prop="azrq_vue">
							<el-date-picker  size="small"value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期" v-model="xhsForm.azrq_vue" style="width: 100%"></el-date-picker></el-form-item>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>消火栓型号&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-form-item prop="xhbh">
						<el-select v-model="xhsForm.xhbh" style="width: 100%" size="small">
							<el-option
									v-for="item in xhsxhOptions"
									:key="item.codevalue"
									:label="item.codename"
									:value="item.codevalue">
							</el-option>
						</el-select></el-form-item>
					</el-col>
					<el-col :span="3" style="text-align: right">
						<span>安装方式&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-form-item prop="azfsbh">
						<el-select v-model="xhsForm.azfsbh" style="width: 100%" size="small">
							<el-option
									v-for="item in xhsazfsOptions"
									:key="item.codevalue"
									:label="item.codename"
									:value="item.codevalue">
							</el-option>
						</el-select>
						</el-form-item>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>GPS数据&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-form-item prop="gps">
							<el-input v-model="xhsForm.gps" size="small"></el-input></el-form-item>
					</el-col>
					<el-col :span="10">
						<label style="margin-left: 5px;color: #66b1ff">格式&nbsp;&nbsp;35XXXXX.XXX,5XXXXX.XXX,XX.XXX</label>
					</el-col>
				</el-form-item>

				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>备注&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="21">
						<el-input type="textarea":rows="3"  v-model="xhsForm.bz"></el-input>
					</el-col>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer" style="text-align: center" v-if="isReadonly != '1'">
				<el-button @click="xhspzDialogFormVisible = false" type="info" size="mini"><i class="iconfont icon-quxiao" ></i>取 消</el-button>
				<el-button type="primary" size="mini"  @click="xhsxxwh_vue('xhsForm')"><i class="iconfont icon-queren"></i>确 定</el-button>
			</div>
			<row v-if="isReadonly == '1'">&nbsp;</row>
		</el-dialog>
		<el-dialog title="选择水表信息" :visible.sync="SbDialogFormVisible" width="80%" :close-on-click-modal="false">
			<el-table :data="SbData" style="width: 100%" border :row-class-name="tableRowClassName">
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
						prop="hyfl"
						label="报装行业分类"
						align="center"
						>
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
						<label title="选择"><i @click="chooseSb_vue(scope.$index, scope.row)" class="iconfont icon-xuanze" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
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
		<el-dialog title="消火栓点志记"  :visible.sync="xhsdzjDialogFormVisible" width="50%" :close-on-click-modal="false">
			<el-form  :model="xhsdzjForm"  >
				<el-input style="display: none" v-model="xhsdzjForm.id"  ></el-input>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>点志记编号&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-input :disabled="true" v-model="xhsdzjForm.dzjbh" v-if="xhsdzjForm.dzjbh!=null&&xhsdzjForm.dzjbh!=''" ></el-input>
						<el-input :disabled="true" v-else value="保存后自动生成" ></el-input>
					</el-col>
					<el-col :span="3" style="text-align: right">
						<span>点志记状态&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-select v-model="xhsdzjForm.zt" style="width: 100%" :disabled="true">
							<el-option
									v-for="item in ztOptions"
									:key="item.codevalue"
									:label="item.codename"
									:value="item.codevalue">
							</el-option>
						</el-select>
					</el-col>

				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>路名&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="21">
						<el-input v-model="xhsdzjForm.lm"></el-input>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>消火栓编号&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-input :disabled="true" v-model="xhsdzjForm.xhsbh"  ></el-input>
					</el-col>
					<el-col :span="3" style="text-align: right">
						<span>型号&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-select v-model="xhsdzjForm.xhbh" style="width: 100%" :disabled="true">
							<el-option
									v-for="item in xhsxhOptions"
									:key="item.codevalue"
									:label="item.codename"
									:value="item.codevalue">
							</el-option>
						</el-select>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>安装方式&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-select v-model="xhsdzjForm.azfsbh" style="width: 100%" :disabled="true">
							<el-option
									v-for="item in xhsazfsOptions"
									:key="item.codevalue"
									:label="item.codename"
									:value="item.codevalue">
							</el-option>
						</el-select>
					</el-col>
					<el-col :span="3" style="text-align: right">
						<span>制造厂家&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-input v-model="xhsdzjForm.xhscj" :disabled="true"></el-input>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>消火栓坐标&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-input v-model="xhsdzjForm.gps" :disabled="true"></el-input>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>安装日期&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-date-picker :editable="false" type="datetime" value-format="yyyy-MM-dd HH:mm" format="yyyy-MM-dd HH:mm" placeholder="选择日期" v-model="xhsdzjForm.azrq_vue" style="width: 100%"></el-date-picker>
					</el-col>
					<el-col :span="3" style="text-align: right">
						<span>绘图人&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-input v-model="xhsdzjForm.htr" placeholder="为空时保存当前登录人"></el-input>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>点志记插图&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="21">
						<a href="#">管理</a>
						<a href="#">下载</a>
						<label>附件（{{fjnumber}}）份</label>
					</el-col>

				</el-form-item>

			</el-form>
			<div slot="footer" class="dialog-footer" style="text-align: center">
				<el-button type="info" size="small"  @click="xhsdzjDialogFormVisible = false"><i class="iconfont icon-quxiao"></i>取 消</el-button>
				<el-button type="primary" size="small" @click="xhsxxwh_vue()"><i class="iconfont icon-queren"></i>确 定</el-button>
			</div>
		</el-dialog>
	</section>
</template>
<script>
	import { getxhsData,xhsxxwh,getxhsxh,getXhsazfs,xhspzHandle,xhspzDelete,getSb} from '../../../../api/bwyj';

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
		data() {
			var checkGPS = (rule, value, callback) => {
				let val = value.replace('，', ',');
				let gps = val.split(',');
				if (gps.length != 3)
				{
					callback(new Error('请确认GPS数据格式：35XXXXX.XXX,5XXXXX.XXX,XX.XXX'));
				}
				else
				{
					if (gps[0].substring(0, 2) != "35" || gps[1].substring(0, 1) != "5")
					{
						callback(new Error('请确认GPS数据格式：35XXXXX.XXX,5XXXXX.XXX,XX.XXX'));
					}else{
						callback();
					}
				}

			};
			return {
				rules:{
					sbbzh:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					azwz:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					xhscj:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					xhbh:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					azfsbh:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					gps:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' },
						{ validator: checkGPS, trigger: 'blur'}
					],
					azrq_vue:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					]},
				fjnumber:0,
				total: 10,//表格数据总数量
				page: 1,//当前页码
				size: 10,//每页容量
				//xmid:'1',
				row:[],
				SbData:[],
				xhsazfsOptions:[],
				xhsxhOptions:[],
				SbDialogFormVisible:false,
				xhspzDialogFormVisible:false,
				xhsdzjDialogFormVisible:false,
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
				xhsForm:{
					sbid:"",
					id:"",
					sbbzh:"",
					xhsbh:"",
					azwz:"",
					xhscj:"",
					azrq_vue:"",
					xhbh:"",
					azfsbh:"",
					gps:"",
					czsj_vue:"",
					czr:"",
					bz:"",
				},
				xhsdzjForm:{
					id:"",
					dzjbh:"",
					zt:"0",
					lm:"",
					dzjazrq:"",
					htr:""
				},
			}
		},
		methods: {

			tableRowClassName({row, rowIndex}){
				if(row.hh==this.xhsForm.sbbzh){
					return 'success-row';
				}
				return '';
			},
			//转换已制点志记格式
			yzdzjFormatter(row, column){
				if(row.yzdzj){
					return "是";
				}else{
					return "否";
				}
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
			//获取消火栓
			getxhsData_vue(){
				let params={
					page: this.page,
					size: this.size,
					xmid:this.xmid
				}
				getxhsData(params).then((res)=>{
					if(res.data!=null){
						this.row=res.data.row;
						this.total=res.data.total;
					}
				})
			},
			handleCurrentChange(val){
				this.page = val;
				this.getxhsData_vue();
			},
			handleSizeChange(val) {
				this.size = val;
				this.page=1;
				this.getxhsData_vue();
			},
			CurrentChange(val){
				this.page = val;
				this.openSb_vue();
			},
			SizeChange(val) {
				this.size = val;
				this.openSb_vue();
			},
			clearDate(){
				this.xhsForm={
					        sbid:"",
							id:"",
							sbbzh:"",
							xhsbh:"",
							azwz:"",
							xhscj:"",
							azrq_vue:"",
							xhbh:"",
							azfsbh:"",
							gps:"",
							czsj_vue:"",
							czr:"",
							bz:"",
				}
			},
			//新增消火栓
			xfspzAdd_vue(){
				this.clearDate();
				this.xhspzDialogFormVisible=true;
			},
			//消火栓参数验证
			checkxhsParams(){
				if (this.xhsForm.sbbzh == "") {
					this.$message.error('水表报装号不可为空！');
					return false;
				}
				if(this.xhsForm.azwz==""){
					this.$message.error('安装位置不可为空！');
					return false;
				}
				if(this.xhsForm.xhscj==""){
					this.$message.error('消火栓厂家不可为空！');
					return false;
				}
				if(this.xhsForm.azrq_vue==""){
					this.$message.error('安装日期不可为空！');
					return false;
				}
				if(this.xhsForm.xhsxh==""){
					this.$message.error('消火栓型号不可为空！');
					return false;
				}
				if(this.xhsForm.azfs==""){
					this.$message.error('安装方式不可为空！');
					return false;
				}
				if(this.xhsForm.gps==""){
					this.$message.error('GPS数据不可为空！');
					return false;
				}
				return true;
			},
			//消火栓信息维护
			xhsxxwh_vue(formName){
				// let checkxhsParams = this.checkxhsParams();
				// if(!checkxhsParams){
				// 	return;
				// }
				this.$refs[formName].validate((valid) => {
					if (valid) {
						let param={
							xmid:this.xmid
						}
						let params = Object.assign(param,this.xhsForm);
						xhsxxwh(params).then((res)=>{
							if(res.data){
								this.getxhsData_vue();
								this.xhspzDialogFormVisible=false;
							}else{
								this.$message.error('保存失败！');
							}
						})
					} else {
						return false;
					}
				});
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
			chooseSb_vue(index, row){;
				this.xhsForm.sbbzh = row.hh;
				this.xhsForm.sbid = row.id;
				this.SbDialogFormVisible=false;
			},
		//	获取消火栓型号
			getXhsxh_vue(){
				let params={}
				getxhsxh(params).then((res)=>{
					if(res.data!=null){
						this.xhsxhOptions=res.data.row;
					}
				})
			},
		//	获取消火栓安装方式
			getXhsazfs_vue(){
				let params={}
				getXhsazfs(params).then((res)=>{
					if(res.data!=null){
						this.xhsazfsOptions=res.data.row;
					}
				})
			},
			//消火栓数据回显
			xhspzHandle_vue(index, row) {
				let params = {
					id: row.id
				}
				xhspzHandle(params).then((res) => {
					if (res.data != null) {
						this.xhsForm = res.data.row[0]
						this.xhspzDialogFormVisible = true
					}
				})
			},
			//删除消火栓
			xhspzDelete_vue(index, row) {
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					let params = {
						id: row.id
					}
					xhspzDelete(params).then((res) => {
						if (res.data) {
							this.getxhsData_vue();
						}
					})
				})
			},
			//	打开点志记管理
			opendzjgl_vue(index,row){
				this.xhsdzjForm=row;
				this.xhsdzjDialogFormVisible = true;
			}
		},
		mounted() {
			this.getxhsData_vue();
			this.getXhsxh_vue();
			this.getXhsazfs_vue();
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
	.el-table .success-row {
		background: #f0f9eb;
	}
</style>