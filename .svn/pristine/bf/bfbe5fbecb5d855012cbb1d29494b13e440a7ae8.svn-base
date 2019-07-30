<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
    <section>
        <el-row>
            <el-col :span="24">
                <!--项目基本信息-->
                <XMJBXX></XMJBXX>

                <!--区所反馈-->
                <el-card class="box-card"  style="margin-top: 15px;" v-if="qsfk">
                    <div slot="header" class="clearfix">
                        <div class="div_title">
                            区所勘查结果
                            <span class="span_cz" @click="openBody('3')" v-if="!qsbody"><i class="el-icon-caret-right">点击展开</i></span>
                            <span class="span_cz" @click="closeBody('3')" v-if="qsbody"><i class="el-icon-caret-bottom">点击收起</i></span>
                        </div>

                    </div>
                    <div class="text item" v-if="qsbody">
                        <el-table :data="qsFk" border style="width: 100%;margin-top: 10px;">
                            <el-table-column type="expand">
                                <template slot-scope="props">
                                    <el-form label-position="left" label-width="100px" inline class="demo-table-expand">
                                        <!--<el-form-item label="商品名称">
                                            <span>{{ props.row.id }}</span>
                                        </el-form-item>-->
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
                                                <el-form-item label="调查点位置">
                                                    <span>{{props.row.dcdwz}}</span>
                                                </el-form-item>
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
                                                <el-form-item label="备注">
                                                    <span>{{props.row.bz}}</span>
                                                </el-form-item>
                                                <el-form-item label="测压点深度">
                                                    <span>{{props.row.cydsd}}</span>&nbsp;<span class="itext">米</span>
                                                </el-form-item>
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

                        <el-row style="margin-top: 20px;">
                            <el-col :span="24">
                                <el-form>
                                    <el-form-item label="区所反馈意见" style="width: 100%">
                                        <el-input type="textarea" v-model="qsFkyj" readonly  disabled></el-input>
                                    </el-form-item>
                                </el-form>
                            </el-col>
                        </el-row>
                    </div>
                </el-card>

                <!--施工单位反馈-->
                <el-card class="box-card"  style="margin-top: 15px;" v-if="sgdwfk">
                    <div slot="header" class="clearfix">
                        <div class="div_title">
                            施工单位勘查结果
                            <span class="span_cz" @click="openBody('2')" v-if="!sgdwbody"><i class="el-icon-caret-right">点击展开</i></span>
                            <span class="span_cz" @click="closeBody('2')" v-if="sgdwbody"><i class="el-icon-caret-bottom">点击收起</i></span>
                        </div>
                    </div>
                    <div class="text item" v-if="sgdwbody">
                        <el-form :model="sgdwFk" label-width="150px" ref="xmxxForm" >
                            <el-form-item label="">
                                <el-row>
                                    <el-col :span="4">
                                        <el-checkbox v-model="sgdwFk.sfycgc" true-label="1" false-label="0" readonly disabled>超高层</el-checkbox>
                                    </el-col>
                                    <el-col :span="10">
                                        <el-form-item label="">
                                            泵房供水位置
                                            <el-input type="text" size="small" v-model="sgdwFk.bfgswz" style="width: 180px;" readonly disabled></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                            </el-form-item>
                            <el-form-item label="">
                                <el-row>
                                    <el-col :span="4">
                                        <el-checkbox v-model="sgdwFk.sfyldsx" true-label="1" false-label="0" readonly disabled>楼顶水箱</el-checkbox>
                                    </el-col>
                                    <el-col :span="10" style="text-align: left;">
                                        <el-form-item label="">
                                            楼顶水箱位置
                                            <el-input type="text" size="small" v-model="sgdwFk.ldsxwz" style="width: 180px;" readonly disabled></el-input>
                                        </el-form-item>
                                    </el-col>
                                </el-row>
                            </el-form-item>
                            <el-form-item label="">
                                <el-row>
                                    <el-col :span="4">
                                        <el-checkbox v-model="sgdwFk.sfxydllhpf" true-label="1" false-label="0" readonly disabled>道路绿化破复</el-checkbox>
                                    </el-col>
                                    <el-col :span="4">
                                        <el-checkbox v-model="sgdwFk.sfxypzdb" true-label="1" false-label="0" readonly disabled>需要配置地表</el-checkbox>
                                    </el-col>
                                </el-row>
                            </el-form-item>
                            <el-form-item label="">
                                <el-row>
                                    <el-col :span="4">
                                        <el-radio v-model="sgdwFk.sfhykjbylzg" label="1" false-label="0" readonly disabled>有</el-radio>
                                        <el-radio v-model="sgdwFk.sfhykjbylzg" label="0" false-label="0" readonly disabled>否</el-radio>
                                    </el-col>
                                    <el-col :span="10">
                                        有可接表预留支管(若有在备注里说明)；
                                    </el-col>
                                </el-row>
                            </el-form-item>
                            <el-form-item label="">
                                <el-row>
                                    <el-col :span="4">
                                        <el-radio v-model="sgdwFk.sfxybqg" label="1" false-label="0" readonly disabled>有</el-radio>
                                        <el-radio v-model="sgdwFk.sfxybqg" label="0" false-label="0" readonly disabled>否</el-radio>
                                    </el-col>
                                    <el-col :span="20">
                                        需敷设表前管(约<el-input type="text" size="small" v-model="sgdwFk.bqgcd" style="width: 100px;" readonly disabled></el-input>m；
                                        接表需开挖面积约<el-input type="text" size="small" v-model="sgdwFk.jbxkwmj" style="width: 100px;" ref="jbxkwmj" readonly disabled> </el-input>㎡)；
                                        (包含过路管开挖敷设)
                                    </el-col>
                                </el-row>
                            </el-form-item>
                            <el-form-item label="">
                                <el-row>
                                    <el-col :span="4">
                                        <el-radio v-model="sgdwFk.sfxyzglg" label="1" false-label="0" readonly disabled>有</el-radio>
                                        <el-radio v-model="sgdwFk.sfxyzglg" label="0" false-label="0" readonly disabled>否</el-radio>
                                    </el-col>
                                    <el-col :span="20">
                                        接表是否需要做过路管(若需要做过路管，拖管距离约
                                        <el-input type="text" size="small" v-model="sgdwFk.tgjl" style="width: 100px;" readonly disabled></el-input>m)；
                                        顶管管尾处破复面积约<el-input type="text" size="small" v-model="sgdwFk.dggwcpfmj" style="width: 100px;" ref="dggwcpfmj" readonly disabled></el-input>㎡；
                                        <br>
                                        过路管勾点处破复面积约<el-input type="text" size="small" v-model="sgdwFk.glggdpfmj" style="width: 100px;" ref="glggdpfmj" readonly disabled></el-input>㎡
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

                        <el-row style="margin-top: 20px;">
                            <el-col :span="24">
                                <el-form>
                                    <el-form-item label="施工单位反馈意见" style="width: 100%">
                                        <el-input type="textarea" v-model="sgdwFkyj" readonly disabled></el-input>
                                    </el-form-item>
                                </el-form>
                            </el-col>
                        </el-row>
                    </div>
                </el-card>

                <!--设计院反馈-->
                <el-card class="box-card"  style="margin-top: 15px;" v-if="sjyfk">
                    <div slot="header" class="clearfix">
                        <div class="div_title">
                            设计院勘查结果
                            <span class="span_cz" @click="openBody('1')" v-if="!sjybody"><i class="el-icon-caret-right">点击展开</i></span>
                            <span class="span_cz" @click="closeBody('1')" v-if="sjybody"><i class="el-icon-caret-bottom">点击收起</i></span>
                        </div>
                    </div>
                    <div class="text item" v-if="sjybody">
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

                        <el-row style="margin-top: 20px;">
                            <el-col :span="24">
                                <el-form>
                                    <el-form-item label="设计院反馈意见" style="width: 100%">
                                        <el-input type="textarea" v-model="sjyFkyj" readonly disabled></el-input>
                                    </el-form-item>
                                </el-form>
                            </el-col>
                        </el-row>
                    </div>
                </el-card>

                <!--预算科-->
                <el-card class="box-card"  style="margin-top: 15px;" v-if="ysfk">
                    <div slot="header" class="clearfix">
                        <div class="div_title">
                            预算科勘查结果
                            <span class="span_cz" @click="openBody('4')" v-if="!ysbody"><i class="el-icon-caret-right">点击展开</i></span>
                            <span class="span_cz" @click="closeBody('4')" v-if="ysbody"><i class="el-icon-caret-bottom">点击收起</i></span>
                        </div>
                    </div>
                    <div class="text item" v-if="ysbody">
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
                                        <el-input type="textarea" v-model="ysFkyj" readonly disabled></el-input>
                                    </el-form-item>
                                </el-form>
                            </el-col>
                        </el-row>
                    </div>
                </el-card>

                <!--项目经理反馈评价-->
                <el-card class="box-card"  style="margin-top: 15px;width: 100%" v-if="xmjlfk">
                    <div slot="header" class="clearfix">
                        <div class="div_title">项目经理勘查反馈
                            <span class="span_cz" @click="openBody('5')" v-if="!xmjlbody"><i class="el-icon-caret-right">点击展开</i></span>
                            <span class="span_cz" @click="closeBody('5')" v-if="xmjlbody"><i class="el-icon-caret-bottom">点击收起</i></span>
                        </div>
                    </div>
                    <div class="text item" v-if="xmjlbody">
                        <el-row style="margin-top: 20px;">
                            <el-col :span="20">
                                <el-form label-width="150px" :model="kcpj" ref="kcpj" >
                                    <el-form-item label="实际勘查日期" style="width: 45%" prop="sjkcsj">
                                        <el-date-picker v-model="kcpj.sjkcsj" type="date" placeholder="选择日期" size="small"
                                                        value-format="yyyy-MM-dd" format="yyyy-MM-dd" readonly></el-date-picker>
                                    </el-form-item>
                                    <el-form-item label="勘查评价附件上传" style="width: 100%">
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
                                    </el-form-item>
                                    <el-form-item label="反馈意见" style="width: 100%">
                                        <el-input type="textarea" v-model="kcpj.xmjlFkyj" readonly disabled></el-input>
                                    </el-form-item>
                                </el-form>
                            </el-col>
                        </el-row>
                    </div>
                </el-card>

                <!--用户确认-->
                <el-card class="box-card"  style="margin-top: 15px;width: 100%" v-if="yhqr">
                    <div slot="header" class="clearfix">
                        <div class="div_title">用户确认勘查结果
                            <span class="span_cz" @click="openBody('6')" v-if="!yhqrbody"><i class="el-icon-caret-right">点击展开</i></span>
                            <span class="span_cz" @click="closeBody('6')" v-if="yhqrbody"><i class="el-icon-caret-bottom">点击收起</i></span>
                        </div>
                    </div>
                    <div class="text item" v-if="yhqrbody">
                        <el-row style="margin-top: 20px;">
                            <el-col :span="20">
                                <el-form label-width="150px" :model="kcpj" ref="kcpj" >
                                    <el-form-item label="客户同意时间" style="width: 45%" prop="sjkcsj">
                                        <el-date-picker v-model="khtysj" type="date" placeholder="选择日期" size="small"
                                                        value-format="yyyy-MM-dd" format="yyyy-MM-dd" readonly></el-date-picker>
                                    </el-form-item>
                                    <el-form-item label="客户同意勘查结果资料附件" style="width: 100%">
                                        <el-table :data="khtyData" border style="width: 100%;">
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
                                    </el-form-item>
                                </el-form>
                            </el-col>
                        </el-row>
                    </div>
                </el-card>

            </el-col>
        </el-row>
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
    .span_cz {
        display: inline-block;
        float: right;
        cursor: pointer;
        color: #666;
    }
</style>
<script>
    import XMJBXX from '@/views/template/xmjbxx.vue';
    import { getFkpjKcbm_sjst,download } from '../../api/xkylx';
    import {getSjstGwfk}from'../../api/sjst'

    export default {
        components: {
            'XMJBXX': XMJBXX  //将别名demo 变成 组件 Demo
        },
        data() {
            return {

                type: '5',
                qsfk : false,
                gwfk:true,
                sgdwfk : false,
                sjyfk : false,
                ysfk : false,
                xmjlfk : false,
                kcrwzbid : '',//勘查任务主表id
                xmid : this.$route.query.xmid,//项目id
                kcbm : [],
                qsFk : [],//区所反馈
                gwFk:[],//管网反馈
                qsFj : [],
                gwFj:[],
                qsFkyj: '',//区所反馈意见
                gwFkyj:'',//管网反馈意见
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
                kcpj:{
                    sjkcsj:'',
                    xmjlFkyj :''//项目经理反馈意见
                },
                fjData :[],
                sgdwFj : [],//施工单位反馈附件
                sgdwFkyj: '',//施工单位反馈意见
                khtyData :[],
                khtysj :'',
                sjybody : false,
                sgdwbody : false,
                qsbody : false,
                ysbody : false,
                xmjlbody:false,
                gwfkbody:false,
                yhqrbody:false
            }
        },
        methods: {
            formatDcnr : function (row, column) {
                return row.dcnr == '0' ? '项目周边管网' :  '具体位置测压';
            },
            openBody(xs) {
                switch (xs) {
                    case '1':
                        this.sjybody = true;break;
                    case '2':
                        this.sgdwbody = true;break;
                    case '3':
                        this.qsbody = true;break;
                    case '4':
                        this.ysbody = true;break;
                    case '5':
                        this.xmjlbody = true;break;
                    case '6':
                        this.yhqrbody = true;break;
                    default:break;
                }
            },
            closeBody(xs) {
                switch (xs) {
                    case '1':
                        this.sjybody = false;break;
                    case '2':
                        this.sgdwbody = false;break;
                    case '3':
                        this.qsbody = false;break;
                    case '4':
                        this.ysbody = false;break;
                    case '5':
                        this.xmjlbody = false;break;
                    case '6':
                        this.yhqrbody = false;break;
                    default:break;
                }
            },
            getKcbm(){//参与勘查的部门以及反馈信息
                let params = { xmid: this.xmid, kczt:'0'};
                getFkpjKcbm_sjst(params).then((res) => {
                    this.kcbm = res.data.row;
                    this.kcrwzbid = res.data.kcrwzbid;
                    if(this.type != '3'){
                        this.qsFk = res.data.qsFk;
                        this.qsFkyj = res.data.qsFkyj;
                        this.qsFj = res.data.qsFj;
                        this.qsfk = true;
                    }
                    if(this.type != '1'){
                        this.sjyFj = res.data.sjyFj;
                        this.sjyFkyj = res.data.sjyFkyj;
                        this.sjyfk = true;
                    }
                    if(this.type != '4'){
                        this.ysFj = res.data.ysFj;
                        this.ysFkyj = res.data.ysFkyj;
                        this.ysfk = true;
                    }
                    if(this.type != '2'){
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
                    this.fjData = res.data.fj;
                    this.kcpj.sjkcsj = res.data.sjkcrq;
                    this.kcpj.xmjlFkyj = res.data.xmjlfkyj;
                    this.khtysj = res.data.khtysj;//客户同意时间
                    this.khtyData = res.data.khtyfj;//客户同意附件
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

            //管网反馈
            getgwfk(){
                let params = { xmid: this.xmid, kczt:'0'};
                getSjstGwfk(params).then((res) => {
                    this.gwFk = res.data.listGwcb;
                 })
            }

        },
        mounted() {
            this.getKcbm();
            this.getgwfk();
            if(this.$route.query.type == '5'){
                this.xmjlfk = true;
                this.yhqr = false;
            }else if(this.$route.query.type == '6'){
                this.xmjlfk = true;
                this.yhqr = true;
            }
        },
    }
</script>