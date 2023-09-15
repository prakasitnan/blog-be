package com.github.prakasitnan.blogbe.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "post", schema = "blog-db-001", catalog = "")
public class PostEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "post_id")
    private long postId;
    @Basic
    @Column(name = "post_title")
    private String postTitle;
    @Basic
    @Column(name = "post_detail")
    private String postDetail;
    @Basic
    @Column(name = "status_id")
    private Long statusId;
    @Basic
    @Column(name = "cate_id")
    private Long cateId;
    @Basic
    @Column(name = "img_banner_url")
    private String imgBannerUrl;
    @Basic
    @Column(name = "img_banner")
    private String imgBanner;
    @Basic
    @Column(name = "publish_id")
    private Long publishId;
    @Basic
    @Column(name = "create_by")
    private Long createBy;
    @Basic
    @Column(name = "create_date")
    private Timestamp createDate;
    @Basic
    @Column(name = "update_by")
    private Long updateBy;
    @Basic
    @Column(name = "update_date")
    private Timestamp updateDate;


    @OneToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(column = @JoinColumn(name = "cate_id", referencedColumnName = "cate_id", insertable = false, updatable = false))
    })
    private MCategoryEntity mCategoryEntityList;

    @OneToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(column = @JoinColumn(name = "create_by", referencedColumnName = "user_id", insertable = false, updatable = false))
    })
    private UserEntity userEntity;


    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDetail() {
        return postDetail;
    }

    public void setPostDetail(String postDetail) {
        this.postDetail = postDetail;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getCateId() {
        return cateId;
    }

    public void setCateId(Long cateId) {
        this.cateId = cateId;
    }

    public String getImgBannerUrl() {
        return imgBannerUrl;
    }

    public void setImgBannerUrl(String imgBannerUrl) {
        this.imgBannerUrl = imgBannerUrl;
    }

    public String getImgBanner() {
        return imgBanner;
    }

    public void setImgBanner(String imgBanner) {
        this.imgBanner = imgBanner;
    }

    public Long getPublishId() {
        return publishId;
    }

    public void setPublishId(Long publishId) {
        this.publishId = publishId;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public MCategoryEntity getmCategoryEntityList() {
        return mCategoryEntityList;
    }

    public void setmCategoryEntityList(MCategoryEntity mCategoryEntityList) {
        this.mCategoryEntityList = mCategoryEntityList;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostEntity that = (PostEntity) o;

        if (postId != that.postId) return false;
        if (postTitle != null ? !postTitle.equals(that.postTitle) : that.postTitle != null) return false;
        if (postDetail != null ? !postDetail.equals(that.postDetail) : that.postDetail != null) return false;
        if (statusId != null ? !statusId.equals(that.statusId) : that.statusId != null) return false;
        if (cateId != null ? !cateId.equals(that.cateId) : that.cateId != null) return false;
        if (imgBannerUrl != null ? !imgBannerUrl.equals(that.imgBannerUrl) : that.imgBannerUrl != null) return false;
        if (imgBanner != null ? !imgBanner.equals(that.imgBanner) : that.imgBanner != null) return false;
        if (publishId != null ? !publishId.equals(that.publishId) : that.publishId != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (postId ^ (postId >>> 32));
        result = 31 * result + (postTitle != null ? postTitle.hashCode() : 0);
        result = 31 * result + (postDetail != null ? postDetail.hashCode() : 0);
        result = 31 * result + (statusId != null ? statusId.hashCode() : 0);
        result = 31 * result + (cateId != null ? cateId.hashCode() : 0);
        result = 31 * result + (imgBannerUrl != null ? imgBannerUrl.hashCode() : 0);
        result = 31 * result + (imgBanner != null ? imgBanner.hashCode() : 0);
        result = 31 * result + (publishId != null ? publishId.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        return result;
    }
}
