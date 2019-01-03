package planeacion.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
/**
 * Servlet implementation class SrvltProyectos
 */
@SuppressWarnings("unused")
@WebServlet(urlPatterns = { "/SrvltProyectos" }, 
		    initParams = { 
				@WebInitParam(name = "className", value = "com.mysql.jdbc.Driver"), 
				@WebInitParam(name = "url", value = "jdbc:mysql://localhost:3306/orgdata"), 
				@WebInitParam(name = "userID", value = "root"), 
				@WebInitParam(name = "password", value = "admin")
		})
public class SrvltProyectos extends HttpServlet {
	
    /**
	 * @serial -5185662505044136427L
	 */
	private static final long serialVersionUID = -5185662505044136427L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public SrvltProyectos() {
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
	
	/**
	 * Conecta seg�n par�metros del descriptor web.xml 
	 */
	public Connection getConector() throws Exception {
		Connection db = null;
		String url = "jdbc:mysql://localhost:3306/orgdata";
		String userID = "root";
		String password = "53b4f0c2a2";
		String className = "com.mysql.jdbc.Driver";
		try {
			Class.forName(className);
			db = DriverManager.getConnection(url, userID, password);
			/* --------------- */
		} catch (Exception e) {
			e.printStackTrace();
		} 		
		return db;
	}
	
	/**
	 * Servlet que genera Proyectos seg�n Autorizaci�n de Responsabilidad 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void performTask(HttpServletRequest request, 
								HttpServletResponse response) 
									throws IOException, ServletException {
		
		String usuario = request.getParameter("rut").trim();
		String credencial = request.getParameter("credencial").trim();
		String perfil = request.getParameter("login").trim();
		/* -------------------------------- */
		String autorizacion  = request.getParameter("autorizacion");
		String gerencia = request.getParameter("gerencia");
		String subgerencia = request.getParameter("subgerencia");
		String gerenciaI = request.getParameter("gerenciaI");
		String subgerenciaI = request.getParameter("subgerenciaI");
		String gerenciaInt = request.getParameter("gerenciaInt");
		String subgerenciaInt = request.getParameter("subgerenciaInt");
		String subgerente = request.getParameter("subgerente");
		String area = request.getParameter("area");
		String responsable = request.getParameter("responsable");
		String proyecto = request.getParameter("proyecto");
		String idsesion = request.getParameter("idsesion");
		String idP = null;
		/* -------------------------------- */
		Connection connection = null;
		PreparedStatement stmt = null;
		PreparedStatement stmtI = null;
		ResultSet rs = null;
		ResultSet rsI = null;
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
			
			String query = null;
			String queryP = null;
			String queryCategoria = null;
			int categoriaI = 0;
			String idProyecto;
			String nombreProyecto;
			String categoria;
			String liberty;
			String objetivoE;
			String prioridad;
			double totalCLP = 0;
			double totalUSD = 0;
			
			if (perfil.equals("1")){
				/* Administradores */
				query = "SELECT " +
						"`orgproyectos`.`idorgProyectos`, " +
						"`orgproyectos`.`orgNombreProyecto`, " +
						"`orgproyectos`.`orgCategoria`, " +
						"`orgproyectos`.`orgCategoriaLib`, " +
						"`orgproyectos`.`orgObjEstrategico`, " +
						"`orgproyectos`.`orgProyectoPrioridad`, "  +
						"SUM(`itemproyectoorgproyectos`.`totalCLPpxqorgitemProyecto`) " +
						"FROM `orgdata`.`orgproyectos`, `orgdata`.`itemproyectoorgproyectos` " +
						"WHERE `orgproyectos`.`idorgProyectos` = `itemproyectoorgproyectos`.`idorgProyectos` " +
						"GROUP BY `orgproyectos`.`idorgProyectos` " +
						"ORDER BY SUM(`itemproyectoorgproyectos`.`totalCLPpxqorgitemProyecto`) Desc";
				
			}else if (perfil.equals("2")){
				/* Gerentes */
				query = "SELECT " +
						"`orgproyectos`.`idorgProyectos`, " +
						"`orgproyectos`.`orgNombreProyecto`, " +
						"`orgproyectos`.`orgCategoria`, " +
						"`orgproyectos`.`orgCategoriaLib`, " +
						"`orgproyectos`.`orgObjEstrategico`, " +
						"`orgproyectos`.`orgProyectoPrioridad`, "  +
						"SUM(`itemproyectoorgproyectos`.`totalCLPpxqorgitemProyecto`) " +
						"FROM `orgdata`.`orgproyectos`, `orgdata`.`itemproyectoorgproyectos` " +
						"WHERE `orgproyectos`.`orgGerencia` =" + gerenciaInt +
						" AND `orgproyectos`.`idorgProyectos` = `itemproyectoorgproyectos`.`idorgProyectos` " +
						"GROUP BY `orgproyectos`.`idorgProyectos` " +
						"ORDER BY SUM(`itemproyectoorgproyectos`.`totalCLPpxqorgitemProyecto`) Desc";
										
			}else if (perfil.equals("3")){
				/* Sub Gerentes */
				query = "SELECT " +
						"`orgproyectos`.`idorgProyectos`, " +
						"`orgproyectos`.`orgNombreProyecto`, " +
						"`orgproyectos`.`orgCategoria`, " +
						"`orgproyectos`.`orgCategoriaLib`, " +
						"`orgproyectos`.`orgObjEstrategico`, " +
						"`orgproyectos`.`orgProyectoPrioridad`, "  +
						"SUM(`itemproyectoorgproyectos`.`totalCLPpxqorgitemProyecto`) " +
						"FROM `orgdata`.`orgproyectos`, `orgdata`.`itemproyectoorgproyectos` " +
						"WHERE `orgproyectos`.`orgGerencia` =" + gerenciaInt + 
						" AND `orgproyectos`.`orgSubGerencia` =" + subgerenciaInt +	
						" AND `orgproyectos`.`idorgProyectos` = `itemproyectoorgproyectos`.`idorgProyectos` " +
						"GROUP BY `orgproyectos`.`idorgProyectos` " +
						"ORDER BY SUM(`itemproyectoorgproyectos`.`totalCLPpxqorgitemProyecto`) Desc";
			}else if (perfil.equals("4")){
				/* Jefes de Proyecto */
				query = "SELECT " +
						"`orgproyectos`.`idorgProyectos`, " +
						"`orgproyectos`.`orgNombreProyecto`, " +
						"`orgproyectos`.`orgCategoria`, " +
						"`orgproyectos`.`orgCategoriaLib`, " +
						"`orgproyectos`.`orgObjEstrategico`, " +
						"`orgproyectos`.`orgProyectoPrioridad`, "  +
						"SUM(`itemproyectoorgproyectos`.`totalCLPpxqorgitemProyecto`) " +
						"FROM `orgdata`.`orgproyectos`, `orgdata`.`itemproyectoorgproyectos` " +
						"WHERE `orgproyectos`.`orgusuarioProyecto` ='" + usuario + "'" +
						" AND `orgproyectos`.`idorgProyectos` = `itemproyectoorgproyectos`.`idorgProyectos` " +
						"GROUP BY `orgproyectos`.`idorgProyectos` " +
						"ORDER BY SUM(`itemproyectoorgproyectos`.`totalCLPpxqorgitemProyecto`) Desc";
			}
							
			out.println("<div align=center>");	
				out.println("<div id=tableContainer class=tableContainer>");
				out.println("<table border=0 cellpadding=0 cellspacing=0 width=100% class=scrollTable>");
				/* ---------------------------------- */
				out.println("<thead class=fixedHeader>");
				out.println("<tr align=center>");
				out.println("<tr height=11>");
					out.println("<th><font face=Tahoma size=2 color=#ffffff>NOMBRE PROYECTO</font></th>");
					out.println("<th><font face=Tahoma size=2 color=#ffffff>CATEGORIA</font></th>");
					out.println("<th><font face=Tahoma size=2 color=#ffffff>LIBERTY</font></th>");
					out.println("<th><font face=Tahoma size=2 color=#ffffff>OBJ EST</font></th>");
					out.println("<th><font face=Tahoma size=2 color=#ffffff>PRIORIDAD</font></th>");
					out.println("<th><font face=Tahoma size=2 color=#ffffff>TOTAL USD</font></th>");;
					out.println("<th><font face=Tahoma size=2 color=#ffffff>TOTAL CLP</font></th>");;
					out.println("</tr>");
				out.println("</thead>");
				out.println("<tbody class=scrollContent>");
			try{	
				/* --------------------------------------- */
				stmt = getConector().prepareStatement(query);
				rs   = stmt.executeQuery();
				int id_P = 0;
				String pesosCLP = "", tpesosCLP = "";
				String dolaresCLP = "", tdolaresCLP = "";
				/* ------------------- */
				double TCLP = 0;
				double TUSD = 0;
				/* --------------------------------------- */
				NumberFormat fmt = new DecimalFormat("#,###");
				/* --------------------------------------- */
				boolean sw = false;
				/* --------------------------------------- */
				/* ----- Control Condicion de Borde ------ */
				/* --------------------------------------- */
				if (sw) {
					
					if (perfil.equals("1")){
						query = "SELECT " +
								"`orgproyectos`.`idorgProyectos`, " +
								"`orgproyectos`.`orgNombreProyecto`, " +
								"`orgproyectos`.`orgCategoria`, " +
								"`orgproyectos`.`orgCategoriaLib`, " +
								"`orgproyectos`.`orgObjEstrategico`, " +
								"`orgproyectos`.`orgProyectoPrioridad` "  +
								"FROM `orgdata`.`orgproyectos` ";
						stmt = getConector().prepareStatement(query);
						rs = stmt.executeQuery();
						sw = true;
					}
					
					if (perfil.equals("2")){
						query = "SELECT " +
								"`orgproyectos`.`idorgProyectos`, " +
								"`orgproyectos`.`orgNombreProyecto`, " +
								"`orgproyectos`.`orgCategoria`, " +
								"`orgproyectos`.`orgCategoriaLib`, " +
								"`orgproyectos`.`orgObjEstrategico`, " +
								"`orgproyectos`.`orgProyectoPrioridad` "  +
								"FROM `orgdata`.`orgproyectos` " +
								"WHERE `orgproyectos`.`orgGerencia` =" + gerenciaInt;
						stmt = getConector().prepareStatement(query);
						rs = stmt.executeQuery();
						sw = true;
					}
					
					if (perfil.equals("3")){
						query = "SELECT " +
								"`orgproyectos`.`idorgProyectos`, " +
								"`orgproyectos`.`orgNombreProyecto`, " +
								"`orgproyectos`.`orgCategoria`, " +
								"`orgproyectos`.`orgCategoriaLib`, " +
								"`orgproyectos`.`orgObjEstrategico`, " +
								"`orgproyectos`.`orgProyectoPrioridad` "  +
								"FROM `orgdata`.`orgproyectos` " +
								"WHERE `orgproyectos`.`orgGerencia` =" + gerenciaInt + 
								" AND `orgproyectos`.`orgSubGerencia` =" + subgerenciaInt;
						stmt = getConector().prepareStatement(query);
						rs = stmt.executeQuery();
						sw = true;
					}
					
					if (perfil.equals("4")){
						query = "SELECT " +
								"`orgproyectos`.`idorgProyectos`, " +
								"`orgproyectos`.`orgNombreProyecto`, " +
								"`orgproyectos`.`orgCategoria`, " +
								"`orgproyectos`.`orgCategoriaLib`, " +
								"`orgproyectos`.`orgObjEstrategico`, " +
								"`orgproyectos`.`orgProyectoPrioridad` "  +
								"FROM `orgdata`.`orgproyectos` "+
								"WHERE `orgproyectos`.`orgusuarioProyecto` ='" + usuario + "'";
						stmt = getConector().prepareStatement(query);
						rs = stmt.executeQuery();
						sw = true;
					}
	
				}
				/* --------------------------------------- */
				/* ------- fin condicion de borde -------- */
				/* --------------------------------------- */
				while(rs.next()){
					idProyecto     = rs.getString(1); 
					id_P = Integer.parseInt(idProyecto);
					nombreProyecto = rs.getString(2);
					categoria      = rs.getString(3);
					if (categoria.equals("1") ){
						queryCategoria = "Mantenci�n de la Operaci�n Actual";
					}else if(categoria.equals("2")){
						queryCategoria = "Crecimiento";
					}else if(categoria.equals("3")){
						queryCategoria = "Otras Iniciativas";
					}
					liberty        = rs.getString(4);
					objetivoE      = rs.getString(5);
					prioridad      = rs.getString(6);
					/* ----------------------------------  */
					if(sw) {
						totalCLP = 0; 
					}else{
						totalCLP = rs.getDouble(7);
					}
					/* ----------------------------------  */
					pesosCLP = fmt.format(Math.ceil(totalCLP));
					totalUSD = totalCLP/485; 	//Tipo Cambio 16-11-2011
					dolaresCLP = fmt.format(Math.ceil(totalUSD));
					TCLP = TCLP + totalCLP;
					TUSD = TCLP/485; 			//Tipo Cambio 16-11-2011
					/* Registro */
					out.println("<tr>");
					out.println("<td align=left><a href=\"orgIngresoU.jsp?idP="+idProyecto+
																   "&idsesion="+idsesion+
																   "&autorizacion="+autorizacion+
																   "&perfil="+perfil+
																   "&rut="+usuario+
																   "&subgerente="+subgerente+
															       "&area="+area+
																   "&responsable="+responsable+
																   "&gerencia="+gerencia+
																   "&subgerencia="+subgerencia+"\">" + nombreProyecto + "</td>");													
					out.println("<td align=left>" + queryCategoria + "</td>");
					out.println("<td align=left>" + liberty + "</td>");
					out.println("<td align=left>" + objetivoE + "</td>");
					out.println("<td align=left>" + prioridad + "</td>");
					out.println("<td align=right>" + dolaresCLP + "</td>");
					out.println("<td align=right>" + pesosCLP + "</td>");
					out.println("</tr>");
					
					}
				    /* ----------------------------------  */ 
					tpesosCLP = fmt.format(Math.ceil(TCLP));
					tdolaresCLP = fmt.format(Math.ceil(TUSD));
					/* ----------------------------------  */
					out.println("<tr align=right bgcolor=#DF0101>");						
					out.println("<td colspan=5><font color=#ff000 size=2><b>TOTAL GENERAL:</b></font></td>");
					out.println("<td><font color=#ff000 size=2><b>"+ tdolaresCLP + "</b></font></td>");
					out.println("<td><font color=#ff000 size=2><b>" + tpesosCLP + "</b></font></td>");
					out.println("</tr>");
				/* ------------------ */
				out.println("</tbody>");	
				out.println("</table>");
				out.println("</div>" );
				/* ----------------------------------------- */	
				out.println("</div>" );
			/* -------------------- */	
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
	/** performTask */
}
