package com.ljx.hfgsjt.gcpd.service;

import com.ljx.hfgsjt.entity.dto.Activity.WfHiInstContentEntity;
import com.ljx.hfgsjt.entity.dto.gcpd.GcpdSqgchgpdEntity;
import com.ljx.hfgsjt.entity.dto.gcpd.YshgFjEntity;
import com.ljx.hfgsjt.entity.dto.gcpd.YshgRwbEntity;
import com.ljx.hfgsjt.entity.dto.gcpd.YshgRwbFjEntity;

import java.util.List;

public interface GcpdService {
    /**
     * 数据条数
     * @return
     */
    int getTotal();

    /**
     * 100001
     * 申请工程合格评定动查询
     * @param date1
     * @param date2
     * @param xmbh
     * @param lxr
     * @param xmdz
     * @param azlx
     * @param size
     * @return
     */
    List<GcpdSqgchgpdEntity> geYszptall(String xmid,String xmfzr,String date1, String date2, String xmbh,String xmmc, String lxr, String xmdz, String azlx,String pdzt, Integer page, Integer size);

    /**
     * 更据xmid查看（YSHG_RWB）是否有该数据
     * @param xmid
     * @return
     */
    YshgRwbEntity getYshgRwbByXmid(String xmid);

    /**
     * 保存供水工程竣工验收合格证明
     * @param yshgRwbEntity
     */
    void saveYshgRwbEntity(YshgRwbEntity yshgRwbEntity);

    /**
     * 更新供水工程竣工验收合格证明
     * @param id
     * @param ysfw
     * @param xmlx
     * @param ysnr
     * @param cbyj
     * @param ysjl
     */
    void updateYshgRwbEntity(String id, String ysfw, String xmlx, String ysnr, String cbyj, String ysjl);

    /**
     * 上传附件
     * @param yshgRwbFj
     */
    void setyshgRwbFj(YshgRwbFjEntity yshgRwbFj);

    /**
     * 查询附件列表显示
     * @param fj
     * @return
     */
    List<YshgFjEntity> getAllYshgFj(YshgFjEntity fj);

    /**
     * 查询某任务附件个数
     * @param zbid
     * @return
     */
    int getPsTotals(String zbid);

    /**
     * 查询任务附件路径
     * @param zbid
     * @return
     */
    List<YshgFjEntity> getAllYshgFjFjlj(String zbid);

    /**
     * 删除高拍仪拍摄照片
     * @param zbid
     * @param fjlj
     */
    void deleteImage(String zbid, String fjlj);

    /**
     * 删除工程评定附件
     * @param fjid
     */
    void deleteFkFj(String fjid);

    /**
     * 查看某一项目，是否已经创建过实例
     * @param xmid
     * @return
     */
    WfHiInstContentEntity getwfHiInstContent(String xmid);
}
