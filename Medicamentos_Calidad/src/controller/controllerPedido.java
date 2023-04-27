/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import model.modelPedido;
import view.viewPedido;
import view.viewResumen;

/**
 *
 * @author Bryan
 */
public class controllerPedido {

    private modelPedido moPedido;
    private viewPedido viPedido;

    public controllerPedido(modelPedido moPedido, viewPedido viPedido) {
        this.moPedido = moPedido;
        this.viPedido = viPedido;
        viPedido.setLocationRelativeTo(null);
        cargarTipos();
        viPedido.setVisible(true);
        viPedido.setTitle("Pedido");
    }

    public void inicialControl() {
        viPedido.getBtnOK().addActionListener(l -> cargarDatos());
    }

    public void cargarDatos() {

        validaciones mivalidacion = new validaciones();

        if (validar()) {
            String nombre = viPedido.getTxtNombre().getText(),
                    tipo = "",
                    distribuidor = "",
                    sucursal = "",
                    cant = String.valueOf(viPedido.getTxtCantidad().getText());
            int cantidad = Integer.parseInt(cant);

            if (viPedido.getCbTipo().getSelectedIndex() != viPedido.getCbTipo().getItemCount() - 1) {
                tipo = viPedido.getCbTipo().getSelectedItem().toString();
            }

            if (viPedido.getRbDistribuidor1().isSelected()) {
                distribuidor = "COFARMA";
            } else {
                if (viPedido.getRbDistribuidor2().isSelected()) {
                    distribuidor = "EMPSEPHAR";
                } else {
                    if (viPedido.getRbDistribuidor3().isSelected()) {
                        distribuidor = "CEMEFAR";
                    } else {
                        JOptionPane.showMessageDialog(viPedido, "Seleccione el distribuidor del medicamento");
                    }
                }
            }

            if (viPedido.getCheckPrincipal().isSelected() && viPedido.getCheckSecundaria().isSelected()) {
                sucursal = viPedido.getCheckPrincipal().getText() + ", " + viPedido.getCheckSecundaria().getText();
            } else {
                if (viPedido.getCheckPrincipal().isSelected()) {
                    sucursal = viPedido.getCheckPrincipal().getText();
                } else {
                    if (viPedido.getCheckSecundaria().isSelected()) {
                        sucursal = viPedido.getCheckSecundaria().getText();
                    }
                }
            }
//
            modelPedido pedido = new modelPedido();
            pedido.setCantidad(cantidad);
            pedido.setDistribuidor(distribuidor);
            pedido.setNombre(nombre);
            pedido.setSucursal(sucursal);
            pedido.setTipo(tipo);

            viewResumen viResumen = new viewResumen();

            controllerResumen controlResumen = new controllerResumen(pedido, viResumen, viPedido);
            controlResumen.inicialControl();

        }
    }

    public boolean validar() {
        boolean ban = true;

        validaciones mivalidacion = new validaciones();

        //NOMBRE
        if (!viPedido.getTxtNombre().getText().isEmpty()) {
            if (!mivalidacion.validarNombApeEspacios(viPedido.getTxtNombre().getText())) {
                JOptionPane.showMessageDialog(viPedido, "Nombre invalido");
                ban = false;
            }
        } else {
            JOptionPane.showMessageDialog(viPedido, "Ingrese el nombre");
            ban = false;
        }

        //TIPO
        if (viPedido.getCbTipo().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(viPedido, "Seleccione el tipo de medicamento");
            ban = false;
        }

        //CANTIDAD
        if (!viPedido.getTxtCantidad().getText().isEmpty()) {
            if (!mivalidacion.validarInt(viPedido.getTxtCantidad().getText())) {
                JOptionPane.showMessageDialog(viPedido, "Ingrese una cantidad entera");
                ban = false;
            } else {
                if (Integer.parseInt(viPedido.getTxtCantidad().getText()) <= 0) {
                    JOptionPane.showMessageDialog(viPedido, "Ingrese una cantidad mayor a 0");
                    ban = false;
                }
            }
        } else {
            JOptionPane.showMessageDialog(viPedido, "Ingrese la cantidad de medicamento");
            ban = false;
        }

        //DISTRIBUIDOR
        if (!viPedido.getRbDistribuidor1().isSelected() && !viPedido.getRbDistribuidor2().isSelected() && !viPedido.getRbDistribuidor3().isSelected()) {
            ban = false;
            JOptionPane.showMessageDialog(viPedido, "Seleccione un distribuidor");
        }

        //SUCURSAR
        if (!viPedido.getCheckPrincipal().isSelected() && !viPedido.getCheckSecundaria().isSelected()) {
            ban = false;
            JOptionPane.showMessageDialog(viPedido, "Seleccione almenos una sucursal");
        }

        return ban;
    }

    public void cargarTipos() {
        ArrayList<String> tipos = new ArrayList<>();
        tipos.add("Seleccione una opcion");
        tipos.add("analgésico");
        tipos.add("analéptico");
        tipos.add("anestésico");
        tipos.add("antiácido");
        tipos.add("antidepresivo");
        tipos.add("antibióticos");

        for (String datos : tipos) {
            viPedido.getCbTipo().addItem(datos);
        }
    }

    public void limpiarDatos() {
        viPedido.getTxtNombre().setText("");
        viPedido.getCbTipo().setSelectedIndex(0);
        viPedido.getTxtCantidad().setText("");
        viPedido.getBtnGDistribuidor().clearSelection();
        viPedido.getCheckPrincipal().setSelected(false);
        viPedido.getCheckSecundaria().setSelected(false);
    }

}
