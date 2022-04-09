package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicService {

    private final BasicDao basicDao;

    @Autowired
    public BasicService(BasicDao basicDao) {
        this.basicDao = basicDao;
    }

    public List<User> selectUsers() {
        return basicDao.selectAll();
    }

}
