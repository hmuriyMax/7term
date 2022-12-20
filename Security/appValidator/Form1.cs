using System;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Diagnostics;
using System.Threading.Tasks;
using System.IO;
using System.Runtime.InteropServices;
using System.Security.Cryptography;
using Microsoft.Win32;

namespace appValidator
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        bool DirChosen, InfoCollected;
        static DirectoryInfo dir;
        private byte[] sysInfo;

        [DllImport("user32.dll", CharSet = CharSet.Auto, SetLastError = true)]
        [return: MarshalAs(UnmanagedType.I4)]
        static extern int GetKeyboardType(int nTypeFlag);

        static string DriveFormat()
        {
            Console.WriteLine("Disc {0}", dir.Root);
            DriveInfo[] allDrives = DriveInfo.GetDrives();
            foreach (DriveInfo d in allDrives)
            {
                if (d.Name == dir.Root.ToString())
                {
                    return d.DriveFormat;
                }
            }
            return "Not found";
        }

        bool CheckReady()
        {
            return DirChosen && InfoCollected;
        }

        void Write(MemoryStream data, string str)
        {
            byte[] b = Encoding.ASCII.GetBytes(str);
            data.Write(b, 0, b.Length);
        }

        void Write(MemoryStream data, int num)
        {
            byte[] b = BitConverter.GetBytes(num);
            data.Write(b, 0, b.Length);
        }

        private void collect_info()
        {
            MemoryStream data = new MemoryStream();
            Write(data, Environment.MachineName);
            Write(data, Environment.UserName);
            Write(data, Environment.GetEnvironmentVariable("windir"));
            Write(data, Environment.SystemDirectory);
            Write(data, SystemInformation.PrimaryMonitorSize.Height);

            Write(data, GetKeyboardType(0));
            Write(data, GetKeyboardType(1));
            Write(data, DriveFormat());

            /*MD5 hash = MD5.Create();
            string hsh = hash.ComputeHash(data).ToString();
            string fact;
            fact = (string)Registry.GetValue("HKEY_CURRENT_USER\\Software\\Schemilkin", "Signature", "e");
            return fact == hsh;*/
            sysInfo = data.GetBuffer();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            collect_info();
            string reg = textBox1.Text;
            byte[] signData = new byte[0];
            byte[] publicKey = new byte[0];

            try
            {
                signData = (byte[])Registry.GetValue("HKEY_CURRENT_USER\\Software\\" + reg, "Signature", null);
                publicKey = (byte[])Registry.GetValue("HKEY_CURRENT_USER\\Software\\" + reg, "Public key", null);

                using (RSACryptoServiceProvider rsa = new RSACryptoServiceProvider())
                {
                    rsa.ImportCspBlob(publicKey);
                    if (!rsa.VerifyData(sysInfo, "MD5", signData))
                    {
                        MessageBox.Show("Неверная цифровая подпись.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                        Close();
                        return;
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Возникла ошибка! Переустановите программу.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                Close();
                return;
            }
            Process srv = Process.Start("lab.exe", "f7vrcs17ds54");
            srv.WaitForExit();
            Close();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            dir = Directory.GetParent(Directory.GetCurrentDirectory());
        }
    }
}