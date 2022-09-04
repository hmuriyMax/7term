namespace Lab1
{
    partial class UserList
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.users_listBox = new System.Windows.Forms.ListBox();
            this.passSecure_checkBox = new System.Windows.Forms.CheckBox();
            this.Block_checkBox = new System.Windows.Forms.CheckBox();
            this.user_label = new System.Windows.Forms.Label();
            this.user_textBox = new System.Windows.Forms.TextBox();
            this.del_button = new System.Windows.Forms.Button();
            this.add_button = new System.Windows.Forms.Button();
            this.find_button = new System.Windows.Forms.Button();
            this.find_textBox = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.changePass_button = new System.Windows.Forms.Button();
            this.Close_button = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // users_listBox
            // 
            this.users_listBox.FormattingEnabled = true;
            this.users_listBox.ItemHeight = 20;
            this.users_listBox.Location = new System.Drawing.Point(13, 5);
            this.users_listBox.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.users_listBox.Name = "users_listBox";
            this.users_listBox.Size = new System.Drawing.Size(246, 304);
            this.users_listBox.TabIndex = 0;
            this.users_listBox.SelectedValueChanged += new System.EventHandler(this.users_listBox_SelectedValueChanged);
            // 
            // passSecure_checkBox
            // 
            this.passSecure_checkBox.AutoSize = true;
            this.passSecure_checkBox.Location = new System.Drawing.Point(271, 198);
            this.passSecure_checkBox.Name = "passSecure_checkBox";
            this.passSecure_checkBox.Size = new System.Drawing.Size(247, 24);
            this.passSecure_checkBox.TabIndex = 4;
            this.passSecure_checkBox.Text = "Проверка сложности пароля";
            this.passSecure_checkBox.UseVisualStyleBackColor = true;
            this.passSecure_checkBox.Click += new System.EventHandler(this.passSecure_checkBox_Click);
            // 
            // Block_checkBox
            // 
            this.Block_checkBox.AutoSize = true;
            this.Block_checkBox.Location = new System.Drawing.Point(271, 228);
            this.Block_checkBox.Name = "Block_checkBox";
            this.Block_checkBox.Size = new System.Drawing.Size(119, 24);
            this.Block_checkBox.TabIndex = 5;
            this.Block_checkBox.Text = "Блокировка";
            this.Block_checkBox.UseVisualStyleBackColor = true;
            this.Block_checkBox.Click += new System.EventHandler(this.Block_checkBox_Click);
            // 
            // user_label
            // 
            this.user_label.AutoSize = true;
            this.user_label.Location = new System.Drawing.Point(272, 151);
            this.user_label.Name = "user_label";
            this.user_label.Size = new System.Drawing.Size(122, 40);
            this.user_label.TabIndex = 3;
            this.user_label.Text = "Выбранный\r\nпользователь:";
            this.user_label.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // user_textBox
            // 
            this.user_textBox.Location = new System.Drawing.Point(403, 148);
            this.user_textBox.Multiline = true;
            this.user_textBox.Name = "user_textBox";
            this.user_textBox.ReadOnly = true;
            this.user_textBox.Size = new System.Drawing.Size(209, 43);
            this.user_textBox.TabIndex = 40;
            this.user_textBox.TabStop = false;
            // 
            // del_button
            // 
            this.del_button.Location = new System.Drawing.Point(266, 312);
            this.del_button.Name = "del_button";
            this.del_button.Size = new System.Drawing.Size(346, 37);
            this.del_button.TabIndex = 7;
            this.del_button.Text = "Удалить выбранного пользователя";
            this.del_button.UseVisualStyleBackColor = true;
            this.del_button.Click += new System.EventHandler(this.del_button_Click);
            // 
            // add_button
            // 
            this.add_button.Location = new System.Drawing.Point(267, 75);
            this.add_button.Name = "add_button";
            this.add_button.Size = new System.Drawing.Size(346, 35);
            this.add_button.TabIndex = 3;
            this.add_button.Text = "Добавить нового пользователя";
            this.add_button.UseVisualStyleBackColor = true;
            this.add_button.Click += new System.EventHandler(this.add_button_Click);
            // 
            // find_button
            // 
            this.find_button.Location = new System.Drawing.Point(266, 5);
            this.find_button.Name = "find_button";
            this.find_button.Size = new System.Drawing.Size(346, 32);
            this.find_button.TabIndex = 1;
            this.find_button.Text = "Найти пользователя";
            this.find_button.UseVisualStyleBackColor = true;
            this.find_button.Click += new System.EventHandler(this.find_button_Click);
            // 
            // find_textBox
            // 
            this.find_textBox.Location = new System.Drawing.Point(266, 43);
            this.find_textBox.MaxLength = 64;
            this.find_textBox.Name = "find_textBox";
            this.find_textBox.Size = new System.Drawing.Size(346, 26);
            this.find_textBox.TabIndex = 2;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(262, 111);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(351, 20);
            this.label1.TabIndex = 9;
            this.label1.Text = "______________________________________";
            // 
            // changePass_button
            // 
            this.changePass_button.Location = new System.Drawing.Point(266, 258);
            this.changePass_button.Name = "changePass_button";
            this.changePass_button.Size = new System.Drawing.Size(347, 48);
            this.changePass_button.TabIndex = 6;
            this.changePass_button.Text = "Сменить пароль выбранного пользователя";
            this.changePass_button.UseVisualStyleBackColor = true;
            this.changePass_button.Click += new System.EventHandler(this.changePass_button_Click);
            // 
            // Close_button
            // 
            this.Close_button.Location = new System.Drawing.Point(13, 312);
            this.Close_button.Name = "Close_button";
            this.Close_button.Size = new System.Drawing.Size(105, 36);
            this.Close_button.TabIndex = 41;
            this.Close_button.Text = "Закрыть";
            this.Close_button.UseVisualStyleBackColor = true;
            this.Close_button.Click += new System.EventHandler(this.Close_button_Click);
            // 
            // UserList
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(624, 361);
            this.Controls.Add(this.Close_button);
            this.Controls.Add(this.changePass_button);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.find_textBox);
            this.Controls.Add(this.find_button);
            this.Controls.Add(this.add_button);
            this.Controls.Add(this.del_button);
            this.Controls.Add(this.user_textBox);
            this.Controls.Add(this.user_label);
            this.Controls.Add(this.Block_checkBox);
            this.Controls.Add(this.passSecure_checkBox);
            this.Controls.Add(this.users_listBox);
            this.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.Name = "UserList";
            this.Text = "Список пользователей";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.UserList_FormClosing);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListBox users_listBox;
        private System.Windows.Forms.CheckBox passSecure_checkBox;
        private System.Windows.Forms.CheckBox Block_checkBox;
        private System.Windows.Forms.Label user_label;
        private System.Windows.Forms.TextBox user_textBox;
        private System.Windows.Forms.Button del_button;
        private System.Windows.Forms.Button add_button;
        private System.Windows.Forms.Button find_button;
        private System.Windows.Forms.TextBox find_textBox;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button changePass_button;
        private System.Windows.Forms.Button Close_button;
    }
}