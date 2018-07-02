package metro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Metro {
	
	private int cantIslas; //Nodos
	private int cantTuneles; //Aristas
	private int cantPuentes; //Aristas fake
	int[][] matriz;
	
	public Metro(String path) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File(path));
		this.cantIslas = sc.nextInt();
		this.cantTuneles = sc.nextInt();
		this.cantPuentes = sc.nextInt();
		//sc.nextLine();
		this.matriz = new int[this.cantIslas][this.cantIslas];
		for(int i=0;i<this.cantIslas;i++)
			Arrays.fill(this.matriz[i], -1);
		if(sc.hasNextLine())
		{
			sc.nextLine();
			for(int i=0;i<this.cantTuneles;i++)
			{
				int origen = sc.nextInt() -1;
				int destino = sc.nextInt() -1;
				this.matriz[origen][destino] = this.matriz[destino][origen] = 0;
			}

			for(int i=0;i<this.cantPuentes;i++)
			{
				int origen = sc.nextInt() -1;
				int destino = sc.nextInt() -1;
				this.matriz[origen][destino] = this.matriz[destino][origen] = 1;
			}
		}
		sc.close();
	}
	
	public void resolver()
	{
		//Prim me va a devolver el costo m�nimo de generar el �rbol. Como tengo t�neles y puentes, y los puentes tienen m�s peso que los t�neles,
		//lo que me devuelve Prim es la cantidad m�nima de puentes que tiene que usar
		int res = this.cantPuentes==0 && this.cantTuneles ==0? (this.cantIslas-1):prim(); //Ver esto
		System.out.println("Cantidad m�nima de puentes: " + res);
	}
	
	public LinkedList<Arista> getAristas(int nodo)
	{
		if(nodo >= this.cantIslas)
			try 
			{
				throw new Exception("Nodo inv�lido");
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		LinkedList<Arista> aristas = new LinkedList<>();
		for(int i=0;i<this.cantIslas;i++)
		{
			if(this.matriz[nodo][i] == 0 || this.matriz[nodo][i] == 1)
				aristas.add(new Arista(nodo, i, this.matriz[nodo][i]));
		}
		return aristas;
	}
	
	public int prim()
	{
		LinkedList<Arista> aristas = new LinkedList<>();
		Queue<Arista> cola = new PriorityQueue<Arista>();
		LinkedList<Integer> visitados = new LinkedList<Integer>();
		int costoTotal=0;
		
		visitados.add(0); //Empiezo por el primero
		cola.addAll(getAristas(0)); //Busco las aristas de ese nodo y las agrego a la pq
		
		while(!cola.isEmpty())
		{
			Arista a = cola.poll();
			
			if(!visitados.contains(a.getDestino()))
			{
				aristas.add(a); //Voy agregando las aristas con la que estoy armando el �rbol
				visitados.add(a.getDestino());
				costoTotal += a.getCosto();
				cola.addAll(getAristas(a.getDestino()));
			}
		}
		return costoTotal;
	}

}
