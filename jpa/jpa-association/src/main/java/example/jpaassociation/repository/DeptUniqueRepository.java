package example.jpaassociation.repository;

import example.jpaassociation.entity.unique.DeptUnique;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeptUniqueRepository extends JpaRepository<DeptUnique, Long> {

    @EntityGraph(attributePaths = {"parent"})
    List<DeptUnique> findByName(String name);

    @EntityGraph(attributePaths = {"parent"})
    @Override
    List<DeptUnique> findAll();
}
