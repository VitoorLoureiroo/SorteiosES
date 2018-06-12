package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ApostadorDAO;
import dao.ApostadorDAOImpl;
import model.Apostador;

/**
 * Servlet implementation class login
 */
@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println( "tem coisa" );
		ApostadorDAO apostadorDao = new ApostadorDAOImpl();
		List<Apostador> lista =apostadorDao.pesquisarApostador(request.getParameter("cpf"));
		if (lista.size()!=0) {
			if(lista.get(0).getSenha().equals(request.getParameter("senha"))){
				response.sendRedirect("./sorteios.jsp");
			}else {		
				String message = "Senha Inválida.";
				request.getSession().setAttribute("MESSAGE", message);
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("btn").equals("acessar")){
			doGet(request, response);
		}else if (request.getParameter("btn").equals("cadastrar")) {
			response.sendRedirect("./cadastro.jsp");
		}
		
	}

}
