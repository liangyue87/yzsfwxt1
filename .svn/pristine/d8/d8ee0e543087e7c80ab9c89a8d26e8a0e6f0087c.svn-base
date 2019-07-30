<!--项目基本信息模板界面-->
<template>
	<section>
		<el-row style="margin-top: 15px;">
			<el-col :span="24" >
					<el-row>
                        <el-col :span="5">
                            <el-tree :data="treedata" :props="defaultProps" :default-expand-all="true" @node-click="handleNodeClick"></el-tree>
                        </el-col>
                        <el-col :span="19">
                            <div class="text item">
                                <el-button v-if="this.qb != '0'&&isxmtc!='2'" style="margin-bottom: 10px;" size="mini" type="primary" @click="dbpzAdd_vue()"><i class="iconfont icon-xinzeng"></i>批量维护楼栋户表信息</el-button>
                                <el-button v-if="sqlx=='30' && this.qb != '0'&&isxmtc!='2'" style="margin-bottom: 10px;" size="mini" type="success" @click="download_vue()"><i class="iconfont icon-xiazai_f" ></i>下载表格</el-button>
                                <el-upload
                                        style="display: inline; margin-left: 10px;margin-right: 10px;"
                                        :show-file-list='false'
                                        :before-upload="beforeUpload"
                                        action='#'
                                        :file-list="fileList"
                                        :on-exceed="handleExceed"
                                        :on-change="handleChange"
                                        :on-remove="handleRemove"
                                        :on-preview="handlePreview"
                                        :http-request="uploadFile" >
                                    <el-button  size="mini" type="primary" v-if="this.qb != '0' && sqlx=='30'&&isxmtc!='2'"><i class="iconfont icon-daoru"></i>导入数据</el-button>
                                </el-upload>
                                <el-table :data="Data" style="width: 100%" border>
                                    <el-table-column
                                            prop="hh"
                                            label="报装户号"
                                            align="center">
                                    </el-table-column>
                                    <el-table-column
                                            prop="hm"
                                            label="户名"
                                            align="center">
                                    </el-table-column>
                                    <el-table-column
                                            prop="mph"
                                            label="门牌号"
                                            align="center"
                                            width="80">
                                        <template slot-scope="scope">
                                            <a href="#" @click="mphHandle(scope.row)" style="color: #20a0ff">{{scope.row.mph}}</a>

                                        </template>
                                    </el-table-column>
                                    <el-table-column
                                            prop="dz"
                                            label="用户地址"
                                            align="center"
                                            :formatter="dzFormatter">
                                    </el-table-column>
                                    <el-table-column
                                            prop="kjname"
                                            label="口径"
                                            align="center">
                                    </el-table-column>
                                    <el-table-column
                                            prop="ysxzname"
                                            label="用水性质"
                                            align="center">
                                    </el-table-column>
                                    <el-table-column
                                            prop="hyflname"
                                            label="行业分类"
                                            align="center">
                                    </el-table-column>
                                    <el-table-column
                                            prop="jffsname"
                                            label="交费方式"
                                            align="center">
                                    </el-table-column><!--v-if="this.qb != '0'"-->
                                    <el-table-column  label="操作" align="center" width="150" >
                                        <template slot-scope="scope">
                                            <label title="编辑"><i @click="dbpzHandle_vue(scope.$index, scope.row)" class="iconfont icon-ai-edit" style="font-size:16px;cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                                            <label title="删除" v-if="isxmtc!='2'"><i @click="dbpzDelete_vue(scope.$index, scope.row)" class="iconfont delete" style="font-size:18px;cursor:pointer;padding: 2px;color: #F56C6C"></i></label>
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
                            <div style="display: none">
                                <el-table id="out-table" :data="AllData" style="width: 100%" border>
                                    <el-table-column prop="hh" label="报装户号" align="center"></el-table-column>
                                    <el-table-column prop="ldbh" label="楼栋范围" align="center"></el-table-column>
                                    <el-table-column prop="hm" label="户名" align="center"></el-table-column>
                                    <el-table-column prop="" label="门牌号" align="center" width="80"></el-table-column>
                                    <el-table-column prop="" label="身份证号" align="center" width="80"></el-table-column>
                                    <el-table-column prop="" label="联系方式" align="center" width="80"></el-table-column>
                                    <el-table-column prop="dz" label="用户地址" align="center" :formatter="dzFormatter"></el-table-column>
                                    <el-table-column prop="kjname" label="口径" align="center"></el-table-column>
                                    <el-table-column prop="ysxzname" label="用水性质" align="center"></el-table-column>
                                    <el-table-column prop="hyflname" label="行业分类" align="center"></el-table-column>
                                    <el-table-column prop="jffsname" label="交费方式" align="center"></el-table-column>
                                </el-table>
                            </div>
                        </el-col>
					</el-row>
                <el-dialog title="门牌号信息" :visible.sync="pmhDialogFormVisible" width="20%" :close-on-click-modal="false">
                    <el-form :model="mphForm">
                        <el-form-item label="门牌号" :label-position="labelPosition" style="margin-bottom: 15px" size="small" :label-width="formLabelWidth" prop="mph" >
                            <el-input v-model="mphForm.mph" ></el-input>
                        </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="pmhDialogFormVisible = false" size="mini" v-if="isxmtc!='2'">取 消</el-button>
                        <el-button type="primary" @click="mphHandel_vue()" size="mini" v-if="isxmtc!='2'">确 定</el-button>
                    </div>
                </el-dialog>
                <el-dialog title="居民表详细信息"  :visible.sync="dbpzDialogFormVisible" width="55%" top="10vh" :close-on-click-modal="false">
                    <el-tabs type="card" v-model="tabJmb">
                        <el-tab-pane label="新建小区" name="xjxq" v-if="sqlx!='30'">
                            <el-form :model="dbpzForm"  ref="dbpzForm" :rules="rules">
                                <el-input style="display: none" :model="dbpzForm.id"></el-input>
                                <el-row>
                                    <el-col :span="12">
                                        <el-form-item label="户名" :label-position="labelPosition" :label-width="formLabelWidth" prop="hm" style="margin-bottom: 15px" size="small">
                                            <el-input v-model="dbpzForm.hm" style="width: 200px;"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="12">
                                        <el-form-item label="口径" :label-position="labelPosition" :label-width="formLabelWidth" prop="kj" style="margin-bottom: 15px" size="small">
                                            <el-select v-model="dbpzForm.kjbh" style="width: 200px;">
                                                <el-option
                                                        v-for="item in kjOptions"
                                                        :key="item.codevalue"
                                                        :label="item.codename"
                                                        :value="item.codevalue">
                                                </el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                </el-row>

                                <el-row>
                                    <el-col :span="24">
                                        <el-form-item label="装表地址" :label-position="labelPosition" :label-width="formLabelWidth" prop="dz" style="margin-bottom: 15px" size="small">
                                            <el-input v-model="dbpzForm.dz" readonly></el-input>
                                            <div style="font-size: 12px;color:#66b1ff">[楼号]，[门牌]是系统变量，请完整保留括号及文字，系统将自动使用具体的楼号和门牌号替换变量</div>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="12">
                                        <el-form-item label="用水性质" :label-position="labelPosition" :label-width="formLabelWidth" prop="ysxz"style="margin-bottom: 15px" size="small" >
                                            <!--<el-select v-model="dbpzForm.ysxzbh" style="width: 200px;" @change="getHyflData_vue">-->
                                            <el-select v-model="dbpzForm.ysxzbh" style="width: 200px;">
                                                <el-option
                                                        v-for="item in ysxzOptions"
                                                        :key="item.codevalue"
                                                        :label="item.codename"
                                                        :value="item.codevalue">
                                                </el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="12">
                                        <el-form-item label="行业分类" :label-position="labelPosition" :label-width="formLabelWidth" prop="hyfl"style="margin-bottom: 15px" size="small" >
                                            <!--<el-select v-model="dbpzForm.hyflbh" style="width: 200px;"@change="change()">-->
                                            <el-select v-model="dbpzForm.hyflbh" style="width: 200px;">
                                                <el-option
                                                        v-for="item in hyflOptions"
                                                        :key="item.codevalue"
                                                        :label="item.codename"
                                                        :value="item.codevalue">
                                                </el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="24">
                                        <el-form-item label="交费方式" :label-position="labelPosition" :label-width="formLabelWidth" prop="hyfl" style="margin-bottom: 15px" size="small" >
                                            <el-select v-model="dbpzForm.jffsbh" style="width: 200px;" @change="getShow">
                                                <el-option
                                                        v-for="item in jffsOptions"
                                                        :key="item.codevalue"
                                                        :label="item.codename"
                                                        :value="item.codevalue">
                                                </el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="12">
                                        <el-form-item label="账户名" :label-position="labelPosition" :label-width="formLabelWidth" prop="khmc" v-if="isOrNotShow" style="margin-bottom: 15px" size="small">
                                            <el-input v-model="dbpzForm.khmc" style="width: 200px;"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="12">
                                        <el-form-item label="账号" :label-position="labelPosition" :label-width="formLabelWidth" prop="yhzh" v-if="isOrNotShow" style="margin-bottom: 15px" size="small">
                                            <el-input v-model="dbpzForm.yhzh" style="width: 200px;"></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="12">
                                        <el-form-item label="开户行" :label-position="labelPosition" :label-width="formLabelWidth" prop="khyh" v-if="isOrNotShow" style="margin-bottom: 15px" size="small">
                                            <el-select v-model="dbpzForm.khzh" style="width: 200px;" @change="getKhyhData_vue">
                                                <el-option
                                                        v-for="item in khzhOptions"
                                                        :key="item.codevalue"
                                                        :label="item.codename"
                                                        :value="item.codevalue">
                                                </el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="12">
                                        <el-form-item label=" " :label-position="labelPosition" :label-width="formLabelWidth" prop="khyh" v-if="isOrNotShow" style="margin-bottom: 15px" size="small">
                                            <el-select v-model="dbpzForm.khyh" style="width: 200px;"@change="change()">
                                                <el-option
                                                        v-for="item in khyhOptions"
                                                        :key="item.codevalue"
                                                        :label="item.codename"
                                                        :value="item.codevalue">
                                                </el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="24">
                                        <el-form-item label="建户方式" :label-position="labelPosition" :label-width="formLabelWidth" v-if="isOrNotShowMph" style="margin-bottom: 15px" size="small">
                                            <el-row>
                                                <el-col :span="24">
                                                    <el-radio-group v-model="dbpzForm.jhfs" border="1" @change="initJhfs()">
                                                        <el-row>
                                                            <el-col :span="10"><el-radio label="1">按户型逐层建立居民表</el-radio></el-col>
                                                            <el-col :span="14"><el-radio label="2">以逗号分隔门牌号任意添加</el-radio></el-col>
                                                        </el-row>
                                                        <el-row style="margin-top: 5px">
                                                            <el-col :span="10"><el-radio label="3">按层逐户型建立居民表</el-radio></el-col>
                                                            <el-col :span="14"><el-radio label="4">从EXCEL中复制粘贴门牌号至下框每行一个门牌号</el-radio></el-col>
                                                        </el-row>
                                                        <el-row  style="margin-top: 5px"><el-col :span="24"><el-radio label="5">按逐户型逐层建立居民表</el-radio></el-col></el-row>
                                                    </el-radio-group>
                                                    <el-row v-if="dbpzForm.jhfs=='1'"  style="margin-top: 5px">
                                                        <el-col :span="3" style="text-align: right">
                                                            <span>户型号：</span>
                                                        </el-col>
                                                        <el-col :span="2">
                                                            <el-input  v-model="dbpzForm.hxh" size="small"></el-input>
                                                        </el-col>
                                                        <el-col :span="2" style="text-align: right">
                                                            <span>楼层：</span>
                                                        </el-col>
                                                        <el-col :span="2">
                                                            <el-input  v-model.number="dbpzForm.lcstart" size="small"></el-input>
                                                        </el-col>
                                                        <el-col :span="2" style="text-align: center">
                                                            <span>至</span>
                                                        </el-col>
                                                        <el-col :span="2">
                                                            <el-input  v-model.number="dbpzForm.lcend" size="small"></el-input>
                                                        </el-col>
                                                        <el-col :span="4" style="text-align: right">
                                                            <el-button  size="mini"  @click="process1()" type="primary"><i class="iconfont icon-shengcheng"></i>生成</el-button>
                                                        </el-col>
                                                    </el-row>
                                                    <el-row v-if="dbpzForm.jhfs=='3'" style="margin-top: 5px">
                                                        <el-col :span="2" style="text-align: right">
                                                            <span>楼层：</span>
                                                        </el-col>
                                                        <el-col :span="2">
                                                            <el-input  v-model="dbpzForm.lc" size="small"></el-input>
                                                        </el-col>
                                                        <el-col :span="3" style="text-align: right">
                                                            <span>户型号：</span>
                                                        </el-col>
                                                        <el-col :span="2">
                                                            <el-input  v-model.number="dbpzForm.hxstart" size="small"></el-input>
                                                        </el-col>
                                                        <el-col :span="2" style="text-align: center">
                                                            <span>至</span>
                                                        </el-col>
                                                        <el-col :span="2">
                                                            <el-input  v-model.number="dbpzForm.hxend" size="small"></el-input>
                                                        </el-col>
                                                        <el-col :span="4" style="text-align: right">
                                                            <span>户型位数：</span>
                                                        </el-col>
                                                        <el-col :span="2">
                                                            <el-input  v-model.number="dbpzForm.hxws" size="small"></el-input>
                                                        </el-col>

                                                        <el-col :span="4" style="text-align: right">
                                                            <el-button  size="mini"  @click="process2()" type="primary"><i class="iconfont icon-shengcheng"></i>生成</el-button>
                                                        </el-col>
                                                    </el-row>
                                                    <el-row v-if="dbpzForm.jhfs=='5'" style="margin-top: 5px">
                                                        <el-col :span="2" style="text-align: right">
                                                            <span>楼层：</span>
                                                        </el-col>
                                                        <el-col :span="2">
                                                            <el-input  v-model.number="dbpzForm.lcstart" size="small"></el-input>
                                                        </el-col>
                                                        <el-col :span="2" style="text-align: center">
                                                            <span>至</span>
                                                        </el-col>
                                                        <el-col :span="2">
                                                            <el-input  v-model.number="dbpzForm.lcend" size="small"></el-input>
                                                        </el-col>
                                                        <el-col :span="2" style="text-align: right">
                                                            <span>户型号：</span>
                                                        </el-col>
                                                        <el-col :span="2">
                                                            <el-input  v-model.number="dbpzForm.hxstart" size="small"></el-input>
                                                        </el-col>
                                                        <el-col :span="2" style="text-align: center">
                                                            <span>至</span>
                                                        </el-col>
                                                        <el-col :span="2">
                                                            <el-input  v-model.number="dbpzForm.hxend" size="small"></el-input>
                                                        </el-col>
                                                        <el-col :span="3" style="text-align: right">
                                                            <span>户型位数：</span>
                                                        </el-col>
                                                        <el-col :span="2">
                                                            <el-input  v-model.number="dbpzForm.hxws" size="small"></el-input>
                                                        </el-col>
                                                        <el-col :span="3" style="text-align: right">
                                                            <el-button  size="mini"  @click="process3()" type="primary"><i class="iconfont icon-shengcheng"></i>生成</el-button>
                                                        </el-col>
                                                    </el-row>
                                                </el-col>
                                            </el-row>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="24">
                                        <el-form-item label="门牌号" :label-position="labelPosition" prop="mph" :label-width="formLabelWidth" v-if="isOrNotShowMph">
                                            <el-input type="textarea" v-model="dbpzForm.mph" ></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>


                            </el-form>
                        </el-tab-pane >
                        <el-tab-pane label="出户改造" name="chgz" v-if="sqlx=='30'">
                            <el-form :model="drpzForm" ref="drpzForm" :rules="dfRules">
                                <el-row v-if="drpzForm.id!=''">
                                    <el-col :span="12">
                                        <el-form-item label="户名" :label-position="labelPosition" :label-width="formLabelWidth" prop="hm" style="margin-bottom: 15px" size="small">
                                            <el-input v-model="drpzForm.hm" style="width: 200px;"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="12">
                                        <el-form-item label="门牌号" :label-position="labelPosition" :label-width="formLabelWidth" prop="mph" style="margin-bottom: 15px" size="small">
                                            <el-input v-model="drpzForm.mph" style="width: 200px;"></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row v-if="drpzForm.id!=''">
                                    <el-col :span="12">
                                        <el-form-item label="身份证号" :label-position="labelPosition" :label-width="formLabelWidth" prop="sfzh" style="margin-bottom: 15px" size="small">
                                            <el-input v-model="drpzForm.sfzh" style="width: 200px;"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="12">
                                        <el-form-item label="联系方式" :label-position="labelPosition" :label-width="formLabelWidth" prop="lxdh" style="margin-bottom: 15px" size="small">
                                            <el-input v-model="drpzForm.lxdh" style="width: 200px;"></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="12">
                                        <el-form-item label="口径" :label-position="labelPosition" :label-width="formLabelWidth" prop="kjbh" style="margin-bottom: 15px" size="small">
                                            <el-select v-model="drpzForm.kjbh" style="width: 200px;">
                                                <el-option
                                                        v-for="item in kjOptions"
                                                        :key="item.codevalue"
                                                        :label="item.codename"
                                                        :value="item.codevalue">
                                                </el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="12" v-if="drpzForm.id==''">
                                        <el-form-item label="数量" :label-position="labelPosition" :label-width="formLabelWidth" prop="counts" style="margin-bottom: 15px" size="small">
                                            <el-input v-model="drpzForm.counts" style="width: 200px;"></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="24">
                                        <el-form-item label="装表地址" :label-position="labelPosition" :label-width="formLabelWidth" prop="dz" style="margin-bottom: 15px" size="small">
                                            <el-input v-model="drpzForm.dz" ></el-input>
                                            <div style="font-size: 12px;color:#66b1ff">[楼号]，[门牌]是系统变量，请完整保留括号及文字，系统将自动使用具体的楼号和门牌号替换变量</div>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="12">
                                        <el-form-item label="用水性质" :label-position="labelPosition" :label-width="formLabelWidth" prop="ysxz"style="margin-bottom: 15px" size="small" >
                                            <!--<el-select v-model="drpzForm.ysxzbh" style="width: 200px;" @change="getHgkHyflData_vue">-->
                                            <el-select v-model="drpzForm.ysxzbh" style="width: 200px;">
                                                <el-option
                                                        v-for="item in ysxzOptions"
                                                        :key="item.codevalue"
                                                        :label="item.codename"
                                                        :value="item.codevalue">
                                                </el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="12">
                                        <el-form-item label="行业分类" :label-position="labelPosition" :label-width="formLabelWidth" prop="hyfl"style="margin-bottom: 15px" size="small" >
                                            <!--<el-select v-model="drpzForm.hyflbh" style="width: 200px;" @change="change()">-->
                                            <el-select v-model="drpzForm.hyflbh" style="width: 200px;" >
                                                <el-option
                                                        v-for="item in hyflOptions"
                                                        :key="item.codevalue"
                                                        :label="item.codename"
                                                        :value="item.codevalue">
                                                </el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="24">
                                        <el-form-item label="交费方式" :label-position="labelPosition" :label-width="formLabelWidth" prop="hyfl" style="margin-bottom: 15px" size="small" >
                                            <el-select v-model="drpzForm.jffsbh" style="width: 200px;" @change="getShow">
                                                <el-option
                                                        v-for="item in jffsOptions"
                                                        :key="item.codevalue"
                                                        :label="item.codename"
                                                        :value="item.codevalue">
                                                </el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="12">
                                        <el-form-item label="账户名" :label-position="labelPosition" :label-width="formLabelWidth" prop="khmc" v-if="isOrNotShow" style="margin-bottom: 15px" size="small">
                                            <el-input v-model="drpzForm.khmc" style="width: 200px;"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="12">
                                        <el-form-item label="账号" :label-position="labelPosition" :label-width="formLabelWidth" prop="yhzh" v-if="isOrNotShow" style="margin-bottom: 15px" size="small">
                                            <el-input v-model="drpzForm.yhzh" style="width: 200px;"></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="12">
                                        <el-form-item label="开户行" :label-position="labelPosition" :label-width="formLabelWidth" prop="khyh" v-if="isOrNotShow" style="margin-bottom: 15px" size="small">
                                            <el-select v-model="drpzForm.khzh" style="width: 200px;" @change="getKhyhData_vue">
                                                <el-option
                                                        v-for="item in khzhOptions"
                                                        :key="item.codevalue"
                                                        :label="item.codename"
                                                        :value="item.codevalue">
                                                </el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="12">
                                        <el-form-item label=" " :label-position="labelPosition" :label-width="formLabelWidth" prop="khyh" v-if="isOrNotShow" style="margin-bottom: 15px" size="small">
                                            <el-select v-model="drpzForm.khyh" style="width: 200px;">
                                                <el-option
                                                        v-for="item in khyhOptions"
                                                        :key="item.codevalue"
                                                        :label="item.codename"
                                                        :value="item.codevalue">
                                                </el-option>
                                            </el-select>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                            </el-form>
                        </el-tab-pane>
                    </el-tabs>
                    <div slot="footer" class="dialog-footer" v-if="this.tabJmb == 'xjxq'">
                        <el-button @click="dbpzDialogFormVisible = false" size="mini" type="info" v-if="isxmtc!='2'"><i class="iconfont icon-quxiao"></i>取 消</el-button>
                        <el-button type="primary" @click="dbxq_vue('dbpzForm')" size="mini" v-if="isxmtc!='2'"><i class="iconfont icon-queren"></i>确 定</el-button>
                    </div>
                    <div slot="footer" class="dialog-footer" v-else>
                        <el-button @click="dbpzDialogFormVisible = false" size="mini" type="info" v-if="isxmtc!='2'"><i class="iconfont icon-quxiao"></i>取 消</el-button>
                        <el-button type="primary" @click="drxq_vue('drpzForm')" size="mini" v-if="isxmtc!='2'"><i class="iconfont icon-queren"></i>确 定</el-button>
                    </div>
                </el-dialog>
			</el-col>
		</el-row>
	</section>
</template>

<script>
    import {initLdhb,getKjData,getCodeData,getKhzhData,getKhyhData,dbxq,initDbpzTable,dbpzDelete,dbpzAdd,mphHandel,drxq,queryAllLdsb,importExcel,getHyflCodeData,getXmsqlx,getYsxzData,getHyflData} from '../../api/sjst';
    import FileSaver from "file-saver";
    import XLSX from "xlsx";
    export default {
        props:{
            xmid:{
                type: String,
                required: true
            },
            qb:{
                required: false
            },
            isxmtc:{
                type: String,
                required: true
            }
        },
			data() {
			return {
			    sqlx:"",
			    /*出户改造科导入导出EXCEL*/
                tabJmb:"",
                drpzForm:{
                    hm:"",
                    id:"",
                    mph:"",
                    sfzh:"",
                    lxdh:"",
                    kjbh:'',
                    counts:1,
                    dz:"",
                    ysxzbh:"",
                    hyflbh:"",
                    jffsbh:"XJ",
                    khzh:"",
                    khmc:"",
                    yhzh:"",
                    khyh:"",
                },
                dfRules:{
                    kjbh:[
                        { required: true, message: '该字段不可为空', trigger: 'blur' }
                    ],
                    counts:[
                        { required: true, message: '该字段不可为空', trigger: 'blur' }
                    ],
                },
                AllData:[],
                ldids:'',
                fileList:[],

                rules:{
                    hm:[
                        { required: true, message: '该字段不可为空', trigger: 'blur' }
                    ],
                    dz:[
                        { required: true, message: '该字段不可为空', trigger: 'blur' }
                    ],
                    khmc:[
                        { required: true, message: '该字段不可为空', trigger: 'blur' }
                    ],
                    yhzh:[
                        { required: true, message: '该字段不可为空', trigger: 'blur' }
                    ],
                    mph:[
                        { required: true, message: '该字段不可为空', trigger: 'blur' }
                    ],},
                total: 0,//表格数据总数量
                page: 1,//当前页码
                size: 5,//每页容量
                isOrNotShow:false,
                formLabelWidth:"100px",
                labelPosition: 'right',
                Data:[],
                khzhOptions:[],
                khyhOptions:[],
                hyflOptions:[],
                ysxzOptions:[],
                kjOptions:[],
                jffsOptions:[],
                treedata: [{
					label: '楼栋居民表',
					children: [],
				}],
				defaultProps: {
					children: 'children',
					label: 'label'
				},
                formLabelWidth:"100px",
                //xmid:this.$route.query.xmid
				//xmid:"1",
                ldid:"",
                dbpzDialogFormVisible:false,
                isOrNotShowMph:false,
                dbpzForm:{
                    id:"",
                    bzlb:1,
                    hm:"",
                    hh:"",
                    kjbh:"",
                    dz:"地址[楼号]#[门牌]室",
                    ysxzbh:"",
                    hyflbh:"",
                    jffsbh:"",
                    khzh:"",
                    khmc:"",
                    yhzh:"",
                    khyh:"",
                    jhfs:"",
                    mph:"",
                    hxh:null,
                    lcstart:null,
                    lcend:null,
                    lc:null,
                    hxstart:null,
                    hxend:null,
                    hxws:null
                },
                pmhDialogFormVisible:false,
                mphForm:{
                    mph:"",
                    id:""
                }
			}
		},

		methods: {
            getXmsqlx_vue(){
              let params={
                xmid:this.xmid
              }
                getXmsqlx(params).then((res)=>{
                    this.sqlx=res.data.row[0].SQLX;
                    if(this.sqlx=='30'){
                        this.tabJmb='chgz';
                    }else{
                        this.tabJmb='xjxq';
                    }
                })
            },
            mphHandel_vue(){
                let params={
                    mph:this.mphForm.mph,
                    id:this.mphForm.id
                }
                mphHandel(params).then((res)=>{
                    if(res.data){
                        this.initDbpzTable_vue(this.ldid)
                    }
                    this.pmhDialogFormVisible=false;
                })
            },
            change(){
                this.$forceUpdate()
            },
            mphHandle(row){
                this.mphForm.mph=row.mph;
                this.mphForm.id=row.id;
                this.pmhDialogFormVisible=true;
            },
            dzFormatter(row, column){
                if(row.sqlx!='30'){
                    if(row.mph == null){
                        return '';
                    }else{
                        return row.dz.replace("[楼号]",row.ldbh).replace("[门牌]",row.mph);
                    }
                }else{
                    return row.dz;
                }
            },
			handleNodeClick(data) {
                this.ldid = data.id;
                this.page = 1;
                this.initDbpzTable_vue(data.id);
			},
            //初始化左边树
            initLdhb_vue(){
                let params = {
                    xmid:this.xmid
                };
                this.ldids = "";
                initLdhb(params).then((res)=>{
                    if(res.data!=null){
                        this.treedata[0].children=res.data.row;
                        let list = res.data.row;
                        if(list.length>0){
                            this.ldids = "(";
                            list.forEach((res,index)=>{
                                if(index == 0){
                                    this.ldids += "'" + res.id + "'";
                                }else{
                                    this.ldids += ",'" + res.id + "'";
                                }
                            });
                            this.ldids += ")";
                        }
                    }
                })
            },
            //获取水表口径
            getKjData_vue(){
                let params={};
                getKjData(params).then((res)=>{
                    if(res.data!=null){
                        this.dbpzForm.kjbh = res.data.row[0].codevalue;
                        this.kjOptions = res.data.row;
                    }
                })
            },
            // //获取用水性质
            // getYsxzData_vue(){
            //     let params={
            //         type:'ysxz'
            //     }
            //     getCodeData(params).then((res)=>{
            //         if(res.data!=null){
            //             this.dbpzForm.ysxzbh = res.data.row[0].codevalue
            //             this.ysxzOptions = res.data.row;
            //         }
            //     })
            // },
            // //获取行业分类
            // getHyflData_vue(){
            //     let params={
            //         type:'hyfl'
            //     }
            //     getCodeData(params).then((res)=>{
            //         if(res.data!=null){
            //             this.dbpzForm.hyflbh = res.data.row[0].codevalue
            //             this.hyflOptions = res.data.row;
            //         }
            //     })
            // },
            //获取用水性质
            // getYsxzData_vue(val,hyflval){
            //     let params={
            //         type:'ysxz'
            //     }
            //     getCodeData(params).then((res)=>{
            //         if(res.data!=null){
            //             if(val==""&&val==null||val==undefined){
            //                 this.dbpzForm.ysxzbh = res.data.row[0].id;
            //                 this.drpzForm.ysxzbh = res.data.row[0].id;
            //             }else{
            //                 this.dbpzForm.ysxzbh = val;
            //                 this.drpzForm.ysxzbh = val;
            //             }
            //             this.getHyflData_vue(this.dbpzForm.ysxzbh,hyflval);
            //             this.getHgkHyflData_vue(this.dbpzForm.ysxzbh,hyflval);
            //             this.ysxzOptions = res.data.row;
            //         }
            //     })
            // },
            // getHgkHyflData_vue(parentid,val){
            //     let params={
            //         parentid:parentid
            //     }
            //     getHyflCodeData(params).then((res)=>{
            //         if(res.data!=null){
            //             if(res.data.row.length>0){
            //                 if(val==""||val==null||val==undefined){
            //                     this.drpzForm.hyflbh = res.data.row[0].codevalue
            //                 }else{
            //                     this.drpzForm.hyflbh = val;
            //                 }
            //                 this.hyflOptions = res.data.row;
            //             }else{
            //                 this.drpzForm.hyflbh = "";
            //                 this.hyflOptions = [];
            //             }
            //
            //         }
            //     })
            // },
            getYsxzData_vue(){
                getYsxzData().then((res)=>{
                    if(res.data!=null){
                        this.ysxzOptions = res.data.row;
                        this.dbpzForm.ysxzbh = res.data.row[0].codevalue;
                        this.drpzForm.ysxzbh = res.data.row[0].codevalue;
                    }
                })
            },
            //获取行业分类
            getHyflData_vue(){
                getHyflData().then((res)=>{
                    if(res.data!=null){
                        this.hyflOptions = res.data.row;
                        this.dbpzForm.hyflbh = res.data.row[0].codevalue;
                        this.drpzForm.hyflbh = res.data.row[0].codevalue;
                    }
                })
            },
            // //获取行业分类
            // getHyflData_vue(parentid,val){
            //     let params={
            //         parentid:parentid
            //     }
            //     getHyflCodeData(params).then((res)=>{
            //         if(res.data!=null){
            //             if(res.data.row.length>0){
            //                 if(val==""||val==null||val==undefined){
            //                     this.dbpzForm.hyflbh = res.data.row[0].codevalue
            //                 }else{
            //                     this.dbpzForm.hyflbh = val;
            //                 }
            //                 this.hyflOptions = res.data.row;
            //             }else{
            //                 this.dbpzForm.hyflbh = "";
            //                 this.hyflOptions = [];
            //             }
            //
            //         }
            //     })
            // },
            //获取交费方式
            getjffsData_vue(){
                let params={
                    type:'jffs'
                }
                getCodeData(params).then((res)=>{
                    if(res.data!=null){
                        this.jffsOptions = res.data.row;
                        this.dbpzForm.jffsbh = 'XJ'
                        if(this.dbpzForm.jffsbh=='XJ'){
                            this.isOrNotShow=false;
                        }else{
                            this.isOrNotShow=true;
                        }
                    }
                })
            },
            //获取开户总行
            getKhzhData_vue(val,khyhval){
                let params={}
                getKhzhData(params).then((res)=>{
                    if(res.data!=null){
                        if(val==""&&val==null||val==undefined){
                            this.dbpzForm.khzh = res.data.row[0].codevalue;
                            this.drpzForm.khzh = res.data.row[0].codevalue;
                        }else{
                            this.dbpzForm.khzh = val;
                            this.drpzForm.khzh = val;
                        }
                        this.khzhOptions = res.data.row;
                        this.getKhyhData_vue(this.dbpzForm.khzh,khyhval);
                    }
                })
            },
            //获取开户银行
            getKhyhData_vue(value,khyhval){
                let params = {type:value}
                getKhyhData(params).then((res)=>{
                    if(res.data!=null){
                        if(khyhval==""&&khyhval==null||khyhval==undefined){
                            this.dbpzForm.khyh = res.data.row[0].codevalue;
                            this.drpzForm.khyh = res.data.row[0].codevalue;
                        }else{
                            this.dbpzForm.khyh = khyhval;
                            this.drpzForm.khyh = khyhval;
                        }
                        this.khyhOptions = res.data.row;
                    }
                })
            },
            // //获取开户总行
            // getKhzhData_vue(){
            //     let params={}
            //     getKhzhData(params).then((res)=>{
            //         if(res.data!=null){
            //             this.dbpzForm.khzh = res.data.row[0].codevalue
            //             this.khzhOptions = res.data.row;
            //             this.getKhyhData_vue(res.data.row[0].codevalue);
            //         }
            //     })
            // },
            // //获取开户银行
            // getKhyhData_vue(value){
            //     let params = {type:value}
            //     getKhyhData(params).then((res)=>{
            //         if(res.data!=null){
            //             this.dbpzForm.khyh = res.data.row[0].codevalue
            //             this.khyhOptions = res.data.row;
            //         }
            //     })
            // },
            getShow(value){
                if(value=='XJ'){
                    this.isOrNotShow=false;
                }else{
                    this.isOrNotShow=true;
                }
            },

            dbxq_vue(formName){
                // if(this.getForm()){
                //     return;
                // }
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let params={}
                        if(this.isOrNotShowMph){
                            params = {
                                hhlxbh:'HB',
                                xmId:this.xmid,
                                ldid:this.ldid,
                                zt:0
                            }
                        }else{
                            params = {
                                hhlxbh:'HB',
                                xmId:this.xmid,
                                zt:0
                            }
                        }
                        params = Object.assign(params,this.dbpzForm);
                        dbxq(params).then((res)=>{
                            if(res){
                                this.$message.success('保存成功！');
                                this.initLdhb_vue();
                                this.dbpzDialogFormVisible=false;
                                this.initDbpzTable_vue(this.ldid);
                            }else{
                                this.$message.error('保存失败！');
                            }
                        })
                    } else {
                        return false;
                    }
                });
            },
            //出户改造的水表信息保存
            drxq_vue(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let params = Object.assign({hhlxbh:'HB',xmId:this.xmid,ldid:this.ldid,zt:0,bzlb:1},this.drpzForm);
                        drxq(params).then((res)=>{
                            if(res){
                                this.$message.success('保存成功！');
                                this.initLdhb_vue();
                                this.dbpzDialogFormVisible=false;
                                this.initDbpzTable_vue(this.ldid);
                            }else{
                                this.$message.error('保存失败！');
                            }
                        })
                    }
                });
            },
            //初始化地表配置
            initDbpzTable_vue(ldid){
                let params={
                    ldid:ldid,
                    xmId:this.xmid,
                    page:this.page,
                    size:this.size,
                    hhlxbh:'HB',
                }
                initDbpzTable(params).then((res)=>{
                    if(res.data!=null){
                        if(res.data.total>0&&res.data.row.length<=0&&this.page>1){
                            this.page=this.page-1
                            this.initDbpzTable_vue(res.data.ldid);
                        }
                        this.Data = res.data.row;
                        this.total=res.data.total;
                    }
                })
            },
            handleCurrentChange(val) {
                this.page = val;
                this.initDbpzTable_vue(this.ldid);
            },
            handleSizeChange(val) {
                this.size = val;
                this.page = 1;
                this.initDbpzTable_vue(this.ldid);
            },
            clearForm(){
                // this.getYsxzData_vue();
                this.getKhzhData_vue();
                this.dbpzForm={
                        bzlb:1,
                        hm:"",
                        hh:"",
                        kjbh:this.kjOptions[0].codevalue,
                        ysxzbh:this.ysxzOptions[0].codevalue,
                        hyflbh:this.hyflOptions[0].codevalue,
                        dz:"地址[楼号]#[门牌]室",
                        jffsbh:"XJ",
                        khmc:"",
                        yhzh:"",
                        jhfs:"",
                        mph:"",
                        hxh:null,
                        lcstart:null,
                        lcend:null,
                        lc:null,
                        hxstart:null,
                        hxend:null,
                        hxws:null
                };
                this.drpzForm={
                        id:"",
                        hm:"",
                        mph:"",
                        sfzh:"",
                        lxdh:"",
                        kjbh:'',
                        counts:1,
                        dz:"",
                        ysxzbh:this.ysxzOptions[0].codevalue,
                        hyflbh:this.hyflOptions[0].codevalue,
                        jffsbh:"XJ",
                        khzh:"",
                        khmc:"",
                        yhzh:"",
                        khyh:"",
                },
                this.isOrNotShow=false;
            },
            dbpzAdd_vue(){
                this.clearForm();
			    this.isOrNotShowMph=true;
                if(this.ldid==''||this.ldid==null){
                    this.$message.error('请选择楼栋！');
                    return;
                }
			    let params={
			        xmid:this.xmid,
                    ldid:this.ldid
                }
                dbpzAdd(params).then((res)=>{
                    if(res.data.row.length>0){
                        // this.getYsxzData_vue(res.data.row[0].ysxzbh,res.data.row[0].hyflbh);
                        this.getKhzhData_vue(res.data.row[0].yhbh,res.data.row[0].khyh);
                        if(this.sqlx=='30'){
                            this.drpzForm.hm=res.data.row[0].hm;
                            this.drpzForm.counts=res.data.row.length;
                            this.drpzForm.mph=res.data.row[0].mph;
                            this.drpzForm.sfzh=res.data.row[0].sfzh;
                            this.drpzForm.lxdh=res.data.row[0].lxdh;
                            this.drpzForm.kjbh=res.data.row[0].kjbh;
                            this.drpzForm.dz=res.data.row[0].dz;
                            this.drpzForm.ysxzbh=res.data.row[0].ysxzbh;
                            this.drpzForm.hyflbh=res.data.row[0].hyflbh;
                            this.drpzForm.jffsbh=res.data.row[0].jffsbh;
                            if(res.data.row[0].jffsbh=='XJ'){
                                this.isOrNotShow=false;
                            }else{
                                this.isOrNotShow=true;
                            }
                            this.drpzForm.khmc=res.data.row[0].khmc;
                            this.drpzForm.yhzh=res.data.row[0].yhzh;
                        }else{
                            // this.dbpzForm.khyh=res.data.row[0].khyh;
                            // this.dbpzForm.khzh=res.data.row[0].yhbh;
                            this.dbpzForm.dz=res.data.row[0].dz;
                            this.dbpzForm.hh=res.data.row[0].hh;
                            this.dbpzForm.hm=res.data.row[0].hm;
                            this.dbpzForm.hyflbh=res.data.row[0].hyflbh;
                            this.dbpzForm.jffsbh=res.data.row[0].jffsbh;
                            if(this.dbpzForm.jffsbh=='XJ'){
                                this.isOrNotShow=false;
                            }else{
                                this.isOrNotShow=true;
                            }
                            this.dbpzForm.ysxzbh=res.data.row[0].ysxzbh;
                            this.dbpzForm.yhzh=res.data.row[0].yhzh;
                            this.dbpzForm.khmc=res.data.row[0].khmc;
                            this.dbpzForm.kjbh=res.data.row[0].kjbh;
                            this.dbpzForm.mph=res.data.row[0].mph;
                        }

                    }else{
                        this.dbpzForm.kjbh = res.data.ldRow[0].ldsbkj;
                        this.dbpzForm.dz=res.data.dz+"[楼号]#[门牌]室";
                    }
                    this.dbpzDialogFormVisible=true;
                })

            },
            dbpzHandle_vue(index,row){

                // this.getYsxzData_vue(row.ysxzbh,row.hyflbh);
                this.getKhzhData_vue(row.yhbh,row.khyh);
                if(row.sqlx=='30'){
                    this.drpzForm.id=row.id;
                    this.drpzForm.hm=row.hm;
                    this.drpzForm.counts=1;
                    this.drpzForm.mph=row.mph;
                    this.drpzForm.sfzh=row.sfzh;
                    this.drpzForm.lxdh=row.lxdh;
                    this.drpzForm.kjbh=row.kjbh;
                    this.drpzForm.dz=row.dz;
                    this.drpzForm.ysxzbh=row.ysxzbh;
                    this.drpzForm.hyflbh=row.hyflbh;
                    this.drpzForm.jffsbh=row.jffsbh;
                    if(row.jffsbh=='XJ'){
                        this.isOrNotShow=false;
                    }else{
                        this.isOrNotShow=true;
                    }
                    this.drpzForm.khmc=row.khmc;
                    this.drpzForm.yhzh=row.yhzh;
                    this.dbpzDialogFormVisible=true;
                }else{

                    // this.dbpzForm.khyh=row.khyh;
                    // this.dbpzForm.khzh=row.yhbh;

                    this.dbpzForm.id=row.id;
                    this.dbpzForm.dz=row.dz;
                    this.dbpzForm.hh=row.hh;
                    this.dbpzForm.hm=row.hm;
                    this.dbpzForm.hyflbh=row.hyflbh;
                    this.dbpzForm.jffsbh=row.jffsbh;
                    this.dbpzForm.ysxzbh=row.ysxzbh;
                    this.dbpzForm.yhzh=row.yhzh;
                    this.dbpzForm.khmc=row.khmc;
                    this.dbpzForm.kjbh=row.kjbh;
                    if(row.jffsbh=='XJ'){
                        this.isOrNotShow=false;
                    }else{
                        this.isOrNotShow=true;
                    }
                    this.isOrNotShowMph=false;

                    this.dbpzDialogFormVisible=true;
                }


            },
            initJhfs(value){
                this.dbpzForm.hxh=null
                this.dbpzForm.lcstart=null
                this.dbpzForm.lcend=null
                this.dbpzForm.lc=null
                this.dbpzForm.hxstart=null
                this.dbpzForm.hxend=null
                this.dbpzForm.hxws=null
            },
            process1(){
                this.dbpzForm.mph="";
                if(!this.dbpzForm.lcstart||!Number.isInteger(this.dbpzForm.lcstart)){
                    this.$message.error('楼层不为空且楼层为数字！');
                    return;
                }
                if(!this.dbpzForm.lcend||!Number.isInteger(this.dbpzForm.lcend)){
                    this.$message.error('楼层不为空且楼层为数字！');
                    return;
                }
                for(let i=this.dbpzForm.lcstart;i<=this.dbpzForm.lcend;i++){
                    if(i==this.dbpzForm.lcend){
                        this.dbpzForm.mph+=i+this.dbpzForm.hxh
                    }else{
                        this.dbpzForm.mph+=i+this.dbpzForm.hxh+','
                    }
                }
            },
            process2(){
                this.dbpzForm.mph="";
                if(!this.dbpzForm.hxstart||!Number.isInteger(this.dbpzForm.hxstart)){
                    this.$message.error('户型不为空且户型为数字！');
                    return;
                }
                if(!this.dbpzForm.hxend||!Number.isInteger(this.dbpzForm.hxend)){
                    this.$message.error('户型不为空且户型为数字！');
                    return;
                }
                if(!this.dbpzForm.hxws||!Number.isInteger(this.dbpzForm.hxws)){
                    this.$message.error('户型位数不为空且户型位数为数字！');
                    return;
                }
                for(let i=this.dbpzForm.hxstart;i<=this.dbpzForm.hxend;i++){
                    if(i==this.dbpzForm.hxend){
                        this.dbpzForm.mph+=this.dbpzForm.lc+this.PrefixInteger(i, this.dbpzForm.hxws)
                    }else{
                        this.dbpzForm.mph+=this.dbpzForm.lc+this.PrefixInteger(i, this.dbpzForm.hxws)+','
                    }
                }
            },
            process3(){
                this.dbpzForm.mph="";
                if(!this.dbpzForm.lcstart||!Number.isInteger(this.dbpzForm.lcstart)){
                    this.$message.error('楼层不为空且楼层为数字！');
                    return;
                }
                if(!this.dbpzForm.lcend||!Number.isInteger(this.dbpzForm.lcend)){
                    this.$message.error('楼层不为空且楼层为数字！');
                    return;
                }
                if(!this.dbpzForm.hxstart||!Number.isInteger(this.dbpzForm.hxstart)){
                    this.$message.error('户型不为空且户型为数字！');
                    return;
                }
                if(!this.dbpzForm.hxend||!Number.isInteger(this.dbpzForm.hxend)){
                    this.$message.error('户型不为空且户型为数字！');
                    return;
                }
                if(!this.dbpzForm.hxws||!Number.isInteger(this.dbpzForm.hxws)){
                    this.$message.error('户型位数不为空且户型位数为数字！');
                    return;
                }
                for(let i=this.dbpzForm.lcstart;i<=this.dbpzForm.lcend;i++){
                    for(let j=this.dbpzForm.hxstart;j<=this.dbpzForm.hxend;j++){
                        if(i==this.dbpzForm.lcend&&j==this.dbpzForm.hxend){
                            this.dbpzForm.mph+=i+this.PrefixInteger(j, this.dbpzForm.hxws)
                        }else{
                            this.dbpzForm.mph+=i+this.PrefixInteger(j, this.dbpzForm.hxws)+','
                        }
                    }
                }
            },
            PrefixInteger(num, length) {
                return (Array(length).join('0') + num).slice(-length);
            },
            dbpzDelete_vue(index,row){
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    let params = {
                        id: row.id,
                        zt: 1
                    }
                    dbpzDelete(params).then((res) => {
                        if(res.data){
                            this.initDbpzTable_vue(this.ldid);
                            this.initLdhb_vue();
                        }
                    })
                })
            },
            download_vue(){
                let params = {xmid:this.xmid,ldids:this.ldids};
                queryAllLdsb(params).then(res=>{
                   this.AllData = res.data.list;
                    setTimeout(()=>{
                        this.xzbg_vue();
                    },1);
                });
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

                var wb = XLSX.utils.table_to_book(document.querySelector("#out-table"),{raw:true});
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
                        new Blob([wbout], { type: "application/octet-stream;charset=utf-8" }),
                        //设置导出文件名称
                        "出户改造信息填报"+name+".xlsx"
                    );
                } catch (e) {
                    if (typeof console !== "undefined") console.log(e, wbout);
                }
                return wbout;
            },
            // 上传文件
            uploadFile (item) {
                const fileObj = item.file;
                // FormData 对象
                const form = new FormData();
                // 文件对象
                form.append('multipartFiles', fileObj);
                this.listLoading = true;
                importExcel(form).then((res) => {
                    if(res.msg=='文件类型错误'){
                        this.$message.error('文件类型错误，请上传Excel！');
                    }else if(res.msg=='文件为空，请重新上传'){
                        this.$message.error('文件为空，请重新上传！');
                    }else if(res.msg=='上传成功'){
                        this.$message({
                            message: '编辑成功，已成功修改' +res.count + '个',
                            type: 'success'
                        });
                        //this.initDbpzTable_vue(this.ldid);
                    }else{
                        this.$message.error('编辑失败！');
                    }
                    this.fileList=[];
                    this.listLoading = false;
                });
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
		},
		mounted() {
			this.initLdhb_vue();
            this.initDbpzTable_vue("");
            this.getKjData_vue();
            this.getYsxzData_vue();
            this.getHyflData_vue();
            this.getjffsData_vue();
            this.getKhzhData_vue();
            this.getXmsqlx_vue();
		},
        components:{

        }
	}

</script>

<style>
    .el-dialog__header {
        padding: 10px 20px 10px;
    }
</style>