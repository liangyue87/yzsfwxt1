import {get,post} from '../httpConfig/axioss.js'


export const getDfActivity = params => { return get(`/WfDfActivityController/getDfActivity`, { params: params }); };

export const remove = params => { return post(`/WfDfActivityController/deleteDfActivityByTid`, params).then(res => res.data); };

export const save = params => { return post(`/WfDfActivityController/save`, params).then(res => res.data); };
