<template>
    <section>
        <el-row>
            <!--工具条-->
            <el-col :span="24">
                <el-card class="box-card" style="margin-top: 15px;" body-style="{}">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">基本信息
                        </div>
                    </div>
                    <el-form :model="filters" ref="filters" class="demo-ruleForm"><!--:rules="xqrules"-->
                        <el-form-item>
                            <el-row>
                                <el-col :span="2" style="text-align: right">
                                    <span>申请类型&nbsp;&nbsp;</span>
                                </el-col>
                                <el-col :span="5">
                                    <el-select v-model="filters.sqlx" style="width:206px;" size="small" :disabled="bool"
                                               placeholder="请选择" @change="getFormBylx(filters.sqlx)">
                                        <el-option v-for="item in sqList"
                                                   :key="item.VALUE"
                                                   :label="item.LABEL"
                                                   :value="item.VALUE"></el-option>
                                    </el-select>
                                </el-col>
                            </el-row>
                        </el-form-item>
                        <el-form-item>
                            <el-row>
                                <el-col :span="2" style="text-align: right">
                                    <span>申请编号&nbsp;&nbsp;</span>
                                </el-col>
                                <el-col :span="5">
                                    <el-input v-model="filters.sqbh" size="small" style="width:206px;"
                                              placeholder="输入申请编号" :readonly="true"></el-input>
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <span>安装类型&nbsp;&nbsp;</span>
                                </el-col>
                                <el-col :span="5">
                                    <el-select v-model="filters.azlx" size="small" style="width:206px;"
                                               placeholder="请选择">
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
                                    <el-input v-model="filters.slrq" size="small" style="width:206px;"
                                              placeholder="输入受理日期" :readonly="true"></el-input>
                                </el-col>
                            </el-row>
                        </el-form-item>
                        <el-form-item>
                            <el-row>
                                <el-col :span="2" style="text-align: right">
                                    <span>所属区域&nbsp;&nbsp;</span>
                                </el-col>
                                <el-col :span="5">
                                    <el-form-item prop="ssqy" :rules="this.publicUtil.notNull">
                                        <el-select v-model="filters.ssqy" size="small" style="width:206px;"
                                                   placeholder="请选择">
                                            <el-option v-for="item in qyList"
                                                       :key="item.VALUE"
                                                       :label="item.LABEL"
                                                       :value="item.VALUE"></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <span>工程地址&nbsp;&nbsp;</span>
                                </el-col>
                                <el-col :span="5">
                                    <el-form-item prop="gcdz" :rules="this.publicUtil.notNull">
                                        <el-input v-model="filters.gcdz" size="small" style="width:557px;"
                                                  placeholder="输入工程地址"></el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form-item>
                        <el-form-item>
                            <el-row>
                                <el-col :span="2" style="text-align: right">
                                    <span>工程名称&nbsp;&nbsp;</span>
                                </el-col>
                                <el-col :span="5"><!--:rules="this.publicUtil.notNull"-->
                                    <el-form-item prop="gcmc" >
                                        <el-input v-model="filters.gcmc" size="small" style="width:206px;"
                                                  placeholder="输入工程名称"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <span>报建单位&nbsp;&nbsp;</span>
                                </el-col>
                                <el-col :span="5">
                                    <el-input v-model="filters.bjdw" size="small" style="width:557px;"
                                              placeholder="输入报建单位"></el-input>
                                </el-col>
                            </el-row>
                        </el-form-item>
                        <el-form-item>
                            <el-row>
                                <!--<div v-if="showHg">
                                    <el-col :span="2" style="text-align: right">
                                        <span>小区名称&nbsp;&nbsp;</span>
                                    </el-col>
                                    <el-col :span="5">
                                        <el-input v-model="filters.xqmc" size="small" style="width:206px;"
                                                  placeholder="输入小区名称"></el-input>
                                    </el-col>
                                </div>
                                <div v-else>
                                    <el-col :span="2" style="text-align: right">
                                        <span>用户性质&nbsp;&nbsp;</span>
                                    </el-col>
                                    <el-col :span="5">
                                        <el-input v-model="filters.yhxz" size="small" style="width:206px;"
                                                  placeholder="输入用户性质"></el-input>
                                    </el-col>
                                </div>-->
                                <el-col :span="2" style="text-align: right">
                                    <span>联系人&nbsp;&nbsp;</span>
                                </el-col>
                                <el-col :span="5">
                                    <el-form-item prop="lxr" :rules="this.publicUtil.notNull">
                                        <el-input v-model="filters.lxr" size="small" style="width:206px;"
                                                  placeholder="输入联系人"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <span>移动电话&nbsp;&nbsp;</span>
                                </el-col>
                                <el-col :span="5">
                                    <el-form-item prop="lxdh" :rules="this.publicUtil.phone">
                                        <el-input v-model="filters.lxdh" size="small" style="width:206px;"
                                                  placeholder="输入联系电话"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="2" style="text-align: right">
                                    <span>办公电话&nbsp;&nbsp;</span>
                                </el-col>
                                <el-col :span="5"><!--:rules="this.publicUtil.zjphone"-->
                                    <el-form-item prop="zjlxdh" >
                                        <el-input v-model="filters.zjlxdh" size="small" style="width:206px;" placeholder="输入联系电话"></el-input>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                        </el-form-item>
                        <el-form-item v-if="showJm">
                            <el-col :span="2" style="text-align: right">
                                <span>总建筑面积&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5"><!--:rules="this.publicUtil.numPot2"-->
                                <el-form-item prop="zjzmj" :rules="this.publicUtil.NullOrInteger">
                                    <el-input v-model="filters.zjzmj" size="small" style="width:206px;"
                                              placeholder="输入总建筑面积"></el-input>
                                    ㎡
                                </el-form-item>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>住宅面积&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5"><!--:rules="this.publicUtil.numPot2"-->
                                <el-form-item prop="zzmj" :rules="this.publicUtil.NullOrInteger">
                                    <el-input v-model="filters.zzmj" size="small" style="width:206px;"
                                              placeholder="输入住宅面积"></el-input>㎡
                                </el-form-item>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>地块简称&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.dkjc" size="small" style="width:206px;"
                                          placeholder="输入地块简称"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item v-if="showJm">
                            <el-col :span="2" style="text-align: right">
                                <span>户数&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5"><!--:rules="this.publicUtil.InterNum"-->
                                <el-form-item prop="hs" :rules="this.publicUtil.NullOrInteger">
                                    <el-input v-model="filters.hs" size="small" style="width:206px;"
                                              placeholder="输入户数"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>设计范围&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.sjfw" size="small" style="width:557px;"
                                          placeholder="输入设计范围"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item v-if="showJm">
                            <el-col :span="2" style="text-align: right">
                                <span>单元楼号&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.dylh" size="small" style="width:206px;"
                                          placeholder="输入单元楼号"></el-input>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>规划泵房数&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5"><!--:rules="this.publicUtil.InterNum"-->
                                <el-form-item prop="ghbfs" :rules="this.publicUtil.NullOrInteger">
                                    <el-input v-model="filters.ghbfs" size="small" style="width:206px;"
                                              placeholder="输入规划泵房数"></el-input>
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
                                    <!--format="yyyy-MM-dd HH:mm"-->
                                    <!--value-format="yyyy-MM-dd HH:mm"-->
                                </el-date-picker>
                            </el-col>
                        </el-form-item>
                        <el-form-item v-if="showJm">
                            <el-col :span="2" style="text-align: right">
                                <span>分期情况&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.fqqk" size="small" style="width:206px;"
                                          placeholder="输入分期情况"></el-input>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>房屋性质&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-select v-model="filters.fwxz" size="small" style="width:206px;" placeholder="请选择">
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
                                <el-input v-model="filters.yhxz" size="small" style="width:206px;" placeholder="输入用户性质"></el-input>
                            </el-col>
                        </el-form-item>
                        <el-form-item v-if="showGs">
                            <el-col :span="2" style="text-align: right">
                                <span>意向接表数量&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="yxjbsl" :rules="this.publicUtil.NullOrInteger">
                                    <el-input v-model="filters.yxjbsl" size="small" style="width:206px;"
                                              placeholder="输入意向接表数量"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>接水用途&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.jsyt" size="small" style="width:206px;"
                                          placeholder="输入接水用途"></el-input>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>用户性质&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.yhxz" size="small" style="width:206px;" placeholder="输入用户性质"></el-input>
                            </el-col>
                            <!--<el-col :span="2" style="text-align: right">-->
                                <!--<span>原户号&nbsp;&nbsp;</span>-->
                            <!--</el-col>-->
                            <!--<el-col :span="5">-->
                                <!--<el-input v-model="filters.yhh" size="small" style="width:206px;"-->
                                          <!--placeholder="输入原户号"></el-input>-->
                            <!--</el-col>-->
                        </el-form-item>
                        <el-form-item v-if="showGs">
                            <!--<el-col :span="2" style="text-align: right">-->
                                <!--<span>原表口径&nbsp;&nbsp;</span>-->
                            <!--</el-col>-->
                            <!--<el-col :span="5">-->
                                <!--<el-input v-model="filters.ybkj" size="small" style="width:206px;"-->
                                          <!--placeholder="输入原表口径"></el-input>-->
                            <!--</el-col>-->
                            <el-col :span="2" style="text-align: right">
                                <span>允许开工时间&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-date-picker
                                        v-model="filters.jhkgsj"
                                        type="date"
                                        format="yyyy-MM-dd"
                                        value-format="yyyy-MM-dd"
                                        placeholder="选择日期" size="small" style="width: 207px;">
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
                                <el-select v-model="filters.ysfs" size="small" style="width:206px;" placeholder="请选择">
                                    <el-option label="市政直供" value="市政直供"></el-option>
                                    <el-option label="二次加压" value="二次加压"></el-option>
                                    <el-option label="楼顶水箱" value="楼顶水箱"></el-option>
                                </el-select>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>用水状况&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-select v-model="filters.yszk" size="small" style="width:206px;" placeholder="请选择">
                                    <el-option label="良好" value="良好"></el-option>
                                    <el-option label="一般" value="一般"></el-option>
                                    <el-option label="差" value="差"></el-option>
                                </el-select>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>楼顶水箱&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item prop="ldsx" :rules="this.publicUtil.NullOrInteger">
                                    <el-input v-model="filters.ldsx" size="small" style="width:206px;"
                                              placeholder="输入楼顶水箱"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-form-item>
                        <el-form-item v-if="showHg">
                            <el-col :span="2" style="text-align: right">
                                <span>楼房栋数&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5"><!--:rules="this.publicUtil.InterNum"-->
                                <el-form-item prop="lfds" :rules="this.publicUtil.NullOrInteger">
                                    <el-input v-model="filters.lfds" size="small" style="width:206px;"
                                              placeholder="输入楼房栋数"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>楼房户数&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5"><!--:rules="this.publicUtil.InterNum"-->
                                <el-form-item prop="lfhs" :rules="this.publicUtil.NullOrInteger">
                                    <el-input v-model="filters.lfhs" size="small" style="width:206px;"
                                              placeholder="输入楼房户数"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>平房户数&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5"><!--:rules="this.publicUtil.InterNum"-->
                                <el-form-item prop="pfhs" :rules="this.publicUtil.NullOrInteger">
                                    <el-input v-model="filters.pfhs" size="small" style="width:206px;"
                                              placeholder="输入平房户数"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-form-item>
                        <el-form-item v-if="showHg">
                            <el-col :span="2" style="text-align: right">
                                <span>最高层数&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5"><!--:rules="this.publicUtil.InterNum"-->
                                <el-form-item prop="zgcs" :rules="this.publicUtil.NullOrInteger">
                                    <el-input v-model="filters.zgcs" size="small" style="width:206px;"
                                              placeholder="输入最高层数（层）"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>内部消防&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-select v-model="filters.nbxf" size="small" style="width:206px;" placeholder="请选择">
                                    <el-option label="有" value="0"></el-option>
                                    <el-option label="无" value="1"></el-option>
                                </el-select>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>车库用水&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5"><!--:rules="this.publicUtil.InterNum"-->
                                <el-form-item prop="ckys" :rules="this.publicUtil.NullOrInteger">
                                    <el-input v-model="filters.ckys" size="small" style="width:206px;"
                                              placeholder="输入车库用水（间）"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-form-item>
                        <el-form-item v-if="showHg">
                            <el-col :span="2" style="text-align: right">
                                <span>车棚用水&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5"><!--:rules="this.publicUtil.InterNum"-->
                                <el-form-item prop="cpys" :rules="this.publicUtil.NullOrInteger">
                                    <el-input v-model="filters.cpys" size="small" style="width:206px;"
                                              placeholder="输入自行车棚用水（个）"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>门面用水&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5"><!--:rules="this.publicUtil.InterNum"-->
                                <el-form-item prop="mmys" :rules="this.publicUtil.NullOrInteger">
                                    <el-input v-model="filters.mmys" size="small" style="width:206px;"
                                              placeholder="输入经营门面用水（户）"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>公厕用水&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5"><!--:rules="this.publicUtil.InterNum"-->
                                <el-form-item prop="gcys" :rules="this.publicUtil.NullOrInteger">
                                    <el-input v-model="filters.gcys" size="small" style="width:206px;"
                                              placeholder="输入公厕用水（所）"></el-input>
                                </el-form-item>
                            </el-col>
                        </el-form-item>
                        <el-form-item v-if="showHg">
                            <el-col :span="2" style="text-align: right">
                                <span>物管用水&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5"><!--:rules="this.publicUtil.InterNum"-->
                                <el-form-item prop="wgys" :rules="this.publicUtil.NullOrInteger">
                                    <el-input v-model="filters.wgys" size="small" style="width:206px;"
                                              placeholder="输入物管办公房用水（处）"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <span>其他用水&nbsp;&nbsp;</span>
                            </el-col>
                            <el-col :span="5">
                                <el-input v-model="filters.qtys" size="small" style="width:206px;"
                                          placeholder="输入其他用水"></el-input>
                            </el-col>
                            <div v-if="showHg">
                                <el-col :span="2" style="text-align: right">
                                    <span>小区名称&nbsp;&nbsp;</span>
                                </el-col>
                                <el-col :span="5">
                                    <el-input v-model="filters.xqmc" size="small" style="width:206px;" placeholder="输入小区名称"></el-input>
                                </el-col>
                            </div>
                            <!--<div v-else>
                                <el-col :span="2" style="text-align: right">
                                    <span>用户性质&nbsp;&nbsp;</span>
                                </el-col>
                                <el-col :span="5">
                                    <el-input v-model="filters.yhxz" size="small" style="width:206px;" placeholder="输入用户性质"></el-input>
                                </el-col>
                            </div>-->
                        </el-form-item>
                    </el-form>
                </el-card>
            </el-col>

            <!--小区水表安装基本信息-->
            <el-col :span=24>
                <el-card class="box-card1" style="margin-top: 15px;" v-if="sbazjbxx">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">报建信息录入
                        </div>
                    </div>
                    <el-form :inline="true">
                        <el-form-item>
                            <el-button size="mini" type="primary" @click="handleAdds">
                                <i class="iconfont icon-xinzeng"></i>新增
                            </el-button>
                        </el-form-item>
                    </el-form>
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
                        <el-table-column label="操作" align="center">
                            <template slot-scope="scope">
                                <label title="编辑">
                                    <i class="iconfont icon-ai-edit" @click="handleEdit(scope.$index, scope.row)"
                                       style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                </label>
                                <label title="删除">
                                    <i class="iconfont delete" @click="handleDel(scope.$index, scope.row)"
                                       style="cursor:pointer;padding: 2px;color: #f78989"></i>
                                </label>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-card>
                <el-card class="box-card1" style="margin-top: 15px;" v-if="gssbazjbxx">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">报建信息录入
                        </div>
                    </div>
                    <el-form :inline="true">
                        <el-form-item>
                            <el-button size="mini" type="primary" @click="gshandleAdds">
                                <i class="iconfont icon-xinzeng"></i>新增
                            </el-button>
                        </el-form-item>
                    </el-form>
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
                        <el-table-column label="操作" align="center">
                            <template slot-scope="scope">
                                <label title="编辑">
                                    <i class="iconfont icon-ai-edit" @click="gshandleEdit(scope.$index, scope.row)"
                                       style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                </label>
                                <label title="删除">
                                    <i class="iconfont delete" @click="gshandleDel(scope.$index, scope.row)"
                                       style="cursor:pointer;padding: 2px;color: #f78989"></i>
                                </label>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-card>
                <el-card class="box-card1" style="margin-top: 15px;" v-if="hgsbazjbxx">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">报建信息录入
                        </div>
                    </div>
                    <el-tabs type="card" v-model="yzbtabBdz">
                        <el-tab-pane label="小区水表安装基本信息（户表）" name="小区水表安装基本信息（户表）">
                            <el-form :inline="true">
                                <el-form-item>
                                    <el-button size="mini" type="primary" @click="handleAdds">
                                        <i class="iconfont icon-xinzeng"></i>新增
                                    </el-button>
                                </el-form-item>
                            </el-form>
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
                                <el-table-column label="操作" align="center">
                                    <template slot-scope="scope">
                                        <label title="编辑">
                                            <i class="iconfont icon-ai-edit"
                                               @click="handleEdit(scope.$index, scope.row)"
                                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label>
                                        <label title="删除">
                                            <i class="iconfont delete" @click="handleDel(scope.$index, scope.row)"
                                               style="cursor:pointer;padding: 2px;color: #f78989"></i>
                                        </label>
                                    </template>
                                </el-table-column>
                            </el-table>
                        </el-tab-pane>
                        <el-tab-pane label="原总表信息表" name="原总表信息表">
                            <el-form :inline="true">
                                <el-form-item>
                                    <el-button size="mini" type="primary" @click="yzbhandleAdds">
                                        <i class="iconfont icon-xinzeng"></i>新增
                                    </el-button>
                                </el-form-item>
                            </el-form>
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
                                <el-table-column label="操作" align="center">
                                    <template slot-scope="scope">
                                        <label title="编辑">
                                            <i class="iconfont icon-ai-edit"
                                               @click="yzbhandleEdit(scope.$index, scope.row)"
                                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label>
                                        <label title="删除">
                                            <i class="iconfont delete" @click="yzbhandleDel(scope.$index, scope.row)"
                                               style="cursor:pointer;padding: 2px;color: #f78989"></i>
                                        </label>
                                    </template>
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
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">资料上传
                        </div>
                    </div>
                    <el-row style="margin-bottom: 5px;display: none">
                        <!--<el-col :span="5">-->
                        <!--<el-select v-model="zlType" placeholder="请选择资料类型">-->
                        <!--<el-option-->
                        <!--v-for="item in zllx_data"-->
                        <!--:key="item.ID"-->
                        <!--:label="item.NAME"-->
                        <!--:value="item.ID">-->
                        <!--</el-option>-->
                        <!--</el-select>-->
                        <!--</el-col>-->
                        <!--<el-col :span="2">-->
                            <!--<div class="grid-content bg-purple">-->
                                <!--<el-upload-->
                                        <!--class="upload-demo"-->
                                        <!--ref="upload"-->
                                        <!--:show-file-list='false'-->
                                        <!--:multiple=true-->
                                        <!--action="#"-->
                                        <!--:http-request="submitUpload"-->
                                        <!--:on-preview="handlePreview"-->
                                        <!--:on-change="handleChange"-->
                                        <!--:on-remove="handleRemove"-->
                                        <!--accept=".jpg,.jpeg,.png"-->
                                        <!--:file-list="fileList">-->
                                    <!--<el-button slot="trigger" size="mini" type="primary" id="chooseFile">-->
                                        <!--<i class="iconfont icon-shangchuan"></i>选取文件-->
                                    <!--</el-button>-->
                                <!--</el-upload>-->
                            <!--</div>-->
                        <!--</el-col>-->
                        <!--<el-col :span="16">
                            <el-button size="mini" type="primary" @click="openVisible" id="choosePs">
                                <i class="iconfont icon-paishe"></i>拍摄
                            </el-button>
                        </el-col>-->
                    </el-row>

                    <el-table :data="zlrow" v-loading="zllistLoading" :stripe="true" style="width: 100%;">
                        <el-table-column prop="NAME" label="资料类型" align="center">
                        </el-table-column>
                        <el-table-column prop="COUNT" label="资料数量" align="center">
                        </el-table-column>
                        <el-table-column label="操作" align="center">
                            <template slot-scope="scope">
                                <el-row>
                                    <el-col :span="3" style="text-align: right;margin-left: 35%">
                                        <el-upload
                                                class="upload-demo"
                                                ref="upload"
                                                :show-file-list='false'
                                                action="#"
                                                :limit="1"
                                                :http-request="submitUpload"
                                                :on-preview="handlePreview"
                                                :on-change="handleChange"
                                                :on-remove="handleRemove"
                                                accept=".jpg,.jpeg,.png"
                                                :file-list="fileList">
                                            <label title="上传"><i @click="chooseFileClick(scope.$index, scope.row)" class="iconfont icon-shangchuan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                                        </el-upload>
                                    </el-col>
                                    <el-col :span="3" style="text-align: right">
                                        <label title="拍摄">
                                            <i class="iconfont icon-paishe" @click="openVisible(scope.$index, scope.row)"
                                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label>
                                        <label title="详情">
                                            <i class="iconfont icon-xiangqing"
                                               @click="toDetial_vue(scope.$index, scope.row)"
                                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label>
                                    </el-col>
                                </el-row>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-card>
                <el-row style="text-align: center;margin-top: 25px;">
                    <el-button size="mini" type="success" @click="openDy">
                        <i class="iconfont icon-dayin_f"></i>打印
                    </el-button>
                    <el-button size="mini" type="primary" :loading="submitLoading" @click="handleAdd" ><!-- @click="$routerTab.close()"-->
                        <i class="iconfont icon-queren"></i>保存
                    </el-button>
                    <el-button size="mini" type="primary" @click="slzp_vue">
                        <i class="iconfont icon-shenhetongguorenyuan"></i>受理指派
                    </el-button>
                </el-row>
            </el-col>

        </el-row>
        <el-dialog title="" :visible.sync="printFormVisible">
            <div id="printTest">
                <table class="border-table">
                    <caption align="top"><span style="font-size: 20px;font-family: 黑体;">合肥供水集团有限公司业务受理通知单</span>
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
                        <td width="40%" height="40px">{{dy_bjdw}}</td>
                        <td width="10%" height="40px">业务类型</td>
                        <td width="40%" height="40px">{{ywlx}}</td>
                    </tr>
                    <tr>
                        <td width="10%" height="40px">工程名称</td>
                        <td width="40%" height="40px" colspan="3">{{dy_xmmc}}</td>
                    </tr>
                    <tr>
                        <td width="10%" height="40px">工程地址</td>
                        <td width="40%" height="40px" colspan="3">{{dy_xmdz}}</td>
                    </tr>
                    <tr>
                        <td height="40px" colspan="4"><span style="font-family: 黑体;">已接受用户提供的纸质申报资料明细</span></td>
                    </tr>
                    <tr v-for="(item) in zlsc_data" :key="item.id">
                        <td height="40px" colspan="3">《<span>{{item.TYPE}}</span>》</td>
                        <td height="40px"><span>{{item.VALUE}}</span>份</td>
                    </tr>
                    <tr>
                        <th align="left" height="40px" colspan="3">受理单位：合肥供水集团有限公司</th>
                        <th align="left" height="40px">受理人：<span id="dy_slr"></span></th>
                    </tr>
                    <tr>
                        <th align="left" height="40px" colspan="3">用户确认签章：</th>
                        <th align="left" height="40px">日期：<span id="dy_rq"></span></th>
                    </tr>
                </table>
            </div>
            <div style="text-align: center;">
                <el-button size="mini" style="margin-bottom: 15px" type="success" v-print="'#printTest'">
                    <i class="iconfont icon-dayin_f"></i>打印
                </el-button>
            </div>
        </el-dialog>

        <!--新增界面-->
        <el-dialog title="新增" :visible.sync="addFormVisible">
            <el-form :model="addForm" label-width="80px" ref="addForm">
                <el-form-item label="楼栋号">
                    <el-input v-model="addForm.ldh" size="small" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="单元数" prop="dys" :rules="this.publicUtil.InterNum">
                    <el-input v-model="addForm.dys" size="small" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="楼层" prop="lc" :rules="this.publicUtil.InterNum">
                    <el-input v-model="addForm.lc" size="small" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="每层户数" prop="mchs" :rules="this.publicUtil.InterNum">
                    <el-input v-model="addForm.mchs" size="small"></el-input>
                </el-form-item>
                <el-form-item label="单元户数" prop="zhs" :rules="this.publicUtil.InterNum">
                    <el-input v-model="addForm.zhs" size="small"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="addForm.bz" size="small" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer" style="text-align: center">
                <el-button size="mini" type="info" @click.native="addFormVisible = false">
                    <i class="iconfont icon-quxiao"></i>取消
                </el-button>
                <el-button size="mini" type="primary" @click.native="addSubmit" :loading="addLoading">
                    <i class="iconfont icon-queren"></i>提交
                </el-button>
            </div>
        </el-dialog>

        <!--原总表新增界面-->
        <el-dialog title="新增" :visible.sync="yzbaddFormVisible">
            <el-form :model="yzbaddForm" label-width="80px" ref="yzbaddForm">
                <el-form-item label="原户号" prop="hh" :rules="this.publicUtil.notNull">
                    <el-input v-model="yzbaddForm.hh" size="small" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="原户名" prop="hm" :rules="this.publicUtil.notNull">
                    <el-input v-model="yzbaddForm.hm" size="small" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="口径" prop="kjbh" :rules="this.publicUtil.notNull">
                    <el-select v-model="yzbaddForm.kjbh" size="small" style="width:206px;" placeholder="请选择">
                        <el-option v-for="item in kjList"
                                   :key="item.VALUE"
                                   :label="item.LABEL"
                                   :value="item.VALUE"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="用水性质" prop="ysxzbh">
                    <el-select v-model="yzbaddForm.ysxzbh" size="small" style="width:206px;" placeholder="请选择">
                        <el-option v-for="item in ysxzList"
                                   :key="item.codevalue"
                                   :label="item.codename"
                                   :value="item.codevalue"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="行业分类" prop="hyflbh">
                    <el-select v-model="yzbaddForm.hyflbh" size="small" style="width:206px;" placeholder="请选择">
                        <el-option v-for="item in hyflList"
                                   :key="item.codevalue"
                                   :label="item.codename"
                                   :value="item.codevalue"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="户数" prop="kchs">
                    <el-input v-model="yzbaddForm.kchs" size="small" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="yzbaddForm.bz" size="small" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer" style="text-align: center">
                <el-button size="mini" type="info" @click.native="yzbaddFormVisible = false">
                    <i class="iconfont icon-quxiao"></i>取消
                </el-button>
                <el-button size="mini" type="primary" @click.native="yzbaddSubmit" :loading="yzbaddLoading">
                    <i class="iconfont icon-queren"></i>提交
                </el-button>
            </div>
        </el-dialog>

        <!--编辑界面-->
        <el-dialog title="编辑" :visible.sync="editFormVisible">
            <el-form :model="editForm" label-width="80px" ref="editForm">
                <el-form-item label="楼栋号">
                    <el-input v-model="editForm.ldh" size="small" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="单元数" prop="dys" :rules="this.publicUtil.InterNum">
                    <el-input v-model="editForm.dys" size="small" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="楼层" prop="lc" :rules="this.publicUtil.InterNum">
                    <el-input v-model="editForm.lc" size="small" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="每层户数" prop="mchs" :rules="this.publicUtil.InterNum">
                    <el-input v-model="editForm.mchs" size="small"></el-input>
                </el-form-item>
                <el-form-item label="单元户数" prop="zhs" :rules="this.publicUtil.InterNum">
                    <el-input v-model="editForm.zhs" size="small"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="editForm.bz" size="small" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer" style="text-align: center">
                <el-button size="mini" type="info" @click.native="editFormVisible = false">
                    <i class="iconfont icon-quxiao"></i>取消
                </el-button>
                <el-button size="mini" type="primary" @click.native="editSubmit" :loading="editLoading">
                    <i class="iconfont icon-queren"></i>提交
                </el-button>
            </div>
        </el-dialog>

        <!--原总表编辑界面-->
        <el-dialog title="编辑" :visible.sync="yzbeditFormVisible">
            <el-form :model="yzbeditForm" label-width="80px" ref="yzbeditForm">
                <el-form-item label="原户号" prop="hh" :rules="this.publicUtil.notNull">
                    <el-input v-model="yzbeditForm.hh" size="small" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="原户名" prop="hm" :rules="this.publicUtil.notNull">
                    <el-input v-model="yzbeditForm.hm" size="small" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="口径" prop="kjbh" :rules="this.publicUtil.notNull">
                    <el-select v-model="yzbeditForm.kjbh" size="small" style="width:206px;" placeholder="请选择">
                        <el-option v-for="item in kjList"
                                   :key="item.VALUE"
                                   :label="item.LABEL"
                                   :value="item.VALUE"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="用水性质" prop="ysxzbh">
                    <el-select v-model="yzbeditForm.ysxzbh" size="small" style="width:206px;" placeholder="请选择">
                        <el-option v-for="item in ysxzList"
                                   :key="item.codevalue"
                                   :label="item.codename"
                                   :value="item.codevalue"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="行业分类" prop="hyflbh">
                    <el-select v-model="yzbeditForm.hyflbh" size="small" style="width:206px;" placeholder="请选择">
                        <el-option v-for="item in hyflList"
                                   :key="item.codevalue"
                                   :label="item.codename"
                                   :value="item.codevalue"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="户数" prop="kchs">
                    <el-input v-model="yzbeditForm.kchs" size="small" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="yzbeditForm.bz" size="small" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer" style="text-align: center">
                <el-button size="mini" type="info" @click.native="yzbeditFormVisible = false">
                    <i class="iconfont icon-quxiao"></i>取消
                </el-button>
                <el-button size="mini" type="primary" @click.native="yzbeditSubmit" :loading="yzbeditLoading">
                    <i class="iconfont icon-queren"></i>提交
                </el-button>
            </div>
        </el-dialog>

        <!--受理指派界面-->
        <el-dialog title="受理指派" :visible.sync="slzpFormVisible">
            <div class="text item">
                <el-row>
                    <el-form label-width="120px" :inline="true">
                        <el-radio-group v-model="resource" style="width: 100%;">
                            <el-col :span="8" v-model="data" v-for="(item,index) in data" class="col" :key="index">
                                <el-form-item style="width: 45%">
                                    <el-radio v-model="radio" :label="item.USERID">
                                        {{item.USERNAME}}（在建项目{{item.NUM}}个）
                                    </el-radio>
                                </el-form-item>
                            </el-col>
                            <el-col style="text-align: center">
                                <el-form-item style="width: 45%">
                                    <router-link :to="zpurl">
                                        <el-button size="mini" type="primary" :loading="zpLoading" @click="handleAdd(1)">
                                            <i class="iconfont icon-queren"></i>提交
                                        </el-button>
                                    </router-link>
                                </el-form-item>
                            </el-col>
                        </el-radio-group>
                    </el-form>
                </el-row>
            </div>
        </el-dialog>

        <!--项目资料下载界面-->
        <el-dialog title="项目资料下载" :visible.sync="dialogTableVisible" :modal-append-to-body="false"
                   style="padding-bottom: 10px" max-height="400px">
            <el-row>
                <el-col>
                    <el-table :data="gridData" :stripe="true" style="padding-bottom: 10px">
                        <el-table-column property="FJMC" label="附件名称" width="200" align="center"></el-table-column>
                        <el-table-column property="SCSJ" label="上传时间" width="200" align="center"></el-table-column>
                        <el-table-column property="USERNAME" label="上传人" width="100" align="center"></el-table-column>
                        <el-table-column property="address" label="操作" width="200" align="center">
                            <template slot-scope="scope">
                                <el-row>
                                    <el-col>
                                        <label title="查看">
                                            <i class="iconfont icon-yulan"
                                               @click="downloadFile_vue(scope.$index, scope.row)"
                                               style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label>
                                        <label title="删除">
                                            <i class="iconfont delete"
                                               @click="deleteFj_vue(scope.$index, scope.row)"
                                               style="cursor:pointer;padding: 2px;color: #f78989"></i>
                                        </label>
                                    </el-col>
                                </el-row>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-col>
                <el-col>
                    <el-pagination background layout="total, prev, pager, next, sizes , jumper"
                                   :page-sizes="[5, 10, 20, 30]"
                                   @current-change="handleCurrentChange" @size-change="handleSizeChange"
                                   :page-size="this.zlxxSize" style="float:right;" :total="this.zlxxTotal">
                    </el-pagination>
                </el-col>
            </el-row>
        </el-dialog>

        <!--拍摄-->
        <el-dialog title="拍摄" :visible.sync="creamVisible" top="5vh">
            <DocCamera v-bind:dirName="dirName" v-bind:fileName="fileName"
                       v-on:listenToChildEvent="showMsg"></DocCamera>
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

    </section>

</template>

<script>
    import DocCamera from '../template/DocCamera'
    import {
        saveSld, getSclx, queryZlcount, queryUser, addLdxx, editLdxx, getLdxxList, remove, importFile,
        getYzbList, yzbremove, addYzb, editYzb, toZpry, toDetial, deleteFj, download, saveImageToFj,getHyflCodeData,
        getZlrow,addGsbjxx,getGsbjxxList,editGsbjxx,gsremove,defaultsqlx} from '../../api/xqsl';
    import { getYsxzData,getHyflData } from '../../api/sjst';
    let zlType="";
    export default {
        name: "addxq",
        components: {
            'DocCamera': DocCamera
        },
        data() {
            return {
                bool:false,
                ywlx:'',
                dy_bjdw:'',
                dy_xmmc:'',
                dy_xmdz:'',
                zpurl:'/addXq',
                zpLoading:false,
                zllistLoading: false,
                submitLoading: false,

                /*拍摄*/
                fileName: 'xqsl',//给子组件的文件名字
                dirName: '',//保存图片的文件夹名字
                creamVisible: false,

                /*资料上传及拍摄*/
                zlType: '',//选中资料类型
                zllx_data: [],//资料类型集合
                fileList: [],//已选中文件
                zlrow: [],//已上传资料集合
                dialogTableVisible: false,//是否打开资料详情界面
                gridData: [],//已上传资料详情集合
                zlxxPage: 1,
                zlxxSize: 5,
                zlxxTotal: 0,
                slide1: [],

                /*受理指派*/
                slzpFormVisible: false,
                resource: '',
                radio: '',//选中的指派人员
                data: [],

                qyList: [],//区域下拉框数据集合
                azList: [],//安装类型下拉框数据集合
                sqList: [],//申请类型下拉框数据集合
                kjList: [],//口径下拉框数据集合
                ysxzList: [],//用水性质下拉框数据集合
                hyflList: [],//行业分类下拉框数据集合
                fwxzList: [],//房屋性质下拉框数据集合
                filters: {
                    sqlx: '',
                    fwxz: '',
                    sqbh: '',
                    azlx: '',
                    slrq: '',
                    ssqy: '',
                    lxr: '',
                    lxdh: '',
                    gcmc: '',
                    bjdw: '',
                    yhxz: '',
                    gcdz: '',
                    xqmc: '',

                    zjzmj: '',
                    zzmj: '',
                    dkjc: '',
                    hs: '',
                    sjfw: '',
                    dylh: '',
                    ghbfs: '',
                    jfsj: '',
                    fqqk: '',

                    yxjbsl: '',
                    jsyt: '',
                    yhh: '',
                    ybkj: '',

                    ysfs: '',
                    yszk: '',
                    hxzk: '',
                    lfds: '',
                    lfhs: '',
                    pfhs: '',
                    zgcs: '',
                    nbxf: '',
                    ckys: '',
                    cpys: '',
                    mmys: '',
                    gcys: '',
                    wgys: '',
                    qtys: '',
                    ldsx:'',
                },
                showHg: false,
                showJm: false,
                showGs: false,

                sbazjbxx: false,//新建小区申请楼栋表列表是否显示
                ldxxs: [],//新建小区申请楼栋表表单数据
                addFormVisible: false,
                addLoading: false,
                addForm: {
                    ldh: '', dys: '', lc: '', mchs: '', zhs: '', bz: ''
                },
                editFormVisible: false,
                editLoading: false,
                editForm: {
                    ldh: '', dys: '', lc: '', mchs: '', zhs: '', bz: ''
                },
                listLoading: false,

                hgsbazjbxx: false,//户改科列表是否显示
                yzbtabBdz: '小区水表安装基本信息（户表）',
                yzbxx: [],//原总表信息表集合
                yzblistLoading: false,
                yzbaddFormVisible: false,
                yzbaddLoading: false,
                yzbaddForm: {
                    hh: '', hm: '', kjbh: '', ysxzbh: '', hyflbh: '', kchs: '', bz: ''
                },
                yzbeditFormVisible: false,
                yzbeditLoading: false,
                yzbeditForm: {
                    hh: '', hm: '', kjbh: '', ysxzbh: '', hyflbh: '', kchs: '', bz: ''
                },

                printFormVisible: false,//打印界面是否显示


                zlsc_data: [],//已上传资料集合
                fileIds: [],//已上传的资料ID集合
                ldxxIds: [],//已添加新建小区申请楼栋ID集合
                yzbIds: [],//原总表信息表ID集合
                user: "",//当前用户

                yjfjlx:'',
                ejfjlx:'',
                sqzbid:'',

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
        mounted() {
            this.findUser();
            this.init();
            //根据当前登录人，显示申请列表
            this.defaultsqlx();
            this.getYsxzData_vue();
            this.getHyflData_vue();
        },
        methods: {
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
            //根据当前登录人，显示申请列表
            defaultsqlx(){

                let params = {}
                defaultsqlx(params).then((res) => {
                    if ( typeof res.defaultsqlx.VALUE != "undefined"){
                        this.filters.sqlx = res.defaultsqlx.VALUE;
                        this.getFormBylx(this.filters.sqlx);
                        this.bool = true;
                    }

                });


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
                        let para = Object.assign({sqlx: this.filters.sqlx,sqid:this.sqzbid}, this.gsaddForm);
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
                let para = {id:this.sqzbid};
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
            init() {//初始化参数
                let JSONstr = sessionStorage.getItem("user");
                let JSON = eval("(" + JSONstr + ")");
                this.dirName = this.filters.sqbh;//文件夹名字
            },
            showMsg: function (data) {//存储子组件保存的图片名称
                this.zlType = zlType;
                let params = {sqlx: this.filters.sqlx, operation: this.zlType, dirName: this.dirName,
                    fileName: data,sldid:this.sqzbid};
                saveImageToFj(params).then((res) => {
                    let msg = res.msg;
                    if (msg == '上传成功') {
                        this.$message({
                            message: '上传成功',
                            type: 'success'
                        });
                        let fileId = res.fileId;
                        this.fileIds = this.fileIds.concat(fileId);
                        this.queryZlrow_vue();
                    } else {
                        this.$message.error('上传失败！');
                    }
                    this.creamVisible = false;
                });
            },
            openVisible(index, row) {
                zlType = row.ID;
                if (zlType == "" || zlType == null) {
                    this.$message.error('请选择资料类型！');
                } else {
                    this.creamVisible = true;
                }
            },
            //打开附件详情窗口
            toDetial_vue(index, row) {
                this.dialogTableVisible = true;
                if(row == 2){

                }else{
                    this.yjfjlx = row.ID;
                    this.ejfjlx = row.PARENTID;
                }
                let params = {
                    ejfjlx: this.yjfjlx,
                    yjfjlx: this.ejfjlx,
                    sqlx: this.filters.sqlx,
                    sqdid:this.sqzbid,
                    page: this.zlxxPage - 1,
                    size: this.zlxxSize
                };
                toDetial(params).then((res) => {
                    if (res.data != null && res.data.row != null) {
                        if(res.data.total>0&&res.data.row.length<=0&&this.zlxxPage>0){
                            this.zlxxPage=this.zlxxPage-1;
                            this.toDetial_vue(index, row);
                        }else{
                            this.gridData = res.data.row;
                            this.zlxxTotal = res.data.total;
                        }
                    }
                })
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
            //删除附件
            deleteFj_vue(index, row) {
                let detialRow = {
                    ID: row.FJLX_EJ,
                    PARENTID: row.FJLX_YJ,
                };
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    let params = {
                        id: row.ID,
                        sqlx: this.filters.sqlx,
                        size: this.zlxxSize,
                        ejfjlx: row.FJLX_EJ,
                        yjfjlx: row.FJLX_YJ
                    };
                    deleteFj(params).then((res) => {
                        if (res.data.flag) {
                            this.$message({
                                message: '删除成功',
                                type: 'success'
                            });
                            this.toDetial_vue(index, detialRow);
                            this.queryZlrow_vue();
                        } else {
                            this.$message.error('删除失败！');
                        }
                    });
                })
            },
            //查看图片
            downloadFile_vue(index, row) {
                this.slide1 = [];
                let formData = new FormData();
                formData.append("path", row.FJLJ);
                formData.append("sqlx", this.filters.sqlx);
                download(formData).then((res) => {

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
            slzp_vue() {
                this.slzpFormVisible = true;
            },
            //新建小区申请楼栋列表新增界面
            handleAdds() {
                this.addFormVisible = true;
                this.addForm = {
                    ldh: '', dys: '', lc: '', mchs: '', zhs: '', bz: ''
                };
            },
            //原总表信息表新增界面
            yzbhandleAdds() {
                this.yzbaddFormVisible = true;
                this.yzbaddForm = {
                    yhh: '', yhm: '', kj: '', ysxz: '', hyfl: '', hs: '', bz: ''
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
            addSubmit() {
                this.$refs.addForm.validate((valid) => {
                    if (valid) {
                        this.addLoading = true;
                        let para = Object.assign({sqlx: this.filters.sqlx,sqid:this.sqzbid}, this.addForm);
                        addLdxx(para).then((res) => {
                            this.addLoading = false;
                            this.$message({
                                message: '提交成功',
                                type: 'success'
                            });
                            this.$refs['addForm'].resetFields();
                            this.addFormVisible = false;
                            this.getLdxx();
                            this.ldxxIds = this.ldxxIds.concat(res.id);
                        });
                    }
                });
            },
            //原总表信息表列表新增
            yzbaddSubmit() {
                this.$refs.yzbaddForm.validate((valid) => {
                    if (valid) {
                        this.yzbaddLoading = true;
                        let para = Object.assign({sqid:this.sqzbid}, this.yzbaddForm);
                        addYzb(para).then((res) => {
                            this.yzbaddLoading = false;
                            this.$message({
                                message: '提交成功',
                                type: 'success'
                            });
                            this.$refs['yzbaddForm'].resetFields();
                            this.yzbaddFormVisible = false;
                            this.getYzb();
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
                            this.getLdxx();
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
                            this.getYzb();
                        });
                    }
                });
            },
            //新建小区申请楼栋表列表展示
            getLdxx() {
                let para = {id:this.sqzbid};
                this.listLoading = true;
                getLdxxList(para).then((res) => {
                    this.ldxxs = res.data.ldxxs;
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
            //新建小区申请楼栋表列表删除
            handleDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    let para = {id: row.id};
                    remove(para).then((res) => {
                        this.listLoading = false;
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getLdxx();
                    });
                }).catch(() => {

                });
            },
            yzbhandleDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.yzblistLoading = true;
                    let para = {id: row.id};
                    yzbremove(para).then((res) => {
                        this.yzblistLoading = false;
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getYzb();
                    });
                }).catch(() => {

                });
            },
            //申请类型下拉框触发方法
            getFormBylx(value, type) {
                if (value == "10") {
                    this.showJm = true;
                    this.sbazjbxx = true;
                    this.gssbazjbxx = false;
                    this.hgsbazjbxx = false;
                    this.showHg = false;
                    this.showGs = false;
                    this.getLdxx();
                } else if (value == "20") {
                    this.showGs = true;
                    this.sbazjbxx = false;
                    this.gssbazjbxx = true;
                    this.hgsbazjbxx = false;
                    this.showHg = false;
                    this.showJm = false;
                } else if (value == "30") {
                    this.showHg = true;
                    this.sbazjbxx = false;
                    this.gssbazjbxx = false;
                    this.hgsbazjbxx = true;
                    this.showJm = false;
                    this.showGs = false;
                    this.getLdxx();
                    this.getYzb();
                }
                if (type == 1) {

                } else {
                    this.filters = {
                        sqlx: this.filters.sqlx,
                        sqbh: this.filters.sqbh,
                        azlx: '',
                        slrq: this.filters.slrq,
                        ssqy: '',
                        lxr: '',
                        lxdh: '',
                        gcmc: '',
                        bjdw: '',
                        yhxz: '',
                        gcdz: '',
                        zjzmj: '',
                        zzmj: '',
                        dkjc: '',
                        hs: '',
                        sjfw: '',
                        dylh: '',
                        ghbfs: '',
                        jfsj: '',
                        fqqk: '',
                        yxjbsl: '',
                        jsyt: '',
                        yhh: '',
                        ybkj: '',
                    };
                }
                //查询上传附件类型/所属区域集合/安装类型集合
                let params = {sqlx: this.filters.sqlx,id:this.sqzbid};
                getSclx(params).then((res) => {
                    this.zllx_data = res.data.sclx;
                    this.qyList = res.data.qyList;
                    this.azList = res.data.azList;
                    this.data = res.data.zpry;
                    this.sqzbid = res.data.sqzbid;
                    this.queryZlrow_vue();
                });
            },
            //查询资料集合
            queryZlrow_vue(){
                let para = {sqlx:this.filters.sqlx,id:this.sqzbid};
                getZlrow(para).then(res=>{
                    this.zlrow = res.data.zlrow;
                });
            },
            //打开打印界面
            openDy() {
                this.printFormVisible = true;
                this.$nextTick(() => {
                    let list = this.fileIds;
                    let params = {azlxbh:this.filters.azlx};
                    let i = 0;
                    list.forEach((item, index) => {
                        params['value' + (index + 1)] = list[i];
                        i++;
                    });
                    let sqlx = this.filters.sqlx;
                    if(sqlx == '10'){
                        params['sqlx'] = "1";
                    }else if(sqlx == '20'){
                        params['sqlx'] = "2";
                    }else if(sqlx == '30'){
                        params['sqlx'] = "3";
                    }
                    params['count'] = i;
                    queryZlcount(params).then((res) => {
                        this.zlsc_data = res.data.list;
                        this.ywlx = res.data.azlx;
                    });
                    document.getElementById("dy_sqbh").innerText = this.filters.sqbh;
                    document.getElementById("dy_sqrq").innerText = this.publicUtil.getNowDay();
                    document.getElementById("dy_lxr").innerText = this.filters.lxr;
                    document.getElementById("dy_lxdh").innerText = this.filters.lxdh;
                    // document.getElementById("dy_bjdw").innerText = this.filters.bjdw;
                    this.dy_bjdw = this.filters.bjdw;
                    this.dy_xmmc = this.filters.gcmc;
                    this.dy_xmdz = this.filters.gcdz;
                    // document.getElementById("dy_xmmc").innerText = this.filters.gcmc;
                    // document.getElementById("dy_xmdz").innerText = this.filters.gcdz;
                    document.getElementById("dy_slr").innerText = this.user;
                    document.getElementById("dy_rq").innerText = this.publicUtil.getNowDay();
                });
            },
            //获取当前用户/获得申请类型集合
            findUser() {
                this.filters.slrq = this.publicUtil.getNowDay();
                let param = {
                    type: 'fwxz'
                };
                queryUser(param).then((res) => {
                    this.user = res.data.user;
                    this.filters.sqbh = res.data.sqbh;
                    this.sqList = res.data.sqList;
                    this.kjList = res.data.kjList;
                    this.fwxzList = res.data.fwxzList;
                });
            },
            //提交
            handleAdd(sfzp) {
                this.$refs.filters.validate((valid) => {
                    if (valid) {
                        if (lx == '') {
                            this.$message({
                                message: '请选择申请类型！',
                                type: 'warning'
                            });
                        } else {
                            if (sfzp == 1) {
                                if (this.resource == '') {
                                    this.$message({
                                        message: '请选择指派人员！',
                                        type: 'warning'
                                    });
                                } else {
                                    let list = this.fileIds;
                                    let ldxxList = this.ldxxIds;
                                    let yzbList = this.yzbIds;
                                    let params = this.filters;
                                    let i = 0;
                                    let j = 0;
                                    let k = 0;
                                    list.forEach((item, index) => {
                                        params['value' + (index + 1)] = list[index];
                                        i++;
                                    });
                                    ldxxList.forEach((item, index) => {
                                        params['ldxxvalue' + (index + 1)] = ldxxList[index];
                                        j++;
                                    });
                                    yzbList.forEach((item, index) => {
                                        params['yzbvalue' + (index + 1)] = yzbList[index];
                                        k++;
                                    });
                                    params['count'] = i;
                                    params['ldxxcount'] = j;
                                    params['yzbcount'] = k;
                                    params['sfzp'] = '1';
                                    params['sqzbid'] = this.sqzbid;
                                    this.submitLoading = true;
                                    this.zpurl = '/Backlog';
                                    saveSld(params).then((res) => {
                                        this.handlezp(res);
                                    });
                                }
                            } else {
                                let list = this.fileIds;
                                let ldxxList = this.ldxxIds;
                                let yzbList = this.yzbIds;
                                let params = this.filters;
                                let i = 0;
                                let j = 0;
                                let k = 0;
                                list.forEach((item, index) => {
                                    params['value' + (index + 1)] = list[index];
                                    i++;
                                });
                                ldxxList.forEach((item, index) => {
                                    params['ldxxvalue' + (index + 1)] = ldxxList[index];
                                    j++;
                                });
                                yzbList.forEach((item, index) => {
                                    params['yzbvalue' + (index + 1)] = yzbList[index];
                                    k++;
                                });
                                params['count'] = i;
                                params['ldxxcount'] = j;
                                params['yzbcount'] = k;
                                params['sqzbid'] = this.sqzbid;
                                // const loadingObj = this.$loading({
                                //     lock: true,
                                //     text: '提交中...',
                                //     spinner: 'el-icon-loading',
                                //     background: 'rgba(0, 0, 0, 0.3)',
                                //     target: document.body
                                // });
                                this.submitLoading = true;
                                saveSld(params).then((res) => {
                                    if (res == true) {
                                        this.$message({
                                            message: '提交成功',
                                            type: 'success'
                                        });
                                    } else {
                                        this.$message.error('提交失败！');
                                    }
                                    // loadingObj.close();
                                    // this.$store.commit("delete_tabs", '/addXq');
                                    // this.$router.push({path: '/Xqsl'});
                                    // this.$store.commit("set_active_index", '/Xqsl');
                                    this.$routerTab.close('/addXq');
                                    this.$routerTab.refresh('/Xqsl');
                                });
                            }
                        }
                    }
                });
                let lx = this.filters.sqlx;
            },
            handlezp: function (id) {
                let params = {
                    rwid: id,
                    name: this.resource,
                    type: this.filters.sqlx
                };
                this.zpLoading=true;
                toZpry(params).then((res) => {
                    if (res.data == true) {
                        this.$message({
                            message: '提交成功',
                            type: 'success'
                        });
                    } else {
                        this.$message.error('提交失败！');
                    }
                    this.resource = "";
                    this.zpLoading=false;
                    this.$routerTab.close('/addXq');
                    this.$routerTab.refresh('/Xqsl');
                    this.$routerTab.refresh('/Backlog');
                    this.$routerTab.refresh('/MyWork');
                });
            },
            chooseFileClick(index, row) {
                // this.zlType = row.ID;
                // setTimeout(() => {
                //     document.getElementById("chooseFile").click();
                //     document.getElementById("chooseFile").setAttribute("info", row.ID);
                //     document.getElementById("chooseFile").setAttribute("sqlx", this.filters.sqlx);
                // }, 1);
                zlType = row.ID;
            },
            // choosePsClick(index, row) {
            //     this.zlType = row.ID;
            //     document.getElementById("choosePs").click();
            // },
            submitUpload(event) {
                // let zlType = document.getElementById("chooseFile").getAttribute("info");
                // let sqlx = document.getElementById("chooseFile").getAttribute("sqlx");
                if (zlType == "" || zlType == null) {
                    this.$message.error('请选择资料类型！');
                    this.fileList = [];
                    return;
                }
                let fileList = this.fileList;
                this.zlType=zlType;
                let operation = this.zlType;
                if (fileList != null) {
                    for (let i = 0; i < fileList.length; i++) {
                        let file = fileList[i];
                        let formData = new FormData();
                        formData.append("operation", operation);
                        formData.append("multipartFiles", file.raw);
                        formData.append("filename", file.name);
                        formData.append("sqlx", this.filters.sqlx);
                        formData.append("sldid",this.sqzbid);
                        formData.append("sqbh",this.filters.sqbh);
                        importFile(formData).then((res) => {
                            let msg = res.msg;
                            if (msg == '上传成功') {
                                this.$message({
                                    message: '上传成功',
                                    type: 'success'
                                });
                                let fileId = res.fileId;
                                this.fileIds = this.fileIds.concat(fileId);
                                this.fileList = [];
                                // this.getFormBylx(this.filters.sqlx,1);
                                this.queryZlrow_vue();
                            } else {
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
            handleChange(file, fileList) {
                this.fileList = fileList;
            },
        },
        // watch: {
        //     zlrow() {
        //         console.log(this.zlrow);
        //     }
        // }
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
