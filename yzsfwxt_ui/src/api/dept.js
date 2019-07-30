import axios from 'axios';
import apiUrl from '../httpConfig/baseUrl'
axios.defaults.baseURL = apiUrl

import {get,post} from '../httpConfig/axioss.js'

//let base = 'http://localhost:8080/api/v1.0';
//let base = '';

//export const requestLogin = params => { return axios.post(`/MenuController/login`, params).then(res => res.data); };
export const getDeptById = params => { return get(`/DepartmentController/getDepartmentById`, { params: params }); };

export const getAllDept = params => { return get(`/DepartmentController/getDepartmentTree`, { params: params }); };

export const saveDept = params => { return post(`/DepartmentController/saveDepartment`,  params ).then(res => res.data);};

export const deleteDept = params => { return post(`/DepartmentController/deleDepartment`, params ).then(res => res.data);};
