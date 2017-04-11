package akka.app.java.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.app.java.messages.MapData;
import akka.app.java.messages.WordCount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MapActor extends UntypedActor{

    private ActorRef reduceActor = null;

    private static final String[] STOP_WORDS = {"a", "is"};

    private static final List<String> STOP_WORDS_LIST = Arrays.asList(STOP_WORDS);

    public MapActor(final ActorRef inReduceActor){reduceActor = inReduceActor;}

    @Override
    public void onReceive(final Object message) throws Exception {
        if(message instanceof String){
            final String work = (String)message;
            final MapData data = evaluateExpression(work);
            reduceActor.tell(data, reduceActor);
        }else{
            unhandled(message);
        }
    }

    private MapData evaluateExpression(final String line) {
        final List<WordCount> dataList = new ArrayList<WordCount>();
        final StringTokenizer parser = new StringTokenizer(line);
        while(parser.hasMoreTokens()){
            final String word = parser.nextToken().toLowerCase();
            if(!STOP_WORDS_LIST.contains(word)){
                dataList.add(new WordCount(word, 1));
            }
        }

        return new MapData(dataList);
    }
}
