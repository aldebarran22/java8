package es.curso.ficheros.principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import es.curso.ficheros.beans.Pedido;
import es.curso.ficheros.dao.IOperaciones;
import es.curso.ficheros.dao.PedidoException;
import es.curso.ficheros.dao.PedidoFile;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//pruebaLeerFichero();
		//pruebaLeerScanner();
		//pruebaPedidoFile();
		//pruebaEscribirFichero();
		//pruebaPaises();
		
		pruebaScanner();
		
	}

	private static void pruebaScanner() {
		// Prueba de teclado con Scanner:
		Scanner scan;
		String cad;
		
		// Lectura de teclado:
		scan = new Scanner(System.in);
		System.out.println("Leer una cadena:");
		cad = scan.nextLine();
		System.out.println("cad: "+cad);
		scan.close();	
		
		// Procesamiento de cadenas: separa en palabras:
		scan = new Scanner("hola que tal estas");
		while (scan.hasNext()) {
			System.out.println(scan.next());			
		}
		scan.close();
	}

	private static void pruebaPaises() {
		// TODO Auto-generated method stub				
		PedidoFile.exportarPaises("pedidos.csv");
	}

	private static void pruebaEscribirFichero() {
		// Grabar en un fichero. Modo write / append
		FileOutputStream fichero = null;
		String[] meses = new String[] {"ene","feb","mar","abr","may"};
		
		try {
			fichero = new FileOutputStream("ficheros/salida.txt", true);
			for (String mes : meses) {
				
				// Grabar en bytes:
				fichero.write(mes.getBytes());
				fichero.write("\n".getBytes());
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			
		} finally {
			if (fichero != null) {
				try {
					fichero.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
	}

	private static void pruebaPedidoFile() {
		IOperaciones operaciones;
		
		
		try {
			operaciones = new PedidoFile("pedidos.csv");
			List<Pedido> pedidos = operaciones.select();
			
			for (Pedido p : pedidos) {
				System.out.println(p);
			}
			System.out.println("Pedidos: "+pedidos.size());
			
		} catch (PedidoException e) {
			System.err.println(e.getMessage());
		}
		
		
	}

	private static void pruebaLeerScanner() {
		Scanner scanner = null;
		String linea;
		
		try {
			scanner = new Scanner(new File("pedidos.csv"));
			while (scanner.hasNextLine()) {
				linea = scanner.nextLine();
				System.out.println(linea);
			}
			
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			
		} finally {
			if (scanner != null) scanner.close();
		}
		
	}

	private static void pruebaLeerFichero() {
		// Leer un fichero por bytes.
		
		FileInputStream fichero = null;
		String linea;
		byte[] bytes;
		
		try {
			fichero = new FileInputStream("pedidos.csv");
			
			// Ojo estamos leyendo en bytes. No es la mejor forma para leer un fichero
			// organizado en filas. Podemos perder la estructura de las filas del fichero.
			while (fichero.available() > 0) {
				bytes = new byte[100];
				fichero.read(bytes);
				
				linea = new String(bytes, 0, bytes.length);
				System.out.println(linea);
				System.out.println();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			if (fichero != null)
				try {
					fichero.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
	}

}
