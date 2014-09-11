package br.ufcg.ppgcc.compor.ir.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.ufcg.ppgcc.compor.ir.excecaoCriarFonte;
import br.ufcg.ppgcc.compor.ir.FachadaExperimento;
import br.ufcg.ppgcc.compor.ir.FontePagadora;
import br.ufcg.ppgcc.compor.ir.Titular;

public class ImpostoDeRenda implements FachadaExperimento{
	
	private List<Titular> titulares = new ArrayList<Titular>();
	private Map<Titular,List<FontePagadora>> fontePagadoras = new LinkedHashMap<Titular, List<FontePagadora>>();
	private FontePagadora fonte;
	
	
	public void criarNovoTitular(Titular titular) {
		if(titular.getNome()== null){
			throw new excecaoCriarFonte("O campo nome é obrigatório");
			
		}
		if(titular.getCpf()== null){
			throw new excecaoCriarFonte("O campo CPF é obrigatório");
			
		}
		
			if (titular.getCpf().matches("\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d") == false) {
							throw new excecaoCriarFonte("O campo CPF está inválido");
				 		}
	
		titulares.add(titular);
		fontePagadoras.put(titular, new ArrayList<FontePagadora>());
	}
		
	public List<Titular> listarTitulares() {
		
		return titulares;
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
		
			if(fontePagadoras.containsKey(titular)){
				List<FontePagadora> listFont = fontePagadoras.get(titular);
				listFont.add(fonte);
		}
	
	}
	

	
	public List<FontePagadora> listarFontes(Titular titular){
		return fontePagadoras.get(titular);
	}

	public FontePagadora getFonte() {
		return fonte;
	}

	public void setFonte(FontePagadora fonte) {
		this.fonte = fonte;
	}

	
	
}

