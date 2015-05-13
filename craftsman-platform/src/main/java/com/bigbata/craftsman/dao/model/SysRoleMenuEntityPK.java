package com.bigbata.craftsman.dao.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by lixianghui on 15-5-13.
 */
public class SysRoleMenuEntityPK implements Serializable {
    private int roleid;
    private int menusid;

    @Column(name = "roleid", nullable = false, insertable = true, updatable = true)
    @Id
    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    @Column(name = "menusid", nullable = false, insertable = true, updatable = true)
    @Id
    public int getMenusid() {
        return menusid;
    }

    public void setMenusid(int menusid) {
        this.menusid = menusid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysRoleMenuEntityPK that = (SysRoleMenuEntityPK) o;

        if (menusid != that.menusid) return false;
        if (roleid != that.roleid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleid;
        result = 31 * result + menusid;
        return result;
    }
}
