// import axios from 'axios';
// import apiUrl from '../httpConfig/baseUrl'
// axios.defaults.baseURL = apiUrl

import {get,post} from '../httpConfig/axioss.js'
import {postDownloadFile, postFile} from "../httpConfig/axioss";


//查询表位移交列表
export const getBwyjlb = params => { return get(`/BwyjController/getBwyjlb`, { params: params })};
//办理
export const BwyjHandleEdit = params => { return get(`/sjstController/handleEdit`, { params: params })};
////获取阀门
export const getFmData = params => { return get(`/BwyjController/getFmData`, { params: params })};
////阀门信息维护
export const fmxxwh = params => { return get(`/BwyjController/fmxxwh`, { params: params })};
//获取水表
export const getSb = params => { return get(`/BwyjController/getSb`, { params: params })};
//	获取阀门口径
export const getFmkj = params => { return get(`/BwyjController/getFmkj`, { params: params })};
//	获取阀门类型
export const getFmlx = params => { return get(`/BwyjController/getFmlx`, { params: params })};
//	阀门作用
export const getFmzy = params => { return get(`/BwyjController/getFmzy`, { params: params })};
//	转动方式
export const getZdfs = params => { return get(`/BwyjController/getZdfs`, { params: params })};
//	转动方向
export const getZdfx = params => { return get(`/BwyjController/getZdfx`, { params: params })};
//	阀井盖材质
export const getfjgcz = params => { return get(`/BwyjController/getfjgcz`, { params: params })};
//	阀井盖形式
export const getFjgxs = params => { return get(`/BwyjController/getFjgxs`, { params: params })};
//阀杆材质
export const getFgcz = params => { return get(`/BwyjController/getFgcz`, { params: params })};
//编辑阀门
export const fmpzHandle = params => { return get(`/BwyjController/fmpzHandle`, { params: params })};
//删除阀门
export const fmpzDelete = params => { return get(`/BwyjController/fmpzDelete`, { params: params })};

//查询点志记
export const getDzjlb = params => { return get(`/BwyjController/getDzjlb`, { params: params })};
//查询阀门点志记
export const getfmdzjData = params => { return get(`/BwyjController/getfmdzjData`, { params: params })};


//获取消火栓列表
export const getxhsData = params => { return get(`/XhsController/getXhsData`, { params: params })};
//消火栓信息维护
export const xhsxxwh = params => { return get(`/XhsController/xhsxxwh`, { params: params })};
//	获取消火栓信号
export const getxhsxh = params => { return get(`/XhsController/getXhsxh`, { params: params })};
//	获取消火栓安装方式
export const getXhsazfs = params => { return get(`/XhsController/getXhsazfs`, { params: params })};
//编辑消火栓
export const xhspzHandle = params => { return get(`/XhsController/xhspzHandle`, { params: params })};
//删除消火栓
export const xhspzDelete = params => { return get(`/XhsController/xhspzDelete`, { params: params })};
//阀门点志记信息维护
export const fmdzjxxwh = params => { return get(`/BwyjController/fmdzjxxwh`, { params: params })};
//删除点志记
export const dzjHandleDelete = params => { return get(`/BwyjController/dzjHandleDelete`, { params: params })};

//保存上传文件
export const saveDzjfile = params => { return postFile(`/XhsController/saveDzjfile`, params).then(res => res.data); }
//获取消火栓点志记
export const xhsdzjData = params => { return get(`/XhsController/xhsdzjData`, { params: params }); }

/*下载文件*/

export const download = params => { return postDownloadFile(`/sjstController/download`, params); }

//表位移交提交
export const bwyjdzjSubmit = params => { return get(`/BwyjController/bwyjdzjSubmit`, { params: params })};


//获取水表三箱下拉框
export const getOptions = params => { return get(`/SbsxController/getOptions`, { params: params })};
//水表三箱点志记保存
export const getDzjSbsxxx = params => { return get(`/SbsxController/getDzjSbsxxx`, { params: params })};
//水表三箱点志记保存
export const saveDzjsbsx = params => { return get(`/SbsxController/saveDjz`, { params: params })};
//保存上传文件
export const saveDzjSbsxfile = params => { return postFile(`/SbsxController/saveDzjfile`, params).then(res => res.data); }
/*下载文件*/
export const downloadSbsx = params => { return postDownloadFile(`/SbsxController/download`, params).then(res => res.data); }




//获取楼栋列表
export const getldData = params => { return get(`/LdController/getLdData`, { params: params })};
//获取楼栋点志记
export const LddzjData = params => { return get(`/LdController/LddzjData`, { params: params }); }
export const LdhxData = params => { return get(`/LdController/LdhxData`, { params: params }); }
//是否隐藏提交按钮
export const submitShow = params => { return get(`/BwyjController/submitShow`, { params: params }); }


//表位移交签收
export const qsqs= params => { return get(`/sjstController/goSjrwfp`, { params: params }); }
//区所项目
export const getQsBwyjlb= params => { return get(`/BwyjController/getQsBwyjlb`, { params: params }); }
//区所确认
export const qsSubmit= params => { return get(`/BwyjController/qsSubmit`, { params: params }); }
