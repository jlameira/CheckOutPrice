package br.com.prodiga.checkout;

import java.util.HashMap;
import java.util.Map;

import br.com.prodiga.bean.Produto;
import br.com.prodiga.regras.RegraPrecificar;

public class CheckOutPrice {
	private Map<Produto, Integer> map = new HashMap<Produto, Integer>();
    private RegraPrecificar precificar;

    public CheckOutPrice(RegraPrecificar precificar) {
		this.precificar = precificar;
	     
    }
    
    public void scan(Produto produto) {
        if(!map.containsKey(produto)) {
            map.put(produto, 1);
            
        }
        else {
            int count = map.get(produto);
            map.put(produto, ++count);
        }
    }

    public int total() {
        int total = 0;

        for(Produto item : map.keySet()) {
            int count = map.get(item);
            total += precificar.realizarPrecificacao(item, count);
        }

        return total;
    }

}
