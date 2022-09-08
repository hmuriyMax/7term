//package MetZoitLec;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class OptimizationBasic{

	public static Color color_font = new Color(247,  247,  255);
	public static Color color1 = new Color(220,  230,  255);
	public static Color color2 = new Color(0,  0,  60);
	public static Font font1 = new Font("Dialog",  1,  12);

	public static void setColor(JScrollBar obj){
		obj.setFont(font1);
		obj.setBackground(color_font);
		}

	public static void setColor(Container obj){
		obj.setBackground(color_font);
		}

	public static void setColor(JRadioButton obj){
		obj.setFont(font1);
		obj.setBackground(color_font);
		}

	public static void setColor(JComboBox obj){
		obj.setBackground(color1);
		obj.setForeground(color2);
		}


	public static void setColor(JButton obj){
		obj.setBackground(color1);
		obj.setForeground(color2);
		obj.setFont(font1);
		}

	public static void setColor(JPanel obj){
		obj.setBorder(BorderFactory.createLineBorder(new Color(157,  187,  255)));
		obj.setBackground(Color.white);
		obj.setForeground(color2);
		obj.setFont(font1);
		}

	public static void setColor(JLabel obj,  String s){
		int k = 0; if (s.equals("title")) k = 1;
		if (s.equals("show_info")) k = 2;
		switch (k){
			case 1:
				obj.setVerticalAlignment(SwingConstants.TOP);
				obj.setHorizontalAlignment(SwingConstants.CENTER);
				obj.setFont(new java.awt.Font("Dialog",  1,  16));
				obj.setForeground(new Color(130,  0,  80));
				break;
			case 2:
				obj.setVerticalAlignment(SwingConstants.TOP);
				obj.setFont(new java.awt.Font("Dialog",  3,  12));
				obj.setForeground(new Color(180,  0,  80));
				break;
				default:
					obj.setVerticalAlignment(SwingConstants.TOP);
				obj.setFont(font1);
				obj.setForeground(new Color(130,  0,  80));
				break;
			}
		}

	public static void setColor(JLabel obj){
		obj.setVerticalAlignment(SwingConstants.TOP);
		obj.setFont(new java.awt.Font("Dialog",  1,  12));
		obj.setForeground(new Color(0,  0,  80));
		obj.setBorder(BorderFactory.createLineBorder(new Color(157,  187,  255)));
		obj.setBackground(Color.white);
	}


	public static double round(double x, int k){return Math.round(x*Math.pow(10, k))/Math.pow(10, k); }
	public static double round(double x){return Math.round(x*Math.pow(10, 3))/Math.pow(10, 3); }

	public static void drawFormula(Graphics g, String s, int x, int y){
		Font font1 = new Font("Dialog",  1,  12);
		Font font2 = new Font("Dialog",  1,  10);
		showDraw(g, s, x, y, font1, font2);
		}
	public static void drawFormula(Graphics g, String s, int x, int y, Font font1, Font font2){
		showDraw(g, s, x, y, font1, font2);
		}
	public static void showDraw(Graphics g, String s, int x, int y, Font font1, Font font2){
		s = s + " "; //s = changeFormula(s);
		Font font = g.getFont();
		g.setFont(font1);
		FontMetrics fm  =  g.getFontMetrics();
 		int x_tek = x, i = 0, length = s.length();
		char ch[] = s.toCharArray();
		String sf[] = {"^{", "_{", "|{", "/{", "sum{"};
		int sf_position[] = new int[10];
		int pos_temp[] = new int[5];
		String s_temp[] = new String[5];
		int k = 0, count = 0; //, s_end;
		int temp1 = 0, temp2 = 0;
 		while(i < length&&count < 1000){
			g.setFont(font1); count++;
			for (int ii = 0; ii < sf.length; ii++)sf_position[ii] = s.indexOf(sf[ii], i);
			k = 0;
			for (int ii = 1; ii < sf.length; ii++)
				if ((sf_position[ii] < sf_position[k]&&sf_position[ii]!= -1)||sf_position[k] == -1) k = ii;
			if (sf_position[k] == -1) k = -1;
			switch (k){
				case 0:
					g.drawString(s.substring(i, sf_position[k]), x_tek, y);
					fm  =  g.getFontMetrics();
					x_tek = x_tek + fm.stringWidth(s.substring(i, sf_position[k]));
					pos_temp[0] = s.indexOf("}", i);
					s_temp[0] = s.substring(sf_position[k] + sf[k].length(), pos_temp[0]);
					temp1 = fm.getAscent();
					g.setFont(font2);
					g.drawString(s_temp[0], x_tek, y-temp1 + 8);
					x_tek = x_tek + fm.stringWidth(s_temp[0]);
					i = pos_temp[0] + 1;
					break;
				case 1:
					g.drawString(s.substring(i, sf_position[k]), x_tek, y);
					fm  =  g.getFontMetrics();
					x_tek = x_tek + fm.stringWidth(s.substring(i, sf_position[k]));
					pos_temp[0] = s.indexOf("}", i);
					s_temp[0] = s.substring(sf_position[k] + sf[k].length(), pos_temp[0]);
					g.setFont(font2);
					fm  =  g.getFontMetrics();
					g.drawString(s_temp[0], x_tek, y + 5);
					x_tek = x_tek + fm.stringWidth(s_temp[0]);
					i = pos_temp[0] + 1;
					break;
				case 2:
					g.drawString(s.substring(i, sf_position[k]), x_tek, y);
					fm  =  g.getFontMetrics();
					x_tek = x_tek + fm.stringWidth(s.substring(i, sf_position[k]));
					pos_temp[0] = s.indexOf(", ", i);
					pos_temp[1] = s.indexOf("}", i);
					s_temp[0] = s.substring(sf_position[k] + sf[k].length(), pos_temp[0]);
					s_temp[1] = s.substring(pos_temp[0] + 1, pos_temp[1]);
					temp1 = fm.getAscent();
					g.setFont(font2);
					fm  =  g.getFontMetrics();
					g.drawString(s_temp[0], x_tek, y-temp1 + 8);
					g.drawString(s_temp[1], x_tek, y + 5);
					x_tek = x_tek + Math.max(fm.stringWidth(s_temp[0]), fm.stringWidth(s_temp[1]));
					i = pos_temp[1] + 1;
					break;
				case 3:
					g.drawString(s.substring(i, sf_position[k]), x_tek, y);
					fm  =  g.getFontMetrics();
					x_tek = x_tek + fm.stringWidth(s.substring(i, sf_position[k]));
					pos_temp[0] = s.indexOf(", ", i);
					pos_temp[1] = s.indexOf("}", i);
					s_temp[0] = s.substring(sf_position[k] + sf[k].length(), pos_temp[0]);
					s_temp[1] = s.substring(pos_temp[0] + 1, pos_temp[1]);
					int s1_l = fm.stringWidth(s_temp[0]);
					int s2_l = fm.stringWidth(s_temp[1]);
					int max_l = (int)Math.max(s1_l, s2_l);
					x_tek = x_tek + 2;
					temp1 = fm.getAscent()/2;
					g.drawString(s_temp[0], x_tek + (int)Math.max(0, (max_l-s1_l)/2), y-temp1);
					g.drawString(s_temp[1], x_tek + (int)Math.max(0, (max_l-s2_l)/2), y + temp1 + 2);
					g.drawLine(x_tek, y-temp1 + 3, x_tek + max_l, y-temp1 + 3);
					i = pos_temp[1] + 1;
					x_tek = x_tek + max_l;
					break;
				case 4:
					g.drawString(s.substring(i, sf_position[k]), x_tek, y);
					x_tek = x_tek + fm.stringWidth(s.substring(i, sf_position[k]));
					pos_temp[0] = s.indexOf(", ", i);
					pos_temp[1] = s.indexOf("}", i);
					s_temp[0] = s.substring(sf_position[k] + sf[k].length(), pos_temp[0]);
					s_temp[1] = s.substring(pos_temp[0] + 1, pos_temp[1]);
					g.drawString("\u2211", x_tek, y);
					fm  =  g.getFontMetrics();
					int max_length = fm.charWidth('\u2211');
					temp1 = (int)(fm.getAscent()*0.8);
					g.setFont(font2);
					fm  =  g.getFontMetrics();
					temp2 = (int)(fm.getAscent()*0.8);
					g.drawString(s_temp[0], x_tek, y + temp2);
					g.drawString(s_temp[1], x_tek, y-temp1);
					max_length = Math.max(max_length, fm.stringWidth(s_temp[0]));
					max_length = Math.max(max_length, fm.stringWidth(s_temp[1]));
					i = pos_temp[1] + 1;
					x_tek = x_tek + max_length + 2;
					break;
				default:g.drawString(s.substring(i, length), x_tek, y); i = length; break;
				}
 			}
		}
	}
