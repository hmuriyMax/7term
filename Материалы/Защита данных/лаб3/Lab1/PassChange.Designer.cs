namespace Lab1
{
    partial class PassChange
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
            this.Old_pass_label = new System.Windows.Forms.Label();
            this.New_pass_label = new System.Windows.Forms.Label();
            this.Confirm_pass_label = new System.Windows.Forms.Label();
            this.Old_pass_textBox = new System.Windows.Forms.TextBox();
            this.New_pass_textBox = new System.Windows.Forms.TextBox();
            this.Confirm_pass_textBox = new System.Windows.Forms.TextBox();
            this.Cancel_button = new System.Windows.Forms.Button();
            this.Ok_button = new System.Windows.Forms.Button();
            this.Try_count_label = new System.Windows.Forms.Label();
            this.Error_label = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // Old_pass_label
            // 
            this.Old_pass_label.AutoSize = true;
            this.Old_pass_label.Location = new System.Drawing.Point(13, 15);
            this.Old_pass_label.Name = "Old_pass_label";
            this.Old_pass_label.Size = new System.Drawing.Size(126, 20);
            this.Old_pass_label.TabIndex = 6;
            this.Old_pass_label.Text = "Старый пароль";
            // 
            // New_pass_label
            // 
            this.New_pass_label.AutoSize = true;
            this.New_pass_label.Location = new System.Drawing.Point(12, 47);
            this.New_pass_label.Name = "New_pass_label";
            this.New_pass_label.Size = new System.Drawing.Size(118, 20);
            this.New_pass_label.TabIndex = 7;
            this.New_pass_label.Text = "Новый пароль";
            // 
            // Confirm_pass_label
            // 
            this.Confirm_pass_label.AutoSize = true;
            this.Confirm_pass_label.Location = new System.Drawing.Point(13, 79);
            this.Confirm_pass_label.Name = "Confirm_pass_label";
            this.Confirm_pass_label.Size = new System.Drawing.Size(174, 20);
            this.Confirm_pass_label.TabIndex = 8;
            this.Confirm_pass_label.Text = "Подтвердить пароль";
            // 
            // Old_pass_textBox
            // 
            this.Old_pass_textBox.Location = new System.Drawing.Point(193, 12);
            this.Old_pass_textBox.MaxLength = 64;
            this.Old_pass_textBox.Name = "Old_pass_textBox";
            this.Old_pass_textBox.PasswordChar = '*';
            this.Old_pass_textBox.Size = new System.Drawing.Size(419, 26);
            this.Old_pass_textBox.TabIndex = 1;
            this.Old_pass_textBox.KeyDown += new System.Windows.Forms.KeyEventHandler(this.Old_pass_textBox_KeyDown);
            // 
            // New_pass_textBox
            // 
            this.New_pass_textBox.Location = new System.Drawing.Point(193, 44);
            this.New_pass_textBox.MaxLength = 64;
            this.New_pass_textBox.Name = "New_pass_textBox";
            this.New_pass_textBox.PasswordChar = '*';
            this.New_pass_textBox.Size = new System.Drawing.Size(419, 26);
            this.New_pass_textBox.TabIndex = 2;
            this.New_pass_textBox.KeyDown += new System.Windows.Forms.KeyEventHandler(this.New_pass_textBox_KeyDown);
            // 
            // Confirm_pass_textBox
            // 
            this.Confirm_pass_textBox.Location = new System.Drawing.Point(193, 76);
            this.Confirm_pass_textBox.MaxLength = 64;
            this.Confirm_pass_textBox.Name = "Confirm_pass_textBox";
            this.Confirm_pass_textBox.PasswordChar = '*';
            this.Confirm_pass_textBox.Size = new System.Drawing.Size(419, 26);
            this.Confirm_pass_textBox.TabIndex = 3;
            this.Confirm_pass_textBox.KeyDown += new System.Windows.Forms.KeyEventHandler(this.Confirm_pass_textBox_KeyDown);
            // 
            // Cancel_button
            // 
            this.Cancel_button.Location = new System.Drawing.Point(12, 134);
            this.Cancel_button.Name = "Cancel_button";
            this.Cancel_button.Size = new System.Drawing.Size(87, 31);
            this.Cancel_button.TabIndex = 5;
            this.Cancel_button.Text = "Отмена";
            this.Cancel_button.UseVisualStyleBackColor = true;
            this.Cancel_button.Click += new System.EventHandler(this.Cancel_button_Click);
            // 
            // Ok_button
            // 
            this.Ok_button.Location = new System.Drawing.Point(525, 134);
            this.Ok_button.Name = "Ok_button";
            this.Ok_button.Size = new System.Drawing.Size(87, 31);
            this.Ok_button.TabIndex = 4;
            this.Ok_button.Text = "Принять";
            this.Ok_button.UseVisualStyleBackColor = true;
            this.Ok_button.Click += new System.EventHandler(this.Ok_button_Click);
            // 
            // Try_count_label
            // 
            this.Try_count_label.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.Try_count_label.ForeColor = System.Drawing.Color.Red;
            this.Try_count_label.Location = new System.Drawing.Point(111, 142);
            this.Try_count_label.Name = "Try_count_label";
            this.Try_count_label.Size = new System.Drawing.Size(408, 27);
            this.Try_count_label.TabIndex = 10;
            this.Try_count_label.Text = "Попыток до деавторизации осталось: 2";
            this.Try_count_label.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // Error_label
            // 
            this.Error_label.BackColor = System.Drawing.SystemColors.Control;
            this.Error_label.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.Error_label.ForeColor = System.Drawing.Color.Red;
            this.Error_label.Location = new System.Drawing.Point(12, 108);
            this.Error_label.Name = "Error_label";
            this.Error_label.Size = new System.Drawing.Size(600, 27);
            this.Error_label.TabIndex = 9;
            this.Error_label.Text = "Не верный пароль!";
            this.Error_label.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // PassChange
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(624, 177);
            this.Controls.Add(this.Try_count_label);
            this.Controls.Add(this.Error_label);
            this.Controls.Add(this.Ok_button);
            this.Controls.Add(this.Cancel_button);
            this.Controls.Add(this.Confirm_pass_textBox);
            this.Controls.Add(this.New_pass_textBox);
            this.Controls.Add(this.Old_pass_textBox);
            this.Controls.Add(this.Confirm_pass_label);
            this.Controls.Add(this.New_pass_label);
            this.Controls.Add(this.Old_pass_label);
            this.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.Name = "PassChange";
            this.Text = "Изменение пароля";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.PassChange_FormClosing);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label Old_pass_label;
        private System.Windows.Forms.Label New_pass_label;
        private System.Windows.Forms.Label Confirm_pass_label;
        private System.Windows.Forms.TextBox Old_pass_textBox;
        private System.Windows.Forms.TextBox New_pass_textBox;
        private System.Windows.Forms.TextBox Confirm_pass_textBox;
        private System.Windows.Forms.Button Cancel_button;
        private System.Windows.Forms.Button Ok_button;
        private System.Windows.Forms.Label Try_count_label;
        private System.Windows.Forms.Label Error_label;
    }
}