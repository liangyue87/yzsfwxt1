import {get,post} from '../httpConfig/axioss.js'
import {postDownloadFile, postFile} from "../httpConfig/axioss";

//获取申请发起综合验收列表
export const getSqzhysLsit = params => { return get(`/SqzhysController/getSqzhysLsit`,{ params: params });};
export const selectZlflsl = params => { return get(`/SqzhysController/selectZlflsl`,{ params: params });};
export const selectZhysfwxx = params => { return get(`/SqzhysController/selectZhysfwxx`,{ params: params });};
export const saveZhysSqzb = params => { return post(`/SqzhysController/saveZhysSqzb`, params,).then(res => res.data); };

export const getXdzhysdlb = params => { return get(`/XdzhysdController/getXdzhysdlb`,{ params: params });};

export const getSbxx = params => { return get(`/SqzhysController/getSbxx`,{ params: params });};

//发起验收
export const doFqys = params => { return get(`/XdzhysdController/doFqys`,{ params: params });};
//获得指派人员
export const getZpry = params => { return get(`/XdzhysdController/getZpry`,{ params: params });};
//获得当前登陆人所在参与部门
export const getCybm = params => { return get(`/XdzhysdController/getCybm`,{ params: params });};
//根据申请单ID获得
export const getFkyj = params => { return get(`/XdzhysdController/getFkyj`,{ params: params });};

export const getBmxx = params => { return post(`/XdzhysdController/getBmxx`, params,).then(res => res.data); };

export const importFile = params => { return postFile(`/XdzhysdController/upoadFile`, params).then(res => res.data); };

export const getZhysdById = params => { return get(`/XdzhysdController/getZhysdById`,{ params: params });};

export const download = params => { return postDownloadFile(`/SqzhysController/download`, params); };

export const Bmdownload = params => { return postDownloadFile(`/XdzhysdController/Bmdownload`, params); };

//下载和查看文件
export const downloads = params => { return postDownloadFile(`/XdzhysdController/downloads`, params).then(res => res.data); };

export const selectRwzbid = params => { return get(`/XdzhysdController/selectRwzbid`,{ params: params });};
//获取附件列表（上传）
export const getFkFjData = params => { return post(`/XdzhysdController/getFkFjData`, params,).then(res => res.data); };
//附件列表删除
export const deleteFkFj = params => { return post(`/XdzhysdController/deleteFkFj`, params,).then(res => res.data); };


//个部门资料下载
export const toDetial = params => { return get(`/XdzhysdController/toDetial`,{ params: params });};

//更改签收状态
export const getQianshou = params => { return post(`/SgxdController/updateCheckTask`, params).then(res => res.data); };

