package snakePack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

// ===============================================================
public class Ventana extends JPanel implements ActionListener {

    private SegmentoSnake[] segmento_snake = new SegmentoSnake[Settings.MAX_LONGITUD_SNAKE];
    private Manzana manzana;

    private Timer timer;

    // --------------------------------------------------
    public Ventana() {
        
        inicializa();
    }
    
    private void inicializa() {

        addKeyListener(new TAdapter());

        int[] rgb = Settings.colorFondo;
        setBackground(new Color(rgb[0], rgb[1], rgb[2]));
        setFocusable(true);
        setPreferredSize(new Dimension(Settings.RES_X, Settings.RES_Y));

        comenzar();
    }

    private void comenzar() {

        segmento_snake[0] = new SegmentoSnake(Settings.X_INI_SNAKE, Settings.Y_INI_SNAKE, Settings.TILE_X, Settings.TILE_Y);

        manzana = new Manzana(Settings.COLUMNAS, Settings.FILAS, Settings.TILE_X, Settings.TILE_Y);

        timer = new Timer((int) (1000 / Settings.FPS), this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        renderiza(g);
    }
    
    private void renderiza(Graphics g) {
        
        if (Settings.Estado.enJuego) {

            for (int i = 0; i < Settings.FILAS; i ++) {
                for (int ii = 0; ii < Settings.COLUMNAS; ii ++) {

                    g.setColor(Color.white);
                    g.drawRect(ii * Settings.TILE_X, i * Settings.TILE_Y, Settings.TILE_X, Settings.TILE_Y);
                }
            }

            manzana.dibuja(g, Settings.colorManzana);

            for (int i = 0; i < Settings.longitudSnake; i++) {
                segmento_snake[i].dibuja(g, Settings.colorSnake);
            }

            //textos((int) (resX / 20), "Score: " + marcador, 3, g);

            Toolkit.getDefaultToolkit().sync();

        } else {

            //gameOver(g);
        }        
    }

    // private void gameOver(Graphics g) {

    //     textos((int) (resX / 10), "Game Over", 1, g);
    //     textos((int) (resX / 30), "Pulse SPACE para jugar otra partida...", 2, g);
    // }

    // private void textos(int size, String texto, int idTxt, Graphics g) {

    //     int fontSize = size;
    //     String msg = texto;

    //     Font fuente = new Font("Helvetica", Font.BOLD, fontSize);
    //     FontMetrics metr = getFontMetrics(fuente);

    //     g.setFont(fuente);

    //     if (idTxt == 1) {
    //         g.setColor(Color.orange);
    //         g.drawString(msg, (resX - metr.stringWidth(msg)) / 2, resY / 2);

    //     } else if (idTxt == 2) {
    //         g.setColor(Color.white);
    //         g.drawString(msg, (resX - metr.stringWidth(msg)) / 2, (int) (resY / 1.2));

    //     } else if (idTxt == 3) {
    //         g.setColor(Color.yellow);
    //         g.drawString(msg, (resX - metr.stringWidth(msg)) / 2, 30);
    //     }
    // }

    private void check_colisionManzana() {

        if (segmento_snake[0].x == manzana.getX() && segmento_snake[0].y == manzana.getY()) {

            Settings.longitudSnake ++;
            Settings.marcador ++;
            Toolkit.getDefaultToolkit().beep();

            manzana = new Manzana(Settings.COLUMNAS, Settings.FILAS, Settings.TILE_X, Settings.TILE_Y);
            segmento_snake[Settings.longitudSnake - 1] = new SegmentoSnake(Settings.X_INI_SNAKE, Settings.Y_INI_SNAKE, Settings.TILE_X, Settings.TILE_Y);
        }
    }

    private void update_snake() {

        for (int i = Settings.longitudSnake - 1; i > 0; i--) {
            segmento_snake[i].x = segmento_snake[(i - 1)].x;
            segmento_snake[i].y = segmento_snake[(i - 1)].y;
        }

        // -------------------------------------
        if (Settings.Controles.izquierda) {
            segmento_snake[0].x --;
        }

        if (Settings.Controles.derecha) {
            segmento_snake[0].x ++;
        }

        if (Settings.Controles.arriba) {
            segmento_snake[0].y --;
        }

        if (Settings.Controles.abajo) {
            segmento_snake[0].y ++;
        }
    }

    private Boolean check_autoColision() {

        for (int i = Settings.longitudSnake - 1; i > 0; i --) {

            SegmentoSnake segmentoCola = segmento_snake[i];
            SegmentoSnake cabeza = segmento_snake[0];

            if (cabeza.x == segmentoCola.x && cabeza.y == segmentoCola.y) {
                return true;
            }
        }

        return false;
    }

    public Boolean check_limites() {

        SegmentoSnake cabeza = segmento_snake[0];

        if (cabeza.y >= Settings.FILAS || cabeza.y < 0 || cabeza.x >= Settings.COLUMNAS || cabeza.x < 0) {
            return true;
        }

        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (Settings.Estado.enJuego) {

            check_colisionManzana();
            update_snake();

            if (check_autoColision() || check_limites()) {

                Settings.Estado.enJuego = false;
                Toolkit.getDefaultToolkit().beep();
                System.exit(0);
            }
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (Settings.Estado.enJuego) {

                if ((key == KeyEvent.VK_LEFT) && (!Settings.Controles.derecha)) {
                    Settings.Controles.izquierda = true;
                    Settings.Controles.arriba = false;
                    Settings.Controles.abajo = false;
                }

                if ((key == KeyEvent.VK_RIGHT) && (!Settings.Controles.izquierda)) {
                    Settings.Controles.derecha = true;
                    Settings.Controles.arriba = false;
                    Settings.Controles.abajo = false;
                }

                if ((key == KeyEvent.VK_UP) && (!Settings.Controles.abajo)) {
                    Settings.Controles.arriba = true;
                    Settings.Controles.derecha = false;
                    Settings.Controles.izquierda = false;
                }

                if ((key == KeyEvent.VK_DOWN) && (!Settings.Controles.arriba)) {
                    Settings.Controles.abajo = true;
                    Settings.Controles.derecha = false;
                    Settings.Controles.izquierda = false;
                }
            }

            if (key == KeyEvent.VK_ESCAPE) {
                Toolkit.getDefaultToolkit().beep();
                System.exit(0);
            }
        }
    }
}
