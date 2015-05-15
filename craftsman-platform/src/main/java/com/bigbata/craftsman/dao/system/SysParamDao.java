package com.bigbata.craftsman.dao.system;

import com.bigbata.craftsman.dao.model.SysParamEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by lixianghui on 15-5-15.
 */
public interface SysParamDao extends PagingAndSortingRepository<SysParamEntity, Integer> {
    public Page<SysParamEntity> findByTypeCodeAndNameLikeAndCodeLike(Pageable pageable, String typeCode, String name, String code);

    public SysParamEntity findByName(String name);

    public SysParamEntity findByCode(String code);

    @Modifying
    @Query("update SysParamEntity set typeCode= :oldTypeCode where typeCode = :newTypeCode ")
    public void updateDictType(@Param("newTypeCode") String newTypeCode, @Param("oldTypeCode") String oldTypeCode);

    @Modifying
    @Query("delete SysParamEntity where typeCode = :typeCode")
    public void delByTypeCode(@Param("typeCode") String typeCode);
}
