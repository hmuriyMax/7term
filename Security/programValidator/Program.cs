using System;
using System.Management;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Runtime.InteropServices;
using System.Security.Cryptography;
using Microsoft.Win32;

bool DirChosen, InfoCollected;
DirectoryInfo dir;

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

void WriteStr(MemoryStream data, string str)
{
    byte[] b = Encoding.ASCII.GetBytes(str);
    data.Write(b, 0, b.Length);
}

void WriteInt(MemoryStream data, int num)
{
    byte[] b = BitConverter.GetBytes(num);
    data.Write(b, 0, b.Length);
}

bool validate_info()
{
    MemoryStream data = new MemoryStream();
    WriteStr(data, Environment.MachineName);

    WriteStr(data, Environment.UserName);
    WriteStr(data, Environment.GetEnvironmentVariable("windir"));
    WriteStr(data, Environment.SystemDirectory);
    WriteInt(data, SystemInformation.PrimaryMonitorSize.Height);

    WriteInt(data, GetKeyboardType(0));
    WriteInt(data, GetKeyboardType(1));
    WriteStr(data, DriveFormat());

    MD5 hash = MD5.Create();
    string hsh = hash.ComputeHash(data).ToString();
    string fact;
    fact = (string) Registry.GetValue("HKEY_CURRENT_USER\\Software\\Schemilkin", "Signature", "e");
    return fact == hsh;
}