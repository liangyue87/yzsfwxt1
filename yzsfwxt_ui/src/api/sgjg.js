import {get,post} from '../httpConfig/axioss.js'

export const getSgxdList = params => { return get(`/SgxdController/getSgxdList`,{params:params}); };

export const queryGcxdByXmid = params => { return get(`/SgxdController/queryGcxdByXmid`,{params:params}); };

export const Sbsubmit = params => {return post(`/SgxdController/Sbsubmit`, params ).then(res => res.data);};

export const xdsbClose = params => { return get(`/SgxdController/xdsbClose`, { params: params }); };

export const saveSgxd = params => { return get(`/SgxdController/saveSgxd`, { params: params }); };

//下单列表
export const getSggcdList = params => { return get(`/SgxdController/getSggcdList`,{params:params}); };

export const showEditForm = params => { return get(`/SgxdController/showEditForm`,{params:params}); };

export const getZpry = params => { return get(`/SgxdController/getZpry`, { params: params }); };

export const getSgrwdById = params => { return get(`/SgxdController/getSgrwdById`, { params: params }); };

// export const querySbByLdid = params => { return get(`/SgxdController/querySbByLdid`, { params: params }); };

export const querySbByLdid = params => {return post(`/SgxdController/querySbByLdid`, params ).then(res => res.data);};

export const queryxmxxByid = params => { return get(`/SgxdController/queryxmxxByid`, { params: params }); };

export const getDycontent = params => { return get(`/SgxdController/getDycontent`, { params: params }); };

export const updateCheckTask = params => {return post(`/SgxdController/updateCheckTask`, params ).then(res => res.data);};

export const initLdhb = params => {return get(`/SgxdController/initLdhb`, { params: params } ).then(res => res.data);};

export const initDbpzTable = params => {return get(`/SgxdController/initDbpzTable`, { params: params } );};

//生成施工单ID
export const findsgdid = params => { return get(`/SgxdController/findsgdid`, { params: params }); };

export const HbSumit = params => {return post(`/SgxdController/HbSumit`, params ).then(res => res.data);};

//删除垃圾施工单ID
export const delSgdid = params => {return post(`/SgxdController/delSgdid`, params ).then(res => res.data);};
//管网
export const initGWTable_sgxd = params => {return get(`/SgxdController/initGWTable_sgxd`, { params: params } );};
//管网提交
export const gwsubmit = params => {return post(`/SgxdController/gwsubmit`, params ).then(res => res.data);};

