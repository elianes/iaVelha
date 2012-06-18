package servidor.iaVelha;

public class valorRetorno {
	private Node estado;
	private Node father;
	private Node Mjogada;
	private int heuristica;
	public valorRetorno(){
		this.estado=null;
		this.father=null;
		this.heuristica=0;
	}
	public int getHeristica() {
		
		return this.heuristica;
	}
	public Node getFather(){
		return this.father;
	}
	public Node getEstado(){
		return this.estado;
	}
	
	public void setHeuristica(int i) {
		this.heuristica=i;
		
	}
	public Node getMjogada(){
		return this.Mjogada;
	}
	
	public void setHeuristica(Node e) {
		this.Mjogada=e;
		
	}
	public void setEstado(Node filho) {
		this.estado=filho;
		
	}
	public void setFather(Node pai) {
		this.father=pai;
		
	}
}
