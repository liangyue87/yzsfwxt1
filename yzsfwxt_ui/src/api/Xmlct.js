import {get,post} from '../httpConfig/axioss.js'
import {postDownloadFile, postFile} from "../httpConfig/axioss";

export const getXmlctDqjd = params => { return get(`/XmlctController/getXmlctDqjd`,{ params: params });};
export const selectXqsl = params => { return get(`/XmlctController/getXqsl`,{ params: params });};