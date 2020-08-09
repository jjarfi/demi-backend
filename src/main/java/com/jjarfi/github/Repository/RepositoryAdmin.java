package com.jjarfi.github.Repository;

import com.jjarfi.github.Model.Admin;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("repoadmin")
public interface RepositoryAdmin extends PagingAndSortingRepository<Admin, String> {

    Admin findByUsernameAndPassword(String uname, String pass);

    Optional<Admin> findById(Integer id);
}
