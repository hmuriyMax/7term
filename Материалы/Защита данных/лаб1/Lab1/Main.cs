using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Lab1
{
    public partial class Main : Form
    {
        public const string path = ".\\users";
        public const string admin = "Admin";
        public static user_list users = new user_list();
        public static string current_user;

        public Main()
        {
            if (System.IO.File.Exists(path))
            {
                users.Read_from_file(path);
                Login login = new Login();
                login.ShowDialog();
            }
            else
            {
                current_user = admin;
                users.Add_new_user(current_user);
                PassChange passChange = new PassChange(current_user, 'f');
                passChange.ShowDialog();
                users.Save_to_file(path);
            }
            InitializeComponent();
            redrow();
        }

        private void выходToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void оПрограммеToolStripMenuItem_Click(object sender, EventArgs e)
        {
            AboutBox AboutBox = new AboutBox();
            AboutBox.ShowDialog();
        }

        private void Main_FormClosed(object sender, FormClosedEventArgs e)
        {
            users = null;
        }

        private void сменитьПарольToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            PassChange passChange = new PassChange(current_user);
            passChange.ShowDialog();
            redrow();
        }

        private void просмотрСпискаПользователейToolStripMenuItem_Click(object sender, EventArgs e)
        {
            UserList userList = new UserList();
            userList.ShowDialog();
        }

        private void сменитьПользователяToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.Visible = false;
            Login login = new Login();
            login.ShowDialog();
            redrow();
            this.Visible = true;
        }

        public void redrow()
        {
            if (current_user == admin)
            {
                функцииToolStripMenuItem.Visible = true;
            }
            else
            {
                функцииToolStripMenuItem.Visible = false;
            }
        }
    }
}
