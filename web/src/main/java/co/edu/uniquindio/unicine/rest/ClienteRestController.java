package co.edu.uniquindio.unicine.rest;

import co.edu.uniquindio.unicine.dto.Mensaje;
import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/clientes")
public class ClienteRestController {

    @Autowired
    private ClienteServicio clienteServicio;


    @PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestBody String email, @RequestBody String contrasena) {
        try {
            Cliente cliente = clienteServicio.iniciarSesion(email, contrasena);
            return ResponseEntity.status(200).body(cliente);
        } catch (Exception e) {
            return ResponseEntity.status(500).body( new Mensaje(e.getMessage()) );
        }
    }

    @PostMapping
    public ResponseEntity<Mensaje> registrar(@RequestBody Cliente cliente) {
        try {
            clienteServicio.registrar(cliente);
            return ResponseEntity.status(201).body( new Mensaje("Cliente registrado correctamente") );
        } catch (Exception e) {
            return ResponseEntity.status(500).body( new Mensaje(e.getMessage()) );
        }
    }

    @PostMapping("/activate")
    public ResponseEntity<Mensaje> activarCuenta(@RequestBody String emailCod, @RequestBody String fechaCod) {
        try {
            clienteServicio.activarCuenta(emailCod, fechaCod);
            return ResponseEntity.status(200).body( new Mensaje("Cuenta activada correctamente") );
        } catch (Exception e) {
            return ResponseEntity.status(500).body( new Mensaje(e.getMessage()) );
        }
    }

    @PutMapping
    public ResponseEntity<Mensaje> actualizarCliente(@RequestBody Cliente cliente) {
        try{
            clienteServicio.actualizar(cliente);
            return ResponseEntity.status(200).body( new Mensaje("Cliente actualizado correctamente") );
        } catch (Exception e) {
            return ResponseEntity.status(500).body( new Mensaje(e.getMessage()) );
        }
    }

    @DeleteMapping("/{cedula}")
    public ResponseEntity<Mensaje> eliminarCliente(@PathVariable("cedula") String cedula) {
        try{
            clienteServicio.eliminar(cedula);
            return ResponseEntity.status(200).body( new Mensaje("Cliente eliminado correctamente") );
        } catch (Exception e) {
            return ResponseEntity.status(500).body( new Mensaje(e.getMessage()) );
        }
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<?> obtenerCliente(@PathVariable("cedula") String cedula) {
        try {
            Cliente cliente = clienteServicio.obtener(cedula);
            return ResponseEntity.status(200).body(cliente);
        } catch (Exception e) {
            return ResponseEntity.status(500).body( new Mensaje(e.getMessage()) );
        }
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteServicio.listarClientes();
    }

    @GetMapping("/{cedula}/compras")
    public ResponseEntity<?> listarCompras(@PathVariable("cedula") String cedula) {
        try {
            List<Compra> comprasCliente = clienteServicio.listarCompras(cedula);
            return ResponseEntity.status(200).body(comprasCliente);
        } catch (Exception e) {
            return ResponseEntity.status(500).body( new Mensaje(e.getMessage()) );
        }
    }




}
