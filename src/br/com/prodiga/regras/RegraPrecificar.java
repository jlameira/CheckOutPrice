package br.com.prodiga.regras;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.prodiga.bean.Produto;

public class RegraPrecificar {
	 private Map<Produto, Preco> map = new HashMap<Produto, Preco>();
	 
	 public RegraPrecificar(String arquivoComPreco) throws NumberFormatException, IOException{
		 BufferedReader arquivo = new BufferedReader(new FileReader(arquivoComPreco));
		 
		 while(arquivo.ready()){
			 String regraInicio = null;
			 
			 try {
				regraInicio = arquivo.readLine();
				String[] produtos = regraInicio.split(",");
				String nomeDoProduto = produtos[0];
				int precoDoProduto = Integer.parseInt(produtos[1]);
				
				int descontoUm = 0;
				int descontoDois = 0;
				if(produtos.length > 2){
					String[] darDesconto = produtos[2].split("->");
					descontoUm = Integer.parseInt(darDesconto[0]);
					descontoDois = Integer.parseInt(darDesconto[1]);
				}
				
				map.put(new Produto(nomeDoProduto), new PrecoRealDoProduto(precoDoProduto, descontoUm, descontoDois));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		 }
		 
	 }
	 
	 public int realizarPrecificacao(Produto produto, int count){
		 Preco preco = map.get(produto);
		 if(preco != null){
			 return preco.getPrecoTotal(count);
		 }else{
			 throw new IllegalArgumentException("Produto não encontrado" + produto);
		 }
	 }

}
