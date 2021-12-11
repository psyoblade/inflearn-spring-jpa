package me.suhyuk.springjpa;

import me.suhyuk.springjpa.strategy.IdentityType;
import me.suhyuk.springjpa.strategy.SequenceType;
import me.suhyuk.springjpa.strategy.TableType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootApplication
public class SpringJpaApplication {

	// 아이덴티티 트리거는 롤백 되었음
	public static void tryIdentity(EntityManager em) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			IdentityType identity = new IdentityType("아이덴티티");
			em.persist(identity);
			System.out.println(identity);
			if (identity.getName().equals("아이덴티티"))
				throw new RuntimeException("아이덴티티 오류 발생");
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			System.out.println("아이덴티티 롤백 호출");
		}
	}


	// 시퀀스 트리거는 롤백 되었음
	public static void trySequence(EntityManager em) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			SequenceType sequence = new SequenceType("시퀀스");
			em.persist(sequence);
			System.out.println(sequence);
			if (sequence.getName().equals("시퀀스"))
				throw new RuntimeException("시퀀스 오류 발생");
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			System.out.println("시퀀스 롤백 호출");
		}
	}

	// 테이블 트리거는 롤백되지 않음
	public static void tryTable(EntityManager em) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			TableType table = new TableType("테이블");
			em.persist(table);
			System.out.println(table);
			if (table.getName().equals("테이블"))
				throw new RuntimeException("테이블 오류 발생");
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			System.out.println("테이블 롤백 호출");
		}
	}

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpashop");
		EntityManager em = emf.createEntityManager();
		tryIdentity(em);
		trySequence(em);
		tryTable(em);
		emf.close();
	}

}
