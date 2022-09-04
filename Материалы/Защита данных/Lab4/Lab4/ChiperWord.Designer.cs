namespace Lab4
{
    partial class ChiperWord
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
            this.label_pass_phrase = new System.Windows.Forms.Label();
            this.textBox_pass_phrase = new System.Windows.Forms.TextBox();
            this.button_pass_phrase = new System.Windows.Forms.Button();
            this.button_exit = new System.Windows.Forms.Button();
            this.textBox_pass_phrase_сonfirm = new System.Windows.Forms.TextBox();
            this.label_pass_phrase_сonfirm = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // label_pass_phrase
            // 
            this.label_pass_phrase.AutoSize = true;
            this.label_pass_phrase.Location = new System.Drawing.Point(13, 17);
            this.label_pass_phrase.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label_pass_phrase.Name = "label_pass_phrase";
            this.label_pass_phrase.Size = new System.Drawing.Size(152, 20);
            this.label_pass_phrase.TabIndex = 0;
            this.label_pass_phrase.Text = "Парольная фраза:";
            // 
            // textBox_pass_phrase
            // 
            this.textBox_pass_phrase.Location = new System.Drawing.Point(173, 14);
            this.textBox_pass_phrase.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.textBox_pass_phrase.MaxLength = 64;
            this.textBox_pass_phrase.Name = "textBox_pass_phrase";
            this.textBox_pass_phrase.PasswordChar = '*';
            this.textBox_pass_phrase.Size = new System.Drawing.Size(310, 26);
            this.textBox_pass_phrase.TabIndex = 1;
            this.textBox_pass_phrase.KeyDown += new System.Windows.Forms.KeyEventHandler(this.textBox_pass_phrase_KeyDown);
            // 
            // button_pass_phrase
            // 
            this.button_pass_phrase.Location = new System.Drawing.Point(347, 91);
            this.button_pass_phrase.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.button_pass_phrase.Name = "button_pass_phrase";
            this.button_pass_phrase.Size = new System.Drawing.Size(136, 35);
            this.button_pass_phrase.TabIndex = 3;
            this.button_pass_phrase.Text = "Расшифровать";
            this.button_pass_phrase.UseVisualStyleBackColor = true;
            this.button_pass_phrase.Click += new System.EventHandler(this.button_pass_phrase_Click);
            // 
            // button_exit
            // 
            this.button_exit.Location = new System.Drawing.Point(17, 91);
            this.button_exit.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.button_exit.Name = "button_exit";
            this.button_exit.Size = new System.Drawing.Size(136, 35);
            this.button_exit.TabIndex = 4;
            this.button_exit.Text = "Выход";
            this.button_exit.UseVisualStyleBackColor = true;
            this.button_exit.Click += new System.EventHandler(this.button_exit_Click);
            // 
            // textBox_pass_phrase_сonfirm
            // 
            this.textBox_pass_phrase_сonfirm.Location = new System.Drawing.Point(173, 57);
            this.textBox_pass_phrase_сonfirm.MaxLength = 64;
            this.textBox_pass_phrase_сonfirm.Name = "textBox_pass_phrase_сonfirm";
            this.textBox_pass_phrase_сonfirm.PasswordChar = '*';
            this.textBox_pass_phrase_сonfirm.Size = new System.Drawing.Size(310, 26);
            this.textBox_pass_phrase_сonfirm.TabIndex = 2;
            this.textBox_pass_phrase_сonfirm.Visible = false;
            this.textBox_pass_phrase_сonfirm.KeyDown += new System.Windows.Forms.KeyEventHandler(this.textBox_pass_phrase_сonfirm_KeyDown);
            // 
            // label_pass_phrase_сonfirm
            // 
            this.label_pass_phrase_сonfirm.AutoSize = true;
            this.label_pass_phrase_сonfirm.Location = new System.Drawing.Point(26, 60);
            this.label_pass_phrase_сonfirm.Name = "label_pass_phrase_сonfirm";
            this.label_pass_phrase_сonfirm.Size = new System.Drawing.Size(139, 20);
            this.label_pass_phrase_сonfirm.TabIndex = 10;
            this.label_pass_phrase_сonfirm.Text = "Подтверждение:";
            this.label_pass_phrase_сonfirm.Visible = false;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.label1.ForeColor = System.Drawing.Color.Red;
            this.label1.Location = new System.Drawing.Point(160, 98);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(0, 20);
            this.label1.TabIndex = 11;
            // 
            // ChiperWord
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(496, 139);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.textBox_pass_phrase_сonfirm);
            this.Controls.Add(this.label_pass_phrase_сonfirm);
            this.Controls.Add(this.button_exit);
            this.Controls.Add(this.button_pass_phrase);
            this.Controls.Add(this.textBox_pass_phrase);
            this.Controls.Add(this.label_pass_phrase);
            this.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.Name = "ChiperWord";
            this.Text = "Расшифровка файла учётных данных";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.ChiperWord_FormClosing);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label_pass_phrase;
        private System.Windows.Forms.TextBox textBox_pass_phrase;
        private System.Windows.Forms.Button button_pass_phrase;
        private System.Windows.Forms.Button button_exit;
        private System.Windows.Forms.TextBox textBox_pass_phrase_сonfirm;
        private System.Windows.Forms.Label label_pass_phrase_сonfirm;
        private System.Windows.Forms.Label label1;
    }
}