package ProgramacionIII.tp2;

public class MainTP2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {200,100,20,12,9,8,7,6};
		
		imprimir(arr);
		burbujeo(arr);
		imprimir(arr);
		
	}
	
	public static void burbujeo(int[] arr) {
		int i = 0;
		int pos = 0;
		int nav = 0;
		int aux = 0;
		
		while(i < arr.length) {
			pos = 0;
			
			while(pos < arr.length - i) {
				nav = pos + 1;
				
				if((nav < arr.length-i) && (arr[pos] > arr[nav])) {
					aux = arr[pos];
					arr[pos] = arr[nav];
					arr[nav] = aux;
				}
				pos++;
			}
			i++;
		}
	}

	public static void imprimir(int[] arr) {
		int i = 0;
		
		while(i < arr.length) {
			System.out.print(arr[i]);
			System.out.print(", ");
			i++;
		}
		System.out.println();
	}
}
