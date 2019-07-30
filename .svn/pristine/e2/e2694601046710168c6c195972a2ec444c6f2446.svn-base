import {get,post} from '../httpConfig/axioss.js'

//let base = 'http://localhost:8080/api/v1.0';
//let base = '';

/*----------------   Role  --------------------*/
export const getRoleList = params => { return get(`/RoleController/getRoleList`,{ params: params });};

export const delRole = params => {return post(`/RoleController/delrole`, params).then(res => res.data);};

export const addRole = params => {return post(`/RoleController/addRole`, params ).then(res => res.data);};

export const editRole = params => {return post(`/RoleController/updateRole`, params ).then(res => res.data);};

export const batchRemoveRole = params => {return post(`/RoleController/batchRemoveRole`, { params: params });};

export const saveMenuForRole = params => {return post(`/RoleController/saveMenuForRole`, params ).then(res => res.data);};

export const getMenuForRole = params => {return get(`/RoleController/getMenuForRole`, { params: params });};

export const getAllMenuList = params => { return get(`/MenuController/getMenuTree`, { params: params }); };

export const getAssignUser = params => { return get(`/RoleController/getAssignUser`, { params: params }); };

export const saveUserForRole = params => {return post(`/RoleController/saveUserForRole`, params ).then(res => res.data);};

export const getUserForRole = params => {return get(`/RoleController/getUserForRole`, { params: params });};