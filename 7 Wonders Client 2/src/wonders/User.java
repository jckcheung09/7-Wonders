package wonders;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name = "abc";
	int age = 25;
	WonderBoard wonderBoard= new WonderBoard(0, 'A');
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]"+ " wonderBoard: "+ wonderBoard.name;
	}

}
