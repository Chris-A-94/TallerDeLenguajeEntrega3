package daos;

import java.sql.Connection;
import java.util.List;

public interface DaoInterface<T> {
	public void crearTabla();
	public boolean modificar(T o);
	public boolean guardar(T o);
	public List<T> devolverTabla();
	public void remover(String s);
	
	
}
