<%@ Page Title="" Language="C#" MasterPageFile="~/TechShopperAdmin.Master" AutoEventWireup="true" CodeBehind="Historial.aspx.cs" Inherits="TechShopperWA.ReporteVentas" %>
<asp:Content ID="Content1" ContentPlaceHolderID="Titulo" runat="server">
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="container mt-5">
        <h2 class="mb-4 text-center">Historial de movimientos de stock</h2>
        <asp:GridView ID="gvMovimientos" runat="server"
            CssClass="table table-bordered table-striped"
            AutoGenerateColumns="False" AllowPaging="true" PageSize="10"
            RowStyle-CssClass="align-middle"
            PagerStyle-CssClass="custom-pager"
            OnPageIndexChanging="gvMovimientos_PageIndexChanging"
            EmptyDataText="No se encontraron movimientos registrados.">
            <Columns>
                <asp:BoundField DataField="idMovimiento" HeaderText="ID Movimiento" />
    
                <asp:TemplateField HeaderText="Producto">
                    <ItemTemplate>
                        <%# Eval("producto.nombre") %>
                    </ItemTemplate>
                </asp:TemplateField>
    
                <asp:TemplateField HeaderText="Tipo">
                    <ItemTemplate>
                        <%# Eval("tipo") %>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:BoundField HeaderText="Fecha" DataField="fechaMovimientoStr" />
                <asp:BoundField DataField="cantidad" HeaderText="Cantidad" />

                <asp:TemplateField HeaderText="Usuario">
                    <ItemTemplate>
                        <%# Eval("usuario.nombre") %>
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
        </asp:GridView>
    </div>
</asp:Content>