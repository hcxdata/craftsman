package com.bigbata.craftsman.dao.system;

import com.bigbata.craftsman.dao.model.SysMenusEntity;
import com.bigbata.craftsman.dao.model.SysRoleMenuEntity;
import com.bigbata.craftsman.dao.model.SysRoleMenuEntityPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by lixianghui on 15-5-11.
 */
public interface SysRoleMenuDao extends PagingAndSortingRepository<SysRoleMenuEntity, SysRoleMenuEntityPK> {
    public List<SysRoleMenuEntity> findByRoleid(Integer roleid);

    public List<SysRoleMenuEntity> findByMenusid(Integer menusid);

    @Modifying
    @Query("delete from SysRoleMenuEntity where roleid = :roleId")
    public void deleteByRoleId(@Param("roleId") Integer roleId);
}
