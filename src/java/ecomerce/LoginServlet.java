/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomerce;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Douglas
 */
public class LoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException
	{ 
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();

	try
	{

Class.forName("com.mysql.jdbc.Driver");
Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/prova","root","pass123");
PreparedStatement p=c.prepareStatement("select * from usuarios where nome=? and senha=?");

String nome=req.getParameter("nome"),senha=req.getParameter("senha");


p.setString(1,nome);

p.setString(2,senha);

ResultSet rs=p.executeQuery();
if(rs.next())
	{
	out.println("<HTML><HEAD><TITLE> Eshop </TITLE></HEAD><frameset cols='20%,80%' border=0 bg color=black><frame src='http://localhost:8080/P1_Java/terceiro.html' name=COL1><frame src='http://localhost:8080/P1_Java/quarto.html' NAME=COL2></FRAMESET></HTML>");

HttpSession session = req.getSession(true);


	}
	else{out.println("<HTML><HEAD><TITLE> New Document </TITLE></HEAD><body><h2>Usuario Invalido<a href='http://localhost:8080/Login.html'>Tente novamente</a></h2></body></html>");}
	}catch(ClassNotFoundException e){} catch (SQLException e) {
        }
	}
}
