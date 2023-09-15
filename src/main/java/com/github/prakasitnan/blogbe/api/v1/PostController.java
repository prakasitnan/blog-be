package com.github.prakasitnan.blogbe.api.v1;

import com.github.prakasitnan.blogbe.api.v1.base.BaseAPIController;
import com.github.prakasitnan.blogbe.api.v1.model.DataTableResult;
import com.github.prakasitnan.blogbe.api.v1.model.PostSaveReq;
import com.github.prakasitnan.blogbe.model.PostEntity;
import com.github.prakasitnan.blogbe.security.UserDetailsImpl;
import com.github.prakasitnan.blogbe.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/post")
public class PostController extends BaseAPIController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/getDataTable", method = RequestMethod.GET)
    public ResponseEntity<?> getDataTable(@RequestParam(required = false, name = "search[value]") String search,
                                          @RequestParam(required = true, defaultValue = "0") int iDisplayStart,
                                          @RequestParam(required = true, defaultValue = "10") int iDisplayLength,
                                          @RequestParam(required = true, defaultValue = "0") int draw,
                                          @RequestParam(required = true, defaultValue = "0", name = "iSortCol_0") Integer sortIndex,
                                          @RequestParam(required = true, defaultValue = "asc", name = "sSortDir_0") String sortDir) {
        int page = (iDisplayStart > 1 ) ? iDisplayStart - 1 : 0;
        DataTableResult result = postService.getDataTable(search, sortIndex, sortDir, page, iDisplayLength, draw);
        List<PostEntity> postList = (List<PostEntity>) result.getData();
        for (PostEntity item: postList) {
            if (item.getImgBannerUrl() != null)
                item.setImgBannerUrl(WEB_URL+item.getImgBannerUrl());
        }
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@RequestParam("postId") Long postId) {
        PostEntity post = postService.findByPostId(postId);
        if (post.getImgBannerUrl() != null)
            post.setImgBannerUrl(WEB_URL+post.getImgBannerUrl());
        return ResponseEntity.ok(post);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "multipart/form-data")
    @Operation(security = @SecurityRequirement(name = "Bearer Authentication"))
    public ResponseEntity<?> save(@Valid PostSaveReq postSaveReq) {
        UserDetailsImpl userDetails = getCurrentUser();
        PostEntity post = postService.save(postSaveReq, userDetails.getId());
        return ResponseEntity.ok(post);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @Operation(security = @SecurityRequirement(name = "Bearer Authentication"))
    public ResponseEntity<?> delete(@RequestParam("postId") Long postId) {
        return ResponseEntity.ok(postService.updateStatus(-9L, postId));
    }
}
