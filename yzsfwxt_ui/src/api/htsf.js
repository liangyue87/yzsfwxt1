import {get,post} from '../httpConfig/axioss.js'
import {postDownloadFile, postFile} from "../httpConfig/axioss";


export const add_htbz = params => { return get(`/HtbzController/add_htbz`, { params: params }); };

export const getXmBybh = params => { return get(`/HtbzController/getXmBybh`, { params: params }); };


export const queryZllist = params => { return get(`/HtbzController/queryZllist`, { params: params }); };

export const deleteFjByid = params => { return get(`/HtbzController/deleteFjByid`, { params: params }); };

export const download = params => { return postDownloadFile(`/sjstController/download`, params); };

export const queryxmxxByid = params => { return get(`/HtbzController/queryxmxxByid`, { params: params }); };

export const queryYslist = params => { return get(`/HtbzController/queryYslist`, { params: params }); };

export const saveHt = params => { return post(`/HtbzController/saveHt`, params).then(res => res.data); };

export const queryHtbz = params => { return post(`/HtbzController/queryHtbz`, params).then(res => res.data); };

export const getSftzd = params => { return get(`/SfkpController/getSftzd`, { params: params }); };

export const getSkzh = params => { return get(`/SfkpController/getSkzh`, { params: params }); };

export const addJftzd = params => { return get(`/SfkpController/addJftzd`, { params: params }); };

export const getGcflm = params => { return get(`/SfkpController/getGcflm`, { params: params }); };

export const updateSftzd = params => { return get(`/SfkpController/updateSftzd`, { params: params }); };

export const getHtbzList = params => { return post(`/HtbzController/getHtbzList`, params).then(res => res.data); };

export const getJfList = params => { return post(`/SfkpController/getJfList`, params).then(res => res.data); };

export const importFile = params => { return postFile(`/HtbzController/upoadFile`, params).then(res => res.data); };

export const queryGsbByXmid = params => { return get(`/SfkpController/queryGsbByXmid`,{params:params}); };

export const gsbelxedit = params => { return post(`/SfkpController/gsbelxedit`, params).then(res => res.data); };

export const gspldjxg = params => { return post(`/SfkpController/gspldjxg`, params).then(res => res.data); };

export const gsjezj = params => { return post(`/SfkpController/gsjezj`, params).then(res => res.data); };

export const queryLdByxmid = params => { return get(`/SfkpController/queryLdByxmid`,{params:params}); };

export const querySbByldid = params => { return get(`/SfkpController/querySbByldid`,{params:params}); };

export const savejfhb = params => { return post(`/SfkpController/savejfhb`, params).then(res => res.data); };

export const queryHb = params => { return get(`/SfkpController/queryHb`,{params:params}); };

export const saveHbgcf = params => { return post(`/SfkpController/saveHbgcf`, params).then(res => res.data); };

export const hbjezj = params => { return post(`/SfkpController/hbjezj`, params).then(res => res.data); };

export const queryByTzdid = params => { return get(`/SfkpController/queryByTzdid`,{params:params}); };

export const querylxByname = params => { return get(`/SfkpController/querylxByname`,{params:params}); };

export const querylxBynames = params => { return get(`/SfkpController/querylxBynames`,{params:params}); };

export const importPz = params => { return postFile(`/SfkpController/importPz`, params).then(res => res.data); };

export const querypjlx = params => { return get(`/SfkpController/querypjlx`,{params:params}); };

export const finddxForm = params => { return get(`/SfkpController/finddxForm`,{params:params}); };
//获取项目基本信息
export const getjbxx = params => { return post(`/SfkpController/getjbxx`, params).then(res => res.data); };
//保存分期金额
export const saveFqje = params => { return post(`/HtbzController/saveFqje`, params).then(res => res.data); };
//查询分期金额设置
export const findFqje = params => { return get(`/HtbzController/findFqje`, { params: params }); };

export const queryjfZllist = params => { return get(`/SfkpController/queryjfZllist`, { params: params }); };

export const deletejfFjByid = params => { return get(`/SfkpController/deletejfFjByid`, { params: params }); };

export const findAzlx = params => { return get(`/YswtController/findAzlx`,{params:params}); };
/*发送短信*/
export const fsdx = params => { return post(`/SfkpController/fsdx`, params).then(res => res.data); };

export const getxmdz = params => { return get(`/SfkpController/getxmdz`,{params:params}); };

export const saveImageToFj = params => {return post(`/SfkpController/saveImageToFj`, params ).then(res => res.data);};
