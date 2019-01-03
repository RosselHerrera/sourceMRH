package planeacion.persistencia;

import java.io.Serializable;

public class DTOItemProyecto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idI;
	private int idItem;
	private int idP;
	private String descripcionI;
	private String proveedorI;
	private String moneda;
	private double priceI;
	private int qty;
	private String estimadoPI;
	private double Tusd;
	private double Tclp;
	
	public int getIdI() {
		return idI;
	}
	public void setIdI(int idI) {
		this.idI = idI;
	}
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public int getIdP() {
		return idP;
	}
	public void setIdP(int idP) {
		this.idP = idP;
	}
	public String getDescripcionI() {
		return descripcionI;
	}
	public void setDescripcionI(String descripcionI) {
		this.descripcionI = descripcionI;
	}
	public String getProveedorI() {
		return proveedorI;
	}
	public void setProveedorI(String proveedorI) {
		this.proveedorI = proveedorI;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public double getPriceI() {
		return priceI;
	}
	public void setPriceI(double priceI) {
		this.priceI = priceI;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getEstimadoPI() {
		return estimadoPI;
	}
	public void setEstimadoPI(String estimadoPI) {
		this.estimadoPI = estimadoPI;
	}
	public double getTusd() {
		return Tusd;
	}
	public void setTusd(double tusd) {
		Tusd = tusd;
	}
	public double getTclp() {
		return Tclp;
	}
	public void setTclp(double tclp) {
		Tclp = tclp;
	}
		
	

	
}
