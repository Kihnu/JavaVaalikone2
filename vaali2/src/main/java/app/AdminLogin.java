package app;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String username = "admin";
	String password = crypt("1234");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("username");
		String pass = request.getParameter("password");
		
		if (name.equals(username) && crypt(pass).equals(password)) {
			
			RequestDispatcher rd = request.getRequestDispatcher("jsp/AdminMain.jsp");
			rd.forward(request, response);
			
			doGet(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.forward(request, response);
			
			doGet(request, response);
		}
	}
	
	 public static String crypt(String str) {
	        if (str == null || str.length() == 0) {
	            throw new IllegalArgumentException("String to encript cannot be null or zero length");
	        }

	        MessageDigest digester;
	        try {
	            digester = MessageDigest.getInstance("MD5");

	            digester.update(str.getBytes());
	            byte[] hash = digester.digest();
	            StringBuffer hexString = new StringBuffer();
	            for (int i = 0; i < hash.length; i++) {
	                if ((0xff & hash[i]) < 0x10) {
	                    hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
	                } else {
	                    hexString.append(Integer.toHexString(0xFF & hash[i]));
	                }
	            }
	            return hexString.toString();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return "";
	    }

}
