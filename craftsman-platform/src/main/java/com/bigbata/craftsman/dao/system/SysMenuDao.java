package com.bigbata.craftsman.dao.system;

import com.bigbata.craftsman.dao.model.SysMenusEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by lixianghui on 15-4-21.
 */
public interface SysMenuDao extends PagingAndSortingRepository<SysMenusEntity, Integer> {

    public List<SysMenusEntity> findByParentIdOrderByOrdersAsc(Integer parentId);

    @Query(value = "select count(s.id) from SysMenusEntity s where s.parentId = :parentId")
    public Integer getCountByParentId(@Param(value = "parentId") Integer parentId);

    @Modifying
    @Query("update SysMenusEntity set orders = orders - 1 where parentId = :parentId and orders > :orders")
    public void upOrderFrom(@Param("parentId") Integer parentId, @Param("orders") Integer orders);

    @Modifying
    @Query("update SysMenusEntity set orders = :newOrders where parentId = :parentId and orders = :oldOrders")
    public void updateOrderFromParentIdAndOrder(@Param("parentId") Integer parentId, @Param("newOrders") Integer newOrders, @Param("oldOrders") Integer oldOrders);

    @Modifying
    @Query("update SysMenusEntity set parentId = :parentId where id = :id")
    public void upParentId(@Param("id") Integer id, @Param("parentId") Integer parentId);
}
