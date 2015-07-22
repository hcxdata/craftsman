package com.bigbata.craftsman.api.system;

import java.util.ArrayList;
import java.util.List;

import com.bigbata.craftsman.dao.model.SysRoleCheck;
import com.bigbata.craftsman.dao.model.SysRoleEntity;
import com.bigbata.craftsman.dao.model.SysUserRoleEntity;
import com.bigbata.craftsman.dao.system.SysRoleDao;
import com.bigbata.craftsman.dao.system.SysUserRoleDao;
import com.bigbata.craftsman.exception.ME;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bigbata.craftsman.dao.model.SysUser;
import com.bigbata.craftsman.dao.system.SysUserDao;

@RestController
@RequestMapping({"/api/system/users"})
public class SysUsersApi {
    @Autowired
    private SysUserDao userDao;
    @Autowired
    private SysRoleDao roleDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;


    @RequestMapping(method = RequestMethod.GET)
    public Page<SysUser> index(@PageableDefault Pageable pageable, String name) {
        if (name == null)
            name = "%";
        else
            name = "%" + name + "%";
        Page<SysUser> users = userDao.findByNameLike(pageable, name);
        return users;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SysUser show(@PathVariable Long id) {
        return userDao.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public SysUser create(@RequestBody SysUser user) {
        if (userDao.findUserByName(user.getName()) != null)
            throw new ME("该用户已存在");
        return userDao.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void edit(@RequestBody SysUser user) {
        userDao.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void destory(@PathVariable Long id) {
        sysUserRoleDao.deleteByUserId(id);
        userDao.delete(id);
    }

    @RequestMapping(value = "/{userid}/roles", method = RequestMethod.GET)
    public List<SysRoleCheck> showRoles(@PathVariable Long userid) {
        Iterable<SysRoleEntity> roles = roleDao.findAll();
        Iterable<SysUserRoleEntity> userRoles = sysUserRoleDao.findByUserid(userid);
        List<SysRoleCheck> results = new ArrayList<>();
        for (SysRoleEntity role : roles) {
            SysRoleCheck check = new SysRoleCheck();
            BeanUtils.copyProperties(role, check);
            results.add(check);
            for (SysUserRoleEntity userRole : userRoles) {
                if (userRole.getRoleid() == role.getId()) {
                    check.setChecked(true);
                }
            }
        }
        return results;
    }


    @RequestMapping(value = "/{userid}/roles", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateRoles(@RequestBody UserRoleParam param) {
        sysUserRoleDao.deleteByUserId(param.getUserid());
        for (Integer roleId : param.getRoles()) {
            SysUserRoleEntity userRole = new SysUserRoleEntity();
            userRole.setUserid(param.getUserid());
            userRole.setRoleid(roleId);
            sysUserRoleDao.save(userRole);
        }
    }

    public static class UserRoleParam {
        private Long userid;

        public List<Integer> getRoles() {
            return roles;
        }

        public void setRoles(List<Integer> roles) {
            this.roles = roles;
        }

        public Long getUserid() {
            return userid;
        }

        public void setUserid(Long userid) {
            this.userid = userid;
        }

        private List<Integer> roles;

    }
}
