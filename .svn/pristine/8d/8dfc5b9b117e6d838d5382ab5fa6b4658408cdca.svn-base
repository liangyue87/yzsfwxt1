import axios from 'axios';
import routes from "../routes";
import VueRouter from 'vue-router';
import apiUrl from "./baseUrl";
import qs from 'qs'
import _this from '../main.js';
// 创建axios实例
const service = axios.create({
    timeout: 30000, // 请求超时时间
    baseURL: apiUrl
})
const router = new VueRouter({
        routes
})
// const router = this.$router;
// router.beforeEach((to, from, next) => {
//     debugger;
//     //NProgress.start();
//     if (to.path == '/login') {
//       sessionStorage.removeItem('user');
//     }
//     let user = JSON.parse(sessionStorage.getItem('user'));
//     if (!user && to.path != '/login') {
//       next({ path: '/login' })
//     } else {
//       next()
//     }
//   })

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
    loadinginstace.close()
    return Promise.reject(error);
})
//添加响应拦截器
axios.interceptors.response.use(function(response){
    if(response.data=="noLogin"){
        router.push({//你需要接受路由的参数再跳转
            path: '/login'
        });
        router.go(0);
    }else{
        return response;
    }

},function(error){
    return Promise.reject(error);
});

// export function get(url, params = {}) {
//     // params.t = new Date().getTime(); //get方法加一个时间参数,解决ie下可能缓存问题.
//     console.log(params)
//     let sendParams = qs.stringify(params)
//     return service({
//         url: url,
//         method: 'get',
//         headers: {
//         },
//         sendParams
//     })
// }

export function get(url,params={}){
    return new Promise((resolve,reject) => {
        axios.get(url,params)
            .then(response => {
                resolve(response);
            })
            .catch(err => {
                reject(err)
            })
    })
}


//封装post请求
export function post(url, data={}) {
    //默认配置
    let sendObject={
        url: url,
        method: 'post',
        headers: {
            'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'
        },
        data:qs.stringify(data)
    };
    return service(sendObject);
}

//封装put方法 (resfulAPI常用)
export function put(url,data = {}){
    return service({
        url: url,
        method: 'put',
        headers: {
            'Content-Type':'application/json;charset=UTF-8'
        },
        data:qs.stringify(data)
    })
}

//封装post上传文件请求
export function postFile(url, data) {
    //默认配置
    let sendObject={
        url: url,
        method: 'post',
        headers: {
            'Content-Type':'multipart/form-data'
        },
        data: data
    };
    return service(sendObject);
}

//封装post上传文件请求
export function postDownloadFile(url, data) {
    //默认配置
    let sendObject={
        url: url,
        method: 'post',
        headers: {
            'Content-Type': 'application/json; application/octet-stream; multipart/form-data;'
        },
        responseType: 'blob',
        data: data
    };
    return service(sendObject);
}

//删除方法(resfulAPI常用)
export function deletes(url){
    return service({
        url: url,
        method: 'delete',
        headers: {}
    })
}

//不要忘记export
export {
    service
}
