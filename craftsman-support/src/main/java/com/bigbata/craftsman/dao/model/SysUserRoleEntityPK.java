package com.bigbata.craftsman.dao.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by lixianghui on 15-5-13.
 */
public class SysUserRoleEntityPK implements Serializable {
    private long userid;
    private int roleid;

    @Column(name = "userid", nullable = false, insertable = true, updatable = true)
    @Id
    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    @Column(name = "roleid", nullable = false, insertable = true, updatable = true)
    @Id
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

        SysUserRoleEntityPK that = (SysUserRoleEntityPK) o;

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
