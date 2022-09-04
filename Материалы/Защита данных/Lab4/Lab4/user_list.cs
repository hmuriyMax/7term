using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Security.Cryptography;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;
using System.Runtime.Serialization;
using Org.Mentalis.Security.Cryptography;

namespace Lab4
{    
    public class user_list
    {
        const bool DefaultPassSecure = false;
        [Serializable]
        public struct user
        {
            public string Name;
            public byte[] Pass_Hash;
            public bool PassSecure;
            public bool IsBlocked;
        }

        private byte[] salt;
        private byte[] key;
        private Dictionary<string, user> Users = new Dictionary<string, user>();
        private user temp_user = new user();
        private MD5 md5 = MD5.Create();
        const int key_legth_in_bytes = 64;

        public void Add_new_user(string Name, string pass, bool PassSecure, bool IsBlocked)
        {
            if (Name != "" && !Users.ContainsKey(Name))
            {
                temp_user.Name = Name;
                temp_user.Pass_Hash = Get_hash(pass);
                temp_user.PassSecure = PassSecure;
                temp_user.IsBlocked = IsBlocked;
                Users.Add(Name, temp_user);
            }
        }

        public void Add_new_user(string Name, string pass, bool PassSecure)
        {
            if (Name != "" && !Users.ContainsKey(Name))
            {
                temp_user.Name = Name;
                temp_user.Pass_Hash = Get_hash(pass);
                temp_user.PassSecure = PassSecure;
                temp_user.IsBlocked = false;
                Users.Add(Name, temp_user);
            }
        }

        public void Add_new_user(string Name, string pass)
        {
            if (Name != "" && !Users.ContainsKey(Name))
            {
                temp_user.Name = Name;
                temp_user.Pass_Hash = Get_hash(pass);
                temp_user.PassSecure = DefaultPassSecure;
                temp_user.IsBlocked = false;
                Users.Add(Name, temp_user);
            }
        }

        public void Add_new_user(string Name)
        {
            if (Name != "" && !Users.ContainsKey(Name))
            {
                temp_user.Name = Name;
                temp_user.Pass_Hash = Get_hash("");
                temp_user.PassSecure = DefaultPassSecure;
                temp_user.IsBlocked = false;
                Users.Add(Name, temp_user);
            }
        }

        public void Del_user(string Name)
        {
            if (Name != "")
            {
                Users.Remove(Name);
            }
        }

        public List<string> Get_user_list()
        {
            List<string> l = new List<string>();
            foreach (string name in Main.users.Users.Keys)
            {
                l.Add(name);
            }
            return l;
        }

        public bool Pass_Check(string Name, string pass)
        {
            if (Name != "" && Users.ContainsKey(Name) && Users[Name].Pass_Hash.SequenceEqual(Get_hash(pass)))
            {
                return true;
            }
            return false;
        }

        private byte[] Get_hash(string pass)
        {
            return md5.ComputeHash(Encoding.Unicode.GetBytes(pass));
        }

        public bool Is_pass_secure_on(string Name)
        {
            if (Name != "" && Users.ContainsKey(Name))
            {
                return Users[Name].PassSecure;
            }
            else return false;
        }

        public bool Is_blocked(string Name)
        {
            if (Name != "" && Users.ContainsKey(Name))
            {
                return Users[Name].IsBlocked;
            }
            else return false;
        }

        public bool Is_exist(string Name)
        {
            return Users.ContainsKey(Name);
        }

        public void Pass_Change(string Name, string pass)
        {
            if (Name != "" && Users.ContainsKey(Name))
            {
                temp_user = Users[Name];
                temp_user.Pass_Hash = Get_hash(pass);
                Users[Name] = temp_user;
            }
        }

        public void Change_pass_secure(string Name, bool sec)
        {
            if (Name != "" && Users.ContainsKey(Name))
            {
                temp_user = Users[Name];
                temp_user.PassSecure = sec;
                Users[Name] = temp_user;
            }
        }

        public void Change_block(string Name, bool block)
        {
            if (Name != "" && Users.ContainsKey(Name))
            {
                temp_user = Users[Name];
                temp_user.IsBlocked = block;
                Users[Name] = temp_user;
            }
        }
        public void Get_key(string key_world)
        {
            salt = new byte[key_legth_in_bytes];
            new RNGCryptoServiceProvider().GetBytes(salt);
            key = new PasswordDeriveBytes(key_world, salt).GetBytes(key_legth_in_bytes);
        }

        public bool Read_from_file(string path, string key_word)
        {
            bool err = false;
            Users = new Dictionary<string, user>();
            salt = new byte[key_legth_in_bytes];
            using (FileStream fs = new FileStream(path, FileMode.OpenOrCreate, FileAccess.Read))
            {
                if (fs.Length > 64)
                {
                    fs.Read(salt, 0, key_legth_in_bytes);
                    key = new PasswordDeriveBytes(key_word, salt).GetBytes(key_legth_in_bytes);
                    byte[] encrypted = new byte[fs.Length - key_legth_in_bytes];
                    fs.Read(encrypted, 0, (int)fs.Length - key_legth_in_bytes);
                    using (RC4CryptoServiceProvider rc4csp = new RC4CryptoServiceProvider())
                    {
                        rc4csp.KeySize = 128;
                        rc4csp.Key = key;
                        using (ICryptoTransform it = rc4csp.CreateDecryptor(rc4csp.Key, rc4csp.IV))
                        using (MemoryStream mstr = new MemoryStream(encrypted))
                        using (CryptoStream cstr = new CryptoStream(mstr, it, CryptoStreamMode.Read))
                        {
                            BinaryFormatter formatter = new BinaryFormatter();
                            try
                            {
                                Users = (Dictionary<string, user>)formatter.Deserialize(cstr);
                            }
                            catch (SerializationException)
                            {
                                Users.Clear();
                                err = true;
                            }
                        }
                    }
                }
            }
            Get_key(key_word);
            return !err && Is_exist("Admin");
        }

        public void Save_to_file(string path)
        {
            using (FileStream fs = new FileStream(path, FileMode.OpenOrCreate, FileAccess.Write))
            {
                fs.Write(salt, 0, 64);
                using (MemoryStream mstr = new MemoryStream())
                {
                    using (RC4CryptoServiceProvider rc4csp = new RC4CryptoServiceProvider())
                    {
                        rc4csp.KeySize = 128;
                        rc4csp.Key = key;
                        using (ICryptoTransform it = rc4csp.CreateEncryptor(rc4csp.Key, rc4csp.IV))
                        using (CryptoStream cstr = new CryptoStream(mstr, it, CryptoStreamMode.Write))
                        {
                            BinaryFormatter formatter = new BinaryFormatter();
                            formatter.Serialize(cstr, Users);
                        }
                        byte[] b = mstr.ToArray();
                        fs.Write(b, 0, b.Length);
                    }
                }
            }
        }
    }
}
