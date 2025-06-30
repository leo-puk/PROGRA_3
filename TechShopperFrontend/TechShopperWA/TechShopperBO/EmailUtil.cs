using System;
using System.Net;
using System.Net.Mail;
using System.Diagnostics;

public static class EmailUtil
{
    private const string SMTP_HOST = "smtp.gmail.com";
    private const int SMTP_PORT = 587;
    private const string EMAIL_FROM = "githappens2025@gmail.com";
    private const string PASSWORD = " ";

    // Método base privado para reutilizar lógica de envío
    private static void EnviarEmail(string destinatario, string nombreUsuario, string asunto, string cuerpoHtml)
    {
        try
        {
            using (var client = new SmtpClient(SMTP_HOST, SMTP_PORT))
            using (var message = new MailMessage())
            {
                client.EnableSsl = true;
                client.UseDefaultCredentials = false;
                client.Credentials = new NetworkCredential(EMAIL_FROM, PASSWORD);
                client.DeliveryMethod = SmtpDeliveryMethod.Network;
                client.Timeout = 20000;

                message.From = new MailAddress(EMAIL_FROM, "TechShopper");
                message.To.Add(new MailAddress(destinatario, nombreUsuario));
                message.Subject = asunto;
                message.IsBodyHtml = true;
                message.Priority = MailPriority.Normal;
                message.Body = cuerpoHtml;

                Debug.WriteLine($"[{DateTime.Now}] Intentando enviar email a {destinatario}...");
                client.Send(message);
                Debug.WriteLine($"[{DateTime.Now}] Email enviado correctamente a {destinatario}");
            }
        }
        catch (SmtpException smtpEx)
        {
            Debug.WriteLine($"[{DateTime.Now}] Error SMTP al enviar email: {smtpEx.Message}");
            throw new Exception($"Error al enviar email: {smtpEx.Message}", smtpEx);
        }
        catch (Exception ex)
        {
            Debug.WriteLine($"[{DateTime.Now}] Error inesperado: {ex.ToString()}");
            throw new Exception("Error inesperado al enviar email: " + ex.Message, ex);
        }
    }

    // Email para verificación de cliente
    public static void EnviarEmailVerificacionCliente(string destinatario, string nombreUsuario)
    {
        string asunto = "¡Registro exitoso en TechShopper!";
        string cuerpo = $@"
        <!DOCTYPE html>
        <html lang='es'>
        <head>
            <meta charset='UTF-8'>
            <meta name='viewport' content='width=device-width, initial-scale=1.0'>
            <title>Confirmación de Registro</title>
            <style>
                body {{
                    font-family: 'Arial', sans-serif;
                    line-height: 1.6;
                    color: #333333;
                    margin: 0;
                    padding: 0;
                    background-color: #f5f5f5;
                }}
                .container {{
                    max-width: 600px;
                    margin: 20px auto;
                    padding: 0;
                    background-color: #ffffff;
                    border-radius: 8px;
                    overflow: hidden;
                    box-shadow: 0 0 10px rgba(0,0,0,0.1);
                }}
                .header {{
                    background-color: #835da2;
                    color: white;
                    padding: 20px;
                    text-align: center;
                }}
                .content {{
                    padding: 25px;
                    color: #333333;
                }}
                .footer {{
                    margin-top: 20px;
                    padding: 15px;
                    font-size: 12px;
                    text-align: center;
                    color: #777777;
                    background-color: #f9f9f9;
                }}
                .logo {{
                    text-align: center;
                    margin-bottom: 20px;
                }}
                .logo img {{
                    max-width: 150px;
                    height: auto;
                }}
                .button {{
                    display: inline-block;
                    padding: 10px 20px;
                    margin: 20px 0;
                    background-color: #3498db;
                    color: white !important;
                    text-decoration: none;
                    border-radius: 4px;
                    font-weight: bold;
                }}
                h1 {{
                    color: white;
                    margin: 0;
                }}
                p {{
                    margin-bottom: 15px;
                }}
            </style>
        </head>
        <body>
            <div class='container'>
                <div class='header'>
                    <h1>¡Bienvenido a TechShopper!</h1>
                </div>
                <div class='content'>
                    <div class='logo'>
                        <img src='https://i.ibb.co/VfB2qyH/githappens.png' alt='TechShopper Logo'>
                    </div>
                    <p>Hola <strong>{nombreUsuario}</strong>,</p>
                    <p>¡Gracias por registrarte en TechShopper! Tu cuenta ha sido creada exitosamente.</p>
                    <p>Ahora puedes acceder a todos nuestros servicios y comenzar a disfrutar de la mejor experiencia de compra tecnológica.</p>
                    <p>Si tienes alguna pregunta o necesitas ayuda, no dudes en contactar a nuestro equipo de soporte.</p>
                    <p style='text-align: center;'>
                        <a href='mailto:soporte@techshopper.com' class='button'>Contactar Soporte</a>
                    </p>
                </div>
                <div class='footer'>
                    <p>© {DateTime.Now.Year} TechShopper. Todos los derechos reservados.</p>
                    <p>
                        <a href='https://techshopper.com' style='color: #777777;'>Visita nuestro sitio web</a> | 
                        <a href='https://techshopper.com/privacidad' style='color: #777777;'>Política de Privacidad</a>
                    </p>
                </div>
            </div>
        </body>
        </html>";

        EnviarEmail(destinatario, nombreUsuario, asunto, cuerpo);
    }

    // Email para verificación de administrador
    public static void EnviarEmailVerificacionAdministrador(string destinatario, string nombreUsuario)
    {
        string asunto = "¡Bienvenido al equipo administrativo de TechShopper!";
        string cuerpo = $@"
        <!DOCTYPE html>
        <html lang='es'>
        <head>
            <meta charset='UTF-8'>
            <meta name='viewport' content='width=device-width, initial-scale=1.0'>
            <title>Bienvenida Administrador</title>
            <style>
                /* Los mismos estilos del método anterior */
                body {{ font-family: 'Arial', sans-serif; line-height: 1.6; color: #333333; margin: 0; padding: 0; background-color: #f5f5f5; }}
                .container {{ max-width: 600px; margin: 20px auto; padding: 0; background-color: #ffffff; border-radius: 8px; overflow: hidden; box-shadow: 0 0 10px rgba(0,0,0,0.1); }}
                .header {{ background-color: #835da2; color: white; padding: 20px; text-align: center; }}
                .content {{ padding: 25px; color: #333333; }}
                .footer {{ margin-top: 20px; padding: 15px; font-size: 12px; text-align: center; color: #777777; background-color: #f9f9f9; }}
                .logo {{ text-align: center; margin-bottom: 20px; }}
                .logo img {{ max-width: 150px; height: auto; }}
                .button {{ display: inline-block; padding: 10px 20px; margin: 20px 0; background-color: #3498db; color: white !important; text-decoration: none; border-radius: 4px; font-weight: bold; }}
                h1 {{ color: white; margin: 0; }}
                p {{ margin-bottom: 15px; }}
            </style>
        </head>
        <body>
            <div class='container'>
                <div class='header'>
                    <h1>¡Bienvenido al equipo, {nombreUsuario}!</h1>
                </div>
                <div class='content'>
                    <div class='logo'>
                        <img src='https://i.ibb.co/VfB2qyH/githappens.png' alt='TechShopper Logo'>
                    </div>
                    <p>Estamos emocionados de tenerte como parte de nuestro equipo administrativo en TechShopper.</p>
                    <p>Tu rol es fundamental para brindar la mejor experiencia a nuestros clientes y mantener nuestra plataforma funcionando perfectamente.</p>
                    <p>En los próximos minutos recibirás un correo con tus credenciales de acceso al sistema administrativo.</p>
                    <p style='text-align: center;'>
                        <a href='mailto:it@techshopper.com' class='button'>Contactar al equipo IT</a>
                    </p>
                </div>
                <div class='footer'>
                    <p>© {DateTime.Now.Year} TechShopper. Todos los derechos reservados.</p>
                    <p>
                        <a href='https://techshopper.com/admin' style='color: #777777;'>Acceso Administrativo</a> | 
                        <a href='https://techshopper.com/privacidad' style='color: #777777;'>Política de Privacidad</a>
                    </p>
                </div>
            </div>
        </body>
        </html>";

        EnviarEmail(destinatario, nombreUsuario, asunto, cuerpo);
    }

    // Email para confirmación de pedido
    public static void EnviarEmailVerificacionPedido(string destinatario, string nombreUsuario)
    {
        string asunto = $"TechShopper - Confirmación de pedido";
        string cuerpo = $@"
        <!DOCTYPE html>
        <html lang='es'>
        <head>
            <meta charset='UTF-8'>
            <meta name='viewport' content='width=device-width, initial-scale=1.0'>
            <title>Confirmación de Pedido</title>
            <style>
                /* Los mismos estilos del método anterior */
                body {{ font-family: 'Arial', sans-serif; line-height: 1.6; color: #333333; margin: 0; padding: 0; background-color: #f5f5f5; }}
                .container {{ max-width: 600px; margin: 20px auto; padding: 0; background-color: #ffffff; border-radius: 8px; overflow: hidden; box-shadow: 0 0 10px rgba(0,0,0,0.1); }}
                .header {{ background-color: #835da2; color: white; padding: 20px; text-align: center; }}
                .content {{ padding: 25px; color: #333333; }}
                .footer {{ margin-top: 20px; padding: 15px; font-size: 12px; text-align: center; color: #777777; background-color: #f9f9f9; }}
                .logo {{ text-align: center; margin-bottom: 20px; }}
                .logo img {{ max-width: 150px; height: auto; }}
                .button {{ display: inline-block; padding: 10px 20px; margin: 20px 0; background-color: #3498db; color: white !important; text-decoration: none; border-radius: 4px; font-weight: bold; }}
                h1 {{ color: white; margin: 0; }}
                p {{ margin-bottom: 15px; }}
                .pedido-info {{ background-color: #f8f9fa; padding: 15px; border-radius: 5px; margin: 20px 0; }}
            </style>
        </head>
        <body>
            <div class='container'>
                <div class='header'>
                    <h1>¡Gracias por tu pedido!</h1>
                </div>
                <div class='content'>
                    <div class='logo'>
                        <img src='https://i.ibb.co/VfB2qyH/githappens.png' alt='TechShopper Logo'>
                    </div>
                    <p>Hola <strong>{nombreUsuario}</strong>,</p>
                    <p>Hemos recibido tu pedido y está siendo procesado.</p>
                    
                    <div class='pedido-info'>
                        <p>Recibirás una notificación cuando tu pedido sea enviado.</p>
                        <p>Puedes consultar el estado de tu pedido en cualquier momento desde tu cuenta.</p>
                    </div>
                    
                    <p>Si tienes alguna duda sobre tu compra, nuestro equipo de atención al cliente está disponible para ayudarte.</p>
                    <p style='text-align: center;'>
                        <a href='mailto:ventas@techshopper.com' class='button'>Consultar pedido</a>
                    </p>
                </div>
                <div class='footer'>
                    <p>© {DateTime.Now.Year} TechShopper. Todos los derechos reservados.</p>
                    <p>
                        <a href='https://techshopper.com/mis-pedidos' style='color: #777777;'>Ver mis pedidos</a> | 
                        <a href='https://techshopper.com/contacto' style='color: #777777;'>Contacto</a>
                    </p>
                </div>
            </div>
        </body>
        </html>";

        EnviarEmail(destinatario, nombreUsuario, asunto, cuerpo);
    }
}