package com.example.jpaboard.domain;

import com.example.jpaboard.controller.dto.BoardUpdate;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"member"})
@Getter
public class Board extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private int viewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Board(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public void modifiedWith(BoardUpdate boardUpdate) {
        title = boardUpdate.getTitle();
        content = boardUpdate.getContent();
    }
}
