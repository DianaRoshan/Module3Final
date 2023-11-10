import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

public class GameServletTest {
    @Test
    public void doGetTest() throws ServletException, IOException {

        GameServlet gameServlet = new GameServlet();
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        String pathWin = "/win.jsp";
        String pathLose = "/lose.jsp";
        String path = "/game.jsp";
        String questionTest = "Ты потерял память. Принять вызов НЛО?";
        List<String> answerTest = List.of("Принять вызов", "Отклонить вызов");

        //когда будет вызван request.getRequestDispatcher(path), тогда вернется dispatcher. иначе будет NPE у dispatcher
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getRequestDispatcher(pathWin)).thenReturn(dispatcher);
        when(request.getRequestDispatcher(pathLose)).thenReturn(dispatcher);

        when(request.getSession()).thenReturn(session);
        when(request.getAttribute("question")).thenReturn(1);
        when(request.getAttribute("answers")).thenReturn(2);

        GameServlet.setQuestionNumber(0);
        gameServlet.doGet(request, response);
        verify(request, times(1)).setAttribute("question", questionTest);
        verify(request, times(1)).setAttribute("answers", answerTest);

        Assertions.assertEquals(1, request.getAttribute("question"));
        Assertions.assertEquals(2, request.getAttribute("answers"));

        verify(request, times(1)).getRequestDispatcher(path);
        verify(dispatcher, times(1)).forward(request, response);

        GameServlet.setQuestionNumber(3);
        gameServlet.doGet(request, response);
        verify(session, times(1)).invalidate();
    }

    @Test
    public void doPostTest() throws IOException, ServletException {
        GameServlet gameServlet = mock(GameServlet.class);
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        when(request.getParameter("answer")).thenReturn("1");
        when(request.getSession()).thenReturn(session);

        gameServlet.doPost(request, response);
        verify(response, times(1)).setCharacterEncoding("UTF-8");
        verify(response, times(1)).setContentType("text/html;charset=UTF-8");
        verify(request, times(1)).setCharacterEncoding("UTF-8");
        gameServlet.doGet(request, response);
        verify(gameServlet, times(1)).doGet(request, response);
        session.invalidate();
        verify(session, times(1)).invalidate();
    }

}
