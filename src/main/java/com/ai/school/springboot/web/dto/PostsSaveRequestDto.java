package com.ai.school.springboot.web.dto;

import com.ai.school.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // Entity 로 변환
    public Posts toEntity(){
        return Posts.builder().title(title).content(content).author(author).build();
    }

}
