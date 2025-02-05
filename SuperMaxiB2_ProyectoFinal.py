from datetime import datetime
# Menú principal
def main():
    ventas = []  
    ventasCate = [0.0, 0.0, 0.0, 0.0, 0.0]  
    deducibles = [0.0, 0.0, 0.0, 0.0, 0.0]  

    while True:
        print("BIENVENID@ A")
        print("---SUPERMAXI---")
        print("1. PRODUCTOS")
        print("2. GESTION DE PRODUCTOS")
        print("3. PROMOCIONES")
        print("4. FACTURAR")
        print("5. ESTADISTICAS")
        print("6. SALIR SISTEMA")
        opcion = int(input("INGRESE UNA OPCION: "))
        if opcion == 1:
            mostrar_productos()
        elif opcion == 2:
            gestion_productos()
        elif opcion == 3:
            promociones = generar_promociones()
            mostrar_promociones(promociones)
        elif opcion == 4:
            promociones = generar_promociones()
            facturacion(promociones, ventas, ventasCate, deducibles)
        elif opcion == 5:
            estadisticas(ventas, ventasCate)
        elif opcion == 6:
            print("Saliendo...")
            break
        else:
            print("!!OPCION INCORRECTA!!")
            
#LISTA DE PRODUCTOS
products = [
    # Vivienda
    ["Bombillo", 2.50, 100, "2028-12-31", "Vivienda"],
    ["Pintura", 15.00, 30, "2026-06-30", "Vivienda"],
    ["Cepillo de Piso", 5.00, 50, "2027-12-31", "Vivienda"],
    ["Almohada", 20.00, 20, "2030-01-01", "Vivienda"],
    ["Basurero", 12.00, 40, "2029-12-31", "Vivienda"],
    ["Escoba", 6.00, 60, "2027-12-31", "Vivienda"],
    ["Cortina", 18.00, 25, "2030-06-15", "Vivienda"],
    ["Silla", 45.00, 15, "2032-03-20", "Vivienda"],
    ["Mesa", 80.00, 10, "2032-07-10", "Vivienda"],
    ["Lampara", 25.00, 35, "2029-09-05", "Vivienda"],
    # Educación
    ["Cuaderno", 1.50, 200, "2025-12-31", "Educacion"],
    ["Lapiz", 0.50, 300, "2026-12-31", "Educacion"],
    ["Mochila", 25.00, 60, "2030-01-01", "Educacion"],
    ["Regla", 0.80, 150, "2027-06-30", "Educacion"],
    ["Marcador", 3.00, 100, "2028-12-31", "Educacion"],
    ["Borrador", 0.60, 250, "2026-05-10", "Educacion"],
    ["Sacapuntas", 1.00, 180, "2027-04-15", "Educacion"],
    ["Tijeras", 4.50, 120, "2028-07-30", "Educacion"],
    ["Cartulina", 0.90, 500, "2026-11-20", "Educacion"],
    ["Calculadora", 20.00, 50, "2031-01-01", "Educacion"],
    # Alimentación
    ["Leche", 1.20, 50, "2025-02-15", "Alimentacion"],
    ["Pan", 2.00, 80, "2025-02-20", "Alimentacion"],
    ["Manzanas", 1.50, 100, "2025-02-18", "Alimentacion"],
    ["Yogur", 0.90, 60, "2025-02-25", "Alimentacion"],
    ["Cereal", 4.00, 40, "2025-03-01", "Alimentacion"],
    ["Arroz", 3.00, 70, "2025-06-10", "Alimentacion"],
    ["Azucar", 2.50, 90, "2025-05-15", "Alimentacion"],
    ["Sal", 1.80, 120, "2026-08-30", "Alimentacion"],
    ["Cafe", 6.00, 45, "2025-09-01", "Alimentacion"],
    ["Chocolate", 2.80, 55, "2025-04-20", "Alimentacion"],
    # Vestimenta
    ["Camisa", 15.00, 30, "2030-12-31", "Vestimenta"],
    ["Pantalon", 25.00, 20, "2030-12-31", "Vestimenta"],
    ["Zapatos", 30.00, 15, "2030-12-31", "Vestimenta"],
    ["Cinturon", 10.00, 50, "2030-12-31", "Vestimenta"],
    ["Sombrero", 12.00, 10, "2030-12-31", "Vestimenta"],
    ["Bufanda", 8.00, 25, "2030-12-31", "Vestimenta"],
    ["Guantes", 7.00, 40, "2030-12-31", "Vestimenta"],
    ["Calcetines", 5.00, 60, "2030-12-31", "Vestimenta"],
    ["Chaqueta", 40.00, 15, "2030-12-31", "Vestimenta"],
    ["Vestido", 35.00, 20, "2030-12-31", "Vestimenta"],
    # Salud
    ["Paracetamol", 0.50, 200, "2025-12-31", "Salud"],
    ["Gel", 3.00, 100, "2026-01-01", "Salud"],
    ["Mascarillas", 0.80, 300, "2026-12-31", "Salud"],
    ["Alcohol", 2.50, 150, "2026-06-30", "Salud"],
    ["Termometro", 15.00, 20, "2030-01-01", "Salud"],
    ["Venda", 2.00, 100, "2027-03-01", "Salud"],
    ["Curitas", 1.50, 250, "2026-09-15", "Salud"],
    ["Jabon", 3.20, 180, "2027-12-01", "Salud"],
    ["Shampoo", 5.50, 80, "2028-06-10", "Salud"],
    ["Vitaminas", 12.00, 50, "2029-02-20", "Salud"]
]

# FUNCIONES

def mostrar_productos():
    print("---------------------------------------------------------------------------------")
    print("| Nro | Nombre               | Precio  | Stock  | Caducidad   | Categoria     |")
    print("---------------------------------------------------------------------------------")
    for i, product in enumerate(products):
        print(f"| {i+1:3} | {product[0]:20} | {product[1]:7} | {product[2]:6} | {product[3]:11} | {product[4]:13} |")
    print("---------------------------------------------------------------------------------")

def gestion_productos():
    print("---GESTION DE PRODUCTOS---")
    print("1. AGREGAR PRODUCTO")
    print("2. MODIFICAR STOCK DE PRODUCTO")
    print("3. VOLVER AL MENU PRINCIPAL")
    opcion = int(input("INGRESE UNA OPCION: "))
    if opcion == 1:
        agregar_producto()
    elif opcion == 2:
        modificar_stock()
    elif opcion == 3:
        return
    else:
        print("OPCION INCORRECTA")

def agregar_producto():
    nombre = input("Ingrese nombre del producto: ")
    precio = float(input("Ingrese el precio: "))
    stock = int(input("Ingrese su stock: "))
    caducidad = input("Ingrese fecha de caducidad (YYYY-MM-DD): ")
    categoria = input("Ingrese su categoría(Vivienda/Educacion/Alimentacion/Vestimenta/Salud): ")
    products.append([nombre, precio, stock, caducidad, categoria])
    print("PRODUCTO AGREGADO EXITOSAMENTE!!")

def modificar_stock():
    nombre = input("Ingrese el nombre del Producto que desea modificar: ")
    for product in products:
        if product[0].lower() == nombre.lower():
            print(f"Stock actual: {product[2]}")
            nuevo_stock = int(input("Ingrese el nuevo stock: "))
            product[2] = nuevo_stock
            print("Stock modificado de manera exitosa")
            return
    print("PRODUCTO NO ENCONTRADO")

def generar_promociones():
    hoy = datetime.now().date()
    promociones = []
    for product in products:
        caducidad = datetime.strptime(product[3], "%Y-%m-%d").date()
        dias_para_vencer = (caducidad - hoy).days
        if product[2] > 100 or dias_para_vencer <= 30:
            promociones.append(product[0])
    return promociones

def mostrar_promociones(promociones):
    print("\n-------------- PRODUCTOS EN PROMOCIÓN --------------")
    print("-------------------------------------------------------------")
    print("| Producto            | Stock  | Caducidad   | Descuento |")
    print("-------------------------------------------------------------")
    for product in products:
        if product[0] in promociones:
            print(f"| {product[0]:20} | {product[2]:6} | {product[3]:11} | 10%       |")
    print("-------------------------------------------------------------")

def facturacion(promociones, ventas, ventasCate, deducibles):
    total = 0
    print("\n----------------------- FACTURACIÓN -----------------------")
    while True:
        producto = input("INGRESE EL NOMBRE DEL PRODUCTO O (F) PARA FINALIZAR: ")
        if producto.lower() == 'f':
            break
        encontrado = False
        for product in products:
            if product[0].lower() == producto.lower():
                encontrado = True
                cantidad = int(input("Ingrese la cantidad: "))
                if cantidad <= product[2]:
                    precio = product[1]
                    subtotal = cantidad * precio
                    descuento = 0
                    if product[0] in promociones:
                        descuento = subtotal * 0.1
                        print("Descuento del 10% aplicado!!")
                    subtotal_final = subtotal - descuento
                    # Guardamos nombre, subtotal y categoría
                    ventas.append((product[0], subtotal_final))
                    # Actualizar ventas por categoría y sus deducibles
                    if product[4] == "Vivienda":
                        ventasCate[0] += subtotal_final
                        deducibles[0] += subtotal_final * 0.325
                    elif product[4] == "Educacion":
                        ventasCate[1] += subtotal_final
                        deducibles[1] += subtotal_final * 0.325
                    elif product[4] == "Alimentacion":
                        ventasCate[2] += subtotal_final
                        deducibles[2] += subtotal_final * 0.325
                    elif product[4] == "Vestimenta":
                        ventasCate[3] += subtotal_final
                        deducibles[3] += subtotal_final * 0.325
                    elif product[4] == "Salud":
                        ventasCate[4] += subtotal_final
                        deducibles[4] += subtotal_final * 1.3
                    
                    product[2] -= cantidad
                    total += subtotal_final
                    print(f"| {product[0]:20} | {cantidad:8} | {subtotal:8.2f} | {descuento:10.2f} |")
                else:
                    print("Stock insuficiente")
                    print(f"Disponible: {product[2]}")
                break
        if not encontrado:
            print("Producto inexistente, intente de nuevo!")
    iva = total * 0.12
    total += iva
    print(f"\nSubtotal: ${total - iva:.2f}")
    print(f"IVA (12%): ${iva:.2f}")
    print(f"Total a Pagar: ${total:.2f}")
    print("\n------------------ DEDUCIBLES POR CATEGORIA ------------------")
    print(f"Vivienda: ${deducibles[0]:.2f}")
    print(f"Educación: ${deducibles[1]:.2f}")
    print(f"Alimentación: ${deducibles[2]:.2f}")
    print(f"Vestimenta: ${deducibles[3]:.2f}")
    print(f"Salud: ${deducibles[4]:.2f}")
    print("----------------------------------------------------------")

def estadisticas(ventas, ventasCate):
    print("\n-------------------- ESTADÍSTICAS DE VENTAS --------------------")
    totalVentas = 0

    # VENTAS POR PRODUCTO
    print("\n------------------ VENTAS POR PRODUCTO ------------------")
    print("| {:<20} | {:<10} |".format("Producto", "Total Ventas"))
    print("--------------------------------------------------------")
    
    # Crear un diccionario para acumular ventas por producto
    # El diccionario contiene un valor y una clave
    ventas_por_producto = {}
    for nombre, subtotal in ventas:
        if nombre in ventas_por_producto:
            ventas_por_producto[nombre] += subtotal
        else:
            ventas_por_producto[nombre] = subtotal
    
    # Mostrar ventas por producto
    for nombre, subtotal in ventas_por_producto.items():
        print("| {:<20} | ${:<9.2f} |".format(nombre, subtotal))
        totalVentas += subtotal
    print("--------------------------------------------------------")

    # VENTAS POR CATEGORIA
    print("\n------------------ VENTAS POR CATEGORIA -----------------")
    print("| {:<15} | {:<10} |".format("Categoria", "Total Ventas"))
    print("--------------------------------------------------------")
    categorias = ["Vivienda", "Educacion", "Alimentacion", "Vestimenta", "Salud"]
    for i, categoria in enumerate(categorias):
        print("| {:<15} | ${:<9.2f} |".format(categoria, ventasCate[i]))
    print("--------------------------------------------------------")

    # VENTAS TOTALES
    print("\n{:<20}: ${:.2f}".format("VENTAS TOTALES", totalVentas))
    print("--------------------------------------------------------")
    
if __name__ == "__main__":
    main()

    #
