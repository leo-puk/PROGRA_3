<%@ Page Title="" Language="C#" MasterPageFile="~/TechShopperAdmin.Master" AutoEventWireup="true" CodeBehind="Envios.aspx.cs" Inherits="TechShopperWA.HistorialEnvios.Envios" %>
<asp:Content ID="Content1" ContentPlaceHolderID="Titulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
     <div class="card">
    <div class="card-header">
        <h2 class="titulo mb-4 text-center">Historial de Envíos</h2>
    </div>

    <div class="card-body">
         <div class="w-75 mx-auto">
    <asp:GridView ID="gvEnvios" runat="server"
    AllowPaging="true"
    PageSize="10"
    AutoGenerateColumns="false"
    OnPageIndexChanging="gvEnvios_PageIndexChanging"
    OnRowEditing="gvEnvios_RowEditing"
    OnRowCancelingEdit="gvEnvios_RowCancelingEdit"
    OnRowUpdating="gvEnvios_RowUpdating"
    DataKeyNames="IdEnvio"
    CssClass="table table-hover table-responsive table-striped"
    PagerStyle-CssClass="custom-pager"
    PagerSettings-Mode="Numeric"
    PagerSettings-Position="Bottom"
    EmptyDataText="No se encontraron envíos para mostrar.">

    <Columns>
        <asp:TemplateField HeaderText="Acciones">
            <ItemTemplate>
                <asp:LinkButton ID="btnEditar" runat="server" CommandName="Edit" CssClass="btn btn-sm edit-button text-white" ToolTip="Editar">
                    Editar
                </asp:LinkButton>
            </ItemTemplate>
            <EditItemTemplate>
                <asp:LinkButton ID="btnActualizar" runat="server" CommandName="Update" CssClass="btn btn-sm btn-success me-1" ToolTip="Guardar">
                    <i class="bi bi-check-lg"></i>
                </asp:LinkButton>
                <asp:LinkButton ID="btnCancelar" runat="server" CommandName="Cancel" CssClass="btn btn-sm btn-secondary" ToolTip="Cancelar">
                    <i class="bi bi-x-lg"></i>
                </asp:LinkButton>
            </EditItemTemplate>
        </asp:TemplateField>

        <asp:BoundField HeaderText="ID Envío" DataField="IdEnvio" ReadOnly="true" />
        <asp:BoundField HeaderText="Fecha Entrega" DataField="FechaEntregaStr" ReadOnly="true" />

        <asp:TemplateField HeaderText="Empresa Courier">
            <ItemTemplate>
                <%# Eval("EmpresaCourier") %>
            </ItemTemplate>
            <EditItemTemplate>
                <asp:TextBox ID="txtEmpresaCourier" runat="server" 
                             Text='<%# Bind("EmpresaCourier") %>' 
                             CssClass="form-control" />
            </EditItemTemplate>
        </asp:TemplateField>

        <asp:BoundField HeaderText="Precio" DataField="Precio" DataFormatString="S/ {0:N2}" HtmlEncode="false" ReadOnly="true" />
    </Columns>
</asp:GridView>
</div>
    </div>
</div>
</asp:Content>
