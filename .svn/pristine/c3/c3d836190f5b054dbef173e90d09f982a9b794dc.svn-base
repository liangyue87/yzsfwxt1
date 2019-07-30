import {get,post} from '../httpConfig/axioss.js'
//let base = 'http://localhost:8080/api/v1.0';
//let base = '';

//export const requestLogin = params => { return post(`/MenuController/login`, params).then(res => res.data); };
export const getMenuById = params => { return get(`/MenuController/getMenuById`, { params: params }); };

export const getAllMenuList = params => { return get(`/MenuController/getMenuTree`, { params: params }); };

export const deleteMenu = params => { return post(`/MenuController/deleMenu`, params ).then(res => res.data);};

export const saveMenu = params => { return post(`/MenuController/saveMenu`, params ).then(res => res.data);};

export const getSbcjList = params => { return get(`/SbcjController/getSbcjList`, params ).then(res => res.data);};

export const getMenuByUserid = params => { return get(`/MenuController/getMenuByUserid`, { params: params }); };
