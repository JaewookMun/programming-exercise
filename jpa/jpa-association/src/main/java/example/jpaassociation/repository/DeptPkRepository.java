package example.jpaassociation.repository;

import example.jpaassociation.entity.pk.DeptPk;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeptPkRepository extends JpaRepository<DeptPk, String> {

    @EntityGraph(attributePaths = {"parent"})
    List<DeptPk> findByName(String name);

    @EntityGraph(attributePaths = {"parent"})
    @Override
    List<DeptPk> findAll();
}
