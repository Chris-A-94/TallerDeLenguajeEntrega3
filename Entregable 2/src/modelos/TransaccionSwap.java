package modelos;

public class TransaccionSwap extends Transaccion {
	
	String siglaFrom, siglaTo;
	Double cantFrom, cantTo;
	
	public TransaccionSwap(String dia, String mes, String year, String user_id, String siglaFrom, Double cantFrom, String siglaTo, Double cantTo) {
		super(dia, mes, year, user_id);
		this.setTipo(TipoTransaccion.SWAP);
		this.siglaTo = siglaTo;
		this.siglaFrom = siglaFrom;
		this.cantTo = cantTo;
		this.cantFrom = cantFrom;
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String toString() {
		return	cantFrom + ";" + siglaFrom + ";" + cantTo + ";" + siglaTo;
	
	}
	public String description() {
		return (this.getFecha().toString() + ": Saldo final en " + this.siglaTo.toUpperCase()+" "+this.cantTo+ ". Saldo final en "+ this.siglaFrom.toUpperCase()+" "+this.cantFrom);
	}
}
