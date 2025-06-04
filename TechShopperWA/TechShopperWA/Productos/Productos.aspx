<%@ Page Title="" Language="C#" MasterPageFile="~/TechShopperAdmin.Master" AutoEventWireup="true" CodeBehind="Productos.aspx.cs" Inherits="TechShopperWA.Productos" %>

<asp:Content ID="Content1" ContentPlaceHolderID="Titulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">

    <div class="card">

        <div class="card-header">
            <h2>Gestionar Producto</h2>
        </div>

        <div class="card-body">
            <div class="row">
                <div class="hero-productos">
                    <div class="container row">

                        <div class="col-md-6">
                            <h2>Listado de Productos</h2>
                        </div>

                        <div class="input-group col-md-6">
                            <asp:DropDownList ID="ddlCategoria" runat="server" CssClass="form-control" AutoPostBack="true" OnSelectedIndexChanged="ddlCategoria_SelectedIndexChanged">
                                <asp:ListItem Text="-- Todas las Categorías --" Value="" />
                            </asp:DropDownList>
                        </div>
                        <!--Barra de busqueda-->
                        <div class="col-md-6">
                            <div class="input-group mb-3">
                                <asp:TextBox ID="txtBuscar" runat="server" CssClass="form-control" placeholder="Buscar" />
                                <asp:Button ID="btnBuscar" runat="server" Text="Buscar" CssClass="btn custom-button" OnClick="btnBuscar_Click" />
                            </div>
                        </div>
                    
                        <!--Tabla de productos-->
                        <div class="container row">
                            <asp:GridView ID="gvProducto" runat="server" AllowPaging="false" AutoGenerateColumns="false" CssClass="table table-hover table-responsive table-striped">
                                <Columns>
                                    <asp:BoundField HeaderText="Código" DataField="ProductoId" />
                                    <asp:BoundField HeaderText="Nombre" DataField="Nombre" />
                                    <asp:BoundField HeaderText="Descripcion" DataField="Descripcion" />
                                    <asp:BoundField HeaderText="Categoria" DataField="Categoria" />
                                    <asp:BoundField HeaderText="Marca" DataField="Marca" />
                                    <asp:BoundField HeaderText="Stock" DataField="Stock" />
                                    <asp:BoundField HeaderText="Precio" DataField="Precio" DataFormatString="S/ {0:N2}" HtmlEncode="false" />

                                    <asp:TemplateField HeaderText="Acciones">
                                        <ItemTemplate>
                                            <asp:LinkButton ID="btnModificar" runat="server" CommandArgument='<%# Eval("ProductoId") %>' OnClick="btnModificar_Click" CssClass="btn btn-sm edit-button">
                                                <i class="bi bi-pencil-square"></i>
                                            </asp:LinkButton>
                                            <asp:LinkButton ID="btnEliminar" runat="server" CommandArgument='<%# Eval("ProductoId") %>' OnClick="btnEliminar_Click" CssClass="btn btn-sm erase-button">
                                                <i class="bi bi-trash"></i>
                                            </asp:LinkButton>
                                        </ItemTemplate>
                                    </asp:TemplateField>
                                </Columns>
                            </asp:GridView>
                        </div>
                    </div>
                </div>
            </div>

        </div>


        <div class="card-footer">
            <asp:Button ID="BtnAgregar" runat="server" Text="Agregar" CssClass="btn custom-button" OnClick="btnAgregar_Click" />
            <asp:Button ID="BtnCancelar" runat="server" Text="Cancelar" CssClass="btn custom-button" />
        </div>
    </div>
    </div>

   
</asp:Content>
