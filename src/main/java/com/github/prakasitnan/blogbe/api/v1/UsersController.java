package com.github.prakasitnan.blogbe.api.v1;

import com.github.prakasitnan.blogbe.api.v1.model.DataTableResult;
import com.github.prakasitnan.blogbe.api.v1.model.UserReq;
import com.github.prakasitnan.blogbe.exception.UserDuplicateException;
import com.github.prakasitnan.blogbe.model.UserEntity;
import com.github.prakasitnan.blogbe.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/users")
@SecurityRequirement(name = "Bearer Authentication")
public class UsersController {
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);
    UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/getDataTable", method = RequestMethod.GET)
    public ResponseEntity<?> getUserDataTable(@RequestParam(required = false, name = "search[value]") String search,
                                              @RequestParam(required = true, defaultValue = "0") int iDisplayStart,
                                              @RequestParam(required = true, defaultValue = "10") int iDisplayLength,
                                              @RequestParam(required = true, defaultValue = "0") int draw,
                                              @RequestParam(required = true, defaultValue = "0", name = "iSortCol_0") Integer sortIndex,
                                              @RequestParam(required = true, defaultValue = "asc", name = "sSortDir_0") String sortDir) {
        int page = (iDisplayStart > 1 ) ? iDisplayStart - 1 : 0;
        DataTableResult result = userService.getDataTable(search, sortIndex, sortDir, page, iDisplayLength, draw);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<?> saveUser(@Valid UserReq userReq) throws UserDuplicateException {
        UserEntity user = userService.save(userReq);
        return ResponseEntity.ok(user);
    }

}
