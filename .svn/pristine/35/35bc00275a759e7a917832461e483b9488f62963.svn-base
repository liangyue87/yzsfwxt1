package com.ljx.hfgsjt.yj.dao;

import com.ljx.hfgsjt.entity.dto.Sjst.CodeModel;
import com.ljx.hfgsjt.entity.yj.XmXhs;
import com.ljx.hfgsjt.entity.yj.YjDzjcb;
import com.ljx.hfgsjt.entity.yj.YjDzjzb;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface XhsMapper {


    int getXhsCount(@Param("xmid")String xmid);

    List<XmXhs> getXhsData(@Param("xmid") String xmid, @Param("startRow") int startRow, @Param("endRow") int endRow);

    List<CodeModel> getXhsxh();

    List<CodeModel> getXhsazfs();

    List<XmXhs> xhspzHandle(@Param("id")String id);

    List<XmXhs> xhsdzjData(String id);

    void deleteDzjfj(@Param("dzjid") String dzjid,@Param("userid")String userid);

    @Select("select * from XTGL_GGMC t order by bh")
    List<Map<String,String>> getJgmcOptions();
    @Select("select * from XTGL_GDKJ t order by bh")
    List<Map<String,String>> getJgkjOptions();
    @Select("select * from XTGL_GXCZ t order by bh")
    List<Map<String,String>> getBqgczOptions();
    //List<Map<String,String>> getBqgkjOptions();
    @Select("select * from yj_dzjcb where dzjzbid = #{id}")
    List<YjDzjcb> findDzjcbByZbid(@Param("id") String id);
    @Select("select t.*,to_char(t.azrq,'yyyy-mm-dd') azrq_vue from yj_dzjzb t where id =#{id}")
    YjDzjzb findDzjbByid(@Param("id")  String id);
    @Select("select * from xm_sb where id = #{id}")
    Map<String,String> getSbxx(@Param("id") String id);
    @Select("select * from xm_fm where id = #{id}")
    Map<String,String> getFmxx(@Param("id") String id);
    @Select("select * from YJ_DZJZB_FJ t where  dzjid = #{id} and (sc_flag is null or sc_flag = '0' )")
    Map<String,String> getFilexx(@Param("id") String id);
}
