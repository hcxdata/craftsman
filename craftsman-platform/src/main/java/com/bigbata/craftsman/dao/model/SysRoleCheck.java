package com.bigbata.craftsman.dao.model;

/**
 * Created by lixianghui on 15-5-13.
 */
public class SysRoleCheck extends SysRoleEntity {
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    private boolean checked = false;
}
