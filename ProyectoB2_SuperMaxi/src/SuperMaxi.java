
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 * *
 * El objetivo del proyecto es desarrollar un sistema de facturación para el
 * SuperMaxi en Loja. Este sistema deberá permitir la facturación de N
 * productos, considerando precios normales y promocionales cuando existan
 * muchos productos en stock o su fecha de caducidad esté próxima. Además, se
 * deberá realizar una factura que resuma los totales de impuestos a la renta
 * deducibles por productos en las siguientes categorías: Vivienda, Educación,
 * Alimentación, Vestimenta y Salud. Al final del día, se generará una
 * estadística de ventas totales, por productos y categorías, que ayudará a los
 * gerentes del SuperMaxi en la toma de decisiones. Características a
 * considerar:  Gestión de Productos: Implementar métodos para agregar y
 * gestionar productos en el sistema, considerando su cantidad en stock, fecha
 * de caducidad y precios normales y promocionales.  Facturación: Desarrollarun
 * sistema que calcule el monto total de la factura, teniendo en cuenta los
 * precios normales y promocionales, y que muestre un resumen de los impuestos a
 * la renta deducibles por cada categoría de producto.  Estadísticas de Ventas:
 * Generar métodos para recopilar estadísticas de ventas diarias, que incluyan
 * las ventas totales y desgloses por productos y categorías, para tomar
 * decisiones gerenciales.
 */
public class SuperMaxi {

    static String products[][] = {
        // Vivienda
        {"Bombillo", "2.50", "100", "2028-12-31", "Vivienda"},
        {"Pintura", "15.00", "30", "2026-06-30", "Vivienda"},
        {"Cepillo de Piso", "5.00", "50", "2027-12-31", "Vivienda"},
        {"Almohada", "20.00", "20", "2030-01-01", "Vivienda"},
        {"Basurero", "12.00", "40", "2029-12-31", "Vivienda"},
        {"Escoba", "6.00", "60", "2027-12-31", "Vivienda"},
        {"Cortina", "18.00", "25", "2030-06-15", "Vivienda"},
        {"Silla", "45.00", "15", "2032-03-20", "Vivienda"},
        {"Mesa", "80.00", "10", "2032-07-10", "Vivienda"},
        {"Lampara", "25.00", "35", "2029-09-05", "Vivienda"},
        // Educación
        {"Cuaderno", "1.50", "200", "2025-12-31", "Educacion"},
        {"Lapiz", "0.50", "300", "2026-12-31", "Educacion"},
        {"Mochila", "25.00", "60", "2030-01-01", "Educacion"},
        {"Regla", "0.80", "150", "2027-06-30", "Educacion"},
        {"Marcador", "3.00", "100", "2028-12-31", "Educacion"},
        {"Borrador", "0.60", "250", "2026-05-10", "Educacion"},
        {"Sacapuntas", "1.00", "180", "2027-04-15", "Educacion"},
        {"Tijeras", "4.50", "120", "2028-07-30", "Educacion"},
        {"Cartulina", "0.90", "500", "2026-11-20", "Educacion"},
        {"Calculadora", "20.00", "50", "2031-01-01", "Educacion"},
        // Alimentación
        {"Leche", "1.20", "50", "2025-02-15", "Alimentacion"},
        {"Pan", "2.00", "80", "2025-02-20", "Alimentacion"},
        {"Manzanas", "1.50", "100", "2025-02-18", "Alimentacion"},
        {"Yogur", "0.90", "60", "2025-02-25", "Alimentacion"},
        {"Cereal", "4.00", "40", "2025-03-01", "Alimentacion"},
        {"Arroz", "3.00", "70", "2025-06-10", "Alimentacion"},
        {"Azucar", "2.50", "90", "2025-05-15", "Alimentacion"},
        {"Sal", "1.80", "120", "2026-08-30", "Alimentacion"},
        {"Cafe", "6.00", "45", "2025-09-01", "Alimentacion"},
        {"Chocolate", "2.80", "55", "2025-04-20", "Alimentacion"},
        // Vestimenta
        {"Camisa", "15.00", "30", "2030-12-31", "Vestimenta"},
        {"Pantalon", "25.00", "20", "2030-12-31", "Vestimenta"},
        {"Zapatos", "30.00", "15", "2030-12-31", "Vestimenta"},
        {"Cinturon", "10.00", "50", "2030-12-31", "Vestimenta"},
        {"Sombrero", "12.00", "10", "2030-12-31", "Vestimenta"},
        {"Bufanda", "8.00", "25", "2030-12-31", "Vestimenta"},
        {"Guantes", "7.00", "40", "2030-12-31", "Vestimenta"},
        {"Calcetines", "5.00", "60", "2030-12-31", "Vestimenta"},
        {"Chaqueta", "40.00", "15", "2030-12-31", "Vestimenta"},
        {"Vestido", "35.00", "20", "2030-12-31", "Vestimenta"},
        // Salud
        {"Paracetamol", "0.50", "200", "2025-12-31", "Salud"},
        {"Gel", "3.00", "100", "2026-01-01", "Salud"},
        {"Mascarillas", "0.80", "300", "2026-12-31", "Salud"},
        {"Alcohol", "2.50", "150", "2026-06-30", "Salud"},
        {"Termometro", "15.00", "20", "2030-01-01", "Salud"},
        {"Venda", "2.00", "100", "2027-03-01", "Salud"},
        {"Curitas", "1.50", "250", "2026-09-15", "Salud"},
        {"Jabon", "3.20", "180", "2027-12-01", "Salud"},
        {"Shampoo", "5.50", "80", "2028-06-10", "Salud"},
        {"Vitaminas", "12.00", "50", "2029-02-20", "Salud"}
    };

    public static void main(String[] args) {
        Scanner tcl = new Scanner(System.in);
        int filas = products.length, columnas = products[0].length, num;
        String Productos[][] = new String[filas + 5][columnas];
        boolean system = true;
        double deducibles[] = new double[5];
        double ventasCate[] = new double[5];
        double ventas[] = new double[filas + 5];
        String productProms[] = new String[filas];
        copyPro(Productos);

        while (system) {
            System.out.println("BIENVENID@ A");
            System.out.println("---SUPERMAXI---");
            System.out.println("1. PRODUCTOS");
            System.out.println("2. GESTION DE PRODUCTOS ");
            System.out.println("3. PROMOCIONES");
            System.out.println("4. FACTURAR");
            System.out.println("5. ESTADISTICAS");
            System.out.println("6. SALIR SISTEMA");
            System.out.print("INGRESE UNA OPCION: ");
            num = tcl.nextInt();

            switch (num) {
                case 1:
                    System.out.println(mostrarProduct(Productos));
                    generarActualiProms(Productos, productProms);
                    break;
                case 2:
                    GestionProductos(Productos);
                    generarActualiProms(Productos, productProms);
                    break;
                case 3:
                    generarActualiProms(Productos, productProms);
                    presentarProms(Productos, productProms);
                    break;
                case 4:
                    Facturacion(Productos, productProms, ventas, deducibles);
                    generarActualiProms(Productos, productProms);
                    break;
                case 5:
                    dbEstadis(Productos, ventas, ventasCate);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    system = false;
                    break;
                default:
                    System.out.println("!!OPCION INCORREECTA!!");
            }
        }

    }

    //SE COPIAN LOS DATOS DE LA MATRIZ GLOBAL EN LA MATRIZ LOCAL PRODUCTOS[][]
    public static void copyPro(String Productos[][]) {
        for (int i = 0; i < products.length; i++) {
            for (int j = 0; j < products[i].length; j++) {
                Productos[i][j] = products[i][j];
            }
        }
    }
//FUNCION QUE MUESTRA LOS PRODUCTOS

    public static String mostrarProduct(String Productos[][]) {
        String mostrar = "---------------------------------------------------------------------------------\n";
        mostrar += String.format("| %-3s | %-20s | %-8s | %-8s | %-12s | %-12s|\n",
                "Nro", "Nombre", "Precio", "Stock", "Caducidad", "Categoria");
        mostrar += "---------------------------------------------------------------------------------\n";
        for (int i = 0; i < Productos.length; i++) {
            if (Productos[i][0] != null) {  // SOLO MOSTRAR PRODUCTOS NO VACIOS
                mostrar += String.format("| %-3s | %-20s | %-8s | %-8s | %-12s | %-12s|\n",
                        (i + 1), Productos[i][0], Productos[i][1],
                        Productos[i][2], Productos[i][3], Productos[i][4]);

            }
        }
        mostrar += "---------------------------------------------------------------------------------\n";
        return mostrar;
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
        for (int i = 0; i < Productos.length; i++) { //SE VERIFICA SI HAY ESPACIO DISPONIBLE
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
        Productos[pos][0] = tcl.nextLine();
        System.out.print("Ingrese el precio: ");
        Productos[pos][1] = tcl.next();
        System.out.print("Ingrese su stock: ");
        Productos[pos][2] = tcl.next();
        System.out.print("Ingrese fecha de caducidad (YYYY-MM-DD): ");
        Productos[pos][3] = tcl.next();
        System.out.print("Ingrese su categoría(Vivienda/Educacion/Alimentacion/Vestimenta/Salud): ");
        Productos[pos][4] = tcl.next();
        System.out.println("PRODUCTO AGREGADO EXITOSAMENTE!!");

    }

    public static void ModStock(String Productos[][]) {
        Scanner tcl = new Scanner(System.in);
        String nombre;
        System.out.print("Ingrese el nombre del Producto que desea modificar: ");
        nombre = tcl.next();
        for (int i = 0; i < Productos.length; i++) {
            if (Productos[i][0].equalsIgnoreCase(nombre) && Productos[i][0] != null) {  //BUSCA EL PRODUCTO Y VERIFICA QUE NO ESTE VACIO
                System.out.println("Stock actual: " + Productos[i][2]);
                System.out.print("Ingrese el nuevo stock: ");
                Productos[i][2] = tcl.next();
                System.out.println("Stock modificado de manera exitosa");
                return;
            }
        }
        System.out.println("PRODUCTO NO ENCONTRADO");
    }

    public static void generarActualiProms(String Productos[][], String productProms[]) {
        String fecha;
        int stock;
        LocalDate hoy = LocalDate.now();

        for (int i = 0; i < Productos.length; i++) {
            if (Productos[i][0] != null) {
                fecha = Productos[i][3];
                stock = Integer.parseInt(Productos[i][2]);
                LocalDate fechaCaducidad = LocalDate.parse(fecha);
                long diasParaVencer = ChronoUnit.DAYS.between(hoy, fechaCaducidad);

                if (stock > 100 || diasParaVencer <= 30) { //VERIFICA QUE PUEDAN SER PROMOCIONES
                    productProms[i] = Productos[i][0];
                } 
            }
        }
    }

    public static void presentarProms(String Productos[][], String productProms[]) { //IMPRIME Y GUARDA PROMOCIONES
        boolean proms = false;
        System.out.println("\n-------------- PRODUCTOS EN PROMOCIÓN --------------");
        System.out.println("-------------------------------------------------------------");
        System.out.printf("| %-20s | %-8s | %-12s | %-10s |\n", "Producto", "Stock", "Caducidad", "Descuento");
        System.out.println("-------------------------------------------------------------");
        for (int i = 0; i < productProms.length; i++) {
            if (productProms[i] != null) {
                System.out.printf("| %-20s | %-8s | %-12s | %-10s |\n",
                        Productos[i][0], Productos[i][2], Productos[i][3], "10%");
                proms = true;

            }
        }
        if (!proms) {
            System.out.println("NO HAY PROMOCIONES!!");
        }
        System.out.println("-------------------------------------------------------------");
    }

    public static void Facturacion(String Productos[][], String productProms[], double ventas[], double deducibles[]) {
        Scanner tcl = new Scanner(System.in);
        String product;
        int cantidad, stockDispo;
        double precio, subtotal, descuento, total = 0, iva, subtotalFinal = 0;
        boolean buying = true;
        System.out.println("\n----------------------- FACTURACIÓN -----------------------");

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
                            subtotal = (cantidad * precio); //SOLO ES EL PRECIO DEL PRODUCTO
                            descuento = 0;//REINICIAMOS EL DESCUENTO EN 0

                            for (int j = 0; j < productProms.length; j++) {
                                if (productProms[j] != null && productProms[j].equalsIgnoreCase(product)) {
                                    descuento = subtotal * 0.1;
                                    System.out.println("Descuento del 10% aplicado!!");
                                    break;
                                }

                            }
                            subtotalFinal = subtotal - descuento; //EL PRECIO DEL PRODUCTO, RESTANDO EL DESCUENTO
                            ventas[i] += subtotalFinal; // SE ACUMULA PARA USAR POSTERIORMENTE EN ESTADISTICAS

                            Productos[i][2] = String.valueOf(stockDispo - cantidad);//ACTUALIZA EL STOCK DEL PRODUCTO A COMPRAR
                            total += subtotalFinal; // TOTAL SIN IVA
                            System.out.printf("| %-20s | %-8s | %-8s | %-10s |\n", "Producto", "Cantidad", "Subtotal", "Descuento");
                            System.out.println("----------------------------------------------------------");
                            System.out.printf("| %-20s | %-8d | %-8.2f | %-10.2f |\n", product, cantidad, subtotal, descuento);

                            switch (Productos[i][4]) {
                                case "Vivienda":
                                    deducibles[0] += subtotalFinal * 0.325;
                                    break;
                                case "Educacion":
                                    deducibles[1] += subtotalFinal * 0.325;
                                    break;
                                case "Alimentacion":
                                    deducibles[2] += subtotalFinal * 0.325;
                                    break;
                                case "Vestimenta":
                                    deducibles[3] += subtotalFinal * 0.325;
                                    break;
                                case "Salud":
                                    deducibles[4] += subtotalFinal * 1.3;
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

        }
        System.out.println("----------------------------------------------------------");
        iva = total * 0.12; //SACAMOS EL IVA
        total += iva; //TOTAL CON IVA

        System.out.printf("\n%-15s: $%.2f\n", "Subtotal", (total - iva));
        System.out.printf("%-15s: $%.2f\n", "IVA (12%)", iva);
        System.out.printf("%-15s: $%.2f\n", "Total a Pagar", total);

        System.out.println("\n------------------- DEDUCIBLES POR CATEGORÍA -------------------");
        System.out.printf("%-15s: $%.2f\n", "Vivienda", deducibles[0]);
        System.out.printf("%-15s: $%.2f\n", "Educación", deducibles[1]);
        System.out.printf("%-15s: $%.2f\n", "Alimentación", deducibles[2]);
        System.out.printf("%-15s: $%.2f\n", "Vestimenta", deducibles[3]);
        System.out.printf("%-15s: $%.2f\n", "Salud", deducibles[4]);
        System.out.println("----------------------------------------------------------------");
    }

    public static void dbEstadis(String Productos[][], double ventas[], double ventasCate[]) {
        System.out.println("\n-------------------- ESTADÍSTICAS DE VENTAS --------------------");
        double totalVentas = 0;
        // VENTAS POR PRODUCTOS SIN CONTAR EL IVA Y RESTANDOLE EL DESCUENTO SI ES QUE TIENE
        System.out.println("\n------------------ VENTAS POR PRODUCTO ------------------");
        System.out.printf("| %-20s | %-10s |\n", "Producto", "Total Ventas");
        System.out.println("--------------------------------------------------------");
        for (int i = 0; i < Productos.length; i++) {
            if (Productos[i][0] != null && ventas[i] > 0) {
                System.out.printf("| %-20s | $%-9.2f |\n", Productos[i][0], ventas[i]);
                totalVentas += ventas[i];

                // Acumular ventas por categoría
                switch (Productos[i][4]) {
                    case "Vivienda":
                        ventasCate[0] += ventas[i];
                        break;
                    case "Educacion":
                        ventasCate[1] += ventas[i];
                        break;
                    case "Alimentacion":
                        ventasCate[2] += ventas[i];
                        break;
                    case "Vestimenta":
                        ventasCate[3] += ventas[i];
                        break;
                    case "Salud":
                        ventasCate[4] += ventas[i];
                        break;
                }
            }
        }
        System.out.println("--------------------------------------------------------");

        // VENTAS POR CATEGORIA
        System.out.println("\n------------------ VENTAS POR CATEGORIA -----------------");
        System.out.printf("| %-15s | %-10s |\n", "Categoria", "Total Ventas");
        System.out.println("--------------------------------------------------------");
        System.out.printf("| %-15s | $%-9.2f |\n", "Vivienda", ventasCate[0]);
        System.out.printf("| %-15s | $%-9.2f |\n", "Educacion", ventasCate[1]);
        System.out.printf("| %-15s | $%-9.2f |\n", "Alimentacion", ventasCate[2]);
        System.out.printf("| %-15s | $%-9.2f |\n", "Vestimenta", ventasCate[3]);
        System.out.printf("| %-15s | $%-9.2f |\n", "Salud", ventasCate[4]);
        System.out.println("--------------------------------------------------------");

        // VENTAS TOTALES
        System.out.printf("\n%-20s: $%.2f\n", "VENTAS TOTALES", totalVentas);
        System.out.println("--------------------------------------------------------");
    }
}
/***
 * run:
 * BIENVENID@ A
 * ---SUPERMAXI---
 * 1. PRODUCTOS
 * 2. GESTION DE PRODUCTOS 
 * 3. PROMOCIONES
 * 4. FACTURAR
 * 5. ESTADISTICAS
 * 6. SALIR SISTEMA
 * INGRESE UNA OPCION: 1
 * ---------------------------------------------------------------------------------
 * | Nro | Nombre               | Precio   | Stock    | Caducidad    | Categoria   |
 * ---------------------------------------------------------------------------------
 * | 1   | Bombillo             | 2.50     | 100      | 2028-12-31   | Vivienda    |
 * | 2   | Pintura              | 15.00    | 30       | 2026-06-30   | Vivienda    |
 * | 3   | Cepillo de Piso      | 5.00     | 50       | 2027-12-31   | Vivienda    |
 * | 4   | Almohada             | 20.00    | 20       | 2030-01-01   | Vivienda    |
 * | 5   | Basurero             | 12.00    | 40       | 2029-12-31   | Vivienda    |
 * | 6   | Escoba               | 6.00     | 60       | 2027-12-31   | Vivienda    |
 * | 7   | Cortina              | 18.00    | 25       | 2030-06-15   | Vivienda    |
 * | 8   | Silla                | 45.00    | 15       | 2032-03-20   | Vivienda    |
 * | 9   | Mesa                 | 80.00    | 10       | 2032-07-10   | Vivienda    |
 * | 10  | Lampara              | 25.00    | 35       | 2029-09-05   | Vivienda    |
 * | 11  | Cuaderno             | 1.50     | 200      | 2025-12-31   | Educacion   |
 * | 12  | Lapiz                | 0.50     | 300      | 2026-12-31   | Educacion   |
 * | 13  | Mochila              | 25.00    | 60       | 2030-01-01   | Educacion   |
 * | 14  | Regla                | 0.80     | 150      | 2027-06-30   | Educacion   |
 * | 15  | Marcador             | 3.00     | 100      | 2028-12-31   | Educacion   |
 * | 16  | Borrador             | 0.60     | 250      | 2026-05-10   | Educacion   |
 * | 17  | Sacapuntas           | 1.00     | 180      | 2027-04-15   | Educacion   |
 * | 18  | Tijeras              | 4.50     | 120      | 2028-07-30   | Educacion   |
 * | 19  | Cartulina            | 0.90     | 500      | 2026-11-20   | Educacion   |
 * | 20  | Calculadora          | 20.00    | 50       | 2031-01-01   | Educacion   |
 * | 21  | Leche                | 1.20     | 50       | 2025-02-15   | Alimentacion|
 * | 22  | Pan                  | 2.00     | 80       | 2025-02-20   | Alimentacion|
 * | 23  | Manzanas             | 1.50     | 100      | 2025-02-18   | Alimentacion|
 * | 24  | Yogur                | 0.90     | 60       | 2025-02-25   | Alimentacion|
 * | 25  | Cereal               | 4.00     | 40       | 2025-03-01   | Alimentacion|
 * | 26  | Arroz                | 3.00     | 70       | 2025-06-10   | Alimentacion|
 * | 27  | Azucar               | 2.50     | 90       | 2025-05-15   | Alimentacion|
 * | 28  | Sal                  | 1.80     | 120      | 2026-08-30   | Alimentacion|
 * | 29  | Cafe                 | 6.00     | 45       | 2025-09-01   | Alimentacion|
 * | 30  | Chocolate            | 2.80     | 55       | 2025-04-20   | Alimentacion|
 * | 31  | Camisa               | 15.00    | 30       | 2030-12-31   | Vestimenta  |
 * | 32  | Pantalon             | 25.00    | 20       | 2030-12-31   | Vestimenta  |
 * | 33  | Zapatos              | 30.00    | 15       | 2030-12-31   | Vestimenta  |
 * | 34  | Cinturon             | 10.00    | 50       | 2030-12-31   | Vestimenta  |
 * | 35  | Sombrero             | 12.00    | 10       | 2030-12-31   | Vestimenta  |
 * | 36  | Bufanda              | 8.00     | 25       | 2030-12-31   | Vestimenta  |
 * | 37  | Guantes              | 7.00     | 40       | 2030-12-31   | Vestimenta  |
 * | 38  | Calcetines           | 5.00     | 60       | 2030-12-31   | Vestimenta  |
 * | 39  | Chaqueta             | 40.00    | 15       | 2030-12-31   | Vestimenta  |
 * | 40  | Vestido              | 35.00    | 20       | 2030-12-31   | Vestimenta  |
 * | 41  | Paracetamol          | 0.50     | 200      | 2025-12-31   | Salud       |
 * | 42  | Gel                  | 3.00     | 100      | 2026-01-01   | Salud       |
 * | 43  | Mascarillas          | 0.80     | 300      | 2026-12-31   | Salud       |
 * | 44  | Alcohol              | 2.50     | 150      | 2026-06-30   | Salud       |
 * | 45  | Termometro           | 15.00    | 20       | 2030-01-01   | Salud       |
 * | 46  | Venda                | 2.00     | 100      | 2027-03-01   | Salud       |
 * | 47  | Curitas              | 1.50     | 250      | 2026-09-15   | Salud       |
 * | 48  | Jabon                | 3.20     | 180      | 2027-12-01   | Salud       |
 * | 49  | Shampoo              | 5.50     | 80       | 2028-06-10   | Salud       |
 * | 50  | Vitaminas            | 12.00    | 50       | 2029-02-20   | Salud       |
 * ---------------------------------------------------------------------------------

 * BIENVENID@ A
 * ---SUPERMAXI---
 * 1. PRODUCTOS
 * 2. GESTION DE PRODUCTOS 
 * 3. PROMOCIONES
 * 4. FACTURAR
 * 5. ESTADISTICAS
 * 6. SALIR SISTEMA
 * INGRESE UNA OPCION: 3

 * -------------- PRODUCTOS EN PROMOCI�N --------------
 * -------------------------------------------------------------
 * | Producto             | Stock    | Caducidad    | Descuento  |
 * -------------------------------------------------------------
 * | Cuaderno             | 200      | 2025-12-31   | 10%        |
 * | Lapiz                | 300      | 2026-12-31   | 10%        |
 * | Regla                | 150      | 2027-06-30   | 10%        |
 * | Borrador             | 250      | 2026-05-10   | 10%        |
 * | Sacapuntas           | 180      | 2027-04-15   | 10%        |
 * | Tijeras              | 120      | 2028-07-30   | 10%        |
 * | Cartulina            | 500      | 2026-11-20   | 10%        |
 * | Leche                | 50       | 2025-02-15   | 10%        |
 * | Pan                  | 80       | 2025-02-20   | 10%        |
 * | Manzanas             | 100      | 2025-02-18   | 10%        |
 * | Yogur                | 60       | 2025-02-25   | 10%        |
 * | Cereal               | 40       | 2025-03-01   | 10%        |
 * | Sal                  | 120      | 2026-08-30   | 10%        |
 * | Paracetamol          | 200      | 2025-12-31   | 10%        |
 * | Mascarillas          | 300      | 2026-12-31   | 10%        |
 * | Alcohol              | 150      | 2026-06-30   | 10%        |
 * | Curitas              | 250      | 2026-09-15   | 10%        |
 * | Jabon                | 180      | 2027-12-01   | 10%        |
 * -------------------------------------------------------------
 * BIENVENID@ A
 * ---SUPERMAXI---
 * 1. PRODUCTOS
 * 2. GESTION DE PRODUCTOS 
 * 3. PROMOCIONES
 * 4. FACTURAR
 * 5. ESTADISTICAS
 * 6. SALIR SISTEMA
 * INGRESE UNA OPCION: 2
 * ---GESTION DE PRODUCTOS---
 * 1. AGREGAR PRODUCTO
 * 2. MODIFICAR STOCK DE PRODUCTO
 * 3. VOLVER AL MENU PRINCIPAL
 * INGRESE UNA OPCION: 1
 * Ingrese nombre del producto: Medicine
 * Ingrese el precio: 12
 * Ingrese su stock: 12
 * Ingrese fecha de caducidad (YYYY-MM-DD): 2029-12-11
 * Ingrese su categor�a(Vivienda/Educacion/Alimentacion/Vestimenta/Salud): Salud
 * PRODUCTO AGREGADO EXITOSAMENTE!!
 * BIENVENID@ A
 * ---SUPERMAXI---
 * 1. PRODUCTOS
 * 2. GESTION DE PRODUCTOS 
 * 3. PROMOCIONES
 * 4. FACTURAR
 * 5. ESTADISTICAS
 * 6. SALIR SISTEMA
 * INGRESE UNA OPCION: 2
 * ---GESTION DE PRODUCTOS---
 * 1. AGREGAR PRODUCTO
 * 2. MODIFICAR STOCK DE PRODUCTO
 * 3. VOLVER AL MENU PRINCIPAL
 * INGRESE UNA OPCION: 2
 * Ingrese el nombre del Producto que desea modificar: Medicine
 * Stock actual: 12
 * Ingrese el nuevo stock: 10
 * Stock modificado de manera exitosa
 * BIENVENID@ A
 * ---SUPERMAXI---
 * 1. PRODUCTOS
 * 2. GESTION DE PRODUCTOS 
 * 3. PROMOCIONES
 * 4. FACTURAR
 * 5. ESTADISTICAS
 * 6. SALIR SISTEMA
 * INGRESE UNA OPCION: 1
 * ---------------------------------------------------------------------------------
 * | Nro | Nombre               | Precio   | Stock    | Caducidad    | Categoria   |
 * ---------------------------------------------------------------------------------
 * | 1   | Bombillo             | 2.50     | 100      | 2028-12-31   | Vivienda    |
 * | 2   | Pintura              | 15.00    | 30       | 2026-06-30   | Vivienda    |
 * | 3   | Cepillo de Piso      | 5.00     | 50       | 2027-12-31   | Vivienda    |
 * | 4   | Almohada             | 20.00    | 20       | 2030-01-01   | Vivienda    |
 * | 5   | Basurero             | 12.00    | 40       | 2029-12-31   | Vivienda    |
 * | 6   | Escoba               | 6.00     | 60       | 2027-12-31   | Vivienda    |
 * | 7   | Cortina              | 18.00    | 25       | 2030-06-15   | Vivienda    |
 * | 8   | Silla                | 45.00    | 15       | 2032-03-20   | Vivienda    |
 * | 9   | Mesa                 | 80.00    | 10       | 2032-07-10   | Vivienda    |
 * | 10  | Lampara              | 25.00    | 35       | 2029-09-05   | Vivienda    |
 * | 11  | Cuaderno             | 1.50     | 200      | 2025-12-31   | Educacion   |
 * | 12  | Lapiz                | 0.50     | 300      | 2026-12-31   | Educacion   |
 * | 13  | Mochila              | 25.00    | 60       | 2030-01-01   | Educacion   |
 * | 14  | Regla                | 0.80     | 150      | 2027-06-30   | Educacion   |
 * | 15  | Marcador             | 3.00     | 100      | 2028-12-31   | Educacion   |
 * | 16  | Borrador             | 0.60     | 250      | 2026-05-10   | Educacion   |
 * | 17  | Sacapuntas           | 1.00     | 180      | 2027-04-15   | Educacion   |
 * | 18  | Tijeras              | 4.50     | 120      | 2028-07-30   | Educacion   |
 * | 19  | Cartulina            | 0.90     | 500      | 2026-11-20   | Educacion   |
 * | 20  | Calculadora          | 20.00    | 50       | 2031-01-01   | Educacion   |
 * | 21  | Leche                | 1.20     | 50       | 2025-02-15   | Alimentacion|
 * | 22  | Pan                  | 2.00     | 80       | 2025-02-20   | Alimentacion|
 * | 23  | Manzanas             | 1.50     | 100      | 2025-02-18   | Alimentacion|
 * | 24  | Yogur                | 0.90     | 60       | 2025-02-25   | Alimentacion|
 * | 25  | Cereal               | 4.00     | 40       | 2025-03-01   | Alimentacion|
 * | 26  | Arroz                | 3.00     | 70       | 2025-06-10   | Alimentacion|
 * | 27  | Azucar               | 2.50     | 90       | 2025-05-15   | Alimentacion|
 * | 28  | Sal                  | 1.80     | 120      | 2026-08-30   | Alimentacion|
 * | 29  | Cafe                 | 6.00     | 45       | 2025-09-01   | Alimentacion|
 * | 30  | Chocolate            | 2.80     | 55       | 2025-04-20   | Alimentacion|
 * | 31  | Camisa               | 15.00    | 30       | 2030-12-31   | Vestimenta  |
 * | 32  | Pantalon             | 25.00    | 20       | 2030-12-31   | Vestimenta  |
 * | 33  | Zapatos              | 30.00    | 15       | 2030-12-31   | Vestimenta  |
 * | 34  | Cinturon             | 10.00    | 50       | 2030-12-31   | Vestimenta  |
 * | 35  | Sombrero             | 12.00    | 10       | 2030-12-31   | Vestimenta  |
 * | 36  | Bufanda              | 8.00     | 25       | 2030-12-31   | Vestimenta  |
 * | 37  | Guantes              | 7.00     | 40       | 2030-12-31   | Vestimenta  |
 * | 38  | Calcetines           | 5.00     | 60       | 2030-12-31   | Vestimenta  |
 * | 39  | Chaqueta             | 40.00    | 15       | 2030-12-31   | Vestimenta  |
 * | 40  | Vestido              | 35.00    | 20       | 2030-12-31   | Vestimenta  |
 * | 41  | Paracetamol          | 0.50     | 200      | 2025-12-31   | Salud       |
 * | 42  | Gel                  | 3.00     | 100      | 2026-01-01   | Salud       |
 * | 43  | Mascarillas          | 0.80     | 300      | 2026-12-31   | Salud       |
 * | 44  | Alcohol              | 2.50     | 150      | 2026-06-30   | Salud       |
 * | 45  | Termometro           | 15.00    | 20       | 2030-01-01   | Salud       |
 * | 46  | Venda                | 2.00     | 100      | 2027-03-01   | Salud       |
 * | 47  | Curitas              | 1.50     | 250      | 2026-09-15   | Salud       |
 * | 48  | Jabon                | 3.20     | 180      | 2027-12-01   | Salud       |
 * | 49  | Shampoo              | 5.50     | 80       | 2028-06-10   | Salud       |
 * | 50  | Vitaminas            | 12.00    | 50       | 2029-02-20   | Salud       |
 * | 51  | Medicine             | 12       | 10       | 2029-12-11   | Salud       |
 * ---------------------------------------------------------------------------------

 * BIENVENID@ A
 * ---SUPERMAXI---
 * 1. PRODUCTOS
 * 2. GESTION DE PRODUCTOS 
 * 3. PROMOCIONES
 * 4. FACTURAR
 * 5. ESTADISTICAS
 * 6. SALIR SISTEMA
 * INGRESE UNA OPCION: 4

 * ----------------------- FACTURACI�N -----------------------
 * INGRESE EL NOMBRE DEL PRODUCTO O (F) PARA FINALIZAR: Cuaderno
 * Ingrese la cantidad: 1
 * Descuento del 10% aplicado!!
 * | Producto             | Cantidad | Subtotal | Descuento  |
 * ----------------------------------------------------------
 * | Cuaderno             | 1        | 1,50     | 0,15       |
 * INGRESE EL NOMBRE DEL PRODUCTO O (F) PARA FINALIZAR: Silla
 * Ingrese la cantidad: 1
 * | Producto             | Cantidad | Subtotal | Descuento  |
 * ----------------------------------------------------------
 * | Silla                | 1        | 45,00    | 0,00       |
 * INGRESE EL NOMBRE DEL PRODUCTO O (F) PARA FINALIZAR: Pan
 * Ingrese la cantidad: 1
 * Descuento del 10% aplicado!!
 * | Producto             | Cantidad | Subtotal | Descuento  |
 * ----------------------------------------------------------
 * | Pan                  | 1        | 2,00     | 0,20       |
 * INGRESE EL NOMBRE DEL PRODUCTO O (F) PARA FINALIZAR: Gel
 * Ingrese la cantidad: 1
 * | Producto             | Cantidad | Subtotal | Descuento  |
 * ----------------------------------------------------------
 * | Gel                  | 1        | 3,00     | 0,00       |
 * INGRESE EL NOMBRE DEL PRODUCTO O (F) PARA FINALIZAR: Camisa
 * Ingrese la cantidad: 1
 * | Producto             | Cantidad | Subtotal | Descuento  |
 * ----------------------------------------------------------
 * | Camisa               | 1        | 15,00    | 0,00       |
 * INGRESE EL NOMBRE DEL PRODUCTO O (F) PARA FINALIZAR: f
 * ----------------------------------------------------------

 * Subtotal       : $66,15
 * IVA (12%)      : $7,94
 * Total a Pagar  : $74,09

 * ------------------- DEDUCIBLES POR CATEGOR�A -------------------
 * Vivienda       : $14,63
 * Educaci�n      : $0,44
 * Alimentaci�n   : $0,59
 * Vestimenta     : $4,88
 * Salud          : $3,90
 * ----------------------------------------------------------------
 * BIENVENID@ A
 * ---SUPERMAXI---
 * 1. PRODUCTOS
 * 2. GESTION DE PRODUCTOS 
 * 3. PROMOCIONES
 * 4. FACTURAR
 * 5. ESTADISTICAS
 * 6. SALIR SISTEMA
 * INGRESE UNA OPCION: 5

 * -------------------- ESTAD�STICAS DE VENTAS --------------------

 * ------------------ VENTAS POR PRODUCTO ------------------
 * | Producto             | Total Ventas |
 * --------------------------------------------------------
 * | Silla                | $45,00     |
 * | Cuaderno             | $1,35      |
 * | Pan                  | $1,80      |
 * | Camisa               | $15,00     |
 * | Gel                  | $3,00      |
 * --------------------------------------------------------

 * ------------------ VENTAS POR CATEGORIA -----------------
 * | Categoria       | Total Ventas |
 * --------------------------------------------------------
 * | Vivienda        | $45,00     |
 * | Educacion       | $1,35      |
 * | Alimentacion    | $1,80      |
 * | Vestimenta      | $15,00     |
 * | Salud           | $3,00      |
 * --------------------------------------------------------

 * VENTAS TOTALES      : $66,15
 * --------------------------------------------------------
 * BIENVENID@ A
 * ---SUPERMAXI---
 * 1. PRODUCTOS
 * 2. GESTION DE PRODUCTOS 
 * 3. PROMOCIONES
 * 4. FACTURAR
 * 5. ESTADISTICAS
 * 6. SALIR SISTEMA
 * INGRESE UNA OPCION: 6
 * Saliendo...
 * BUILD SUCCESSFUL (total time: 2 minutes 22 seconds)
 */ 