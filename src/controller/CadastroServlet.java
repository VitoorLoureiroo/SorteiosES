package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ApostadorDAO;
import dao.ApostadorDAOImpl;
import model.Apostador;

/**
 * Servlet implementation class CadastroServlet
 */
@WebServlet("/CadastroServlet")
public class CadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		ApostadorDAO apostadorDao = new ApostadorDAOImpl();
		Apostador apostador = new Apostador(request.getParameter("nome"),
								request.getParameter("cpf"),
								0,
								request.getParameter("email"),
								request.getParameter("senha"));
		
		apostadorDao.adicionarApostador(apostador);
		String message = "Cadastro Realizado com Sucesso.";
		request.getSession().setAttribute("MESSAGE", message);
		response.sendRedirect("./login.jsp");
	}

}
