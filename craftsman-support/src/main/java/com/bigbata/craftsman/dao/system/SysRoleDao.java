package com.bigbata.craftsman.dao.system;

import com.bigbata.craftsman.dao.model.SysRoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * Created by lixianghui on 15-5-11.
 */
public interface SysRoleDao extends PagingAndSortingRepository<SysRoleEntity, Integer> {
    public Page<SysRoleEntity> findByNameLike(Pageable pageable, String name);

    public SysRoleEntity findByName(String name);
}
