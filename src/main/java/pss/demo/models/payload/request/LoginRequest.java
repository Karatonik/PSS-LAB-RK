package pss.demo.models.payload.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
	private String name;

	@NotBlank
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public LoginRequest(@NotBlank String name, @NotBlank String password) {
		this.name = name;
		this.password = password;
	}
}
