package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.SorteioDAO;
import dao.SorteioDAOImpl;
import model.Sorteio;

/**
 * Servlet implementation class SorteioServlet
 */
@WebServlet("/SorteioServlet")
public class SorteioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SorteioServlet() {
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

		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		mapper.setDateFormat(sdf);
		
		SorteioDAO sorteioDao = new SorteioDAOImpl();
		List<Sorteio> bos = sorteioDao.pesquisarSorteios();
		

		String jsonTexto = mapper.writeValueAsString( bos );
		
		response.setContentType("application/json");
		response.setStatus(200);
		response.getWriter().println(jsonTexto);
		response.getWriter().flush();
	}

}
