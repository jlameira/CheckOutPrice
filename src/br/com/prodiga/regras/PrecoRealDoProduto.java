package br.com.prodiga.regras;

public class PrecoRealDoProduto implements Preco {
	
	private int precoDoProduto;
	private int descontoUm;
	private int descontoDois;

	public PrecoRealDoProduto(int precoDoProduto, int descontoUm, int descontoDois){
		this.precoDoProduto = precoDoProduto;
		this.descontoUm = descontoUm;
		this.descontoDois = descontoDois;
		
	}

	@Override
	public int getPrecoTotal(int count) {
		int precoTotal;

        if(descontoUm > 0 && count >= descontoUm) {
            int desconto = count / descontoUm * descontoDois;
            int precoNormal = count % descontoUm * precoDoProduto;
            precoTotal = (desconto + precoNormal);
        }
        else {
        	precoTotal = count * precoDoProduto;
        }

        return precoTotal;
	}

}
