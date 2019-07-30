<template>
    <el-row :gutter="15">
        <!-- <el-col :span="6">
             <el-card class="box-card" style="height: 620px;overflow: auto;">
                 <div class="grid-content bg-purple">
                     <el-tree node-key="id" :data="fpryTree" :props="defaultProps1" ref="tree1"
                              :default-checked-keys="selectUserid"
                              default-expand-all

                              @node-click="handleNodeClick">
                         <span slot-scope="{ node, data }">
                             <span>
                                 <i :class="data.icon"></i>{{ node.label }}
                             </span>
                         </span>
                     </el-tree>
                 </div>
             </el-card>
         </el-col>-->
        <!--列表-->
        <el-col :span="16" class="toolbar">
            <el-form :model="filters">
                <el-form-item>
                    <el-col :span="4" style="text-align: right">
                        <span> 用户名称 &nbsp;</span>
                    </el-col>
                    <el-col :span="5">
                        <el-input v-model="username" size="small " :disabled="true"></el-input>
                    </el-col>
                    <el-col :span="4" style="text-align: right">
                        <span> 用户手机号 &nbsp;</span>
                    </el-col>
                    <el-col :span="5">
                        <el-input v-model="userphon" size="small " :disabled="true"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-col :span="4" style="text-align: right">
                        <span>科室类型 &nbsp;</span>
                    </el-col>
                    <el-col :span="5">
                        <el-select v-model="filters.codevalue" clearable placeholder="请选择" style="width:100%"
                                   size="small ">
                            <el-option label="全部" value=""></el-option>
                            <el-option label="居民客户科" value="10"></el-option>
                            <el-option label="工商客户科" value="20"></el-option>
                            <el-option label="出户改造科" value="30"></el-option>
                        </el-select>
                    </el-col>
                    <el-col :span="4" style="text-align: right">
                        <span>业务提醒 &nbsp;</span>
                    </el-col>
                    <el-col :span="5">
                        <el-select v-model="filters.id" clearable placeholder="请选择" style="width:100%" size="small ">
                            <el-option label="全部" value=""></el-option>
                            <el-option label="业务黄灯提醒" value="1"></el-option>
                            <el-option label="业务即将红灯" value="2"></el-option>
                            <el-option label="业务红灯提醒" value="3"></el-option>
                        </el-select>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-col :span="4" style="text-align: right">
                        <span> 业务名称 &nbsp;</span>
                    </el-col>
                    <el-col :span="5">
                        <el-input v-model="filters.activityname" size="small "></el-input>
                    </el-col>
                    <el-col :span="12" style="text-align: center">
                        <el-button type="primary" size="small " v-on:click="getHsjglr_vue(2)">查询</el-button>
                        <!-- <el-button type="info" @click.native="setVisible = false" size="mini"><i
                                  class="iconfont icon-quxiao"></i>取消
                          </el-button>-->
                        <!--    <el-button type="primary" @click.native="tijiao" size="mini"><i
                                    class="iconfont icon-queren"></i>提交
                            </el-button>-->
                        <el-button type="primary" @click.native="openSettings" size="mini"><i
                                class="iconfont icon-queren"></i>操作
                        </el-button>
                    </el-col>

                </el-form-item>
            </el-form>
        </el-col>

        <el-col :span="16" class="toolbar">
            <el-table
                    ref="dxlbtable"
                    :data="row"
                    tooltip-effect="dark"
                    width="100%"
                    height="398px"
            >
                <!-- <el-table-column
                         type="selection"
                         width="55">
                 </el-table-column>-->
                <el-table-column prop="codename" label="科室类型" width="150" align="center">
                </el-table-column>
                <el-table-column prop="dxmblx" label="业务提醒" width="150" align="center">
                </el-table-column>
                <el-table-column prop="activityname" label="业务名称" width="200" align="center">
                </el-table-column>
                <!--  <el-table-column prop="dxid" label="是否订阅" width="150" align="center">
                  </el-table-column>-->
            </el-table>
            <el-col :span="24" class="toolbar" style="float: right">
                <!--工具条-->
                <el-pagination background layout="total" :page-size="this.size" :total="this.total"
                               style="float:right;">
                </el-pagination>
            </el-col>
            <div slot="footer" class="dialog-footer" style="text-align: center;margin-top: 25px;"></div>
        </el-col>

        <!--弹窗-->
        <el-dialog title="设置短信接收" :visible.sync="setVisible">
            <el-col :span="24" class="toolbar" style="border: #f5f7fa solid 1px">
                <el-form :model="filters_1">
                    <el-form-item>
                        <el-col :span="4" style="text-align: right">
                            <span> 用户名称 &nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input v-model="username" size="small " :disabled="true"></el-input>
                        </el-col>
                        <el-col :span="4" style="text-align: right">
                            <span> 用户手机号 &nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input v-model="userphon" size="small " :disabled="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="4" style="text-align: right">
                            <span>科室类型 &nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-select v-model="filters_1.codevalue" clearable placeholder="请选择" style="width:100%"
                                       size="small ">
                                <el-option label="全部" value=""></el-option>
                                <el-option label="居民客户科" value="10"></el-option>
                                <el-option label="工商客户科" value="20"></el-option>
                                <el-option label="出户改造科" value="30"></el-option>
                            </el-select>
                        </el-col>
                        <el-col :span="4" style="text-align: right">
                            <span>业务提醒 &nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-select v-model="filters_1.id" clearable placeholder="请选择" style="width:100%"
                                       size="small ">
                                <el-option label="全部" value=""></el-option>
                                <el-option label="业务黄灯提醒" value="1"></el-option>
                                <el-option label="业务即将红灯" value="2"></el-option>
                                <el-option label="业务红灯提醒" value="3"></el-option>
                            </el-select>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="4" style="text-align: right">
                            <span> 业务名称 &nbsp;</span>
                        </el-col>
                        <el-col :span="5">
                            <el-input v-model="filters_1.activityname" size="small "></el-input>
                        </el-col>
                        <el-col :span="12" style="text-align: center">
                            <el-button type="primary" size="small " v-on:click="getHsjglr_vue_1">查询</el-button>
                            <el-button type="info" @click.native="setVisible = false" size="mini"><i
                                    class="iconfont icon-quxiao"></i>取消
                            </el-button>
                            <el-button type="primary" @click.native="tijiao" size="mini"><i
                                    class="iconfont icon-queren"></i>提交
                            </el-button>
                        </el-col>

                    </el-form-item>
                </el-form>
            </el-col>

            <el-table
                    ref="dxlbtable"
                    :data="row_1"
                    tooltip-effect="dark"
                    style="width: 100%"
                    height="398px"
                    @select="onTableSelect"
                    @selection-change="handleSelectionChange">
                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>
                <el-table-column prop="codename" label="科室类型" width="150" align="center">
                </el-table-column>
                <el-table-column prop="dxmblx" label="业务提醒" width="150" align="center">
                </el-table-column>
                <el-table-column prop="activityname" label="业务名称" width="200" align="center">
                </el-table-column>
            </el-table>
            <el-col :span="24" class="toolbar">
                <el-pagination background layout="total"
                               :page-size="this.size_1" :total="this.total_1" style="float:right;">
                </el-pagination>
            </el-col>
            <div slot="footer" class="dialog-footer" style="text-align: center;margin-top: 25px;"></div>

        </el-dialog>
    </el-row>

    <!-- <el-dialog title="设置短信接收" :visible.sync="setVisible">-->
    <!--<el-col :span="24" class="toolbar" style="border: #f5f7fa solid 1px">
        <el-form :model="filters">
            <el-form-item>
                <el-col :span="4" style="text-align: right">
                    <span> 用户名称 &nbsp;</span>
                </el-col>
                <el-col :span="5">
                    <el-input v-model="username" size="small " :disabled="true"></el-input>
                </el-col>
                <el-col :span="4" style="text-align: right">
                    <span> 用户手机号 &nbsp;</span>
                </el-col>
                <el-col :span="5">
                    <el-input v-model="userphon" size="small " :disabled="true"></el-input>
                </el-col>
            </el-form-item>
            <el-form-item>
                <el-col :span="4" style="text-align: right">
                    <span>科室类型 &nbsp;</span>
                </el-col>
                <el-col :span="5">
                    <el-select v-model="filters.codevalue" clearable placeholder="请选择" style="width:100%"
                               size="small ">
                        <el-option label="全部" value=""></el-option>
                        <el-option label="居民客户科" value="1"></el-option>
                        <el-option label="工商客户科" value="2"></el-option>
                        <el-option label="出户改造科" value="3"></el-option>
                    </el-select>
                </el-col>
                <el-col :span="4" style="text-align: right">
                    <span>业务提醒 &nbsp;</span>
                </el-col>
                <el-col :span="5">
                    <el-select v-model="filters.id" clearable placeholder="请选择" style="width:100%" size="small ">
                        <el-option label="全部" value=""></el-option>
                        <el-option label="业务黄灯提醒" value="1"></el-option>
                        <el-option label="业务即将红灯" value="2"></el-option>
                        <el-option label="业务红灯提醒" value="3"></el-option>
                    </el-select>
                </el-col>
            </el-form-item>
            <el-form-item>
                <el-col :span="4" style="text-align: right">
                    <span> 业务名称 &nbsp;</span>
                </el-col>
                <el-col :span="5">
                    <el-input v-model="filters.activityname" size="small "></el-input>
                </el-col>
                <el-col :span="12" style="text-align: center">
                    <el-button type="primary" size="small " v-on:click="getHsjglr_vue">查询</el-button>
                    <el-button type="info" @click.native="setVisible = false" size="mini"><i
                            class="iconfont icon-quxiao"></i>取消
                    </el-button>
                    <el-button type="primary" @click.native="tijiao" size="mini"><i
                            class="iconfont icon-queren"></i>提交
                    </el-button>
                </el-col>

            </el-form-item>
        </el-form>
    </el-col>

    <el-table
            ref="dxlbtable"
            :data="row"
            tooltip-effect="dark"
            style="width: 100%"
            height="398px"
            @selection-change="handleSelectionChange">
        <el-table-column
                type="selection"
                width="55">
        </el-table-column>
        <el-table-column prop="codename" label="科室类型" width="150" align="center">
        </el-table-column>
        <el-table-column prop="dxmblx" label="业务提醒" width="150" align="center">
        </el-table-column>
        <el-table-column prop="activityname" label="业务名称" width="200" align="center">
        </el-table-column>
        &lt;!&ndash;  <el-table-column prop="dxid" label="是否订阅" width="150" align="center">
          </el-table-column>&ndash;&gt;
    </el-table>
    <el-col :span="24" class="toolbar">
        &lt;!&ndash;工具条&ndash;&gt;
        <el-pagination background layout="total"
                       :page-size="this.size" :total="this.total" style="float:right;">
        </el-pagination>
    </el-col>
    <div slot="footer" class="dialog-footer" style="text-align: center;margin-top: 25px;"></div>-->
    <!--</el-dialog>-->
</template>

<script>
    import {getAssignUser} from '../../api/role';
    import {getdxjslist, saveDyjl} from "../../api/api";

    export default {
        name: "Dxdygl",
        data() {
            return {
                userid: '',//选中部门的id
                fpryTree: [],
                defaultProps1: {
                    children: 'children',
                    label: 'text'
                },
                selectUserid: [],//选中的人员id
                //短信模块
                row: [],
                row_1: [],
                total: 10,//表格数据总数量
                total_1: 10,
                page: 1,//当前页码
                size: 300,//每页容量
                size_1: 300,//每页容量
                listLoading: false,
                multipleSelection: [],//选中的值
                multipleSelection_1: [],//当前列表没有选中
                multipleSelection_2: [],//当前角色以订阅
                multipleSelection_3: [],//当前取消订阅
                multipleSelection_4: [],//当前列表新订阅
                filters: {
                    codevalue: '',
                    id: '',
                    activityname: '',
                },
                filters_1: {
                    codevalue: '',
                    id: '',
                    activityname: '',
                },
                username: '',
                userphon: '',
                setVisible: false,
            }
        },
        methods: {
            handleNodeClick(fpryTree) {//点击节点 table显示部门的人员
                this.userid = fpryTree.id;
                this.getHsjglr_vue(2);
            },
            getUserTree() {
                //NProgress.start();
                getAssignUser().then(res => {
                    this.fpryTree = res.data;
                });
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;

            },
            onTableSelect(rows, row) {
                /*  let selected = rows.length && rows.indexOf(row) !== -1
                  console.log(selected);
                  if(!selected){
                      this.multipleSelection_1.push(rows);
                      console.log(this.multipleSelection_1);
                  }*/
            },
            tijiao() {
              /*  this.multipleSelection_1 = [];
                this.multipleSelection_3 = [];
                this.multipleSelection_4 = [];
                if (this.multipleSelection != "undefined") {
                    this.row_1.forEach((v, i) => { //当前列表所有数据
                        this.multipleSelection.forEach((_v, _i) => {//当前列表选中数据
                            if (v.codevalue === _v.codevalue && v.id === _v.id && v.activityid === _v.activityid) {
                                this.row_1.splice(i,1);
                            } else {
                              //  this.multipleSelection_1.push(this.multipleSelection[_i]);
                            //    console.log(this.multipleSelection_1);
                            }
                        })
                    });
                } else {
                    this.multipleSelection_1 = this.row_1;
                }
                console.log(this.row_1);
              /!*  if (this.multipleSelection_2 != "undefined") {
                    this.multipleSelection_1.forEach((v, i) => {//当前未选中列表数据
                        this.multipleSelection_2.forEach((_v, _i) => {//用户已订阅数据
                            if (v.codevalue === _v.ywlx && v.id === _v.dxlx && v.activityid === _v.activityid) {
                                this.multipleSelection_3.push(this.multipleSelection_1[i]);//
                            }
                        })
                    });
                } else {
                    this.multipleSelection_3 = [];
                }
                if (this.multipleSelection != "undefined") {
                    if (this.multipleSelection_2 != "undefined") {
                        this.multipleSelection.forEach((v, i) => {//当前列表选中数据
                            this.multipleSelection_2.forEach((_v, _i) => {//用户已订阅数据
                                if (v.codevalue === _v.ywlx && v.id === _v.dxlx && v.activityid === _v.activityid) {

                                } else {
                                    this.multipleSelection_4.push(this.multipleSelection[i]);//
                                }
                            });
                        });
                    } else {
                        this.multipleSelection_4 = this.multipleSelection;
                    }
                } else {
                    this.multipleSelection_4 = [];
                }*!/

                console.log(this.multipleSelection);
               // console.log(this.multipleSelection_1);
                console.log(this.multipleSelection_2);
                console.log(this.multipleSelection_3);
                console.log(this.multipleSelection_4);*/
                console.log(this.multipleSelection);
                console.log(this.multipleSelection_3);
                let gouxuan = JSON.stringify(this.multipleSelection);
                let weigouxuan = JSON.stringify(this.multipleSelection_3);///旧的
                let params = {
                    userid: this.userid,
                    json_gx: gouxuan,
                    json_qx: weigouxuan,
                }
                this.listLoading = true;
                saveDyjl(params).then((res) => {
                    this.sumbitLoading = false;
                    if (res.result == 'success') {
                        this.getHsjglr_vue(2);
                        this.$message({
                            message: '短信订阅成功',
                            type: 'success'
                        });
                        this.setVisible = false;
                    } else {
                        this.$message({
                            message: '短信订阅异常',
                            type: 'error'
                        });
                    }
                });

            },
            getHsjglr_vue(flag) {
                if (flag == 1) {
                    this.page = 1;
                }
                if (flag == 2) {
                    this.row = [];
                }
                let params = Object.assign({page: this.page, size: this.size, userid: this.userid}, this.filters);
                this.listLoading = true;
                getdxjslist(params).then((res) => {//
                    //   this.total = res.data.total;
                    this.username = res.data.username;
                    this.userphon = res.data.mobilephone;
                    //  this.row = res.data.list;
                    //   this.total = res.data.total;
                    // this.multipleSelection = res.data.dylist;
                    res.data.list.forEach((_v, _i) => {
                        res.data.dylist.forEach((v, i) => {
                            if (v.ywlx === _v.codevalue && v.dxlx === _v.id && v.activityid === _v.activityid) {
                                this.row.push(res.data.list[_i]);
                            }
                        })
                    });
                    if (res.data.dylist.length <= 0) {
                        this.row = [];
                        this.multipleSelection = "";
                    } else {
                        this.$nextTick(function () {
                            if (this.row.length > 0 && res.data.dylist.length > 0) {
                                this.row.forEach((_v, _i) => {
                                    res.data.dylist.forEach((v, i) => {
                                        if (v.ywlx === _v.codevalue && v.dxlx === _v.id && v.activityid === _v.activityid) {
                                            // this.row_1.push( res.data.list[_i]);
                                            this.$refs.dxlbtable.toggleRowSelection(_v)
                                        }
                                    })
                                });
                            }
                        });
                    }
                    // console.log(this.row);
                    this.total = this.row.length;
                    this.listLoading = false;
                });
            },
            openSettings() {
                //打开设置
                this.setVisible = true;
                this.getHsjglr_vue_1();
            },
            getHsjglr_vue_1(flag) {
                this.multipleSelection_3=[];
                if (flag == 1) {
                    this.page = 1;
                }
                let params = Object.assign({page: this.page, size: this.size, userid: this.userid}, this.filters_1);
                this.listLoading = true;
                getdxjslist(params).then((res) => {//
                    this.total_1 = res.data.total;
                    this.row_1 = res.data.list;
                  //  this.multipleSelection_3 = res.data.dylist;
                    this.$nextTick(function () {
                        if (this.row_1 != "undefined" && res.data.dylist != "undefined") {
                            res.data.dylist.forEach((v, i) => {
                                this.row_1.forEach((_v, _i) => {
                                    if (v.ywlx === _v.codevalue && v.dxlx === _v.id && v.activityid === _v.activityid) {
                                        this.$refs.dxlbtable.toggleRowSelection(_v);
                                        this.multipleSelection_3 .push(res.data.dylist[i]);
                                    }
                                })
                            })
                        }
                    });

                    this.listLoading = false;
                });
            },
        },
        mounted() {
            this.getUserTree();
            this.getHsjglr_vue();
        }
    }

</script>

<style>

</style>