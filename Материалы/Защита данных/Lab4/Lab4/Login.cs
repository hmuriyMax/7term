using System;
using System.Windows.Forms;

namespace Lab4
{
    public partial class Login : Form
    {
        private bool bad = true;
        int try_count = 3;
        public Login()
        {
            InitializeComponent();
            Error_label.Text = "";
            Try_count_label.Text = "";
        }
        private void Cancel_button_Click(object sender, EventArgs e)
        {
            Close();
        }
        private void Ok_login_button_Click(object sender, EventArgs e)
        {
            if (!Main.users.Is_exist(Name_textBox.Text))
            {
                Error_label.Text = "Такого пользователя не существует!";
                Try_count_label.Text = "";
            }
            else if (Main.users.Is_blocked(Name_textBox.Text))
            {
                Error_label.Text = "Пользователь заблокирован!";
                Try_count_label.Text = "";
            }
            else if (Main.users.Pass_Check(Name_textBox.Text, Pass_textBox.Text))
            {
                Main.current_user = Name_textBox.Text;
                bad = false;
                if (Pass_textBox.Text == "")
                {
                    this.Visible = false;
                    PassChange passChange = new PassChange(Name_textBox.Text, 'e');
                    passChange.ShowDialog();
                }
                Close();
            }
            else if (try_count > 0)
            {
                Error_label.Text = "Не верный пароль!";
                Try_count_label.Text = "Попыток до закрытия приложения осталось: " + try_count;
                try_count--;
            }
            else
            {
                Close();
            }            
        }
        private void Login_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (bad)
            {
                Main.will_exit = true;
                Application.Exit();
            }
        }

        private void Pass_textBox_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                Ok_login_button_Click(sender, e);
            }
        }

        private void Name_textBox_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                Pass_textBox.Focus();
            }
        }
    }
}
