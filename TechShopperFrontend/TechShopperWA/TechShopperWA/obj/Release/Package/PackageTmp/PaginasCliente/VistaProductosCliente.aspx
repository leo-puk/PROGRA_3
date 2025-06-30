<%@ Page Title="Inicio" Language="C#" MasterPageFile="~/PaginasCliente/MasterCliente.Master" AutoEventWireup="true" CodeBehind="Index.aspx.cs" Inherits="TechShopperWA.PaginasCliente.Index" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">

</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="container-fluid mt-4">
        <div class="row">
            <!-- Barra lateral de filtros -->
            <div class="col-md-3 col-lg-2 mb-4">
                <div class="card shadow-sm">
                    <div class="card-header bg-purple text-white">
                        <h5 class="mb-0"><i class="fas fa-filter me-2"></i>Filtros</h5>
                    </div>
                    <div class="card-body">
                        <!-- Filtro por nombre -->
                        <div class="mb-3">
                            <label class="form-label fw-bold">Buscar por nombre</label>
                            <asp:TextBox ID="txtNombreFiltro" runat="server" CssClass="form-control" 
                                placeholder="Nombre del producto"></asp:TextBox>
                        </div>
                        
                        <!-- Filtro por rango de precios -->
                        <div class="mb-3">
                            <label class="form-label fw-bold">Rango de precios</label>
                            <div class="d-flex align-items-center justify-content-between mb-2">
                                <asp:TextBox ID="txtPrecioMin" runat="server" CssClass="form-control me-2" 
                                    placeholder="Mínimo" TextMode="Number"></asp:TextBox>
                                <span>a</span>
                                <asp:TextBox ID="txtPrecioMax" runat="server" CssClass="form-control ms-2" 
                                    placeholder="Máximo" TextMode="Number"></asp:TextBox>
                            </div>
                            <asp:RangeValidator ID="rvPrecioMin" runat="server" ControlToValidate="txtPrecioMin"
                                Type="Double" MinimumValue="0" MaximumValue="999999" Display="Dynamic"
                                ErrorMessage="Precio inválido" CssClass="text-danger small"></asp:RangeValidator>
                            <asp:RangeValidator ID="rvPrecioMax" runat="server" ControlToValidate="txtPrecioMax"
                                Type="Double" MinimumValue="0" MaximumValue="999999" Display="Dynamic"
                                ErrorMessage="Precio inválido" CssClass="text-danger small"></asp:RangeValidator>
                        </div>
                        
                        <!-- Filtro por marcas -->
                        <div class="mb-3">

                            <label class="form-label fw-bold">Marcas</label>
                            <asp:CheckBoxList ID="cblMarcas" runat="server" CssClass="form-check">
                            </asp:CheckBoxList>
                        </div>
                        
                        <!-- Botón de aplicar filtros -->
                        <asp:Button ID="btnAplicarFiltros" runat="server" Text="Aplicar Filtros" 
                            CssClass="btn btn-purple w-100" OnClick="btnAplicarFiltros_Click" />
                        
                        <!-- Botón de limpiar filtros -->
                        <asp:Button ID="btnLimpiarFiltros" runat="server" Text="Limpiar Filtros" 
                            CssClass="btn btn-outline-secondary w-100 mt-2" OnClick="btnLimpiarFiltros_Click" />
                    </div>
                </div>
            </div>
            
            <!-- Contenido principal - Lista de productos -->
            <div class="col-md-9 col-lg-10">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h3 class="mb-0">Productos destacados</h3>
                    
                </div>
                
                <!-- Lista de productos -->
                <div class="row" id="productosContainer" runat="server">
                    <!-- Los productos se cargarán dinámicamente aquí -->
                </div>
            </div>
        </div>
    </div>
</asp:Content>