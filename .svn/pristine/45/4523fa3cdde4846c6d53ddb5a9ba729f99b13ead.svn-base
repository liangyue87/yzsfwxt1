package com.ljx.hfgsjt.main.dao;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Map;

public interface UserMapper {

    @Select("select t.* from XTGL_USER t where t.deptid = #{deptid}")
    Map<String, Object> getUserByDeptid(String deptid,String username, Integer page, Integer size);

    @Select("select * from XTGL_USER t where t.account = #{account}")
    public UserEntity getUserByAccount(String account);

    @Select("select * from DX_USER_PZ where userid = #{userid}")
    List<Map<String,Object>> getDxSettings(@Param("userid") String userid);

    @Insert("insert into DX_USER_PZ VALUES (#{id},#{userid},#{account},#{dxmbid})")
    void addDxSettings(@Param("id") String id,@Param("userid") String userid,@Param("account") String account,@Param("dxmbid") String dxmbid);

    @Delete("delete from DX_USER_PZ where id = #{id}")
    void deleteDxSettings(@Param("id") String id);

    @Select("select * from XTGL_ORG")
    public List<Map<String,Object>> getOrgList();

    @Select("select * from xtgl_user a left join xtgl_userorg b on a.userid = b.userid")
    public List<Map<String,Object>> getUserList();

    @Delete("delete from xtgl_userrole where roleid = #{roleid}")
    int deleteByRoleid(@Param("roleid") String roleid);

    @Insert("insert into xtgl_userrole values (#{userid},#{roleid})")
    void saveRoleUser(Map<String,String> map);

    @Select("select * from xtgl_userrole where roleid = #{roleid}")
    List<Map<String,Object>> getUserForRole(@Param("roleid") String roleid);

    @Select("select * from XTGL_USER r   where USERID = #{userid}")
    UserEntity getUser(@Param("userid") String userid);

    @Update("update XTGL_USER u set u.USERNAME = #{username},u.ACCOUNT = #{account}," +
            "u.MOBILEPHONE = #{mobilephone},u.GDDH = #{gddh},u.EMAIL = #{email} where u.USERID = #{userid}")
    void updateUser(@Param("userid") String userid, @Param("username")String username, @Param("account")String account,
                    @Param("mobilephone")String mobilephone, @Param("gddh")String gddh, @Param("email")String email);

    @Update("update XTGL_USER u set u.USERNAME = #{username},u.ACCOUNT = #{account},u.MOBILEPHONE = #{mobilephone}," +
            "u.GDDH = #{gddh},u.EMAIL = #{email},u.PASSWORD = #{password} where u.USERID = #{userid}")
    void updateUserPassword(@Param("userid")String userid, @Param("username")String username, @Param("account")String account,
                            @Param("mobilephone")String mobilephone, @Param("gddh")String gddh, @Param("email")String email, @Param("password")String password);

    @Select("select * from XTGL_USER where USERID = #{userid} and PASSWORD = #{password}")
    UserEntity getPassWordDb(@Param("userid") String userid, @Param("password") String password);

    @Update("update XTGL_USER u set u.PASSWORD = #{password} where u.USERID = #{userid}")
    void editpassword(@Param("userid") String userid, @Param("password") String password);
}
