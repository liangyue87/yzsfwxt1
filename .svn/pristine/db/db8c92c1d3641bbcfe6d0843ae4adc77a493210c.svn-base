<template>
    <section>
        <el-row>
            <el-col :span="24" class="toolbar" >
                <el-form  :model="filters" >
                    <el-form-item>
                        <el-col :span="2" style="text-align: right">
                            <span>门牌号：</span>
                        </el-col>
                        <el-col :span="4">
                            <el-input size="small" v-model="filters.beginMp"></el-input>
                        </el-col>
                        <el-col class="line" :span="1" style="text-align: center">-</el-col>
                        <el-col :span="4">
                            <el-input size="small" v-model="filters.endMp"></el-input>
                        </el-col>
                        <el-col :span="10" style="margin-left: 10px">
                            <el-button size="mini" type="primary" @click="qrgx_vue">
                                <i class="iconfont icon-queren"></i>确认勾选</el-button>
                            <el-button size="mini" type="info" style="margin-left: 3px" v-on:click="qxgx_vue">
                                <i class="iconfont icon-quxiao"></i>取消勾选</el-button>
                            <router-link :to="{path:'/addSbyjd', query:{xmid:this.xmid,xmmc:this.xmmc,
                            xmbh:this.xmbh,gclx:this.gclx,zyjdbh:this.zyjdbh,jmselid:this.jmselid,type:'1',
                            jmldid:this.ldid,jmselcounts:this.jmselcounts,isEdit:this.isEdit,isTh:this.isTh,
                            yjzbid:this.yjzbid,taskId:this.taskId,activityId:this.activityId}}">
                                <el-button size="mini" type="primary" @click="save_vue">
                                    <i class="iconfont icon-queren"></i>保存并退出</el-button>
                            </router-link>
                            <router-link :to="{path:'/addSbyjd', query:{xmid:this.xmid,xmmc:this.xmmc,
                            xmbh:this.xmbh,gclx:this.gclx,zyjdbh:this.zyjdbh,isEdit:this.isEdit,isTh:this.isTh,
                            yjzbid:this.yjzbid,taskId:this.taskId,activityId:this.activityId}}">
                                <el-button size="mini" type="info" v-on:click="return_vue">
                                    <i class="iconfont icon-quxiao"></i>返回</el-button>
                            </router-link>
                        </el-col>
                    </el-form-item>
                </el-form>
            </el-col>

            <el-col :span="24" class="toolbar">
                <el-col style="text-align: left;margin-left: 5px">
                    <el-button size="mini" type="primary" v-on:click="pledit_vue">
                        <i class="iconfont icon-ai-edit"></i>编辑</el-button>
                    <el-button size="mini" type="success" v-on:click="xzbg_vue">
                        <i class="iconfont icon-xiazai_f"></i>下载表格</el-button>
                    <el-upload
                            style="display: inline; margin-left: 10px;margin-right: 10px;"
                            :show-file-list='false'
                            :before-upload="beforeUpload"
                            action='#'
                            :on-exceed="handleExceed"
                            :on-change="handleChange"
                            :on-remove="handleRemove"
                            :on-preview="handlePreview"
                            :http-request="uploadFile" >
                        <el-button size="mini" type="success">
                            <i class="iconfont icon-daoru"></i>导入数据
                        </el-button>
                    </el-upload>
                    <el-button size="mini" type="success" v-on:click="scbb_vue">
                        <i class="iconfont icon-xiazai_f"></i>生成报表</el-button>
                    <el-button size="mini" type="success" v-on:click="cysb_vue">
                        <i class="iconfont icon-dayin_f"></i>出验收表</el-button>
                </el-col>
                <!--列表-->
                <elx-editable ref="editable" :data.sync="row" height="450" border highlight-current-row v-loading="listLoading"
                           @selection-change="selsChange" @blur-active="elxedit_vue"  style="width: 100%;">
                    <elx-editable-column type="selection" width="100" align="center" :selectable='jmcheckboxInit'>
                    </elx-editable-column>
                    <elx-editable-column label="操作" align="center">
                        <template slot-scope="scope">
                            <label title="编辑">
                                <i class="iconfont icon-ai-edit"  @click="edit_vue(scope.$index, scope.row)"
                                   style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                            </label>
                        </template>
                    </elx-editable-column>
                    <elx-editable-column prop="hh" label="报装户号" width="150" align="center">
                    </elx-editable-column>
                    <elx-editable-column prop="ldbh" label="楼栋号" align="center">
                    </elx-editable-column>
                    <elx-editable-column prop="mph" label="门派号" align="center">
                    </elx-editable-column>
                    <elx-editable-column prop="azwz" label="装表位置" width="100" align="center" :edit-render="{name: 'ElInput'}">
                    </elx-editable-column>
                    <elx-editable-column prop="bsm" label="表身码" width="100" align="center" :edit-render="{name: 'ElInput'}">
                    </elx-editable-column>
                    <elx-editable-column prop="sgdw_sbkj" label="口径" align="center"
                                         :edit-render="{name: 'ElSelect',options: zbkjList,optionProps:{value:'VALUE',label:'LABEL'}}">
                    </elx-editable-column>
                    <elx-editable-column prop="fq" label="分区" align="center">
                    </elx-editable-column>
                    <elx-editable-column prop="pqbh" label="供水所" width="100" align="center"
                                         :edit-render="{name: 'ElSelect',options: pqList,optionProps:{value:'VALUE',label:'LABEL'}}">
                    </elx-editable-column>
                    <elx-editable-column prop="sgsj" label="安装日期" width="160" align="center"
                                         :formatter="formatAzrq" :edit-render="{name: 'ElDatePicker', props: {type: 'date', format: 'yyyy-MM-dd'}}">
                    </elx-editable-column>
                    <elx-editable-column prop="tssj" label="通水日期" width="160" align="center"
                                         :formatter="formatTsrq" :edit-render="{name: 'ElDatePicker', props: {type: 'date', format: 'yyyy-MM-dd'}}">
                    </elx-editable-column>
                    <elx-editable-column prop="ds" label="底度" align="center" :edit-render="{name: 'ElInput'}">
                    </elx-editable-column>
                    <elx-editable-column prop="sbcjbh" label="水表厂家" width="100" align="center"
                                         :edit-render="{name: 'ElSelect',options: sbcjList,optionProps:{value:'VALUE',label:'LABEL'}}">
                    </elx-editable-column>
                    <elx-editable-column prop="sblxbh" label="水表类型" width="100" align="center"
                                         :edit-render="{name: 'ElSelect',options: sblxList,optionProps:{value:'VALUE',label:'LABEL'}}">
                    </elx-editable-column>
                    <elx-editable-column prop="sgry" label="施工人员" width="100" align="center" :edit-render="{name: 'ElInput'}">
                    </elx-editable-column>
                    <elx-editable-column prop="sgryqzsj" label="施工签字日期" width="160" align="center"
                                         :formatter="formatSgsj" :edit-render="{name: 'ElDatePicker', props: {type: 'date', format: 'yyyy-MM-dd'}}">
                    </elx-editable-column>
                    <elx-editable-column prop="yhqr" label="用户确认人" width="120" align="center" :edit-render="{name: 'ElInput'}">
                    </elx-editable-column>
                    <elx-editable-column prop="yhqrqzsj" label="用户确认日期" width="160" align="center"
                                         :formatter="formatYhsj" :edit-render="{name: 'ElDatePicker', props: {type: 'date', format: 'yyyy-MM-dd'}}">
                    </elx-editable-column>
                </elx-editable>
                <div style="display: none">
                    <elx-editable id="out-table" :data.sync="row" border highlight-current-row v-loading="listLoading"
                                  @selection-change="selsChange" @blur-active="elxedit_vue"  style="width: 100%;">
                        <elx-editable-column prop="hh" label="报装户号" width="120" align="center">
                        </elx-editable-column>
                        <elx-editable-column prop="mph" label="门派号" width="150" align="center">
                        </elx-editable-column>
                        <elx-editable-column prop="azwz" label="装表位置" width="100" align="center">
                        </elx-editable-column>
                        <elx-editable-column prop="" label="表身码" width="100" align="center">
                        </elx-editable-column>
                        <elx-editable-column prop="" label="分区" width="100" align="center">
                        </elx-editable-column>
                    </elx-editable>
                </div>
                <div style="display: none">
                    <elx-editable id="scbb-table" :data.sync="row" border highlight-current-row v-loading="listLoading"
                                  @selection-change="selsChange" @blur-active="elxedit_vue"  style="width: 100%;">
                        <elx-editable-column prop="hh" label="报装户号" width="120" align="center">
                        </elx-editable-column>
                        <elx-editable-column prop="" label="立户号" width="120" align="center">
                        </elx-editable-column>
                        <elx-editable-column prop="hm" label="户名" width="120" align="center">
                        </elx-editable-column>
                        <elx-editable-column prop="dz" label="用户地址" width="120" align="center">
                        </elx-editable-column>
                        <elx-editable-column prop="ldmc" label="楼栋号" width="100" align="center">
                        </elx-editable-column>
                        <elx-editable-column prop="mph" label="门派号" width="150" align="center">
                        </elx-editable-column>
                        <elx-editable-column prop="azwz" label="装表位置" width="100" align="center">
                        </elx-editable-column>
                        <elx-editable-column prop="bsm" label="表身码" width="100" align="center">
                        </elx-editable-column>
                        <elx-editable-column prop="sgdw_sbkj" label="口径" width="100" align="center">
                        </elx-editable-column>
                        <elx-editable-column prop="fq" label="分区" width="100" align="center">
                        </elx-editable-column>
                        <elx-editable-column prop="pqbh" label="供水所" width="100" align="center">
                        </elx-editable-column>
                        <elx-editable-column prop="sgsj" label="安装日期" width="150" align="center" :formatter="formatAzrq">
                        </elx-editable-column>
                        <elx-editable-column prop="tssj" label="通水日期" width="150" align="center" :formatter="formatTsrq">
                        </elx-editable-column>
                        <elx-editable-column prop="ds" label="底度" width="100" align="center">
                        </elx-editable-column>
                        <elx-editable-column prop="sbcjbh" label="水表厂家" width="100" align="center">
                        </elx-editable-column>
                        <elx-editable-column prop="sblxbh" label="水表类型" width="100" align="center">
                        </elx-editable-column>
                        <elx-editable-column prop="sgry" label="施工人员" width="100" align="center">
                        </elx-editable-column>
                        <elx-editable-column prop="sgryqzsj" label="施工签字日期" width="150" align="center">
                        </elx-editable-column>
                        <elx-editable-column prop="yhqr" label="用户确认人" width="120" align="center">
                        </elx-editable-column>
                        <elx-editable-column prop="yhqrqzsj" label="用户确认日期" width="150" align="center">
                        </elx-editable-column>
                    </elx-editable>
                </div>
            </el-col>

            <!--列编辑-->
            <el-dialog title="" :visible.sync ="jmbFormVisible" width="1000px">
                <el-card class="box-card"  style="margin-top: 15px;overflow: auto">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">施工完成确认</div>
                    </div>
                    <el-form :model="jmbform"  ref="jmbform" class="demo-ruleForm">
                        <el-form-item style="text-align: right">
                            <el-button size="medium" type="primary" icon="el-icon-check" v-on:click="jmbedit_vue">保存</el-button>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>装表地址：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-select size="small" v-model="jmbform.qy" placeholder="请选择">
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
                                    <el-input size="small" v-model="jmbform.zbdz"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>营业中心片区：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="yyzxpq" :rules="this.publicUtil.notNull">
                                    <el-select size="small" v-model="jmbform.yyzxpq" placeholder="请选择">
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
                                <el-date-picker size="small" type="date" placeholder="选择日期" v-model="jmbform.xcyjrq" style="width: 100%"></el-date-picker>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>装表口径：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="zbkj" :rules="this.publicUtil.notNull">
                                    &Phi;
                                    <el-select size="small" v-model="jmbform.zbkj" @change="getzbkj" placeholder="请选择" style="width:140px;">
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
                                    <el-input size="small" v-model="jmbform.bsm" style="width: 130px"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>安装日期：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="azrq" :rules="this.publicUtil.notNull">
                                    <el-date-picker size="small" type="date" placeholder="选择日期" v-model="jmbform.azrq" style="width: 100%"></el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>通水日期：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="tsrq" :rules="this.publicUtil.notNull">
                                    <el-date-picker size="small" type="date" placeholder="选择日期" v-model="jmbform.tsrq" style="width: 100%"></el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>新装水表底度：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="xzsbdd" :rules="this.publicUtil.NullOrFloat">
                                    <el-input size="small" v-model="jmbform.xzsbdd"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>拆回旧表底度：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="chjbdd" :rules="this.publicUtil.NullOrFloat">
                                    <el-input size="small" v-model="jmbform.chjbdd"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>水表厂家：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="sbcj" :rules="this.publicUtil.notNull">
                                    <el-select size="small" v-model="jmbform.sbcj" @change="getsbcjjc" placeholder="请选择">
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
                                    <el-select size="small" v-model="jmbform.sblx" placeholder="请选择">
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
                                    <el-input size="small" v-model="jmbform.gps" style="width:250px;"></el-input>
                                    <span>格式：35xxxxx.xxx,5xxxxx.xxx,xx.xxx</span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>施工人员：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="sgry" :rules="this.publicUtil.notNull">
                                    <el-input size="small" v-model="jmbform.sgry"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>日期：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="sgrq" :rules="this.publicUtil.notNull">
                                    <el-date-picker size="small" type="date" placeholder="选择日期" v-model="jmbform.sgrq" style="width: 100%"></el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>用户确认：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="yhqr" :rules="this.publicUtil.notNull">
                                    <el-input size="small" v-model="jmbform.yhqr"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>日期：</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="yhqrrq" :rules="this.publicUtil.notNull">
                                    <el-date-picker size="small" type="date" placeholder="选择日期" v-model="jmbform.yhqrrq" style="width: 100%"></el-date-picker>
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
                                <span>报装户号：</span>
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
                    </el-form>
                </el-card>
            </el-dialog>

            <!--公共字段批量编辑-->
            <el-dialog title="编辑" :visible.sync ="plbjVisible">
                <el-form :model="pljmbform"  ref="pljmbform" class="demo-ruleForm">
                    <el-row>
                        <el-form-item>
                            <el-col :span="6" style="text-align: center">
                                <span>已勾选{{gxsl}}只表</span>
                            </el-col>
                            <el-col :span="6" style="text-align: right">
                                <el-button size="small" type="primary" icon="el-icon-check" v-on:click="pledit">应用</el-button>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>装表地址：</span>
                            </el-col>
                            <el-col :span="8">
                                <el-select size="small" v-model="pljmbform.zbdz" placeholder="请选择" style="width: 243px;">
                                    <el-option v-for="item in qyList"
                                               :key="item.VALUE"
                                               :label="item.LABEL"
                                               :value="item.VALUE"></el-option>
                                </el-select>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>供水所：</span>
                            </el-col>
                            <el-col :span="8">
                                <el-select size="small" v-model="pljmbform.gss" placeholder="请选择" style="width: 243px;">
                                    <el-option v-for="item in pqList"
                                               :key="item.VALUE"
                                               :label="item.LABEL"
                                               :value="item.VALUE"></el-option>
                                </el-select>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>水表口径：</span>
                            </el-col>
                            <el-col :span="10">
                                &Phi;
                                <el-select size="small" v-model="pljmbform.sbkj" placeholder="请选择">
                                    <el-option v-for="item in zbkjList"
                                               :key="item.VALUE"
                                               :label="item.LABEL"
                                               :value="item.VALUE"></el-option>
                                </el-select>
                                mm
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>安装日期：</span>
                            </el-col>
                            <el-col :span="8">
                                <el-date-picker size="small" type="date" placeholder="选择日期" v-model="pljmbform.azrq" style="width: 100%"></el-date-picker>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>通水日期：</span>
                            </el-col>
                            <el-col :span="8">
                                <el-date-picker size="small" type="date" placeholder="选择日期" v-model="pljmbform.tsrq" style="width: 100%"></el-date-picker>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>水表厂家：</span>
                            </el-col>
                            <el-col :span="8">
                                <el-select size="small" v-model="pljmbform.sbcj" placeholder="请选择" style="width: 243px;">
                                    <el-option v-for="item in sbcjList"
                                               :key="item.VALUE"
                                               :label="item.LABEL"
                                               :value="item.VALUE"></el-option>
                                </el-select>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>水表类型：</span>
                            </el-col>
                            <el-col :span="8">
                                <el-select size="small" v-model="pljmbform.sblx" placeholder="请选择" style="width: 243px;">
                                    <el-option v-for="item in sblxList"
                                               :key="item.VALUE"
                                               :label="item.LABEL"
                                               :value="item.VALUE"></el-option>
                                </el-select>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>施工人员：</span>
                            </el-col>
                            <el-col :span="8">
                                <el-input size="small" v-model="pljmbform.sgry"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>日期：</span>
                            </el-col>
                            <el-col :span="8">
                                <el-date-picker size="small" type="date" placeholder="选择日期" v-model="pljmbform.sgrq" style="width: 100%"></el-date-picker>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-col :span="3" style="text-align: right">
                                <span>用户代表：</span>
                            </el-col>
                            <el-col :span="8">
                                <el-input size="small" v-model="pljmbform.yhdb"></el-input>
                            </el-col>
                            <el-col :span="3" style="text-align: right">
                                <span>日期：</span>
                            </el-col>
                            <el-col :span="8">
                                <el-date-picker size="small" type="date" placeholder="选择日期" v-model="pljmbform.yhqrrq" style="width: 100%"></el-date-picker>
                            </el-col>
                        </el-form-item>
                    </el-row>
                </el-form>
            </el-dialog>

            <el-dialog title="" :visible.sync ="dialogImportVisible" class="dialog-import">

            </el-dialog>

            <!--出验收表-->
            <el-dialog title="" :visible.sync ="printFormVisible">
                <div id="printTest">
                    <center>
                        <div style="margin-bottom: 20px;">
                            <span style="font-size: 30px;font-family: 黑体;">合肥供水集团户表验收清单</span>
                        </div>
                    </center>

                    <table align="center" border="1" cellspacing="0" width="100%">


                        <tbody align="center">
                            <tr>
                     <td width="90px" height="30px" >项目名称</td>
                     <td width="300px" colspan=3><span id="xmmc"></span></td>
                     <td width="80px">楼栋号</td>
                     <td width="100px"><span id="ldh"></span></td>
                     <td width="80px">安装部门</td>
                     <td width="100px"><span id="azbm"></span></td>
                     <td width="80px">通水日期</td>
                     <td width="100px"><span id="tsrq"></span></td>
                     </tr>

                            <tr>
                     <td width="80px" height="30px" >建设单位</td>
                     <td width="100px"><span id="jsdw"></span></td>
                     <td width="80px">工程类型</td>
                     <td width="100px"><span id="gclx"></span></td>
                     <td width="80px">项目编号</td>
                     <td width="100px"><span id="xmbh"></span></td>
                     <td width="80px">安装班组</td>
                     <td width="100px"><span id="azbz"></span></td>
                     <td width="80px">打印日期</td>
                     <td width="100px"><span id="dyrq"></span></td>
                      </tr>
                            <tr>
                                <td width="80px" height="30px">施工代表</td>
                                <td width="100px"><span id="sgdb"></span></td>
                                <td width="80px" height="30px">制表人</td>
                                <td width="100px"><span id="zbr"></span></td>
                                <td width="80px" height="30px">施工单号</td>
                                <td width="100px"><span id="sgdh"></span></td>
                                <td width="80px" height="30px">验收人</td>
                                <td width="100px" ><span id="ysr"></span></td>
                                <td width="80px" height="30px">验收日期</td>
                                <td width="100px" ><span id="ysrq"></span></td>

                            </tr>
                            <tr>
                                <td width="80px" height="30px">户名</td>
                                <td colspan=9><span id="hm"></span></td>
                            </tr>


                        </tbody>
                    </table>
                    <table align="center" border="1" cellspacing="0" width="100%" style="margin-top: 10px">
                        <tbody align="center" id="tbodyNew">
                            <tr>
                     <td width="80px" height="30px" >序号</td>
                     <td width="100px" colspan=1>立户号</td>
                     <td width="100px" colspan=5>装表地址</td>
                     <td width="100px">分区</td>
                     <td width="120px"  colspan=1>表身码</td>
                     <td width="100px">订正区</td>
                     </tr>
                           <!-- <tr>
                                <td width="80px" height="30px" ></td>
                     <td width="100px" colspan=1></td>
                     <td width="100px" colspan=5></td>
                     <td width="100px"></td>
                     <td width="120px"  colspan=1></td>
                     <td width="100px"></td>
                            </tr>
                            <tr>
                                <td width="80px" height="30px" ></td>
                     <td width="100px" colspan=1></td>
                     <td width="100px" colspan=5></td>
                     <td width="100px"></td>
                     <td width="120px"  colspan=1></td>
                     <td width="100px"></td>
                            </tr>-->
                        </tbody>
                    </table>
                </div>

                <div align="center">
                    <el-button size="mini" type="success" v-print="'#printTest'" style="margin-bottom: 3%;margin-top: 10px">
                        <i class="iconfont icon-dayin_f"></i>打印</el-button>
                </div>

            </el-dialog>

        </el-row>
    </section>
</template>

<script>
    import { findJmbByLdid,elxedit,editWb,pledit,importExcel,sbyjLdSubmit,querysbcjjcBybh,querysbkjmcBybh } from '../../api/sbyj';
    import FileSaver from "file-saver";
    import XLSX from "xlsx";
    export default {
        name: "sbyjHbgx",
        data() {
            return {
                xmid:this.$route.query.xmid,//项目id
                ldid:this.$route.query.ldid,//楼栋ID
                xmmc:this.$route.query.xmmc,//项目名称
                gclx:this.$route.query.gclx,//工程类型
                xmbh:this.$route.query.xmbh,//项目名称
                jsdw:this.$route.query.jsdw,//建设单位
                zyjdbh:this.$route.query.zyjdbh,//移交单编号
                isEdit:this.$route.query.isEdit,//是否编辑
                isTh:this.$route.query.isTh,//是否退回
                yjzbid:this.$route.query.yjzbid,//移交主表ID
                taskId:this.$route.query.taskId,
                activityId:this.$route.query.activityId,

                jmselid:'',
                jmselcounts:'',

                row:[],//列表集合
                listLoading:false,
                sels:[],//选中列

                /*门牌号范围勾选*/
                filters:{
                    beginMp:'',
                    endMp:'',
                },
                /*门牌号范围勾选*/

                /*列编辑*/
                sbcjList:[],//水表厂家下拉框
                sblxList:[],//水表类型下拉框
                zbkjList:[],//装表口径下拉框
                pqList:[],//营业中心片区下拉框
                qyList:[],//区域下拉框
                jmbFormVisible:false,
                jmbform:{
                    qy:'',zbdz:'',yyzxpq:'',xcyjrq:'',zbkj:'',bsm:'',azrq:'',tsrq:'',xzsbdd:'',chjbdd:'',sbcj:'',
                    sblx:'',gps:'',sgry:'',sgrq:'',yhqr:'',yhqrrq:'',sbid:'',
                },
                publicform:{
                    sqbh:'',hh:'',hm:'',yhdz:'',yhlxr:'',lxdh:'',sbkj:'',ysxz:'',jffs:'',khm:'',zh:'',khh:'',
                },
                /*列编辑*/

                /*公共字段批量编辑*/
                plbjVisible:false,
                gxsl:'',
                pljmbform:{
                    zbdz:'',gss:'',sbkj:'',azrq:'',tsrq:'',sbcj:'',sblx:'',sgry:'',sgrq:'',yhdb:'',yhqrrq:'',
                },
                /*公共字段批量编辑*/

                /*导出excel模板，批量导入数据*/
                dialogImportVisible:false,
                fileList:[],
                /*出验收表*/
                printFormVisible:false,

                bsmqz:'HF020-',
                bsmjc:'',
                bsmkj:'',
            }
        },
        mounted() {
            this.init_vue();
        },
        methods: {
            //初始化方法
            init_vue(){
                let params = {xmid:this.xmid,ldid:this.ldid};
                findJmbByLdid(params).then(res=>{
                    this.row = res.data.list;
                    this.qyList = res.data.qyList;
                    this.zbkjList = res.data.zbkjList;
                    this.sbcjList = res.data.sbcjList;
                    this.sblxList = res.data.sblxList;
                    this.pqList = res.data.pqList;
                    this.jmbform.qy = res.data.varList[0].QY;
                    this.publicform.sqbh = res.data.varList[0].SQBH;
                    this.publicform.yhlxr = res.data.varList[0].USERNAME;
                    this.publicform.lxdh = res.data.varList[0].LXRSJH;
                    this.querySbcheck();
                });
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
            //确认勾选
            qrgx_vue(){
                let beginMp = this.filters.beginMp;
                let endMp = this.filters.endMp;
                this.row.forEach((row,index)=>{
                    if(parseInt(beginMp) <= parseInt(row.mph) && parseInt(endMp) >= parseInt(row.mph)){
                        setTimeout(()=>{
                            this.$refs.editable.toggleRowSelection(this.row[index],true)
                        },1)
                    }else{
                        setTimeout(()=>{
                            this.$refs.editable.toggleRowSelection(this.row[index],false)
                        },1)
                    }
                })
            },
            //取消勾选
            qxgx_vue(){
                let row = this.row;
                let beginMp = this.filters.beginMp;
                let endMp = this.filters.endMp;
                this.row.forEach((row,index)=>{
                    if(parseInt(beginMp) <= parseInt(row.mph) && parseInt(endMp) >= parseInt(row.mph)){
                        setTimeout(()=>{
                            this.$refs.editable.toggleRowSelection(this.row[index],false)
                        },1)
                    }else{
                        setTimeout(()=>{
                            this.$refs.editable.toggleRowSelection(this.row[index],true)
                        },1)
                    }
                })
            },
            //进入列编辑界面
            edit_vue(index,row){
                this.jmbFormVisible = true;
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
                    this.jmbform.qy = row.azwzxzq;
                }
                this.jmbform.zbdz = row.azwz;
                this.jmbform.yyzxpq = row.pqbh;
                this.jmbform.xcyjrq = row.yyzx_xcyjrq;
                this.jmbform.zbkj = row.sgdw_sbkj;
                this.jmbform.bsm = row.bsm;
                this.jmbform.azrq = row.sgsj;
                this.jmbform.tsrq = row.tssj;
                this.jmbform.xzsbdd = row.ds;
                this.jmbform.chjbdd = row.ybds;
                this.jmbform.sbcj = row.sbcjbh;
                this.jmbform.sblx = row.sblxbh;
                this.jmbform.gps = row.gps;
                this.jmbform.sgry = row.sgry;
                this.jmbform.sgrq = row.sgryqzsj;
                this.jmbform.yhqr = row.yhqr;
                this.jmbform.yhqrrq = row.yhqrqzsj;
                this.jmbform.sbid = row.id;
            },
            //居民表保存
            jmbedit_vue(){
                this.$refs.jmbform.validate((valid) => {
                    if (valid) {
                        let params = this.jmbform;
                        this.listLoading = true;
                        editWb(params).then(res=>{
                            if(res.data == true){
                                this.$message({
                                    message: '保存成功',
                                    type: 'success'
                                });
                                this.jmbFormVisible = false;
                                this.init_vue();
                                this.listLoading = false;
                            }else{
                                this.$message.error('保存失败！');
                            }
                        });
                    }
                });
            },
            //表内编辑保存
            elxedit_vue(row,column,cell){
                let mc = column.property;
                let params =  Object.assign(row);
                params['mc'] = mc;
                elxedit(params).then(res=>{
                    // console.log(res);
                });
            },
            //公共字段批量编辑界面
            pledit_vue(){
                this.plbjVisible = true;
                this.gxsl = this.sels.length;
                this.pljmbform = {
                    zbdz:'',gss:'',sbkj:'',azrq:'',tsrq:'',sbcj:'',sblx:'',sgry:'',sgrq:'',yhdb:'',yhqrrq:'',
                };
            },
            //批量编辑
            pledit(){
                if(this.gxsl == ''){
                    this.$message({
                        message: '未勾选水表！',
                        type: 'warning'
                    });
                }else{
                    let sbids = "(";
                    for(let i=0;i<this.sels.length;i++){
                        if(i==0){
                            sbids += "'"+ this.sels[i].id + "'";
                        }else{
                            sbids += ",'"+ this.sels[i].id + "'";
                        }
                    }
                    sbids += ")";
                    let params = this.pljmbform;
                    params['ids'] = sbids;
                    this.listLoading = true;
                    pledit(params).then(res=>{
                        if(res == true){
                            this.$message({
                                message: '保存成功',
                                type: 'success'
                            });
                            this.plbjVisible = false;
                            this.init_vue();
                            this.listLoading = false;
                        }else{
                            this.$message.error('保存失败！');
                        }
                    });
                }
            },
            //to下载表格
            xzbg_vue(){
                // 设置当前日期
                let time = new Date();
                let year = time.getFullYear();
                let month = time.getMonth() + 1;
                let day = time.getDate();
                let name = year + "" + month + "" + day;
                /* 从表生成工作簿对象 */
                var wb = XLSX.utils.table_to_book(document.querySelector("#out-table"));
                /* 获取二进制字符串作为输出 */
                var wbout = XLSX.write(wb, {
                    bookType: "xlsx",
                    bookSST: true,
                    type: "array"
                });
                try {
                    FileSaver.saveAs(
                        //Blob 对象表示一个不可变、原始数据的类文件对象。
                        //Blob 表示的不一定是JavaScript原生格式的数据。
                        //File 接口基于Blob，继承了 blob 的功能并将其扩展使其支持用户系统上的文件。
                        //返回一个新创建的 Blob 对象，其内容由参数中给定的数组串联组成。
                        new Blob([wbout], { type: "application/octet-stream" }),
                        //设置导出文件名称
                        "户表施工信息填报"+name+".xlsx"
                    );
                } catch (e) {
                    if (typeof console !== "undefined") console.log(e, wbout);
                }
                return wbout;
            },
            //to导入数据
            drsj_vue(){
                this.dialogImportVisible = true;
            },
            //生成报表
            scbb_vue(){
                // 设置当前日期
                let time = new Date();
                let year = time.getFullYear();
                let month = time.getMonth() + 1;
                let day = time.getDate();
                let name = year + "" + month + "" + day;
                /* 从表生成工作簿对象 */
                var wb = XLSX.utils.table_to_book(document.querySelector("#scbb-table"));
                /* 获取二进制字符串作为输出 */
                var wbout = XLSX.write(wb, {
                    bookType: "xlsx",
                    bookSST: true,
                    type: "array"
                });
                try {
                    FileSaver.saveAs(
                        //Blob 对象表示一个不可变、原始数据的类文件对象。
                        //Blob 表示的不一定是JavaScript原生格式的数据。
                        //File 接口基于Blob，继承了 blob 的功能并将其扩展使其支持用户系统上的文件。
                        //返回一个新创建的 Blob 对象，其内容由参数中给定的数组串联组成。
                        new Blob([wbout], { type: "application/octet-stream" }),
                        //设置导出文件名称
                        "户表验收清单"+name+".xlsx"
                    );
                } catch (e) {
                    if (typeof console !== "undefined") console.log(e, wbout);
                }
                return wbout;
            },
            //出验收表
            cysb_vue(){
                this.printFormVisible = true;//出验收表显示


                this.$nextTick(()=>{
                    var user = sessionStorage.getItem("user");
                    user = JSON.parse(user);

                    document.getElementById("xmmc").innerText = this.xmmc;//项目名称
                    document.getElementById("ldh").innerText = this.row[0].ldbh;//楼栋号
                    document.getElementById("azbm").innerText = '';//安装部门
                    document.getElementById("tsrq").innerText = this.row[0].tssj;//通水日期
                    document.getElementById("jsdw").innerText = this.jsdw;//建设单位
                    document.getElementById("gclx").innerText = this.gclx;//工程类型
                    document.getElementById("xmbh").innerText = this.xmbh;//项目编号
                    document.getElementById("azbz").innerText = '';//安装班组
                    document.getElementById("dyrq").innerText = this.publicUtil.getNowDay();//打印日期
                    document.getElementById("sgdb").innerText = this.row[0].sgry;//施工代表
                    document.getElementById("zbr").innerText = user.name;//制表人
                    document.getElementById("sgdh").innerText = this.zyjdbh;//施工单号
                    document.getElementById("ysr").innerText = this.row[0].yhqr;//验收人
                    document.getElementById("ysrq").innerText = this.row[0].yhqrqzsj;//验收日期
                    document.getElementById("hm").innerText = this.row[0].hh;//户名



                    //把表格代码放到一个变量里面
                    var tab = "<tr>" +
                        "<td width=\"80px\" height=\"30px\" >序号</td>" +
                        "<td width=\"100px\" colspan=1>报装户号</td>" +
                        "<td width=\"100px\" colspan=5>装表地址</td>" +
                        "<td width=\"100px\">分区</td>" +
                        "<td width=\"120px\"  colspan=1>表身码</td>" +
                        "<td width=\"100px\">订正区</td>" +
                        "</tr>";
                    //循环行
                    for(let i=0;i<this.row.length;i++) {
                        if(this.row[i].azwz == null){
                            this.row[i].azwz = "";
                        }
                        if(this.row[i].fq == null){
                            this.row[i].fq = "";
                        }
                        if(this.row[i].bsm == null){
                            this.row[i].bsm = "";
                        }


                        tab += "<tr>";
                        tab += "<td width='80px' height='30px'>"+(i+1)+"</td>"
                        tab += "<td width='100px' colspan=1>"+this.row[i].hh+"</td>"
                        tab += "<td width='100px' colspan=5>"+this.row[i].azwz+"</td>"
                        tab += "<td width='100px'>"+this.row[i].fq+"</td>"
                        tab += "<td width='120px' colspan=1>"+"HF020-"+this.row[i].bsm+"</td>"
                        tab += "<td width='100px'></td>"
                        tab += "</tr>";
                    }
                    //得到table
                    var divv = document.getElementById("tbodyNew");
                    //把table的代码设置到div里面去
                    divv.innerHTML = tab;

/*
                    for (let i=0;i<this.row.length;i++){
                        document.getElementById("").innerHTML = ""

                    }*/




                });




            },
            //保存并退出
            save_vue(){
                let ids = "";
                let count=0;
                for (let i = 0;i<this.sels.length;i++){
                    if(i == 0){
                        ids += this.sels[i].id;
                    }else{
                        ids +=  "," + this.sels[i].id ;
                    }
                    count++;
                }
                let params = {yjdbh:this.zyjdbh,ldsbid:ids,ldid:this.ldid,yjzbid:this.yjzbid};
                this.jmselid = ids;
                this.jmselcounts = count;
                this.$routerTab.close();
                sbyjLdSubmit(params).then(res=>{

                });
            },
            return_vue(){
                this.$routerTab.close();
            },
            selsChange: function (sels) {
                this.sels = sels;
            },
            //查询水表是否默认不可选
            jmcheckboxInit(row,index){
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
            //水表是否默认选中
            querySbcheck(){
                if(this.row != ''){
                    this.row.forEach((row,index)=>{
                        if(row.sbyjdbh == this.zyjdbh){
                            setTimeout(()=>{
                                this.$refs.editable.toggleRowSelection(this.row[index],true)
                            },1)
                        }else{
                            setTimeout(()=>{
                                this.$refs.editable.toggleRowSelection(this.row[index],false)
                            },1)
                        }
                    })
                }
            },
            formatAzrq:function (row, column) {
                let sj = row.sgsj;
                return this.publicUtil.formatChange(sj);
            },
            formatTsrq:function (row, column) {
                let sj = row.tssj;
                return this.publicUtil.formatChange(sj);
            },
            formatSgsj:function (row, column) {
                let sj = row.sgryqzsj;
                return this.publicUtil.formatChange(sj);
            },
            formatYhsj:function (row, column) {
                let sj = row.yhqrqzsj;
                return this.publicUtil.formatChange(sj);
            },
            // 上传文件之前的钩子
            beforeUpload (file) {
                const isText = file.type === 'application/vnd.ms-excel';
                const isTextComputer = file.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet';
                return (isText | isTextComputer)
            },
            // 上传文件个数超过定义的数量
            handleExceed (files, fileList) {
                this.$message.warning(`当前限制选择 1 个文件，请删除后继续上传`)
            },
            handleRemove(file, fileList) {
            },
            handlePreview(file) {
            },
            handleChange(file, fileList) {
                this.fileList = fileList;
            },
            // 上传文件
            uploadFile (item) {
                const fileObj = item.file;
                // FormData 对象
                const form = new FormData();
                // 文件对象
                form.append('multipartFiles', fileObj);
                form.append('comId', this.comId);
                // console.log(JSON.stringify(form));
                this.listLoading = true;
                importExcel(form).then((res) => {
                    if(res.msg=='文件类型错误'){
                        this.$message.error('文件类型错误，请上传Excel！');
                    }else if(res.msg=='文件为空，请重新上传'){
                        this.$message.error('文件为空，请重新上传！');
                    }else if(res.msg=='上传成功'){
                        this.$message({
                            message: '编辑成功',
                            type: 'success'
                        });
                    }else{
                        this.$message.error('上传失败！');
                    }
                    this.fileList=[];
                    this.init_vue();
                    this.listLoading = false;
                });
            }
        }
    }
</script>

<style scoped>
    .border-table {
        /*border-collapse: collapse;*/
       /* border: none;
        width: 100%;*/
    }
    .border-table td {
        border: solid #000 1px;
    }

</style>