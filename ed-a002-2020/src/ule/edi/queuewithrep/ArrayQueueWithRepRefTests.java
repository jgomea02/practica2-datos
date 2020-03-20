package ule.edi.queuewithrep;

import org.junit.Test;

import junit.framework.Assert;

public class ArrayQueueWithRepRefTests extends AbstractQueueWithRefTests {

	@Override
	protected <T> QueueWithRep<T> createQueueWithRep() {
		
		return new ArrayQueueWithRepImpl<T>();
	}
	
	// tests solo para implementación Array, que no aplican a la implementación  Linked
@Test
public void expandCapacityTest() {
	ArrayQueueWithRepImpl<Integer> aq = new ArrayQueueWithRepImpl<Integer>(2);
	aq.add(1);
	aq.add(2);
	aq.add(3);
	aq.add(4);
	aq.add(5);
	aq.add(6);
	aq.add(7);
	aq.add(8);
	aq.add(15);
	aq.add(16);
	aq.add(17);
	aq.add(18);
	Assert.assertEquals(16, aq.size());
}

}
