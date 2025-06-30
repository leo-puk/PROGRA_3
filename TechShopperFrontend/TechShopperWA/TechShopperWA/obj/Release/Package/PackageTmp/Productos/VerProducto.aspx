<%@ Page Title="" Language="C#" MasterPageFile="~/TechShopperAdmin.Master" AutoEventWireup="true" CodeBehind="VerProducto.aspx.cs" Inherits="TechShopperWA.VerProducto" %>
<asp:Content ID="Content1" ContentPlaceHolderID="Titulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
<div class="card mt-2">
    <div class="card-header">
        <h2 class="titulo">Ver Producto</h2>
    </div>

    <div class="card-body">
        <div class="row mb-3">
            <label class="col-sm-2 fw-bold">Código:</label>
            <div class="col-sm-4">
                <asp:Label ID="lblCodigo" runat="server" CssClass="form-control-plaintext"></asp:Label>
            </div>
        </div>

        <div class="row mb-3">
            <label class="col-sm-2 fw-bold">Nombre:</label>
            <div class="col-sm-4">
                <asp:Label ID="lblNombre" runat="server" CssClass="form-control-plaintext"></asp:Label>
            </div>
        </div>

        <div class="row mb-3">
            <label class="col-sm-2 fw-bold">Descripción:</label>
            <div class="col-sm-6">
                <asp:Label ID="lblDescripcion" runat="server" CssClass="form-control-plaintext"></asp:Label>
            </div>
        </div>

        <div class="row mb-3">
            <label class="col-sm-2 fw-bold">Categoría:</label>
            <div class="col-sm-4">
                <asp:Label ID="lblCategoria" runat="server" CssClass="form-control-plaintext"></asp:Label>
            </div>
        </div>

        <div class="row mb-3">
            <label class="col-sm-2 fw-bold">Marca:</label>
            <div class="col-sm-4">
                <asp:Label ID="lblMarca" runat="server" CssClass="form-control-plaintext"></asp:Label>
            </div>
        </div>

        <div class="row mb-3">
            <label class="col-sm-2 fw-bold">Stock Disponible:</label>
            <div class="col-sm-2">
                <asp:Label ID="lblStockDisponible" runat="server" CssClass="form-control-plaintext"></asp:Label>
            </div>
        </div>

        <div class="row mb-3">
            <label class="col-sm-2 fw-bold">Stock Mínimo:</label>
            <div class="col-sm-2">
                <asp:Label ID="lblStockMinimo" runat="server" CssClass="form-control-plaintext"></asp:Label>
            </div>
        </div>

        <div class="row mb-3">
            <label class="col-sm-2 fw-bold">Precio:</label>
            <div class="col-sm-2">
                <asp:Label ID="lblPrecio" runat="server" CssClass="form-control-plaintext"></asp:Label>
            </div>
        </div>

        <div class="row mb-3">
            <label class="col-sm-2 fw-bold">ID Admin:</label>
            <div class="col-sm-2">
                <asp:Label ID="lblIdAdmin" runat="server" CssClass="form-control-plaintext"></asp:Label>
            </div>
        </div>
        <div class="row mb-3">
            <label class="col-sm-2 fw-bold">Imagen:</label>
            <div class="col-sm-4">
                <asp:PlaceHolder ID="phImagen" runat="server" />
            </div>
        </div>

    </div>

    <div class="card-footer">
        <asp:Button ID="btnRegresar" runat="server" Text="Regresar" CssClass="btn erase-button" OnClick="btnRegresar_Click" />
    </div>
</div>
</asp:Content>
