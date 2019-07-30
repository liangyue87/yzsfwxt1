package com.ljx.hfgsjt.yj.serviceImpl;

import com.ljx.hfgsjt.entity.dto.Sjst.CodeModel;
import com.ljx.hfgsjt.entity.dto.Sjst.ProjectModel;
import com.ljx.hfgsjt.entity.dto.Sjst.XmSb;
import com.ljx.hfgsjt.entity.yj.*;
import com.ljx.hfgsjt.repository.yj.*;
import com.ljx.hfgsjt.yj.dao.BwyjMapper;
import com.ljx.hfgsjt.yj.service.BwyjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BwyjServiceImpl implements BwyjService {

    @Autowired
    private BwyjMapper mapper;
    @Autowired
    private FmRepository repository;
    @Autowired
    private DjzcbRepository dzjcbrepository;
    @Autowired
    private DjzzbRepository dzjzbrepository;
    @Autowired
    private BwyjZbRepository bwyjZbRepository;
    @Autowired
    private BwyjCbRepository bwyjCbRepository;

    @Override
    public int getProjectCount(ProjectModel model) {
        return mapper.getProjectCount(model);
    }

    @Override
    public List<ProjectModel> getProjectList(ProjectModel model) {
        return mapper.getProjectList(model);
    }

    @Override
    public int getFmCount(String xmid) {
        return mapper.getFmCount(xmid);
    }

    @Override
    public List<XmFm> getFmData(String xmid, int startRow, int endRow) {
        return mapper.getFmData(xmid,startRow,endRow);
    }

    @Override
    public void insertFm(XmFm model) {
        repository.save(model);
    }



    @Override
    public int getSbCount(String xmid) {
        return mapper.getSbCount(xmid);
    }

    @Override
    public List<XmSb> getSbData(String xmid, int startRow, int endRow) {
        return mapper.getSbData(xmid,startRow,endRow);
    }

    @Override
    public List<CodeModel> getFmkj() {
        return mapper.getFmkj();
    }

    @Override
    public List<CodeModel> getFmlx() {
        return mapper.getFmlx();
    }

    @Override
    public List<CodeModel> getFmzy() {
        return mapper.getFmzy();
    }

    @Override
    public List<CodeModel> getZdfs() {
        return mapper.getZdfs();
    }

    @Override
    public List<CodeModel> getZdfx() {
        return mapper.getZdfx();
    }

    @Override
    public List<CodeModel> getfjgcz() {
        return mapper.getfjgcz();
    }

    @Override
    public List<CodeModel> getFjgxs() {
        return mapper.getFjgxs();
    }

    @Override
    public List<XmFm> fmpzHandle(String id) {
        return mapper.fmpzHandle(id);
    }

    @Override
    public List<CodeModel> getFgcz() {
        return mapper.getFgcz();
    }

    @Override
    public void fmpzDelete(XmFm model) {
         mapper.fmpzDelete(model);
    }

    @Override
    public int getDzjlbCount(String xmid) {
        return mapper.getDzjlbCount(xmid);
    }

    @Override
    public List<YjDzjzb> getDzjlbData(String xmid, int startRow, int endRow) {
        return mapper.getDzjlbData(xmid,startRow,endRow);
    }

    @Override
    public List<YjDzjzb> getfmdzjData(String id) {
        return mapper.getfmdzjData(id);
    }

    @Override
    public void insertDzjcb(YjDzjcb cbModel) {
        dzjcbrepository.save(cbModel);
    }

    @Override
    public void insertDzj(YjDzjzb model) {
        dzjzbrepository.save(model);
    }

    @Override
    public void dzjHandleDelete(String userid, String id, String zt) {
        mapper.dzjHandleDelete(userid, id ,zt);
    }

    @Override
    public String getNextNode(String activityId, int direction) {
        return mapper.getNextNode(activityId,direction);
    }

    @Override
    public List<YjDzjcb> getCbid(String id) {
        return mapper.getCbid(id);
    }

    @Override
    public List<Map<String, Object>> getrwblr(String xmid) {
        return mapper.getrwblr(xmid);
    }

    @Override
    public void saveBwyjzbModel(YjBwyjzb bwyjzbModel) {
        bwyjZbRepository.save(bwyjzbModel);
    }

    @Override
    public List<String> getDzjid(String xmid) {
        return mapper.getDzjid(xmid);
    }

    @Override
    public void saveBwyjcbModel(YjBwyjcb bwyjcbModel) {
        bwyjCbRepository.save(bwyjcbModel);
    }

    @Override
    public int submitShow(String xmid) {
        return mapper.submitShow(xmid);
    }

    @Override
    public int getQsBwyjlbCount(ProjectModel model) {
        return mapper.getQsBwyjlbCount(model);
    }

    @Override
    public List<ProjectModel> getQsBwyjlbList(ProjectModel model) {
        return mapper.getQsBwyjlbList(model);
    }

    @Override
    public String getxmyjCount(String xmid) {
        return mapper.getxmyjCount(xmid);
    }

}
