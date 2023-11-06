package snakePack;

// ===============================================================
public class Settings {

	public static final int RES_X = 600;
    public static final int RES_Y = 600;
    public static final int TILE_X = 40;
    public static final int TILE_Y = 40;
    public static final int FILAS = (int) (RES_Y / TILE_Y);
    public static final int COLUMNAS = (int) (RES_X / TILE_X);

    public static final int FPS = 15;

    public static final int MAX_LONGITUD_SNAKE = 999;
    public static final int INICIAL_LONGITUD_SNAKE = 1;

    public static final int X_INI_SNAKE = (int) (COLUMNAS / 2);
    public static final int Y_INI_SNAKE = (int) (FILAS / 2);

    public static final int[] colorFondo = {80, 160, 9};
    public static final int[] colorManzana = {220, 30, 9};
    public static final int[] colorSnake = {170, 240, 9};

    // ------------------------------------------------
    public static int longitudSnake = INICIAL_LONGITUD_SNAKE;

    public static int marcador = 0;
    public static int hiScore = 27;

    public static int contador_optionPane = 9;

    // ================================================
    public class Controles {

    	public static Boolean izquierda = false;
	    public static Boolean derecha = false;
	    public static Boolean arriba = false;
	    public static Boolean abajo = false;
    }

    // ================================================
    public class Estado {

    	public static Boolean preJuego = true;
    	public static Boolean enJuego = false;
    	public static Boolean gameOver = false;
    }
}
