
import java.util.Scanner;
/***
 * El objetivo del proyecto es desarrollar un sistema de facturación para el SuperMaxi en
 * Loja. Este sistema deberá permitir la facturación de N productos, considerando precios
 * normales y promocionales cuando existan muchos productos en stock o su fecha de
 * caducidad esté próxima. Además, se deberá realizar una factura que resuma los totales
 * de impuestos a la renta deducibles por productos en las siguientes categorías: Vivienda,
 * Educación, Alimentación, Vestimenta y Salud. Al final del día, se generará una
 * estadística de ventas totales, por productos y categorías, que ayudará a los gerentes del
 * SuperMaxi en la toma de decisiones.
 * Características a considerar:
 *  Gestión de Productos: Implementar métodos para agregar y gestionar productos
 * en el sistema, considerando su cantidad en stock, fecha de caducidad y precios
 * normales y promocionales.
 *  Facturación: Desarrollar un sistema que calcule el monto total de la factura,
 * teniendo en cuenta los precios normales y promocionales, y que muestre un
 * resumen de los impuestos a la renta deducibles por cada categoría de producto.
 *  Estadísticas de Ventas: Generar métodos para recopilar estadísticas de ventas
 * diarias, que incluyan las ventas totales y desgloses por productos y categorías,
 * para tomar decisiones gerenciales.
 */
public class SuperMaxi {
   public static void main(String[] args) {
        Scanner tcl = new Scanner(System.in);
        int filas = 30, columnas = 5, num, proms = 5;
        String Productos[][] = new String[filas][columnas];
        boolean system = true;
        double deducible = 0;
        double ventas[][] = new double[filas][2];
        String productProms[] = new String[proms];
        LlenarMatriz(Productos);
        while (system) {
            System.out.println("BIENVENID@ A");
            System.out.println("---SUPERMAXI---");
            System.out.println("1. PRODUCTOS");
            System.out.println("2. GESTION DE PRODUCTOS ");
            System.out.println("3. PROMOCIONES");
            System.out.println("4. FACTURAR");
            System.out.println("5. CERRAR SISTEMA");
            System.out.print("INGRESE UNA OPCIÓN: ");
            num = tcl.nextInt();

            switch (num) {
                case 1:
                    MostrarProductos(Productos, filas);
                    break;
                case 2:
                    GestionProductos(Productos);
                    break;
                case 3:
                    PromoProduct(Productos, productProms);
                    break;
                case 4:
                    Facturacion(Productos, productProms, ventas);
                    break;
                case 5:
                    dbEstadis(Productos, ventas);
                    break;
                default:
                    System.out.println("!!OPCION INCORREECTA!!");
            }
        }

    }

    public static void LlenarMatriz(String Productos[][]) {
        String productos[][] = {
            // Vivienda
            {"Bombillo LED", "2.50", "100", "2028-12-31", "Vivienda"},
            {"Pintura Acrílica", "15.00", "30", "2026-06-30", "Vivienda"},
            {"Cepillo de Piso", "5.00", "50", "2027-12-31", "Vivienda"},
            {"Almohada Memory Foam", "20.00", "20", "2030-01-01", "Vivienda"},
            {"Cubo de Basura", "12.00", "40", "2029-12-31", "Vivienda"},
            // Educación
            {"Cuaderno A4", "1.50", "200", "2025-12-31", "Educación"},
            {"Lápices HB", "0.50", "300", "2026-12-31", "Educación"},
            {"Mochila Escolar", "25.00", "60", "2030-01-01", "Educación"},
            {"Regla 30cm", "0.80", "150", "2027-06-30", "Educación"},
            {"Marcadores Permanentes", "3.00", "100", "2028-12-31", "Educación"},
            // Alimentación
            {"Leche", "1.20", "50", "2025-02-15", "Alimentación"},
            {"Pan Integral", "2.00", "80", "2025-01-20", "Alimentación"},
            {"Manzanas", "1.50", "100", "2025-01-18", "Alimentación"},
            {"Yogur", "0.90", "60", "2025-01-25", "Alimentación"},
            {"Cereal", "4.00", "40", "2025-03-01", "Alimentación"},
            // Vestimenta
            {"Camisa", "15.00", "30", "2030-12-31", "Vestimenta"},
            {"Pantalón", "25.00", "20", "2030-12-31", "Vestimenta"},
            {"Zapatos", "30.00", "15", "2030-12-31", "Vestimenta"},
            {"Cinturón", "10.00", "50", "2030-12-31", "Vestimenta"},
            {"Sombrero", "12.00", "10", "2030-12-31", "Vestimenta"},
            // Salud
            {"Paracetamol", "0.50", "200", "2025-12-31", "Salud"},
            {"Gel Antibacterial", "3.00", "100", "2026-01-01", "Salud"},
            {"Mascarillas", "0.80", "300", "2026-12-31", "Salud"},
            {"Alcohol", "2.50", "150", "2026-06-30", "Salud"},
            {"Termómetro", "15.00", "20", "2030-01-01", "Salud"}
        };
        for (int i = 0; i < productos.length; i++) {
            for (int j = 0; j < productos[0].length; j++) {
                Productos[i][j] = productos[i][j];

            }

        }
    }
//MODIFICAR ESTE METODO PARA QUE SEA SOLO UNA FUNCION, COMO SE HIZO EN EL SOLUCIONARIO DEL EXAMEN PARCIAL

    public static void MostrarProductos(String Productos[][], int filas) {
        for (int i = 0; i < Productos.length; i++) {
            if (Productos[i][0] == null) {
                System.out.println("ESPACIO RESTANTE: " + (filas - i) + "\n");
                break;
            } else {
                System.out.println("Nombre: " + Productos[i][0] + ", Precio: " + Productos[i][1] + ", Stock: " + Productos[i][2] + ", Caducidad: " + Productos[i][3]);
            }

        }
    }

    public static void GestionProductos(String Productos[][]) {
        Scanner tcl = new Scanner(System.in);
        int n;
        System.out.println("---GESTION DE PRODUCTOS---");
        System.out.println("1. AGREGAR PRODUCTO");
        System.out.println("2. MODIFICAR STOCK DE PRODUCTO");
        System.out.println("3. VOLVER AL MENU PRINCIPAL");
        System.out.print("INGRESE UNA OPCION: ");
        n = tcl.nextInt();
        switch (n) {
            case 1:
                AgregarProducto(Productos);
                break;
            case 2:
                ModStock(Productos);
                break;
            case 3:
                return;
            default:
                System.out.println("OPCION INCORRECTA");

        }

    }

    public static void AgregarProducto(String Productos[][]) {
        Scanner tcl = new Scanner(System.in);
        int pos = -1;
        for (int i = 0; i < Productos.length; i++) {
            if (Productos[i][0] == null) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            System.out.println("No hay espacio para más productos");
            return;
        }
        System.out.print("Ingrese nombre del producto: ");
        Productos[pos][0] = tcl.next();
        System.out.print("Ingrese el precio: ");
        Productos[pos][1] = tcl.next();
        System.out.print("Ingrese su stock: ");
        Productos[pos][2] = tcl.next();
        System.out.print("Ingrese fecha de caducidad (YYYY-MM-DD): ");
        Productos[pos][3] = tcl.next();
        System.out.print("Ingrese su categoría(Vivienda/Educación/Alimentación/Vestimenta/Salud): ");
        Productos[pos][4] = tcl.next();
        System.out.println("PRODUCTO AGREGADO EXITOSAMENTE!!");

    }
//VER SI PUEDE SER MODIFICADO

    public static void ModStock(String Productos[][]) {
        Scanner tcl = new Scanner(System.in);
        String nombre;
        System.out.print("Ingrese el nombre del Producto que desea modificar: ");
        nombre = tcl.next();
        for (int i = 0; i < Productos.length; i++) {
            if (Productos[i][0].equalsIgnoreCase(nombre) && Productos[i][0] != null) {
                System.out.println("Stock actual: " + Productos[i][2]);
                System.out.print("Ingrese el nuevo stock: ");
                Productos[i][2] = tcl.next();
                System.out.println("Stock modificado de manera exitosa");
                return;
            }
        }
        System.out.println("PRODUCTO NO ENCONTRADO");
    }
//VERIFICAR SI PUEDE SER MODIFICADO

    public static void PromoProduct(String Productos[][], String productProms[]) {
        int f, c = 0;
        System.out.println("---PRODUCTOS EN PROMOCION(10% DE DESCUENTO)---");
        for (int i = 0; i < 5; i++) {
            f = (int) (Math.random() * 25);
            System.out.println(Productos[f][0]);
            productProms[i] = Productos[f][0];

        }
    }

    public static void Facturacion(String Productos[][], String productProms[], double ventas[][]) {
        Scanner tcl = new Scanner(System.in);
        String product;
        int cantidad, stockDispo;
        double precio, subtotal = 0, descuento = 0, total = 0, iva = 0;
        double deducibleVivi = 0, deducibleEdu = 0, deducibleAli = 0, deducibleSalu = 0;
        boolean buying = true;

        while (buying) {
            System.out.print("INGRESE EL NOMBRE DEL PRODUCTO O (F) PARA FINALIZAR: ");
            product = tcl.next();
            if (product.equalsIgnoreCase("F")) {
                buying = false;
            } else {
                boolean encontrado = false;
                for (int i = 0; i < Productos.length; i++) {
                    if (Productos[i][0] != null && product.equalsIgnoreCase(Productos[i][0])) {
                        encontrado = true;
                        System.out.print("Ingrese la cantidad: ");
                        cantidad = tcl.nextInt();
                        stockDispo = Integer.parseInt(Productos[i][2]);

                        if (cantidad <= stockDispo) {
                            precio = Double.parseDouble(Productos[i][1]);
                            subtotal = (cantidad * precio);

                            for (int j = 0; j < productProms.length; j++) {
                                if (productProms[j] != null && productProms[j].equalsIgnoreCase(product)) {
                                    descuento = subtotal * 0.1;
                                    System.out.println("Descuento del 10% aplicado!!");
                                    break;
                                }

                            }

                            subtotal = (subtotal - descuento);
                            iva = (subtotal * 0.12);
                            Productos[i][2] = String.valueOf(stockDispo - cantidad);
                            total += subtotal + iva;

                            switch (Productos[i][4]) {
                                case "Vivienda":
                                    deducibleVivi += subtotal * 0.3;
                                    break;
                                case "Educación":
                                    deducibleEdu += subtotal * 0.5;
                                    break;
                                case "Alimentación":
                                    deducibleAli += subtotal * 0.4;
                                    break;
                                case "Salud":
                                    deducibleSalu += subtotal * 0.8;
                                    break;

                            }
                        } else {
                            System.out.println("Stock insuficiente");
                            System.out.println("Disponible: " + stockDispo);
                        }
                        break;
                    }

                }
                if (!encontrado) {
                    System.out.println("Producto inexistente, intente de nuevo!");
                }
            }
            System.out.println("Subtotal: " + subtotal);
            System.out.println("IVA: " + iva);
            System.out.println("Su total a pagar sera de: $" + total);
            System.out.println("--- DEDUCIBLES ---");
            System.out.println("Vivienda: " + deducibleVivi);
            System.out.println("Educacion: " + deducibleEdu);
            System.out.println("Alimentacion: " + deducibleAli);
            System.out.println("Salud: " + deducibleSalu);
        }
    }

    public static void dbEstadis(String Productos[][], double ventas[][]) {
        System.out.println("\n--- ESTADÍSTICAS DE VENTAS ---");
        double totalVentas = 0;
        double ventasVivienda = 0, ventasEducacion = 0, ventasAlimentacion = 0, ventasSalud = 0;

        // Mostrar ventas por producto
        System.out.println("\nVENTAS POR PRODUCTO:");
        for (int i = 0; i < Productos.length; i++) {
            if (Productos[i][0] != null && ventas[i][1] > 0) {
                System.out.println(Productos[i][0] + ": $" + ventas[i][1]);
                totalVentas += ventas[i][1];

                // Acumular ventas por categoría
                switch (Productos[i][4]) {
                    case "Vivienda":
                        ventasVivienda += ventas[i][1];
                        break;
                    case "Educación":
                        ventasEducacion += ventas[i][1];
                        break;
                    case "Alimentación":
                        ventasAlimentacion += ventas[i][1];
                        break;
                    case "Salud":
                        ventasSalud += ventas[i][1];
                        break;
                }
            }
        }

        System.out.println("\nVENTAS POR CATEGORÍA:");
        System.out.println("Vivienda: $" + ventasVivienda);
        System.out.println("Educación: $" + ventasEducacion);
        System.out.println("Alimentación: $" + ventasAlimentacion);
        System.out.println("Salud: $" + ventasSalud);

        System.out.println("\nVENTAS TOTALES: $" + totalVentas);
    } 
}
