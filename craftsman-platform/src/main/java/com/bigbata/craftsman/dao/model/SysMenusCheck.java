package com.bigbata.craftsman.dao.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixianghui on 15-5-11.
 */
public class SysMenusCheck extends SysMenusEntity {
    boolean checked = false;
    List<SysMenusCheck> children = new ArrayList<SysMenusCheck>();

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<SysMenusCheck> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenusCheck> children) {
        this.children = children;
    }

}
