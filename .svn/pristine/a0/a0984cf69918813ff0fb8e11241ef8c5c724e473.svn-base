import axios from 'axios';
import { Message } from 'element-ui';
import Qs from 'qs'

axios.defaults.timeout = 5000;
axios.defaults.baseURL ='http://localhost:8095/';
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';


// //http request 拦截器
// axios.interceptors.request.use(
//     config => {
//         // const token = getCookie('名称');注意使用的时候需要引入cookie方法，推荐js-cookie
//         config.data = JSON.stringify(config.data);
//         config.headers = {
//             'Content-Type':'application/x-www-form-urlencoded'
//         }
//         // if(token){
//         //   config.params = {'token':token}
//         // }
//         return config;
//     },
//     error => {
//         return Promise.reject(err);
//     }
// );
//
//
// //http response 拦截器
// axios.interceptors.response.use(
//     response => {
//         if(response.data.errCode ==2){
//             router.push({
//                 path:"/login",
//                 querry:{redirect:router.currentRoute.fullPath}//从哪个页面跳转
//             })
//         }
//         return response;
//     },
//     error => {
//         return Promise.reject(error)
//     }
// )
//添加请求拦截器
axios.interceptors.request.use(function (config) {
    config.headers['Content-Type'] = 'application/x-www-form-urlencoded'
    if(config.method === 'post') { // post请求时，处理数据
        config.data = Qs.stringify( {
            ...config.data //后台数据接收这块需要以表单形式提交数据，而axios中post默认的提交是json数据,所以这里选用qs模块来处理数据，也有其他处理方式，但个人觉得这个方式最简单好用
        })
    }
    return config;
}, function (error) {
    loadinginstace.close()
    return Promise.reject(error);
})
// 添加响应拦截器
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

/**
 * 封装get方法
 * @param url
 * @param data
 * @returns {Promise}
 */

export function get(url,params={}){
    return new Promise((resolve,reject) => {
        axios.get(url,{
            params:params
        })
            .then(response => {
                resolve(response.data);
            })
            .catch(err => {
                reject(err)
            })
    })
}


/**
 * 封装post请求
 * @param url
 * @param data
 * @returns {Promise}
 */

export function post(url,data = { }){
    console.log("data:",data);
    console.log(typeof(data));
    data = Qs.stringify(data);
    // let param = new URLSearchParams()
    // param.append('username', 'admin')
    // param.append('password', '123456')
    // console.log("data:",data);
    // console.log(typeof(data));
    // return new Promise((resolve,reject) => {
    //     axios.post(url,data = data)
    //         .then(response => {
    //             resolve(response.data);
    //         },err => {
    //             reject(err)
    //         })
    // })
    return new Promise ((resolve,reject) => {
        axios({
            method: 'post',
            url: url,
            data: Qs.stringify(data)
        }).then(response => {
            resolve(response.data);
        },err => {
            reject(err)
        })
    })


}

/**
 * 封装patch请求
 * @param url
 * @param data
 * @returns {Promise}
 */

export function patch(url,data = {}){
    return new Promise((resolve,reject) => {
        axios.patch(url,data)
            .then(response => {
                resolve(response.data);
            },err => {
                reject(err)
            })
    })
}

/**
 * 封装put请求
 * @param url
 * @param data
 * @returns {Promise}
 */

export function put(url,data = {}){
    return new Promise((resolve,reject) => {
        axios.put(url,data)
            .then(response => {
                resolve(response.data);
            },err => {
                reject(err)
            })
    })
}
