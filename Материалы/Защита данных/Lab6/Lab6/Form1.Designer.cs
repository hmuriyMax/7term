namespace Lab6
{
    partial class Form1
    {
        /// <summary>
        /// Обязательная переменная конструктора.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Освободить все используемые ресурсы.
        /// </summary>
        /// <param name="disposing">истинно, если управляемый ресурс должен быть удален; иначе ложно.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Код, автоматически созданный конструктором форм Windows

        /// <summary>
        /// Требуемый метод для поддержки конструктора — не изменяйте 
        /// содержимое этого метода с помощью редактора кода.
        /// </summary>
        private void InitializeComponent()
        {
            this.textBox_install_path = new System.Windows.Forms.TextBox();
            this.label_install_path = new System.Windows.Forms.Label();
            this.button_install = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // textBox_install_path
            // 
            this.textBox_install_path.Location = new System.Drawing.Point(13, 105);
            this.textBox_install_path.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.textBox_install_path.Name = "textBox_install_path";
            this.textBox_install_path.ReadOnly = true;
            this.textBox_install_path.Size = new System.Drawing.Size(481, 26);
            this.textBox_install_path.TabIndex = 0;
            this.textBox_install_path.Click += new System.EventHandler(this.textBox_install_path_Click);
            // 
            // label_install_path
            // 
            this.label_install_path.AutoSize = true;
            this.label_install_path.Location = new System.Drawing.Point(17, 79);
            this.label_install_path.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label_install_path.Name = "label_install_path";
            this.label_install_path.Size = new System.Drawing.Size(131, 20);
            this.label_install_path.TabIndex = 1;
            this.label_install_path.Text = "Путь установки:";
            // 
            // button_install
            // 
            this.button_install.Enabled = false;
            this.button_install.Location = new System.Drawing.Point(501, 100);
            this.button_install.Name = "button_install";
            this.button_install.Size = new System.Drawing.Size(111, 31);
            this.button_install.TabIndex = 2;
            this.button_install.Text = "Установить";
            this.button_install.UseVisualStyleBackColor = true;
            this.button_install.Click += new System.EventHandler(this.button_install_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 23);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(136, 20);
            this.label1.TabIndex = 4;
            this.label1.Text = "Раздел реестра:";
            // 
            // textBox1
            // 
            this.textBox1.Location = new System.Drawing.Point(13, 48);
            this.textBox1.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.textBox1.Name = "textBox1";
            this.textBox1.ReadOnly = true;
            this.textBox1.Size = new System.Drawing.Size(481, 26);
            this.textBox1.TabIndex = 5;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(784, 162);
            this.Controls.Add(this.textBox1);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.button_install);
            this.Controls.Add(this.label_install_path);
            this.Controls.Add(this.textBox_install_path);
            this.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.Name = "Form1";
            this.Text = "Установка программы \"Лабораторная №4\"";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox textBox_install_path;
        private System.Windows.Forms.Label label_install_path;
        private System.Windows.Forms.Button button_install;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox textBox1;
    }
}

