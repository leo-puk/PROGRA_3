using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TechShopperBO.ClientesWS;
using TechShopperBO.EnviosWS;
using TechShopperBO.ProductosWS;
namespace TechShopperBO
{
    public class EnvioClient
    {
        private EnviosClient enviosWSClient;

        public EnvioClient()
        {
            enviosWSClient = new EnviosClient();
        }

        public List<envioDTO> ListarTodos()
        {
            var lista = enviosWSClient.listarTodosLosEnvios();
            return lista != null ? new List<envioDTO>(lista) : new List<envioDTO>();
        }

        public envioDTO ObtenerPorId(int id)
        {
            return enviosWSClient.obtenerEnvioPorId(id);
        }

        public envioDTO RegistrarEnvio(envioDTO p)
        {
            int idLocal = 1;
            string fechaFormateada = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            return enviosWSClient.crearEnvio(

                fechaFormateada, p.empresaCourier, p.precio, idLocal
            );


        }

        public envioDTO ActualizarEnvioCourier(envioDTO p, int idAdmin)
        {
            return enviosWSClient.actualizarEmpresaCourier(
                p.idEnvio, p.empresaCourier, idAdmin
            );
        }

        public int EliminarProducto(int id)
        {
            return enviosWSClient.eliminarEnvio(id);
        }

        public List<envioDTO> Buscar(string nombre, string categoria, string marca)
        {
            return new List<envioDTO>(enviosWSClient.listarTodosLosEnvios());
        }

        public envioDTO ActualizarEmpresaCourier(int idEnvio, string nuevaEmpresa, int idAdminEditor)
        {
            try
            {
                return enviosWSClient.actualizarEmpresaCourier(idEnvio, nuevaEmpresa, idAdminEditor);
            }
            catch (Exception ex)
            {
                // Puedes registrar logs si lo deseas
                throw new ApplicationException("Error al actualizar la empresa courier", ex);
            }
        }

    }
}
