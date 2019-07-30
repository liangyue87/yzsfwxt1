package com.ljx.hfgsjt.yj.serviceImpl;

import com.ljx.hfgsjt.entity.dto.Sjst.CodeModel;
import com.ljx.hfgsjt.entity.yj.XmXhs;
import com.ljx.hfgsjt.entity.yj.YjDzjcb;
import com.ljx.hfgsjt.entity.yj.YjDzjzb;
import com.ljx.hfgsjt.entity.yj.YjDzjzbFj;
import com.ljx.hfgsjt.repository.yj.DjzcbRepository;
import com.ljx.hfgsjt.repository.yj.DjzzbFjRepository;
import com.ljx.hfgsjt.repository.yj.DjzzbRepository;
import com.ljx.hfgsjt.repository.yj.XhsRepository;
import com.ljx.hfgsjt.yj.dao.XhsMapper;
import com.ljx.hfgsjt.yj.service.XhsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class XhsServiceImpl implements XhsService{

    @Autowired
    private XhsMapper mapper;
    @Autowired
    private XhsRepository repository;
    @Autowired
    private DjzzbRepository yjdzjzbrepository;
    @Autowired
    private DjzcbRepository yjdzjcbrepository;
    @Autowired
    private DjzzbFjRepository yjdzjzbfjrepository;


    @Override
    public int getXhsCount(String xmid) {
        return mapper.getXhsCount(xmid);
    }

    @Override
    public List<XmXhs> getXhsData(String xmid, int startRow, int endRow) {
        return mapper.getXhsData(xmid,startRow,endRow);
    }

    @Override
    public void insertXhs(XmXhs model) {
        repository.save(model);
    }

    @Override
    public List<CodeModel> getXhsxh() {
        return mapper.getXhsxh();
    }

    @Override
    public List<CodeModel> getXhsazfs() {
        return mapper.getXhsazfs();
    }

    @Override
    public List<XmXhs> xhspzHandle(String id) {
        return mapper.xhspzHandle(id);
    }

    @Override
    public void xhspzDelete(String id) {
        repository.deleteById(id);
    }

    @Override
    public void insertDzjzb(YjDzjzb model) {
        yjdzjzbrepository.save(model);
    }

    @Override
    public void insertDzjcb(YjDzjcb model) {
        yjdzjcbrepository.save(model);
    }

    @Override
    public void saveFj(YjDzjzbFj model) {
        yjdzjzbfjrepository.save(model);
    }

    @Override
    public List<XmXhs> xhsdzjData(String id) {
        return mapper.xhsdzjData(id);
    }

    @Override
    public void deleteDzjfj(String dzjid, String userid) {
        mapper.deleteDzjfj(dzjid,userid);
    }

    @Override
    public List<Map<String, String>> getJgmcOptions() {
        return mapper.getJgmcOptions();
    }

    @Override
    public List<Map<String, String>> getJgkjOptions() {
        return mapper.getJgkjOptions();
    }

    @Override
    public List<Map<String, String>> getBqgczOptions() {
        return mapper.getBqgczOptions();
    }

    @Override
    public List<YjDzjcb> findDzjcbByZbid(String id) {
        return mapper.findDzjcbByZbid(id);
    }

    @Override
    public YjDzjzb findDzjbByid(String id) {
        return mapper.findDzjbByid(id);
    }

    @Override
    public Map<String, String> getSbxx(String id) {
        return mapper.getSbxx(id);
    }

    @Override
    public Map<String, String> getFmxx(String id) {
        return mapper.getFmxx(id);
    }

    @Override
    public Map<String, String> getFilexx(String id) {
        return mapper.getFilexx(id);
    }

}
