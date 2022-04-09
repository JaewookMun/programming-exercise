package com.example.mybatis;

import com.example.mybatis.BasicDao;
import com.example.mybatis.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicService {

    @Autowired
    private BasicDao basicDao;


    public List<User> selectAll(){
        return basicDao.selectAll();
    }
}
