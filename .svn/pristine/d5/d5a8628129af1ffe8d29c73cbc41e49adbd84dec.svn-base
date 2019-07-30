import {get,post} from '../httpConfig/axioss.js'


export const xtgl_htmb = params => { return get(`/HtqdController/xtgl_htmb`, { params: params }); };

export const saveHtmb = params => { return post(`/HtqdController/save_Htmb`, params).then(res => res.data); };

export const remove = params => { return post(`/HtqdController/deleteHtmbById`, params).then(res => res.data); }
