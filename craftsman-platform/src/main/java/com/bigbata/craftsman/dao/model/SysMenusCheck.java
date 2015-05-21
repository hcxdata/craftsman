package com.bigbata.craftsman.dao.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixianghui on 15-5-11.
 */
public class SysMenusCheck extends SysMenusEntity {
    boolean checked = false;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }


}
