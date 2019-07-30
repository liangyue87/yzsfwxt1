import {get,post} from '../httpConfig/axioss.js'

//let base = 'http://localhost:8080/api/v1.0';
//let base = '';

// export const requestLogin = params => { return axios.post(`/MenuController/login`, params).then(res => res.data); };

export const getCodeTree = params => { return get(`/CodeController/getCodeTree`, { params: params }); };
export const getCodeById = params => { return get(`/CodeController/getCodeById`, { params: params }); };
export const addCode = params => { return post(`/CodeController/saveCode`, params).then(res => res.data); };
export const deleteCode = params => { return post(`/CodeController/deleteCode`, params).then(res => res.data); };


