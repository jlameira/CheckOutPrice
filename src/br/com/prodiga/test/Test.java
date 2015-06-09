package br.com.prodiga.test;

import static org.junit.Assert.*;

import org.junit.Before;

import br.com.prodiga.bean.Produto;
import br.com.prodiga.checkout.CheckOutPrice;
import br.com.prodiga.regras.RegraPrecificar;

public class Test {
	
	private RegraPrecificar rules ;
	
	@Before
    public void setUp() throws Exception {
        this.rules = new RegraPrecificar("dados/precos.txt");
    }
	

	@org.junit.Test
	public void testTotals() {
		assertEquals(0, price(""));
		assertEquals(50, price("A"));
		assertEquals(80, price("AB"));
		assertEquals(115, price("CDBA"));
		
		assertEquals(100, price("AA"));
		assertEquals(130, price("AAA"));
		assertEquals(180, price("AAAA"));
		assertEquals(230, price("AAAAA"));
		assertEquals(260, price("AAAAAA"));
		
		assertEquals(160, price("AAAB"));
		assertEquals(175, price("AAABB"));
		assertEquals(190, price("AAABBD"));
		assertEquals(190, price("DABABA"));
	}
	
	public void testIncremental(){
		CheckOutPrice co = new CheckOutPrice(rules);
		
		assertEquals(0, co.total());
		co.scan(new Produto("A")); assertEquals(50, co.total());
		co.scan(new Produto("B")); assertEquals(80, co.total());
		co.scan(new Produto("A")); assertEquals(130, co.total());
		co.scan(new Produto("A")); assertEquals(160, co.total());
		co.scan(new Produto("B")); assertEquals(175, co.total());
	}
	
	public int price(String goods){
		CheckOutPrice co = new CheckOutPrice(rules);
		String[] parts = goods.split("");
		
		for(int i = 0; i < goods.length(); i++) {
            String itemName = goods.substring(i, i + 1);
            Produto item = new Produto(itemName);
            co.scan(item);
        }
		
		return co.total();
	}
}
