package com.bigbata.craftsman.dao;

import com.bigbata.craftsman.dao.model.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lixianghui on 15-8-1.
 */
public interface UserMapper {
    public Page<SysUser> getAllUsers(@Param("name") String name, Pageable pageable);

    public void update(SysUser user);
}
