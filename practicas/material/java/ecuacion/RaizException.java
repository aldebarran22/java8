package modelo;


	 public class RaizException extends Exception {
	 
	 	private String msg;
		 
		
		public RaizException(){
			msg = "No hay soluci�n, ra�z no v�lida";
		}
		
		
		public String getMessage(){ return msg; }
	} 