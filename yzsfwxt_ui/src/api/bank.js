import axios from 'axios';
import apiUrl from '../httpConfig/baseUrl'
axios.defaults.baseURL = apiUrl
import {get,post} from '../httpConfig/axioss.js'


export const getBankList = params => { return get(`/BankController/getBankList`, { params: params }); };

export const getBankNo = params => { return get(`/BankController/getBankNo`, { params: params }); };

export const remove = params => { return post(`/BankController/deleteBankByTid`, params).then(res => res.data); };

export const batchRemoveBank = params => { return post(`/BankController/batchRemoveBank`, params).then(res => res.data); };

export const addBank = params => { return post(`/BankController/saveBank`, params).then(res => res.data); };

export const editBank = params => { return post(`/BankController/editBank`, params).then(res => res.data); };
