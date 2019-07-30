package com.ljx.hfgsjt.repository.sjst;

import com.ljx.hfgsjt.entity.dto.Sjst.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectModel, String>,JpaSpecificationExecutor<ProjectModel>, Serializable {
    @Query(value="select z.id,z.xmbh,z.xmfzr,z.xmmc,z.dwmc,z.xmdz,z.lxr,z.xmjd,to_char(z.lxsj,'yyyy-MM-dd') " +
            "from (select x.* from (select ROWNUM rn, t.id, t.xmbh, t.xmmc, t.dwmc, t.xmdz, t.lxr, t.lxsj,t.xmfzr " +
            "case (select a.iscurrent from wf_task_history a " +
            "left join wf_ru_inst b on a.proc_inst_id = b.proc_inst_id " +
            "left join wf_ru_inst_content c on c.proc_inst_id = b.proc_inst_id " +
            "left join wf_hi_inst d on a.proc_inst_id = d.proc_inst_id " +
            "left join wf_hi_inst_content e on d.proc_inst_id = e.proc_inst_id " +
            "where ((c.table_name = 'xm_instance' and c.contentname = 'id' and c.contentvalue = t.id) " +
            "or (e.table_name = 'xm_instance' and e.contentname = 'id' and e.contentvalue = t.id)) " +
            "and a.c_activityid = :#{#model.nodeid}) when 0 then 0 else 1 end xmjd from xm_instance t " +
            "where t.xmfzr = :#{#model.xmfzr} and if(:#{#model.xmbh} is not null ,t.xmbh LIKE CONCAT('%',:#{#model.xmbh},'%') , 1 = 1) " +
            "and if(:#{#model.dwmc} is not null,t.dwmc LIKE CONCAT('%',:#{#model.dwmc},'%') , 1 = 1) " +
            "and if(:#{#model.lxr} is not null,t.lxr LIKE CONCAT('%',:#{#model.lxr},'%') , 1 = 1) " +
            "and if(:#{#model.kssj} is not null,t.lxsj>=to_date(:#{#model.kssj},'yyyy-MM-dd hh24:mi:ss'), 1 = 1) " +
            "and if(:#{#model.jssj} is not null,t.lxsj<=to_date(:#{#model.jssj},'yyyy-MM-dd hh24:mi:ss'), 1 = 1) )x " +
            "where if(:#{#model.xmjd} is not null,x.xmjd=:#{#model.xmjd},1=1) order by x.xmjd desc,x.lxsj desc) z " +
            "where z.rn between :startRow and :endRow",nativeQuery=true)
    List<ProjectModel> getProjectList(@Param("model") ProjectModel model);

    @Query(value="select count(x.id) from (select " +
            "               t.id," +
            "               t.xmbh," +
            "               t.xmmc," +
            "               t.dwmc," +
            "               t.xmfzr," +
            "               t.xmdz," +
            "               t.lxr," +
            "               t.lxsj," +
            "               case (select a.iscurrent" +
            "                   from wf_task_history a" +
            "                   left join wf_ru_inst b" +
            "                     on a.proc_inst_id = b.proc_inst_id" +
            "                   left join wf_ru_inst_content c" +
            "                     on c.proc_inst_id = b.proc_inst_id" +
            "                   left join wf_hi_inst d" +
            "                     on a.proc_inst_id = d.proc_inst_id" +
            "                   left join wf_hi_inst_content e" +
            "                     on d.proc_inst_id = e.proc_inst_id" +
            "                  where ((c.table_name = 'xm_instance' and" +
            "                        c.contentname = 'id' and c.contentvalue = t.id) or" +
            "                        (e.table_name = 'xm_instance' and" +
            "                        e.contentname = 'id' and e.contentvalue = t.id))and a.c_activityid = :#{#model.nodeid})" +
            "                 when 0 then" +
            "                  0" +
            "                 else" +
            "                  1" +
            "               end xmjd" +
            "          from xm_instance t" +
            "         where t.xmfzr = :#{#model.xmfzr} " +
            "         and if(:#{#model.xmbh} is not null and :#{#model.xmbh}!='') " +
            "         then t.xmbh LIKE CONCAT('%',:#{#model.xmbh},'%') else (1=1) end if"+
            "         )x ",nativeQuery=true)
    int getProjectCount(@Param("model")ProjectModel model);
}
