package guo.ping.transaction;

import org.springframework.transaction.annotation.Transactional;

public class ActorTestService {

	private IActorService actorService;

	private ActorTestService1 actorTestService1;

	@Transactional
	public void testTwoUpdate(String name){
		actorService.updateActoe(name, 1);
		try {
			actorTestService1.updateName(name+"1",7);
		} catch (Exception e) {
		}
		int i = 10 / 0;
	}

	public void setActorService(IActorService actorService) {
		this.actorService = actorService;
	}

	public void setActorTestService1(ActorTestService1 actorTestService1) {
		this.actorTestService1 = actorTestService1;
	}


}
