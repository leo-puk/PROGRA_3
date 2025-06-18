<%@ Page Title="" Language="C#" MasterPageFile="~/InicionSesion/InicioSesion.Master" AutoEventWireup="true" CodeBehind="Registro.aspx.cs" Inherits="TechShopperWA.InicionSesion.Registro" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="IniciarSesionContent" runat="server">
    <div class="container my-5">
        <div class="row justify-content-center">
            <div class="col-lg-6 col-md-8">
                <div class="card shadow-lg border-0" style="border-radius: 15px; overflow: hidden;">
                    <div class="card-header py-3" style="background-color: #835da2;">
                        <h2 class="text-center mb-0 text-white"><i class="fa fa-user-plus me-1"></i>REGISTRO DE ADMINISTRADOR</h2>
                    </div>
                    <div class="card-body p-4" style="background-color: #f8f9fa;">

                        <div class="mb-3">
                            <label class="form-label fw-bold" style="color: #835da2;">Nombre de usuario</label>
                            <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control form-control-lg" placeholder="Ingrese su nombre de usuario" />
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-bold" style="color: #835da2;">Correo electrónico</label>
                            <asp:TextBox ID="txtEmail" runat="server" TextMode="Email" CssClass="form-control form-control-lg" placeholder="Ingrese su correo" />
                        </div>

                        <div id="divValidacionEmail" style="margin-top: 10px; display: none;">
                            <span id="lblValidacionEmail"
                                  class="alert alert-danger"
                                  style="border-left: 5px solid #dc3545; background-color: #f8d7da; color: #721c24; padding: 10px 15px; border-radius: 8px; font-size: 0.95rem; display: block;"></span>
                        </div>


                        <div class="mb-3">
                            <label class="form-label fw-bold" style="color: #835da2;">Contraseña</label>
                            <div class="input-group">
                                <asp:TextBox ID="txtContraseña" runat="server" TextMode="Password"
                                    CssClass="form-control form-control-lg" placeholder="Cree una contraseña" />
                                <button type="button" class="btn btn-outline-secondary" id="togglePassword" tabindex="-1">
                                    <i class="fas fa-eye"></i>
                                </button>
                            </div>
                        </div>

                        <div id="divValidacionPassword" style="margin-top: 20px; display: none;">
                            <label id="lblValidacionPassword"
                                class="alert alert-danger"
                                style="border-left: 5px solid #dc3545; background-color: #f8d7da; color: #721c24; padding: 10px 15px; border-radius: 8px; font-size: 0.95rem; display: block;">
                            </label>
                        </div>
                    

                        <div class="d-grid gap-2">
                            <asp:Button ID="btnRegistrar" runat="server" CssClass="btn btn-lg fw-bold text-white"
                                Style="background-color: #835da2; border-radius: 50px; border: none;"
                                Text="REGISTRARSE" OnClick="btnRegistrar_Click" />
                        </div>

                        <div class="text-center mt-3">
                            <a href="IniciarSesion.aspx" class="text-decoration-none small" style="color: #835da2;">¿Ya tienes cuenta? Inicia sesión</a>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script>
        $(document).ready(function () {
            const emailInput = $('#<%= txtEmail.ClientID %>');

        emailInput.on('input', function () {
            const email = $(this).val();
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            if (!emailRegex.test(email)) {
                $('#lblValidacionEmail').html("Por favor, ingrese un correo electrónico válido.");
                $('#divValidacionEmail').slideDown();
            } else {
                $('#lblValidacionEmail').html('');
                $('#divValidacionEmail').slideUp();
            }
        });
    });
    </script>
<script>
        $(document).ready(function () {
            $('#<%= txtContraseña.ClientID %>').on('input', function () {
            const password = $(this).val();
            const errores = [];

            if (password.length < 8) {
                errores.push("Debe tener al menos 8 caracteres.");
            }
            if (!/[A-Z]/.test(password)) {
                errores.push("Debe tener al menos una letra mayúscula.");
            }
            if (!/[a-z]/.test(password)) {
                errores.push("Debe tener al menos una letra minúscula.");
            }
            if (!/[0-9]/.test(password)) {
                errores.push("Debe tener al menos un número.");
            }

            if (errores.length > 0) {
                $('#lblValidacionPassword').html(errores.join('<br>'));
                $('#divValidacionPassword').slideDown();
            } else {
                $('#lblValidacionPassword').html('');
                $('#divValidacionPassword').slideUp();
                }
                
        });
    });
    </script>
    <script>
        $(document).ready(function () {
            $('#togglePassword').on('click', function () {
                const input = $('#<%= txtContraseña.ClientID %>');
                const icon = $(this).find('i');

                if (input.attr('type') === 'password') {
                    input.attr('type', 'text');
                    icon.removeClass('fa-eye').addClass('fa-eye-slash');
                } else {
                    input.attr('type', 'password');
                    icon.removeClass('fa-eye-slash').addClass('fa-eye');
                }
            });
        });

    </script>


</asp:Content>
