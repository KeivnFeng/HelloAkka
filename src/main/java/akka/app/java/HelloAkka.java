package akka.app.java;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.app.java.actors.MasterActor;
import akka.app.java.messages.Result;


public class HelloAkka {

    public static void main(String[] args) throws Exception{

        final ActorSystem _system = ActorSystem.create("HelloAkka");
        final ActorRef master = _system.actorFor(new Props(MasterActor.class), "master");

        master.tell("Hi! My name is Rocky, I'm so so so so happy to be here. ", master);
        master.tell("Today, I'm going to read a news article for you. ", master);
        master.tell("I hope I hope you'll like it.", master);
        Thread.sleep(500);
        master.tell(new Result(), master);
        Thread.sleep(500);
        _system.shutdown();

    }
}
