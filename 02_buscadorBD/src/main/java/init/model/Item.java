package init.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="items")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column (name="idItem") Esto no vale. Para que no de error "unknown colum id_item", para ello se añaden 2 propiedades en el application.properties.
	/*spring.jpa.hibernate.naming.implicit-strategy=org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl
			spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl*/
	private int idItem; 
	
	
	private String url; 
	private String tematica; 
	private String descripcion;
	
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Item(int idItem, String url, String tematica, String descripcion) {
		super();
		this.idItem = idItem;
		this.url = url;
		this.tematica = tematica;
		this.descripcion = descripcion;
	}
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTematica() {
		return tematica;
	}
	public void setTematica(String tematica) {
		this.tematica = tematica;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
