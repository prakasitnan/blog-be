package com.github.prakasitnan.blogbe.model;

import jakarta.persistence.*;

@Entity
@Table(name = "m_status", schema = "blog-db-001", catalog = "")
public class MStatusEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "status_id")
    private long statusId;
    @Basic
    @Column(name = "status_name")
    private String statusName;

    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MStatusEntity that = (MStatusEntity) o;

        if (statusId != that.statusId) return false;
        if (statusName != null ? !statusName.equals(that.statusName) : that.statusName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (statusId ^ (statusId >>> 32));
        result = 31 * result + (statusName != null ? statusName.hashCode() : 0);
        return result;
    }
}
