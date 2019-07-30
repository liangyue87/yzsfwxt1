import {get,post} from '../httpConfig/axioss.js'
import {postDownloadFile, postFile} from "../httpConfig/axioss";

export const getJssqLsit = params => { return get(`/JssqController/getJssqLsit`,{ params: params });};
export const SelectYshz = params => { return get(`/JssqController/SelectYshz`,{ params: params });};
export const SaveYhjssq = params => { return get(`/JssqController/SaveYhjssq`,{ params: params });};
export const selectBzyhjssList = params => { return get(`/JssqController/selectBzyhjssList`,{ params: params });};

export const getYszlscLsit = params => { return get(`/JssqController/getYszlscLsit`,{ params: params });};
export const deleteJszl = params => { return get(`/JssqController/deleteJszl`,{ params: params });};

export const SaveJsje = params => { return get(`/JssqController/SaveJsje`,{ params: params });};

export const saveJsbc = params => { return post(`/JssqController/saveJsbc`, params).then(res => res.data); };


export const getJssqCsLsit = params => { return get(`/JssqCsController/getJssqCsLsit`,{ params: params });};
export const SelectJshz = params => { return get(`/JssqCsController/SelectJshz`,{ params: params });};

export const getJssqJczl = params => { return get(`/JssqCsController/getJssqJczl`,{ params: params });};

export const SaveJsSh = params => { return get(`/JssqCsController/SaveJsSh`,{ params: params });};

export const Tuihui = params => { return get(`/JssqCsController/Tuihui`,{ params: params });};


export const importFile = params => { return postFile(`/JssqController/importFile`,params);};

export const getFile = params => { return postFile(`/JssqCsController/importFile`,params);};
export const getdownload = params => { return postDownloadFile(`/JssqCsController/download`, params); };


export const yszbid = params => { return get(`/JssqController/yszbid`,{ params: params });};
//更改签收状态
export const getQianshou = params => { return post(`/SgxdController/updateCheckTask`, params).then(res => res.data); };

export const jszbid = params => { return get(`/JssqController/jszbid`,{ params: params });};
//回显审核意见
export const getjsshyi = params => { return get(`/JssqCsController/getjsshyi`,{ params: params });};