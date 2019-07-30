import {get,post} from '../httpConfig/axioss.js'


/*export const getWtsjQk = params => {
    return get(`/WtsjqkController/getWtsjQk`, params,).then(res => res.data);*/
export const getYjqklb= params => { return get(`/YjqklbController/getYjqklb`,{ params: params }).then(res => res.data);};
//}