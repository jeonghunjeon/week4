package com.example.week3.Entity;

import com.example.week3.Dto.BlogRequestDto;
import com.example.week3.Dto.BlogResponseDto;
import com.example.week3.Repository.CommentRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Blog extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blogId;
    @ManyToOne
    @JoinColumn(name = "ID")
    private User user;
    @OneToMany(mappedBy = "blog") // Blog 삭제시 연관된 Comment까지 삭제
    private List<Comment> comments = new ArrayList<>();
    private String title;
    private String userName;
    private String content;

    public Blog(BlogRequestDto requestDto, User user) {
        this.user = user;
        this.title = requestDto.getTitle();
        this.userName = user.getUserName();
        this.content = requestDto.getContent();
    }
    public void update(BlogRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
