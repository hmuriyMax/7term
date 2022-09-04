using System;
using System.Windows.Forms;

namespace Lab4
{
    public partial class ChiperWord : Form
    {
        private bool bad = true;
        private char param;
        public ChiperWord()
        {
            InitializeComponent();
        }
        public ChiperWord(char p)
        {
            InitializeComponent();
            param = p;
            if (param == 'f')
            {
                button_pass_phrase.Text = "Подтвердить";
                label_pass_phrase_сonfirm.Visible = true;
                textBox_pass_phrase_сonfirm.Visible = true;
            }
        }

        private void button_exit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void button_pass_phrase_Click(object sender, EventArgs e)
        {
            if (param == 'f')
            {
                if (textBox_pass_phrase.Text == textBox_pass_phrase_сonfirm.Text)
                {
                    Main.users.Get_key(textBox_pass_phrase.Text);
                    bad = false;
                    Close();
                }
                else
                {
                    label1.Text = "Поля не совпадают.";
                }
            }
            else if (Main.users.Read_from_file(Main.path, textBox_pass_phrase.Text))
            {
                bad = false;
                Close();
            }
            else
            {
                MessageBox.Show("Неверная парольная фраза!");
                Close();
            }
        }

        private void ChiperWord_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (bad)
            {
                Main.will_exit = true;
                Application.Exit();
            }
        }

        private void textBox_pass_phrase_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                if (param == 'f') textBox_pass_phrase_сonfirm.Focus();
                else button_pass_phrase_Click(sender, e);
            }
        }

        private void textBox_pass_phrase_сonfirm_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                button_pass_phrase_Click(sender, e);
            }
        }
    }
}
