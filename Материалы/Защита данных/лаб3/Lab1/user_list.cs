using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Security.Cryptography;
using System.Xml;

namespace Lab1
{    
    public class user_list
    {
        const bool DefaultPassSecure = false;
        public struct user
        {
            public string Name;
            public string Pass;
            public bool PassSecure;
            public bool IsBlocked;
        }

        private Dictionary<string, user> Users = new Dictionary<string, user>();
        private user temp_user = new user();

        public void Add_new_user(string Name, string pass, bool PassSecure, bool IsBlocked)
        {
            if (Name != "" && !Users.ContainsKey(Name))
            {
                temp_user.Name = Name;
                temp_user.Pass = Get_hash(Name, pass);
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
                temp_user.Pass = Get_hash(Name, pass);
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
                temp_user.Pass = Get_hash(Name, pass); ;
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
                temp_user.Pass = ""; 
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
            if (Name != "" && Users.ContainsKey(Name) && Users[Name].Pass.SequenceEqual(Get_hash(Name, pass)))
            {
                return true;
            }
            return false;
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
            if (Name != "" && Users.ContainsKey(Name) )
            {
                temp_user = Users[Name];
                temp_user.Pass = Get_hash(Name, pass);
                Users[Name] = temp_user;
            }
        }

        public void Change_pass_secure(string Name, bool sec)
        {
            if (Name != "" && Users.ContainsKey(Name) )
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

        public void Read_from_file(string path)
        {
            Users = new Dictionary<string, user>();
            XmlDocument xDoc = new XmlDocument();
            xDoc.Load(path);
            XmlElement xRoot = xDoc.DocumentElement;
            foreach (XmlElement xnode in xRoot)
            {
                foreach (XmlNode childnode in xnode.ChildNodes)
                {
                    if (childnode.Name == "Name") temp_user.Name = childnode.InnerText;
                    if (childnode.Name == "Pass") temp_user.Pass = childnode.InnerText;
                    if (childnode.Name == "PassSecure") temp_user.PassSecure = Boolean.Parse(childnode.InnerText);
                    if (childnode.Name == "IsBlocked") temp_user.IsBlocked = Boolean.Parse(childnode.InnerText);
                }
                Users.Add(temp_user.Name, temp_user);
            }
        }

        public void Save_to_file(string path)
        {
            using (XmlTextWriter writer = new XmlTextWriter(path, null))
            {
                writer.Formatting = Formatting.Indented;
                writer.Indentation = 1;
                writer.IndentChar = '\t';
                writer.WriteStartDocument();
                writer.WriteStartElement("Users");
                {
                    foreach (user u in Users.Values)
                    {
                        writer.WriteStartElement("User");
                        {   
                            writer.WriteElementString("Name", u.Name);
                            writer.WriteElementString("Pass", u.Pass);
                            writer.WriteElementString("PassSecure", u.PassSecure.ToString());
                            writer.WriteElementString("IsBlocked", u.IsBlocked.ToString());
                            writer.WriteEndElement();
                        }
                    }
                }
                writer.WriteEndElement();
            }
        }

        private string Get_hash(string Name, string pass)
        {
            if (pass == "") return "";
            char[] output_array = new char[Name.Length];
            int multiplier = Convert.ToInt32(pass[0]);
            int term = Convert.ToInt32(pass[1]);
            int gamma = Convert.ToInt32(pass[2]);
            for (int itera = 0; itera < Name.Length; itera++)
            {
                int current_symbol = Convert.ToInt32(Name[itera]);
                output_array[itera] = Convert.ToChar(current_symbol ^ gamma);
                gamma = (multiplier * gamma + term) % 256;
            }
            return new String(output_array);
        }
    }
}
