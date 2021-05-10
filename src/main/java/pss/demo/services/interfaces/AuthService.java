package pss.demo.services.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import pss.demo.models.payload.request.LoginRequest;
import pss.demo.models.payload.request.SignupRequest;

import javax.validation.Valid;

public interface AuthService {

    ResponseEntity<?> authenticateUser( LoginRequest loginRequest);

    ResponseEntity<?> registerUser( SignupRequest signUpRequest);

    ResponseEntity<?> singInByExternal(SignupRequest signUpRequest);
}
