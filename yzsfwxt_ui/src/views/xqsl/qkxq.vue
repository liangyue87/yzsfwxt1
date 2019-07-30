<template>
    <section>
        <el-row>
            <!--工具条-->
            <el-col :span="24">
                <div v-if="ishx == '1'">

                </div>
                <div v-else>
                    <!--红黄绿灯时间-->
                    <hhldsx :xmid="rwid" :activityid="activityId"></hhldsx>
                </div>
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
                                    <el-input v-model="filters.sqlx" size="small" style="width:206px;" placeholder="输入申请编号" :readonly="true"></el-input>
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
                                    <el-select v-model="filters.azlx" disabled size="small" style="width:206px;">
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
                                    <el-input v-model="filters.slrq" size="small" style="width:206px;" :readonly="true"></el-input>
                                </el-col>
                            </el-row>
                        </el-form-item>
                        <el-form-item>
                            <el-row>
                                <el-col :span="2" style="text-align: right">
                                    <span>所属区域&nbsp;&nbsp;</span>
                                </el-col>
                                <el-col :span="5">
                                    <el-select v-model="filters.ssqy" disabled size="small" style="width:206px;">
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
                                <el-col :span="2" style="text-align: right">
                                    <span>联系人&nbsp;&nbsp;</span>
                                </el-col>
                                <el-col :span="5">
                                    <el-input v-model="filters.lxr" size="small" style="width:206px;" :readonly="true"></el-input>
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <span>移动电话&nbsp;&nbsp;</span>
                                </el-col>
                                <el-col :span="5">
                                    <el-input v-model="filters.lxdh" size="small" style="width:206px;" :readonly="true"></el-input>
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <span>办公电话&nbsp;&nbsp;</span>
                                </el-col>
                                <el-col :span="5">
                                    <el-form-item>
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
                                <el-input v-model="filters.zjzmj" size="small" style="width:206px;" :readonly="true"></el-input>㎡
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>住宅面积&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.zzmj" size="small" style="width:206px;" :readonly="true"></el-input>㎡
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
                                <el-input v-model="filters.hs" size="small" style="width:206px;" :readonly="true"></el-input>
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
                                <el-input v-model="filters.ghbfs" size="small" style="width:206px;" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>交房时间&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.jfsj" size="small" style="width:206px;" :readonly="true"></el-input>
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
                                <el-select v-model="filters.fwxz" size="small" style="width:206px;" :disabled="true">
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
                                <el-input v-model="filters.yxjbsl" size="small" style="width:206px;" :readonly="true"></el-input>
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
                        </el-form-item>
                        <el-form-item v-if="showGs">
                            <el-col :span="2" style="text-align: right">
                                <span>计划开工时间&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.jhkgsj" size="small" style="width:206px;"  :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item v-if="showHg">
                            <el-col :span="2" style="text-align: right">
                                <span>用水方式&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.ysfs" size="small" style="width:206px;" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>用水状况&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.yszk" size="small" style="width:206px;" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>楼顶水箱&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="ldsx">
                                    <el-input v-model="filters.ldsx" :readonly="true" size="small" style="width:206px;"
                                              placeholder=""></el-input>
                                </el-form-item>
                            </el-col>
                        </el-form-item>
                        <el-form-item v-if="showHg">
                            <el-col :span="2" style="text-align: right">
                                <span>楼房栋数&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.lfds" size="small" style="width:206px;" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>楼房户数&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.lfhs" size="small" style="width:206px;" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>平房户数&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.pfhs" size="small" style="width:206px;" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item v-if="showHg">
                            <el-col :span="2" style="text-align: right">
                                <span>最高层数&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.zgcs" size="small" style="width:206px;" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>内部消防&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.nbxf" size="small" style="width:206px;" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>车库用水&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.ckys" size="small" style="width:206px;" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item v-if="showHg">
                            <el-col :span="2" style="text-align: right">
                                <span>车棚用水&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.cpys" size="small" style="width:206px;" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>门面用水&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.mmys" size="small" style="width:206px;" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>公厕用水&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.gcys" size="small" style="width:206px;" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item v-if="showHg">
                            <el-col :span="2" style="text-align: right">
                                <span>物管用水&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.wgys" size="small" style="width:206px;" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>其他用水&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.qtys" size="small" style="width:206px;" :readonly="true"></el-input>
                            </el-col>
                            <div v-if="showHg">
                                <el-col :span="2" style="text-align: right">
                                    <span>小区名称&nbsp;&nbsp;</span>
                                </el-col>
                                <el-col :span="5">
                                    <el-input v-model="filters.xqmc" size="small" style="width:206px;" :readonly="true"></el-input>
                                </el-col>
                            </div>
                        </el-form-item>
                    </el-form>
                </el-card>
            </el-col>

            <!--小区水表安装基本信息-->
            <el-col :span=24>
                <el-card class="box-card1" style="margin-top: 15px;" v-if="sbazjbxx">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">报建信息录入</div>
                    </div>
                    <el-table :data="ldxxs" highlight-current-row v-loading="listLoading" style="width: 100%;">
                        <el-table-column type="index" label="序号" align="center">
                        </el-table-column>
                        <el-table-column property="ldh" label="楼栋号" align="center">
                        </el-table-column>
                        <el-table-column property="dys" label="单元数" align="center">
                        </el-table-column>
                        <el-table-column property="lc" label="楼层" align="center">
                        </el-table-column>
                        <el-table-column property="mchs" label="一梯户数" align="center">
                        </el-table-column>
                        <el-table-column property="zhs" label="单元户数" align="center">
                        </el-table-column>
                        <el-table-column property="bz" label="备注" align="center">
                        </el-table-column>
                    </el-table>
                </el-card>

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

                <el-card class="box-card1" style="margin-top: 15px;" v-if="hgsbazjbxx">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">报建信息录入</div>
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
                                <el-table-column property="mchs" label="一梯户数" align="center">
                                </el-table-column>
                                <el-table-column property="zhs" label="单元户数" align="center">
                                </el-table-column>
                                <el-table-column property="bz" label="备注" align="center">
                                </el-table-column>
                            </el-table>
                        </el-tab-pane>
                        <el-tab-pane label="原总表信息表" name="原总表信息表">
                            <el-table :data="yzbxx" highlight-current-row v-loading="yzblistLoading" style="width: 100%;">
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

            <!--资料上传-->
            <el-col :span=24>
                <el-card class="box-card" style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">资料上传</div>
                    </div>
                    <el-table :data="zlrow"  :stripe="true" style="width: 100%;" >
                        <el-table-column prop="NAME" label="资料类型" align="center">
                        </el-table-column>
                        <el-table-column prop="COUNT" label="资料数量" align="center">
                        </el-table-column>
                        <el-table-column label="操作" align="center">
                            <template slot-scope="scope" >
                                <el-row>
                                    <label title="详情">
                                        <i class="iconfont icon-xiangqing"
                                           @click="toDetial_vue(scope.$index, scope.row)"
                                           style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                    </label>
                                </el-row>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-card>
                <div v-if="ishx == '1'">

                </div>
                <div v-else>
                    <el-row style="text-align: center;margin-top: 25px;">
                        <el-button size="mini" type="success" @click="openDy">
                            <i class="iconfont icon-dayin_f"></i>打印</el-button>
                        <router-link :to="tabUrl">
                            <el-button size="mini" type="primary" :loading="submitLoading" @click="handleAdd">
                                <i class="iconfont icon-queren"></i>签收<!--是否确认签收？-->
                            </el-button>
                        </router-link>
                        <el-button size="mini" type="primary" @click="openZd">
                            <i class="iconfont  icon-tuxing"></i>转单</el-button>
                        <el-button size="mini" type="primary" @click="goxmqj()" >
                            <i class="iconfont icon-xiangmuquanjing_"></i>项目全景
                        </el-button>
                    </el-row>
                </div>
            </el-col>
        </el-row>
        <el-dialog title="" :visible.sync ="printFormVisible">
            <div id="printTest">
                <table class="border-table">
                    <caption align="top"><span style="font-size: 20px;font-family: 黑体;">合肥供水集团有限公司业务受理通知单</span></caption>
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
                        <td width="40%" height="40px">{{dy_bjdw}}</td>
                        <td width="10%" height="40px">业务类型</td>
                        <td width="40%" height="40px">{{ywlx}}</td>
                    </tr>
                    <tr>
                        <td width="10%" height="40px">工程名称</td>
                        <td width="90%" height="40px" colspan="3">{{dy_xmmc}}</td>
                    </tr>
                    <tr>
                        <td width="10%" height="40px">工程地址</td>
                        <td width="90%" height="40px" colspan="3">{{dy_xmdz}}</td>
                    </tr>
                    <tr>
                        <td height="40px" colspan="4"><span style="font-family: 黑体;">已接受用户提供的纸质申报资料明细</span></td>
                    </tr>
                    <tr v-for="(item) in zlsc_data" :key="item.index">
                        <td height="40px" colspan="3">《<span>{{item.TYPE}}</span>》</td>
                        <td height="40px"><span>{{item.VALUE}}</span>份</td>
                    </tr>
                    <tr>
                        <th align="left"  height="40px" colspan="3">受理单位：合肥供水集团有限公司</th>
                        <th align="left" height="40px">受理人：<span id="dy_slr"></span></th>
                    </tr>
                    <tr>
                        <th align="left"  height="40px" colspan="3">用户确认签章：</th>
                        <th align="left"  height="40px">日期：<span id="dy_rq"></span></th>
                    </tr>
                </table>
            </div>
            <div style="text-align: center;">
                <el-button size="mini" style="margin-bottom: 15px" type="success" v-print="'#printTest'">
                    <i class="iconfont icon-dayin_f"></i>打印
                </el-button>
            </div>
        </el-dialog>


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
                                    <label title="查看">
                                        <i class="iconfont icon-yulan"  @click="downloadFile_vue(scope.$index, scope.row)"
                                           style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                    </label>
                                    <label title="下载">
                                        <i class="iconfont icon-xiazai"
                                           @click="toDownload_vue(scope.$index, scope.row)"
                                           style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                    </label>
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

        <!--工商报建信息新增界面-->
        <el-dialog title="新增" :visible.sync="gsaddFormVisible">
            <el-form :model="gsaddForm" label-width="80px" ref="gsaddForm">
                <el-form-item label="原户号">
                    <el-input v-model="gsaddForm.yhh" size="small" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="原表口径" prop="ybkj">
                    <el-select v-model="gsaddForm.ybkj" size="small" style="width:206px;" placeholder="请选择">
                        <el-option v-for="item in kjList"
                                   :key="item.VALUE"
                                   :label="item.LABEL"
                                   :value="item.VALUE"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="意向口径" prop="yxkj">
                    <el-select v-model="gsaddForm.yxkj" size="small" style="width:206px;" placeholder="请选择">
                        <el-option v-for="item in kjList"
                                   :key="item.VALUE"
                                   :label="item.LABEL"
                                   :value="item.VALUE"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="gsaddForm.bz" size="small" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer" style="text-align: center">
                <el-button size="mini" type="info" @click.native="gsaddFormVisible = false">
                    <i class="iconfont icon-quxiao"></i>取消
                </el-button>
                <el-button size="mini" type="primary" @click.native="gsaddSubmit">
                    <i class="iconfont icon-queren"></i>提交
                </el-button>
            </div>
        </el-dialog>

        <!--工商报建信息编辑界面-->
        <el-dialog title="编辑" :visible.sync="gseditFormVisible">
            <el-form :model="gseditForm" label-width="80px" ref="gseditForm">
                <el-form-item label="原户号">
                    <el-input v-model="gseditForm.yhh" size="small" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="原表口径" prop="ybkj">
                    <el-select v-model="gseditForm.ybkj" size="small" style="width:206px;" placeholder="请选择">
                        <el-option v-for="item in kjList"
                                   :key="item.VALUE"
                                   :label="item.LABEL"
                                   :value="item.VALUE"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="意向口径" prop="yxkj">
                    <el-select v-model="gseditForm.yxkj" size="small" style="width:206px;" placeholder="请选择">
                        <el-option v-for="item in kjList"
                                   :key="item.VALUE"
                                   :label="item.LABEL"
                                   :value="item.VALUE"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="gseditForm.bz" size="small" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer" style="text-align: center">
                <el-button size="mini" type="info" @click.native="gseditFormVisible = false">
                    <i class="iconfont icon-quxiao"></i>取消
                </el-button>
                <el-button size="mini" type="primary" @click.native="gseditSubmit">
                    <i class="iconfont icon-queren"></i>提交
                </el-button>
            </div>
        </el-dialog>

        <el-dialog title="转单" :visible.sync="zdFormVisible">
            <div class="text item">
                <el-row>
                    <el-form label-width="120px" :inline="true">
                        <el-radio-group v-model="zdresource" style="width: 100%;">
                            <el-col :span="8" v-model="zddata" v-for="(item,index) in zddata" class="col" :key="index">
                                <el-form-item style="width: 45%">
                                    <el-radio v-model="zdradio" :label="item.USERID">
                                        {{item.USERNAME}}（在建项目{{item.NUM}}个）
                                    </el-radio>
                                </el-form-item>
                            </el-col>
                            <el-col style="text-align: center">
                                <el-form-item style="width: 45%">
                                    <el-button size="mini" type="primary" :loading="zdLoading" @click="zd_vue">
                                        <i class="iconfont icon-queren"></i>提交
                                    </el-button>
                                </el-form-item>
                            </el-col>
                        </el-radio-group>
                    </el-form>
                </el-row>
            </div>
        </el-dialog>

    </section>

</template>

<script>
    import DocCamera from '../template/DocCamera'
    import hhldsx from '@/views/template/hhldsx.vue';
    import {queryZlcount,queryUser,getScwj,addLdxx,editLdxx,getLdxxList,remove,importFile,
        getYzbList,yzbremove,addYzb,editYzb,toZpry,toDetial,download,toXqqs,Qsmessages,getXqslById,
        addGsbjxx,getGsbjxxList,editGsbjxx,gsremove,updateCheckTask,downloadfj,queryzdry,saveZd} from '../../api/xqsl';
    export default {
        name: "editxq",
        components:{
            'hhldsx': hhldsx,
            'DocCamera' : DocCamera
        },
        data() {
            return {
                ishx:this.$route.query.ishx,//项目流程图回显参数
                /*转单*/
                zdFormVisible:false,
                zdresource:'',
                zdradio : '',
                zddata :[],
                zdLoading:false,
                /*转单*/
                ywlx:'',
                dy_bjdw:'',
                dy_xmmc:'',
                dy_xmdz:'',
                tabUrl:'',
                submitLoading:false,
                activityId:this.$route.query.activityId,
                taskId:this.$route.query.taskId,
                rwid:this.$route.query.rwid,
                type:this.$route.query.TYPEID,

                /*拍摄*/
                fileName: 'xqsl',//给子组件的文件名字
                dirName: '',//保存图片的文件夹名字
                creamVisible : false,

                /*资料上传及拍摄*/
                zlType:'',//选中资料类型
                fileList:[],//已选中文件
                zlrow:[],//已上传资料集合
                dialogTableVisible:false,//是否打开资料详情界面
                gridData:[],//已上传资料详情集合
                gridblistLoading:false,
                zlxxPage:1,
                zlxxSize:5,
                zlxxTotal:0,
                slide1:[],

                sqid:'',

                /*受理指派*/
                slzpFormVisible:false,
                resource:'',
                radio : '',//选中的指派人员
                data :[],

                sqList:[],
                qyList:[],//区域下拉框数据集合
                azList:[],//安装类型下拉框数据集合
                kjList:[],//口径下拉框数据集合
                ysxzList:[],//用水性质下拉框数据集合
                hyflList:[],//行业分类下拉框数据集合
                fwxzList:[],//房屋性质下拉框数据集合
                filters: {
                    sqlx: '',sqdlx:'',fwxz:'',
                    sqbh: '',azlx: '',slrq: '',ssqy: '',lxr: '',lxdh: '',gcmc: '',bjdw: '',yhxz: '',gcdz: '',xqmc:'',

                    zjzmj: '',zzmj: '',dkjc:'',hs:'',sjfw:'',dylh:'',ghbfs:'',jfsj:'',fqqk:'',jhkgsj:'',zjlxdh:'',

                    yxjbsl: '',jsyt: '',yhh: '',ybkj: '',

                    ysfs:'',yszk:'',hxzk:'',lfds:'',lfhs:'',pfhs:'',zgcs:'',nbxf:'',ckys:'',cpys:'',mmys:'',
                    gcys:'',wgys:'',qtys:'',ldsx:''
                },
                showHg: false,
                showJm:false,
                showGs:false,

                sbazjbxx:false,//新建小区申请楼栋表列表是否显示
                ldxxs:[],//新建小区申请楼栋表表单数据
                addFormVisible:false,
                addLoading: false,
                addForm:{
                    ldh:'',dys:'',lc:'',mchs:'',zhs:'',bz:''
                },
                editFormVisible:false,
                editLoading: false,
                editForm:{
                    ldh:'',dys:'',lc:'',mchs:'',zhs:'',bz:''
                },
                listLoading: false,

                hgsbazjbxx:false,//户改科列表是否显示
                yzbtabBdz:'小区水表安装基本信息（户表）',
                yzbxx:[],//原总表信息表集合
                yzblistLoading:false,
                yzbaddFormVisible:false,
                yzbaddLoading: false,
                yzbaddForm:{
                    hh:'',hm:'',kjbh:'',ysxzbh:'',hyflbh:'',kchs:'',bz:''
                },
                yzbeditFormVisible:false,
                yzbeditLoading: false,
                yzbeditForm:{
                    hh:'',hm:'',kjbh:'',ysxzbh:'',hyflbh:'',kchs:'',bz:''
                },

                printFormVisible:false,//打印界面是否显示
                zllx_data:[],//资料类型集合
                zlsc_data:[],//已上传资料集合
                fileIds:[],//已上传的资料ID集合
                ldxxIds:[],//已添加新建小区申请楼栋ID集合
                yzbIds:[],//原总表信息表ID集合
                updateId:'',//更新唯一标识
                user:"",//当前用户

                yjfjlx:'',
                ejfjlx:'',

                /*工商科报建信息录入*/
                gssbazjbxx:false,//工商报建信息列表是否显示
                gsbjxx:[],//工商报建信息集合
                gslistLoading:false,
                gsaddFormVisible:false,
                gseditFormVisible:false,
                gsaddForm:{
                    yhh:'',ybkj:'',yxkj:'',bz:''
                },
                gseditForm:{
                    yhh:'',ybkj:'',yxkj:'',bz:''
                },
            }
        },
        mounted(){
            this.queryinit();
            this.findUser();
            this.Qsmessage();
            this.changeQszt();
        },
        methods: {
            //跳转至项目全景
            goxmqj(){
                this.$router.push({path:'/Xmlct', query:{ xmid:this.rwid  }})
            },
            //打开转单界面
            openZd(){
                queryzdry().then(res=>{
                    this.zddata = res.list;
                    this.zdFormVisible = true;
                });
            },
            //转单
            zd_vue(){
                if(this.zdresource == ''){
                    this.$message({
                        message: '请选择转单人员！',
                        type: 'warning'
                    });
                }else{
                    this.zdFormVisible = false;
                    let para = {taskId:this.taskId,zdr:this.zdresource,rwid:this.rwid,type:this.type,
                        xmmc:this.filters.xmmc};
                    this.$routerTab.close();
                    this.$routerTab.refresh('/Backlog');
                    this.$routerTab.refresh('/MyWork');
                    saveZd(para).then(res=>{
                        if(res){
                            this.$message({
                                message: '转单成功',
                                type: 'success'
                            });
                        }else{
                            this.$message.error('转单失败！');
                        }
                    });
                }
            },
            //流程改变签收状态
            changeQszt(){
                if(this.taskId != '' && this.taskId != undefined){
                    let para = {taskId: this.taskId};
                    updateCheckTask(para).then(res=>{

                    });
                }
            },
            //工商报建信息新增
            gshandleAdds(){
                this.gsaddFormVisible = true;
                this.gsaddForm = {
                    yhh:'',ybkj:'',yxkj:'',bz:''
                };
            },
            //工商报建信息修改
            gshandleEdit(index,row){
                this.gseditFormVisible = true;
                this.gseditForm = Object.assign({}, row);//编辑表单的赋值
            },
            //工商报建信息删除
            gshandleDel(index,row){
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.gslistLoading = true;
                    let para = {id: row.id};
                    gsremove(para).then((res) => {
                        this.gslistLoading = false;
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getGsbjxx();
                    });
                }).catch(() => {

                });
            },
            //工商报建信息新增保存
            gsaddSubmit(){
                this.$refs.gsaddForm.validate((valid) => {
                    if (valid) {
                        let para = Object.assign({sqlx:this.filters.sqlx,sqid:this.updateId}, this.gsaddForm);
                        addGsbjxx(para).then((res) => {
                            this.$message({
                                message: '提交成功',
                                type: 'success'
                            });
                            this.$refs['gsaddForm'].resetFields();
                            this.gsaddFormVisible = false;
                            this.getGsbjxx();
                        });
                    }
                });
            },
            //工商报建信息列表
            getGsbjxx() {
                let para = {id:this.updateId};
                this.gslistLoading = true;
                getGsbjxxList(para).then((res) => {
                    this.gsbjxx = res.data.gsbjxx;
                    this.gslistLoading = false;
                });
            },
            //工商报建信息列表编辑
            gseditSubmit: function () {
                this.$refs.gseditForm.validate((valid) => {
                    if (valid) {
                        let para = Object.assign({}, this.gseditForm);
                        editGsbjxx(para).then((res) => {
                            this.$message({
                                message: '提交成功',
                                type: 'success'
                            });
                            this.$refs['gseditForm'].resetFields();
                            this.gseditFormVisible = false;
                            this.getGsbjxx();
                        });
                    }
                });
            },
            //项目经理签收时发送短信
            Qsmessage(){

            },
            //打开附件详情窗口
            toDetial_vue(index, row) {
                this.dialogTableVisible = true;
                this.gridblistLoading = true;
                if(row == 2){

                }else{
                    this.yjfjlx = row.ID;
                    this.ejfjlx = row.PARENTID;
                }
                let params = {
                    ejfjlx: this.yjfjlx,
                    yjfjlx: this.ejfjlx,
                    sqlx: this.sqid,
                    sqdid:this.updateId,
                    page: this.zlxxPage - 1,
                    size: this.zlxxSize
                };
                toDetial(params).then((res) => {
                    if (res.data != null && res.data.row != null) {
                        this.gridData = res.data.row;
                        this.zlxxTotal = res.data.total;
                        this.gridblistLoading = false;
                    }
                })
            },
            //下载附件
            toDownload_vue(index,row){
                let formData = new FormData();
                formData.append("path",row.FJLJ);
                downloadfj(formData).then((res)=>{
                    const content = res.data;
                    const blob = new Blob([content]);
                    const fileName = row.FJMC;
                    if ('download' in document.createElement('a')) { // 非IE下载
                        const elink = document.createElement('a');
                        elink.download = fileName;
                        elink.style.display = 'none';
                        elink.href = URL.createObjectURL(blob);
                        document.body.appendChild(elink);
                        elink.click();
                        URL.revokeObjectURL(elink.href); // 释放URL 对象
                        document.body.removeChild(elink);
                    } else { // IE10+下载
                        navigator.msSaveBlob(blob, fileName);
                    }
                });
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
            //打开受理指派人员界面
            slzp_vue(){
                this.slzpFormVisible = true;
            },
            //获取上个页面的参数
            queryinit:function(type){
                let params = this.$route.query;
                if(type == 1){

                }else{
                    this.filters.sqlx = params.TYPE;
                    this.filters.sqbh = params.SQBH;
                    this.filters.slrq = params.SLRQ;
                    this.filters.azlx = params.AZLX;
                    this.filters.ssqy = params.XMDZ_XZQYBH;
                    this.filters.lxr = params.LXR;
                    this.filters.lxdh = params.LXRSJH;
                    this.filters.zjlxdh = params.ZJLXDH;
                    this.filters.gcmc = params.XMMC;
                    this.filters.bjdw = params.DWMC;
                    this.filters.yhxz = params.YHXZ;
                    this.filters.gcdz = params.XMDZ;
                    this.updateId = params.ID;
                    if(params.TYPEID == '10'){
                        this.showJm = true;
                        this.sbazjbxx = true;
                        this.gssbazjbxx = false;
                        this.hgsbazjbxx = false;
                        this.getLdxx(params.ID);
                        this.filters.zjzmj = params.ZJZMJ;
                        this.filters.zzmj = params.ZZJZMJ;
                        this.filters.dkjc = params.DKJC;
                        this.filters.hs = params.HS;
                        this.filters.sjfw = params.SJFW;
                        this.filters.dylh = params.DYLH;
                        this.filters.ghbfs = params.GHBFS;
                        this.filters.jfsj = params.JFSJ;
                        this.filters.fqqk = params.FQQK;
                        this.filters.fwxz = params.FWXZ;
                        this.sqid = "10";
                    }else if(params.TYPEID == '20'){
                        this.showGs = true;
                        this.sbazjbxx = false;
                        this.gssbazjbxx = true;
                        this.hgsbazjbxx = false;
                        this.getGsbjxx();
                        this.filters.yxjbsl = params.YXAZSL;
                        this.filters.jsyt = params.JSYT;
                        this.filters.yhh = params.YHH;
                        this.filters.ybkj = params.YBKJ;
                        this.filters.jhkgsj = params.JHKGSJ;
                        this.sqid = "20";
                    }else{
                        this.showHg = true;
                        this.sbazjbxx = false;
                        this.gssbazjbxx = false;
                        this.hgsbazjbxx = true;
                        this.getLdxx(params.ID);
                        this.getYzb(params.ID);
                        this.filters.xqmc = params.XQMC;
                        this.filters.ysfs = params.XYSFS;
                        this.filters.yszk = params.XYSZK;
                        this.filters.hxzk = params.XHXZK;
                        this.filters.ldsx = params.LDSX;
                        this.filters.lfds = params.LFDS;
                        this.filters.lfhs = params.LFHS;
                        this.filters.pfhs = params.PFHS;
                        this.filters.zgcs = params.ZGCS;
                        let nbxf = params.NBXF;
                        if(nbxf == 0){
                            this.filters.nbxf = '有';
                        }else if(nbxf == 1){
                            this.filters.nbxf = '无';
                        }else{
                            this.filters.nbxf = '';
                        }
                        this.filters.ckys = params.CKYSS;
                        this.filters.cpys = params.CPYSS;
                        this.filters.mmys = params.MMYSS;
                        this.filters.gcys = params.GCYSS;
                        this.filters.wgys = params.WYYSS;
                        this.filters.qtys = params.QTYS;
                        this.sqid = "30";
                    }
                }
                let param = {
                    id:params.ID,
                    sqlx:params.TYPEID
                };
                getScwj(param).then((res)=>{
                    this.zllx_data = res.data.sclx;
                    this.qyList = res.data.qyList;
                    this.azList = res.data.azList;
                    this.data = res.data.zpry;
                    this.zlrow = res.data.zlrow;
                    let fileCount = res.data.files;
                    let vm = this;
                    for(let i=0;i<fileCount.length;i++){
                        let files = fileCount[i].value;
                        let type = fileCount[i].type;
                        let fileList = [];
                        if(files) { //后台返回的文件链接
                            let a = (files).split(',');
                            if(a.length > 0) {
                                a.forEach(item => {
                                    var obj = {};
                                    let index = item.lastIndexOf('\/');
                                    let fileName  = item.substring(index + 1, item.length); //最后的文件名截取出来
                                    vm.$set(obj,'name',fileName);
                                    vm.$set(obj,'url',item);  //修改files里面的结构（name,url）
                                    fileList.push(obj);
                                    this.fileIds = this.fileIds.concat(fileCount[i].id);
                                })
                            }
                        }
                    }
                });
            },
            //打开打印界面
            openDy(){
                this.printFormVisible = true;
                this.$nextTick(()=>{
                    let list = this.fileIds;
                    let params = {azlxbh:this.filters.azlx};
                    let i = 0;
                    list.forEach((item,index)=>{
                        params['value'+(index+1)] = list[i];
                        i++;
                    });
                    let sqlx = this.sqid;
                    if(sqlx == '10'){
                        params['sqlx'] = "1";
                    }else if(sqlx == '20'){
                        params['sqlx'] = "2";
                    }else if(sqlx == '30'){
                        params['sqlx'] = "3";
                    }
                    params['count'] = i;
                    queryZlcount(params).then((res)=>{
                        this.zlsc_data = res.data.list;
                        this.ywlx = res.data.azlx;
                    });
                    document.getElementById("dy_sqbh").innerText = this.filters.sqbh;
                    document.getElementById("dy_sqrq").innerText = this.publicUtil.getNowDay();
                    document.getElementById("dy_lxr").innerText = this.filters.lxr;
                    document.getElementById("dy_lxdh").innerText = this.filters.lxdh;
                    this.dy_bjdw = this.filters.bjdw;
                    this.dy_xmmc = this.filters.gcmc;
                    this.dy_xmdz = this.filters.gcdz;
                    document.getElementById("dy_slr").innerText = this.user;
                    document.getElementById("dy_rq").innerText = this.publicUtil.getNowDay();
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
            //提交
            handleAdd(sfzp){
                let param = {
                    rwid:this.rwid,
                    type:this.type
                };
                this.$routerTab.close();
                this.tabUrl = "/Backlog";
                this.$routerTab.refresh('/Backlog');
                this.$routerTab.refresh('/MyWork');
                toXqqs(param).then((res) => {
                	if(res == true){
                        let params = {
                            rwid:this.rwid
                        };
                        getXqslById(params).then(res => {
                            let rows = res.data.list[0];
                            //项目经理签收成功发送短信通知用户
                            let xx = {
                                lxr:rows.LXR,
                                lxrsjh:rows.LXRSJH,
                                rwid:rows.ID,
                                type:rows.TYPEID,
                            };
                            Qsmessages(xx).then((res) =>{
                                this.$routerTab.close();
                                this.$routerTab.refresh('/Backlog');
                                this.$routerTab.refresh('/MyWork');
                            });
                        });
                        this.$message({
                            message: '签收成功',
                            type: 'success'
                        });
                	}else{
                		this.$message.error('签收失败！');
                	}
                });
            },
            handlezp: function(id){
                let params= {
                    rwid: id,
                    name: this.resource,
                    type: this.filters.sqlx
                };
                toZpry(params).then((res) => {
                    if(res.data == true){
                        this.$message({
                            message: '提交成功',
                            type: 'success'
                        });
                    }else{
                        this.$message.error('提交失败！');
                    }
                    this.resource = "";
                    this.$router.push({path: '/Backlog'})
                });
            },
            submitUpload(event) {
                if(this.zlType==""||this.zlType==null){
                    this.$message.error('请选择资料类型！');
                    this.fileList=[];
                    return;
                }
                let fileList = this.fileList;
                let operation = this.zlType;
                if (fileList !=null){
                    for (let i=0;i<fileList.length;i++){
                        let file = fileList[i];
                        let formData = new FormData();
                        formData.append("operation",operation);
                        formData.append("multipartFiles",file.raw);
                        formData.append("filename",file.name);
                        formData.append("sqlx",this.sqid);
                        formData.append("sldid",this.updateId);
                        importFile(formData).then((res) => {
                            let msg = res.msg;
                            if(msg == '上传成功'){
                                this.$message({
                                    message: '上传成功',
                                    type: 'success'
                                });
                                let fileId = res.fileId;
                                this.fileIds = this.fileIds.concat(fileId);
                                this.fileList=[];
                                this.queryinit(1);
                            }else{
                                this.$message.error('上传失败！');
                            }
                        });
                    }
                }
            },
            handleRemove(file, fileList) {
            },
            handlePreview(file) {
            },
            handleChange(file,fileList){
                this.fileList = fileList;
            },
            //新建小区申请楼栋列表新增界面
            handleAdds(){
                this.addFormVisible = true;
                this.addForm = {
                    ldh:'',dys:'',lc:'',mchs:'',zhs:'',bz:''
                };
            },
            yzbhandleAdds(){
                this.yzbaddFormVisible = true;
                this.yzbaddForm = {
                    yhh:'',yhm:'',kj:'',ysxz:'',hyfl:'',hs:'',bz:''
                };
            },
            //新建小区申请楼栋表列表编辑界面
            handleEdit: function (index, row) {
                this.editFormVisible = true;
                this.editForm = Object.assign({}, row);//编辑表单的赋值
            },
            //原总表信息表编辑界面
            yzbhandleEdit: function (index, row) {
                this.yzbeditFormVisible = true;
                this.yzbeditForm = Object.assign({}, row);//编辑表单的赋值
            },
            //新建小区申请楼栋表列表新增
            addSubmit(){
                this.$refs.addForm.validate((valid) => {
                    if (valid) {
                        this.addLoading = true;
                        let para = Object.assign({}, this.addForm);
                        addLdxx(para).then((res) => {
                            this.addLoading = false;
                            this.$message({
                                message: '提交成功',
                                type: 'success'
                            });
                            this.$refs['addForm'].resetFields();
                            this.addFormVisible = false;
                            this.getLdxx(this.updateId);
                            this.ldxxIds = this.ldxxIds.concat(res.id);
                        });
                    }
                });
            },
            //原总表信息表列表新增
            yzbaddSubmit(){
                this.$refs.yzbaddForm.validate((valid) => {
                    if (valid) {
                        this.yzbaddLoading = true;
                        let para = Object.assign({}, this.yzbaddForm);
                        addYzb(para).then((res) => {
                            this.yzbaddLoading = false;
                            this.$message({
                                message: '提交成功',
                                type: 'success'
                            });
                            this.$refs['yzbaddForm'].resetFields();
                            this.yzbaddFormVisible = false;
                            this.getYzb(this.updateId);
                            this.yzbIds = this.yzbIds.concat(res.id);
                        });
                    }
                });
            },
            //新建小区申请楼栋表列表编辑
            editSubmit: function () {
                this.$refs.editForm.validate((valid) => {
                    if (valid) {
                        this.editLoading = true;
                        let para = Object.assign({}, this.editForm);
                        editLdxx(para).then((res) => {
                            this.editLoading = false;
                            this.$message({
                                message: '提交成功',
                                type: 'success'
                            });
                            this.$refs['editForm'].resetFields();
                            this.editFormVisible = false;
                            this.getLdxx(this.updateId);
                        });
                    }
                });
            },
            //原总表信息表列表编辑
            yzbeditSubmit: function () {
                this.$refs.yzbeditForm.validate((valid) => {
                    if (valid) {
                        this.yzbeditLoading = true;
                        let para = Object.assign({}, this.yzbeditForm);
                        editYzb(para).then((res) => {
                            this.yzbeditLoading = false;
                            this.$message({
                                message: '提交成功',
                                type: 'success'
                            });
                            this.$refs['yzbeditForm'].resetFields();
                            this.yzbeditFormVisible = false;
                            this.getYzb(this.updateId);
                        });
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
            },
            //原总表列表展示
            getYzb(id) {
                let para = {
                    id:id
                };
                this.yzblistLoading = true;
                getYzbList(para).then((res) => {
                    this.yzbxx = res.data.yzbxx;
                    this.yzblistLoading = false;
                });
            },
            //新建小区申请楼栋表列表删除
            handleDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    let para = { id: row.id };
                    remove(para).then((res) => {
                        this.listLoading = false;
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getLdxx(this.updateId);
                    });
                }).catch(() => {

                });
            },
            yzbhandleDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.yzblistLoading = true;
                    let para = { id: row.id };
                    yzbremove(para).then((res) => {
                        this.yzblistLoading = false;
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getYzb(this.updateId);
                    });
                }).catch(() => {

                });
            },
        }
    }
</script>

<style scoped>
    .border-table {
        border-collapse: collapse;
        border: none;
        width: 100%;
    }
    .border-table td {
        border: solid #000 1px;
    }
</style>
