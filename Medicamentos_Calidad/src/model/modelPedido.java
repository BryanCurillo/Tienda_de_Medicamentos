/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Bryan
 */
public class modelPedido extends pedido {

    modelPGconexion mpgc = new modelPGconexion();

    public boolean setPedido() {
        String sql = "INSERT INTO public.pedido("
                + "	nombre, tipo, cantidad, distribuidor, sucursal) "
                + "	VALUES ('"+getNombre()+"', '"+getTipo()+"', "+getCantidad()+", '"+getDistribuidor()+"', '"+getSucursal()+"');";
        return mpgc.accion(sql);//EJECUTAMOS EN INSERT
    }
}
