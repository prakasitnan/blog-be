package com.github.prakasitnan.blogbe.api.v1.model;

import org.springframework.web.multipart.MultipartFile;

public class PostSaveReq {
    private Long postId;
    private String postTitle;
    private String postDetail;
    private Long statusId;
    private Long cateId;
    private MultipartFile imgBanner;
    private String imgBannerDelete;
    private Long publishId;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
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

    public MultipartFile getImgBanner() {
        return imgBanner;
    }

    public void setImgBanner(MultipartFile imgBanner) {
        this.imgBanner = imgBanner;
    }

    public String getImgBannerDelete() {
        return imgBannerDelete;
    }

    public void setImgBannerDelete(String imgBannerDelete) {
        this.imgBannerDelete = imgBannerDelete;
    }

    public Long getPublishId() {
        return publishId;
    }

    public void setPublishId(Long publishId) {
        this.publishId = publishId;
    }
}
