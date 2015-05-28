package com.huoteng.entity;

import javax.persistence.*;

/**
 * Created by huoteng on 5/28/15.
 */
@Entity
@Table(name = "target-url", catalog = "search")
public class TargetUrl {
    private int id;
    private String url;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TargetUrl targetUrl = (TargetUrl) o;

        if (id != targetUrl.id) return false;
        if (url != null ? !url.equals(targetUrl.url) : targetUrl.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
