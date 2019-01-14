package com.example.flowableactivity.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class SwaggerController {

    @RequestMapping("/api")
    public String redirectSwagger() {
        return "redirect:swagger-ui.html";
    }
}
