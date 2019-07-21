package edu.edgewood.assignmentTwo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class FormServlet
 */
@WebServlet(name = "formServlet", urlPatterns = { "/formServlet" })
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try (PrintWriter writer = response.getWriter()) {						
			createInputPage(writer, Optional.of(""));
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("message");
		
		try(PrintWriter writer= response.getWriter()){
			
			//No message included in request
			if(message == null) {
				createInputPage(writer, Optional.of("Damn! We forgot the input field!"));
				return;
			}
			
			//Message field was left blank
			if(message.length() == 0) {				
				createInputPage(writer, Optional.of("Please input a message"));
				return;
			}
			
			//Message is nothing but white space
			if(message.trim().length() == 0) {				
				createInputPage(writer, Optional.of("Message must contain at least"
						+ " one non-whitespace character"));
				return;
			}
			
			createHeadersPage(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createInputPage(PrintWriter writer, Optional<String> msg)
			throws ServletException{
			writer.println("<html>");
			
			writer.println("<head>");
			
			//Javascript resources
			writer.println("<script src='js/lib/jquery-3.3.1.min.js'></script>");
			writer.println("<script src='js/scripts.js'></script>");
			
			
			writer.println("</head>");
			
			writer.println("<body>");
			
			writer.println("<div id='container'>");  
			writer.println(	"<div id='header'>");
			writer.println(	"</div>"); 
			
			writer.println(	"<div id='content'>");
			
			writer.println(msg.get());
			
			writer.println("<form id='formId' action='formServlet' method='post'>");
			writer.println("Message:");
			writer.println("<input type='text' id='messageTextId' name='message' value=''>");
			writer.println("<input type='submit' id='submitBtnId' value='Submit'/>");
			writer.println("</form>");
			
			writer.println("</div>"); 
			
			writer.println(	"<div id='footer'>");
			writer.println("</div>");
			
			writer.println(	"</div>");
			writer.println("</body>");
			writer.println("</html>");
		}
	
	
	
	private void createHeadersPage(HttpServletRequest request, HttpServletResponse response ) 
			throws IOException {
		
		
        try(PrintWriter writer = response.getWriter()) {
			response.setContentType("text/html");
			
			//get list of header names
			Enumeration<String> headerNames = request.getHeaderNames();
			writer.println("<h3>Headers</h3>");
			writer.println("<ul>");
			
			//iterate over headerNames
			while (headerNames.hasMoreElements()) {

				String headerName = headerNames.nextElement();
				
				//get list of headers 
				Enumeration<String> headers = request.getHeaders(headerName);
				
				//iterate over headers and print out the header name and value as within HTML <li> tags
				while (headers.hasMoreElements()) {
					String headerValue = headers.nextElement();
					writer.println("<li><b>" + headerName + ":</b>" + headerValue + "</li>");
				}
			}
			writer.println("</ul>");
			writer.println("<b>Browser:</b> " + request.getHeader("user-agent") + "<br/>");
			writer.println("<b>Request URL:</b> " + request.getHeader("referer") + "<br/>");
			writer.println("<b>User Input:</b> " + request.getParameter("message") + "<br/>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
 
    }
 
   }


