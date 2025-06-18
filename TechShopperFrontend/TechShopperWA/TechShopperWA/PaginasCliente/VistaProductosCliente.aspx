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
                    <div class="dropdown">
                        <button class="btn btn-outline-purple dropdown-toggle" type="button" 
                            id="dropdownOrdenar" data-bs-toggle="dropdown" aria-expanded="false">
                            Ordenar por
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownOrdenar">
                            <li><asp:LinkButton ID="lnkMenorPrecio" runat="server" CssClass="dropdown-item" 
                                OnClick="lnkOrdenar_Click" CommandArgument="precio_asc">Menor precio</asp:LinkButton></li>
                            <li><asp:LinkButton ID="lnkMayorPrecio" runat="server" CssClass="dropdown-item" 
                                OnClick="lnkOrdenar_Click" CommandArgument="precio_desc">Mayor precio</asp:LinkButton></li>
                            <li><asp:LinkButton ID="lnkNombreAZ" runat="server" CssClass="dropdown-item" 
                                OnClick="lnkOrdenar_Click" CommandArgument="nombre_asc">Nombre (A-Z)</asp:LinkButton></li>
                            <li><asp:LinkButton ID="lnkNombreZA" runat="server" CssClass="dropdown-item" 
                                OnClick="lnkOrdenar_Click" CommandArgument="nombre_desc">Nombre (Z-A)</asp:LinkButton></li>
                        </ul>
                    </div>
                </div>
                
                <!-- Lista de productos -->
                <div class="row" id="productosContainer" runat="server">
                    <!-- Los productos se cargarán dinámicamente aquí -->
                </div>
                
                <!-- Paginación -->
                <nav aria-label="Page navigation" class="mt-4" id="paginacion" runat="server">
                    <ul class="pagination justify-content-center">
                        <li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1">Anterior</a>
                        </li>
                        <li class="page-item active"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item">
                            <a class="page-link" href="#">Siguiente</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</asp:Content>