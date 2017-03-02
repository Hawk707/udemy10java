import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * Created by HAWK-VAIO on 2/22/2017.
 */
@WebServlet(name = "Verify")
public class Verify extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String data = reader.readLine();
        BufferedReader input = new BufferedReader(new FileReader("E:\\Oracle\\Java\\Udemy\\10 Projects in Java\\07 Servlet\\EditorApplet\\passwords.txt"));
        StringTokenizer userData = new StringTokenizer(data);
        String user = userData.nextToken();
        String pass = userData.nextToken();
        String storedPass = null;
        String line = input.readLine();
        while(line != null){
            StringTokenizer st = new StringTokenizer(line);
            if(user.equals(st.nextToken())){
                storedPass = st.nextToken();
                break;
            }
            line = input.readLine();
        }
        input.close();
        OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
        if(storedPass.equals(pass)){
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            writer.write(user+" user has logged in");
        }
        else{
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            writer.write("Unable to authenticate "+user);
        }
        writer.flush();
        writer.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
