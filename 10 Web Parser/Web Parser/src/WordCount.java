import org.jsoup.nodes.Document;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by HAWK-VAIO on 3/14/2017.
 */
public class WordCount extends JScrollPane {
    String text;
    HashMap<String, Integer> counter;
    JPanel panel;
    ArrayList<Map.Entry<String, Integer>> sorted;

    WordCount(Document doc){
        counter = new HashMap<String, Integer>();
        text = doc.body().text();
        countWords();
        sorted = sortByValues(counter);
        panel = new JPanel(new GridLayout(counter.size(),2 ));
        for(Map.Entry<String, Integer> entry:sorted){
            JLabel wrd = new JLabel(entry.getKey());
            JLabel val = new JLabel(entry.getValue().toString());
            panel.add(wrd);
            panel.add(val);

        }
        setViewportView(panel);
        setPreferredSize(new Dimension(350, 350));
    }

    private ArrayList<Map.Entry<String,Integer>> sortByValues(HashMap<String, Integer> map) {
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Comparator<Map.Entry<String, Integer>> byMapValues = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> left, Map.Entry<String, Integer> right) {
                return right.getValue().compareTo(left.getValue());
            }
        };
        Collections.sort(list, byMapValues);
        return list;
    }

    private void countWords() {
        StringTokenizer st = new StringTokenizer(text);
        while(st.hasMoreTokens()){
            String word = st.nextToken();
            int count = counter.containsKey(word)?counter.get(word):0;
            counter.put(word, count + 1);
        }
    }
}
