<template>
	<section>
		<el-row>
			<!--工具条-->
			<!--<el-col :span="24">
				<el-card class="box-card" style="margin-top: 15px;">
					<div slot="header" class="clearfix">
						<span>基本信息</span>
					</div>
					<el-form :model="filters">
						<el-form-item>
							<el-row>
								<el-col :span="2" style="text-align: right">
									<span>申请类型:</span>
								</el-col>
								<el-col :span="5">
									<el-input v-model="filters.sqlx" style="width:206px;" :readonly="true"></el-input>
								</el-col>
							</el-row>
						</el-form-item>
						<el-form-item>
							<el-row>
								<el-col :span="2" style="text-align: right">
									<span>申请编号:</span>
								</el-col>
								<el-col :span="5">
									<el-input v-model="filters.sqbh" style="width:206px;"  :readonly="true"></el-input>
								</el-col>
								<el-col :span="2" style="text-align: right">
									<span>安装类型:</span>
								</el-col>
								<el-col :span="5">
									<el-select v-model="filters.azlx" style="width:206px;"  :readonly="true">
										<el-option v-for="item in azList"
												   :key="item.VALUE"
												   :label="item.LABEL"
												   :value="item.VALUE"></el-option>
									</el-select>
								</el-col>
								<el-col :span="2" style="text-align: right">
									<span>受理日期:</span>
								</el-col>
								<el-col :span="5">
									<el-input v-model="filters.slrq" style="width:206px;" :readonly="true"></el-input>
								</el-col>
							</el-row>
						</el-form-item>
						<el-form-item>
							<el-row>
								<el-col :span="2" style="text-align: right">
									<span>所属区域:</span>
								</el-col>
								<el-col :span="5">
									<el-select v-model="filters.xmdz_xzqybh" style="width:206px;" placeholder="请选择">
										<el-option v-for="item in qyList"
												   :key="item.VALUE"
												   :label="item.LABEL"
												   :value="item.VALUE"></el-option>
									</el-select>
								</el-col>
								<el-col :span="2" style="text-align: right">
									<span>联系人:</span>
								</el-col>
								<el-col :span="5">
									<el-input v-model="filters.lxr" style="width:206px;" :readonly="true"></el-input>
								</el-col>
								<el-col :span="2" style="text-align: right">
									<span>联系电话:</span>
								</el-col>
								<el-col :span="5">
									<el-input v-model="filters.lxrsjh" style="width:206px;" :readonly="true"></el-input>
								</el-col>
							</el-row>
						</el-form-item>
						<el-form-item>
							<el-row>
								<el-col :span="2" style="text-align: right">
									<span>工程名称:</span>
								</el-col>
								<el-col :span="5">
									<el-input v-model="filters.xmmc" style="width:206px;" :readonly="true"></el-input>
								</el-col>
								<el-col :span="2" style="text-align: right">
									<span>报建单位:</span>
								</el-col>
								<el-col :span="5">
									<el-input v-model="filters.dwmc" style="width:560px;" :readonly="true"></el-input>
								</el-col>
							</el-row>
						</el-form-item>
						<el-form-item>
							<el-row>
								<el-col :span="2" style="text-align: right">
									<span>用户性质:</span>
								</el-col>
								<el-col :span="5">
									<el-input v-model="filters.yhxz" style="width:206px;" :readonly="true"></el-input>
								</el-col>
								<el-col :span="2" style="text-align: right">
									<span>工程地址:</span>
								</el-col>
								<el-col :span="5">
									<el-input v-model="filters.xmdz" style="width:560px;" :readonly="true"></el-input>
								</el-col>
							</el-row>
						</el-form-item>
						<el-form-item v-if="showJm">
							<el-col :span="2" style="text-align: right">
								<span>总建筑面积:</span>
							</el-col>
							<el-col :span="5">
								<el-input v-model="filters.zjzmj" style="width:206px;"  :readonly="true"></el-input>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>住宅面积:</span>
							</el-col>
							<el-col :span="5">
								<el-input v-model="filters.zzjzmj" style="width:206px;"  :readonly="true"></el-input>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>地块简称:</span>
							</el-col>
							<el-col :span="5">
								<el-input v-model="filters.dkjc" style="width:206px;" :readonly="true"></el-input>
							</el-col>
						</el-form-item>
						<el-form-item v-if="showJm">
							<el-col :span="2" style="text-align: right">
								<span>户数:</span>
							</el-col>
							<el-col :span="5">
								<el-input v-model="filters.hs" style="width:206px;" :readonly="true"></el-input>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>设计范围:</span>
							</el-col>
							<el-col :span="5">
								<el-input v-model="filters.sjfw" style="width:560px;"  :readonly="true"></el-input>
							</el-col>
						</el-form-item>
						<el-form-item v-if="showJm">
							<el-col :span="2" style="text-align: right">
								<span>单元楼号:</span>
							</el-col>
							<el-col :span="5">
								<el-input v-model="filters.dylh" style="width:206px;"  :readonly="true"></el-input>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>规划泵房数:</span>
							</el-col>
							<el-col :span="5">
								<el-input v-model="filters.ghbfs" style="width:206px;"  :readonly="true"></el-input>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>交房时间:</span>
							</el-col>
							<el-col :span="5">
								<el-date-picker
										v-model="filters.jfsj"
										type="date"
										 :readonly="true">
								</el-date-picker>
							</el-col>
						</el-form-item>
						<el-form-item v-if="showJm">
							<el-col :span="2" style="text-align: right">
								<span>分期情况:</span>
							</el-col>
							<el-col :span="5">
								<el-input v-model="filters.fqqk" style="width:206px;"  :readonly="true"></el-input>
							</el-col>
						</el-form-item>
						<el-form-item v-if="showGs">
							<el-col :span="2" style="text-align: right">
								<span>意向接表数量:</span>
							</el-col>
							<el-col :span="5">
								<el-input v-model="filters.yxazsl" style="width:206px;"  :readonly="true"></el-input>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>接水用途:</span>
							</el-col>
							<el-col :span="5">
								<el-input v-model="filters.jsyt" style="width:206px;"  :readonly="true"></el-input>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>原户号:</span>
							</el-col>
							<el-col :span="5">
								<el-input v-model="filters.yhh" style="width:206px;"  :readonly="true"></el-input>
							</el-col>
						</el-form-item>
						<el-form-item v-if="showGs">
							<el-col :span="2" style="text-align: right">
								<span>原表口径:</span>
							</el-col>
							<el-col :span="5">
								<el-input v-model="filters.ybkj" style="width:206px;" :readonly="true"></el-input>
							</el-col>
						</el-form-item>
						<el-form-item label="用水方式" sortable v-if="showHg">
							<el-select v-model="filters.ysfs" style="width:206px;" >
								<el-option label="市政直供" value="市政直供"></el-option>
								<el-option label="二次加压" value="二次加压"></el-option>
								<el-option label="楼顶水箱" value="楼顶水箱"></el-option>
							</el-select>
						</el-form-item>
						<el-form-item label="用水状况" sortable v-if="showHg">
							<el-select v-model="filters.yszk" style="width:206px;" placeholder="请选择">
								<el-option label="良好" value="良好"></el-option>
								<el-option label="一般" value="一般"></el-option>
								<el-option label="差" value="差"></el-option>
							</el-select>
						</el-form-item>
						<el-form-item label="户型状况" sortable v-if="showHg">
							<el-select v-model="filters.hxzk" style="width:206px;" placeholder="请选择">
								<el-option label="商住" value="商住"></el-option>
								<el-option label="住宅" value="住宅"></el-option>
								<el-option label="综合" value="综合"></el-option>
							</el-select>
						</el-form-item>
						<el-form-item label="楼房户数" sortable v-if="showHg">
							<el-input v-model="filters.lfhs" placeholder="输入楼房户数"></el-input>
						</el-form-item>
						<el-form-item label="小区楼房" sortable v-if="showHg">
							<el-input v-model="filters.xqlf" placeholder="输入小区楼房（栋）"></el-input>
						</el-form-item>
						<el-form-item label="平房户数" sortable v-if="showHg">
							<el-input v-model="filters.pfhs" placeholder="输入平房户数"></el-input>
						</el-form-item>
						<el-form-item label="最高层数" sortable v-if="showHg">
							<el-input v-model="filters.zgcs" placeholder="输入最高层数（层）"></el-input>
						</el-form-item>
						<el-form-item label="内部消防" sortable v-if="showHg">
							<el-select v-model="filters.nbxf" style="width:206px;" placeholder="请选择">
								<el-option label="是" value="0"></el-option>
								<el-option label="否" value="1"></el-option>
							</el-select>
						</el-form-item>
						<el-form-item label="车库用水" sortable v-if="showHg">
							<el-input v-model="filters.ckys" placeholder="输入车库用水（间）"></el-input>
						</el-form-item>
						<el-form-item label="车棚用水" sortable v-if="showHg">
							<el-input v-model="filters.cpys" placeholder="输入自行车棚用水（个）"></el-input>
						</el-form-item>
						<el-form-item label="门面用水" sortable v-if="showHg">
							<el-input v-model="filters.mmys" placeholder="输入经营门面用水（户）"></el-input>
						</el-form-item>
						<el-form-item label="公厕用水" sortable v-if="showHg">
							<el-input v-model="filters.gcys" placeholder="输入公厕用水（所）"></el-input>
						</el-form-item>
						<el-form-item label="物管用水" sortable v-if="showHg">
							<el-input v-model="filters.wgys" placeholder="输入物管办公房用水（处）"></el-input>
						</el-form-item>
						<el-form-item label="其他用水" sortable v-if="showHg">
							<el-input v-model="filters.qtys" placeholder="输入其他用水"></el-input>
						</el-form-item>
					</el-form>
				</el-card>
			</el-col>-->

			<el-col :span="24">
				<el-card class="box-card" style="margin-top: 15px;">
					<div slot="header" class="clearfix">
						<div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">基本信息</div>
					</div>
					<el-form :model="filters"  ref="filters" class="demo-ruleForm"><!--:rules="xqrules"-->
						<el-form-item>
							<el-row>
								<el-col :span="2" style="text-align: right">
									<span>申请类型&nbsp;&nbsp;</span>
								</el-col>
								<el-col :span="5">
									<el-input v-model="filters.sqdlx" size="small" style="width:206px;" placeholder="输入申请编号" :readonly="true"></el-input>
								</el-col>
							</el-row>
						</el-form-item>
						<el-form-item>
							<el-row>
								<el-col :span="2" style="text-align: right">
									<span>申请编号&nbsp;&nbsp;</span>
								</el-col>
								<el-col :span="5">
									<el-input v-model="filters.sqbh" size="small" style="width:206px;" placeholder="输入申请编号" :readonly="true"></el-input>
								</el-col>
								<el-col :span="2" style="text-align: right">
									<span>安装类型&nbsp;&nbsp;</span>
								</el-col>
								<el-col :span="5">
									<el-select v-model="filters.azlx" size="small" style="width:206px;" placeholder="请选择" :disabled="true">
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
									<el-input v-model="filters.slrq" size="small" style="width:206px;" placeholder="输入受理日期" :readonly="true"></el-input>
								</el-col>
							</el-row>
						</el-form-item>
						<el-form-item>
							<el-row>
								<el-col :span="2" style="text-align: right">
									<span>所属区域&nbsp;&nbsp;</span>
								</el-col>
								<el-col :span="5">
									<el-select v-model="filters.ssqy" size="small" style="width:206px;" placeholder="请选择" :disabled="true">
										<el-option v-for="item in qyList"
												   :key="item.VALUE"
												   :label="item.LABEL"
												   :value="item.VALUE"></el-option>
									</el-select>
								</el-col>
								<el-col :span="2" style="text-align: right">
									<span>工程地址&nbsp;&nbsp;</span>
								</el-col>
								<el-col :span="5">
									<el-input v-model="filters.gcdz" size="small" style="width:557px;" :readonly="true"></el-input>
								</el-col>
							</el-row>
						</el-form-item>
						<el-form-item>
							<el-row>
								<el-col :span="2" style="text-align: right">
									<span>工程名称&nbsp;&nbsp;</span>
								</el-col>
								<el-col :span="5">
									<el-input v-model="filters.gcmc" size="small" style="width:206px;" :readonly="true"></el-input>
								</el-col>
								<el-col :span="2" style="text-align: right">
									<span>报建单位&nbsp;&nbsp;</span>
								</el-col>
								<el-col :span="5">
									<el-input v-model="filters.bjdw" size="small" style="width:557px;" :readonly="true"></el-input>
								</el-col>
							</el-row>
						</el-form-item>
						<el-form-item>
							<el-row>
								<!-- <div v-if="showHg">
                                     <el-col :span="2" style="text-align: right">
                                         <span>小区名称&nbsp;&nbsp;</span>
                                     </el-col>
                                     <el-col :span="5">
                                         <el-input v-model="filters.xqmc" size="small" style="width:206px;" placeholder="输入小区名称"></el-input>
                                     </el-col>
                                 </div>
                                 <div v-else>
                                     <el-col :span="2" style="text-align: right">
                                         <span>用户性质&nbsp;&nbsp;</span>
                                     </el-col>
                                     <el-col :span="5">
                                         <el-input v-model="filters.yhxz" size="small" style="width:206px;" placeholder="输入用户性质"></el-input>
                                     </el-col>
                                 </div>-->
								<el-col :span="2" style="text-align: right">
									<span>联系人&nbsp;&nbsp;</span>
								</el-col>
								<el-col :span="5">
									<el-form-item prop="lxr" >
										<el-input v-model="filters.lxr" size="small" style="width:206px;" :readonly="true"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="2" style="text-align: right">
									<span>移动电话&nbsp;&nbsp;</span>
								</el-col>
								<el-col :span="5">
									<el-form-item prop="lxdh" >
										<el-input v-model="filters.lxdh" size="small" style="width:206px;" :readonly="true"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="2" style="text-align: right">
									<span>办公电话&nbsp;&nbsp;</span>
								</el-col>
								<el-col :span="5">
									<el-form-item prop="zjlxdh" >
										<el-input v-model="filters.zjlxdh" size="small" style="width:206px;" :readonly="true"></el-input>
									</el-form-item>
								</el-col>
							</el-row>
						</el-form-item>
						<el-form-item v-if="showJm">
							<el-col :span="2" style="text-align: right">
								<span>总建筑面积&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-form-item prop="zjzmj" >
									<el-input v-model="filters.zjzmj" size="small" style="width:206px;" readonly></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>住宅面积&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-form-item prop="zzmj" >
									<el-input v-model="filters.zzmj" size="small" style="width:206px;" readonly></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>地块简称&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-input v-model="filters.dkjc" size="small" style="width:206px;" :readonly="true"></el-input>
							</el-col>
						</el-form-item>
						<el-form-item v-if="showJm">
							<el-col :span="2" style="text-align: right">
								<span>户数&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-form-item prop="hs" >
									<el-input v-model="filters.hs" size="small" style="width:206px;" :readonly="true"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>设计范围&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-input v-model="filters.sjfw" size="small" style="width:557px;" :readonly="true"></el-input>
							</el-col>
						</el-form-item>
						<el-form-item v-if="showJm">
							<el-col :span="2" style="text-align: right">
								<span>单元楼号&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-input v-model="filters.dylh" size="small" style="width:206px;" :readonly="true"></el-input>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>规划泵房数&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-form-item prop="ghbfs" >
									<el-input v-model="filters.ghbfs" size="small" style="width:206px;" :readonly="true"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>交房时间&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-date-picker
										v-model="filters.jfsj"
										type="date"
										format="yyyy-MM-dd"
										value-format="yyyy-MM-dd"
										placeholder="选择日期" size="small" style="width: 207px;">
								</el-date-picker>
							</el-col>
						</el-form-item>
						<el-form-item v-if="showJm">
							<el-col :span="2" style="text-align: right">
								<span>分期情况&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-input v-model="filters.fqqk" size="small" style="width:206px;" :readonly="true"></el-input>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>房屋性质&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-select v-model="filters.fwxz" size="small" style="width:206px;" :readonly="true" :disabled="true">
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
								<el-input v-model="filters.yhxz" size="small" style="width:206px;" :readonly="true"></el-input>
							</el-col>
						</el-form-item>
						<el-form-item v-if="showGs">
							<el-col :span="2" style="text-align: right">
								<span>意向接表数量&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-form-item prop="yxjbsl" >
									<el-input v-model="filters.yxazsl" size="small" style="width:206px;" :readonly="true"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>接水用途&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-input v-model="filters.jsyt" size="small" style="width:206px;" :readonly="true"></el-input>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>用户性质&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-input v-model="filters.yhxz" size="small" style="width:206px;" :readonly="true"></el-input>
							</el-col>
							<!--<el-col :span="2" style="text-align: right">
								<span>原户号&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-input v-model="filters.yhh" size="small" style="width:206px;" :readonly="true"></el-input>
							</el-col>-->
						</el-form-item>
						<el-form-item v-if="showGs">
							<!--<el-col :span="2" style="text-align: right">
								<span>原表口径&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-input v-model="filters.ybkj" size="small" style="width:206px;" :readonly="true"></el-input>
							</el-col>-->
							<el-col :span="2" style="text-align: right">
								<span>计划开工时间&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-date-picker
										v-model="filters.jhkgsj"
										type="date"
										format="yyyy-MM-dd"
										value-format="yyyy-MM-dd"
										placeholder="选择日期" size="small" style="width: 207px;" :readonly="true">
									<!--format="yyyy-MM-dd HH:mm"-->
									<!--value-format="yyyy-MM-dd HH:mm"-->
								</el-date-picker>
							</el-col>
						</el-form-item>
						<el-form-item v-if="showHg">
							<el-col :span="2" style="text-align: right">
								<span>用水方式&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-select v-model="filters.ysfs" size="small" style="width:206px;" placeholder="请选择" :disabled="true">
									<el-option label="市政直供" value="市政直供"></el-option>
									<el-option label="二次加压" value="二次加压"></el-option>
									<el-option label="楼顶水箱" value="楼顶水箱"></el-option>
								</el-select>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>用水状况&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-select v-model="filters.yszk" size="small" style="width:206px;" placeholder="请选择" :disabled="true">
									<el-option label="良好" value="良好"></el-option>
									<el-option label="一般" value="一般"></el-option>
									<el-option label="差" value="差"></el-option>
								</el-select>
							</el-col>
							<!--<el-col :span="2" style="text-align: right">
								<span>户型状况&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-select v-model="filters.hxzk" size="small" style="width:206px;" placeholder="请选择" :disabled="true">
									<el-option label="商住" value="商住"></el-option>
									<el-option label="住宅" value="住宅"></el-option>
									<el-option label="综合" value="综合"></el-option>
								</el-select>
							</el-col>-->
							<el-col :span="2" style="text-align: right">
								<span>楼顶水箱&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-form-item>
									<el-input v-model="filters.ldsx" size="small" style="width:206px;" readonly></el-input>
								</el-form-item>
							</el-col>
						</el-form-item>
						<el-form-item v-if="showHg">
							<el-col :span="2" style="text-align: right">
								<span>楼房栋数&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-form-item prop="lfds" >
									<el-input v-model="filters.lfds" size="small" style="width:206px;" :readonly="true"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>楼房户数&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-form-item prop="lfhs" >
									<el-input v-model="filters.lfhs" size="small" style="width:206px;" :readonly="true"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>平房户数&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-form-item prop="pfhs" >
									<el-input v-model="filters.pfhs" size="small" style="width:206px;" :readonly="true"></el-input>
								</el-form-item>
							</el-col>
						</el-form-item>
						<el-form-item v-if="showHg">
							<el-col :span="2" style="text-align: right">
								<span>最高层数&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-form-item prop="zgcs" >
									<el-input v-model="filters.zgcs" size="small" style="width:206px;" :readonly="true"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>内部消防&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-select v-model="filters.nbxf" size="small" style="width:206px;" placeholder="请选择" :disabled="true">
									<el-option label="是" value="0"></el-option>
									<el-option label="否" value="1"></el-option>
								</el-select>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>车库用水&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-form-item prop="ckys" >
									<el-input v-model="filters.ckyss" size="small" style="width:206px;" :readonly="true"></el-input>
								</el-form-item>
							</el-col>
						</el-form-item>
						<el-form-item v-if="showHg">
							<el-col :span="2" style="text-align: right">
								<span>车棚用水&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-form-item prop="cpys" >
									<el-input v-model="filters.cpyss" size="small" style="width:206px;" :readonly="true"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>门面用水&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-form-item prop="mmys" >
									<el-input v-model="filters.mmyss" size="small" style="width:206px;" :readonly="true"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>公厕用水&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-form-item prop="gcys" >
									<el-input v-model="filters.gcyss" size="small" style="width:206px;" :readonly="true"></el-input>
								</el-form-item>
							</el-col>
						</el-form-item>
						<el-form-item v-if="showHg">
							<el-col :span="2" style="text-align: right">
								<span>物管用水&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-form-item prop="wgys" >
									<el-input v-model="filters.wyyss" size="small" style="width:206px;" :readonly="true"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>其他用水&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-input v-model="filters.qtys" size="small" style="width:206px;" :readonly="true"></el-input>
							</el-col>
							<el-col :span="2" style="text-align: right">
								<span>小区名称&nbsp;&nbsp;</span>
							</el-col>
							<el-col :span="5">
								<el-input v-model="filters.xqmc" size="small" style="width:206px;" :readonly="true"></el-input>
							</el-col>
						</el-form-item>
					</el-form>
				</el-card>
			</el-col>

			<!--小区水表安装基本信息-->
			<el-col :span=24>
				<el-card class="box-card1" style="margin-top: 15px;" v-if="sbazjbxx">
					<div slot="header" class="clearfix">
						<span>小区水表安装基本信息（户表）</span>
					</div>
					<el-table :data="ldxxs" highlight-current-row border v-loading="listLoading" style="width: 100%;">
						<el-table-column type="index" label="序号" width="50" align="center">
						</el-table-column>
						<el-table-column property="ldh" label="楼栋号" width="150" align="center">
						</el-table-column>
						<el-table-column property="dys" label="单元数" width="150" align="center">
						</el-table-column>
						<el-table-column property="lc" label="楼层" width="150" align="center">
						</el-table-column>
						<el-table-column property="mchs" label="一梯户数" width="150" align="center">
						</el-table-column>
						<el-table-column property="zhs" label="单元户数" width="150" align="center">
						</el-table-column>
						<el-table-column property="bz" label="备注" width="250" align="center">
						</el-table-column>
					</el-table>
				</el-card>

				<!--工商科报建信息-->
				<el-card class="box-card1" style="margin-top: 15px;" v-if="gssbazjbxx">
					<div slot="header" class="clearfix">
						<div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">报建信息录入
						</div>
					</div>
					<el-table :data="gsbjxx" highlight-current-row v-loading="gslistLoading" style="width: 100%;">
						<el-table-column type="index" label="序号" align="center">
						</el-table-column>
						<el-table-column property="yhh" label="原户号" align="center">
						</el-table-column>
						<el-table-column property="ybkjname" label="原表口径" align="center">
						</el-table-column>
						<el-table-column property="yxkjname" label="意向口径" align="center">
						</el-table-column>
						<el-table-column property="bz" label="备注" align="center">
						</el-table-column>
					</el-table>
				</el-card>
				<!--户改科报建信息-->
				<el-card class="box-card1" style="margin-top: 15px;" v-if="hgsbazjbxx">
					<div slot="header" class="clearfix">
						<div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">报建信息录入
						</div>
					</div>
					<el-tabs type="card" v-model="yzbtabBdz">
						<el-tab-pane label="小区水表安装基本信息（户表）" name="小区水表安装基本信息（户表）">
							<el-table :data="ldxxs" highlight-current-row v-loading="listLoading" style="width: 100%;">
								<el-table-column type="index" label="序号" align="center">
								</el-table-column>
								<el-table-column property="ldh" label="楼栋号" align="center">
								</el-table-column>
								<el-table-column property="dys" label="单元数" align="center">
								</el-table-column>
								<el-table-column property="lc" label="楼层" align="center">
								</el-table-column>
								<el-table-column property="mchs" label="每层户数" align="center">
								</el-table-column>
								<el-table-column property="zhs" label="单元户数" align="center">
								</el-table-column>
								<el-table-column property="bz" label="备注" align="center">
								</el-table-column>
							</el-table>
						</el-tab-pane>
						<el-tab-pane label="原总表信息表" name="原总表信息表">
							<el-table :data="yzbxx" highlight-current-row v-loading="yzblistLoading"
									  style="width: 100%;">
								<el-table-column type="index" label="序号" align="center">
								</el-table-column>
								<el-table-column property="hh" label="原户号" align="center">
								</el-table-column>
								<el-table-column property="hm" label="户名" align="center">
								</el-table-column>
								<el-table-column property="kjmc" label="口径" align="center">
								</el-table-column>
								<el-table-column property="ysxz" label="用水性质" align="center">
								</el-table-column>
								<el-table-column property="hyfl" label="行业分类" align="center">
								</el-table-column>
								<el-table-column property="kchs" label="用户报建数" align="center">
								</el-table-column>
							</el-table>
						</el-tab-pane>
					</el-tabs>
				</el-card>
			</el-col>

			<!--附件资料-->
			<el-col :span="24"><zlfj></zlfj></el-col>

			<el-col :span="24">
				<el-card class="box-card" style="margin-top: 15px;">
					<div slot="header">
						<span>立项信息</span>
					</div>
					<el-row>
						<el-form :model="lx" label-width="100px" ref="lx" :rules="rules">
							<el-row style="margin-top:10px;">
								<el-col :span="8" >
									<el-form-item label="项目情况">
										<el-radio-group v-model="lx.xmlb" @change="xmlbChange">
											<el-radio :label="lx.xmlb" label="0">新建立项目</el-radio>
											<el-radio :label="lx.xmlb" label="1">选择主项目</el-radio>
										</el-radio-group>
									</el-form-item>
								</el-col>
								<el-col :span="12" >
									<el-form-item label="主项目名称" v-if="lx.xmlb == '0'" prop="zxmmc">
										<el-input v-model="lx.zxmmc" style="width:50%;" @keyup.native="zxmmcChange"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="12" >
									<el-form-item label="选择主项目" v-if="lx.xmlb == '1'" prop="zxmid">
										<el-input v-model="lx.zxmmc" style="width:50%;" readonly></el-input>
										<el-button size="mini" type="primary" @click="openZxmlb">
											<i class="iconfont icon-xuanze"></i>选择</el-button>
										<!--<el-select v-model="lx.zxmid" style="width:50%;" @change="zxmmcSChange">
											<el-option v-for="item in xmList"
													   :key="item.id"
													   :label="item.xmmc"
													   :value="item.id"></el-option>
										</el-select>-->
									</el-form-item>
								</el-col>
							</el-row>
							<el-row style="margin-top:5px;">
								<el-col :span="8" >
									<el-form-item label="子项目名称">
										<el-input v-model="lx.xmmc" style="width:70%;"></el-input>
									</el-form-item>
								</el-col>
								<el-col :span="12" v-if="type=='10'" >
									<el-form-item label="供水方式">
										<el-select v-model="lx.gsfs" style="width:50%;">
											<el-option v-for="item in gsfsOption"
													   :key="item.VALUE"
													   :label="item.LABEL"
													   :value="item.VALUE"></el-option>
										</el-select>
									</el-form-item>
								</el-col>
								<el-col :span="12" v-if="1==2">
									<el-form-item label="项目类别">
										<el-select v-model="lx.xmfl" style="width:50%;"  :readonly="true">
											<el-option v-for="item in xmflm"
													   :key="item.codevalue"
													   :label="item.codename"
													   :value="item.codevalue"></el-option>
										</el-select>
									</el-form-item>
								</el-col>
							</el-row>
							<!--<el-row style="margin-top:5px;">
								<el-col :span="8" v-if="type=='10'" >
									<el-form-item label="供水方式">
										<el-select v-model="lx.gsfs" style="width:70%;">
											<el-option v-for="item in gsfsOption"
													   :key="item.VALUE"
													   :label="item.LABEL"
													   :value="item.VALUE"></el-option>
										</el-select>
									</el-form-item>
								</el-col>
							</el-row>-->
						<!--<el-col :span="24">
							<el-form-item label="工程类型">
								<el-row>
									<el-col :span="20">
										<el-checkbox-group v-model="gctz">
											<el-col :span="6" v-model="gctzm" v-for="item in gctzm" :key="item.codevalue">
												<el-checkbox :label="item.codevalue">{{item.codename}}</el-checkbox>
											</el-col>
										</el-checkbox-group>
									</el-col>
								</el-row>
							</el-form-item>
						</el-col>-->
						</el-form>
					</el-row>
				</el-card>
			</el-col>
			<el-col :span="24" style="text-align: center;margin-top: 25px;margin-bottom: 35px;">
				<el-col :span="24" style="text-align: center;margin: 5px;">
					<el-button size="mini" type="info" @click="toLxlb">
						<i class="iconfont icon-quxiao"></i>取消</el-button>

					<el-button size="mini" type="primary" @click="saveLxxx('lx')">
						<i class="iconfont icon-set_up_project"></i>立项</el-button>

					<el-button size="mini" type="primary" @click="goxmqj()">
						<i class="iconfont icon-xiangmuquanjing_"></i>项目全景</el-button>
				</el-col>

				<!--<el-button type="info" icon="el-icon-close" @click="toLxlb">取消</el-button>
				<el-button type="primary" @click="saveLxxx" icon="el-icon-check">确认立项</el-button>-->
			</el-col>

		</el-row>

		<el-dialog  title="选择主项目" :visible.sync ="checkXmVisible" >
			<el-row>
				<!--工具条-->
				<el-col :span="24" class="toolbar" style="border: 1px solid #f5f7fa;">
					<el-form  :model="select" style="margin-top: 10px;" size="small">
						<el-form-item>
							<el-col :span="3" style="text-align: right">
								<span>申请编号&nbsp;</span>
							</el-col>
							<el-col :span="6">
								<el-input v-model="select.xmbh"></el-input>
							</el-col>
							<el-col :span="1" style="text-align: right">
								&nbsp;
							</el-col>
							<el-col :span="3" style="text-align: right">
								<span>项目名称&nbsp;</span>
							</el-col>
							<el-col :span="6">
								<el-input v-model="select.xmmc"></el-input>
							</el-col>
							<el-col :span="3" style="text-align: right">
								&nbsp;
							</el-col>
						</el-form-item>
						<el-form-item>
							<el-col :span="3" style="text-align: right">
								<span>受理时间&nbsp;</span>
							</el-col>
							<el-col :span="6">
								<el-date-picker type="date" placeholder="选择开始时间" v-model="select.kssj" value-format="yyyy-MM-dd" style="width: 100%;">
								</el-date-picker>
							</el-col>
							<el-col class="line" :span="4" style="text-align: center">-</el-col>
							<el-col :span="6">
								<el-date-picker type="date" placeholder="选择结束时间" v-model="select.jssj" value-format="yyyy-MM-dd" style="width: 100%;">
								</el-date-picker>
							</el-col>
							<el-col :span="1"style="text-align: right">
								<span>&nbsp;</span>
							</el-col>
							<el-col :span="3" style="text-align: left">
								<el-button size="mini" type="primary" @click="getCheckZxm"><i class="iconfont icon-chaxun"></i>查询</el-button>
							</el-col>
						</el-form-item>
					</el-form>
				</el-col>
				<el-col :span="24" class="toolbar">
					<!--列表-->
					<el-table :data="row" highlight-current-row v-loading="listLoading" style="width: 100%;"
							  height="300" :header-cell-style="{fontSize:'15px',background:'#f5f7fa'}">
						<el-table-column type="index" label="序号" width="80" align="center">
						</el-table-column>
						<el-table-column property="xmbh" label="项目编号" width="180" align="center">
						</el-table-column>
						<el-table-column property="xmmc" label="项目名称" width="200" align="center">
						</el-table-column>
						<el-table-column property="lxsj_str" label="立项时间" width="150" align="center">
						</el-table-column>
						<!--<el-table-column property="xmdz" label="工程地址"  min-width="180" align="center">
                        </el-table-column>-->
						<el-table-column label="操作" width="100" align="center" fixed="right">
							<template slot-scope="scope">
								<label title="选择此项目" @click="CheckZxm(scope.$index, scope.row)">
									<i class="iconfont icon-queren" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
							</template>
						</el-table-column>
					</el-table>
				</el-col>
				<el-col :span="24" class="toolbar" >
					<!--工具条-->
					<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[5, 10, 30]"
								   @current-change="handleCurrentChange" @size-change="handleSizeChange"
								   :page-size="this.size" style="float:right;" :total="this.total">
					</el-pagination>
				</el-col>
			</el-row>
		</el-dialog>

	</section>

</template>

<script>
	import axios from 'axios'
	import zlfj from '@/views/xkylx/zlfj.vue';
	import { getSlxx,initSqbCheckbox,saveLxxx,getCheckZxm } from '../../api/xkylx';
	import { queryUser,getLdxxList,getYzbList,getGsbjxxList } from '../../api/xqsl';
	export default {
		components: {
			'zlfj': zlfj,
		},
		data() {
			var checkZxmmc = (rule, value, callback) => {
				if (this.lx.xmlb == '0' && this.lx.zxmmc == '') {
					callback(new Error('新建主项目名称不可为空'));
				} else {
					callback();
				}
			};
			var checkZxmid = (rule, value, callback) => {
				if (this.lx.xmlb == '1' && this.lx.zxmid == '') {
					callback(new Error('请选择主项目'));
				} else {
					callback();
				}
			};
			return {
				id : this.$route.query.id,
				type : this.$route.query.type,
				qyList:[],//区域下拉框数据集合
				azList:[],//安装类型下拉框数据集合
				fwxzList:[],
				xmflm:[],//项目分类码
				gctzm:[],//工程特征码
				gsfsOption:[],//供水方式
				xmList:[],//主项目列表
				filters: {
					sqlx: '',sqdlx:'',
					sqbh: '',azlx: '',slrq: '',xmdz_xzqybh: '',lxr: '',lxrsjh: '',xmmc: '',dwmc: '',yhxz: '',xmdz: '',
					zjzmj: '',zzjzmj: '',dkjc:'',hs:'',sjfw:'',dylh:'',ghbfs:'',jfsj:'',fqqk:'',
					yxazsl: '',jsyt: '',yhh: '',ybkj: '',yxazsl:'',zjlxdh:'',lfds:'',lfhs:'',pfhs:'',
					zgcs:'',nbxf:'',ckyss:'',cpyss:'',mmyss:'',gcyss:'',qtys:'',xqmc:'',wyyss:'',ldsx:''
				},
				gctz : [],
				lx: {
					xmlb : '0',
					zxmmc : '',
					zxmid : '',
					xmmc : '',
					xmfl : '',
					gsfs:''
				},
				showHg: false,
				showJm:false,
				showGs:false,
				sbazjbxx:false,//新建小区申请楼栋表列表是否显示
				ldxxs:[],//新建小区申请楼栋表表单数据
				listLoading: false,
				ldxxIds:[],//已添加新建小区申请楼栋ID集合
				checkXmVisible : false,
				row: [],
				total: 5,//表格数据总数量
				page: 1,//当前页码
				size: 5,//每页容量
				listLoading: false,
				select: {
					xmbh:'',
					dwmc:'',
					lxr:'',
					kssj:'',
					jssj:''
				},
				sqzbid:this.$route.query.id,
				/*工商科报建信息录入*/
				gssbazjbxx:false,//工商报建信息列表是否显示
				gsbjxx:[],//工商报建信息集合
				hgsbazjbxx:false,//户改科列表是否显示
				yzbtabBdz:'小区水表安装基本信息（户表）',
				yzbxx:[],//原总表信息表集合
				yzblistLoading:false,
				rules: {
					zxmmc: [
						{validator: checkZxmmc, trigger: 'blur'}
					],
					zxmid: [
						{validator: checkZxmid, trigger: 'blur'}
					]
				},
			}
		},
		mounted(){
			if(this.type == '10'){
				this.filters.sqlx = "居民科";
				this.showJm = true;
				this.sbazjbxx = true;
				this.gssbazjbxx = false;
				this.hgsbazjbxx = false;
				this.getLdxx(this.id);
			}else if(this.type == '20'){
				this.filters.sqlx = "工商科";
				this.showGs = true;
				this.sbazjbxx = false;
				this.gssbazjbxx = true;
				this.hgsbazjbxx = false;
				this.getGsbjxx();
			}else{
				this.filters.sqlx = "户改科";
				this.showHg = true;
				this.sbazjbxx = false;
				this.gssbazjbxx = false;
				this.hgsbazjbxx = true;
				this.getYzb();
				this.getLdxx(this.sqzbid);
			}
			//this.filters.sqdlx = this.type;

			this.initCheckBox();
			this.initCheckBox2();
			this.initData();
		},
		methods: {
			openZxmlb:function(){
				this.checkXmVisible = true;
				this.getCheckZxm()
			},
			CheckZxm(index, row){
				this.lx.zxmid = row.id;
				this.lx.zxmmc = row.xmmc;
				this.checkXmVisible = false;
			},
			getCheckZxm() {
				let params = Object.assign({ page: this.page, size: this .size }, this.select);
				this.listLoading = true;
				getCheckZxm(params).then((res) => {
					this.total = res.data.total;
					this.row = res.data.row;
					this.listLoading = false;
				});
			},
			//原总表列表展示
			getYzb() {
				let para = {id:this.sqzbid};
				this.yzblistLoading = true;
				getYzbList(para).then((res) => {
					this.yzbxx = res.data.yzbxx;
					this.yzblistLoading = false;
				});
			},
			//工商报建信息列表
			getGsbjxx() {
				let para = {id:this.sqzbid};
				this.gslistLoading = true;
				getGsbjxxList(para).then((res) => {
					this.gsbjxx = res.data.gsbjxx;
					this.gslistLoading = false;
				});
			},
			handleCurrentChange(val) {
				this.page = val;
				this.getCheckZxm();
			},
			handleSizeChange(val) {
				this.size = val;
				this.page = 1;
				this.getCheckZxm();
			},
			initCheckBox:function(){//加载下拉框
				let params = {type : this.type,flag:'gsfs'};
				initSqbCheckbox(params).then((res)=>{
					this.qyList = res.data.qyList;
					this.azList = res.data.azList;
					this.xmflm = res.data.xmflm;
					this.gctzm = res.data.gctzm;
					this.gsfsOption = res.data.gsfsOption;
					this.xmList = res.data.xm;
				})
			},
			initCheckBox2(){
				let param = {
					type:'fwxz'
				};
				queryUser(param).then((res)=>{
					//this.user =  res.data.user;
					//this.kjList = res.data.kjList;
					//this.ysxzList = res.data.ysxzList;
					//this.hyflList = res.data.hyflList;
					this.fwxzList = res.data.fwxzList;
				})
			},
			//获取申请表数据
			initData:function(){
				let params = {
					id : this.id,
					type : this.type
				}
				getSlxx(params).then((res) => {
					let obj = eval('('+res.data.ssbxx+')');
					this.filters.sqdlx = obj.type;
					this.filters.ssqy = obj.xmdz_xzqybh == 'null'? '': obj.xmdz_xzqybh;
					this.filters.slrq = obj.slrq_str;
					this.filters.jfsj = obj.jfsj_str;
					this.filters.gcmc = obj.xmmc == 'null'? '': obj.xmmc;
					this.filters.gcdz = obj.xmdz == 'null'? '': obj.xmdz;
					this.filters.bjdw = obj.dwmc == 'null'? '': obj.dwmc;
					this.filters.lxdh = obj.lxrsjh;
					this.filters.zjlxdh = obj.zjlxdh;
					this.filters.fwxz = obj.fwxz;
					this.filters.zzmj = obj.zzjzmj == 'null'? '': obj.zzjzmj;
					this.filters.jhkgsj = obj.jhkgsj_str;
					//this.filters.zjlxdh = obj.zjlxdh;
					this.filters.ysfs = obj.xysfs == 'null'? '': obj.xysfs;
					this.filters.yszk = obj.xyszk == 'null'? '': obj.xyszk;
					this.filters.hxzk = obj.xhxzk;
					this.filters.ldsx = obj.ldsx;
					var aProps = Object.getOwnPropertyNames(this.filters);
					//var bProps = Object.getOwnPropertyNames(obj);
					for (var i = 0; i < aProps.length; i++) {
						var propName = aProps[i];
						if(obj[propName] == 'null'){
							this.filters[propName] = '';
						}else if(obj[propName] == undefined){
							continue;
						}else{
							this.filters[propName] = obj[propName];
						}
					}
				})
			},
			xmlbChange(value){
				if(value == '0'){

				}else{

				}
			},
			zxmmcChange(value){
				this.lx.xmmc = this.lx.zxmmc;
			},
			zxmmcSChange(){

			},
			toLxlb(){//跳转页面Fkpjlb
				this.$routerTab.close();
				this.$routerTab.refresh('/Lxlb');
				this.$routerTab.refresh('/Xckcrwcj');//刷新发起勘查列表 以防止数据错误影响勘查

			},
			goxmqj(){
				this.$router.push({path:'/Xmlct', query:{ xmid:this.sqzbid }})
			},
			saveLxxx(lx){//保存立项信息
				if( this.filters.ssqy == '' || this.filters.ssqy == 'null' ){
					this.$message({
						message: '受理信息所属区域未填写，请补充信息后重新立项！',
						type: 'error'
					});
					return;
				}
				this.$refs[lx].validate((valid) => {
					if (valid) {
						let gctz = this.gctz.join(",")
						let params = Object.assign({ id : this.id, type : this.type, gctz : gctz}, this.lx);
						saveLxxx(params).then((res) => {
							if(res.result == "success"){
								this.$message({
									message: '操作成功',
									type: 'success'
								});
								this.toLxlb();
							}else{
								this.$message({
									message: '操作失败',
									type: 'error'
								});
								this.toLxlb();
							}
						})
					} else {
						return false;
					}
				});
			},
			//新建小区申请楼栋表列表展示
			getLdxx(id) {
				let para = {
					id:id
				};
				this.listLoading = true;
				getLdxxList(para).then((res) => {
					this.ldxxs = res.data.ldxxs;
					this.listLoading = false;
				});
			}
		}
	}
</script>

<style scoped>

</style>
