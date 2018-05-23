package controller;

import java.util.Date;

import model.Aposta;
import model.Sorteio;

public class ApostaController {

		public ApostaController(){
			
			Sorteio sorteio1 = new Sorteio(1, new Date(), new Date(), 70, 5.00, 3);
			Aposta aposta1 = new Aposta(new int[]{1,2,3}, 5.00, new Date(), "123.456.789-00", sorteio1.getId());
			
		};
}
