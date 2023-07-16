package example.jpaassociation.repository;

import example.jpaassociation.entity.pk.MemberPk;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberPkRepository extends JpaRepository<MemberPk, Long> {

    @EntityGraph(attributePaths = "dept")
    @Override
    List<MemberPk> findAll();
}
