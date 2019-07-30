<template>
    <section>
        <el-row>
            <!--工具条-->
            <el-col :span="24" class="toolbar" style="border: #f5f7fa solid 1px" v-if="cz_show">
                <el-form :inline="true">
                    <el-form-item>
                        <router-link :to="'/Jfxmlb'">
                            <el-button size="mini" type="info" @click="toReturn">
                                <i class="iconfont icon-quxiao"></i>取消</el-button>
                        </router-link>
                        <el-button size="mini" type="primary" @click="handleAdd">
                            <i class="iconfont icon-xinzeng"></i>新增缴费通知单</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
            <el-col :span="24" class="toolbar" >
                <!--列表-->
                <el-table :data="row" highlight-current-row v-loading="listLoading" style="width: 100%;"
                          :stripe="true" :row-style="rowClass" :header-cell-style="{fontSize:'15px',background:'#f5f7fa' }">
                    <el-table-column property="TZDBH" label="通知单编号" width="200" align="center">
                    </el-table-column>
                    <el-table-column property="YSJE" label="单据金额" align="center">
                    </el-table-column>
                    <el-table-column property="SSJE" label="交费金额" align="center">
                    </el-table-column>
                    <el-table-column property="TZDZT" label="状态" align="center" :formatter="formatZt">
                    </el-table-column>
                    <el-table-column property="ZDR_NAME" label="制单人" align="center">
                    </el-table-column>
                    <el-table-column property="ZDSJ" label="制单时间" width="160" align="center" :formatter="formatZdsj">
                    </el-table-column>
                    <el-table-column property="PJLXMC" label="票据类型" align="center">
                    </el-table-column>
                    <el-table-column property="PJH" label="票据号" align="center">
                    </el-table-column>
                    <el-table-column property="QRR_NAME" label="确认人" align="center">
                    </el-table-column>
                    <el-table-column property="JFQRSJ" label="确认时间" align="center" width="160" :formatter="formatJfqrsj">
                    </el-table-column>
                    <el-table-column fixed="right" label="操作" align="center" width="150">
                        <template slot-scope="scope">
                            <div v-if="scope.row.TZDZT == 0 && cz_show">
                                <label title="编辑">
                                    <i class="iconfont icon-ai-edit"  @click="toBj(scope.$index, scope.row)"
                                       style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                </label>
                                <label title="打印">
                                    <i class="iconfont icon-dayin"  @click="toDy(scope.$index, scope.row)"
                                       style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                </label>
                                <label title="确认">
                                    <i class="iconfont icon-queren"  @click="toQr(scope.$index, scope.row)"
                                       style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                </label>
                                <label title="发送短信">
                                    <i class="iconfont icon-iconfontichomeyy"  @click="toDx(scope.$index, scope.row)"
                                       style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                </label>
                            </div>
                            <div v-else-if="cz_show">
                                <label title="打印">
                                    <i class="iconfont icon-dayin"  @click="toDy(scope.$index, scope.row)"
                                       style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                </label>
                                <label title="确认">
                                    <i class="iconfont icon-queren"  @click="toQr(scope.$index, scope.row,1)"
                                       style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                </label>
                                <label title="发送短信">
                                    <i class="iconfont icon-iconfontichomeyy"  @click="toDx(scope.$index, scope.row)"
                                       style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                </label>
                            </div>
                            <div v-else-if="cz_show == false">
                                <label title="查看">
                                    <i class="iconfont icon-yulan_f"  @click="toShow(scope.$index, scope.row)"
                                       style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
                                </label>
                            </div>
                        </template>
                    </el-table-column>
                </el-table>
            </el-col>
        </el-row>

        <!--打印弹出-->
        <el-dialog title="" :visible.sync ="printFormVisible">
            <div id="printTest">
                <table class="border-table">
                    <caption align="top"><span style="font-size: 20px;font-family: 黑体;">合 肥 供 水 集 团 有 限 公 司</span></caption>
                    <caption align="top"><span style="font-size: 20px;font-family: 黑体;">用户接水工程费收费通知单</span></caption>
                    <tr>
                        <th></th>
                        <th></th>
                        <th colspan="2">项目编号：<span id="dy_xmbh"></span></th>
                    </tr>
                    <tr>
                        <td width="10%" height="40px">通知单号</td>
                        <td width="40%" height="40px"><span id="dy_tzdh"></span></td>
                        <td width="10%" height="40px">业务类型</td>
                        <td width="40%" height="40px"><span id="dy_ywlx"></span></td>
                    </tr>
                    <tr>
                        <td height="40px">报建单位</td>
                        <td height="40px"><span id="dy_dwmc"></span></td>
                        <td height="40px">联系人</td>
                        <td height="40px">{{dy_lxr}}</td>
                    </tr>
                    <tr>
                        <td height="40px">项目名称</td>
                        <td colspan="3" height="40px"><span id="dy_xmmc"></span></td>
                    </tr>
                    <tr>
                        <td height="40px">项目地址</td>
                        <td colspan="3" height="40px">{{dy_xmdz}}</td>
                    </tr>
                    <tr>
                        <td rowspan="2" height="40px">工程费</td>
                        <td height="40px">小写：<span id="dy_gcfxx"></span>元</td>
                        <td rowspan="2" height="40px">账号</td>
                        <td rowspan="2" height="40px">
                            <span id="dy_zhmc"></span><br>
                            <span id="dy_zh"></span><br>
                            <span id="dy_zhyh"></span></td>
                    </tr>
                    <tr>
                        <td height="40px">大写：<span id="dy_gcfdx"></span></td>
                    </tr>
                    <tr>
                        <td height="40px">项目情况</td>
                        <td colspan="3" height="40px"><span id="dy_xmqk"></span></td>
                    </tr>
                    <tr>
                        <td height="40px">备注</td>
                        <td colspan="3" height="40px" align="left">
                            <div style="text-align:left;"><span id="dy_bz"></span></div>
                        </td>
                    </tr>
                    <tr>

                    </tr>
                </table>
                <div>
                    <span>经办签字：（单位盖章）</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <!--<span>项目标识： <span id="dy_xmbs"></span></span>-->&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span style="float: right">打印日期：<span id="dy_dyrq"></span></span>
                </div>
        　　　</div>
            <div style="text-align: center;margin-top: 10px">
                <el-button size="mini" style="margin-bottom: 15px" type="success" v-print="'#printTest'">
                    <i class="iconfont icon-dayin_f"></i>打印
                </el-button>
            </div>
        </el-dialog>

        <!--发送短信弹出-->
        <el-dialog title="发送短信" :visible.sync="dxFormVisible" width="30%">
            <el-form>
                <el-form-item>
                    <el-col :span="6" style="text-align: center">
                        <span>联系人</span>
                    </el-col>
                    <el-col :span="18">
                        <el-input size="small" v-model="dxlxr" :readonly="true"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-col :span="6" style="text-align: center">
                        <span>手机号码</span>
                    </el-col>
                    <el-col :span="18">
                        <el-input size="small" v-model="dxsjh" :readonly="true"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-input size="small" type="textarea" v-model="dxbz"
                              :autosize="{ minRows: 8, maxRows: 10}"></el-input>
                </el-form-item>
                <el-form-item style="text-align: center">
                    <el-button size="mini" type="info" @click="dxReturn_vue">
                        <i class="iconfont icon-quxiao"></i>取消</el-button>
                    <el-button size="mini" type="success" @click="dxadd_vue">
                        <i class="iconfont icon-queren"></i>发送</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </section>
</template>

<script>
    import util from '../../common/js/util'

    import { getSftzd,finddxForm,fsdx,getxmdz } from '../../api/htsf';
    export default {
        data() {
            return {
                row: [],
                total: 10,//表格数据总数量
                page: 1,//当前页码
                size: 10,//每页容量
                listLoading: false,
                xmid:this.$route.query.xmid,//项目ID
                xmbh:this.$route.query.xmbh,//项目编号
                dwmc:this.$route.query.dwmc,//单位名称
                azlx:this.$route.query.azlx,//安装类型
                sqlx:this.$route.query.sqlx,//申请类型
                printFormVisible:false,//打印界面是否显示
                tableData:'',

                dxFormVisible:false,//发送短信界面是否显示
                dxlxr:this.$route.query.lxr,
                dxsjh:this.$route.query.lxrsjh,
                dxbz:'',

                dy_lxr:'',
                dy_xmdz:'',

                ishx : this.$route.query.ishx,
                cz_show : true
            }
        },
        methods: {
            //返回
            toReturn(){
                this.$routerTab.close();
            },
            //获取收费通知单列表
            getSftzd() {
                let params = {
                    xmid: this.xmid
                };
                this.listLoading = true;
                getSftzd(params).then((res) => {
                    this.row = res.data.row;
                    this.listLoading = false;
                });
            },
            //跳转到新增收费通知单界面
            handleAdd(){
                let params = {
                    xmid : this.xmid,
                    dwmc : this.dwmc,
                    azlx : this.azlx,
                    sqlx : this.sqlx,
                    xmbh : this.xmbh,
                };
                this.$router.push({path: '/AddJftzd', query: params});
            },
            //制单时间格式转换
            formatZdsj: function (row, column) {
                let sj = row.ZDSJ;
                return this.publicUtil.formatChange(sj);
            },
            //确认时间格式转换
            formatJfqrsj: function (row, column) {
                let sj = row.JFQRSJ;
                return this.publicUtil.formatChange(sj);
            },
            //通知单状态转换
            formatZt:function (row, column) {
                return row.TZDZT == 0 ? '待确认' : '已确认';
            },
            //编辑通知单
            toBj:function(index,row){
                let params = {
                    xmid : row.XMID,
                    tzdid: row.ID,
                    azlx : this.azlx,
                    sqlx : this.sqlx,
                    lxr:this.dxlxr,
                    lxrsjh:this.dxsjh
                };
                this.$router.push({path: '/editJftzd', query: params});
                this.$routerTab.close("/AddJftzd?xmid="+this.xmid+"&dwmc="+this.dwmc+"&azlx="+this.azlx+
                     "&sqlx="+this.sqlx+"&xmbh="+this.xmbh);
            },
            //查看通知单
            toShow:function(index,row){
                let params = {
                    xmid : row.XMID,
                    tzdid: row.ID,
                    azlx : this.azlx,
                    sqlx : this.sqlx,
                    lxr:this.dxlxr,
                    lxrsjh:this.dxsjh,
                    ishx: 1
                };
                this.$router.push({path: '/showJftzd', query: params});
                this.$routerTab.close("/AddJftzd?xmid="+this.xmid+"&dwmc="+this.dwmc+"&azlx="+this.azlx+
                    "&sqlx="+this.sqlx+"&xmbh="+this.xmbh);
            },
            //确认通知单
            toQr:function (index, row,type) {
                let params = {
                    zbid:row.ID,
                    ysje:row.YSJE,
                    xmid : row.XMID,
                    azlx : this.azlx,
                    sqlx : this.sqlx,
                    dwmc: this.dwmc,
                    xmbh:this.xmbh,
                    lxr:this.dxlxr,
                    lxrsjh:this.dxsjh
                };
                if(type == 1){
                    params['type'] = '1';
                }else{
                    params['type'] = '0';
                }
                this.$router.push({path: '/sfqr', query: params})
            },
            //发送短信
            toDx:function(index,row){
                this.dxFormVisible = true;
                let params = {xmid:this.xmid};
                finddxForm(params).then(res=>{
                    this.dxbz = res.data.content;
                });
            },
            //发送短信
            dxadd_vue(){
                let params = {
                    xmid:this.xmid,
                    jsr:this.dxlxr,
                    jsrsjh:this.dxsjh,
                    dxnr:this.dxbz
                };
                fsdx(params).then(res=>{
                    if (res == "ok"){
                        this.$message({
                            type: 'success',
                            message: '短信发送成功'
                        });
                    }else {
                        this.$message({
                            type: 'warning',
                            message: '短信发送失败'
                        });
                    }

                });

            },
            dxReturn_vue(){
                this.dxFormVisible = false;
            },
            //打印通知单
            toDy:function (index, row) {
                this.printFormVisible = true;
                let para = {xmid:this.xmid,id:row.ID};
                var xmqkGs = "";
                getxmdz(para).then(res=>{
                    try {
                        xmqkGs = res.data.gs[0].A
                    } catch (e) {
                        // 捕获处理
                        console.log(e); // ReferenceError: c is not defined
                    }


                    /**
                     * element 应该是弹框显示出来的时候才生成元素,所以需要this.$nextTick
                     */
                    this.$nextTick(()=>{
                        document.getElementById("dy_xmbh").innerText = this.xmbh;
                        document.getElementById("dy_tzdh").innerText = row.TZDBH;
                        document.getElementById("dy_ywlx").innerText = this.azlx;
                        document.getElementById("dy_dwmc").innerText = row.JFDW;
                        this.dy_lxr = this.dxlxr+"（"+this.dxsjh+"）";
                        this.dy_xmdz = res.data.list[0].xmdz;
                        document.getElementById("dy_xmmc").innerText = row.XMMC;
                        document.getElementById("dy_zhmc").innerText = row.ZHMC;
                        document.getElementById("dy_zh").innerText = row.ZH;
                        document.getElementById("dy_zhyh").innerText = row.ZHYH;
                        document.getElementById("dy_xmqk").innerText = xmqkGs;
                       // document.getElementById("dy_xmqk").innerText = '工商表Φ40×1，居民表Φ40×1';
                        document.getElementById("dy_bz").innerText = row.BZ;
                        document.getElementById("dy_gcfxx").innerText = row.YSJE;
                        document.getElementById("dy_gcfdx").innerText = this.publicUtil.toChies(row.YSJE);
                        // document.getElementById("dy_xmbs").innerText = row.JFDW;
                        document.getElementById("dy_dyrq").innerText = this.publicUtil.getNowDay();
                    });
                });
            },
            rowClass:function (row,index) {
                return {"height":"6.0vh"}
            }
        },
        mounted() {
            this.getSftzd();
            if(this.ishx == 1){
                this.cz_show = false;
            }
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
