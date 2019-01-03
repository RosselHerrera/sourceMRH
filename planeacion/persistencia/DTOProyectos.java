package planeacion.persistencia;

import java.io.Serializable;

public class DTOProyectos implements Serializable {
	
	private String idProyecto; 
	private String nombreProyecto;
	private String categoria;
	private String categoriaLiberty;
	private String objetivoEstrategico;
	private String prioridad;
	private double totalCLP;
	
	public String getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(String idProyecto) {
		this.idProyecto = idProyecto;
	}
	public String getNombreProyecto() {
		return nombreProyecto;
	}
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getCategoriaLiberty() {
		return categoriaLiberty;
	}
	public void setCategoriaLiberty(String categoriaLiberty) {
		this.categoriaLiberty = categoriaLiberty;
	}
	public String getObjetivoEstrategico() {
		return objetivoEstrategico;
	}
	public void setObjetivoEstrategico(String objetivoEstrategico) {
		this.objetivoEstrategico = objetivoEstrategico;
	}
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public double getTotalCLP() {
		return totalCLP;
	}
	public void setTotalCLP(double totalCLP) {
		this.totalCLP = totalCLP;
	}
	
}
