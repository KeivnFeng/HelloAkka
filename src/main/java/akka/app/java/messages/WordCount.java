package akka.app.java.messages;

/**
 * Created by Kevin on 11/4/2017.
 */
public class WordCount {

    private String word;
    private Integer count;

    public WordCount(String inWord, Integer inCount){
        word = inWord;
        count = inCount;
    }

    public String getWord() {
        return word;
    }

    public Integer getCount() {
        return count;
    }
}
