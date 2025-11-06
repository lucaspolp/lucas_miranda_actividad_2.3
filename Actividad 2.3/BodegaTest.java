package test;

import modelo.Bodega;
import modelo.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BodegaTest {
    private Bodega bodega;
    
    @BeforeEach
    void setUp() {
        bodega = new Bodega();
    }
    
    @Test
    void testAgregarProductoDuplicado() {
        // Arrange
        bodega.agregarProducto("Laptop", 5);
        
        // Act
        boolean resultado = bodega.agregarProducto("Laptop", 10);
        
        // Assert
        assertFalse(resultado, "No debería permitir agregar producto duplicado");
    }
    
    @Test
    void testAgregarStockSumaCorrectamente() {
        // Arrange
        bodega.agregarProducto("Laptop", 7);
        
        // Act
        boolean resultado = bodega.agregarStock("Laptop", 5);
        
        // Assert
        assertTrue(resultado, "Debería agregar stock correctamente");
        Producto producto = bodega.buscarProducto("Laptop");
        assertNotNull(producto, "El producto debería existir");
        assertEquals(12, producto.getStock(), "7 + 5 debería ser 12");
    }
    
    @Test
    void testRestarStockRestaCorrectamente() {
        // Arrange
        bodega.agregarProducto("Mouse", 10);
        
        // Act
        boolean resultado = bodega.restarStock("Mouse", 3);
        
        // Assert
        assertTrue(resultado, "Debería restar stock correctamente");
        Producto producto = bodega.buscarProducto("Mouse");
        assertNotNull(producto, "El producto debería existir");
        assertEquals(7, producto.getStock(), "10 - 3 debería ser 7");
    }
    
    @Test
    void testRestarStockNoPermiteNegativos() {
        // Arrange
        bodega.agregarProducto("Teclado", 10);
        
        // Act
        boolean resultado = bodega.restarStock("Teclado", 11);
        
        // Assert
        assertFalse(resultado, "No debería permitir stock negativo");
        Producto producto = bodega.buscarProducto("Teclado");
        assertNotNull(producto, "El producto debería existir");
        assertEquals(10, producto.getStock(), "El stock debería permanecer en 10");
    }
    
    @Test
    void testAgregarStockCantidadNegativa() {
        // Arrange
        bodega.agregarProducto("Monitor", 5);
        
        // Act
        boolean resultado = bodega.agregarStock("Monitor", -1);
        
        // Assert
        assertFalse(resultado, "No debería permitir agregar cantidad negativa");
    }
    
    @Test
    void testGenerarReporteInventarioVacio() {
        // Act
        String reporte = bodega.generarReporteInventario();
        
        // Assert
        assertTrue(reporte.contains("No hay productos"));
    }
}