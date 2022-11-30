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
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void MessShow(string message)
        {
            Form2 newForm = new Form2();
            newForm.parent = this;
            newForm.message = message;
            newForm.Show();
            this.Hide();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (!File.Exists("bin"))
            {
                MessShow("Установщик поврежден!");
                return;
            }
            File.Copy("bin", dir.FullName + "\\webServer.exe");
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
                dir = Directory.CreateDirectory(path + "\\SuperSecureSystem");

                if (Directory.Exists(dir.ToString()))
                {
                    MessShow("Директория уже существует.");
                    return;
                }
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

            MD5 hash = MD5.Create();
            byte[] hashBytes = hash.ComputeHash(data);
            Registry.SetValue("HKEY_CURRENT_USER\\Software\\Schemilkin", "Signature", hashBytes);
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
