package akka.app.java.messages;

import java.util.HashMap;

/**
 * Created by Kevin on 11/4/2017.
 */
public class ReduceData {

    private HashMap<String, Integer> reduceDataList;

    public HashMap<String, Integer> getReduceDataList() {
        return reduceDataList;
    }

    public void setReduceDataList(final HashMap<String, Integer> reduceDataList) {
        this.reduceDataList = reduceDataList;
    }
}
