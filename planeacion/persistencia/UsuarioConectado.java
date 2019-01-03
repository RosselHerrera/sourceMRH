package planeacion.persistencia;

import java.io.Serializable;

public class UsuarioConectado implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String gerencia;
	private String subgerencia;
	private String idsession;
	
	public String getGerencia() {
		return gerencia;
	}
	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}
	public String getSubgerencia() {
		return subgerencia;
	}
	public void setSubgerencia(String subgerencia) {
		this.subgerencia = subgerencia;
	}
	public String getIdsession() {
		return idsession;
	}
	public void setIdsession(String idsession) {
		this.idsession = idsession;
	}
	
	

}
