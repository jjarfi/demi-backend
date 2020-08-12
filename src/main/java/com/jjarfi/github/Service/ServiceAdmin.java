package com.jjarfi.github.Service;

import com.jjarfi.github.Model.Admin;
import com.jjarfi.github.Repository.RepositoryAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceAdmin {

    @Autowired
    private RepositoryAdmin repoadmin;

    // TODO: Service untuk find admin by id 8/12/20
    public Optional<Admin> findId(Integer id){
        return repoadmin.findById(id);
    }
}
