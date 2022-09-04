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
    public partial class UserList : Form
    {
        public UserList()
        {
            InitializeComponent();
            refresh_listBox();
            user_textBox.Text = Main.current_user;
            passSecure_checkBox.Checked = Main.users.Is_pass_secure_on(Main.current_user);
            Block_checkBox.Checked = false;
            Block_checkBox.Visible = false;
            del_button.Visible = false;
        }

        private void refresh_listBox()
        {
            foreach (string name in Main.users.Get_user_list())
            {
                users_listBox.Items.Add(name);
            }
        }

        private void users_listBox_SelectedValueChanged(object sender, EventArgs e)
        {
            if (users_listBox.SelectedItem != null)
            {
                user_textBox.Text = users_listBox.SelectedItem.ToString();
                if (user_textBox.Text == Main.admin)
                {
                    Block_checkBox.Visible = false;
                    del_button.Visible = false;
                }
                else
                {
                    Block_checkBox.Visible = true;
                    del_button.Visible = true;
                }
                passSecure_checkBox.Checked = Main.users.Is_pass_secure_on(user_textBox.Text);
                Block_checkBox.Checked = Main.users.Is_blocked(user_textBox.Text);
                label1.Text = "______________________________________";
            }
        }

        private void find_button_Click(object sender, EventArgs e)
        {
            if (find_textBox.Text != "")
            {
                if (users_listBox.Items.Contains(find_textBox.Text))
                {
                    users_listBox.SelectedItem = find_textBox.Text;
                }
            }
        }

        private void add_button_Click(object sender, EventArgs e)
        {
            if (find_textBox.Text != "")
            {
                if (users_listBox.Items.Contains(find_textBox.Text))
                {
                    users_listBox.SelectedItem = find_textBox.Text;
                    label1.Text = "Такой пользователь уже существует!";
                }
                else
                {
                    Main.users.Add_new_user(find_textBox.Text);
                    users_listBox.Items.Add(find_textBox.Text);
                    users_listBox.SelectedItem = find_textBox.Text;
                }
            }
        }

        private void UserList_FormClosing(object sender, FormClosingEventArgs e)
        {
            Main.users.Save_to_file(Main.path);
        }

        private void del_button_Click(object sender, EventArgs e)
        {
            Main.users.Del_user(user_textBox.Text);
            users_listBox.Items.Remove(users_listBox.Items);
        }

        private void passSecure_checkBox_CheckedChanged(object sender, EventArgs e)
        {
            
        }

        private void Block_checkBox_CheckedChanged(object sender, EventArgs e)
        {
            
        }

        private void changePass_button_Click(object sender, EventArgs e)
        {
            PassChange passChange = new PassChange(user_textBox.Text, 'a');
            passChange.ShowDialog();
        }

        private void passSecure_checkBox_Click(object sender, EventArgs e)
        {
            Main.users.Change_pass_secure(user_textBox.Text, passSecure_checkBox.Checked);
        }

        private void Block_checkBox_Click(object sender, EventArgs e)
        {
            Main.users.Change_block(user_textBox.Text, Block_checkBox.Checked);
        }

        private void Close_button_Click(object sender, EventArgs e)
        {
            Close();
        }
    }
}
