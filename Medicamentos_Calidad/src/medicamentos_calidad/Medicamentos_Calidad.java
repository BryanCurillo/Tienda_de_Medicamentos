/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package medicamentos_calidad;

/**
 *
 * @author Bryan
 */
public class Medicamentos_Calidad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        model.modelPedido modeloP = new model.modelPedido();
        view.viewPedido vistaP = new view.viewPedido();
        controller.controllerPedido controllerL = new controller.controllerPedido(modeloP, vistaP);
        controllerL.inicialControl();
    }

}
