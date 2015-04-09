package com.bigbata.craftsman.dao.system;

import com.bigbata.craftsman.dao.model.system.SysDictTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by lixianghui on 15-4-2.
 */
public interface SysDictTypeDao extends PagingAndSortingRepository<SysDictTypeEntity, Integer> {

    public Page<SysDictTypeEntity> findByNameLikeAndCodeLike(Pageable pageable, String name, String code);

    public SysDictTypeEntity findByName(String name);

    public SysDictTypeEntity findByCode(String code);
}
