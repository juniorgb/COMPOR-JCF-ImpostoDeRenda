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
	private List<FontePagadora> fontePagadores = new ArrayList<FontePagadora>();
	private Map<Titular, FontePagadora> fontePagadoras = new HashMap<Titular, FontePagadora>();
	
	public void criarNovoTitular(Titular titular) {
		if(titular.getNome()== null){
			try{
				throw new ExcecaoImpostoDeRenda("O campo nome é obrigatório");
			} catch (Exception e) {
				throw new ExcecaoImpostoDeRenda("O campo nome é obrigatório");
			}
			
		}
		if(titular.getCpf()== null){
			try{
				throw new ExcecaoImpostoDeRenda("O campo CPF é obrigatório");
			} catch (Exception e) {
				throw new ExcecaoImpostoDeRenda("O campo CPF é obrigatório");
			}
			
		}
		if(titular.getCpf() != "000.000.000-00"){
			try{
				throw new ExcecaoImpostoDeRenda("O campo CPF está inválido");
			} catch (Exception e) {
				throw new ExcecaoImpostoDeRenda("O campo CPF está inválido");
			}
		}
		
		titulares.add(titular);
	}
		
	public List<Titular> listarTitulares() {
		
		return titulares;
	}

	public void criarFontePagadora(Titular titular, FontePagadora fonte) {
		fontePagadores.add(fonte);
	}

	public List<FontePagadora> listarFontes(Titular titular){
		return fontePagadores;
	}
	
}

