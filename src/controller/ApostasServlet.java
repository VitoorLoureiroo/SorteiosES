package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ApostaDAO;
import dao.ApostaDAOImpl;
import dao.BolaoDAO;
import dao.BolaoDAOImpl;
import dao.SorteioDAO;
import dao.SorteioDAOImpl;
import model.Aposta;
import model.Bolao;
import model.Sorteio;
import model.ViewModelApostas;

/**
 * Servlet implementation class ApostasServlet
 */
@WebServlet("/ApostasServlet")
public class ApostasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApostasServlet() {
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
		
		
		String valueBtn = request.getParameter("btn");
		
		if(valueBtn!=null && valueBtn.equals("redirectAposta")) {
			//response.sendRedirect("./cadastro.jsp");
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
			
			ApostaDAO apostaDao = new ApostaDAOImpl();
			Aposta aposta = new Aposta(numerosAposta, sorteios.get(0).getValorAposta(), new Date(),cpf, sorteioId );
			apostaDao.adicionarAposta(aposta);
			response.sendRedirect("./apostas.jsp?sorteioId=" + request.getSession().getAttribute("SORTEIO"));
		}
		else if(valueBtn!=null && valueBtn.equals("redirectBolao")) {
			int [] numerosAposta= new int[request.getParameterMap().size()-1];
			response.sendRedirect("./cadastroBolao.jsp?numPorAposta=" + numerosAposta.length );
		}else {
			
			ServletInputStream in = request.getInputStream();
			int a = 0;
			StringBuffer sb = new StringBuffer();
			while ((a = in.read()) != -1) { 
				sb.append( (char) a);			
			}
			
			if(sb.toString().contains("[")) {
				BolaoDAO bolaoDao = new BolaoDAOImpl();
				bolaoDao.adicionarAposta(sb.toString());
				response.sendRedirect("./apostas.jsp?sorteioId=" + request.getSession().getAttribute("SORTEIO"));
			}else {
				long sorteioId = Long.parseLong(sb.toString());
			
				request.getSession().setAttribute("SORTEIO", sorteioId);

			ObjectMapper mapper = new ObjectMapper();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			mapper.setDateFormat(sdf);
		

			SorteioDAO sorteioDao = new SorteioDAOImpl();
			List<Sorteio> sorteios = sorteioDao.pesquisarSorteiosPorID(sorteioId);
		
			BolaoDAO bolaoDao = new BolaoDAOImpl();
			List<Bolao> bolaoList = bolaoDao.pesquisarBolaoPorSorteio(sorteioId);
		
			List<ViewModelApostas> listaView = new ArrayList<>();
			if(bolaoList.size()>0) {
				for(Bolao bolao : bolaoList) {
			
				ViewModelApostas apostasView = new ViewModelApostas(bolao.getApostadores()[0],bolao.getApostadores().length,bolao.getData(),bolao.getData(),Arrays.toString(bolao.getNumeros()));
				apostasView.setNumPorAposta(sorteios.get(0).getNumerosPorAposta());			
				listaView.add(apostasView);
				}
		
			}else {
				ViewModelApostas apostasView = new ViewModelApostas("",0,new Date(),new Date(0),"");
				apostasView.setNumPorAposta(sorteios.get(0).getNumerosPorAposta());			
				listaView.add(apostasView);
			}
		
			String jsonTexto = mapper.writeValueAsString( listaView );
		
			response.setContentType("application/json");
			response.setStatus(200);
			response.getWriter().println(jsonTexto);
			response.getWriter().flush();
		}
		}
	}
	
	
}

