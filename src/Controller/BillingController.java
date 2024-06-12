/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.BillingModel;
/**
 *
 * @author 3C Tech
 */
public class BillingController {
    private BillingModel billingModel;

    public BillingController() {
        billingModel = new BillingModel();
    }

    public void saveBillingData(double subTotal, double tax, double total) {
        billingModel.saveBillingData(subTotal, tax, total);
    }
}
