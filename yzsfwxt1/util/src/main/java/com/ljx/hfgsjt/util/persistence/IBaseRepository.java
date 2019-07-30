package com.ljx.hfgsjt.util.persistence;

import com.ljx.hfgsjt.util.http.PageInfo;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@NoRepositoryBean
public interface IBaseRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

    /**
     * 保存Tree节点
     *
     * @param entity
     * @return
     */
    T saveTreeEntity(T entity);

    /**
     * 逻辑删除
     *
     * @param id
     */
    void deleteLogic(ID id);

    /**
     * 删除Tree节点
     *
     * @param id
     */
    void deleteTreeEntity(ID id);

    <T> List<T> list(String hql, Map<String, Object> params);

    /**
     * 查询全部并排除逻辑删除的数据
     *
     * @return
     */
    <T> List<T> findAllWithoutDeleted();

    /**
     * 获取单条数据
     */
    T getByHql(String hql, Object... params);

    /**
     * 获取单条数据
     */
    T getByHql(String hql, Map<String, Object> params);

    /**
     * 列表所有数据获取数据
     */
    <T> List<T> list(String hql, Object... params);

    /**
     * 分页获取数据数据
     */
    PageInfo<T> page(PageInfo pageInfo, String hql, Object... params);

    /**
     * 分页获取数据数据
     */
    PageInfo<T> page(PageInfo pageInfo, String hql, Map<String, Object> params);

    /**
     * 执行修改语句
     */
    int executeUpdate(String hql, Object... params);

    <E> E executeGet(String hql, Object... params);

    <E> E executeGet(String hql, Map<String, Object> params);

    <E> PageInfo<E> executePage(PageInfo pageInfo, String hql, Object... params);

    <E> PageInfo<E> executePage(PageInfo pageInfo, String hql, Map<String, Object> params);

    <E> List<E> executeList(String hql, Object... params);

    <E> List<E> executeList(String hql, Map<String, Object> params);
}
