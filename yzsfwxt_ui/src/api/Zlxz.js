import {get,post} from '../httpConfig/axioss.js'
import {postDownloadFile} from "../httpConfig/axioss";

/*获取资料列表*/
export const getFileList = params => { return get(`/ZlxzController/getFileList`, { params: params }); };//get请求用此方法
/*下载*/
export const download = params => { return postDownloadFile(`/ZlxzController/download`, params).then(res => res.data); }
