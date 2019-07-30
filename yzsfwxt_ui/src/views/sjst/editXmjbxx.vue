<template>
	<section  >
		<el-card class="box-card" style="margin-top: 15px;">
			<div slot="header" class="clearfix">
				<div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">基本信息</div>
			</div>
			<el-form :model="filters"  ref="filters" class="demo-ruleForm"><!--:rules="xqrules"-->
				<el-form-item>
					<el-row>
						<el-col :span="3" style="text-align: right">
							<span>申请类型&nbsp;&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-input :disabled="true" v-model="filters.sqdlx" size="small" style="width:206px;" placeholder="输入申请编号" :readonly="true"></el-input>
						</el-col>
					</el-row>
				</el-form-item>
				<el-form-item>
					<el-row>
						<el-col :span="3" style="text-align: right">
							<span>申请编号&nbsp;&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-input :disabled="true" v-model="filters.sqbh" size="small" style="width:206px;" placeholder="输入申请编号" :readonly="true"></el-input>
						</el-col>
						<el-col :span="2" style="text-align: right">
							<span>安装类型&nbsp;&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-select v-if="filters.sqlx=='20'&&azlx=='0020202'" v-model="filters.azlx" size="small" style="width:206px;" placeholder="请选择">
								<el-option v-for="item in azList"
										   :key="item.VALUE"
										   :label="item.LABEL"
										   :value="item.VALUE"
										   :disabled="item.disabled"></el-option>
							</el-select>
							<el-select v-else v-model="filters.azlx" size="small" disabled style="width:206px;" placeholder="请选择">
								<el-option v-for="item in azList"
										   :key="item.VALUE"
										   :label="item.LABEL"
										   :value="item.VALUE"></el-option>
							</el-select>
						</el-col>
						<el-col :span="2" style="text-align: right">
							<span>受理日期&nbsp;&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-date-picker
									:clearable="false"
									:editable="false"
									v-model="filters.slrq"
									type="date"
									:picker-options="pickerOptionsStart"
									format="yyyy-MM-dd"
									value-format="yyyy-MM-dd"
									placeholder="选择日期" size="small">
							</el-date-picker>
						</el-col>
					</el-row>
				</el-form-item>
				<el-form-item>
					<el-row>
						<el-col :span="3" style="text-align: right">
							<span>所属区域&nbsp;&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-select v-model="filters.ssqy" size="small" style="width:206px;" placeholder="请选择">
								<el-option v-for="item in qyList"
										   :key="item.VALUE"
										   :label="item.LABEL"
										   :value="item.VALUE"></el-option>
							</el-select>
						</el-col>
						<el-col :span="2" style="text-align: right">
							<span>工程地址&nbsp;&nbsp;</span>
						</el-col>
						<el-col :span="12">
							<el-form-item prop="gcdz" :rules="this.publicUtil.notNull">
								<el-input v-model="filters.gcdz" size="small"  placeholder="输入工程地址"></el-input>
							</el-form-item>
						</el-col>
					</el-row>
				</el-form-item>
				<el-form-item>
					<el-row>
						<el-col :span="3" style="text-align: right">
							<span>项目名称&nbsp;&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-form-item prop="xmmc" :rules="this.publicUtil.notNull">
								<el-input v-model="filters.xmmc" size="small" placeholder="输入项目名称"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="2" style="text-align: right">
							<span>工程名称&nbsp;&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-input :disabled="true" v-model="filters.gcmc" size="small"  placeholder="输入工程名称"></el-input>
						</el-col>
						<el-col :span="2" style="text-align: right">
							<span>报建单位&nbsp;&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-form-item prop="bjdw" :rules="this.publicUtil.notNull">
								<el-input v-model="filters.bjdw" size="small"  placeholder="输入报建单位"></el-input>
							</el-form-item>
						</el-col>
					</el-row>
				</el-form-item>
				<el-form-item>
					<el-row>
						<el-col :span="3" style="text-align: right">
							<span>联系人&nbsp;&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-form-item prop="lxr" :rules="this.publicUtil.notNull">
								<el-input v-model="filters.lxr" size="small" style="width:206px;" placeholder="输入联系人"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="2" style="text-align: right">
							<span>移动电话&nbsp;&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-form-item prop="lxdh" :rules="this.publicUtil.phone">
								<el-input v-model="filters.lxdh" size="small" style="width:206px;" placeholder="输入联系电话"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="2" style="text-align: right">
							<span>办公电话&nbsp;&nbsp;</span>
						</el-col>
						<el-col :span="5">
							<el-form-item>
								<el-input v-model="filters.zjlxdh" size="small" style="width:206px;" placeholder="输入办公电话"></el-input>
							</el-form-item>
						</el-col>
					</el-row>
				</el-form-item>
				<el-form-item v-if="showJm">
					<el-col :span="3" style="text-align: right">
						<span>总建筑面积&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-form-item prop="zjzmj">
							<el-input :disabled="true" v-model="filters.zjzmj" size="small" style="width:206px;" placeholder="输入总建筑面积"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>住宅面积&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-form-item prop="zzmj">
							<el-input :disabled="true" v-model="filters.zzmj" size="small" style="width:206px;" placeholder="输入住宅面积"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>地块简称&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-input :disabled="true" v-model="filters.dkjc" size="small" style="width:206px;" placeholder="输入地块简称"></el-input>
					</el-col>
				</el-form-item>
				<el-form-item v-if="showJm">
					<el-col :span="3" style="text-align: right">
						<span>户数&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-form-item prop="hs">
							<el-input :disabled="true" v-model="filters.hs" size="small" style="width:206px;" placeholder="输入户数"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>设计范围&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="12">
						<el-input :disabled="true" v-model="filters.sjfw" size="small" placeholder="输入设计范围"></el-input>
					</el-col>
				</el-form-item>
				<el-form-item v-if="showJm">
					<el-col :span="3" style="text-align: right">
						<span>单元楼号&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-input :disabled="true" v-model="filters.dylh" size="small" style="width:206px;" placeholder="输入单元楼号"></el-input>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>规划泵房数&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-form-item prop="ghbfs">
							<el-input :disabled="true" v-model="filters.ghbfs" size="small" style="width:206px;" placeholder="输入规划泵房数"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>交房时间&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-date-picker
								:disabled="true"
								v-model="filters.jfsj"
								type="date"
								format="yyyy-MM-dd"
								value-format="yyyy-MM-dd"
								placeholder="选择日期" size="small" style="width: 207px;">
						</el-date-picker>
					</el-col>
				</el-form-item>
				<el-form-item v-if="showJm">
					<el-col :span="3" style="text-align: right">
						<span>分期情况&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-input :disabled="true" v-model="filters.fqqk" size="small" style="width:206px;" placeholder="输入分期情况"></el-input>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>房屋性质&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-select v-model="filters.fwxz" size="small" style="width:206px;" placeholder="请选择" :disabled="true">
							<el-option v-for="item in fwxzList"
									   :key="item.VALUE"
									   :label="item.LABEL"
									   :value="item.VALUE"></el-option>
						</el-select>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>用户性质&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-input :disabled="true" v-model="filters.yhxz" size="small" style="width:206px;" placeholder="输入用户性质"></el-input>
					</el-col>
				</el-form-item>
				<el-form-item v-if="showGs">
					<el-col :span="3" style="text-align: right">
						<span>意向接表数量&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-form-item>
							<el-input :disabled="true" v-model="filters.yxjbsl" size="small" style="width:206px;" placeholder="输入意向接表数量"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>接水用途&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-input :disabled="true" v-model="filters.jsyt" size="small" style="width:206px;" placeholder="输入接水用途"></el-input>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>用户性质&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-input :disabled="true" v-model="filters.yhxz" size="small" style="width:206px;" placeholder="输入用户性质"></el-input>
					</el-col>
				</el-form-item>
				<el-form-item v-if="showGs">
					<el-col :span="3" style="text-align: right">
						<span>允许开工时间&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-date-picker
								:disabled="true"
								v-model="filters.jhkgsj"
								type="date"
								format="yyyy-MM-dd"
								value-format="yyyy-MM-dd"
								placeholder="选择日期" size="small" >
						</el-date-picker>
					</el-col>
				</el-form-item>
				<el-form-item v-if="showHg">
					<el-col :span="3" style="text-align: right">
						<span>用水方式&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-select :disabled="true" v-model="filters.ysfs" size="small" style="width:206px;" placeholder="请选择">
							<el-option label="市政直供" value="市政直供"></el-option>
							<el-option label="二次加压" value="二次加压"></el-option>
							<el-option label="楼顶水箱" value="楼顶水箱"></el-option>
						</el-select>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>用水状况&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-select :disabled="true" v-model="filters.yszk" size="small" style="width:206px;" placeholder="请选择">
							<el-option label="良好" value="良好"></el-option>
							<el-option label="一般" value="一般"></el-option>
							<el-option label="差" value="差"></el-option>
						</el-select>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>楼顶水箱&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-form-item prop="ldsx" :rules="this.publicUtil.NullOrInteger">
							<el-input v-model="filters.ldsx" size="small" style="width:206px;" :disabled="true"
									  placeholder="输入楼顶水箱"></el-input>
						</el-form-item>
					</el-col>
				</el-form-item>
				<el-form-item v-if="showHg">
					<el-col :span="3" style="text-align: right">
						<span>楼房栋数&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-form-item prop="lfds">
							<el-input :disabled="true" v-model="filters.lfds" size="small" style="width:206px;" placeholder="输入楼房栋数"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>楼房户数&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-form-item prop="lfhs">
							<el-input :disabled="true" v-model="filters.lfhs" size="small" style="width:206px;" placeholder="输入楼房户数"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>平房户数&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-form-item prop="pfhs">
							<el-input :disabled="true" v-model="filters.pfhs" size="small" style="width:206px;" placeholder="输入平房户数"></el-input>
						</el-form-item>
					</el-col>
				</el-form-item>
				<el-form-item v-if="showHg">
					<el-col :span="3" style="text-align: right">
						<span>最高层数&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-form-item>
							<el-input :disabled="true" v-model="filters.zgcs" size="small" style="width:206px;" placeholder="输入最高层数（层）"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>内部消防&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-select :disabled="true" v-model="filters.nbxf" size="small" style="width:206px;" placeholder="请选择">
							<el-option label="有" value="0"></el-option>
							<el-option label="无" value="1"></el-option>
						</el-select>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>车库用水&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-form-item prop="ckys">
							<el-input :disabled="true" v-model="filters.ckys" size="small" style="width:206px;" placeholder="输入车库用水（间）"></el-input>
						</el-form-item>
					</el-col>
				</el-form-item>
				<el-form-item v-if="showHg">
					<el-col :span="3" style="text-align: right">
						<span>车棚用水&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-form-item prop="cpys">
							<el-input :disabled="true" v-model="filters.cpys" size="small" style="width:206px;" placeholder="输入自行车棚用水（个）"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>门面用水&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-form-item prop="mmys">
							<el-input :disabled="true" v-model="filters.mmys" size="small" style="width:206px;" placeholder="输入经营门面用水（户）"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>公厕用水&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-form-item prop="gcys">
							<el-input :disabled="true" v-model="filters.gcys" size="small" style="width:206px;" placeholder="输入公厕用水（所）"></el-input>
						</el-form-item>
					</el-col>
				</el-form-item>
				<el-form-item v-if="showHg">
					<el-col :span="3" style="text-align: right">
						<span>物管用水&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-form-item prop="wgys">
							<el-input :disabled="true" v-model="filters.wgys" size="small" style="width:206px;" placeholder="输入物管办公房用水（处）"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>其他用水&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-input :disabled="true" v-model="filters.qtys" size="small" style="width:206px;" placeholder="输入其他用水"></el-input>
					</el-col>
					<el-col :span="2" style="text-align: right">
						<span>小区名称&nbsp;&nbsp;</span>
					</el-col>
					<el-col :span="5">
						<el-input :disabled="true" v-model="filters.xqmc" size="small" style="width:206px;" placeholder="输入小区名称"></el-input>
					</el-col>
				</el-form-item>
			</el-form>
		</el-card>
		<el-col :span=24>
			<el-card class="box-card" style="margin-top: 15px;" v-loading="loading" element-loading-text="文件上传中，请稍后" element-loading-spinner="el-icon-loading" element-loading-background="rgba(0, 0, 0, 0.1)">
				<div slot="header" class="clearfix">
					<div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">资料上传</div>
				</div>
				<el-table :data="zllx_data"  :stripe="true" style="width: 100%;" >
					<el-table-column prop="codename" label="资料类型"  align="center">
					</el-table-column>
					<el-table-column prop="count" label="资料数量"  align="center">
					</el-table-column>
					<el-table-column label="操作" align="center">
						<template slot-scope="scope" >
						<el-row>
						<el-col :span="10" style="text-align: right">
						<el-upload

						class="upload-demo"
						ref="upload"
						:multiple="true"
						:show-file-list='false'
						action="#"
						:http-request="submitUpload"
						:on-preview="handlePreview"
						:on-change="handleChange"
						:on-remove="handleRemove"
						accept=".jpg,.jpeg,.png"
						:file-list="fileList">
						<label title="上传"><i @click="chooseFileClick(scope.$index, scope.row)" class="iconfont icon-shangchuan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
						</el-upload>
						</el-col>
						<el-col :span="4" style="text-align: center">
						<label title="拍摄">
						<i class="iconfont icon-paishe" @click="openVisible(scope.$index, scope.row)"
						style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
						</label>

						</el-col>
						<el-col :span="10" style="text-align: left">
						<label title="详情">
						<i class="iconfont icon-xiangqing"
						@click="toDetial_vue(scope.$index, scope.row)"
						style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
						</label>
						</el-col>
						</el-row>
						</template>
					</el-table-column>
				</el-table>

			</el-card>
			<el-row style="text-align: center;margin-top: 25px;">

				<el-button size="mini" type="primary" :loading="submitLoading" @click="handleAdd_vue">
					<i class="iconfont icon-queren"></i>保存</el-button>
			</el-row>
		</el-col>
		<!--项目资料下载界面-->
		<el-dialog title="项目资料下载" :visible.sync="dialogTableVisible" :modal-append-to-body="false" style="padding-bottom: 10px" max-height="400px">
			<el-row>
				<el-col>
					<el-table :data="gridData" v-loading="gridblistLoading" :stripe="true" style="padding-bottom: 10px">
						<el-table-column property="FJMC" label="附件名称" width="200" align="center"></el-table-column>
						<el-table-column property="SCSJ" label="上传时间" width="200" align="center"></el-table-column>
						<el-table-column property="USERNAME" label="上传人" width="100" align="center"></el-table-column>
						<el-table-column property="address" label="操作" width="200" align="center">
							<template slot-scope="scope">
								<el-row>
									<el-col>
										<label title="查看">
											<i class="iconfont icon-yulan"  @click="downloadFile_vue(scope.$index, scope.row)"
											   style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
										</label>
										<label title="删除">
											<i class="iconfont delete"  @click="deleteFj_vue(scope.$index, scope.row)"
											   style="cursor:pointer;padding: 2px;color: #f78989"></i>
										</label>
									</el-col>
								</el-row>
							</template>
						</el-table-column>
					</el-table>
				</el-col>
				<el-col>
					<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[5, 10, 20, 30]"
								   @current-change="handleCurrentChange" @size-change="handleSizeChange"
								   :page-size="this.zlxxSize" style="float:right;" :total="this.zlxxTotal">
					</el-pagination>
				</el-col>
			</el-row>
		</el-dialog>

		<!--拍摄-->
		<el-dialog title="拍摄" :visible.sync = "creamVisible" top="5vh">
			<DocCamera v-bind:dirName ="dirName" v-bind:fileName ="fileName" v-on:listenToChildEvent="showMsg"></DocCamera>
		</el-dialog>
	</section>
</template>

<script>
	import DocCamera from '@/views/template/DocCamera.vue';
	import '../../assets/iconfont/iconfont.css'
	import {queryUser,getScwj,download} from '../../api/xqsl';
	import {getXmjbxxById,initTble,importFile_xqsl,saveImageToFj_xqsl,toDetial_xqsl,deleteFj_xqsl,handleAdd} from '../../api/sjst';
	let zlType="";
	export default {
		props:{
			xmid:{
				type: String,
				required: true
			}
		},
		data() {
			return {
				pickerOptionsStart: {
					disabledDate (time) {
						return time.getTime() > new Date(new Date().toLocaleDateString()).getTime();
					}
				},
				azlx:"",
				filters: {
					sqlx: '',sqdlx:'',fwxz:'',xmmc:'',slazlx:'',
					sqbh: '',azlx: '',slrq: '',ssqy: '',lxr: '',lxdh: '',gcmc: '',bjdw: '',yhxz: '',gcdz: '',xqmc:'',

					zjzmj: '',zzmj: '',dkjc:'',hs:'',sjfw:'',dylh:'',ghbfs:'',jfsj:'',fqqk:'',jhkgsj:'',zjlxdh:'',

					yxjbsl: '',jsyt: '',yhh: '',ybkj: '',

					ysfs:'',yszk:'',hxzk:'',lfds:'',lfhs:'',pfhs:'',zgcs:'',nbxf:'',ckys:'',cpys:'',mmys:'',
					gcys:'',wgys:'',qtys:'',ldsx:'',
				},
				/*拍摄*/
				fileName: 'xqsl',//给子组件的文件名字
				dirName: '',//保存图片的文件夹名字
				azList:[],//安装类型下拉框数据集合
				qyList:[],
				zllx_data:[],
				fileIds:[],//已上传的资料ID集合
				showHg: false,
				showJm:false,
				showGs:false,
				creamVisible:false,
				submitLoading:false,
				newFileList:[],
				loading:false,
				zlxxPage:1,
				zlxxSize:5,
				zlxxTotal:0,
				/*资料上传及拍摄*/
				zlType:'',//选中资料类型
				fileList:[],//已选中文件
				zlrow:[],//已上传资料集合
				dialogTableVisible:false,//是否打开资料详情界面
				gridData:[],//已上传资料详情集合
				gridblistLoading:false,
				slide1:[],
				sqid:'',
				fwxzList:[],//房屋性质下拉框数据集合
			}
		},
		methods: {
			handleAdd_vue(){
				let params={
					id:this.xmid,
					sqlx:this.filters.sqlx,
					azlx:this.filters.azlx,
					slazlx:this.filters.slazlx,
					slsj:this.filters.slrq,
					xmdz_xzqybh:this.filters.ssqy,
					xmdz:this.filters.gcdz,
					xmmc:this.filters.xmmc,
					dwmc:this.filters.bjdw,
					lxr:this.filters.lxr,
					lxrsjh:this.filters.lxdh,
					lxdh:this.filters.zjlxdh
				}
				this.$refs.filters.validate((valid) => {
					if (valid) {
						if(this.filters.slazlx!=this.filters.azlx){
							this.$confirm('您修改了安装类型，以后将无法再次更改，是否继续保存?', '提示', {
								type: 'warning'
							}).then(() => {
								handleAdd(params).then((res)=>{
									if(res.data){
										this.$message({
											message: '保存成功',
											type: 'success'
										});
										this.getXmjbxxById_vue();
										this.initTble_vue();
									}else{
										this.$message({
											message: '保存失败',
											type: 'error'
										});
									}
								})
							})
						}else{
							handleAdd(params).then((res)=>{
								if(res.data){
									this.$message({
										message: '保存成功',
										type: 'success'
									});
								}else{
									this.$message({
										message: '保存失败',
										type: 'error'
									});
								}
							})
						}


					}
				});

			},
			getXmjbxxById_vue(type) {
				let params = {
					xmid:this.xmid
				};
				getXmjbxxById(params).then((res) => {
					if(type == 1){

					}else {
						this.filters.sqlx = res.data.row.typeid;
						this.filters.sqdlx = res.data.row.type;
						this.filters.sqbh = res.data.row.sqbh;
						this.filters.slrq = res.data.row.slrq_vue;
						this.filters.azlx = res.data.row.xm_azlx;
						this.filters.slazlx=res.data.row.azlx;
						this.azlx = res.data.row.xm_azlx;
						this.filters.ssqy = res.data.row.ssqy;
						this.filters.lxr = res.data.row.xm_lxr;
						this.filters.lxdh = res.data.row.xm_lxrsjh;
						this.filters.zjlxdh = res.data.row.lxdh;
						this.filters.gcmc = res.data.row.xmmc;
						this.filters.xmmc = res.data.row.xm_xmmc;
						this.filters.bjdw = res.data.row.xm_dwmc;
						this.filters.yhxz = res.data.row.yhxz;
						this.filters.gcdz = res.data.row.xm_xmdz;
						this.updateId = res.data.row.id;
						if (res.data.row.typeid == '10') {
							this.showJm = true;
							this.sbazjbxx = true;
							this.filters.zjzmj = res.data.row.zjzmj;
							this.filters.zzmj = res.data.row.zzjzmj;
							this.filters.dkjc = res.data.row.dkjc;
							this.filters.hs = res.data.row.hs;
							this.filters.sjfw = res.data.row.sjfw;
							this.filters.dylh = res.data.row.dylh;
							this.filters.ghbfs = res.data.row.ghbfs;
							this.filters.jfsj = res.data.row.xjfsj;
							this.filters.fqqk = res.data.row.fqqk;
							this.filters.fwxz = res.data.row.fwxz;
							this.sqid = "10";
						} else if (res.data.row.typeid == '20') {
							this.showGs = true;
							this.sbazjbxx = false;
							this.filters.jhkgsj = res.data.row.jhkgsj_str;
							this.filters.yxjbsl = res.data.row.yxazsl;
							this.filters.jsyt = res.data.row.jsyt;
							this.filters.yhh = res.data.row.yhh;
							this.filters.ybkj = res.data.row.ybkj;
							this.sqid = "20";
						} else {
							this.showHg = true;
							this.sbazjbxx = false;
							this.filters.xqmc = res.data.row.xqmc;
							this.filters.ysfs = res.data.row.xysfs;
							this.filters.yszk = res.data.row.xyszk;
							this.filters.hxzk = res.data.row.xhxzk;
							this.filters.ldsx = res.data.row.ldsx;
							this.filters.lfds = res.data.row.lfds;
							this.filters.lfhs = res.data.row.lfhs;
							this.filters.pfhs = res.data.row.pfhs;
							this.filters.zgcs = res.data.row.zgcs;
							this.filters.nbxf = res.data.row.nbxf == null ? '' : '' + res.data.row.nbxf;
							this.filters.ckys = res.data.row.ckyss;
							this.filters.cpys = res.data.row.cpyss;
							this.filters.mmys = res.data.row.mmyss;
							this.filters.gcys = res.data.row.gcyss;
							this.filters.wgys = res.data.row.wyyss;
							this.filters.qtys = res.data.row.qtys;
							this.sqid = "30";
						}}
						this.dirName=res.data.xmbh;
						let param = {
							id: res.data.row.id,
							sqlx: res.data.row.typeid
						};
						getScwj(param).then((res) => {
							// this.zllx_data = res.data.sclx;
							this.qyList = res.data.qyList;
							if(this.filters.sqlx=='20'&&this.filters.azlx=='0020202'){
								for(var i =0;i<res.data.azList.length;i++){
									if(res.data.azList[i].VALUE=='0020203'||res.data.azList[i].VALUE=='0020204'){
										res.data.azList[i]["disabled"]=true;
									}
								}
							}
							this.azList = res.data.azList;
							// this.zlrow = res.data.zlrow;
							// let fileCount = res.data.files;
							// let vm = this;
							// for (let i = 0; i < fileCount.length; i++) {
							// 	let files = fileCount[i].value;
							// 	let type = fileCount[i].type;
							// 	let fileList = [];
							// 	if (files) { //后台返回的文件链接
							// 		let a = (files).split(',');
							// 		if (a.length > 0) {
							// 			a.forEach(item => {
							// 				var obj = {};
							// 				let index = item.lastIndexOf('\/');
							// 				let fileName = item.substring(index + 1, item.length); //最后的文件名截取出来
							// 				vm.$set(obj, 'name', fileName);
							// 				vm.$set(obj, 'url', item);  //修改files里面的结构（name,url）
							// 				fileList.push(obj);
							// 				this.fileIds = this.fileIds.concat(fileCount[i].id);
							// 			})
							// 		}
							// 	}
							// }
						});
					});

			},
			// init(){//初始化参数
			// 	let JSONstr = sessionStorage.getItem("user");
			// 	let JSON = eval("("+JSONstr+")");
			// 	//console.log(JSON);
			// 	//console.log(JSON.username);
			// 	this.dirName = JSON.id;//文件夹名字
			// },
			openVisible(index, row){
				zlType = row.codevalue;
				if (zlType == "" || zlType == null) {
					this.$message.error('请选择资料类型！');
				} else {
					this.creamVisible = true;
				}
			},

			initTble_vue(){
				let params = {
					xmid: this.xmid,
				};
				initTble(params).then((res) => {
					this.zllx_data = res.data.row;
				});
			},
			chooseFileClick(index,row){
				zlType = row.codevalue;
			},
			showMsg:function(data){//存储子组件保存的图片名称
				this.zlType = zlType;
				let params = {sqlx:this.sqid,operation:this.zlType,dirName:this.dirName,
					fileName:data,xmid:this.xmid};
				saveImageToFj_xqsl(params).then((res) => {
					let msg = res.data.msg;
					if(msg == '上传成功'){
						this.$message({
							message: '上传成功',
							type: 'success'
						});
						let fileId = res.fileId;
						this.fileIds = this.fileIds.concat(fileId);
						this.initTble_vue();
					}else{
						this.$message.error('上传失败！');
					}
					this.creamVisible = false;
				});
			},
			//获取当前用户
			findUser(){
				let param = {
					type:'fwxz'
				};
				queryUser(param).then((res)=>{
					this.user =  res.data.user;
					this.kjList = res.data.kjList;
					this.ysxzList = res.data.ysxzList;
					this.hyflList = res.data.hyflList;
					this.fwxzList = res.data.fwxzList;
				})
			},
			submitUpload() {
				if(zlType==""||zlType==null){
					this.$message.error('请选择资料类型！');
					this.fileList=[];
					return;
				}
				let fileList = this.fileList;
				this.zlType=zlType;
				if (fileList !=null){
					// for (let i=0;i<fileList.length;i++){
						let file = fileList[fileList.length-1];
						const isLt50M = file.size / 1024 / 1024 > 50;
						if(isLt50M){
							this.$message.error('上传文件大于50M，上传失败！');
							return;
						}
					this.loading=true;
						let formData = new FormData();
						formData.append("multipartFiles",file.raw);
						formData.append("filename",file.name);
						formData.append("zlType",this.zlType);
						formData.append("xmid",this.xmid);
						importFile_xqsl(formData).then((res) => {
							this.loading=false;
							let msg = res.data.msg;
							if(msg == '上传成功'){
								this.$message({
									message: '上传成功',
									type: 'success'
								});
								// let fileId = res.fileId;
								// this.fileIds = this.fileIds.concat(fileId);
								this.fileList=[];
								this.initTble_vue();
							}else{
								this.$message.error('上传失败！');
							}
						});
					// }
				}
			},
			handleRemove(file, fileList) {
			},
			handlePreview(file) {
			},
			handleChange(file,fileList){
				this.fileList = fileList;
			},
			//打开附件详情窗口
			toDetial_vue(index, row) {
				this.dialogTableVisible = true;
				this.gridblistLoading = true;
				if(row == 2){

				}else{
					this.yjfjlx = row.codevalue;
					this.ejfjlx = row.parentid;
				}
				let params = {
					ejfjlx: this.yjfjlx,
					yjfjlx: this.ejfjlx,
					sqlx: this.sqid,
					xmid:this.xmid,
					page: this.zlxxPage,
					size: this.zlxxSize
				};
				toDetial_xqsl(params).then((res) => {
					if (res.data != null && res.data.row != null) {
						if(res.data.total>0&&res.data.row.length==0){
							this.zlxxPage = this.zlxxPage-1;
							let detialRow={
								codevalue: this.yjfjlx,
								parentid: this.ejfjlx
							};
							this.toDetial_vue("", detialRow);
						}
						this.gridData = res.data.row;
						this.zlxxTotal = res.data.total;
						this.gridblistLoading = false;
					}
				})
			},
			handleCurrentChange(val) {
				this.zlxxPage = val;
				this.toDetial_vue(1,2);
			},
			handleSizeChange(val) {
				this.zlxxSize = val;
				this.zlxxPage = 1;
				this.toDetial_vue(1,2);
			},
			//删除附件
			deleteFj_vue(index,row){
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					let params = {
						id: row.ID,
					};
					deleteFj_xqsl(params).then((res) => {
						if(res.data){
							this.$message({
								message: '删除成功',
								type: 'success'
							});
							// if(res.data.sfjy == "1"){
							// 	this.toDetial_vue(-1, detialRow);
							// }else{
							// 	this.toDetial_vue(-2, detialRow);
							// }
							let detialRow={
								codevalue: row.FJLXEJ,
								parentid: row.FJLXYJ
							};
							this.toDetial_vue("", detialRow);
							this.initTble_vue();
						}else{
							this.$message.error('删除失败！');
						}
					});
				})
			},
			//查看图片
			downloadFile_vue(index,row) {
				let formData = new FormData();
				formData.append("path", row.FJLJ);
				formData.append("sqlx", this.sqid);
				download(formData).then((res) => {
					this.slide1 = [];
					let reader = new FileReader();
					reader.onload = (e) => {
						let item = {};
						let img = new Image();
						item.msrc = item.src = e.target.result;
						img.src =e.target.result;
						item.h = img.height;
						item.w = img.width;
						this.slide1.push(item);
						this.$preview.openList(0, this.slide1);
					};
					reader.readAsDataURL(res.data)
				});
			},
		},
		mounted() {
			this.initTble_vue();
			this.findUser();
			this.getXmjbxxById_vue();
			// this.init();
		},
		components: {
			'DocCamera':DocCamera
		}
	}

</script>
<style>
	.el-input.is-disabled .el-input__inner {
		background-color: #F5F7FA;
		border-color: #E4E7ED;
		color: #303133;
		cursor: not-allowed;
	}
	.toolbar {
		background: #ffffff;
		 /*padding: 10px;*/
		margin: 10px 0px;
	}
	.el-table th{
		background-color: #f5f7fa;
	}
</style>
