<template>
	<section>
		<el-dialog title="消火栓列表"  :visible.sync="xhslbDialogFormVisible" width="80%" :append-to-body="true" :close-on-click-modal="false">
			<el-row>
				<el-table :data="row"style="width: 100%" border :row-class-name="tableRowClassName">

					<el-table-column type="index" label="序号" width="50" align="center">
					</el-table-column>
					<el-table-column
                            width="100"
							prop="xmbh"
							label="项目编码"
							align="center">
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
							prop="azrq_vue"
							label="安装日期"
							align="center"
                            width="100">
					</el-table-column>
					<el-table-column
							prop="xhscj"
							label="消火栓厂家"
							align="center"
                            width="100">
					</el-table-column>
					<el-table-column
							prop="xhbhmc"
							label="消火栓型号"
							align="center"
                            width="100">
					</el-table-column>
					<el-table-column
							prop="azfsbhmc"
							label="安装方式"
							align="center"
                            width="100">
					</el-table-column>
					<el-table-column
							prop="gps"
							label="GPS数据"
							align="center"
                            width="180">
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
							<label title="选择" ><i @click="xhsselect_vue(scope.$index, scope.row)" class="iconfont icon-xuanze" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
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
			</el-row>
		</el-dialog>
		<el-dialog title="消火栓信息录入"  :visible.sync="xhspzDialogFormVisible" width="50%" :append-to-body="true" :close-on-click-modal="false">
			<el-form  :model="xhsForm"  ref="xhsForm" :rules="xhsrules">
				<el-input style="display: none" v-model="xhsForm.id"  ></el-input>
				<el-input style="display: none" v-model="xhsForm.sbid"  ></el-input>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>消火栓编号：</span>
					</el-col>
					<el-col :span="9">
						<el-input :disabled="true" v-model="xhsForm.xhsbh" v-if="xhsForm.xhsbh!=null&&xhsForm.xhsbh!=''"  size="small"></el-input>
						<el-input :disabled="true" v-else value="保存后自动生成" size="small" ></el-input>
					</el-col>
					<el-col :span="3"style="text-align: right">
						<span>水表报装号：</span>
					</el-col>
					<el-col :span="6">
						<el-form-item prop="sbbzh">
							<el-input :disabled="true" v-model="xhsForm.sbbzh" size="small"></el-input></el-form-item>
					</el-col>
					<el-col :span="3">
						<el-button type="primary" size="mini" @click="openSb_vue()" style="margin-left: 5px"><i class="iconfont icon-xuanze"></i>关联水表</el-button>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>安装位置：</span>
					</el-col>
					<el-col :span="21">
						<el-form-item prop="azwz">
							<el-input v-model="xhsForm.azwz" size="small"></el-input></el-form-item>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>消火栓厂家：</span>
					</el-col>
					<el-col :span="9">
						<el-form-item prop="xhscj">
							<el-input v-model="xhsForm.xhscj" size="small" ></el-input></el-form-item>
					</el-col>
					<el-col :span="3" style="text-align: right">
						<span>安装日期：</span>
					</el-col>
					<el-col :span="9">
						<el-form-item prop="azrq_vue">
							<el-date-picker format="yyyy-MM-dd" value-format="yyyy-MM-dd" size="small" type="date" placeholder="选择日期" v-model="xhsForm.azrq_vue" style="width: 100%"></el-date-picker></el-form-item>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>消火栓型号：</span>
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
						<span>安装方式：</span>
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
						</el-select></el-form-item>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>GPS数据：</span>
					</el-col>
					<el-col :span="9">
						<el-form-item prop="gps">
							<el-input v-model="xhsForm.gps" size="small"></el-input></el-form-item>
					</el-col>
					<el-col :span="10">
						<label style="margin-left: 5px;color: #66b1ff">格式：35XXXXX.XXX,5XXXXX.XXX,XX.XXX</label>
					</el-col>
				</el-form-item>

				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>备注：</span>
					</el-col>
					<el-col :span="21">
						<el-input type="textarea":rows="3"  v-model="xhsForm.bz"></el-input>
					</el-col>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer" style="text-align: center">
				<el-button type="info" size="small" @click="xhspzDialogFormVisible = false"><i class="iconfont icon-quxiao"></i>取 消</el-button>
				<el-button type="primary" size="small" @click="xhsxxwh_vue('xhsForm')"><i class="iconfont icon-queren"></i>确 定</el-button>
			</div>
		</el-dialog>
		<el-dialog title="选择水表信息" :visible.sync="SbDialogFormVisible" width="80%" :append-to-body="true" :close-on-click-modal="false">
			<el-table :data="SbData"style="width: 100%" border>
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
							   @current-change="CurrentChange" @size-change="SizeChange" :current-page.sync="this.page"
							   :page-size="this.size" style="float:right;" :total="this.total">
				</el-pagination>
			</el-row>
		</el-dialog>
		<el-form  :model="xhsdzjForm" ref="xhsdzjForm" :rules="rules">
				<el-input style="display: none" v-model="xhsdzjForm.id"  ></el-input>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>点志记编号：</span>
					</el-col>
					<el-col :span="9">
						<el-input readonly v-model="xhsdzjForm.dzjbh" v-if="xhsdzjForm.dzjbh!=null&&xhsdzjForm.dzjbh!=''" size="small"></el-input>
						<el-input readonly v-else value="保存后自动生成" size="small"></el-input>
					</el-col>
					<el-col :span="4"style="text-align: right" >
						<span>点志记状态：</span>
					</el-col>
					<el-col :span="8">
                        <label v-if="xhsdzjForm.zt=='0'">正在制单</label>
                        <label v-if="xhsdzjForm.zt=='1'">准备移交</label>
                        <label v-if="xhsdzjForm.zt=='2'">单据已传</label>
                        <label v-if="xhsdzjForm.zt=='3'">作废终止</label>
                        <label v-if="xhsdzjForm.zt=='4'">交接完成</label>
                        <label v-if="xhsdzjForm.zt=='5'">区所已收</label>
					</el-col>

				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>路名：</span>
					</el-col>
					<el-col :span="21">
						<el-form-item prop="lm">
							<el-input v-model="xhsdzjForm.lm" size="small"></el-input></el-form-item>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>消火栓编号：</span>
					</el-col>
					<el-col :span="5">
						<el-form-item prop="xhsbh">
							<el-input readonly v-model="xhsdzjForm.xhsbh"  size="small" ></el-input></el-form-item>
					</el-col>
					<el-col :span="6">
                        <el-row>
                            <el-col :span="12">
                                <el-button style="margin-left:10px;" size="mini" type="primary" @click="dzjxz_vue"><i class="iconfont icon-xuanze" style="font-size: 12px"></i>选择</el-button>
                            </el-col>
                            <el-col :span="12">
                                <el-button  v-if="xhsdzjForm.xhsbh!=''" style="margin-left:10px;" size="mini" type="primary" @click="xhspzHandle_vue"><i class="iconfont icon-ai-edit" style="font-size: 12px"></i>编辑</el-button>&nbsp;
                            </el-col>
                        </el-row>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>型号：</span>
					</el-col>
					<el-col :span="8">
						<el-input readonly v-model="xhsdzjForm.xhbhmc" size="small" ></el-input>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>安装方式：</span>
					</el-col>
					<el-col :span="9">
						<el-input readonly v-model="xhsdzjForm.azfsbhmc" size="small" ></el-input>
					</el-col>
					<el-col :span="4" style="text-align: right">
						<span>制造厂家：</span>
					</el-col>
					<el-col :span="8">
						<el-input readonly v-model="xhsdzjForm.xhscj"size="small" ></el-input>
					</el-col>
				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>消火栓坐标：</span>
					</el-col>
					<el-col :span="9">
						<el-input v-model="xhsdzjForm.gps" readonly size="small"></el-input>
					</el-col>

				</el-form-item>
				<el-form-item>
					<el-col :span="3" style="text-align: right">
						<span>安装日期：</span>
					</el-col>
					<el-col :span="9">
						<el-form-item prop="azrq_vue">
							<el-date-picker :editable="false" size="small"  type="date" value-format="yyyy-MM-dd" format="yyyy-MM-dd" placeholder="选择日期" v-model="xhsdzjForm.azrq_vue" style="width: 100%"></el-date-picker></el-form-item>
					</el-col>
					<el-col :span="4" style="text-align: right">
						<span>绘图人：</span>
					</el-col>
					<el-col :span="8">
						<el-input v-model="xhsdzjForm.htr" placeholder="为空时保存当前登录人" size="small"></el-input>
					</el-col>
				</el-form-item>
			<el-form-item>
				<el-col :span="3" style="text-align: right">
					<span>点志记插图：</span>
				</el-col>
				<el-col :span="21">
					<el-upload
							class="upload-demo"
							ref="upload"
							action="#"
							accept=".jpg,.png,.JPG,.PNG"
							:on-preview="handlePreview"
							:http-request="submitUpload"
							:on-remove="handleRemove"
							:on-change="handleChange"
							:file-list="fileList"
							:auto-upload="true"
							:show-file-list=false>
						<el-button slot="trigger" size="mini" type="success"><i class="iconfont icon-shangchuan_f"></i>上传</el-button>
						<!--<el-button style="margin-left: 10px;" size="small" type="primary" @click="submitUpload" icon="el-icon-upload">上传</el-button>-->
						<el-button size="mini" type="success" style="margin-left: 10px" @click="download"><i class="iconfont icon-xiazai_f"></i>下载</el-button>
						<el-button size="mini" type="primary" style="margin-left: 10px" @click="look"><i class="iconfont icon-yulan"></i>预览</el-button>
						<label>附件（{{fjnumber}}）份</label>
						<div slot="tip" class="el-upload__tip"></div>
					</el-upload>

				</el-col>

			</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer" style="text-align: center;padding-bottom: 10px">
				<el-button type="info" size="mini" @click="cancer()"><i class="iconfont icon-quxiao"></i>取 消</el-button>
				<el-button type="primary" size="mini" @click="xhsdzjxxwh_vue('xhsdzjForm')"><i class="iconfont icon-queren"></i>确 定</el-button>
			</div>
	</section>
</template>
<script>
	import { getxhsData,xhsxxwh,getxhsxh,getXhsazfs,xhspzHandle,getSb,fmdzjxxwh,xhsdzjData,download,saveDzjfile } from '../../../../api/bwyj';

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
		data() {
			return {
				xhsrules:{
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
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],
					azrq_vue:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					]},
				rules:{
					lm:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],

					azrq_vue:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					],

					xhsbh:[
						{ required: true, message: '该字段不可为空', trigger: 'blur' }
					]},
				slide1:[],
				readonly:true,
				fjnumber:0,
				total: 10,//表格数据总数量
				page: 1,//当前页码
				size: 10,//每页容量
				 //xmid:'1',//项目id
				 //dzjid:'',//点志记id
				xhsid:'',//消火栓id
                path:'',//下载路径
				row:[],//消火栓数据
                fileList:[],//文件列表
				filerow:[],//文件列表数据
				SbData:[],//水表列表数据
				xhsazfsOptions:[],//消火栓安装方式下拉数据
				xhsxhOptions:[],//消火栓型号下拉数据
				SbDialogFormVisible:false,
				xhspzDialogFormVisible:false,
				xhslbDialogFormVisible:false,
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
					dzjlxbh:"03",
					zt:"0",
					lm:"",
					xhsbh:"",
					gps:"",
					azfsbhmc:"",
					xhbhmc:"",
					xhscj:"",
					azrq_vue:"",
					htr:"",
					dzjcblxbh:"04",
					xhsid:this.xhsid//消火栓id
				},
			}
		},
		methods: {
			tableRowClassName({row, rowIndex}){
				if(row.xhsbh==this.xhsdzjForm.xhsbh){
					return 'success-row';
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
				this.page=1;
				this.getxhsData_vue();
			},
			//选择消火栓按钮
			dzjxz_vue(){
				this.page=1;
				this.size=10;
				this.getxhsData_vue();
				this.xhslbDialogFormVisible=true;
			},
			//确认消火栓选择
			xhsselect_vue(index,row){
				this.xhsdzjForm.xhbhmc = row.xhbhmc;
				this.xhsdzjForm.azfsbhmc = row.azfsbhmc;
				this.xhsdzjForm.xhscj = row.xhscj;
				this.xhsdzjForm.gps = row.gps;
				this.xhsdzjForm.xhsbh = row.xhsbh;
				this.xhsid = row.id;
				this.xhslbDialogFormVisible=false;
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
			chooseSb_vue(index, row){
				this.page = 1;
				this.size = 10;
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
			xhspzHandle_vue() {
				let params = {
					id: this.xhsid
				}
				xhspzHandle(params).then((res) => {
					if (res.data != null) {
						this.xhsForm = res.data.row[0]
						this.xhspzDialogFormVisible = true
					}
				})
			},
			//消火栓参数验证
			checkdzjParams(){
				if (this.xhsdzjForm.xhsbh == "") {
					this.$message.error('请选择消火栓！');
					return false;
				}
				if (this.xhsdzjForm.lm == ""|| this.xhsdzjForm.lm==null) {
					this.$message.error('路名不可为空！');
					return false;
				}
				if(this.xhsdzjForm.azrq_vue==""||this.xhsdzjForm.azrq_vue==null){
					this.$message.error('填表日期不可为空！');
					return false;
				}

				return true;
			},
			//	保存点志记管理
			xhsdzjxxwh_vue(formName){
				// let checkdzjParams = this.checkdzjParams();
				// if(!checkdzjParams){
				// 	return;
				// }
				this.$refs[formName].validate((valid) => {
					if (valid) {
						let param={
							id:this.dzjid,
							dzjbh:this.xhsdzjForm.dzjbh,
							xhsid:this.xhsid,
							lm:this.xhsdzjForm.lm,
							azrq_vue:this.xhsdzjForm.azrq_vue,
							zt:this.xhsdzjForm.zt,
							htr:this.xhsdzjForm.htr,
							xmid:this.xmid,
							dzjlxbh:"03",
							dzjcbbh:"04",
							fbjj:"0",
						}
						let params = Object.assign(param);
						fmdzjxxwh(params).then((res)=>{
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
            //清空消火栓点志记
            clearXhsdzj(){
                this.xhsdzjForm={
                        id:"",
                        dzjbh:"",
                        dzjlxbh:"03",
                        zt:"0",
                        lm:"",
                        xhsbh:"",
                        azfsbhmc:"",
                        xhbhmc:"",
                        xhscj:"",
                        azrq_vue:"",
                        czrq_vue:"",
                        htr:"",
                        czrname:"",
                        dzjcblxbh:"04",
                        xhsid:this.xhsid//消火栓id
                }
            },
            //消火栓点志记数据回显
            xhsdzjData_vue(){
			  if(this.dzjid==""){
			      this.clearXhsdzj();
              }else{  
			      let params={
			          id : this.dzjid
                  }
                  xhsdzjData(params).then((res)=>{
                      this.xhsdzjForm.id = res.data.row[0].dzjid;
                      this.xhsdzjForm.dzjbh = res.data.row[0].dzjbh;
                      this.xhsdzjForm.zt = res.data.row[0].zt;
                      this.xhsdzjForm.lm = res.data.row[0].lm;
                      this.xhsdzjForm.xhsbh = res.data.row[0].xhsbh;
                      this.xhsdzjForm.azfsbhmc = res.data.row[0].azfsbhmc;
                      this.xhsdzjForm.xhbhmc = res.data.row[0].xhbhmc;
                      this.xhsdzjForm.xhscj = res.data.row[0].xhscj;
                      this.xhsdzjForm.azrq_vue = res.data.row[0].azrq_vue;
                      this.xhsdzjForm.czrq_vue = res.data.row[0].czrq_vue;
					  this.xhsdzjForm.gps = res.data.row[0].gps;
                      this.xhsdzjForm.htr = res.data.row[0].htr;
                      this.xhsdzjForm.czrname = res.data.row[0].czrname;
                      this.xhsid = res.data.row[0].id;
                      this.fjnumber = res.data.row[0].fjnumber;
                      this.path = res.data.row[0].path;
                  })
              }
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
								let formData = new FormData();
								formData.append("xmid",this.xmid);
								formData.append("dzjid",this.dzjid);
								formData.append("multipartFiles",file.raw);
								formData.append("filename",file.name);
								formData.append("fjlxej","xhsdzj");
								formData.append("fjnumber",this.fjnumber);
								saveDzjfile(formData).then((res) => {
									let msg = res.msg;
									if(msg == '上传成功'){
										this.$message({
											message: '上传成功',
											type: 'success'
										});
										this.path = res.path;
										this.dzjid = res.dzjid;
										this.fjnumber=1;
									}else{
										this.$message.error('上传失败！');
									}
									this.fileList=[];
								});
							}).catch(() => {
								return;
							});
						}else{
							let formData = new FormData();
							formData.append("xmid",this.xmid);
							formData.append("dzjid",this.dzjid);
							formData.append("multipartFiles",file.raw);
							formData.append("filename",file.name);
							formData.append("fjlxej","xhsdzj");
							formData.append("fjnumber",this.fjnumber);
							saveDzjfile(formData).then((res) => {
								let msg = res.msg;
								if(msg == '上传成功'){
									this.$message({
										message: '上传成功',
										type: 'success'
									});
									this.path = res.path;
									this.dzjid = res.dzjid;
									this.fjnumber=1;
								}else{
									this.$message.error('上传失败！');
								}
								this.fileList=[];
							});
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
			download(){//附件查看或下载
				let formData = new FormData();
				if(this.path==''){
					this.$message.warning('未上传任何文件！');
					return;
				}
				formData.append("path",this.path);
				download(formData).then((res) => { // 处理返回的文件流
					const content = res.data
					const blob = new Blob([content])
					let files = this.path.split("/");
					const fileName = files[files.length-1];
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
				})
			},
			look(){
				let formData = new FormData();
				if(this.path==''){
					this.$message.warning('未上传任何文件！');
					return;
				}
				this.slide1=[];
				formData.append("path",this.path);
				download(formData).then((res) => { // 处理返回的文件流
					let reader = new FileReader()
					reader.onload = (e) => {
						let img = new Image();
						img.src =e.target.result;
						let item = {};
						item.msrc = item.src = e.target.result;
						// this.Imgsrc = e.target.result;
						item.h = img.height;
						item.w = img.width;
						this.slide1.push(item);
						this.$preview.openList(0, this.slide1);
						//this.msgFlag = true;
						//this.prop.src = this.Imgsrc
					}
					reader.readAsDataURL(res.data)
				})
			},
			//    取消
			cancer(){
				this.$emit('childConcer', true);
			}
		},
		mounted() {
            this.xhsdzjData_vue();
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