// import axios from 'axios';
// import apiUrl from '../httpConfig/baseUrl'
// axios.defaults.baseURL = apiUrl

import {get,post} from '../httpConfig/axioss.js'


//let base = 'http://localhost:8080/api/v1.0';
//let base = '';
/*----------------   User  --------------------*/
//export const requestLogin = params => { return post(`/UserController/login`, params).then(res => res.data); };//post请求用此方法

export const findBacklog = params => { return get(`/WorkController/findBacklog`, { params: params }); };//获取待办
export const findDone = params => { return get(`/WorkController/findDone`, { params: params }); };//获取我办理的任务
export const findOriginate = params => { return get(`/WorkController/findOriginate`, { params: params }); };//获取我发起的任务
export const getTypeCheckbox = params => { return get(`/WorkController/getTypeCheckbox`, { params: params }); };//获取任务类型下拉框数据
export const getData = params => { return get(`/WorkController/getData`, { params: params }); };//获取项目经理首页数据
export const getMessageList = params => { return get(`/WorkController/getMessageList`, { params: params }); };//获取项目经理首页消息提醒


export const getAllInstInfo = params => { return get(`/WorkController/getAllInstInfo`, { params: params });};
export const getAllInstInfoNum = params => { return get(`/WorkController/getAllInstInfoNum`, { params: params });};

export const getXmlctDqjd = params => { return get(`/XmlctController/getXmlctDqjd`,{ params: params });};

export const getXmlb = params => { return get(`/XmlctController/getXmlb`,{ params: params });};

export const getXqslByXmid = params => { return get(`/XqslController/getXqslByXmid`, { params: params }); };

export const queryHtbz = params => { return post(`/HtbzController/queryHtbz`, params).then(res => res.data); };

export const querysfkp = params => { return post(`/SfkpController/querysfkp`, params).then(res => res.data); };

export const getXmmcByid = params => { return post(`/XmlctController/getXmmcByid`, params).then(res => res.data); };
