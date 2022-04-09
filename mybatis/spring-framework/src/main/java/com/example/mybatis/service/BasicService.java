package com.example.mybatis.service;

import com.example.mybatis.dao.BasicDao;
import com.example.mybatis.domain.User;
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
