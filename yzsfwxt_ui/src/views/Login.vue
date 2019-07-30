<template>
  <el-container>
    <el-main>
      <div>
        <div class="head">
          <img src="../../static/img/供水系统登录_03.png" class="headImg">
        </div>
        <div class="body">
          <div class="bodyL">
            <img src="../../static/img/供水系统登录_07.png" style="float: right">
          </div>
          <div class="bodyR">
            <div style="background-color:white;height: 352px;width: 350px">
              <el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" >
                <div class="bodyForm">
                  <div class="bodyFormLx"></div>
                  <div class="bodyFormYhdl">
                    <span class="bodyFormYhdl_zt">用户登录</span>
                  </div>
                </div>
               <!-- <h3 class="title">系统登录</h3>-->
                <el-form-item prop="account">
                  <input @keyup.enter.native="handleSubmit2" v-model="ruleForm2.account" type="text" auto-complete="off" placeholder="名称" class="inputZh"/>
                </el-form-item>
                <el-form-item prop="checkPass">
                  <input v-model="ruleForm2.checkPass" @keyup.enter.native="handleSubmit2" type="password" auto-complete="off" placeholder="密码" class="inputMa"/>
                </el-form-item>
                <el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>
                <el-form-item style="width:100%;">
                  <button type="primary" @click.prevent="handleSubmit2" :loading="logining" class="buttonDl">
                    登&ensp;&ensp;录
                  </button>
                 <!-- <el-button type="primary" style="width:300px;margin-left:  25px;" @click.native.prevent="handleSubmit2" :loading="logining">登&ensp;&ensp;录</el-button>-->
                </el-form-item>
              </el-form>
            </div>
          </div>
        </div>
        <div class="foot">
              合肥供水集团新装业务报建协同系统V1.0
        </div>
      </div>
    </el-main>
  </el-container>
</template>

<script>
  import { requestLogin } from '../api/api';
  import { getMenuByUserid } from '../api/menu';
  import "../styles/Login.css";

 // @import "//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css";
  //import NProgress from 'nprogress'
  export default {
    data() {
      return {
        logining: false,
        ruleForm2: {
          account: '',
          checkPass: ''
        },
        rules2: {
          account: [
            { required: true, message: '请输入账号', trigger: 'blur' },
            //{ validator: validaePass }
          ],
          checkPass: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            //{ validator: validaePass2 }
          ]
        },
        checked: true
      };
    },
    methods: {
      handleReset2() {
        this.$refs.ruleForm2.resetFields();
      },
      /*filterAsyncRouter(asyncRouterMap) { //遍历后台传来的路由字符串，转换为组件对象
          const accessedRouters = asyncRouterMap.filter(route => {
          if (route.component) {
              //route.component = _import(route.component)
              route.component = DynamicRouterMap.default.dynamicRouterMap[route.component]
          }
          if (route.children && route.children.length) {
              route.children = this.filterAsyncRouter(route.children)
          }
          return true
          })

          return accessedRouters
      },*/
      handleSubmit2(ev) {
        var _this = this;
        this.$refs.ruleForm2.validate((valid) => {
          if (valid) {
            //_this.$router.replace('/table');
            // this.logining = true;
            //NProgress.start();
            var loginParams = { 'username': this.ruleForm2.account, 'password': this.ruleForm2.checkPass };
            requestLogin(loginParams).then(data => {
              this.logining = false;
              //NProgress.done();
              let { msg, code, user } = data;
              if (code !== 200) {
                this.$message({
                  message: msg,
                  type: 'error'
                });
              } else {
                sessionStorage.setItem('user', JSON.stringify(user));
                this.logining = true;
                let params = { userid : user.id}
                getMenuByUserid(params).then((res) => {
                  let routers = [];
                  let menuName = [];//一级菜单名字
                  for (let x = 0;x<res.data.length;x++){
                    let mm = {
                      name: res.data[x].text,
                      iconCls: res.data[x].icon,
                    }
                    menuName.push(mm);
                    let data = res.data[x].children;
                    let router = [];
                    for(let i=0;i<data.length;i++) {
                      let comp_p = {};
                      if(data[i].children.length == 0){
                        comp_p = {
                          path: '/',
                          component: 'Home',//() => import('.' + data[i].url + '.vue'),
                          name: '',
                          iconCls: data[i].icon,
                          leaf : true,
                          children: [
                            {
                              path: data[i].url,
                              component: data[i].url,
                              name: data[i].text
                            }
                          ]
                        }
                      }else {
                        comp_p = {
                          path: data[i].url,
                          component: 'Home',//() => import('.' + data[i].url + '.vue'),
                          name: data[i].text,
                          iconCls: data[i].icon,
                          children: []
                        }
                      }
                      let coms = data[i].children
                      for (let j = 0; j < coms.length; j++) {
                        let com = {
                          path: coms[j].url,//coms[j].url,
                          component: coms[j].url,//() => import('./work' + data[i].url + '.vue'),
                          name: coms[j].text,
                          iconCls: data[i].icon,
                          meta: {
                            keepAlive: true,
                            title: coms[j].text,
                            icon: 'rt-icon-log'
                          }
                        }
                        comp_p.children.push(com);
                      }
                      router.push(comp_p);
                    }
                    routers.push(router);
                  }
                  this.$router.options.routes = [];
                  sessionStorage.setItem('menuName', JSON.stringify({'arr':menuName}));
                  sessionStorage.setItem('menuList', JSON.stringify({'arr':routers}));
                  sessionStorage.setItem('menuArr', JSON.stringify({'arr':routers[0]}));
                  for(let o=0;o<routers[0].length;o++){
                    this.$router.options.routes.push(routers[0][o]);
                  }
                  let path = routers[0][0].children[0].path;
                  this.$router.push({ path: path });
                })
                  //this.$router.push({ path: '/Backlog' });
              }
            });
          } else {
            console.log('error submit!!');
            return false;
          }
        });
        if(this.logining == true){
          this.$router.push({ path: '/Home' })
        }
      }
    }
  }

</script>

<style lang="scss" scoped>
  .login-container {
    /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
    .title {
      margin: 0px auto 40px auto;
      text-align: center;
      color: #505458;
    }
    .remember {
      margin: 0px 0px 35px 0px;
    }
  }
</style>