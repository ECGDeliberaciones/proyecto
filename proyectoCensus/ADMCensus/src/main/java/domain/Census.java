package domain;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@XmlRootElement(name="census")
@Access(AccessType.PROPERTY)
public class Census extends DomainEntity{
	
	private String token_propietario;//Token enviado por votación para saber creador del censo
	private int votacion_id;//Id que identifica de forma únia a la votacion
	private HashMap<String, Boolean> voto_por_usuario = new HashMap<String, Boolean>();//Mapa encargado de asignar un true o false (ha votado o no) a un token unico de un usuario
	private Date fecha_inicio;
	private Date fecha_fin;
	
	
	public Census(){
		
	}

    @MapKeyColumn(name="token")
    @Column(name="valor")
    @CollectionTable(name="value", joinColumns=@JoinColumn(name="token"))
	public HashMap<String, Boolean> getVoto_por_usuario() {
		return voto_por_usuario;
	}

	public void setVoto_por_usuario(HashMap<String, Boolean> voto_por_usuario) {
		this.voto_por_usuario = voto_por_usuario;
	}

	@NotBlank
	public String getToken_propietario() {
		return token_propietario;
	}

	public void setToken_propietario(String token_propietario) {
		this.token_propietario = token_propietario;
	}

	@Column(unique=true)
	public int getVotacion_id() {
		return votacion_id;
	}

	public void setVotacion_id(int votacion_id) {
		this.votacion_id = votacion_id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@NotNull
	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@NotNull
	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	

}


