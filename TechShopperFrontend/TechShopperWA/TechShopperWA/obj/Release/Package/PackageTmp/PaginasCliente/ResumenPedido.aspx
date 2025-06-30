<%@ Page Title="" Language="C#" MasterPageFile="~/PaginasCliente/MasterCliente.Master" AutoEventWireup="true" CodeBehind="ResumenPedido.aspx.cs" Inherits="TechShopperWA.PaginasCliente.ResumenPedido" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="container py-5">
        <div class="row">
            <div class="col-lg-8">
                <div class="card summary-card mb-4">
                    <div class="card-header bg-white">
                        <h4 class="mb-0"><i class="fas fa-shopping-cart me-2"></i>Resumen de tu pedido</h4>
                    </div>
                    <div class="card-body">
                        <!-- Lista de productos -->
                        <div class="list-group">


                            <!-- Producto 1 -->
                            <asp:Repeater ID="rptProductos" runat="server">
                                <ItemTemplate>
                                    <div class="list-group-item border-0">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <div class="d-flex align-items-center gap-3 p-2">
                                                <!-- Imagen del producto -->
                                                <img src='<%# Eval("ImagenUrl") %>'
                                                    alt='<%# "Imagen de " + Eval("Nombre") %>'
                                                    class="product-img rounded"
                                                    style="width: 64px; height: 64px; object-fit: cover"
                                                    loading="lazy"
                                                    onerror="this.src='../Images/default-product.png'; this.alt='Producto sin imagen'" />

                                                <!-- Detalles del producto -->
                                                <div class="flex-grow-1">
                                                    <h6 class="mb-1 fw-semibold text-truncate" style="max-width: 200px">
                                                        <%# Eval("Nombre") %>
                                                    </h6>
                                                    <div class="d-flex justify-content-between align-items-center">
                                                        <span class="text-muted small">Cantidad: <span class="fw-medium"><%# Eval("Cantidad") %></span>
                                                        </span>

                                                    </div>
                                                </div>
                                            </div>
                                            <div class="text-end">
                                                <h6 class="mb-1">S/ <%# Convert.ToDouble(Eval("PrecioUnitario")).ToString("N2") %></h6>
                                            </div>
                                        </div>
                                    </div>
                                </ItemTemplate>
                            </asp:Repeater>


                        </div>
                    </div>
                </div>

                <!-- Dirección de envío -->
                <div class="card summary-card mb-4">
                    <div class="card-header bg-white">
                        <h4 class="mb-0"><i class="fas fa-map-marker-alt me-2"></i>Dirección de envío</h4>
                    </div>
                    <div class="card-body">
                        <div class="mb-3">
                            <h6 class="mb-1" runat="server" id="lblNombreCliente"></h6>
                        </div>

                        <!-- Teléfono -->
                        <div class="mb-3">
                            <p class="mb-0" runat="server" id="lblTelefono"></p>
                        </div>

                        <!-- Dirección editable con validación -->
<div class="mb-3">
    <label for="txtDireccion" class="form-label fw-bold">Dirección</label>
    <div class="input-group">
        <asp:TextBox ID="txtDireccion" runat="server"
            CssClass="form-control"
            placeholder="Ingrese su dirección de envío"
            Text="Av. Javier Prado Este 1234, San Borja, Lima" />
        <asp:Button ID="btnValidarDireccion" runat="server"
            CssClass="btn btn-outline-success"
            Text="Validar y guardar"
            OnClick="btnValidarDireccion_Click" />
    </div>
    <small class="text-muted fst-italic mt-1 d-block">
        Asegúrese que la dirección sea dentro de Lima, Perú.
    </small>
</div>


                        
                    </div>
                </div>

            </div>

            <!-- Resumen de compra -->
            <div class="col-lg-4">
                <div class="card summary-card sticky-top" style="top: 20px;">
                    <div class="card-header bg-white">
                        <h4 class="mb-0"><i class="fas fa-receipt me-2"></i>Resumen de compra</h4>
                    </div>
                    <div class="card-body">
                        <div class="mb-3">
                            <div class="d-flex justify-content-between mb-2">
                                <span>Subtotal (<asp:Literal ID="litCantidadProductos" runat="server"></asp:Literal>
                                    productos)</span>
                                <span runat="server" id="lblSubtotal">S/ 0.00</span>
                            </div>
                            <div class="d-flex justify-content-between mb-2">
                                <span>Envío</span>
                                <span runat="server" id="lblEnvio">S/ 0.00</span>
                            </div>
                            <div class="d-flex justify-content-between mb-2">
                                <span>Descuento</span>
                                <span class="text-success" runat="server" id="lblDescuento">- S/ 0.00</span>
                            </div>
                            <hr>
                            <div class="d-flex justify-content-between">
                                <h5>Total</h5>
                                <h5 runat="server" id="lblTotal">S/ 0.00</h5>
                            </div>
                            <small class="text-muted">Incluye impuestos</small>
                        </div>

                        <div class="mb-3 shipping-info p-3">
                            <div class="d-flex align-items-center mb-2">
                                <i class="fas fa-truck me-2"></i>
                                <span runat="server" id="lblTipoEnvio">Envío estándar</span>
                            </div>
                            <small class="text-muted" runat="server" id="lblTiempoEntrega">Entrega estimada: 2-3 días hábiles</small>
                        </div>

                        <asp:Button ID="btnFinalizarPedido" runat="server"
                            CssClass="btn btn-confirm w-100 mt-3"
                            Style="background-color: #835da2; color: white; border: none; border-radius: 30px;"
                            Text="Finalizar pedido"
                            OnClick="btnFinalizarPedido_Click" />


                    </div>
                </div>
            </div>
        </div>
</asp:Content>




