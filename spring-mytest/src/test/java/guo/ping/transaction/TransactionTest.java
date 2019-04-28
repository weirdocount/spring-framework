package guo.ping.transaction;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;

/**
 * @description:
 * @author: guoping wang
 * @date: 2018/11/3 10:56
 * @project: spring
 */
public class TransactionTest {

	@Test
	public void testTransaction() {
		ApplicationContext context = new ClassPathXmlApplicationContext("transaction-Test.xml");
		IActorService actorService = (IActorService) context.getBean("actorService");
		Actor actor = new Actor(null, "事务", "测试", new Timestamp(System.currentTimeMillis()));
		actorService.save(actor);
	}

	@Test
	public void testTransaction1() {
		ApplicationContext context = new ClassPathXmlApplicationContext("transaction-Test.xml");
		ActorTestService actorTestService = (ActorTestService) context.getBean("actorTestService");
//		actorTestService.testWithTx(7);
	}

	/**
	 * 1.主方法不加事务（异常），从方法 REQUIRES_NEW 结果-》主不回滚，从回滚
	 * 2.主方法不加事务（异常），从方法 NESTED 结果-》主不回滚，从回滚
	 * 3。主方法加默认事务（异常），从方法 REQUIRES_NEW 结果-》主回滚，从回滚
	 * 4。主方法加默认事务（异常），从方法 NESTED 结果-》主回滚，从回滚
	 */
	@Test
	public void testTx() {
		ApplicationContext context = new ClassPathXmlApplicationContext("transaction-Test.xml");
		ActorTestService actorTestService = (ActorTestService) context.getBean("actorTestService");
		actorTestService.testTwoUpdate("hello121");
	}

	/**
	 * 测试内部调用 没有的调用有的，事务失效
	 * 预计效果是 7 的名字改为hi 事务没有回滚
	 */
	@Test
	public void testTx1() {
		ApplicationContext context = new ClassPathXmlApplicationContext("transaction-Test.xml");
		ActorTestService1 actorTestService = (ActorTestService1) context.getBean("actorTestService1");
//		actorTestService.updateNameWithTxNested("haha11",7);
		actorTestService.testWithoutWx();
	}

	/**
	 * 测试事务生效 @Transactional(propagation = Propagation.NESTED) 回滚
	 * 测试事务生效 @Transactional(propagation = Propagation.REQUIRES_NEW) 回滚
	 */
	@Test
	public void testTx2() {
		ApplicationContext context = new ClassPathXmlApplicationContext("transaction-Test.xml");
		ActorTestService1 actorTestService = (ActorTestService1) context.getBean("actorTestService1");
//		actorTestService.updateNameWithTxNested("haha11",7);
		actorTestService.updateNameWithTxNested("hehe", 7);
	}

}
