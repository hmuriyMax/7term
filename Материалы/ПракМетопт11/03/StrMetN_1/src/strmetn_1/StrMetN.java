package strmetn_1;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;

public class StrMetN extends JApplet {
   Image pic;
        private int step = 1;
        public int vid=0;
        private final int LAST_STEP = 15;
        private boolean isStandalone = false;
  Color ColorFon = new Color(247,247,255);//цвет фона
  Color ColorPanelFon = new Color(255,255,255);//цвет фона панели
  Color ColorPanelRamka = new Color(157,187,255);//цвет обрамления панели
  Color ColorBtnBackground = new Color(220,230,255);//цвет фона кнопки
  Color ColorBtnForeground = new Color(0,0,60);//цвет надписи на кнопке
  Font FontBtn = new java.awt.Font("Dialog",1,12);//шрифт надписи на кнопке
  Font FontBtnStr = new java.awt.Font("Dialog",1,14);//шрифт надписи на кнопке(стрелки)
  Color ColorTitle = new Color(130,0,80);//цвет заголовка
  Font FontTitle = new java.awt.Font("Dialog",1,16);//шрифт заголовка

        Container cp;
        JLabel jLabel1 = new JLabel();
        JButton nextButton = new JButton();
        JButton refreshButton = new JButton();
        JButton previousButton = new JButton();
        TitledBorder titledBorder1;
  //JPanel drawPanel = new JPanel();

  DrawPanel_strn drawPanel = new DrawPanel_strn();
  JButton jButton_graf = new JButton();
 // JPanel jPanel_3d = new JPanel();
  Panel_3d jPanel_3d = new Panel_3d();
  TitledBorder titledBorder2;
//         drawPanel.language = language;
        public String getParameter(String key, String def) {
                return isStandalone ? System.getProperty(key, def) :
                        (getParameter(key) != null ? getParameter(key) : def);
        }

        public void init() {
                try {
                        jbInit();
                }
                catch (Exception e) {
                        e.printStackTrace();
                }
                step = 1;
                updateButtons();
        }
        private void jbInit() throws Exception {

                cp = this.getContentPane();
                pic = getImage(getCodeBase(), "fun.jpg");
                titledBorder1 = new TitledBorder("");
                titledBorder2 = new TitledBorder("");
    jLabel1.setFont(FontTitle);
                jLabel1.setForeground(ColorTitle);
                jLabel1.setPreferredSize(new Dimension(178, 21));
                jLabel1.setRequestFocusEnabled(true);
                jLabel1.setToolTipText("");
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
                 jLabel1.setText("Идея поиска минимума функции методом Марквардта");
                jLabel1.setBounds(new Rectangle(0, 5, 494, 18));
                cp.setLayout(null);
                this.getContentPane().setBackground(ColorFon);
                this.setSize(new Dimension(511, 557));
        this.addFocusListener(new StrMetN_this_focusAdapter(this));
                nextButton.setBackground(ColorBtnBackground);
                nextButton.setBounds(new Rectangle(313, 522, 100, 23));
                nextButton.setFont(FontBtnStr);
                nextButton.setForeground(ColorBtnForeground);
                nextButton.setMaximumSize(new Dimension(121, 23));
                nextButton.setMinimumSize(new Dimension(121, 23));
                nextButton.setPreferredSize(new Dimension(121, 23));
                nextButton.setText("==>");
                nextButton.addActionListener(new StrMetN_nextButton_actionAdapter(this));
                refreshButton.setBackground(ColorBtnBackground);
                refreshButton.setBounds(new Rectangle(193, 522, 100, 23));
                refreshButton.setFont(FontBtn);
                refreshButton.setForeground(ColorBtnForeground);
                refreshButton.setMaximumSize(new Dimension(121, 23));
                refreshButton.setMinimumSize(new Dimension(121, 23));
                refreshButton.setPreferredSize(new Dimension(121, 23));
                refreshButton.setText("Обновить");
                refreshButton.addActionListener(new StrMetN_refreshButton_actionAdapter(this));
                previousButton.setBackground(ColorBtnBackground);
                previousButton.setBounds(new Rectangle(73, 522, 100, 23));
                previousButton.setFont(FontBtnStr);
                previousButton.setForeground(ColorBtnForeground);
                previousButton.setMaximumSize(new Dimension(121, 23));
                previousButton.setMinimumSize(new Dimension(121, 23));
                previousButton.setPreferredSize(new Dimension(121, 23));
                previousButton.setText("<==");
                previousButton.addActionListener(new StrMetN_previousButton_actionAdapter(this));
                drawPanel.setAlignmentY( (float) 0.5);
                drawPanel.setBorder(null);
                drawPanel.setLocation(4, 26);
                drawPanel.setBounds(new Rectangle(13, 28, 484, 431));
                drawPanel.setBounds(new Rectangle(16, 26, 482, 477));
    jButton_graf.setBounds(new Rectangle(3, 302, 236, 42));
    jButton_graf.setFont(new java.awt.Font("Dialog", 3, 12));
    jButton_graf.setBackground(ColorBtnBackground);
    jButton_graf.setForeground(ColorBtnForeground);
    jButton_graf.setText("Построить 3-мерный график   f");
    jButton_graf.addActionListener(new StrMetN_jButton_graf_actionAdapter(this));

    jPanel_3d.setBackground(Color.white);
    jPanel_3d.setBorder(BorderFactory.createLoweredBevelBorder());
    jPanel_3d.setBounds(new Rectangle(1, 20, 467, 281));
    jPanel_3d.setLayout(null);
    jPanel_3d.setVisible(false);

    cp.add(jLabel1, null);
    cp.add(refreshButton, null);
    cp.add(previousButton, null);
    cp.add(nextButton, null);
    cp.add(drawPanel, null);
    drawPanel.add(jButton_graf, null);
    drawPanel.add(jPanel_3d, null);
    cp.add(drawPanel, null);
//    cp.add(drawPanel, null);
        }
        //Start the applet
        public void start() {
        }
        //Stop the applet
        public void stop() {
        }
        //Destroy the applet
        public void destroy() {
        }
        //Get Applet information
        public String getAppletInfo() {
                return "Applet Information";
        }
        //Get parameter info
        public String[][] getParameterInfo() {
                return null;
        }
//Main method
  public static void main(String[] args) {
        StrMetN applet = new StrMetN();
        applet.isStandalone = true;
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        frame.setTitle("Applet Frame");
        frame.getContentPane().add(applet, BorderLayout.CENTER);
        applet.init();
        applet.start();
        frame.setSize(700, 600);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation( (d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
        frame.setVisible(true);
  }
  static {
        try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e) {
        }
  }

  class Panel_3d extends JPanel{
  public void paintComponent(Graphics y){
    super.paintComponent(y);
     y.drawImage(pic, 10,7, this);
     //ColorPanelRamka
     y.setColor(Color.black);
     y.drawRect(3,3,460,275);
     //  y.setFont(new java.awt.Font("Dialog", 1, 16));
     //  y.setColor(new Color(180,0,60));
     //  y.drawString("Упражнение", 270,18);

  }
 }


  void nextButton_actionPerformed(ActionEvent e) {
    if (step >= LAST_STEP) { return;}
    if(!Stringer_strN.tryUpdate()) {
        System.out.println("aaaa");
        return;
    }
    step++;
    drawPanel.setStep(step);
    updateButtons();
  }
  public void updateButtons() {
        previousButton.setEnabled(step != 1);
//        refreshButton.setEnabled(step != 1);
        nextButton.setEnabled(step != LAST_STEP);
  }
  void previousButton_actionPerformed(ActionEvent e) {
        if (step <= 1) { return;}
        step--;
        drawPanel.setStep(step);
        updateButtons();
  }
  void this_focusLost(FocusEvent e) {}
  void refreshButton_actionPerformed(ActionEvent e) {
        step = 1;
        drawPanel.setStep(1);
        updateButtons();
  }

  void jButton_graf_actionPerformed(ActionEvent e) {

    jPanel_3d.setVisible(true);

    if (vid<=2){
    vid = vid + 1;
  }
  if(vid==0)
  {jPanel_3d.setVisible(false);
 }
 if(vid==1)
 { jPanel_3d.setVisible(true);
jButton_graf.setText("Скрыть 3-мерный график   f");
}
if(vid==2)
  { jPanel_3d.setVisible(false);
   jButton_graf.setText("Построить 3-мерный график   f");
 }
if(vid==2) {vid=0;}

  drawPanel.repaint();
}


}
class StrMetN_nextButton_actionAdapter implements java.awt.event.
        ActionListener {
        StrMetN adaptee;

        StrMetN_nextButton_actionAdapter(StrMetN adaptee) {
                this.adaptee = adaptee;
        }
        public void actionPerformed(ActionEvent e) {
                adaptee.nextButton_actionPerformed(e);
        }
}
class StrMetN_previousButton_actionAdapter implements java.awt.event.
        ActionListener {
        StrMetN adaptee;

        StrMetN_previousButton_actionAdapter(StrMetN adaptee) {
                this.adaptee = adaptee;
        }
        public void actionPerformed(ActionEvent e) {
                adaptee.previousButton_actionPerformed(e);
        }
}
class StrMetN_this_focusAdapter extends java.awt.event.FocusAdapter {
        StrMetN adaptee;
        StrMetN_this_focusAdapter(StrMetN adaptee) {
        this.adaptee = adaptee;
        }
        public void focusLost(FocusEvent e) {
        adaptee.this_focusLost(e);
        }
}
class StrMetN_refreshButton_actionAdapter implements java.awt.event.ActionListener {
        StrMetN adaptee;
        StrMetN_refreshButton_actionAdapter(StrMetN adaptee) {
                this.adaptee = adaptee;
        }
        public void actionPerformed(ActionEvent e) {
                adaptee.refreshButton_actionPerformed(e);
        }
}

class StrMetN_jButton_graf_actionAdapter implements java.awt.event.ActionListener {
  StrMetN adaptee;

  StrMetN_jButton_graf_actionAdapter(StrMetN adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton_graf_actionPerformed(e);
  }
}
