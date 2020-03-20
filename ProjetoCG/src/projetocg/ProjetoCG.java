package projetocg;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.util.logging.*;
import javax.imageio.*;
import javax.swing.*;

public class ProjetoCG extends JPanel {

    //variaveis globais
    private int numeroDeQuadro;
    private long tempoPassado;
    private float tamanhoPixel;
    
    int x = 0;
    int y = 0;
    int y2 = 0;
    int x2 = 0;
    int x3 = 0, x4 = 0;

    private void mover() {//valores para fazer translate
        x++;
        y = y + 0;
        y2++;
        x2--;
        if(y2 == 15){
            y2 = 0;
        }
        if(x2 == -15){
            x2 = 0;
        }
        
        x3--;
        if(x3 == -15){
            x3 = 0;
        }
        x4++;
        if(x4 == 15){
            x4 = 0;
        }
        
    }

   
    public static void main(String[] args) {
        JFrame janela = new JFrame("Projeto Java - Flavio & Silvino");
        final ProjetoCG panel = new ProjetoCG();
        janela.setContentPane(panel);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.pack();

        Dimension ecra = Toolkit.getDefaultToolkit().getScreenSize();//tamanho da janela

        janela.setLocation((ecra.width - janela.getWidth()) / 2,
                (ecra.height - janela.getHeight()) / 2 - 30);//centrar janela

        janela.setVisible(true);
        //panel.requestFocusInWindow();
        janela.setResizable(true);

        //animar os objetos
        final long tempoInicio = System.currentTimeMillis();
        Timer tempoAnimacao = new Timer(16,
                new ActionListener() {
            public void actionPerformed(ActionEvent arg) {
                panel.numeroDeQuadro++;
                panel.tempoPassado = System.currentTimeMillis() - tempoInicio;

                panel.repaint(); //redesenhar os objetos na janela
            }
        });

        tempoAnimacao.start();
        
        while (true) {
			panel.mover();
			panel.repaint();
            try {
                Thread.sleep(100);//tempo de espera para atualizar o frame
            } catch (InterruptedException ex) {
                Logger.getLogger(ProjetoCG.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

    }

    // construtor
    public ProjetoCG() {
        setPreferredSize(new Dimension(1300, 650));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        Graphics2D g3 = (Graphics2D) g.create();
        Graphics2D g4 = (Graphics2D) g.create();
        Graphics2D g5 = (Graphics2D) g.create();
        Graphics2D g6 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//ativando o anti-aliasing
        g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g4.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g5.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g6.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setPaint(new GradientPaint(0, 0, new Color(50, 190, 240), getWidth(), 300, new Color(90, 245, 255))); //céu
        g2.fillRect(0, 0, getWidth(), 300);

        g2.setStroke(new BasicStroke(3));

        
        Rectangle2D.Double r = new Rectangle2D.Double(0, 280, getWidth(), getHeight()); //terra
        g2.setPaint(new Color(112, 162, 65));
        g2.fill(r);
        g2.draw(r);

        Rectangle2D.Double rec = new Rectangle2D.Double(0, 398, getWidth(), 210); //estrada
        g2.setPaint(Color.DARK_GRAY);
        g2.fill(rec);
        g2.draw(rec);

        Rectangle2D.Double b = new Rectangle2D.Double(0, 350, getWidth(), 45); //passeio
        g2.setPaint(Color.lightGray);
        g2.fill(b);
        g2.draw(b);

        Rectangle2D.Double b2 = new Rectangle2D.Double(0, 610, getWidth(), 45); //passeio
        g2.setPaint(Color.lightGray);
        g2.fill(b2);
        g2.draw(b2);

        g2.setPaint(Color.WHITE); //eixo da via
        int esp = 0;
        for (int i = 0; i < 20; i++) {
            g2.fill(new Rectangle2D.Double(0 + esp, 500, 100, 10));
            g2.draw(new Rectangle2D.Double(0 + esp, 500, 100, 10));
            esp += 120;
        }

        g2.fill(new Rectangle2D.Double(0, 410, getWidth(), 2)); //Guia
        g2.fill(new Rectangle2D.Double(0, 590, getWidth(), 2));

        g2.setPaint(new GradientPaint(1150, 30, Color.YELLOW, 1100, 100, new Color(251, 171, 24)));   //sol
        g2.fillOval(1150, 30, 100, 100);

        g2.setColor(Color.WHITE);   //nuvem
        g2.fillOval(130, 40, 120, 100);
        g2.fillOval(180, 10, 120, 100);
        g2.fillOval(210, 50, 140, 120);

        g2.fillOval(500, 70, 30, 10);
        g2.fillOval(520, 70, 30, 10);
        g2.fillOval(515, 75, 30, 10);

        g2.fillOval(400, 150, 30, 10);
        g2.fillOval(420, 150, 30, 10);
        g2.fillOval(415, 150, 30, 10);

        

        g2.setPaint(new Color(175, 205, 55));  //Montanha      
        Path2D.Double m = new Path2D.Double();
        m.moveTo(getWidth(), 100);
        m.lineTo(getWidth(), 300);
        m.lineTo(1000, 300);
        m.lineTo(getWidth(), 100);
        m.closePath();
        g2.fill(m);
        g2.draw(m);

        g2.setPaint(new Color(228, 175, 30));
        Path2D.Double m2 = new Path2D.Double();
        m2.moveTo(1000, 100);
        m2.lineTo(1200, 300);
        m2.lineTo(80, 300);
        m2.lineTo(1000, 100);
        m2.closePath();
        g2.fill(m2);
        g2.draw(m2);

        g2.setPaint(Color.WHITE);
        Path2D.Double m3 = new Path2D.Double();
        m3.moveTo(995, 105);//topo branco da montanha
        m3.lineTo(950, 150);
        m3.lineTo(975, 140);
        m3.closePath();
        g2.fill(m3);
        g2.draw(m3);

        g2.setPaint(new Color(235, 185, 50));
        Path2D.Double m4 = new Path2D.Double();
        m4.moveTo(1040, 140);//montanha
        m4.lineTo(900, 300);
        m4.lineTo(1200, 300);
        m4.closePath();
        g2.fill(m4);
        g2.draw(m4);

        g2.setPaint(new Color(18, 126, 190));
        g2.fill(new Rectangle2D.Double(50, 170, 130, 170)); //predio

        g2.setPaint(new Color(252, 251, 221));

        int jan = 0;
        for (int i = 0; i < 4; i++) {//criar janelas
            g2.fill(new Rectangle2D.Double(60 + jan, 195, 15, 25));
            g2.fill(new Rectangle2D.Double(60 + jan, 235, 15, 25));
            jan += 30;
        }
        g2.fill(new Rectangle2D.Double(90, 280, 50, 50));//porta
        g2.setPaint(Color.GRAY);
        g2.fill(new Rectangle2D.Double(115, 280, 2, 50));//divide a porta ao meio
        g2.setPaint(Color.DARK_GRAY);
        g2.fill(new Rectangle2D.Double(100, 305, 10, 2));//puxador de porta
        g2.fill(new Rectangle2D.Double(122, 305, 10, 2));//puxador de porta

        g2.fill(new Rectangle2D.Double(80, 330, 70, 10));//escada
        g2.fill(new Rectangle2D.Double(70, 340, 90, 10));
        g2.fill(new Rectangle2D.Double(60, 350, 110, 10));

        Path2D.Double p = new Path2D.Double();//parede garagem
        p.moveTo(185, 333);
        p.lineTo(185, 250);
        p.lineTo(330, 250);
        p.lineTo(330, 333);
        g2.setPaint(Color.GRAY);
        g2.setStroke(new BasicStroke(12));
        g2.draw(p);

        g2.setPaint(new Color(252, 251, 221));
        g2.fillRect(190, 255, 135, 85);//portao de garagem

        g2.setPaint(new Color(0, 0, 0));
        g2.fill(new Rectangle2D.Double(200, 270, 115, 1));
        g2.fill(new Rectangle2D.Double(200, 285, 115, 1));
        g2.fill(new Rectangle2D.Double(200, 300, 115, 1));
        g2.fill(new Rectangle2D.Double(250, 320, 20, 3));

        
        
        //Usando imagem que está no HD
        URL url = getClass().getResource("dog.png");
        Image image = null;
        try {
            image = ImageIO.read(url);
        } catch (IOException ex) {
            Logger.getLogger(ProjetoCG.class.getName()).log(Level.SEVERE, null, ex);
        }

        Graphics2D fundo = (Graphics2D) g.create();
        fundo.drawImage(image, 400, 320, 50, 50, null);// vai colocar a imagem só uma vez na tela

        BufferedImage bi = new BufferedImage(image.getWidth(this), image.getHeight(this), BufferedImage.TYPE_INT_RGB);
        bi.createGraphics().drawImage(image, 0, 0, this);

        
        g2.setColor(new Color(255, 229, 172));   //pessoa
        g2.fillOval(330, 281, 20, 20);//cabeça
        g2.setPaint(new GradientPaint(500, 300, Color.red, 530, 310, Color.blue, true));
        g2.fillOval(325, 300, 20, 40); //corpo
        g2.setPaint(Color.BLACK);
        g2.fillOval(331, 281, 18, 10); //cabelo
        Path2D.Double p1 = new Path2D.Double();
        g2.setPaint(new Color(255, 229, 172));
        p1.moveTo(335, 310);//braço
        p1.lineTo(345, 325);
        g2.setStroke(new BasicStroke(5));
        g2.draw(p1);

        Path2D.Double p2 = new Path2D.Double();
        g2.setPaint(Color.BLACK);
        p2.moveTo(333, 340);//pé
        p2.lineTo(334, 362);
        p2.moveTo(337, 340);
        p2.lineTo(343, 362);
        g2.setStroke(new BasicStroke(8));
        g2.draw(p2);

        
        Path2D.Double poste = new Path2D.Double();
        g2.setPaint(new Color(80, 80, 80));
        poste.moveTo(400, 360);//poste
        poste.lineTo(400, 200);
        g2.setStroke(new BasicStroke(12));
        g2.draw(poste);

        Path2D.Double lamp = new Path2D.Double();
        g2.setPaint(new Color(120, 120, 120));
        poste.moveTo(400, 205);//suporte de lampada
        poste.lineTo(430, 210);
        g2.setStroke(new BasicStroke(5));
        g2.draw(poste);

        g2.setColor(new Color(180, 180, 180));
        g2.fillOval(425, 205, 40, 15);//caixa da lampada

        
        //Usando imagem que está no HD
        URL url2 = getClass().getResource("bird.png");
        Image image2 = null;
        try {
            image2 = ImageIO.read(url2);
        } catch (IOException ex) {
            Logger.getLogger(ProjetoCG.class.getName()).log(Level.SEVERE, null, ex);
        }

        Graphics2D fundo2 = (Graphics2D) g.create();
        fundo.drawImage(image2, 430, 172, 50, 50, null);// vai colocar a imagem só uma vez na tela

        BufferedImage bi2 = new BufferedImage(image2.getWidth(this), image2.getHeight(this), BufferedImage.TYPE_INT_RGB);
        bi2.createGraphics().drawImage(image2, 0, 0, this);

        
        g2.setColor(new Color(255, 229, 172));   //pessoa
        g2.fillOval(370, 281, 20, 20);//cabeça
        g2.setPaint(new GradientPaint(500, 300, Color.cyan, 530, 310, Color.yellow, true));
        g2.fillOval(375, 300, 20, 40); //corpo
        g2.setPaint(Color.BLACK);
        
        
        Path2D.Double p3 = new Path2D.Double();
        g2.setPaint(new Color(255, 229, 172));
        p3.moveTo(380, 310);//braço
        p3.lineTo(365, 305);
        g2.setStroke(new BasicStroke(5));
        g2.draw(p3);

        Path2D.Double p4 = new Path2D.Double();
        g2.setPaint(Color.BLACK);
        p4.moveTo(387, 340);//pé
        p4.lineTo(387, 362);
        p4.moveTo(380, 340);
        p4.lineTo(375, 362);
        g2.setStroke(new BasicStroke(8));
        g2.draw(p4);

        
        g2.setPaint(new Color(70, 70, 70));
        g2.fillOval(725, 355, 80, 10);//base
        g2.setPaint(new Color(205, 100, 10));
        g2.fillRect(755, 260, 20, 100);//tronco
        g2.setColor(new Color(0, 200, 0));
        g2.fillOval(700, 200, 80, 80);//ramos
        g2.fillOval(730, 180, 80, 80);
        g2.fillOval(760, 210, 80, 80);

        g2.setPaint(new Color(70, 70, 70));
        g2.fillOval(1175, 355, 80, 10);//base
        g2.setPaint(new Color(205, 100, 10));
        g2.fillRect(1205, 260, 20, 100);//tronco
        g2.setColor(new Color(0, 200, 50));
        g2.fillOval(1150, 200, 80, 80);//ramos
        g2.fillOval(1180, 180, 80, 80);
        g2.fillOval(1210, 210, 80, 80);
        g2.fillOval(1230, 190, 60, 60);

        //BANCO
        Path2D.Double banco = new Path2D.Double();
        g2.setPaint(new Color(70, 70, 70));
        g2.fillOval(835, 358, 20, 7);//base
        g2.fillOval(895, 358, 20, 7);//base

        g2.setPaint(Color.RED);
        g2.fillRect(840, 312, 8, 50);
        g2.fillRect(900, 312, 8, 50);
        g2.fillRect(829, 335, 90, 10);
        g2.fillRect(840, 325, 60, 5);
        g2.fillRect(840, 315, 60, 5);
        g2.setStroke(new BasicStroke(12));
        g2.draw(banco);

        
        
        aplicarTransformacaoWindowToViewport(g2, 0, getWidth(), getHeight(), 0, true);
        
        
    //ANIMAÇÃO
        g2.setColor(new Color(255, 229, 172));   //pessoa
        g2.fillOval(30 + x, 301 + y, 20, 20);//cabeça
        g2.setPaint(Color.red);
        g2.fillOval(25 + x, 320 + y, 20, 40); //corpo
        g2.setPaint(Color.BLACK);
        g2.fillOval(31 + x, 301 + y, 18, 10); //cabelo

        Path2D.Double pa = new Path2D.Double();
        g2.setPaint(new Color(255, 229, 172));
        pa.moveTo(35 + x, 330);//braço
        pa.lineTo(45 + x + x3, 345);
        g2.setStroke(new BasicStroke(5));
        g2.draw(pa);

        Path2D.Double pb = new Path2D.Double();
        g2.setPaint(Color.BLACK);
        pb.moveTo(35 + x, 360);//pé
        pb.lineTo(45 + x + x3, 382);
        pb.moveTo(35 + x, 360);
        pb.lineTo(30 + x +x4, 382);

        g2.setStroke(new BasicStroke(8));
        g2.draw(pb);

      

        //Raio da roda de traz    
        Path2D raios = new Path2D.Double();
        raios.moveTo(125 + x, 545);
        raios.lineTo(125 + x, 525);
        raios.moveTo(125 + x, 545);
        raios.lineTo(125 + x, 565);
        raios.moveTo(125 + x, 545);
        raios.lineTo(105 + x, 545);
        raios.moveTo(125 + x, 545);
        raios.lineTo(145 + x, 545);
        raios.moveTo(125 + x, 545);
        raios.lineTo(112.5 + x, 530);
        raios.moveTo(125 + x, 545);
        raios.lineTo(112.5 + x, 560);
        raios.moveTo(125 + x, 545);
        raios.lineTo(138.5 + x, 560);
        raios.moveTo(125 + x, 545);
        raios.lineTo(138.5 + x, 530);
        g4.rotate(numeroDeQuadro * 0.05, 125 + x, 545);
        g4.setStroke(new BasicStroke(2 * tamanhoPixel));
        g4.setPaint(Color.WHITE);
        g4.draw(raios);

        //Raio da roda de frente
        Path2D raios2 = new Path2D.Double();      
        g3.setStroke(new BasicStroke(2 * tamanhoPixel));
        g3.setPaint(Color.WHITE);

        raios2.moveTo(225 + x, 545);
        raios2.lineTo(225 + x, 525);
        raios2.moveTo(225 + x, 545);
        raios2.lineTo(225 + x, 565);
        raios2.moveTo(225 + x, 545);
        raios2.lineTo(205 + x, 545);
        raios2.moveTo(225 + x, 545);
        raios2.lineTo(245 + x, 545);
        raios2.moveTo(225 + x, 545);
        raios2.lineTo(212.5 + x, 530);
        raios2.moveTo(225 + x, 545);
        raios2.lineTo(212.5 + x, 560);
        raios2.moveTo(225 + x, 545);
        raios2.lineTo(238.5 + x, 560);
        raios2.moveTo(225 + x, 545);
        raios2.lineTo(238.5 + x, 530);
        g3.rotate(numeroDeQuadro * 0.08, 225 + x, 545);
        g3.draw(raios2);

        g2.setPaint(new Color(255, 235, 0));//rodas
        g2.setStroke(new BasicStroke(8));
        g2.drawOval(100 + x, 520, 50, 50);//traseira
        g2.drawOval(205 + x, 527, 40, 40);//frente


        Path2D.Double q = new Path2D.Double();//quadro
        g2.setPaint(new Color(60, 255, 25));
        q.moveTo(125 + x, 545);
        q.lineTo(160 + x, 500);
        q.lineTo(170 + x, 550);
        q.closePath();
        q.moveTo(170 + x, 550);
        q.lineTo(210 + x, 500);
        q.lineTo(160 + x, 500);
        q.moveTo(160 + x, 500);
        q.lineTo(160 + x, 485);
        q.moveTo(210 + x, 500);
        q.lineTo(225 + x, 545);
        q.moveTo(210 + x, 500);
        q.lineTo(210 + x, 485);
        g2.setStroke(new BasicStroke(4));
        g2.draw(q);

        Path2D.Double q1 = new Path2D.Double();//volante
        g2.setPaint(new Color(255, 235, 0));
        q1.moveTo(220 + x, 475);
        q1.lineTo(200 + x, 490);
        g2.setStroke(new BasicStroke(7));
        g2.draw(q1);

        Path2D.Double q2 = new Path2D.Double();//cilim
        g2.setPaint(new Color(255, 235, 0));
        q2.moveTo(155 + x, 485);
        q2.lineTo(165 + x, 485);
        g2.setStroke(new BasicStroke(10));
        g2.draw(q2);

        g2.setColor(Color.lightGray);
        g2.fillOval(165 + x, 540, 20, 20);//circulo pedal

        Path2D.Double q3 = new Path2D.Double();//pata pedal
        g6.setPaint(new Color(0, 0, 0));
        q3.moveTo(175 + x, 552);
        q3.lineTo(175 + x, 542);
        g6.setStroke(new BasicStroke(4));
        g6.rotate(numeroDeQuadro * 0.08, 175 + x, 552);
        g6.draw(q3);
        

        g2.setColor(new Color(255, 229, 172));   //pessoa
        g2.fillOval(160 + x, 397, 30, 30);//cabeça

        Path2D.Double pc1 = new Path2D.Double();
        pc1.moveTo(170 + x, 442);//braço1
        pc1.lineTo(215 + x, 470);
        g2.draw(pc1);

        g2.setPaint(Color.GREEN);
        g2.fillOval(155 + x, 425, 25, 55); //corpo
        g2.setPaint(new Color(255, 229, 172));

        g2.setPaint(Color.BLACK);

        Path2D.Double pc = new Path2D.Double();
        g2.setPaint(new Color(255, 229, 172));
        pc.moveTo(170 + x, 442);//braço2
        pc.lineTo(197 + x, 486);
        g2.setStroke(new BasicStroke(10));
        g2.draw(pc);

        
        Path2D.Double coxa = new Path2D.Double();
        g2.setPaint(new Color(0, 0, 0));//coxa
        coxa.moveTo(168+x, 478);
        coxa.lineTo(183 + x, 503+y2);
        g2.setStroke(new BasicStroke(15));
        g2.draw(coxa);
        
        Path2D.Double perna = new Path2D.Double();
        g2.setPaint(new Color(255, 229, 172));//perna
        perna.moveTo(185 + x, 505+y2);
        perna.lineTo(175 + x, 540+y2);
        g2.setStroke(new BasicStroke(12));
        g2.draw(perna);
        
        
        Path2D.Double pata = new Path2D.Double();//pe
        pata.moveTo(175+x, 540+y2);
        pata.lineTo(185+x +x2, 548+y2);
        g2.setStroke(new BasicStroke(10));
        g2.setPaint(new Color(0, 0, 0));
        g2.draw(pata);
        
    }

    private void aplicarTransformacaoWindowToViewport(Graphics2D grafico2d, double esquerda, double direita, double inferior, double superior, boolean preservarAspecto) {

        int largura = getWidth();  // Largura da area de desenho, em píxeis.
        int altura = getHeight();  // Altura da área de desenho, em píxeis.

        if (preservarAspecto) {
            // Ajustar os limites para match a relação de aspecto da área de desenho.
            double exibirAspecto = Math.abs((double) altura / largura);
            double aspectoPedido = Math.abs((inferior - superior) / (direita - esquerda));

            if (exibirAspecto > aspectoPedido) {
                // Expandir o viewport verticalmente.
                double excesso = (inferior - superior) * (exibirAspecto / aspectoPedido - 1);
                inferior += excesso / 2;
                superior -= excesso / 2;
            } else if (exibirAspecto < aspectoPedido) {
                // Expandir o viewport horizontalmente.
                double excesso = (direita - esquerda) * (aspectoPedido / exibirAspecto - 1);
                direita += excesso / 2;
                esquerda -= excesso / 2;
            }
        }

        grafico2d.scale(largura / (direita - esquerda), altura / (inferior - superior));
        grafico2d.translate(-esquerda, -superior);

        double larguraPixel = Math.abs((direita - esquerda) / largura);
        double alturaPixel = Math.abs((inferior - superior) / altura);

        tamanhoPixel = (float) Math.max(larguraPixel, alturaPixel);

    }

}
