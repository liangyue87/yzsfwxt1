package com.ljx.hfgsjt.main.serviceImpl;

import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.main.dao.UserMapper;
import com.ljx.hfgsjt.main.service.UserService;
import com.ljx.hfgsjt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserMapper mapper;

    private int pages;

    public int getPages() {
        return pages;
    }

    @Override
    public List<Map<String, Object>> getDxSettings(String userid) {
        return mapper.getDxSettings(userid);
    }

    @Override
    public void addDxSettings(String id, String userid, String account, String dxmbid) {
        mapper.addDxSettings(id,userid,account,dxmbid);
    }

    @Override
    public void deleteDxSettings(String id) {
        mapper.deleteDxSettings(id);
    }

    @Override
    public UserEntity getUser(String userid) {
        return mapper.getUser(userid);
    }

    @Override
    public void updateUser(String userid, String username, String account, String mobilephone, String gddh, String email) {
        mapper.updateUser(userid,username,account,mobilephone,gddh,email);
    }

    @Override
    public void updateUserPassword(String userid, String username, String account, String mobilephone, String gddh, String email, String password) {
        mapper.updateUserPassword(userid,username,account,mobilephone,gddh,email,password);
    }

    @Override
    public UserEntity getPassWordDb(String userid, String password) {
        return mapper.getPassWordDb(userid,password);
    }

    @Override
    public void editpassword(String userid, String password) {
        mapper.editpassword(userid,password);
    }


    @Override
    public UserEntity login(String account,String password) {
        return repository.findByAccountAndPassword(account,password);
    }

    @Override
    public UserEntity getUserByAccount(String account) {
        return mapper.getUserByAccount(account);
    }

    @Override
    public Page<UserEntity> getUserList(String username,  String deptid, Integer page, Integer size) {
        if (null == page) {
            page = 0;
        }
        if (size==null) {
            size = 10;
        }
        Page<UserEntity> userpage = null;
        PageRequest request = PageRequest.of(page, size);
        if(deptid != null && !deptid.equals("")){
            userpage = repository.findByUsernameLike(username,deptid,request);
        }else{
            userpage = repository.findByUsernameLike(username,request);
        }
        return userpage;
    }

    public List<UserEntity> getAllUserList(){
        List<UserEntity> users = repository.findAll();
        return users;
    }

    @Override
    public void saveUser(UserEntity userEntity) {
        repository.save(userEntity);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }


}
