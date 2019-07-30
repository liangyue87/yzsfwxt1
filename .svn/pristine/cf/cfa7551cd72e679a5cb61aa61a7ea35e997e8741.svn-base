import {get,post} from '../httpConfig/axioss.js'

//let base = 'http://localhost:8080/api/v1.0';
//let base = '';

/*---------------- getSbcjList  Sbcj  --------------------*/
export const getSbcjLists = params => { return get(`/SbcjController/getSbcjList`, { params: params });};

export const deleSbcj = params => {return post(`/SbcjController/deleSbcj`, params ).then(res => res.data);};

export const addSbcj = params => {return post(`/SbcjController/addSbcj`, params ).then(res => res.data);};

export const updateSbcj = params => {return post(`/SbcjController/updateSbcj`, params ).then(res => res.data);};

export const batchRemoveSbcj = params => {return post(`${base}/SbcjController/batchRemoveSbcj`, { params: params });};
