<template>
    <section>
        <el-row style="margin-top: 15px;">
            <el-col :span="24">
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">项目配置范围</div>
                    </div>
                    <div class="text item">
                        <el-row>
                            <el-col :span="20">
                                <el-form :inline="true">
                                    <el-form-item>
                                        <el-button size="mini" type="primary" @click="openSbxx">
                                            <i class="iconfont icon-yulan_f"></i>水表信息</el-button>
                                    </el-form-item>
                                </el-form>
                                <table class="border-table">
                                    <tr style="text-align: center">
                                        <td>类别</td>
                                        <td>数量</td>
                                        <td>配置时间</td>
                                        <td>配置人</td>
                                        <td>操作</td>
                                    </tr>
                                    <tr style="text-align: center">
                                        <td>庭院</td>
                                        <td>{{tysl}}</td>
                                        <td>{{tysj}}</td>
                                        <td>{{tymc}}</td>
                                        <td> <label title="查看">
                                            <i class="iconfont icon-yulan"  @click="getTylist()" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label></td>
                                    <!--    <td title="查看" @click="getTylist" style="color: #00a65a"><i class="iconfont icon-tiaocha"></i></td>-->
                                    </tr>
                                    <tr style="text-align: center">
                                        <td>楼栋</td>
                                        <td>{{ldsl}}</td>
                                        <td>{{ldsj}}</td>
                                        <td>{{ldmc}}</td>
                                        <td> <label title="查看">
                                            <i class="iconfont icon-yulan"  @click="getLdlist()" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label></td>
                                      <!--  <td title="查看" @click="getLdlist" style="color: #00a65a"><i class="iconfont icon-tiaocha"></i></td>-->
                                    </tr>
                                    <tr style="text-align: center">
                                        <td>地下室</td>
                                        <td>{{dxssl}}</td>
                                        <td>{{dxssj}}</td>
                                        <td>{{dxsmc}}</td>
                                        <td> <label title="查看">
                                            <i class="iconfont icon-yulan"  @click="getDxslist()" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label></td>
                                      <!--  <td title="查看" @click="getDxslist(3)" style="color: #00a65a"><i class="iconfont icon-tiaocha"></i></td>-->
                                    </tr>
                                    <tr style="text-align: center">
                                        <td>泵房</td>
                                        <td>{{bfsl}}</td>
                                        <td>{{bfsj}}</td>
                                        <td>{{bfmc}}</td>
                                        <td> <label title="查看">
                                            <i class="iconfont icon-yulan"  @click="getBflist()" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label></td>
                                      <!--  <td title="查看" @click="getBflist(4)" style="color: #00a65a"><i class="iconfont icon-tiaocha"></i></td>-->
                                    </tr>
                                    <tr style="text-align: center">
                                        <td>水箱</td>
                                        <td>{{sxsl}}</td>
                                        <td>{{sxsj}}</td>
                                        <td>{{sxmc}}</td>
                                        <td> <label title="查看">
                                            <i class="iconfont icon-yulan"  @click="getSxlist()" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                        </label></td>
                                       <!-- <td title="查看" @click="getSxlist(5)" style="color: #00a65a"><i class="iconfont icon-tiaocha"></i></td>-->
                                    </tr>
                                </table>
                            </el-col>
                        </el-row>
                    </div>
                </el-card>

                <!--庭院查看详细数据-->
                <el-dialog :title="title" :visible.sync="selectTysj">
                    <el-tabs type="card">
                        <el-table :data="tyform" v-loading="listLoading_ty">
                            <el-table-column property="bh" label="庭院编号" width="200"></el-table-column>
                            <el-table-column property="mc" label="庭院名称" width="200"></el-table-column>
                            <el-table-column property="username" label="创建人" width="150"></el-table-column>
                            <el-table-column property="cjsj_str" label="创建时间" width="250"></el-table-column>
                        </el-table>
                        <el-col :span="24" class="toolbar">
                            <el-pagination background layout="total, prev, pager, next, sizes , jumper"
                                           @current-change="handleCurrentChange_ty" @size-change="handleSizeChange_ty"
                                           :page-sizes="[5, 10, 20, 50]"
                                           :page-size="this.size_ty" :total="this.total_ty" style="float:right;">
                            </el-pagination>
                        </el-col>
                    </el-tabs>
                </el-dialog>
                <!--楼栋查看详细数据-->
                <el-dialog :title="title" :visible.sync="selectLdsj">
                    <el-tabs type="card">
                        <el-table :data="ldform" v-loading="listLoading_ld">
                            <el-table-column property="ldbh" label="楼栋编号" width="200"></el-table-column>
                            <el-table-column property="ldmc" label="楼栋名称" width="200"></el-table-column>
                            <el-table-column property="username" label="创建人" width="150"></el-table-column>
                            <el-table-column property="cjsj_str" label="创建时间" width="250"></el-table-column>
                        </el-table>
                        <el-col :span="24" class="toolbar">
                            <el-pagination background layout="total, prev, pager, next, sizes , jumper"
                                           @current-change="handleCurrentChange_ld" @size-change="handleSizeChange_ld"
                                           :page-sizes="[5, 10, 20, 50]"
                                           :page-size="this.size_ld" :total="this.total_ld" style="float:right;">
                            </el-pagination>
                        </el-col>
                    </el-tabs>
                </el-dialog>
                <!--地下室-->
                <el-dialog :title="title" :visible.sync="selectDxssj">
                    <el-tabs type="card">
                        <el-table :data="dxsform" v-loading="listLoading_dxs">
                            <el-table-column property="bh" label="庭院编号" width="200"></el-table-column>
                            <el-table-column property="mc" label="庭院名称" width="200"></el-table-column>
                            <el-table-column property="username" label="创建人" width="150"></el-table-column>
                            <el-table-column property="cjsj_str" label="创建时间" width="250"></el-table-column>
                        </el-table>
                        <el-col :span="24" class="toolbar">
                            <el-pagination background layout="total, prev, pager, next, sizes , jumper"
                                           @current-change="handleCurrentChange_dxs" @size-change="handleSizeChange_dxs"
                                           :page-sizes="[5, 10, 20, 50]"
                                           :page-size="this.size_dxs" :total="this.total_dxs" style="float:right;">
                            </el-pagination>
                        </el-col>
                    </el-tabs>
                </el-dialog>
                <!--泵房-->
                <el-dialog :title="title" :visible.sync="selectBfsj">
                    <el-tabs type="card">
                        <el-table :data="bfform" v-loading="listLoading_bf">
                            <el-table-column property="bh" label="庭院编号" width="200"></el-table-column>
                            <el-table-column property="mc" label="庭院名称" width="200"></el-table-column>
                            <el-table-column property="username" label="创建人" width="150"></el-table-column>
                            <el-table-column property="cjsj_str" label="创建时间" width="250"></el-table-column>
                        </el-table>
                        <el-col :span="24" class="toolbar">
                            <el-pagination background layout="total, prev, pager, next, sizes , jumper"
                                           @current-change="handleCurrentChange_bf" @size-change="handleSizeChange_bf"
                                           :page-sizes="[5, 10, 20, 50]"
                                           :page-size="this.size_bf" :total="this.total_bf" style="float:right;">
                            </el-pagination>
                        </el-col>
                    </el-tabs>
                </el-dialog>
                <!--水箱查看详细数据-->
                <el-dialog :title="title" :visible.sync="selectSxsj">
                    <el-tabs type="card">
                        <el-table :data="sxform" v-loading="listLoading_sx">
                            <el-table-column property="sxbh" label="水箱编号" width="200"></el-table-column>
                            <el-table-column property="sxmc" label="水箱名称" width="200"></el-table-column>
                            <el-table-column property="username" label="创建人" width="150"></el-table-column>
                            <el-table-column property="cjsj_str" label="创建时间" width="250"></el-table-column>
                        </el-table>
                        <el-col :span="24" class="toolbar">
                            <el-pagination background layout="total, prev, pager, next, sizes , jumper"
                                           @current-change="handleCurrentChange_sx" @size-change="handleSizeChange_sx"
                                           :page-sizes="[5, 10, 20, 50]"
                                           :page-size="this.size_sx" :total="this.total_sx" style="float:right;">
                            </el-pagination>
                        </el-col>
                    </el-tabs>
                </el-dialog>
                <!--查看水表信息界面-->
                <el-dialog title="水表信息" :visible.sync="xdsbFormVisible" width="60%">
                    <el-tabs type="card">
                        <el-tab-pane label="五级计量表">
                            <el-table ref="wbmultipleTable" :data="wjjlb" border highlight-current-row
                                      v-loading="sblistLoading" style="width: 100%;">
                                <el-table-column property="HH" label="户号" width="80" align="center">
                                </el-table-column>
                                <el-table-column property="HM" label="户名" width="90" align="center">
                                </el-table-column>
                                <el-table-column property="HM" label="安装类型" width="100" align="center">
                                </el-table-column>
                                <el-table-column property="DZ" label="装表地址" width="100" align="center">
                                </el-table-column>
                                <el-table-column property="KJ" label="口径" width="80" align="center">
                                </el-table-column>
                                <el-table-column property="MPH" label="门牌号" width="80" align="center">
                                </el-table-column>
                                <el-table-column property="YSXZ" label="用水性质" width="100" align="center">
                                </el-table-column>
                                <el-table-column property="HM" label="行业分类" width="100" align="center">
                                </el-table-column>
                                <el-table-column property="JFFSBH" label="缴费方式" width="90" align="center">
                                </el-table-column>
                            </el-table>
                            <el-col :span="24" class="toolbar">
                                <el-pagination layout="total, prev, pager, next" @current-change="wbhandleCurrentChange"
                                               :page-size="this.wbsize" :total="this.wbtotal" style="float:right;">
                                </el-pagination>
                            </el-col>
                        </el-tab-pane>
                        <el-tab-pane label="地表">
                            <el-table ref="dbmultipleTable" :data="db" border highlight-current-row
                                      v-loading="sblistLoading" style="width: 100%;">
                                <el-table-column property="HH" label="户号" width="80" align="center">
                                </el-table-column>
                                <el-table-column property="HM" label="户名" width="90" align="center">
                                </el-table-column>
                                <el-table-column property="HM" label="安装类型" width="100" align="center">
                                </el-table-column>
                                <el-table-column property="DZ" label="装表地址" width="100" align="center">
                                </el-table-column>
                                <el-table-column property="KJ" label="口径" width="80" align="center">
                                </el-table-column>
                                <el-table-column property="MPH" label="门牌号" width="80" align="center">
                                </el-table-column>
                                <el-table-column property="YSXZ" label="用水性质" width="100" align="center">
                                </el-table-column>
                                <el-table-column property="HM" label="行业分类" width="100" align="center">
                                </el-table-column>
                                <el-table-column property="JFFSBH" label="缴费方式" width="90" align="center">
                                </el-table-column>
                            </el-table>
                            <el-col :span="24" class="toolbar">
                                <el-pagination layout="total, prev, pager, next" @current-change="dbhandleCurrentChange"
                                               :page-size="this.dbsize" :total="this.dbtotal" style="float:right;">
                                </el-pagination>
                            </el-col>
                        </el-tab-pane>
                        <el-tab-pane label="户表">
                            <el-table ref="hbmultipleTable" :data="hb" border highlight-current-row
                                      v-loading="sblistLoading" style="width: 100%;">
                                <el-table-column property="TYMC" label="所属庭院" width="200" align="center">
                                </el-table-column>
                                <el-table-column property="LDMC" label="楼栋名称" width="200" align="center">
                                </el-table-column>
                                <el-table-column property="ZHS" label="总户数" width="180" align="center">
                                </el-table-column>
                                <el-table-column property="LFSX" label="楼房属性" width="190" align="center">
                                </el-table-column>
                            </el-table>
                            <el-col :span="24" class="toolbar">
                                <el-pagination layout="total, prev, pager, next" @current-change="hbhandleCurrentChange"
                                               :page-size="this.hbsize" :total="this.hbtotal" style="float:right;">
                                </el-pagination>
                            </el-col>
                        </el-tab-pane>
                    </el-tabs>
                </el-dialog>
            </el-col>
        </el-row>
    </section>
</template>

<script>

    import {selectZhysfwxx, getSbxx} from '../../api/zhys'

    export default {
        name: "Zhysfw",
        data() {
            return {
                xmid: this.$route.query.xmid,//项目id
                total_ty: 5, total_ld: 5, total_dxs: 5, total_bf: 5, total_sx: 5,//表格数据总数量
                page_ty: 1, page_ld: 1, page_dxs: 1, page_bf: 1, page_sx: 1,//当前页码
                size_ty: 5, size_ld: 5, size_dxs: 5, size_bf: 5, size_sx: 5,//每页容量
                //数量
                tysl: "", ldsl: "", dxssl: "", bfsl: "", sxsl: "",
                //时间
                tysj: "", ldsj: "", dxssj: "", bfsj: "", sxsj: "",
                //名称
                tymc: '', ldmc: '', dxsmc: '', bfmc: '', sxmc: '',
                title: '', bhstr: '', mcstr: '',
                bh: "", mc: "", username: "", cjsj_str: "",
                ldbh: "", ldmc: "", username: "", cjsj_str: "",
                sxbh: "", sxmc: "", username: "", cjsj_str: "",
                tyform: [],
                ldform: [],
                dxsform: [],
                bfform: [],
                sxform: [],
                listLoading_ty: false,
                listLoading_ld: false,
                listLoading_dxs: false,
                listLoading_bf: false,
                listLoading_sx: false,
                selectTysj: false,//庭院--地下室--泵房
                selectLdsj: false,//楼栋
                selectDxssj: false,//地下室
                selectBfsj: false,//泵房
                selectSxsj: false,//水箱
                xdsbFormVisible: false,//水表信息界面是否显示
                sblistLoading: false,//水表信息列表加载
                wjjlb: [],//五级计量表集合
                db: [],//地表集合
                hb: [],//户表集合
                /*分页*/
                wbsize: 0,//五级计量表每页容量
                wbtotal: 0,//五级计量表表格数据总数量
                wbpage: 0,//五级计量表当前页码
                hbsize: 0,//户表每页容量
                hbtotal: 0,//户表表格数据总数量
                hbpage: 0,//户表当前页码
                dbsize: 0,//地表每页容量
                dbtotal: 0,//地表表格数据总数量
                dbpage: 0,//地表当前页码
                /*分页*/
            }
        },
        methods: {
            //查看水表信息
            openSbxx() {
                let params = {
                    xmid: this.xmid,
                };
                getSbxx(params).then((res) => {
                    this.wjjlb = res.data.wblist;
                    this.db = res.data.dblist;
                    this.hb = res.data.hblist;
                    this.xdsbFormVisible = true;
                });
            },
            getZhyfwsxx(str) { //综合验收信息
                let params = Object.assign({
                    xmid: this.xmid, page: this.page_ty, size: this.size_ty,
                    page: this.page_ld, size: this.size_ld,
                    page: this.page_dxs, size: this.size_dxs,
                    page: this.page_bf, size: this.size_bf,
                    page: this.page_sx, size: this.size_sx,
                });
                selectZhysfwxx(params).then((res) => {
                    if (res.data.tysl > 0) {
                        this.tysl = res.data.tysl;
                        this.tysj = res.data.tylist[0].cjsj_str;
                        this.tymc = res.data.tylist[0].username;
                    } else {
                        this.tysl = "- ";
                        this.tysj = "- ";
                        this.tymc = "- ";
                    }
                    if (res.data.ldsl > 0) {
                        this.ldsl = res.data.ldsl;
                        this.ldsj = res.data.ldlist[0].cjsj_str;
                        this.ldmc = res.data.ldlist[0].username;
                    } else {
                        this.ldsl = "- ";
                        this.ldsj = "- ";
                        this.ldmc = "- ";
                    }
                    if (res.data.dxssl > 0) {
                        this.dxssl = res.data.dxssl;
                        this.dxssj = res.data.dxslist[0].cjsj_str;
                        this.dxsmc = res.data.dxslist[0].username;
                    } else {
                        this.dxssl = "- ";
                        this.dxssj = "- ";
                        this.dxsmc = "- ";
                    }
                    if (res.data.bfsl > 0) {
                        this.bfsl = res.data.bfsl;
                        this.bfsj = res.data.bflist[0].cjsj_str;
                        this.bfmc = res.data.bflist[0].username;
                    } else {
                        this.bfsl = "- ";
                        this.bfsj = "- ";
                        this.bfmc = "- ";
                    }
                    if (res.data.sxsl > 0) {
                        this.sxsl = res.data.sxsl;
                        this.sxsj = res.data.sxlist[0].cjsj_str;
                        this.sxmc = res.data.sxlist[0].username;
                    } else {
                        this.sxsl = "- ";
                        this.sxsj = "- ";
                        this.sxmc = "- ";
                    }
                });
            },
            getTylist() {//庭院
                let params = Object.assign({xmid: this.xmid, page: this.page_ty, size: this.size_ty});
                this.listLoading_ty = true;
                selectZhysfwxx(params).then((res) => {
                    this.title = "庭院列表数据";
                    this.total_ty = res.data.tysl;
                    this.tyform = res.data.tylist;
                    this.listLoading_ty = false;
                    this.selectTysj = true;
                });
            },
            getLdlist() {//楼栋
                let params = Object.assign({xmid: this.xmid, page: this.page_ld, size: this.size_ld});
                this.listLoading_ld = true;
                selectZhysfwxx(params).then((res) => {
                    this.title = "楼栋列表数据";
                    this.total_ld = res.data.ldsl;
                    this.ldform = res.data.ldlist;
                    this.listLoading_ld = false;
                    this.selectLdsj = true;
                });
            },
            getDxslist() {//地下室
                let params = Object.assign({xmid: this.xmid, page: this.page_dxs, size: this.size_dxs});
                this.listLoading_dxs = true;
                selectZhysfwxx(params).then((res) => {
                    this.title = "地下室列表数据";
                    this.total_dxs = res.data.dxssl;
                    this.dxsform = res.data.dxslist;
                    this.listLoading_dxs = false;
                    this.selectDxssj = true;
                });
            },
            getBflist() {//泵房
                let params = Object.assign({xmid: this.xmid, page: this.page_bf, size: this.size_bf});
                this.listLoading_bf = true;
                selectZhysfwxx(params).then((res) => {
                    this.title = "泵房列表数据";
                    this.total_bf = res.data.bfsl;
                    this.bfform = res.data.bflist;
                    this.listLoading_bf = false;
                    this.selectBfsj = true;
                });
            },
            getSxlist() {//水箱
                let params = Object.assign({xmid: this.xmid, page: this.page_sx, size: this.size_sx});
                this.listLoading_sx = true;
                selectZhysfwxx(params).then((res) => {
                    this.title = "水箱列表数据";
                    this.total_sx = res.data.sxsl;
                    this.sxform = res.data.sxlist;
                    this.listLoading_sx = false;
                    this.selectSxsj = true;
                });
            },
            handleCurrentChange_ty(val) {//庭院
                this.page_ty = val;
                this.getTylist();
            },
            handleSizeChange_ty(val) {
                this.size_ty = val;
                this.page_ty = 1;
                this.getTylist();
            },
            handleCurrentChange_ld(val) {//楼栋
                this.page_ld = val;
                this.getLdlist();
            },
            handleSizeChange_ld(val) {
                this.size_ld = val;
                this.page_ld = 1;
                this.getLdlist();
            },
            handleCurrentChange_dxs(val) {//地下室
                this.page_dxs = val;
                this.getDxslist();
            },
            handleSizeChange_dxs(val) {
                this.size_dxs= val;
                this.page_dxs = 1;
                this.getDxslist();
            },
            handleCurrentChange_bf(val) {//泵房
                this.page_bf = val;
                this.getBflist();
            },
            handleSizeChange_bf(val) {
                this.size_bf = val;
                this.page_bf = 1;
                this.getBflist();
            },
            handleCurrentChange_sx(val) {//水箱
                this.page_sx = val;
                this.getSxlist();
            },
            handleSizeChange_sx(val) {
                this.size_sx = val;
                this.page_sx = 1;
                this.getSxlist();
            },


            wbhandleCurrentChange(val) {
                this.wbpage = val;
            },
            dbhandleCurrentChange(val) {
                this.dbpage = val;
            },
            hbhandleCurrentChange(val) {
                this.hbpage = val;
            },
        }
    }
</script>

<style scoped>

</style>