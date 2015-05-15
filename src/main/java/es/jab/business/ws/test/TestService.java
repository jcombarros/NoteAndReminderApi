package es.jab.business.ws.test;

import org.springframework.beans.factory.annotation.Autowired;

import es.jab.persistence.jpa.test.Message;
import es.jab.persistence.jpa.test.TestJpa;

public class TestService { 
	
	@Autowired
	private TestJpa testJpa;
	
	public void setTestJpa(TestJpa testJpa){
		this.testJpa = testJpa;
	}
	
	public TestService(){
		super();
	}
	
	public String printMessage(){
		return testJpa.testJpaPersistAndFind(new Message("Hello World!"));
	}

}
