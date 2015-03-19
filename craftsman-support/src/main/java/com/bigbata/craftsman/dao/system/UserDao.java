package com.bigbata.craftsman.dao.system;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bigbata.craftsman.dao.model.system.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {

    public User findUserByName(String name);

    public Page<User> findByNameLike(Pageable pageable, String name);
}
