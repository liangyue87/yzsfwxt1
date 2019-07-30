package com.ljx.hfgsjt.gcpd.serviceImpl;

import com.ljx.hfgsjt.entity.dto.Activity.WfHiInstContentEntity;
import com.ljx.hfgsjt.entity.dto.gcpd.GcpdSqgchgpdEntity;
import com.ljx.hfgsjt.entity.dto.gcpd.YshgFjEntity;
import com.ljx.hfgsjt.entity.dto.gcpd.YshgRwbEntity;
import com.ljx.hfgsjt.entity.dto.gcpd.YshgRwbFjEntity;
import com.ljx.hfgsjt.gcpd.dao.GcpdMapper;
import com.ljx.hfgsjt.gcpd.service.GcpdService;
import com.ljx.hfgsjt.repository.gcpd.GcpdRepository;
import com.ljx.hfgsjt.repository.gcpd.YshgFjRepository;
import com.ljx.hfgsjt.repository.gcpd.YshgRwbFjRepository;
import com.ljx.hfgsjt.repository.gcpd.YshgRwbRepository;
import com.ljx.hfgsjt.util.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class GcpdServiceImpl implements GcpdService {

    @Autowired
    private GcpdRepository gcpdrepository;
    @Autowired
    private YshgRwbRepository yshgRwbrepository;
    @Autowired
    private YshgRwbFjRepository yshgRwbFjRepository;

    @Autowired
    private YshgFjRepository yshgFjRepository;

    @Autowired
    private GcpdMapper gcpdMapper;



    private int total;

    public int getTotal() {
        return total;
    }

    @Override
    public List<GcpdSqgchgpdEntity> geYszptall(String xmid,String xmfzr,String date1, String date2, String xmbh,String xmmc, String lxr, String xmdz, String azlx,String pdzt, Integer page, Integer size) {
        //根据立项目时间进间排序
        Sort sort = new Sort(Sort.Direction.DESC, "lxsj");
        PageRequest request = PageRequest.of(page, size,sort);
        Specification<GcpdSqgchgpdEntity> querySpecifi = new Specification<GcpdSqgchgpdEntity>(){
            @Override
            public Predicate toPredicate(Root<GcpdSqgchgpdEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotBlank(date1)) {
                    //大于或等于传入时间
                    predicates.add(cb.greaterThanOrEqualTo(root.get("lxsj").as(String.class), date1));
                }
                if (StringUtils.isNotBlank(date2)) {
                    //小于或等于传入时间
                    predicates.add(cb.lessThanOrEqualTo(root.get("lxsj").as(String.class), date2));
                }
                if (StringUtils.isNotBlank(xmbh)) {
                    //模糊查找
                    predicates.add(cb.like(root.get("xmbh").as(String.class), "%" + xmbh + "%"));
                }
                if (StringUtils.isNotBlank(xmmc)) {
                    //模糊查找
                    predicates.add(cb.like(root.get("xmmc").as(String.class), "%" + xmmc + "%"));
                }
                if (StringUtils.isNotBlank(lxr)) {
                    //模糊查找
                    predicates.add(cb.like(root.get("lxr").as(String.class), "%" + lxr + "%"));
                }
                if (StringUtils.isNotBlank(xmdz)) {
                    //模糊查找
                    predicates.add(cb.like(root.get("xmdz").as(String.class), "%" + xmdz + "%"));
                }
                if (StringUtils.isNotBlank(azlx)) {
                    //模糊查找
                    predicates.add(cb.like(root.get("codename").as(String.class), "%" + azlx + "%"));
                }
                if (StringUtils.isNotBlank(pdzt)) {
                    //模糊查找
                    predicates.add(cb.like(root.get("pdzt").as(String.class), "%" + pdzt + "%"));
                }
                if (StringUtils.isNotBlank(xmid)) {
                    //模糊查找
                    predicates.add(cb.like(root.get("id").as(String.class), "%" + xmid + "%"));
                }
                /*if (StringUtils.isNotBlank(xmfzr)) {
                    //模糊查找（项目负责人）
                    predicates.add(cb.like(root.get("xmfzr").as(String.class), "%" + xmfzr + "%"));
                }*/
                // and到一起的话所有条件就是且关系，or就是或关系
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        Page<GcpdSqgchgpdEntity> all = gcpdrepository.findAll(querySpecifi, request);
        this.total = (int) all.getTotalElements();
        List<GcpdSqgchgpdEntity> list = all.getContent();
        return list;
    }

    @Override
    public YshgRwbEntity getYshgRwbByXmid(String xmid) {
        return gcpdMapper.getYshgRwbByXmid(xmid);
    }

    @Override
    public void saveYshgRwbEntity(YshgRwbEntity yshgRwbEntity) {
        yshgRwbrepository.save(yshgRwbEntity);
    }

    @Override
    public void updateYshgRwbEntity(String id, String ysfw, String xmlx, String ysnr, String cbyj, String ysjl) {
        gcpdMapper.updateYshgRwbEntity(id,ysfw,xmlx,ysnr,cbyj,ysjl);
    }

    @Override
    public void setyshgRwbFj(YshgRwbFjEntity yshgRwbFj) {
        yshgRwbFjRepository.saveAndFlush(yshgRwbFj);
    }

    @Override
    public List<YshgFjEntity> getAllYshgFj(YshgFjEntity fj) {
        ExampleMatcher matcher = EX(1);
        Example<YshgFjEntity> ex = Example.of(fj, matcher);
        return yshgFjRepository.findAll(ex);
    }

    @Override
    public int getPsTotals(String zbid) {
        return gcpdMapper.getPsTotals(zbid);
    }

    @Override
    public List<YshgFjEntity> getAllYshgFjFjlj(String zbid) {
        return gcpdMapper.getAllYshgFjFjlj(zbid);
    }

    @Override
    public void deleteImage(String zbid, String fjlj) {
        gcpdMapper.deleteImage(zbid,fjlj);
    }

    @Override
    public void deleteFkFj(String fjid) {
        gcpdMapper.deleteFkFj(fjid);
    }

    @Override
    public WfHiInstContentEntity getwfHiInstContent(String xmid) {
        return gcpdMapper.getwfHiInstContent(xmid);
    }

    public ExampleMatcher EX(int  i){
        if (i == 1){//精确
            ExampleMatcher matching = ExampleMatcher.matching()
                    .withStringMatcher(ExampleMatcher.StringMatcher.EXACT)
                    .withIgnoreCase(true);
            return  matching;
        }else if (i == 2){ //模糊
            ExampleMatcher matching = ExampleMatcher.matching()
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                    .withIgnoreCase(true);
            return  matching;
        }
        return null;

    }
}
