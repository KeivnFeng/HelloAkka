package akka.app.java.actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.app.java.messages.MapData;
import akka.app.java.messages.ReduceData;
import akka.app.java.messages.WordCount;

import java.util.HashMap;
import java.util.List;

public class ReduceActor extends UntypedActor{

    private ActorRef aggregateActor = null;

    public ReduceActor(ActorRef inAggregateActor){
        aggregateActor = inAggregateActor;
    }

    @Override
    public void onReceive(final Object message) throws Exception {
        if(message instanceof MapData){
            final MapData mapData = (MapData) message;
            final ReduceData reduceData = reduce(mapData.getDataList());
            aggregateActor.tell(reduceData, aggregateActor);
        }else{
            unhandled(message);
        }
    }

    private ReduceData reduce(final List<WordCount> dataList) {
        final HashMap<String, Integer> reducedMap = new HashMap<String, Integer>();
        for(final WordCount wordCount : dataList){
            if(reducedMap.containsKey(wordCount.getWord())){
                Integer value = reducedMap.get(wordCount.getWord());
                value++;
                reducedMap.put(wordCount.getWord(), value);
            }else{
                reducedMap.put(wordCount.getWord(),reducedMap.get(wordCount.getWord()));
            }
        }
        final ReduceData data = new ReduceData();
        data.setReduceDataList(reducedMap);
        return data;
    }
}
