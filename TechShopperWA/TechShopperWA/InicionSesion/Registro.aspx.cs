using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace TechShopperWA.InicionSesion
{
	public partial class Registro : System.Web.UI.Page
	{
		protected void Page_Load(object sender, EventArgs e)
		{

		}

        protected void btnRegistrar_Click(object sender, EventArgs e)
        {
            string nombre = txtNombre.Text.Trim();
            string email = txtEmail.Text.Trim();
            string contraseña = txtContraseña.Text.Trim();
            string telefono = txtTelefono.Text.Trim();
            string direccion = txtDireccion.Text.Trim();
            string mensaje;
            // Aquí iría la lógica de guardado en la base de datos, por ejemplo con ADO.NET o Entity Framework
            // Validar campos, insertar en la tabla de usuarios...
            if(ValidarPassword(contraseña) && ValidarNombre(nombre) && ValidarEmail(email))
            {

                
                //registrarlo mediante el BO
                //insertar(...., ...., ...);

                // Por ahora puedes mostrar un mensaje o redirigir
                Response.Redirect("IniciarSesion.aspx");

            }

        }

        private bool ValidarEmail(string email)
        {


            // Expresión regular para validar correo electrónico
            string patronEmail = @"^[^@\s]+@[^@\s]+\.[^@\s]+$";

            return Regex.IsMatch(email, patronEmail));
           
        }

        private bool ValidarNombre(string nombre)
        {
            //Se debe verificar que no coincida con ninguno mediante el BO
            return true;
        }

        private bool ValidarPassword(string password)
        {
            List<string> errores = new List<string>();

            if (password.Length < 8)
                errores.Add("Debe tener al menos 8 caracteres.");

            if (!password.Any(char.IsUpper))
                errores.Add("Debe contener al menos una letra mayúscula.");

            if (!password.Any(char.IsLower))
                errores.Add("Debe contener al menos una letra minúscula.");

            if (!password.Any(char.IsDigit))
                errores.Add("Debe contener al menos un número.");

            if (!password.Any(c => "!@#$%^&*()_+-=[]{}|;':\",.<>?/\\~`".Contains(c)))
                errores.Add("Debe contener al menos un carácter especial.");

            if (errores.Count > 0)
            {
                return false;
            }

            return true;
        }
    }
        

    
}