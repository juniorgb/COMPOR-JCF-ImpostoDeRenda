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
import br.ufcg.ppgcc.compor.ir.Resultado;

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
	
	public Resultado declaracaoCompleta(Titular titular) {
			
			double aliquota = 0;
			double parcelaDeDeducao = 0;
			double impostoDevido = 0;
			Resultado result = new Resultado();
			result.getImpostoDevido();
			
			double somatorioDeRendimentos = 0;
			
			for (FontePagadora fp : listarFontes(titular)) {
					
				somatorioDeRendimentos += fp.getRendimentoRecebidos();
			}
			
			if (somatorioDeRendimentos < 19645.33) {
				
				aliquota = 0;
				parcelaDeDeducao = 0;
			
			} else if (somatorioDeRendimentos >= 19645.33 && somatorioDeRendimentos <= 29442.0) {
				
				aliquota = 7.5 / 100;
				parcelaDeDeducao = 1473.4;
					
			} else if (somatorioDeRendimentos >= 29442.01 && somatorioDeRendimentos <= 39256.56) {
				
				aliquota = 15.0 / 100;
				parcelaDeDeducao = 3681.55;
			
			} else if (somatorioDeRendimentos >= 39256.57 && somatorioDeRendimentos <= 49051.8) {
					
				aliquota = 22.5 / 100;
				parcelaDeDeducao = 6625.79;
		
			} else if (somatorioDeRendimentos > 49051.80) {
				
				aliquota = 27.5 / 100;
				parcelaDeDeducao = 9078.38;
		
			}
			
			impostoDevido = (somatorioDeRendimentos * aliquota) - parcelaDeDeducao;
			result.setImpostoDevido(impostoDevido);
			result.setImpostoDevido(this.calcularImpostoDevido(titular));
			
			return result;
			
		}
	
	public int calcularFaixa(double totalRendimentos) {
			
		if (totalRendimentos < 19645.33) {
			return 1;
		} else if (totalRendimentos >= 19645.33 && totalRendimentos < 29442.01) {
			return 2;
		} else if (totalRendimentos >= 29442.01 && totalRendimentos < 39256.57) {
			return 3;
		} else if (totalRendimentos >= 39256.57 && totalRendimentos < 49051.9) {
			return 4;
		} else if (totalRendimentos > 49051.80) {
			return 5;
		}
		return 0;
	}
		
	public double calcularImpostoDevido(Titular titular) {
		
		double aliquota = 0, parcelaDeducao = 0, totalRendimentos = 0, deducaoPorDependente = 0;
		for (FontePagadora fp : this.listarFontes(titular)) {
			
			totalRendimentos += fp.getRendimentoRecebidos();
		
		}
		
		deducaoPorDependente = 1974.72 * this.listarDependentes(titular).size();
		totalRendimentos -= deducaoPorDependente;
		
		switch (this.calcularFaixa(totalRendimentos)) {
		
		case 1:
			aliquota = 0;
			parcelaDeducao = 0;
			break;
		
		case 2:
			aliquota = 7.5 / 100;
			parcelaDeducao = 1473.36;
			break;
		
		case 3:
			aliquota = 15.0 / 100;
			parcelaDeducao = 3681.60;
			break;
		
		case 4:
			aliquota = 22.5 / 100;
			parcelaDeducao = 6625.80;
			break;
		
		case 5:
			aliquota = 27.5 / 100;
			parcelaDeducao = 9078.36;
			break;
		
		default:
			aliquota = 0;
			parcelaDeducao = 0;
			break;
		}
		
		return (totalRendimentos * aliquota) - parcelaDeducao;
	
	}

		
}