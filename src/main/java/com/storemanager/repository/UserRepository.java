package com.storemanager.repository;


import com.storemanager.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
    // extends JpaRepository<T,ID>
        extends CrudRepository<User,String> {
}
