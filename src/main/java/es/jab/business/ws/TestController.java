package es.jab.business.ws;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
     
	@RequestMapping(value = {"/Test"}) 
    public void prueba(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse) throws IOException {		
        httpServletResponse.getWriter().println("Hello World!");
    }
}