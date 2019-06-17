/**
 * Aplicacion principal del juego Arkanoid.
 * @author Miguel García
 * @version 1.0
 *
 */
public class Arkanoid {
	
	
	public static void main(String[] args){
		int i, n, x;
		String cadena;
		String cadena2 = "hola";
		int bloquesDestructibles = 0; //Contador de los bloques que aun quedan por destruir
		Bloque[] bloques = new Bloque[35];
		Bola bola;	//Creo una bola de tipo Bola
		Pala pala;

		
		//Defino los vectores que representan los muros del juego
		Vector v1 = new Vector(0.0, 0.5);
		Vector v2 = new Vector(1.0, 0.5);
		Vector v3 = new Vector(0.5, 1.0);
		Vector v5 = new Vector(0.5, 0.015); //Vector de la posicion inicial de la Pala.
		
		Vector d1 = new Vector(0.002, 0.5);
		Vector d2 = new Vector(0.5, 0.002);
		Vector d3 = new Vector(0.06, 0.017); //Vector de las dimensiones de la pala.
		
		//Defino los vectores posicion y velocidad inicial de Bola
		Vector pInicial = new Vector(0.5, 0.1);
		Vector vInicial = new Vector(0.04, 0.02);
		
		bola = new Bola(0.01, pInicial, vInicial);
		pala = new Pala(v5, d3);
		
		
		bloques[0] = new Muro(v1,d1);
		bloques[1] = new Muro(v2,d1);
		bloques[2] = new Muro(v3,d2);
		
		//Creo los Ladrillos
		x = 3;
		for(i=0; i < 8; i++){ //Columnas
			for(n=0; n < 4; n++){ //Filas
				bloques[x] = new Ladrillo(new Vector(((0.125) * i) + 0.0625, (0.1*n) + 0.5), new Vector(0.0625, 0.05), (int) (1 + Math.random() * 3));
				x++;
			}
		}

		
		//Contador de Bloques con forma de ladrillo.
		for(i=0; i<bloques.length; i++){
			if(bloques[i] instanceof Ladrillo){
				bloquesDestructibles++;
			}
		}

		StdDraw.setCanvasSize(600, 600);
		//Bucle principal del juego
		while(i > -1){
			
			//Contruyo todos los bloques sean del tipo que sea
			for(i=0; i < bloques.length; i++){
				bloques[i].dibujar();
			}
			
			//Recorro el array de bloques en busca de los ladrillos para comprobar si la bola impacta con ellos.
			for(i=0; i < bloques.length; i++){
				if(bloques[i] instanceof Ladrillo){
					if(bloques[i].ImpactosRestantes > 0){
						if(bloques[i].calcularImpacto(bola) != 999){
							if(bloques[i].ImpactosRestantes > 0 && bloques[i].ImpactosRestantes <= 3){
								bola.cambiarTrayectoria(bloques[i].calcularImpacto(bola));
								bloques[i].getNumImpactosRestantes();
							}
							
						}
					}
					else if(bloquesDestructibles > 0 && bloques[i].ImpactosRestantes == 0){
						bloquesDestructibles = bloquesDestructibles - 1;
						
						bloques[i].getNumImpactosRestantes();	//Me resta en una unidad los impactos restantes
					}
				}
				
			}
			
			if(pala.calcularImpacto(bola) != 999){
				bola.cambiarTrayectoria(pala.calcularImpacto(bola));
			}
			
			//Recorro el array de bloques para comprobar si la bola ha chocado con un muro, y si es así, esta cambiará su trayectoria.
			for(i=0; i < bloques.length; i++){
				if(bloques[i] instanceof Muro){
					if(bloques[i].calcularImpacto(bola) != 999){
						System.out.println(bloques[i].calcularImpacto(bola));
						bola.cambiarTrayectoria(bloques[i].calcularImpacto(bola));
					}
				}
			}
			
				StdDraw.setPenRadius(.02); 
				StdDraw.setPenColor(StdDraw.WHITE); 
				StdDraw.filledRectangle(0.8, 0.9, 0.2, 0.02);
				
				if(bloquesDestructibles == 0){
					//Marcador de bloques por derribar
					cadena = "Quedan por derribar... " + bloquesDestructibles;
					StdDraw.setPenRadius(.02); 
					StdDraw.setPenColor(StdDraw.BLACK); 
					StdDraw.text(0.8, 0.9, cadena);
					
					//Mensaje de victoria y fin del juego
					cadena2 = "¡¡¡HAS GANADO!!!";
					StdDraw.setPenRadius(.02); 
					StdDraw.setPenColor(StdDraw.BLACK); 
					StdDraw.text(0.5, 0.2, cadena2);
				}
						
				bola.mover(0.1);
				bola.dibujar();
				StdDraw.show(7);
				
				pala.mover(new Vector(0.0, 0.0));
				pala.dibujar();
				
				if (StdDraw.hasNextKeyTyped()){
					switch(StdDraw.nextKeyTyped()){
						case ',': // Izquierda
							if ((pala.getPosicion().cartesian(0) - pala.getDimensiones().cartesian(0)) > (bloques[0].getDimensiones().cartesian(0)*2)){
								pala.mover(new Vector(-0.02, 0.0));
								System.out.println("Se ha metido");	//Prueba
								System.out.println(pala.getPosicion().cartesian(0) - pala.getDimensiones().cartesian(0));	//Prueba
							}
							break;
						case '.': // Derecha
							if ((pala.getPosicion().cartesian(0) + pala.getDimensiones().cartesian(0)) < (1-2*bloques[1].getDimensiones().cartesian(0)))
								pala.mover(new Vector(0.02, 0.0));
								System.out.println("Se ha metido");
							break;
						}
						pala.dibujar();
						System.out.println(pala.getPosicion().cartesian(0) - pala.getDimensiones().cartesian(0)); //Prueba
				}
				
				if(cadena2.equals("¡¡¡HAS GANADO!!!") == false){
					if(bola.getPosicion().cartesian(1) < 0.015){
						StdDraw.setPenRadius(.02); 
						StdDraw.setPenColor(StdDraw.WHITE); 
						StdDraw.filledRectangle(0.5, 0.5, 0.2, 0.02);
						cadena2 = "¡¡¡HAS PERDIDO!!!";
						StdDraw.setPenRadius(.02); 
						StdDraw.setPenColor(StdDraw.BLACK); 
						StdDraw.text(0.5, 0.2, cadena2);
					}
				}
			}
				
		}
				
	}



