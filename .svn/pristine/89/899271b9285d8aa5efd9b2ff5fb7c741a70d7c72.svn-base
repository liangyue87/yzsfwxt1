import {get,post} from '../httpConfig/axioss.js'
import {postDownloadFile, postFile} from "../httpConfig/axioss";

//let base = 'http://localhost:8080/api/v1.0';
//let base = '';
/*----------------   User  --------------------*/

export const getXqsl = params => { return get(`/XqslController/getAllXqsl`, { params: params }); };
export const getXqsllx = params => { return get(`/XqslController/getAllXqsl`, { params: params }); }

export const toZpry = params => { return get(`/XqslController/toZpry`, { params: params }); };

export const saveSld = params => { return post(`/XqslController/saveSld`, params).then(res => res.data); };

export const getXqqs = params => { return get(`/XqslController/getXqqs`, { params: params }); };

export const toXqqs = params => { return post(`/XqslController/toXqqs`, params).then(res => res.data); };
//签收时发送短信
export const Qsmessages = params => { return post(`/XqslController/Qsmessages`, params).then(res => res.data); };

export const getZpry = params => { return get(`/XqslController/getZpry`, { params: params }); };

export const getSclx = params => { return get(`/XqslController/getSclx`, { params: params }); };

export const getZlrow = params => { return get(`/XqslController/getZlrow`, { params: params }); };

export const queryXqsl = params => { return get(`/XqslController/queryXqsl`, { params: params }); };

export const queryZlcount = params => { return get(`/XqslController/queryZlcount`, { params: params }); };

export const queryUser = params => {return get(`/XqslController/queryUser`, { params: params }); };

export const getScwj = params => { return get(`/XqslController/getScwj`, { params: params }); };

export const addLdxx = params => { return post(`/XqslController/addLdxx`, params).then(res => res.data); };

export const editLdxx = params => { return post(`/XqslController/editLdxx`, params).then(res => res.data); };

export const getLdxxList = params => { return get(`/XqslController/getLdxxList`, { params: params }); };

export const remove = params => { return post(`/XqslController/deleteLdxxById`, params).then(res => res.data); };

export const importFile = params => { return postFile(`/XqslController/importEnterPriseQuota`, params).then(res => res.data); };

export const getXqslById = params => { return get(`/XqslController/getXqslById`, { params: params }); };

export const getYzbList = params => { return get(`/XqslController/getYzbList`, { params: params }); };

export const yzbremove = params => { return post(`/XqslController/deleteYzbById`, params).then(res => res.data); };

export const addYzb = params => { return post(`/XqslController/addYzb`, params).then(res => res.data); };

export const editYzb = params => { return post(`/XqslController/editYzb`, params).then(res => res.data); };

export const toDetial = params => { return get(`/XqslController/toDetial`,{ params: params });};

export const deleteFj = params => { return get(`/XqslController/deleteFj`,{ params: params });};

export const download = params => { return postDownloadFile(`/XqslController/download`, params); };

export const saveImageToFj = params => {return post(`/XqslController/saveImageToFj`, params ).then(res => res.data);};

export const queryXqByid = params => { return get(`/XqslController/queryXqByid`,{ params: params });};

export const getHyflCodeData = params => { return get(`/SbyjController/getHyflCodeData`,{params:params}); };

export const addGsbjxx = params => { return post(`/XqslController/addGsbjxx`, params).then(res => res.data); };

export const getGsbjxxList = params => { return get(`/XqslController/getGsbjxxList`, { params: params }); };

export const editGsbjxx = params => { return post(`/XqslController/editGsbjxx`, params).then(res => res.data); };

export const gsremove = params => { return post(`/XqslController/deleteGsbjxxById`, params).then(res => res.data); };

export const deleteXq = params => { return post(`/XqslController/deleteXq`, params).then(res => res.data); };

export const updateCheckTask = params => {return post(`/SgxdController/updateCheckTask`, params ).then(res => res.data);};

export const downloadfj = params => { return postDownloadFile(`/sjstController/download`, params); };

/*获得转单人员列表*/
export const queryzdry = params => { return post(`/XqslController/queryzdry`, params).then(res => res.data); };

export const saveZd = params => { return post(`/XqslController/saveZd`, params).then(res => res.data); };

//根据当前登录人，显示申请列表
export const defaultsqlx = params => { return post(`/XqslController/defaultsqlx`, params).then(res => res.data); };
