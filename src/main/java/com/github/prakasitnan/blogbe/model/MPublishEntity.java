package com.github.prakasitnan.blogbe.model;

import jakarta.persistence.*;

@Entity
@Table(name = "m_publish", schema = "blog-db-001", catalog = "")
public class MPublishEntity {
    @Id
    @Column(name = "publish_id")
    private Long publishId;
    @Basic
    @Column(name = "publish_name")
    private String publishName;

    public Long getPublishId() {
        return publishId;
    }

    public void setPublishId(Long publishId) {
        this.publishId = publishId;
    }

    public String getPublishName() {
        return publishName;
    }

    public void setPublishName(String publishName) {
        this.publishName = publishName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MPublishEntity that = (MPublishEntity) o;

        if (publishId != null ? !publishId.equals(that.publishId) : that.publishId != null) return false;
        if (publishName != null ? !publishName.equals(that.publishName) : that.publishName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = publishId != null ? publishId.hashCode() : 0;
        result = 31 * result + (publishName != null ? publishName.hashCode() : 0);
        return result;
    }
}
