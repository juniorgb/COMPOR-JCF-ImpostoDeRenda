package br.ufcg.ppgcc.compor.ir.impl;
import java.util.ArrayList;
import java.util.List;

import br.ufcg.ppgcc.compor.ir.ExcecaoImpostoDeRenda;
import br.ufcg.ppgcc.compor.ir.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.Titular;

public class ImpostoDeRenda implements FachadaExperimento{
	
	List<Titular> titulares = new ArrayList<Titular>();
	private boolean add;
	public void criarNovoTitular(Titular titular) {
		if(titular.getNome()== null){
			throw new ExcecaoImpostoDeRenda("Favor Informar seu nome");
		}
		if(titular.getCpf()== null){
			throw new ExcecaoImpostoDeRenda("Favor Informar seu CPF");
		}
		titulares.add(titular);
	}
		
	public List<Titular> listarTitulares() {
		
		return titulares;
	}
	
}

