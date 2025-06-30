<%@ Page Title="" Language="C#" MasterPageFile="~/TechShopperAdmin.Master" AutoEventWireup="true" CodeBehind="Productos.aspx.cs" Inherits="TechShopperWA.Productos" %>

<asp:Content ID="Content1" ContentPlaceHolderID="Titulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server" >

    <div class="card">

        <div class="card-header">
            <h2 class="titulo" >Gestionar Producto</h2>
        </div>

        <div class="card-body">
            <div class="row">
                <div class="hero-productos">
                    <div class="container row">

                        <div class="col-md-6">
                            <h2 class="titulo">Listado de Productos</h2>
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
                           <asp:GridView ID="gvProducto" runat="server"
                               AllowPaging="true"
                               PageSize="3"
                               AutoGenerateColumns="false"
                               OnPageIndexChanging="gvProducto_PageIndexChanging"
                               CssClass="table table-hover table-responsive table-striped"
                               PagerStyle-CssClass="custom-pager"
                               PagerSettings-Mode="Numeric"
                               PagerSettings-Position="Bottom"
                               EmptyDataText="No se encontraron productos para mostrar.">

                               <Columns>
                                   <asp:BoundField HeaderText="Código" DataField="IdProducto" />
                                   <asp:BoundField HeaderText="Nombre" DataField="Nombre" />
                                   <asp:BoundField HeaderText="Descripcion" DataField="Descripcion" />
                                   <asp:BoundField HeaderText="Categoria" DataField="Categoria" />
                                   <asp:BoundField HeaderText="Marca" DataField="Marca" />
                                   <asp:BoundField HeaderText="StockDisponible" DataField="StockDisponible" />
                                   <asp:BoundField HeaderText="StockMinimo" DataField="StockMinimo" />
                                   <asp:BoundField HeaderText="Precio" DataField="Precio" DataFormatString="S/ {0:N2}" HtmlEncode="false" />

                                   <asp:TemplateField HeaderText="Acciones">
                                       <ItemTemplate>
                                            <div class="d-flex gap-2">
                                                <asp:LinkButton ID="btnVer" runat="server" CommandArgument='<%# Eval("IdProducto") %>' OnClick="btnVer_Click" CssClass="btn btn-sm watch-button">
                                                    <i class="bi bi-eye-fill"></i>
                                                </asp:LinkButton>
                                                <asp:LinkButton ID="btnModificar" runat="server" CommandArgument='<%# Eval("IdProducto") %>' OnClick="btnModificar_Click" CssClass="btn btn-sm edit-button">
                                                    <i class="bi bi-pencil-square"></i>
                                                </asp:LinkButton>
                                                <asp:LinkButton ID="btnEliminar" runat="server" CommandArgument='<%# Eval("IdProducto") %>' OnClick="btnEliminar_Click" CssClass="btn btn-sm erase-button">
                                                    <i class="bi bi-trash"></i>
                                                </asp:LinkButton>
                                                
                                            </div>
                                        </ItemTemplate>
                                   </asp:TemplateField>
                               </Columns>
                           </asp:GridView>

                            <div class="footer-button">
                                  <asp:Button ID="BtnAgregar" runat="server" Text="Agregar" CssClass="btn custom-button" OnClick="btnAgregar_Click" />
                                  <asp:Button ID="BtnVistaCliente" runat="server" Text="VistaClientes" CssClass="btn custom-button" OnClick="btnVistaClientes_Click" />
                             </div>
                       </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="card-footer">
              
         </div>
       
    </div>

   
</asp:Content>
