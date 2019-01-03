package planeacion.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import planeacion.persistencia.ConexionP;
import planeacion.persistencia.DTOMensualizado;
import planeacion.persistencia.UsuarioAutorizacion;
import planeacion.persistencia.DTOProyecto;
import planeacion.persistencia.DTOProyectos;
import planeacion.persistencia.DTOItemProyecto;

public class ServicesController {
	
	public int executeSQLIDProyectos() throws Exception, SQLException {
		Connection db = null;
		ResultSet resultSet = null;
		String query = null;
		Statement dataRequest = null;
		DTOProyecto Controles = null;		
		query = "SELECT COUNT(*) FROM orgdata.orgproyectos";		
		System.out.println("Query : " + query);	
		int id = 0;		
		 try {
				db = new ConexionP().getConection();
				dataRequest = db.createStatement();
				resultSet = dataRequest.executeQuery(query);	
				boolean records = resultSet.next();				
				if (records) {				
					Controles = new DTOProyecto();
					Controles.setIdP(resultSet.getString(1));
					id = resultSet.getInt(1) + 1;
					System.out.println("Count : " + id);					
				}else {					
					System.err.println("error resultado vacio");
					throw new SQLException("Resultado vacio de tabla ");
				}			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (db != null) {
					db.close();
				}
			}
			return id;
	}
	
	/**
	 * 
	 * @param rut
	 * @param credencial
	 * @param autorizacion
	 * @return
	 * @throws SQLException
	 */
	public UsuarioAutorizacion loginByAutorizacion(String rut, String credencial, String autorizacion) throws SQLException{
		ResultSet resultSet = null;
		String query = null;
		Statement dataRequest = null;
		UsuarioAutorizacion Autorizacion = null;
		if (rut == null) {
			try {
				throw new Exception("Debe especificar criterio de busqueda");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Connection db = null;	
		query = "SELECT a.idorgUsuarios, " +
	    					"b.orgDescripcionPerfil, " +
	    				 	"a.orgUsuario, " +
	    				  	"a.orgCredencial, " +
	    				  	"a.orgNombreUsuario, " +
	    				  	"a.orgApPaternoUsuario, " +
	    				  	"a.orgApMaternoUsuario, " +
	    				  	"a.orgAltaUsuario, " +
	    				  	"a.orgFechaAltaUsuario, " +
	    				  	"a.orgObservacionUsuario " +
	    		"FROM `orgdata`.`orgusuarios` a, `orgdata`.`orgperfiles` b, `orgdata`.`orgautorizaciones` c " +
	    		"WHERE b.orgCodigoPerfil = '" + autorizacion +
	    		"' AND a.orgUsuario  = '" + rut +
	    		"' AND a.orgCredencial = '" + credencial + 
	    		"' AND c.idorgautorizaciones = b.orgCodigoPerfil" +
	    		"  AND a.orgPerfiles_idorgPerfiles = b.orgCodigoPerfil"; 
		try {
			try {
				db = new ConexionP().getConection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			dataRequest = db.createStatement();
			resultSet = dataRequest.executeQuery(query);			
			boolean records = resultSet.next();
			if (!records) {		
				
				throw new SQLException("error resultado vacio tabla " + "USUARIOS o PERFIL"); 
				
			}else{			
				Autorizacion = new UsuarioAutorizacion();	
				
				Autorizacion.setRut(rut);
				Autorizacion.setCredencial(credencial);
				Autorizacion.setAutorizacion(resultSet.getString(2));
				Autorizacion.setNombres(resultSet.getString(5));
				Autorizacion.setApaterno(resultSet.getString(6));
				Autorizacion.setAmaterno(resultSet.getString(7));
				Autorizacion.setDescripcion(resultSet.getString(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return Autorizacion;
	}
	
	/**
	 * 
	 * @param rut
	 * @return
	 * @throws SQLException
	 */
	public UsuarioAutorizacion loginByRut(String rut) throws SQLException{
		ResultSet resultSet = null;
		String query = null;
		Statement dataRequest = null;
		UsuarioAutorizacion Autorizacion = null;
		if (rut == null) {
			try {
				throw new Exception("Debe especificar criterio de busqueda");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Connection db = null;	
		query = "SELECT `orgusuarios`.`idorgUsuarios`, " +
	    				"`orgperfiles`.`orgDescripcionPerfil`, " +
	    				"`orgusuarios`.`orgUsuario`, " +
	    				"`orgusuarios`.`orgCredencial`, " +
	    				"`orgusuarios`.`orgNombreUsuario`, " +
	    				"`orgusuarios`.`orgApPaternoUsuario`, " +
	    				"`orgusuarios`.`orgApMaternoUsuario`, " +
	    				"`orgusuarios`.`orgAltaUsuario`, " +
	    				"`orgusuarios`.`orgFechaAltaUsuario`, " +
	    				"`orgusuarios`.`orgObservacionUsuario` " +
	    		"FROM `orgdata`.`orgusuarios`, `orgdata`.`orgperfiles` " +
	    		"WHERE `orgusuarios`.`orgUsuario`  = '" + rut + 
	    		"' AND `orgusuarios`.`orgUsuario` = `orgperfiles`.`orgRutPerfil`"; 
		try {
			try {
				db = new ConexionP().getConection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			dataRequest = db.createStatement();
			resultSet = dataRequest.executeQuery(query);			
			boolean records = resultSet.next();
			if (!records) {		
				
				throw new SQLException("error resultado vacio tabla " + "USUARIOS o PERFIL"); 
				
			}else{			
				Autorizacion = new UsuarioAutorizacion();	
				
				Autorizacion.setRut(rut);
				Autorizacion.setCredencial(resultSet.getString(4));
				Autorizacion.setAutorizacion(resultSet.getString(2));
				Autorizacion.setNombres(resultSet.getString(5));
				Autorizacion.setApaterno(resultSet.getString(6));
				Autorizacion.setAmaterno(resultSet.getString(7));
				Autorizacion.setDescripcion(resultSet.getString(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return Autorizacion;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public int executeSQLIdP() throws Exception, SQLException {
		Connection db = null;
		ResultSet resultSet = null;
		String query = null;
		Statement dataRequest = null;
		DTOProyecto idP = null;		
		query ="SELECT `orgproyectos`.`idorgProyectos` FROM orgdata.orgproyectos ORDER BY idorgProyectos DESC";	
		int id = 0;		
		 try {
				db = new ConexionP().getConection();
				dataRequest = db.createStatement();
				resultSet = dataRequest.executeQuery(query);	
				boolean records = resultSet.next();				
				if (records) {				
					idP = new DTOProyecto();
					idP.setIdP(resultSet.getString(1));
					id = resultSet.getInt(1);				
				}else {					
					System.err.println("error resultado vacio tabla Proyectos");
					throw new SQLException("Resultado vacio de tabla Proyectos");
				}			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (db != null) {
					db.close();
				}
			}
			return id;
	}
	
	
    /**
     * 
     * @param id
     * @return
     */
	public DTOProyecto TraeProyectoById(String id){
		/* ----------------------------- */
		ResultSet resultSet = null;
		String query = null;
		Statement dataRequest = null;
		DTOProyecto Proyecto = null;
		Connection db = null;
		/* ----------------------------- */
		query = "SELECT " +
				"`orgproyectos`.`idorgProyectos`, " +
				"`orgproyectos`.`orgusuarioProyecto`, " +
				"`orgproyectos`.`orgCategoria`, " +
				"`orgproyectos`.`orgCategoriaLib`, " +
				"`orgproyectos`.`orgSubLiberty`, " +
				"`orgproyectos`.`orgObjEstrategico`, " +
				"`orgproyectos`.`orgGerencia`, " +
				"`orgproyectos`.`orgSubGerencia`, " +
				"`orgproyectos`.`orgNombreProyecto`, " +
				"`orgproyectos`.`orgDescripcionProyecto`, " +
				"`orgproyectos`.`orgProyectoPrioridad`, " +
				"`orgproyectos`.`orgEstadoProyecto`,"  +
				"`orgproyectos`.`orgFechaEstadoProyecto`, " +
				"`orgproyectos`.`orgVersionProyecto`, " +
				"`orgproyectos`.`orgFechaversionProyectos`, " +
				"`orgproyectos`.`I1`, " +
				"`orgproyectos`.`I2`, " +
				"`orgproyectos`.`I3`, " +
				"`orgproyectos`.`I4`, " +
				"`orgproyectos`.`I5`, " +
				"`orgproyectos`.`I6`, " +
				"`orgproyectos`.`I7`, " +
				"`orgproyectos`.`I8`, " +
				"`orgproyectos`.`I9`  "+
				"FROM `orgdata`.`orgproyectos` " +
				"WHERE `orgproyectos`.`idorgProyectos`=" + id;
		
	 		try{
	 			try {
					db = new ConexionP().getConection();
				} catch (Exception e) {
					e.printStackTrace();
				}
	 			/* ----------------------------- */
				dataRequest = db.createStatement();
				resultSet = dataRequest.executeQuery(query);
				/* ----------------------------- */				
				boolean records = resultSet.next();
				if (!records) {		
					throw new SQLException("error resultado vacio tabla " + "PROYECTOS"); 		
				}else{	
					/* ----------------------- */
					Proyecto = new DTOProyecto();
					/* ----------------------- */
					Proyecto.setIdP(resultSet.getString(1));
					Proyecto.setRut(resultSet.getString(2));
					Proyecto.setCategoria(resultSet.getString(3));
					Proyecto.setLiberty(resultSet.getString(4));
					Proyecto.setSubliberty(resultSet.getString(5));
					Proyecto.setObjetivoE(resultSet.getString(6));
					Proyecto.setGerenciaI(resultSet.getString(7));
					Proyecto.setSubgerenciaI(resultSet.getString(8));
					Proyecto.setProyecto(resultSet.getString(9));
					Proyecto.setDescripcionP(resultSet.getString(10));
					Proyecto.setPrioridad(resultSet.getString(11));
					Proyecto.setEstadoP(resultSet.getString(12));
					Proyecto.setFechaEstadoProyecto(resultSet.getString(13));
					Proyecto.setVersionProyecto(resultSet.getString(14));
					Proyecto.setFechaversionProyectos(resultSet.getString(15));
					Proyecto.setI1(resultSet.getString(16));
					Proyecto.setI2(resultSet.getString(17));
					Proyecto.setI3(resultSet.getString(18));
					Proyecto.setI4(resultSet.getString(19));
					Proyecto.setI5(resultSet.getString(20));
					Proyecto.setI6(resultSet.getString(21));
					Proyecto.setI7(resultSet.getString(22));
					Proyecto.setI8(resultSet.getString(23));
					Proyecto.setI9(resultSet.getString(24));
					/* ----------------------- */
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				try {
					db.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
	 	return Proyecto;
	}
	
	/**
	 * 
	 * @param String SQL Proyectos por Autorizacion
	 * @return listadoProyectos por Perfil
	 * @throws SQLException
	 */
	public ArrayList<DTOProyectos> listaProyectosByAutorizacion(String SQL) throws SQLException {
		
		ResultSet resultSet = null;
		String query = null;
		Statement dataRequest = null;
		DTOProyectos proyectos = null;
		double TCLP = 0;
		ArrayList<DTOProyectos> listadoProyectos = new ArrayList<DTOProyectos>();
		
		try {		
			Connection db = null;
			try {
				db = new ConexionP().getConection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			dataRequest = db.createStatement();
			resultSet = dataRequest.executeQuery(SQL);
		
			while (resultSet.next()) {		
				
				proyectos = new DTOProyectos();
				
				proyectos.setIdProyecto(resultSet.getString(1));
				proyectos.setNombreProyecto(resultSet.getString(2));
				proyectos.setCategoria(resultSet.getString(3));
				proyectos.setCategoriaLiberty(resultSet.getString(4));
				proyectos.setObjetivoEstrategico(resultSet.getString(5));
				proyectos.setPrioridad(resultSet.getString(6));
				TCLP = resultSet.getDouble(7);
				proyectos.setTotalCLP(TCLP);
				
				listadoProyectos.add(proyectos);
				
			}
		
		}  catch (Exception e) {
			e.printStackTrace();
		} 
		return listadoProyectos;	
	}
	
	
	/**
	 * 
	 * @param idP
	 * @return
	 * @throws SQLException
	 */
	public int SumItemsProyecto(int idP) throws SQLException{
		Connection db = null;
		ResultSet resultSet = null;
		Statement dataRequest = null;
		String query = null;
		int SumaItemsProyecto = 0;
		
		query = "SELECT " +
				"SUM(`itemproyectoorgproyectos`.`totalCLPpxqorgitemProyecto`) " +
				"FROM `orgdata`.`itemproyectoorgproyectos` " +
				"WHERE `itemproyectoorgproyectos`.`idorgProyectos` = " + idP;
		
		try {		
			try {
				db = new ConexionP().getConection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			dataRequest = db.createStatement();
			resultSet = dataRequest.executeQuery(query);
			
			while (resultSet.next())
			{				
				SumaItemsProyecto = resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return SumaItemsProyecto;
	}
	
	public double SumItemProyecto(int idP, int idI) throws SQLException{
		Connection db = null;
		ResultSet resultSet = null;
		Statement dataRequest = null;
		String query = null;
		double SumaItemProyecto = 0;
		
		query = "SELECT " +
				"SUM(`itemproyectoorgproyectos`.`totalCLPpxqorgitemProyecto`) " +
				"FROM `orgdata`.`itemproyectoorgproyectos` " +
				"WHERE `itemproyectoorgproyectos`.`idorgProyectos` = " + idP +
				" AND `itemproyectoorgproyectos`.`idItem` = " + idI;
		
		try {		
			try {
				db = new ConexionP().getConection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			dataRequest = db.createStatement();
			resultSet = dataRequest.executeQuery(query);
			
			while (resultSet.next())
			{				
				SumaItemProyecto = resultSet.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return SumaItemProyecto;
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<DTOItemProyecto> listaItemsProyecto(int tipo) throws SQLException{		
		ResultSet resultSet = null;
		String query = null;
		Statement dataRequest = null;
		DTOItemProyecto Items = null;
		ArrayList<DTOItemProyecto> listadoItems = new ArrayList<DTOItemProyecto>();  		
		Connection db = null;	
		query = "SELECT " +
				"`itemproyectoorgproyectos`.`idorgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`idItem`, " +
				"`itemproyectoorgproyectos`.`idorgProyectos`, " +
				"`itemproyectoorgproyectos`.`descripcionorgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`proveedororgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`monedaorgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`porgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`qorgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`estimadoPago`, " +
				"`itemproyectoorgproyectos`.`totalUSDpxqorgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`totalCLPpxqorgitemProyecto` " +
				"FROM `orgdata`.`itemproyectoorgproyectos` " +
				"WHERE `itemproyectoorgproyectos`.`idItem` = " + tipo +
				" ORDER BY `itemproyectoorgproyectos`.`totalCLPpxqorgitemProyecto` DESC";
		try {		
			try {
				db = new ConexionP().getConection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			dataRequest = db.createStatement();
			resultSet = dataRequest.executeQuery(query);
			
			while (resultSet.next())
			{				
				Items = new DTOItemProyecto();	
				
				Items.setIdI(resultSet.getInt(1));
				Items.setIdItem(resultSet.getInt(2));
				Items.setIdP(resultSet.getInt(3));
				Items.setDescripcionI(resultSet.getString(4));
				Items.setProveedorI(resultSet.getString(5));
				Items.setMoneda(resultSet.getString(6));
				Items.setPriceI(resultSet.getDouble(7));
				Items.setQty(resultSet.getInt(8));
				Items.setEstimadoPI(resultSet.getString(9));
				Items.setTusd(resultSet.getDouble(10));
				Items.setTclp(resultSet.getDouble(11));
				/* ------------------- */
				listadoItems.add(Items);
				/* ------------------- */
			}		
		} finally{
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return listadoItems;
	}
	
	/**
	 * 
	 * @param tipo
	 * @param idP
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<DTOItemProyecto> listaItemsProyectoP(int tipo, int idP) throws SQLException{		
		ResultSet resultSet = null;
		String query = null;
		Statement dataRequest = null;
		DTOItemProyecto Items = null;
		ArrayList<DTOItemProyecto> listadoItemsP = new ArrayList<DTOItemProyecto>();  		
		Connection db = null;	
		query = "SELECT " +
				"`itemproyectoorgproyectos`.`idorgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`idItem`, " +
				"`itemproyectoorgproyectos`.`idorgProyectos`, " +
				"`itemproyectoorgproyectos`.`descripcionorgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`proveedororgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`monedaorgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`porgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`qorgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`estimadoPago`, " +
				"`itemproyectoorgproyectos`.`totalUSDpxqorgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`totalCLPpxqorgitemProyecto` " +
				"FROM `orgdata`.`itemproyectoorgproyectos` " +
				"WHERE `itemproyectoorgproyectos`.`idItem` = " + tipo + " AND `itemproyectoorgproyectos`.`idorgProyectos` =" + idP +
				" ORDER BY `itemproyectoorgproyectos`.`idItem` ASC, `itemproyectoorgproyectos`.`totalCLPpxqorgitemProyecto` DESC";
		try {		
			try {
				db = new ConexionP().getConection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			dataRequest = db.createStatement();
			resultSet = dataRequest.executeQuery(query);
			
			while (resultSet.next())
			{				
				Items = new DTOItemProyecto();	
				
				Items.setIdI(resultSet.getInt(1));
				Items.setIdItem(resultSet.getInt(2));
				Items.setIdP(resultSet.getInt(3));
				Items.setDescripcionI(resultSet.getString(4));
				Items.setProveedorI(resultSet.getString(5));
				Items.setMoneda(resultSet.getString(6));
				Items.setPriceI(resultSet.getDouble(7));
				Items.setQty(resultSet.getInt(8));
				Items.setEstimadoPI(resultSet.getString(9));
				Items.setTusd(resultSet.getDouble(10));
				Items.setTclp(resultSet.getDouble(11));
				/* ------------------- */
				listadoItemsP.add(Items);
				/* ------------------- */
			}		
		} finally{
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return listadoItemsP;
	}
	
	/**
	 * listaItemsProyectoT
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<DTOItemProyecto> listaItemsProyectoT(String idP) throws SQLException{		
		ResultSet resultSet = null;
		String query = null;
		Statement dataRequest = null;
		DTOItemProyecto Items = null;
		ArrayList<DTOItemProyecto> listadoItems = new ArrayList<DTOItemProyecto>();  		
		Connection db = null;	
		query = "SELECT " +
				"`itemproyectoorgproyectos`.`idorgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`idItem`, " +
				"`itemproyectoorgproyectos`.`idorgProyectos`, " +
				"`itemproyectoorgproyectos`.`descripcionorgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`proveedororgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`monedaorgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`porgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`qorgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`estimadoPago`, " +
				"`itemproyectoorgproyectos`.`totalUSDpxqorgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`totalCLPpxqorgitemProyecto` " +
				"FROM `orgdata`.`itemproyectoorgproyectos` " +
				"WHERE `itemproyectoorgproyectos`.`idorgProyectos` = "+ idP + 
				" ORDER BY `itemproyectoorgproyectos`.`idItem` ASC, `itemproyectoorgproyectos`.`idorgitemProyecto` ASC ";
		try {		 
			try {
				db = new ConexionP().getConection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			dataRequest = db.createStatement();
			resultSet = dataRequest.executeQuery(query);
			
			while (resultSet.next())
			{				
				Items = new DTOItemProyecto();	
				
				Items.setIdI(resultSet.getInt(1));
				Items.setIdItem(resultSet.getInt(2));
				Items.setIdP(resultSet.getInt(3));
				Items.setDescripcionI(resultSet.getString(4));
				Items.setProveedorI(resultSet.getString(5));
				Items.setMoneda(resultSet.getString(6));
				Items.setPriceI(resultSet.getDouble(7));
				Items.setQty(resultSet.getInt(8));
				Items.setEstimadoPI(resultSet.getString(9));
				Items.setTusd(resultSet.getDouble(10));
				Items.setTclp(resultSet.getDouble(11));
				/* ------------------- */
				listadoItems.add(Items);
				/* ------------------- */
			}		
		} finally{
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return listadoItems;
	}
	
	/**
	 * listaItemsValor
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<DTOMensualizado> listaItemsValor(String idP) throws SQLException{		
		ResultSet resultSet = null;
		String query = null;
		Statement dataRequest = null;
		DTOMensualizado Items = null;
		ArrayList<DTOMensualizado> listadoItemsV = new ArrayList<DTOMensualizado>();  		
		Connection db = null;	
		query = "SELECT " +
				"`orgmensualizado`.`idorgMensualizado`, " +
				"`orgmensualizado`.`idProyecto`, " +
				"`orgmensualizado`.`idTipo`, " +
				"`orgmensualizado`.`idItem`, " +
				"`orgmensualizado`.`octa`, " +
				"`orgmensualizado`.`nova`, " +
				"`orgmensualizado`.`dica`, " +
				"`orgmensualizado`.`ene`, " +
				"`orgmensualizado`.`feb`, " +
				"`orgmensualizado`.`mar`, " +
				"`orgmensualizado`.`abr`, " +
				"`orgmensualizado`.`may`, " +
				"`orgmensualizado`.`jun`, " +
				"`orgmensualizado`.`jul`, " +
				"`orgmensualizado`.`ago`, " +
				"`orgmensualizado`.`sep`, " +
				"`orgmensualizado`.`oct`, " +
				"`orgmensualizado`.`nov`, " +
				"`orgmensualizado`.`dic`, " +
				"`orgmensualizado`.`total`, " +
				"`orgmensualizado`.`fechamensualizado` " +
				"FROM `orgdata`.`orgmensualizado` " +
				"WHERE `orgmensualizado`.`idProyecto` = "+ idP + 
				" ORDER BY  `orgmensualizado`.`idTipo` ASC, `orgmensualizado`.`idItem` ASC";
		try {		 
			try {
				db = new ConexionP().getConection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			dataRequest = db.createStatement();
			resultSet = dataRequest.executeQuery(query);
			
			while (resultSet.next())
			{				
				Items = new DTOMensualizado();	
				
				Items.setIdM(resultSet.getInt(1));
				Items.setIdP(resultSet.getInt(2));
				Items.setIdT(resultSet.getInt(3));
				Items.setIdI(resultSet.getInt(4));
				Items.setOcta(resultSet.getDouble(5));
				Items.setNova(resultSet.getDouble(6));
				Items.setDica(resultSet.getDouble(7));
				Items.setEne(resultSet.getDouble(8));
				Items.setFeb(resultSet.getDouble(9));
				Items.setMar(resultSet.getDouble(10));
				Items.setAbr(resultSet.getDouble(11));
				Items.setMay(resultSet.getDouble(12));
				Items.setJun(resultSet.getDouble(13));
				Items.setJul(resultSet.getDouble(14));
				Items.setAgo(resultSet.getDouble(15));
				Items.setSep(resultSet.getDouble(16));
				Items.setOct(resultSet.getDouble(17));
				Items.setNov(resultSet.getDouble(18));
				Items.setDic(resultSet.getDouble(19));
				Items.setTotal(resultSet.getDouble(20));
				Items.setFechaM(resultSet.getString(21));
				/* ------------------- */
				listadoItemsV.add(Items);
				/* ------------------- */
			}		
		} finally{
			if (db != null) {
				db.close();
			}
		}		
		return listadoItemsV;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public DTOItemProyecto TraeItemById(String id) throws SQLException{
		/* ----------------------------- */
		ResultSet resultSet = null;
		String query = null;
		Statement dataRequest = null;
		DTOItemProyecto Item = null;
		Connection db = null;
		/* ----------------------------- */
		query = "SELECT " +
				"`itemproyectoorgproyectos`.`idorgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`idItem`, " +
				"`itemproyectoorgproyectos`.`idorgProyectos`, " +
				"`itemproyectoorgproyectos`.`descripcionorgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`proveedororgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`monedaorgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`porgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`qorgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`estimadoPago`, " +
				"`itemproyectoorgproyectos`.`totalUSDpxqorgitemProyecto`, " +
				"`itemproyectoorgproyectos`.`totalCLPpxqorgitemProyecto` " +
				"FROM `orgdata`.`itemproyectoorgproyectos` " +
				"WHERE `itemproyectoorgproyectos`.`idorgitemProyecto`=" + id;
		
	 		try{
	 			try {
					db = new ConexionP().getConection();
				} catch (Exception e) {
					e.printStackTrace();
				}
	 			/* ----------------------------- */
				dataRequest = db.createStatement();
				resultSet = dataRequest.executeQuery(query);
				/* ----------------------------- */				
				boolean records = resultSet.next();
				if (!records) {		
					throw new SQLException("error resultado vacio tabla " + "ITEM PROYECTOS"); 		
				}else{	
					/* ----------------------- */
					Item = new DTOItemProyecto();	
					/* ----------------------- */
					Item.setIdI(resultSet.getInt(1));
					Item.setIdItem(resultSet.getInt(2));
					Item.setIdP(resultSet.getInt(3));
					Item.setDescripcionI(resultSet.getString(4));
					Item.setProveedorI(resultSet.getString(5));
					Item.setMoneda(resultSet.getString(6));
					Item.setPriceI(resultSet.getDouble(7));
					Item.setQty(resultSet.getInt(8));
					Item.setEstimadoPI(resultSet.getString(9));
					Item.setTusd(resultSet.getDouble(10));
					Item.setTclp(resultSet.getDouble(11));
					/* ----------------------- */
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				if (db != null) {
					db.close();
				}
			}	
	 	return Item;
	}
	
	/**
	 * 
	 * @param gerencia
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public String executeSQLGerencia(String gerencia) throws Exception, SQLException {
		Connection db = null;
		ResultSet resultSet = null;
		String query = null;
		Statement dataRequest = null;
		UsuarioAutorizacion Autorizacion = null;		
		query = "SELECT descripcionorgGerencia FROM `orgdata`.`orggerencias` WHERE `orggerencias`.`idorgGerencia` = '" + gerencia + "'";
		//System.out.println("QueryPerfil : " + query);	
		String descripcion = "0";	
		 try {
				db = new ConexionP().getConection();	
				dataRequest = db.createStatement();
				resultSet = dataRequest.executeQuery(query);		
				boolean records = resultSet.next();		
				if (records) {			
					gerencia = resultSet.getString(1);			
					//System.out.println("Gerencia : " + gerencia);			
				}else {			
					System.err.println("error resultado vacio");
					throw new SQLException("Resultado vacio de tabla ");
				}			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (db != null) {
					db.close();
				}
			}
		return gerencia;
	}
	
	/**
	 * 
	 * @param gerencia
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public int executeSQLGerenciaI(String gerencia) throws Exception, SQLException {
		Connection db = null;
		ResultSet resultSet = null;
		String query = null;
		Statement dataRequest = null;
		UsuarioAutorizacion Autorizacion = null;
		int gerenciaI = 0;
		query = "SELECT `orggerencias`.`idorgGerencia` FROM `orgdata`.`orggerencias` WHERE `orggerencias`.`descripcionorgGerencia` = '" + gerencia + "'";
		//System.out.println("QueryPerfil : " + query);	
		int descripcion = 0;	
		 try {
				db = new ConexionP().getConection();	
				dataRequest = db.createStatement();
				resultSet = dataRequest.executeQuery(query);		
				boolean records = resultSet.next();		
				if (records) {			
					gerenciaI = resultSet.getInt(1);			
					//System.out.println("Gerencia : " + gerencia);			
				}else {			
					System.err.println("error resultado vacio");
					throw new SQLException("Resultado vacio de tabla ");
				}			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (db != null) {
					db.close();
				}
			}
		return gerenciaI;
	}

	/**
	 * 
	 * @param subgerencia
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public String executeSQLSubGerencia(String subgerencia) throws Exception, SQLException {
		Connection db = null;
		ResultSet resultSet = null;
		String query = null;
		Statement dataRequest = null;
		UsuarioAutorizacion Autorizacion = null;		
		query = "SELECT descripcionorgSubgerencias FROM `orgdata`.`orgsubgerencias` WHERE `orgsubgerencias`.`idorgSubgerencias` = '" + subgerencia + "'";
		//System.out.println("QueryPerfil : " + query);	
		String descripcion = "0";	
		 try {
				db = new ConexionP().getConection();	
				dataRequest = db.createStatement();
				resultSet = dataRequest.executeQuery(query);		
				boolean records = resultSet.next();		
				if (records) {			
					subgerencia = resultSet.getString(1);			
					System.out.println("SubGerencia : " + subgerencia);			
				}else {			
					System.err.println("error resultado vacio");
					throw new SQLException("Resultado vacio de tabla ");
				}			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (db != null) {
					db.close();
				}
			}
		return subgerencia;
	}
	
	/**
	 * 
	 * @param subgerencia
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public int executeSQLSubGerenciaI(String subgerencia) throws Exception, SQLException {
		Connection db = null;
		ResultSet resultSet = null;
		String query = null;
		Statement dataRequest = null;
		UsuarioAutorizacion Autorizacion = null;	
		int subgerenciaI = 0;
		query = "SELECT `orgsubgerencias`.`idorgSubgerencias` FROM `orgdata`.`orgsubgerencias` WHERE `orgsubgerencias`.`descripcionorgSubgerencias` = '" + subgerencia + "'";
		//System.out.println("QueryPerfil : " + query);	
		String descripcion = "0";	
		 try {
				db = new ConexionP().getConection();	
				dataRequest = db.createStatement();
				resultSet = dataRequest.executeQuery(query);		
				boolean records = resultSet.next();		
				if (records) {			
					subgerenciaI = resultSet.getInt(1);			
					System.out.println("SubGerencia : " + subgerencia);			
				}else {			
					System.err.println("error resultado vacio");
					throw new SQLException("Resultado vacio de tabla ");
				}			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (db != null) {
					db.close();
				}
			}
		return subgerenciaI;
	}
	
	/**
	 * 
	 * @param categoria
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public String executeSQLCategoria(String categoria) throws Exception, SQLException {
		Connection db = null;
		ResultSet resultSet = null;
		String query = null;
		Statement dataRequest = null;		
		query = "SELECT descripcionorgCategoria FROM `orgdata`.`orgcategorias` WHERE `orgcategorias`.`codorgCategorias` = '" + categoria + "'";
		//System.out.println("QueryPerfil : " + query);	
		 try {
				db = new ConexionP().getConection();	
				dataRequest = db.createStatement();
				resultSet = dataRequest.executeQuery(query);		
				boolean records = resultSet.next();		
				if (records) {			
					categoria = resultSet.getString(1);			
					System.out.println("Categoria : " + categoria);			
				}else {			
					System.err.println("error resultado vacio");
					throw new SQLException("Resultado vacio de tabla ");
				}			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (db != null) {
					db.close();
				}
			}
		return categoria;
	}
	
	/**
	 * 
	 * @param liberty
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public String executeSQLLiberty(String liberty) throws Exception, SQLException {
		Connection db = null;
		ResultSet resultSet = null;
		String query = null;
		Statement dataRequest = null;		
		query = "SELECT descripcionorgCatLiberty FROM `orgdata`.`orgcatliberty` WHERE `orgcatliberty`.`idorgCatLiberty` = '" + liberty + "'";
		//System.out.println("QueryPerfil : " + query);	
		 try {
				db = new ConexionP().getConection();	
				dataRequest = db.createStatement();
				resultSet = dataRequest.executeQuery(query);		
				boolean records = resultSet.next();		
				if (records) {			
					liberty = resultSet.getString(1);			
					System.out.println("Categoria Liberty : " + liberty);			
				}else {			
					System.err.println("error resultado vacio");
					throw new SQLException("Resultado vacio de tabla ");
				}			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (db != null) {
					db.close();
				}
			}
		return liberty;
	}
	
	/**
	 * 
	 * @param subliberty
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public String executeSQLSubLiberty(String subliberty) throws Exception, SQLException {
		Connection db = null;
		ResultSet resultSet = null;
		String query = null;
		Statement dataRequest = null;		
		query = "SELECT descripcionorgSubLiberty FROM `orgdata`.`orgsubliberty` WHERE `orgsubliberty`.`idorgSubLiberty` = '" + subliberty + "'";
		//System.out.println("QueryPerfil : " + query);	
		 try {
				db = new ConexionP().getConection();	
				dataRequest = db.createStatement();
				resultSet = dataRequest.executeQuery(query);		
				boolean records = resultSet.next();		
				if (records) {			
					subliberty = resultSet.getString(1);			
					System.out.println("Sub Categoria Liberty : " + subliberty);			
				}else {			
					System.err.println("error resultado vacio");
					throw new SQLException("Resultado vacio de tabla ");
				}			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (db != null) {
					db.close();
				}
			}
		return subliberty;
	}
	
	/**
	 * 
	 * @param objetivoE
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public String executeSQLObjEstrategico(String objetivoE) throws Exception, SQLException {
		Connection db = null;
		ResultSet resultSet = null;
		String query = null;
		Statement dataRequest = null;		
		query = "SELECT descripcionorgObjEstrategicos FROM `orgdata`.`orgobjestrategicos` WHERE `orgobjestrategicos`.`idorgObjEstrategicos` = '" + objetivoE + "'";
		//System.out.println("QueryPerfil : " + query);	
		 try {
				db = new ConexionP().getConection();	
				dataRequest = db.createStatement();
				resultSet = dataRequest.executeQuery(query);		
				boolean records = resultSet.next();		
				if (records) {			
					objetivoE = resultSet.getString(1);			
					System.out.println("Objetivo Estrategico : " + objetivoE);			
				}else {			
					System.err.println("error resultado vacio");
					throw new SQLException("Resultado vacio de tabla ");
				}			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (db != null) {
					db.close();
				}
			}
		return objetivoE;
	}
	
	/**
	 * 
	 * @param sSQL
	 * @return
	 * @throws SQLException
	 */
	public int executeSQL(String sSQL) throws SQLException {
		Connection db = null;
		Statement dataRequest = null;
		boolean resultSet = false;
		int retorno = 0;
		String query = null;
		try {
			// -----------------------------------/
			try {
				db = new ConexionP().getConection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			query = sSQL;
			dataRequest = db.createStatement();
			resultSet = dataRequest.execute(query);
			// -----------------------------------/
			if (resultSet) {
				retorno = 1;
			}else
				retorno = 0;
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (db != null) {
				db.close();
			}
		}
		return retorno;
	}
	
	public int executeSQLLogger(String usuario, String accion, String fechahora) throws SQLException {
		Connection db = null;
		Statement dataRequest = null;
		boolean resultSet = false;
		int retorno = 0;
		String query = null;
		try {
			// -----------------------------------/
			try {
				db = new ConexionP().getConection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			query ="INSERT INTO `orgdata`.`loggerapp` " +
							"(`usuario`, " +
							"`accionlog`, " +
							"`fechahora`) " +
							"VALUES ('" + usuario + "','" + accion + "','" + fechahora + "')";						
			dataRequest = db.createStatement();
			resultSet = dataRequest.execute(query);
			// -----------------------------------/
			if (resultSet) {
				retorno = 1;
			}else
				retorno = 0;
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (db != null) {
				db.close();
			}
		}
		return retorno;
	}
	
	/**
	 * function exeCorrelativo
	 * @return int CorrelativoP
	 * @throws SQLException
	 */
	public int exeCorrelativoP() throws SQLException {
		int correlativoP=0;
		int correlativoUp=0;
		Connection db = null;
		ResultSet resultSet = null;
		boolean resultUp = false;
		String query = null;
		String queryUp = null;
		Statement dataRequest = null;	
		Statement dataUpdate = null;
		query = "SELECT correlativoP FROM `orgdata`.`orgCorrelativoP`";
		try {
				db = new ConexionP().getConection();	
				dataRequest = db.createStatement();
				resultSet = dataRequest.executeQuery(query);		
				boolean records = resultSet.next();	
				/* ----------------------------  */
				correlativoP = resultSet.getInt(1);
				correlativoUp = correlativoP + 1;
				/* ----------------------------  */
				queryUp = "UPDATE `orgdata`.`orgCorrelativoP` SET `correlativoP`= "+ correlativoUp + " WHERE `idorgCorrelativoP`='1'";
				dataUpdate = db.createStatement();
				resultUp = dataUpdate.execute(queryUp);
				/* ----------------------------  */
			} catch (Exception e) {
				e.printStackTrace();
	
			} finally {
				if (db != null) {
					db.close();
			}
		}
		/* -------------- */
		return correlativoP;
		/* -------------- */
	} 
	
}
