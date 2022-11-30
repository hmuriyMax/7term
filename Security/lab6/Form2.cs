using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace lab6
{
    public partial class Form2 : Form
    {
        public Form1 parent;
        public string message;
        public Form2()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            parent.Close();
            parent.Dispose();
            button1.Enabled = false;
            Close();
        }

        private void Form2_Load(object sender, EventArgs e)
        {
            label1.Text = message;
        }
    }
}
