package com.bigbata.craftsman.dao.model;

import javax.persistence.*;

/**
 * Created by lixianghui on 15-4-21.
 */
@Entity
@Table(name = "sys_menus")
public class SysMenusEntity {
    private int id;
    private Integer parentId;
    private String text;
    private Integer orders;
    private String hrefTarget;
    private Long childCount;

    public SysMenusEntity() {
    }

    public SysMenusEntity(int id, Integer parentId, String text, Integer orders, String hrefTarget, Long childCount) {
        this.id = id;
        this.parentId = parentId;
        this.text = text;
        this.orders = orders;
        this.hrefTarget = hrefTarget;
        this.childCount = childCount;

    }

    public void setChildCount(Long childCount) {
        this.childCount = childCount;
    }


    @Transient
    public boolean isLeaf() {
        return !(childCount != null && childCount > 0);
    }


    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "parent_id", nullable = true, insertable = true, updatable = true)
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "text", nullable = true, insertable = true, updatable = true, length = 600)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "orders", nullable = true, insertable = true, updatable = true)
    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    @Basic
    @Column(name = "href_target", nullable = true, insertable = true, updatable = true, length = 3000)
    public String getHrefTarget() {
        return hrefTarget;
    }

    public void setHrefTarget(String hrefTarget) {
        this.hrefTarget = hrefTarget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysMenusEntity that = (SysMenusEntity) o;

        if (id != that.id) return false;
        if (hrefTarget != null ? !hrefTarget.equals(that.hrefTarget) : that.hrefTarget != null) return false;
        if (orders != null ? !orders.equals(that.orders) : that.orders != null) return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        result = 31 * result + (hrefTarget != null ? hrefTarget.hashCode() : 0);
        return result;
    }
}
