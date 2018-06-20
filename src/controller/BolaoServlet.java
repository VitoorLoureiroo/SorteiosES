package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ApostaDAO;
import dao.ApostaDAOImpl;
import dao.BolaoDAO;
import dao.BolaoDAOImpl;
import dao.SorteioDAO;
import dao.SorteioDAOImpl;
import model.Aposta;
import model.Bolao;
import model.Sorteio;

/**
 * Servlet implementation class BolaoServlet
 */
@WebServlet("/BolaoServlet")
public class BolaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BolaoServlet() {
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
		int [] numerosAposta= new int[request.getParameterMap().size()-1];
		for ( int i=0; i<=request.getParameterMap().size(); i ++) {
			if(request.getParameter("number" + i)!=null) {
				numerosAposta[i] = Integer.parseInt(request.getParameter("number" + i));
			}
		}
		SorteioDAO sorteioDao = new SorteioDAOImpl();
		long sorteioId = (long) request.getSession().getAttribute("SORTEIO");
		List<Sorteio> sorteios = sorteioDao.pesquisarSorteiosPorID(sorteioId);
		
		String cpf = (String) request.getSession().getAttribute("CPF");
		
		String [] apostadores = new String [1];
		apostadores[0] = cpf;
		
		Bolao bolao = new Bolao(apostadores,numerosAposta,sorteioId, new Date(), sorteios.get(0).getValorAposta());
		BolaoDAO bolaoDao = new BolaoDAOImpl();
		bolaoDao.adicionarBolao(bolao);
		
		response.sendRedirect("./apostas.jsp?sorteioId=" + request.getSession().getAttribute("SORTEIO"));
		
	}

}
