/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package uk.ac.leedsbeckett.ltidemo.tool;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.leedsbeckett.ltidemo.DemoState;
import uk.ac.leedsbeckett.lti.state.LaunchState;

/**
 *
 * @author jon
 */
@WebServlet( name = "PlatformResourceServlet", urlPatterns =
{
  "/platformresource"
} )
public class PlatformResourceServlet extends AbstractDemoToolServlet
{

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest( HttpServletRequest request, HttpServletResponse response )
          throws ServletException, IOException
  {
    DemoState state = getState( request, response );
    if ( state == null ) return;
    
    LaunchState platform = state.getPlatformLaunchState();
    if ( platform == null )
    {
      response.sendError( 500, "Could not find data about the requested resource." );
      return;
    }
    
    response.setContentType( "text/html;charset=UTF-8" );
    try (  PrintWriter out = response.getWriter() )
    {
      /* TODO output your page here. You may use following sample code. */
      out.println( "<!DOCTYPE html>" );
      out.println( "<html>" );
      out.println( "<head>" );
      out.println( "<title>Servlet PlatformResourceServlet</title>" );      
      out.println( "</head>" );
      out.println( "<body>" );
      out.println( "<h1>A Platform Level Tool On " + request.getServerName() + "</h1>" );
      out.println( "<h2>About the Resource</h2>" );
      out.println( "<p>According to <strong>" + platform.getPlatformName() + "</strong> " );
      out.println( "you are <strong>" + platform.getPersonName() + "</strong></p>" );
      out.println( "<p>Your roles for this resource</p><ul>" );
      for ( int i=0; i<platform.getRoles().getSize(); i++ )
        out.println( "<li><strong>" + platform.getRoles().getAsString( i ) + "</strong></li>" );
      out.println( "</ul>" );
      out.println( "<h2>The Resource</h2>" );
      out.println( "<p>This resource is currently empty and cannot be edited yet.</p>" );
      
      out.println( "</body>" );
      out.println( "</html>" );
    }
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet( HttpServletRequest request, HttpServletResponse response )
          throws ServletException, IOException
  {
    processRequest( request, response );
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost( HttpServletRequest request, HttpServletResponse response )
          throws ServletException, IOException
  {
    processRequest( request, response );
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo()
  {
    return "Short description";
  }// </editor-fold>

}