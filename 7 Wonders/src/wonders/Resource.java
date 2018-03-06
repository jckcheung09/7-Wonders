
package wonders;

import java.io.Serializable;

public class Resource implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 8L;
	String name;
    int quantity;
    public Resource(){
    	
    }
    public Resource(String name, int number) {
        this.name = name;
        this.quantity = number;
    }
    @Override
	public String toString() {
		return "Resource: name=" + name + ", quantity: " + quantity ;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + quantity;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resource other = (Resource) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
}
