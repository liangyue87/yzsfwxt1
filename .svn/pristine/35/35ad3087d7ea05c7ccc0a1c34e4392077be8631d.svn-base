import {get,post} from '../httpConfig/axioss.js'


export const getJjr = params => { return get(`/JjrdyController/getAllxtgl_jjrdy`, { params: params }); };

export const saveJjr = params => { return post(`/JjrdyController/save_jjrdy`, params).then(res => res.data); };

export const editJjr = params => { return post(`/HtglJjrNew/editJjr`, params).then(res => res.data); };

export const remove = params => { return post(`/HtglJjrNew/deleteJjrByTid`, params).then(res => res.data); }
