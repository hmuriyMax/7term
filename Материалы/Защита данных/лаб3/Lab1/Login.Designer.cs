namespace Lab1
{
    partial class Login
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
            this.Pass_textBox = new System.Windows.Forms.TextBox();
            this.Ok_button = new System.Windows.Forms.Button();
            this.Cancel_button = new System.Windows.Forms.Button();
            this.Pass_label = new System.Windows.Forms.Label();
            this.Name_label = new System.Windows.Forms.Label();
            this.Name_textBox = new System.Windows.Forms.TextBox();
            this.Error_label = new System.Windows.Forms.Label();
            this.Try_count_label = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // Pass_textBox
            // 
            this.Pass_textBox.Location = new System.Drawing.Point(171, 40);
            this.Pass_textBox.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.Pass_textBox.MaxLength = 64;
            this.Pass_textBox.Name = "Pass_textBox";
            this.Pass_textBox.PasswordChar = '*';
            this.Pass_textBox.Size = new System.Drawing.Size(441, 26);
            this.Pass_textBox.TabIndex = 2;
            this.Pass_textBox.WordWrap = false;
            this.Pass_textBox.KeyDown += new System.Windows.Forms.KeyEventHandler(this.Pass_textBox_KeyDown);
            // 
            // Ok_button
            // 
            this.Ok_button.Location = new System.Drawing.Point(529, 90);
            this.Ok_button.Name = "Ok_button";
            this.Ok_button.Size = new System.Drawing.Size(83, 33);
            this.Ok_button.TabIndex = 3;
            this.Ok_button.Text = "Принять";
            this.Ok_button.UseVisualStyleBackColor = true;
            this.Ok_button.Click += new System.EventHandler(this.Ok_login_button_Click);
            // 
            // Cancel_button
            // 
            this.Cancel_button.Location = new System.Drawing.Point(16, 90);
            this.Cancel_button.Name = "Cancel_button";
            this.Cancel_button.Size = new System.Drawing.Size(89, 33);
            this.Cancel_button.TabIndex = 4;
            this.Cancel_button.Text = "Выход";
            this.Cancel_button.UseVisualStyleBackColor = true;
            this.Cancel_button.Click += new System.EventHandler(this.Cancel_button_Click);
            // 
            // Pass_label
            // 
            this.Pass_label.AutoSize = true;
            this.Pass_label.Location = new System.Drawing.Point(97, 43);
            this.Pass_label.Name = "Pass_label";
            this.Pass_label.Size = new System.Drawing.Size(67, 20);
            this.Pass_label.TabIndex = 17;
            this.Pass_label.Text = "Пароль";
            // 
            // Name_label
            // 
            this.Name_label.AutoSize = true;
            this.Name_label.Location = new System.Drawing.Point(12, 9);
            this.Name_label.Name = "Name_label";
            this.Name_label.Size = new System.Drawing.Size(153, 20);
            this.Name_label.TabIndex = 18;
            this.Name_label.Text = "Имя пользователя";
            // 
            // Name_textBox
            // 
            this.Name_textBox.Location = new System.Drawing.Point(171, 6);
            this.Name_textBox.MaxLength = 64;
            this.Name_textBox.Name = "Name_textBox";
            this.Name_textBox.Size = new System.Drawing.Size(441, 26);
            this.Name_textBox.TabIndex = 1;
            this.Name_textBox.WordWrap = false;
            this.Name_textBox.KeyDown += new System.Windows.Forms.KeyEventHandler(this.Name_textBox_KeyDown);
            // 
            // Error_label
            // 
            this.Error_label.BackColor = System.Drawing.SystemColors.Control;
            this.Error_label.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.Error_label.ForeColor = System.Drawing.Color.Red;
            this.Error_label.Location = new System.Drawing.Point(111, 71);
            this.Error_label.Name = "Error_label";
            this.Error_label.Size = new System.Drawing.Size(412, 27);
            this.Error_label.TabIndex = 20;
            this.Error_label.Text = "Неверное имя пользователя или пароль";
            this.Error_label.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // Try_count_label
            // 
            this.Try_count_label.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.Try_count_label.ForeColor = System.Drawing.Color.Red;
            this.Try_count_label.Location = new System.Drawing.Point(115, 105);
            this.Try_count_label.Name = "Try_count_label";
            this.Try_count_label.Size = new System.Drawing.Size(408, 27);
            this.Try_count_label.TabIndex = 21;
            this.Try_count_label.Text = "Попыток до блокировки осталось: 2";
            this.Try_count_label.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // Login
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(624, 135);
            this.Controls.Add(this.Try_count_label);
            this.Controls.Add(this.Error_label);
            this.Controls.Add(this.Name_textBox);
            this.Controls.Add(this.Name_label);
            this.Controls.Add(this.Pass_label);
            this.Controls.Add(this.Cancel_button);
            this.Controls.Add(this.Ok_button);
            this.Controls.Add(this.Pass_textBox);
            this.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.Name = "Login";
            this.Text = "Авторизация";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Login_FormClosing);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.TextBox Pass_textBox;
        private System.Windows.Forms.Button Ok_button;
        private System.Windows.Forms.Button Cancel_button;
        private System.Windows.Forms.Label Pass_label;
        private System.Windows.Forms.Label Name_label;
        private System.Windows.Forms.TextBox Name_textBox;
        private System.Windows.Forms.Label Error_label;
        private System.Windows.Forms.Label Try_count_label;
    }
}