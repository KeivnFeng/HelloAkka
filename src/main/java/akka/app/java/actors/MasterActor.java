package akka.app.java.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.app.java.messages.Result;


public class MasterActor extends UntypedActor {

    private ActorRef aggregateActor = getContext().actorOf(new Props(AggregateActor.class), "aggregate");

    private final ActorRef reduceActor = getContext().actorOf(new Props(new ReduceActor(aggregateActor)), "reduce");

    private final ActorRef mapActor = getContext().actorOf(new Props(new MapActor(reduceActor)), "map");


    @Override
    public void onReceive(final Object message) throws Throwable {
        if (message instanceof String) {
            mapActor.tell(message, mapActor);
        } else if (message instanceof Result) {
            aggregateActor.tell(message, aggregateActor);
        } else {
            unhandled(message);
        }
    }
}
