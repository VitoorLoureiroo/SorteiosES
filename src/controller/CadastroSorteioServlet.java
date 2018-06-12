package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SorteioDAO;
import dao.SorteioDAOImpl;
import model.Sorteio;

/**
 * Servlet implementation class CadastroSorteio
 */
@WebServlet("/CadastroSorteioServlet")
public class CadastroSorteioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroSorteioServlet() {
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
		
		SorteioDAO sorteioDao = new SorteioDAOImpl();
		
	    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	    
		Sorteio sorteio;
		try {
			sorteio = new Sorteio(0,
					df.parse(request.getParameter("dataAbertura")),
					df.parse(request.getParameter("dataEncerramento")),
					Integer.parseInt(request.getParameter("rangeNum")),
					Double.parseDouble(request.getParameter("valorAposta")),
					Integer.parseInt(request.getParameter("rangeAposta")));
			
			sorteioDao.adicionarSorteio(sorteio);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String message = "Sorteio Criado.";
		request.getSession().setAttribute("MESSAGE", message);
		response.sendRedirect("./sorteios.jsp");
	}

}
