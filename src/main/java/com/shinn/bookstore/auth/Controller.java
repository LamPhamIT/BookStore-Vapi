package com.shinn.bookstore.auth;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test/auth")
public class Controller {
    @GetMapping("/authen")
    public String sayHelloFromServer() {
        return "Hello Client";
    }

    @GetMapping("/author")
    public String sayHelloFromServerV2() {
        return "Hello Admin";
    }
}
