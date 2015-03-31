package com.bigbata.craftsman.dao.system;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bigbata.craftsman.dao.model.system.SysUser;

public interface SysUserDao extends PagingAndSortingRepository<SysUser, Long> {

    public SysUser findUserByName(String name);

    public Page<SysUser> findByNameLike(Pageable pageable, String name);
}
