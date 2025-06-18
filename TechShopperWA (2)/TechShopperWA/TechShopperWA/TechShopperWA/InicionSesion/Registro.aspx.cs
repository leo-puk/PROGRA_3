using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using TechShopperWA.AdministradoresWS;

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

            

            if (ValidarPassword(contraseña) && ValidarNombre(nombre) && ValidarEmail(email))
            {
                
                // Crear cliente del servicio
                var client = new AdministradoresClient(); // Asumiendo nombre del proxy generado

                
                int resultado = client.registrarAdministrador(contraseña, nombre, email);

                if (resultado >= 0)
                {
                    // Registro exitoso, redirige a login
                    Response.Redirect("IniciarSesion.aspx");
                }
                else
                {
                    // Mostrar mensaje de error
                    ClientScript.RegisterStartupScript(this.GetType(), "alert", "alert('Error al registrar.');", true);
                }
            }

        }

        private bool ValidarEmail(string email)
        {


            // Expresión regular para validar correo electrónico
            string patronEmail = @"^[^@\s]+@[^@\s]+\.[^@\s]+$";

            return Regex.IsMatch(email, patronEmail);
           
        }

        private bool ValidarNombre(string nombre)
        {
            var client = new AdministradoresClient();
            // Suponiendo que tienes un método en el servicio para buscar por nombre o email
            var resultado = client.iniciarSesion(nombre, ""); // Intenta iniciar sesión sin contraseña
            return resultado == null;
           
        }

        private bool ValidarPassword(string password)
        {
            
            List<string> errores = new List<string>();

            if (password.Length < 4)
                errores.Add("Debe tener al menos 8 caracteres.");

            if (!password.Any(char.IsUpper))
                errores.Add("Debe contener al menos una letra mayúscula.");

            //if (!password.Any(char.IsLower))
            //    errores.Add("Debe contener al menos una letra minúscula.");

            if (!password.Any(char.IsDigit))
                errores.Add("Debe contener al menos un número.");

            //if (!password.Any(c => "!@#$%^&*()_+-=[]{}|;':\",.<>?/\\~`".Contains(c)))
            //    errores.Add("Debe contener al menos un carácter especial.");

            if (errores.Count > 0)
            {
                return false;
            }
            return true;
            
        }
    }
        

    
}