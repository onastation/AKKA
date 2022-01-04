package callcenter.actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import callcenter.messages.Messages;
import org.junit.Test;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class CallReceiverActorTest {

    private ActorSystem system = ActorSystem.create("test");


    @Test
    public void testReplyWithEmptyReadingIfNoTemperatureIsKnown() {
        TestKit probe = new TestKit(system);
        ActorRef operatorActor = system.actorOf(CallReceiverActor.props("Operator"));
        operatorActor.tell(new Messages.CallReceived(), probe.getRef());
        Messages.CallFinished response = probe.expectMsgClass(new FiniteDuration(10,TimeUnit.SECONDS),Messages.CallFinished.class);
        assertEquals("Operator", response.getAttendedBy());

    }

}