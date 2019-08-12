package br.com.eaguiar.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.eaguiar.hangman.Hangman;

public class Control extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Control() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		HttpSession session = request.getSession(true);
		
		if("Start".equals(request.getParameter("sAction")) || "New Game".equals(request.getParameter("sAction"))) {
			String word = new Hangman().getXML();
			System.out.println(word);
			session.setAttribute("word", word); 
			request.setAttribute("attempts", 0); 
			request.setAttribute("letters", new Hangman().buildInputs(word));
			request.setAttribute("win", "no");
			request.getRequestDispatcher("game.jsp").forward(request,	response);
		}

		if(request.getParameter("sAction").equals("Try")) {
			request = new Hangman().check(request);
			if("yes".equals(request.getAttribute("win"))){
				request.getRequestDispatcher("win.jsp").forward(request,response);
				return;
			}
			
			request.getRequestDispatcher("game.jsp").forward(request,response);
			
		}
		
	}

}
