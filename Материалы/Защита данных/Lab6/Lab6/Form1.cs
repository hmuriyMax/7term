using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Forms;
using System.Runtime.InteropServices;
using System.IO;
using System.Security.Cryptography;
using Microsoft.Win32;
using System.Linq;

namespace Lab6
{
    public partial class Form1 : Form
    {
        public static string install_Path;

        public Form1()
        {
            InitializeComponent();
            textBox1.Text = "HKEY_CURRENT_USER\\Software\\Krylov";
        }

        private void textBox_install_path_Click(object sender, EventArgs e)
        {
            FolderBrowserDialog FBD = new FolderBrowserDialog();
            if (FBD.ShowDialog() == DialogResult.OK)
            {
                install_Path = FBD.SelectedPath + "\\Lab4";
                textBox_install_path.Text = install_Path;
                button_install.Enabled = true;
            }
        }

        private void button_install_Click(object sender, EventArgs e)
        {
            if (File.Exists(".\\Lab4\\Lab4.exe") && File.Exists(".\\Lab4\\Org.Mentalis.Security.dll"))
            {
                Directory.CreateDirectory(install_Path);
                File.Copy(".\\Lab4\\Lab4.exe", install_Path + "\\Lab4.exe", true);
                File.Copy(".\\Lab4\\Org.Mentalis.Security.dll", install_Path + "\\Org.Mentalis.Security.dll", true);
                MD5 md5 = MD5.Create();
                Registry.SetValue(textBox1.Text, "Signature", md5.ComputeHash(System_info()));
                if (md5.ComputeHash(System_info()).SequenceEqual((Byte[])Registry.GetValue("HKEY_CURRENT_USER\\Software\\Krylov", "Signature", 0)))
                    MessageBox.Show("Установка успешно завершена.");
            }
            else
            {
                MessageBox.Show("Установочные файлы повреждены.");
            }
            Close();
        }
        public Byte[] System_info()
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

    }
}
