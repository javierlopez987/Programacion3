package ProgramacionIII.tp4Entregable;

public class ComparadorMiembros extends ComparadorFamilia {

	public ComparadorMiembros() {
		super();
	}

	@Override
	public int compare(Familia f1, Familia f2) {
		// TODO Auto-generated method stub
		return f1.miembros() - f2.miembros();
	}
	
	
}
