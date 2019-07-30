<template>
	<section>
		<!--<el-dialog title="阀门信息录入"  :visible.sync="fmpzDialogFormVisible" width="50%">-->

			<el-form  :model="fmForm"  ref="fmForm" :rules="rules">
				<el-input style="display: none" v-model="fmForm.id"  ></el-input>
				<el-input style="display: none" v-model="fmForm.sbid"  ></el-input>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>阀门编号&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-input :disabled="true" v-model="fmForm.fmbh" v-if="fmForm.fmbh!=null&&fmForm.fmbh!=''" size="small" ></el-input>
						<el-input :disabled="true" v-else value="保存后自动生成" size="small"></el-input>
					</el-col>
					<el-col :span="3"style="text-align: right">
						<span>水表报装号&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="6">
						<el-form-item prop="sbbzh">
							<el-input :disabled="true" v-model="fmForm.sbbzh" size="small" ></el-input></el-form-item>
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
							<el-input v-model="fmForm.azwz" size="small" ></el-input></el-form-item>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>阀门厂家&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-form-item prop="fmcj">
						<el-input v-model="fmForm.fmcj" size="small" ></el-input></el-form-item>
					</el-col>
					<el-col :span="3" style="text-align: right">
						<span>安装日期&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-form-item prop="azrq_vue">
						<el-date-picker size="small" format="yyyy-MM-dd" value-format="yyyy-MM-dd" :editable="false" type="date" placeholder="选择日期" v-model="fmForm.azrq_vue" style="width: 100%"></el-date-picker>
						</el-form-item>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>阀门口径&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-form-item prop="fmkjbh">
						<el-select v-model="fmForm.fmkjbh" style="width: 100%" size="small">
							<el-option
									v-for="item in fmkjbhOptions"
									:key="item.codevalue"
									:label="item.codename"
									:value="item.codevalue">
							</el-option>
						</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="3" style="text-align: right">
						<span>阀门类型&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-form-item prop="fmlxbh">
						<el-select v-model="fmForm.fmlxbh" style="width: 100%" size="small">
							<el-option
									v-for="item in fmlxbhOptions"
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
						<span>阀门作用&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-form-item prop="fmzybh">
						<el-select v-model="fmForm.fmzybh" style="width: 100%" size="small">
							<el-option
									v-for="item in fmzybhOptions"
									:key="item.codevalue"
									:label="item.codename"
									:value="item.codevalue">
							</el-option>
						</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="3" style="text-align: right">
						<span>阀门型号&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-form-item prop="fmxh">
							<el-input v-model="fmForm.fmxh" size="small"></el-input></el-form-item>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>转动方式&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-form-item prop="zdfsbh">
						<el-select v-model="fmForm.zdfsbh" style="width: 100%"  size="small">
							<el-option
									v-for="item in zdfsbhOptions"
									:key="item.codevalue"
									:label="item.codename"
									:value="item.codevalue">
							</el-option>
						</el-select></el-form-item>
					</el-col>
					<el-col :span="3" style="text-align: right">
						<span>转动方向&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-form-item prop="zdfxbh">
						<el-select v-model="fmForm.zdfxbh" style="width: 100%"  size="small">
							<el-option
									v-for="item in zdfxbhOptions"
									:key="item.codevalue"
									:label="item.codename"
									:value="item.codevalue">
							</el-option>
						</el-select></el-form-item>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>启闭转数&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="6">
						<el-form-item prop="qbzs">
							<el-input v-model="fmForm.qbzs" size="small"></el-input></el-form-item>
					</el-col>
					<el-col :span="3">
						<label style="margin-left: 5px;">转</label>
					</el-col>
					<el-col :span="3" style="text-align: right">
						<span>阀杆材质&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-form-item prop="fgczbh">
						<el-select v-model="fmForm.fgczbh" style="width: 100%" size="small" >
							<el-option
									v-for="item in fgczbhOptions"
									:key="item.codevalue"
									:label="item.codename"
									:value="item.codevalue">
							</el-option>
						</el-select></el-form-item>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>阀杆方身&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="6">
						<el-form-item prop="fgfs">
							<el-input v-model="fmForm.fgfs" size="small"></el-input></el-form-item>
					</el-col>
					<el-col :span="3">
						<label style="margin-left: 5px;">mm</label>
					</el-col>
					<el-col :span="3" style="text-align: right">
						<span>阀杆深度&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="6">
						<el-form-item prop="fgsd">
							<el-input v-model="fmForm.fgsd" size="small"></el-input></el-form-item>
					</el-col>
					<el-col :span="3">
						<label style="margin-left: 5px">m</label>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>阀井盖材质&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-form-item prop="fjgczbh">
						<el-select v-model="fmForm.fjgczbh" style="width: 100%" size="small" >
							<el-option
									v-for="item in fjgczbhOptions"
									:key="item.codevalue"
									:label="item.codename"
									:value="item.codevalue">
							</el-option>
						</el-select></el-form-item>
					</el-col>
					<el-col :span="3" style="text-align: right">
						<span>阀井盖形式&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="9">
						<el-form-item prop="fjgxsbh">
						<el-select v-model="fmForm.fjgxsbh" style="width: 100%" size="small" >
							<el-option
									v-for="item in fjgxsbhOptions"
									:key="item.codevalue"
									:label="item.codename"
									:value="item.codevalue">
							</el-option>
						</el-select></el-form-item>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>GPS数据&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="11">
						<el-form-item prop="gps">
							<el-input v-model="fmForm.gps" size="small"></el-input></el-form-item>
					</el-col>
					<el-col :span="10">
						<label style="margin-left: 5px">格式&nbsp;&nbsp;35XXXXX.XXX,5XXXXX.XXX,XX.XXX</label>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>备注&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="21">
						<el-input type="textarea":rows="3" v-model="fmForm.bz" ></el-input>
					</el-col>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer" style="text-align: center;padding-bottom: 20px" v-if="isReadonly != '1'">
				<el-button type="info" size="mini" @click="cancer()"><i class="iconfont icon-quxiao" ></i>取 消</el-button>
				<el-button type="primary" size="mini" @click="fmxxwh_vue('fmForm')"><i class="iconfont icon-queren"></i>确 定</el-button>
			</div>
		<row v-if="isReadonly == '1'">&nbsp;</row>
		<!--</el-dialog>-->
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
						width="200"
						>

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
						<label title="选择"><i @click="chooseSb_vue(scope.$index, scope.row)" class="iconfont icon-xuanze" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
					</template>
				</el-table-column>

			</el-table>
			<!--工具条-->
			<el-row>
				<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[5,10,20,50]"
							   @current-change="CurrentChange" @size-change="handleSizeChange":current-page.sync="this.page"
							   :page-size="this.size" style="float:right;" :total="this.total">
				</el-pagination>
			</el-row>
		</el-dialog>
	</section>
</template>

<script>
	import { fmxxwh,getSb,getFmkj,getFmlx,getFmzy,getZdfs,getZdfx,getfjgcz,getFjgxs,fmpzHandle,fmpzDelete,getFgcz } from '../../../../api/bwyj';
	export default {
		props:{
			id:{
				type: String,
				required: true
			},
			xmid:{
				type: String,
				required: true
			},
			isReadonly:{
				type: String,
				required: true
			}
		},
		watch:{
			id:{
				handler(newValue,oldValue){
					this.id = newValue;
					this.handle_vue();
				},
				deep:true
			}
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
					fmcj:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					fmkjbh:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					fmzybh:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					zdfsbh:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					qbzs:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' },
						{ validator: checkNum, trigger: 'blur'}
					],
					fgfs:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' },
						{ validator: checkNum, trigger: 'blur'}
					],
					fjgczbh:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					gps:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' },
						{ validator: checkGPS, trigger: 'blur'}
					],
					azrq_vue:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					fmlxbh:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					fmxh:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					zdfxbh:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					fgczbh:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					fgsd:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' },
						{ validator: checkNum, trigger: 'blur'}
					],
					fjgxsbh:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					]},
				total: 0,//表格数据总数量
				page: 1,//当前页码
				size: 10,//每页容量
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
				SbDialogFormVisible:false,
				// fmpzDialogFormVisible:false,
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
			tableRowClassName({row, rowIndex}){
				if(row.hh==this.fmForm.sbbzh){
					return 'success-row';
				}
				return '';
			},
			handle_vue(){
				if(this.id==""){
					this.fmpzAdd_vue();
				}else{
					this.fmpzHandle_vue(this.id);
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
			CurrentChange(val){
				this.page = val;
				this.openSb_vue();
			},
			handleSizeChange(val) {
				this.size = val;
				this.page=1;
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
				this.claerFb();
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
			//阀门信息维护
			fmxxwh_vue(formName){
				this.$refs[formName].validate((valid) => {
					if (valid) {
						let param={
							xmid:this.xmid
						}
						let params = Object.assign(param,this.fmForm);
						fmxxwh(params).then((res)=>{
							if(res.data){
								this.$emit('childConcer', this.fmForm.id);
								//this.$emit('getFmData_vue');
							}else{
								this.$message.error('保存失败！');
							}
						})
					} else {
						return false;
					}
				});
			},
			//阀门数据回显
			fmpzHandle_vue(id) {
				let params = {
					id: id
				}
				fmpzHandle(params).then((res) => {
					if (res.data != null) {
						this.fmForm = res.data.row[0]
					}
				})
			},
			cancer(){
				this.$emit('childConcer', this.fmForm.id);
			},
			//获取水表
			openSb_vue(){debugger
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
		},
		mounted() {
			this.handle_vue();
			this.getFmkj_vue();
			this.getFmlx_vue();
			this.getFmzy_vue();
			this.getZdfs_vue();
			this.getZdfx_vue();
			this.getfjgcz_vue();
			this.getFjgxs_vue();
			this.getFgcz_vue();
		},
		destroyed() {

		}
	}

</script>
<style>
	.el-form-item__error {
		color: #F56C6C;
		font-size: 12px;
		line-height: 1;
		 padding-top: 0px;
		position: absolute;
		top: 100%;
		left: 0;
	}
	.el-table .success-row {
		background: #f0f9eb;
	}
</style>