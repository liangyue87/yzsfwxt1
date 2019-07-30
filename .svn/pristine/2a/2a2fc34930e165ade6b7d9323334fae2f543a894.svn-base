import '@babel/polyfill';
import axios from 'axios';
import qs from 'qs' // 引入qs
import Vue from 'vue';
import App from './App';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
//import 'element-ui/lib/theme-default/index.css'
//import './assets/theme/theme-green/index.css'
import VueRouter from 'vue-router';
import RouterTab from './common/components/other/vue-router-tab/index'
// import 'vue-router-tab/dist/lib/vue-router-tab.css'
import store from '@/store/index';
import Vuex from 'vuex';
//import NProgress from 'nprogress'
//import 'nprogress/nprogress.css'
import routes from './routes';
import uploader from './bigfileuploader'
//import Mock from './mock'
//Mock.bootstrap();
import 'font-awesome/css/font-awesome.min.css';
import echarts from 'echarts' //引入echarts
Vue.prototype.$echarts = echarts
//print
 import Print from 'vue-print-nb'

// import uploader from 'vue-simple-uploader'

//表内编辑vue-element-extends
import 'vue-element-extends/lib/index.css'
import { Editable, EditableColumn } from 'vue-element-extends';

//引用公共js
import publicUtil from 'commom_js'

/*import VueQuillEditor  from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'*/

import '../static/UE/ueditor.config.js'
import '../static/UE/ueditor.all.min.js'
import '../static/UE/lang/zh-cn/zh-cn.js'
import '../static/UE/ueditor.parse.min.js'

import VuePreview from 'vue-preview'
import install from './common/install.js'

import LightTimeline from 'vue-light-timeline'

//import install from './common/install'

/*=============*/
/*
npm install --save vue-resource
vue中使用this.$http.post之前的准备 请求头
*/
import VueResource from 'vue-resource'
install();
Vue.use(VueResource);
Vue.use(Print);
Vue.use(Editable);
Vue.use(EditableColumn);
/*=============*/


Vue.http.options.emulateHTTP = true;
Vue.http.options.emulateJSON = true;

axios.defaults.withCredentials=true;
Vue.prototype.publicUtil = publicUtil;
Vue.use(ElementUI);

Vue.use(VueRouter);
Vue.use(RouterTab);
Vue.use(Vuex);
Vue.use(VuePreview);
Vue.use(VuePreview, {
    mainClass: 'pswp--minimal--dark',
    barsSize: {top: 0, bottom: 0},
    captionEl: false,
    fullscreenEl: false,
    shareEl: false,
    bgOpacity: 0.85,
    tapToClose: true,
    tapToToggleControls: false
});
Vue.use(LightTimeline);
Vue.use(uploader);
// Vue.user(uploader)


/*Vue.use(VueQuillEditor);*/

//NProgress.configure({ showSpinner: false });

const router = new VueRouter({
  routes
});

router.beforeEach((to, from, next) => {
  //NProgress.start();
  if (to.path == '/login') {
    sessionStorage.removeItem('user');
  }
  let user = JSON.parse(sessionStorage.getItem('user'));
  // console.log(user);
  // console.log(!user);
  // if (!user && to.path != '/login' && from.path != '/login' && to.path != '/Backlog') {
  //   next({ path: '/login' })
  // } else {
  //   next()
  // }


    if ((user == null || user == undefined)  && to.path != '/login') {
    next({ path: '/login' })
  } else {
    next()
  }
});

//router.afterEach(transition => {
//NProgress.done();
//});
//添加请求拦截器
axios.interceptors.request.use(function (config) {
  config.headers['Content-Type'] = 'application/x-www-form-urlencoded'
  if(config.method === 'post') { // post请求时，处理数据
    config.data = qs.stringify( {
      ...config.data //后台数据接收这块需要以表单形式提交数据，而axios中post默认的提交是json数据,所以这里选用qs模块来处理数据，也有其他处理方式，但个人觉得这个方式最简单好用
    })
  }
  return config;
}, function (error) {
  loadinginstace.close();
  return Promise.reject(error);
});
//添加响应拦截器
axios.interceptors.response.use(function(response){
  if(response.data=="noLogin"){
    router.push({//你需要接受路由的参数再跳转
      path: '/login'
    });
  }else{
    return response;
  }

},function(error){
  return Promise.reject(error);
});
new Vue({
  el: '#app',
  template: '<App/>',
  router,
  store,
  //components: { App }
  render: h => h(App)
}).$mount('#app');

