package persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ProducaoTableViewItem {
	private conexaoDAO c;
    private float quantidade;
    private int tipo;
    private Date dia;
    private int ID;
	private String BUS = "SELECT * FROM \"producao\" WHERE \"id_pd\"=?";

    
    public ProducaoTableViewItem(){
	 c = new conexaoDAO("jdbc:postgresql://localhost:5432/BDAGENDA","postgres","123");

    }
    
	public float getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public Date getDia() {
		return dia;
	}
	public void setDia(Date dia) {
		this.dia = dia;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}

	public ProducaoTableViewItem buscar(int ID) {
	    ProducaoTableViewItem item = null;

	    try {
	        c.conectar();
	        PreparedStatement inst = c.getConexao().prepareStatement(BUS);
	        inst.setInt(1, ID);
	        ResultSet rs = inst.executeQuery();

	        if (rs.next()) {
	            item = new ProducaoTableViewItem();
	            item.setQuantidade(rs.getFloat("quantidade"));
	            item.setTipo(rs.getInt("tipo"));
	            item.setDia(rs.getDate("dia"));
	            item.setID(rs.getInt("ID"));
	        }

	        c.desconectar();
	    } catch (Exception e) {
	        System.out.println("Erro na busca" + e);
	    }

	    return item;
	}
}
