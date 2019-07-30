import {get,post} from '../httpConfig/axioss.js'

//获取水表口径
export const getSbkjList = params => {
    return get(`/JbfsController/getSbkjList`).then(res => res.data);
}

export const requestLogin = params => { return post(`/UserController/login`, params).then(res => res.data); };

export const getJbfsList = params => { return get(`/JbfsController/getJbfsList`, { params: params }); };

export const removeUser = params => { return post(`/DemoHwController/deleteUserByTid`, params).then(res => res.data); };

export const batchRemoveUser = params => { return get(`${base}/user/batchremove`, { params: params }); };

export const addUser = params => { return post(`/DemoHwController/saveUser`, params).then(res => res.data); };
