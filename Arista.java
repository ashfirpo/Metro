package metro;

public class Arista implements Comparable<Arista>{
	
	private int origen;
	private int destino;
	private int costo;
	
	public Arista(int origen, int destino, int costo)
	{
		this.origen=origen;
		this.destino=destino;
		this.costo=costo;
	}
	
	public int getOrigen()
	{
		return this.origen;
	}
	
	public int getDestino()
	{
		return this.destino;
	}
	
	public int getCosto()
	{
		return this.costo;
	}

	@Override
	public int compareTo(Arista o) {
		return this.costo - o.costo;
	}

}
