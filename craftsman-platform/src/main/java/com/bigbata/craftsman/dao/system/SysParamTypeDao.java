package com.bigbata.craftsman.dao.system;

import com.bigbata.craftsman.dao.model.SysParamTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by lixianghui on 15-5-15.
 */
public interface SysParamTypeDao extends PagingAndSortingRepository<SysParamTypeEntity, Integer> {
    public Page<SysParamTypeEntity> findByNameLikeAndCodeLike(Pageable pageable, String name, String code);

    public SysParamTypeEntity findByName(String name);

    public SysParamTypeEntity findByCode(String code);
}
