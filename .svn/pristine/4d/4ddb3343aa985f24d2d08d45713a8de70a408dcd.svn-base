<template>
    <section>
        <el-row class="container">
            <el-col :span="24" class="header">
                <el-col :span="20" class="logo" :class="collapsed ? 'logo-collapse-width' : 'logo-width'">
                    <span class="logo_icon"><img style="margin-left: -3%;" src="../assets/logo2.png"/></span>
                    <span class="title_icon">{{ collapsed ? '' : sysName }}</span>
                </el-col>
                <!-- <el-col :span="10">
                   <div class="tools" @click.prevent="collapse" >
                     <i class="fa fa-align-justify"></i>
                   </div>
                 </el-col>-->
                <el-col :span="4" class="userinfo">
                    <el-dropdown trigger="hover">
            <span style="cursor: pointer;">
              <span class="user_icon"><img src="../assets/user1.png"/></span>
              <span class="el-dropdown-link userinfo-inner">{{sysUserName}} <i class="el-icon-caret-bottom"></i></span>
            </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item>
                                我的消息
                            </el-dropdown-item>
                            <el-dropdown-item @click.native="openSettings">
                                短信订阅
                            </el-dropdown-item>
                            <el-dropdown-item @click.native="openXgmm">
                                个人设置
                            </el-dropdown-item>
                            <el-dropdown-item @click.native="openZlxz">
                                资料下载
                            </el-dropdown-item>
                            <el-dropdown-item divided @click.native="logout">
                                退出登录
                            </el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </el-col>
            </el-col>
            <el-col :span="24" class="main">
                <aside :class="collapsed ? 'menu-collapsed' : 'menu-expanded'">
                    <div class="div_menu">
                        <el-row>
                            <el-col :span="24" class="col_div" :class="{ col_div_:changeRed == i}" :title="m.name"
                                    v-for="(m,i) in menu" :key="i" @click.native="clickMenu(i)">
                                <i class="iconfont i_menu" :class="m.iconCls"></i>
                            </el-col>
                        </el-row>
                    </div>
                    <!--导航菜单-->
                    <el-menu :default-active="$route.path" class="el-menu-vertical-demo" background-color="#FAFAFA"
                             @open="handleopen" @close="handleclose" @select="handleselect"
                             unique-opened router v-if="!collapsed" style="display: inline-block;width: 184px;">
                        <el-scrollbar style="height: 100%;">
                            <template v-for="(item, index) in $router.options.routes"
                                      v-if="!item.hidden">
                                <el-submenu :index="index+''" v-if="!item.leaf">
                                    <template slot="title">
                                        <i :class="item.iconCls" class="iconfont menuCol"></i>
                                        <span class="menuCol">{{ item.name }}</span>
                                    </template>
                                    <el-menu-item v-for="child in item.children"
                                                  :index="child.path"
                                                  v-if="!child.hidden"
                                                  :key="child.path"
                                                  @click="menuClick(child.path)">
                                        <span class="menuCol_">{{ child.name }}</span>
                                    </el-menu-item>
                                </el-submenu>
                                <el-menu-item v-if="item.leaf && item.children.length"
                                              :index="item.children[0].path">
                                    <i :class="item.iconCls" class="iconfont menuCol"
                                       v-if="item.iconCls != undefined"></i>
                                    <span class="menuCol_">{{ item.children[0].name }}</span>
                                </el-menu-item>
                            </template>
                        </el-scrollbar>
                    </el-menu>

                    <!--导航菜单-折叠后-->
                    <ul class="el-menu el-menu-vertical-demo collapsed"
                        v-if="collapsed"
                        ref="menuCollapsed">
                        <li v-for="(item, index) in $router.options.routes"
                            v-if="!item.hidden"
                            class="el-submenu item"
                            :key="index">
                            <template v-if="!item.leaf">
                                <div class="el-submenu__title"
                                     style="padding-left: 20px;"
                                     @mouseover="showMenu(index,true)"
                                     @mouseout="showMenu(index,false)">
                                    <i :class="item.iconCls"></i>
                                </div>
                                <ul class="el-menu submenu"
                                    :class="'submenu-hook-' + index"
                                    @mouseover="showMenu(index,true)"
                                    @mouseout="showMenu(index,false)">
                                    <li v-for="(child, index) in item.children"
                                        v-if="!child.hidden"
                                        class="el-menu-item"
                                        style="padding-left: 40px;"
                                        :class="$route.path==child.path?'is-active':''"
                                        @click="menuClick(child.path)"
                                        :key="index">
                                        {{ child.name }}
                                    </li>
                                </ul>
                            </template>
                            <template v-else>
                        <li class="el-submenu">
                            <div class="el-submenu__title el-menu-item"
                                 style="padding-left: 20px;height: 56px;line-height: 56px;padding: 0 20px;"
                                 :class="$route.path == item.children[0].path ? 'is-active' : ''"
                                 @click="$router.push(item.children[0].path)">
                                <i :class="item.iconCls"></i>
                            </div>
                        </li>
</template>
</li>
</ul>
</aside>
<section class="content-container">
    <router-tab :tabs="tabs"/>
    <!-- <router-view /> -->
    <!--<div class="grid-content bg-purple-light">-->
    <!--<div class="template-tabs">-->
    <!--<el-tabs-->
    <!--v-model="activeIndex"-->
    <!--type="border-card"-->
    <!--closable-->
    <!--@tab-click="tabClick"-->
    <!--v-if="options.length"-->
    <!--@tab-remove="tabRemove"-->
    <!--&gt;-->
    <!--<el-tab-pane-->
    <!--:key="item.name"-->
    <!--v-for="(item, index) in options"-->
    <!--:label="item.name"-->
    <!--:name="item.route"-->
    <!--&gt;-->
    <!--<el-col-->
    <!--:span="24"-->
    <!--class="content-wrapper"-->
    <!--&gt;-->
    <!--<transition-->
    <!--name="fade"-->
    <!--mode="out-in"-->
    <!--&gt;-->
    <!--<keep-alive>-->
    <!--<router-view v-if="$route.meta.keepAlive"></router-view>-->
    <!--</keep-alive>-->
    <!---->
    <!--</transition>-->
    <!--<transition-->
    <!--name="fade"-->
    <!--mode="out-in"-->
    <!--&gt;-->
    <!--<router-view v-if="!$route.meta.keepAlive"></router-view>-->
    <!--</transition>-->
    <!--</el-col>-->
    <!--</el-tab-pane>-->

    <!--</el-tabs>-->
    <!--</div>-->
    <!--&lt;!&ndash; <el-col :span="24" class="content-wrapper">-->
    <!--<transition name="fade" mode="out-in">-->
    <!--<router-view></router-view>-->
    <!--</transition>-->
    <!--</el-col> &ndash;&gt;-->
    <!--</div>-->
</section>
<!-- <router-tab/> -->
</el-col>
</el-row>

<el-dialog title="设置短信接收" :visible.sync="setVisible">
    <el-row>
          <el-col :span="24">
              <table class="border-table">
                  <tr>
                      <td></td>
                      <td>黄灯短信</td>
                      <td>接近红灯短信</td>
                      <td>红灯短信</td>
                  </tr>
                  <tr>
                      <td>是否接收</td>
                      <td>
                          <el-checkbox v-model="check_value.yellow"></el-checkbox>
                      </td>
                      <td>
                          <el-checkbox v-model="check_value.nearred"></el-checkbox>
                      </td>
                      <td>
                          <el-checkbox v-model="check_value.red"></el-checkbox>
                      </td>
                  </tr>
              </table>
          </el-col>
      </el-row>
    <div slot="footer" class="dialog-footer">
        <el-button type="info" size="mini" @click.native="setVisible = false"><i class="iconfont icon-quxiao"></i>取消
        </el-button>
        <el-button type="primary" size="mini" @click.native="settings"><i class="iconfont icon-queren"></i>提交
        </el-button>
    </div>
    <!--列表-->
   <!-- <el-col :span="24" class="toolbar" style="border: #f5f7fa solid 1px">
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
                    <el-input v-model="userphon" size="small "  :disabled="true"></el-input>
                </el-col>
            </el-form-item>
            <el-form-item>
                <el-col :span="4" style="text-align: right">
                    <span>科室类型 &nbsp;</span>
                </el-col>
                <el-col :span="5">
                    <el-select v-model="filters.codevalue" clearable placeholder="请选择" style="width:100%" size="small ">
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
                    <el-button type="primary" @click.native="tijiao" size="mini"><i class="iconfont icon-queren"></i>提交
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
</el-dialog>

<!--修改密码-->
<el-dialog title="个人设置" :visible.sync="setXgmm" width="500px">
    <el-row>
        <el-col :span="30">
            <el-form :model="UserAll" label-width="120px" ref="UserAll" :inline="true" class="demo-ruleForm">
                <el-form-item label="用户名称">
                    <el-input v-model="UserAll.username" style="width: 300px"></el-input>
                </el-form-item>
                <el-form-item label="登录账号">
                    <el-input v-model="UserAll.account" style="width: 300px"></el-input>
                </el-form-item>
                <el-form-item label="手机号码">
                    <el-input v-model="UserAll.mobilephone" style="width: 300px"></el-input>
                </el-form-item>
                <el-form-item label="对外电话">
                    <el-input v-model="UserAll.gddh" style="width: 300px"></el-input>
                </el-form-item>
                <el-form-item label="电子邮箱">
                    <el-input v-model="UserAll.email" style="width: 300px"></el-input>
                </el-form-item>
                <el-form-item label="原密码" @keyup.enter.native="handleSubmit2" @keyup.native="handleSubmit2">
                    <el-input v-model="UserAll.ysmm" style="width: 300px;" show-password autocomplete="off"></el-input>
                    <i class="el-icon-check" style="color: green" v-if="this.zq"></i>
                    <i class="el-icon-close" style="color: red" v-else></i>
                </el-form-item>
                <el-form-item label="新密码" prop="xmm" @keyup.native="renewpwd" :rules="this.publicUtil.mimayz">
                    <el-input v-model="UserAll.xmm" style="width: 300px" show-password autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="qrmm" @keyup.native="renewpwd" :rules="this.publicUtil.mimayz">
                    <el-input v-model="UserAll.qrmm" style="width: 300px" show-password autocomplete="off"></el-input>
                    <i class="el-icon-check" style="color: green" v-if="this.lcmm"></i>
                    <i class="el-icon-close" style="color: red" v-else></i>
                </el-form-item>
            </el-form>
            <p style="text-align: center"><b>提示：</b>修改基本信息时需要输入原密码进行确认</p>
        </el-col>
    </el-row>
    <div slot="footer" class="dialog-footer">
        <el-button type="info" size="mini" @click.native="setXgmm = false"><i class="iconfont icon-quxiao"></i>取消
        </el-button>
        <el-button type="primary" size="mini" @click.native="updateUser"><i class="iconfont icon-queren"></i>提交
        </el-button>
    </div>
</el-dialog>

</section>

</template>
<script>
    import {getDxSettings, dxSettings, getUser, updateUser, getPassWordDb, getdxjslist, saveDyjl} from "../api/api";
    import publicUtils from '../common/js/publicUtil';
    import qs from 'qs'

    export default {
        data() {
            return {
                sysName: "合肥供水集团新装业务报建协同系统",
                collapsed: false,
                sysUserName: "",
                sysUserAvatar: "",
                changeRed: 0,
                menu: {},
                form: {
                    name: "",
                    region: "",
                    date1: "",
                    date2: "",
                    delivery: false,
                    type: [],
                    resource: "",
                    desc: ""
                },
                setXgmm: false,//修改密码对话框
                userId: '',//用户id
                UserAll: {//用户信息
                    username: '',
                    account: '',
                    mobilephone: '',
                    gddh: '',
                    email: '',
                    password: '',
                    xmm: '',//新密码
                    qrmm: '',//确认密码
                    ysmm: '',//原始密码

                },
                zq: false,//原始密码输入是否正确
                lcmm: false,//两次输入的密码是否一致
                setVisible: false,
                check_value: {
                    yellow: true,
                    nearred: true,
                    red: true
                },
                tabs: [
                    //{ to: '/Backlog', title: '', icon: 'icon-page', closable: false }
                    //{ to: '/Originate', title: '我发起的', icon: 'icon-page', closable: true }
                ],
                // activeIndex: "/Backlog"
                //短信模块
                row: [],
                total: 10,//表格数据总数量
                page: 1,//当前页码
                size: 300,//每页容量
                listLoading: false,
                multipleSelection: [],//选中的值
                filters: {
                    codevalue: '',
                    id: '',
                    activityname: '',
                },
                username:'',
                userphon:'',

            };
        },
        methods: {
            //更新用户基本信息
            updateUser() {
                //修改基本信息时需要原始密码
                if (this.zq) {//密码正确
                    if ((typeof this.UserAll.xmm == "undefined" && typeof this.UserAll.qrmm == "undefined") || (this.UserAll.xmm == "" && this.UserAll.qrmm == "")) {//不修改密码
                        let params = {
                            userid: this.userId,
                            username: this.UserAll.username,
                            account: this.UserAll.account,
                            mobilephone: this.UserAll.mobilephone,
                            gddh: this.UserAll.gddh,
                            email: this.UserAll.email,
                            password: ''
                        }
                        updateUser(params).then(res => {
                            if (res.flag == "ok") {
                                this.$message({
                                    message: '修改成功',
                                    type: 'success'
                                });
                                this.UserAll.ysmm = '';
                                this.zq = false;
                                this.setXgmm = false;
                            } else {
                                this.$message.error('修改失败');
                                this.setXgmm = false;
                            }

                        });

                    }

                    if (this.UserAll.xmm != "" || this.UserAll.qrmm != "") {//修改密码
                        if (this.UserAll.xmm != "" && this.UserAll.qrmm != "" && this.lcmm == true) {//判断新密码和确认密码都不为空且两次密码相同的时候执行
                            var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)(?![_]+$)[0-9A-Za-z_]{8,16}$/;
                            if (!reg.test(this.UserAll.qrmm) && this.UserAll.qrmm != "") {
                                return false
                            }

                            let params = {
                                userid: this.userId,
                                username: this.UserAll.username,
                                account: this.UserAll.account,
                                mobilephone: this.UserAll.mobilephone,
                                gddh: this.UserAll.gddh,
                                email: this.UserAll.email,
                                password: this.UserAll.qrmm
                            }
                            updateUser(params).then(res => {
                                if (res.flag == "ok") {
                                    this.$message({
                                        message: '修改成功',
                                        type: 'success'
                                    });
                                    this.UserAll.ysmm = '';
                                    this.UserAll.xmm = '';
                                    this.UserAll.qrmm = '';
                                    this.zq = false;
                                    this.lcmm = false;
                                    this.setXgmm = false;
                                } else {
                                    this.$message.error('修改失败');
                                    this.setXgmm = false;
                                }

                            });

                        } else if (this.lcmm == false) {//两次密码不一致
                            this.$message({
                                message: '两次输入的密码不一致',
                                type: 'warning'
                            });
                        }

                    }


                } else {//密码错误
                    if (this.UserAll.ysmm == "" || typeof this.UserAll.ysmm == "undefined") {
                        this.$message({
                            message: '请输入原密码',
                            type: 'warning'
                        });
                    } else if (this.UserAll.ysmm != "") {
                        this.$message({
                            message: '原密码不正确',
                            type: 'warning'
                        });
                    }

                }

            },
            //判断第二次输入的密码是否和第一次一样
            renewpwd() {
                if (this.UserAll.xmm === this.UserAll.qrmm) {
                    this.lcmm = true;
                } else {
                    this.lcmm = false;
                }
            },
            //判断输入的原始密码是否正确
            handleSubmit2() {
                let params = {
                    password: this.UserAll.ysmm,
                    userid: this.userId
                }
                getPassWordDb(params).then(res => {
                    if (res.flag == "true") {
                        this.zq = true;
                    } else {
                        this.zq = false;
                    }
                });


                /* if (this.ysmm === this.UserAll.password){
                   this.zq = true;
                 }else {
                   this.zq = false;
                 }*/
            },
            clickMenu(index) {
                this.changeRed = index;
                let menuList = JSON.parse(sessionStorage.getItem('menuList')).arr;
                let obj = JSON.parse(sessionStorage.getItem('menuArr'));
                let routes = menuList[index];
                this.$router.options.routes = [];
                for (let o = 0; o < routes.length; o++) {
                    this.$router.options.routes.push(routes[o]);
                }
            },
            onSubmit() {
                console.log("submit!");
            },
            handleopen() {
                //console.log('handleopen');
            },
            handleclose() {
                //console.log('handleclose');
            },
            handleselect: function (a, b) {
            },
            // 退出登录
            logout() {
                var _this = this;
                this.$confirm("确认退出吗?", "提示", {})
                    .then(() => {
                        sessionStorage.removeItem("user");
                        _this.$router.push("/login");
                    })
                    .catch(() => {
                    });
            },
            //弹出修改密码对话框
            openXgmm() {
                this.UserAll.ysmm = '';
                this.UserAll.xmm = '';
                this.UserAll.qrmm = '';
                this.zq = false;
                this.lcmm = false;
                let params = {
                    userid: this.userId
                }
                getUser(params).then(res => {
                    this.UserAll = res.user;

                });
                this.setXgmm = true;
            },
            openZlxz() {
                this.$router.push({path: '/Zlxz', query: {}})
            },
            openSettings() {
                //打开设置
                this.setVisible = true;
                this.getuser();
                /*this.getHsjglr_vue();*/
                this.check_value.yellow = true;
                this.check_value.nearred = true;
                this.check_value.red = true;
                let JSONstr = sessionStorage.getItem("user");
                let JSON = eval("(" + JSONstr + ")");
                getDxSettings({userid: JSON.id}).then(res => {
                    let list = res.data.list;
                    for (let i = 0; i < list.length; i++) {
                        if (list[i].DXMBID == "1") {
                            this.check_value.yellow = false;
                        } else if (list[i].DXMBID == "2") {
                            this.check_value.nearred = false;
                        } else if (list[i].DXMBID == "3") {
                            this.check_value.red = false;
                        }
                    }
                    this.setVisible = true;
                });
            },
            settings() {
                //设置短信接收
                let JSONstr = sessionStorage.getItem("user");
                let JSON = eval("(" + JSONstr + ")");
                let params = Object.assign(
                    {userid: JSON.id, account: JSON.username},
                    this.check_value
                );
                dxSettings(params).then(res => {
                    if (res.result == "success") {
                        this.$message({
                            message: "设置成功",
                            type: "success"
                        });
                    } else {
                        this.$message({
                            message: "设置失败",
                            type: "error"
                        });
                    }
                    this.setVisible = false;
                });
            },
            // 折叠导航栏
            collapse() {
                this.collapsed = !this.collapsed;
            },
            showMenu(i, status) {
                this.$refs.menuCollapsed.getElementsByClassName(
                    "submenu-hook-" + i
                )[0].style.display = status ? "block" : "none";
            },
            // // tab切换时，动态的切换路由
            // tabClick(tab) {
            //   let path = this.activeIndex;
            //   // this.activeIndex = tab.name;
            //   // console.log(path);
            //   this.$store.commit("set_active_index",path);
            //   this.$router.push({ path: path });
            // },
            // tabRemove(targetName) {
            //   console.log(targetName);
            //   // 首页不可删除
            //   if (targetName == "/Backlog") {
            //     return;
            //   }
            //   this.$store.commit("delete_tabs", targetName);
            //   if (this.activeIndex === targetName) {
            //     // 设置当前激活的路由
            //     if (this.options && this.options.length >= 1) {
            //       this.$store.commit(
            //               "set_active_index",
            //               this.options[this.options.length - 1].route
            //       );
            //       this.$router.push({ path: this.activeIndex });
            //     } else {
            //       this.$router.push({ path: "/Backlog" });
            //     }
            //   }
            // },
            menuClick(path) {
                this.$router.push({path: path});
                // this.$store.commit("set_active_index",path);
                // if(path in this.store){
                //   tabRemove();
                //
                // }
                // this.activeIndex = path;
            },
            //获取短信项目列表

            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            tijiao() {
                let json = JSON.stringify(this.multipleSelection);
                let params = {
                    json: json
                }
                this.listLoading = true;
                saveDyjl(params).then((res) => {
                    this.sumbitLoading = false;
                    console.log(res.result);
                    if (res.result == 'success') {
                        this.getHsjglr_vue();
                        this.$message({
                            message: '短信订阅成功',
                            type: 'success'
                        });
                    } else {
                        this.$message({
                            message: '短信订阅异常',
                            type: 'error'
                        });
                    }
                });

            },

            getuser(){
                let params = {
                    userid: this.userId
                }
                getUser(params).then(res => {
                    this.username = res.user.username;
                    this.userphon = res.user.mobilephone;
                });
              },
            getHsjglr_vue(flag) {
                if (flag == 1) {
                    this.page = 1;
                }
                let params = Object.assign({page: this.page, size: this.size}, this.filters);
                this.listLoading = true;
                getdxjslist(params).then((res) => {//
                    this.total = res.data.total;
                    this.row = res.data.list;
                    this.multipleSelection = res.data.dylist;
                    this.$nextTick(function(){
                        if (this.row != "undefined" && res.data.dylist != "undefined") {
                            res.data.dylist.forEach((v, i) => {
                                this.row.forEach((_v, _i) => {
                                    if (v.ywlx === _v.codevalue && v.dxlx === _v.id && v.activityid === _v.activityid) {
                                        this.$refs.dxlbtable.toggleRowSelection(_v);
                                    }
                                })
                            })
                        }
                    });

                    this.listLoading = false;
                });
            },
            handleCurrentChange(val) {
                this.page = val;
                this.getHsjglr_vue();
            },

            handleSizeChange(val) {
                this.size = val;
                this.page = 1;
                this.getHsjglr_vue();

            },
        },
        mounted() {

            var user = sessionStorage.getItem("user");
            if (user) {
                user = JSON.parse(user);
                this.sysUserName = user.name || "";
                this.sysUserAvatar = "../assets/user.png";
                this.userId = user.id;
            }
            ;
            let menu = JSON.parse(sessionStorage.getItem('menuName'));
            this.menu = menu.arr;

            //this.$routerTab.close('/Originate');
        }
        // computed: {
        //   options() {
        //     if (this.$store.state.options.length == 0) {
        //       this.$router.push({ path: "/Backlog" });
        //       this.$store.commit("set_active_index",'/Backlog');
        //       this.$store.state.options.push({ name: "我的待办", route: "/Backlog" });
        //     }
        //     return this.$store.state.options;
        //   },
        //   activeIndex: {
        //     get() {
        //       if(this.$store.state.activeIndex == null || this.$store.state.activeIndex == 'undefind'){
        //         return "/Backlog";
        //       }
        //       return this.$store.state.activeIndex;
        //     },
        //     set(val) {
        //       this.$store.commit("set_active_index", val);
        //     }
        //   }
        // },
        // watch: {
        //   $route(to) {
        //     let flag = false;
        //     for (let option of this.options) {
        //       if (option.name === to.name) {
        //         flag = true;
        //         this.$store.commit("set_active_index", "/" + to.path.split("/")[1]);
        //         break;
        //       }
        //     }
        //     if (!flag) {
        //       this.$store.commit("add_tabs", {
        //         route: "/" + to.path.split("/")[1],
        //         name: to.name
        //       });
        //       this.$store.commit("set_active_index", "/" + to.path.split("/")[1]);
        //     }
        //   }
        // }
    };
</script>
<style scoped>

    .div_menu {
        height: 100%;
        width: 45px;
        background-color: #409eff;
        display: inline-block;
        float: left;
        margin-top: 0px;
        border-top: 1px solid #FAFAFA;
    }

    .col_div {
        margin: 0px auto;
        text-align: center;
        line-height: 44px;
    }

    .col_div_ {
        background-color: #2c8cf1;
    }

    .i_menu {
        font-size: 20px;
        cursor: pointer;
        color: #C1FAFA;
    }

    .user_icon {
        top: 8px;
        left: -53px;
        position: absolute;
    }

    .logo_icon {
        margin-left: -3%;
    }

    .title_icon {
        margin-left: 4%;
        font-size: 26px;
    }

    /*.menuCol {
      color : #333333;
    }
    .menuCol_ {
      color : #666666;
    }*/
</style>
<style scoped lang="scss">
    @import "~scss_vars";

    .container {
        position: absolute;
        top: 0px;
        bottom: 0px;
        width: 100%;

        .header {
            height: 60px;
            line-height: 60px;
            background: $color-primary;
            color: #fff;

            .userinfo {
                text-align: right;
                padding-right: 35px;
                float: right;

                .userinfo-inner {
                    cursor: pointer;
                    color: #fff;

                    img {
                        width: 40px;
                        height: 40px;
                        border-radius: 20px;
                        margin: 10px 0px 10px 10px;
                        float: right;
                    }
                }
            }

            .logo {
                //width:230px;
                height: 60px;
                font-size: 22px;
                padding-left: 20px;
                padding-right: 20px;
                border-color: rgba(238, 241, 146, 0.3);
                border-right-width: 1px;
                /*border-right-style: solid;*/
                img {
                    width: 40px;
                    float: left;
                    margin: 10px 10px 10px 18px;
                }

                .txt {
                    color: #fff;
                }
            }

            .logo-width {
                width: 500px;
            }

            .logo-collapse-width {
                width: 60px;
            }

            .tools {
                padding: 0px 23px;
                width: 14px;
                height: 60px;
                line-height: 60px;
                cursor: pointer;
            }
        }

        .main {
            display: flex;
            // background: #324057;
            position: absolute;
            top: 60px;
            bottom: 0px;
            overflow: hidden;

            aside {
                flex: 0 0 230px;
                width: 230px;
                // position: absolute;
                // top: 0px;
                // bottom: 0px;
                .el-menu {
                    height: 100%;
                }

                .collapsed {
                    width: 60px;

                    .item {
                        position: relative;
                    }

                    .submenu {
                        position: absolute;
                        top: 0px;
                        left: 60px;
                        z-index: 99999;
                        height: auto;
                        display: none;
                    }
                }
            }

            .menu-collapsed {
                flex: 0 0 60px;
                width: 60px;
            }

            .menu-expanded {
                flex: 0 0 230px;
                width: 230px;
            }

            .content-container {
                // background: #f1f2f7;
                flex: 1;
                // position: absolute;
                // right: 0px;
                // top: 0px;
                // bottom: 0px;
                // left: 230px;
                overflow-y: scroll;
                padding: 20px;

                .breadcrumb-container {
                    //margin-bottom: 15px;
                    .title {
                        width: 200px;
                        float: left;
                        color: #475669;
                    }

                    .breadcrumb-inner {
                        float: right;
                    }
                }

                .content-wrapper {
                    background-color: #fff;
                    box-sizing: border-box;
                }
            }
        }
    }

</style>
