package planeacion.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class SrvltLogin
 */
@WebServlet("/SrvltLogin")
public class SrvltLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SrvltLogin() {
        super();
    }

    /**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init();
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public String getServletInfo() {
		return super.getServletInfo();
	}
	
	public ServletConfig getServletConfig() {
		return super.getServletConfig();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}
	
	public void performTask(HttpServletRequest request, 
								HttpServletResponse response) 
									throws IOException, ServletException {
		@SuppressWarnings("unused")
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query = null;
		//String usuario = request.getParameter("rut");
		String usuario ="5825869-5";
		//String credencial = request.getParameter("credencial");
		String credencial = "mrosselh";
		//String perfil = request.getParameter("login");
		String perfil = "perfil";
		
		/* -------------------------------- */
		PrintWriter out;
		response.setContentType("text/html");
		out= response.getWriter();
		/* -------------------------------- */
		if ((usuario.length() > 0) &&  (credencial.length() > 0)){		
		/* -------------------------------- */
			int i = 0;
			String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			String horaActual = new SimpleDateFormat("HH:mm:ss").format(new Date());
			try{	
				out.println("<div align=center>");
				/* ----------------------------------------- */
				out.println("<table border=0 cellspacing=1 cellpadding=1 width=900>");				
				out.println("<tr bgcolor=#ffffff>");
				out.println("<td align=left>&nbsp;</td>");
				/* ----------------------------------------- */
				out.println("<td align=center>");
				out.println("<a href=\"/PlaneacionVTR/jsp/vtrMensualizarP.jsp \">" +
							"<img src=\"/PlaneacionVTR/images/mensualiza.png\" border=0 alt=\"Mensualiza el presupuesto del Proyecto\">" +
							"</a>");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr bgcolor=#ffffff>");
				out.println("<td align=center colspan=3>");
					/* Tabla  750 px ------------------------------------------------ */
					out.println("<table align=center border=0 bgcolor=#fdfdfd cellspacing=1 cellpadding=1 width=900>");
					out.println("<tr bgcolor=#DF0101>" );
					out.println("<td align=center height=30 colspan=8>");
					out.println("<b><font face=Tahoma size=2 color=#ffffff>");
					out.println("Inversiones Proyectos 2011 01-07-2011-I</b></font>");
					out.println("</td></tr>");
					out.println("<tr bgcolor=#DF0101><td align=right height=10  colspan=8>");
					//out.println("<font face=Tahoma size=1 color=#336600> Servlet Engine Version : " +  getServletConfig().getServletContext().getMajorVersion() + "." + getServletConfig().getServletContext().getMinorVersion() + "&nbsp;&nbsp;Fecha : " + fechaActual + " : " + horaActual + "&nbsp;&nbsp;");
					out.println("<font face=Tahoma size=1 color=#ffffff>Usuario : " + usuario + "  Perfil :" + perfil + "&nbsp;&nbsp;Fecha : " + fechaActual + " : " + horaActual + "&nbsp;&nbsp;");
					out.println("</font></td></tr>");
					/* ------------------------------------------------ */

					/* ------------------------------------------------ */
					out.println("<tr bgcolor=#DF0101>");
					out.println("<td align=center><font face=Tahoma size=1 color=#ffffff><b>TIPO INV.</b></font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#ffffff><b>ITEM TIPO</b></font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#ffffff><b>PROVEEDOR</b></font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#ffffff><b>MONEDA</b></font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#ffffff><b>P (Price)</b></font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#ffffff><b>Q (Quanty)</b></font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#ffffff><b>TOTAL USD</b></font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#ffffff><b>TOTAL CLP</b></font></td></tr>");
					/* Tabla  730 px ------------------------------------------------ */
					
					out.println("<tr bgcolor=#fffffc>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>E/H</font></td>");
					out.println("<td align=center>" + "<a href=\"vtrIngresoI.jsp\">" + 
								"<font face=Tahoma size=2 color=#610B0B>" +
								"Ingresar descripcion equipo" +
								"</a></font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>Ingresar Proveedor</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>Seleccionar Moneda</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>Ingresar P</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>Ingresar Q</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>P*Q USD</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>P*Q CLP</font></td></tr>");	
					
					out.println("<tr bgcolor=#fffffc>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>E/H</font></td>");
					out.println("<td align=center>" + "<a href=\"vtrIngresoI.jsp\">" + 
								"<font face=Tahoma size=2 color=#610B0B>" +
								"Ingresar descripcion equipo" +
								"</a></font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>Ingresar Proveedor</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>Seleccionar Moneda</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>Ingresar P</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>Ingresar Q</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>P*Q USD</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>P*Q CLP</font></td></tr>");	
					
					out.println("<tr bgcolor=#fffffc>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>R/H</font></td>");
					out.println("<td align=center>" + "<a href=\"vtrIngresoI.jsp\">" + 
								"<font face=Tahoma size=2 color=#610B0B>" +
								"Ingresar descripcion equipo" +
								"</a></font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>Ingresar Proveedor</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>Seleccionar Moneda</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>Ingresar P</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>Ingresar Q</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>P*Q USD</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>P*Q CLP</font></td></tr>");	
					
					out.println("<tr bgcolor=#fffffc>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>MAT</font></td>");
					out.println("<td align=center>" + "<a href=\"vtrIngresoI.jsp\">" + 
								"<font face=Tahoma size=2 color=#610B0B>" +
								"Ingresar descripcion equipo" +
								"</a></font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>Ingresar Proveedor</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>Seleccionar Moneda</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>Ingresar P</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>Ingresar Q</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>P*Q USD</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>P*Q CLP</font></td></tr>");	
					
					out.println("<tr bgcolor=#fffffc>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>MMOO</font></td>");
					out.println("<td align=center>" + "<a href=\"vtrIngresoI.jsp\">" + 
								"<font face=Tahoma size=2 color=#610B0B>" +
								"Ingresar descripcion equipo" +
								"</a></font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>Ingresar Proveedor</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>Seleccionar Moneda</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>Ingresar P</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>Ingresar Q</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>P*Q USD</font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#610B0B>P*Q CLP</font></td></tr>");	
					
					/* Tabla  730 px ------------------------------------------------ */
					out.println("<tr bgcolor=#DF0101>");
					out.println("<td align=left colspan=8><font face=Tahoma size=1 color=#ff0000>Pie de Tabla</font></td></tr>");
					out.println("</table>");
				out.println("</td>");
		
				out.println("</tr>");
				
				out.println("<tr bgcolor=#fffffc align=center>");
				//out.println("<td colspan=3><a href=\"/PlaneacionVTR/jsp/vtrIngresoProyectoP.jsp\" >" +
				out.println("<td colspan=3><a href=\"/PlaneacionVTR/jsp/vtrLogueado.jsp\" >" +
							"<img src=\"/PlaneacionVTR/images/IrapaginaPrincipal.png\" border=0>" +
							"</a></font></td>");
				out.println("</tr>");	
				
				out.println("</table>");
				/* ----------------------------------------- */
				out.println("</div");
				//out.println("</body>");				
				//out.println("</html>");	
			}catch (Exception e){			
				e.printStackTrace();
			} 
		/* --------------------------------  */
		}else{
			out.println("<div style=\"HEIGHT: 350px\">");
		    out.println("Ingrese, Usuario, Credencial y seleccione su Perfil, Gracias");
		    out.println("</div>");
      
		}
	}
	
}
