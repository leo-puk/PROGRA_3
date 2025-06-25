using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TechShopperBO.MovimientosStockWS;

namespace TechShopperBO
{
    public class MovimientoStockClient
    {
        private MovimientosStockClient movimientosWSClient;

        public MovimientoStockClient()
        {
            movimientosWSClient = new MovimientosStockClient();
        }

        public List<movimientoStockDTO> ListarMovimientos()
        {
            var lista = movimientosWSClient.listarTodosLosMovimientos();
            return lista != null ? new List<movimientoStockDTO>(lista) : new List<movimientoStockDTO>();
        }
        public int RegistrarMovimiento(movimientoStockDTO p, int tipoMov)
        {
            if (tipoMov > 0)
            {
                return movimientosWSClient.registrarMovimiento(
                p.producto.idProducto, "ENTRADA", p.cantidad, p.fechaMovimiento, p.usuario.idUsuario
                );
            }
            else
            {
                return movimientosWSClient.registrarMovimiento(
                p.producto.idProducto, "SALIDA", p.cantidad, p.fechaMovimiento, p.usuario.idUsuario
                );
            }
        }
    }
}
