import {get,post} from '../httpConfig/axioss.js'
import {postFile} from "../httpConfig/axioss";

//条件查询水表移交列表
export const getSbyjList = params => { return get(`/SbyjController/getSbyjList`,{params:params}); };

//查询安装类型
export const findAzlx = params => { return get(`/SbyjController/findAzlx`,{params:params}); };

//查询安装类型
export const queryYjdbh = params => { return get(`/SbyjController/queryYjdbh`,{params:params}); };

//根据项目ID查询水表移交单列表
export const querySbyjList = params => { return get(`/SbyjController/querySbyjList`,{params:params}); };

//根据项目ID查询水表列表
export const showSbList = params => { return get(`/SbyjController/showSbList`,{params:params}); };

//编辑保存五级计量表
export const editWb = params => { return get(`/SbyjController/editWb`,{params:params}); };

//根据楼栋ID/项目ID查询居民表数据
export const findJmbByLdid = params => { return get(`/SbyjController/findJmbByLdid`,{params:params}); };

//表内编辑
export const elxedit = params => { return post(`/SbyjController/elxedit`, params).then(res => res.data); };

//公共字段批量编辑
export const pledit = params => { return post(`/SbyjController/pledit`, params).then(res => res.data); };

//Excel表格批量编辑
export const importExcel = params => { return postFile(`/SbyjController/importExcel`, params).then(res => res.data); };

//水表勾选移交保存
export const sbyjSubmit = params => { return get(`/SbyjController/sbyjSubmit`,{params:params}); };

//水表勾选移交保存
export const sbyjLdSubmit = params => { return get(`/SbyjController/sbyjLdSubmit`,{params:params}); };

//审核界面水表集合
export const shSbList = params => { return get(`/SbyjController/shSbList`,{params:params}); };

//查询行业分类
export const getHyflCodeData = params => { return get(`/SbyjController/getHyflCodeData`,{params:params}); };

//审核界面水表编辑
export const sheditsb = params => { return post(`/SbyjController/sheditsb`, params).then(res => res.data); };

//审核界面根据楼栋ID/项目ID查询居民表数据
export const shJmbByLdid = params => { return get(`/SbyjController/shJmbByLdid`,{params:params}); };

//审核界面批量编辑水表
export const shpledit = params => { return post(`/SbyjController/shpledit`, params).then(res => res.data); };

//审核水表移交指派区域抄表员
export const shsbyjSubmit = params => { return get(`/SbyjController/shsbyjSubmit`,{params:params}); };

//抄表员界面水表编辑
export const cbyeditsb = params => { return post(`/SbyjController/cbyeditsb`, params).then(res => res.data); };

//抄表员界面批量编辑水表
export const cbypledit = params => { return post(`/SbyjController/cbypledit`, params).then(res => res.data); };

//抄表员水表移交提交
export const cbysbyjSubmit = params => { return get(`/SbyjController/cbysbyjSubmit`,{params:params}); };

//抄表员导入
export const cbysbdr = params => { return get(`/SbyjController/cbysbdr`,{params:params}); };

//查询导出到营收系统数据
export const querydcsb = params => { return get(`/SbyjController/querydcsb`,{params:params}); };

//根据水表厂家编号获得简称
export const querysbcjjcBybh = params => { return post(`/SbyjController/querysbcjjcBybh`, params).then(res => res.data); };

//根据水表口径编号获取名称
export const querysbkjmcBybh = params => { return post(`/SbyjController/querysbkjmcBybh`, params).then(res => res.data); };

//获得移交主表ID
export const getyjzbid = params => { return get(`/SbyjController/getyjzbid`,{params:params}); };

//更改任务签收状态
export const updateCheckTask = params => {return post(`/SgxdController/updateCheckTask`, params ).then(res => res.data);};

//综合查询 收费开票查询
export const querysfkp = params => { return get(`/SbyjController/querysfkp`,{params:params}); };

//综合查询 施工下单查询
export const querysgxd = params => { return get(`/SbyjController/querysgxd`,{params:params}); };

//区域抄表员工商表批量编辑表册号
export const gsplbj = params => { return post(`/SbyjController/gsplbj`, params).then(res => res.data); };