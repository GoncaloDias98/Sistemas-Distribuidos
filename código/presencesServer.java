import java.net.*;
import java.io.*;

public class presencesServer {
	static int DEFAULT_PORT=2000;
	
	public static void main(String[] args) {
		int port=DEFAULT_PORT;
		Presences presences = new Presences();
			
		ServerSocket servidor = null; 
	
// Create a server socket, bound to the specified port: API java.net.ServerSocket	
	
		try	{ 
				servidor = new ServerSocket(port);
			} catch (Exception e) { 
				System.err.println("erro ao criar socket servidor...");
				e.printStackTrace();
				System.exit(-1);
			}
	
		System.out.println("Servidor a' espera de ligacoes no porto " + port);
		
		while(true) {
			try {

// Listen for a connection to be made to the socket and accepts it: API java.net.ServerSocket				
				
				Socket ligacao = servidor.accept();
				
// Start a GetPresencesRequestHandler thread				
				
				GetPresencesRequestHandler t = new GetPresencesRequestHandler(ligacao, presences);
				t.start();
				
			} catch (IOException e) {
				System.out.println("Erro na execucao do servidor: "+e);
				System.exit(1);
			}
		}
	}
}
