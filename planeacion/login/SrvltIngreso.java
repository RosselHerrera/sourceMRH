package planeacion.login;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class SrvltIngreso
 */
@WebServlet("/SrvltIngreso")
public class SrvltIngreso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SrvltIngreso() {
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

		//Connection connection = null;
		//PreparedStatement stmt = null;
		//ResultSet rs = null;
		//String query = null;
		//String usuario = request.getParameter("rut");
		String usuario ="5825869-5";
		//String credencial = request.getParameter("credencial");
		String credencial = "mrosselh";
		//String perfil = request.getParameter("login");
		String perfil = "perfil";
		/* -------------------------------- */
		String gerencia = request.getParameter("gerencia");
		String subgerencia = request.getParameter("subgerencia");
		String subgerente = request.getParameter("subgerente");
		String area = request.getParameter("area");
		String responsable = request.getParameter("responsable");
		String proyecto = request.getParameter("proyecto");
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
				out.println("<td align=left>");
					/* Tabla colspan 5 420 px ------------------ */
				
					out.println("<table border=0 cellspacing=1 cellpadding=1 width=380>");
					out.println("<tr height=11>");
					out.println("<td bgcolor=#DF0101 height=11 align=left width=150>");
					out.println("<font face=Tahoma size=1 color=#ffffff>");
					out.println("NOMBRE PROYECTO");
					out.println("</font>");
					out.println("</td>");
					out.println("<td bgcolor=#FBEFF2 align=left height=11 width=380>");
					out.println(proyecto);
					out.println("</td>");
					out.println("</tr>");
					
					out.println("<tr height=11>");
					out.println("<td bgcolor=#DF0101 align=left height=11 width=150>");
					out.println("<font face=Tahoma size=1 color=#ffffff>");
					out.println("CATEGORÍA");
					out.println("</font>");
					out.println("</td>");
					out.println("<td bgcolor=#FBEFF2 align=left height=11 width=380>");
					out.println("<input id=categoria name=categoria type=text class=text style=height=20px; width:380px;>");
					out.println("</td>");
					out.println("</tr>");
					
					out.println("<tr height=11>");
					out.println("<td bgcolor=#DF0101 align=left height=11 width=150>");
					out.println("<font face=Tahoma size=1 color=#ffffff>");
					out.println("CATEGORÍA LIBERTY");
					out.println("</font>");
					out.println("</td>");
					out.println("<td bgcolor=#FBEFF2 align=left height=11 width=380>");
					out.println("<input id=cliberty name=cliberty type=text class=text style=height=20px; width:380px;>");
					out.println("</td>");
					out.println("</tr>");
					
					out.println("<tr height=11>");
					out.println("<td bgcolor=#DF0101 align=left height=11 width=150>");
					out.println("<font face=Tahoma size=1 color=#ffffff>");
					out.println("SUBCATEGORÍA");
					out.println("</font>");
					out.println("</td>");
					out.println("<td bgcolor=#FBEFF2 align=left height=11 width=380>");
					out.println("<input id=subcategoria name=subcategoria type=text class=text style=height=20px; width:380px;>");
					out.println("</td>");
					out.println("</tr>");
					
					out.println("<tr height=11>");
					out.println("<td bgcolor=#DF0101 align=left height=11 width=150>");
					out.println("<font face=Tahoma size=1 color=#ffffff>");
					out.println("OBJ. ESTRATÉGICO");
					out.println("</font>");
					out.println("</td>");
					out.println("<td bgcolor=#FBEFF2 align=left height=11 width=380>");
					out.println("<input id=objetivoe name=objetivoe type=text class=text style=height=20px; width:380px;>");
					out.println("</td>");
					out.println("</tr>");
					
					out.println("<tr height=11>");
					out.println("<td bgcolor=#DF0101 align=left height=11 width=150>");
					out.println("<font face=Tahoma size=1 color=#ffffff>");
					out.println("DESCRIPCIÓN");
					out.println("</font>");
					out.println("</td>");
					out.println("<td bgcolor=#FBEFF2 align=left height=11 width=380>");
					out.println("<input id=descripcion name=descripcion type=text class=text style=height=20px; width:380px;>");
					out.println("</td>");
					out.println("</tr>");
					
					out.println("</table>");						
					
				out.println("</td>");
				/* ----------------------------------------- */
				out.println("<td align=center width=160>");
				out.println("<a href=\"/PlaneacionVTR/jsp/vtrMensualizarP.jsp \">" +
							"<img src=\"/PlaneacionVTR/images/mensualiza.png\" border=0 alt=\"Mensualiza el presupuesto del Proyecto\">" +
							"</a>");
				out.println("</td>");
				out.println("<td align=rigth>");	
				/* ----------------------------------------- */
					/* Tabla colspan 5 240 px ------------------ */
					out.println("<table border=0 bgcolor=#fdfdfd cellspacing=1 cellpadding=1 width=340>");
					out.println("<tr bgcolor=#ff0000>" );
					out.println("<td align=center height=15 colspan=3>");
					out.println("<b><font face=Tahoma size=1 color=#ffffff>");
					out.println("Inversiones 2011 01-07-2011-I</b></font>");
					out.println("</td></tr>");
					/* ------------------------------------------------ */
					out.println("<tr bgcolor=#F5A9A9>");
					out.println("<td width=150px align=center><font face=Tahoma size=1 color=#ff0000><b>TIPO INVERSION</b></font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#ff0000><b>USD</b></font></td>");
					out.println("<td align=center><font face=Tahoma size=1 color=#ff0000><b>CLP</b></font></td>");
					out.println("</tr>");
					/* Tabla colspan 5 240 px ------------------------------------------------ */
						int dUSD = 0;
						int dCLP = 0;
						int tipoCb =540;
						dUSD = 13000;
						dCLP = dUSD * tipoCb;
					out.println("<tr bgcolor=#fffffc>");
					out.println("<td width=150px align=left><font face=Tahoma size=1 color=#ff0000>" +
				    			"<a href=\"vtrIngresoI.jsp\">Inv.1 (Detalle)</a></font></td>");
					out.println("<td align=right><font face=Tahoma size=1 color=#ff0000>" + dUSD + "</font></td>");
					out.println("<td align=right><font face=Tahoma size=1 color=#ff0000>" + dCLP + "</font></td>");
					out.println("</tr>");
					out.println("<tr bgcolor=#fffffc>");
					out.println("<td width=150px align=left><font face=Tahoma size=1 color=#ff0000>" +
				    			"<a href=\"vtrIngresoI.jsp\">Inv.1 (Detalle)</a></font></td>");
					out.println("<td align=right><font face=Tahoma size=1 color=#ff0000>" + dUSD + "</font></td>");
					out.println("<td align=right><font face=Tahoma size=1 color=#ff0000>" + dCLP + "</font></td>");
					out.println("</tr>");
					out.println("<tr bgcolor=#fffffc>");
					out.println("<td width=150px align=left><font face=Tahoma size=1 color=#ff0000>" +
				    			"<a href=\"vtrIngresoI.jsp\">Inv.1 (Detalle)</a></font></td>");
					out.println("<td align=right><font face=Tahoma size=1 color=#ff0000>" + dUSD + "</font></td>");
					out.println("<td align=right><font face=Tahoma size=1 color=#ff0000>" + dCLP + "</font></td>");
					out.println("</tr>");
					out.println("<tr bgcolor=#fffffc>");
					out.println("<td width=150px align=left><font face=Tahoma size=1 color=#ff0000>" +
				    			"<a href=\"vtrIngresoI.jsp\">Inv.1 (Detalle)</a></font></td>");
					out.println("<td align=right><font face=Tahoma size=1 color=#ff0000>" + dUSD + "</font></td>");
					out.println("<td align=right><font face=Tahoma size=1 color=#ff0000>" + dCLP + "</font></td>");
					out.println("</tr>");
					out.println("<tr bgcolor=#fffffc>");
					out.println("<td width=150px align=left><font face=Tahoma size=1 color=#ff0000>" +
				    			"<a href=\"vtrIngresoI.jsp\">Inv.1 (Detalle)</a></font></td>");
					out.println("<td align=right><font face=Tahoma size=1 color=#ff0000>" + dUSD + "</font></td>");
					out.println("<td align=right><font face=Tahoma size=1 color=#ff0000>" + dCLP + "</font></td>");
					out.println("</tr>");
					/* Tabla colspan 5 240 px ------------------------------------------------ */
					out.println("<tr bgcolor=#ff0000>");
					out.println("<td align=right><font face=Tahoma size=1 color=#ffffff><b>TOTAL :</b></font></td>");
					out.println("<td align=right><font face=Tahoma size=1 color=#ffffff>" + dUSD*5 + "</font></td>");
					out.println("<td align=right><font face=Tahoma size=1 color=#ffffff>" + dCLP*5 + "</font></td>");
					out.println("</tr>");
					out.println("</table>");
					
				out.println("</td>");
				out.println("</tr>");
				/* ------------------------------- */
				out.println("<tr bgcolor=#ffffff>");
				out.println("<td align=center colspan=3 height=5px width=100px>");
				out.println("&nbsp;");
				out.println("</td>");
				out.println("</tr>");
				/* ------------------------------- */
				out.println("<tr bgcolor=#ffffff align=left>");
				out.println("<td colspan=3>");
					/* Tabla  750 px ------------------------------------------------ */
					out.println("<table align=left border=0 bgcolor=#fdfdfd cellspacing=1 cellpadding=1 width=900>");
					out.println("<tr bgcolor=#ff0000 align=left>");
					out.println("<td align=center height=20 colspan=2>");
					out.println("<b><font face=Tahoma size=2 color=#ffffff>");
					out.println("INGRESO 2011 01-07-2011-I</b></font>");
					out.println("<font face=Tahoma size=1 color=#ffffff>");
					out.println(" " + usuario + "  Perfil :" + perfil  + "&nbsp;&nbsp;Fecha : " + fechaActual + " : " + horaActual + "&nbsp;&nbsp;");
					out.println("</font>");
					out.println("</td></tr>");
					
				
					/* Tabla  730 px ------------------------------------------------ */
					out.println("<tr bgcolor=#fffffc >");
					//out.println("<td align=center><a href=\"/PlaneacionVTR/jsp/vtrIngresaProyecto.jsp?gerencia=" +gerencia+ "&subgerencia=" +subgerencia+ "&subgerente=" +subgerente+ "&area=" +area+ "&responsable="+responsable+ "\">" +
					
					out.println("<td align=center colspan=2>" +
								"<input type=\"checkbox\" name=\"Chk" + "01" + ">" +
							    "<a href=\"/PlaneacionVTR/jsp/vtrIngresoI.jsp?gerencia=" +gerencia+ "&subgerencia=" +subgerencia+ "&subgerente=" +subgerente+ "&area=" +area+ "&responsable="+responsable+ "\">" +
								"<img src=\"/PlaneacionVTR/images/boton_rh_01b.png\" border=0>" +
								"</a></font>" +
								"</td>");
					out.println("</tr>");	
					
					out.println("<tr bgcolor=#fffffc>");
					out.println("<td align=center>");
					out.println("<input type=\"checkbox\" name=\"Chk" + "02" + ">");
					out.println("</td>");
					
					out.println("<td align=center colspan=6>" + 
							    "<a href=\"/PlaneacionVTR/jsp/vtrIngresaProyecto.jsp\">" + 
								"<font face=Tahoma size=2 color=#ff0000>" +
								"<img src=\"/PlaneacionVTR/images/boton_mt_02b.png\" border=0>" +
								"</a></font></td>");
					out.println("</tr>");	
					
					out.println("<tr bgcolor=#fffffc>");
					out.println("<td align=center>");
					out.println("<input type=\"checkbox\" name=\"Chk" + "03" + ">");
					out.println("</td>");
					
					out.println("<td align=center colspan=6>" + 
							    "<a href=\"/PlaneacionVTR/jsp/vtrIngresaProyecto.jsp\">" + 
								"<font face=Tahoma size=2 color=#ff0000>" +
								"<img src=\"/PlaneacionVTR/images/boton_sl_03b.png\" border=0>" +
								"</a></font></td>");
					out.println("</tr>");	
					
					out.println("<tr bgcolor=#fffffc>");
					out.println("<td align=center>");
					out.println("<input type=\"checkbox\" name=\"Chk" + "04" + ">");
					out.println("</td>");
					
					out.println("<td align=center colspan=6>" + 
							    "<a href=\"/PlaneacionVTR/jsp/vtrIngresaProyecto.jsp\">" + 
								"<img src=\"/PlaneacionVTR/images/boton_mmoo_04b.png\" border=0>" +
								"</a></font></td>");
					out.println("</tr>");	
					
					out.println("<tr bgcolor=#fffffc>");
					out.println("<td align=center>");
					out.println("<input type=\"checkbox\" name=\"Chk" + "05" + ">");
					out.println("</td>");					
					
					out.println("<td align=center colspan=6>" + 
							    "<a href=\"/PlaneacionVTR/jsp/vtrIngresaProyecto.jsp\">" + 
								"<img src=\"/PlaneacionVTR/images/boton_dac_05b.png\" border=0>" +
								"</a></font></td>");
					out.println("</tr>");
					
					out.println("<tr bgcolor=#fffffc>");
					out.println("<td align=center>");
					out.println("<input type=\"checkbox\" name=\"Chk" + "06" + ">");
					out.println("</td>");
					
					out.println("<td align=center colspan=6>" + "<a href=\"/PlaneacionVTR/jsp/vtrIngresaProyecto.jsp\">" + 
								"<img src=\"/PlaneacionVTR/images/boton_ea_06b.png\" border=0>" +
								"</a></font></td>");
					out.println("</tr>");
					
					out.println("<tr bgcolor=#fffffc>");
					out.println("<td align=center>");
					out.println("<input type=\"checkbox\" name=\"Chk" + "07" + ">");
					out.println("</td>");
					
					out.println("<td align=center colspan=6>" + "<a href=\"/PlaneacionVTR/jsp/vtrIngresaProyecto.jsp\">" + 
								"<img src=\"/PlaneacionVTR/images/boton_gs_07b.png\" border=0>" +
								"</a></font></td>");
					out.println("</tr>");
					
					out.println("<tr bgcolor=#fffffc>");
					out.println("<td align=center>");
					out.println("<input type=\"checkbox\" name=\"Chk" + "08" + ">");
					out.println("</td>");
					
					out.println("<td align=center colspan=6>" + "<a href=\"/PlaneacionVTR/jsp/vtrIngresaProyecto.jsp\">" + 
								"<img src=\"/PlaneacionVTR/images/boton_ot_08b.png\" border=0>" +
								"</a></font></td>");
					out.println("</tr>");
					
					/* Tabla  730 px ------------------------------------------------ */
					out.println("<tr bgcolor=#ff0000>");
					out.println("<td align=left><font face=Tahoma size=1 color=#ff0000>Pie de Tabla</font></td></tr>");
					out.println("</table>");
				out.println("</td>");
		
				out.println("</tr>");
				
				out.println("</table>");
				/* ----------------------------------------- */
				out.println("</div>");

			}catch (Exception e){			
				e.printStackTrace();
			} 
		/* --------------------------------  */
		}else{
			out.println("");
			out.println("");
		    out.println("Ingrese, Usuario, Credencial y seleccione su Perfil, Gracias");
      
		}
	}

}
