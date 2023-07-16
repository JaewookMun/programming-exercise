package example.jpaassociation.repository;

import example.jpaassociation.entity.unique.MemberUnique;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberUniqueRepository extends JpaRepository<MemberUnique, Long> {

    @EntityGraph(attributePaths = "dept")
    @Override
    List<MemberUnique> findAll();
}
