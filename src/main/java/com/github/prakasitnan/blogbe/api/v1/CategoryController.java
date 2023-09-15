package com.github.prakasitnan.blogbe.api.v1;

import com.github.prakasitnan.blogbe.api.v1.base.BaseAPIController;
import com.github.prakasitnan.blogbe.api.v1.model.CateSaveReq;
import com.github.prakasitnan.blogbe.api.v1.model.DataTableResult;
import com.github.prakasitnan.blogbe.security.UserDetailsImpl;
import com.github.prakasitnan.blogbe.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController extends BaseAPIController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/getDataTable", method = RequestMethod.GET)
    public ResponseEntity<?> getDataTable(@RequestParam(required = false, name = "search[value]") String search,
                                          @RequestParam(required = true, defaultValue = "0") int iDisplayStart,
                                          @RequestParam(required = true, defaultValue = "10") int iDisplayLength,
                                          @RequestParam(required = true, defaultValue = "0") int draw,
                                          @RequestParam(required = true, defaultValue = "0", name = "iSortCol_0") Integer sortIndex,
                                          @RequestParam(required = true, defaultValue = "asc", name = "sSortDir_0") String sortDir) {
        int page = (iDisplayStart > 1 ) ? iDisplayStart - 1 : 0;
        DataTableResult result = categoryService.getDataTable(search, sortIndex, sortDir, page, iDisplayLength, draw);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/getCategoryById", method = RequestMethod.GET)
    public ResponseEntity<?> getCategoryById(@RequestParam("cateId") Long cateId) {
        return ResponseEntity.ok(categoryService.findByCateId(cateId));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "multipart/form-data")
    @Operation(security = @SecurityRequirement(name = "Bearer Authentication"))
    public ResponseEntity<?> save(@Valid CateSaveReq cateSaveReq) {
        UserDetailsImpl userDetails = getCurrentUser();
        categoryService.save(cateSaveReq, userDetails.getId());
        return ResponseEntity.ok(cateSaveReq);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @Operation(security = @SecurityRequirement(name = "Bearer Authentication"))
    public ResponseEntity<?> delete(@RequestParam("cateId") Long cateId) {
        return ResponseEntity.ok(categoryService.updateStatus(-9L, cateId));
    }
}
