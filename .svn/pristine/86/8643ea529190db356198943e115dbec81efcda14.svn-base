<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true" :model="filters" @submit.native.prevent>
                <el-form-item label="模板名称">
                    <el-input size="small" v-model="filters.name"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" size="mini" v-on:click="getValues"><i class="iconfont icon-chaxun"></i>查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" size="mini" @click="handleAdd"><i class="iconfont icon-xinzeng"></i>新增模板</el-button>
                </el-form-item>
            </el-form>
        </el-col>

        <!--列表-->
        <el-table :data="htmbList" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
            <el-table-column type="index" width="60">
            </el-table-column>
            <el-table-column prop="mbbh" label="模板编号" width="100" sortable>
            </el-table-column>
            <el-table-column prop="mbmc" label="模板名称" min-width="100" sortable>
            </el-table-column>
            <el-table-column prop="mblx" label="模板类型" width="100" :formatter="formatMblx" sortable>
            </el-table-column>
            <el-table-column prop="mbbm" label="部门" width="100" :formatter="formatMbbm" sortable>
            </el-table-column>
            <el-table-column prop="mbnr" label="模板内容" width="100" :formatter="formatMbnr" sortable>
            </el-table-column>
            <el-table-column prop="cjr" label="创建人" width="100" sortable>
            </el-table-column>
            <el-table-column prop="cjsj" label="创建时间" width="100" :formatter="formatCjsj" sortable>
            </el-table-column>
            <el-table-column prop="xgr" label="修改人" width="100" sortable>
            </el-table-column>
            <el-table-column prop="xgsj" label="修改时间" width="100" :formatter="formatXgsj" sortable>
            </el-table-column>
            <el-table-column prop="scFlag" label="是否删除" width="100" sortable>
            </el-table-column>
            <el-table-column label="操作" width="120">
                <template slot-scope="scope">
                    <label title="编辑" @click="handleEdit(scope.$index, scope.row)">
                        <i class="iconfont icon-ai-edit" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                    <label title="删除" @click="handleDel(scope.$index, scope.row)">
                        <i class="iconfont delete" style="cursor:pointer;padding: 2px;color: #f78989"></i></label>
                    <!--<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>-->
                </template>
            </el-table-column>
        </el-table>

        <!--工具条-->
        <el-col :span="24" class="toolbar">
            <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="this.size" :total="this.total" style="float:right;">
            </el-pagination>
        </el-col>

        <!--编辑界面-->
        <el-dialog title="编辑" :visible.sync ="editFormVisible" @close = "editClose">
            <el-form :model="editForm" label-width="100px" ref="editForm"  size="small" :inline="true">
                <div>
                    <el-form-item label="模板编号" prop="name">
                        <el-input v-model="editForm.mbbh" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="模板名称" prop="name">
                        <el-input v-model="editForm.mbmc" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="模板类型" prop="name">
                        <el-select v-model="editForm.mblx" @change="getMbByLx(value)">
                            <el-option v-for="item in mblxCounts"
                                       :key="item.value"
                                       :label="item.label"
                                       :value="item.value"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="部门" prop="name">
                        <el-cascader
                                :options="mbbmCounts"
                                :show-all-levels="false"
                                @change="getMbByBm(value)"
                        ></el-cascader>
                    </el-form-item>
                </div>
                <div>
                    <UEditor :defaultMsg=defaultMsg :config=config ref="ue"></UEditor>
                </div>
            </el-form>
            <div slot="footer" class="dialog-footer" style="text-align: center">
                <el-button size="mini" type="info" @click.native="editFormVisible = false">
                    <i class="iconfont icon-quxiao"></i>取消</el-button>

                <el-button size="mini" type="primary" @click="editSubmit" :loading="editLoading">
                    <i class="iconfont icon-queren"></i>提交</el-button>
            </div>
        </el-dialog>

        <!--新增界面-->
        <el-dialog title="新增" :visible.sync ="addFormVisible" @close = "editClose">
            <el-form :model="addForm" label-width="100px" ref="addForm" size="small" :inline="true">
                <div>
                    <el-form-item label="模板编号" prop="name">
                    <el-input v-model="addForm.mbbh" auto-complete="off" style="width: 200px;"></el-input>
                    </el-form-item>
                    <el-form-item label="模板名称" prop="name">
                        <el-input v-model="addForm.mbmc" auto-complete="off" style="width: 200px;"></el-input>
                    </el-form-item>
                    <el-form-item label="模板类型" prop="name">
                        <el-select v-model="addForm.mblx" @change="getMbByLx(value)" style="width: 200px;">
                            <el-option v-for="item in mblxCounts"
                                       :key="item.value"
                                       :label="item.label"
                                       :value="item.value"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="部门" prop="name">
                        <el-cascader style="width: 200px;"
                                :options="mbbmCounts"
                                :show-all-levels="false"
                                @change="getMbByBm(value)"
                        ></el-cascader>
                    </el-form-item>
                </div>
                <el-button type="primary" size="mini" @click="getUEContent" style="margin: 10px;">获取内容</el-button>
                <div>
                    <UEditor :defaultMsg=defaultMsg :config=config ref="ue"></UEditor>
                </div>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button size="mini" type="info" @click.native="addFormVisible = false">
                    <i class="iconfont icon-quxiao"></i>取消</el-button>

                <el-button size="mini" type="primary" @click.native="addSubmit" :loading="addLoading">
                    <i class="iconfont icon-queren"></i>提交</el-button>
            </div>
        </el-dialog>
    </section>
</template>

<script>
    import util from '../../common/js/util'
    import Vue from 'vue'
    //import VueResource from 'vue-resource';
    //Vue.use(VueResource)
    //import NProgress from 'nprogress'
    import { xtgl_htmb,saveHtmb,remove } from '../../api/Htmb';
    import UEditor from '../../UE.vue';

    export default {
        components: {UEditor},
        inject:['reload'],
        data() {
            return {
                filters: {
                    name: ''
                },
                htmbList: [],
                total: 10,//表格数据总数量
                page: 1,//当前页码
                size: 10,//每页容量
                listLoading: false,
                sels: [],//列表选中列
                editFormVisible: false,//编辑界面是否显示
                editLoading: false,
                //编辑界面数据
                editForm: {
                    wybs: '',
                    mbbh: '',
                    mbmc: '',
                    mblx: '',
                    mbbm: '',
                    mbnr: '',
                    cjr: '',
                    cjsj: '',
                    xgr: '',
                    xgsj: '',
                    scFlag: ''
                },
                addFormVisible: false,//新增界面是否显示
                addLoading: false,
                //新增界面数据
                addForm: {
                    wybs: '',
                    mbbh: '',
                    mbmc: '',
                    mblx: '',
                    mbbm: '',
                    mbnr: '',
                    cjr: '',
                    cjsj: '',
                    xgr: '',
                    xgsj: '',
                    scFlag: ''
                },
                defaultMsg:
                    '<p style="white-space: normal;"><span style="font-size: 19px; font-family: 宋体;">项目代码：<span style="text-decoration-line: underline; text-indent: 40px;">' +
                    '<span id="gsk_xmdm" style="font-size: 20px; line-height: 55px;">&nbsp; &nbsp; 500001&nbsp; &nbsp; &nbsp;&nbsp;</span></span></span></p><p style="white-space: normal; text-align: center;"><span style="font-size: 19px; font-family: 宋体;"><span style="font-size: 29px; font-family: 黑体;">供</span>&nbsp;<span style="font-size: 29px; font-family: 黑体;">水</span>&nbsp;<span style="font-size: 29px; font-family: 黑体;">工</span>&nbsp;<span style="font-size: 29px; font-family: 黑体;">程</span>&nbsp;<span style="font-size: 29px; font-family: 黑体;">委</span>&nbsp;<span style="font-size: 29px; font-family: 黑体;">托</span>&nbsp;<span style="font-size: 29px; font-family: 黑体;">代</span>&nbsp;<span style="font-size: 29px; font-family: 黑体;">建</span>&nbsp;<span style="font-size: 29px; font-family: 黑体;">合</span>&nbsp;<span style="font-size: 29px; font-family: 黑体;">同</span></span></p><p style="white-space: normal;">' +
                    '<span style="font-size: 20px; line-height: 40px; font-family: 黑体;">工程名称：</span><span style="text-decoration-line: underline;"><span style="font-size: 20px; line-height: 40px;">&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;' +
                    '<span style="font-family: 黑体;" id="gsk_gcmc">供水安装工程&nbsp; &nbsp;&nbsp;</span></span></span></p><p style="white-space: normal; line-height: 32px;">' +
                    '<span style="text-indent: 40px; font-size: 20px; line-height: 40px; font-family: 黑体;">工程地点：</span><span style="text-indent: 40px; text-decoration-line: underline;"></span><span style="text-indent: 40px; text-decoration-line: underline;">' +
                    '<span style="font-size: 20px; line-height: 55px; font-family: 黑体;" id="gsk_gcdd">&nbsp; &nbsp;地点1 &nbsp;&nbsp; &nbsp; &nbsp;</span></span><span style="text-indent: 40px; text-decoration-line: underline;"><span style="font-size: 20px; line-height: 55px;">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</span></span></p><p style="white-space: normal; text-align: center;"><span style="font-size: 19px; font-family: 宋体;"><span style="font-size: 29px; font-family: 黑体;"><strong><span style="font-size: 21px; font-family: 宋体;">供水工程委托代建合同书</span></strong></span></span><br/></p><p style="white-space: normal;"><span style="font-size: 19px; font-family: 宋体;"><span style="font-size: 29px; font-family: 黑体;"><strong><span style="font-size: 21px; font-family: 宋体;"></span></strong></span></span></p><p style="white-space: normal; line-height: 27px;"><strong>' +
                    '<span style="font-family: 宋体;" id="gsk_jf">甲方：</span></strong></p><p style="margin-bottom: 2px; white-space: normal; line-height: 29px;"><strong>' +
                    '<span style="font-family: 宋体;" id="gsk_yf">乙方：合肥供水集团有限公司</span></strong></p><p style="margin-bottom: 2px; white-space: normal; line-height: 29px;"><strong><span style="font-family: 宋体;"></span></strong></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;">依据《中华人民共和国合同法》等法律、法规，为明确本项目中双方的权利、义务，双方遵循平等、自愿、公平和诚实信用的原则，经协商同意，签订本合同。</p><p style="margin-bottom: 2px; white-space: normal; text-indent: 32px; line-height: 29px;"><strong><span style="font-family: 宋体;">一、项目概况</span></strong></p><p style="margin-bottom: 2px; white-space: normal; text-indent: 32px; line-height: 29px;"><span style="font-family: 宋体;">1</span>' +
                    '<span style="font-family: 宋体;">、项目名称：<span style="text-decoration-line: underline;">&nbsp;</span></span><span style="text-decoration-line: underline;">' +
                    '<span style="font-family: 宋体;" id="gsk_xmmc">&nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp;</span></span></p><p style="margin-bottom: 2px; white-space: normal; text-indent: 32px; line-height: 29px;"><span style="font-family: 宋体;">2</span>' +
                    '<span style="font-family: 宋体;">、项目地点：<span style="text-decoration-line: underline;" id="gsk_xmdd">&nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</span></span></p><p style="margin-bottom: 2px; white-space: normal; text-indent: 32px; line-height: 29px;"><span style="font-family: 宋体;">3</span><span style="font-family: 宋体;">、项目供水工程范围</span><span style="font-family: 宋体;">：' +
                    '<span style="text-decoration-line: underline;">表前管</span> <span id="gsk_bqg" style="text-decoration-line: underline;">&nbsp; &nbsp;</span> ' +
                    '<span style="text-decoration-line: underline;">米，工商表 </span><span id="gsk_gsb" style="text-decoration-line: underline;">&nbsp; &nbsp;</span> <span style="text-decoration-line: underline;">只。</span></span></p><p style="white-space: normal; text-indent: 31px; line-height: 29px;"><strong>二、代建内容</strong></p><p style="white-space: normal; line-height: 29px;"><span style="font-family: 宋体;">&nbsp;&nbsp;&nbsp; 1</span><span style="font-family: 宋体;">、本项目供水工程设计（审图）服务。&nbsp; &nbsp; &nbsp; &nbsp;</span></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;"><span style="font-family: 宋体;">&nbsp; 2</span><span style="font-family: 宋体;">、本项目供水工程监理服务。</span></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;"><span style="font-family: 宋体;">&nbsp; 3</span><span style="font-family: 宋体;">、本项目供水工程施工、安装。</span></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;"><span style="font-family: 宋体;">&nbsp; 4</span><span style="font-family: 宋体;">、本项目供水工程建设所需的全部主辅材料及加压设备。</span></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;"><span style="font-family: 宋体;">&nbsp; 5</span><span style="font-family: 宋体;">、本项目供水工程建设涉及土方（非市政）开挖、回填。</span></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;"><span style="font-family: 宋体;">&nbsp; 6</span><span style="font-family: 宋体;">、本项目供水工程试压、冲洗、消毒、水质检测及水表勾点通水工作。&nbsp;</span>&nbsp;&nbsp;&nbsp;&nbsp;</p><p style="white-space: normal; text-indent: 32px; line-height: 29px;"><span style="font-family: 宋体;">&nbsp; 7</span><span style="font-family: 宋体;">、本项目供水工程涉及的设计、施工、监理、采购等工作由乙方另行与具体实施单位签订设计、施工、监理、采购合同。&nbsp;&nbsp;</span></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;"><span style="font-family: 宋体;">&nbsp; 8</span>' +
                    '<span style="font-family: 宋体;">、其他：</span><span style="text-decoration-line: underline;" id="gsk_qt">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><p style="margin-top: 0px; margin-bottom: 2px; white-space: normal; text-indent: 32px; line-height: 29px;"><strong><span style="font-family: 宋体;">三、工程建设费用</span></strong></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;"><span style="font-family: 宋体;">1</span>' +
                    '<span style="font-family: 宋体;">、本项目费用共计（固定价）：</span><strong><span style="text-decoration-line: underline;">¥</span></strong><strong><span style="text-decoration-line: underline;">' +
                    '<span style="font-family: 宋体;" id="gsk_zfy">&nbsp; &nbsp; &nbsp; &nbsp;</span></span></strong><span style="font-family: 宋体;">元。⑴设计费：</span><strong><span style="text-decoration-line: underline;">¥</span></strong><strong><span style="text-decoration-line: underline;">' +
                    '<span style="font-family: 宋体;" id="gsk_sjf">&nbsp; &nbsp; &nbsp;&nbsp;</span></span></strong><span style="text-decoration-line: underline;"><span style="font-family: 宋体;">元</span></span><span style="font-family: 宋体;">，⑵监理费：</span><strong><span style="text-decoration-line: underline;">¥</span></strong><strong><span style="text-decoration-line: underline;">' +
                    '<span style="font-family: 宋体;" id="gsk_jlf">&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;</span></span></strong><span style="text-decoration-line: underline;"><span style="font-family: 宋体;">元，</span></span><span style="font-family: 宋体;">⑶工程费：</span><strong><span style="text-decoration-line: underline;">¥</span></strong><strong><span style="text-decoration-line: underline;">' +
                    '<span style="font-family: 宋体;" id="gsk_gcf">&nbsp; &nbsp;&nbsp; &nbsp;</span></span></strong><span style="text-decoration-line: underline;"><span style="font-family: 宋体;">元。</span></span></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;"><span style="font-family: 宋体;">（详见经双方确认的预算清单）。</span></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;">2、<span style="font-family: 宋体;">本项目工程费中材料价格执行相应季度《合肥供水集团有限公司供水材料价格表》计算，材料价格表中没有的材料单价依据当月的市场信息价或市场询价。</span></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;"><span style="font-family: 宋体;">3</span><span style="font-family: 宋体;">、本项目供水工程原则上不含以下内容：<span style="text-decoration-line: underline;">工商表表后管连接；确有需要的，甲方可委托乙方施工，费用由甲方承担。</span></span></p><p style="margin-top: 0px; margin-bottom: 2px; white-space: normal; text-indent: 31px; line-height: 29px;"><strong><span style="font-family: 宋体;">四、费用支付方式</span></strong></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;"><span style="font-family: 宋体;">1</span><span style="font-family: 宋体;">、支付方式</span></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;"><span style="font-family: 宋体;">⑴、</span><span style="text-decoration-line: underline;">' +
                    '<span style="font-family: 宋体;" id="gsk_zffs">本合同签订后一周内，甲方向乙方一次性付供水工程费（ 432</span></span><span style="text-decoration-line: underline;"><span style="font-family: 宋体;">元）</span></span></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;"><span style="font-family: 宋体;">2</span><span style="font-family: 宋体;">、工程结算</span></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;"><span style="text-decoration-line: underline;">' +
                    '<span style="font-family: 宋体;" id="gsk_jsfs">⑴、结算方式：预算范围内的固定总价。</span></span></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;"><span style="text-decoration-line: underline;">' +
                    '<span style="font-family: 宋体;" id="gsk_jszl">⑵、结算资料：&nbsp; &nbsp;2&nbsp;</span></span><span style="text-decoration-line: underline;"><span style="font-family: 宋体;">/&nbsp; &nbsp;43&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</span></span></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;"><span style="font-family: 宋体;">⑶、签证结算：如因现场情况发生变更或签证，增减工程量需办理经济签证，相应产生的工程费用在最终结算时计入工程总价。</span></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;"><span style="font-family: 宋体;">因工程变更和工程量增减引起的价格变化按下列规定调整：</span></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;"><span style="font-family: 宋体;">①</span><span style="font-family: 宋体;">、</span><span style="font-family: 宋体;">预算工程量清单综合单价中有适用的采用该项目单价；</span></p><p style="margin-left: 32px; white-space: normal; line-height: 29px;"><span style="font-family: 宋体;">②、</span><span style="font-family: 宋体;">预算工程量清单综合单价中没有适用但有类似的，参照类似项目单价；</span></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;"><span style="font-family: 宋体;">③</span><span style="font-family: 宋体;">、</span><span style="font-family: 宋体;">预算工程量清单综合单价没有适用也没有类似的，依据预算计算规则和计价办法，按照2018版安徽省</span><span style="font-family: 宋体;">建设工程计价依据执行，主材价格按合肥供水集团有限公司当期材料价格表</span><span style="font-family: 宋体;">,</span><span style="font-family: 宋体;">定额中所含材料价格调整执行当月合肥市市场价格信息。</span></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;"><span style="font-family: 宋体;">④</span><span style="font-family: 宋体;">、若上述情况都没有，则由甲乙双方协商确定价格。</span></p><p style="margin-bottom: 2px; white-space: normal; text-indent: 32px; line-height: 29px;"><strong><span style="font-family: 宋体;">五、工程工期</span></strong></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;">1、甲方按合同款支付工程费用，待具备建设条件（现场、资料）后，乙方一周内安排施工单位进场，并根据甲方现场进度合理编排施工计划，具体工期经甲乙双方协商为总日历日天数<span style="text-decoration-line: underline;">&nbsp; &nbsp;13&nbsp;&nbsp;</span>天，开工日期以下达开工令为准。</p><p style="margin-bottom: 2px; white-space: normal; text-indent: 32px; line-height: 29px;">2<span style="font-family: 宋体;">、</span><span style="font-family: 宋体;">若因现场工地条件制约、不可抗拒或甲方原因造成工期延误的，工期相应顺延，工程工期顺延必须有经甲、乙双方以书面文字签字确定认可。</span></p><p style="margin-bottom: 2px; white-space: normal; text-indent: 32px; line-height: 29px;"><strong><span style="font-family: 宋体;">六、双方责任</span></strong></p><p style="white-space: normal; line-height: 24px;"><span style="font-size: 20px; line-height: 30px; font-family: 仿宋_GB2312;">&nbsp;&nbsp;&nbsp;</span><span style="line-height: 24px; font-family: 宋体;">（一）甲方责任</span></p><p style="white-space: normal; text-indent: 32px; line-height: 24px;"><span style="line-height: 24px; font-family: 宋体;">1</span><span style="line-height: 24px; font-family: 宋体;">、按照法律规定履行项目审批手续，在规定的时间内向乙方提供设计、施工、监理等各项基础资料及文件，并对其完整性、正确性、及时性负责；</span><span style="line-height: 24px; font-family: 宋体;">及时对供水设计图纸进行审核确认；</span><span style="line-height: 24px; font-family: 宋体;">不得要求乙方违反国家有关标准进行设计、施工、监理。</span></p><p style="white-space: normal; text-indent: 32px; line-height: 24px;"><span style="line-height: 24px; font-family: 宋体;">2</span><span style="line-height: 24px; font-family: 宋体;">、应确保现场具备施工条件，包括但不限于以下内容：（1）确保运输道路、通道（含货梯）的畅通、施工现场满足供水施工所需的水电要求；（2）提供施工过程中乙方施工材料和工具的堆放及安全场所；（3）需提供工程地质和地下管线资料，协调处理施工场地周围地下管线和邻近建筑物、构筑物（含文物保护建筑）、古树名木的保护工作；（4）负责施工过程中现场或不可预见的施工阻碍物清除及与相关部门协调工作，负责办理施工所涉及到的市政道路（含人行道板）破复、园林绿化迁移、土方外运等相关手续，承担相关费用。</span></p><p style="white-space: normal; text-indent: 32px; line-height: 29px;"><span style="font-family: 宋体;">3</span><span style="font-family: 宋体;">、负责按照合同约定期限和方式按时支付，因甲方原因超过60天未开工的，乙方有权根据政策因素或市场因素发生的变化调整预算，甲方予以配合，并按调整后的预算支付费用。</span></p><p style="white-space: normal; text-indent: 32px; line-height: 24px;"><span style="line-height: 24px; font-family: 宋体;">4</span><span style="line-height: 24px; font-family: 宋体;">、安排专人负责施工现场配合及协调，如因交叉作业造成已建供水管道或供水设施损坏，需及时协调处理、现场情况确认，由此增加的费用和（或）延误的工期由甲方承担。</span></p><p style="white-space: normal; text-indent: 32px; line-height: 24px;"><span style="line-height: 24px; font-family: 宋体;">5</span><span style="line-height: 24px; font-family: 宋体;">、负责及时做好工程施工现场签证、相关资料提供、预决算认定、工程验收移交配合工作，如因甲方配合不到位或甲方原因发生工程部分的变更，导致工程停工、返工的，由甲方承担由此增加的费用和（或）延误的工期。</span></p><p style="white-space: normal; text-indent: 32px; line-height: 24px;"><span style="line-height: 24px; font-family: 宋体;">6</span><span style="line-height: 24px; font-family: 宋体;">、工程完工后，如因甲方原因导致供水系统未能移交的，成品保护由甲方负责；对含有二次供水生活泵房的项目，在二次供水设施移交前需办理二次供水泵房独立电表报装等相关手续。</span><span style="line-height: 24px; font-family: 宋体;"></span></p><p style="white-space: normal; text-indent: 32px; line-height: 24px;"><span style="line-height: 24px; font-family: 宋体;">7</span><span style="line-height: 24px; font-family: 宋体;">、工程竣工验收后，甲方不得改动供水管线，由此产生的后果由甲方负责；甲方确需更改供水管线的，应以书面形式通知乙方，乙方按相关程序规范更改，所产生的费用由甲方承担。</span></p><p style="white-space: normal; text-indent: 32px; line-height: 24px;"><span style="line-height: 24px; font-family: 宋体;">8</span><span style="line-height: 24px; font-family: 宋体;">、</span><span style="line-height: 24px; font-family: 宋体;">本项目工程未经竣工验收或未按照相关通水程序以及甲方未按本合同约定付清全部工程费用前，不得提前挂表通水；在办理供水工程评定确认手续前，需办理供水工程移交工作中甲方必须完善的事项，同时向购房者或用户告知供水工程建设情况</span><span style="font-family: 宋体;">。</span></p><p style="white-space: normal; text-indent: 24px; line-height: 24px;"><span style="line-height: 24px; font-family: 宋体;">（二）乙方责任</span></p><p style="white-space: normal; text-indent: 32px; line-height: 24px;"><span style="line-height: 24px; font-family: 宋体;">1</span><span style="line-height: 24px; font-family: 宋体;">、按照甲方需求及供水工程的相关规范要求，依据相关法律法规、国家技术规范组织设计、施工、监理，确保本合同范围内的工程按期实施、顺利验收移交。</span></p><p style="white-space: normal; text-indent: 32px; line-height: 24px;"><span style="line-height: 24px; font-family: 宋体;">2</span><span style="line-height: 24px; font-family: 宋体;">、甲方按合同约定支付首期款项后，乙方向甲方提供设计成果，并及时联系甲方开展施工进场前的准备工作。</span></p><p style="white-space: normal; text-indent: 32px; line-height: 24px;"><span style="line-height: 24px; font-family: 宋体;">3</span><span style="line-height: 24px; font-family: 宋体;">、严格要求施工单位按图施工，确保工程范围内工程材料、工程质量符合规范要求。</span></p><p style="white-space: normal; text-indent: 32px; line-height: 24px;"><span style="line-height: 24px; font-family: 宋体;">4</span><span style="line-height: 24px; font-family: 宋体;">、在施工过程中应做到安全、文明施工，对属于乙方责任的工程质量问题，乙方负责修理和返工，承担相应的责任。</span></p><p style="white-space: normal; text-indent: 32px; line-height: 24px;"><span style="line-height: 24px; font-family: 宋体;">5</span><span style="line-height: 24px; font-family: 宋体;">、组织协调施工全过程设计、监理服务，并按甲方需求安排有关技术人员到现场进行配合、解决相关问题，直至项目竣工验收为止。</span></p><p style="white-space: normal; text-indent: 32px; line-height: 24px;"><span style="line-height: 24px; font-family: 宋体;">6</span><span style="line-height: 24px; font-family: 宋体;">、按照给水管道施工规范的试压标准和规定进行管道试压、消毒冲洗、水质检测工作。</span></p><p style="white-space: normal; text-indent: 32px; line-height: 24px;"><span style="line-height: 24px; font-family: 宋体;">7</span><span style="line-height: 24px; font-family: 宋体;">、按照相关程序及标准办理供水工程验收移交手续。</span></p><p style="margin-bottom: 2px; white-space: normal; text-indent: 32px; line-height: 29px;"><strong><span style="font-family: 宋体;">七、违约责任及合同争议解决方式</span></strong></p><p style="margin-bottom: 2px; white-space: normal; text-indent: 24px; line-height: 29px;"><span style="font-family: 宋体;">&nbsp;1</span><span style="font-family: 宋体;">、</span><span style="font-family: 宋体;">双方在合同履行期间，如一方违约，给另一方造成损失，由违约方按实际损失给予对方经济赔偿。</span></p><p style="white-space: normal; text-indent: 32px; line-height: 24px;"><span style="line-height: 24px; font-family: 宋体;">2</span><span style="line-height: 24px; font-family: 宋体;">、本合同在履行过程中发生争议时，当事人双方应及时协商解决。协商不成时，双方可向合同履行地人民法院起诉。</span></p><p style="margin-bottom: 2px; white-space: normal; text-indent: 32px; line-height: 29px;"><strong><span style="font-family: 宋体;">八、其他</span></strong></p><p style="white-space: normal; text-indent: 32px; line-height: 24px;"><span style="line-height: 24px; font-family: 宋体;">1</span><span style="line-height: 24px; font-family: 宋体;">、由于不可抗力因素致使合同无法履行时，双方应及时协商解决；协调不成的，按照合肥市有关部门鉴定为准。</span></p><p style="white-space: normal; text-indent: 32px; line-height: 24px;"><span style="line-height: 24px; font-family: 宋体;">2</span><span style="line-height: 24px; font-family: 宋体;">、</span><span style="line-height: 24px; font-family: 宋体;">如合同履行期间发生政策性调整，按规定执行。</span></p><p style="margin-bottom: 2px; white-space: normal; text-indent: 40px; line-height: 29px;"><span style="font-family: 宋体;">本合同未尽事宜，由合同双方友好协商解决，可签订补充协议作为附件，补充协议与本合同具有同等效力</span><span style="font-family: 宋体;">。</span></p><p style="white-space: normal;"><strong><span style="font-family: 宋体;">&nbsp;&nbsp;九、本合同一式贰份，甲方执壹份，乙方执壹份；本合同经双方盖章后生效。</span></strong></p><p style="white-space: normal;"><strong><span style="font-family: 宋体;"><br/></span></strong></p><p style="white-space: normal;"><strong><span style="font-family: 宋体;"><br/></span></strong></p><p style="white-space: normal;"><strong><span style="font-family: 宋体;"></span></strong></p>' +
                    '<p style="white-space: normal; line-height: 24px;"><span style="font-size: 19px; line-height: 28.5px; font-family: 宋体;">甲方：（公 章）&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;乙方：（公 章）</span></p><p style="white-space: normal; line-height: 29px;">' +
                    '<span style="font-size: 19px; font-family: 宋体;">法定代表人：</span>' +
                    '<span style="font-size: 19px; font-family: 宋体;" id="gsk_jfdbr">甲某某&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</span> ' +
                    '<span style="font-size: 19px; font-family: 宋体;">法定代表人：</span>' +
                    '<span style="font-size: 19px; font-family: 宋体;" id="gsk_yfdbr">&nbsp;乙某某&nbsp; </span></p>' +
                    '<p style="white-space: normal; line-height: 29px;"><span style="font-size: 19px; font-family: 宋体;">委托代理人：</span>' +
                    '<span style="font-size: 19px; font-family: 宋体;" id="gsk_jfdlr">甲代理&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </span>' +
                    '<span style="font-size: 19px; font-family: 宋体;">委托代理人：</span>' +
                    '<span style="font-size: 19px; font-family: 宋体;" id="gsk_yfdlr">乙代理&nbsp; &nbsp;</span></p>' +
                    '<p style="white-space: normal;"><span style="font-size: 19px; font-family: 宋体;">签约时间：</span>' +
                    '<span style="font-size: 19px; font-family: 宋体;" id="gsk_jfqysj">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</span>' +
                    '<span style="font-size: 19px; font-family: 宋体;">签约时间：</span>' +
                    '<span style="font-size: 19px; font-family: 宋体;" id="gsk_yfqysj">2018/2/13</span></p>' +
                    '<p style="white-space: normal;"><span style="font-size: 19px; font-family: 宋体;">甲方名称：</span>' +
                    '<span style="font-size: 19px; font-family: 宋体;" id="gsk_jfmc"> 甲方&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</span>' +
                    '<span style="font-size: 19px; font-family: 宋体;"> 乙方名称：</span>' +
                    '<span style="font-size: 19px; font-family: 宋体;" id="gsk_yfmc">乙方&nbsp; &nbsp;</span></p>' +
                    '<p style="white-space: normal;"><span style="font-size: 19px; font-family: 宋体;">开户行：</span>' +
                    '<span style="font-size: 19px; font-family: 宋体;" id="gsk_jfkhh">工商银行合肥淮河路支行&nbsp; &nbsp;</span>' +
                    '<span style="font-size: 19px; font-family: 宋体;"> 开户行：</span>' +
                    '<span style="font-size: 19px; font-family: 宋体;" id="gsk_yfkhh">工商银行合肥淮河路支行&nbsp;</span></p>' +
                    '<p style="white-space: normal;"><span style="font-size: 19px; font-family: 宋体;">账号：</span>' +
                    '<span style="font-size: 19px; font-family: 宋体;" id="gsk_jfzh">1302010409022100556&nbsp; &nbsp; &nbsp; &nbsp;</span>' +
                    '<span style="font-size: 19px; font-family: 宋体;">账号：</span>' +
                    '<span style="font-size: 19px; font-family: 宋体;" id="gsk_yfzh">1302010409022100556&nbsp;</span></p><p><br/></p>',
                config: {
                    autoHeightEnabled: false,
                    autoFloatEnabled: true,
                    initialFrameWidth: null,
                    initialFrameHeight: 450,
                },
                //模板类型下拉框数值集合
                mblxCounts:[{
                    value:'1',
                    label:'合同'
                },{
                    value:'2',
                    label:'协议'
                }],
                //模板部门下拉框数值集合 1工商科2居民科3户改科
                mbbmCounts: [{
                    value: '1',
                    label: '工商科',
                }, {
                    value: '2',
                    label: '居民科',
                    children:[{
                        value: '2',
                        label: '自建'
                    },{
                        value: '2',
                        label: '代建'
                    },{
                        value: '2',
                        label: '政府代建'
                    }]
                }, {
                    value: '3',
                    label: '户改科',
                    children: [{
                        value: '3',
                        label: '自建'
                    }, {
                        value: '3',
                        label: '代建'
                    }]
                }]
            }
        },
        //Enter键监听执行查询
        created() {
            let lett = this;
            document.onkeydown = function(e) {
                var key = window.event.keyCode;
                if (key == 13) {
                    lett.getValues();
                }
            }
        },
        methods: {
            //创建时间格式转换
            formatCjsj: function (row, column) {
                let sj = row.cjsj;
                return this.publicUtil.formatChange(sj);
            },
            //修改时间格式转换
            formatXgsj: function (row, column) {
                let sj = row.xgsj;
                return this.publicUtil.formatChange(sj);
            },
            //模板类型转换
            formatMblx: function (row, column) {
                return row.mblx == 1 ? '合同' : row.mblx == 2 ? '协议' : '未知';
            },
            //模板部门转换
            formatMbbm: function (row, column) {
                return row.mbbm == 1 ? '工商科' : row.mbbm == 2 ? '居民科' : '户改科';
            },
            formatMbnr: function (row, column) {
                return '';
            },
            handleCurrentChange(val) {
                this.page = val;
                this.getValues();
            },
            //获取用户列表
            getValues() {
                let para = {
                    page: this.page - 1,
                    size: this .size,
                    mbmc: this.filters.name
                };
                this.listLoading = true;
                //NProgress.start();
                xtgl_htmb(para).then((res) => {
                    this.total = res.data.total;
                    this.htmbList = res.data.row;
                    this.listLoading = false;
                    //NProgress.done();
                });
            },
            //删除
            handleDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = { wybs: row.wybs };
                    remove(para).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getValues();
                    });
                }).catch(() => {

                });
            },
            //显示编辑界面
            handleEdit: function (index, row) {
                this.editFormVisible = true;
                this.editForm = Object.assign({}, row);//编辑表单的赋值
                this.defaultMsg = row.mbnr;
            },
            //显示新增界面
            handleAdd: function () {
                this.addFormVisible = true;
                this.addForm = {
                    wybs: '',
                    mbbh: '',
                    mbmc: '',
                    mblx: '1',
                    mbbm: '1',
                    mbnr: '',
                    cjr: '',
                    cjsj: '',
                    xgr: '',
                    xgsj: '',
                    scFlag: ''
                };
            },
            //编辑
            editSubmit: function () {
                this.$refs.editForm.validate((valid) => {
                    if (valid) {
                        //this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.editLoading = true;
                            //NProgress.start();
                            this.editForm.mbnr = this.$refs['ue'].getUMContent();
                            let para = Object.assign({}, this.editForm);
                            saveHtmb(para).then((res) => {
                                this.editLoading = false;
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['editForm'].resetFields();
                                this.editFormVisible = false;
                                this.getValues();
                            });
                        //});
                    }
                });
            },
            //新增
            addSubmit: function () {
                this.$refs.addForm.validate((valid) => {
                    if (valid) {
                        //this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.addLoading = true;
                            //NProgress.start();
                            this.addForm.mbnr = this.$refs['ue'].getUMContent();
                            let para = Object.assign({}, this.addForm);
                            saveHtmb(para).then((res) => {
                                this.addLoading = false;
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['addForm'].resetFields();
                                this.addFormVisible = false;
                                this.getValues();
                            });
                        //});
                    }
                });
            },
            selsChange: function (sels) {
                this.sels = sels;
            },
            //批量删除
            batchRemove: function () {
                var wfids = this.sels.map(item => item.wfid).toString();
                this.$confirm('确认删除选中记录吗？', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = { wfids: wfids };
                    batchRemoveBank(para).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getValues();
                    });
                }).catch(() => {

                });
            },
            getUEContent() {
                let content = this.$refs.ue.getUMContent(); // 调用子组件方法
                this.$notify({
                    title: '获取成功，可在控制台查看！',
                    message: content,
                    type: 'success'
                });
                /*console.log(content);*/
                var iframe = document.getElementsByTagName("iframe")[0];
                var win = iframe.contentWindow;  // 通过contentWindow获取ifame子页面的window窗体对象。(不允许跨域名访问)
                var iframeDocument = iframe.contentWindow.document;
                var input1 = iframeDocument.getElementById("gsk_xmdm").innerText;
                console.log(input1);
            },
            //模板类型下拉框触发方法
            getMbByLx(){

            },
            //模板部门下拉框触发方法
            getMbByBm(){
                // this.config.initialContent = "";
                // this.$refs.ue.setUMContent(this.config.initialContent);
            },
            editClose(){
                this.reload();
            }
        },
        mounted() {
            this.getValues();
        }
    }

</script>

<style>
</style>
