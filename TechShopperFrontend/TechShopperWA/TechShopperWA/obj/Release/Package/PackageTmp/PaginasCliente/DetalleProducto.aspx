<%@ Page Title="" Language="C#" MasterPageFile="~/PaginasCliente/MasterCliente.Master" AutoEventWireup="true" CodeBehind="DetalleProducto.aspx.cs" Inherits="TechShopperWA.PaginasCliente.DetalleProducto" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="container py-5">
        <div class="row">
            <!-- Galería de imágenes -->
            <div class="col-lg-6">
                <div class="border rounded-3 p-3 mb-4">
                    <img id="mainImage" runat="server" src="https://via.placeholder.com/600x600"
                        class="img-fluid rounded-3 w-100 mb-3" style="height: 400px; object-fit: contain;" />

                    <div class="d-flex overflow-auto py-2">
                        <asp:Repeater ID="rptThumbnails" runat="server">
                            <ItemTemplate>
                                <asp:Image ID="imgProducto" runat="server" />

                            </ItemTemplate>
                        </asp:Repeater>
                    </div>
                </div>

                <!-- Envío y devoluciones -->
                <div class="card border-0 shadow-sm mb-3">
                    <div class="card-body">
                        <div class="d-flex align-items-center mb-2">
                            <i class="fas fa-truck fs-5 text-purple me-3"></i>
                            <div>
                                <h6 class="mb-0">Envío rápido</h6>
                                <small class="text-muted">Recíbelo en 1-3 días hábiles</small>
                            </div>
                        </div>
                        <div class="d-flex align-items-center">
                            <i class="fas fa-undo fs-5 text-purple me-3"></i>
                            <div>
                                <h6 class="mb-0">Devoluciones fáciles</h6>
                                <small class="text-muted">30 días para cambiar de opinión</small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Información del producto -->
            <div class="col-lg-6">
                <h1 runat="server" id="lblNombre" class="fw-bold mb-3">Nombre del Producto</h1>

                <div class="d-flex align-items-center mb-3">
                    <div class="text-warning me-2">
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star-half-alt"></i>
                    </div>
                </div>

                <div class="d-flex align-items-center mb-4">
                    <span runat="server" id="lblPrecio" class="fs-3 fw-bold text-danger">S/99.99</span>
                </div>

                <div class="mb-4">
                    <h5 class="fw-bold mb-3">Descripción</h5>
                    <p runat="server" id="lblDescripcion" class="mb-0">Descripción detallada del producto...</p>
                </div>

                <div class="mb-4">
                    <h5 class="fw-bold mb-3">Especificaciones</h5>
                    <ul class="list-unstyled" runat="server" id="ulEspecificaciones">
                        <li class="mb-2"><strong>Marca:</strong> <span runat="server" id="lblMarca">Marca</span></li>

                    </ul>
                </div>

                <!-- Selector de cantidad -->
                <div class="d-flex align-items-center mb-4">
                    <h5 class="fw-bold mb-0 me-3">Cantidad:</h5>
                    <div class="input-group" style="width: 120px;">
                        <button class="btn btn-outline-secondary" type="button" onclick="decrementQuantity()">-</button>
                        <asp:TextBox ID="txtCantidad" runat="server" CssClass="form-control text-center" Text="1" />
                        <button class="btn btn-outline-secondary" type="button" onclick="incrementQuantity()">+</button>
                    </div>
                </div>


                <!-- Botones de acción -->
                <div class="d-flex flex-wrap gap-3 mb-4">
                    <asp:Button ID="btnComprarAhora" runat="server" Text="Comprar ahora"
                        CssClass="btn btn-danger flex-grow-1 py-3 fw-bold"
                        OnClick="btnComprarAhora_Click" />

                    <asp:Button ID="btnAgregarCarrito" runat="server" Text="Añadir al carrito"
                        CssClass="btn btn-outline-purple flex-grow-1 py-3 fw-bold"
                        OnClick="btnAgregarCarrito_Click" />

                </div>
            </div>
        </div>

        
    </div>

    <script>
        function incrementQuantity() {
            const input = document.getElementById('<%= txtCantidad.ClientID %>');
        let current = parseInt(input.value) || 1;
        input.value = current + 1;
    }

    function decrementQuantity() {
        const input = document.getElementById('<%= txtCantidad.ClientID %>');
            let current = parseInt(input.value) || 1;
            if (current > 1) {
                input.value = current - 1;
            }
        }
    </script>

</asp:Content>
