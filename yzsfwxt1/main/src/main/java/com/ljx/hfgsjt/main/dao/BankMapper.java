package com.ljx.hfgsjt.main.dao;

import com.ljx.hfgsjt.entity.dto.BankEntity;
import com.ljx.hfgsjt.main.dao.Provider.BankProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface BankMapper {

    @Insert("insert into XTGL_FZYH values (#{bh},#{mc},#{yhbh},#{yhmc})")
    public void save(BankEntity bankEntity);

    @Select("select mc from XTGL_FZYH where bh = #{bh}")
    List<String> findBankByBh(String bh);

    @Update("update XTGL_FZYH set mc = #{mc},yhbh = #{yhbh},yhmc=#{yhmc} where bh = #{bh}")
    void updateBank(BankEntity bankEntity);

    @Select("select distinct t.yhbh||'_'||t.yhmc label,t.yhbh||'_'||t.yhmc value from XTGL_FZYH t")
    List<Map<String, String>> findOption();

    @Select("select max(t.bh) bh from XTGL_FZYH t where t.yhmc = #{yhmc}")
    List<String> findMaxNoByYhmc(String yhmc);

    @Delete("delete from XTGL_FZYH t where t.bh = #{bh}")
    void deleteById(String bh);

    @Select("select * from (select t.*,ROWNUM rn from XTGL_FZYH t where t.yhmc = #{search_yhmc} order by bh) " +
            " WHERE rn > #{pagebefor} AND rn <= #{pageafter}")
    List<BankEntity> findBankByyhmc(@Param("pagebefor") Integer pagebefor, @Param("pageafter") Integer pageafter, @Param("search_yhmc") String search_yhmc);

    @Select("select t.* from XTGL_FZYH t where t.yhmc = #{search_yhmc}")
    List<BankEntity> findAll(String search_yhmc);

    @DeleteProvider(type= BankProvider.class,method = "deleteByIds")
    void deleteByIds(List<String> list);
}
