package com.bigbata.craftsman.dao.model;

import javax.persistence.*;

/**
 * Created by lixianghui on 15-5-13.
 */
@Entity
@Table(name = "sys_user_role", schema = "", catalog = "craftsman")
@IdClass(SysUserRoleEntityPK.class)
public class SysUserRoleEntity {
    private long userid;
    private int roleid;

    @Id
    @Column(name = "userid", nullable = false, insertable = true, updatable = true)
    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    @Id
    @Column(name = "roleid", nullable = false, insertable = true, updatable = true)
    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysUserRoleEntity that = (SysUserRoleEntity) o;

        if (roleid != that.roleid) return false;
        if (userid != that.userid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userid ^ (userid >>> 32));
        result = 31 * result + roleid;
        return result;
    }
}
