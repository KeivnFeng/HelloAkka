package akka.app.java.actors;

import akka.actor.UntypedActor;
import akka.app.java.messages.ReduceData;
import akka.app.java.messages.Result;

import java.util.HashMap;
import java.util.Map;

public class AggregateActor extends UntypedActor{

    private final Map<String, Integer> finalReducedMap = new HashMap<String, Integer>();

    @Override
    public void onReceive(final Object message) throws Throwable {
        if(message instanceof ReduceData){
            final ReduceData reduceData = (ReduceData) message;
            aggregateInMemoryReduce(reduceData.getReduceDataList());
        }else if(message instanceof Result){
            System.out.println(finalReducedMap.toString());
        }else{
            unhandled(message);
        }
    }

    private void aggregateInMemoryReduce(final HashMap<String, Integer> reduceDataList) {
        Integer count = null;
        for(final String key : reduceDataList.keySet()){
            if (finalReducedMap.containsKey(key)){
                count = reduceDataList.get(key) + finalReducedMap.get(key);
                finalReducedMap.put(key, count);
            }else{
                finalReducedMap.put(key, reduceDataList.get(key));
            }
        }
    }
}
