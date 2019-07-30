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
                                      v-loading="sblistLoading"  @selection-change="wbselsChange" style="width: 100%;">
                                <el-table-column type="selection" width="50" align="center" :selectable='wbcheckboxInit'>
                                </el-table-column>
                                <el-table-column property="hh" label="报装户号" align="center">
                                </el-table-column>
                                <el-table-column property="vsgkjmc" label="口径" align="center">
                                </el-table-column>
                                <el-table-column property="azwz" label="装表地址" align="center">
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
                                      v-loading="sblistLoading" @selection-change="gsbselsChange" style="width: 100%;">
                                <el-table-column type="selection" width="50" align="center" :selectable='gscheckboxInit'>
                                </el-table-column>
                                <el-table-column property="hh" label="报装户号" align="center">
                                </el-table-column>
                                <el-table-column property="hm" label="户名" align="center">
                                </el-table-column>
                                <el-table-column property="azwz" label="装表地址" align="center">
                                </el-table-column>
                                <el-table-column property="vsgkjmc" label="口径" align="center">
                                </el-table-column>
                                <el-table-column property="yjzt" label="移交状态" align="center" :formatter="formatYjzt">
                                </el-table-column>
                                <el-table-column property="tssj" label="通水日期" align="center" :formatter="formatLxsj">
                                </el-table-column>
                                <el-table-column property="sgry" label="施工人员" align="center">
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
                                      v-loading="sblistLoading" @selection-change="jmbselsChange" style="width: 100%;">
                                <el-table-column type="selection" align="center" :selectable='jmcheckboxInit'>
                                </el-table-column>
                                <el-table-column property="LDBH" label="楼栋号" align="center">
                                </el-table-column>
                                <el-table-column property="ZHS" label="总户数" align="center">
                                </el-table-column>
                                <el-table-column property="YJSL" label="待移交数量" align="center">
                                </el-table-column>
                                <el-table-column property="WYJSL" label="未移交数量" align="center">
                                </el-table-column>
                                <el-table-column property="BCGYSL" label="本次勾选数量" align="center">
                                    <template slot-scope="scope">{{ scope.row.BCGYSL }}</template>
                                </el-table-column>
                                <el-table-column label="操作" align="center">
                                    <template slot-scope="scope">
                                        <label title="编辑">
                                            <i class="iconfont icon-ai-edit"  @click="editjmb_vue(scope.$index, scope.row)"
                                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label>
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
                        <!--<el-col :span="24" class="toolbar" >-->
                            <!--<el-button type="primary" size="small" @click="sbSubmit" :disabled="this.jmbsels.length===0 && this.gsbsels.length===0 ">提交</el-button>-->
                            <!---->
                        <!--</el-col>-->
                    </el-tabs>
                </el-card>

                <el-card class="box-card"  style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">提交至营销专人</div>
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
                <el-row style="text-align: center;margin-top: 25px">
                    <router-link :to="{path:'/sbyjdlb', query:{xmid:this.xmid,xmmc:this.xmmc,
                                    xmbh:this.xmbh,gclx:this.gclx}}">
                        <el-button size="mini" type="info" v-on:click="return_vue">
                            <i class="iconfont icon-quxiao"></i>取消</el-button>
                    </router-link>
                    <router-link :to="{path:'/sbyjdlb', query:{xmid:this.xmid,xmmc:this.xmmc,
                                    xmbh:this.xmbh,gclx:this.gclx}}">
                        <el-button size="mini" type="primary" @click="sbSubmit(1)">
                            <i class="iconfont icon-save"></i>保存</el-button>
                    </router-link>
                    <router-link :to="tabUrl">
                        <el-button size="mini" type="primary" @click="sbSubmit(2)"><!--:disabled="this.jmbsels.length===0 && this.gsbsels.length===0 "-->
                            <i class="iconfont icon-queren"></i>提交</el-button>
                    </router-link>
                    <el-button size="mini" type="primary" @click="goxmqj()" >
                        <i class="iconfont icon-xiangmuquanjing_"></i>项目全景
                    </el-button>
                </el-row>
            </el-col>

            <el-dialog title="编辑" :visible.sync ="wbFormVisible" width="1000px" :close-on-click-modal="false">
                <el-card class="box-card"  style="margin-top: 15px;overflow: auto">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">施工完成确认</div>
                    </div>
                    <el-form :model="wbform"  ref="wbform" class="demo-ruleForm">
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>装表地址：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-select size="small" v-model="wbform.qy" placeholder="请选择">
                                    <el-option v-for="item in qyList"
                                               :key="item.VALUE"
                                               :label="item.LABEL"
                                               :value="item.VALUE"></el-option>
                                </el-select>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>装表地址：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="zbdz" :rules="this.publicUtil.notNull">
                                    <el-input size="small" v-model="wbform.zbdz"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>营业中心片区：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="yyzxpq" :rules="this.publicUtil.notNull">
                                    <el-select size="small" v-model="wbform.yyzxpq" placeholder="请选择">
                                        <el-option v-for="item in pqList"
                                                   :key="item.VALUE"
                                                   :label="item.LABEL"
                                                   :value="item.VALUE"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>现场移交日期：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="xcyjrq" :rules="this.publicUtil.notNull">
                                    <el-date-picker size="small" format="yyyy-MM-dd" value-format="yyyy-MM-dd" type="date" placeholder="选择日期" v-model="wbform.xcyjrq" style="width: 100%"></el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>装表口径：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="zbkj" :rules="this.publicUtil.notNull">
                                    &Phi;
                                    <el-select size="small" v-model="wbform.zbkj" @change="getwbzbkj" placeholder="请选择" style="width:140px;">
                                        <el-option v-for="item in zbkjList"
                                                   :key="item.VALUE"
                                                   :label="item.LABEL"
                                                   :value="item.VALUE"></el-option>
                                    </el-select>
                                    mm
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>表身码：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="bsm" :rules="this.publicUtil.notNull">
                                    {{wbbsmqz}}
                                    <el-input size="small" v-model="wbform.bsm" style="width: 130px"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>安装日期：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="azrq" :rules="this.publicUtil.notNull">
                                    <el-date-picker size="small" type="date" placeholder="选择日期" v-model="wbform.azrq" style="width: 100%"></el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>通水日期：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="tsrq" :rules="this.publicUtil.notNull">
                                    <el-date-picker size="small" type="date" placeholder="选择日期" v-model="wbform.tsrq" style="width: 100%"></el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>新装水表底度：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="xzsbdd" :rules="this.publicUtil.NullOrFloat">
                                    <el-input size="small" v-model="wbform.xzsbdd"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>拆回旧表底度：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="chjbdd" :rules="this.publicUtil.NullOrFloat">
                                    <el-input size="small" v-model="wbform.chjbdd"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>水表厂家：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="sbcj" :rules="this.publicUtil.notNull">
                                    <el-select size="small" v-model="wbform.sbcj" @change="getwbsbcjjc" placeholder="请选择">
                                        <el-option v-for="item in sbcjList"
                                                   :key="item.VALUE"
                                                   :label="item.LABEL"
                                                   :value="item.VALUE"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>水表类型：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="sblx" :rules="this.publicUtil.notNull">
                                    <el-select size="small" v-model="wbform.sblx" placeholder="请选择">
                                        <el-option v-for="item in sblxList"
                                                   :key="item.VALUE"
                                                   :label="item.LABEL"
                                                   :value="item.VALUE"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>GPS数据：</span>
                            </el-col>
                            <el-col :span="13">
                                <el-form-item prop="gps" :rules="this.publicUtil.gps">
                                    <el-input size="small" v-model="wbform.gps" style="width:250px;"></el-input>
                                    <span>格式：35xxxxx.xxx,5xxxxx.xxx,xx.xxx</span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>施工人员：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="sgry" :rules="this.publicUtil.notNull">
                                    <el-input size="small" v-model="wbform.sgry"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>日期：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="sgrq" :rules="this.publicUtil.notNull">
                                    <el-date-picker size="small" type="date" placeholder="选择日期" v-model="wbform.sgrq" style="width: 100%"></el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>用户确认：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="yhqr" :rules="this.publicUtil.notNull">
                                    <el-input size="small" v-model="wbform.yhqr"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>日期：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="yhqrrq" :rules="this.publicUtil.notNull">
                                    <el-date-picker size="small" type="date" placeholder="选择日期" v-model="wbform.yhqrrq" style="width: 100%"></el-date-picker>
                                </el-form-item>
                            </el-col>
                        </el-form-item>
                    </el-form>
                </el-card>
                <el-card class="box-card"  style="margin-top: 15px;overflow: auto">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">用户申请信息</div>
                    </div>
                    <el-form>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>申请编号：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="publicform.sqbh" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>户号：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="publicform.hh" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>用户联系人：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="publicform.yhlxr" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>户名：</span>
                            </el-col>
                            <el-col :span="13">
                                <el-input size="small" v-model="publicform.hm" :readonly="true" style="width: 500px"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>联系电话：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="publicform.lxdh" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>用户地址：</span>
                            </el-col>
                            <el-col :span="13">
                                <el-input size="small" v-model="publicform.yhdz" :readonly="true" style="width: 500px"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>水表口径：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="publicform.sbkj" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>用水性质：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="publicform.ysxz" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>交费方式：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="publicform.jffs" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>开户名：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="publicform.khm" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>账号：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="publicform.zh" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>开户行：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="publicform.khh" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item style="text-align: center">
                            <el-button size="mini" type="primary" :loading="submitLoading" v-on:click="wbedit_vue" >
                                <i class="iconfont icon-save"></i>保存
                            </el-button>
                        </el-form-item>
                    </el-form>
                </el-card>
            </el-dialog>

            <el-dialog title="编辑" :visible.sync ="gsbFormVisible" width="1000px" :close-on-click-modal="false">
                <el-card class="box-card"  style="margin-top: 15px;overflow: auto">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">施工完成确认</div>
                    </div>
                    <el-form :model="gsbform"  ref="gsbform" class="demo-ruleForm">
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>装表地址：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-select size="small" v-model="gsbform.qy" placeholder="请选择">
                                    <el-option v-for="item in qyList"
                                               :key="item.VALUE"
                                               :label="item.LABEL"
                                               :value="item.VALUE"></el-option>
                                </el-select>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>装表地址：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="zbdz" :rules="this.publicUtil.notNull">
                                    <el-input size="small" v-model="gsbform.zbdz"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>营业中心片区：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="yyzxpq" :rules="this.publicUtil.notNull">
                                <el-select size="small" v-model="gsbform.yyzxpq" placeholder="请选择">
                                    <el-option v-for="item in pqList"
                                               :key="item.VALUE"
                                               :label="item.LABEL"
                                               :value="item.VALUE"></el-option>
                                </el-select>
                                </el-form-item>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>现场移交日期：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="xcyjrq" :rules="this.publicUtil.notNull">
                                    <el-date-picker size="small" type="date" format="yyyy-MM-dd" value-format="yyyy-MM-dd" placeholder="选择日期" v-model="gsbform.xcyjrq" style="width: 100%"></el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>装表口径：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="zbkj" :rules="this.publicUtil.notNull">
                                    &Phi;
                                    <el-select size="small" v-model="gsbform.zbkj" @change="getzbkj" placeholder="请选择" style="width:140px;">
                                        <el-option v-for="item in zbkjList"
                                                   :key="item.VALUE"
                                                   :label="item.LABEL"
                                                   :value="item.VALUE"></el-option>
                                    </el-select>
                                    mm
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>表身码：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="bsm" :rules="this.publicUtil.notNull">
                                    {{bsmqz}}
                                    <el-input size="small" v-model="gsbform.bsm" style="width: 130px"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>安装日期：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="azrq" :rules="this.publicUtil.notNull">
                                    <el-date-picker size="small" type="date" placeholder="选择日期" v-model="gsbform.azrq" style="width: 100%"></el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>通水日期：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="tsrq" :rules="this.publicUtil.notNull">
                                    <el-date-picker size="small" type="date" placeholder="选择日期" v-model="gsbform.tsrq" style="width: 100%"></el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>新装水表底度：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="xzsbdd" :rules="this.publicUtil.NullOrFloat">
                                    <el-input size="small" v-model="gsbform.xzsbdd"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>拆回旧表底度：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="chjbdd" :rules="this.publicUtil.NullOrFloat">
                                    <el-input size="small" v-model="gsbform.chjbdd"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>水表厂家：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="sbcj" :rules="this.publicUtil.notNull">
                                    <el-select size="small" v-model="gsbform.sbcj" @change="getsbcjjc" placeholder="请选择">
                                        <el-option v-for="item in sbcjList"
                                                   :key="item.VALUE"
                                                   :label="item.LABEL"
                                                   :value="item.VALUE"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>水表类型：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="sblx" :rules="this.publicUtil.notNull">
                                    <el-select size="small" v-model="gsbform.sblx" placeholder="请选择">
                                        <el-option v-for="item in sblxList"
                                                   :key="item.VALUE"
                                                   :label="item.LABEL"
                                                   :value="item.VALUE"></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>GPS数据：</span>
                            </el-col>
                            <el-col :span="13">
                                <el-form-item prop="gps" :rules="this.publicUtil.gps">
                                    <el-input size="small" v-model="gsbform.gps" style="width:250px;"></el-input>
                                    <span>格式：35xxxxx.xxx,5xxxxx.xxx,xx.xxx</span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>施工人员：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="sgry" :rules="this.publicUtil.notNull">
                                    <el-input size="small" v-model="gsbform.sgry"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>日期：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="sgrq" :rules="this.publicUtil.notNull">
                                    <el-date-picker size="small" type="date" placeholder="选择日期" v-model="gsbform.sgrq" style="width: 100%"></el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>用户确认：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="yhqr" :rules="this.publicUtil.notNull">
                                    <el-input size="small" v-model="gsbform.yhqr"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>日期：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="yhqrrq" :rules="this.publicUtil.notNull">
                                    <el-date-picker size="small" type="date" placeholder="选择日期" v-model="gsbform.yhqrrq" style="width: 100%"></el-date-picker>
                                </el-form-item>
                            </el-col>
                        </el-form-item>
                    </el-form>
                </el-card>
                <el-card class="box-card"  style="margin-top: 15px;overflow: auto">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">用户申请信息</div>
                    </div>
                    <el-form>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>申请编号：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="publicform.sqbh" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>户号：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="publicform.hh" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>用户联系人：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="publicform.yhlxr" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>户名：</span>
                            </el-col>
                            <el-col :span="13">
                                <el-input size="small" v-model="publicform.hm" :readonly="true" style="width: 500px"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>联系电话：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="publicform.lxdh" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>用户地址：</span>
                            </el-col>
                            <el-col :span="13">
                                <el-input size="small" v-model="publicform.yhdz" :readonly="true" style="width: 500px"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>水表口径：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="publicform.sbkj" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>用水性质：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="publicform.ysxz" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>交费方式：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="publicform.jffs" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>开户名：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="publicform.khm" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>账号：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="publicform.zh" :readonly="true"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>开户行：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input size="small" v-model="publicform.khh" :readonly="true"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item style="text-align: center">
                            <el-button size="mini" type="primary" :loading="submitLoading" v-on:click="gsbedit_vue" >
                                <i class="iconfont icon-save"></i>保存
                            </el-button>
                        </el-form-item>
                    </el-form>
                </el-card>
            </el-dialog>
        </el-row>
    </section>
</template>

<script>
    import { showSbList,editWb,findJmbByLdid,sbyjSubmit,querysbcjjcBybh,querysbkjmcBybh,updateCheckTask } from '../../api/sbyj';

    export default {
        // props: ['yjdbh','gclx','jsdw'],
        data(){
            return{
                tabUrl:'',
                xmid:this.$route.query.xmid,//项目id
                xmbh:this.$route.query.xmbh,//项目编号
                gclx:this.$route.query.gclx,//工程类型
                xmmc:this.$route.query.xmmc,//项目名称
                jsdw:this.$route.query.jsdw,//建设单位*/
                isEdit:this.$route.query.isEdit,//是否编辑
                isTh:this.$route.query.isTh,//是否退回
                yjzbid:this.$route.query.yjzbid,//移交主表ID
                taskId:this.$route.query.taskId,
                activityId:this.$route.query.activityId,
                zyjdbh:'',
                submitLoading:false,

                /*居民楼栋勾选水表返回参数*/
                jmselid:this.$route.query.jmselid,//居民表已勾选
                jmldid:this.$route.query.jmldid,//已勾选楼栋ID
                type:this.$route.query.type,//居民表返回
                jmselcounts:this.$route.query.jmselcounts,//居民表勾选数量
                ldselid:'',//楼栋勾选数量
                /*居民楼栋勾选水表返回参数*/

                /*工商勾选水表返回参数*/
                gsbselid:'',//工商表已勾选水表ID
                /*工商勾选水表返回参数*/

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
                wbsels:[],//五级计量表列表选中列
                gsbsels: [],//工商表列表选中列
                jmbsels: [],//居民表列表选中列

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
                sbcjList:[],//水表厂家下拉框
                sblxList:[],//水表类型下拉框
                zbkjList:[],//装表口径下拉框
                pqList:[],//营业中心片区下拉框
                qyList:[],//区域下拉框
                wbform:{
                    qy:'',zbdz:'',yyzxpq:'',xcyjrq:'',zbkj:'',bsm:'',azrq:'',tsrq:'',xzsbdd:'',chjbdd:'',sbcj:'',
                    sblx:'',gps:'',sgry:'',sgrq:'',yhqr:'',yhqrrq:'',sbid:'',
                },
                gsbform:{
                    qy:'',zbdz:'',yyzxpq:'',xcyjrq:'',zbkj:'',bsm:'',azrq:'',tsrq:'',xzsbdd:'',chjbdd:'',sbcj:'',
                    sblx:'',gps:'',sgry:'',sgrq:'',yhqr:'',yhqrrq:'',sbid:'',
                },
                publicform:{
                    sqbh:'',hh:'',hm:'',yhdz:'',yhlxr:'',lxdh:'',sbkj:'',ysxz:'',jffs:'',khm:'',zh:'',khh:'',
                },

                bsmqz:'HF020-',
                bsmjc:'',
                bsmkj:'',

                wbbsmqz:'HF020-',
                wbbsmjc:'',
                wbbsmkj:'',

            }
        },
        mounted(){
            this.changeQszt();
        },
        methods:{
            //跳转至项目全景
            goxmqj(){
                this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid  }})
            },
            //流程改变签收状态
            changeQszt(){
                if(this.taskId != '' && this.taskId != undefined){
                    let para = {taskId: this.taskId};
                    updateCheckTask(para).then(res=>{

                    });
                }
            },
            //初始化方法
            yjchildinit(yjdbh){
                this.zyjdbh = yjdbh;
                let params ={xmid:this.xmid,wbsize:this.wbsize,wbpage:this.wbpage-1,jmbsize:this.jmbsize,
                    jmbpage:this.jmbpage-1,gsbsize:this.gsbsize,gsbpage:this.gsbpage-1};
                showSbList(params).then((res)=>{
                    this.data = res.data.zpry;//指派人员
                    this.wjjlb = res.data.wbList;
                    this.wbtotal = res.data.wbtotal;
                    this.gsb = res.data.gsbList;
                    this.gsbtotal = res.data.gsbtotal;
                    this.jmb = res.data.jmbList;
                    this.jmbtotal = res.data.jmbtotal;
                    this.qyList = res.data.qyList;
                    this.zbkjList = res.data.zbkjList;
                    this.sbcjList = res.data.sbcjList;
                    this.sblxList = res.data.sblxList;
                    this.pqList = res.data.pqList;
                    this.wbform.qy = res.data.varList[0].QY;
                    this.gsbform.qy = res.data.varList[0].QY;
                    this.publicform.sqbh = res.data.varList[0].SQBH;
                    this.publicform.yhlxr = res.data.varList[0].USERNAME;
                    this.publicform.lxdh = res.data.varList[0].LXRSJH;
                    if(this.type == '1'){
                        this.jmgx_vue(this.jmldid,this.jmselcounts);
                    }
                    this.querySbcheck();
                });
            },
            jmgx_vue(ldid,count){
                if(ldid == -1){
                    this.tabBdz = "居民表";
                    this.jmb.forEach((row,index)=>{
                        row.BCGYSL = count;
                    });
                }else{
                    this.sblistLoading = true;
                    this.tabBdz = "居民表";
                    this.jmb.forEach((row,index)=>{
                        this.sblistLoading = false;
                        if(row.LDID == ldid){
                            row.BCGYSL = count;
                        }
                    });
                }
            },
            getsbcjjc(val){
                let para = {sbcj:val};
                querysbcjjcBybh(para).then(res=>{
                    this.bsmjc = res.sbcjjc;
                    if(this.bsmkj == ''){
                        this.bsmqz = res.sbcjjc + "020-";
                    }else {
                        this.bsmqz = res.sbcjjc + this.bsmkj+"-";
                    }
                });
            },
            getzbkj(val){
                let para = {kjbh:val};
                querysbkjmcBybh(para).then(res=>{
                    this.bsmkj = res.sbkjmc;
                    if(this.bsmjc == ''){
                        this.bsmqz = "HF" + res.sbkjmc + "-";
                    }else {
                        this.bsmqz = this.bsmjc + res.sbkjmc +"-";
                    }
                });
            },
            getwbsbcjjc(val){
                let para = {sbcj:val};
                querysbcjjcBybh(para).then(res=>{
                    this.wbbsmjc = res.sbcjjc;
                    if(this.wbbsmkj == ''){
                        this.wbbsmqz = res.sbcjjc + "020-";
                    }else {
                        this.wbbsmqz = res.sbcjjc + this.wbbsmkj+"-";
                    }
                });
            },
            getwbzbkj(val){
                let para = {kjbh:val};
                querysbkjmcBybh(para).then(res=>{
                    this.wbbsmkj = res.sbkjmc;
                    if(this.wbbsmjc == ''){
                        this.wbbsmqz = "HF" + res.sbkjmc + "-";
                    }else {
                        this.wbbsmqz = this.wbbsmjc + res.sbkjmc +"-";
                    }
                });
            },
            //五级计量表保存
            wbedit_vue(){
                this.$refs.wbform.validate((valid) => {
                    if (valid) {
                        let params = this.wbform;
                        this.sblistLoading = true;
                        editWb(params).then(res=>{
                            if(res.data == true){
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
                        editWb(params).then(res=>{
                            if(res.data == true){
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
            //查询工商水表是否默认不可选
            gscheckboxInit(row,index){
                if(row.yjzt == 'yyj'){
                    if(row.sbyjdbh == this.zyjdbh){
                        return true;
                    }else{
                        return false;//不可勾选
                    }
                }else{
                    return true;//可勾选
                }
            },
            wbcheckboxInit(row,index){
                if(row.yjzt == 'yyj'){
                    if(row.sbyjdbh == this.zyjdbh){
                        return true;
                    }else{
                        return false;//不可勾选
                    }
                }else{
                    return true;//可勾选
                }
            },
            //查询居民水表是否默认不可选
            jmcheckboxInit(row,index){
                if(row.YJSL == row.ZHS){
                    return false;//不可勾选
                }else{
                    return true;//可勾选
                }
            },
            //水表是否默认选中
            querySbcheck(){
                if(this.gsb != ''){
                    this.gsb.forEach((row,index)=>{
                        if(row.sbyjdbh == this.zyjdbh){
                            setTimeout(()=>{
                                this.$refs.dbmultipleTable.toggleRowSelection(this.gsb[index],true)
                            },1)
                        }else{
                            setTimeout(()=>{
                                this.$refs.dbmultipleTable.toggleRowSelection(this.gsb[index],false)
                            },1)
                        }
                    })
                }
                if(this.wjjlb != ''){
                    this.wjjlb.forEach((row,index)=>{
                        if(row.sbyjdbh == this.zyjdbh){
                            setTimeout(()=>{
                                this.$refs.wbmultipleTable.toggleRowSelection(this.wjjlb[index],true)
                            },1)
                        }else{
                            setTimeout(()=>{
                                this.$refs.wbmultipleTable.toggleRowSelection(this.wjjlb[index],false)
                            },1)
                        }
                    })
                }
            },
            //打开五级计量表编辑界面
            editwjjlb_vue(index, row){
                this.wbFormVisible = true;
                let a = row.hh == null ? '': row.hh;
                let b = row.yyzx_hh == null ? '': row.yyzx_hh;
                this.publicform.hh = a + " " + b;
                this.publicform.hm = row.hm;
                this.publicform.yhdz = row.dz;
                this.publicform.sbkj = row.vkjmc + 'mm';
                this.publicform.ysxz = row.vysxz;
                this.publicform.jffs = row.vjffs;
                this.publicform.khm = row.khmc;
                this.publicform.zh = row.yhzh;
                this.publicform.khh = row.khyh;

                if(row.azwzxzq != null){
                    this.wbform.qy = row.azwzxzq;
                }
                if(row.azwz != null){
                    this.wbform.zbdz = row.azwz;
                }else{
                    this.wbform.zbdz = row.dz;
                }
                this.wbform.yyzxpq = row.pqbh;
                this.wbform.xcyjrq = row.yyzx_xcyjrq;
                // this.wbform.zbkj = row.vsgkjmc;
                this.wbform.zbkj = row.sgdw_sbkj;
                this.wbform.bsm = row.bsm;
                this.wbform.azrq = row.sgsj;
                this.wbform.tsrq = row.tssj;
                this.wbform.xzsbdd = row.ds;
                this.wbform.chjbdd = row.ybds;
                this.wbform.sbcj = row.sbcjbh;
                // this.wbform.sblx = row.vsblx;
                this.wbform.sblx = row.sblxbh;
                this.wbform.gps = row.gps;
                this.wbform.sgry = row.sgry;
                this.wbform.sgrq = row.sgryqzsj;
                this.wbform.yhqr = row.yhqr;
                this.wbform.yhqrrq = row.yhqrqzsj;
                this.wbform.sbid = row.id;
            },
            //打开工商表编辑界面
            editgsb_vue(index, row){
                this.gsbFormVisible = true;
                let a = row.hh == null ? '': row.hh;
                let b = row.yyzx_hh == null ? '': row.yyzx_hh;
                this.publicform.hh = a + " " + b;
                this.publicform.hm = row.hm;
                this.publicform.yhdz = row.dz;
                this.publicform.sbkj = row.vkjmc + 'mm';
                this.publicform.ysxz = row.vysxz;
                this.publicform.jffs = row.vjffs;
                this.publicform.khm = row.khmc;
                this.publicform.zh = row.yhzh;
                this.publicform.khh = row.khyh;

                if(row.azwzxzq != null){
                    this.gsbform.qy = row.azwzxzq;
                }
                if(row.azwz != null){
                    this.gsbform.zbdz = row.azwz;
                }else{
                    this.gsbform.zbdz = row.dz;
                }
                this.gsbform.yyzxpq = row.pqbh;
                this.gsbform.xcyjrq = row.yyzx_xcyjrq;
                // this.gsbform.zbkj = row.vsgkjmc;
                this.gsbform.zbkj = row.sgdw_sbkj;
                this.gsbform.bsm = row.bsm;
                this.gsbform.azrq = row.sgsj;
                this.gsbform.tsrq = row.tssj;
                this.gsbform.xzsbdd = row.ds;
                this.gsbform.chjbdd = row.ybds;
                this.gsbform.sbcj = row.sbcjbh;
                // this.gsbform.sblx = row.vsblx;
                this.gsbform.sblx = row.sblxbh;
                this.gsbform.gps = row.gps;
                this.gsbform.sgry = row.sgry;
                this.gsbform.sgrq = row.sgryqzsj;
                this.gsbform.yhqr = row.yhqr;
                this.gsbform.yhqrrq = row.yhqrqzsj;
                this.gsbform.sbid = row.id;
            },
            //户表勾选界面
            editjmb_vue(index, row){
                this.$router.push({path: '/sbyjHbgx', query: {xmid:this.xmid,ldid:row.LDID,zyjdbh:this.zyjdbh,
                        xmmc:this.xmmc,jsdw:this.jsdw,xmbh:this.xmbh,gclx:this.gclx,isEdit:this.isEdit,
                        isTh:this.isTh,yjzbid:this.yjzbid,taskId:this.taskId,activityId:this.activityId}});
            },
            sbSubmit(type){
                if(type == 1){
                    let ldids = "";
                    if(this.jmbsels != ''){
                        for(let i=0;i<this.jmbsels.length;i++){
                            if(i==0){
                                ldids +=  this.jmbsels[i].LDID;
                            }else{
                                ldids += "," + this.jmbsels[i].LDID;
                            }
                        }
                    }
                    let sbids = "";
                    if(this.gsbsels != ''){
                        for(let j=0;j<this.gsbsels.length;j++){
                            if(j == this.gsbsels.length -1){
                                sbids += this.gsbsels[j].id ;
                            }else{
                                sbids += this.gsbsels[j].id + ",";
                            }
                        }
                    }
                    let wbsbids = "";
                    if(this.wbsels != ''){
                        for(let k=0;k<this.wbsels.length;k++){
                            if(k == this.wbsels.length -1){
                                wbsbids += this.wbsels[k].id ;
                            }else{
                                wbsbids += this.wbsels[k].id + ",";
                            }
                        }
                    }
                    let params = {sbids :sbids,ldids:ldids,wbsbids:wbsbids,xmid:this.xmid,yjdbh:this.zyjdbh,
                        name: this.resource,type:type,isEdit:this.isEdit,isTh:this.isTh,yjzbid:this.yjzbid};
                    this.$routerTab.close();
                    this.$routerTab.refresh('/sbyjdlb?xmid='+this.xmid+'&xmmc='+this.xmmc+'&xmbh='+this.xmbh+'&gclx='+this.gclx);
                    sbyjSubmit(params).then(res=>{
                        if(res.data == true){
                            this.$message({
                                message: '保存成功',
                                type: 'success'
                            });
                        }else{
                            this.$message.error('保存失败！');
                        }
                    });
                }else{
                    if(this.resource == ''){
                        this.$message({
                            message: '请选择指派人员！',
                            type: 'warning'
                        });
                        if(this.isEdit == '1'){
                            if(this.isTh == '1'){
                                this.tabUrl = "/addSbyjd?xmid="+this.xmid+"&zyjdbh="+this.zyjdbh+"&xmmc="+this.xmmc+
                                    "&xmbh="+this.xmbh+"&gclx="+this.gclx+"&isEdit="+this.isEdit+
                                    "&yjzbid="+this.yjzbid+"&isTh=1&jsdw="+this.jsdw+"&activityId="+this.activityId+
                                    "&taskId="+this.taskId;
                            }else{
                                this.tabUrl = "/addSbyjd?xmid="+this.xmid+"&zyjdbh="+this.zyjdbh+"&xmmc="+this.xmmc+
                                    "&xmbh="+this.xmbh+"&gclx="+this.gclx+"&isEdit="+this.isEdit+"&yjzbid="+this.yjzbid;
                            }
                        }else{
                            this.tabUrl = "/addSbyjd?xmid="+this.xmid+"&xmmc="+this.xmmc+
                                "&xmbh="+this.xmbh+"&gclx="+this.gclx+"&jsdw="+this.jsdw+"&yjzbid="+this.yjzbid;
                        }
                    }else{
                        let ldids = "";
                        if(this.jmbsels != ''){
                            for(let i=0;i<this.jmbsels.length;i++){
                                if(i==0){
                                    ldids +=  this.jmbsels[i].LDID;
                                }else{
                                    ldids += "," + this.jmbsels[i].LDID;
                                }
                            }
                        }
                        let sbids = "";
                        if(this.gsbsels != ''){
                            for(let j=0;j<this.gsbsels.length;j++){
                                if(j == this.gsbsels.length -1){
                                    sbids += this.gsbsels[j].id ;
                                }else{
                                    sbids += this.gsbsels[j].id + ",";
                                }
                            }
                        }
                        let wbsbids = "";
                        if(this.wbsels != ''){
                            for(let k=0;k<this.wbsels.length;k++){
                                if(k == this.wbsels.length -1){
                                    wbsbids += this.wbsels[k].id ;
                                }else{
                                    wbsbids += this.wbsels[k].id + ",";
                                }
                            }
                        }
                        let params = {sbids :sbids,ldids:ldids,wbsbids:wbsbids,xmid:this.xmid,yjdbh:this.zyjdbh,
                            name: this.resource,type:type,isEdit:this.isEdit,isTh:this.isTh,yjzbid:this.yjzbid};
                        this.tabUrl = '/sbyjdlb?xmid='+this.xmid+'&xmmc='+this.xmmc+'&xmbh='+this.xmbh+'&gclx='+this.gclx;
                        this.$routerTab.close();
                        this.$routerTab.refresh('/sbyjdlb?xmid='+this.xmid+'&xmmc='+this.xmmc+'&xmbh='+this.xmbh+'&gclx='+this.gclx);
                        sbyjSubmit(params).then(res=>{
                            if(res.data == true){
                                this.$message({
                                    message: '指派成功',
                                    type: 'success'
                                });
                            }else{
                                this.$message.error('提交失败！');
                            }
                        });
                    }
                }
            },
            return_vue(){
                this.$routerTab.close();
            },
            //移交状态
            formatYjzt:function (row, column) {
                return row.yjzt == 'wyj' ? '未移交' : '已移交';
            },
            //通水时间格式转换
            formatLxsj: function (row, column) {
                let sj = row.tssj;
                return this.publicUtil.formatChange(sj);
            },
            wbselsChange:function(sels){
                this.wbsels = sels;
            },
            gsbselsChange: function (sels) {
                this.gsbsels = sels;
            },
            jmbselsChange: function (sels) {
                if(sels == ''){
                    this.jmgx_vue(-1,0);
                }else{
                    let ldid = sels[0].LDID;
                    this.jmgx_vue(ldid,sels[0].WYJSL);
                    // let params = {xmid:this.xmid,ldid:ldid};
                    // findJmbByLdid(params).then(res=>{
                    //     this.sblistLoading = true;
                    //     this.jmgx_vue(ldid,res.data.list.length);
                    // });
                }
                this.jmbsels = sels;
            },
            jmbhandleCurrentChange(val) {
                this.jmbpage = val;
                this.yjchildinit();
            },
            jmbhandleSizeChange(val) {
                this.jmbsize = val;
                this.jmbpage = 1;
                this.yjchildinit();
            },
            gsbhandleCurrentChange(val) {
                this.gsbpage = val;
                this.yjchildinit();
            },
            gsbhandleSizeChange(val) {
                this.gsbsize = val;
                this.gsbpage = 1;
                this.yjchildinit();
            },
            wbhandleCurrentChange(val) {
                this.wbpage = val;
                this.yjchildinit();
            },
            wbhandleSizeChange(val) {
                this.wbsize = val;
                this.wbpage = 1;
                this.yjchildinit();
            },

        }
    }
</script>

<style>

</style>