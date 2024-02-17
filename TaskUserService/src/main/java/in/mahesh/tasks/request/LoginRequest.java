package in.mahesh.tasks.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document
public class LoginRequest {

    private String email;
    private String password;

    public String getemail() {
        return email;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public void setemail(String username) {
		// TODO Auto-generated method stub
		this.setUsername(username);
		
	}
}
