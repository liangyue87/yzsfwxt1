package com.ljx.hfgsjt.main.service;

import com.ljx.hfgsjt.entity.dto.UserEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


public interface UserService {
    public UserEntity login(String account,String password);

    public UserEntity getUserByAccount(String account);

    public Page<UserEntity> getUserList(String username, String deptid, Integer page, Integer size);

    public List<UserEntity> getAllUserList();

    public void saveUser(UserEntity userEntity);

    void deleteById(String id);

    public int getPages();

    List<Map<String,Object>> getDxSettings(String userid);

    void addDxSettings(String id,String userid,String account,String dxmbid);

    void deleteDxSettings(String id);

    UserEntity getUser(String userid);

    void updateUser(String userid, String username, String account, String mobilephone, String gddh, String email);

    void updateUserPassword(String userid, String username, String account, String mobilephone, String gddh, String email, String password);

    UserEntity getPassWordDb(String userid, String password);

    void editpassword(String userid, String password);
}
