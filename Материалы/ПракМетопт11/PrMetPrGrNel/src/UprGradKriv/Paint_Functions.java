package UprGradKriv;

import java.awt.*;




public class Paint_Functions extends UprGradKriv{

  public Paint_Functions() {
  }


public static void point(Graphics g,double x, double y,String str,Color cv)
  {
    g.setColor(cv);
    if((int)(m*x)-3+(x0+xGraph+aGraph/2)>xGraph+1&&(int)(m*x)+13+(x0+xGraph+aGraph/2)<xGraph+aGraph-1&&
       -(int)(m*y)-14+(-y0+yGraph+bGraph/2)>yGraph+1&&-(int)(m*y)+3+(-y0+yGraph+bGraph/2)<yGraph+bGraph-1)
     {g.fillOval((int)(m*x)-2+(x0+xGraph+aGraph/2),-(int)(m*y)-2+(-y0+yGraph+bGraph/2),4,4);
   Stringer.fonts[0] = new Font(textFont, styleTextFont, sizeTextFont-2);
   Stringer.fonts[1] = new Font(textFont, styleTextFont, sizeTextFont-6);
   Stringer.fonts[2] = new Font(textFont, styleTextFont, sizeTextFont-6);
   g.setFont(new java.awt.Font(textFont, styleTextFont, sizeTextFont-2));

   Stringer.drawString(""+str,(int)(m*x)+3+(x0+xGraph+aGraph/2),-(int)(m*y)-3+(-y0+yGraph+bGraph/2));
     }
  }



    public static void fillobl(Graphics g,Color cv)
      {
        int xi,yi,xx,yy,i; double xt,yt;
        xx=x0+xGraph+aGraph/2;
        yy=-y0+yGraph+bGraph/2;

        g.setColor(cv);



        xi=0; yi=0;
        for(xi=-aGraph/2-x0;xi<aGraph/2-x0;xi++)
         for(yi=-bGraph/2-y0;yi<bGraph/2-y0;yi++)
         {
           // Function:


           if (xi+xx>xGraph&&xi+xx<xGraph+aGraph&&-yi + yy>yGraph&&-yi + yy<yGraph+bGraph)
            {
              xt=(xi + xx-(x0+xGraph+aGraph/2))/(double)m; yt=(-(-yi + yy)+(-y0+yGraph+bGraph/2))/(double)m;
            //  xt=((double)((int)(xt*10)))/(double)10; yt=((double)((int)(yt*10)))/(double)10;
               if((Count_Functions.g1x(xt,yt)<0||g1==1)&&(Count_Functions.g2x(xt,yt)<0||g2==1)
                  &&(Count_Functions.g3x(xt,yt)<0||g3==1)&&(Count_Functions.g4x(xt,yt)<0||g4==1))
                 g.drawLine(xi + xx, -yi + yy, xi + xx, -yi + yy);

            }

         }



      }



//public static void line(Graphics g,int x1, int y1,int x2, int y2)
  // {
    //  g.drawLine(x1+(x0+xGraph+aGraph/2),-y1+(-y0+yGraph+bGraph/2),x2+(x0+xGraph+aGraph/2),-y2+(-y0+yGraph+bGraph/2));
   //}



public static void oXoYm(Graphics g,int m)
  {

   int xx=xGraph+aGraph/2,yy=yGraph+bGraph/2;
   int i,xi,yi;

   xx+=x0; yy-=y0;

   g.setColor(Color.black);
   Stringer.fonts[0] = new Font("SansSerif", 0, 11);
   Stringer.fonts[1] = new Font("SansSerif", 0, 7);
   Stringer.fonts[2] = new Font("SansSerif", 0, 7);



   if (yy>yGraph&&yy<yGraph+bGraph)
    {
      g.drawLine(xGraph + 1, yy, xGraph + aGraph - 1, yy);
      g.drawLine(xGraph + aGraph - 12, yy - 3, xGraph + aGraph - 1, yy);
      g.drawLine(xGraph + aGraph - 12, yy + 3, xGraph + aGraph - 1, yy);
      Stringer.drawString("x_{1}",xGraph + aGraph - 13, yy - 9);
      g.setFont(new java.awt.Font("SansSerif", 0, 11));
      for(xi=xx,i=0;xi<xGraph+aGraph-14;xi+=m,i++)
       {
         if (xi>xGraph&&xi<xGraph+aGraph)
          {
            g.drawLine(xi, yy - 2, xi, yy + 2);
            if (xi != xx&&i<10) g.drawString("" + i, xi - 3, yy + 14);
            if (xi != xx&&i>=10) g.drawString("" + i, xi - 6, yy + 14);
          }
       }
      for(xi=xx,i=0;xi>xGraph+14;xi-=m,i--)
       {
         if (xi>xGraph&&xi<xGraph+aGraph)
          {
            g.drawLine(xi, yy - 2, xi, yy + 2);
            if (xi != xx&&i>-10) g.drawString("" + i, xi - 4, yy + 14);
            if (xi != xx&&i<=-10) g.drawString("" + i, xi - 7, yy + 14);
          }
       }

    }
   if (xx>xGraph&&xx<xGraph+aGraph)
    {
      g.drawLine(xx, yGraph + 1, xx, yGraph + bGraph - 1);
      g.drawLine(xx, yGraph + 1, xx - 3, yGraph + 13);
      g.drawLine(xx, yGraph + 1, xx + 3, yGraph + 13);
      Stringer.drawString("x_{2}",xx + 7, yGraph + 13);
      g.setFont(new java.awt.Font("SansSerif", 0, 11));

      for(yi=yy,i=0;yi<yGraph+bGraph-14;yi+=m,i--)
       {
         if (yi>yGraph&&yi<yGraph+bGraph)
          {
            g.drawLine(xx - 2, yi, xx + 2, yi);
            if (yi != yy&&i>-10) g.drawString("" + i, xx - 14, yi + 5);
            if (yi != yy&&i<=-10) g.drawString("" + i, xx - 21, yi + 5);
          }
       }
      for(yi=yy,i=0;yi>yGraph+14;yi-=m,i++)
       {
         if (yi>yGraph&&yi<yGraph+bGraph)
          {
            g.drawLine(xx - 2, yi, xx + 2, yi);
            if (yi != yy&&i<10) g.drawString("" + i, xx - 10, yi + 5);
            if (yi != yy&&i>=10) g.drawString("" + i, xx - 17, yi + 5);
          }
       }

    }

   if(xx>xGraph&&xx<xGraph+aGraph&&yy>yGraph&&yy<yGraph+bGraph)
    g.drawString("0",xx-8,yy+12);

  }

public static void Elipse(Graphics g,int Ck)
  {
    int xi,yi,xx,yy,i,h=m; Cx*=m; Cy*=m;
    xx=x0+xGraph+aGraph/2;
    yy=-y0+yGraph+bGraph/2;
    yi=0;

    if(Ck==0) { h=m; Ck=1; m=1;}
    g.setColor(cvGraph);

    for(xi=-aGraph/2-x0;xi<aGraph/2-x0;xi++)
     {
       // Function:
     if((2*A*K*(xi-Cx)+2*B*(M*xi-Cy))*(2*A*K*(xi-Cx)+2*B*(M*xi-Cy))-4*(A*K*K+B)*(A*(xi-Cx)*(xi-Cx)-Ck+B*(M*xi-Cy)*(M*xi-Cy))>=0)
     yi=(-(2*A*K*(xi-Cx)+2*B*(M*xi-Cy))+(int)(Math.sqrt((2*A*K*(xi-Cx)+2*B*(M*xi-Cy))*(2*A*K*(xi-Cx)+2*B*(M*xi-Cy))-4*(A*K*K+B)*(A*(xi-Cx)*(xi-Cx)-Ck+B*(M*xi-Cy)*(M*xi-Cy)))))/(2*(A*K*K+B));

       if (xi+xx>xGraph&&xi+xx<xGraph+aGraph&&-yi + yy>yGraph&&-yi + yy<yGraph+bGraph&&
           (2*A*K*(xi-Cx)+2*B*(M*xi-Cy))*(2*A*K*(xi-Cx)+2*B*(M*xi-Cy))-4*(A*K*K+B)*(A*(xi-Cx)*(xi-Cx)-Ck+B*(M*xi-Cy)*(M*xi-Cy))>=0)
        {
          g.drawLine(xi + xx, -yi + yy, xi + xx, -yi + yy);
          if(m==1) {g.fillOval(xi + xx-1,-yi + yy-1,2,2);}
        }

     }

     yi=0;
     for(xi=-aGraph/2-x0;xi<aGraph/2-x0;xi++)
      {
        // Function:
      if((2*A*K*(xi-Cx)+2*B*(M*xi-Cy))*(2*A*K*(xi-Cx)+2*B*(M*xi-Cy))-4*(A*K*K+B)*(A*(xi-Cx)*(xi-Cx)-Ck+B*(M*xi-Cy)*(M*xi-Cy))>=0)
      yi=(-(2*A*K*(xi-Cx)+2*B*(M*xi-Cy))-(int)(Math.sqrt((2*A*K*(xi-Cx)+2*B*(M*xi-Cy))*(2*A*K*(xi-Cx)+2*B*(M*xi-Cy))-4*(A*K*K+B)*(A*(xi-Cx)*(xi-Cx)-Ck+B*(M*xi-Cy)*(M*xi-Cy)))))/(2*(A*K*K+B));

        if (xi+xx>xGraph&&xi+xx<xGraph+aGraph&&-yi + yy>yGraph&&-yi + yy<yGraph+bGraph&&
            (2*A*K*(xi-Cx)+2*B*(M*xi-Cy))*(2*A*K*(xi-Cx)+2*B*(M*xi-Cy))-4*(A*K*K+B)*(A*(xi-Cx)*(xi-Cx)-Ck+B*(M*xi-Cy)*(M*xi-Cy))>=0)
         {
           g.drawLine(xi + xx, -yi + yy, xi + xx, -yi + yy);
           if(m==1) {g.fillOval(xi + xx-1,-yi + yy-1,2,2);}
         }

      }

      xi=0; int A1=A,B1=B,Cx1=Cx,Cy1=Cy,K1=K,M1=M;
      A=B1; B=A1; Cx=Cy1; Cy=Cx1; K=M1; M=K1;
      for(yi=-bGraph/2-y0;yi<bGraph/2-y0;yi++)
       {
         // Function:
       if((2*A*K*(yi-Cx)+2*B*(M*yi-Cy))*(2*A*K*(yi-Cx)+2*B*(M*yi-Cy))-4*(A*K*K+B)*(A*(yi-Cx)*(yi-Cx)-Ck+B*(M*yi-Cy)*(M*yi-Cy))>=0)
       xi=(-(2*A*K*(yi-Cx)+2*B*(M*yi-Cy))-(int)(Math.sqrt((2*A*K*(yi-Cx)+2*B*(M*yi-Cy))*(2*A*K*(yi-Cx)+2*B*(M*yi-Cy))-4*(A*K*K+B)*(A*(yi-Cx)*(yi-Cx)-Ck+B*(M*yi-Cy)*(M*yi-Cy)))))/(2*(A*K*K+B));

         if (xi+xx>xGraph&&xi+xx<xGraph+aGraph&&-yi + yy>yGraph&&-yi + yy<yGraph+bGraph&&
             (2*A*K*(yi-Cx)+2*B*(M*yi-Cy))*(2*A*K*(yi-Cx)+2*B*(M*yi-Cy))-4*(A*K*K+B)*(A*(yi-Cx)*(yi-Cx)-Ck+B*(M*yi-Cy)*(M*yi-Cy))>=0)
          {
            g.drawLine(xi + xx, -yi + yy, xi + xx, -yi + yy);
            if(m==1) {g.fillOval(xi + xx-1,-yi + yy-1,2,2);}
          }

       }
       xi=0;
       for(yi=-bGraph/2-y0;yi<bGraph/2-y0;yi++)
        {
          // Function:
        if((2*A*K*(yi-Cx)+2*B*(M*yi-Cy))*(2*A*K*(yi-Cx)+2*B*(M*yi-Cy))-4*(A*K*K+B)*(A*(yi-Cx)*(yi-Cx)-Ck+B*(M*yi-Cy)*(M*yi-Cy))>=0)
        xi=(-(2*A*K*(yi-Cx)+2*B*(M*yi-Cy))+(int)(Math.sqrt((2*A*K*(yi-Cx)+2*B*(M*yi-Cy))*(2*A*K*(yi-Cx)+2*B*(M*yi-Cy))-4*(A*K*K+B)*(A*(yi-Cx)*(yi-Cx)-Ck+B*(M*yi-Cy)*(M*yi-Cy)))))/(2*(A*K*K+B));

          if (xi+xx>xGraph&&xi+xx<xGraph+aGraph&&-yi + yy>yGraph&&-yi + yy<yGraph+bGraph&&
              (2*A*K*(yi-Cx)+2*B*(M*yi-Cy))*(2*A*K*(yi-Cx)+2*B*(M*yi-Cy))-4*(A*K*K+B)*(A*(yi-Cx)*(yi-Cx)-Ck+B*(M*yi-Cy)*(M*yi-Cy))>=0)
           {
             g.drawLine(xi + xx, -yi + yy, xi + xx, -yi + yy);
             if(m==1) {g.fillOval(xi + xx-1,-yi + yy-1,2,2);}
           }
        }
     A=A1; B=B1; Cx=Cx1; Cy=Cy1; K=K1; M=M1;
     m=h; Cx/=m; Cy/=m;
  }

  public static void parab(Graphics g,double at,double bt,double ct,int p,int shtr,Color cv)
    {
      int xi,yi,xx,yy,i;
      xx=x0+xGraph+aGraph/2;
      yy=-y0+yGraph+bGraph/2;

      g.setColor(cv);

      if(p==1)
      {
        int sx=0; if(at>0) sx=5; if(at<0) sx=-5;
      yi=0;
      for(xi=-aGraph/2-x0;xi<aGraph/2-x0;xi++)
       {
         // Function: x1=a(x2-c)^2+b
       if((xi-bt)/at>=0)
       yi=(int)(Math.sqrt((xi-bt)/at)+ct);

         if (xi+xx>xGraph&&xi+xx<xGraph+aGraph&&-yi + yy>yGraph&&-yi + yy<yGraph+bGraph&&
             (xi-bt)/at>=0)
          {
            g.drawLine(xi + xx, -yi + yy, xi + xx, -yi + yy);
            if((xi + xx)%5==0&&shtr==1)
              g.drawLine(xi + xx, -yi + yy, xi + xx, -yi + yy+sx);

          }

       }

       yi=0;
       for(xi=-aGraph/2-x0;xi<aGraph/2-x0;xi++)
        {
          // Function: x1=a(x2-c)^2+b
        if((xi-bt)/at>=0)
        yi=(int)(-Math.sqrt((xi-bt)/at)+ct);

          if (xi+xx>xGraph&&xi+xx<xGraph+aGraph&&-yi + yy>yGraph&&-yi + yy<yGraph+bGraph&&
              (xi-bt)/at>=0)
           {
             g.drawLine(xi + xx, -yi + yy, xi + xx, -yi + yy);
             if((xi + xx)%5==0&&shtr==1)
               g.drawLine(xi + xx, -yi + yy, xi + xx, -yi + yy-sx);

           }

        }

        xi=0;
        for(yi=-bGraph/2-y0;yi<bGraph/2-y0;yi++)
         {
           // Function:
         xi=(int)(at*(yi-ct)*(yi-ct)+bt);

           if (xi+xx>xGraph&&xi+xx<xGraph+aGraph&&-yi + yy>yGraph&&-yi + yy<yGraph+bGraph)
            {
              g.drawLine(xi + xx, -yi + yy, xi + xx, -yi + yy);

            }

         }

      }

      if(p==2)
      {
        xi=0; int sx=0; if(at>0) sx=5; if(at<0) sx=-5;
        for(yi=-bGraph/2-y0;yi<bGraph/2-y0;yi++)
         {
           // Function: x1=a(x2-c)^2+b
         if((yi-bt)/at>=0)
         xi=(int)(Math.sqrt((yi-bt)/at)+ct);

           if (xi+xx>xGraph&&xi+xx<xGraph+aGraph&&-yi + yy>yGraph&&-yi + yy<yGraph+bGraph&&
               (yi-bt)/at>=0)
            {
              g.drawLine(xi + xx, -yi + yy, xi + xx, -yi + yy);
              if((yi + yy)%5==0&&shtr==1)
              g.drawLine(xi + xx, -yi + yy, xi + xx-sx, -yi + yy);

            }

         }

         xi=0;
         for(yi=-bGraph/2-y0;yi<bGraph/2-y0;yi++)
          {
            // Function: x1=a(x2-c)^2+b
          if((yi-bt)/at>=0)
          xi=(int)(-Math.sqrt((yi-bt)/at)+ct);

            if (xi+xx>xGraph&&xi+xx<xGraph+aGraph&&-yi + yy>yGraph&&-yi + yy<yGraph+bGraph&&
                (yi-bt)/at>=0)
             {
               g.drawLine(xi + xx, -yi + yy, xi + xx, -yi + yy);
               if((yi + yy)%5==0&&shtr==1)
               g.drawLine(xi + xx, -yi + yy, xi + xx+sx, -yi + yy);

             }

          }

          yi=0;
          for(xi=-aGraph/2-x0;xi<aGraph/2-x0;xi++)
           {
             // Function:
           yi=(int)(at*(xi-ct)*(xi-ct)+bt);

             if (xi+xx>xGraph&&xi+xx<xGraph+aGraph&&-yi + yy>yGraph&&-yi + yy<yGraph+bGraph)
              {
                g.drawLine(xi + xx, -yi + yy, xi + xx, -yi + yy);

              }

           }

      }

    }


    public static void linex(Graphics g,double at,double bt,double ct,int shtr,Color cv)
      {
        int xi,yi,xx,yy,i;
        xx=x0+xGraph+aGraph/2;
        yy=-y0+yGraph+bGraph/2;

        g.setColor(cv);


        int sx=0; if(at>0) sx=5; if(at<0) sx=-5;
        yi=0;
        for(xi=-aGraph/2-x0;xi<aGraph/2-x0;xi++)
         {
           // Function:

         yi=(int)(at*(xi-ct)+bt);

           if (xi+xx>xGraph&&xi+xx<xGraph+aGraph&&-yi + yy>yGraph&&-yi + yy<yGraph+bGraph)
            {
              g.drawLine(xi + xx, -yi + yy, xi + xx, -yi + yy);
            //  if((xi + xx)%5==0&&shtr==1)
            //    g.drawLine(xi + xx, -yi + yy, xi + xx, -yi + yy+sx);

            }

         }

         xi=0;
         for(yi=-bGraph/2-y0;yi<bGraph/2-y0;yi++)
          {
            // Function:

          xi=(int)(ct+(yi-bt)/at);

            if (xi+xx>xGraph&&xi+xx<xGraph+aGraph&&-yi + yy>yGraph&&-yi + yy<yGraph+bGraph)
             {
               g.drawLine(xi + xx, -yi + yy, xi + xx, -yi + yy);
               if((yi + yy)%5==0&&shtr==1)
                 g.drawLine(xi + xx, -yi + yy, xi + xx-sx, -yi + yy);

             }

          }






      }


public static void Strelka(Graphics g,int SM, int SM2)
  {
              g.setColor(Color.red);
              g.drawLine(346+SM,101+SM2,365+SM,120+SM2);
              g.drawLine(346+SM,102+SM2,365+SM,121+SM2);
              ///-----1------
              g.drawLine(356+SM,121+SM2,365+SM,121+SM2);
              g.drawLine(358+SM,120+SM2,365+SM,120+SM2);
              g.drawLine(360+SM,119+SM2,365+SM,119+SM2);
              ///-----2------
              g.drawLine(365+SM,111+SM2,365+SM,121+SM2);
              g.drawLine(364+SM,113+SM2,364+SM,121+SM2);
              g.drawLine(363+SM,116+SM2,363+SM,121+SM2);
  }


  public static void line(Graphics g,double x1,double y1,double x2,double y2,Color cv)
    {
      int xi,yi,xx,yy,i;
      xx=x0+xGraph+aGraph/2;
      yy=-y0+yGraph+bGraph/2;

      g.setColor(cv);


      yi=0;



      for(xi=-aGraph/2-x0;xi<aGraph/2-x0;xi++)
       {
         // Function:



       yi=(int)(y1-(x1-xi)*(y1-y2)/(x1-x2));

         if (xi+xx>xGraph&&xi+xx<xGraph+aGraph&&-yi + yy>yGraph&&-yi + yy<yGraph+bGraph&&
             ((x1<x2&&xi>x1&&xi<x2)||(x1>x2&&xi>x2&&xi<x1)))
          {
            g.drawLine(xi + xx, -yi + yy, xi + xx, -yi + yy);

          }

       }

       xi=0;
       for(yi=-bGraph/2-y0;yi<bGraph/2-y0;yi++)
        {
          // Function:

        xi=(int)(x1-(y1-yi)*(x1-x2)/(y1-y2));

        if (xi+xx>xGraph&&xi+xx<xGraph+aGraph&&-yi + yy>yGraph&&-yi + yy<yGraph+bGraph&&
             ((y1<y2&&yi>y1&&yi<y2)||(y1>y2&&yi>y2&&yi<y1)))
           {
             g.drawLine(xi + xx, -yi + yy, xi + xx, -yi + yy);

           }

        }






    }


}
