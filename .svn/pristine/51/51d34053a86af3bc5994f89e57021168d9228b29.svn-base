<template>
    <section>
        <el-row style="margin-top: 15px;">
            <el-col :span="24" >
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">选择移交水表</div>
                    </div>
                    <el-tabs type="card"  v-model="tabBdz">
                        <el-tab-pane label="五级计量表" name="五级计量表">
                            <el-table ref="wbmultipleTable" :data="wjjlb" height="300" border highlight-current-row
                                      v-loading="sblistLoading" style="width: 100%;">
                                <el-table-column property="HH" label="报装户号" align="center">
                                </el-table-column>
                                <el-table-column property="ZBKJ" label="口径" align="center">
                                </el-table-column>
                                <el-table-column property="ZBDZ" label="装表地址" align="center">
                                </el-table-column>
                                <el-table-column label="操作" align="center">
                                    <template slot-scope="scope">
                                        <label title="编辑">
                                            <i class="iconfont icon-ai-edit"  @click="editwjjlb_vue(scope.$index, scope.row)"
                                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label>
                                    </template>
                                </el-table-column>
                            </el-table>
                            <!--<el-col :span="24" class="toolbar" >-->
                                <!--<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[5 ,10 ,20, 50]"-->
                                               <!--@current-change="wbhandleCurrentChange" @size-change="wbhandleSizeChange"-->
                                               <!--:page-size="this.wbsize" style="float:right;" :total="this.wbtotal">-->
                                <!--</el-pagination>-->
                            <!--</el-col>-->
                        </el-tab-pane>
                        <el-tab-pane label="工商表" name="工商表">
                            <el-table ref="dbmultipleTable" :data="gsb" height="300" border highlight-current-row
                                      v-loading="sblistLoading" style="width: 100%;">
                                <el-table-column property="HH" label="报装户号" align="center">
                                </el-table-column>
                                <el-table-column property="HM" label="户名" align="center">
                                </el-table-column>
                                <el-table-column property="ZBDZ" label="装表地址" align="center">
                                </el-table-column>
                                <el-table-column property="ZBKJ" label="口径" align="center">
                                </el-table-column>
                                <el-table-column property="yjzt" label="移交状态" align="center" :formatter="formatYjzt">
                                </el-table-column>
                                <el-table-column property="TSRQ" label="通水日期" align="center">
                                </el-table-column>
                                <el-table-column property="SGRY" label="施工人员" align="center">
                                </el-table-column>
                                <el-table-column label="操作" align="center">
                                    <template slot-scope="scope">
                                        <label title="编辑">
                                            <i class="iconfont icon-ai-edit"  @click="editgsb_vue(scope.$index, scope.row)"
                                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label>
                                    </template>
                                </el-table-column>
                            </el-table>
                            <!--<el-col :span="24" class="toolbar" >-->
                                <!--<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[5 ,10 ,20, 50]"-->
                                               <!--@current-change="gsbhandleCurrentChange" @size-change="gsbhandleSizeChange"-->
                                               <!--:page-size="this.gsbsize" style="float:right;" :total="this.gsbtotal">-->
                                <!--</el-pagination>-->
                            <!--</el-col>-->
                        </el-tab-pane>
                        <el-tab-pane label="居民表" name="居民表">
                            <el-table ref="hbmultipleTable" :data="jmb" height="300" border highlight-current-row
                                      v-loading="sblistLoading" style="width: 100%;">
                                <el-table-column property="LDBH" label="楼栋号" align="center">
                                </el-table-column>
                                <el-table-column property="ZHS" label="总户数" align="center">
                                </el-table-column>
                                <el-table-column property="YYJS" label="已移交数量" align="center">
                                </el-table-column>
                                <el-table-column property="WYJSL" label="未移交数量" align="center">
                                </el-table-column>
                                <el-table-column property="BCYJS" label="本次移交数量" align="center">
                                    <template slot-scope="scope">{{ scope.row.BCYJS }}</template>
                                </el-table-column>
                                <el-table-column label="操作" align="center">
                                    <template slot-scope="scope">
                                        <div v-if="scope.row.BCYJS == 0">

                                        </div>
                                        <div v-else>
                                            <label title="编辑">
                                                <i class="iconfont icon-ai-edit"  @click="editjmb_vue(scope.$index, scope.row)"
                                                   style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                            </label>
                                        </div>
                                    </template>
                                </el-table-column>
                            </el-table>
                            <!--<el-col :span="24" class="toolbar" >-->
                                <!--<el-pagination background layout="total, prev, pager, next, sizes , jumper" :page-sizes="[5 ,10 ,20, 50]"-->
                                               <!--@current-change="jmbhandleCurrentChange" @size-change="jmbhandleSizeChange"-->
                                               <!--:page-size="this.jmbsize" style="float:right;" :total="this.jmbtotal">-->
                                <!--</el-pagination>-->
                            <!--</el-col>-->
                        </el-tab-pane>
                    </el-tabs>
                </el-card>

                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">提交至抄表站点</div>
                    </div>
                    <div class="text item">
                        <el-row>
                            <el-form label-width="120px" :inline="true">
                                <el-radio-group v-model="resource" style="width: 100%;">
                                    <el-col :span="6" v-model="data" v-for="(item,index) in data" class="col" :key="index">
                                        <el-form-item style="width: 45%">
                                            <el-radio v-model="radio" :label="item.USERID">{{item.USERNAME}}（在建项目{{item.NUM}}个）</el-radio>
                                        </el-form-item>
                                    </el-col>
                                </el-radio-group>
                            </el-form>
                        </el-row>
                    </div>
                </el-card>
                <el-row style="text-align: center;margin-top: 15px">
                    <router-link :to="'/Backlog'">
                        <el-button size="mini" type="info" v-on:click="return_vue">
                            <i class="iconfont icon-quxiao"></i>取消</el-button>
                    </router-link>
                    <router-link :to="zpurl">
                        <el-button size="mini" type="info" @click="th_vue()">
                            <i class="iconfont icon-fanhui"></i>退回</el-button>
                    </router-link>
                    <router-link :to="'/Backlog'">
                        <el-button size="mini" type="primary" @click="sbSubmit()"><!--:disabled="this.jmbsels.length===0 && this.gsbsels.length===0 "-->
                            <i class="iconfont icon-shenhetongguorenyuan"></i>提交</el-button>
                    </router-link>
                    <el-button size="mini" type="primary" @click="goxmqj()" >
                        <i class="iconfont icon-xiangmuquanjing_"></i>项目全景
                    </el-button>
                </el-row>
            </el-col>

            <el-dialog title="编辑" :visible.sync ="wbFormVisible" width="1000px">
                <el-form :model="wbform"  ref="wbform" class="demo-ruleForm">
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>项目编号&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="wbform.xmbh" :readonly="true"></el-input>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>户号&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="wbform.hh" :readonly="true"></el-input>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>户名&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="wbform.hm" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>用户地址&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="21">
                            <el-input size="small" v-model="wbform.ybdz" style="width: 520px" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>装表地址&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="21">
                            <el-input size="small" v-model="wbform.zbdz" style="width: 520px" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>新装底数&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="wbform.xzds" :readonly="true"></el-input>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>拆回旧表底数&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="wbform.chjbds" :readonly="true"></el-input>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>表身码&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="wbform.bsm" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>水表口径&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="wbform.sbkj" :readonly="true"></el-input>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>水表厂家&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="wbform.sbcj" :readonly="true"></el-input>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>水表类型&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="wbform.sblx" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>用水性质&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item prop="ysxz" :rules="this.publicUtil.notNull">
                                <el-select size="small" v-model="wbform.ysxz" placeholder="请选择"><!--@change="getHyflHbData_vue"-->
                                    <el-option v-for="item in ysxzList"
                                               :key="item.codevalue"
                                               :label="item.codename"
                                               :value="item.codevalue"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>行业分类&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item prop="hyfl" :rules="this.publicUtil.notNull">
                                <el-select size="small" v-model="wbform.hyfl" placeholder="请选择">
                                    <el-option v-for="item in hyflList"
                                               :key="item.codevalue"
                                               :label="item.codename"
                                               :value="item.codevalue"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>表册号&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="wbform.bch" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>短信服务&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-radio-group v-model="wbform.dxfw">
                                <el-radio @click.native="toggleTabwb(1)" label="Y">有</el-radio>
                                <el-radio @click.native="toggleTabwb(2)" label="N">无</el-radio>
                            </el-radio-group>
                        </el-col>
                        <div v-if="wbdxxs">
                            <el-col :span="3" style="text-align: right">
                                <span>机主名称&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="wbform.jzmc"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>手机号码&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="wbform.sjhm"></el-input>
                            </el-col>
                        </div>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>代收垃圾费&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-radio-group v-model="wbform.dsljf">
                                <el-radio label="1">是</el-radio>
                                <el-radio label="0">否</el-radio>
                            </el-radio-group>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>交费方式&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="13">
                            <el-input size="small" v-model="wbform.jffs" style="width: 520px" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>片区&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item prop="pq" :rules="this.publicUtil.notNull">
                                <el-select size="small" v-model="wbform.pq" placeholder="请选择">
                                    <el-option v-for="item in pqList"
                                               :key="item.VALUE"
                                               :label="item.LABEL"
                                               :value="item.VALUE"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>GPS数据&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="13">
                            <el-input size="small" v-model="wbform.gps" style="width: 520px" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>安装日期&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-date-picker size="small" type="date" placeholder="选择日期" v-model="wbform.azrq" :readonly="true" style="width: 100%"></el-date-picker>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>通水日期&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-date-picker size="small" type="date" placeholder="选择日期" v-model="wbform.tsrq" :readonly="true" style="width: 100%"></el-date-picker>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>现场移交&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item prop="xcyjrq" :rules="this.publicUtil.notNull">
                                <el-date-picker size="small" type="date" placeholder="选择日期"
                                                format="yyyy-MM-dd"
                                                value-format="yyyy-MM-dd"
                                                v-model="wbform.xcyjrq" style="width: 100%"></el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>施工单位&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item prop="sgdw" :rules="this.publicUtil.notNull">
                                <el-input size="small" v-model="wbform.sgdw"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>供水所&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item prop="gss" :rules="this.publicUtil.notNull">
                                <el-input size="small" v-model="wbform.gss"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>营业中心&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item prop="yyzx" :rules="this.publicUtil.notNull">
                                <el-input size="small" v-model="wbform.yyzx"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>备注&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="21">
                            <el-form-item prop="bz" :rules="this.publicUtil.notNull">
                                <el-input size="small" v-model="wbform.bz" style="width: 520px"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-form-item>
                    <el-form-item style="text-align: center">
                        <el-button size="mini" type="primary" @click="wbedit_vue()">
                            <i class="iconfont icon-save"></i>保存</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>

            <el-dialog title="编辑" :visible.sync ="gsbFormVisible" width="1000px">
                <el-form :model="gsbform"  ref="gsbform" class="demo-ruleForm">
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>项目编号&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="gsbform.xmbh" :readonly="true"></el-input>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>户号&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="gsbform.hh" :readonly="true"></el-input>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>户名&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="gsbform.hm" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>用户地址&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="21">
                            <el-input size="small" v-model="gsbform.ybdz" style="width: 520px" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>装表地址&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="21">
                            <el-input size="small" v-model="gsbform.zbdz" style="width: 520px" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>新装底数&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="gsbform.xzds" :readonly="true"></el-input>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>拆回旧表底数&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="gsbform.chjbds" :readonly="true"></el-input>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>表身码&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="gsbform.bsm" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>水表口径&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="gsbform.sbkj" :readonly="true"></el-input>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>水表厂家&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="gsbform.sbcj" :readonly="true"></el-input>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>水表类型&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="gsbform.sblx" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>用水性质&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item prop="ysxz" :rules="this.publicUtil.notNull">
                                <el-select size="small" v-model="gsbform.ysxz" placeholder="请选择"><!--@change="getHyflHbData_vue"-->
                                    <el-option v-for="item in ysxzList"
                                               :key="item.codevalue"
                                               :label="item.codename"
                                               :value="item.codevalue"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>行业分类&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item prop="hyfl" :rules="this.publicUtil.notNull">
                                <el-select size="small" v-model="gsbform.hyfl" placeholder="请选择">
                                    <el-option v-for="item in hyflList"
                                               :key="item.codevalue"
                                               :label="item.codename"
                                               :value="item.codevalue"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>表册号&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input size="small" v-model="gsbform.bch" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>短信服务&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-radio-group v-model="gsbform.dxfw">
                                <el-radio @click.native="toggleTab(1)" label="Y">有</el-radio>
                                <el-radio @click.native="toggleTab(2)" label="N">无</el-radio>
                            </el-radio-group>
                        </el-col>
                        <div v-if="gsdxxs">
                            <el-col :span="3" style="text-align: right">
                                <span>机主名称&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="gsbform.jzmc"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>手机号码&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="gsbform.sjhm"></el-input>
                            </el-col>
                        </div>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>代收垃圾费&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-radio-group v-model="gsbform.dsljf">
                                <el-radio label="1">是</el-radio>
                                <el-radio label="0">否</el-radio>
                            </el-radio-group>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>交费方式&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="13">
                            <el-input size="small" v-model="gsbform.jffs" style="width: 520px" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>片区&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item prop="pq" :rules="this.publicUtil.notNull">
                                <el-select size="small" v-model="gsbform.pq" placeholder="请选择">
                                    <el-option v-for="item in pqList"
                                               :key="item.VALUE"
                                               :label="item.LABEL"
                                               :value="item.VALUE"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>GPS数据&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="13">
                            <el-input size="small" v-model="gsbform.gps" style="width: 520px" :readonly="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>安装日期&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-date-picker size="small" type="date" placeholder="选择日期" v-model="gsbform.azrq" :readonly="true" style="width: 100%"></el-date-picker>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>通水日期&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-date-picker size="small" type="date" placeholder="选择日期" v-model="gsbform.tsrq" :readonly="true" style="width: 100%"></el-date-picker>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>现场移交&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item prop="xcyjrq" :rules="this.publicUtil.notNull">
                                <el-date-picker size="small" type="date" placeholder="选择日期"
                                                format="yyyy-MM-dd"
                                                value-format="yyyy-MM-dd"
                                                v-model="gsbform.xcyjrq" style="width: 100%"></el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>施工单位&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item prop="sgdw" :rules="this.publicUtil.notNull">
                                <el-input size="small" v-model="gsbform.sgdw"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>供水所&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item prop="gss" :rules="this.publicUtil.notNull">
                                <el-input size="small" v-model="gsbform.gss"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="3" style="text-align: right">
                            <span>营业中心&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-form-item prop="yyzx" :rules="this.publicUtil.notNull">
                                <el-input size="small" v-model="gsbform.yyzx"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="3" style="text-align: right">
                            <span>备注&nbsp;&nbsp;</span>
                        </el-col>
                        <el-col :span="21">
                            <el-form-item prop="bz" :rules="this.publicUtil.notNull">
                                <el-input size="small" v-model="gsbform.bz" style="width: 520px"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-form-item>
                    <el-form-item style="text-align: center;">
                        <el-button size="mini" type="primary" @click="gsbedit_vue()">
                            <i class="iconfont icon-save"></i>保存</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </el-row>
    </section>
</template>

<script>
    import { shSbList,sheditsb,shsbyjSubmit,getHyflCodeData,updateCheckTask } from '../../api/sbyj';
    import { getYsxzData,getHyflData } from '../../api/sjst';

    export default {
        // props: ['yjdbh','gclx','jsdw'],
        data(){
            return{
                zpurl:'',
                para:'',
                xmid:this.$route.query.xmid,//项目id
                refId:this.$route.query.refId,
                activityId:this.$route.query.activityId,
                taskId:this.$route.query.taskId,
                zyjdbh:this.$route.query.rwbh,

                gsdxxs:false,
                wbdxxs:false,

                wbform:{
                    sbid:'',
                    xmbh:'',hh:'',hm:'',ybdz:'',zbdz:'',azrq:'',tsrq:'',xcyjrq:'',xzds:'',chjbds:'',bsm:'',
                    sbkj:'',sbcj:'',sblx:'',ysxz:'',hyfl:'',dxfw:'N',dsljf:'0',bch:'',jffs:'',gps:'',pq:'',
                    jzmc:'',sjhm:'',sgdw:'',gss:'',yyzx:'',bz:''
                },
                gsbform:{
                    sbid:'',
                    xmbh:'',hh:'',hm:'',ybdz:'',zbdz:'',azrq:'',tsrq:'',xcyjrq:'',xzds:'',chjbds:'',bsm:'',
                    sbkj:'',sbcj:'',sblx:'',ysxz:'',hyfl:'',dxfw:'N',dsljf:'0',bch:'',jffs:'',gps:'',pq:'',
                    jzmc:'',sjhm:'',sgdw:'',gss:'',yyzx:'',bz:''
                },
                ysxzList:[],//用水性质下拉框
                hyflList:[],//行业分类下拉框
                pqList:[],//营业中心片区下拉框

                /*指派人员*/
                resource:'',
                radio : '',//选中的指派人员
                data :[],
                /*指派人员*/

                tabBdz:'五级计量表',//选项卡默认选择
                wjjlb:[],//五级计量表集合
                gsb:[],//地表集合
                jmb:[],//户表集合
                sblistLoading:false,//下单水表列表加载

                /*分页*/
                wbsize:5,//五级计量表每页容量
                wbtotal:0,//五级计量表表格数据总数量
                wbpage:1,//五级计量表当前页码
                jmbsize:5,//居民表每页容量
                jmbtotal:0,//居民表表格数据总数量
                jmbpage:1,//居民表当前页码
                gsbsize:5,//工商表每页容量
                gsbtotal:0,//工商表表格数据总数量
                gsbpage:1,//工商表当前页码
                /*分页*/

                wbFormVisible:false,//打开五级计量表编辑界面
                gsbFormVisible:false,//打开五级计量表编辑界面
            }
        },
        mounted(){
            this.changeQszt();
            this.getYsxzData_vue();
            this.getHyflData_vue();
        },
        methods:{
            //跳转至项目全景
            goxmqj(){
                this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid  }})
            },
            //流程改变签收状态
            changeQszt(){
                this.zpurl="/shSbyjd?xmid="+this.xmid+"&rwbh="+this.zyjdbh+"&refId="+this.refId
                    +"&activityId="+this.activityId+"&taskId="+this.taskId;
                if(this.taskId != '' && this.taskId != undefined){
                    let para = {taskId: this.taskId};
                    updateCheckTask(para).then(res=>{

                    });
                }
            },
            //初始化方法
            yjchildinit(yjdbh){
                // this.zyjdbh = yjdbh;
                let params ={xmid:this.xmid,wbsize:this.wbsize,wbpage:this.wbpage-1,jmbsize:this.jmbsize,
                    jmbpage:this.jmbpage-1,gsbsize:this.gsbsize,gsbpage:this.gsbpage-1,yjzbid:this.refId,
                    yjdbh:this.zyjdbh};
                shSbList(params).then((res)=>{
                    this.data = res.data.zpcby;//指派抄表员
                    this.wjjlb = res.data.wbList;
                    this.wbtotal = res.data.wbtotal;
                    this.gsb = res.data.gsbList;
                    this.gsbtotal = res.data.gsbtotal;
                    this.jmb = res.data.jmbList;
                    this.jmbtotal = res.data.jmbtotal;
                    this.pqList = res.data.pqList;
                    this.wbform.xmbh = res.data.varList[0].XMBH;
                    this.gsbform.xmbh = res.data.varList[0].XMBH;
                });
            },
            toggleTab(val){
                if(val == '1'){
                    this.gsdxxs = true;
                }else{
                    this.gsdxxs = false;
                }
            },
            toggleTabwb(val){
                if(val == '1'){
                    this.wbdxxs = true;
                }else{
                    this.wbdxxs = false;
                }
            },
            getYsxzData_vue(){
                getYsxzData().then((res)=>{
                    if(res.data!=null){
                        this.ysxzList = res.data.row;
                    }
                })
            },
            //获取行业分类
            getHyflData_vue(){
                getHyflData().then((res)=>{
                    if(res.data!=null){
                        this.hyflList = res.data.row;
                    }
                })
            },
            //获取行业分类
            // getHyflData_vue(val,hyfl){
            //     let para = {ysxz:this.wbform.ysxz};
            //     getHyflCodeData(para).then(res=>{
            //         this.hyflList = res.data.list;
            //         if(hyfl == '' ||hyfl==null||hyfl==undefined){
            //             this.wbform.hyfl = res.data.list[0].VALUE;
            //         }else{
            //             this.wbform.hyfl = hyfl;
            //         }
            //     });
            // },
            // getHyflHbData_vue(val,hyfl){
            //     let para = {ysxz:this.gsbform.ysxz};
            //     getHyflCodeData(para).then(res=>{
            //         this.hyflList = res.data.list;
            //         if(hyfl == '' ||hyfl==null||hyfl==undefined){
            //             this.gsbform.hyfl = res.data.list[0].VALUE;
            //         }else{
            //             this.gsbform.hyfl = hyfl;
            //         }
            //     });
            // },
            //五级计量表保存
            wbedit_vue(){
                this.$refs.wbform.validate((valid) => {
                    if (valid) {
                        let params = this.wbform;
                        this.sblistLoading = true;
                        sheditsb(params).then(res=>{
                            if(res == true){
                                this.$message({
                                    message: '保存成功',
                                    type: 'success'
                                });
                                this.wbFormVisible = false;
                                this.yjchildinit(this.zyjdbh);
                                this.sblistLoading = false;
                            }else{
                                this.$message.error('保存失败！');
                            }
                        });
                    }
                });
            },
            //工商表保存
            gsbedit_vue(){
                this.$refs.gsbform.validate((valid) => {
                    if (valid) {
                        let params = this.gsbform;
                        this.sblistLoading = true;
                        sheditsb(params).then(res=>{
                            if(res == true){
                                this.$message({
                                    message: '保存成功',
                                    type: 'success'
                                });
                                this.gsbFormVisible = false;
                                this.yjchildinit(this.zyjdbh);
                                this.sblistLoading = false;
                            }else{
                                this.$message.error('保存失败！');
                            }
                        });
                    }
                });
            },
            //打开五级计量表编辑界面
            editwjjlb_vue(index, row){
                this.wbFormVisible = true;
                let lhh = row.YYZX_HH == undefined ? '': row.YYZX_HH;
                this.wbform.hh = "新装"+row.HH +" 立户"+lhh;
                this.wbform.hm = row.HM;
                if(row.MPH == ''){
                    this.wbform.ybdz = "";
                }else{
                    this.wbform.ybdz = row.YHDZ.replace("[楼号]",row.LDBH).replace("[门牌]",row.MPH);
                }
                this.wbform.zbdz = row.ZBDZ;
                this.wbform.azrq = row.AZRQ;
                this.wbform.tsrq = row.TSRQ;
                this.wbform.xcyjrq = row.XCYJRQ;
                this.wbform.xzds = row.XZDS;
                this.wbform.chjbds = row.CHJBDS;
                this.wbform.bsm = row.BSM;
                let p = row.BZKJ == undefined ? '': row.BZKJ;
                let q = row.ZBKJ == undefined ? '': row.ZBKJ;
                this.wbform.sbkj = "报装：Φ"+p +"mm 装表： Φ" + q + "mm";
                this.wbform.sbcj = row.SBCJ;
                this.wbform.sblx = row.SBLX;
                this.wbform.ysxz = row.YSXZBH;
                this.wbform.hyfl = row.HYFLBH;
                this.wbform.dxfw = row.DXFWBH == undefined ? 'N' :row.DXFWBH;
                if(row.DXFWBH == 'Y'){
                    this.wbdxxs = true;
                }
                this.wbform.jzmc = row.DXMC;
                this.wbform.sjhm = row.DXHM;
                this.wbform.dsljf = row.ZSLJFBH;
                this.wbform.bch = row.BCH;
                this.wbform.jffs = row.JFFS;
                this.wbform.gps = row.GPS;
                this.wbform.pq = row.PQBH;
                this.wbform.sgdw = row.YYZX_SGRY;
                this.wbform.gss = row.YYZX_GSSRY;
                this.wbform.yyzx = row.YYZX_YYZXRY;
                this.wbform.bz = row.BZ;
                this.wbform.sbid = row.ID;
            },
            //打开工商表编辑界面
            editgsb_vue(index, row){
                this.gsbFormVisible = true;
                let lhh = row.YYZX_HH == undefined ? '': row.YYZX_HH;
                this.gsbform.hh = "新装"+row.HH +" 立户"+lhh;
                this.gsbform.hm = row.HM;
                if(row.MPH == ''){
                    this.gsbform.ybdz = "";
                }else{
                    this.gsbform.ybdz = row.YHDZ.replace("[楼号]",row.LDBH).replace("[门牌]",row.MPH);
                }
                this.gsbform.zbdz = row.ZBDZ;
                this.gsbform.azrq = row.AZRQ;
                this.gsbform.tsrq = row.TSRQ;
                this.gsbform.xcyjrq = row.XCYJRQ;
                this.gsbform.xzds = row.XZDS;
                this.gsbform.chjbds = row.CHJBDS;
                this.gsbform.bsm = row.BSM;
                let p = row.BZKJ == undefined ? '': row.BZKJ;
                let q = row.ZBKJ == undefined ? '': row.ZBKJ;
                this.gsbform.sbkj = "报装：Φ"+p +"mm 装表：Φ" + q + "mm";
                this.gsbform.sbcj = row.SBCJ;
                this.gsbform.sblx = row.SBLX;
                this.gsbform.ysxz = row.YSXZBH;
                this.gsbform.hyfl = row.HYFLBH;
                // this.getHyflHbData_vue(row.YSXZBH,row.HYFLBH);
                // this.gsbform.hyfl = row.HYFLBH;
                this.gsbform.dxfw = row.DXFWBH == undefined ? 'N' :row.DXFWBH;
                if(row.DXFWBH == 'Y'){
                    this.gsdxxs = true;
                }
                this.gsbform.jzmc = row.DXMC;
                this.gsbform.sjhm = row.DXHM;
                this.gsbform.dsljf = row.ZSLJFBH;
                this.gsbform.bch = row.BCH;
                this.gsbform.jffs = row.JFFS;
                this.gsbform.gps = row.GPS;
                this.gsbform.pq = row.PQBH;
                this.gsbform.bz = row.BZ;
                this.gsbform.sgdw = row.YYZX_SGRY;
                this.gsbform.gss = row.YYZX_GSSRY;
                this.gsbform.yyzx = row.YYZX_YYZXRY;
                this.gsbform.sbid = row.ID;
            },
            //户表勾选界面
            editjmb_vue(index, row){
                this.$router.push({path: '/sbyjHbsh',
                    query: {xmid:this.xmid,ldid:row.LDID,rwbh:this.zyjdbh,refId:this.refId,
                        activityId:this.activityId,taskId:this.taskId}});
            },
            //退回
            th_vue(){
                this.$confirm('您正在递交工作结果，一旦递交确认后将不能打回或再次修改，你确定要递交吗？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let params = {xmid:this.xmid,yjdbh:this.zyjdbh,refId:this.refId,
                        name: this.resource,type:"2"};
                    this.$routerTab.close();
                    this.zpurl = "/Backlog";
                    this.$routerTab.refresh('/Backlog');
                    this.$routerTab.refresh('/MyWork');
                    shsbyjSubmit(params).then(res=>{
                        if(res.data == true){
                            this.$message({
                                message: '退回成功',
                                type: 'success'
                            });
                        }else{
                            this.$message.error('退回失败！');
                        }
                    });
                }).catch(() => {

                });
            },
            sbSubmit(){
                if(this.resource == ''){
                    this.$message({
                        message: '请选择指派人员！',
                        type: 'warning'
                    });
                }else{
                    let params = {xmid:this.xmid,yjdbh:this.zyjdbh,refId:this.refId,
                         name: this.resource,type:"1"};
                    this.$routerTab.close();
                    this.$routerTab.refresh('/Backlog');
                    this.$routerTab.refresh('/MyWork');
                    shsbyjSubmit(params).then(res=>{
                        if(res.data == true){
                            this.$message({
                                message: '指派成功',
                                type: 'success'
                            });
                            // this.zpurl="/BackLog";
                        }else{
                            // this.zpurl = "/shSbyjd?xmid="+this.xmid+"&rwbh="+this.zyjdbh+"&refId="+this.refId;
                            this.$message.error('提交失败！');
                        }
                    });
                }
            },
            return_vue(){
                this.$routerTab.close();
            },
            //移交状态
            formatYjzt:function (row, column) {
                return '移交中';
            },
            //通水时间格式转换
            formatLxsj: function (row, column) {
                let sj = row.tssj;
                return this.publicUtil.formatChange(sj);
            },
            jmbhandleCurrentChange(val) {
                this.jmbpage = val;
                this.yjchildinit(this.zyjdbh);
            },
            jmbhandleSizeChange(val) {
                this.jmbsize = val;
                this.jmbpage = 1;
                this.yjchildinit(this.zyjdbh);
            },
            gsbhandleCurrentChange(val) {
                this.gsbpage = val;
                this.yjchildinit(this.zyjdbh);
            },
            gsbhandleSizeChange(val) {
                this.gsbsize = val;
                this.gsbpage = 1;
                this.yjchildinit(this.zyjdbh);
            },
            wbhandleCurrentChange(val) {
                this.wbpage = val;
                this.yjchildinit(this.zyjdbh);
            },
            wbhandleSizeChange(val) {
                this.wbsize = val;
                this.wbpage = 1;
                this.yjchildinit(this.zyjdbh);
            },

        }
    }
</script>

<style>

</style>