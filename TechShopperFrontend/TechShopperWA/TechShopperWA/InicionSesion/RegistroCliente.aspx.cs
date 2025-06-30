using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web.UI;
using TechShopperBO;
using TechShopperBO.ClientesWS;

namespace TechShopperWA.InicionSesion
{
    public partial class RegistroCliente : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnRegistrar_Click(object sender, EventArgs e)
        {
            string nombre = txtNombre.Text.Trim();
            string email = txtEmail.Text.Trim();
            string contraseña = txtContraseña.Text.Trim();
            string direccion = txtDireccion.Text.Trim();
            string telefono = txtTelefono.Text.Trim();


            if (ValidarPassword(contraseña) && ValidarNombre(nombre) && ValidarEmail(email))
            {

                // Crear cliente del servicio
                var clientAdmin = new AdministradorClient();
                var clientCli = new ClienteClient();
                string dominio = email.Split('@').Length > 1 ? email.Split('@')[1] : "";


                if (!Regex.IsMatch(telefono, @"^\d{7,15}$"))
                {
                    ClientScript.RegisterStartupScript(this.GetType(), "alert", "alert('Teléfono inválido. Solo números y entre 7 y 15 dígitos.');", true);
                    return;
                }

                int resultado = -1;
                if (dominio == "pucp.edu.pe")
                {
                    resultado = clientAdmin.RegistrarAdministrador(contraseña, nombre, email);
                }
                else
                {
                    clienteDTO cli = new clienteDTO();
                    cli.nombre = nombre;
                    cli.email = email;
                    cli.direccion = direccion;
                    cli.telefono = telefono;
                    cli.contraseña = contraseña;
                    cli.balanceCuenta = 0; // Asignar un balance inicial
                    cli.infoTarjetaCredito = ""; // Asignar un valor por defecto o vacío
                    resultado = clientCli.RegistrarCliente(cli);
                }

                if (!Regex.IsMatch(telefono, @"^\d{7,15}$"))
                {
                    ClientScript.RegisterStartupScript(this.GetType(), "alert", "alert('Teléfono inválido. Solo números y entre 7 y 15 dígitos.');", true);
                    return;
                }

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
            string patronEmail = @"^[a-zA-Z0-9_-]+@(gmail\.com)$";

            return Regex.IsMatch(email, patronEmail);

        }

        private bool ValidarNombre(string nombre)
        {
            var client = new AdministradorClient();
            // Suponiendo que tienes un método en el servicio para buscar por nombre o email
            var resultado = client.IniciarSesion(nombre, ""); // Intenta iniciar sesión sin contraseña
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