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
        public int RegistrarMovimiento(int cantidad, int idProd, int idUsuario, int hayVariacion)
        {
            if (hayVariacion > 0)
            {
                return movimientosWSClient.registrarMovimiento(
                idProd, "ENTRADA", cantidad, idUsuario
                );
            }
            else
            {
                return movimientosWSClient.registrarMovimiento(
                idProd, "SALIDA", cantidad, idUsuario
                );
            }
        }
    }
}
