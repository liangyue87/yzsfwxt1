// import axios from 'axios';
// import apiUrl from '../httpConfig/baseUrl'
// axios.defaults.baseURL = apiUrl

import {get,post} from '../httpConfig/axioss.js'


//let base = 'http://localhost:8080/api/v1.0';
//let base = '';
/*----------------   Workday  --------------------*/
export const getDaysList = params => { return get(`/WorkdayController/getDaysList`, { params: params }); };//get

export const getWorkTime = params => { return get(`/WorkdayController/getWorkTime`, { params: params }); };

export const deleteDays = params => { return post(`/WorkdayController/deleteDays`, params).then(res => res.data); };//post

export const saveDays = params => { return post(`/WorkdayController/saveDays`, params).then(res => res.data); };

export const saveTime = params => { return post(`/WorkdayController/saveTime`, params).then(res => res.data); };

export const deleteTime = params => { return post(`/WorkdayController/deleteTime`, params).then(res => res.data); };
