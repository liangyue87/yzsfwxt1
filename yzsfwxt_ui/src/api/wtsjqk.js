import {get,post} from '../httpConfig/axioss.js'


/*export const getWtsjQk = params => {
    return get(`/WtsjqkController/getWtsjQk`, params,).then(res => res.data);*/
export const getWtsjQk = params => { return get(`/WtsjqkController/getWtsjQk`,{ params: params }).then(res => res.data);};
//}