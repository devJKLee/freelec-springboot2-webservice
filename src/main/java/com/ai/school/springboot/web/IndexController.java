package com.ai.school.springboot.web;

import com.ai.school.springboot.config.auth.LoginUser;
import com.ai.school.springboot.config.auth.dto.SessionUser;
import com.ai.school.springboot.domain.user.User;
import com.ai.school.springboot.service.PostsService;
import com.ai.school.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            // userName variable 사용 금지. userName 으로 넘겨줄 시 view template engine 에서, window os 의 %UserName% 을 가져다 쓰는 문제가 있었음
            model.addAttribute("profileName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }

}
