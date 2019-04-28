package guo.ping.transaction;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public class ActorTestService1 {

	private IActorService actorService;

	public void setActorService(IActorService actorService) {
		this.actorService = actorService;
	}


	public void testWithoutWx(){
		updateNameWithTxNested("hi", 7);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateNameWithTxNested(String userName, Integer id){
		actorService.updateActoe(userName, id);
		int i = 10 / 0;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateName(String userName, Integer id){
		actorService.updateActoe(userName, id);
		int i = 10 / 0;
	}
}
