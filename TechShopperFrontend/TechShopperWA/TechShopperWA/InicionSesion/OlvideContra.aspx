<%@ Page Title="Recuperar Contraseña" Language="C#" MasterPageFile="~/InicionSesion/InicioSesion.Master" AutoEventWireup="true" CodeBehind="OlvideContra.aspx.cs" Inherits="TechShopperWA.InicionSesion.OlvideContra" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server" />
<asp:Content ID="Content2" ContentPlaceHolderID="IniciarSesionContent" runat="server">
    <div class="container my-5">
        <div class="row justify-content-center">
            <div class="col-lg-6 col-md-8">
                <div class="card shadow-lg border-0" style="border-radius: 15px; overflow: hidden;"">
                    <div class="card-header py-3" style="background-color: #835da2;">
                        <h2 class="text-center mb-0 text-white"><i class="fa fa-key me-1"></i>RECUPERAR CONTRASEÑA</h2>
                    </div>
                    <div class="card-body p-4" style="background-color: #f8f9fa;">
                        
                        <!-- Correo -->
                        <div class="mb-3">
                            <label class="form-label fw-bold" style="color: #835da2;">Correo electrónico</label>
                            <asp:TextBox ID="txtEmail" runat="server" TextMode="Email" CssClass="form-control form-control-lg" placeholder="Ingrese su correo" />
                        </div>

                        <!-- Nueva contraseña -->
                        <div class="mb-3">
                            <label class="form-label fw-bold" style="color: #835da2;">Nueva contraseña</label>
                            <asp:TextBox ID="txtNuevaClave" runat="server" TextMode="Password" CssClass="form-control form-control-lg" placeholder="Ingrese nueva contraseña" />
                        </div>

                        <!-- Confirmar contraseña -->
                        <div class="mb-3">
                            <label class="form-label fw-bold" style="color: #835da2;">Confirmar contraseña</label>
                            <asp:TextBox ID="txtConfirmarClave" runat="server" TextMode="Password" CssClass="form-control form-control-lg" placeholder="Confirme la contraseña" />
                        </div>

                        <!-- Guaradar -->
                        <div class="d-grid gap-2">
                            <asp:Button ID="btnActualizar" runat="server" CssClass="btn btn-lg fw-bold text-white"
                                Style="background-color: #835da2; border-radius: 50px; border: none;"
                                Text="ACTUALIZAR CONTRASEÑA" OnClick="btnActualizar_Click" />
                        </div>

                        <div class="text-center mt-3">
                            <a href="IniciarSesion.aspx" class="text-decoration-none small" style="color: #835da2;">Volver al inicio de sesión</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</asp:Content>