import {get,post,postFile,postDownloadFile} from '../httpConfig/axioss.js'


/*----------------   预算委托  --------------------*/

//表1
export const getYswtList = params => { return get(`/YswtController/TestgetYszt`,{params:params}); };
//表2
export const getTable = params => { return post(`/YswtController/getTable`, params ).then(res => res.data);};
//启动流程
export const startDelegate = params => { return get(`/YswtController/startDelegate`,{params:params}); };
//退回
export const ToBackward = params => { return post(`/YswtController/ToBackward` , params ).then(res => res.data);};
//获取预算专人
export const getYsZy = params => { return get(`/YswtController/getYsZy`,{params:params}); };
//获取预算专人
export const getYszyById = params => { return get(`/YswtController/getYszyById`,{params:params}); };
//获取预算专人的项目信息
export const getYszyxmById = params => { return get(`/YswtController/getYszyxmById`,{params:params}); };
//发起指派
export const ToYsZpzr = params => {return get(`/YswtController/ToYsZpzr`,{params:params});};
//查询预算项
export const getYsx = params => {return get(`/YswtController/getYsx`,{params:params});};
//回显已经保存的预算数据
/*export const ysbg = params => {return get(`/YswtController/ysbg`,{params:params});};*/
export const ysbg = params => { return post(`/YswtController/ysbg`, params ).then(res => res.data);};
//预算编制
export const setYsbz = params => {return post(`/YswtController/setYsbz`, params ).then(res => res.data);};
//保存编制
export const saveYsbz = params => {return post(`/YswtController/saveYsbz`, params ).then(res => res.data);};
//预算审批（审批通过）
export const ToYsZpzrOK = params => {return post(`/YswtController/ToYsZpzrOK`, params ).then(res => res.data);};
//预算审批（审批不通过）
export const ToYsZpzrNO = params => {return post(`/YswtController/ToYsZpzrNO`, params ).then(res => res.data);};
//签收（关闭流程）
export const Qs = params => { return get(`/YswtController/Qs`,{params:params}); };
//获取上传列表
export const getyszl = params => { return get(`/YswtController/getYsZlxx`,{params:params}); };
//上传文件
export const importFile = params => { return postFile(`/YswtController/scwj`, params).then(res => res.data); }
//上传文件（签收用户确认凭证）
export const importYhpz = params => { return postFile(`/YswtController/importYhpz`, params).then(res => res.data); }
/*上传用户签字盖章的电子文件，供预算科预算参考*/
export const importFileQzgz = params => { return postFile(`/YswtController/importFileQzgz`, params).then(res => res.data); }
/*下载文件*/
export const download = params => { return postDownloadFile(`/YswtController/download`, params).then(res => res.data); }
//获取预算总金额
export const getyYsjezh = params => { return get(`/YswtController/getYSJEZH`,{params:params}); };
//获取上传列表
export const getYszls = params => { return get(`/YswtController/getYsZlxx`,{params:params}); };
/*下载文件*/
export const downloads = params => { return postDownloadFile(`/YswtController/download`, params).then(res => res.data); }
/*删除预算编制附件deleteFkFj*/
export const deleteFkFj = params => { return post(`/YswtController/deleteFkFj`, params).then(res => res.data); }
//删除附件（用户确认凭证）
export const deleteYhqrfj = params => { return post(`/YswtController/deleteYhqrfj`, params).then(res => res.data); }
/*获取预算说明（预算人员备注）*/
export const getYssm = params => { return post(`/YswtController/getYssm`, params).then(res => res.data); }
//查询安装类型
export const findAzlx = params => { return get(`/YswtController/findAzlx`,{params:params}); };
//获取设计审图主表id
export const getsjstrzwid = params => { return post(`/YswtController/getsjstrzwid`, params).then(res => res.data); }
//更新签收状态
export const updateQszt = params =>{return post(`/YswtController/updateQszt`, params).then(res => res.data); }
//获取预算委托时上传附件
export  const getYszlyswt = params =>{return post(`/YswtController/getYszl`,params).then(res =>res.data);}
//获取预算委托时上传附件(用户确认上传凭证)
export  const getYszlYhpz = params =>{return post(`/YswtController/getYszlYhpz`,params).then(res =>res.data);}
//查询项目基本信息
export  const getYsbc = params =>{return post(`/YswtController/getYsbc`,params).then(res =>res.data);}
//初始化前期预算资料
export  const getEarly_stage = params =>{return post(`/YswtController/getEarly_stage`,params).then(res =>res.data);}
//初始化预算成果资料
export  const getLate = params =>{return post(`/YswtController/getLate`,params).then(res =>res.data);}
/*会审结果基本信息*/
export  const gethsjljbxx = params =>{return post(`/YswtController/gethsjljbxx`,params).then(res =>res.data);}
/*查询项目是否创建预算任务主表*/
export  const getYszbidOrGuid = params =>{return post(`/YswtController/getYszbidOrGuid`,params).then(res =>res.data);}
/*预算补充保存*/
export  const saveYsbc = params =>{return post(`/YswtController/saveYsbc`,params).then(res =>res.data);}




/*----------------   综合查询  --------------------*/
//初始化综合查询（红黄绿灯列表）
export  const getHhldcx = params =>{return post(`/YswtController/getHhldcx`,params).then(res =>res.data);}
//初始化综合查询（预算编制列表）
export  const getYsbzcxList = params =>{return post(`/YswtController/getYsbzcxList`,params).then(res =>res.data);}



/*----------------   项目管理（项目统筹）  --------------------*/
export  const newYsrwzb = params =>{return post(`/YswtController/newYsrwzb`,params).then(res =>res.data);}



/*----------------   项目全景图（预算编制跳转）  --------------------*/
//预算编制跳转
export  const getXxzl = params =>{return post(`/YswtController/getXxzl`,params).then(res =>res.data);}







