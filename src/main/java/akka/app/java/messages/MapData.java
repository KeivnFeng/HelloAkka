package akka.app.java.messages;

import java.util.List;


public class MapData {

    private List<WordCount> dataList;

    public MapData(final List<WordCount> dataList) {
    }

    public List<WordCount> getDataList() {
        return dataList;
    }

    public void setDataList(final List<WordCount> dataList) {
        this.dataList = dataList;
    }
}
