package br.com.six2six.fixturefactory;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.six2six.fixturefactory.model.Item;
import br.com.six2six.fixturefactory.model.Order;

public class FixtureCircularReferenceTest {

	@BeforeClass
	public static void setUp() {
		FixtureFactoryLoader.loadTemplates("br.com.six2six.template");
	}
	
	@Test
	public void circularReference() {
		Order order = Fixture.from(Order.class).gimme("valid");
		
		for (Item item : order.getItems()) {
			assertTrue("order relationship with item should have the same reference", item.getOrder() == order);
		}
		
		assertTrue("payment one-to-one relationship should have the same reference", order == order.getPayment().getOrder());
	}
	
	@Test
	public void circularReferenceEspecifyProperty() {
		Order order = Fixture.from(Order.class).gimme("otherValid");
		
		for (Item item : order.getItems()) {
			assertTrue("order relationship with item should have the same reference", item.getOrder() == order);
		}
		
		assertTrue("payment one-to-one relationship should have the same reference", order == order.getPayment().getOrder());
	}

}
