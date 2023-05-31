import jakarta.servlet.*;
import java.io.*;
import jakarta.servlet.http.*;
import java.util.*;

public class ShowRequestHeaders extends HttpServlet {
   public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
  {
  		response.setContentType("text/html");
  		PrintWriter out = response.getWriter();
  		Enumeration headerNames = request.getHeaderNames();
  		out.println("<TABLE>");
  		while(headerNames.hasMoreElements()) {
    		       String headerName = (String)headerNames.nextElement();
	    	       out.println("<TR><TD>" + headerName+"</TD>");
    		       out.println("<TD>" + request.getHeader(headerName)+"</TD></TR>");
  		}
  		out.println("</TABLE>");
	}
}
