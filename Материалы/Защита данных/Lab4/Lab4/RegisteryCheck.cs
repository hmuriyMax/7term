using Microsoft.Win32;
using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using System.Windows.Forms;
using System.Security.Cryptography;
using System.Linq;

namespace Lab4
{
    public partial class RegisteryCheck : Form
    {
        public static Byte[] System_info()
        {
            List<Byte> temp = new List<byte>();
            DriveInfo driveInfo = new DriveInfo(Environment.CurrentDirectory.Substring(0, 1));
            temp.AddRange(Encoding.UTF8.GetBytes(Environment.UserName));
            temp.AddRange(Encoding.UTF8.GetBytes(Environment.MachineName));
            temp.AddRange(Encoding.UTF8.GetBytes(Environment.GetEnvironmentVariable("windir")));
            temp.AddRange(Encoding.UTF8.GetBytes(Environment.SystemDirectory));
            temp.AddRange(BitConverter.GetBytes(SystemInformation.MouseButtons));
            temp.AddRange(BitConverter.GetBytes(SystemInformation.PrimaryMonitorSize.Height));
            temp.AddRange(Encoding.UTF8.GetBytes(driveInfo.VolumeLabel));
            return temp.ToArray();
        }

        private bool bad = true;
        public RegisteryCheck()
        {
            InitializeComponent();
        }

        private void button_exit_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            MD5 md5 = MD5.Create();
            object temp = Registry.GetValue(textBox_name.Text, "Signature", 0);
            md5.ComputeHash(System_info());
            if (temp != null && temp.GetType() != Type.GetType("System.Int32") && md5.ComputeHash(System_info()).SequenceEqual((Byte[])temp))
            {
                bad = false;
            }
            else
            {
                MessageBox.Show("Проверка не пройдена", "Ошибка");
            }
            Close();
        }

        private void RegisteryCheck_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (bad)
            {
                Main.will_exit = true;
                Application.Exit();
            }
        }
    }
}
