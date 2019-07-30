package com.ljx.hfgsjt.xkybz.serviceImpl;

import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.BaseEntity;
import com.ljx.hfgsjt.entity.dto.CodeEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.CodeModel;
import com.ljx.hfgsjt.entity.dto.Sjst.SjstYhzl;
import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.dto.xkybz.*;
import com.ljx.hfgsjt.entity.xqsl.GetXqslEntity;
import com.ljx.hfgsjt.entity.xqsl.YwslChgzsqbEntity;
import com.ljx.hfgsjt.entity.xqsl.YwslGshbsqbEntity;
import com.ljx.hfgsjt.entity.xqsl.YwslXjxqsqbEntity;
import com.ljx.hfgsjt.entity.yj.GetXmlbSbyjEntity;
import com.ljx.hfgsjt.repository.XmInstanceRepository;
import com.ljx.hfgsjt.repository.activity.*;
import com.ljx.hfgsjt.repository.message.DxDfsRepository;
import com.ljx.hfgsjt.repository.xkylx.XckcFkQsRepository;
import com.ljx.hfgsjt.repository.xkylx.XckcFkSgdwRepository;
import com.ljx.hfgsjt.repository.xkylx.XckcRwcbRepository;
import com.ljx.hfgsjt.repository.xkylx.XckcRwzbRepository;
import com.ljx.hfgsjt.repository.yj.GetXmlbSbyjRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.ljx.hfgsjt.xkybz.service.*;
import com.ljx.hfgsjt.xkybz.dao.*;

import java.util.List;
import java.util.Map;

@Service
public class XkylxServiceImpl implements XkylxService{

    @Autowired
    private WfRuInstRepository instRepository;
    @Autowired
    private WfHiInstRepository hiInstRepository;
    @Autowired
    private WfRuInstContentRepository instContentRepository;
    @Autowired
    private WfDfActivityRepository activityRepository;
    @Autowired
    private WfReNodeRepository nodeRepository;
    @Autowired
    private WfTaskRepository taskRepository;
    @Autowired
    private WfTaskHistoryRepository taskHistoryRepository;
    @Autowired
    private WfHiInstContentRepository hiInstContentRepository;
    @Autowired
    private XckcRwzbRepository rwzbRepository;
    @Autowired
    private XmInstanceRepository xmInstanceRepository;
    /*@Autowired
    private kcrwfqViewRepository kcrwfqViewRepository;*/
    @Autowired
    private XckcRwzbRepository xckcRwzbRepository;
    @Autowired
    private XckcRwcbRepository xckcRwcbRepository;
    @Autowired
    private XckcFkQsRepository xckcFkQsRepository;
    @Autowired
    private XckcFkSgdwRepository xckcFkSgdwRepository;
    @Autowired
    private DxDfsRepository dxDfsRepository;

    @Autowired
    private XkylxMapper mapper;

    @Autowired
    private GetXmlbSbyjRepository getXmlbSbyjRepository;



    @Override
    public void saveXmXckcRela(Map<String, Object> xmXckcRela) {
        mapper.saveXmXckcRela(xmXckcRela);
    }

    @Override
    public void saveFj(Map map) {
        mapper.saveFj(map);
    }

    @Override
    public GetXckcxxView getXmxxFromFq(String id) {
        return mapper.getXmxxFromFq(id);
    }

    @Override
    public GetXckcxxView getXmxxFromSqd(String id) {
        return mapper.getXmxxFromSqd(id);
    }

    @Override
    public GetXckcxxView getXckcxxByView(String id) {
        return mapper.getXckcxxByView(id);
    }

    @Override
    public String getQszprByQybm(String qybm) {
        return mapper.getQszprByQybm(qybm);
    }

    @Override
    public String getQsryRoleIdByQybm(String qybm) {
        return mapper.getQsryRoleIdByQybm(qybm);
    }

    @Override
    public Page<WfReNodeEntity> finAllWfReNode(Specification<WfReNodeEntity> example, Pageable pageable) {
        return nodeRepository.findAll(example,pageable);
    }

    @Override
    public void saveWfRuInst(WfRuInstEntity e) {
        instRepository.save(e);
    }

    @Override
    public void saveWfHiRuInst(WfHiInstEntity e) {
        hiInstRepository.save(e);
    }

    @Override
    public void saveWfTaskHistory(WfTaskHistoryEntity e) {
        taskHistoryRepository.save(e);
    }

    @Override
    public List<WfTaskHistoryEntity> getAllTaskHistory(String procInstId) {
        return mapper.getAllTaskHistory(procInstId);
    }

    @Override
    public void saveWfTask(WfTaskEntity e) {
        taskRepository.save(e);
    }

    @Override
    public void saveWfRuInstContent(WfRuInstContentEntity e) {
        instContentRepository.save(e);
    }

    @Override
    public WfDfActivityEntity findByActivityid(String activityid) {
        return activityRepository.findByActivityid(activityid);
    }

    @Override
    public List<WfReNodeEntity> findByActivityidAndDirection(String activityid, String direction) {
        return nodeRepository.findByActivityidAndDirection(activityid,direction);
    }

    @Override
    public WfRuInstEntity getWfRuInstByXmidAndPdefid(String activityid,String xmid) {
        return mapper.getWfRuInstByXmidAndPdefid(activityid,xmid);
    }

    @Override
    public List<WfTaskEntity> getTaskAllByInstId(String instid) {
        return mapper.getTaskAllByInstId(instid);
    }

    @Override
    public WfTaskEntity getTaskByInstId(String instid, String nodekey, String attrvalue) {
        return mapper.getTaskByInstId(instid,nodekey,attrvalue);
    }

    @Override
    public WfTaskEntity getWfTaskById(String taskid) {
        return taskRepository.getWfTaskById(taskid);
    }

    @Override
    public void deleteTaskById(String taskid) {
        taskRepository.deleteById(taskid);
    }

    @Override
    public void deleteInstById(String instid) {
        instRepository.deleteById(instid);
    }

    @Override
    public List<WfRuInstContentEntity> getWfRuInstContentByInstId(String id) {
        return mapper.getWfRuInstContentByInstId(id);
    }

    @Override
    public void deleteWfRuInstContentById(String id) {
        instContentRepository.deleteById(id);
    }

    @Override
    public void saveWfHiInstContent(WfHiInstContentEntity wfHiInstContentEntity) {
        hiInstContentRepository.save(wfHiInstContentEntity);
    }


    @Override
    public List<KcrwfqView> getKcrwfq(KcrwfqView kcrwfqView) {
        return mapper.getKcrwfq(kcrwfqView);
    }

    @Override
    public Integer getKcrwfqTotal(KcrwfqView kcrwfqView) {
        return mapper.getKcrwfqTotal(kcrwfqView);
    }

    @Override
    public XmInstanceEntity getXmxx(String xmid) {
        return xmInstanceRepository.findByXmid(xmid);
    }

    @Override
    public XmInstanceEntity getXmxxById(String xmid) {
        return mapper.getXmxxById(xmid);
    }

    @Override
    public void saveXmInstance(XmInstanceEntity m) {
        xmInstanceRepository.save(m);
    }

    @Override
    public void saveXckcRwzb(XckcRwzbEntity xckcRwzbEntity) {
        xckcRwzbRepository.save(xckcRwzbEntity);
    }

    @Override
    public void saveXckcRwcb(XckcRwcbEntity xckcRwcbEntity) {
        xckcRwcbRepository.save(xckcRwcbEntity);
    }

    @Override
    public List<Map<String, Object>> getZpry(String roleid) {
        return mapper.getZpry(roleid);
    }

    @Override
    public List<GetXckcxxView> getXckcZpTask(GetXckcxxView getXckcxxView) {
        return mapper.getXckcZpTask(getXckcxxView);
    }

    @Override
    public Integer getXckcZpTotal(GetXckcxxView getXckcxxView) {
        return mapper.getXckcZpTotal(getXckcxxView);
    }

    @Override
    public XckcRwcbEntity getXckcRwcb(String xmid, String type,String kczt) {
        return mapper.getXckcRwcb(xmid,type,kczt);
    }

    @Override
    public List<XckcRwcbEntity> findAllXckcRwcbBySffk(String xmid, String sffk) {
        return mapper.findAllXckcRwcbBySffk(xmid,sffk);
    }

    @Override
    public List<GetXckcxxView> getKcrwFk(String kcbm) {
        return mapper.getKcrwFk(kcbm);
    }

    @Override
    public void saveXckcFkQs(XckcFkQsEntity xckcFkQsEntity) {
        xckcFkQsRepository.save(xckcFkQsEntity);
    }

    @Override
    public void saveXckcFkSgdw(XckcFkSgdwEntity xckcFkSgdwEntity) {
        xckcFkSgdwRepository.save(xckcFkSgdwEntity);
    }

    @Override
    public List<XckcFkQsEntity> getKcfkQRowQs(String kcrwcbid) {
        return xckcFkQsRepository.findByKcrwcbid(kcrwcbid);
    }

    @Override
    public List<XckcFkQsEntity> getQsfk(String kcrwcbid, String dcnr) {
        return mapper.getQsfk(kcrwcbid,dcnr);
    }

    @Override
    public void deleteKcfkRowQs(String id) {
        xckcFkQsRepository.deleteById(id);
    }

    @Override
    public void saveKcfkRowQs(XckcFkQsEntity e) {
        xckcFkQsRepository.save(e);
    }

    @Override
    public List<CodeEntity> getGxkj() {
        return mapper.getGxkj();
    }

    @Override
    public List<CodeEntity> getGxcz() {
        return mapper.getGxcz();
    }

    @Override
    public List<CodeEntity> getZsffgw() {
        return mapper.getZsffgw();
    }

    @Override
    public List<CodeEntity> getZlwz() {
        return mapper.getZlwz();
    }

    @Override
    public List<GetXckcxxView> getKcrwPj() {
        return mapper.getKcrwPj();
    }

    @Override
    public List<GetXckcxxView> getKcrwYhqr() {
        return mapper.getKcrwYhqr();
    }

    @Override
    public XckcRwzbEntity getXckcRwzbEntity(String id) {
        return mapper.getXckcRwzbEntity(id);
    }

    @Override
    public XckcRwzbEntity getXckcxx(String xmid) {
        return mapper.getXckcxx(xmid);
    }

    @Override
    public XckcRwzbEntity getKczbByXmid(String xmid,String kczt) {
       List<XckcRwzbEntity> list = mapper.getKczbByXmid(xmid,kczt);
       if(list.size()<=0){
           return null;
       }
        return list.get(0);
    }

    @Override
    public List<XckcRwcbEntity> getKcbm(String kcrwzbid) {
        return mapper.getKcbm(kcrwzbid);
    }

    @Override
    public XckcRwzbEntity getXckcRwzbIsDoneBySqdId(String id) {
        return mapper.getXckcRwzbIsDoneBySqdId(id);
    }

    @Override
    public List<XckcFkQsEntity> showQsFk(String id) {
        return mapper.showQsFk(id);
    }

    @Override
    public List<XckcFkFj> getFkFjlb(String tablename, String kcrwcbid) {
        return mapper.getFkFjlb(tablename,kcrwcbid);
    }

    @Override
    public List<XckcFkFj> getPjFjlb(String tablename, String kcrwzbid) {
        return mapper.getPjFjlb(tablename,kcrwzbid);
    }

    @Override
    public List<XckcFkFj> getSgdwFkFjlb(String kcrwcbid) {
        return mapper.getSgdwFkFjlb(kcrwcbid);
    }

    @Override
    public XckcFkSgdwEntity getSgdwKcfk(String kcrwcbid) {
        return mapper.getSgdwKcfk(kcrwcbid);
    }

    @Override
    public void savePjFj(Map map) {
        mapper.savePjFj(map);
    }

    @Override
    public void saveKcjgqrFj(Map map) {
        mapper.saveKcjgqrFj(map);
    }

    @Override
    public List<GetSqbxxView> getLxlb(GetSqbxxView e) {
        return mapper.getLxlb(e);
    }

    @Override
    public int getLxlbTotal(GetSqbxxView e) {
        return mapper.getLxlbTotal(e);
    }

    @Override
    public List<GetSqbxxView> getCheckZxm(XmInstance e) {
        return mapper.getCheckZxm(e);
    }

    @Override
    public int getCheckZxmTotal(XmInstance e) {
        return mapper.getCheckZxmTotal(e);
    }

    @Override
    public GetXqslEntity getSlxx(String id) {
        return mapper.getSlxx(id);
    }

    @Override
    public YwslXjxqsqbEntity getSsbxxJmk(String id) {
        return mapper.getSsbxxJmk(id);
    }

    @Override
    public YwslGshbsqbEntity getSsbxxGsk(String id) {
        return mapper.getSsbxxGsk(id);
    }

    @Override
    public YwslChgzsqbEntity getSsbxxHgk(String id) {
        return mapper.getSsbxxHgk(id);
    }

    @Override
    public int updateSsbSflx(String tablename, String id) {
        return mapper.updateSsbSflx(tablename,id);
    }

    @Override
    public Map<String, String> getGssByXzqybh(String qybh) {
        return mapper.getGssByXzqybh(qybh);
    }

    @Override
    public List<CodeEntity> getXmCodeCheckbox(String codename, String type) {
        return mapper.getXmCodeCheckbox(codename,type);
    }

    @Override
    public List<XmInstanceEntity> getZxm() {
        return mapper.getZxm();
    }

    @Override
    public int setXmidToLdxx(String sqid, String xmid) {
        return mapper.setXmidToLdxx(sqid,xmid);
    }

    @Override
    public int findXmNumForParentId(String id) {
        return mapper.findXmNumForParentId(id);
    }

    @Override
    public Map<String, Object> getRwbh(Map map) {
        return mapper.getRwbh(map);
    }

    @Override
    public Map<String, Object> getXmbh(Map map) {
        return mapper.getXmbh(map);
    }

    @Override
    public String getUserIdByActivityId(String activityId) {
        return mapper.getUserIdByActivityId(activityId);
    }

    @Override
    public void deleteFkFj(String tablename, String id) {
        mapper.deleteFkFj(tablename,id);
    }

    @Override
    public void saveSlfj(Map map) {
        mapper.saveSlfj(map);
    }

    @Override
    public List<Map<String,Object>> findSqFjLb(String tablename, String name, String id) {
        return mapper.findSqFjLb(tablename,name,id);
    }

    @Override
    public List<GetXmlbSbyjEntity> getYjXmxx(String xmid) {//getXmlbSbyjRepository
        return mapper.getYjXmxx(xmid);
    }

    @Override
    public List<CodeModel> getZlzl(String tablename, String fieldname, String sqdid, String fjlx) {
        return mapper.getZlzl(tablename,fieldname,sqdid,fjlx);
    }

    @Override
    public List<SjstYhzl> getYhzl(String tablename, String fieldname, String sqdid, String fjlx) {
        return mapper.getYhzl(tablename,fieldname,sqdid,fjlx);
    }

    @Override
    public List<Map<String,Object>> getFkFjData_sjy(String cbid) {
        return mapper.getFkFjData_sjy(cbid);
    }

    @Override
    public List<Map<String, Object>> getFkFjData_sgdw(String cbid) {
        return mapper.getFkFjData_sgdw(cbid);
    }

    @Override
    public List<SjstYhzl> toDetial_sjy(String id, String ejfjlx, String yjfjlx) {
        return mapper.toDetial_sjy(id, ejfjlx, yjfjlx);
    }

    @Override
    public void saveDxDfsEntity(DxDfsEntity dxDfsEntity) {
        dxDfsRepository.saveAndFlush(dxDfsEntity);
    }

    @Override
    public UserEntity getUserByUserId(String userid) {
        return mapper.getUserByUserId(userid);
    }

    @Override
    public List<Map<String, Object>> getMyBacklog(BaseEntity baseEntity) {
        return mapper.getMyBacklog(baseEntity);
    }

    @Override
    public int getMyBacklogTotal(BaseEntity baseEntity) {
        return mapper.getMyBacklogTotal(baseEntity);
    }

    @Override
    public List<Map<String, Object>> getMyDone(BaseEntity baseEntity) {
        return mapper.getMyDone(baseEntity);
    }

    @Override
    public int getMyDoneTotal(BaseEntity baseEntity) {
        return mapper.getMyDoneTotal(baseEntity);
    }

    @Override
    public List<XckcFkFj> xmjlCk(String kcfkid) {
        return mapper.xmjlCk(kcfkid);
    }

    @Override
    public String getAzlxbm(String azlx) {
        return mapper.getAzlxbm(azlx);
    }


}
