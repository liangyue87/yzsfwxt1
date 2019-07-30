package com.ljx.hfgsjt.wtys.serviceImpl;

import com.ljx.hfgsjt.entity.wtys.GetXmlbEntity;
import com.ljx.hfgsjt.repository.yswt.GetXmlbRepository;
import com.ljx.hfgsjt.util.utils.StringUtils;
import com.ljx.hfgsjt.wtys.dao.YsZjqtMapper;
import com.ljx.hfgsjt.wtys.service.GetXmlbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

@Service
public class GetXmlbServiceImpl implements GetXmlbService {

    @Autowired
    private GetXmlbRepository getXmlbRepository;

    @Autowired
    private YsZjqtMapper dao;

    @Override
    public int getGwxxCount(GetXmlbEntity gwxx) {
        return getXmlbRepository.findAll().size();
    }

    @Override
    public List<GetXmlbEntity> getGwxxList(GetXmlbEntity gwxx) {
        return getXmlbRepository.findAll();
    }

    @Override
    public Page<GetXmlbEntity> getAllXmlb(Pageable pageable) {
        return getXmlbRepository.findAll(pageable);
    }

    @Override
    public List<GetXmlbEntity> getXqqsListByXmjl(Integer page, Integer size, String czr) {
        Integer pagebefor = 1;
        Integer pageafter = 1;
        if (null == page) {
            page = 0;
            pagebefor = 0;
        }
        if (size == null) {
            size = 10;
            pageafter = 10;
        }
        pagebefor = page * size;
        pageafter = (page + 1) * size;
        List<GetXmlbEntity> list = getXmlbRepository.findAll();
        return list;
    }

    @Override
    public List<GetXmlbEntity> getAllXqsl(Map<String, Object> pd) {
        Integer pagebefor = 1;
        Integer pageafter = 1;
        Integer page = Integer.parseInt(String.valueOf(pd.get("page")));
        Integer size = Integer.parseInt(String.valueOf(pd.get("size")));
        if (null == page) {
            page = 0;
            pagebefor = 0;
        }
        if (size==null) {
            size = 10;
            pageafter = 10;
        }
        pagebefor = page * size;
        pageafter = (page+1) * size;
        pd.put("pagebefor",pagebefor);
        pd.put("pageafter",pageafter);
        return dao.getAllXqsl(pd);
    }

    @Override
    public List<GetXmlbEntity> getAllXqslList(Map<String, Object> pd) {
        return dao.getAllXqslList(pd);
    }

    public Page<GetXmlbEntity> search(final GetXmlbEntity student, Pageable pageable) {
        return getXmlbRepository.findAll(new Specification<GetXmlbEntity>() {
            @Override
            public Predicate toPredicate(Root<GetXmlbEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                Predicate xmmcLike = null;
                if (null != student && !StringUtils.isEmpty(student.getXmmc())) {
                    xmmcLike = cb.like(root.<String>get("xmmc"), "%" + student.getXmmc() + "%");
                }

                Predicate xmdzLike = null;
                if (null != student && !StringUtils.isEmpty(student.getXmdz())) {
                    xmdzLike = cb.like(root.<String>get("xmdz"), "%" + student.getXmdz() + "%");
                }

                Predicate lxrLike = null;
                if (null != student && !StringUtils.isEmpty(student.getLxr())) {
                    lxrLike = cb.like(root.<String>get("lxr"), "%" + student.getLxr() + "%");
                }

                Predicate ztLike = null;
                if (null != student && !StringUtils.isEmpty(student.getAzlx())) {
                    ztLike = cb.equal(root.<String>get("xmdz"), "%" + student.getAzlx() + "%");
                }

                Predicate slsjDy = null;
                if (null != student && !StringUtils.isEmpty(student.getXmdz())) {
                    slsjDy = cb.lessThan(root.<String>get("kssj"), student.getSlsj());
                }

                Predicate slsjXy = null;
                if (null != student && !StringUtils.isEmpty(student.getXmdz())) {
                    slsjXy = cb.lessThan(root.<String>get("jssj"), student.getSlsj());
                }

                if (null != xmmcLike) query.where((javax.persistence.criteria.Predicate) xmmcLike);
                if (null != xmdzLike) query.where((javax.persistence.criteria.Predicate) xmdzLike);
                if (null != lxrLike) query.where((javax.persistence.criteria.Predicate) lxrLike);
                if (null != ztLike) query.where((javax.persistence.criteria.Predicate) ztLike);
                if (null != slsjDy) query.where((javax.persistence.criteria.Predicate) slsjDy);
                if (null != slsjXy) query.where((javax.persistence.criteria.Predicate) slsjXy);
                return null;
            }
        }, new PageRequest(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort()));
    }
}
