package wonders;

import java.io.Serializable;
import java.util.ArrayList;

public class AlternativeResource extends Resource implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 21L;
	public boolean isUsed;
	ArrayList<String> names = new ArrayList<String>();

}
