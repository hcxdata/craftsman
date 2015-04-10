package com.bigbata.craftsman.dao.system;

import com.bigbata.craftsman.dao.model.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SysUserDao extends PagingAndSortingRepository<SysUser, Long> {

    public SysUser findUserByName(String name);

    public Page<SysUser> findByNameLike(Pageable pageable, String name);
}
