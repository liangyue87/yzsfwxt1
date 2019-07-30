import {get,post} from '../httpConfig/axioss.js'
import {postDownloadFile, postFile} from "../httpConfig/axioss";

export const getZjqtList = params => { return get(`/YsZjqtGetXmlb/getAllXqsl`,{params:params}); };
export const saveYsZjqt = params => { return post(`/YsZjqt/saveYszjqt`,params).then(res =>res.data); };
export const queryBmById = params => { return get(`/YsZjqt/queryBmById`,{params:params}); };

export const getZjqtFkList = params => { return get(`/YsZjqtFk/ZjqtFkList`,{params:params}); };
export const saveYsZjqtFk = params => { return post(`/YsZjqtFk/saveYsZjqtFk`,params).then(res =>res.data); };
export const queryZjqtzbById = params => { return get(`/YsZjqtFk/queryZjqtzbById`,{params:params}); };

export const importFile = params => { return postFile(`/YsZjqtFk/upoadFile`, params).then(res => res.data); };

export const queryZllist = params => { return get(`/YsZjqtFk/queryZllist`,{params:params}); };

export const deleteFjByid = params => { return get(`/YsZjqtFk/deleteFjByid`,{params:params}); };

export const download = params => { return postDownloadFile(`/sjstController/download`, params); };

export const updateCheckTask = params => {return post(`/SgxdController/updateCheckTask`, params ).then(res => res.data);};

export const queryPara = params => { return get(`/YsZjqtFk/queryPara`,{params:params}); };

export const queryParas = params => { return get(`/YsZjqtFk/queryParas`,{params:params}); };
