<%@ Page Title="" Language="C#" MasterPageFile="~/InicionSesion/InicioSesion.Master" AutoEventWireup="true" CodeBehind="IniciarSesion.aspx.cs" Inherits="TechShopperWA.InicionSesion.IniciarSesion1" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="IniciarSesionContent" runat="server">
    <div class="container my-5">
        <div class="row justify-content-center">
            <div class="col-lg-6 col-md-8">
                <!-- Tarjeta de Login -->
                <div class="card shadow-lg border-0" style="border-radius: 15px; overflow: hidden;">
                    <div class="card-header py-3" style="background-color: #835da2;">
                        <h2 class="text-center mb-0 text-white"><i class="fas fa-sign-in-alt me-1"></i> INICIAR SESIÓN</h2>
                    </div>
                    <div class="card-body p-4" style="background-color: #f8f9fa;">
                        
                        <form>
                            <!-- Campo Usuario -->
                            <div class="mb-4">
                                <label for="txtUsuario" class="form-label fw-bold" style="color: #835da2;">Usuario</label>
                                <div class="input-group">
                                    <span class="input-group-text" style="background-color: #cfbdd8; color: #835da2;">
                                        <i class="fas fa-user"></i>
                                    </span>
                                    <asp:TextBox ID="txtUsuario" runat="server"
                                        CssClass="form-control form-control-lg"
                                        placeholder="Ingrese su usuario"
                                        required="true"
                                        Style="border-left: none; border-color: #cfbdd8;" />

                                </div>
                            </div>

                            <!-- Campo Contraseña -->
                            <div class="mb-4">
                                <label for="txtPassword" class="form-label fw-bold" style="color: #835da2;">Contraseña</label>
                                <div class="input-group">
                                    <span class="input-group-text" style="background-color: #cfbdd8; color: #835da2;">
                                        <i class="fas fa-lock"></i>
                                    </span>
                                    <asp:TextBox ID="txtContraseña" runat="server" CssClass="form-control form-control-lg" placeholder="Ingrese su contraseña" 
                                        required="true" Style="border-left: none; border-color: #cfdbb8;" type="password"/>
                                    
                                </div>
                                <div class="text-end mt-2">
                                    <a href="/InicionSesion/OlvideContra.aspx" class="text-decoration-none small" style="color: #835da2;">¿Olvidaste tu contraseña?</a>
                                </div>
                            </div>

                            <!-- Botón de Login -->
                            <div class="d-grid gap-2">
                                <asp:Button ID="btnIngresar" runat="server"
                                    CssClass="btn btn-lg fw-bold text-white"
                                    Style="background-color: #835da2; border-radius: 50px; border: none;"
                                    Text='INGRESAR'
                                    OnClick="btnIngresar_Click" />
                            </div>
                        </form>

                        <!-- Separador -->
                        <div class="position-relative my-4">
                            <hr style="border-top: 2px solid #cfbdd8;">
                            <div class="position-absolute top-50 start-50 translate-middle px-2" style="background-color: #f8f9fa;">
                                <span class="small" style="color: #835da2;">O</span>
                            </div>
                        </div>

                        <!-- Registro -->
                        <div class="text-center">
                            <p class="mb-2" style="color: #835da2;">¿No estás registrado?</p>
                            <a href="Registro.aspx" class="btn btn-lg fw-bold"
                                style="background-color: #cfbdd8; color: #835da2; border-radius: 50px; border: 1px solid #835da2;">Administrador <i class="fas fa-user-plus ms-2"></i>
                            </a>
                            <a href="RegistroCliente.aspx" class="btn btn-lg fw-bold"
                                style="background-color: #cfbdd8; color: #835da2; border-radius: 50px; border: 1px solid #835da2;">Cliente <i class="fas fa-user-plus ms-2"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
