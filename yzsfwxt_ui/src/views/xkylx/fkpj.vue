<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<el-row>
			<el-col :span="24">
				<!--红黄绿灯时间-->
				<hhldsx :xmid="xmid" :activityid="activityId"></hhldsx>
				<!--项目基本信息模板-->
				<XMJBXX></XMJBXX>
				<!--配合联合勘查的部门-->
				<el-card class="box-card"  style="margin-top: 15px;">
					<div slot="header" class="clearfix">
						<div class="div_title">勘查到场情况</div>
					</div>
					<div class="text item">
						<el-row class="rowTitle">
							<el-col :span="4" >勘查部门</el-col>
							<el-col :span="3" >参与人</el-col>
							<el-col :span="3" >联系方式</el-col>
							<el-col :span="4">是否要求到场</el-col>
							<el-col :span="4">是否到场参与</el-col>
							<el-col :span="4">勘查反馈时间</el-col>
						</el-row>
						<el-row class="rowTitle" v-model="kcbm" v-for="o in kcbm" :key="o.id">
							<el-col :span="4" v-if="o.kcbm == 1"><span class="rowText">设计院</span></el-col>
							<el-col :span="4" v-if="o.kcbm == 2"><span class="rowText">施工单位</span></el-col>
							<el-col :span="4" v-if="o.kcbm == 3"><span class="rowText">区所</span></el-col>
							<el-col :span="4" v-if="o.kcbm == 4"><span class="rowText">预算科</span></el-col>
							<el-col :span="3"><span class="rowText">{{o.username}}</span></el-col>
							<el-col :span="3"><span class="rowText">{{o.mobilephone}}</span></el-col>
							<el-col :span="4" v-if="o.sfxydc == 1"><span class="rowText"><el-checkbox checked></el-checkbox></span></el-col>
							<el-col :span="4" v-if="o.sfxydc == 0 || o.sfxydc == ''"><span class="rowText"><el-checkbox v-model="checked"></el-checkbox></span></el-col>
							<el-col :span="4" v-if="o.sfxydc == 0 || o.sfxydc == '' || o.sfdc == null"><span class="rowText">未到场</span></el-col>
							<el-col :span="4" v-if="o.sfxydc == 1 && o.sfdc ==1"><span class="rowText">已到场</span></el-col>
							<el-col :span="4">
								<el-date-picker v-model="o.fksj_vue" type="datetime" placeholder="" size="small" value-format="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd HH:mm:ss" readonly></el-date-picker>
							</el-col>
						</el-row>
					</div>
				</el-card>

				<!--区所反馈-->
				<el-card class="box-card"  style="margin-top: 15px;" v-if="qsfk">
					<div slot="header" class="clearfix">
						<div class="div_title">区所反馈</div>
					</div>
					<div class="text item">
						<el-table :data="qsFk" border style="width: 100%;margin-top: 10px;">
							<el-table-column type="expand">
								<template slot-scope="props">
									<el-form label-position="left" :inline="true" class="demo-table-expand">
										<el-form-item label="调查项分类">
											<span v-if="props.row.dcnr == 0">项目周边管网</span>
											<span v-if="props.row.dcnr == 1">具体位置测压</span>
										</el-form-item>
										<el-form-item label="">
											<span class="spanText">管线在主路的</span>
											<span>{{props.row.zlwz_str}} </span>
											<span class="spanText">侧，距主路中线 </span>
											<span>{{props.row.gxjzl}}</span>
											<span class="spanText">米，正上方覆盖物</span>
											<span> {{props.row.zsffgw_str}} </span>
											<span class="spanText">。</span>
										</el-form-item>
										<el-form-item label="线路主路">
											<span>{{props.row.gxzl}}</span>
										</el-form-item>
										<el-form-item label="管线路段">
											<span>{{props.row.gxld}}</span>
										</el-form-item>
										<el-form-item label="管线口径">
											<span>{{props.row.kj_str}}</span>
										</el-form-item>
										<el-form-item label="管线材质">
											<span>{{props.row.gxcz_str}}</span>
										</el-form-item>
										<el-form-item label="测压对象">
											<span>{{props.row.cydx}}</span>
										</el-form-item>
										<el-form-item label="测压时间">
											<span>{{props.row.cysj_str}}</span>
										</el-form-item>
										<el-row>
											<el-form-item label="调查点位置">
												<span>{{props.row.dcdwz}}</span>
											</el-form-item>
										</el-row>

										<el-form-item label="测压点压力">
											<span>{{props.row.cydyl}}</span>&nbsp;<span class="itext">Mpa</span>
										</el-form-item>
										<el-form-item label="测压点标高">
											<span>{{props.row.cydbg}}</span>&nbsp;<span class="itext">米</span>
										</el-form-item>

										<el-form-item label="并网通水">
											<span v-if="props.row.bwts == 0">否</span>
											<span v-if="props.row.bwts == 1">是</span>
										</el-form-item>

										<el-form-item label="管网移交">
											<span v-if="props.row.gwyj == 0">否</span>
											<span v-if="props.row.gwyj == 1">是</span>
										</el-form-item>

										<el-form-item label="调查人">
											<span>{{props.row.dcr}}</span>
										</el-form-item>

										<el-form-item label="调查日期">
											<span>{{props.row.dcrq_str}}</span>
										</el-form-item>
										<el-row>
											<el-form-item label="备注">
												<span>{{props.row.bz}}</span>
											</el-form-item>
											<el-form-item label="测压点深度">
												<span>{{props.row.cydsd}}</span>
											</el-form-item>
										</el-row>
									</el-form>
								</template>
							</el-table-column>
							<el-table-column type="index" label="序号" width="150" align="center">
							</el-table-column>
							<el-table-column property="dcxnr" label="调查内容" width="200" align="center" :formatter="formatDcnr">
							</el-table-column>
							<el-table-column property="dcr" label="调查人" width="200" align="center">
							</el-table-column>
							<el-table-column property="dcrq_str" label="调查日期" min-width="180" align="center">
							</el-table-column>
						</el-table>

						<el-card class="box-card"  style="margin-top: 20px;">
							<span>资料附件</span>
							<div class="text item">
								<el-table :data="qsFj" border style="width: 100%;">
									<el-table-column type="index" label="序号" width="80" align="center">
									</el-table-column>
									<el-table-column property="fjmc" label="附件名称" width="200" align="center">
									</el-table-column>
									<el-table-column property="fjhz" label="文件类型" width="100" align="center">
									</el-table-column>
									<el-table-column property="fjlx_str" label="附件类型" width="180" align="center">
									</el-table-column>
									<el-table-column property="scr_str" label="上传人" width="150" align="center">
									</el-table-column>
									<el-table-column property="scsj_str" label="上传时间" min-width="180" align="center">
									</el-table-column>
									<el-table-column property="xmdz" label="操作"  width="180" align="center">
										<template slot-scope="scope">
											<div v-if="scope.row.fjhz=='png'||scope.row.fjhz=='jpg'">
												<label title="查看" @click="downloadFile_vue(scope.$index, scope.row,1)">
													<i class="iconfont icon-yulan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
											</div>
											<div v-else>
												<label title="下载" @click="downloadFile_vue(scope.$index, scope.row,2)">
													<i class="iconfont icon-xiazai" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
											</div>
										</template>
									</el-table-column>
								</el-table>
							</div>
						</el-card>
					</div>
					<el-row style="margin-top: 20px;">
						<el-col :span="24">
							<el-form>
								<el-form-item label="区所反馈意见" style="width: 100%">
									<el-input type="textarea" v-model="qsFkyj" readonly></el-input>
								</el-form-item>
							</el-form>
						</el-col>
					</el-row>
				</el-card>

				<!--施工单位反馈-->
				<el-card class="box-card"  style="margin-top: 15px;" v-if="sgdwfk">
					<div slot="header" class="clearfix">
						<div class="div_title">施工单位反馈</div>
					</div>
					<div class="text item">
						<el-form :model="sgdwFk" label-width="150px" ref="xmxxForm" >
							<el-form-item label="">
								<el-row>
									<el-col :span="4">
										<el-checkbox v-model="sgdwFk.sfycgc" true-label="1" false-label="0">超高层</el-checkbox>
									</el-col>
									<el-col :span="10">
										<el-form-item label="">
											泵房供水位置
											<el-input type="text" size="small" v-model="sgdwFk.bfgswz" style="width: 180px;" readonly></el-input>
										</el-form-item>
									</el-col>
								</el-row>
							</el-form-item>
							<el-form-item label="">
								<el-row>
									<el-col :span="4">
										<el-checkbox v-model="sgdwFk.sfyldsx" true-label="1" false-label="0">楼顶水箱</el-checkbox>
									</el-col>
									<el-col :span="10" style="text-align: left;">
										<el-form-item label="">
											楼顶水箱位置
											<el-input type="text" size="small" v-model="sgdwFk.ldsxwz" style="width: 180px;" readonly></el-input>
										</el-form-item>
									</el-col>
								</el-row>
							</el-form-item>
							<el-form-item label="">
								<el-row>
									<el-col :span="4">
										<el-checkbox v-model="sgdwFk.sfxydllhpf" true-label="1" false-label="0">道路绿化破复</el-checkbox>
									</el-col>
									<el-col :span="4">
										<el-checkbox v-model="sgdwFk.sfxypzdb" true-label="1" false-label="0">需要配置地表</el-checkbox>
									</el-col>
								</el-row>
							</el-form-item>
							<el-form-item label="">
								<el-row>
									<el-col :span="4">
										<el-radio v-model="sgdwFk.sfhykjbylzg" label="1" false-label="0">有</el-radio>
										<el-radio v-model="sgdwFk.sfhykjbylzg" label="0" false-label="0">否</el-radio>
									</el-col>
									<el-col :span="10">
										有可接表预留支管(若有在备注里说明)；
									</el-col>
								</el-row>
							</el-form-item>
							<el-form-item label="">
								<el-row>
									<el-col :span="4">
										<el-radio v-model="sgdwFk.sfxybqg" label="1" false-label="0">有</el-radio>
										<el-radio v-model="sgdwFk.sfxybqg" label="0" false-label="0">否</el-radio>
									</el-col>
									<el-col :span="20">
										需敷设表前管(约<el-input type="text" size="small" v-model="sgdwFk.bqgcd" style="width: 100px;" readonly></el-input>m；
										接表需开挖面积约<el-input type="text" size="small" v-model="sgdwFk.jbxkwmj" style="width: 100px;" ref="jbxkwmj" readonly> </el-input>㎡)；
										(包含过路管开挖敷设)
									</el-col>
								</el-row>
							</el-form-item>
							<el-form-item label="">
								<el-row>
									<el-col :span="4">
										<el-radio v-model="sgdwFk.sfxyzglg" label="1" false-label="0">有</el-radio>
										<el-radio v-model="sgdwFk.sfxyzglg" label="0" false-label="0">否</el-radio>
									</el-col>
									<el-col :span="20">
										接表是否需要做过路管(若需要做过路管，拖管距离约
										<el-input type="text" size="small" v-model="sgdwFk.tgjl" style="width: 100px;"></el-input>m)；
										顶管管尾处破复面积约<el-input type="text" size="small" v-model="sgdwFk.dggwcpfmj" style="width: 100px;" ref="dggwcpfmj" readonly></el-input>㎡；
										<br>
										过路管勾点处破复面积约<el-input type="text" size="small" v-model="sgdwFk.glggdpfmj" style="width: 100px;" ref="glggdpfmj" readonly></el-input>㎡
									</el-col>
								</el-row>
							</el-form-item>
							<el-card class="box-card"  style="margin-top: 0px;">
								<span>资料附件</span>
								<div class="text item">
									<el-table :data="sgdwFj" border style="width: 100%;">
										<el-table-column type="index" label="序号" width="80" align="center">
										</el-table-column>
										<el-table-column property="fjmc" label="附件名称" width="200" align="center">
										</el-table-column>
										<el-table-column property="fjhz" label="文件类型" width="100" align="center">
										</el-table-column>
										<el-table-column property="fjlx_str" label="附件类型" width="180" align="center">
										</el-table-column>
										<el-table-column property="scr_str" label="上传人" width="150" align="center">
										</el-table-column>
										<el-table-column property="scsj_str" label="上传时间" min-width="180" align="center">
										</el-table-column>
										<el-table-column property="xmdz" label="操作"  width="180" align="center">
											<template slot-scope="scope">
												<div v-if="scope.row.fjhz=='png'||scope.row.fjhz=='jpg'">
													<label title="查看" @click="downloadFile_vue(scope.$index, scope.row,1)">
														<i class="iconfont icon-yulan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
												</div>
												<div v-else>
													<label title="下载" @click="downloadFile_vue(scope.$index, scope.row,2)">
														<i class="iconfont icon-xiazai" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
												</div>
											</template>
										</el-table-column>
									</el-table>
								</div>
							</el-card>
						</el-form>
					</div>
					<el-row style="margin-top: 20px;">
						<el-col :span="24">
							<el-form>
								<el-form-item label="施工单位反馈意见" style="width: 100%">
									<el-input type="textarea" v-model="sgdwFkyj" readonly></el-input>
								</el-form-item>
							</el-form>
						</el-col>
					</el-row>
				</el-card>

				<!--设计院反馈-->
				<el-card class="box-card"  style="margin-top: 15px;" v-if="sjyfk">
					<div slot="header" class="clearfix">
						<div class="div_title">设计院反馈</div>
					</div>
					<div class="text item">
						<el-card class="box-card"  style="margin-top: 0px;">
							<span>资料附件</span>
							<div class="text item">
								<el-table :data="sjyFj" border style="width: 100%;">
									<el-table-column type="index" label="序号" width="80" align="center">
									</el-table-column>
									<el-table-column property="fjmc" label="附件名称" width="200" align="center">
									</el-table-column>
									<el-table-column property="fjhz" label="文件类型" width="100" align="center">
									</el-table-column>
									<el-table-column property="fjlx_str" label="附件类型" width="180" align="center">
									</el-table-column>
									<el-table-column property="scr_str" label="上传人" width="150" align="center">
									</el-table-column>
									<el-table-column property="scsj_str" label="上传时间" min-width="180" align="center">
									</el-table-column>
									<el-table-column property="xmdz" label="操作"  width="180" align="center">
										<template slot-scope="scope">
											<div v-if="scope.row.fjhz=='png'||scope.row.fjhz=='jpg'">
												<label title="查看" @click="downloadFile_vue(scope.$index, scope.row,1)">
													<i class="iconfont icon-yulan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
											</div>
											<div v-else>
												<label title="下载" @click="downloadFile_vue(scope.$index, scope.row,2)">
													<i class="iconfont icon-xiazai" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
											</div>
										</template>
									</el-table-column>
								</el-table>
							</div>
						</el-card>
					</div>
					<el-row style="margin-top: 20px;">
						<el-col :span="24">
							<el-form>
								<el-form-item label="设计院反馈意见" style="width: 100%">
									<el-input type="textarea" v-model="sjyFkyj" readonly></el-input>
								</el-form-item>
							</el-form>
						</el-col>
					</el-row>
				</el-card>

				<!--预算科-->
				<el-card class="box-card"  style="margin-top: 15px;" v-if="ysfk">
					<div slot="header" class="clearfix">
						<div class="div_title">
							预算科反馈
						</div>
					</div>
					<div class="text item">
						<el-card class="box-card"  style="margin-top: 0px;">
							<span>资料附件</span>
							<div class="text item">
								<el-table :data="ysFj" border style="width: 100%;">
									<el-table-column type="index" label="序号" width="80" align="center">
									</el-table-column>
									<el-table-column property="fjmc" label="附件名称" width="200" align="center">
									</el-table-column>
									<el-table-column property="fjhz" label="文件类型" width="100" align="center">
									</el-table-column>
									<el-table-column property="fjlx_str" label="附件类型" width="180" align="center">
									</el-table-column>
									<el-table-column property="scr_str" label="上传人" width="150" align="center">
									</el-table-column>
									<el-table-column property="scsj_str" label="上传时间" min-width="180" align="center">
									</el-table-column>
									<el-table-column property="xmdz" label="操作"  width="180" align="center">
										<template slot-scope="scope">
											<div v-if="scope.row.fjhz=='png'||scope.row.fjhz=='jpg'">
												<label title="查看" @click="downloadFile_vue(scope.$index, scope.row,1)">
													<i class="iconfont icon-yulan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
											</div>
											<div v-else>
												<label title="下载" @click="downloadFile_vue(scope.$index, scope.row,2)">
													<i class="iconfont icon-xiazai" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
											</div>
										</template>
									</el-table-column>
								</el-table>
							</div>
						</el-card>

						<el-row style="margin-top: 20px;">
							<el-col :span="24">
								<el-form>
									<el-form-item label="预算科反馈意见" style="width: 100%">
										<el-input type="textarea" v-model="ysFkyj" readonly></el-input>
									</el-form-item>
								</el-form>
							</el-col>
						</el-row>
					</div>
				</el-card>

				<el-card class="box-card"  style="margin-top: 15px;width: 100%">
					<div slot="header" class="clearfix">
						<div class="div_title">项目经理反馈</div>
					</div>
					<div class="text item">
						<el-row style="margin-top: 20px;">
							<el-col :span="20">
								<el-form label-width="150px" :model="kcpj" ref="kcpj" :rules="rules">
									<el-form-item label="实际勘查日期" style="width: 45%" prop="sjkcsj">
										<el-date-picker v-model="kcpj.sjkcsj" type="date" placeholder="选择日期" size="small"
														value-format="yyyy-MM-dd" format="yyyy-MM-dd"></el-date-picker>

									</el-form-item>
									<el-form-item label="勘查评价附件上传" style="width: 100%">
										<el-upload
												class="upload-demo"
												ref="upload"
												action="#"
												multiple
												:on-preview="handlePreview"
												:on-remove="handleRemove"
												:on-change="handleChange"
												:file-list="fileList"
												:http-request="submitUpload"
												:show-file-list="false"
												:auto-upload="true">
											<el-button slot="trigger" size="mini" type="success">
												<i class="iconfont icon-shangchuan_f"></i>上传</el-button>
											<el-table :data="fjData" border style="width: 100%;">
												<el-table-column property="fjmc" label="附件名称" min-width="200" align="center">
												</el-table-column>
												<el-table-column property="fjhz" label="文件类型" width="100" align="center">
												</el-table-column>
												<el-table-column property="fjlx_str" label="附件类型" width="200" align="center">
												</el-table-column>
												<el-table-column property="scsj_str" label="上传时间" min-width="180" align="center">
												</el-table-column>
												<el-table-column property="xmdz" label="操作"  width="120" align="center">
													<template slot-scope="scope">
														<div v-if="scope.row.fjhz=='png'||scope.row.fjhz=='jpg'">
															<!--<el-button slot="reference" size="small" type="primary" @click="downloadFile_vue(scope.$index, scope.row,1)">查看</el-button>
                                                            <el-button size="small" type="danger" @click="deleteFj(scope.$index, scope.row)">删除</el-button>-->

															<label title="查看" @click="downloadFile_vue(scope.$index, scope.row,1)">
																<i class="iconfont icon-yulan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>

															<label title="删除" @click="deleteFj(scope.$index, scope.row)">
																<i class="iconfont delete" style="cursor:pointer;padding: 2px;color: #f78989"></i></label>
														</div>
														<div v-else>
															<!--<el-button size="small" type="primary" @click="downloadFile_vue(scope.$index, scope.row,2)">下载</el-button>
                                                            <el-button size="small" type="error" @click="deleteFj(scope.$index, scope.row)">删除</el-button>-->

															<label title="下载" @click="downloadFile_vue(scope.$index, scope.row,2)">
																<i class="iconfont icon-xiazai" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>

															<label title="删除" @click="deleteFj(scope.$index, scope.row)">
																<i class="iconfont delete" style="cursor:pointer;padding: 2px;color: #f78989"></i></label>
														</div>

													</template>
												</el-table-column>
											</el-table>
										</el-upload>
									</el-form-item>
									<el-form-item label="反馈意见" style="width: 100%">
										<el-input type="textarea" v-model="kcpj.xmjlFkyj"></el-input>
									</el-form-item>
								</el-form>
							</el-col>
						</el-row>
					</div>
				</el-card>

				<el-row style="text-align: center;margin-top: 35px;margin-bottom: 25px;">
					<el-button size="mini" type="info" @click="toFkpjlb">
						<i class="iconfont icon-quxiao"></i>取消</el-button>

					<el-button size="mini" type="primary" @click="submitKcpj('kcpj')" :loading="sumbitLoading">
						<i class="iconfont icon-queren"></i>提交</el-button>

					<el-button size="mini" type="primary" @click="Kcpjjgzl('kcpj')" :loading="sumbitLoading">
						<i class="iconfont icon-yingyongtubiaodaochu"></i>结果总览</el-button>

					<el-button size="mini" type="primary" @click="goxmqj()">
						<i class="iconfont icon-xiangmuquanjing_"></i>项目全景</el-button>

					<!--<el-button type="warning" @click="openDy">打印</el-button>
					<el-button type="primary" @click="submitKcpj('kcpj')"  :loading="sumbitLoading">提交</el-button>-->
				</el-row>
			</el-col>
		</el-row>


		<!--打印界面-->
		<el-dialog title="" :visible.sync ="printFormVisible">
			<div id="printTests">
				<table class="border-table">
					<caption align="top">
						<span style="font-size: 20px;font-family: 黑体;">合肥供水集团</span><br>
						<span style="font-size: 20px;font-family: 黑体;">供水管网信息调查表</span>
					</caption>
					<tr>
						<td width="10%" height="40px">申请编号</td>
						<td width="40%" height="40px"><span id="dy_sqbh"></span></td>
						<td width="10%" height="40px">申请日期</td>
						<td width="40%" height="40px"><span id="dy_sqrq"></span></td>
					</tr>
					<tr>
						<td width="10%" height="40px">联系人</td>
						<td width="40%" height="40px"><span id="dy_lxr"></span></td>
						<td width="10%" height="40px">联系电话</td>
						<td width="40%" height="40px"><span id="dy_lxdh"></span></td>
					</tr>
					<tr>
						<td width="10%" height="40px">报建单位</td>
						<td width="40%" height="40px"><span id="dy_bjdw"></span></td>
						<td width="10%" height="40px">业务类型</td>
						<td width="40%" height="40px"><span id="dy_ywlx"></span></td>
					</tr>
					<tr>
						<td width="10%" height="40px">项目名称</td>
						<td width="40%" height="40px" colspan="3"><span id="dy_xmmc"></span></td>
					</tr>
					<tr>
						<td width="10%" height="40px">项目地址</td>
						<td width="40%" height="40px" colspan="3"><span id="dy_xmdz"></span></td>
					</tr>
					<tr>
						<td height="40px" colspan="4"><span style="font-family: 黑体;">已接受用户提供的纸质申报资料明细</span></td>
					</tr>
					<tr v-for="(item) in zlsc_data">
						<td height="40px" colspan="3">《<span>{{item.TYPE}}</span>》</td>
						<td height="40px"><span>{{item.VALUE}}</span>份</td>
					</tr>
					<tr>
						<th align="left" width="60%" height="40px" colspan="3">受理单位：合肥供水集团有限公司</th>
						<th align="left" width="40%" height="40px">受理人：<span id="dy_slr"></span></th>
					</tr>
					<tr>
						<th align="left" width="60%" height="40px" colspan="3">用户确认签章：</th>
						<th align="left" width="40%" height="40px">日期：<span id="dy_rq"></span></th>
					</tr>
				</table>
			</div>
			<div>
				<el-button size="small" type="primary" v-print="'#printTests'">打印</el-button>
			</div>
		</el-dialog>

		<el-dialog title="" :visible.sync ="print">
			<div class=WordSection1 style='layout-grid:15.6pt' id="printTest">
				<p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'>
					<b style='mso-bidi-font-weight:normal'>
						<span style='font-size:20.0pt;font-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>合肥供水集团接水报建业务
							<span lang=EN-US></span>
						</span>
					</b>
				</p>
				<p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'>
					<b style='mso-bidi-font-weight:normal'>
						<span style='font-size:20.0pt;font-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>勘查结果一次告知
							<span lang=EN-US></span>
						</span>
					</b>
				</p>
				<p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'>
					<b style='mso-bidi-font-weight:normal'>
						<span lang=EN-US style='font-size:20.0pt;font-family:黑体;mso-bidi-font-family:宋体;mso-font-kerning:0pt'>
						</span>
					</b>
				</p>
				<div>
					<span>业务号： <u><span id="ywh"></span></u></span>
					<span style="float: right;">勘查现场日期：<u><span id="sjkcsj"></span></u></span>
					<table border="1" cellspacing="0" width="100%">
						<tbody>
						<tr align="center">
							<td width="100px" height="30px" >项目名称</td>
							<td width="300px" colspan=5><span id="xmmc"></span></td>
							<td width="100px">用户</td>
							<td width="100px" colspan=3><span id="lxr"></span></td>
						</tr>
						<tr align="center">
							<td width="100px" height="30px" >安装地点</td>
							<td width="300px" colspan=5><span id="azdd"></span></td>
							<td width="100px">联系方式</td>
							<td width="100px" colspan=3><span id="lxfs"></span></td>
						</tr>
						<tr>
							<td width="100px" height="80px" style="font-size: 14px">项目接表周边管线情况(供水所)</td>
							<td width="100px" colspan=9>
								<span style="float: left;">现状周边管网情况：</span><br>
								<span style="float: left;margin-left: 30px;margin-top: 10px" v-for='value,index in qsFk'>
									<span v-if="value.bwts == '1'">是☑&nbsp;&nbsp;&nbsp;否□</span>
									<span v-else>是□&nbsp;&nbsp;&nbsp;否☑</span>&nbsp;并网通水；
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span v-if="value.gwyj == '1'">是☑&nbsp;&nbsp;&nbsp;否□</span>
									<span v-else>是□&nbsp;&nbsp;&nbsp;否☑</span>&nbsp;验证移交；
									<br>
									<u>&nbsp;&nbsp;&nbsp;{{value.gxzl}}&nbsp;&nbsp;&nbsp;</u>
									（<u>&nbsp;&nbsp;&nbsp;{{value.zlwz_str}}&nbsp;&nbsp;&nbsp;</u>侧，
									<u>&nbsp;&nbsp;&nbsp;{{value.zsffgw_str}}&nbsp;&nbsp;&nbsp;</u>下方）
									有DN<u>&nbsp;&nbsp;&nbsp;{{value.kj_str}}&nbsp;&nbsp;&nbsp;</u>
									管材<u>&nbsp;&nbsp;&nbsp;{{value.gxcz_str}}&nbsp;&nbsp;&nbsp;</u>；
								</span>
							</td>
						</tr>
						<tr>
							<td width="100px" height="80px" style="font-size: 14px">接表工程概括、施工破复面积及简图（三欣）</td>
							<td width="100px" colspan=9>
								<span style="margin-left: 30px" v-model="sgdwFk">
									<span v-if="sgdwFk.sfhykjbylzg == '1'">是☑&nbsp;&nbsp;&nbsp;&nbsp;否□</span>
									<span v-else>是□&nbsp;&nbsp;&nbsp;否☑</span>&nbsp;有可接表预留支管(若有在备注里说明);
									<br>
									<span v-if="sgdwFk.sfxybqg == '1'" style="margin-left: 30px">是☑&nbsp;&nbsp;&nbsp;&nbsp;否□</span>
									<span v-else>是□&nbsp;&nbsp;&nbsp;否☑</span>&nbsp;需敷设表前管(约
									<u>&nbsp;&nbsp;{{sgdwFk.bqgcd}}&nbsp;&nbsp;</u>m； 接表需开挖面积约
									<u>&nbsp;&nbsp;{{sgdwFk.jbxkwmj}}&nbsp;&nbsp;</u>㎡)； (包含过路管开挖敷设)
									<br>
									<span v-if="sgdwFk.sfxyzglg == '1'" style="margin-left: 30px">是☑&nbsp;&nbsp;&nbsp;&nbsp;否□</span>
									<span v-else>是□&nbsp;&nbsp;&nbsp;否☑</span>&nbsp;接表是否需要做过路管(若需要做过路管，拖管距离约
									<u>&nbsp;&nbsp;{{sgdwFk.tgjl}}&nbsp;&nbsp;</u>m)； 顶管管尾处破复面积约
									<u>&nbsp;&nbsp;{{sgdwFk.dggwcpfmj}}&nbsp;&nbsp;</u>㎡；
										过路管勾点处破复面积约<u>&nbsp;&nbsp;{{sgdwFk.glggdpfmj}}&nbsp;&nbsp;</u>㎡
									<br>
									<span v-if="sgdwFk.sfycgc == '1'" style="margin-left: 30px">是☑&nbsp;&nbsp;&nbsp;&nbsp;否□</span>
									<span v-else>是□&nbsp;&nbsp;&nbsp;否☑</span>&nbsp;超高层;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span v-if="sgdwFk.sfyldsx == '1'">是☑&nbsp;&nbsp;&nbsp;&nbsp;否□</span>
									<span v-else>是□&nbsp;&nbsp;&nbsp;否☑</span>&nbsp;楼顶水箱;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span v-if="sgdwFk.sfxydllhpf == '1'">是☑&nbsp;&nbsp;&nbsp;&nbsp;否□</span>
									<span v-else>是□&nbsp;&nbsp;&nbsp;否☑</span>&nbsp;道路绿化破复;
									<br>
									<span v-if="sgdwFk.sfxypzdb == '1'" style="margin-left: 30px">是☑&nbsp;&nbsp;&nbsp;&nbsp;否□</span>
									<span v-else style="margin-left: 30px">是□&nbsp;&nbsp;&nbsp;否☑</span>&nbsp;需要配置地表;
									泵房供水位置<u>&nbsp;&nbsp;&nbsp;{{sgdwFk.bfgswz}}&nbsp;&nbsp;&nbsp;</u>；
									楼顶水箱位置<u>&nbsp;&nbsp;&nbsp;{{sgdwFk.ldsxwz}}&nbsp;&nbsp;&nbsp;</u>；
								</span>
							</td>
						</tr>
						<tr>
							<td width="100px" height="80px" style="font-size: 14px;text-align: center">设计院反馈</td>
							<td width="100px" colspan=9>
								<span id="sjyFkyj" style="margin-left: 30px;">
								</span>
							</td>
						</tr>
						<tr>
							<td width="100px" height="80px" style="font-size: 14px;text-align: center">预算反馈</td>
							<td width="100px" colspan=9>
								<span id="ysFkyj" style="margin-left: 30px;">
								</span>
							</td>
						</tr>
						<tr>
							<td width="100px" height="80px" style="font-size: 14px;text-align: center">项目经理反馈</td>
							<td width="100px" colspan=9>
								<span id="xmjlFkyj" style="margin-left: 30px;">
								</span>
							</td>
						</tr>
						<tr>
							<td width="100px" height="80px" style="font-size: 14px;text-align: center">用户需知</td>
							<td width="100px" colspan=9>
								<span>
									<p style="font-weight: 600">1、如管网未移交，无管网资料，存在第二次破复风险。</p>
									<p>2、接表所涉及道路破复以及绿化移植恢复等事宜用户（建设单位）自行前往市政相关部门办理相应手续（若不属于市政管理范围内的道路，用户需经道路管理方书面同意），道路
									破复以及绿化移植恢复等费用由用户自理，提供破路简易图一份，请用户在市政办理相关道路破复、绿化移植手续时，考虑转账时间以及施工单位时间安排，以免许可证过期影响水表安装，破复手续或情况说明办好后及时交由客服发
									展部项目负责人。</p>
									<p>
										3、由于道路破复/绿化移植施工期间没有做好现场的安全防护和路面及时恢复而造成行人、车辆伤害或其它经济损失
										，均与我单位无关，请用户督促相关单位做好现场的安全防护，及时进行路面恢复等工作。
									</p>
									<p>
										4、根据合肥市供水条例规定、<span style="font-weight: 600">表后管道（含表后管连接）由用户自理</span>
									</p>
									<p>
										5、DN80以上（含DN80）水表需提供项目用水理及消防水理计算书、给水总平面图。
									</p>
									<p>
										6、集中用水单位需建调蓄水池，以满足高峰期用水需求。
									</p>



								</span>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
				<p style="margin-left: 20px;float: left">
					用户签章:
				</p>
				<p style="margin-right: 150px;float: right">
					客户发展签字:
				</p>

			</div>
			<center>
				<div style="margin-left: 250px">
					<el-button size="mini" type="success" v-print="'#printTest'" align="center" style="margin-top: 50px;margin-bottom: 5px;"><i class="iconfont icon-dayin_f" style="cursor:pointer;padding: 2px;color: white"></i>打印</el-button>
					<el-button size="mini" type="success"  @click="downWord()" style=""><i class="iconfont icon-xiazai_f" style="cursor:pointer;padding: 2px;color: white;"></i>下载</el-button>
				</div>
			</center>

		</el-dialog>

	</section>
</template>
<style scoped>
	.rowTitle{
		margin-bottom: 15px;
	}
	.rowTitle .el-col{
		text-align: center;
	}
	.spanText{
		color: #99a9bf;
	}
	.demo-table-expand {
		font-size: 0;
	}
	.demo-table-expand label {
		width: 90px;
		color: #99a9bf;
	}

	.demo-table-expand .el-form-item {
		margin-right: 0;
		margin-bottom: 0;
		width: 50%;
	}
	.border-table {
		border-collapse: collapse;
		border: none;
		width: 100%;
	}
	.border-table td {
		border: solid #000 1px;
	}
	.div_title {
		margin:-18px -20px;
		padding:10px 20px;
		background: #f5f7fa;
		height: 18px;
	}
</style>

<script>
	import axios from 'axios'
	import XMJBXX from '@/views/template/xmjbxx.vue';
	import util from '../../common/js/util'
	import { getFkpjKcbm,getKcfkRowQs,saveXckcPj,updateCheckTask,importFile,download,deleteFkFj,getPjFjData,findXmxx,downWord} from '../../api/xkylx';
	import hhldsx from '@/views/template/hhldsx.vue';

	export default {
		data() {
			return {
				printFormVisible:false,//打印下载界面是否显示
				qsfk : false,
				sgdwfk : false,
				sjyfk : false,
				ysfk : false,
				kcrwzbid : '',//勘查任务主表id
				xmid : this.$route.query.xmid,//项目id
				activityId:this.$route.query.activityId,
				rwbh:this.$route.query.rwbh,//任务编号
				kcbm : [],
				qsFk : [],//区所反馈
				qsFj : [],
				qsFkyj: '',//区所反馈意见
				sjyFj : [],//设计院反馈附件
				sjyFkyj: '',//设计院反馈意见
				ysFj : [],//预算科反馈附件
				ysFkyj: '',//预算科反馈意见
				sgdwFk : {//施工单位反馈意见
					sfycgc : '0',
					sfyldsx : '0',
					sfxydllhpf :'0',
					sfxypzdb :'0',
					sjkcsj : '',
					bfgswz :'',
					ldsxwz :'',
					info : '',
					sfhykjbylzg : '0',
					sfxybqg :'0',
					jbxkwmj :'',
					bqgcd :'',
					sfxyzglg :'0',
					tgjl :'',
					dggwcpfmj :'',
					glggdpfmj :''
				},
				fjData :[],
				sgdwFj : [],//施工单位反馈附件
				sgdwFkyj: '',//施工单位反馈意见
				kcpj:{
					sjkcsj:'',
					xmjlFkyj :''//项目经理反馈意见
				},
				fileList : [],
				fileList2 : [],
				checked : false,
				print : false,//打印界面
				zlsc_data : [],
				sumbitLoading : false,
				rules: {
					sjkcsj: [
						{required: true, message: '实际勘查时间不能为空', trigger: 'blur'}
					]
				},
				jcsj: {
					sqbh : '',//申请单编号
					xmbh : '',//项目编号
					sqlx : '',//受理类型
					lxr : '',//联系人
					lxrsjh : '',//联系人联系方式
					slsj : '',//受理时间
					lxrq : '',//立项时间
					xmdz : '',//项目地址
					xmmc : '',//项目名称
					xmfzr_str : '',//项目经理
					xmfzr_sjh : ''//项目经理联系方式
				},//表格数据(基本项目信息)
			}
		},
		methods: {
			//下载成Word
			downWord(){
				let params1 = this.jcsj;
				params1.kcpj = this.kcpj;//项目经理反馈意见
				params1.sjyFkyj = this.sjyFkyj;//设计院反馈意见
				params1.ysFkyj = this.ysFkyj;//预算科反馈意见
				params1.sgdwFk = this.sgdwFk;//施工单位反馈
				params1.qsFk = this.qsFk;//现状周边管网情况

				let json = JSON.stringify(params1);
				let params = {
					rwbh:this.rwbh,
					kcrwzbid:this.kcrwzbid,
					json: json
				};
				downWord(params).then((res) => {
						let blob = new Blob([res], {
							type: `application/msword` //word文档为msword,pdf文档为pdf，msexcel 为excel
						});
						let objectUrl = URL.createObjectURL(blob);
						let link = document.createElement("a");
						let fname = `合肥供水集团接水报建业务勘查结果一次告知.doc`; //下载文件的名字+后缀名
						link.href = objectUrl;
						link.setAttribute("download", fname);
						document.body.appendChild(link);
						link.click();
				});
			},
			//结果总览
			Kcpjjgzl(){
				this.print = true;
				this.$nextTick(()=>{
					let params = {
						xmid :this.xmid
					};
					findXmxx(params).then((res) => {
						let obj = eval("("+ res.data.xmxx +")");
						if(obj != null) {
							for (var i in obj) {
								if (obj[i] == 'null') {
									obj[i] = '';
								}
							}
							this.jcsj = obj;
							if(res.data.sflx==0){
								this.flag = false;
								this.jcsj.slsj = obj.slrq;
								this.jcsj.xmfzr_str = obj.xmjl_str;
								this.jcsj.xmfzr_sjh = obj.xmjl_sjh;
							}
						}
						document.getElementById("xmmc").innerText = this.jcsj.xmmc;
						document.getElementById("lxr").innerText = this.jcsj.lxr;
						document.getElementById("azdd").innerText = this.jcsj.xmdz;
						document.getElementById("lxfs").innerText = this.jcsj.lxrsjh;
					});
					if (typeof (this.sjyFkyj) != "undefined"){
						document.getElementById("sjyFkyj").innerText = this.sjyFkyj;
					}
					if (typeof (this.ysFkyj) != "undefined"){
						document.getElementById("ysFkyj").innerText = this.ysFkyj;
					}
					if (typeof (this.kcpj.xmjlFkyj) != "undefined"){
						document.getElementById("xmjlFkyj").innerText = this.kcpj.xmjlFkyj;
					}
					if (this.kcpj.sjkcsj !=""){
						document.getElementById("sjkcsj").innerText = this.kcpj.sjkcsj;
					}else{
						document.getElementById("sjkcsj").innerText ="____________";
					}
					if (this.rwbh != ""){
						document.getElementById("ywh").innerText = this.rwbh;
					} else {
						document.getElementById("ywh").innerText ="____________";
					}




				});


			},
			formatDcnr : function (row, column) {
				return row.dcnr == '0' ? '项目周边管网' :  '具体位置测压';
			},
			getFjData(){
				let params = {xmid:this.xmid,type:'4'}
				getPjFjData(params).then((res) => {
					this.fjData = res.data.fj;
				})
			},
			toFkpjlb(){//跳转页面Fkpjlb
				this.$routerTab.close();
				this.$routerTab.refresh('/Backlog');
				this.$routerTab.refresh('/MyWork');
			},
			goxmqj(){
				this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid }})
			},
			getKcbm(){//参与勘查的部门以及反馈信息
				let params = { xmid: this.xmid, kczt:'3'};
				getFkpjKcbm(params).then((res) => {
					this.kcbm = res.data.row;
					this.kcrwzbid = res.data.kcrwzbid;
					if(res.data.qsFk != undefined){
						this.qsFk = res.data.qsFk;
						this.qsFkyj = res.data.qsFkyj;
						this.qsFj = res.data.qsFj;
						this.qsfk = true;
					}
					if(res.data.sjyFj != undefined){
						this.sjyFj = res.data.sjyFj;
						this.sjyFkyj = res.data.sjyFkyj;
						this.sjyfk = true;
					}
					if(res.data.ysFj != undefined){
						this.ysFj = res.data.ysFj;
						this.ysFkyj = res.data.ysFkyj;
						this.ysfk = true;
					}
					if(res.data.sgdwFk != undefined){
						let obj = eval('('+ res.data.sgdwFk +')');
						if(obj != null) {
							for (var i in obj) {
								if (obj[i] == 'null') {
									obj[i] = '';
								}
							}
							this.sgdwFk = obj;
						}
						this.sgdwFkyj = res.data.sgdwFkyj;
						this.sgdwFj = res.data.sgdwFj;
						this.sgdwfk = true;
					}
				});
			},
			getKcfkRow() {//获取区所反馈记录
				let params = { type: '3',xmid:this.xmid };
				getKcfkRowQs(params).then((res) => {
					this.kcrwcbid = res.data.kcrwcbid;
					let row = res.data.row;
					for(var i in row){
						if(row[i].dcrq != null){
							row[i].dcrq = row[i].dcrq.substring(0,row[i].dcrq.lastIndexOf(' '));
						}
						if(row[i].cysj != null){
							row[i].cysj = row[i].cysj.substring(0,row[i].cysj.lastIndexOf(':'));
						}
					}
					this.kcfkRow = row;
				});
			},
			submitKcpj(kcpj){//提交勘察反馈评价
				this.$confirm('您正在递交工作结果，一旦递交确认后将不能打回或再次修改，你确定要递交吗？', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$refs[kcpj].validate((valid) => {
						if (valid) {
							this.sumbitLoading = true;
							let params = { xmid: this.xmid, info: this.kcpj.xmjlFkyj, sjkcsj: this.kcpj.sjkcsj};
							saveXckcPj(params).then((res) => {
								this.sumbitLoading = false;
								if(res.result == 'success'){
									this.$message({
										message: '提交反馈评价成功',
										type: 'success'
									});
									this.toFkpjlb();
								}else {
									this.$message({
										message: '提交反馈评价异常',
										type: 'error'
									});
								}
							});
						}else {
							return false;
						}
					});
				}).catch(() => {

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
			submitUpload() {
				let fileList = this.fileList;
				let operation = "勘查评价附件";

				if (fileList !=null && fileList.length >0){
					//for (let i=0;i<fileList.length;i++){
						let file = fileList[fileList.length-1];
						let formData = new FormData();
						formData.append("operation",operation);
						formData.append("multipartFiles",file.raw);
						formData.append("filename",file.name);
						formData.append("xmid",this.xmid);
						formData.append("type",'4');
						importFile(formData).then((res) => {
							let msg = res.msg;
							if(msg == '上传成功'){
								this.$message({
									message: '上传成功',
									type: 'success'
								});
							}else{
								this.$message.error('上传失败！');
							}
							this.fileList = [];
							this.getFjData();
						});
					//}
				}else {
					this.$message.warning('未选择任何文件！');
				}
			},
			handleRemove2(file, fileList) {
				console.log(file, fileList);
			},
			handlePreview2(file) {
				console.log(file);
			},
			handleChange2(file,fileList){
				this.fileList2 = fileList;
			},
			submitUpload2() {
				let fileList = this.fileList2;
				let operation = "快速接水方案上传";

				if (fileList !=null && fileList.length >0){
					for (let i=0;i<fileList.length;i++){
						let file = fileList[i];
						let formData = new FormData();
						formData.append("operation",operation);
						formData.append("multipartFiles",file.raw);
						formData.append("filename",file.name);
						formData.append("xmid",this.xmid);
						formData.append("type",'4');
						importFile(formData).then((res) => {
							let msg = res.msg;
							if(msg == '上传成功'){
								this.$message({
									message: '上传成功',
									type: 'success'
								});
							}else{
								this.$message.error('上传失败！');
							}
						});
					}
				}else {
					this.$message.warning('未选择任何文件！');
				}
			},
			deleteFj(index,row,flag) {
				this.$confirm('确认删除该附件？, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'error'
				}).then(() => {
					let params = {id:row.id,type:'4'}
					deleteFkFj(params).then((res) => {
						this.getFjData();
					})
				}).catch(() => {
				});
			},
			downloadFile_vue(index,row,flag){//附件查看或下载
				let formData = new FormData();
				formData.append("path",row.fjlj);
				download(formData).then((res) => { // 处理返回的文件流
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
						const content = res
						const blob = new Blob([content])
						const fileName = row.fjmc
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
			},
			openDy(){//打开打印界面
				this.printFormVisible = true;
				this.$nextTick(()=>{
					let list = this.fileIds;
					let params = {};
					let i = 0;
					list.forEach((item,index)=>{
						params['value'+(index+1)] = list[i];
						i++;
					});
					params['sqlx'] = this.filters.sqlx;
					params['count'] = i;
					queryZlcount(params).then((res)=>{
						this.zlsc_data = res.data.list;
					});
					document.getElementById("dy_sqbh").innerText = this.filters.sqbh;
					document.getElementById("dy_sqrq").innerText = this.publicUtil.getNowDay();
					document.getElementById("dy_lxr").innerText = this.filters.lxr;
					document.getElementById("dy_lxdh").innerText = this.filters.lxdh;
					document.getElementById("dy_bjdw").innerText = this.filters.bjdw;
					document.getElementById("dy_ywlx").innerText = this.filters.azlx;
					document.getElementById("dy_xmmc").innerText = this.filters.gcmc;
					document.getElementById("dy_xmdz").innerText = this.filters.gcdz;
					document.getElementById("dy_slr").innerText = this.user;
					document.getElementById("dy_rq").innerText = this.publicUtil.getNowDay();
				});
			}

		},
		mounted() {
			updateCheckTask({xmid:this.xmid,type:''}).then((res) => {});//更改任务签收状态
			this.getKcbm();
			this.getFjData();
			//this.getKcfkRow();
		},
		components: {
			'hhldsx': hhldsx,
			'XMJBXX': XMJBXX  //将别名demo 变成 组件 Demo
		}

	}
</script>

<style>
	.demo-table-expand {
		font-size: 0;
	}
	.demo-table-expand label {
		width: 90px;
		color: #99a9bf;
	}
	.demo-table-expand .el-form-item {
		margin-right: 0;
		margin-bottom: 0;
		width: 50%;
	}
	.delete {
		font-size: 19px;
	}
	.rowText {
		line-height: 32px;
	}
	.itext {
		color: #99a9bf;
	}
</style>