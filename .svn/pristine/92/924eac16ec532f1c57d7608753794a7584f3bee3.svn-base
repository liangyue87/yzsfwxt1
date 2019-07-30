// import axios from 'axios';
// import apiUrl from '../httpConfig/baseUrl'
// axios.defaults.baseURL = apiUrl

import {get,post} from '../httpConfig/axioss.js'


//let base = 'http://localhost:8080/api/v1.0';
//let base = '';
/*----------------   User  --------------------*/
export const requestLogin = params => { return post(`/UserController/login`, params).then(res => res.data); };//post请求用此方法

export const getUserList = params => { return get(`/UserController/getUserList`, { params: params }); };//get请求用此方法

export const removeUser = params => { return post(`/UserController/deleteUserByTid`, params).then(res => res.data); };

export const batchRemoveUser = params => { return get(`${base}/user/batchremove`, { params: params }); };

export const addUser = params => { return post(`/UserController/saveUser`, params).then(res => res.data); };

export const showAllDept = params => { return get(`/DepartmentController/showDepartmentTree`, { params: params }); };
/*获取短信接收设置*/
export const getDxSettings = params => { return get(`/UserController/getDxSettings`, { params: params }); };
/*设置短信接收*/
export const dxSettings = params => { return post(`/UserController/dxSettings`, params).then(res => res.data); };
/*获取用户信息*/
export const getUser = params => { return post(`/UserController/getUser`, params).then(res => res.data); };
/*更新用户基本信息*/
export const updateUser = params => { return post(`/UserController/updateUser`, params).then(res => res.data); };
/*对比输入的原始密码*/
export const getPassWordDb = params => { return post(`/UserController/getPassWordDb`, params).then(res => res.data); };
/*重置密码*/
export const editpassword = params => { return post(`/UserController/editpassword`, params).then(res => res.data); };

//短信接口列表数据
export const getdxjslist = params => { return get(`/GwxxController/getdxjslist`,{ params: params });};
export const saveDyjl = params => { return post(`/GwxxController/saveDyjl`, params).then(res => res.data); };
