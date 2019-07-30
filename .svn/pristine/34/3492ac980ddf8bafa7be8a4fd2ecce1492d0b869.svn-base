import {get,post,postDownloadFile,postFile} from '../httpConfig/axioss.js'

//获取项目列表
export const getXmlb = params => { return get(`/sjstController/getProjectList`,{ params: params });};
//项目统筹
export const getTcxm = params => { return get(`/sjstController/getTcxm`,{ params: params });};
//项目终止
export const stopProject = params => { return get(`/sjstController/stopProject`,{ params: params });};

export const getQtzl = params => { return get(`/sjstController/getQtzl`,{ params: params });};
export const initTble = params => { return get(`/sjstController/initTble`,{ params: params });};
export const deleteFj = params => { return get(`/sjstController/deleteFj`,{ params: params });};
export const wtsj = params => { return get(`/sjstController/wtsj`,{ params: params });};
export const handleEdit = params => { return get(`/sjstController/handleEdit`,{ params: params });};
export const getzlTypeOptions = params => { return get(`/sjstController/getzlTypeOptions`,{ params: params });};
export const importFile = params => { return postFile(`/sjstController/importFile`,params);};
export const sjqzl = params => { return get(`/sjstController/sjqzl`,{ params: params });};
export const fjzlPreview = params => { return get(`/sjstController/fjzlPreview`,{ params: params });};
export const submitShow = params => { return get(`/sjstController/submitShow`,{ params: params });};


export const getFile = params => { return postFile(`/GwxxController/importFile`,params);};
export const getHsjgLsit = params => { return get(`/GwxxController/getHsjgLsit`,{ params: params });};
export const deleteHszl = params => { return get(`/GwxxController/deleteHszl`,{ params: params });};
//发起官网信息数据
export const findXmxx = params => { return get(`/GwxxController/findXmxx`,{ params: params });};
//发起官网信息数据
export const getGwxxLb = params => { return get(`/GwxxController/getGwxxList`,{ params: params });};
//官网反馈信息列表
export const GetfqlbList = params => { return get(`/GwxxController/GetfqlbList`,{ params: params });};
//会审结果录入数据列表
export const getHsjglr = params => { return get(`/GwxxController/getHsjgList`,{ params: params });};
//查询官网信息调查从表数据
export const getGwxxdcCB  = params => { return get(`/GwxxController/getGwxxdcCB`, { params: params }); };
//新增保存 官网信息调查从表数据
export const saveGwxxdcCB = params => { return post(`/GwxxController/saveGwxxdcCB`, params,).then(res => res.data); };
//删除 官网信息从表数据
export const deleteGwxxFq = params => { return post(`/GwxxController/deleteGwxxFq`, params,).then(res => res.data); };
//保存 官网反馈信息
export const saveGwxxFQ = params => { return post(`/GwxxController/saveGwxxFQ`, params,).then(res => res.data); };
//保存退回官网反馈数据
export const saveReverSion = params => { return post(`/GwxxController/saveReverSion`, params,).then(res => res.data); };
//保存会审记录数据
export const saveHsjglr = params => { return post(`/GwxxController/saveHsjglr`, params).then(res => res.data); };
//回显会审记录数据
export const getHsnr = params => { return post(`/GwxxController/getHsnr`, params).then(res => res.data); };
//保存发起官网信息记录
export const saveFqgwxxTask = params => { return post(`/GwxxController/saveFqgwxxTask`, params).then(res => res.data); };

export const selectXzqy = params => { return post(`/GwxxController/selectXzqy`, params).then(res => res.data); };

/*获取管网反馈的*/
export const getSjstGwfk = params => { return get(`/GwxxController/getSjstGwfk`, { params: params }); };

//export const downloadFile = params => { return post(`/sjstController/download`, params,).then(res => res.data); };//post请求用此方法

//export const downloadFile = params => { return post(`/sjstController/download`, params,).then(res => res.data); };//post请求用此方法
//-----------------------设计任务分派

export const getSjrwfpXmlb = params => { return get(`/sjstController/getSjrwfpXmlb`,{ params: params });};
export const goSjrwfp = params => { return get(`/sjstController/goSjrwfp`,{ params: params });};
export const getZlfl = params => { return get(`/sjstController/getZlfl`,{ params: params });};
export const goDetail = params => { return get(`/sjstController/goDetail`,{ params: params });};
export const toDetial = params => { return get(`/sjstController/toDetial`,{ params: params });};
export const getZpry = params => { return get(`/sjstController/getZpry`, { params: params }); };
export const sjrwfpSubmit = params => { return get(`/sjstController/sjrwfpSubmit`, { params: params }); };
export const getZlzl = params => { return get(`/sjstController/getZlzl`, { params: params }); };
export const download_sjst = params => { return postDownloadFile(`/sjstController/download`, params); };
export const initSjsx = params => { return get(`/sjstController/initSjsx`, { params: params }); };
export const rwtxfsdx = params => { return get(`/sjstController/rwtxfsdx`, { params: params }); };


//-----------------------------------------设计人员上传设计成果
//是否显示转单按钮
export const buttonshow = params => { return get(`/sjstController/buttonshow`,{ params: params });};
//获取转单人员
export const getzdry = params => { return get(`/sjstController/getzdry`,{ params: params });};
//提交转单人员
export const zdsubmit = params => { return get(`/sjstController/zdsubmit`,{ params: params });};
//申请延期信息
export const sqyqxx = params => { return get(`/sjstController/sqyqxx`,{ params: params });};
//申请延期
export const sqyq = params => { return get(`/sjstController/sqyq`,{ params: params });};
//是否显示延期按钮
export const yqbuttonshow = params => { return get(`/sjstController/yqbuttonshow`,{ params: params });};

export const importFile_delay = params => { return postFile(`/sjstController/importFile_delay`,params);};
export const toDetial_delay = params => { return get(`/sjstController/toDetial_delay`,{ params: params });};
export const deleteDelayFj = params => { return get(`/sjstController/deleteDelayFj`,{ params: params });};
export const stcgsc_delay = params => { return get(`/sjstController/stcgsc_delay`,{ params: params });};


export const getSjrySjrwXmlb = params => { return get(`/sjstController/getSjrySjrwXmlb`,{ params: params });};
export const tyxxwh = params => { return get(`/sjstController/tyxxwh`,{ params: params });};
export const initTYTable = params => { return get(`/sjstController/initTYTable`,{ params: params });};
export const initDXSTable = params => { return get(`/sjstController/initDXSTable`,{ params: params });};
export const dxsxxwh = params => { return get(`/sjstController/dxsxxwh`,{ params: params });};
export const initBFTable = params => { return get(`/sjstController/initBFTable`,{ params: params });};
export const bfxxwh = params => { return get(`/sjstController/bfxxwh`,{ params: params });};
/*初始化泵房选择器*/
export const initBFOption = params => { return get(`/sjstController/initBFOption`,{ params: params });};
/*初始化水箱数据*/
export const initSXTable = params => { return get(`/sjstController/initSXTable`,{ params: params });};
/*水箱信息维护*/
export const sxxxwh = params => { return get(`/sjstController/sxxxwh`,{ params: params });};
/*管网信息维护*/
export const gwxxwh = params => { return post(`/sjstController/gwxxwh`,params);};
/*管网字段查询*/
export const initParams = params => { return get(`/sjstController/initParams`,{ params: params });};
/*初始化管网列表*/
export const initGWTable = params => { return get(`/sjstController/initGWTable`,{ params: params });};
/*初始化管网列表*/
export const GWHandleEdit = params => { return get(`/sjstController/GWHandleEdit`,{ params: params });};
/*初始化管网列表*/
export const GWHandleDelete = params => { return get(`/sjstController/GWHandleDelete`,{ params: params });};
/*初始化管网列表*/
export const GLGHandleDelete = params => { return get(`/sjstController/GLGHandleDelete`,{ params: params });};

/*初始化楼栋table*/
export const initLDTable = params => { return get(`/sjstController/initLDTable`,{ params: params });};
/*初始化庭院选择器*/
export const initLYOption = params => { return get(`/sjstController/initLYOption`,{ params: params });};
/*楼栋信息维护*/
export const ldxxwh = params => { return get(`/sjstController/ldxxwh`,{ params: params });};
//分区信息
export const getFQData = params => { return get(`/sjstController/getFQData`,{ params: params });};
//分区信息维护
export const fqxxwh = params => { return post(`/sjstController/fqxxwh`, params).then(res => res.data); };//post请求用此方法
//项目申请类型
export const xmsqlx = params => { return get(`/sjstController/xmsqlx`,{ params: params });};
//新建小区楼栋
export const xjxqld = params => { return get(`/sjstController/xjxqld`,{ params: params });};

//-----------物料清单配置
//物料清单添加
export const wlHandleAdd = params => { return get(`/sjstController/wlHandleAdd`,{ params: params });};
//项目物料清单查询
export const pickHandle = params => { return get(`/sjstController/pickHandle`,{ params: params });};
//总物料清单查询
export const getWlData = params => { return get(`/sjstController/getWlData`,{ params: params });};
//项目物料清单编辑
export const wlqdHandleEdit = params => { return get(`/sjstController/wlqdHandleEdit`,{ params: params });};
//项目物料删除
export const wlqdHandleDelete = params => { return get(`/sjstController/wlqdHandleDelete`,{ params: params });};
//初始化审图成果列表
export const initStcgTable = params => { return get(`/sjstController/initStcgTable`,{ params: params });};
//审图成果详情
export const stManage = params => { return get(`/sjstController/stManage`,{ params: params });};
//删除审图附件
export const stDelete = params => { return get(`/sjstController/stDelete`,{ params: params });};
//预览图片
export const stPreview = params => { return get(`/sjstController/stPreview`,{ params: params });};
export const stcgsc = params => { return get(`/sjstController/stcgsc`,{ params: params });};
//获取水表口径
export const getKjData = params => { return get(`/sjstController/getKjData`,{ params: params });};
//根据类型获取XTGL_CODE表中数据
export const getCodeData = params => { return get(`/sjstController/getCodeData`,{ params: params });};

//根据类型获取XTGL_CODE表中数据
export const getHyflCodeData = params => { return get(`/sjstController/getHyflCodeData`,{ params: params });};
//用水性质
export const getYsxzData = params => { return get(`/sjstController/getYsxzData`,{ params: params });};
//行业分类
export const getHyflData = params => { return get(`/sjstController/getHyflData`,{ params: params });};

//获取开户总行
export const getKhzhData = params => { return get(`/sjstController/getKhzhData`,{ params: params });};
//获取开户银行
export const getKhyhData = params => { return get(`/sjstController/getKhyhData`,{ params: params });};
//地表信息维护
// export const dbxq = params => { return get(`/sjstController/dbxq`,{ params: params });};
export const dbxq = params => { return post(`/sjstController/dbxq`, params,).then(res => res.data); };
//初始化地表信息
export const initDbpzTable = params => { return get(`/sjstController/initDbpzTable`,{ params: params });};
//删除地表信息
export const dbpzDelete = params => { return get(`/sjstController/dbpzDelete`,{ params: params });};
//初始化左边树
export const initLdhb = params => { return get(`/sjstController/initLdhb`,{ params: params });};
export const dbpzAdd = params => { return get(`/sjstController/dbpzAdd`,{ params: params });};
//修改门牌号
export const mphHandel = params => { return get(`/sjstController/mphHandel`,{ params: params });};
//上传设计成果提交
export const scsjcgSubmit = params => { return get(`/sjstController/scsjcgSubmit`,{ params: params });};
//退回
export const sjcgscBack = params => { return get(`/sjstController/sjcgscBack`,{ params: params });};
//红黄绿灯
export const initHhld = params => { return get(`/sjstController/initHhld`,{ params: params });};
//出户改造户表保存
export const drxq = params => { return post(`/sjstController/drxq`, params,).then(res => res.data); };
//查询所有楼栋ID下的水表
export const queryAllLdsb = params => { return get(`/sjstController/queryAllLdsb`,{ params: params });};
//Excel表格批量编辑
export const importExcel = params => { return postFile(`/sjstController/importExcel`, params).then(res => res.data); };
//设计补充
export const saveXmjbxx = params => { return get(`/sjstController/saveXmjbxx`,{ params: params });};
//获取审批项目列表
export const getspXmlb = params => { return get(`/sjstController/getspXmlb`,{ params: params });};
//项目审批
export const passdelay = params => { return get(`/sjstController/passdelay`,{ params: params });};
//项目基本信息
export const getXmjbxxById = params => { return get(`/sjstController/getXmjbxxById`,{ params: params });};
//项目需求受理资料上传
export const importFile_xqsl = params => { return postFile(`/sjstController/importFile_xqsl`,params);};
//项目需求受理资料上传
export const saveImageToFj_xqsl = params => { return get(`/sjstController/saveImageToFj_xqsl`,{ params: params });};
//需求受理资料详情
export const toDetial_xqsl = params => { return get(`/sjstController/toDetial_xqsl`,{ params: params });};
//需求受理资料详情
export const deleteFj_xqsl = params => { return get(`/sjstController/deleteFj_xqsl`,{ params: params });};
//项目基本信息保存
export const handleAdd = params => { return get(`/sjstController/handleAdd`,{ params: params });};

//更改签收状态
export const getQianshou = params => { return post(`/SgxdController/updateCheckTask`, params).then(res => res.data); };
//延期申请短信
export const fsdx = params => { return get(`/sjstController/fsdx`, { params: params }); };
//延期审批短信
export const spfsdx = params => { return get(`/sjstController/spfsdx`, { params: params }); };
//获取项目信息
export const getXmsqlx = params => { return get(`/sjstController/getXmsqlx`, { params: params }); };
