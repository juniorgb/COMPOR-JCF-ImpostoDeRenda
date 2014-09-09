package br.ufcg.ppgcc.compor.ir.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufcg.ppgcc.compor.ir.ExcecaoImpostoDeRenda;
import br.ufcg.ppgcc.compor.ir.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.FontePagadora;
import br.ufcg.ppgcc.compor.ir.Titular;

public class ImpostoDeRenda implements FachadaExperimento{
	
	private List<Titular> titulares = new ArrayList<Titular>();
	private Map<Titular,List <FontePagadora>> fontePagadoras = new HashMap<Titular, List<FontePagadora>>();
	
	
	public void criarNovoTitular(Titular titular) {
		if(titular.getNome()== null){
			throw new ExcecaoImpostoDeRenda("O campo nome é obrigatório");
			
		}
		if(titular.getCpf()== null){
			throw new ExcecaoImpostoDeRenda("O campo CPF é obrigatório");
			
		}
		
			if (titular.getCpf().matches("\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d") == false) {
							throw new ExcecaoImpostoDeRenda("O campo CPF está inválido");
				 		}
	
		titulares.add(titular);
		fontePagadoras.put(titular, new ArrayList<FontePagadora>());
	}
		
	public List<Titular> listarTitulares() {
		
		return titulares;
	}

	public void criarFontePagadora(Titular titular, FontePagadora fonte) {
		if(fontePagadoras.containsKey(titular)){
		List<FontePagadora> listFont = fontePagadoras.get(titular);
		listFont.add(fonte);
		
		
		}
		
	}

	public List<FontePagadora> listarFontes(Titular titular){
		return fontePagadoras.get(titular);
	}

	
	
}

