package com.example.jpaboard.repository;

import com.example.jpaboard.controller.dto.BoardUpdate;
import com.example.jpaboard.domain.Board;
import com.example.jpaboard.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;
    @PersistenceContext
    private EntityManager em;


    @Test
    public void 게시글_추가() {
        //given
        Member member = new Member("james", "1234");
        memberRepository.save(member);

        String title = "Spring Data JPA in Spring boot";
        String content = "it's very nice";
        Board board = new Board(title, content, member);
        boardRepository.save(board);

        em.flush();
        em.clear();
        System.out.println("------------------------------------------");

        //when
        Board findOne = boardRepository.findByTitleLike("%JPA%").get(0);
        Member writer = findOne.getMember();

        //then
        assertThat(findOne.getTitle()).isEqualTo(title);
        assertThat(findOne.getContent()).isEqualTo(content);

        assertThat(writer.getName()).isEqualTo(member.getName());
        assertThat(writer.getPassword()).isEqualTo(member.getPassword());
    }

    @Test
    public void 게시물_목록조회() {
        // given
        Member member = new Member("tester", "password");
        memberRepository.save(member);

        String title = "title for JPA";
        String content = "create code faster!!!";

        for(int i=0; i<10; i++) {
            Board board = new Board(title + i, content + i, member);
            boardRepository.save(board);
        }

        em.flush();
        em.clear();

        // when
        int page = 0;
        int size = 5;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "title"));
        Page<Board> currentPage = boardRepository.findAll(pageRequest);

        assertThat(currentPage.getNumber()).isEqualTo(page);
        assertThat(currentPage.getSize()).isEqualTo(size);
        assertThat(currentPage.getTotalElements()).isEqualTo(10);
        assertThat(currentPage.isFirst());
        assertThat(currentPage.hasNext());

        for (Board board : currentPage.getContent()) {
            System.out.println(board);
        }
    }

    @Test
    public void 개별게시물_조회() throws InterruptedException {
        //given
        Member member = new Member("james", "1234");
        memberRepository.save(member);

        String title = "Spring Data JPA in Spring boot";
        String content = "it's very nice";
        Board board = new Board(title, content, member);
        boardRepository.save(board);

        em.flush();
        em.clear();
        System.out.println("------------------------------------------");

        //when
        Thread.sleep(20000);
        boardRepository.increaseViewCount(board.getId());

        //then
        Board found = boardRepository.findById(board.getId()).get();

        assertThat(found.getViewCount()).isEqualTo(1);
        assertThat(found.getCreatedDate()).isEqualTo(found.getModifiedDate());
    }

    @Test
    public void 변경감지_테스트() {
        //given
        Member member = new Member("james", "1234");
        memberRepository.save(member);

        String title = "Spring Data JPA in Spring boot";
        String content = "it's very nice";
        Board board = new Board(title, content, member);
        boardRepository.save(board);

        em.flush();
        em.clear();

        // when
        BoardUpdate update = new BoardUpdate();
        update.setTitle("test");
        update.setContent("test content");

        Board found = boardRepository.findById(board.getId()).get();
        found.modifiedWith(update);
    }

    /**
     * Application 과 별개 테스트 - 쿼리 실행 확인용
     */
    @Test
    public void jpql_fetch_join_test() {
        Member member = new Member("james", "1234");
        em.persist(member);

        String title = "Spring Data JPA in Spring boot";
        String content = "it's very nice";
        Board board = new Board(title, content, member);
        em.persist(board);

        em.flush();
        em.clear();

        Board foundBoard = em.createQuery("select b from Board b join b.member", Board.class)
                .getSingleResult();

        System.out.println(foundBoard + foundBoard.getMember().toString());

        em.flush();
        em.clear();

        Board foundBoard2 = em.createQuery("select b from Board b join fetch b.member", Board.class).getSingleResult();

        System.out.println(foundBoard2 + foundBoard2.getMember().toString());
    }
}