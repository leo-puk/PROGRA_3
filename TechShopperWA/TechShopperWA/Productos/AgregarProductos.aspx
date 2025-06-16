<%@ Page Title="" Language="C#" MasterPageFile="~/TechShopperAdmin.Master" AutoEventWireup="true" CodeBehind="AgregarProductos.aspx.cs" Inherits="TechShopperWA.WebForm1" %>

<asp:Content ID="Content1" ContentPlaceHolderID="Titulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="card mt-2">
        <div class="card-header">

            <h2>Agregar/Modificar</h2>
        </div>
        <div class="card-body">
            <div class="mb-3 row">
                <asp:Label ID="lblIdProducto" runat="server" Text="Código: " CssClass="col-sm-2 col-form-label"></asp:Label>
                <div class="col-sm-8">
                    <asp:TextBox ID="txtCodigo" Enabled="false" runat="server" CssClass="form-control"></asp:TextBox>
                </div>
            </div>
            <div class="mb-3 row">
                <asp:Label ID="lblNombre" runat="server" Text="Nombre: " CssClass="col-sm-2 col-form-label"></asp:Label>
                <div class="col-sm-8">
                    <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control"></asp:TextBox>
                </div>
            </div>
            <div class="mb-3 row">
                <asp:Label ID="lblDescripcion" runat="server" Text="Descripción: " CssClass="col-sm-2 col-form-label"></asp:Label>
                <div class="col-sm-8">
                    <asp:TextBox ID="txtDescripcion" runat="server" CssClass="form-control" TextMode="MultiLine" Rows="2" placeholder="Ingresa la descripción del producto..."></asp:TextBox>
                </div>
            </div>

            <div class="mb-3 row">
                <asp:Label ID="lblCategoria" runat="server" Text="Categoría: " CssClass="col-sm-2 col-form-label"></asp:Label>
                <div class="col-sm-8">
                    <asp:DropDownList ID="ddlCategoria" runat="server" CssClass="form-control">
                    </asp:DropDownList>
                </div>
            </div>

            <div class="mb-3 row">
                <asp:Label ID="lblMarca" runat="server" Text="Marca: " CssClass="col-sm-2 col-form-label"></asp:Label>
                <div class="col-sm-8">
                    <asp:TextBox ID="txtMarca" runat="server" CssClass="form-control"></asp:TextBox>
                </div>
            </div>
            <div class="mb-3 row">
                <asp:Label ID="lblStock" runat="server" Text="Stock: " CssClass="col-sm-2 col-form-label"></asp:Label>
                <div class="col-sm-8">
                    <asp:TextBox ID="txtStock" runat="server" CssClass="form-control"></asp:TextBox>
                </div>
            </div>
            <div class="mb-3 row">
                <asp:Label ID="Label1" runat="server" Text="Precio: " CssClass="col-sm-2 col-form-label"></asp:Label>
                <div class="col-sm-8">
                    <asp:TextBox ID="txtPrecio" runat="server" CssClass="form-control"></asp:TextBox>
                </div>
            </div>
        </div>
        <div class="card-footer">
            <asp:Button ID="btnRegresar" CssClass="float-start btn erase-button" runat="server" Text="Regresar" OnClick="btnRegresar_Click" />
            <asp:Button ID="btnGuardar" CssClass="float-end btn custom-button" runat="server" Text="Guardar" OnClick="btnGuardar_Click" />
        </div>
    </div>
</asp:Content>
