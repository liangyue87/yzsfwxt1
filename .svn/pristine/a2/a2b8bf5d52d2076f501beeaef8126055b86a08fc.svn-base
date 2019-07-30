import {get,post,postFile,postDownloadFile} from '../httpConfig/axioss.js'

//let base = 'http://localhost:8080/api/v1.0';
//let base = '';
/*----------------   User  --------------------*/

/*预览图片*/
export const showImage = params => { return get(`/XkylxController/showImage`, { params: params }); };
/*拍摄上传图片*/
/*export const uploadCream = params => { return post(`/XkylxController/uploadCream`, params).then(res => res.data); }*/
/*获取系统的绝对路径*/
/*export const getPath = params => { return get(`/XkylxController/getPath`, { params: params }); };*/
/*获取勘查任务*/
export const getKcrw = params => { return get(`/XkylxController/getXckcTask`, { params: params }); };
/*获取项目信息（未立项则获取申请单信息）*/
export const findXmxx = params => { return get(`/XkylxController/getXmxx`, { params: params }); };
/*获取勘查信息*/
export const ShowXckcxx = params => { return get(`/XkylxController/ShowXckcxx`, { params: params }); };
/*修改勘查时间*/
export const saveKcsj = params => { return post(`/XkylxController/saveKcsj`, params).then(res => res.data); };
/*终止勘查任务*/
export const abandonKcrw = params => { return post(`/XkylxController/abandonKcrw`, params).then(res => res.data); };
/*export const setXmidToSession = params => { return post(`/XkylxController/setXmidToSession`, params).then(res => res.data); };*/
/*保存勘查主表信息*/
export const saveXckcRwzb = params => { return post(`/XkylxController/saveXckcRwzb`, params).then(res => res.data); };
/*保存指派*/
export const saveTaskZp = params => { return post(`/XkylxController/saveTaskZp`, params).then(res => res.data); };
/*获取指派人员*/
export const getZpry = params => { return get(`/XkylxController/getZpry`, { params: params }); };
/*获取指派任务*/
export const getXckcZpTask = params => { return get(`/XkylxController/getXckcZpTask`, { params: params }); };
/*获取勘查反馈任务*/
export const getKcrwFk = params => { return get(`/XkylxController/getKcrwFk`, { params: params }); };
/*保存勘查反馈信息*/
export const saveXckcFk = params => { return post(`/XkylxController/saveXckcFk`, params).then(res => res.data); };
/*获取区所反馈信息列表*/
export const getKcfkRowQs = params => { return get(`/XkylxController/getKcfkRowQs`, { params: params }); };
/*保存区所反馈信息*/
export const saveKcfkRowQs = params => { return post(`/XkylxController/saveKcfkRowQs`, params).then(res => res.data); };
/*获取区所反馈表单checkbox数据*/
export const getFkQsCode = params => { return get(`/XkylxController/getFkQsCode`, { params: params }); };
/*删除区所反馈信息*/
export const deleteKcfkRowQs = params => { return post(`/XkylxController/deleteKcfkRowQs`, params).then(res => res.data); };
/*获取勘查评价列表*/
export const getKcfkPj = params => { return get(`/XkylxController/getKcfkPj`, { params: params }); };
/*获取勘查反馈的部门*/
export const getFkpjKcbm = params => { return get(`/XkylxController/getFkpjKcbm`, { params: params }); };
/*获取勘查反馈的部门*/
export const getFkpjKcbm_sjst = params => { return get(`/XkylxController/getFkpjKcbm_sjst`, { params: params }); };
/*获取勘查结果用户确认列表*/
export const getKcfkYhqr = params => { return get(`/XkylxController/getKcfkYhqr`, { params: params }); };
/*保存勘查评价*/
export const saveXckcPj = params => { return post(`/XkylxController/saveXckcPj`, params).then(res => res.data); };
/*保存勘查结果客户确认*/
export const saveXckcJgqr = params => { return post(`/XkylxController/saveXckcJgqr`, params).then(res => res.data); };
/*获取立项列表*/
export const getLxlb = params => { return get(`/XkylxController/getLxlb`, { params: params }); };
/*获取申请信息*/
export const getSsbxx = params => { return get(`/XkylxController/getSsbxx`, { params: params }); };
/*获取受理信息*/
export const getSlxx = params => { return get(`/XkylxController/getSlxx`, { params: params }); };
/*获取下拉框的数据*/
export const initSqbCheckbox = params => { return get(`/XkylxController/initSqbCheckbox`, { params: params }); }
/*获取主项目信息列表*/
export const getCheckZxm = params => { return get(`/XkylxController/getCheckZxm`, { params: params }); };
/*保存立项信息*/
export const saveLxxx = params => { return post(`/XkylxController/saveLxxx`, params).then(res => res.data); }
/*更改任务签收状态*/
export const updateCheckTask = params => { return post(`/XkylxController/updateCheckTask`, params).then(res => res.data); }
/*上传文件*/
export const importFile = params => { return postFile(`/XkylxController/importFile`, params).then(res => res.data); }

/*通过xmid获取xmbh   用于设计院勘查反馈高拍仪上传文件时路径*/
export const getXmbhByXmid = params => { return get(`/XkylxController/getXmbhByXmid`, { params: params }); };
/*设计院上传文件*/
export const importFile_sjy = params => { return postFile(`/XkylxController/importFile_sjy`, params).then(res => res.data); }
export const importFile_sgdw = params => { return postFile(`/XkylxController/importFile_sgdw`, params).then(res => res.data); }

/*下载文件*/
export const download = params => { return postDownloadFile(`/XkylxController/download`, params).then(res => res.data); }

export const getKczbxx = params => { return get(`/XkylxController/getKczbxx`, { params: params }); };
/*获取上传的文件getFjData*/
export const getFkFjData = params => { return get(`/XkylxController/getFkFjData`, { params: params }); };
/*设计反馈高拍仪拍摄保存文件路径到附件表*/
export const camera_sjy = params => { return post(`/XkylxController/camera_sjy`, params).then(res => res.data); }
/*获取上传的文件getFjData*/
export const getFkFjData_sjy = params => { return get(`/XkylxController/getFkFjData_sjy`, { params: params }); };
export const getFkFjData_sgdw = params => { return get(`/XkylxController/getFkFjData_sgdw`, { params: params }); };

export const toDetial_sjy = params => { return get(`/XkylxController/toDetial_sjy`, { params: params }); };
export const toDetial_sgdw = params => { return get(`/XkylxController/toDetial_sgdw`, { params: params }); };

/*删除反馈时附件deleteFkFj*/
export const deleteFkFj = params => { return post(`/XkylxController/deleteFkFj`, params).then(res => res.data); }

export const getPjFjData = params => { return get(`/XkylxController/getPjFjData`, { params: params }); };

//高拍仪拍摄图片路径保存到数据库
export const saveImagePath = params => { return post(`/XkylxController/saveImagePath`, params).then(res => res.data); }
/*获取立项时需求受理阶段的附件类型*/
export const getZlzl = params => { return get(`/XkylxController/getZlzl`, { params: params }); };
/*获取附件*/
export const toDetial = params => { return get(`/XkylxController/toDetial`, { params: params }); };
//下载Word
export const downWord = params => { return post(`/XkylxController/downWord`, params).then(res => res.data); }

