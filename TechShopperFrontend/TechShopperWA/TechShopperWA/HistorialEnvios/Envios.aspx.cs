using System;
using System.Collections.Generic;
using System.Web.UI;
using System.Web.UI.WebControls;
using TechShopperBO;
using TechShopperBO.EnviosWS;

namespace TechShopperWA.HistorialEnvios
{
    public partial class Envios : System.Web.UI.Page
    {
        private EnvioClient client = new EnvioClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            //((TechShopperWA.Site1)this.Master).PaginaActiva = "envios";
            if (!IsPostBack)
            {
                CargarEnvios();
            }
        }

        private void CargarEnvios()
        {
            try
            {
                List<envioDTO> lista = new List<envioDTO>(client.ListarTodos());
                gvEnvios.DataSource = lista;
                gvEnvios.DataBind();
            }
            catch (Exception ex)
            {
                // Mostrar mensaje de error si es necesario
                Console.WriteLine("Error cargando envíos: " + ex.Message);
            }
        }

        protected void gvEnvios_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvEnvios.PageIndex = e.NewPageIndex;
            CargarEnvios();
        }

        protected void gvEnvios_RowEditing(object sender, GridViewEditEventArgs e)
        {
            gvEnvios.EditIndex = e.NewEditIndex;
            CargarEnvios(); // método que recarga los datos en el GridView
        }

        protected void gvEnvios_RowCancelingEdit(object sender, GridViewCancelEditEventArgs e)
        {
            gvEnvios.EditIndex = -1;
            CargarEnvios();
        }

        protected void gvEnvios_RowUpdating(object sender, GridViewUpdateEventArgs e)
        {
            int idEnvio = Convert.ToInt32(gvEnvios.DataKeys[e.RowIndex].Value);
            GridViewRow row = gvEnvios.Rows[e.RowIndex];

            TextBox txtEmpresa = (TextBox)row.FindControl("txtEmpresaCourier");
            if (txtEmpresa == null)
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "error", "alert('Error al obtener el campo de empresa courier.');", true);
                return;
            }

            string nuevaEmpresa = txtEmpresa.Text.Trim();
            int idAdminEditor = (int)Session["IdUsuario"];

            EnvioClient envioBO = new EnvioClient();
            try
            {
                envioDTO actualizado = envioBO.ActualizarEmpresaCourier(idEnvio, nuevaEmpresa, idAdminEditor);
                gvEnvios.EditIndex = -1;
                CargarEnvios();
            }
            catch (Exception ex)
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "error", $"alert('Error: {ex.Message}');", true);
            }
        }

    }
}