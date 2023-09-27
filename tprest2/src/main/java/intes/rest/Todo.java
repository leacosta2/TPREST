package intes.rest;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

// import javax.xml.bind.annotation.XmlElement;
// import javax.xml.bind.annotation.XmlRootElement;


// @XmlRootElement
public class Todo {
	
	protected static HashMap<String, Todo> store = new HashMap<String, Todo>();
	public static Todo getStore(String id) {
		return store.get(id);
	}
	public static void putStore(String id, Todo c) {
		store.put(id, c);
	}
	public static Todo removeStore(String id) {
		return store.remove(id);
	}
	
	public static boolean containStore(String id) {
		return store.containsKey(id);
	}	
	
	public static Collection<Todo> valuesStore() {
		return store.values();
	}
	
	
	public String id;
	public String description;
	public boolean done;
	public String 	deadline;
	public List<String> tags;
	
	public Todo(){
		
	}
	public Todo (String id, String desc, String d, List<String> tags){
		this.id = id;
		this.description = desc;
		this.done = false;
		this.deadline = d;
		this.tags = tags;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String desc) {
		this.description = desc;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
	// @XmlElement(name="tag")
	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}  
	
}
