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
    public partial class PassChange : Form
    {
        private char param;
        int try_count = 3;
        string changed_user;
        public PassChange(string user)
        {
            InitializeComponent();
            Error_label.Text = "";
            Try_count_label.Text = "";
            changed_user = user;
        }
        /// <summary>
        /// Paramerts:
        /// 'a' - change the password without confirming the old one;
        /// 'e' - mandatory password change without confirmation of the old one
        /// (only exit from the application is available);
        /// 'f' - use when starting the program when there is no credential file
        /// (for the first authorization of the administrator).
        /// </summary>
        /// <param name="user"></param>
        /// <param name="p"></param>
        public PassChange(string user, char p)
        {
            InitializeComponent();
            param = p;
            changed_user = user;
            if (param == 'f')
            {
                this.Text = "Первая авторизация администратора";
                Old_pass_textBox.PasswordChar = '\0';
                Cancel_button.Text = "Выход";
                Old_pass_label.Text = "Администратор:";
            }
            else if(param == 'e')
            {
                Cancel_button.Text = "Выход";
                Old_pass_label.Text = "Пользователь: " + user;
                Old_pass_textBox.Visible = false;
            }
            else if (param == 'a')
            {
                Old_pass_label.Text = "Пользователь: " + user;
                Old_pass_textBox.Visible = false;
            }
            Error_label.Text = "";
            Try_count_label.Text = "";
        }
        private void Cancel_button_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void Ok_button_Click(object sender, EventArgs e)
        {
            if (param == 'f')
            {
                if (Old_pass_textBox.Text == Main.admin)
                {
                    New_pass_examine();
                }
                else
                {
                    if (try_count > 0)
                    {
                        Error_label.Text = "Неверное имя администратора";
                        Try_count_label.Text = "Попыток до закрытия приложения осталось: " + try_count;
                        try_count--;
                    }
                    else
                    {
                        Close();
                    }
                }
            }
            else if (param == 'e' || param == 'a' || Main.users.Pass_Check(changed_user, Old_pass_textBox.Text))
            {
                New_pass_examine();
            }
            else 
            {
                if (try_count > 0)
                {
                    Error_label.Text = "Неверный пароль";
                    Try_count_label.Text = "Попыток до деавторизации осталось: " + try_count;
                    try_count--;
                }
                else
                {
                    param = 'l';
                    Close();
                }
            }
            
        }
        private void New_pass_examine()
        {
            if (New_pass_textBox.Text == Confirm_pass_textBox.Text)
            {
                if (Main.users.Is_pass_secure_on(changed_user) && !Secure_test(New_pass_textBox.Text))
                {
                    Error_label.Text = "Пароль должен содержать буквы";
                    Try_count_label.Text = "кириллицы, латиницы и цифры";
                }
                else
                {
                    Main.users.Pass_Change(changed_user, New_pass_textBox.Text);
                    param = '\0';
                    Close();
                }
            }
            else
            {
                Error_label.Text = "Новый пароль и его подтверждение не совпадают";
                Try_count_label.Text = "";
            }
        }
        private void PassChange_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (param == 'e' || param == 'f')
            {
                Environment.Exit(0);
            }
            if (param == 'l')
            {
                this.Visible = false;
                Login login = new Login();
                login.ShowDialog();
            }
        }
        private bool Secure_test(string pass) //Наличие латинских букв, символов кириллицы и цифр..
        {
            bool kir = false;
            bool lat = false;
            bool dig = false;
            foreach (char c in pass)
            {
                if (!dig && Char.IsDigit(c)) dig = true;
                if (!lat && (((int)c >= 97 && (int)c <= 122) || ((int)c >= 65 && (int)c <= 90))) lat = true;//Проверка латинских букв.
                if (!kir && (int)c >= 1040 && (int)c <= 1103) kir = true;//Проверка Кириллицы.
            }
            return lat && kir && dig;
        }

        private void Old_pass_textBox_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                New_pass_textBox.Focus();
            }
        }

        private void New_pass_textBox_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                Confirm_pass_textBox.Focus();
            }
        }

        private void Confirm_pass_textBox_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                Ok_button_Click(sender, e);
            }
        }
    }
}
