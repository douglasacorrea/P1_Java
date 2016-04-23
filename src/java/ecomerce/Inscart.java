package ecomerce;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Douglas
 */
@WebServlet(urlPatterns = {"/Inscart"})
public class Inscart extends HttpServlet {
@Override
public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException    	{ 
int index=0;
		  double gt = 0;
		  String pid="",pnm="",up="";
	      res.setContentType("text/html");
	      PrintWriter out=res.getWriter();

 	      try        	{
			Class.forName("com.mysql.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/prova","root","pass123");

              pid = req.getParameter("pro");
              pnm = req.getParameter("pros");
              up = req.getParameter("soop");

			out.println("<html><center><form method=post action=http://localhost:8080/P1_Java/deleteservlet>");
            out.println("<h2><U>Carrinho</U></H2>");
			out.println("<th>Nome do Produto</th><th>Quantidade</th><th>UnitPrice</th><th>Total</th></tr>"+
                                "<table border=0><tr><th>        </th><th>Id Produto</th>");
    String user = null;
			
			if(pid!=null && pnm!=null && up!=null)			{
              PreparedStatement r=c.prepareStatement("select * from carrinho where ProdutoId=? and Nome=?");		  		
              r.setString(1,pid);
                            String nome = null;
              r.setString(2,nome);
              ResultSet rp=r.executeQuery();

              if(rp.next())                {
                   int z=rp.getInt(3);
                   int inc = z+1;
                   PreparedStatement k=c.prepareStatement("update carrinho  set  Quantidade=? where Quantidade=? and Nome=? and ProdutoId=?");
                   k.setInt(1,inc);
                   k.setInt(2,z);
	               k.setString(3,user);
	               k.setString(4,pid);	
                   k.executeUpdate();
                   k.close();

                   int ab =inc;  //quantidade
                   int aa =  rp.getInt(4); //preço unidade
                   int ad =  rp.getInt(5);//total
                   int ac = aa*ab;

                   PreparedStatement w =c.prepareStatement("update carrinho  set  Total=? where Total=? and Nome=? and ProdutoId=?");
                   w.setInt(1,ac);
                   w.setInt(2,ad);
                   w.setString(3,user);
                   w.setString(4,pid);
                   w.executeUpdate();
				   rp.close();
				   w.close();
	            }
            else              {
                  PreparedStatement p=c.prepareStatement("insert into carrinho (ProdutoId,NomeProduto,Preco,Nome,Total) values(?,?,?,?,?);");
                  p.setString(1,pid);
                  p.setString(2,pnm);
                  p.setString(3,up);
                  p.setString(4,user);
                  p.setString(5,up);
                  p.executeUpdate();
				  p.close();
              }
		   }
		   
          PreparedStatement q=c.prepareStatement("select * from carrinho where nome=?");
          q.setString(1,user);
  			   			 																									
          ResultSet rs=q.executeQuery();
          ResultSetMetaData rsmd=rs.getMetaData();

          while(rs.next())		   	{
              out.println("<tr>");
              String a=rs.getString(1);															 
              String b=rs.getString(2);
              int f=rs.getInt(3);
             double d=rs.getDouble(4);
             double e=rs.getDouble(5);
             double tot = f*d;
			 ++index;			  
			 out.println("<td><input type=checkbox   name=cb"+index+"  value="+a+"></td><td align=center>"+a+"</td><td align=center>"+b+"</td><td align=center><input type=text border=0 name=txt   size=2 value="+f+" disabled></td><td>"+d+"</td><td>"+tot+"</td></tr>");
			 gt = gt+tot;             			 
			 System.out.println("cb"+index);
		   } 
	   rs.close();
		   c.close();
         }catch(ClassNotFoundException e){System.out.println("erro in  " + e);} catch (SQLException e) {
             System.out.println("erro in  " + e);
    }
	   	 out.println("<tr><td></td><td></td><td></td><td></td><td><h4>&nbsp;<b>TOTAL : </b></h4></td><td><b><hr>"+gt+"<hr><br><b></td></tr></table><br><center><input type=text name=test value="+index+"  style='visibility:hidden'>");
     }
}
