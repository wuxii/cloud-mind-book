package com.harmony.cmb.core.controller;

import com.harmony.umbrella.web.Response;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author wuxii
 */
@RestController
public class IndexController {

    @PostMapping("/login")
    public Response<String> login() {
        return Response.ok(UUID.randomUUID().toString());
    }

    @RequestMapping(path = "/logout", method = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE})
    public Response<String> logout() {
        return Response.ok();
    }

}
