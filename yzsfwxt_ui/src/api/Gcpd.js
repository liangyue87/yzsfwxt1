import {get,post,postFile,postDownloadFile} from '../httpConfig/axioss.js'

/*----------------   工程评定  --------------------*/

/*export const getYswtList = params => { return get(`/YswtController/TestgetYszt`,{params:params}); };*/
/*export const ToYsZpzrNO = params => {return post(`/YswtController/ToYsZpzrNO`, params ).then(res => res.data);};*/

//申请工程合格评定的列表
export const getSqgcHgpdList = params => { return get(`/GcpdController/getSqgcHgpdList`,{params:params}); };
//保存供水工程竣工验收合格证明
export const bc = params => { return get(`/GcpdController/bc`,{params:params}); };
//查看当前数据在数据库是否存在
export const getSfbc = params => { return get(`/GcpdController/getSfbc`,{params:params}); };
//saveImageToDb
export const saveImageToDb = params => {return post(`/GcpdController/saveImageToDb`, params ).then(res => res.data);};
//getPsTotals
export const getPsTotals = params => { return get(`/GcpdController/getPsTotals`,{params:params}); };
//删除高拍仪照片
export const deleteImage = params => { return get(`/GcpdController/deleteImage`,{params:params}); };
//上传文件
export const importFile = params => { return postFile(`/GcpdController/scwj`, params).then(res => res.data); }
/*下载文件*/
export const download = params => { return postDownloadFile(`/GcpdController/download`, params).then(res => res.data); }
//查询上传列表信息
export const getyszl = params => { return get(`/GcpdController/getYshg`,{params:params}); };
//获取上传列表
export const getYszlss = params => { return get(`GcpdController/getYshg`,{params:params}); };
/*下载文件*/
export const downloads = params => { return postDownloadFile(`/GcpdController/download`, params).then(res => res.data); }
/*删除工程评定附件deleteFkFj*/
export const deleteFkFj = params => { return post(`/GcpdController/deleteFkFj`, params).then(res => res.data); }

