/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JOptionPane;
import model.modelPedido;
import view.viewPedido;
import view.viewResumen;

/**
 *
 * @author Bryan
 */
public class controllerResumen {

    private modelPedido moPedido;
    private viewResumen viResumen;
    private viewPedido viPedido;

    public controllerResumen(modelPedido moPedido, viewResumen viResumen, viewPedido viPedido) {
        this.moPedido = moPedido;
        this.viResumen = viResumen;
        this.viPedido = viPedido;
        viResumen.setLocationRelativeTo(null);
        viResumen.setVisible(true);
        resumen(moPedido);
    }

    public void inicialControl() {
        viResumen.getBtnAceptar().addActionListener(l -> registrar());
        viResumen.getBtnCancelar().addActionListener(l->cancelar());
    }

    public void resumen(modelPedido pedido) {

        String txt1 = pedido.getCantidad() + " unidades de " + pedido.getTipo() + " " + pedido.getNombre(),
                direcP = "Calle de la Rosa n. 28",
                direcS = "Calle Alcazabilla n. 3",
                txt2 = "";
        if (viPedido.getCheckPrincipal().isSelected()) {
            txt2 = "Para la farmacia situada en la " + direcP;
        }
        if (viPedido.getCheckSecundaria().isSelected()) {
            txt2 = "Para la farmacia situada en la " + direcS;
        }
        if (viPedido.getCheckPrincipal().isSelected() && viPedido.getCheckSecundaria().isSelected()) {
            txt2 = "Para la farmacia situada en la " + direcP + " y para la situada en la " + direcS;
        }

        viResumen.setTitle("Pedido al distribuidor " + pedido.getDistribuidor());

        viResumen.getTxt1().setText(txt1);
        viResumen.getTxt2().setText(txt2);
    }

    public void registrar() {
        System.out.println("nombre= " + moPedido.getNombre());
        if (moPedido.setPedido()) {
            JOptionPane.showMessageDialog(viPedido, "Pedido registrado exitosamente");
            viResumen.dispose();
            limpiarDatos();
        } else {
            JOptionPane.showMessageDialog(viPedido, "El pedido no se pudo registrar con exito");

        }
    }
    public void cancelar(){
        viResumen.dispose();
        limpiarDatos();
    }

    public void limpiarDatos() {
        viResumen.getTxt1().setText("");
        viResumen.getTxt2().setText("");
        viResumen.setTitle("Pedido");
        viPedido.getTxtNombre().setText("");
        viPedido.getCbTipo().setSelectedIndex(0);
        viPedido.getTxtCantidad().setText("");
        viPedido.getBtnGDistribuidor().clearSelection();
        viPedido.getCheckPrincipal().setSelected(false);
        viPedido.getCheckSecundaria().setSelected(false);
    }

}
