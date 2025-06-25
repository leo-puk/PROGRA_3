using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace TechShopperWA
{
	public partial class Home : System.Web.UI.Page
	{
		protected void Page_Init(object sender, EventArgs e)
		{
            if (Session["Usuario"] == null)
            {
                Response.Redirect("/InicionSesion/IniciarSesion.aspx");
                return;
            }
        }
	}
}