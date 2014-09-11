package br.ufcg.ppgcc.compor.ir.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.ufcg.ppgcc.compor.ir.Dependente;
import br.ufcg.ppgcc.compor.ir.ExcecaoImpostoDeRenda;
import br.ufcg.ppgcc.compor.ir.excecaoCriarFonte;
import br.ufcg.ppgcc.compor.ir.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.FontePagadora;
import br.ufcg.ppgcc.compor.ir.Titular;

public class ImpostoDeRenda implements FachadaExperimento{
	
	private Map<Titular, List<FontePagadora>> listaDeTitulares = new LinkedHashMap<Titular, List<FontePagadora>>();
	private Map<Titular, List<Dependente>> listaDeDependentes = new HashMap<Titular, List<Dependente>>();
	
	public void criarNovoTitular(Titular titular) {
		if (titular.getNome() == null) {
			throw new excecaoCriarFonte("O campo nome é obrigatório");

		}
		if (titular.getCpf() == null) {
			throw new excecaoCriarFonte("O campo CPF é obrigatório");

		}

		if (titular.getCpf().matches("\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d") == false) {
			throw new excecaoCriarFonte("O campo CPF está inválido");
		}

		listaDeTitulares.put(titular, new ArrayList<FontePagadora>());
		listaDeDependentes.put(titular, new ArrayList<Dependente>());
	}
		
	public List<Titular> listarTitulares() {
		
		return new ArrayList<Titular>(listaDeTitulares.keySet());
	}

	public void criarFontePagadora(Titular titular, FontePagadora fonte) {
		
		if(fonte.getNome()==null){
			throw new excecaoCriarFonte("O campo nome é obrigatório");
			}
		if(fonte.getRendimentoRecebidos()==0.0){
			throw new excecaoCriarFonte("O campo rendimentos recebidos é obrigatório");
		} else if(fonte.getRendimentoRecebidos()<0.0){
			throw new excecaoCriarFonte("O campo rendimentos recebidos deve ser maior que zero");
			
			}if(fonte.getCpfCnpj()==null ){
				throw new excecaoCriarFonte("O campo CPF/CNPJ é obrigatório");
				
				}else if(!fonte.getCpfCnpj().matches("\\d\\d.\\d\\d\\d.\\d\\d\\d"+"/"+"\\d\\d\\d\\d-\\d\\d")){
					throw new excecaoCriarFonte("O campo CPF/CNPJ é inválido");
				}
			if(listaDeTitulares.containsKey(titular)==false){
				throw new excecaoCriarFonte("Titular não cadastrado");
			}
			
			if(listaDeTitulares.containsKey(titular)){
				List<FontePagadora> listFont = listaDeTitulares.get(titular);
				listFont.add(fonte);
		}
	
	}
		
	public List<FontePagadora> listarFontes(Titular titular){
		return listaDeTitulares.get(titular);
	}

	public void criarDependente(Titular titular, Dependente dependente) {
		if(dependente.getCpf()==null){
			throw new ExcecaoImpostoDeRenda("O campo CPF é obrigatório");
			}
		if(dependente.getNome()==null){
			throw new ExcecaoImpostoDeRenda("O campo nome é obrigatório");
			}
		if(dependente.getTipo()==0){
			throw new ExcecaoImpostoDeRenda("O campo tipo é obrigatório");
			}
		if(dependente.getCpf().matches("\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d") == false){
			throw new ExcecaoImpostoDeRenda("O campo CPF é inválido");
			}
		if(dependente.getTipo() <= 0){
			throw new ExcecaoImpostoDeRenda("O campo tipo é inválido");
			}
		if(listaDeDependentes.containsKey(titular) == false){
			throw new ExcecaoImpostoDeRenda("Titular não cadastrado");
			}
					
		if(listaDeDependentes.containsKey(titular)){
			List<Dependente> listDependente = listaDeDependentes.get(titular);
			listDependente.add(dependente);
			}
		
	}
	
	public List<Dependente> listarDependentes(Titular titular) {
				return listaDeDependentes.get(titular);
		}
			
}

