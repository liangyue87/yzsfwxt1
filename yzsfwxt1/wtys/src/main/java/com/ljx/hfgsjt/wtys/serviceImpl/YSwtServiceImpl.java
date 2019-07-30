package com.ljx.hfgsjt.wtys.serviceImpl;

import com.ljx.hfgsjt.entity.dto.Activity.WfRuInstContentEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfRuInstEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfTaskEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfTaskHistoryEntity;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.dto.yswt.*;
import com.ljx.hfgsjt.entity.yj.azlxEntity;
import com.ljx.hfgsjt.repository.XmInstanceRepository;
import com.ljx.hfgsjt.repository.message.DxDfsRepository;
import com.ljx.hfgsjt.repository.yswt.*;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import com.ljx.hfgsjt.util.utils.StringUtils;
import com.ljx.hfgsjt.wtys.dao.YsWpMapper;
import com.ljx.hfgsjt.wtys.service.YSwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.ljx.hfgsjt.util.utils.StringUtil.isEmpty;

@Service
public class YSwtServiceImpl implements YSwtService {


    @Autowired
    private YswtRepository repository;
    @Autowired
    private XmVfwRepository xmVfwrepository;
    @Autowired
    private YsRwzbRepository YsRwzbrepository;
    @Autowired
    private YsVzpRepository ysVzpRepository;
    @Autowired
    private YsYszyRepository YsYszyRepository;
    @Autowired
    private XmInstanceRepository xmInstanceRepository;
    @Autowired
    private YsVbzRepository ysVbzRepository;
    @Autowired
    private XtglFylxRepository xtglFylxRepository;
    @Autowired
    private YsRwcdRepository YsRwcdRepository;
    @Autowired
    private YsRwfjRepository YsRwfjRepository;
    @Autowired
    private YsRwyhqrfjRepository ysRwyhqrfjRepository;
    @Autowired
    private YsZlxxRepository YsZlxxRepository;
    @Autowired
    private YsLdshRepository YsLdshRepository;
    @Autowired
    private YsQsRepository YsQsRepository;
    @Autowired
    private YsYsjezhRepository YsYsjezhRepository;
    @Autowired
    private DxDfsRepository dxDfsRepository;

    @Autowired
    private YsWpMapper ysWpMapper;


    private int total;

    public int getTotal() {
        return total;
    }


    @Override
    public List<YsLdshNewEntity> getYsLdsh(String sfsh, String xmbh, String lxr, String xmdz, String azlx, Integer page, Integer size) {

        PageRequest request = PageRequest.of(page, size);
        YsLdshNewEntity ysLdsh = new YsLdshNewEntity();
        ysLdsh.setXmbh(xmbh);
        ysLdsh.setLxr(lxr);
        ysLdsh.setXmdz(xmdz);
        ysLdsh.setAzlx(azlx);
        ysLdsh.setSfsh(sfsh);
        ExampleMatcher matcher = EX(2);
        Example<YsLdshNewEntity> ex = Example.of(ysLdsh, matcher);
        Specification<YsLdshNewEntity> querySpecifi = new Specification<YsLdshNewEntity>(){
            @Override
            public Predicate toPredicate(Root<YsLdshNewEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotBlank(xmbh)) {
                    //模糊查找
                    predicates.add(cb.like(root.get("xmbh").as(String.class), "%" + xmbh + "%"));
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
                if (StringUtils.isNotBlank(sfsh)) {
                    //模糊查找
                    predicates.add(cb.like(root.get("sfsh").as(String.class), "%" + sfsh + "%"));
                }
                // and到一起的话所有条件就是且关系，or就是或关系
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page<YsLdshNewEntity> all = YsLdshRepository.findAll(querySpecifi, request);
        this.total = (int)all.getTotalElements();
        List<YsLdshNewEntity> list = all.getContent();

        return list;
    }

    @Override
    public List<XmYsztEntity> getall(String bjzt,String userid,String date1,String date2,String xmbh, String lxr, String xmdz, String azlx, String yszt,String xmmc, Integer page, Integer size) {
        //根据立项目时间进间排序
        Sort sort = new Sort(Sort.Direction.DESC, "lxsj");
        PageRequest request = PageRequest.of(page, size,sort);
        XmYsztEntity entity = new XmYsztEntity();
        entity.setXmbh(xmbh);
        entity.setLxr(lxr);
        entity.setXmdz(xmdz);
        entity.setAzlx(azlx);
        entity.setYszt(yszt);
        ExampleMatcher matcher = EX(2);
        Example<XmYsztEntity> ex = Example.of(entity, matcher);

        Specification<XmYsztEntity> querySpecifi = new Specification<XmYsztEntity>(){
            @Override
            public Predicate toPredicate(Root<XmYsztEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotBlank(date1)) {
                    //大于或等于传入时间
                    predicates.add(cb.greaterThanOrEqualTo(root.get("lxsj").as(String.class), date1));
                }
                if (StringUtils.isNotBlank(date2)) {
                    //小于或等于传入时间
                    predicates.add(cb.lessThanOrEqualTo(root.get("lxsj").as(String.class), date2));
                }
                if (StringUtils.isNotBlank(xmmc)) {
                    //模糊查找
                    predicates.add(cb.like(root.get("xmmc").as(String.class), "%" + xmmc + "%"));
                }
                if (StringUtils.isNotBlank(xmbh)) {
                    //模糊查找
                    predicates.add(cb.like(root.get("xmbh").as(String.class), "%" + xmbh + "%"));
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
                if (StringUtils.isNotBlank(yszt)) {
                    //模糊查找
                    predicates.add(cb.like(root.get("yszt").as(String.class), "%" + yszt + "%"));
                }
                if (StringUtils.isNotBlank(userid)) {
                    //模糊查找
                    predicates.add(cb.like(root.get("xmfzr").as(String.class), "%" + userid + "%"));
                }
                if (StringUtils.isNotBlank(bjzt)) {
                    //模糊查找
                    predicates.add(cb.like(root.get("bjzt").as(String.class), "%" + bjzt + "%"));
                }
                // and到一起的话所有条件就是且关系，or就是或关系
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        Page<XmYsztEntity> all = repository.findAll(querySpecifi, request);
        this.total = (int) all.getTotalElements();
        List<XmYsztEntity> list = all.getContent();
        return list;
    }

    @Override
    public List<Map<String, Object>> getZhcxHhldcx(ZhcxHhldcxEntity hhldcxEntity) {
        return ysWpMapper.getZhcxHhldcx(hhldcxEntity);
    }

    @Override
    public int getZhcxHhldcxToTal(ZhcxHhldcxEntity hhldcxEntity) {
        return ysWpMapper.getZhcxHhldcxToTal(hhldcxEntity);
    }

    @Override
    public List<Map<String, Object>> getYsbzcxList(ZhcxYsbzEntity zhcxYsbzEntity) {
        return ysWpMapper.getYsbzcxList(zhcxYsbzEntity);
    }

    @Override
    public int getYsbzcxListToTal(ZhcxYsbzEntity zhcxYsbzEntity) {
        return ysWpMapper.getYsbzcxListToTal(zhcxYsbzEntity);
    }

    @Override
    public YsVbzNewEntity getXxzl(String xmid) {
        return ysWpMapper.getXxzl(xmid);
    }

    @Override
    public List<YsVzpNewEntity> geYszptall( String id ,String date1,String date2,String xmbh, String lxr, String xmdz, String azlx, Integer page, Integer size) {
        PageRequest request = PageRequest.of(page, size);
        Specification<YsVzpNewEntity> querySpecifi = new Specification<YsVzpNewEntity>(){
            @Override
            public Predicate toPredicate(Root<YsVzpNewEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
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
                    predicates.add(cb.like(root.get("azlx").as(String.class), "%" + azlx + "%"));
                }
                if (StringUtils.isNotBlank(id)) {
                    //模糊查找
                    predicates.add(cb.like(root.get("id").as(String.class), "%" + id + "%"));
                }
                // and到一起的话所有条件就是且关系，or就是或关系
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page<YsVzpNewEntity> all = ysVzpRepository.findAll(querySpecifi, request);
        this.total = (int) all.getTotalElements();
        List<YsVzpNewEntity> list = all.getContent();
        return list;
    }

    @Override
    public List<YsVbzNewEntity> getYsBz(String xmid,String date1,String date2,String yszt, String xmbh, String lxr, String xmdz, String azlx, Integer page, Integer size) {
        PageRequest request = PageRequest.of(page, size);
        YsVbzNewEntity ysvbz = new YsVbzNewEntity();
        ysvbz.setXmbh(xmbh);
        ysvbz.setLxr(lxr);
        ysvbz.setXmdz(xmdz);
        ysvbz.setAzlx(azlx);
        ysvbz.setYszt(yszt);
        ExampleMatcher matcher = EX(2);
        Example<YsVbzNewEntity> ex = Example.of(ysvbz, matcher);
        Specification<YsVbzNewEntity> querySpecifi = new Specification<YsVbzNewEntity>(){
            @Override
            public Predicate toPredicate(Root<YsVbzNewEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
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
                if (StringUtils.isNotBlank(yszt)) {
                    //模糊查找
                    predicates.add(cb.like(root.get("yszt").as(String.class), "%" + yszt + "%"));
                }
                if (StringUtils.isNotBlank(xmid)) {
                    //模糊查找
                    predicates.add(cb.like(root.get("id").as(String.class), "%" + xmid + "%"));
                }
                // and到一起的话所有条件就是且关系，or就是或关系
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page<YsVbzNewEntity> all = ysVbzRepository.findAll(querySpecifi, request);
        this.total = (int) all.getTotalElements();
        List<YsVbzNewEntity> list = all.getContent();

        return list;
    }

    @Override
    public void DeleById(YsRwzb ysrwzb) {
        ExampleMatcher matcher = EX(1);

        Example<YsRwzb> ex = Example.of(ysrwzb, matcher);
        List<YsRwzb> all = YsRwzbrepository.findAll(ex);
        YsRwzbrepository.delete(all.get(0));

    }

    @Override
    public List<YsYsjezh> getAllYsjezh(YsYsjezh ysjezh) {
        ExampleMatcher matcher = EX(1);
        Example<YsYsjezh> ex = Example.of(ysjezh, matcher);
        return YsYsjezhRepository.findAll(ex);
    }

    @Override
    @Transactional
    public List<XmVfw> getXmVfw(String id, String zt) {


        XmVfw xmLd = new XmVfw();
        xmLd.setXmid(id);
        ExampleMatcher matcher = EX(1);
        Example<XmVfw> ex = Example.of(xmLd, matcher);
        List<XmVfw> all = xmVfwrepository.findAll(ex);

       // Ysztgb(xmbh,zt,matcher);

        return all;
    }

    @Override
    public List<YsZlxx> getAllYsZlxx(YsZlxx ysZlxx) {
        ExampleMatcher matcher = EX(1);
        Example<YsZlxx> ex = Example.of(ysZlxx, matcher);



        return YsZlxxRepository.findAll(ex);
    }

    @Override
    public void setYsRwfj(YsRwfj ysfj) {
        YsRwfjRepository.save(ysfj);
    }

    @Override
    public List<YsYszy> getYSZY() {
        return YsYszyRepository.findAll();
    }

    @Override
    public Long getYSZY(String UserId) {
        return YsYszyRepository.countYsZpry(UserId);
    }

    @Override
    public void setOny(YsRwzb ysrwzb) {
        YsRwzbrepository.saveAndFlush(ysrwzb);
    }

    @Override
    @Transactional
    public void setYsBz(Map map,String sbm) {
        if (sbm.equals("1")){
            //当再次提交保存时，删除原来的保存的数据，以达到更新的效果
            ysWpMapper.deleteYsRwcbByYsrwzbid(map.get("zbid").toString());

            for(int i= 1;i<=Integer.parseInt(map.get("i").toString());i++){
                YsRwcb ysRwcb = new YsRwcb();
                ysRwcb.setId(  GuidUtils.getGuid());
                ysRwcb.setYsrwzbid(map.get("zbid").toString());
                String bz ="";
                String ysje="";
                try {
                    bz=map.get("bz"+i).toString();
                } catch (NullPointerException e) {
                    bz = "";
                }
                try {
                    ysje= map.get("ysje"+i).toString();
                } catch (NullPointerException e) {
                    ysje="0";
                }

                ysRwcb.setBz(bz);

                ysRwcb.setYsje(ysje);
                ysRwcb.setYsfl(map.get("yslx"+i).toString());

                YsRwcb ysRwcbs= ysWpMapper.getYsRwcbByYsrwzbidAndYsfl(map.get("zbid").toString(),map.get("yslx"+i).toString());
                if (isEmpty(ysRwcbs)){
                    YsRwcdRepository.saveAndFlush(ysRwcb);
                }else{
                    ysWpMapper.updateYscb(ysRwcbs.getId(),ysRwcb.getBz(),ysRwcb.getYsje());
                }

            }

            try {
                YsRwzbrepository.updateYszb(map.get("zbid").toString(),new Date(),"3","0",map.get("ysbzbz").toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (sbm.equals("2")){
            //当再次提交保存时，删除原来的保存的数据，以达到更新的效果
            ysWpMapper.deleteYsRwcbByYsrwzbid(map.get("zbid").toString());

            for(int i= 1;i<=Integer.parseInt(map.get("i").toString());i++){
                YsRwcb ysRwcb = new YsRwcb();
                ysRwcb.setId(  GuidUtils.getGuid());
                ysRwcb.setYsrwzbid(map.get("zbid").toString());
                String bz ="";
                String ysje="";
                try {
                    bz=map.get("bz"+i).toString();
                } catch (NullPointerException e) {
                    bz = "";
                }
                ysRwcb.setBz(bz);

                try {
                    ysje= map.get("ysje"+i).toString();
                } catch (NullPointerException e) {
                    ysje="0";
                }
                ysRwcb.setYsje(ysje);
                ysRwcb.setYsfl(map.get("yslx"+i).toString());

                YsRwcb ysRwcbs= ysWpMapper.getYsRwcbByYsrwzbidAndYsfl(map.get("zbid").toString(),map.get("yslx"+i).toString());
                if (isEmpty(ysRwcbs)){
                    YsRwcdRepository.saveAndFlush(ysRwcb);
                }else{
                    ysWpMapper.updateYscb(ysRwcbs.getId(),ysRwcb.getBz(),ysRwcb.getYsje());
                }
            }
            try {
                YsRwzbrepository.updateYszb(map.get("zbid").toString(),new Date(),"2","0",map.get("ysbzbz").toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (sbm.equals("3")){//预算补充保存预算价格
            //当再次提交保存时，删除原来的保存的数据，以达到更新的效果
            ysWpMapper.deleteYsRwcbByYsrwzbid(map.get("zbid").toString());

            for(int i= 1;i<=Integer.parseInt(map.get("i").toString());i++){
                YsRwcb ysRwcb = new YsRwcb();
                ysRwcb.setId(  GuidUtils.getGuid());
                ysRwcb.setYsrwzbid(map.get("zbid").toString());
                String bz ="";
                String ysje="";
                try {
                    bz=map.get("bz"+i).toString();
                } catch (NullPointerException e) {
                    bz = "";
                }
                ysRwcb.setBz(bz);

                try {
                    ysje= map.get("ysje"+i).toString();
                } catch (NullPointerException e) {
                    ysje="0";
                }
                ysRwcb.setYsje(ysje);
                ysRwcb.setYsfl(map.get("yslx"+i).toString());

                YsRwcb ysRwcbs= ysWpMapper.getYsRwcbByYsrwzbidAndYsfl(map.get("zbid").toString(),map.get("yslx"+i).toString());
                if (isEmpty(ysRwcbs)){
                    YsRwcdRepository.saveAndFlush(ysRwcb);
                }else{
                    ysWpMapper.updateYscb(ysRwcbs.getId(),ysRwcb.getBz(),ysRwcb.getYsje());
                }
            }
            try {
                //预算保存时，修改预算主表里的状态
                YsRwzbrepository.updateYszbYsbc(map.get("zbid").toString(),new Date(),"2",map.get("ysbzbz").toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }

    @Override
    public List<YsYszy> getYszyById(YsYszy ysYszy) {
        ExampleMatcher matching = EX(1);

        Example<YsYszy> ex = Example.of(ysYszy, matching);
        List<YsYszy> all = YsYszyRepository.findAll(ex);
        return all;
    }

    @Override
    public List<YsRwzb> getYszyXmById(String id) {
        YsRwzb rwzb = new YsRwzb();
        rwzb.setYsr(id);
        ExampleMatcher matching = EX(1);

        Example<YsRwzb> ex = Example.of(rwzb, matching);
        List<YsRwzb> all = YsRwzbrepository.findAll(ex);
        return all;

    }

    @Override
    public List<XmInstanceEntity> getYszyXmxxById(String xmid) {
        XmInstanceEntity entity = new XmInstanceEntity();
        entity.setId(xmid);
        ExampleMatcher matching = EX(1);

        Example<XmInstanceEntity> ex = Example.of(entity, matching);
        List<XmInstanceEntity> all = xmInstanceRepository.findAll(ex);

        return all;
    }

    @Override
    public WfRuInstEntity getWfRuInstByXmidAndPdefid(String pdefid,String activityid, String xmid) {
        return ysWpMapper.getWfRuInstByXmidAndPdefid(pdefid,activityid,xmid);
    }

    @Override
    @Transactional
    public void updataYsShtg(Map map,int bs) {

        if(bs == 1){
            YsRwzb rwzb = new YsRwzb();
            rwzb.setId(map.get("zbid").toString());

            rwzb.setSfsh("1");//是否审核
            rwzb.setShsj(new Date());
            rwzb.setShr(map.get("shr").toString());
            rwzb.setShyj(map.get("shyj").toString());

            YsRwzbrepository.updateYszb(rwzb.getId(),rwzb.getSfsh(),rwzb.getShsj(),rwzb.getShr(),rwzb.getShyj());

        }else if(bs == 2){
            YsRwzb rwzb = new YsRwzb();

            rwzb.setYszt("2");//预算状态0待预算1预算中2预算变更中3完成4作废
            rwzb.setId(map.get("zbid").toString());
            YsRwzbrepository.updateYszbShbtg(rwzb.getId(),rwzb.getYszt());
          /*   YsRwcb ysRwcb = new YsRwcb();
           ysRwcb.setYsrwzbid(map.get("zbid").toString());
            YsRwcdRepository.delete(ysRwcb);*/

        }
        }

    @Override
    public WfTaskEntity getTaskByInstId(String procInstId) {
        return ysWpMapper.getTaskByInstId(procInstId);
    }

    @Override
    public List<YsQsEntity> getYsQs(String sfsh, String xmbh, String lxr, String xmdz, String azlx, Integer page,Integer size) {

        PageRequest request = PageRequest.of(page, size);
        YsQsEntity ysQs = new YsQsEntity();
        ysQs.setXmbh(xmbh);
        ysQs.setLxr(lxr);
        ysQs.setXmdz(xmdz);
        ysQs.setAzlx(azlx);
        ysQs.setSfsh(sfsh);
        ExampleMatcher matcher = EX(2);
        Example<YsQsEntity> ex = Example.of(ysQs, matcher);
        Specification<YsQsEntity> querySpecifi = new Specification<YsQsEntity>(){
            @Override
            public Predicate toPredicate(Root<YsQsEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotBlank(xmbh)) {
                    //模糊查找
                    predicates.add(cb.like(root.get("xmbh").as(String.class), "%" + xmbh + "%"));
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
                    predicates.add(cb.like(root.get("azlx").as(String.class), "%" + azlx + "%"));
                }
                if (StringUtils.isNotBlank(sfsh)) {
                    //模糊查找
                    predicates.add(cb.like(root.get("sfsh").as(String.class), "%" + sfsh + "%"));
                }
                // and到一起的话所有条件就是且关系，or就是或关系
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page<YsQsEntity> all = YsQsRepository.findAll(querySpecifi, request);
        this.total = (int)all.getTotalElements();
        List<YsQsEntity> list = all.getContent();

        return list;
    }

    @Override
    public WfRuInstContentEntity getWfRuInstAndWfRuInstContentByXmid(String xmid) {
        return ysWpMapper.getWfRuInstAndWfRuInstContentByXmid(xmid);
    }

    @Override
    public WfRuInstEntity getwfRuInstByid(String procInstId) {
        return ysWpMapper.getwfRuInstByid(procInstId);
    }

    @Override
    public WfRuInstContentEntity getWfRuInstContentEntityById(String contentid) {
        return ysWpMapper.getWfRuInstContentEntityById(contentid);
    }

    @Override
    public List<YsRwcb> getYsRwcb(String zbid) {

        List<YsRwcb> all = ysWpMapper.findAll(zbid);
        return all;
    }

    @Override
    public YsBhEntity getYsBhEntity() {
        return ysWpMapper.getYsBhEntity();
    }

    @Override
    public WfTaskHistoryEntity getwfTaskHistoryEntity(String procinstid) {
        return ysWpMapper.getwfTaskHistoryEntity(procinstid);
    }

    @Override
    public void updateWfTaskHistory(String taskid) {
        ysWpMapper.updateWfTaskHistory(taskid);
    }

    @Override
    public void updateWfRuInstPdefId(String pdefid,String procinstid) {
        ysWpMapper.updateWfRuInstPdefId(pdefid,procinstid);
    }

    @Override
    public WfTaskHistoryEntity getwfTaskHistoryEntityByTaskid(String taskid) {
        return ysWpMapper.getwfTaskHistoryEntityByTaskid(taskid);
    }

    @Override
    public void updatewfTaskHistoryEntityByTaskid(String taskid) {
        ysWpMapper.updatewfTaskHistoryEntityByTaskid(taskid);
    }

    @Override
    public WfRuInstEntity getWfRuInstProcinstidByContentvalue(String xmid,String pdefid) {
        return ysWpMapper.getWfRuInstProcinstidByContentvalue(xmid,pdefid);
    }

    @Override
    public void deleteRwzbByXmid(String xmid) {
        ysWpMapper.deleteRwzbByXmid(xmid);
    }

    @Override
    public YsRwzb getYsRwzbByXmid(String xmid) {
        return ysWpMapper.getYsRwzbByXmid(xmid);
    }

    @Override
    public void deleteFkFj(String fjid) {
        ysWpMapper.deleteFkFj(fjid);
    }

    @Override
    public List<YsRwzb> getYssm(String zbid) {
       return ysWpMapper.getYssm(zbid);
    }

    @Override
    public List<azlxEntity> findAzlxBySllx(Map<String, Object> pd) {
        return ysWpMapper.findAzlxBySllx(pd);
    }

    @Override
    public List<WfTaskHistoryEntity> getsjstrzwid(String xmid, String activityId) {
        return ysWpMapper.getsjstrzwid(xmid,activityId);
    }

    @Override
    public void updateQszt(String xmid, String activityId) {
         ysWpMapper.updateQszt(xmid,activityId);
    }

    @Override
    public List<YsZlxx> getYszl(String xmid) {
        return ysWpMapper.getYszl(xmid);
    }

    @Override
    public void saveysRwyhqrfjEntity(YsRwyhqrfjEntity ysRwyhqrfjEntity) {
        ysRwyhqrfjRepository.save(ysRwyhqrfjEntity);
    }

    @Override
    public List<YsZlxx> getYszlYhpz(String ysrwzbid) {
        return ysWpMapper.getYszlYhpz(ysrwzbid);
    }

    @Override
    public void deleteYhqrfj(String yhqrfjid) {
        ysWpMapper.deleteYhqrfj(yhqrfjid);
    }

    @Override
    public void updateYszl(String rwfjid, String id) {
        ysWpMapper.updateYszl(rwfjid,id);
    }

    @Override
    public List<YsZlxx> getYsZlxx(String xmid, String zbid) {
        return ysWpMapper.getYsZlxx(xmid,zbid);
    }

    @Override
    public List<XmInstanceEntity> getYsbc(String xmid) {
        return ysWpMapper.getYsbc(xmid);
    }

    @Override
    public List<YsZlxx> getEarly_stage(String xmid, String zbid) {
        return ysWpMapper.getEarly_stage(xmid,zbid);
    }

    @Override
    public List<YsZlxx> getLate(String zbid) {
        return ysWpMapper.getLate(zbid);
    }

    @Override
    public XmInstanceEntity getXmfzr(String xmid) {
        return ysWpMapper.getXmfzr(xmid);
    }

    @Override
    public UserEntity getUserIdAndUserMobilephone(String s) {
        return ysWpMapper.getUserIdAndUserMobilephone(s);
    }

    @Override
    public void saveDxDfsEntity(DxDfsEntity dxDfsEntity) {
        dxDfsRepository.saveAndFlush(dxDfsEntity);
    }

    @Override
    public UserEntity getUserByXmfzr(String xmfzr) {
        return ysWpMapper.getUserByXmfzr(xmfzr);
    }

    @Override
    public List<Map<Object,String>> gethsjljbxx(String xmid) {
        return ysWpMapper.gethsjljbxx(xmid);
    }

    @Override
    public void saveYsRwzb(YsRwzb rwzb) {
        YsRwzbrepository.saveAndFlush(rwzb);
    }



    @Override
    public void updataYszb(YsRwzb rwzb) {

        String s = YsRwzbrepository.findYszb(rwzb.getXmid());
        YsRwzb ysRwzb1 = YsRwzbrepository.findById(s).get();

        ysRwzb1.setYsr(rwzb.getYsr());
        ysRwzb1.setYszt(rwzb.getYszt());
        ysRwzb1.setZpr(rwzb.getZpr());
        ysRwzb1.setZprbz(rwzb.getZprbz());
        YsRwzbrepository.saveAndFlush(ysRwzb1);

        XmInstanceEntity entity = xmInstanceRepository.findById(rwzb.getXmid()).get();
        entity.setYsfzr(rwzb.getYsr());
        xmInstanceRepository.saveAndFlush(entity);

    }

    /**
     *
     */

   /* @Transactional
    public void Ysztgb(String xmbh, String zt,ExampleMatcher matcher) {
        XmInstanceEntity instanceEntity = new XmInstanceEntity();
        instanceEntity.setXmbh(xmbh);
        Example<XmInstanceEntity> instanceEntityExample = Example.of(instanceEntity, matcher);
        List<XmInstanceEntity> all1 = xmInstanceRepository.findAll(instanceEntityExample);

        for (XmInstanceEntity a : all1) {

            String yszb = YsRwzbrepository.findYszb(a.getId());
            YsRwzbrepository.updateYszt(zt, yszb);
        }
    }*/

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

    @Override
    public List<XtglFylx> getYsx() {
        List<XtglFylx> all = xtglFylxRepository.findAllByAsc();

        return all;
    }
}