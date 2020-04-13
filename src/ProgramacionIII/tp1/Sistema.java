package ProgramacionIII.tp1;
import ProgramacionIII.util.*;

public class Sistema {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Timer reloj = new Timer();
		
		Pila pila = new Pila();
		
		
		int maxListado = 200000;
		
		reloj.start();
		for(int i = 0; i < maxListado; i++) {
			pila.push(i+1);
		}
		System.out.println(reloj.stop());
		
		reloj.start();
		System.out.println(pila.top());
		System.out.println(reloj.stop());
		
		reloj.start();
		System.out.println(pila.pop());
		System.out.println(reloj.stop());
		
		reloj.start();
		System.out.println(pila.top());
		System.out.println(reloj.stop());
		
	}

}
