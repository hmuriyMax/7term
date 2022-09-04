using System;
using System.Windows.Forms;

namespace Lab4
{
    public partial class Main : Form
    {
        public const string path = ".\\users";
        public const string admin = "Admin";
        public static user_list users = new user_list();
        public static string current_user;
        public static bool will_exit = false;

        public Main()
        {
            InitializeComponent();
            RegisteryCheck registeryCheck = new RegisteryCheck();
            registeryCheck.ShowDialog();
            if (!will_exit)
            {
                if (System.IO.File.Exists(path))
                {
                    ChiperWord chiperWord = new ChiperWord();
                    chiperWord.ShowDialog();
                    if (!will_exit)
                    {
                        Login login = new Login();
                        login.ShowDialog();
                    }
                }
                else
                {
                    ChiperWord chiperWord = new ChiperWord('f');
                    chiperWord.ShowDialog();
                    if (!will_exit)
                    {
                        current_user = admin;
                        users.Add_new_user(current_user);
                        PassChange passChange = new PassChange(current_user, 'f');
                        passChange.ShowDialog();
                    }
                    if (!will_exit)
                    {
                        users.Save_to_file(path);
                    }
                }
                reDrow();
            }
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
            reDrow();
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
            reDrow();
            this.Visible = true;
        }

        public void reDrow()
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

        public void Exit()
        {
            Close();
        }

        private void файлToolStripMenuItem_Click(object sender, EventArgs e)
        {

        }
    }
}
