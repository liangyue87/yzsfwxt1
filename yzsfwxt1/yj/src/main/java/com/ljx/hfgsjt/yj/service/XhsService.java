package com.ljx.hfgsjt.yj.service;

import com.ljx.hfgsjt.entity.dto.Sjst.CodeModel;
import com.ljx.hfgsjt.entity.yj.XmXhs;
import com.ljx.hfgsjt.entity.yj.YjDzjcb;
import com.ljx.hfgsjt.entity.yj.YjDzjzb;
import com.ljx.hfgsjt.entity.yj.YjDzjzbFj;

import java.util.List;
import java.util.Map;

public interface XhsService {

    int getXhsCount(String xmid);

    List<XmXhs> getXhsData(String xmid, int startRow, int endRow);

    void insertXhs(XmXhs model);

    List<CodeModel> getXhsxh();

    List<CodeModel> getXhsazfs();

    List<XmXhs> xhspzHandle(String id);

    void xhspzDelete(String id);

    void insertDzjzb(YjDzjzb model);

    void insertDzjcb(YjDzjcb cbmodel);

    void saveFj(YjDzjzbFj model);



    //获取水表三箱接管名称
    List<Map<String,String>> getJgmcOptions();
    //获取水表三箱接管口径
    List<Map<String,String>> getJgkjOptions();
    //获取水表三箱表前管材质
    List<Map<String,String>> getBqgczOptions();
    //获取水表三箱表前管口径
    //List<Map<String,String>> getBqgkjOptions();
    List<YjDzjcb> findDzjcbByZbid(String id);
    YjDzjzb findDzjbByid(String id);
    Map<String,String> getSbxx(String id);
    Map<String,String> getFmxx(String id);
    Map<String,String> getFilexx(String id);


    List<XmXhs> xhsdzjData(String id);

    void deleteDzjfj(String dzjid,String userid);

}
