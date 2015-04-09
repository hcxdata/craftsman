package com.bigbata.craftsman.dao.system;

import com.bigbata.craftsman.dao.model.system.SysDictEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by lixianghui on 15-4-2.
 */
public interface SysDictDao extends PagingAndSortingRepository<SysDictEntity, Integer> {
    public Page<SysDictEntity> findByTypeCodeAndNameLikeAndCodeLikeOrderByOrdersAsc(Pageable pageable, String typeCode, String name, String code);

    public SysDictEntity findByTypeCodeAndName(String typeCode, String name);

    public SysDictEntity findByTypeCodeAndCode(String typeCode, String code);

    @Query(value = "select count(s.id) from SysDictEntity s where s.typeCode = :typeCode")
    public Integer getCountByTypeCode(@Param(value = "typeCode") String typeCode);

    @Query(value = "select count(s.id) from SysDictEntity s ")
    public Integer getCount();

    @Modifying
    @Query("update SysDictEntity set orders = :newOrders where typeCode = :typeCode and orders = :oldOrders")
    public void updateOrderFromTypeCodeAndOrder(@Param("typeCode") String typeCode, @Param("newOrders") Integer newOrders, @Param("oldOrders") Integer oldOrders);

    @Modifying
    @Query("update SysDictEntity set typeCode= :oldTypeCode where typeCode = :newTypeCode ")
    public void updateDictType(@Param("newTypeCode") String newTypeCode, @Param("oldTypeCode") String oldTypeCode);

    @Modifying
    @Query("update SysDictEntity set orders = orders - 1 where typeCode = :typeCode and orders > :orders")
    public void upOrderFrom(@Param("typeCode") String typeCode, @Param("orders") Integer orders);

    @Modifying
    @Query("delete from SysDictEntity where typeCode = :typeCode ")
    public void deleteByTypeCode(@Param("typeCode") String typeCode);
}
