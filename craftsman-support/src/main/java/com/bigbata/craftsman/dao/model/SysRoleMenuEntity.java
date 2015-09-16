package com.bigbata.craftsman.dao.model;

import javax.persistence.*;

/**
 * Created by lixianghui on 15-5-13.
 */
@Entity
@Table(name = "sys_role_menu")
@IdClass(SysRoleMenuEntityPK.class)
public class SysRoleMenuEntity {
    private int roleid;
    private int menusid;

    @Id
    @Column(name = "roleid", nullable = false, insertable = true, updatable = true)
    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    @Id
    @Column(name = "menusid", nullable = false, insertable = true, updatable = true)
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

        SysRoleMenuEntity that = (SysRoleMenuEntity) o;

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
