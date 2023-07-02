package com.example.jpaboard.service;

import com.example.jpaboard.controller.dto.BoardRegisterForm;
import com.example.jpaboard.controller.dto.BoardUpdate;
import com.example.jpaboard.controller.dto.DetailBoardDto;
import com.example.jpaboard.domain.Board;
import com.example.jpaboard.domain.Member;
import com.example.jpaboard.repository.BoardRepository;
import com.example.jpaboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void register(BoardRegisterForm form) {
        Member writer = memberRepository.save(new Member(form.getWriter(), form.getPassword()));

        Board board = new Board(form.getTitle(), form.getContent(), writer);
        boardRepository.save(board);
    }

    @Transactional
    public DetailBoardDto viewDetail(Long id) {
        Board board = getBoardAfterValidation(id);
        // 조회된 entity 의 viewCount 를 직접 증가시키면 Auditing 기능 때문에 lastModifiedDate 의 값이 변경되므로
        // 직접 jpql 을 통해 수정 처리
        boardRepository.increaseViewCount(board.getId());

        return new DetailBoardDto(board).increaseViewCount();
    }

    private Board getBoardAfterValidation(Long id) {
        Optional<Board> foundBoard = boardRepository.findById(id);
        if(foundBoard.isEmpty()) throw new RuntimeException("there is no foundBoard");

        return foundBoard.get();
    }

    @Transactional
    public void modify(Long id, BoardUpdate boardUpdate) {
        getBoardAfterValidation(id).modifiedWith(boardUpdate);
    }
}
