package br.ufcg.ppgcc.compor.ir.impl;
import java.util.ArrayList;
import java.util.List;

import br.ufcg.ppgcc.compor.ir.ExcecaoImpostoDeRenda;
import br.ufcg.ppgcc.compor.ir.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.Titular;

public class ImpostoDeRenda implements FachadaExperimento{
	
	List<Titular> titulares = new ArrayList<Titular>();
	
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
	
}

