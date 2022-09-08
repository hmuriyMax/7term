//package MetZoitLec;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;


public class SimplexAlgoritm extends JApplet{
	boolean isStandalone = false;
	int step=0,ok=0;
	int x[]=new int[3];
	int y[]=new int[3];
	Panel_paint1 jPn1Main = new Panel_paint1();
	Panel_paint2 jPn1Plus = new Panel_paint2();
	JButton jbtnNext = new JButton();
	JButton jbtnNew = new JButton();
	JButton jbtnBack = new JButton();
	JCheckBox jChkbFormula = new JCheckBox();
	/**Get a parameter value*/
	public String getParameter(String key, String def){
		return isStandalone ? System.getProperty(key, def) :
		(getParameter(key) != null ? getParameter(key) : def);
		}

	/**Construct the applet*/
	public SimplexAlgoritm(){
		}
	/**Initialize the applet*/
	public void init(){
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  /**Component initialization*/
  private void jbInit() throws Exception {

   this.getContentPane().setLayout(null);

   jbtnNext.setBounds(new Rectangle(430,515,127,23));
   jbtnNext.setBackground(new Color(167, 213, 255));
   jbtnNext.setFont(new java.awt.Font("Dialog", 1, 14));
   jbtnNext.setForeground(new Color(0, 0, 130));
   jbtnNext.setText("==>");
   this.getContentPane().add(jbtnNext, null);
   jbtnNext.addActionListener(new java.awt.event.ActionListener() {
     public void actionPerformed(ActionEvent e) {
       jbtnNext_actionPerformed(e);
     }
   });

   jbtnNew.setBounds(new Rectangle(280,515,127,23));
   jbtnNew.setBackground(new Color(167, 213, 255));
   jbtnNew.setFont(new java.awt.Font("Dialog", 1, 12));
   jbtnNew.setForeground(new Color(0, 0, 130));
   jbtnNew.setText("Обновить");
   this.getContentPane().add(jbtnNew, null);
   jbtnNew.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(ActionEvent e) {
       jbtnNew_actionPerformed(e);
     }
   });

   jbtnBack.setBounds(new Rectangle(130,515,127,23));
   jbtnBack.setBackground(new Color(167, 213, 255));
   jbtnBack.setFont(new java.awt.Font("Dialog", 1, 14));
   jbtnBack.setForeground(new Color(0, 0, 130));
   jbtnBack.setText("<==");
   this.getContentPane().add(jbtnBack, null);
   jbtnBack.addActionListener(new java.awt.event.ActionListener() {
	   	public void actionPerformed(ActionEvent e) {
	   		jbtnBack_actionPerformed(e);
		   	}

   		});

   jChkbFormula.setBounds(545,483,100,20);
   jChkbFormula.setBackground(Color.white);
   jChkbFormula.setFont(new java.awt.Font("Dialog", 1, 14));
   jChkbFormula.setForeground(new Color(130, 0, 60));
   jChkbFormula.setBorder(BorderFactory.createLineBorder(new Color(164, 197, 151),1));
   jChkbFormula.setText("Формулы");
   jChkbFormula.setVisible(false);
   jChkbFormula.setSelected(false);
   this.getContentPane().add(jChkbFormula, null);
   jChkbFormula.addMouseListener(new java.awt.event.MouseAdapter() {
	   			public void mousePressed(MouseEvent e) {
	   					jChkbFormula_mousePressed(e);
	   					}
    			});

   this.setSize(new Dimension(700,570));
   this.getContentPane().setBackground(new Color(80, 80, 180));

   jPn1Plus.setBackground(new Color(230, 230, 230));
   jPn1Plus.setBounds(new Rectangle(135, 80, 400, 410));
   jPn1Plus.setVisible(false);
   this.getContentPane().add(jPn1Plus, null);

   jPn1Main.setBackground(new Color(31, 82, 113));
   jPn1Main.setBounds(new Rectangle(0, 0, 700, 570));
   this.getContentPane().add(jPn1Main, null);
  }
  /**Get Applet information*/
  public String getAppletInfo() {
    return "Applet Information";
  }
  /**Get parameter info*/
  public String[][] getParameterInfo() {
    return null;
  }

  class Panel_paint2 extends JPanel {
  public void paintComponent(Graphics g2) {
   super.paintComponent(g2);
   // Формулы
   g2.setColor(new Color(167, 213, 255)); //
   g2.fill3DRect(1,1,398,408,true);       //
   g2.setColor(Color.white);              //
   g2.fillRect(20,20,360,370);            //
   g2.setColor(new Color(213, 225, 255)); //
   for(int i=0;i<38;i++)                  //
    g2.drawLine(20,20+i*10,380,20+i*10);  //            Оформление окна
   for(int i=0;i<37;i++)                  //
   g2.drawLine(20+i*10,20,20+i*10,370+20);   //
   g2.setColor(new Color(40,70,170));     //
   g2.drawRect(20,20,360,370);            //
   g2.setColor(new Color(150,0,0));       //
   g2.setFont(new Font("Dialog",1,14));   //
   g2.drawString("Формулы:",160,40);      //
   g2.setColor(new Color(0,0,80));        //
   g2.setFont(new Font("Dialog",3,12));   //

   if(step>=2)
    {
       OptimizationBasic.drawFormula(g2,"min(c,x) = min(c_{1} x_{1} +...+c_{n} x_{n} )",40,60,new java.awt.Font("Dialog",1,14),new java.awt.Font("SanSerief",1,11));
       OptimizationBasic.drawFormula(g2,"(1)",350,60,new java.awt.Font("Dialog",1,14),new java.awt.Font("SanSerief",1,11));
	   OptimizationBasic.drawFormula(g2,"\u2211 a_{ij}x_{j} \u2264 b_{i}",40,90,new java.awt.Font("Dialog",1,14),new java.awt.Font("SanSerief",1,11));
	   OptimizationBasic.drawFormula(g2,"(2)",350,90,new java.awt.Font("Dialog",1,14),new java.awt.Font("SanSerief",1,11));
	   OptimizationBasic.drawFormula(g2,"\u2211 a_{ij}x_{j} \u2264 b_{i}, x_{j}\u22650",40,120,new java.awt.Font("Dialog",1,14),new java.awt.Font("SanSerief",1,11));
	   OptimizationBasic.drawFormula(g2,"(3)",350,120,new java.awt.Font("Dialog",1,14),new java.awt.Font("SanSerief",1,11));
       OptimizationBasic.drawFormula(g2,"\u2211 a_{ij}x_{j} = b_{i}, x_{j}\u22650",40,150,new java.awt.Font("Dialog",1,14),new java.awt.Font("SanSerief",1,11));
       OptimizationBasic.drawFormula(g2,"(4)",350,150,new java.awt.Font("Dialog",1,14),new java.awt.Font("SanSerief",1,11));
    }

   if(step>=12)
    {
	 OptimizationBasic.drawFormula(g2,"\u03b2_{l} / a_{lk} = min_{i} { \u03b2_{i} / a_{ik} } = \u03b8 \u2265 0",40,180,new java.awt.Font("Dialog",1,14),new java.awt.Font("Dialog",1,11));
	 OptimizationBasic.drawFormula(g2,"(5)",350,180,new java.awt.Font("Dialog",1,14),new java.awt.Font("SanSerief",1,11));
    }

   if(step>=13)
    {
	 OptimizationBasic.drawFormula(g2,"c_{k} - \u2211 c_{i} a_{ik} \u2264 0 \u21d2 c_{k} - z_{k} \u2264 0",40,210,new java.awt.Font("Dialog",1,14),new java.awt.Font("Dialog",1,11));
	 OptimizationBasic.drawFormula(g2,"(6)",350,210,new java.awt.Font("Dialog",1,14),new java.awt.Font("SanSerief",1,11));
    }
}}

  class Panel_paint1 extends JPanel {
  public void paintComponent(Graphics g1) {
   super.paintComponent(g1);
//----------------Оформление окна-----------------------------------
   g1.setColor(new Color(0,0,50));
   g1.fill3DRect(12,13,655,500,true);
   g1.setColor(Color.white);
   g1.fill3DRect(10,10,651,496,true);
   g1.setColor(new Color(31, 82, 113));
   for(int i=0;i<57;i++)
    for(int j=0;j<57;j++)
      {
        if(Math.sqrt((j-9-57)*(j-9-57)+(i-9-57)*(i-9-57))>60)
         g1.drawRect(i,j,1,1);
      }

    g1.setColor(new Color(31, 82, 113));
    g1.drawLine(58,9,663,9);
    g1.drawArc(9,9,96,96,90,90);
    g1.drawLine(9,58,9,509);
    g1.drawLine(58,10,663,10);
    g1.drawArc(10,10,96,96,90,90);
    g1.drawLine(10,58,10,509);
    for(int i=1;i<=22;i++)
     {
      g1.setColor(new Color(0,0,0));
      g1.fillOval(41+i*25,16,8,8);
      g1.drawArc(35+i*25,1,20,20,9,270);
      g1.drawArc(34+i*25,0,20,20,5,270);
      g1.setColor(new Color(170,170,170));
      g1.drawArc(41+i*25,17,5,5,220,180);
      g1.drawArc(36+i*25,2,19,19,10,60);
      g1.drawArc(33+i*25,-1,23,23,110,70);
     }
    g1.setColor(new Color(213, 225, 255));

    for(int i=5;i<46;i++)
     g1.drawLine(40,30+i*10,630,30+i*10);
    for(int i=0;i<60;i++)
     g1.drawLine(40+i*10,80,40+i*10,480);

//--------------------------------------------
   if(step==0)
      {
       g1.setColor(new Color(0,0,160));
       g1.setFont(new Font("Dialog",1,26));
       g1.drawString("Тема лекции:",250,170);
       g1.setColor(new Color(130,0,60));
       g1.setFont(new Font("Dialog",1,28));
       g1.drawString("Симплекс метод",200,270);
       g1.setFont(new Font("Dialog",1,18));
       g1.setColor(new Color(60,0,250));
       g1.drawLine(80,280,590,280);
       g1.drawLine(160,290,510,290);
       g1.setColor(new Color(0,0,10));
       g1.drawLine(160,291,510,291);
       g1.drawLine(80,281,590,281);
       g1.setColor(new Color(40,70,170));
       g1.drawRect(40,80,590,400);
       jbtnBack.setEnabled(false);
      }
        else
        if(step!=104)
        {
          g1.setFont(new Font("Dialog",1,16));
          g1.setColor(new Color(130,0,60));
          g1.drawString("Симплекс метод",260,55);

          g1.setColor(new Color(100, 80, 255));
          g1.drawLine(150,62,520,62);
          g1.drawLine(230,70,440,70);
          g1.setColor(new Color(0, 0, 0));
          g1.drawLine(150,63,520,63);
          g1.drawLine(230,71,440,71);
        }

     if(step<2)
      {
       jPn1Plus.setVisible(false);
       jChkbFormula.setVisible(false);
       jChkbFormula.setSelected(false);
       ok=0;
      }
     if(ok==1 && jPn1Plus.isVisible()==false && step>=2)
        {
         jPn1Plus.setVisible(true);
         repaint();
        }
        else
         if(ok==0 && jPn1Plus.isVisible()==true)
          {
           jPn1Plus.setVisible(false);
           repaint();
          }
      if(step>=2 && step<=100)
       jChkbFormula.setVisible(false);
       else
        jChkbFormula.setVisible(false);
     if(step>=1)
      jbtnBack.setEnabled(true);

     if(step>=2 && step<=9)
     {
     if(step>=2)
      {
       if(step==2)
        g1.setColor(new Color(0,0,255));
        else
         g1.setColor(new Color(0,0,80));
       g1.setFont(new Font("SanSerief",2,13));
       g1.drawString("Линейное программирование ( ЛП )",50,100);
       g1.setFont(new Font("SanSerief",1,13));
       g1.drawString(" — математическая дисциплина, посвящённая теории",270,100);
       g1.drawString("и методам решения экстремальных задач на множествах n-мерного векторного",50,120);
       g1.drawString("пространства, задаваемых системами линейных уравнений и неравенств.",50,140);
  	}
     if(step>=3)
      {
		g1.setColor(new Color(255,0,0));
       g1.drawString("Общая задача линейного программирования:",50,160);
   	}

     if(step>=4)
      {
       if(step==4)
        g1.setColor(new Color(0,0,255));
        else
         g1.setColor(new Color(0,0,80));
       OptimizationBasic.drawFormula(g1,"f(x) = \u2211 c_{j} x_{j} = c_{1} x_{1} + c_{2} x_{2} + ... + c_{n} x_{n} \u2192 max ( min ) (целевая функция)",50,180,new java.awt.Font("Dialog",1,14),new java.awt.Font("Dialog",1,9));
       g1.setFont(new Font("SanSerief",1,9));
       g1.drawString("n",92,169);
       g1.drawString("j = 1",88,190);
 //      OptimizationBasic.drawFormula(g1,"(1)",590,180,new java.awt.Font("SanSerief",1,14),new java.awt.Font("SanSerief",1,9));
	}
     if(step>=5)
      {
       if(step==5)
        g1.setColor(new Color(0,0,255));
        else
         g1.setColor(new Color(0,0,80));

       g1.setFont(new Font("Dialog",1,13));
	   g1.drawString("при ограничениях:",50,207);
	   OptimizationBasic.drawFormula(g1,"\u2211 a_{ij} x_{j} \u2264 b_{i}, i = 1,2...m_{1}",57,231,new java.awt.Font("SanSerief",1,14),new java.awt.Font("SanSerief",1,9));
	   g1.setFont(new Font("SanSerief",1,9));
	   g1.drawString("n",60,220);
       g1.drawString("j = 1",55,240);
       OptimizationBasic.drawFormula(g1,"\u2211 a_{ij} x_{j} \u2265 b_{i}, i = m_{1} + 1,... m_{2}",57,261,new java.awt.Font("SanSerief",1,14),new java.awt.Font("SanSerief",1,9));
       g1.setFont(new Font("SanSerief",1,9));
	   g1.drawString("n",60,250);
       g1.drawString("j = 1",55,270);
	   OptimizationBasic.drawFormula(g1,"\u2211 a_{ij} x_{j} = b_{i}, i = m_{2} + 1,... m",57,291,new java.awt.Font("SanSerief",1,14),new java.awt.Font("SanSerief",1,9));
       g1.setFont(new Font("SanSerief",1,9));
	   g1.drawString("n",60,280);
       g1.drawString("j = 1",55,300);
       OptimizationBasic.drawFormula(g1,"x_{j} \u2264 0, j = 1,...n",50,320,new java.awt.Font("SanSerief",1,14),new java.awt.Font("SanSerief",1,9));
	   g1.setFont(new Font("SanSerief",1,13));
   }
     if(step>=6)
      {
       if(step==6)
        g1.setColor(new Color(0,0,255));
        else
         g1.setColor(new Color(0,0,80));
	   g1.drawString("Одним из универсальных методов решения задач линейного программирования",50,340);
	   g1.drawString("является                               или метод последовательного улучшения плана.",50,360);
       //g1.drawString("разработанный американским математиком Джорджем Данцигом в 1947 году.",50,380);
       g1.setFont(new Font("SanSerief",2,13));
       g1.drawString("симплекс-метод",120,360);
      }
     if(step>=7)
      {
       if(step==7)
        g1.setColor(new Color(0,0,255));
        else
         g1.setColor(new Color(0,0,80));
        g1.setFont(new Font("SanSerief",1,13));
		g1.drawString("Если задача разрешима, то ее оптимальный план совпадает, по крайней мере, c",50,380);
		g1.drawString("одним из опорных решений системы ограничений.",50,400);
      }
     if(step>=8)
      {
       if(step==8)
        g1.setColor(new Color(0,0,255));
        else
         g1.setColor(new Color(0,0,80));
        g1.setFont(new Font("SanSerief",1,13));
		g1.drawString("Именно этот опорный план и",400,400);
		g1.drawString("отыскивается симплекс-методом в результате упорядоченного перебора опорных",50,420);
		g1.drawString("решений.",50,440);
      }
     if(step>=9)
      {
       if(step==9)
        g1.setColor(new Color(0,0,255));
        else
         g1.setColor(new Color(0,0,80));
        g1.setFont(new Font("SanSerief",1,13));
		g1.drawString("Так как общее число опорных решений конечно, то через определенное",115,440);
		g1.drawString("число шагов будет найден оптимальный опорный план, либо установлена",50,460);
		g1.drawString("неразрешимость задачи.",50,480);
      }
	}

     if(step>=10 && step<=20)
     {
		 if(step>=10)
		  {
		   if(step==10)
			g1.setColor(new Color(0,0,255));
			else
			 g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",1,13));

		  }
		 if(step>=11)
		  {
		   if(step==11)
			g1.setColor(new Color(0,0,255));
			else
			 g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",1,13));
			g1.drawString("Чтобы получить новый опорный план, первоначальный базис преобразовывают",50,100);
			g1.drawString("в новый. Для этого из первоначального базиса удаляют некоторую базисную",50,120);
			g1.drawString("переменную и вместо неё вводят другую из группы свободных.",50,140);
		  }
		 if(step>=12)
		  {
			g1.setColor(new Color(255,0,0));
			g1.setFont(new Font("Dialog",1,13));
			g1.drawString("Этапы решения задачи линейного прогораммирования симплекс-методом:",50,160);
		  }
		 if(step>=13)
		  {
		   if(step==13)
			g1.setColor(new Color(0,0,255));
			else
			 g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",2,13));
			g1.drawString("расширенной",475,180);
			g1.setFont(new Font("Dialog",1,13));
		    g1.drawString("1. Задачна должна быть сведена либо к канонической, либо к                        форме",50,180);
		    g1.drawString("(притом все элементы свободных членов должны быть неотрицательными).",50,200);
	   	  }
		 if(step>=14)
		  {
		   if(step==14)
			g1.setColor(new Color(0,0,255));
			else
			 g1.setColor(new Color(0,0,80));
		   g1.drawString("2. Нахождение начального опорного плана задачи.",50,220);
	   	  }
		 if(step>=15)
		  {
		   if(step==15)
			g1.setColor(new Color(0,0,255));
			else
			 g1.setColor(new Color(0,0,80));
		   g1.drawString("3. Составление и заполнение симплекс-таблицы.",50,240);
	   	  }
		 if(step>=16)
		  {
		   if(step==16)
			g1.setColor(new Color(0,0,255));
			else
			 g1.setColor(new Color(0,0,80));
		   g1.drawString("4. Нахождение оптимального плана задачи.",50,260);
	   	  }

		 if(step>=17)
		  {
           g1.setColor(new Color(255,0,0));
		   g1.drawString("Приведение к каноническому виду:",50,280);
	   	  }
		 if(step>=18)
		  {
		   if(step==18)
			g1.setColor(new Color(0,0,255));
			else
			 g1.setColor(new Color(0,0,80));
		   g1.drawString("1) Минимизация целевой функции (f(x)) равносильна максимизации целевой функции",50,300);
		   g1.drawString("(-f(x)). Так, если целевая функция исследуется на минимум, т.е. f(x) \u2192 min, то",50,320);
		   g1.drawString("можно рассмотреть функцию с противоположенным знаком, которая будет стремится",50,340);
		   g1.drawString("к максимуму: -f(x) \u2192 max;",50,360);
	   	  }
		 if(step>=19)
		  {
		   if(step==19)
			g1.setColor(new Color(0,0,255));
			else
			 g1.setColor(new Color(0,0,80));
		   OptimizationBasic.drawFormula(g1,"2) ограничения-неравенства вида a_{i 1}x_{1} + a_{i 2}x_{2} + ... + a_{i n}x_{n} \u2264 ( \u2265 ) b_{i} преобразуется в",50,380,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		   g1.drawString("ограничения-равенства путем прибавления ( вычетания ) к левым частям",50,400);
		   OptimizationBasic.drawFormula(g1,"дополнительных ( балансовых ) неотрицательных переменных x_{n + i} \u2265 0.",50,420,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		   OptimizationBasic.drawFormula(g1,"a_{i 1}x_{1} + a_{i 2}x_{2} + ... + a_{i n}x_{n} + ( - ) x_{n + i} = b_{i}, i = 1...m ;",50,440,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
	   	  }
		 if(step>=20)
		  {
		   if(step==20)
			g1.setColor(new Color(0,0,255));
			else
			 g1.setColor(new Color(0,0,80));
		   g1.drawString("3) Дополнительные переменные в целевую функцию вводятся с коффициентами,",50,460);
		   OptimizationBasic.drawFormula(g1,"равными нулю: c_{n + i} = 0, i = 1...m",50,480,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
	   	  }
	}

     if(step>=21 && step<=29)
     {
		 if(step>=21)
		 {
		 	g1.setFont(new Font("Dialog",1,13));
		 	g1.setColor(new Color(255,0,0));
		 	g1.drawString("Нахождение начального опорного плана:",50,100);
		 }
		 if(step>=22)
		 {
		 	if(step==22)
			g1.setColor(new Color(0,0,255));
			else
			 g1.setColor(new Color(0,0,80));
		 	g1.setFont(new Font("Dialog",1,13));
		 	g1.drawString("Говорят, что ограничение-равенство канонической задачи имеет",50,120);
		 	g1.setFont(new Font("Dialog",2,13));
		 	g1.drawString("предпочтительный",490,120);
		 	g1.drawString("вид,",50,140);
		 	g1.setFont(new Font("Dialog",1,13));
		 	OptimizationBasic.drawFormula(g1,"если при неотрицательности его правой части ( b_{i} \u2265 0, i = 1 ... m ) левая часть",80,140,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	g1.drawString("содержит переменную с единичным коэффициентом, которая во все остальные",50,160);
		 	g1.drawString("ограничения входит с коэффициаентами, равными нулю.",50,180);

		 }
		 if(step>=23)
		 {
		 	if(step==23)
			g1.setColor(new Color(0,0,255));
			else
			 g1.setColor(new Color(0,0,80));
		 	g1.setFont(new Font("Dialog",1,13));
		 	g1.drawString("Если каждое ограничение канонической задачи ЛП имеет имеет предпочтительный",50,200);
		 	g1.drawString("вид ( т.е.",50,220);
		 	g1.setFont(new Font("Dialog",2,13));
		 	g1.drawString("система ограничений приведена к единичному неотрицательному базису",115,220);
		 	g1.drawString("начальный опорный план",50,240);
		 	g1.setFont(new Font("Dialog",1,13));
		 	g1.drawString("), то",575,220);
		 	g1.drawString("(т.е. неотрицательное базисное решение) строится следующим",210,240);
		 	g1.drawString("образом.",50,260);
		}
		 if(step>=24)
		 {
		 	if(step==24)
			g1.setColor(new Color(0,0,255));
			else
			 g1.setColor(new Color(0,0,80));
		 	g1.setFont(new Font("Dialog",1,13));
			g1.drawString("Предпочтительные переменные выбираются в качестве",117,260);
		 	g1.setFont(new Font("Dialog",2,13));
		 	g1.drawString("базисных",500,260);
		 	g1.drawString("свободных",300,280);
		 	g1.setFont(new Font("Dialog",1,13));
		 	g1.drawString(", а все",560,260);
		 	g1.drawString("остальные - в качестве переменных",50,280);
		 	g1.drawString("переменных.",370,280);
		}
		 if(step>=25)
		 {
		 	if(step==25)
			g1.setColor(new Color(0,0,255));
			else
			 g1.setColor(new Color(0,0,80));
		 	g1.setFont(new Font("Dialog",1,13));
			g1.drawString("Свободные переменные",465,280);
		 	OptimizationBasic.drawFormula(g1,"приравниваются нулю.",50,300,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=26)
		 {
		 	if(step==26)
			g1.setColor(new Color(0,0,255));
			else
			 g1.setColor(new Color(0,0,80));
		 	g1.setFont(new Font("Dialog",1,13));

		 	g1.drawString("Начальный опорный план будет иметь следующий вид:",50,320);
		}
		 if(step>=27)
		 {
		 	if(step==27)
			g1.setColor(new Color(0,0,255));
			else
			 g1.setColor(new Color(0,0,80));
		 	g1.setFont(new Font("Dialog",1,13));
		 	OptimizationBasic.drawFormula(g1,"X_{0} = ( b_{1}, b_{2}, ... , b_{m}, 0, ... , 0 ), (количество нулей: n)",50,340,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=28)
		 {
		 	if(step==28)
			g1.setColor(new Color(0,0,255));
			else
			 g1.setColor(new Color(0,0,80));
		 	g1.setFont(new Font("Dialog",1,13));
		 	g1.drawString("Пусть задача ЛП представлена в симметричном виде:",50,360);
		}
		 if(step>=29)
		 {
		 	if(step==29)
			g1.setColor(new Color(0,0,255));
			else
			 g1.setColor(new Color(0,0,80));
		 	g1.setFont(new Font("Dialog",1,13));

		 	OptimizationBasic.drawFormula(g1,"z = c_{1}x_{1} + c_{2}x_{2} + ... + c_{n}x_{n} \u2192 max",100,380,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	g1.drawString("система ограничений",50,400);
		 	OptimizationBasic.drawFormula(g1,"a_{11}x_{1} + a_{12}x_{2} + ... + a_{1n}x_{n} \u2264 b_{1},",100,420,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"a_{21}x_{1} + a_{22}x_{2} + ... + a_{2n}x_{n} \u2264 b_{2},",100,440,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			g1.drawString(".............................................",100,460);
		 	OptimizationBasic.drawFormula(g1,"a_{m1}x_{1} + a_{m2}x_{2} + ... + a_{mn}x_{n} \u2264 b_{m},",100,480,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));

		 	g1.drawString("где",330,450);
		 	OptimizationBasic.drawFormula(g1,"x_{j} \u2265 0, j = 1...n",380,440,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"b_{i} \u2265 0, i = 1...m",380,460,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 }

	 }

     if(step>=30 && step<=39)
     {
		 if(step>=30)
		 {
		 	if(step==30)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",1,13));
			g1.drawString("Приводим систему ограничений к единичному неотрицательному базису:",50,100);
		}
		 if(step>=31)
		 {
		 	if(step==31)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",1,13));
		 	OptimizationBasic.drawFormula(g1,"z = c_{1}x_{1} + c_{2}x_{2} + ... + c_{n}x_{n} \u2192 max",100,120,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	g1.drawString("система ограничений",50,140);
		 	OptimizationBasic.drawFormula(g1,"a_{11}x_{1} + a_{12}x_{2} + ... + a_{1n}x_{n} + x_{n + 1}                        = b_{1},",100,160,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"a_{21}x_{1} + a_{22}x_{2} + ... + a_{2n}x_{n}             + x_{n + 2}            = b_{2},",100,180,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			g1.drawString(".............................................................................",100,200);
		 	OptimizationBasic.drawFormula(g1,"a_{m1}x_{1} + a_{m2}x_{2} + ... + a_{mn}x_{n}                     + x_{n + m} = b_{m},",100,220,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));

		 	g1.drawString("где",450,190);
		 	OptimizationBasic.drawFormula(g1,"x_{j} \u2265 0, j = 1...n + m",500,180,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"b_{i} \u2265 0, i = 1...m",500,200,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=32)
		 {
		 	if(step==32)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",1,13));
		 	g1.drawString("Полученная система ограничений эквивалента исходной и имеет предпочтительный",50,240);
		 	g1.drawString("вид.",50,260);
		}

		 if(step>=33)
		 {
		 	if(step==33)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",1,13));
		 	g1.drawString("Свободные переменные приравниваются нулю, а предпочительные (базисные)",80,260);
		 	g1.drawString("переменные равны свобоным членам.",50,280);
		}
		 if(step>=34)
		 {
		 	if(step==34)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",1,13));
		 	g1.drawString("Т.о. начальный опорный план имеет вид:",320,280);
		}
		 if(step>=35)
		 {
		 	if(step==35)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",1,13));
		 	OptimizationBasic.drawFormula(g1,"X_{0} = ( 0; 0; ... ; 0; b_{1}; b_{2}; ...; b_{m} ) ,    ( количество нулей: n) ,   z( X_{0}) = 0," ,50,300,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=36)
		 {
		 	if(step==36)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",1,13));
		 	g1.drawString("В задачах ЛП, где все ограничения являются неравенства типа \"\u2264\"",50,320);
		 	g1.drawString("(с неотрицательной правой частью) дополнительные переменные позволяют",50,340);
		 	g1.drawString("сформировать начальное допустимое базисное решение.",50,360);
		}
		 if(step>=37)
		 {
		 	if(step==37)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",1,13));
			g1.drawString("Возникают трудности с",450,360);
		 	g1.drawString("нахождением начального допустимого решения в задачах, где есть ограничения в",50,380);
		 	g1.drawString("виде равенств или неравенств типа \"\u2265\".",50,400);
		}
		 if(step>=38)
		 {
		 	if(step==38)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",2,13));
			g1.drawString("исскуственные",480,400);
			g1.drawString("переменные",50,420);
			g1.setFont(new Font("Dialog",1,13));
			g1.drawString("В этом случае вводят",325,400);
		 	g1.drawString(", которые играют роль дополнительных остаточных переменных в",130,420);
		 	g1.drawString("первой итерации, но на последующих итерациях от них освобождаются. Также",50,440);
		 	g1.drawString("они входят в целевую функцию с коэффициентом -M.",50,460);
		}
		 if(step>=39)
		 {
		 	if(step==39)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",2,13));
			g1.drawString("расширенной форме",65,480);
			g1.drawString("M-задаче.",350,480);
			g1.setFont(new Font("Dialog",1,13));
		 	g1.drawString("Исходная задача представлена",415,460);
		 	g1.drawString("в                                   , то есть приведена к                  .",50,480);
		}
	}

	if(step>=40 && step<=45)
	{
		if(step>=40)
		{
			g1.setColor(new Color(255,0,0));
			g1.setFont(new Font("Dialog",1,13));
			g1.drawString("Нахождение оптимального опорного плана.",50,100);
 		}
        if(step>=41)
		{
		 	if(step==41)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",1,13));
			g1.drawString("Пусть система ограничений имеет предпочтительный вид, т.е. найден начальный",50,120);
			g1.drawString("опорный план.",50,140);
			g1.setColor(new Color(255,0,0));
		}
        if(step>=42)
		{
		 	if(step==42)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
		 	OptimizationBasic.drawFormula(g1,"z = c_{1}x_{1} + c_{2}x_{2} + ... + c_{n}x_{n} \u2192 max",100,160,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	g1.drawString("система ограничений:",50,180);
		 	OptimizationBasic.drawFormula(g1,"a_{11}x_{1} + a_{12}x_{2} + ... + a_{1n}x_{n} + x_{n + 1}                        = b_{1},",100,200,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"a_{21}x_{1} + a_{22}x_{2} + ... + a_{2n}x_{n}             + x_{n + 2}            = b_{2},",100,220,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			g1.drawString(".............................................................................",100,240);
		 	OptimizationBasic.drawFormula(g1,"a_{m1}x_{1} + a_{m2}x_{2} + ... + a_{mn}x_{n}                     + x_{n + m} = b_{m},",100,260,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));

		 	g1.drawString("где",450,230);
		 	OptimizationBasic.drawFormula(g1,"x_{j} \u2265 0, j = 1...n + m",500,220,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"b_{i} \u2265 0, i = 1...m",500,240,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
        if(step>=43)
		{
		 	if(step==43)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
		 	g1.drawString("Применим к системе преобразование Жордана-Гаусса:",50,285);
		}
        if(step>=44)
		{
		 	if(step==44)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",0,40));
			g1.drawString("{",90,322);
			g1.drawString("{",90,362);

			OptimizationBasic.drawFormula(g1,"b'_{i j} = ",50,315,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{i} - a_{i k} ( b_{l r} / a_{l r}) ,  при i \u2260 l",110,305,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{l j} / a_{l r} ,                 при i = l",110,325,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			g1.drawString(" - новые свободные члены",300,315);

			OptimizationBasic.drawFormula(g1,"a'_{i j} = ",50,355,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{i j} - a_{i k} ( a_{l r} / a_{l r}) ,  при i \u2260 l",110,345,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{l j} / a_{l r} ,                   при i = l",110,365,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			g1.drawString(" - новые коэффициенты",300,355);

			g1.setFont(new Font("Dialog",1,13));
			g1.drawString("где  l - индекс выводимого из базиса вектора",100,385);
			g1.drawString("r - индекс вводимого в базис вектора",127,405);
		}
        if(step>=45)
		{
		 	if(step==45)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));

			OptimizationBasic.drawFormula(g1,"Таким образом мы из допустимой точки x = ( 0; ...; 0; b_{1}; b_{2}; ...; b_{m} ) получаем",50,425,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"новую точку x' = ( 0; ...; b_{l}; ...; 0; b'_{1}; b'_{2}; ...; 0; ...; b'_{m} )",50,445,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));

		}
	}

	if(step>=46 && step<=57)
	{
		if(step>=46)
		{
		 	if(step==46)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",1,13));
			OptimizationBasic.drawFormula(g1,"Как выбрать вектор для ввода в базис ( r ):",50,120,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			g1.setColor(new Color(255,0,0));
			g1.drawString("Нахождение оптимального опорного плана.",50,100);
		}
		 if(step>=47)
		 {
		 	if(step==47)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"<c,x'> = \u2211 c_{i} b'_{i} + c_{r} b'_{r} = \u2211 c_{i} (b_{i} - q a_{ir}) + c_{r} q = \u2211 c_{i} b_{i} - q \u2211 c_{i} a_{ir} + c_{r} q =",75,150,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));

			OptimizationBasic.drawFormula(g1,"n",132,140,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"n",236,140,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"n",378,140,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"n",443,140,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));

			OptimizationBasic.drawFormula(g1,"i = 1",127,160,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"i = 1",230,160,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"i = 1",373,160,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"i = 1",438,160,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));

			OptimizationBasic.drawFormula(g1,"= <c,x> + q (c_{r} - \u2211 c_{i} a_{ir}) ,   q = min ( b_{i} / a_{ir})",75,180,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"n",179,170,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"i = 1",173,190,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"i",282,190,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=48)
		 {
		 	if(step==48)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"c_{r} - \u2211 c_{i} a_{ir} \u2264 0   =>   c_{r} - z_{r} = \u2206 \u2264 0",75,210,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"n",100,200,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"i = 1",95,220,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=49)
		 {
		 	if(step==49)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"Таким образом в качестве вектора, вводимого в базис можно взять любой из",50,230,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"векторов у которого \u2206_{j} < 0.",50,250,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=50)
		 {
		 	if(step==50)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"Обычно выбирают максимальное по модулю отрицательное значение |\u2206_{r}| = max |\u2206_{r}|.",50,270,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206_{r} < 0",562,280,new java.awt.Font("Dialog",0,9),new java.awt.Font("Dialog",0,9));
		}

		 if(step>=51)
		 {
		 	if(step==51)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"Как выбрать вектор для вывода из базиса ( l ):",50,295,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=52)
		 {
		 	if(step==52)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"1. i = l ,  тогда b_{l} / a_{lr} \u2265 0 \u21d4 a_{lr} \u2265 0",50,315,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}

		 if(step>=53)
		 {
		 	if(step==53)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"2. i \u2260 l ,  тогда b_{i} - a_{ir} * ( b_{l} / a_{lr} ) \u2265 0 ,  при:",50,335,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}

		 if(step>=54)
		 {
		 	if(step==54)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"1) a_{ir} < 0 => b_{i} \u2265 0",100,355,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}

		 if(step>=55)
		 {
		 	if(step==55)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"2) a_{ir} \u2265 0 => b_{i} - a_{ir} * ( b_{l} / a_{lr} ) \u2265 0",100,375,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}

		 if(step>=56)
		 {
		 	if(step==56)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"b_{i} / a_{ir} \u2265 b_{l} / a_{lr} \u2265 0 => l выбираем так, что бы b_{l} / a_{lr} = min ( b_{i} / a_{ir}) = q \u2265 0",120,395,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=57)
		 {
		 	if(step==57)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"То есть, для определения вектора, подлежащего исключению из базиса, находят",50,415,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"min ( b_{i} / a_{ir}) ,  для всех a_{ir} > 0.",50,435,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
	}
	if(step>=58 && step<=73)
	{
        if(step>=58)
		{
		 	if(step==58)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",1,13));
			g1.drawString("Теорема (критерий оптимальности):",50,120);
			g1.setColor(new Color(255,0,0));
			g1.drawString("Нахождение оптимального опорного плана.",50,100);
		}
        if(step>=59)
		{
		 	if(step==59)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"Опорный план x = ( 0, 0, ...; 0; x_{1}; x_{2}; ...; x_{m}) задачи ЛП является оптимальным, если:",50,140,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206_{j} = с_{j} - z_{j} \u2265 0 , для \u2200 j = 1 ... n + m",50,160,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
        if(step>=60)
		{
		 	if(step==60)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.drawString("Доказательство:",50,180);
		}
        if(step>=61)
		{
		 	if(step==61)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"Рассмотрим любое допустимое решение y, такое что A y = b, тогда:",50,200,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
        if(step>=62)
		{
		 	if(step==62)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"<c,y> = \u2211 c_{j} y_{j} \u2265 \u2211 z_{j} y_{j} = \u2211 ( \u2211 c_{i} a_{i j} ) y_{j} = \u2211 c_{i} \u2211 a_{i j} y_{j} = <c,x>",50,220,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			g1.setFont( new Font("Dialog",1,9));
//up
			g1.drawString("n + m",95,210);
			g1.drawString("n + m",152,210);
			g1.drawString("n + m",206,210);
			g1.drawString("n",240,210);
			g1.drawString("n",323,210);
			g1.drawString("n + m",342,210);
//down
			g1.drawString("j = 1",97,229);
			g1.drawString("j = 1",155,229);
			g1.drawString("j = 1",210,229);
			g1.drawString("i = 1",233,229);
			g1.drawString("i = 1",317,229);
			g1.drawString("j = 1",347,229);
		}
        if(step>=63)
		{
		 	if(step==63)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"A_{1}...A_{n} - столбцы A, тогда \u2200 A_{j} = \u2211 a_{ij} A_{ i}",50,245,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			g1.setFont( new Font("Dialog",1,9));
			g1.drawString("n",266,235);
			g1.drawString("i = 1",262,255);
		}
        if(step>=64)
		{
		 	if(step==64)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"=>  b = A y = \u2211 A_{j} y_{j} = \u2211 ( \u2211 a_{ij} A_{ i} ) y_{j}",320,245,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			g1.setFont( new Font("Dialog",1,9));
			g1.drawString("n",407,235);
			g1.drawString("i = 1",401,255);
			g1.drawString("n",464,235);
			g1.drawString("i = 1",458,255);
			g1.drawString("n + m",478,235);
			g1.drawString("j = 1",481,255);
		}
        if(step>=65)
		{
		 	if(step==65)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));

			OptimizationBasic.drawFormula(g1,"но b = Ax = \u2211 A_{ i} x  =>  x = \u2211 a_{ij} y_{j}, что означает оптимальность решения x.",50,270,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			g1.setFont( new Font("Dialog",1,9));
			g1.drawString("n",130,260);
			g1.drawString("n + m",219,260);
			g1.drawString("i = 1",125,280);
			g1.drawString("j = 1",224,280);
			g1.setFont( new Font("Dialog",1,13));
		}
        if(step>=66)
		{
		 	if(step==66)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));

			g1.drawString("что и требовалось доказать.",420,290);

		}
        if(step>=67)
		{
		 	if(step==67)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.drawString("Теорема ( критерий неограниченности целевой функции ) :",50,310);
		}
        if(step>=68)
		{
		 	if(step==68)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"Если для некоторого опорного плана x = ( 0, 0, ...; 0; x_{1}; x_{2}; ...; x_{m}) существет хотя бы",50,330,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"одна величина \u2206_{j} = с_{j} - z_{j} \u2264 0 , ( j = 1...n + k ),  при a_{ir} < 0, ( i = 1 ... m ), то целевая функция",50,350,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"не оганичена на допустимом множестве.",50,370,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
        if(step>=69)
		{
		 	if(step==69)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.drawString("Доказательство:",50,390);
		}
        if(step>=70)
		{
		 	if(step==70)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"Пусть X = ( 0; ...; 0; x_{1}; x_{2}; ...; x_{n}) - опорный план,",50,410,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
        if(step>=71)
		{
		 	if(step==71)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"x' = ( 0; ...; q / n; ...; 0; x_{1} - q a_{1 n}; x_{2} - q a_{2 n}; ...; x_{m} - q a_{m n} ) - дополнительный план, тогда",50,430,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
        if(step>=72)
		{
		 	if(step==72)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"Ax' = \u2211 A_{ i} ( x_{i} - q a_{in}) + qA_{n} = \u2211 A_{ i} x_{0} - q \u2211 A_{ i} a e_{n} + q A_{n} = \u2211 A_{ i} x_{0} + q ( A_{n} - \u2211 A_{ i} a_{in}) =",50,455,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			g1.setFont(new Font("Dialog",1,9));

			g1.drawString("n",89,465);
			g1.drawString("n",243,465);
			g1.drawString("n",314,465);
			g1.drawString("n",433,465);
			g1.drawString("n",543,465);

			g1.drawString("i = 1",83,445);
			g1.drawString("i = 1",237,445);
			g1.drawString("i = 1",307,445);
			g1.drawString("i = 1",426,445);
			g1.drawString("i = 1",537,445);

			OptimizationBasic.drawFormula(g1,"= \u2211 A_{ i} x_{i} = b",50,480,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			g1.setFont(new Font("Dialog",1,9));
			g1.drawString("n",65,490);
			g1.drawString("i = 1",61,470);
			g1.setFont(new Font("Dialog",1,13));
		}
        if(step>=73)
		{
		 	if(step==73)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));

			g1.drawString("что и требовалось доказать.",420,480);
		}
	}


     if(step>=74 && step<=83)
     {
		 if(step>=74)
		 {
		 	if(step==74)
				g1.setColor(new Color(0,0,160));
			else
				g1.setColor(new Color(0,0,80));
// LINES
			g1.setColor(new Color(0,0,80));
		 	for(int i=155; i<=575;i=i+35) g1.drawLine(i,80,i,260);// |
		 	for(int j=120; j<=260;j=j+20) g1.drawLine(50,j,575,j);// --
		 	g1.drawLine(50,80,575,80);
		 	g1.drawLine(155,100,575,100);
			g1.drawLine(50,80,50,260);
			g1.drawLine(85,80,85,240);
			g1.drawLine(125,80,125,240);

			OptimizationBasic.drawFormula(g1,"c_{j}",550,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//x
			OptimizationBasic.drawFormula(g1,"x_{1}",165,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"x_{2}",200,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",235,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"x_{r}",270,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",305,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"x_{n}",340,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"x_{n + 1}",369,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{k}",445,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n+m}",510,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			//OptimizationBasic.drawFormula(g1,"x_{n+m}",546,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));

//			OptimizationBasic.drawFormula(g1,"БР / _{j}",550,334,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//коэф-ы
			OptimizationBasic.drawFormula(g1,"C_{i}",60,104,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));

//базисные переменные
			OptimizationBasic.drawFormula(g1,"БП",95,104,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));

			g1.drawString("БР",130,104);

			OptimizationBasic.drawFormula(g1,"\u2206_{j}",550,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"БР/a_{ir}",542,114,new java.awt.Font("Dialog",1,11),new java.awt.Font("Dialog",1,9));
		 }
		 if(step>=75)
		 {
		 	if(step==75)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
//c
			OptimizationBasic.drawFormula(g1,"c_{1}",165,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{2}",200,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",235,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{r}",270,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",305,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{n}",340,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{n + 1}",370,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"c_{k}",445,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"c_{n+m}",510,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));

			g1.setFont(new Font("Dialog",1,13));
			g1.drawString("Исходные данные занесем в таблицу.",50,275);
		}
		 if(step>=76)
		 {
		 	if(step==76)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",1,13));
			g1.drawString("Занесем в таблицу базисные перменные, и их коэффициенты и свободные члены.",50,295);
//коэф-ы

			OptimizationBasic.drawFormula(g1,"0",60,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",60,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",60,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));

//базисные переменные


			OptimizationBasic.drawFormula(g1,"x_{n + 1}",89,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + 2}",89,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",89,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + k}",89,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",89,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + m}",89,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//Свободные члены

			OptimizationBasic.drawFormula(g1,"b_{1}",130,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{2}",130,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",130,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{k}",130,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",130,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{m}",130,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=77)
		 {
		 	if(step==77)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",1,13));
			g1.drawString("Занесем в таблицу оставшиеся коэффициенты векторов.",50,315);
//a 1
			OptimizationBasic.drawFormula(g1,"a_{11}",165,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{21}",165,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",165,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{k1}",165,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",165,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{m1}",165,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//a 2
			OptimizationBasic.drawFormula(g1,"a_{12}",200,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{22}",200,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",200,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{k2}",200,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",200,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{m2}",200,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//...
			OptimizationBasic.drawFormula(g1,"...",235,134,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,154,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,234,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//a r
			OptimizationBasic.drawFormula(g1,"a_{1r}",270,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{2r}",270,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",270,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{kr}",270,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",270,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{mr}",270,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//...
			OptimizationBasic.drawFormula(g1,"...",305,134,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,154,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,234,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//a n
			OptimizationBasic.drawFormula(g1,"a_{1n}",340,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{2n}",340,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",340,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{kn}",340,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",340,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{mn}",340,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//a n+1
			OptimizationBasic.drawFormula(g1,"1",375,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",375,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",375,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",375,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",375,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",375,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//...
			OptimizationBasic.drawFormula(g1,"...",410,134,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,154,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,234,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//a k
			OptimizationBasic.drawFormula(g1,"0",445,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",445,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",445,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"1",445,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",445,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",445,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//...
			OptimizationBasic.drawFormula(g1,"...",480,134,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,154,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,234,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//a n+m
			OptimizationBasic.drawFormula(g1,"0",515,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",515,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",515,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",515,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",515,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"1",515,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=78)
		 {
		 	if(step==78)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"Вычислим \u2206_{j} = c_{j} - z_{j} ,  z_{j} = \u2211 c_{i} a_{ij},  ( j = 1...n + m)",50,335,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			g1.setFont(new Font("Dialog",1,9));
			g1.drawString("m",229,325);
			g1.drawString("i = 1",225,345);
// /\i
			OptimizationBasic.drawFormula(g1,"\u2206_{1}",165,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206_{2}",200,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206_{r}",270,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206_{n}",340,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",375,254,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",445,254,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",515,254,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=79)
		 {
		 	if(step==79)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",1,13));
			g1.drawString("Проверяем опорный план на оптимальность, если:",50,355);
		}
		 if(step>=80)
		 {
		 	if(step==80)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",1,13));
			OptimizationBasic.drawFormula(g1,"1) \u2206_{j} \u2265 0 , \u2200 j = 1...n + m, то план является оптимальным;",50,375,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=81)
		 {
		 	if(step==81)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",1,13));
			OptimizationBasic.drawFormula(g1,"2) \u2206_{j} < 0, при j = k, и \u2200 a_{ik} \u2264 0, то целевая функция не ограничена;",50,395,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=82)
		 {
		 	if(step==82)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",1,13));
			OptimizationBasic.drawFormula(g1,"3) \u2206_{j} < 0, при j = k, \u2203 хотя бы одно a_{ik} > 0, то можно перейти к новому опорному плану.",50,415,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=83)
		 {
		 	if(step==83)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",1,13));
			g1.drawString("Переход от одного опорного плана к другому осуществляется исключением из базиса",50,435);
			g1.drawString("одного из векторов и введением нового вектора преобразованием Жордана-Гаусса.",50,455);
			//g1.drawString("Таким образом из допустимой точки получаем новую точку x = (0;...;b; 0; b; ... b).",50,475);
			OptimizationBasic.drawFormula(g1,"Таким образом из допустимого плана получаем новый план x' = ( 0; ...; b'_{r}; 0; b'_{1}; ...; b'_{m})",50,475,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
	}

     if(step>=84 && step<=88)
     {
		 if(step>=84)
		 {
		 	if(step==84)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"В качестве вектора, вводимого в базис, можно взять любой из векторов, где \u2206_{r} < 0.",50,275,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//-------
			g1.setColor(new Color(200,200,255));
//			g1.fillRect(260, 80, 35, 180);//|
//			g1.fillRect(50, 180, 525, 20);//-

			g1.setColor(new Color(0,0,80));
		 	for(int i=155; i<=575;i=i+35) g1.drawLine(i,80,i,260);// |
		 	for(int j=120; j<=260;j=j+20) g1.drawLine(50,j,575,j);// --

		 	g1.drawLine(50,80,575,80);
		 	g1.drawLine(155,100,575,100);

			g1.drawLine(50,80,50,260);
			g1.drawLine(85,80,85,240);
			g1.drawLine(125,80,125,240);

//c
			OptimizationBasic.drawFormula(g1,"c_{1}",165,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{2}",200,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",235,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{r}",270,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",305,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{n}",340,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{n + 1}",370,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"c_{k}",445,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"c_{n+m}",510,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"c_{j}",550,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//x
			OptimizationBasic.drawFormula(g1,"x_{1}",165,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"x_{2}",200,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",235,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"x_{r}",270,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",305,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"x_{n}",340,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"x_{n + 1}",369,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{k}",445,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n+m}",510,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//коэф-ы
			OptimizationBasic.drawFormula(g1,"C_{i}",60,104,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",60,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",60,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//базисные переменные
			OptimizationBasic.drawFormula(g1,"БП",95,104,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + 1}",89,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + 2}",89,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",89,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + k}",89,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",89,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + m}",89,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//Свободные члены
			g1.drawString("БР",130,104);
			OptimizationBasic.drawFormula(g1,"b_{1}",130,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{2}",130,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",130,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{k}",130,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",130,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{m}",130,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206_{j}",550,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"БР/a_{ir}",542,114,new java.awt.Font("Dialog",1,11),new java.awt.Font("Dialog",1,9));
//c
			OptimizationBasic.drawFormula(g1,"c_{1}",165,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{2}",200,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",235,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{r}",270,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",305,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{n}",340,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{n + 1}",370,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"c_{k}",445,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"c_{n+m}",510,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//коэф-ы
			OptimizationBasic.drawFormula(g1,"0",60,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",60,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",60,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//базисные переменные
			OptimizationBasic.drawFormula(g1,"x_{n + 1}",89,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + 2}",89,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",89,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + k}",89,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",89,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + m}",89,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//Свободные члены
			OptimizationBasic.drawFormula(g1,"b_{1}",130,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{2}",130,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",130,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{k}",130,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",130,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{m}",130,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//a 1
			OptimizationBasic.drawFormula(g1,"a_{11}",165,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{21}",165,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",165,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{k1}",165,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",165,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{m1}",165,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//a 2
			OptimizationBasic.drawFormula(g1,"a_{12}",200,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{22}",200,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",200,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{k2}",200,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",200,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{m2}",200,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//...
			OptimizationBasic.drawFormula(g1,"...",235,134,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,154,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,234,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//a r
			OptimizationBasic.drawFormula(g1,"a_{1r}",270,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{2r}",270,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",270,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{kr}",270,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",270,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{mr}",270,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//...
			OptimizationBasic.drawFormula(g1,"...",305,134,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,154,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,234,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//a n
			OptimizationBasic.drawFormula(g1,"a_{1n}",340,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{2n}",340,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",340,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{kn}",340,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",340,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{mn}",340,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//a n+1
			OptimizationBasic.drawFormula(g1,"1",375,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",375,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",375,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",375,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",375,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",375,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//...
			OptimizationBasic.drawFormula(g1,"...",410,134,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,154,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,234,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//a k
			OptimizationBasic.drawFormula(g1,"0",445,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",445,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",445,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"1",445,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",445,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",445,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//...
			OptimizationBasic.drawFormula(g1,"...",480,134,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,154,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,234,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//a n+m
			OptimizationBasic.drawFormula(g1,"0",515,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",515,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",515,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",515,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",515,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"1",515,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
// /\i
			OptimizationBasic.drawFormula(g1,"\u2206_{1}",165,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206_{2}",200,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206_{r}",270,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206_{n}",340,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",375,254,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",445,254,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",515,254,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));

//b i
//			OptimizationBasic.drawFormula(g1,"b_{1}/a_{1r}",544,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//			OptimizationBasic.drawFormula(g1,"b_{2}/a_{2r}",544,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//			OptimizationBasic.drawFormula(g1,"...",550,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//			OptimizationBasic.drawFormula(g1,"b_{k}/a_{kr}",544,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//			OptimizationBasic.drawFormula(g1,"...",550,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//			OptimizationBasic.drawFormula(g1,"b_{m}/a_{mr}",543,234,new java.awt.Font("Dialog",1,11),new java.awt.Font("Dialog",1,9));

//-------
		}
		 if(step>=85)
		 {
		 	if(step==85)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"т.к. <c,x'> = \u2211 c_{i} b'_{i} + c_{r} b'_{r} = \u2211 c_{i} (b_{i} - q a_{ir}) + c_{r} q = \u2211 c_{i} b_{i} - q \u2211 c_{i} a_{ir} + c_{r} q =",50,295,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));

			OptimizationBasic.drawFormula(g1,"n",132,285,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"n",236,285,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"n",375,285,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"n",440,285,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));

			OptimizationBasic.drawFormula(g1,"i = 1",127,305,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"i = 1",230,305,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"i = 1",370,305,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"i = 1",435,305,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));

			OptimizationBasic.drawFormula(g1,"= <c,x> + q (c_{r} - \u2211 c_{i} a_{ir}) ,   q = min ( b_{i} / a_{ir})",50,315,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"n",154,305,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"i = 1",148,325,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"i",257,325,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=86)
		 {
		 	if(step==86)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"c_{r} - \u2211 c_{i} a_{ir} \u2264 0  =>  c_{r} - z_{r} \u2264 0",50,335,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"n",75,325,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"i = 1",70,345,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=87)
		 {
		 	if(step==87)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"Выберем максимальное по модулю отрицательное значение |\u2206_{r}| = max |\u2206_{r}|.",50,355,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206_{r} < 0",500,363,new java.awt.Font("Dialog",0,9),new java.awt.Font("Dialog",0,9));
		}
		 if(step>=88)
		 {
		 	if(step==88)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"Пусть \u2206_{r} - выбранное значение. Тогда столбец x_{r} будет ведущим. Переменную x_{r}",50,375,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"вводим в базис.",50,395,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));

// rect 1
			g1.setColor(new Color(200,200,255));
			g1.fillRect(261, 81, 34, 179);
			g1.setColor(new Color(0,0,80));
			for(int i=155; i<=575;i=i+35) g1.drawLine(i,80,i,260);// |
		 	for(int j=120; j<=260;j=j+20) g1.drawLine(50,j,575,j);// --
		 	g1.drawLine(50,80,575,80);
		 	g1.drawLine(155,100,575,100);
			g1.drawLine(50,80,50,260);
			g1.drawLine(85,80,85,240);
			g1.drawLine(125,80,125,240);
			OptimizationBasic.drawFormula(g1,"a_{1r}",270,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{2r}",270,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",270,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{kr}",270,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",270,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{mr}",270,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{r}",270,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"x_{r}",270,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206_{r}",270,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
	}

     if(step>=89 && step<=92)
     {
		 if(step>=89)
		 {
		 	if(step==89)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));

			g1.setFont(new Font("Dialog",1,13));

			OptimizationBasic.drawFormula(g1,"Для определения вектроа, подлежащего исключению из базиса, находят",50,275,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b' = БР / a_{ir} = min ( b_{i} / a_{ir})",50,295,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{ir} > 0",135,302,new java.awt.Font("Dialog",1,9),new java.awt.Font("Dialog",1,9));


//----------------------------------table
			g1.setColor(new Color(200,200,255));
			g1.fillRect(260, 80, 35, 180);//|
//			g1.fillRect(50, 180, 525, 20);//-

			g1.setColor(new Color(0,0,80));
		 	for(int i=155; i<=575;i=i+35) g1.drawLine(i,80,i,260);// |
		 	for(int j=120; j<=260;j=j+20) g1.drawLine(50,j,575,j);// --

		 	g1.drawLine(50,80,575,80);
		 	g1.drawLine(155,100,575,100);

			g1.drawLine(50,80,50,260);
			g1.drawLine(85,80,85,240);
			g1.drawLine(125,80,125,240);

//c
			OptimizationBasic.drawFormula(g1,"c_{1}",165,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{2}",200,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",235,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{r}",270,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",305,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{n}",340,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{n + 1}",370,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"c_{k}",445,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"c_{n+m}",510,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"c_{j}",550,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//x
			OptimizationBasic.drawFormula(g1,"x_{1}",165,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"x_{2}",200,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",235,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"x_{r}",270,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",305,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"x_{n}",340,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"x_{n + 1}",369,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{k}",445,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n+m}",510,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//коэф-ы
			OptimizationBasic.drawFormula(g1,"C_{i}",60,104,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",60,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",60,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//базисные переменные
			OptimizationBasic.drawFormula(g1,"БП",95,104,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + 1}",89,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + 2}",89,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",89,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + k}",89,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",89,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + m}",89,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//Свободные члены
			g1.drawString("БР",130,104);
			OptimizationBasic.drawFormula(g1,"b_{1}",130,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{2}",130,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",130,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{k}",130,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",130,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{m}",130,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206_{j}",550,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"БР/a_{ir}",542,114,new java.awt.Font("Dialog",1,11),new java.awt.Font("Dialog",1,9));
//c
			OptimizationBasic.drawFormula(g1,"c_{1}",165,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{2}",200,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",235,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{r}",270,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",305,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{n}",340,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{n + 1}",370,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"c_{k}",445,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"c_{n+m}",510,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//коэф-ы
			OptimizationBasic.drawFormula(g1,"0",60,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",60,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",60,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//базисные переменные
			OptimizationBasic.drawFormula(g1,"x_{n + 1}",89,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + 2}",89,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",89,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + k}",89,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",89,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + m}",89,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//Свободные члены
			OptimizationBasic.drawFormula(g1,"b_{1}",130,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{2}",130,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",130,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{k}",130,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",130,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{m}",130,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//a 1
			OptimizationBasic.drawFormula(g1,"a_{11}",165,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{21}",165,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",165,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{k1}",165,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",165,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{m1}",165,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//a 2
			OptimizationBasic.drawFormula(g1,"a_{12}",200,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{22}",200,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",200,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{k2}",200,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",200,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{m2}",200,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//...
			OptimizationBasic.drawFormula(g1,"...",235,134,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,154,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,234,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//a r
			OptimizationBasic.drawFormula(g1,"a_{1r}",270,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{2r}",270,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",270,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{kr}",270,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",270,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{mr}",270,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//...
			OptimizationBasic.drawFormula(g1,"...",305,134,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,154,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,234,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//a n
			OptimizationBasic.drawFormula(g1,"a_{1n}",340,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{2n}",340,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",340,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{kn}",340,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",340,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{mn}",340,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//a n+1
			OptimizationBasic.drawFormula(g1,"1",375,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",375,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",375,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",375,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",375,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",375,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//...
			OptimizationBasic.drawFormula(g1,"...",410,134,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,154,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,234,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//a k
			OptimizationBasic.drawFormula(g1,"0",445,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",445,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",445,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"1",445,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",445,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",445,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//...
			OptimizationBasic.drawFormula(g1,"...",480,134,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,154,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,234,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//a n+m
			OptimizationBasic.drawFormula(g1,"0",515,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",515,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",515,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",515,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",515,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"1",515,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
// /\i
			OptimizationBasic.drawFormula(g1,"\u2206_{1}",165,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206_{2}",200,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206_{r}",270,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206_{n}",340,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",375,254,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",445,254,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",515,254,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));

//----------------------------------
			}
/*
		 if(step>=76)
		 {
		 	if(step==76)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"1. i = l ,  тогда b_{l} / a_{lr} \u2265 0 \u21d4 a_{lr} \u2265 0",50,315,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}

		 if(step>=77)
		 {
		 	if(step==77)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"2. i \u2260 l ,  тогда b_{i} - a_{ir} * ( b_{l} / a_{lr} ) \u2265 0 ,  при:",50,335,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}

		 if(step>=78)
		 {
		 	if(step==78)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"1) a_{ir} < 0 => b_{i} \u2265 0",100,355,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}

		 if(step>=79)
		 {
		 	if(step==79)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"2) a_{ir} \u2265 0 => b_{i} - a_{ir} * ( b_{l} / a_{lr} ) \u2265 0",100,375,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}

		 if(step>=80)
		 {
		 	if(step==80)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"b_{i} / a_{ir} \u2265 b_{l} / a_{lr} \u2265 0 => l выбираем так, что бы b_{l} / a_{lr} = min ( b_{i} / a_{ir}) = q \u2265 0",120,395,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}

*/
		 if(step>=90)
		 {
		 	if(step==90)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));


			OptimizationBasic.drawFormula(g1,"Вычислим отношения БР/a_{ir}.",50,315,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));

// b' i

			OptimizationBasic.drawFormula(g1,"b_{1}/a_{1r}",544,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{2}/a_{2r}",544,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",550,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{k}/a_{kr}",544,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",550,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{m}/a_{mr}",543,234,new java.awt.Font("Dialog",1,11),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=91)
		 {
		 	if(step==91)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"Выберем ведущую строку min ( b_{1} / a_{1r}, ... , b_{m} / a_{mr}). Пусть отношение b_{k} / a_{kr}",50,335,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"минимальное. Тогда строка x_{n + k} - ведущая. Переменная x_{n + k} выводится из базиса.",50,355,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));

			g1.setColor(new Color(200,200,255));
//			g1.fillRect(260, 80, 35, 180);//|
			g1.fillRect(50, 180, 525, 20);//-

			g1.setColor(new Color(0,0,80));

		 	for(int i=155; i<=575;i=i+35) g1.drawLine(i,80,i,260);// |
		 	for(int j=120; j<=260;j=j+20) g1.drawLine(50,j,575,j);// --

		 	g1.drawLine(50,80,575,80);
		 	g1.drawLine(155,100,575,100);

			g1.drawLine(50,80,50,260);
			g1.drawLine(85,80,85,240);
			g1.drawLine(125,80,125,240);

			OptimizationBasic.drawFormula(g1,"0",60,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + k}",89,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{k}",130,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{k1}",165,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{k2}",200,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",270,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{kr}",270,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{kn}",340,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",375,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"1",445,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",515,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{k}/a_{kr}",544,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=92)
		 {
		 	if(step==92)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"Теперь находим новый опорный план и коэффициенты разложения векторов,",50,375,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"через векторы нового базиса, соответстввующего новому опорному плану.",50,395,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"Составляем новую симплекс-таблицу.",50,415,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
	}
//-------------------------2
/*
     if(step>=92 && step<=98)
     {
		 if(step>=83)
		 {
		 	if(step==83)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"Теперь находим новый опорный план и коэффициенты разложения векторов,",50,275,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"через векторы нового базиса, соответстввующего новому опорному плану.",50,295,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"Составляем новую симплекс-таблицу.",50,315,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));

			g1.setColor(new Color(200,200,255));
			g1.fillRect(260, 80, 35, 180);//|
			g1.fillRect(50, 180, 525, 20);//-

			g1.setColor(new Color(0,0,80));
		 	for(int i=155; i<=575;i=i+35) g1.drawLine(i,80,i,260);// |
		 	for(int j=120; j<=260;j=j+20) g1.drawLine(50,j,575,j);// --

		 	g1.drawLine(50,80,575,80);
		 	g1.drawLine(155,100,575,100);

			g1.drawLine(50,80,50,260);
			g1.drawLine(85,80,85,240);
			g1.drawLine(125,80,125,240);

//c
			OptimizationBasic.drawFormula(g1,"c_{1}",165,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{2}",200,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",235,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{r}",270,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",305,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{n}",340,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{n + 1}",370,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"c_{k}",445,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"c_{n+m}",510,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"c_{j}",550,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//x
			OptimizationBasic.drawFormula(g1,"x_{1}",165,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"x_{2}",200,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",235,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"x_{r}",270,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",305,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"x_{n}",340,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"x_{n + 1}",369,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{k}",445,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n+m}",510,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//коэф-ы
			OptimizationBasic.drawFormula(g1,"C_{i}",60,104,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",60,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",60,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//базисные переменные
			OptimizationBasic.drawFormula(g1,"БП",95,104,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + 1}",89,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + 2}",89,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",89,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + k}",89,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",89,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + m}",89,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//Свободные члены
			g1.drawString("БР",130,104);
			OptimizationBasic.drawFormula(g1,"b_{1}",130,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{2}",130,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",130,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{k}",130,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",130,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{m}",130,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206_{j}",550,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"БР/a_{ir}",542,114,new java.awt.Font("Dialog",1,11),new java.awt.Font("Dialog",1,9));
//c
			OptimizationBasic.drawFormula(g1,"c_{1}",165,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{2}",200,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",235,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{r}",270,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",305,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{n}",340,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{n + 1}",370,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"c_{k}",445,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"c_{n+m}",510,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//коэф-ы
			OptimizationBasic.drawFormula(g1,"0",60,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",60,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",60,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//базисные переменные
			OptimizationBasic.drawFormula(g1,"x_{n + 1}",89,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + 2}",89,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",89,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + k}",89,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",89,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + m}",89,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//Свободные члены
			OptimizationBasic.drawFormula(g1,"b_{1}",130,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{2}",130,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",130,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{k}",130,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",130,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{m}",130,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//a 1
			OptimizationBasic.drawFormula(g1,"a_{11}",165,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{21}",165,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",165,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{k1}",165,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",165,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{m1}",165,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//a 2
			OptimizationBasic.drawFormula(g1,"a_{12}",200,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{22}",200,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",200,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{k2}",200,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",200,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{m2}",200,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//...
			OptimizationBasic.drawFormula(g1,"...",235,134,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,154,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,234,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//a r
			OptimizationBasic.drawFormula(g1,"a_{1r}",270,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{2r}",270,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",270,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{kr}",270,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",270,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{mr}",270,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//...
			OptimizationBasic.drawFormula(g1,"...",305,134,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,154,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,234,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//a n
			OptimizationBasic.drawFormula(g1,"a_{1n}",340,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{2n}",340,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",340,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{kn}",340,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",340,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{mn}",340,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//a n+1
			OptimizationBasic.drawFormula(g1,"1",375,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",375,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",375,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",375,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",375,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",375,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//...
			OptimizationBasic.drawFormula(g1,"...",410,134,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,154,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,234,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//a k
			OptimizationBasic.drawFormula(g1,"0",445,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",445,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",445,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"1",445,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",445,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",445,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//...
			OptimizationBasic.drawFormula(g1,"...",480,134,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,154,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,234,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//a n+m
			OptimizationBasic.drawFormula(g1,"0",515,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",515,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",515,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",515,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",515,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"1",515,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
// /\i
			OptimizationBasic.drawFormula(g1,"\u2206_{1}",165,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206_{2}",200,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206_{r}",270,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206_{n}",340,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",375,254,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",445,254,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",515,254,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));

//b i
			OptimizationBasic.drawFormula(g1,"b_{1}/a_{1r}",544,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{2}/a_{2r}",544,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",550,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{k}/a_{kr}",544,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",550,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{m}/a_{mr}",543,234,new java.awt.Font("Dialog",1,11),new java.awt.Font("Dialog",1,9));
		}
	}
*/
     if(step>=93 && step<=100)
     {
		 if(step>=93)
		 {
		 	if(step==93)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
// LINES
			g1.setColor(new Color(0,0,80));
		 	for(int i=155; i<=575;i=i+35) g1.drawLine(i,80,i,260);// |
		 	for(int j=120; j<=260;j=j+20) g1.drawLine(50,j,575,j);// --

		 	g1.drawLine(50,80,575,80);
		 	g1.drawLine(155,100,575,100);

			g1.drawLine(50,80,50,260);
			g1.drawLine(85,80,85,240);
			g1.drawLine(125,80,125,240);

//c
			OptimizationBasic.drawFormula(g1,"c_{1}",165,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{2}",200,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",235,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{r}",270,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",305,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{n}",340,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"c_{n + 1}",370,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"c_{k}",445,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"c_{n+m}",510,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"c_{j}",550,94,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//x
			OptimizationBasic.drawFormula(g1,"x_{1}",165,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"x_{2}",200,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",235,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"x_{r}",270,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"...",305,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"x_{n}",340,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		 	OptimizationBasic.drawFormula(g1,"x_{n + 1}",369,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{k}",445,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n+m}",510,114,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//коэф-ы
			OptimizationBasic.drawFormula(g1,"C_{i}",60,104,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",60,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"c_{r}",60,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",60,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",60,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//базисные переменные
			OptimizationBasic.drawFormula(g1,"БП",95,104,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + 1}",89,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + 2}",89,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",89,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{r}",89,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",89,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"x_{n + m}",89,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//Свободные члены
			g1.drawString("БР",130,104);
		}
		 if(step>=94)
		 {
		 	if(step==94)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"b'_{1}",130,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b'_{2}",130,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",130,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b'_{r}",130,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",130,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b'_{m}",130,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//a 1
			OptimizationBasic.drawFormula(g1,"a'_{11}",165,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a'_{21}",165,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",165,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a'_{r1}",165,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",165,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a'_{m1}",165,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//a 2
			OptimizationBasic.drawFormula(g1,"a'_{12}",200,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a'_{22}",200,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",200,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a'_{r2}",200,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",200,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a'_{m2}",200,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//...
			OptimizationBasic.drawFormula(g1,"...",235,134,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,154,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,234,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//a r
			OptimizationBasic.drawFormula(g1,"a'_{1r}",270,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a'_{2r}",270,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",270,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a'_{rr}",270,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",270,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a'_{mr}",270,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//...
			OptimizationBasic.drawFormula(g1,"...",305,134,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,154,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,234,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//a n
			OptimizationBasic.drawFormula(g1,"a'_{1n}",340,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a'_{2n}",340,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",340,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a'_{rn}",340,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",340,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a'_{mn}",340,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//a n+1
			OptimizationBasic.drawFormula(g1,"1",375,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",375,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",375,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",375,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",375,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",375,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//...
			OptimizationBasic.drawFormula(g1,"...",410,134,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,154,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,234,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//a r
			OptimizationBasic.drawFormula(g1,"0",445,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",445,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",445,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"1",445,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",445,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",445,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
//...
			OptimizationBasic.drawFormula(g1,"...",480,134,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,154,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,194,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,234,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
//a n+m
			OptimizationBasic.drawFormula(g1,"0",515,134,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",515,154,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",515,174,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",515,194,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",515,214,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"1",515,234,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));

			g1.setFont(new Font("Dialog",0,40));
			g1.drawString("{",90,312);
			g1.drawString("{",90,352);
			OptimizationBasic.drawFormula(g1,"Коэффициенты ищутся методом Жордана - Гаусса.",50,275,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b'_{i j} = ",50,305,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{i} - a_{i k} ( b_{l r} / a_{l r}) ,  при i \u2260 l, l = n + k,",110,295,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"b_{l j} / a_{l r} ,                 при i = l, l = n + k,",110,315,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));

			OptimizationBasic.drawFormula(g1,"a'_{i j} = ",50,345,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{i j} - a_{i k} ( a_{l r} / a_{l r}) ,  при i \u2260 l, l = n + k,",110,335,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"a_{l j} / a_{l r} ,                   при i = l, l = n + k,",110,355,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
	}
//-------------------------2end
		 if(step>=95)
		 {
		 	if(step==95)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"Находим \u2206*_{j} = z'_{j} - c_{j}",50,375,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
// /\i
			OptimizationBasic.drawFormula(g1,"\u2206*_{1}",165,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206*_{2}",200,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",235,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206*_{r}",270,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",305,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206*_{n}",340,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",375,254,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",410,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",445,254,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"...",480,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"0",515,254,new java.awt.Font("Dialog",1,12),new java.awt.Font("Dialog",1,9));
			OptimizationBasic.drawFormula(g1,"\u2206_{j}",550,254,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=96)
		 {
		 	if(step==96)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"и вновь проверяем опорный план на оптимальность, если:",200,375,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=97)
		 {
		 	if(step==97)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"1) \u2206*_{j} \u2265 0 , i = 1...n + m, то план является оптимальным",50,395,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=98)
		 {
		 	if(step==98)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"2) \u2206*_{j} < 0, при j = k, a'_{ik} \u2264 0, то функция не ограничена",50,415,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=99)
		 {
		 	if(step==99)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			OptimizationBasic.drawFormula(g1,"3) \u2206*_{j} < 0, при j = k, хотя бы одно a'_{ik} > 0, то можно перейти к новому опорному плану.",50,435,new java.awt.Font("Dialog",1,13),new java.awt.Font("Dialog",1,9));
		}
		 if(step>=100)
		 {
		 	if(step==100)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.drawString("Строим симплекс-таблицы до тех пор, пока не найдем оптимальный план или",50,455);
			g1.drawString("не установим неразрешимость задачи.",50,475);
		}
	}

     if(step>=101 && step<=102)
     {
		 if(step>=101)
		 {
		 	if(step==101)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",1,13));
			g1.setColor(new Color(255,0,0));
			g1.drawString("Метод искусственного базиса:",50,100);
		}
		 if(step>=102)
		 {
		 	if(step==102)
				g1.setColor(new Color(0,0,255));
			else
				g1.setColor(new Color(0,0,80));
			g1.setFont(new Font("Dialog",1,13));
			g1.drawString("Если начальный опорный план задачи находится методом искусственного базиса,",50,120);
			g1.drawString("то сначала надо решить симплекс-методом вспомагательную M-задачу. При этом",50,140);
			g1.drawString("необходимо в начальную симплекс-таблицу включить M-строку, соответствующую",50,160);
			g1.drawString("целевой функции. Решается обычным симплес-методом.",50,180);
			g1.drawString("",50,140);
		}
	}

	if(step==103)
    {
       g1.setFont(new Font("Dialog",1,28));
      g1.setColor(new Color(130,0,60));
      g1.drawString("конец лекции",240,250);
      jbtnNext.setEnabled(false);
    }

}}

 void jbtnNext_actionPerformed(ActionEvent e) {
  if(step<=104)
   step++;
  repaint();
  }

 void jbtnNew_actionPerformed(ActionEvent e) {
  step=0;
  ok=0;
  jbtnNext.setEnabled(true);
  jChkbFormula.setVisible(false);
  jChkbFormula.setSelected(false);
  repaint();
  }

 void jbtnBack_actionPerformed(ActionEvent e) {
  if(step!=0)
   step--;
  jbtnNext.setEnabled(true);
  repaint();
  }

 void jChkbFormula_mousePressed(MouseEvent e) {
   if(jChkbFormula.isSelected()==true)
    ok=0;
   else
    ok=1;
   repaint();
  }

  private void Arrow( int p1, int q1, int p2, int q2, Graphics g1 ) {
    double a, ff = Math.PI/6.0;
    int x1, x2, y1, y2, x, y, m, n;
    a = Math.atan2( Math.abs( q2 - q1 ), Math.abs( p2 - p1 ) );
    if ( p1 < p2 ){
      if ( q1 < q2 ) { m = -1; n = -1; }
      else { m = -1; n = 1; }
    }
    else {
      if ( q1 < q2 ) { m = 1; n = -1; }
      else { m = 1; n = 1; }
    }
    x =(int) Math.round( p2 + m*4*Math.cos(a) );
    y = (int)Math.round( q2 + n*4*Math.sin(a) );
    x1 = (int)Math.round( x + m*5*Math.cos(a - ff) );
    y1 = (int)Math.round( y + n*5*Math.sin(a - ff) );
    x2 = (int)Math.round( x + m*5*Math.cos(a + ff) );
    y2 = (int)Math.round( y + n*5*Math.sin(a + ff) );
    g1.drawLine( x1, y1, p2, q2 );
    g1.drawLine( x2, y2, p2, q2 );
  }
}