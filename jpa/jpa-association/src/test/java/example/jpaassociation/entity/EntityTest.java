package example.jpaassociation.entity;

import example.jpaassociation.entity.pk.DeptPk;
import example.jpaassociation.entity.pk.MemberPk;
import example.jpaassociation.entity.unique.DeptUnique;
import example.jpaassociation.entity.unique.MemberUnique;
import example.jpaassociation.repository.DeptPkRepository;
import example.jpaassociation.repository.DeptUniqueRepository;
import example.jpaassociation.repository.MemberPkRepository;
import example.jpaassociation.repository.MemberUniqueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
@Rollback(false)
public class EntityTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    DeptPkRepository deptPkRepository;
    @Autowired
    DeptUniqueRepository deptUniqueRepository;

    @Autowired
    MemberPkRepository memberPkRepository;
    @Autowired
    MemberUniqueRepository memberUniqueRepository;

    @BeforeEach
    public void init() {
        System.out.println("------------------ init pk ------------------");
        DeptPk root = new DeptPk(UUID.randomUUID().toString(), "JPA 컴퍼니", null);
        DeptPk sales = new DeptPk(UUID.randomUUID().toString(), "Sales", root);
        DeptPk account = new DeptPk(UUID.randomUUID().toString(), "Accounting", root);
        DeptPk subSales1 = new DeptPk(UUID.randomUUID().toString(), "sub Sales1", sales);
        DeptPk subSales2 = new DeptPk(UUID.randomUUID().toString(), "sub Sales2", sales);

        em.persist(root);
        em.persist(sales);
        em.persist(account);
        em.persist(subSales1);
        em.persist(subSales2);


        em.persist(new MemberPk(UUID.randomUUID().toString(), "salesKing123", subSales1));

        System.out.println("\n------------------ init uk ------------------\n");

        DeptUnique rootUk = new DeptUnique(UUID.randomUUID().toString(), "JPA 컴퍼니", null);
        DeptUnique salesUk = new DeptUnique(UUID.randomUUID().toString(), "Sales", rootUk);
        DeptUnique accountUk = new DeptUnique(UUID.randomUUID().toString(), "Accounting", rootUk);
        DeptUnique subSalesUk1 = new DeptUnique(UUID.randomUUID().toString(), "sub Sales1", salesUk);
        DeptUnique subSalesUk2 = new DeptUnique(UUID.randomUUID().toString(), "sub Sales2", salesUk);

        em.persist(rootUk);
        em.persist(salesUk);
        em.persist(accountUk);
        em.persist(subSalesUk1);
        em.persist(subSalesUk2);


        em.persist(new MemberUnique(UUID.randomUUID().toString(), "salesKing123", subSalesUk1));

        em.flush();
        em.clear();

        System.out.println("\n------------------ initialization completed ------------------\n");
    }

    @Test
    public void 연관관계_unique() {

        MemberUnique memberUnique = memberUniqueRepository.findAll().stream().findAny().get();

        List<DeptUnique> foundResults = deptUniqueRepository.findAll();
        foundResults.forEach(System.out::println);

        DeptUnique foundDeptUK = foundResults.stream()
                .filter(uk -> !uk.getName().equals(memberUnique.getDept().getName()))
                .findAny().get();

        memberUnique.changeDept(foundDeptUK);
    }

    @Test
    public void 연관관계_pk() {
        MemberPk memberPk = memberPkRepository.findAll().stream()
                .findAny().get();

        List<DeptPk> foundResults = deptPkRepository.findAll();
        foundResults.forEach(System.out::println);

        DeptPk foundDeptPk = foundResults.stream()
                .filter(pk -> !pk.getName().equals(memberPk.getDept().getName()))
                .findAny().get();

        memberPk.changeDept(foundDeptPk);
    }

    @Test
    public void 연관관계_jpql() {
        List<DeptUnique> resultList = em.createQuery("select d from DeptUnique d join fetch d.parent p", DeptUnique.class)
                .getResultList();

        for (DeptUnique deptUnique : resultList) {
            System.out.println("deptUnique = " + deptUnique);
        }

        List<DeptPk> resultList1 = em.createQuery("select d from DeptPk d join fetch d.parent p", DeptPk.class)
                .getResultList();

        for (DeptPk deptPk : resultList1) {
            System.out.println("deptPk = " + deptPk);
        }
    }
}
