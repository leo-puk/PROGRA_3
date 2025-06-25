package pe.edu.pucp.techshopper.daoImp.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.text.Normalizer;

import org.json.JSONArray;
import org.json.JSONObject;

public class GeoUtils {

    public static double[] geocodificarDireccion(String direccion) {
        try {
            String encodedDireccion = URLEncoder.encode(direccion, "UTF-8");
            String url = "https://nominatim.openstreetmap.org/search?q=" + encodedDireccion + "&format=json&limit=1";

            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0"); // Obligatorio

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String linea;
            while ((linea = in.readLine()) != null) {
                response.append(linea);
            }
            in.close();

            JSONArray jsonArray = new JSONArray(response.toString());
            if (jsonArray.length() == 0) {
                System.out.println("Dirección no encontrada.");
                return null;
            }

            JSONObject obj = jsonArray.getJSONObject(0);
            double lat = Float.parseFloat(obj.getString("lat"));
            double lon = Float.parseFloat(obj.getString("lon"));

            return new double[]{lat, lon};

        } catch (Exception e) {
            System.out.println("Error al geocodificar: " + e.getMessage());
            return null;
        }
    }

    public static boolean esDireccionEnLima(String direccion) {
        try {
            String encoded = URLEncoder.encode(direccion, "UTF-8");
            String url = "https://nominatim.openstreetmap.org/search?q=" + encoded + "&format=json&limit=1&addressdetails=1";

            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder json = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();

            JSONArray arr = new JSONArray(json.toString());
            if (arr.length() == 0) {
                return false;
            }

            JSONObject obj = arr.getJSONObject(0);
            JSONObject address = obj.getJSONObject("address");

            // Imprimir en consola por depuración
//            System.out.println("---- JSON ADDRESS ----");
//            System.out.println(address.toString(4));

            String pais = address.optString("country", "");
            String ciudad = address.optString("city", "");
            String estado = address.optString("state", "");
            String region = address.optString("region", "");
            String distrito = address.optString("state_district", "");

            return normalizar(pais).equals("peru") &&
                   (
                       normalizar(ciudad).equals("lima") ||
                       normalizar(estado).equals("lima") ||
                       normalizar(region).equals("lima") ||
                       normalizar(distrito).contains("lima")
                   );

        } catch (Exception e) {
            System.out.println("Error validando ubicación: " + e.getMessage());
            return false;
        }
    }

    private static String normalizar(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                         .replaceAll("\\p{M}", "")
                         .toLowerCase();
    }
}
