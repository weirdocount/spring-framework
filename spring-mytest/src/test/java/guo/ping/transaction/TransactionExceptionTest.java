package guo.ping.transaction;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: guoping wang
 * @date: 2018/11/3 10:56
 * @project: spring
 */
public class TransactionExceptionTest {

	ActorTestService actorTestService;

	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("transaction-Test.xml");
		actorTestService = (ActorTestService) context.getBean("actorTestService");
	}

	/**
	 * 外部没有事务，内部两个都是required ，外部异常，内部不回滚
	 */
	@Test
	public void notransaction_exception_required_required() {
		actorTestService.notransaction_exception_required_required("hel1lo");
	}

	/**
	 * 外部没有事务，内部两个都是required ，一个异常，异常的回滚，另外一个不回滚
	 */
	@Test
	public void notransaction_required_required_exception() {
		actorTestService.notransaction_required_required_exception("hel11lo");
	}

	/**
	 * 方法内部调用 外部没有事务，内部有，外部异常 内部不回滚
	 * 方法内部调用 外部有事务，内部没有，外部异常 内部回滚
	 */
	@Test
	public void notransaction_internel_noexception() {
		actorTestService.notransaction_internel_noexception("1121");
	}

	/**
	 * 方法内部调用 外部、内部有事务（REQUIRED，REQUIRED），外部异常 内部回滚
	 * 方法内部调用 外部、内部有事务（REQUIRED，REQUIRED_NEW），外部异常 内部回滚
	 * 方法内部调用 外部、内部有事务（REQUIRED，NESTED），外部异常 内部回滚
	 */
	@Test
	public void transaction_internel_noexception() {
		actorTestService.transaction_internel_noexception("111");
	}


	/**
	 * 外部有事务，内部两个都是required ，外部异常，两个都回滚
	 */
	@Test
	public void transaction_exception_required_required() {
		actorTestService.transaction_exception_required_required("1111");
	}

	/**
	 * 外部有事务，内部两个都是NESTED ，外部异常，两个都回滚
	 */
	@Test
	public void transaction_exception_nested_nested() {
		actorTestService.transaction_exception_nested_nested("11111");
	}

	/**
	 * 外部有事务，内部两个都是NESTED ，一个内部异常，两个都回滚
	 */
	@Test
	public void transaction_nested_nested_exception() {
		actorTestService.transaction_nested_nested_exception("11111");
	}

	/**
	 * 外部有事务，内部两个都是NESTED ，一个内部异常捕获，异常那一个回滚，另一个不回滚
	 */
	@Test
	public void transaction_nested_nested_exception_try() {
		actorTestService.transaction_nested_nested_exception_try("11111");
	}

	/**
	 * 外部有事务，内部两个都是required ，外部异常，两个不回滚
	 */
	@Test
	public void notransaction_exception_requiresNew_requiresNew() {
		actorTestService.notransaction_exception_requiresNew_requiresNew("11");
	}

	@Test
	public void notransaction_requiresNew_requiresNew_exception() {
		actorTestService.notransaction_requiresNew_requiresNew_exception("1a1");
	}
}
