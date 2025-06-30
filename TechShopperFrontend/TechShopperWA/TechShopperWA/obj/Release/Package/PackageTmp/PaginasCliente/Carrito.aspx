<%@ Page Title="Carrito de Compras" Language="C#" MasterPageFile="~/PaginasCliente/MasterCliente.Master" AutoEventWireup="true" CodeBehind="Carrito.aspx.cs" Inherits="TechShopperWA.PaginasCliente.Carrito" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    Carrito de compras
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="container mt-5">
        <h2 class="mb-4">Carrito de Compras</h2>

        <asp:Repeater ID="rptCarrito" runat="server">
            <ItemTemplate>
                <div class="card mb-3">
                    <div class="row g-0">
                        <div class="col-md-2">
                            <img src='<%# Eval("ImagenUrl") %>' class="img-fluid rounded-start" alt="..." />
                        </div>
                        <div class="col-md-10">
    <div class="card-body d-flex justify-content-between align-items-center">
        <div>
            <h5 class="card-title"><%# Eval("Nombre") %></h5>
            <p class="card-text text-muted mb-2">
                Precio: S/<%# Eval("precioUnitario", "{0:N2}") %>
            </p>

            <div class="input-group" style="max-width: 160px;">
                <asp:Button ID="btnMenos" runat="server" Text="–" CommandArgument='<%# Eval("Id") + "|" + Eval("Cantidad") + "|" + Eval("PrecioUnitario") %>'
                    CssClass="btn btn-outline-secondary" OnClick="btnMenos_Click" />

                <asp:TextBox ID="txtCantidad" runat="server" CssClass="form-control text-center"
                    Text='<%# Eval("Cantidad") %>' ReadOnly="true" />

                <asp:Button ID="btnMas" runat="server" Text="+" CommandArgument='<%# Eval("Id") + "|" + Eval("Cantidad") + "|" + Eval("PrecioUnitario") %>'
                    CssClass="btn btn-outline-secondary" OnClick="btnMas_Click" />
            </div>
        </div>
        

        
                                       

                                



                                      <asp:Button ID="Button3" runat="server" Text="🗑"
   CommandArgument='<%# Eval("Id") + "|" + Eval("Cantidad") + "|" + Eval("PrecioUnitario") %>'
CssClass="btn btn-danger"
   OnClick="btnEliminar_Click" />
    </div>
</div>

                    </div>
                </div>
            </ItemTemplate>
        </asp:Repeater>

        <asp:Label ID="lblTotal" runat="server" CssClass="h4 d-block mt-4 text-end" />

        <asp:Button ID="btnFinalizarCompra" runat="server" Text="Hacer Pedido"
            CssClass="btn btn-success mt-3 float-end" OnClick="btnFinalizarCompra_Click" />
    </div>
</asp:Content>
