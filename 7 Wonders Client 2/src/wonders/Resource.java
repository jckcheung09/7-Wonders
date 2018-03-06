
package wonders;

import java.io.Serializable;

public class Resource implements Serializable {
	// serialization needed for communicating with server
	private static final long serialVersionUID = 8L;
	//resource name
	public String name;
	// resource quantity
	public  int quantity;
    public Resource(){
    	
    }
    // constructor using name and quantity as parameter
    public Resource(String name, int number) {
        this.name = name;
        this.quantity = number;
    }
    // get details text of this resource
    @Override
	public String toString() {
		return "Resource: name=" + name + ", quantity: " + quantity ;
	}
	
	
}
