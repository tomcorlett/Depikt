package corlett.depikt.dev.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corlett.depikt.dev.model.Member;

@RestController
@RequestMapping(path = "api/v1/login")
public class LoginController {
    @PostMapping("/login")
    public void login(@RequestBody Member member) {}
}
