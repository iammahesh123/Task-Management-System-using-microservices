package in.mahesh.tasks.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Document
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	private String id;
	private String email;
	private String password;
	
	public String getId() {

		return id;
	}
	public void setId(String id) {

		this.id = id;
	}
	public String getEmail() {

		return email;
	}
	public void setEmail(String email) {

		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
