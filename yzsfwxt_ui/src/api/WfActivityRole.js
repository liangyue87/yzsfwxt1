import {get,post} from '../httpConfig/axioss.js'


export const getActivityRole = params => { return get(`/WfActivityRoleController/getActivityRole`, { params: params }); };

export const remove = params => { return post(`/WfActivityRoleController/deleteActivityRoleByTid`, params).then(res => res.data); };

export const save = params => { return post(`/WfActivityRoleController/save`, params).then(res => res.data); };
