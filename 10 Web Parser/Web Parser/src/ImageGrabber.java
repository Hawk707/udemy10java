import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;


/**
 * Created by HAWK-VAIO on 3/14/2017.
 */
public class ImageGrabber extends JScrollPane{
    Elements images;
    Document doc;
    JPanel panel;

    ImageGrabber(Document doc){
        this.doc = doc;
        images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        panel = new JPanel(new GridLayout(images.size(), 1));
        grabImages();
        setViewportView(panel);
        setPreferredSize(new Dimension(350, 350));
    }

    private void grabImages(){
        for(Element image : images){
            String l = image.attr("src");
            if(l.length()>0){
                if(l.length()<4){
                    l = doc.baseUri()+l.substring(1);
                }
                else if(!l.substring(0,4).equals("http")){
                    l = doc.baseUri()+l.substring(1);
                }
                SwingLink label = new SwingLink(l,l);
                panel.add(label);
            }
        }
    }
}
