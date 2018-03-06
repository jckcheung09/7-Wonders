package wonders;

import java.io.Serializable;
import java.util.ArrayList;
// alternate resource is a child class of resource class
public class AlternativeResource extends Resource implements Serializable{
	// serialization needed for communicating with server

	private static final long serialVersionUID = 21L;
	// indicator if the resource is used once for a particular operation
	public boolean isUsed;
	// resource names list 
	ArrayList<String> names = new ArrayList<String>();
	// overriding to string  method for printing the resource
	@Override
	public String toString() {
		String text="";
		for(String name: names){
			text+=name+" / ";
		}
		return text;
	}




}
