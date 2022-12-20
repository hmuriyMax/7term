using System;
using System.Management;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;
using System.Runtime.InteropServices;
using System.Security.Cryptography;
using Microsoft.Win32;

namespace lab6
{
    public partial class Form1 : Form
    {
        [DllImport("user32.dll", CharSet = CharSet.Auto, SetLastError = true)]
        [return: MarshalAs(UnmanagedType.I4)]
        public static extern int GetKeyboardType(int nTypeFlag);

        private bool DirChosen, InfoCollected;
        public static DirectoryInfo dir;
        private byte[] sysInfo;
        public Form1()
        {
            InitializeComponent();
        }

        private void MessShow(string message)
        {
            Form2 newForm = new Form2();
            newForm.parent = this;
            newForm.message = message;
            newForm.Show();
            this.Hide();
        }

        private void CopyAllFiles(string dirpath, string dest)
        {
            string[] files = Directory.GetFiles(dirpath);
            string[] dirs = Directory.GetDirectories(dirpath);
            foreach (var dir in dirs)
            {
                string newDir = dest +'\\'+ dir.Split('\\').Last();
                Directory.CreateDirectory(newDir);
                CopyAllFiles(dir, newDir);
            }
            foreach (var file in files)
            {
                string newFile = dest + file.Substring(dirpath.Length);
                if (File.Exists(newFile))
                {
                    File.Delete(newFile);
                }
                File.Copy(file, newFile);
            }
            return;
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (!Directory.Exists("program"))
            {
                MessShow("Установщик поврежден!");
                return;
            }
            if (textBox1.Text == "")
            {
                MessShow("Вы не ввели имя регистра");
                return;
            }
            CopyAllFiles("program", dir.FullName);

            byte[] signHashData, publicKey;
            using (RSACryptoServiceProvider rsa = new RSACryptoServiceProvider())
            {
                signHashData = rsa.SignData(sysInfo, "MD5");
                publicKey = rsa.ExportCspBlob(false);
            }

            string s = "HKEY_CURRENT_USER\\Software\\" + textBox1.Text;
            Registry.SetValue(s, "Signature", signHashData);
            Registry.SetValue(s, "Public key", publicKey);

            MessShow("Успех!");
            
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string path = "";
            FolderBrowserDialog dialog = new FolderBrowserDialog();

            if (dialog.ShowDialog() == DialogResult.OK)
            {
                if ((path = dialog.SelectedPath) != null)
                {
                    dialog.Dispose();
                }
                string newDir = path + "\\SuperSecureSystem";

                if (Directory.Exists(newDir))
                {
                    MessShow("Директория уже существует.");
                    return;
                }
                dir = Directory.CreateDirectory(newDir);
                DirChosen = true;
                get_info();
                if (CheckReady()){
                    button2.Enabled = true;
                }
            }
        }

        private bool CheckReady()
        {
            return DirChosen && InfoCollected;
        }

        private void Write(MemoryStream data, string str)
        {
            byte[] b = Encoding.ASCII.GetBytes(str);
            data.Write(b, 0, b.Length);
        }

        private void Write(MemoryStream data, int num)
        {
            byte[] b = BitConverter.GetBytes(num);
            data.Write(b, 0, b.Length);
        }

        private void get_info()
        {
            progressBar1.Value = 0;
            MemoryStream data = new MemoryStream();
            progressBar1.Value = 5;
            Write(data, Environment.MachineName);

            progressBar1.Value = 15;
            Write(data, Environment.UserName);
            progressBar1.Value = 25;
            Write(data, Environment.GetEnvironmentVariable("windir"));
            progressBar1.Value = 35;
            Write(data, Environment.SystemDirectory);
            progressBar1.Value = 45;
            Write(data, SystemInformation.PrimaryMonitorSize.Height);
            progressBar1.Value = 55;

            Write(data, GetKeyboardType(0));
            progressBar1.Value = 65;
            Write(data, GetKeyboardType(1));
            progressBar1.Value = 75;
            Write(data, DriveFormat());
            progressBar1.Value = 85;

            sysInfo = data.GetBuffer();
            progressBar1.Value = 100;
            InfoCollected = true;
            if (CheckReady()) {
                button2.Enabled = true;
            }
        }

        public static void Variables()
        {
            Console.WriteLine();
            Console.WriteLine("GetEnvironmentVariables: ");
            foreach (DictionaryEntry de in Environment.GetEnvironmentVariables())
                Console.WriteLine("  {0} = {1}", de.Key, de.Value);
        }

        public static string DriveFormat()
        {
            Console.WriteLine("Disc {0}", dir.Root);
            DriveInfo[] allDrives = DriveInfo.GetDrives();
            foreach (DriveInfo d in allDrives)
            {
                if (d.Name == dir.Root.ToString()){
                    return d.DriveFormat;
                }
            }
            return "Not found";
        }

        public static ulong WMIGetTotalPhysicalMemory()
        {
            ManagementScope mScope = new ManagementScope($@"\\{Environment.MachineName}\root\CIMV2");
            SelectQuery mQuery = new SelectQuery("SELECT * FROM Win32_PhysicalMemory");
            mScope.Connect();

            ulong installedMemory = 0;
            using (ManagementObjectSearcher moSearcher = new ManagementObjectSearcher(mScope, mQuery))
            {
                foreach (ManagementObject moCapacity in moSearcher.Get())
                {
                    installedMemory += (UInt64)moCapacity["Capacity"];
                }
            }
            return installedMemory;
        }
    }
}
