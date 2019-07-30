<template>
    <section>
        <el-row>
            <el-col :span="24">
                <!--项目基本信息模板-->
                <hhldsx :xmid="this.xmid" v-show="hhl" :activityid="this.activityId"></hhldsx>
                <GWJBXX></GWJBXX>

                <!--综合验收范围-->
                <ZHYSFW ref="zhysxx"></ZHYSFW>
                <!--发起造价洽谈需填写内容-->
                <el-card class="box-card" style="margin-top: 15px;">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">资料分类及下载
                        </div>
                    </div>
                    <div class="text item">
                        <el-row>
                            <el-col :span="14">
                                <table class="border-table">
                                    <tr style="text-align: center">
                                        <td>资料类型</td>
                                        <td>资料数量</td>
                                        <td>操作</td>
                                    </tr>
                                    <tr style="text-align: center">
                                        <td>施工资料</td>
                                        <td>{{sgzl}}</td>
                                        <td><label title="查看">
                                            <i class="iconfont icon-yulan" @click="getSgZl()"
                                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label></td>

                                        <!-- <td @click="getSgZl()" style="color: #00a65a">详情</td>-->
                                    </tr>
                                    <tr style="text-align: center">
                                        <td>设计成果</td>
                                        <td>{{sjcg}}</td>
                                        <td><label title="查看">
                                            <i class="iconfont icon-yulan" @click="getSjcg()"
                                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label></td>

                                        <!-- <td @click="getSjcg()" style="color: #00a65a">详情</td>-->
                                    </tr>
                                    <tr style="text-align: center">
                                        <td>客户提供资料</td>
                                        <td>{{khzl}}</td>
                                        <td><label title="查看">
                                            <i class="iconfont icon-yulan" @click="getKhtgzl()"
                                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label></td>

                                        <!--<td @click="getKhtgzl()" style="color: #00a65a">详情</td>-->
                                    </tr>
                                </table>
                            </el-col>
                        </el-row>
                    </div>
                </el-card>

                <!--结算-->
                <el-row>
                    <el-col>
                        <el-card class="box-card" style="margin-top: 10px">
                            <div slot="header" class="clearfix"
                                 style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">
                                <span>结算汇总表</span>
                                <!-- <el-button style="float: right; padding: 3px 0" type="text" @click="openYsxx">选择预算项</el-button>-->
                            </div>
                            <div class="text item">
                                <el-row>
                                    <el-col :span="24">
                                        <el-card class="box-card" style="width: 98%;">
                                            <div slot="header" class="clearfix"
                                                 style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">
                                                <span>结算范围</span></div>
                                            <div class="text item">
                                                <el-col :span="6" style="text-align: center;">
                                                    <span><b>结算分类</b></span>
                                                </el-col>
                                                <el-col :span="3" style="text-align: center;">
                                                    <span><b>预算金额</b></span>
                                                </el-col>
                                                <el-col :span="9" style="text-align: center;">
                                                    <span><b>结算金额</b></span>
                                                </el-col>
                                                <el-form ref="form" :model="formData" label-width="80px">
                                                    <el-form-item
                                                            v-for="(a, index) in formData.domains"
                                                            :label="a.mc"
                                                            label-width="200px"
                                                            :key="index"
                                                            :prop="'domains.' + index + '.ysje'"
                                                    >
                                                        <el-input
                                                                v-if="a.mc != null"
                                                                v-model="a.ysje"
                                                                placeholder="预算金额"
                                                                class="cd"
                                                                :disabled="true"
                                                                id="n1"
                                                                :min=0
                                                                type="number"
                                                        ></el-input>
                                                        <el-input class="cd" v-if="a.mc != null" v-model="a.jsje"
                                                                  :disabled="true" placeholder="结算金额"></el-input>
                                                    </el-form-item>
                                                    <!--<JEJS :value="zje"></JEJS>-->
                                                    <el-col :span="4" style="text-align: right;padding-top: 10px">
                                                        <span><b>总计</b>&nbsp;&nbsp;&nbsp;</span>
                                                    </el-col>
                                                    <el-form-item label-width="200px">
                                                        <el-input style="" v-model='ys_value' placeholder="预算总计"
                                                                  :readonly="true" class="cd"></el-input>
                                                        <el-input style="" v-model='js_value' placeholder="结算总计"
                                                                  :readonly="true" class="cd"></el-input>
                                                    </el-form-item>
                                                </el-form>
                                            </div>
                                        </el-card>
                                    </el-col>
                                </el-row>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="24" style="text-align: center;margin-top: 0px;"></el-col>
                </el-row>


                <!-- <el-row style="margin-top: 15px;">
                     <el-col :span="24">
                         <el-card class="box-card" style="margin-top: 15px;">
                             <div slot="header" class="clearfix">
                                 <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">结算成果表</div>

                             </div>
                             <div class="text item">
                                 <el-row>
                                     <el-col :span="4" style="text-align: center">结算分类</el-col>
                                     <el-col :span="4" style="text-align: center">预算金额</el-col>
                                     <el-col :span="4" style="text-align: center">结算金额</el-col>
                                 </el-row>
                                 <el-row style="margin-top: 20px">
                                     <el-col :span="4" style="text-align: center">庭院给水官网</el-col>
                                     <el-col :span="4" style="text-align: center">{{ty_je}}</el-col>
                                     <el-col :span="4" style="text-align: center">{{ty_js}}</el-col>
                                 </el-row>
                                 <el-row style="margin-top: 10px">
                                     <el-col :span="4" style="text-align: center">生活泵房</el-col>
                                     <el-col :span="4" style="text-align: center">{{bf_je}}</el-col>
                                     <el-col :span="4" style="text-align: center">{{bf_js}}</el-col>
                                 </el-row>
                                 <el-row style="margin-top: 10px">
                                     <el-col :span="4" style="text-align: center">户表部分</el-col>
                                     <el-col :span="4" style="text-align: center">{{hb_je}}</el-col>
                                     <el-col :span="4" style="text-align: center">{{hb_js}}</el-col>
                                 </el-row>
                                 <el-row style="margin-top: 10px">
                                     <el-col :span="4" style="text-align: center">吊管部分</el-col>
                                     <el-col :span="4" style="text-align: center">{{dg_je}}</el-col>
                                     <el-col :span="4" style="text-align: center">{{dg_js}}</el-col>
                                 </el-row>
                                 <el-row style="margin-top: 10px">
                                     <el-col :span="4" style="text-align: center">工商表</el-col>
                                     <el-col :span="4" style="text-align: center">{{gsb_je}}</el-col>
                                     <el-col :span="4" style="text-align: center">{{gsb_js}}</el-col>
                                 </el-row>
                                 <el-row style="margin-top: 10px">
                                     <el-col :span="4" style="text-align: center">工程设计费</el-col>
                                     <el-col :span="4" style="text-align: center">{{gcsjf_je}}</el-col>
                                     <el-col :span="4" style="text-align: center">{{gcsjf_js}}</el-col>
                                 </el-row>
                                 <el-row style="margin-top: 10px">
                                     <el-col :span="4" style="text-align: center">工程监理费</el-col>
                                     <el-col :span="4" style="text-align: center">{{gcjlf_je}}</el-col>
                                     <el-col :span="4" style="text-align: center">{{gcjlf_js}}</el-col>
                                 </el-row>
                                 <el-row style="margin-top: 10px">
                                     <el-col :span="4" style="text-align: center">其他费用</el-col>
                                     <el-col :span="4" style="text-align: center">{{qtfy_je}}</el-col>
                                     <el-col :span="4" style="text-align: center">{{qtfy_js}}</el-col>
                                 </el-row>
                                 <el-row style="margin-top: 10px">
                                     <el-col :span="4" style="text-align: center">总计：</el-col>
                                     <el-col :span="4" style="text-align: center">{{zj_je}}</el-col>
                                     <el-col :span="4" style="text-align: center">{{zj_js}}</el-col>
                                 </el-row>
                                 <el-row style="margin-top: 10px">
                                     <el-col :span="4" style="text-align: center">大写：</el-col>
                                     <el-col :span="4" style="text-align: center">{{zj_je | toChies}}&nbsp;</el-col>
                                     <el-col :span="4" style="text-align: center">{{zj_js | toChies}}</el-col>
                                 </el-row>
                                 <el-row style="left: 15px;margin-top: 30px;">
                                     <el-col :span="3">
                                         结算备注：
                                     </el-col>
                                     <el-col :span="10">
                                         <el-col :span="4" style="text-align: center">{{jsbz}}</el-col>
                                     </el-col>
                                 </el-row>
                             </div>
                         </el-card>
                     </el-col>
                 </el-row>-->

                <!--编辑资料下载-->
                <el-row style="margin-top: 15px;">
                    <el-col :span="24">
                        <el-card class="box-card" style="margin-top: 15px;">
                            <div slot="header" class="clearfix">
                                <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">
                                    结算资料
                                </div>

                            </div>
                            <el-table :data="row" border highlight-current-row v-loading="listLoading"
                                      style="width: 100%;">
                                <el-table-column prop="fjmc" label="资料名称" width="200" align="center">
                                </el-table-column>
                                <!--      <el-table-column prop="fjlx" label="资料类型" width="100" align="center">
                                      </el-table-column>-->
                                <el-table-column prop="scsj_str" label="上传时间" width="200" align="center">
                                </el-table-column>
                                <el-table-column prop="username" label="上传人" width="150" align="center">
                                </el-table-column>
                                <el-table-column label="操作" width="150" align="center">
                                    <template slot-scope="scope">
                                        <label title="下载" @click="Jssq_download(scope.$index, scope.row)">
                                            <i class="iconfont icon-xiazai"
                                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                                        <!--       <el-button size="small" type="primary"
                                                          @click="Jssq_download(scope.$index, scope.row)">下载
                                               </el-button>-->
                                    </template>
                                </el-table-column>
                            </el-table>
                            <el-pagination background layout="total, prev, pager, next, sizes , jumper"
                                           @current-change="handleCurrentChange" @size-change="handleSizeChange"
                                           :page-sizes="[5, 10, 20, 50]"
                                           :page-size="this.size" :total="this.total" style="float:right;">
                            </el-pagination>
                        </el-card>
                    </el-col>
                </el-row>

                <!--检查资料上传-->
                <el-row style="margin-top: 15px;">
                    <el-col :span="24">
                        <el-card class="box-card" style="margin-top: 15px;">
                            <div slot="header" class="clearfix">
                                <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">
                                    审查资料
                                </div>

                            </div>
                            <el-form :model="kcfk" label-width="150px" ref="xmxxForm" style="margin-top: 20px">
                                <el-form-item label="资料上传：" style="width: 45%">
                                    <el-upload
                                            action="#"
                                            multiple
                                            class="upload-demo"
                                            :on-preview="handlePreview"
                                            :on-remove="handleRemove"
                                            :on-change="handleChange"
                                            :file-list="fileList"
                                            :http-request="submitUpload"
                                            :show-file-list="false"
                                            :auto-upload="true"
                                            style="display: inline-block">
                                        <el-button size="mini" v-show="shangchuan" type="success"><i class="iconfont icon-shangchuan_f"></i>上传
                                        </el-button>
                                        <!-- <el-button slot="trigger" size="small" type="primary">上传</el-button>-->
                                        <!--   <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">
                                               上传
                                           </el-button>
                                           <div slot="tip" class="el-upload__tip"></div>-->
                                    </el-upload>
                                </el-form-item>
                            </el-form>
                            <el-table :data="row_4" border highlight-current-row v-loading="listLoading_4"
                                      style="width: 100%;">
                                <el-table-column prop="fjmc_str" label="资料名称" width="200" align="center">
                                </el-table-column>
                                <!--    <el-table-column prop="fjlx_str" label="资料类型" width="100" align="center">
                                    </el-table-column>-->
                                <el-table-column prop="scsj_str_sj" label="上传时间" width="200" align="center">
                                </el-table-column>
                                <el-table-column prop="username_str" label="上传人" width="150" align="center">
                                </el-table-column>
                                <el-table-column label="操作" width="150" align="center">
                                    <template slot-scope="scope">
                                        <div v-if="scope.row.fjhz!=null&&(scope.row.fjhz.toLowerCase()=='png'||scope.row.fjhz.toLowerCase()=='jpg')">
                                            <el-col :span="24">
                                                <label title="删除" v-show="shanchu"><i class="iconfont delete" @click="handleEdit(scope.$index, scope.row)" style="cursor:pointer;padding: 4px;color: #66b1ff"></i></label>
                                                <label title="预览" ><i class="iconfont icon-yulan" @click="downloadFile_vue(scope.$index, scope.row,1)" style="cursor:pointer;padding: 4px;color: #66b1ff"></i></label>
                                                <label title="下载" ><i @click="zlfl_download(scope.$index, scope.row,2)" class="iconfont icon-xiazai" style="cursor:pointer;padding: 4px;color: #66b1ff"></i></label>
                                            </el-col>
                                        </div>
                                        <div v-else>
                                            <el-col :span="24">
                                                <label title="删除"  v-show="shanchu"><i @click="handleEdit(scope.$index, scope.row)" class="iconfont delete" style="cursor:pointer;padding: 4px;color: #66b1ff"></i></label>
                                                <label ><i  style="cursor:pointer;padding: 14px;color: #66b1ff " ></i></label>
                                                <label title="下载" ><i @click="zlfl_download(scope.$index, scope.row,2)" class="iconfont icon-xiazai" style="cursor:pointer;padding: 4px;color: #66b1ff"></i></label>
                                            </el-col>
                                        </div>
                                     <!--   <label title="删除" v-show="shanchu" @click="handleEdit(scope.$index, scope.row)">
                                            <i class="iconfont delete"
                                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>-->
                                    </template>
                                </el-table-column>
                            </el-table>
                            <!--    <el-pagination background layout="total, prev, pager, next, sizes , jumper"
                                               @current-change="handleCurrentChange_4" @size-change="handleSizeChange_4"
                                               :page-sizes="[5, 10, 20, 50]"
                                               :page-size="this.size_4" :total="this.total_4" style="float:right;">
                                </el-pagination>-->
                        </el-card>
                    </el-col>
                </el-row>

                <el-row style="left: 15px;margin-top: 30px;">
                    <el-col :span="2">
                        纪检室意见：
                    </el-col>
                    <el-col :span="10">
                        <el-input v-model="bz"></el-input>
                    </el-col>
                </el-row>
                <el-row style="margin-top: 30px;">
                    <el-col :span="10">&nbsp;</el-col>
                    <el-col :span="12">
                        <router-link :to="tabUrl">
                            <el-button size="mini" type="info" @click="cancel()"><i class="iconfont icon-quxiao"></i>取消
                            </el-button>
                        </router-link>
                        <el-button size="mini" v-show="th" type="info" @click="tuihui()" :loading="sumbitLoading"><i
                                class="iconfont icon-fanhui"></i>退回
                        </el-button>

                        <el-button size="mini"  v-show="jssh" type="primary" @click="tijiao()" :loading="sumbitLoading"><i
                                class="iconfont icon-shenpitongguo"></i>审核
                        </el-button>
                        <el-button size="mini" v-show="xmqj" type="primary" @click="goxmqj()" :loading="sumbitLoading"><i
                                class="iconfont icon-xiangmuquanjing_"></i>项目全景
                        </el-button>

                    </el-col>
                </el-row>
                <!--客户提供资料-->
                <el-dialog :title="title" :visible.sync="selectZlfl_1" width="60%">
                    <el-tabs type="card">
                        <el-table :data="zlflform_1" v-loading="listLoading_1">
                            <el-table-column property="fjmc" label="附件名称" width="250"></el-table-column>
                            <!-- <el-table-column property="nameSter" label="附件类型" width="150"></el-table-column>-->
                            <el-table-column property="scsjStr" label="上传时间" width="200"></el-table-column>
                            <el-table-column label="操作" width="100">
                                <template slot-scope="scope">
                                    <label title="下载" @click="zlfl_download(scope.$index, scope.row)">
                                        <i class="iconfont icon-xiazai"
                                           style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                                    <!--  <el-button size="small" type="primary"
                                                 @click="zlfl_download(scope.$index, scope.row)">下载
                                      </el-button>-->
                                </template>
                            </el-table-column>
                        </el-table>
                        <el-col :span="24" class="toolbar">
                            <el-pagination background layout="total, prev, pager, next, sizes , jumper"
                                           @current-change="handleCurrentChange_1" @size-change="handleSizeChange_1"
                                           :page-sizes="[5, 10, 20, 50]"
                                           :page-size="this.size_1" :total="this.total_1" style="float:right;">
                            </el-pagination>
                        </el-col>
                    </el-tabs>
                </el-dialog>
                <!--设计成果-->
                <el-dialog :title="title" :visible.sync="selectZlfl_2" width="60%">
                    <el-tabs type="card">
                        <el-table :data="zlflform_2" v-loading="listLoading_2">
                            <el-table-column property="fjmc" label="附件名称" width="250"></el-table-column>
                            <!--   <el-table-column property="nameSter" label="附件类型" width="150"></el-table-column>-->
                            <el-table-column property="scsjStr" label="上传时间" width="200"></el-table-column>
                            <el-table-column label="操作" width="100">
                                <template slot-scope="scope">
                                    <label title="下载" @click="zlfl_download(scope.$index, scope.row)">
                                        <i class="iconfont icon-xiazai"
                                           style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                                    <!--  <el-button size="small" type="primary"
                                                 @click="zlfl_download(scope.$index, scope.row)">下载
                                      </el-button>-->
                                </template>
                            </el-table-column>
                        </el-table>
                        <el-col :span="24" class="toolbar">
                            <el-pagination background layout="total, prev, pager, next, sizes , jumper"
                                           @current-change="handleCurrentChange_2" @size-change="handleSizeChange_2"
                                           :page-sizes="[5, 10, 20, 50]"
                                           :page-size="this.size_2" :total="this.total_2" style="float:right;">
                            </el-pagination>
                        </el-col>
                    </el-tabs>
                </el-dialog>
                <!--施工资料-->
                <el-dialog :title="title" :visible.sync="selectZlfl_3" width="60%">
                    <el-tabs type="card">
                        <el-table :data="zlflform_3" v-loading="listLoading_3">
                            <el-table-column property="fjmc" label="附件名称" width="250"></el-table-column>
                            <!-- <el-table-column property="nameSter" label="附件类型" width="150"></el-table-column>-->
                            <el-table-column property="scsjStr" label="上传时间" width="200"></el-table-column>
                            <el-table-column label="操作" width="100">
                                <template slot-scope="scope">
                                    <label title="下载" @click="zlfl_download(scope.$index, scope.row)">
                                        <i class="iconfont icon-xiazai"
                                           style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                                    <!-- <el-button size="small" type="primary"
                                                @click="zlfl_download(scope.$index, scope.row)">下载
                                     </el-button>-->
                                </template>
                            </el-table-column>
                        </el-table>
                        <el-col :span="24" class="toolbar">
                            <el-pagination background layout="total, prev, pager, next, sizes , jumper"
                                           @current-change="handleCurrentChange_3" @size-change="handleSizeChange_3"
                                           :page-sizes="[5, 10, 20, 50]"
                                           :page-size="this.size_3" :total="this.total_3" style="float:right;">
                            </el-pagination>
                        </el-col>
                    </el-tabs>
                </el-dialog>
            </el-col>
            <el-col :span="24" style="text-align: center;margin-top: 35px;">
            </el-col>
        </el-row>

    </section>
</template>

<script>
    import GWJBXX from '@/views/template/gwjbxx.vue';
    import ZHYSFW from '@/views/template/Zhysfw.vue';
    import hhldsx from '@/views/template/hhldsx.vue';
    import util from '../../common/js/util'
    import {selectZlflsl, download} from '../../api/zhys'
    import {download_sjst} from '../../api/sjst';
    import {
        SaveJsSh,
        SelectJshz,
        getYszlscLsit,
        deleteJszl,
        getJssqJczl,
        Tuihui,
        getFile,
        getdownload,
        getQianshou,
        jszbid,
        getjsshyi,
    } from '../../api/Js'
    import axios from 'axios';

    export default {

        data() {
            return {
                hhl: true,
                shangchuan:true,
                shanchu:true,
                th:true,
                jssh:true,
                xmqj:true,

                formData: {
                    domains: [{}]
                },
                zje: '',
                checkList: [],
                ys_value: '',
                js_value: '',

                activityId: 'C0019',
                title: '',
                sgzl: '',
                sjcg: '',
                khzl: '',
                fjmc: '',
                fjlx_Yj: '',
                scsjStr: '',
                selectZlfl_1: false,
                selectZlfl_2: false,
                selectZlfl_3: false,
                //备注内容
                bz: '',
                zlflform_1: [],
                zlflform_2: [],
                zlflform_3: [],
                total_1: 5,
                total_2: 5,
                total_3: 5,
                total_4: 5,//表格数据总数量
                page_1: 1,
                page_2: 1,
                page_3: 1,
                page_4: 1,//当前页码
                size_1: 5,
                size_2: 5,
                size_3: 5,
                size_4: 5,//每页容量
                listLoading_1: false,
                listLoading_2: false,
                listLoading_3: false,
                listLoading_4: false,
                //金额
                ty_je: "",
                bf_je: "",
                hb_je: "",
                dg_je: "",
                gsb_je: "",
                gcsjf_je: "",
                gcjlf_je: "",
                qtfy_je: "",
                zj_je: "",
                zj_js: "",
                //结算
                ty_js: "",
                bf_js: "",
                hb_js: "",
                dg_js: "",
                gsb_js: "",
                gcsjf_js: "",
                gcjlf_js: "",
                qtfy_js: "",
                zj_dx: "",
                jsbz: "",
                row: [],
                row_4: [],
                total: 5,//表格数据总数量
                page: 1,//当前页码
                size: 5,//每页容量
                // nodeid:'30001',
                kcfk: {},
                listLoading: false,
                fileList: [],
                fileIds: [],//上传附件id集合
                xmid: this.$route.query.xmid,
                sqlx: this.$route.query.sqlx,
                sqbh: this.$route.query.sqbh,
                taskId: this.$route.query.taskId,
                lctbh: this.$route.query.lctbh,
                sumbitLoading: false,
            }
        },
        mounted() {
            this.qianShou();//签收
            this.getZilflxx();
            this.openZhysxx();
            this.getYshz();
            this.getJszlsc();
            this.getJssqJC();
            this.rwcbsj();
            this.lctgwfk();//流程图
        },
        components: {
            'GWJBXX': GWJBXX,  //将别名demo 变成 组件 Demo
            'ZHYSFW': ZHYSFW,
            'hhldsx': hhldsx,
        },
        methods: {
            lctgwfk() {
                if (this.lctbh == "jssh") { //项目全景跳转
                    this.hhl=false;
                    this.shangchuan=false;
                    this.shanchu=false;
                    this.th=false;
                    this.jssh=false;
                    this.xmqj=false;
                    let params = {
                        xmid: this.xmid,
                    };
                    getjsshyi(params).then(res => {
                         this.bz=res.data.shyj;
                    })
                }
            },
            //跳转至项目全景
            goxmqj() {
                this.$router.push({path: '/Xmlct', query: {xmid: this.xmid}})

            },
            cancel() {//取消
                this.$router.push({path: '/Backlog', query: {}});
            },
            rwcbsj() {//查询任务从表中数据
                let params = {
                    xmid: this.xmid,
                };
                jszbid(params).then(res => {
                    console.log(res);
                    this.ysRwcb = res.data.ysrwcb;

                    for (let i = 0; i < this.ysRwcb.length; i++) {
                        this.checkList[i] = this.ysRwcb[i].jsfl;
                        this.formData.domains.push(this.ysRwcb[i]);
                    }
                    this.getYsje();
                    this.getJsje();
                });
            },
            //得到预算总金额
            getYsje(res) {
                let data = this.formData.domains;
                this.ys_value = 0;
                data.forEach((a, index) => {
                    if (a.ysje != '' && index != 0) {
                        let num = Number(a.ysje);
                        if (isNaN(num)) {
                            num = 0;
                        }
                        if (this.ys_value == 0) {
                            this.ys_value = num;
                        } else {
                            this.ys_value = num + Number(this.ys_value);
                        }
                    }
                    this.ys_value = parseFloat(this.ys_value).toFixed(2);
                });

            },
            //得到结算总净额
            getJsje() {
                let data = this.formData.domains;
                this.js_value = 0;
                data.forEach((a, index) => {
                    if (a.jsje != '' && index != 0) {
                        let num = Number(a.jsje);
                        if (isNaN(num)) {
                            num = 0;
                        }
                        if (this.js_value == 0) {
                            this.js_value = num;
                        } else {
                            this.js_value = num + Number(this.js_value);
                        }
                    }
                    this.js_value = parseFloat(this.js_value).toFixed(2);
                });
            },

            //签收
            qianShou() {
                let params = {taskId: this.taskId};
                getQianshou(params).then((res) => {
                    /*  if (res.result == 'success') {
                          this.$message({
                              message: '退回成功',
                              type: 'success'
                          });
                      } else {
                          this.$message({
                              message: '退回异常',
                              type: 'error'
                          });
                      }*/
                });

            },
            //打开综合验收范围信息界面
            openZhysxx() {
                setTimeout(() => {
                    this.$refs.zhysxx.getZhyfwsxx();
                }, 0)
            },
            handleCurrentChange(val) {
                this.page = val;
                this.getJszlsc();
            },
            handleSizeChange(val) {
                this.size = val;
                this.page = 1;
                this.getJszlsc();
            },
            handleCurrentChange_1(val) {
                this.page_1 = val;
                this.getKhtgzl();
            },
            handleSizeChange_1(val) {
                this.size_1 = val;
                this.page_1 = 1;
                this.getKhtgzl();
            },
            handleCurrentChange_2(val) {
                this.page_2 = val;
                this.getSjcg();//设计成果
            },
            handleSizeChange_2(val) {
                this.size_2 = val;
                this.page_2 = 1;
                this.getSjcg();
            },
            handleCurrentChange_3(val) {
                this.page_3 = val;
                this.getSgZl();//施工资料
            },
            handleSizeChange_3(val) {
                this.size_3 = val;
                this.page_3 = 1;
                this.getSgZl();
            },
            handleCurrentChange_4(val) {
                this.page_4 = val;
                this.getJssqJC();
            },
            handleSizeChange_4(val) {
                this.size_4 = val;
                this.page_4 = 1;
                this.getJssqJC();
            },
            getZilflxx(str) {//资料分类及下载 数量

                let params = Object.assign({
                    xmid: this.xmid, sqlx: this.sqlx, sqbh: this.sqbh, page: this.page_1, size: this.size_1,
                    page: this.page_2, size: this.size_2, page: this.page_3, size: this.size_3
                });
                selectZlflsl(params).then((res) => {
                    if (res.data.total > 0) {//客户提供资料数量
                        this.khzl = res.data.total;
                    } else {
                        this.khzl = "- ";
                    }
                    if (res.data.sjcgtotal > 0) {//设计成果数量
                        this.sjcg = res.data.sjcgtotal;
                    } else {
                        this.sjcg = "- ";
                    }
                    if (res.data.sgtotal > 0) {//施工资料数量
                        this.sgzl = res.data.sgtotal;
                    } else {
                        this.sgzl = "- ";
                    }
                });
            },
            getKhtgzl() {//客户资料

                let params = Object.assign({
                    xmid: this.xmid,
                    sqlx: this.sqlx,
                    sqbh: this.sqbh,
                    page: this.page_1,
                    size: this.size_1
                });
                this.listLoading_1 = true;
                selectZlflsl(params).then((res) => {
                    this.title = "客户提供资料";
                    this.total_1 = res.data.total;
                    this.zlflform_1 = res.data.khtglist;
                    this.listLoading_1 = false;
                    this.selectZlfl_1 = true;
                });

            },
            getSjcg() {//设计成果

                let params = Object.assign({
                    xmid: this.xmid,
                    sqlx: this.sqlx,
                    sqbh: this.sqbh,
                    page: this.page_2,
                    size: this.size_2
                });
                this.listLoading_2 = true;
                selectZlflsl(params).then((res) => {
                    // console.log(res.data.sjcgtotal+"---------------")
                    this.title = "设计成果";
                    this.total_2 = res.data.sjcgtotal;
                    this.zlflform_2 = res.data.sjcglist;
                    this.listLoading_2 = false;
                    this.selectZlfl_2 = true;
                });
            },
            getSgZl() {

                let params = Object.assign({
                    xmid: this.xmid,
                    sqlx: this.sqlx,
                    sqbh: this.sqbh,
                    page: this.page_3,
                    size: this.size_3
                });
                this.listLoading_3 = true;
                selectZlflsl(params).then((res) => {

                    this.title = "施工资料";
                    this.total_3 = res.data.sgtotal;
                    this.zlflform_3 = res.data.sglist;
                    this.listLoading_3 = false;
                    this.selectZlfl_3 = true;
                });
            },
            //成果文件下载
            zlfl_download(index, row) {
                let formData = new FormData();
                formData.append("path", row.fjlj);
                /*   let instance = axios.create({
                       baseURL: 'http://localhost:8080',
                       headers: {
                           'Content-Type': 'application/json; application/octet-stream'
                       },
                       responseType: 'blob',
                   });
                   instance.post('/SqzhysController/download', formData).then((res) => { // 处理返回的文件流*/
                download(formData).then((res) => {
                    const content = res.data
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
                });
            },
            // 编辑结算文件下载
            Jssq_download(index, row) {
                let formData = new FormData();
                formData.append("path", row.fjlj);
                /* let instance = axios.create({
                     baseURL: 'http://localhost:8080',
                     headers: {
                         'Content-Type': 'application/json; application/octet-stream'
                     },
                     responseType: 'blob',
                 });
                 instance.post('/JssqCsController/download', formData).then((res) => { // 处理返回的文件流*/
                getdownload(formData).then((res) => {
                    const content = res.data
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
                });
            },

            tuihui: function () {
                this.$prompt('请输入退回原因！', '温馨提示：', {
                    confirmButtonText: '提交',
                    cancelButtonText: '取消',
                    inputPattern: /\S/,
                    inputErrorMessage: '输入不能为空！'
                }).then(({value}) => {
                    // let xmid=localStorage.getItem("xmid");
                    let params = {xmid: this.xmid, thyy: value};
                    this.sumbitLoading = true;
                    Tuihui(params).then((res) => {
                        this.sumbitLoading = false;
                        if (res.data.result == 'success') {
                            this.$message({
                                message: '退回成功',
                                type: 'success'
                            });

                        } else {
                            this.$message({
                                message: '退回异常',
                                type: 'error'
                            });
                        }
                        this.$store.commit("delete_tabs", '/JssqCslbsj');
                        this.$router.push({path: './Backlog', query: {}});
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消输入'
                    });
                });

            },

            tijiao() {  //发起申请
                this.$confirm('您正在递交工作结果，一旦递交确认后将不能打回或再次修改，你确定要递交吗？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {

                    let params = Object.assign({xmid: this.xmid, bz: this.bz});
                    this.sumbitLoading = true;
                    this.tabUrl = '/Backlog';
                    this.$routerTab.close();
                    this.$routerTab.refresh('/MyWork');
                    this.$routerTab.refresh('/Backlog');
                    SaveJsSh(params).then((res) => {
                        this.sumbitLoading = false;
                        if (res.data.result == 'success') {
                            this.$message({
                                message: '提交成功',
                                type: 'success'
                            });
                            localStorage.clear();
                        } else {
                            this.$message.error('提交失败！');
                        }

                    });

                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消审核'
                    });
                });


            },
            //预算汇总
            getYshz() {

                let params = Object.assign({xmid: this.xmid});
                SelectJshz(params).then((res) => {
                    this.jsbz = res.data.jsbz
                    //01
                    if (res.data.ty_je != null) {
                        this.ty_je = res.data.ty_je;
                    } else {
                        this.ty_je = "0";
                    }
                    if (res.data.ty_js != null) {
                        this.ty_js = res.data.ty_js;
                    } else {
                        this.ty_js = "0";
                    }
                    //02
                    if (res.data.bf_je != null) {
                        this.bf_je = res.data.bf_je;
                    } else {
                        this.bf_je = "0";
                    }
                    if (res.data.bf_js != null) {
                        this.bf_js = res.data.bf_js;
                    } else {
                        this.bf_js = "0";
                    }
                    //03
                    if (res.data.hb_je != null) {
                        this.hb_je = res.data.hb_je;
                    } else {
                        this.hb_je = "0";
                    }
                    if (res.data.hb_js != null) {
                        this.hb_js = res.data.hb_js;
                    } else {
                        this.hb_js = "0";
                    }
                    //04
                    if (res.data.dg_je != null) {
                        this.dg_je = res.data.dg_je;
                    } else {
                        this.dg_je = "0";
                    }
                    if (res.data.dg_js != null) {
                        this.dg_js = res.data.dg_js;
                    } else {
                        this.dg_js = "0";
                    }
                    //05
                    if (res.data.gsb_je != null) {
                        this.gsb_je = res.data.gsb_je;
                    } else {
                        this.gsb_je = "0";
                    }
                    if (res.data.gsb_js != null) {
                        this.gsb_js = res.data.gsb_js;
                    } else {
                        this.gsb_js = "0";
                    }
                    //06
                    if (res.data.gcsjf_je != null) {
                        this.gcsjf_je = res.data.gcsjf_je;
                    } else {
                        this.gcsjf_je = "0";
                    }
                    if (res.data.gcsjf_js != null) {
                        this.gcsjf_js = res.data.gcsjf_js;
                    } else {
                        this.gcsjf_js = "0";
                    }
                    //07
                    if (res.data.gcjlf_je != null) {
                        this.gcjlf_je = res.data.gcjlf_je;
                    } else {
                        this.gcjlf_je = "0";
                    }
                    if (res.data.gcjlf_js != null) {
                        this.gcjlf_js = res.data.gcjlf_js;
                    } else {
                        this.gcjlf_js = "0";
                    }
                    //08
                    if (res.data.qtfy_je != null) {
                        this.qtfy_je = res.data.qtfy_je;
                    } else {
                        this.qtfy_je = "0";
                    }
                    if (res.data.qtfy_js != null) {
                        this.qtfy_js = res.data.qtfy_js;
                    } else {
                        this.qtfy_js = "0";
                    }
                    this.zj_je = Number(this.ty_je) + Number(this.bf_je) + Number(this.hb_je) + Number(this.dg_je) + Number(this.gsb_je) + Number(this.gcsjf_je) +
                        Number(this.gcjlf_je) + Number(this.qtfy_je);

                    this.zj_js = Number(this.ty_js) + Number(this.bf_js) + Number(this.hb_js) + Number(this.dg_js) + Number(this.gsb_js) + Number(this.gcsjf_js) +
                        Number(this.gcjlf_js) + Number(this.qtfy_js);
                    if (this.zj_je < 0) {
                        this.zj_je = "0";
                    }
                    if (this.zj_js < 0) {
                        this.zj_js = "0";
                    }

                });
            },
            //资料查询
            getJszlsc() {

                let params = Object.assign({xmid: this.xmid, page: this.page, size: this.size});
                this.listLoading = true;
                getYszlscLsit(params).then((res) => {
                    this.total = res.data.total;
                    this.row = res.data.list;
                    this.listLoading = false;
                });
            },
            // 检查查询
            getJssqJC() {

                let params = Object.assign({xmid: this.xmid, page: this.page_4, size: this.size_4});
                this.listLoading_4 = true;
                getJssqJczl(params).then((res) => {
                    if (res.data.total > 0 && res.data.list.length <= 0) {
                        this.page_4 = this.page_4 - 1;
                        this.getJssqJC();
                    }
                    this.total_4 = res.data.total;
                    this.row_4 = res.data.list;
                    this.listLoading_4 = false;
                });
            },

            //删除
            handleEdit: function (index, row) {
                this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let params = Object.assign({id: row.id, xmid: this.xmid});
                    deleteJszl(params).then((res) => {
                        if (res.data.msg == '删除成功') {
                            this.$message({
                                message: '删除成功',
                                type: 'success'
                            });
                            this.getJssqJC();
                        } else {
                            this.$message.error('删除失败！');
                        }
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },

            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
            },
            handleChange(file, fileList) {
                this.fileList = fileList;
            },
            handleExceed(files, fileList) {
                this.$message.warning('当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件');
            },
            beforeRemove(file, fileList) {
                return this.$confirm(`确定移除 ${file.name}？`);
            },
            //上传
            submitUpload() {
                let fileList = this.fileList;
                let operation = "结算资料附件";
                //  let xmid = localStorage.getItem("xmid");
                if (fileList != null && fileList.length > 0) {
                    /*   for (let i = 0; i < fileList.length; i++) {*/
                    /* let file = fileList[i];*/
                    let file = fileList[fileList.length - 1];
                    let formData = new FormData();
                    formData.append("xmid", this.xmid);
                    //   formData.append("zbid",xmid+'12');
                    formData.append("multipartFiles", file.raw);
                    formData.append("filename", file.name);
                    //  formData.append("type","sjy");
                    /*  let instance = axios.create({
                          baseURL: 'http://localhost:8080',
                          headers: {'Content-Type': 'multipart/form-data'}
                      });
                      instance.post('/JssqCsController/importFile', formData).then((res) => {*/
                    getFile(formData).then((res) => {
                        let msg = res.data.msg;
                        this.fileIds = this.fileIds.concat(res.data.id);
                        if (msg == '上传成功') {
                            this.$message({
                                message: '上传成功',
                                type: 'success'
                            });
                            this.getJssqJC();
                        } else {
                            this.$message.error('上传失败！');
                        }
                        this.fileList = [];
                    });
                    /*   }*/
                } else {
                    this.$message.warning('未选择任何文件！');
                }
            },

            //预览
            downloadFile_vue(index,row,flag){
                let formData = new FormData()
                formData.append("path",row.fjlj);
                // let params = {
                // 	path:row.fjlj
                // }
                // let instance = axios.create({
                // 	baseURL:'http://localhost:8080',
                // 	headers:{
                // 		'Content-Type': 'application/json; application/octet-stream'
                // 	},
                // 	responseType: 'blob',
                //
                // });
                download_sjst(formData).then((res)=>{
                    if(flag==1){
                        this.slide1=[];
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
                    }else{
                        const content = res.data
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
                // instance.post('/sjstController/download',formData).then((res) => { // 处理返回的文件流
                //
                //
                //
                // })
            },
        },
        //大写
        filters: {//局部过滤器

            toChies: function (money) {

                //汉字的数字
                var cnNums = new Array('零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖');
                //基本单位
                var cnIntRadice = new Array('', '拾', '佰', '仟');
                //对应整数部分扩展单位
                var cnIntUnits = new Array('', '万', '亿', '兆');
                //对应小数部分单位
                var cnDecUnits = new Array('角', '分', '毫', '厘');
                //整数金额时后面跟的字符
                var cnInteger = '整';
                //整型完以后的单位
                var cnIntLast = '元';
                //最大处理的数字
                var maxNum = 999999999999999.9999;
                //金额整数部分
                var integerNum;
                //金额小数部分
                var decimalNum;
                //输出的中文金额字符串
                var chineseStr = '';
                //分离金额后用的数组，预定义
                var parts;
                if (money == '') {
                    return '';
                }
                money = parseFloat(money);
                if (money >= maxNum) {
                    //超出最大处理数字
                    return '';
                }
                if (money == 0) {
                    chineseStr = cnNums[0] + cnIntLast + cnInteger;
                    return chineseStr;
                }
                //转换为字符串
                money = money.toString();
                if (money.indexOf('.') == -1) {
                    integerNum = money;
                    decimalNum = '';
                } else {
                    parts = money.split('.');
                    integerNum = parts[0];
                    decimalNum = parts[1].substr(0, 4);
                }
                //获取整型部分转换
                if (parseInt(integerNum, 10) > 0) {
                    var zeroCount = 0;
                    var IntLen = integerNum.length;
                    for (var i = 0; i < IntLen; i++) {
                        var n = integerNum.substr(i, 1);
                        var p = IntLen - i - 1;
                        var q = p / 4;
                        var m = p % 4;
                        if (n == '0') {
                            zeroCount++;
                        } else {
                            if (zeroCount > 0) {
                                chineseStr += cnNums[0];
                            }
                            //归零
                            zeroCount = 0;
                            chineseStr += cnNums[parseInt(n)] + cnIntRadice[m];
                        }
                        if (m == 0 && zeroCount < 4) {
                            chineseStr += cnIntUnits[q];
                        }
                    }
                    chineseStr += cnIntLast;
                }
                //小数部分
                if (decimalNum != '') {
                    var decLen = decimalNum.length;
                    for (var i = 0; i < decLen; i++) {
                        var n = decimalNum.substr(i, 1);
                        if (n != '0') {
                            chineseStr += cnNums[Number(n)] + cnDecUnits[i];
                        }
                    }
                }
                if (chineseStr == '') {
                    chineseStr += cnNums[0] + cnIntLast + cnInteger;
                } else if (decimalNum == '') {
                    chineseStr += cnInteger;
                }
                return chineseStr;
            }

        },
    }
</script>
<style>
    .border-table {
        border-collapse: collapse;
        border: none;
        width: 100%;
    }

    .border-table td {
        border: solid rgb(235, 237, 240) 1px;
        width: 8%;
        height: 40px;
        text-align: center;
    }
</style>

