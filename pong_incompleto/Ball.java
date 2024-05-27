import java.awt.*;
// java.util.Random
/**
	Esta classe representa a bola usada no jogo. A classe princial do jogo (Pong)
	instancia um objeto deste tipo quando a execução é iniciada.

	O luis falou que não precisa reiniciar a bolinha toda vez
	a bolinha continua, não reinicia
*/

public class Ball {

	private double cx;
	private double cy;
	private double width;
	private double height;
	private Color color;
	private double speed;
	private double auxX;
	private double auxY;
	private final double CX_INICIAL;
	private final double CY_INICIAL;

	/**
		Construtor da classe Ball. Observe que quem invoca o construtor desta classe define a velocidade da bola 
		(em pixels por millisegundo), mas não define a direção deste movimento. A direção do movimento é determinada 
		aleatóriamente pelo construtor.

		@param cx coordenada x da posição inicial da bola (centro do retangulo que a representa).
		@param cy coordenada y da posição inicial da bola (centro do retangulo que a representa).
		@param width largura do retangulo que representa a bola.
		@param height altura do retangulo que representa a bola.
		@param color cor da bola.
		@param speed velocidade da bola (em pixels por millisegundo).
	*/

	/*
	
		bater na parede: velocidade vertical
		no player  inverte os dois 
	 
	 */

	public Ball(double cx, double cy, double width, double height, Color color, double speed){
		this.cx = cx;
		this.cy = cy;
		this.width = width;
		this.height = height;
		this.color = color;
		this.speed = speed;
		this.auxX = 1;
		this.auxY = 1;
		this.CX_INICIAL = cx;
		this.CY_INICIAL = cy;
	}


	/**
		Método chamado sempre que a bola precisa ser (re)desenhada.
	*/

	public void draw(){
		GameLib.setColor(this.color);
		GameLib.fillRect(this.cx, this.cy, this.width, this.height);
	}

	/**
		Método chamado quando o estado (posição) da bola precisa ser atualizado.
		
		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/

	public void update(long delta){
		this.cx += this.speed*this.auxX*delta;
		this.cy += this.speed*this.auxY*delta;
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com um jogador.
	
		@param playerId uma string cujo conteúdo identifica um dos jogadores.
	*/

	public void onPlayerCollision(String playerId){
			this.auxX *= -1;
		}

	/**
		Método chamado quando detecta-se uma colisão da bola com uma parede.

		@param wallId uma string cujo conteúdo identifica uma das paredes da quadra.
	*/

	public void onWallCollision(String wallId){
		if(wallId.equals("Top") || wallId.equals("Bottom")){
			this.auxY *= -1;
		}

		 if(wallId.equals("Right") ||wallId.equals("Left")){
			this.cx = CX_INICIAL;
			this.cy = CY_INICIAL;
		}
	}

	/**
		Método que verifica se houve colisão da bola com uma parede.

		@param wall referência para uma instância de Wall contra a qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/
	
	public boolean checkCollision(Wall wall){
		String posWall = wall.getId();

		if(posWall.equals("Bottom")){
			if(this.cy + (this.height / 2) > wall.getCy() - (wall.getHeight() / 2)){
				return true;
			}
		}

		else if(posWall.equals("Top")){
			if(this.cy - (this.height / 2) < wall.getCy() + (wall.getHeight() / 2)){
				return true;
			}
		}

		if(posWall.equals("Right")){
			if(this.cx + (this.width / 2) > wall.getCx() + (wall.getWidth() / 2)){
				return true;
			}
		}
			
		if(posWall.equals("Left")){
			if(this.cx - (this.width / 2) < wall.getCx() + (wall.getWidth() / 2)){
				return true;
			}
		}

		return false;
	}


	/**
		Método que verifica se houve colisão da bola com um jogador.

		@param player referência para uma instância de Player contra o qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/	

	public boolean checkCollision(Player player){
		if(this.cy + (this.height / 2) >= player.getCy() - (player.getHeight() / 2) && this.cy - (this.height / 2) <= player.getCy() + (player.getHeight() / 2)){
			if(this.cx - (this.width / 2) < player.getCx() + (player.getWidth() / 2) && this.cx + (this.width / 2) > player.getCx() - (player.getWidth() / 2))
			return true;
		}

		return false;
	}

	/**
		Método que devolve a coordenada x do centro do retângulo que representa a bola.
		@return o valor double da coordenada x.
	*/
	
	public double getCx(){
		
		return this.cx;
	}

	/**
		Método que devolve a coordenada y do centro do retângulo que representa a bola.
		@return o valor double da coordenada y.
	*/

	public double getCy(){
		
		return this.cy;
	}

	/**
		Método que devolve a velocidade da bola.
		@return o valor double da velocidade.

	*/

	public double getSpeed(){
		
		return this.speed;
	}
}
