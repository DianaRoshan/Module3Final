import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/game")
public class GameServlet extends HttpServlet {

    private final static List<String> questions = List.of("Ты потерял память. Принять вызов НЛО?",
                                                          "Ты принял вызов. Поднимешься на мостик к капитану?",
                                                          "Ты поднялся на мостик. Кто ты?");
    private final static List<List<String>> answers = List.of(List.of("Принять вызов", "Отклонить вызов"),
                                                              List.of("Подняться на мостик", "Отказаться подниматься на мостик"),
                                                              List.of("Рассказать правду о себе", "Солгать о себе"));
    private static boolean win;
    private static int questionNumber = 0;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (questionNumber < questions.size()) {
            String question = getQuestion(questionNumber);
            List<String> answers = getAnswers(questionNumber);
            req.setAttribute("question", question);
            req.setAttribute("answers", answers);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/game.jsp");
            dispatcher.forward(req, resp);
        }else if (questionNumber == questions.size()) {
            winOrLose(req, resp, true);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String selectedAnswer = req.getParameter("answer");

        List<String> currentAnswers = getAnswers(questionNumber);
        for (String s : currentAnswers) {
            if (selectedAnswer.equals(s)) {
                if (checkAnswer(questionNumber, currentAnswers.indexOf(s))) {
                    questionNumber++;
                    doGet(req, resp);
                } else {
                    winOrLose(req, resp, false);
                }
                break;
            }
        }

    }

    private boolean checkAnswer(int questionNumber, int answerNumber) {
        switch (questionNumber){
            case 0,1,2 ->{
                if(answerNumber==0){
                    return true;
                }
            }
        }
        return false;
    }

    public static String getQuestion(int index) {
        return questions.get(index);
    }


    public static List<String> getAnswers(int index) {
        return answers.get(index);
    }


    public static void winOrLose(HttpServletRequest req, HttpServletResponse resp, boolean isWin) throws ServletException, IOException {
        win = isWin;
        req.getSession().invalidate();
        questionNumber = 0;

        if(isWin){
            req.getRequestDispatcher("/win.jsp").forward(req, resp);
        } else{
            req.getRequestDispatcher("/lose.jsp").forward(req, resp);
        }
    }
}
