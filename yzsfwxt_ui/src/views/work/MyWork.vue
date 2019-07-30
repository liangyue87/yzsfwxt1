<template>
    <el-container>
        <el-main style="background-color: rgb(242,242,242)">
            <el-row :gutter="15">
                <!--左侧-->
                <el-col :span="18" :style="style_up">
                    <el-row :style="style_down" style="margin-top: 0px;">
                        <el-col :span="24" :style="style_down" style="background-color: white">
                            <div :style="style_down" class="backcol">
                                <div class="tit">
                                    <div class="tit_lf"><i class="iconfont icon-icon-daibanrenwu_"></i>任务待办</div>
                                    <div class="tit_rt" @click="toFqlhkc">更多</div>
                                </div>
                                <div>
                                    <el-col :span="24" class="toolbar">
                                        <!--列表-->
                                        <el-table :data="row" highlight-current-row v-loading="listLoading"
                                                  style="width: 96%;margin: auto;font-size: 13px"
                                                  :header-cell-style="{background:'#f5f7fa' }">
                                            <el-table-column type="index" label="状态" width="80" align="left">
                                                <!--<template slot-scope="scope">
                                                    <i v-if="scope.row.QSZT == '已签收' && scope.row.SFHUANGD == '0' && scope.row.SFHONGD== '0'" class="el-icon-s-opportunity lignt_green"></i>
                                                    <i v-else-if="scope.row.QSZT == '已签收' && scope.row.SFHUANGD == '1' && scope.row.SFHONGD== '0'" class="el-icon-s-opportunity lignt_yellow"></i>
                                                    <i v-else-if="scope.row.QSZT == '已签收' && scope.row.SFHONGD== '1'" class="el-icon-s-opportunity lignt_red"></i>
                                                    <i v-else-if="scope.row.QSZT == '未签收' && scope.row.QSSFCS== '1'" class="el-icon-s-opportunity lignt_red"></i>
                                                    <i v-else-if="scope.row.QSZT == '未签收' && scope.row.QSSFCS== '0'" class="el-icon-s-opportunity lignt_green"></i>
                                                </template>-->
                                                <template slot-scope="scope">
                                                    <i v-if="scope.row.QSZT == '已签收' && scope.row.SFHUANGD == '0' && scope.row.SFHONGD== '0'"
                                                       class="el-icon-s-opportunity lignt_green"></i>
                                                    <i v-else-if="scope.row.QSZT == '已签收' && scope.row.SFHUANGD == '1' && scope.row.SFHONGD== '0'"
                                                       class="el-icon-s-opportunity lignt_yellow"></i>
                                                    <i v-else-if="scope.row.QSZT == '已签收' && scope.row.SFHONGD== '1'"
                                                       class="el-icon-s-opportunity lignt_red"></i>
                                                    <span v-else-if="scope.row.QSZT == '未签收' && scope.row.QSSFCS== '1'">
														<i class="el-icon-s-opportunity lignt_red"></i>
														<i class="iconfont icon-new1 lignt_red"
                                                           style="font-size: 21px;position: relative;top: 5px;left: -7px;"></i>
													</span>
                                                    <span v-else-if="scope.row.QSZT == '未签收' && scope.row.QSSFCS== '0'">
														<i class="el-icon-s-opportunity lignt_green"></i>
														<i class="iconfont icon-new1 lignt_green"
                                                           style="font-size: 21px;position: relative;top: 5px;left: -7px;"></i>
													</span>
                                                </template>
                                            </el-table-column>
                                            <el-table-column property="XMBH" label="项目编号" align="center">
                                            </el-table-column>
                                            <el-table-column property="XMMC" label="项目名称" align="center">
                                            </el-table-column>
                                            <el-table-column property="ACTIVITYNAME" label="环节名称" align="center">
                                            </el-table-column>
                                            <el-table-column label="操作" width="80px" align="center" fixed="right">
                                                <template slot-scope="scope">
                                                    <label title="查看" @click="toDo(scope.$index, scope.row)">
                                                        <i class="iconfont icon-yulan_f"
                                                           style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>

                                                </template>
                                            </el-table-column>
                                        </el-table>
                                    </el-col>
                                </div>
                            </div>
                        </el-col>
                    </el-row>
                    <el-row :style="style_down" style="margin-top: 10px;">
                        <el-col :span="24" :style="style_down" style="background-color: white">
                            <div :style="style_down" class="backcol">
                                <div class="tit">
                                    <div class="tit_lf"><i class="iconfont icon-wofaqiderenwu"></i>我发起的任务</div>
                                    <div class="tit_rt" @click="toOriginate">更多</div>
                                </div>
                                <div>
                                    <el-col :span="24" class="toolbar">
                                        <!--列表-->
                                        <el-table :data="wfqdRw" highlight-current-row v-loading="listLoading"
                                                  style="width: 96%;margin: auto;font-size: 13px"
                                                  :header-cell-style="{background:'#f5f7fa' }">
                                            <el-table-column type="index" label="序号" width="50" align="center">
                                            </el-table-column>
                                            <el-table-column property="XMBH" label="项目编号" width="200" align="center">
                                            </el-table-column>
                                            <el-table-column property="XMMC" label="项目名称" align="center">
                                            </el-table-column>
                                            <el-table-column property="AZLX" label="安装类型" align="center">
                                            </el-table-column>
                                            <el-table-column property="ACTIVITYNAME" label="环节名称" align="center">
                                            </el-table-column>
                                            <el-table-column label="操作" width="80px" align="center" fixed="right">
                                                <template slot-scope="scope">
                                                    <label title="查看" @click="toShow(scope.row)">
                                                        <i class="iconfont icon-yulan_f"
                                                           style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                                                </template>
                                            </el-table-column>
                                        </el-table>
                                    </el-col>
                                </div>
                            </div>
                        </el-col>
                    </el-row>
                </el-col>
                <!--右侧-->
                <el-col :span="6">
                    <div :style="style_right" class="backcol">
                        <el-row style="">
                            <div class="tit">
                                <div class="tit_lf"><i class="iconfont icon-icon-renwu_"></i>我办理的任务</div>
                                <div class="tit_rt" @click="toDone()">更多</div>
                            </div>
                            <div class="block" style="padding-top: 20px">
                                <el-timeline style="width: 60%" id="timeline" :reverse="reverse">
                                    <el-timeline-item
                                            v-for="(activity, index) in activities"
                                            :key="index"
                                            :timestamp="activity.FSSJ" placement="top">
                                        <div style="position: absolute;left: 4px;height: 100%;border-left: 2px solid #E4E7ED;margin-top: -10px">
                                        </div>
                                        <!--<div  style="left: -4px;width: 12px;height: 12px;position: absolute;display: flex;-webkit-box-pack: center;margin-top: -22px" class="el-icon-s-opportunity lignt_green">
                                        </div>-->
                                        <div v-if="activity.QSZT == '未签收' && activity.QSSFCS== '0'"
                                             style="left: -4px;width: 12px;height: 12px;position: absolute;display: flex;-webkit-box-pack: center;margin-top: -22px"
                                             class="el-icon-s-opportunity lignt_green">
                                        </div>
                                        <div v-else-if="activity.QSZT == '未签收' && activity.QSSFCS== '1'"
                                             style="left: -4px;width: 12px;height: 12px;position: absolute;display: flex;-webkit-box-pack: center;margin-top: -22px"
                                             class="el-icon-s-opportunity lignt_red">
                                        </div>
                                        <div v-else-if="activity.QSZT == '已签收' && activity.SFHONGD== '1'"
                                             style="left: -4px;width: 12px;height: 12px;position: absolute;display: flex;-webkit-box-pack: center;margin-top: -22px"
                                             class="el-icon-s-opportunity lignt_red">
                                        </div>
                                        <div v-else-if="activity.QSZT == '已签收' && activity.SFHUANGD == '1' && activity.SFHONGD== '0'"
                                             style="left: -4px;width: 12px;height: 12px;position: absolute;display: flex;-webkit-box-pack: center;margin-top: -22px"
                                             class="el-icon-s-opportunity lignt_yellow">
                                        </div>
                                        <div v-else-if="activity.QSZT == '已签收' && activity.SFHUANGD == '0' && activity.SFHONGD== '0'"
                                             style="left: -4px;width: 12px;height: 12px;position: absolute;display: flex;-webkit-box-pack: center;margin-top: -22px"
                                             class="el-icon-s-opportunity lignt_green">
                                        </div>

                                        <table style="background-color: #F4F4F4;width: 130%;border-radius: 4px;font-size: 12px">
                                            <tr>
                                                <td><a href="javascript:void(0)" @click="toShow(activity)"
                                                       style="color: #20a0ff;cursor: pointer;">【{{activity.ACTIVITYNAME}}】</a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>项目名称：{{activity.XMMC}}</td>
                                            </tr>
                                            <tr>
                                                <td>任务编号：{{activity.RWBH}}</td>
                                            </tr>
                                        </table>
                                    </el-timeline-item>
                                </el-timeline>
                            </div>
                        </el-row>
                    </div>
                </el-col>
            </el-row>

        </el-main>
    </el-container>
</template>

<script>
    import util from '../../common/js/util'

    import {findBacklog, getTypeCheckbox, findOriginate, findDone} from '../../api/work';
    import {getYswtList} from '../../api/Yswt';
    import {getXqslById, toXqqs} from '../../api/xqsl';
    import {getZhysdById, selectRwzbid} from '../../api/zhys';
    import {getSgrwdById, queryxmxxByid} from '../../api/sgjg';
    import {goSjrwfp} from '../../api/sjst';
    import {BwyjHandleEdit} from '../../api/bwyj';

    export default {
        data() {
            return {
                rwblr: '',
                ldsh: [],
                yszp: [],
                ysbz: [],
                ysqs: [],
                filters: {
                    rwbh: '',
                    dwmc: '',
                    lxr: '',
                    kssj: '',
                    jssj: '',
                    type: ''
                },
                row: [],
                wfqdRw: [],
                total: 5,//表格数据总数量
                page: 1,//当前页码
                size: 5,//每页容量
                listLoading: false,
                typeList: [],
                reverse: true,
                style_up: {
                    height: '0px',
                },
                style_down: {
                    height: '0px',
                },
                style_right: {
                    height: '0px',
                },
                activities: [/*{
					content: '活动按期开始',
					timestamp: '2018-04-15'
				}, {
					content: '通过审核',
					timestamp: '2018-04-13'
				}, {
					content: '创建成功',
					timestamp: '2018-04-11'
				}*/],
            }
        },
        methods: {

            toShow(row) {
                let c_activityid = row.C_ACTIVITYID.substr(0, 1);
                let xmid = row.ID;
                this.$router.push({path: '/Jsplumb', query: {xmid: xmid, type: c_activityid}})
            },

            //获取我办理的任务
            findDone() {
                let params = Object.assign({page: this.page, size: this.size, rwblr: this.rwblr}, this.filters);
                this.listLoading = true;
                findDone(params).then((res) => {
                    this.total = res.data.total;
                    this.activities = res.data.list;
                    this.listLoading = false;
                });

            },
            //获取我的发起任务
            findOriginate() {
                let params = Object.assign({page: this.page, size: this.size, rwblr: this.rwblr});
                this.listLoading = true;
                findOriginate(params).then((res) => {
                    this.total = res.data.total;
                    this.wfqdRw = res.data.list;
                    this.listLoading = false;
                });
            },
            //跳转至我的代办
            toFqlhkc() {
                this.$router.push({
                    path: '/Backlog',
                })
            },
            //跳转至我发起的任务
            toOriginate() {
                this.$router.push({
                    path: '/Originate',
                })
            },
            //跳转至我发起的任务
            toDone() {
                this.$router.push({
                    path: '/Done',
                })
            },
            //获取任务代办列表
            findBacklog() {
                let params = Object.assign({page: this.page, size: this.size, rwblr: this.rwblr}, this.filters);
                this.listLoading = true;
                findBacklog(params).then((res) => {
                    this.total = res.data.total;
                    this.row = res.data.list;
                    this.listLoading = false;
                });
            },
            getHeight() {
                let height = window.innerHeight - 160;
                this.style_right.height = height + 10 + 'px';
                this.style_up.height = height * 0.25 + 'px';
                this.style_down.height = height * 0.5 + 'px';
            },
            //跳转至办理界面
            toDo: function (index, row) {
                let flag = row.C_ACTIVITYID;
                if (flag == '20021') {
                    this.$router.push({path: '/Rwzpsjy', query: {xmid: row.ID, type: '1', activityId: flag}})
                } else if (flag == '20022') {
                    this.$router.push({path: '/Rwzpsgdw', query: {xmid: row.ID, type: '2', activityId: flag}})
                } else if (flag == '20023') {
                    this.$router.push({path: '/Rwzpqs', query: {xmid: row.ID, type: '3', activityId: flag}})
                } else if (flag == '20024') {
                    this.$router.push({path: '/Rwzpys', query: {xmid: row.ID, type: '4', activityId: flag}})
                } else if (flag == '20031') {
                    this.$router.push({path: '/Rwfksjy', query: {xmid: row.ID, type: '1', activityId: flag}})
                } else if (flag == '20032') {
                    this.$router.push({path: '/Rwfksgdw', query: {xmid: row.ID, type: '2', activityId: flag}})
                } else if (flag == '20033') {
                    this.$router.push({path: '/Rwfkqs', query: {xmid: row.ID, type: '3', activityId: flag}})
                } else if (flag == '20034') {
                    this.$router.push({path: '/Rwfkys', query: {xmid: row.ID, type: '4', activityId: flag}})
                } else if (flag == '20006') {
                    this.$router.push({path: '/Fkpj', query: {xmid: row.ID, activityId: flag, rwbh: row.RWBH}})
                } else if (flag == '20007') {
                    this.$router.push({path: '/Fkjgqr', query: {xmid: row.ID, activityId: flag}})
                } else if (flag == '30005') {
                    this.$router.push({path: '/Fqgwxxdcsj', query: {xmid: row.ID, bmbm_Qs: row.BMBM_QS}})
                } else if (flag == '30006') {
                    this.$router.push({path: '/Gwxxfqlbsj', query: {xmid: row.ID, taskId: row.TASKID}})
                } else if (flag == '30001') {
                    this.$router.push({path: '/fqwtsj', query: {xmid: row.ID}})
                } else if (flag == '30002') {
                    let params = {
                        xmid: row.ID,
                        activityId: '30002'
                    }
                    goSjrwfp(params).then((res) => {
                        if (res.data == true) {
                            this.$router.push({path: '/sjrwfp', query: {xmid: row.ID, activityId: '30002'}})
                        }
                    })
                } else if (flag == '30003') {
                    let params = {
                        xmid: row.ID,
                        activityId: '30003'
                    }
                    goSjrwfp(params).then((res) => {
                        if (res.data == true) {
                            this.$router.push({
                                path: '/scsjcg',
                                query: {xmid: row.ID, activityId: '30003', sjstrzwid: row.REFID, isxmtc: "0"}
                            })
                        }
                    })
                } else if (flag == '40001') {
                    let params = {
                        xmbh: row.XMBH,//项目编号
                        /*azlx: row.AZLX,*/
                        xmdz: row.XMDZ,//项目地址
                        page: '1',
                        size: '99999',
                        bs: 0,
                    }
                    getYswtList(params).then(res => {

                        this.yswt = res.data.yswt;
                        for (let i = 0; i < this.yswt.length; i++) {
                            if (row.ID == this.yswt[i].id) {
                                this.$router.push({
                                    path: '/YSfqwt',
                                    query: {tow1: this.yswt[i], yszt: this.yswt[i].yszt, xmid: row.ID}
                                })
                            }
                        }
                    });
                } else if (flag == '40002') {
                    this.$router.push({path: '/YSruzp', query: {xmid: row.ID}})


                    /*let params = {
                        xmbh: row.XMBH,//项目编号
                        azlx: row.AZLX,
                        xmdz: row.XMDZ,//项目地址
                        page: '1',
                        size: '99999',
                        bs:1,
                    }
                    getYswtList(params).then(res => {
                        this.yszp = res.data.yszp;
                        for (let i=0;i<this.yszp.length;i++){
                            if (row.ID == this.yszp[i].id){
                                this.$router.push({path:'/YSruzp', query:{ tow1:this.yszp[i],xmid:this.yszp[i].id }})
                            }
                        }
                    });*/
                } else if (flag == '40003') {
                    let params = {
                        xmid: row.ID,//项目id
                        xmbh: row.XMBH,//项目编号
                        /*azlx: row.AZLX,*/
                        xmdz: row.XMDZ,//项目地址
                        page: '1',
                        size: '99999',
                        bs: 2,
                    }
                    getYswtList(params).then(res => {
                        this.ysbz = res.data.ysbz;
                        for (let i = 0; i < this.ysbz.length; i++) {
                            if (row.ID == this.ysbz[i].id) {
                                this.$router.push({path: '/YSbzxq', query: {tow1: this.ysbz[i], xmid: row.ID}})
                            }
                        }
                    });
                } else if (flag == '40008') {
                    let params = {
                        xmbh: row.XMBH,//项目编号
                        /*azlx: row.AZLX,*/
                        xmdz: row.XMDZ,//项目地址
                        page: '1',
                        size: '99999',
                        bs: 3,
                    }
                    getYswtList(params).then(res => {
                        this.ldsh = res.data.ysldsh;
                        for (let i = 0; i < this.ldsh.length; i++) {
                            if (row.ID == this.ldsh[i].id) {
                                this.$router.push({path: '/YSldsh', query: {tow1: this.ldsh[i], xmid: row.ID}})
                            }
                        }
                    });
                } else if (flag == '40081') {
                    let params = {
                        xmbh: row.XMBH,//项目编号
                        /*azlx: row.AZLX,*/
                        xmdz: row.XMDZ,//项目地址
                        page: '1',
                        size: '99999',
                        bs: 4,
                    }
                    getYswtList(params).then(res => {
                        this.ysqs = res.data.ysqs;
                        for (let i = 0; i < this.ysqs.length; i++) {
                            if (row.ID == this.ysqs[i].id) {
                                this.$router.push({path: '/Qsxq', query: {tow1: this.ysqs[i], xmid: row.ID}})
                            }
                        }
                    });
                } else if (flag == 'C0018') {
                    this.$router.push({path: '/Bzyhjsslbsj', query: {xmid: row.ID, taskId: row.TASKID}})
                } else if (flag == 'C0019') {
                    this.$router.push({path: '/JssqCslbsj', query: {xmid: row.ID,taskId: row.TASKID}})
                } else if (flag == '10004' || flag == '10005' || flag == '10006') {
                    let params = {
                        rwid: row.ID
                    };
                    getXqslById(params).then(res => {
                        let rows = res.data.list[0];
                        let param = {
                            rwid: rows.ID,
                            type: rows.TYPEID,
                            activityId: flag,
                            taskId: row.TASKID
                        };
                        this.$router.push({path: '/zpry', query: param})
                    });
                } else if (flag == '10007' || flag == '10008' || flag == '10009') {
                    let params = {
                        rwid: row.ID
                    };
                    getXqslById(params).then(res => {
                        let rows = res.data.list[0];
                        let para = Object.assign({activityId: flag, rwid: row.ID, taskId: row.TASKID}, rows);
                        this.$router.push({path: '/qkxq', query: para});
                    });
                } else if (flag == '40011') {
                    // this.$router.push({path: '/YSZjqt', query: {xmid: row.ID}});
                    this.$router.push({
                        path: '/YSZjqtFk', query: {
                            xmid: row.ID, refid: row.REFID,
                            activityId: flag, taskId: row.TASKID
                        }
                    });
                }
                // else if(flag == '40012'){
                // 	this.$router.push({path: '/YSZjqtFk', query: {xmid: row.ID,refid:row.REFID}});
                // }
                else if (flag == '70001') {
                    this.$router.push({
                        path: '/editSgxd', query: {
                            xmid: row.ID, type: '1', isEdit: '1',
                            sgdid: row.REFID, activityId: flag, rwbh: row.RWBH, isTh: '1', taskId: row.TASKID
                        }
                    });
                } else if (flag == '70002') {
                    this.$router.push({
                        path: '/shSgxd', query: {
                            xmid: row.ID, type: '1', isEdit: '2',
                            sgdid: row.REFID, activityId: flag, taskId: row.TASKID
                        }
                    });
                } else if (flag == '70003') {
                    this.$router.push({
                        path: '/zpSgxd', query: {
                            xmid: row.ID, type: '1', isEdit: '2',
                            sgdid: row.REFID, activityId: flag, taskId: row.TASKID
                        }
                    });
                } else if (flag == '70004') {

                } else if (flag == '70005') {
                    let params = {
                        sgdid: row.REFID
                    };
                    getSgrwdById(params).then(res => {
                        this.$router.push({
                            path: '/xdSgxd', query: {
                                xmid: row.ID, type: '1', isEdit: '2',
                                sgdid: row.REFID, xmjl: res.data.list[0].XMGZRY, activityId: flag, taskId: row.TASKID
                            }
                        });
                    });
                } else if (flag == '80001') {
                    this.$router.push({path: '/Sqzhyslbsj', query: {xmid: row.ID}});
                } else if (flag == '80002') {
                    let params = {
                        xmid: row.ID,
                    };
                    selectRwzbid(params).then((res) => {
                        this.$router.push({
                            path: '/Xdzhysdlbsj', query: {
                                xmid: row.ID, taskId: row.TASKID, sqbh: res.data.sqbh, sqdid: res.data.sqdid,
                                sqlx: res.data.sqlx, lxr: res.data.lxr, lxrsjh: res.data.lxrsjh, bmbmQs: res.data.bmbmQs
                            }
                        });
                    });

                    // let params = {
                    // 	sqdid:row.REFID
                    // };
                    // getZhysdById(params).then(res => {
                    // 	this.$router.push({path: '/Xdzhysdlbsj',query: {xmid:row.ID,sqdid:row.REFID,
                    // 			sqlx:res.data.list[0].}});
                    // });
                } else if (flag == '80003' || flag == '80004' || flag == '80005' || flag == '80006' || flag == '80007'
                    || flag == '80008') {
                    this.$router.push({
                        path: '/zpzhys', query: {
                            xmid: row.ID, sqlx: row.sqlx, sqbh: row.sqbh,
                            sqdid: row.REFID.split(",")[0], flag: flag, bmmc: row.REFID.split(",")[1], activityId: flag
                        }
                    });
                } else if (flag == '80009' || flag == '80010' || flag == '80011' || flag == '80012' || flag == '80013'
                    || flag == '80014') {
                    this.$router.push({
                        path: '/yszhys', query: {
                            xmid: row.ID, sqlx: row.sqlx, sqbh: row.sqbh,
                            sqdid: row.REFID.split(",")[0], bmmc: row.REFID.split(",")[1], activityId: flag
                        }
                    });
                } else if (flag == '80015') {
                    this.$router.push({
                        path: '/fkzhys',
                        query: {xmid: row.ID, sqdid: row.REFID.split(",")[0], activityId: flag}
                    });
                } else if (flag == '90001') {
                    let params = {
                        xmid: row.ID,
                    };
                    queryxmxxByid(params).then(res => {
                        this.$router.push({
                            path: '/addSbyjd',
                            query: {
                                xmid: row.ID,
                                zyjdbh: row.RWBH,
                                yjzbid: row.REFID,
                                isEdit: '1',
                                xmmc: res.data.list[0].XMMC,
                                isTh: '1',
                                xmbh: res.data.list[0].XMBH,
                                gclx: res.data.list[0].AZLX,
                                jsdw: res.data.list[0].DWMC,
                                activityId: flag,
                                taskId: row.TASKID
                            }
                        });
                    });
                } else if (flag == '90003') {
                    this.$router.push({
                        path: '/shSbyjd', query: {
                            xmid: row.ID, rwbh: row.RWBH,
                            refId: row.REFID, activityId: flag, taskId: row.TASKID
                        }
                    });
                } else if (flag == '90004') {
                    this.$router.push({
                        path: '/cbySbyjd', query: {
                            xmid: row.ID, rwbh: row.RWBH,
                            refId: row.REFID, activityId: flag, taskId: row.TASKID
                        }
                    });
                } else if (flag == '90010') {
                    let params = {
                        xmid: row.ID,
                        activityId: '90010',
                        type: "bwyj"
                    }
                    BwyjHandleEdit(params).then((res) => {
                        if (res.data) {
                            this.$router.push({path: '/bwyjHandleShow', query: {xmid: row.ID, isReadonly: '1'}});
                        }
                    })

                }
            },
            created() {
                window.addEventListener('resize', this.getHeight);
                this.getHeight()
            },

        },
        mounted() {
            this.created();
            let end = new Date();
            let start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);//一个月前的时间
            this.filters.jssj = util.formatDate.format(end, 'yyyy-MM-dd');
            this.filters.kssj = util.formatDate.format(start, 'yyyy-MM-dd');

            getTypeCheckbox().then((res) => {
                this.typeList = res.data.list;
            });
            let JSONstr = sessionStorage.getItem("user");
            let JSON = eval("(" + JSONstr + ")");
            this.rwblr = JSON.id;
            this.findBacklog();
            this.findOriginate();
            this.findDone();

        }
    }

</script>

<style scoped>
    .backcol {
        background-color: #ffffff;
    }

    .myWorkBodyRwdb {
        width: 100%;
        height: 20px;
        background-color: #FAFAFA;
        border-radius: 3px 3px 0px 0px;
        /*代表设置对象左上角3px圆角、右上角4px圆角、右下角5px圆角、左下角6px圆角。*/
        margin: auto;
        padding-top: 15px;
        font-size: 13px;
        font-weight: bold;
        color: #7E8392;
    }

    .myWorkBodyRwdbDb {
        float: left;
        margin-left: 20px;
        margin-top: -10px
    }

    .myWorkBodyRwdbA {
        float: right;
        margin-right: 20px;
        margin-top: -5px;
        font-size: 14px;
    }

    .myWorkBodyRwdbA a:hover {
        text-decoration: underline;
    }

    .lignt_green {
        color: #67c23a;
        font-size: large
    }

    .lignt_yellow {
        color: #fee300;
        font-size: large
    }

    .lignt_red {
        color: #f56c6c;
        font-size: large
    }

    .tit {
        width: 100%;
        height: 35px;
        line-height: 35px;
        background-color: #FAFAFA;
        box-sizing: border-box;
        padding: 0 12px;
    }

    .tit:after {
        content: "";
        display: inline-block;
        width: 100%;
        height: 0;
    }

    .tit_lf, .tit_rt {
        display: inline-block;
    }

    .tit_lf {
        font-size: 13px;
        font-weight: bold;
        color: #7E8392;
        float: left;
    }

    .tit_rt {
        font-size: 12px;
        color: #7E8392;
        float: right;
        cursor: pointer;
    }
</style>
