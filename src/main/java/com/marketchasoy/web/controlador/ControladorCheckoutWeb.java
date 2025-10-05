package com.marketchasoy.web.controlador;

import com.marketchasoy.web.modelo.entidades.Carrito;
import com.stripe.Stripe;
import com.stripe.exception.StripeException; // Import necesario para ver el error específico
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorCheckoutWeb {
    
    @Value("${stripe.keys.public}")
    private String stripePublicKey;
    
    @Value("${stripe.keys.secret}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
        System.out.println("Stripe API Key inicializada."); // Mensaje para confirmar
    }

    @GetMapping("/checkout")
    public String mostrarPaginaCheckout(Model model, HttpSession session) {
        Carrito carrito = (Carrito) session.getAttribute("carrito");
        if (carrito == null || carrito.getCantidadItems() == 0) {
            return "redirect:/productos";
        }
        
        // Calculamos el monto en centavos para Stripe
        long montoEnCentavos = (long) (carrito.getTotal() * 100);

        System.out.println("---------------- STRIPE DEBUG ----------------");
        System.out.println("Intentando crear PaymentIntent para un monto de: $" + carrito.getTotal() + " ( " + montoEnCentavos + " centavos).");
        
        try {
            PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setCurrency("cop") // Moneda: Pesos Colombianos
                .setAmount(montoEnCentavos) 
                // Puedes añadir estos parámetros para probar con dólares si 'cop' falla
                // .setCurrency("usd")
                // .setAmount(montoEnCentavos) 
                .build();
            
            // ¡Aquí es donde ocurre la llamada a la API de Stripe!
            PaymentIntent intent = PaymentIntent.create(createParams);
            
            System.out.println("¡ÉXITO! PaymentIntent creado. ID: " + intent.getId());
            System.out.println("Client Secret obtenido: " + intent.getClientSecret().substring(0, 10) + "...");
            
            model.addAttribute("stripePublicKey", stripePublicKey);
            model.addAttribute("clientSecret", intent.getClientSecret());
            
        } catch (StripeException e) {
            // --- ¡ESTO ES LO QUE NECESITAMOS VER! ---
            System.err.println("¡ERROR DE STRIPE! No se pudo crear el PaymentIntent.");
            System.err.println("Stripe Error Code: " + e.getStripeError().getCode());
            System.err.println("Mensaje de Stripe: " + e.getStripeError().getMessage());
            e.printStackTrace(); // Muestra toda la traza del error
            // ---------------------------------------
            
            model.addAttribute("error", "Error al iniciar el pago con Stripe. Revisa la consola.");
        } catch (Exception e) {
            System.err.println("¡Error General Inesperado!");
            e.printStackTrace();
            model.addAttribute("error", "Error inesperado.");
        }
        System.out.println("----------------------------------------------");
        
        return "checkout";
    }

    @GetMapping("/resultado-pago")
    public String mostrarResultadoPago() {
        return "resultado"; // Recuerda crear resultado.html aunque sea vacío
    }
}