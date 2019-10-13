package guo.ping.transaction;

import org.springframework.transaction.annotation.Transactional;

public class ActorTestService {

	private IActorService actorService;

	private ActorTestService1 actorTestService1;

	private ActorTestService2 actorTestService2;

	public void notransaction_exception_required_required(String name){
		actorTestService1.updateName(name,1);
		actorTestService2.updateName(name+"1",7);
		int i = 10 / 0;
	}

	public void notransaction_required_required_exception(String name){
		actorTestService1.updateName(name,1);
		actorTestService2.updateNameWithException(name+"1",7);
	}

	public void notransaction_internel_noexception(String name){
		updateName(name, 1);
		int i = 10 / 0;
	}

	@Transactional
	public void transaction_internel_noexception(String name){
		updateName(name, 1);
		int i = 10 / 0;
	}


	public void updateName(String userName, Integer id){
		actorService.updateActoe(userName, id);
	}


	@Transactional
	public void transaction_exception_required_required(String name){
		actorTestService1.updateName(name,1);
		actorTestService2.updateName(name+"1",7);
		int i = 10 / 0;
	}

	@Transactional
	public void transaction_exception_nested_nested(String name){
		actorTestService1.updateName(name,1);
		actorTestService2.updateName(name+"1",7);
		int i = 10 / 0;
	}

	@Transactional
	public void transaction_nested_nested_exception(String name){
		actorTestService1.updateName(name,1);
		actorTestService2.updateNameWithException(name+"1",7);
	}

	@Transactional
	public void transaction_nested_nested_exception_try(String name){
		actorTestService1.updateName(name,1);
		try {
			actorTestService2.updateNameWithException(name+"1",7);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void notransaction_exception_requiresNew_requiresNew(String name){
		actorTestService1.updateName(name,1);
		actorTestService2.updateName(name+"1",7);
		int i = 10 / 0;
	}

	@Transactional
	public void notransaction_requiresNew_requiresNew_exception(String name){
		actorTestService1.updateName(name,1);
		actorTestService2.updateNameWithException(name+"1",7);
	}


	public void setActorService(IActorService actorService) {
		this.actorService = actorService;
	}

	public void setActorTestService1(ActorTestService1 actorTestService1) {
		this.actorTestService1 = actorTestService1;
	}

	public void setActorTestService2(ActorTestService2 actorTestService2) {
		this.actorTestService2 = actorTestService2;
	}
}
