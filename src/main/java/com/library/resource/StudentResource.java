package com.library.resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/student")
public class StudentResource {

    @PostMapping
    public String getAllStudent() {
        return "Test";
    }
}
