package com.bigbata.craftsman.dao.system;

import com.bigbata.craftsman.dao.model.SysUserRoleEntity;
import com.bigbata.craftsman.dao.model.SysUserRoleEntityPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by lixianghui on 15-5-13.
 */
public interface SysUserRoleDao extends PagingAndSortingRepository<SysUserRoleEntity, SysUserRoleEntityPK> {
    public List<SysUserRoleEntity> findByUserid(Long userid);

    @Modifying
    @Query("delete from SysUserRoleEntity where userid = :userId")
    public void deleteByUserId(@Param("userId") Long userId);
}
