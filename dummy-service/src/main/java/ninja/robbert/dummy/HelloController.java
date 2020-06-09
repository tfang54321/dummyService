package ninja.robbert.dummy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
//@Controller
public class HelloController {

	@GetMapping("/hello")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public Map<String, String> hello(final @AuthenticationPrincipal Jwt jwt) {
		System.out.println("claims:\n" + jwt.getClaims());
		System.out.println("\nheaders:\n" + jwt.getHeaders());
		return Collections.singletonMap("message", "Hello " + jwt.getSubject());
	}
	
//
//	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
////	@GetMapping(path = "/hello", produces = MediaType.TEXT_HTML_VALUE)
////	public String html(Model model) { // <== changed return type, added parameter
////	
////	    model.addAttribute("employee", "employee");
////	    return "admin"; // view name, aka template base name
////	}
	
	
}
