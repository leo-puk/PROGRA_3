using System;
using System.Collections;
using System.Collections.Generic;
using TechShopperBO.LocalizacionesWS;


namespace TechShopperBO
{
    public class LocalizacionClient
    {
        private LocalizacionesClient localizacionWSClient;

        public LocalizacionClient()
        {
            localizacionWSClient = new LocalizacionesClient();
        }

        public int insertarLocalizacion(string direccion, double latitud, double longitud, int idUsuario)
        {
            return localizacionWSClient.insertarLocalizacion(direccion, latitud, longitud, idUsuario);
        }

        public int actualizarLocalizacion(int idLocalizacion, string direccion, int idUsuario)
        {
            return localizacionWSClient.actualizarLocalizacion(idLocalizacion, direccion, idUsuario);
        }

        public int eliminarLocalizacion(int idLocalizacion)
        {
            return localizacionWSClient.eliminarLocalizacion(idLocalizacion);
        }

        public localizacionDTO obtenerLocalizacionPorId(int idLocalizacion)
        {
            return localizacionWSClient.obtenerLocalizacionPorId(idLocalizacion);
        }
        public List<localizacionDTO> listarTodasLasLocalizaciones()
        {
            var items = localizacionWSClient.listarTodasLasLocalizaciones();
            return items != null ? new List<localizacionDTO>(items) : new List<localizacionDTO>();
        }
    }
}