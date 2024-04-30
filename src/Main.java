import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Main {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("****************************");
            System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
            System.out.println("1) Dólar -> Peso Argentino\n" +
                    "2) Peso Argentino -> Dólar\n" +
                    "3) Dólar -> Real Brasileño\n" +
                    "4) Real Brasileño -> Dólar\n" +
                    "5) Dólar -> Peso Colombiano\n" +
                    "6) Peso Colombiano -> Dólar\n" +
                    "7) Salir\n" +
                    "\n" +
                    "Elija una opción valida:\n" +
                    "****************************");

            int opcionConversor = lectura.nextInt();
            switch (opcionConversor) {
                case 1:
                    realizarConversion("USD", "ARS", lectura);
                    break;
                case 2:
                    realizarConversion("ARS", "USD", lectura);
                    break;
                case 3:
                    realizarConversion("USD", "BRL", lectura);
                    break;
                case 4:
                    realizarConversion("BRL", "USD", lectura);
                    break;
                case 5:
                    realizarConversion("USD", "COP", lectura);
                    break;
                case 6:
                    realizarConversion("COP", "USD", lectura);
                    break;
                case 7:
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
        lectura.close();

    }



    public static void realizarConversion(String monedaBase, String monedaObjetivo, Scanner lecturaMoneda) {
        Gson gson = new Gson();

        System.out.println("¿Cuánto desea convertir a " + monedaObjetivo + "?: ");
        double cantidad = lecturaMoneda.nextDouble();

        String apiKey = "52d527bc8b34876b70be90c9";
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + monedaBase;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(url))
                .header("Content-Type", "application/json")
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
            double tipoCambio = jsonObject.getAsJsonObject("conversion_rates").get(monedaObjetivo).getAsDouble();
            double resultado = cantidad * tipoCambio;

            System.out.println(cantidad + " " + monedaBase + " equivale a " + resultado + " " + monedaObjetivo);
        } catch (Exception e) {
            System.out.println("Error al conectarse a la API: " + e.getMessage());
        }
    }

//        ***
//        Sea bienvenido/a al Conversor de Moneda =]
//        1) Dolar -> Peso Argentino
//        2) Peso Argentino -> Dolar
//        3) Dolar -> Real Brasileño
//        4) Real Brasileño -> Dolar
//        5) Dolar -> Peso Colombiano
//        6) Peso Colombiano -> Dolar
//        7) Salir

//        Elija una opcion valida:
//        ***
//        (Aca iria la respuesta)
//
//        Ingrese el valor que desea convertir:
//        (Aca iria la respuesta)
//
//        (Carga el valor)
//        El valor x [USD] corresponde al valor final de >>> x [ARS]

        //Siempre vuelve a cargar asi que debe estar haciendo un while
    }
